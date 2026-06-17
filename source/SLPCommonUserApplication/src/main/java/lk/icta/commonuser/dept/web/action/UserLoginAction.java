package lk.icta.commonuser.dept.web.action;

import java.util.Map;

import lk.icta.commonuser.framework.app.business.UserLoginBusiness;
import lk.icta.commonuser.framework.app.business.UserOperationBusiness;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperation;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperationResult;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginAction.
 */
public class UserLoginAction extends ActionSupport implements SessionAware {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserLoginAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user vo. */
	private UserVO userVo;

	/** The session. */
	private Map<String, Object> session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		UserVO userDetails = this.userVo;

		String retStr = "LOGINFAIL";

		try {
			UserVO loggedOnUser = this.validateUser(userDetails);

			if (loggedOnUser != null) {
				session.put(SessionConstant.USER_DETAILS, loggedOnUser);
				this.setUserOperation(UserOperation.LOGIN.getCode(),
						UserOperationResult.SUCCESS.getCode(),
						loggedOnUser.getId(), null);
				retStr = "LOGINSUCCESS";
			}

		} catch (BusinessException bex) {
			LOGGER.fatal("UserLoginAction # execute error : " + bex);
			addActionError(getText(CommonUtil.getErrorKey(bex)));
			this.setUserOperation(UserOperation.LOGIN.getCode(),
					UserOperationResult.FAILURE.getCode(), 0,
					userDetails.getUserName());
			retStr = "LOGINFAIL";
		}

		return retStr;

	}

	/**
	 * New login.
	 * 
	 * @return the string
	 */
	public String newLogin() {
		return "LOGIN";
	}

	/**
	 * Sets the user vo.
	 * 
	 * @param userVo
	 *            the userVo to set
	 */
	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}

	/**
	 * Gets the user vo.
	 * 
	 * @return the userVo
	 */
	public UserVO getUserVo() {
		return userVo;
	}

	/**
	 * Validate user.
	 * 
	 * @param userDetails
	 *            the user details
	 * @return the user vo
	 * @throws BusinessException
	 *             the business exception
	 */
	public UserVO validateUser(UserVO userDetails) throws BusinessException {
		UserLoginBusiness usrLoginBusiness = UserLoginBusiness.getInstance();
		return usrLoginBusiness.validateUser(userDetails.getUserName(),
				userDetails.getPassword());
	}

	/**
	 * Sets the user operation.
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
	public void setUserOperation(int operationType, int operationResult,
			int createdBy, String userName) {
		UserOperationBusiness userOpBusiness = UserOperationBusiness
				.getInstance();
		userOpBusiness.setOperation(operationType, operationResult, createdBy,
				userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}