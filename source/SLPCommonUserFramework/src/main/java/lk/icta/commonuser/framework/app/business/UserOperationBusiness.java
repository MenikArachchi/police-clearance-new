package lk.icta.commonuser.framework.app.business;

import java.sql.Connection;

import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperation;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperationResult;
import lk.icta.commonuser.framework.database.DatabaseManager;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class UserOperationBusiness.
 */
public final class UserOperationBusiness {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserOperationBusiness.class);

	/** The instance. */
	private static UserOperationBusiness instance;

	// Singleton instance
	/**
	 * Instantiates a new user operation business.
	 */
	private UserOperationBusiness() {

	}

	/**
	 * Gets the single instance of UserOperationBusiness.
	 * 
	 * @return single instance of UserOperationBusiness
	 */
	public static synchronized UserOperationBusiness getInstance() {
		if (instance == null) {
			instance = new UserOperationBusiness();
		}
		return instance;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operationType
	 *            the operation type
	 * @param operationResult
	 *            the operation result
	 * @param createdBy
	 *            the created by
	 * @param userName
	 *            the user name
	 */
	public void setOperation(int operationType, int operationResult,
			int createdBy, String userName) {
		Connection dbCon = null;
		CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
		boolean operationSet = false;

		try {
			dbCon = DatabaseManager.getCOMMUSRConnection();
			dbCon.setAutoCommit(false);
			if (operationType == UserOperation.LOGIN.getCode()
					|| operationType == UserOperation.RESET_PASSWORD.getCode()) {
				if (operationResult == UserOperationResult.FAILURE.getCode()) {
					operationSet = commonUserDAO.setOperation(operationType,
							operationResult, 0, userName, dbCon);
				} else {
					operationSet = commonUserDAO.setOperation(operationType,
							operationResult, createdBy, null, dbCon);
				}
			} else {
				operationSet = commonUserDAO.setOperation(operationType,
						operationResult, createdBy, null, dbCon);
			}
			if (operationSet) {
				dbCon.commit();
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("UserOperationBusiness # setOperation error : "
						+ ex);
			}

		} finally {
			DatabaseManager.close(dbCon);
		}
	}

}
