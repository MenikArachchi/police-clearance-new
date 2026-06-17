package lk.icta.commonuser.dept.web.action;

import java.util.Map;

import lk.icta.commonuser.framework.app.business.ChangePasswordBusiness;
import lk.icta.commonuser.framework.app.business.UserOperationBusiness;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperation;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperationResult;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePasswordAction.
 */
public class ChangePasswordAction extends ActionSupport implements SessionAware {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ChangePasswordAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session. */
	private Map<String, Object> session;

	/** The user vo. */
	private UserVO userVo;

	/** The new password. */
	private String newPassword;

	/** The disp full name. */
	private String dispFullName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {

		if (session == null || session.get(SessionConstant.USER_DETAILS) == null) {

			addActionError(getText("commonuser.session.expired"));
			return "SESSION_EXPIRED";
		}
		UserVO loggedOnUser = (UserVO) session.get(SessionConstant.USER_DETAILS);
		userVo = new UserVO();
		userVo.setUserName(loggedOnUser.getUserName());
		setDispFullName(loggedOnUser.getFullName());
		return "CHANGEPWD";
	}

	/**
	 * Process change password.
	 * 
	 * @return the string
	 */
	public String processChangePassword() {
		String retStr = "CHANGEPWDFAIL";
		boolean passwordUpdated = false;

		if (session == null || session.get(SessionConstant.USER_DETAILS) == null) {

			addActionError(getText("commonuser.session.expired"));
			return "SESSION_EXPIRED";
		}
		UserVO loggedOnUser = (UserVO) session.get(SessionConstant.USER_DETAILS);
		UserVO userDetails = this.userVo;

		try {
			ChangePasswordBusiness changePwdBusiness = ChangePasswordBusiness
					.getInstance();
			passwordUpdated = changePwdBusiness.changePassword(
					loggedOnUser.getId(), userDetails.getPassword(),
					newPassword);
			if (passwordUpdated) {

				addActionMessage(getText("commonuser.changepasswordSucess"));
				this.setUserOperation(UserOperation.CHANGE_PASSWORD.getCode(),
						UserOperationResult.SUCCESS.getCode(),
						loggedOnUser.getId(), null);
				retStr = "CHANGEPWDSUCESS";
			} else {

				addActionError(getText("error.commonuser.unknowError"));
				this.setUserOperation(UserOperation.CHANGE_PASSWORD.getCode(),
						UserOperationResult.FAILURE.getCode(),
						loggedOnUser.getId(), null);
				retStr = "CHANGEPWDFAIL";
			}
		} catch (BusinessException bex) {
			LOGGER.fatal("ChangePasswordAction # processChangePassword error : "
					+ bex);

			addActionError(getText(CommonUtil.getErrorKey(bex)));
			this.setUserOperation(UserOperation.CHANGE_PASSWORD.getCode(),
					UserOperationResult.FAILURE.getCode(),
					loggedOnUser.getId(), null);
			retStr = "CHANGEPWDFAIL";
		}
		userVo.setUserName(loggedOnUser.getUserName());
		setDispFullName(loggedOnUser.getFullName());
		return retStr;
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
	 * Sets the new password.
	 * 
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the new password.
	 * 
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
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

	/**
	 * Sets the disp full name.
	 * 
	 * @param dispFullName
	 *            the dispFullName to set
	 */
	public void setDispFullName(String dispFullName) {
		this.dispFullName = dispFullName;
	}

	/**
	 * Gets the disp full name.
	 * 
	 * @return the dispFullName
	 */
	public String getDispFullName() {
		return dispFullName;
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
