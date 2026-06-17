package lk.icta.police.external.action;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.external.business.ApplicationStatusViewBusiness;
import lk.icta.police.external.business.ReviewApplicationExternalBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CommentsVO;
import lk.icta.police.framework.vo.PoliceAreaVO;
import lk.icta.police.framework.vo.ReviewApplicationExternalSearchCriteriaVO;
import lk.icta.police.framework.vo.UserRoleDisplayVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class ReviewApplicationAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ReviewApplicationAction.class);
	private Map<String, Object> session;
	
	private Date fromDate;
	private Date toDate;
	
	private long applicationId;
	
	private ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO;
	
	private List<ApplicationVO> applicationList;
	
	private List<AddressVO> addressVOList;
	private String recordLockstatus;
	
	private long totalRecordCount;
	private Map<Integer,Integer> gridButtonMap;
	private int currentButton;
	
	private String comment;
	private String clearenceStatus;
	
	private int userRole;
	private UserRoleDisplayVO userRoleDisplayVO;
	
	private List<PoliceAreaVO> policeAreaList;
	
	private long addressId;
	
	private String policeAddressComment;
	private String policeAddressClearenceStatus;
	private String addressUpdatedStatus;
	private String addressUpdatedMessage;
	
	private AddressTempVO addressTempVO;
	
	private String address;
	private String addressStatus;
	private long policeAreaId;
	private String policeArea;
  	private String policeAreaStatus;
  	private String stayPeriodStatus;
	
  	
  	private String emailType;
  	private String emailContent;  	
  	private int sendEmailByPhq;
  	
  	private String adverseType;
  	
  	private String lockedUserName;
	
	public String viewReviewApplication(){
		initializeDefaultData();
		return SUCCESS;
	}
	
	private void initializeDefaultData() {
		initializeSearchCriteria();
		long userLocation=getUserLocationFromSession();
		int userRole=getUserRoleFromSession();
		int userId=getUserIdFromSession();
		
		LOGGER.info("userLocation ACTION||||||| " + userLocation);
		LOGGER.info("userRole ACTION||||||| " + userRole);
		LOGGER.info("userId ACTION||||||| " + userId);
		
		applicationList=ReviewApplicationExternalBusiness.getInstance().searchApplication(searchCriteriaVO,userRole , userLocation, userId);
		if(!(applicationList==null || applicationList.isEmpty())){
			loadGridButtons();
			loadUserRoleData();
		}
		
	}

	public String searchReviewApplication(){
		initializeSearchCriteria();
		applicationList=ReviewApplicationExternalBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(), getUserLocationFromSession(), getUserIdFromSession());
		if(!(applicationList==null || applicationList.isEmpty())){
			loadGridButtons();
			loadUserRoleData();
		}
		return SUCCESS;
	}


	private void initializeSearchCriteria() {
		if(searchCriteriaVO==null){
			searchCriteriaVO=new ReviewApplicationExternalSearchCriteriaVO(null, null, null, null, null);
			searchCriteriaVO.setStartFrom(0);
			searchCriteriaVO.setLimit(20);
		}
		searchCriteriaVO.setFromDate(fromDate);
		searchCriteriaVO.setToDate(toDate);		
		searchCriteriaVO.updateSearchCriteriaVO(getUserRoleFromSession(), getUserLocationFromSession());		
		System.out.println("searchCriteriaVO :" + searchCriteriaVO);
	}
	
	private void loadUserRoleData(){
		this.userRole=getUserRoleFromSession();
		this.userRoleDisplayVO=new UserRoleDisplayVO();
		
	}
	
	private void loadGridButtons() {
		if(!(applicationList.size()<=0)){
			totalRecordCount=ReviewApplicationExternalBusiness.getInstance().getTotalApplicationListCount(searchCriteriaVO, getUserRoleFromSession(), getUserLocationFromSession(), getUserIdFromSession());
			LOGGER.info("totalRecordCount: " + totalRecordCount);
			
			double tempTotalRecordCount=totalRecordCount;
			double tempItemSize=searchCriteriaVO.getLimit();
			LOGGER.info("tempTotalRecordCount:" + tempTotalRecordCount);
			LOGGER.info("tempItemSize:" + tempItemSize);
			double noOfButtons=tempTotalRecordCount/tempItemSize;	
			LOGGER.info("noOfButtons :" + noOfButtons);
			noOfButtons=Math.ceil(noOfButtons);
			gridButtonMap=new LinkedHashMap<Integer, Integer>();
			int startFrom=0;
			for (int i = 1; i <= noOfButtons; i++) {
				gridButtonMap.put(i, startFrom);	
				if(startFrom==searchCriteriaVO.getStartFrom()){
					currentButton=i;
				}
				startFrom=startFrom + searchCriteriaVO.getLimit();
			}		
			LOGGER.info("gridButtonMap: " + gridButtonMap);	
		}		
	}
	
	public String loadAddressListClearence(){
		int userRole=getUserRoleFromSession();
		if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
			addressVOList=ReviewApplicationExternalBusiness.getInstance().getAddressListByUserRole(applicationId, getUserRoleFromSession(), getUserLocationFromSession(), null);
		}
		return SUCCESS;
	}
	
	public String loadEditAddress(){
		if(!(addressId<=0)){
			int userRole=getUserRoleFromSession();
			if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
				//check if an active address edit is already available for this address
				addressTempVO=ReviewApplicationExternalBusiness.getInstance().getActiveAddressTempByIdAddressId(addressId);
				if(addressTempVO==null){
					//if not available, get the address and create a temp one
					AddressVO addressVO=ReviewApplicationExternalBusiness.getInstance().getAddressById(addressId);
					addressTempVO=new AddressTempVO(addressVO);
				}
				policeAreaList=ReviewApplicationExternalBusiness.getInstance().listAllPoliceAreas();
			}
		}		
		return SUCCESS;
	}
	
	public String updateAddressPolice(){
		boolean isValid=true;
		System.out.println("policeAddressClearenceStatus :" + policeAddressClearenceStatus);
		if(!(addressId<=0)){
			policeAddressClearenceStatus=StringUtils.trim(policeAddressClearenceStatus);
			policeAddressComment=StringUtils.trim(policeAddressComment);			
			String approvalStatus=null;
			if(!(StringUtils.isEmpty(policeAddressClearenceStatus))){
				int userRole=getUserRoleFromSession();
				if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){					
					if(StringUtils.equals(policeAddressClearenceStatus, "N")){
						approvalStatus=PoliceEnumConstant.ApprovalStatus.RJ.toString();
						if(StringUtils.isEmpty(policeAddressComment)){
							isValid=false;							
							addressUpdatedMessage="Please enter a comment stating the reason for not to clear the application!";
						}
					}else if(StringUtils.equals(policeAddressClearenceStatus, "Y")){
						approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
						isValid=true;
					}else{
						isValid=false;
						addressUpdatedMessage="Please select if the application is cleared or not!";
					}
					
					if(isValid){
						CommentsVO commentsVO=constructCommentsVO();
						commentsVO.setComment(policeAddressComment);
						String status=ReviewApplicationExternalBusiness.getInstance().updateAddressReviewApprovalStatus(addressId,approvalStatus, commentsVO);
						if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
							addressUpdatedMessage="Record successfully updated!";
							isValid=true;
						}else{
							addressUpdatedMessage="Update failed! Please try again later";
							isValid=false;
						}
					}
					
				}else{
					addressUpdatedMessage="You are not authrized to update the address clearence status!";
					isValid=false;
				}
			}else{
				isValid=false;
				addressUpdatedMessage="Please select the address clearence status!";
			}			
		}else{
			isValid=false;	
			addressUpdatedMessage="The selected address is not valid!";
		}
		if(isValid){
			addressUpdatedStatus=PoliceConstant.SUCCESS;
		}else{
			addressUpdatedStatus=PoliceConstant.ERROR;
		}
		return SUCCESS;
	}
	
	public String updateAddressEditPolice(){
		boolean isValid=true;
		if(!(addressId<=0)){			
			addressTempVO=constructAddressTempVO(true);			
			
			if(!(addressTempVO==null)){
				int userRole=getUserRoleFromSession();
				
				if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){					
										
					CommentsVO commentsVO=constructCommentsVO();
					commentsVO.setComment(comment);
					String status=ReviewApplicationExternalBusiness.getInstance().updateAddressEditPolice(addressTempVO,commentsVO);
					if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
						addressUpdatedMessage="Record successfully updated!";
						isValid=true;
					}else{
						addressUpdatedMessage="Update failed! Please try again later";
						isValid=false;
					}
					
				}else{
					addressUpdatedMessage="You are not authrized to update the address clearence status!";
					isValid=false;
				}
			}else{
				isValid=false;
				addressUpdatedMessage="Please enter all the details!";
			}			
		}else{
			isValid=false;	
			addressUpdatedMessage="The selected address is not valid!";
		}
		
		if(isValid){
			addressUpdatedStatus=PoliceConstant.SUCCESS;
		}else{
			addressUpdatedStatus=PoliceConstant.ERROR;
		}
		return SUCCESS;
	}
	
	public String loadEmailContentPhq(){
		emailType=StringUtils.trim(emailType);
		if(StringUtils.isNotEmpty(emailType) || addressId<=0){
			AddressTempVO addressTempVO=constructAddressTempVO(false);
			PoliceEnumConstant.EmailType emailTypeEnum=PoliceEnumConstant.EmailType.fromCode(emailType);
			emailContent=ReviewApplicationExternalBusiness.getInstance().getEmailContentByEmailType(emailTypeEnum,addressTempVO);
		}		
		return SUCCESS;
	}
	
	
	public String updateAddressPhq(){
		boolean isValid=true;
		if(!(addressId<=0)){
			policeAddressClearenceStatus=StringUtils.trim(policeAddressClearenceStatus);
			policeAddressComment=StringUtils.trim(policeAddressComment);			
			String approvalStatus=null;
			if(!(StringUtils.isEmpty(policeAddressClearenceStatus))){
				int userRole=getUserRoleFromSession();
				if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){					
					if(StringUtils.equals(policeAddressClearenceStatus, "N")){
						approvalStatus=PoliceEnumConstant.ApprovalStatus.RJ.toString();
						if(StringUtils.isEmpty(policeAddressComment)){
							isValid=false;							
							addressUpdatedMessage="Please enter a comment stating the reason for not to clear the application!";
						}
					}else if(StringUtils.equals(policeAddressClearenceStatus, "Y")){
						approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
						isValid=true;
					}else{
						isValid=false;
						addressUpdatedMessage="Please select if the application is cleared or not!";
					}
					
					if(isValid){
						CommentsVO commentsVO=constructCommentsVO();
						commentsVO.setComment(policeAddressComment);
						String status=ReviewApplicationExternalBusiness.getInstance().updateAddressReviewApprovalStatus(addressId,approvalStatus,commentsVO);
						if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
							addressUpdatedMessage="Record successfully updated!";
							isValid=true;
						}else{
							addressUpdatedMessage="Update failed! Please try again later";
							isValid=false;
						}
					}
					
				}else{
					addressUpdatedMessage="You are not authrized to update the address clearence status!";
					isValid=false;
				}
			}else{
				isValid=false;
				addressUpdatedMessage="Please select the address clearence status!";
			}			
		}else{
			isValid=false;	
			addressUpdatedMessage="The selected address is not valid!";
		}
		if(isValid){
			addressUpdatedStatus=PoliceConstant.SUCCESS;
		}else{
			addressUpdatedStatus=PoliceConstant.ERROR;
		}
		return SUCCESS;
	}
	
	public String updateAddressEditPhq(){
		boolean isValid=true;
		if(!(addressId<=0)){
			userRole=getUserRoleFromSession();				
			addressTempVO=constructAddressTempVO(true);			
			if(!(addressTempVO==null)){
				addressTempVO.setEmailContent(emailContent);
				addressTempVO.setSendEmailByPhq(sendEmailByPhq);
				addressTempVO.setEmailType(emailType);				
							
				if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){											
					CommentsVO commentsVO=constructCommentsVO();
					commentsVO.setComment(comment);
					String status=ReviewApplicationExternalBusiness.getInstance().updateAddressEditPhq(addressTempVO,commentsVO);
					if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
						addressUpdatedMessage="Record successfully updated!";
						isValid=true;
					}else{
						addressUpdatedMessage="Update failed! Please try again later";
						isValid=false;
					}					
				}else{
					addressUpdatedMessage="You are not authrized to update the address clearence status!";
					isValid=false;
				}
			}else{
				isValid=false;
				addressUpdatedMessage="Please enter all the details!";
			}			
		}else{
			isValid=false;	
			addressUpdatedMessage="The selected address is not valid!";
		}
		
		if(isValid){
			addressUpdatedStatus=PoliceConstant.SUCCESS;
		}else{
			addressUpdatedStatus=PoliceConstant.ERROR;
		}
		return SUCCESS;
	}
	
	
	public String sendEmailAddressEditPhq(){
		boolean isValid=true;
		if(!(addressId<=0)){
			userRole=getUserRoleFromSession();				
			addressTempVO=new AddressTempVO();	
			addressTempVO.setAddressId(addressId);
			if(sendEmailByPhq==1 && StringUtils.isNotEmpty(emailContent) && StringUtils.isNotEmpty(emailType)){
				addressTempVO.setEmailContent(emailContent);
				addressTempVO.setSendEmailByPhq(sendEmailByPhq);
				addressTempVO.setEmailType(emailType);				
							
				if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){											
					CommentsVO commentsVO=constructCommentsVO();
					commentsVO.setComment(comment);
					String status=ReviewApplicationExternalBusiness.getInstance().sendEmailAddressEditPhq(addressTempVO,commentsVO);
					if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
						addressUpdatedMessage="Record successfully updated!";
						isValid=true;
					}else{
						addressUpdatedMessage="Email Sending failed! Please try again later";
						isValid=false;
					}					
				}else{
					addressUpdatedMessage="You are not authrized to send the email!";
					isValid=false;
				}
			}else{
				isValid=false;
				addressUpdatedMessage="Please enter all the details!";
			}			
		}else{
			isValid=false;	
			addressUpdatedMessage="The selected address is not valid!";
		}
		
		if(isValid){
			addressUpdatedStatus=PoliceConstant.SUCCESS;
		}else{
			addressUpdatedStatus=PoliceConstant.ERROR;
		}
		return SUCCESS;
	}
	
	private AddressTempVO constructAddressTempVO(boolean isStatusRequired) {
		address=StringUtils.trim(address);
		addressStatus=StringUtils.trim(addressStatus);		
		policeArea=StringUtils.trim(policeArea);
		policeAreaStatus=StringUtils.trim(policeAreaStatus);		
		stayPeriodStatus=StringUtils.trim(stayPeriodStatus);
		comment=StringUtils.trim(comment);
		if(isStatusRequired){
		this.userRole=getUserRoleFromSession();
		if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){	
			if(!(StringUtils.isEmpty(address) || StringUtils.isEmpty(policeArea) ||  policeAreaId<=0 || fromDate==null || toDate==null)){
					if(sendEmailByPhq==1){
						if(StringUtils.isEmpty(emailContent)){
							return null;
						}
					}
					System.out.println("CAME EHERE :" + emailContent);
					return new AddressTempVO(addressId, address, addressStatus, policeAreaId, policeArea, policeAreaStatus,
							fromDate, toDate, stayPeriodStatus, comment, 1, getUserIdFromSession(), getUserNameFromSession(), Calendar.getInstance().getTime());
				}				
			}else{
				if(!(StringUtils.isEmpty(address) || StringUtils.isEmpty(addressStatus) || StringUtils.isEmpty(policeArea) || StringUtils.isEmpty(policeAreaStatus) ||
						StringUtils.isEmpty(stayPeriodStatus) || StringUtils.isEmpty(comment))){
					return new AddressTempVO(addressId, address, addressStatus, policeAreaId, policeArea, policeAreaStatus,
							fromDate, toDate, stayPeriodStatus, comment, 1, getUserIdFromSession(), getUserNameFromSession(), Calendar.getInstance().getTime());
					
				}
			}			
		}else{
			if(!(StringUtils.isEmpty(address) || StringUtils.isEmpty(policeArea) ||  policeAreaId<=0)){
				return new AddressTempVO(addressId, address, addressStatus, policeAreaId, policeArea, policeAreaStatus,
						fromDate, toDate, stayPeriodStatus, comment, 1, getUserIdFromSession(), getUserNameFromSession(), Calendar.getInstance().getTime());
			}
		}		
		return null;		
	}

	public String checkAndLockCooRecord(){
		if(!(applicationId==0)){
			
			
			Map<String,Object> map=ReviewApplicationExternalBusiness.getInstance().checkAndLockRecord(applicationId, getUserIdFromSession(), getUserRoleFromSession(), getUserLocationFromSession());
			recordLockstatus=(String) map.get("MESSAGE");
			if(StringUtils.equals(recordLockstatus, PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER)){
				int lockedOtherUserId=(Integer) map.get("LOCKED_USER");
				lockedUserName=ApplicationStatusViewBusiness.getInstance().getUserNameById(lockedOtherUserId);
			}
			
			
			if(StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)){
				addRecordLockedStatusToSession();
			}
		}
		return SUCCESS;
	}
	
	public String checkAndRemoveLock(){
		if(!(applicationId==0)){
			recordLockstatus=ReviewApplicationExternalBusiness.getInstance().checkAndUnLockRecord(applicationId, getUserIdFromSession(), getUserLocationFromSession(), getUserRoleFromSession());
			if(StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)){
				removeRecordLockedStatusFromSession(applicationId);
			}
			if(StringUtils.equals(recordLockstatus, PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER)){
				recordLockstatus=PoliceConstant.SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	public String updateClearenceStatus(){
		LOGGER.info("applicationId :" + applicationId);
		LOGGER.info("comment :" + comment);
		LOGGER.info("clearenceStatus :" + clearenceStatus);
		comment=StringUtils.trim(comment);
		clearenceStatus=StringUtils.trim(clearenceStatus);		
		if((StringUtils.isNotEmpty(clearenceStatus))){
			boolean isValid=true;	
			String approvalStatus=null;
			
			if(StringUtils.equals(clearenceStatus, "N")){
				approvalStatus=PoliceEnumConstant.ApprovalStatus.RJ.toString();
				if(StringUtils.isEmpty(comment)){
					isValid=false;
					addActionError("Please enter a comment stating the reason for not to clear the application!");
				}
			}else if(StringUtils.equals(clearenceStatus, "Y")){
				approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
				isValid=true;
			}else{
				isValid=false;
				addActionError("Please select if the application is cleared or not!");
			}
			
			if(isValid){
				CommentsVO commentsVO=constructCommentsVO();
				String status=ReviewApplicationExternalBusiness.getInstance().updateApplicationReviewApprovalStatus(approvalStatus, getUserLocationFromSession(), commentsVO);
				if(StringUtils.equals(status, PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE)){
					addActionError("You do not own the lock of this record currently!");
				}else if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
					addActionMessage("Record was updated successfully!");
				}else{
					addActionError("Internal Error Occurred, could not update the record!");
				}
			}
		}
		initializeDefaultData();
		return SUCCESS;
	}
	
	public String updateAdverseClearenceStatus(){
		LOGGER.info("applicationId :" + applicationId);
		LOGGER.info("comment :" + comment);
		LOGGER.info("clearenceStatus :" + clearenceStatus);
		LOGGER.info("adverseType :" + adverseType);
		comment=StringUtils.trim(comment);
		clearenceStatus=StringUtils.trim(clearenceStatus);
		
		if((StringUtils.isNotEmpty(adverseType))){
			boolean isValid=true;	
			PoliceEnumConstant.ApprovalStatus approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(adverseType);
			if(!(approvalStatus==null)){
				isValid=true;				
			}else{
				isValid=false;
				addActionError("Please select if the application is cleared or not!");
			}			
			if(isValid){
				CommentsVO commentsVO=constructCommentsVO();
				String status=ReviewApplicationExternalBusiness.getInstance().updateApplicationReviewApprovalStatus(approvalStatus.toString(), getUserLocationFromSession(), commentsVO);
				if(StringUtils.equals(status, PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE)){
					addActionError("You do not own the lock of this record currently!");
				}else if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
					addActionMessage("Record was updated successfully!");
				}else{
					addActionError("Internal Error Occurred, could not update the record!");
				}
			}
		}
		initializeDefaultData();
		return SUCCESS;
	}
	
	public String checkAndLockAddressRecord(){
		
		if(!(applicationId==0 || addressId==0)){
			Map<String,Object> map=ReviewApplicationExternalBusiness.getInstance().checkAndLockAddressRecord(addressId, applicationId, getUserIdFromSession(), getUserRoleFromSession(), getUserLocationFromSession());
			recordLockstatus=(String) map.get("MESSAGE");
			if(StringUtils.equals(recordLockstatus, PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER)){
				int lockedOtherUserId=(Integer) map.get("LOCKED_USER");
				lockedUserName=ApplicationStatusViewBusiness.getInstance().getUserNameById(lockedOtherUserId);
			}
			
			if(StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)){
				addRecordLockedStatusToSession();
			}
		}
		return SUCCESS;
	}
	
	public String checkAndRemoveAddressLock(){
		if(!(applicationId==0)){
			recordLockstatus=ReviewApplicationExternalBusiness.getInstance().checkAndUnLockAddressRecord(applicationId, addressId, getUserIdFromSession(), getUserLocationFromSession(), getUserRoleFromSession());
			if(StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)){
				removeRecordLockedStatusFromSession(applicationId);
			}
		}
		return SUCCESS;
	}
	
	
	private CommentsVO constructCommentsVO() {
		CommentsVO commentsVO=new CommentsVO();
		commentsVO.setComment(comment);
		int userRole=getUserRoleFromSession();
		PoliceEnumConstant.UserDepartment userDepartmentEnum=PoliceEnumConstant.UserDepartment.fromCode(userRole);		
		switch (userDepartmentEnum) {	
		case PHQ:
			commentsVO.setAddressId(addressId);
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.POL.toString());
			break;
		case POL:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.POL.toString());
			commentsVO.setAddressId(addressId);
			break;
		case CID:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.CID.toString());
			break;
		case TID:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.TID.toString());
			break;
		case SIS:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.SIS.toString());
			break;
		case NIC:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.NIC.toString());
			break;
		case IMI:
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.IMI.toString());
			break;
		default:
			LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
			break;
		}	
		commentsVO.setApplicationId(applicationId);
		commentsVO.setCreatedDateTime(Calendar.getInstance().getTime());
		commentsVO.setCreatedUserId(getUserIdFromSession());
		commentsVO.setCreatedUserName(getUserNameFromSession());
		commentsVO.setCreatedUserRole(userRole);
		return commentsVO;
	}

	private void addRecordLockedStatusToSession() {
		Map<Long,Integer> recordLockedMap=null;
		if(!(session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP)==null)){
			recordLockedMap=(Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP);
		}else{
			recordLockedMap=new LinkedHashMap<Long, Integer>();
		}
		recordLockedMap.put(applicationId, getUserIdFromSession());
		session.put(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP, recordLockedMap);
	}
	
	private void removeRecordLockedStatusFromSession(long unlockedApplicationId){
		if(!(session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP)==null)){
			Map<Long, Integer> recordLockedMap=(Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP);
			if(!(recordLockedMap==null)){
				recordLockedMap.remove(unlockedApplicationId);
				session.put(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP, recordLockedMap);
			}
		}
	}

	private String getUserNameFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getFullName();
		}
		return null;
	}
	
	private int getUserIdFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getId();
		}
		return 0;
	}
	
	
	private long getUserLocationFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getAssignedLocation();
		}
		return 0;
	}

	private int getUserRoleFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null || userVO.getDept()==null)){
			return userVO.getDept().getId();
		}
		return 0;
	}
	
	private UserVO getUserFromSession(){
		if(!(session.get(PoliceConstant.SESSION_USER)==null)){
			return (UserVO) session.get(PoliceConstant.SESSION_USER);
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}


	public Date getFromDate() {
		return fromDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public ReviewApplicationExternalSearchCriteriaVO getSearchCriteriaVO() {
		return searchCriteriaVO;
	}


	public void setSearchCriteriaVO(ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO) {
		this.searchCriteriaVO = searchCriteriaVO;
	}

	public List<ApplicationVO> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationVO> applicationList) {
		this.applicationList = applicationList;
	}

	public List<AddressVO> getAddressVOList() {
		return addressVOList;
	}

	public void setAddressVOList(List<AddressVO> addressVOList) {
		this.addressVOList = addressVOList;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getRecordLockstatus() {
		return recordLockstatus;
	}

	public void setRecordLockstatus(String recordLockstatus) {
		this.recordLockstatus = recordLockstatus;
	}

	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public Map<Integer, Integer> getGridButtonMap() {
		return gridButtonMap;
	}

	public void setGridButtonMap(Map<Integer, Integer> gridButtonMap) {
		this.gridButtonMap = gridButtonMap;
	}

	public int getCurrentButton() {
		return currentButton;
	}

	public void setCurrentButton(int currentButton) {
		this.currentButton = currentButton;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClearenceStatus() {
		return clearenceStatus;
	}

	public void setClearenceStatus(String clearenceStatus) {
		this.clearenceStatus = clearenceStatus;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public UserRoleDisplayVO getUserRoleDisplayVO() {
		return userRoleDisplayVO;
	}

	public void setUserRoleDisplayVO(UserRoleDisplayVO userRoleDisplayVO) {
		this.userRoleDisplayVO = userRoleDisplayVO;
	}

	public List<PoliceAreaVO> getPoliceAreaList() {
		return policeAreaList;
	}

	public void setPoliceAreaList(List<PoliceAreaVO> policeAreaList) {
		this.policeAreaList = policeAreaList;
	}


	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	

	public AddressTempVO getAddressTempVO() {
		return addressTempVO;
	}

	public void setAddressTempVO(AddressTempVO addressTempVO) {
		this.addressTempVO = addressTempVO;
	}

	public String getPoliceAddressComment() {
		return policeAddressComment;
	}

	public void setPoliceAddressComment(String policeAddressComment) {
		this.policeAddressComment = policeAddressComment;
	}

	public String getPoliceAddressClearenceStatus() {
		return policeAddressClearenceStatus;
	}

	public void setPoliceAddressClearenceStatus(String policeAddressClearenceStatus) {
		this.policeAddressClearenceStatus = policeAddressClearenceStatus;
	}

	public String getAddressUpdatedStatus() {
		return addressUpdatedStatus;
	}

	public void setAddressUpdatedStatus(String addressUpdatedStatus) {
		this.addressUpdatedStatus = addressUpdatedStatus;
	}

	public String getAddressUpdatedMessage() {
		return addressUpdatedMessage;
	}

	public void setAddressUpdatedMessage(String addressUpdatedMessage) {
		this.addressUpdatedMessage = addressUpdatedMessage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressStatus() {
		return addressStatus;
	}

	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	public long getPoliceAreaId() {
		return policeAreaId;
	}

	public void setPoliceAreaId(long policeAreaId) {
		this.policeAreaId = policeAreaId;
	}

	public String getPoliceArea() {
		return policeArea;
	}

	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}

	public String getPoliceAreaStatus() {
		return policeAreaStatus;
	}

	public void setPoliceAreaStatus(String policeAreaStatus) {
		this.policeAreaStatus = policeAreaStatus;
	}

	public String getStayPeriodStatus() {
		return stayPeriodStatus;
	}

	public void setStayPeriodStatus(String stayPeriodStatus) {
		this.stayPeriodStatus = stayPeriodStatus;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public int getSendEmailByPhq() {
		return sendEmailByPhq;
	}

	public void setSendEmailByPhq(int sendEmailByPhq) {
		this.sendEmailByPhq = sendEmailByPhq;
	}

	public String getAdverseType() {
		return adverseType;
	}

	public void setAdverseType(String adverseType) {
		this.adverseType = adverseType;
	}

	public String getLockedUserName() {
		return lockedUserName;
	}

	public void setLockedUserName(String lockedUserName) {
		this.lockedUserName = lockedUserName;
	}


	
	


	
}
