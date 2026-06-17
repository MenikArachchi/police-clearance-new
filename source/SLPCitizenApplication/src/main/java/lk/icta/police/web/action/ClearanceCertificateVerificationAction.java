package lk.icta.police.web.action;

import java.util.Map;

import lk.icta.police.framework.utility.CommonUtil;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ClearanceCertificateVerificationAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ClearanceCertificateVerificationAction.class);
	private Map<String,Object> session;

	private String urlPath;

	public String clearanceCertificateVerification() {
		String url=CommonUtil.getValueFromFile("police", "rest.application.url");
		setUrlPath(url);
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
