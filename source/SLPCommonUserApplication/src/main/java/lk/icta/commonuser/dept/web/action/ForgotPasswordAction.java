package lk.icta.commonuser.dept.web.action;

import java.util.Map;

import lk.icta.commonuser.dept.web.servlet.CaptchaGeneratorServlet;
import lk.icta.commonuser.framework.app.business.ForgotPasswordBusiness;
import lk.icta.commonuser.framework.app.business.UserOperationBusiness;
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
 * The Class ForgotPasswordAction.
 */
public class ForgotPasswordAction extends ActionSupport implements SessionAware {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ForgotPasswordAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session. */
	private Map<String, Object> session;

	/** The user vo. */
	private UserVO userVo;

	/** The uword. */
	private String uword;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		return "FORGOTPWD";
	}

	/**
	 * Process new password.
	 * 
	 * @return the string
	 */
	public String processNewPassword() {
		UserVO userDetails = this.userVo;
		String retStr = "FORGOTPWDFAIL";
		int userId = 0;

		try {

			String c = (String) session
					.get(CaptchaGeneratorServlet.CAPTCHA_KEY);
			if (!getUword().equals(c)) {

				addActionError(getText("error.commonuser.invalidVerificationText"));
				this.setUserOperation(UserOperation.RESET_PASSWORD.getCode(),UserOperationResult.FAILURE.getCode(), 0,userDetails.getUserName());
			} else {
				ForgotPasswordBusiness forgotPwdBusiness = ForgotPasswordBusiness.getInstance();
				userId = forgotPwdBusiness.resetPassword(userDetails.getUserName(), userDetails.getEmailId());
				if (userId > 0) {

					addActionMessage(getText("commonuser.successForgotPwd"));
					this.setUserOperation(
							UserOperation.RESET_PASSWORD.getCode(),
							UserOperationResult.SUCCESS.getCode(), userId, null);
					retStr = "FORGOTPWDSUCESS";
				} else {

					addActionError(getText("error.commonuser.unknowError"));
					this.setUserOperation(
							UserOperation.RESET_PASSWORD.getCode(),
							UserOperationResult.FAILURE.getCode(), 0,
							userDetails.getUserName());
				}
			}
		} catch (BusinessException bex) {
			LOGGER.fatal("BusinessException in ForgotPasswordAction: "
					+ bex);
			addActionError(getText(CommonUtil.getErrorKey(bex)));
			this.setUserOperation(UserOperation.RESET_PASSWORD.getCode(),
					UserOperationResult.FAILURE.getCode(), 0,
					userDetails.getUserName());
			retStr = "FORGOTPWDFAIL";
		} catch (Exception ex) {
			LOGGER.fatal("Exception in ForgotPasswordAction: "
					+ ex);
			addActionError(getText("error.commonuser.unknowError"));
			this.setUserOperation(UserOperation.RESET_PASSWORD.getCode(),
					UserOperationResult.FAILURE.getCode(), 0,
					userDetails.getUserName());
			retStr = "FORGOTPWDFAIL";
		}
		return retStr;
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
	 * Sets the uword.
	 * 
	 * @param uword
	 *            the uword to set
	 */
	public void setUword(String uword) {
		this.uword = uword;
	}

	/**
	 * Gets the uword.
	 * 
	 * @return the uword
	 */
	public String getUword() {
		return uword;
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
