/**
 * 
 */
package lk.icta.police.web.oauth.util;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import lk.icta.police.web.app.constant.ApplicationConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * @author dvirajith
 * 
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGIN. */
	private static final String LOGIN = "login";
	
	/** The Constant VIEW_LOGIN_ACTION. */
	private static final String HOME = "showHomePage";

	/** The Constant VIEW_LOGIN_ACTION. */
	private static final String VIEW_LOGIN_ACTION = "viewOAuthLogin";

	/** The Constant OAUTH_REDIRECT_ACTION. */
	private static final String OAUTH_REDIRECT_ACTION = "redirectOAuth";

	/** The Constant SET_PROVIDER_ACTION. */
	private static final String SET_PROVIDER_ACTION = "setOAuthProvider";

	/** The Constant CHECK_LOGGED_IN_STATUS. */
	private static final String CHECK_LOGGED_IN_STATUS = "checkLoggedInStatus";

	/** The Constant INDEX_ACTION. */
	private static final String INDEX_ACTION = "index";

	/** The Constant LOGOUT_REDIRECT. */
	private static final String LOGOUT_REDIRECT = "redirectOAuthLogout";
	
//	for locale
	/** The Constant LOCALE_CHANGE. */
	private static final String LOCALE_CHANGE = "localeChange";
	
	/** The Constant APPCODE. */
	private static final String APPCODE = "appcode";
	
	/** The Constant APPCODE_VALUE. */
	private static final String APPCODE_VALUE = "cp";

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		Map<String, Object> session = (Map<String, Object>) invocation.getInvocationContext().getSession();
		
		Map<String, Object> paramMap = (Map<String, Object>) ActionContext.getContext().getParameters();
		Object localeObj = paramMap.get(SessionConstants.LANG);
		Object appcodeObj = paramMap.get(APPCODE);
		String locale="";
		String appcode="";
		
		if (localeObj != null) {
			locale = ((String[]) localeObj)[0];
			if (appcodeObj != null) {
				appcode = ((String[]) appcodeObj)[0];
			} 
		}

		if (appcode != null && APPCODE_VALUE.equals(appcode.toString())) {
			invocation.getInvocationContext().getSession().put(SessionConstants.APP_CODE, appcode);
			ServletActionContext.getRequest().getSession().setAttribute(SessionConstants.APP_CODE, appcode);
		}
//		System.out.println("appcode :" + appcode);
//		System.out.println("SESSION_ID :" + ServletActionContext.getRequest().getSession().getId());
		
		//check if its a valid authenticated request
		System.out.println("session.get(SessionConstants.EMAIL)--->"+session.get(SessionConstants.EMAIL));
		if (session == null || session.get(SessionConstants.EMAIL) == null || "".equals(session.get(SessionConstants.EMAIL))) {
			invocation.getInvocationContext().getSession().put(SessionConstants.OAUTH_USED, "true");

			final String actionMethod = invocation.getProxy().getMethod();
			
			System.out.println("actionMethod--->"+actionMethod);
			
			if (!(VIEW_LOGIN_ACTION.equals(actionMethod)  || "checkAuthentication".equals(actionMethod) || "tempredirectpage".equals(actionMethod)
					|| OAUTH_REDIRECT_ACTION.equals(actionMethod)  || "locale".equals(actionMethod) || CHECK_LOGGED_IN_STATUS.equals(actionMethod) || "updateLoginFailure".equals(actionMethod) 
					|| SET_PROVIDER_ACTION.equals(actionMethod) || "sessionInvalidated".equals(actionMethod) || "unauthorized".equals(actionMethod)
					|| INDEX_ACTION.equals(actionMethod) || LOGOUT_REDIRECT.equals(actionMethod) || "indexOauth".equals(actionMethod) || "checkApplicationStatus".equals(actionMethod)
					|| "clearanceCertificateVerification".equals(actionMethod))) {
				return ApplicationConstants.INVALID_SESSION;
			} 
		}
		
		//if its a valid authenticated request, check if its a locale request
		if (locale != null && !"".equals(locale.toString())) {
			Locale currentLocale = new Locale(locale);
			ActionContext.getContext().setLocale(currentLocale);
			invocation.getInvocationContext().getSession().put(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, currentLocale);
			
			if(!(locale.equals(Language.ENGLISH.getLanguageCode()) || locale.equals(Language.SINHALA.getLanguageCode()) || locale.equals(Language.TAMIL.getLanguageCode()))){
				locale=Language.ENGLISH.getLanguageCode();
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("preferredLocale", locale);	
			
			return LOCALE_CHANGE;
		}

		return invocation.invoke();
	}
}
