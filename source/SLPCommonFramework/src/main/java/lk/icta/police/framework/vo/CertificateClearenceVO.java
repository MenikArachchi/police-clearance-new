package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.commons.lang3.StringUtils;

public class CertificateClearenceVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long applicationId;
	private String clearenceStatus;
	private String approvalStatus;
	private int updatedUserId;
	private String updatedUserName;
	private int updatedUserType;
	private String certificateSeriaNo;
	private String registeredPostNo;
	
	private String recomendedOfficerName;
	private String approvalLetterName;
	private String comment;
	
	private String oicApprovedStatus;
	private String aspApprovedStatus;
	private String dhaApprovedStatus;
	private String digApprovedStatus;
	
	boolean hasApprovedModified;
	boolean hasClearenceModified;
	
	private int versionId;
	
	private int hasAnyAdverse;
	private int hasBlackListed;
	
	private long letterContentCommentId;
	private String letterContent;
	private String certificateType;
	
	
	private int departmentId;
	private long policeAreaId;
	private String reasonToResend;
	
	private String referenceNo;	
	
	
	public CertificateClearenceVO(long applicationId, String clearenceStatus,
			String approvalStatus, int updatedUserId, String updatedUserName,
			int updatedUserType, String certificateSeriaNo,
			String registeredPostNo, String recomendedOfficerName,
			String comment, int versionId) {
		super();
		this.applicationId = applicationId;
		this.clearenceStatus = clearenceStatus;
		this.approvalStatus = approvalStatus;
		this.updatedUserId = updatedUserId;
		this.updatedUserName = updatedUserName;
		this.updatedUserType = updatedUserType;
		this.certificateSeriaNo = certificateSeriaNo;
		this.registeredPostNo = registeredPostNo;
		this.recomendedOfficerName = recomendedOfficerName;
		this.comment = comment;
		this.versionId=versionId;
	}

	public CertificateClearenceVO() {
		super();
	}

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("applicationId", applicationId);
		map.put("clearenceStatus", clearenceStatus);
		map.put("approvalStatus", approvalStatus);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("updatedUserType", updatedUserType);
		map.put("certificateSeriaNo", certificateSeriaNo);
		map.put("registeredPostNo", registeredPostNo);
		map.put("recomendedOfficerName", recomendedOfficerName);
		map.put("comment", comment);
		map.put("letterContent", letterContent);
		map.put("certificateType", certificateType);
		return map;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toBasicMap().toString();
	}
	public long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getClearenceStatus() {
		return clearenceStatus;
	}
	public void setClearenceStatus(String clearenceStatus) {
		this.clearenceStatus = clearenceStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public String getUpdatedUserName() {
		return updatedUserName;
	}
	public void setUpdatedUserName(String updatedUserName) {
		this.updatedUserName = updatedUserName;
	}

	public int getUpdatedUserType() {
		return updatedUserType;
	}
	public void setUpdatedUserType(int updatedUserType) {
		this.updatedUserType = updatedUserType;
	}

	public String getCertificateSeriaNo() {
		return certificateSeriaNo;
	}
	public void setCertificateSeriaNo(String certificateSeriaNo) {
		this.certificateSeriaNo = certificateSeriaNo;
	}

	public String getRegisteredPostNo() {
		return registeredPostNo;
	}
	public void setRegisteredPostNo(String registeredPostNo) {
		this.registeredPostNo = registeredPostNo;
	}

	public String getRecomendedOfficerName() {
		return recomendedOfficerName;
	}
	public void setRecomendedOfficerName(String recomendedOfficerName) {
		this.recomendedOfficerName = recomendedOfficerName;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isHasApprovedModified() {
		return hasApprovedModified;
	}
	public void setHasApprovedModified(boolean hasApprovedModified) {
		this.hasApprovedModified = hasApprovedModified;
	}
	
	public String getOicApprovedStatus() {
		return oicApprovedStatus;
	}
	public void setOicApprovedStatus(String oicApprovedStatus) {
		this.oicApprovedStatus = oicApprovedStatus;
	}
	
	public String getAspApprovedStatus() {
		return aspApprovedStatus;
	}

	public void setAspApprovedStatus(String aspApprovedStatus) {
		this.aspApprovedStatus = aspApprovedStatus;
	}
	public String getDhaApprovedStatus() {
		return dhaApprovedStatus;
	}

	public void setDhaApprovedStatus(String dhaApprovedStatus) {
		this.dhaApprovedStatus = dhaApprovedStatus;
	}
	public boolean isHasClearenceModified() {
		return hasClearenceModified;
	}

	public void setHasClearenceModified(boolean hasClearenceModified) {
		this.hasClearenceModified = hasClearenceModified;
	}

	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}




	public void updateClearenceVO(int updatedUserId, String updatedUserName, int updatedUserType) {
		
		if(StringUtils.isNotEmpty(this.clearenceStatus)){
			this.clearenceStatus=StringUtils.trim(this.clearenceStatus);			
			if(StringUtils.equals(this.clearenceStatus, PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())){
				this.approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
			}else if(StringUtils.equals(this.clearenceStatus, PoliceEnumConstant.ApplicationClearenceStatus.CP.toString())){
					this.approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
			}else if(StringUtils.equals(this.clearenceStatus, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString())){
				this.approvalStatus=PoliceEnumConstant.ApprovalStatus.RJ.toString();
			}else{
				this.approvalStatus=PoliceEnumConstant.ApprovalStatus.PN.toString();
			}
			
		}

		if(StringUtils.isNotEmpty(this.oicApprovedStatus)){
			this.oicApprovedStatus=StringUtils.trim(this.oicApprovedStatus);
			this.approvalStatus=constructApprovalStatus(this.oicApprovedStatus);
		}
		
		if(StringUtils.isNotEmpty(this.aspApprovedStatus)){
			this.aspApprovedStatus=StringUtils.trim(this.aspApprovedStatus);
			this.approvalStatus=constructApprovalStatus(this.aspApprovedStatus);
		}
		
		if(StringUtils.isNotEmpty(this.dhaApprovedStatus)){
			this.dhaApprovedStatus=StringUtils.trim(this.dhaApprovedStatus);
			this.approvalStatus=constructApprovalStatus(this.dhaApprovedStatus);
		}
		
		if(StringUtils.isNotEmpty(this.digApprovedStatus)){
			this.digApprovedStatus=StringUtils.trim(this.digApprovedStatus);
			this.approvalStatus=constructApprovalStatus(this.digApprovedStatus);
		}
		
		if(StringUtils.isNotEmpty(this.recomendedOfficerName)){
			this.recomendedOfficerName=StringUtils.trim(this.recomendedOfficerName);
		}
		
		if(StringUtils.isNotEmpty(this.comment)){
			this.comment=StringUtils.trim(this.comment);
		}
		
		if(StringUtils.isNotEmpty(this.certificateSeriaNo)){
			this.certificateSeriaNo=StringUtils.trim(this.certificateSeriaNo);
		}
		
		if(StringUtils.isNotEmpty(this.registeredPostNo)){
			this.registeredPostNo=StringUtils.trim(this.registeredPostNo);
		}
		
		if(StringUtils.isNotEmpty(this.letterContent)){
			this.letterContent=StringUtils.trim(this.letterContent);
		}
		
		if(StringUtils.isNotEmpty(this.certificateType)){
			this.certificateType=StringUtils.trim(this.certificateType);
		}
		
		if(StringUtils.isNotEmpty(this.approvalLetterName)){
			this.approvalLetterName=StringUtils.trim(this.approvalLetterName);
		}
		
		this.updatedUserId=updatedUserId;		
		this.updatedUserName=updatedUserName;
		this.updatedUserType=updatedUserType;
	}




	private String constructApprovalStatus(String statusFromUI) {
		if(StringUtils.equals(statusFromUI, "Y")){
			return PoliceEnumConstant.ApprovalStatus.AP.toString();
		}else if(StringUtils.equals(statusFromUI, "N")){
			return PoliceEnumConstant.ApprovalStatus.RJ.toString();
		}
		return null;
	}


	public int getHasAnyAdverse() {
		return hasAnyAdverse;
	}
	public void setHasAnyAdverse(int hasAnyAdverse) {
		this.hasAnyAdverse = hasAnyAdverse;
	}

	public int getHasBlackListed() {
		return hasBlackListed;
	}
	public void setHasBlackListed(int hasBlackListed) {
		this.hasBlackListed = hasBlackListed;
	}

	public String getDigApprovedStatus() {
		return digApprovedStatus;
	}
	public void setDigApprovedStatus(String digApprovedStatus) {
		this.digApprovedStatus = digApprovedStatus;
	}

	public String getLetterContent() {
		return letterContent;
	}
	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getApprovalLetterName() {
		return approvalLetterName;
	}

	public void setApprovalLetterName(String approvalLetterName) {
		this.approvalLetterName = approvalLetterName;
	}

	public long getLetterContentCommentId() {
		return letterContentCommentId;
	}

	public void setLetterContentCommentId(long letterContentCommentId) {
		this.letterContentCommentId = letterContentCommentId;
	}

	public String getReasonToResend() {
		return reasonToResend;
	}

	public void setReasonToResend(String reasonToResend) {
		this.reasonToResend = reasonToResend;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public long getPoliceAreaId() {
		return policeAreaId;
	}

	public void setPoliceAreaId(long policeAreaId) {
		this.policeAreaId = policeAreaId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	

	
	
	
}
