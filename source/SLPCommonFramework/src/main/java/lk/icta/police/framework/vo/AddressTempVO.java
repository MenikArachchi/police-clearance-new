package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.commons.lang3.StringUtils;

public class AddressTempVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private long addressTempId;// BIGINT NOT NULL ,
	  private long addressId;// BIGINT NULL ,
	  private String address;// VARCHAR(1000) NULL ,
	  private String addressStatus;// VARCHAR(2) NULL ,
	  private long policeAreaId;// BIGINT NULL ,
	  private String  policeArea;// VARCHAR(1000) NULL ,
	  private String  policeAreaStatus;// VARCHAR(2) NULL ,
	  private Date fromDate;// DATETIME NULL ,
	  private Date toDate;// DATETIME NULL ,
	  private String stayPeriodStatus;// VARCHAR(2) NULL ,
	  private String comment;// VARCHAR(1000) NULL ,
	  private int activeStatus;// VARCHAR(45) NULL ,
	  private int updatedUserId;// INT NULL ,
	  private String updatedUserName;// VARCHAR(500) NULL ,
	  private Date updatedDate;
	  
	  private String addressStatusText;
	  private String  policeAreaStatusText;
	  private String stayPeriodStatusText;
	  private String updatedByText;
	  
	  private String emailType;
	  private String emailContent;  	
	  private int sendEmailByPhq;
	  
	 public AddressTempVO() {
	 		// TODO Auto-generated constructor stub
	 }
	 
	 
	  
	 public AddressTempVO(long addressId, String address, String addressStatus,
			long policeAreaId, String policeArea, String policeAreaStatus,
			Date fromDate, Date toDate, String stayPeriodStatus,
			String comment, int activeStatus, int updatedUserId,
			String updatedUserName, Date updatedDate) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.addressStatus = addressStatus;
		this.policeAreaId = policeAreaId;
		this.policeArea = policeArea;
		this.policeAreaStatus = policeAreaStatus;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.stayPeriodStatus = stayPeriodStatus;
		this.comment = comment;
		this.activeStatus = activeStatus;
		this.updatedUserId = updatedUserId;
		this.updatedUserName = updatedUserName;
		this.updatedDate = updatedDate;
	}



	public AddressTempVO(AddressVO addressVO) {
		 this.addressId=addressVO.getAddressId();
		 this.address=addressVO.getAddress();
		 this.addressStatus=null;
		 this.policeAreaId=addressVO.getPoliceAreaId();
		 this.policeArea=addressVO.getPoliceArea();
		 this.policeAreaStatus=null;
		 this.fromDate=addressVO.getFromDate();
		 this.toDate=addressVO.getToDate();
		 this.stayPeriodStatus=null;
		 this.addressStatusText="N/A";
		 this.policeAreaStatusText="N/A";
		 this.stayPeriodStatusText="N/A";
		 this.updatedByText="N/A";
	 }

	public Map<String,Object> toBasicMap(){
		 Map<String,Object> map=new HashMap<String, Object>();
		 map.put("addressTempId", addressTempId);
		 map.put("addressId", addressId);
		 map.put("address", address);
		 map.put("addressStatus", addressStatus);
		 map.put("policeAreaId", policeAreaId);
		 map.put("policeArea", policeArea);
		 map.put("policeAreaStatus", policeAreaStatus);
		 map.put("fromDate", fromDate);
		 map.put("toDate", toDate);
		 map.put("stayPeriodStatus", stayPeriodStatus);
		 map.put("comment", comment);
		 map.put("activeStatus", activeStatus);
		 map.put("updatedUserId", updatedUserId);
		 map.put("updatedUserName", updatedUserName);
		 map.put("updatedDate", updatedDate);
		 return map;
	 } 
	 
	 @Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	  
	public long getAddressTempId() {
		return addressTempId;
	}
	public void setAddressTempId(long addressTempId) {
		this.addressTempId = addressTempId;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressStatus() {
		return addressStatus;
	}
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
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
	public String getPoliceAreaStatus() {
		return policeAreaStatus;
	}
	public void setPoliceAreaStatus(String policeAreaStatus) {
		this.policeAreaStatus = policeAreaStatus;
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

	public String getStayPeriodStatus() {
		return stayPeriodStatus;
	}
	public void setStayPeriodStatus(String stayPeriodStatus) {
		this.stayPeriodStatus = stayPeriodStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getAddressStatusText() {
		return addressStatusText;
	}
	public void setAddressStatusText(String addressStatusText) {
		this.addressStatusText = addressStatusText;
	}

	public String getPoliceAreaStatusText() {
		return policeAreaStatusText;
	}
	public void setPoliceAreaStatusText(String policeAreaStatusText) {
		this.policeAreaStatusText = policeAreaStatusText;
	}

	public String getStayPeriodStatusText() {
		return stayPeriodStatusText;
	}
	public void setStayPeriodStatusText(String stayPeriodStatusText) {
		this.stayPeriodStatusText = stayPeriodStatusText;
	}



	public String getUpdatedByText() {
		return updatedByText;
	}
	public void setUpdatedByText(String updatedByText) {
		this.updatedByText = updatedByText;
	}

	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public int getSendEmailByPhq() {
		return sendEmailByPhq;
	}
	public void setSendEmailByPhq(int sendEmailByPhq) {
		this.sendEmailByPhq = sendEmailByPhq;
	}

	public void initializeStatusText(AddressVO addressVO) {
		if(StringUtils.isNotEmpty(this.addressStatus)){
			this.addressStatusText=PoliceEnumConstant.AddressModificationStatus.fromCode(this.addressStatus).getDisplayName();
		}
		
		if(StringUtils.isNotEmpty(this.policeAreaStatus)){
			this.policeAreaStatusText=PoliceEnumConstant.AddressModificationStatus.fromCode(this.policeAreaStatus).getDisplayName();
		}
		
		if(StringUtils.isNotEmpty(this.stayPeriodStatus)){
			this.stayPeriodStatusText=PoliceEnumConstant.AddressModificationStatus.fromCode(this.stayPeriodStatus).getDisplayName();
		}	
		
		if(!(this.updatedDate==null || addressVO==null)){
			this.updatedByText=addressVO.getPoliceArea() + " on " + new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(this.updatedDate);
		}
		
		
	}
	  
	  
	  
	
	
}
