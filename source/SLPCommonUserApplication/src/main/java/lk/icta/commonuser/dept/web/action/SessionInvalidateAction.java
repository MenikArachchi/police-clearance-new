package lk.icta.commonuser.dept.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class SessionInvalidateAction extends ActionSupport {
	
	private static final long serialVersionUID = -4804506476718643079L;

	public String execute() {
		addActionError(getText("commonuser.session.invalid"));
		return SUCCESS;
	}

}
