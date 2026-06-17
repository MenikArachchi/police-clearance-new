package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddressAuditVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id ;						//BIGINT
	private long addressId ;				//BIGINT
	private String address;					// VARCHAR(1000) 
	private String policeArea;				// VARCHAR(1000) 
	private Date fromDate;					// DATETIME 
	private Date toDate;					// DATETIME 
	private String fromMessage;				// VARCHAR(1000)
	private String fromSentBy;				// VARCHAR(45)
	private Date fromSentDate;				// DATETIME 
	private String fromReceiveBy;			// VARCHAR(45) 
	private Date fromCreatedDateTime;		// DATETIME
	private String responseMessage;			// VARCHAR(1000) 
	private String responseSentBy;			// VARCHAR(45)
	private Date responseSentDate;			// DATETIME 
	private String responseSentTo;			// VARCHAR(45) 
	private Date responseCreatedDateTime;	// DATETIME 
	private long applicationId;				// BIGINT 
	private String policeStatus;			// VARCHAR(2)
	private String type;					// VARCHAR(2)
	private String policeRecordLockStatus;	//VARCHAR(2)
	
	private int versionId; //INT
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("addressId", addressId);
		map.put("address", address);
		map.put("policeArea", policeArea);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("fromMessage", fromMessage);
		map.put("fromSentBy", fromSentBy);
		map.put("fromSentDate", fromSentDate);
		map.put("fromReceiveBy", fromReceiveBy);
		map.put("fromCreatedDateTime", fromCreatedDateTime);
		map.put("responseMessage", responseMessage);
		map.put("responseSentBy", responseSentBy);
		map.put("responseSentDate", responseSentDate);
		map.put("responseSentTo", responseSentTo);
		map.put("responseCreatedDateTime", responseCreatedDateTime);
		map.put("applicationId", applicationId);
		map.put("policeStatus", policeStatus);
		map.put("type", type);
		map.put("policeRecordLockStatus", policeRecordLockStatus);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPoliceArea() {
		return policeArea;
	}

	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getFromMessage() {
		return fromMessage;
	}

	public void setFromMessage(String fromMessage) {
		this.fromMessage = fromMessage;
	}

	public String getFromSentBy() {
		return fromSentBy;
	}

	public void setFromSentBy(String fromSentBy) {
		this.fromSentBy = fromSentBy;
	}

	public Date getFromSentDate() {
		return fromSentDate;
	}

	public void setFromSentDate(Date fromSentDate) {
		this.fromSentDate = fromSentDate;
	}

	public String getFromReceiveBy() {
		return fromReceiveBy;
	}

	public void setFromReceiveBy(String fromReceiveBy) {
		this.fromReceiveBy = fromReceiveBy;
	}

	public Date getFromCreatedDateTime() {
		return fromCreatedDateTime;
	}

	public void setFromCreatedDateTime(Date fromCreatedDateTime) {
		this.fromCreatedDateTime = fromCreatedDateTime;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseSentBy() {
		return responseSentBy;
	}

	public void setResponseSentBy(String responseSentBy) {
		this.responseSentBy = responseSentBy;
	}

	public Date getResponseSentDate() {
		return responseSentDate;
	}

	public void setResponseSentDate(Date responseSentDate) {
		this.responseSentDate = responseSentDate;
	}

	public String getResponseSentTo() {
		return responseSentTo;
	}

	public void setResponseSentTo(String responseSentTo) {
		this.responseSentTo = responseSentTo;
	}

	public Date getResponseCreatedDateTime() {
		return responseCreatedDateTime;
	}

	public void setResponseCreatedDateTime(Date responseCreatedDateTime) {
		this.responseCreatedDateTime = responseCreatedDateTime;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getPoliceStatus() {
		return policeStatus;
	}

	public void setPoliceStatus(String policeStatus) {
		this.policeStatus = policeStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoliceRecordLockStatus() {
		return policeRecordLockStatus;
	}

	public void setPoliceRecordLockStatus(String policeRecordLockStatus) {
		this.policeRecordLockStatus = policeRecordLockStatus;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	
	
	
}
