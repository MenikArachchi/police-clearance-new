package lk.icta.slprest.action;

import java.util.Map;

import lk.icta.slprest.business.RestApplicationBusiness;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ClearanceCertificateVerificationAction  extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ApplicationStatusCheckAction.class);
	private Map<String,Object> session;
	
	//Clearance Certificate Verification
	private String nicNo;
	private String passportNo;
	private String certificateNo;
	private String statusMessage;
	
	public String clearanceCertificateVerification() {
		LOGGER.info("Nic No         ---> " + nicNo);
		LOGGER.info("Passport No    ---> " + passportNo);
		LOGGER.info("CertificateNo  ---> " + certificateNo);
		
		if(nicNo == null || passportNo == null || certificateNo == null) {
			statusMessage ="Nic number, Passport number and Certificate number is required.";
		} else {
			if(nicNo.trim().equals("")){
				statusMessage ="Nic number is required.";
				
			} else if(passportNo.trim().equals("")){
				statusMessage ="Passport number is required.";
				
			}else if(certificateNo.trim().equals("")){
				statusMessage ="Certificate number is required.";
				
			} else {
				int applicationCount = RestApplicationBusiness.getInstance().clearanceCertificateVerification(nicNo, passportNo, certificateNo);
				if(applicationCount >= 1){
					statusMessage = "The entered certificate number is valid.";
				} else {
					statusMessage = "The entered certificate number is invalid.";
				}
			}
		}
		
		LOGGER.info("Status Message  ---> " + statusMessage);
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}

	public String getNicNo() {
		return nicNo;
	}

	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

}
