package lk.icta.police.web.oauth.action;

import java.io.Serializable;
import java.util.Map;

import lk.icta.police.web.oauth.util.ResourceMessage;
import lk.icta.police.web.oauth.util.ResourceMessageImpl;


//import lk.icta.sample.util.ResourceMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action for Sample OAuth Application.
 * 
 * @author dvirajith
 * 
 */
public class SampleBaseAction extends ActionSupport implements	Serializable, SessionAware {

	private static final long serialVersionUID = 4223604101453665381L;
	private transient final Log log = LogFactory.getLog(SampleBaseAction.class);

	protected ResourceMessage resourceMessage = new ResourceMessageImpl();
	private Map<Object, Object> sessionMap = null;

	/**
	 * Returns a map representing the HTTP session.
	 * 
	 * @return A map representing the HTTP session.
	 */
	public Map<Object, Object> getSession() {
		return this.sessionMap;
	}

	public ResourceMessage getResourceMessage() {
		return resourceMessage;
	}

	public void setResourceMessage(ResourceMessage resourceMessage) {
		this.resourceMessage = resourceMessage;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map map) {
		this.sessionMap = map;
	}
}
