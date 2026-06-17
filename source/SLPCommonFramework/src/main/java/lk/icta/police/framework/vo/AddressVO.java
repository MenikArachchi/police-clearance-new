package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddressVO  implements Serializable,Comparable<AddressVO> {
	
	private static final long serialVersionUID = 1L;
	
	private long addressId ;				//BIGINT
	private String address;					// VARCHAR(1000) 
	private long policeAreaId;
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
	private int policeRecordLockStatus;	//VARCHAR(2)
	
	private int versionId; //INT
	
	private AddressTempVO addressTempVO;
	
	//for display purposes
	private String fromDateStr;
	private String toDateStr;
	
	private String modificationComment;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("addressId", addressId);
		map.put("address", address);
		map.put("policeAreaId", policeAreaId);
		map.put("policeArea", policeArea);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("fromDateStr", fromDateStr);
		map.put("toDateStr", toDateStr);
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
		map.put("modificationComment", modificationComment);
		return map;
	}
	
	
		
	public void constructDateStrings(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		if(!(this.fromDate==null)){
			this.fromDateStr=dateFormat.format(this.fromDate);
		}		
		if(!(this.toDate==null)){
			this.toDateStr=dateFormat.format(this.toDate);
		}
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getPoliceRecordLockStatus() {
		return policeRecordLockStatus;
	}
	public void setPoliceRecordLockStatus(int policeRecordLockStatus) {
		this.policeRecordLockStatus = policeRecordLockStatus;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getFromDateStr() {
		return fromDateStr;
	}

	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}

	public String getToDateStr() {
		return toDateStr;
	}

	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	
	public static Comparator<AddressVO> AddressFromDateComparator  = new Comparator<AddressVO>() {
		public int compare(AddressVO address1, AddressVO address2) {
			//ascending order
			return address1.getFromDate().compareTo(address2.getFromDate());
		}
	};
		
	public static Comparator<AddressVO> AddressToDateComparator  = new Comparator<AddressVO>() {
		public int compare(AddressVO address1, AddressVO address2) {
			//descending order
			return address2.getToDate().compareTo(address1.getToDate());
		}
	};

	public AddressTempVO getAddressTempVO() {
		return addressTempVO;
	}

	public void setAddressTempVO(AddressTempVO addressTempVO) {
		this.addressTempVO = addressTempVO;
	}



	public String getModificationComment() {
		return modificationComment;
	}
	public void setModificationComment(String modificationComment) {
		this.modificationComment = modificationComment;
	}



	@Override
	public int compareTo(AddressVO o) {
		return (new Long(this.getPoliceAreaId())).compareTo(new Long(o.getPoliceAreaId()));
	}
	
	
}
