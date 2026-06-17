package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

public class CommentsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long commentId; 		//BIGINT
	private String comment; 		//VARCHAR(5000)
	private int createdUserId; 		//INT
	private String createdUserName;	//VARCHAR(500)
	private int createdUserRole; 	//INT
	private Date createdDateTime;	//DATETIME
	private String commentType; 	//VARCHAR(3)
	private long applicationId; 	//BIGINT
	private long addressId; 		//BIGINT
	
	
	
	public CommentsVO(String comment, int createdUserId,
			String createdUserName, int createdUserRole, Date createdDateTime,
			String commentType, long applicationId, long addressId) {
		super();
		this.comment = comment;
		this.createdUserId = createdUserId;
		this.createdUserName = createdUserName;
		this.createdUserRole = createdUserRole;
		this.createdDateTime = createdDateTime;
		this.commentType = commentType;
		this.applicationId = applicationId;
		this.addressId = addressId;
	}
	
	

	public CommentsVO() {
		super();
	}



	public CommentsVO(CertificateClearenceVO clearenceVO) {
		this.comment=clearenceVO.getComment(); 
		this.createdUserId=clearenceVO.getUpdatedUserId(); 
		this.createdUserName=clearenceVO.getUpdatedUserName();	
		this.createdUserRole=clearenceVO.getUpdatedUserType(); 
		this.createdDateTime=Calendar.getInstance().getTime();	
		this.commentType=getCommentTypeByUserType(clearenceVO.getUpdatedUserType()); 	
		this.applicationId=clearenceVO.getApplicationId(); 	
		this.addressId=0; 		
	}



	private String getCommentTypeByUserType(int updatedUserType) {
		String commentType=null;
		PoliceEnumConstant.UserType userTypeEnum=PoliceEnumConstant.UserType.fromCode(updatedUserType);	
		switch(userTypeEnum){	
			case CN:
				commentType=PoliceEnumConstant.CommentType.CHK.toString();
				break;
			case OI:
				commentType=PoliceEnumConstant.CommentType.OIC.toString();
				break;
			case AS:
				commentType=PoliceEnumConstant.CommentType.ASP.toString();
				break;
			case DI:
				commentType=PoliceEnumConstant.CommentType.DIG.toString();
				break;
			case DH:
				commentType=PoliceEnumConstant.CommentType.DHA.toString();
				break;
			case CA:
				commentType=PoliceEnumConstant.CommentType.CHA.toString();
				break;
			default:
				System.out.println("NEW USER TYPE :" + userTypeEnum);
				break;
		}	
		return commentType;
	}



	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("commentId", commentId);
		map.put("comment", comment);
		map.put("createdUserId", createdUserId);
		map.put("createdUserName", createdUserName);
		map.put("createdUserRole", createdUserRole);
		map.put("createdDateTime", createdDateTime);
		map.put("commentType", commentType);
		map.put("applicationId", applicationId);
		map.put("addressId", addressId);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public int getCreatedUserRole() {
		return createdUserRole;
	}

	public void setCreatedUserRole(int createdUserRole) {
		this.createdUserRole = createdUserRole;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


}
