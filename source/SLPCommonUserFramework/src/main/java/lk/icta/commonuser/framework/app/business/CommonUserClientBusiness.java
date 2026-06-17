package lk.icta.commonuser.framework.app.business;

import java.sql.Connection;
import java.sql.SQLException;

import lk.icta.commonuser.app.dao.CommonUserClientDAO;
import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.utility.MailUtil;
import lk.icta.commonuser.framework.utility.PasswordService;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;

public class CommonUserClientBusiness {
  private static final Logger LOGGER = Logger.getLogger(CommonUserClientBusiness.class);



  /**
   * Validate user.
   * 
   * @param userName the user name
   * @param password the password
   * @param deptId the dept id
   * @param applicationId
   * @return the user dto
   * @throws LoginFaultException the login fault exception
   */
  public UserVO validateUser(String userName, String password, int deptId, int applicationId) throws BusinessException {
    Connection dbCon = null;
    CommonUserClientDAO userLoginDao = CommonUserClientDAO.getInstance();
    UserVO loggedInUser = null;

    try {
      // variable hold the encrypted password
      String encPassword = PasswordService.getInstance().encrypt(password);

      dbCon = DatabaseManager.getCOMMUSRConnection();
      loggedInUser = userLoginDao.validateUser(userName, encPassword, deptId, applicationId, dbCon);

    } catch (SQLException ex) {
      LOGGER.error("SQLException in validateUser : SQLException - " + ex);
      throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
    } catch (BusinessException ex) {
      LOGGER.error("Exception in validateUser : BusinessException - " + ex.getErrorCode());
      if (ex.getErrorCode() == ErrorCodeConstant.AUTHONTICATION_ERROR) {
        throw new BusinessException(ErrorCodeConstant.AUTHONTICATION_ERROR);
      } else if (ex.getErrorCode() == ErrorCodeConstant.INACTIVE_USER) {
        throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
      } else if (ex.getErrorCode() == ErrorCodeConstant.LOGIN_FAILED) {
        throw new BusinessException(ErrorCodeConstant.LOGIN_FAILED);
      } else {
        throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
      }
    } catch (Exception ex) {
      LOGGER.error("Exception in validateUser : Exception - " + ex.getMessage());
      throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
    } finally {
      DatabaseManager.close(dbCon);
    }

    return loggedInUser;
  }

  /**
   * Change password.
   * 
   * @param userId the user id
   * @param oldPassword the old password
   * @param newPassword the new password
   * @return true, if successful
   * @throws ChangePasswordFaultException the change password fault exception
   */
  public boolean changePassword(int userId, String oldPassword, String newPassword) throws BusinessException {
    Connection dbCon = null;
    CommonUserClientDAO changePwdDao = CommonUserClientDAO.getInstance();
    boolean pwdValidated = false;
    boolean pwdSet = false;
    boolean successFlag = false;
    boolean isRollBackReqd = false;
    try {
      // variables hold the encrypted password
      String encPassword = PasswordService.getInstance().encrypt(oldPassword);
      String encNewPassword = PasswordService.getInstance().encrypt(newPassword);

      dbCon = DatabaseManager.getCOMMUSRConnection();
      pwdValidated = changePwdDao.validateUser(userId, encPassword, dbCon);

      if (!pwdValidated) {
        throw new BusinessException(ErrorCodeConstant.INCORRECT_PASSWORD);
      } else {
        isRollBackReqd = true;
        dbCon.setAutoCommit(false);
        pwdSet = changePwdDao.changePassword(userId, encNewPassword, dbCon);
        if (!pwdSet) {
          throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
        }
      }
      if (pwdValidated && pwdSet) {
        dbCon.commit();
        successFlag = true;
      }
    } catch (BusinessException bx) {
      bx.printStackTrace();
      if (isRollBackReqd) {
        DatabaseManager.rollbackConnection(dbCon);
      }
      throw bx;
    } catch (Exception ex) {
      DatabaseManager.rollbackConnection(dbCon);
      throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
    } finally {
      DatabaseManager.close(dbCon);
    }

    return successFlag;
  }

  /**
   * Reset password.
   * 
   * @param userName the user name
   * @param emailId the email id
   * @return true, if successful
   * @throws ResetPasswordFaultException the reset password fault exception
   */
  public boolean resetPassword(String userName, String emailId) throws BusinessException {
    Connection dbCon = null;
    CommonUserClientDAO forgotPwdDao = CommonUserClientDAO.getInstance();
    boolean pwdReset = false;
    boolean emailSent = false;
    boolean successFlag = false;
    String newEncrPassword = "";
    String newPassword = "";
    String mailSubject = "";
    String mailContent = "";
    boolean isRollBackReqd = false;
    String userFullName = "";
    String mailTitle = "";

    try {
      dbCon = DatabaseManager.getCOMMUSRConnection();
      userFullName = forgotPwdDao.getUserFullName(userName, emailId, dbCon);
      if (CommonUtil.checkBlank(userFullName)) {
        throw new BusinessException(ErrorCodeConstant.INCOMPITABLE_USERNAME_EMAIL);
      } else {
        isRollBackReqd = true;
        dbCon.setAutoCommit(false);
        newPassword = PasswordService.generateRandomPassword(10);
        newEncrPassword = PasswordService.getInstance().encrypt(newPassword);
        pwdReset = forgotPwdDao.resetPassword(userName, emailId, newEncrPassword, dbCon);
        if (!pwdReset) {
          throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
        } else {
          mailTitle = CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.newPassword.title");
          mailSubject = CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.newPassword.subject");
          mailContent =
              CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.newPassword.content", userName,
                  newPassword);

          emailSent = MailUtil.sendEmailMessage(mailTitle, mailSubject, mailContent, emailId, userFullName);
          if (!emailSent) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
            /*
             * throw new ResetPasswordFaultException( "error.commonuser.unknowError");1055
             */
          }
        }
        if (pwdReset && emailSent) {
          dbCon.commit();
          successFlag = true;
        }
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      if (isRollBackReqd) {
        DatabaseManager.rollbackConnection(dbCon);
      }
      throw be;
    } catch (Exception ex) {
      DatabaseManager.rollbackConnection(dbCon);
      if (ex.getMessage().equals(ErrorCodeConstant.INACTIVE_USER)) {
        throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
      } else if (ex.getMessage().equals(ErrorCodeConstant.INACTIVE_USER)) {
        throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
      } else {
        throw new BusinessException(ex);
      }
    } finally {
      DatabaseManager.close(dbCon);
    }

    return successFlag;
  }

  /**
   * Gets the user details.
   * 
   * @param selectedUser the selected user
   * @return the user details
   * @throws BusinessException the business exception
   */
  public UserVO getUserDetails(UserVO selectedUser) throws BusinessException {
    Connection dbCon = null;
    CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
    UserVO userDetail = null;

    try {
      dbCon = DatabaseManager.getCOMMUSRConnection();
      userDetail = commonUserDAO.getUserDetails(dbCon, selectedUser);
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception ex) {
      throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
    } finally {
      DatabaseManager.close(dbCon);
    }

    return userDetail;
  }
}
