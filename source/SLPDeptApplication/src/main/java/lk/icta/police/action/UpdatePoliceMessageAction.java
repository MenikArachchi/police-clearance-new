package lk.icta.police.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ReviewApplicationExternalBusiness;
import lk.icta.police.business.UpdatePoliceMessageBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CommentsVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class UpdatePoliceMessageAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdatePoliceMessageAction.class);
	private Map<String, Object> session;
	
	private long applicationId;
	private ApplicationVO applicationVO;
	private AddressVO addressVO;
	
	private Date fromSentDate;
	private Date toSentDate;
	
	private String clearedStatus;
	private String comment;
	
	private long addressId;
	
	private List<AddressVO> addressVoList;
	
	public String updatePoliceMessage(){
		LOGGER.info("applicationId :" + applicationId);
		applicationVO = UpdatePoliceMessageBusiness.getInstance().getApplicationByApplicationId(applicationId);
		
		addressVoList = UpdatePoliceMessageBusiness.getInstance().getAddressListForUpdate(applicationId);
		
		return SUCCESS;
	}
	
	public String updatePoliceMessageRow(){
		//update addressVO
		addressVO.setFromSentDate(fromSentDate);
		addressVO.setResponseSentDate(toSentDate);
		
		int userId = getUserIdFromSession();
		String username = getUserNameFromSession();
		
		if(StringUtils.isNotEmpty(addressVO.getFromSentBy()) && !StringUtils.isNotEmpty(addressVO.getResponseSentBy())){
			addressVO.setFromCreatedDateTime(new Date());
		}else if(StringUtils.isNotEmpty(addressVO.getFromSentBy()) && StringUtils.isNotEmpty(addressVO.getResponseSentBy())){
			addressVO.setResponseCreatedDateTime(new Date());
		}
		
		boolean result = UpdatePoliceMessageBusiness.getInstance().updateAddressVOForPoliceMessage(addressVO, userId, username);
		
		
		//-------------------- clear status updated as per nadeeshani logics -------------------------
		PoliceEnumConstant.ApprovalStatus approvalStatus = null;
		boolean isValid=true;
		if("Y".equals(clearedStatus)){
			approvalStatus=PoliceEnumConstant.ApprovalStatus.AP;
		}else{
			approvalStatus=PoliceEnumConstant.ApprovalStatus.PN;
		}
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
			}else if(StringUtils.equals(status, PoliceConstant.SUCCESS) && result == true){
				addActionMessage("Record was updated successfully!");
				return SUCCESS;
			}else{
				addActionError("Internal Error Occurred, could not update the record!");
			}
		}
		
		//---------------------------------------------
		
//		if(result == true){
//			return SUCCESS;
//		}else{
//			addActionError("Error occured...!!!");
//			return ERROR;
//		}
		return ERROR;
	}
	
	private CommentsVO constructCommentsVO() {
		CommentsVO commentsVO=new CommentsVO();
		commentsVO.setComment(comment);
		int userRole=getUserRoleFromSession();
		PoliceEnumConstant.UserDepartment userDepartmentEnum=PoliceEnumConstant.UserDepartment.fromCode(userRole);		
		switch (userDepartmentEnum) {	
		case PHQ:
			commentsVO.setAddressId(addressId);
			commentsVO.setCommentType(PoliceEnumConstant.CommentType.PHQ.toString());
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

	private long getUserLocationFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getAssignedLocation();
		}
		return 0;
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

	public long getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationVO getApplicationVO() {
		return applicationVO;
	}

	public void setApplicationVO(ApplicationVO applicationVO) {
		this.applicationVO = applicationVO;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	public String getClearedStatus() {
		return clearedStatus;
	}

	public void setClearedStatus(String clearedStatus) {
		this.clearedStatus = clearedStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<AddressVO> getAddressVoList() {
		return addressVoList;
	}

	public void setAddressVoList(List<AddressVO> addressVoList) {
		this.addressVoList = addressVoList;
	}

	public Date getFromSentDate() {
		return fromSentDate;
	}

	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setFromSentDate(Date fromSentDate) {
		this.fromSentDate = fromSentDate;
	}

	public Date getToSentDate() {
		return toSentDate;
	}

	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setToSentDate(Date toSentDate) {
		this.toSentDate = toSentDate;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
}
