package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;

import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.commons.lang3.StringUtils;

public class ClearanceDateGridVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String policeArea;
	private Date sentDate;
	private Date receivedDate;
	private String adverse;
	private String comment;
	private int sentUserId;
	private int respondedUserId;
	
	private long addressId;
	
	private String sentUserName;
	private String respondedUserName;
	
	public ClearanceDateGridVO() {
		super();
	}
	
	public ClearanceDateGridVO(ApplicationClearanceDate applicationClearanceDate, AddressVO addressVO, String comment) {
		this.policeArea=addressVO.getPoliceArea();
		this.sentDate=applicationClearanceDate.getSentDate();
		this.receivedDate=applicationClearanceDate.getResponsedDate();
		this.adverse=addressVO.getPoliceStatus();
		this.addressId=addressVO.getAddressId();
		if(StringUtils.isNotEmpty(adverse)){
			PoliceEnumConstant.ApprovalStatus status=PoliceEnumConstant.ApprovalStatus.fromCode(adverse);
			if(!(status==null)){
				switch (status) {
				case AP:
					this.adverse=status.getDisplayName();
					break;
				case NI:
					this.adverse=status.getDisplayName();
					break;
				case OI:
					this.adverse=status.getDisplayName();
					break;
				case PN:
					this.adverse=status.getDisplayName();
					break;
				case RJ:
					this.adverse=status.getDisplayName();
					break;
				case TU:
					this.adverse="Pending";
					break;
				default:
					this.adverse="Pending";
					break;
				}
			}
		}
		this.comment=comment;
		this.respondedUserId=applicationClearanceDate.getResponsedUserId();
		this.sentUserId=applicationClearanceDate.getSentUserId();
	}
	
	public ClearanceDateGridVO(ApplicationClearanceDate applicationClearanceDate, String adverse, String comment) {
		this.sentDate=applicationClearanceDate.getSentDate();
		this.receivedDate=applicationClearanceDate.getResponsedDate();
		this.adverse="Pending";
		if(StringUtils.isNotEmpty(adverse)){
			PoliceEnumConstant.ApprovalStatus status=PoliceEnumConstant.ApprovalStatus.fromCode(adverse);
			if(!(status==null)){
				switch (status) {
				case AP:
					this.adverse=status.getDisplayName();
					break;
				case NI:
					this.adverse=status.getDisplayName();
					break;
				case OI:
					this.adverse=status.getDisplayName();
					break;
				case PN:
					this.adverse=status.getDisplayName();
					break;
				case RJ:
					this.adverse=status.getDisplayName();
					break;
				case TU:
					this.adverse="Pending";
					break;
				default:
					break;
				}
			}
		}
		this.comment=comment;
		this.respondedUserId=applicationClearanceDate.getResponsedUserId();
		this.sentUserId=applicationClearanceDate.getSentUserId();
	}
	
	
	public String getPoliceArea() {
		return policeArea;
	}
	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getAdverse() {
		return adverse;
	}
	public void setAdverse(String adverse) {
		this.adverse = adverse;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getSentUserId() {
		return sentUserId;
	}

	public void setSentUserId(int sentUserId) {
		this.sentUserId = sentUserId;
	}

	public int getRespondedUserId() {
		return respondedUserId;
	}

	public void setRespondedUserId(int respondedUserId) {
		this.respondedUserId = respondedUserId;
	}

	public String getSentUserName() {
		return sentUserName;
	}

	public void setSentUserName(String sentUserName) {
		this.sentUserName = sentUserName;
	}

	public String getRespondedUserName() {
		return respondedUserName;
	}

	public void setRespondedUserName(String respondedUserName) {
		this.respondedUserName = respondedUserName;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	
	
	
}
