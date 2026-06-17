package lk.icta.police.external.action;

import com.opensymphony.xwork2.ActionSupport;

public class SessionInvalidateAction extends ActionSupport {
	
	private static final long serialVersionUID = -57086268316568334L;

	public String sessionInvalidated() {
		addActionError("Your session could not be verified. Please login to proceed");
		return SUCCESS;
	}

	public String unauthorized() {
		addActionError("You are not authorized to access this Page");
		return SUCCESS;
	}

}
