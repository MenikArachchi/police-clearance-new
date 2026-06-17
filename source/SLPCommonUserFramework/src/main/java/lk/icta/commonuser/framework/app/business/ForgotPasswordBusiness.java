package lk.icta.commonuser.framework.app.business;

import java.sql.Connection;

import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.utility.MailUtil;
import lk.icta.commonuser.framework.utility.PasswordService;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordBusiness.
 */
public final class ForgotPasswordBusiness {

  /** The instance. */
  private static ForgotPasswordBusiness instance;

  /** The Constant LIVE_MODE. */
  private static final String LIVE_MODE = "LIVE";

  /** The mode. */
  private static String mode;

  // Singleton instance
  /**
   * Instantiates a new forgot password business.
   */
  private ForgotPasswordBusiness() {

  }

  static {
    mode = CommonUtil.getValueFromFile("mode", "mode");
  }

  /**
   * Gets the single instance of ForgotPasswordBusiness.
   * 
   * @return single instance of ForgotPasswordBusiness
   */
  public static synchronized ForgotPasswordBusiness getInstance() {
    if (instance == null) {
      instance = new ForgotPasswordBusiness();
    }
    return instance;
  }

  /**
   * Reset password.
   * 
   * @param userName the user name
   * @param emailId the email id
   * @return the int
   * @throws BusinessException the business exception
   */
  public int resetPassword(String userName, String emailId) throws BusinessException {
    Connection dbCon = null;
    CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
    int userId = 0;
    boolean emailSent = false;
    String newPassword = "";
    String newEncrPassword = "";
    String mailSubject = "";
    String mailContent = "";

    String userFullName = "";

    try {
      dbCon = DatabaseManager.getCOMMUSRConnection();
      userFullName = commonUserDAO.getUserFullName(userName, emailId, dbCon);
      if (CommonUtil.checkBlank(userFullName)) {
        throw new BusinessException(ErrorCodeConstant.INCOMPATIBLE_USERNAME_EMAIL);
      } else {
        dbCon.setAutoCommit(false);
        newPassword = PasswordService.generateRandomPassword(10);
        newEncrPassword = PasswordService.getInstance().encrypt(newPassword);
        userId = commonUserDAO.resetPassword(userName, emailId, newEncrPassword, dbCon);
        if (userId == 0) {
          throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
        } else {
          mailSubject = CommonUtil.getValueFromFile("mail", "commonuser.mail.newPassword.subject");
          mailContent =
              CommonUtil.getValueFromFile("mail", "commonuser.mail.newPassword.content", userName, newPassword);
          if (LIVE_MODE.equals(mode)) {
            emailSent = MailUtil.sendMail(emailId, mailSubject, mailContent);
            if (!emailSent) {
              DatabaseManager.rollbackConnection(dbCon);
              throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
            }
          } else {
            emailSent = true;
          }

        }
        if ((userId > 0) && emailSent) {
          dbCon.commit();
        }
      }
    } catch (BusinessException bex) {
      if (bex.getErrorCode() != ErrorCodeConstant.INCOMPATIBLE_USERNAME_EMAIL) {
        DatabaseManager.rollbackConnection(dbCon);
      }
      throw bex;
    } catch (Exception ex) {
      DatabaseManager.rollbackConnection(dbCon);
      throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
    } finally {
      DatabaseManager.close(dbCon);
    }

    return userId;
  }

}
