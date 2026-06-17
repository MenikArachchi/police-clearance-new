package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.utility.CommonUtil;

public class RequestClarificationVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long requestClarificationId; 	//BIGINT
	private int nic; 						//INT
	private String nicMessage; 				//VARCHAR(2000)
	private int passport; 					//INT
	private String passportMessage; 		//VARCHAR(2000)
	private int verifyName; 				//INT
	private String nameMessage; 			//VARCHAR(2000)
	private int verifyDateOfBirth; 			//INT
	private String dateOfBirthMessage; 		//VARCHAR(2000)
	private String referenceNo; 			//VARCHAR(10)
	private String nicPath; 				//VARCHAR(1500)
	private String nicPathBack;				//VARCHAR(1500)
	private int nicAcceptStatus; 			//INT
	private String passportPath; 			//VARCHAR(1500)
	private String passportPathBack;		//VARCHAR(1500)
	private int passportAcceptStatus; 		//INT
	private String name; 					//VARCHAR(1500) 
	private int nameAcceptStatus; 			//INT
	private Date dateOfBirth; 				//DATETIME
	private int dateOfBirthAcceptStatus; 	//INT
	private String comment; 				//VARCHAR(5000)
	private int requestedUserId; 			//INT
	private String requestedUserName; 		//VARCHAR(500)
	private Date requestedDateTime; 		//DATETIME
	private int resubmittedUserId; 			//INT
	private String resubmittedUserName; 	//VARCHAR(500),
	private Date resubmittedDateTime; 		//DATETIME
	private int reviewedUserId; 			//INT
	private String reviewedUserName; 		//VARCHAR(500)
	private Date reviewedDateTime; 			//DATETIME
	private long applicationId; 			//BIGINT
	
	private String reuploadedNicFileType;
	private String reuploadedPptFileType;
	private String reuploadedNicFileTypeBack;
	private String reuploadedPptFileTypeBack;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("requestClarificationId", requestClarificationId);
		map.put("nic", nic);
		map.put("nicMessage", nicMessage);
		map.put("passport", passport);
		map.put("passportMessage", passportMessage);
		map.put("verifyName", verifyName);
		map.put("nameMessage", nameMessage);
		map.put("verifyDateOfBirth", verifyDateOfBirth);
		map.put("dateOfBirthMessage", dateOfBirthMessage);
		map.put("referenceNo", referenceNo);
		map.put("nicPath", nicPath);
		map.put("nicPathBack", nicPathBack);
		map.put("nicAcceptStatus", nicAcceptStatus);
		map.put("passportPath", passportPath);
		map.put("passportPathBack", passportPathBack);
		map.put("passportAcceptStatus", passportAcceptStatus);
		map.put("name", name);
		map.put("nameAcceptStatus", nameAcceptStatus);
		map.put("dateOfBirth", dateOfBirth);
		map.put("dateOfBirthAcceptStatus", dateOfBirthAcceptStatus);
		map.put("comment", comment);
		map.put("requestedUserId", requestedUserId);
		map.put("requestedUserName", requestedUserName);
		map.put("requestedDateTime", requestedDateTime);
		map.put("resubmittedUserId", resubmittedUserId);
		map.put("resubmittedUserName", resubmittedUserName);
		map.put("resubmittedDateTime", resubmittedDateTime);
		map.put("reviewedUserId", reviewedUserId);
		map.put("reviewedUserName", reviewedUserName);
		map.put("reviewedDateTime", reviewedDateTime);
		map.put("applicationId", applicationId);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public long getRequestClarificationId() {
		return requestClarificationId;
	}

	public void setRequestClarificationId(long requestClarificationId) {
		this.requestClarificationId = requestClarificationId;
	}

	public int getNic() {
		return nic;
	}

	public void setNic(int nic) {
		this.nic = nic;
	}

	public String getNicMessage() {
		return nicMessage;
	}

	public void setNicMessage(String nicMessage) {
		this.nicMessage = nicMessage;
	}

	public int getPassport() {
		return passport;
	}

	public void setPassport(int passport) {
		this.passport = passport;
	}

	public String getPassportMessage() {
		return passportMessage;
	}

	public void setPassportMessage(String passportMessage) {
		this.passportMessage = passportMessage;
	}

	public int getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(int verifyName) {
		this.verifyName = verifyName;
	}

	public String getNameMessage() {
		return nameMessage;
	}

	public void setNameMessage(String nameMessage) {
		this.nameMessage = nameMessage;
	}

	public int getVerifyDateOfBirth() {
		return verifyDateOfBirth;
	}

	public void setVerifyDateOfBirth(int verifyDateOfBirth) {
		this.verifyDateOfBirth = verifyDateOfBirth;
	}

	public String getDateOfBirthMessage() {
		return dateOfBirthMessage;
	}

	public void setDateOfBirthMessage(String dateOfBirthMessage) {
		this.dateOfBirthMessage = dateOfBirthMessage;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getNicPath() {
		return nicPath;
	}

	public void setNicPath(String nicPath) {
		this.nicPath = nicPath;
	}

	public int getNicAcceptStatus() {
		return nicAcceptStatus;
	}

	public void setNicAcceptStatus(int nicAcceptStatus) {
		this.nicAcceptStatus = nicAcceptStatus;
	}

	public String getPassportPath() {
		return passportPath;
	}

	public void setPassportPath(String passportPath) {
		this.passportPath = passportPath;
	}

	public int getPassportAcceptStatus() {
		return passportAcceptStatus;
	}

	public void setPassportAcceptStatus(int passportAcceptStatus) {
		this.passportAcceptStatus = passportAcceptStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNameAcceptStatus() {
		return nameAcceptStatus;
	}

	public void setNameAcceptStatus(int nameAcceptStatus) {
		this.nameAcceptStatus = nameAcceptStatus;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getDateOfBirthAcceptStatus() {
		return dateOfBirthAcceptStatus;
	}

	public void setDateOfBirthAcceptStatus(int dateOfBirthAcceptStatus) {
		this.dateOfBirthAcceptStatus = dateOfBirthAcceptStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRequestedUserId() {
		return requestedUserId;
	}

	public void setRequestedUserId(int requestedUserId) {
		this.requestedUserId = requestedUserId;
	}

	public String getRequestedUserName() {
		return requestedUserName;
	}

	public void setRequestedUserName(String requestedUserName) {
		this.requestedUserName = requestedUserName;
	}

	public Date getRequestedDateTime() {
		return requestedDateTime;
	}

	public void setRequestedDateTime(Date requestedDateTime) {
		this.requestedDateTime = requestedDateTime;
	}

	public int getResubmittedUserId() {
		return resubmittedUserId;
	}

	public void setResubmittedUserId(int resubmittedUserId) {
		this.resubmittedUserId = resubmittedUserId;
	}

	public String getResubmittedUserName() {
		return resubmittedUserName;
	}

	public void setResubmittedUserName(String resubmittedUserName) {
		this.resubmittedUserName = resubmittedUserName;
	}

	public Date getResubmittedDateTime() {
		return resubmittedDateTime;
	}

	public void setResubmittedDateTime(Date resubmittedDateTime) {
		this.resubmittedDateTime = resubmittedDateTime;
	}

	public int getReviewedUserId() {
		return reviewedUserId;
	}

	public void setReviewedUserId(int reviewedUserId) {
		this.reviewedUserId = reviewedUserId;
	}

	public String getReviewedUserName() {
		return reviewedUserName;
	}

	public void setReviewedUserName(String reviewedUserName) {
		this.reviewedUserName = reviewedUserName;
	}

	public Date getReviewedDateTime() {
		return reviewedDateTime;
	}

	public void setReviewedDateTime(Date reviewedDateTime) {
		this.reviewedDateTime = reviewedDateTime;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getReuploadedNicFileType() {
		return reuploadedNicFileType;
	}
	
	public void setReuploadedNicFileType(String reuploadedNicFileType) {
		this.reuploadedNicFileType = reuploadedNicFileType;
	}

	public String getReuploadedPptFileType() {
		return reuploadedPptFileType;
	}

	public void setReuploadedPptFileType(String reuploadedPptFileType) {
		this.reuploadedPptFileType = reuploadedPptFileType;
	}
	
	public String getNicPathBack() {
		return nicPathBack;
	}

	public void setNicPathBack(String nicPathBack) {
		this.nicPathBack = nicPathBack;
	}

	public String getPassportPathBack() {
		return passportPathBack;
	}

	public void setPassportPathBack(String passportPathBack) {
		this.passportPathBack = passportPathBack;
	}
	
	public String getReuploadedNicFileTypeBack() {
		return reuploadedNicFileTypeBack;
	}

	public void setReuploadedNicFileTypeBack(String reuploadedNicFileTypeBack) {
		this.reuploadedNicFileTypeBack = reuploadedNicFileTypeBack;
	}

	public String getReuploadedPptFileTypeBack() {
		return reuploadedPptFileTypeBack;
	}

	public void setReuploadedPptFileTypeBack(String reuploadedPptFileTypeBack) {
		this.reuploadedPptFileTypeBack = reuploadedPptFileTypeBack;
	}

	public void allocateFileTypes() {
		if(!(this.nicPath==null || this.nicPath.equals(""))){
			if(this.nicPath.contains("\\.") || this.nicPath.contains(".")){
				String extension = "." + this.nicPath.split("\\.")[1];
				this.reuploadedNicFileType=CommonUtil.getFileTypeFromExtension(extension);
			}		
		}
		
		if(!(this.nicPathBack==null || this.nicPathBack.equals(""))){
			if(this.nicPathBack.contains("\\.") || this.nicPathBack.contains(".")){
				String extension = "." + this.nicPathBack.split("\\.")[1];
				this.reuploadedNicFileTypeBack=CommonUtil.getFileTypeFromExtension(extension);
			}		
		}
		
		if(!(this.passportPath==null || this.passportPath.equals(""))){
			if(this.passportPath.contains("\\.") || this.passportPath.contains(".")){
				String extension = "." + this.passportPath.split("\\.")[1];
				this.reuploadedPptFileType=CommonUtil.getFileTypeFromExtension(extension);
			}		
		}
		
		if(!(this.passportPathBack==null || this.passportPathBack.equals(""))){
			if(this.passportPathBack.contains("\\.") || this.passportPathBack.contains(".")){
				String extension = "." + this.passportPathBack.split("\\.")[1];
				this.reuploadedPptFileTypeBack=CommonUtil.getFileTypeFromExtension(extension);
			}		
		}
		
	}

	

	
	
	
	
}
