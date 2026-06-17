/**
 * 
 */
package lk.icta.police.web.oauth.util;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * @author dvirajith
 *
 */
public class ChangeLangInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant LOCALE_CHANGE. */
	private static final String LOCALE_CHANGE = "localeChange";
	
	/** The Constant APPCODE. */
	private static final String APPCODE = "appcode";
	
	/** The Constant APPCODE_VALUE. */
	private static final String APPCODE_VALUE = "cp";
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		System.out.println("||||||||||||||||||||||||||||| ChangeLangInterceptor () Method called |||||||||||||||||||||||||||||"); 

		Map<String, Object> paramMap = (Map<String, Object>) ActionContext.getContext().getParameters();
		Object localeObj = paramMap.get(SessionConstants.LANG);
		Object appcodeObj = paramMap.get(APPCODE);
		String locale, appcode;
		
//		System.out.println("======localeObj========"+localeObj);
//		System.out.println("======appcodeObj========"+appcodeObj);
		
		if (localeObj != null) {
//			System.out.println("======if========");
			locale = ((String[]) localeObj)[0];
			
			if (appcodeObj != null) {
				appcode = ((String[]) appcodeObj)[0];
//				System.out.println("-------------- if ------------ "+appcode);
			} else {
				appcode = "";
//				System.out.println("-------------- else ------------ "+appcode);
			}
		} else {
//			System.out.println("======else========");
			locale = "";
			appcode = "";
		}
		//System.out.println("#################################################");
		//System.out.println("appcode from URL:" + appcode);
		//System.out.println("#################################################");
		
		if (appcode != null && APPCODE_VALUE.equals(appcode.toString())) {
			invocation.getInvocationContext().getSession().put(SessionConstants.APP_CODE, appcode);
			ServletActionContext.getRequest().getSession().setAttribute(SessionConstants.APP_CODE, appcode);
		}

		
		//System.out.println("#################################################");
		//System.out.println("session id:" + ServletActionContext.getRequest().getSession().getId());
		//System.out.println("appcode from SESSION:" + ServletActionContext.getRequest().getSession().getAttribute(SessionConstants.APP_CODE));
		//System.out.println("appcode from SESSION MAP:" + invocation.getInvocationContext().getSession().get(SessionConstants.APP_CODE));
		//System.out.println("#################################################");
		
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
		} else {
			//System.out.println("CHANGE LOCALE :" + invocation);
			return invocation.invoke();
		}
		
		
		
	}
}
