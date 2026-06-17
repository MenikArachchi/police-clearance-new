package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class NicRevisionClearenceVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long applicationId;
	private String nicFileName;
	private String nicFileNameBack;
	private String nicRevisionChangedName;
	
	private int nicRevisionVersionId;
	private boolean hasUploadedNicFile;
	
	private int updatedUserId;
	private String updatedUserName;
	private int updatedUserType;
	
	private String emailText;
	
	private boolean updateClearenceStatus;
	
	private String clearenceStatus;
	
	private boolean sendApplicationForApprovalAgain;
	
	public NicRevisionClearenceVO(long applicationId, String nicFileName, String nicFileNameBack,
			String nicRevisionChangedName, boolean updateClearenceStatus, int nicRevisionVersionId,
			int updatedUserId, String updatedUserName,
			int updatedUserType) {
		super();
		this.applicationId = applicationId;
		this.nicFileName = nicFileName;
		this.nicRevisionChangedName = nicRevisionChangedName;
		this.updateClearenceStatus = updateClearenceStatus;
		this.nicRevisionVersionId = nicRevisionVersionId;
		this.updatedUserId = updatedUserId;
		this.updatedUserName = updatedUserName;
		this.updatedUserType = updatedUserType;
		this.nicFileNameBack=nicFileNameBack;
	}


	public NicRevisionClearenceVO() {
		super();
	}


	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("applicationId", applicationId);
		map.put("nicFileName", nicFileName);
		map.put("nicFileNameBack", nicFileNameBack);
		map.put("nicRevisionChangedName", nicRevisionChangedName);
		map.put("updateClearenceStatus", updateClearenceStatus);
		map.put("nicRevisionVersionId", nicRevisionVersionId);
		map.put("hasUploadedNicFile", hasUploadedNicFile);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("updatedUserType", updatedUserType);
		map.put("clearenceStatus", clearenceStatus);
		map.put("sendApplicationForApprovalAgain", sendApplicationForApprovalAgain);
		return map;
	}

	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}


	
	public void updateNicRevisionClearenceVO(int updatedUserId, String updatedUserName, int updatedUserType) {
		if(StringUtils.isNotEmpty(this.nicFileName)){
			this.nicFileName=StringUtils.trim(nicFileName);
			this.nicFileNameBack=StringUtils.trim(nicFileNameBack);
			this.hasUploadedNicFile=true;
		}

		if(StringUtils.isNotEmpty(this.nicRevisionChangedName)){
			this.nicRevisionChangedName=StringUtils.trim(this.nicRevisionChangedName);
		}
		
		
		if(StringUtils.isNotEmpty(this.emailText)){
			this.emailText=StringUtils.trim(this.emailText);
		}
		
		this.updatedUserId=updatedUserId;		
		this.updatedUserName=updatedUserName;
		this.updatedUserType=updatedUserType;
	}


	public long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}


	public String getNicFileName() {
		return nicFileName;
	}


	public void setNicFileName(String nicFileName) {
		this.nicFileName = nicFileName;
	}


	public String getNicRevisionChangedName() {
		return nicRevisionChangedName;
	}


	public void setNicRevisionChangedName(String nicRevisionChangedName) {
		this.nicRevisionChangedName = nicRevisionChangedName;
	}


	public int getNicRevisionVersionId() {
		return nicRevisionVersionId;
	}


	public void setNicRevisionVersionId(int nicRevisionVersionId) {
		this.nicRevisionVersionId = nicRevisionVersionId;
	}


	public boolean isHasUploadedNicFile() {
		return hasUploadedNicFile;
	}


	public void setHasUploadedNicFile(boolean hasUploadedNicFile) {
		this.hasUploadedNicFile = hasUploadedNicFile;
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


	public String getEmailText() {
		return emailText;
	}


	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}


	public boolean isUpdateClearenceStatus() {
		return updateClearenceStatus;
	}

	public void setUpdateClearenceStatus(boolean updateClearenceStatus) {
		this.updateClearenceStatus = updateClearenceStatus;
	}


	public String getClearenceStatus() {
		return clearenceStatus;
	}


	public void setClearenceStatus(String clearenceStatus) {
		this.clearenceStatus = clearenceStatus;
	}


	public boolean isSendApplicationForApprovalAgain() {
		return sendApplicationForApprovalAgain;
	}


	public void setSendApplicationForApprovalAgain(
			boolean sendApplicationForApprovalAgain) {
		this.sendApplicationForApprovalAgain = sendApplicationForApprovalAgain;
	}


	public String getNicFileNameBack() {
		return nicFileNameBack;
	}


	public void setNicFileNameBack(String nicFileNameBack) {
		this.nicFileNameBack = nicFileNameBack;
	}





	
	
	
}
