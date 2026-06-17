package lk.icta.police.external.action;

import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -8517227229292215672L;
	private static final Logger LOGGER = Logger.getLogger(HomeAction.class);
	private Map<String, Object> session;
	
	public String execute(){
		
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
