package lk.icta.police.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class SessionInvalidateAction extends ActionSupport {
	
	private static final long serialVersionUID = -57086268316568334L;

	public String sessionInvalidated() {
		addActionError(getText("dmt.error.invalid.session"));
		return SUCCESS;
	}

	public String unauthorized() {
		addActionError(getText("dmt.error.user.unauthorized"));
		return SUCCESS;
	}

}
