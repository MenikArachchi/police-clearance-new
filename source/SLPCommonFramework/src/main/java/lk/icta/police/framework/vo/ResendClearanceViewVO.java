package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResendClearanceViewVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 
	  private long addressId;// BIGINT NULL ,
	  private String address;
	  private String currentStatus;
	  private String currentComment;
	  private String currentClearanceDate;
	  
	  
	  
	 public ResendClearanceViewVO(long addressId, String address,
			String currentStatus, String currentComment,
			String currentClearanceDate) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.currentStatus = currentStatus;
		this.currentComment = currentComment;
		this.currentClearanceDate = currentClearanceDate;
	}


	public ResendClearanceViewVO() {
	 		// TODO Auto-generated constructor stub
	 }
	 
	
	public Map<String,Object> toBasicMap(){
		 Map<String,Object> map=new HashMap<String, Object>();
		 map.put("addressId", addressId);
		 map.put("address", address);
		 map.put("currentStatus", currentStatus);
		 map.put("currentComment", currentComment);
		 map.put("currentClearanceDate", currentClearanceDate);
		 return map;
	 } 
	 
	 @Override
	public String toString() {
		return this.toBasicMap().toString();
	}


	public long getAddressId() {
		return addressId;
	}


	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public String getCurrentStatus() {
		return currentStatus;
	}


	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}


	public String getCurrentComment() {
		return currentComment;
	}


	public void setCurrentComment(String currentComment) {
		this.currentComment = currentComment;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getCurrentClearanceDate() {
		return currentClearanceDate;
	}
	public void setCurrentClearanceDate(String currentClearanceDate) {
		this.currentClearanceDate = currentClearanceDate;
	}
	  
	  
	  
	  
	
	
}
