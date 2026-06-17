package lk.icta.slprest.action;

import java.util.Map;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.slprest.business.RestApplicationBusiness;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ApplicationStatusCheckAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ApplicationStatusCheckAction.class);
	private Map<String,Object> session;

	//Application Status check
	private String referenceNo;
	private String message;
	
	public String checkApplicationStatus() {
		LOGGER.info("Reference No ---> " + referenceNo);
		
		if(referenceNo == null){
			message ="Reference number is required.";
		} else {
			if(referenceNo.trim().equals("")){
				message ="Reference number is required.";
			} else {
				int applicationCount = RestApplicationBusiness.getInstance().isExistsApplication(referenceNo);
				
				if(applicationCount > 0){
					String applicationStatus = RestApplicationBusiness.getInstance().checkApplicationStatus(referenceNo);
					
					if(applicationStatus.trim().equals(PoliceConstant.APP_STATUS_NOT_VISIBLE)){
						message = "Application status is not available. Please contact Clearance Branch.";
					} else if(applicationStatus.trim().equals(PoliceConstant.ERROR)){
						message = "Error Occured!, Please try again later.";
					} else {
						message = "Application with the reference number " + referenceNo + " is " + 
								PoliceEnumConstant.ApplicationStatusCheck.fromCode(applicationStatus).getDisplayName() + ".";						
					}	
					
				} else {
					message ="Application with the given reference number " + referenceNo + " does not exist.";
				}
			}
		}
		
		

		LOGGER.info("Message --> " + message);
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
