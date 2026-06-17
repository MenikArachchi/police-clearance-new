package lk.icta.commonuser.framework.app.business;

import java.sql.Connection;

import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.PasswordService;
import lk.icta.commonuser.framework.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginBusiness.
 */
public final class UserLoginBusiness {

	/** The instance. */
	private static UserLoginBusiness instance;

	// Singleton instance
	/**
	 * Instantiates a new user login business.
	 */
	private UserLoginBusiness() {

	}

	/**
	 * Gets the single instance of UserLoginBusiness.
	 * 
	 * @return single instance of UserLoginBusiness
	 */
	public static synchronized UserLoginBusiness getInstance() {
		if (instance == null) {
			instance = new UserLoginBusiness();
		}
		return instance;
	}

	/**
	 * Encrypt password.
	 * 
	 * @param password
	 *            the password
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	public String encryptPassword(String password) throws BusinessException {
		PasswordService pwdService = PasswordService.getInstance();
		String encrPassword = "";
		try {
			encrPassword = pwdService.encrypt(password);
		} catch (Exception ex) {
			throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
		}
		return encrPassword;
	}

	/**
	 * Validate user.
	 * 
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the user vo
	 * @throws BusinessException
	 *             the business exception
	 */
	public UserVO validateUser(String userName, String password)
			throws BusinessException {
		Connection dbCon = null;
		CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
		UserVO loggedInUser = null;
		String encrPassword = "";

		try {
			dbCon = DatabaseManager.getCOMMUSRConnection();
			encrPassword = this.encryptPassword(password);
			loggedInUser = commonUserDAO.validateUser(userName, encrPassword, dbCon);
			if (loggedInUser == null) {
				throw new BusinessException(ErrorCodeConstant.LOGIN_FAILED);
			}
			
			// check case-sensitive user name
			if (!userName.equals(loggedInUser.getUserName())) {
				throw new BusinessException(ErrorCodeConstant.LOGIN_FAILED);
			}
			
			
		} catch (BusinessException bex) {
			throw bex;
		} catch (Exception ex) {
			throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
		} finally {
			DatabaseManager.close(dbCon);
		}

		return loggedInUser;
	}
}
