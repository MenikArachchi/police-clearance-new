package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class NotificationVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private long notificationId;// BIGINT NOT NULL ,
	private long applicationId;// BIGINT NULL ,
	private long addressId;// BIGINT NULL ,
	private String senderName;// VARCHAR(500) NULL ,
	private String senderAddress;// VARCHAR(500) NULL ,
	private String recieverName;// VARCHAR(500) NULL ,
	private String recieverAddress;// VARCHAR(500) NULL ,
	private String messageContent;// VARCHAR(1000) NULL ,
	private String notificationType;// VARCHAR(2) NULL ,
	private String sentStatus;// VARCHAR(2) NULL ,
	private Date updatedDate;// DATETIME NULL ,
	private int updatedUserId;// INT NULL ,
	private String updatedUserName;// VARCHAR(500) NULL ,
	private String comment;// VARCHAR(1000) NULL ,
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("notificationId", notificationId);
		map.put("applicationId", applicationId);
		map.put("addressId", addressId);
		map.put("senderName", senderName);
		map.put("senderAddress", senderAddress);
		map.put("recieverName", recieverName);
		map.put("recieverAddress", recieverAddress);
		map.put("messageContent", messageContent);
		map.put("notificationType", notificationType);
		map.put("sentStatus", sentStatus);
		map.put("updatedDate", updatedDate);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("comment", comment);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	public long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
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
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getRecieverName() {
		return recieverName;
	}
	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}
	public String getRecieverAddress() {
		return recieverAddress;
	}
	public void setRecieverAddress(String recieverAddress) {
		this.recieverAddress = recieverAddress;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getSentStatus() {
		return sentStatus;
	}
	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
