package lk.icta.police.web.action;

import java.util.Date;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ChangeAuditVO;
import lk.icta.police.framework.vo.RequestClarificationVO;
import lk.icta.police.web.app.constant.ApplicationConstants;
import lk.icta.police.web.business.ApplicationBusiness;
import lk.icta.police.web.business.RequestClarificationBusiness;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class RequestForUpdatesAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RequestForUpdatesAction.class);
	
	private Map<String,Object> session;	
	
	private String name;
	private Date dateOfBirth;
	private String comment;
	private int nicStatus;
	private int passportStatus;
	private int nameStatus;
	private int dobStatus;
	private String nicAttachPath;
	private String passportAttachPath;
	private String nicAttachPathBack;
	private String passportAttachPathBack;
	private String referenceNo;
	
	private RequestClarificationVO requestClarificationVO;
	private String searchReferenceNo;
	private ApplicationVO applicationVO;
	
	private static final String PROP_FILE_NAME = "police";
	
	public String displayRequestForUpdateForm (){
		System.out.println("SEARCHREFERENCENO1 -> "+searchReferenceNo);
		return SUCCESS;
	}
	
	public String loadRequestForUpdate() {
		System.out.println("SEARCHREFERENCENO2 -> "+searchReferenceNo);
	 	applicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNoAndEmail(searchReferenceNo, getEmailFromUser());
	 	if(applicationVO != null){
	 		
	 		if(applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RV.toString())
	 				|| applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.VF.toString())) {
	 			addActionError("You already update the request.");
	 			return ERROR;
	 		}
	 		requestClarificationVO = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNo(searchReferenceNo);
	 		if(requestClarificationVO == null){
	 			addActionError("You don't need to update the application.");
	 			return ERROR;
	 		}
	 		 referenceNo = applicationVO.getReferenceNo();
		} else {
			applicationVO=ApplicationBusiness.getInstance().getApplication(searchReferenceNo);
			if(!(applicationVO==null)){
				if(!(StringUtils.equals(getEmailFromUser(), applicationVO.getUserEmail()))){
					addActionError("The application with entered Application Reference Number was not submitted by this user account, please use the same user account for enquiries!");
					return ERROR;
				}else{
					addActionError("No clarifications required for the application.");
					return ERROR;
				}
			}else{
				addActionError("No application exists for the entered Application Reference Number.");
				return ERROR;
			}			
		}
		 
		return SUCCESS;
	}
	
	public String saveRequestForUpdate() {
		
		RequestClarificationVO requestClarificationVOObj = new RequestClarificationVO();
		
		applicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNoAndEmail(referenceNo, getEmailFromUser());
	 	if(applicationVO != null){
	 		requestClarificationVOObj = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNo(referenceNo);
	 		requestClarificationVOObj.setApplicationId(applicationVO.getApplicationId());
	 		requestClarificationVOObj.setReferenceNo(applicationVO.getReferenceNo());
	 		requestClarificationVOObj.setNicPath(nicAttachPath);
	 		requestClarificationVOObj.setNicPathBack(nicAttachPathBack);
	 		requestClarificationVOObj.setPassportPath(passportAttachPath);
	 		requestClarificationVOObj.setPassportPathBack(passportAttachPathBack);
	 		requestClarificationVOObj.setName(name);
	 		requestClarificationVOObj.setDateOfBirth(dateOfBirth);
	 		requestClarificationVOObj.setComment(comment);
	 		requestClarificationVOObj.setResubmittedUserId(0);
	 		requestClarificationVOObj.setResubmittedUserName(getEmailFromUser());
	 		requestClarificationVOObj.setResubmittedDateTime(new Date());
	 		
	 		String status = RequestClarificationBusiness.getInstance().updateRequestClarification(requestClarificationVOObj);
	 		System.out.println("STATUS ==================== > " + status);
	 		if(status.equals(PoliceConstant.SUCCESS)){
	 			String updateReviewStatus = ApplicationBusiness.getInstance().
	 					updateReviewStatus(applicationVO.getApplicationId(), PoliceEnumConstant.ApplicationReviewStatus.RV.toString());
	 			if(updateReviewStatus.equals(PoliceConstant.SUCCESS)){
	 				
	 				String commentStr ="";
	 				if(!CommonUtil.checkBlank(nicAttachPath)){
						commentStr += "Request NIC front side file " + applicationVO.getNicAttachPath() + " to " + nicAttachPath + ", ";
					}

					if(!CommonUtil.checkBlank(nicAttachPathBack)){
						commentStr += "Request NIC back side file " + applicationVO.getNicBackAttachPath() + " to " + nicAttachPathBack + ", ";
					}

					if(!CommonUtil.checkBlank(nicAttachPath)){
						commentStr += "Request NIC front side file " + applicationVO.getNicAttachPath() + " to " + nicAttachPath + ", ";
					}

					if(!CommonUtil.checkBlank(nicAttachPathBack)){
						commentStr += "Request NIC back side file " + applicationVO.getNicBackAttachPath() + " to " + nicAttachPathBack + ", ";
					}

	 				if(!CommonUtil.checkBlank(passportAttachPath)){
	 					commentStr += "Request Passport Personal detail page " + applicationVO.getPassportAttachPath() + " to " + passportAttachPath + ", ";
	 				}
	 				
	 				if(!CommonUtil.checkBlank(passportAttachPathBack)){
	 					commentStr += "Request Countries allowed page " + applicationVO.getPassportBackAttachPath() + " to " + passportAttachPathBack + ", ";
	 				}

	 				if(!CommonUtil.checkBlank(name)){
	 					commentStr += "Request Name " + applicationVO.getApplicantNameAsNic() + " to " + name + ", ";
	 				}
	 				
	 				if(dateOfBirth != null){
	 					commentStr += "Request Date of Birth " + applicationVO.getDateOfBirth() + " to " + dateOfBirth + ", ";
	 				}
	 				
	 				commentStr += "Update Review Status RV for application with reference no " + referenceNo + " by " + getEmailFromUser();
	 				addChangeAudit(commentStr, applicationVO.getApplicationId());
	 				addActionMessage("Successfully updated.");
	 			}	
	 		} else {
	 			addActionMessage("Unable to update the record.");
	 			return ERROR;
	 		}
	 	}
		return SUCCESS;
	}
	
	private String addChangeAudit(String comment, long applicationId) {
		String changeAuditUpdateStatus = PoliceConstant.ERROR;
		ChangeAuditVO changeAuditVO = new ChangeAuditVO();
		changeAuditVO.setApplicationId(applicationId);
		changeAuditVO.setUpdatedUserId(0);
		changeAuditVO.setUpdatedUserName(getEmailFromUser());
		changeAuditVO.setUpdatedUserDateTime(new Date());
		changeAuditVO.setComment(comment);
		changeAuditUpdateStatus = ApplicationBusiness.getInstance().addChangeAudit(changeAuditVO);
		
		return changeAuditUpdateStatus;
	}
	
	private String getEmailFromUser() {
		return (String) session.get(ApplicationConstants.EMAIL);
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}
	
		
	
	
	/*private static void copyFileUsingFileChannel(File inputFile,File outputFile) throws IOException {
        FileChannel source = null;
        FileChannel destination = null;
         
        try {
            source = new FileInputStream(inputFile).getChannel();
            destination = new FileOutputStream(outputFile).getChannel();             
            ByteBuffer buf = ByteBuffer.allocateDirect(PoliceConstant.DEFAULT_BUFFER_SIZE);
            while((source.read(buf)) != -1) {
                    buf.flip();
                    destination.write(buf);
                    buf.clear();
            }
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }*/


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@TypeConversion(converter="lk.icta.police.web.util.StringToDateConverter")
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNicStatus() {
		return nicStatus;
	}

	public void setNicStatus(int nicStatus) {
		this.nicStatus = nicStatus;
	}

	public int getPassportStatus() {
		return passportStatus;
	}

	public void setPassportStatus(int passportStatus) {
		this.passportStatus = passportStatus;
	}

	public int getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(int nameStatus) {
		this.nameStatus = nameStatus;
	}

	public int getDobStatus() {
		return dobStatus;
	}

	public void setDobStatus(int dobStatus) {
		this.dobStatus = dobStatus;
	}

	public RequestClarificationVO getRequestClarificationVO() {
		return requestClarificationVO;
	}

	public void setRequestClarificationVO(
			RequestClarificationVO requestClarificationVO) {
		this.requestClarificationVO = requestClarificationVO;
	}

	public String getSearchReferenceNo() {
		return searchReferenceNo;
	}

	public void setSearchReferenceNo(String searchReferenceNo) {
		this.searchReferenceNo = searchReferenceNo;
	}

	public ApplicationVO getApplicationVO() {
		return applicationVO;
	}

	public void setApplicationVO(ApplicationVO applicationVO) {
		this.applicationVO = applicationVO;
	}

	public String getNicAttachPath() {
		return nicAttachPath;
	}

	public void setNicAttachPath(String nicAttachPath) {
		this.nicAttachPath = nicAttachPath;
	}

	public String getPassportAttachPath() {
		return passportAttachPath;
	}

	public void setPassportAttachPath(String passportAttachPath) {
		this.passportAttachPath = passportAttachPath;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getNicAttachPathBack() {
		return nicAttachPathBack;
	}

	public void setNicAttachPathBack(String nicAttachPathBack) {
		this.nicAttachPathBack = nicAttachPathBack;
	}

	public String getPassportAttachPathBack() {
		return passportAttachPathBack;
	}

	public void setPassportAttachPathBack(String passportAttachPathBack) {
		this.passportAttachPathBack = passportAttachPathBack;
	}
	

}
