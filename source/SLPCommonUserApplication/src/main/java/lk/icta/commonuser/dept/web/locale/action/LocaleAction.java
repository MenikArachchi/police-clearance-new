package lk.icta.commonuser.dept.web.locale.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lk.icta.commonuser.framework.constant.SessionConstant;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class LocaleAction.
 */
public class LocaleAction extends ActionSupport implements SessionAware,
		ServletRequestAware {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2346916247023488574L;

	/** The session. */
	@SuppressWarnings("rawtypes")
	private Map session;

	/** The request. */
	private transient HttpServletRequest request;

	// business logic
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		String retPage = "";
		String localeValue = request.getParameter("request_locale");
		session.put(SessionConstant.LOCALE_VALUE, localeValue);
		request = ServletActionContext.getRequest();
		HttpSession thisSession = request.getSession(false);
		if (thisSession != null) {
			if (thisSession.getAttribute(SessionConstant.USER_DETAILS) == null) {
				retPage = "LOGIN";
			} else {
				retPage = "PAGE";
			}
		} else {
			retPage = "LOGIN";
		}
		return retPage;
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

	/**
	 * Gets the servlet request.
	 * 
	 * @return the servlet request
	 */
	public HttpServletRequest getServletRequest() {
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}
}
