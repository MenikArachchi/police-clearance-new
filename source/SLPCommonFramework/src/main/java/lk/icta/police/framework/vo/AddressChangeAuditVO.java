package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddressChangeAuditVO implements Serializable,Comparable<AddressChangeAuditVO> {

	private static final long serialVersionUID = 1L;
	
	
	private long id; //bigint(20) NOT NULL AUTO_INCREMENT,
	private long addressId; //bigint(20) DEFAULT NULL,
	private int updatedUserId; //int(11) DEFAULT NULL,
	private String updatedUserName; //varchar(500) DEFAULT NULL,
	private Date updatedUserDateTime; //datetime DEFAULT NULL,
	private String comment; //varchar(1000) //DEFAULT NULL,
	
	private String policeArea;
	private String address;

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("addressId", addressId);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("updatedUserDateTime", updatedUserDateTime);
		map.put("comment", comment);
		
		map.put("policeArea", policeArea);
		map.put("address", address);
		return map;
	}
	
	public AddressChangeAuditVO() {
		super();
	}

	public AddressChangeAuditVO(long addressId, int updatedUserId,	String updatedUserName, String comment) {
		super();
		this.addressId = addressId;
		this.updatedUserId = updatedUserId;
		this.updatedUserName = updatedUserName;
		this.updatedUserDateTime = Calendar.getInstance().getTime();
		this.comment = comment;
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


	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
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

	public Date getUpdatedUserDateTime() {
		return updatedUserDateTime;
	}

	public void setUpdatedUserDateTime(Date updatedUserDateTime) {
		this.updatedUserDateTime = updatedUserDateTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPoliceArea() {
		return policeArea;
	}

	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int compareTo(AddressChangeAuditVO o) {
		if(!(this.updatedUserDateTime==null || o.getUpdatedUserDateTime()==null)){
			return o.getUpdatedUserDateTime().compareTo(this.updatedUserDateTime);
		}
		return (new Long(this.id).compareTo(new Long(o.getId())));
	}
	
	
	
}
