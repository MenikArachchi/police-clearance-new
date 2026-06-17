package lk.icta.commonuser.framework.app.business;

import java.sql.Connection;

import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.PasswordService;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePasswordBusiness.
 */
public final class ChangePasswordBusiness {

	/** The instance. */
	private static ChangePasswordBusiness instance;

	// Singleton instance
	/**
	 * Instantiates a new change password business.
	 */
	private ChangePasswordBusiness() {

	}

	/**
	 * Gets the single instance of ChangePasswordBusiness.
	 * 
	 * @return single instance of ChangePasswordBusiness
	 */
	public static synchronized ChangePasswordBusiness getInstance() {
		if (instance == null) {
			instance = new ChangePasswordBusiness();
		}
		return instance;
	}

	/**
	 * Change password.
	 * 
	 * @param userId
	 *            the user id
	 * @param oldPassword
	 *            the old password
	 * @param newPassword
	 *            the new password
	 * @return true, if successful
	 * @throws BusinessException
	 *             the business exception
	 */
	public boolean changePassword(int userId, String oldPassword,
			String newPassword) throws BusinessException {
		Connection dbCon = null;
		CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
		boolean pwdValidated = false;
		boolean pwdSet = false;
		boolean successFlag = false;
		String oldEncrPassword = "";
		String newEncrPassword = "";

		try {
			dbCon = DatabaseManager.getCOMMUSRConnection();
			oldEncrPassword = PasswordService.getInstance()
					.encrypt(oldPassword);
			pwdValidated = commonUserDAO.validateUser(userId, oldEncrPassword,
					dbCon);
			if (!pwdValidated) {
				throw new BusinessException(ErrorCodeConstant.INCORRECT_PASSWORD);
			} else {
				dbCon.setAutoCommit(false);
				newEncrPassword = PasswordService.getInstance().encrypt(
						newPassword);
				pwdSet = commonUserDAO.changePassword(userId, newEncrPassword,
						dbCon);
				if (!pwdSet) {
					throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
				}
			}
			if (pwdValidated && pwdSet) {
				dbCon.commit();
				successFlag = true;
			}
		} catch (BusinessException bex) {
			if (bex.getErrorCode() != ErrorCodeConstant.INCORRECT_PASSWORD) {
				DatabaseManager.rollbackConnection(dbCon);
			}
			throw bex;
		} catch (Exception ex) {
			DatabaseManager.rollbackConnection(dbCon);
			throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
		} finally {
			DatabaseManager.close(dbCon);
		}

		return successFlag;
	}

}
