package lk.icta.commonuser.dept.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import lk.icta.commonuser.framework.constant.CommonUserConstant;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.vo.UserVO;
import org.apache.log4j.Logger;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -384351385648411403L;
	private static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String returnString = null;
		try {
			String currentAction = invocation.getProxy().getActionName();
			
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Currently processing action--" + currentAction);
			}
			if (!CommonUserConstant.EXCLUDE_SESSION_CHECK.contains(currentAction)) {
				// Get the session object from the invocation
				final Map<String, Object> sessionMap = invocation.getInvocationContext()
						.getSession();

				// Is there a UserVO object stored in the user's HttpSession?
				UserVO user = (UserVO) sessionMap.get(SessionConstant.USER_DETAILS);
				if (user == null) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("user not object found");
					}
					
					returnString = "SESSION_EXPIRED";
				} 
			}
			if (returnString == null) {
				returnString = invocation.invoke();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return returnString;
	}

}
