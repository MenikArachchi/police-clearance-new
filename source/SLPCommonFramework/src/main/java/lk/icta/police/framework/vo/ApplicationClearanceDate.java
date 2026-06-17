package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationClearanceDate implements Serializable,Comparable<ApplicationClearanceDate> {

	private static final long serialVersionUID = 1L;
	
	private long id;				// BIGINT
	private long applicationId;		// BIGINT
	private long addressId;			// BIGINT
	private String department;		// VARCHAR(10)
	// application sent date to the external department
	private Date sentDate;		// DATETIME
	// the response recieved date from external department
	private Date responsedDate;	// DATETIME
	private int printedStatus;		// INT
	private int sentUserId;			// INT
	private int responsedUserId;    // INT
	private String comment;
	private String type;
	
	public ApplicationClearanceDate() {
		super();
	}
	

	public ApplicationClearanceDate(long applicationId, long addressId,
			String department, Date sentDate, int sentUserId, String type, String comment) {
		super();
		this.applicationId = applicationId;
		this.addressId = addressId;
		this.department = department;
		this.sentDate = sentDate;
		this.sentUserId = sentUserId;
		this.type = type;
		this.comment=comment;
	}

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("applicationId", applicationId);
		map.put("department", department);
		map.put("sentDate", sentDate);
		map.put("responsedDate", responsedDate);
		map.put("printedStatus", printedStatus);
		map.put("sentUserId", sentUserId);
		map.put("responsedUserId", responsedUserId);
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

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}



	public int getPrintedStatus() {
		return printedStatus;
	}

	public void setPrintedStatus(int printedStatus) {
		this.printedStatus = printedStatus;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getResponsedDate() {
		return responsedDate;
	}

	public void setResponsedDate(Date responsedDate) {
		this.responsedDate = responsedDate;
	}

	public int getSentUserId() {
		return sentUserId;
	}

	public void setSentUserId(int sentUserId) {
		this.sentUserId = sentUserId;
	}

	public int getResponsedUserId() {
		return responsedUserId;
	}

	public void setResponsedUserId(int responsedUserId) {
		this.responsedUserId = responsedUserId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(ApplicationClearanceDate o) {
		if(!(this.responsedDate==null || o.getResponsedDate()==null)){
			return this.responsedDate.compareTo(o.getResponsedDate());
		}
		return this.sentDate.compareTo(o.getSentDate());
	}


	
}
