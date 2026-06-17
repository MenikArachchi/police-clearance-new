package lk.icta.commonuser.dept.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lk.icta.commonuser.framework.app.business.UserOperationBusiness;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperation;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperationResult;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLogoutAction.
 */
public class UserLogoutAction extends ActionSupport implements SessionAware,
		ServletRequestAware {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserLogoutAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session. */
	private Map<String, Object> session;

	/** The request. */
	private HttpServletRequest request;

	// @SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		String retStr = "LOGOUTFAIL";

		if (session == null || session.get(SessionConstant.USER_DETAILS) == null) {

			return "SESSION_EXPIRED";
		}
		UserVO loggedOnUser = (UserVO) session.get(SessionConstant.USER_DETAILS);
		int userId = loggedOnUser.getId();

		try {
			session.clear();
			request.getSession().invalidate();
			this.setUserOperation(UserOperation.LOGOUT.getCode(),
					UserOperationResult.SUCCESS.getCode(), userId, null);
			retStr = "LOGOUTSUCCESS";

		} catch (Exception bex) {
			LOGGER.fatal("UserLogoutAction # execute error : " + bex);
			this.setUserOperation(UserOperation.LOGOUT.getCode(),
					UserOperationResult.FAILURE.getCode(), userId, null);
			retStr = "LOGOUTFAIL";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}