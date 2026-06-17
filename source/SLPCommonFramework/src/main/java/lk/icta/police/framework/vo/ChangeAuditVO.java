package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChangeAuditVO implements Serializable,Comparable<ChangeAuditVO> {

	private static final long serialVersionUID = 1L;
	
	private long id; 					//BIGINT
	private long applicationId; 		//BIGINT
	private int updatedUserId; 			//INT
	private String updatedUserName; 	//VARCHAR(500)
	private Date updatedUserDateTime; 	//DATETIME
	private String comment; 			//VARCHAR(1000)

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("applicationId", applicationId);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("updatedUserDateTime", updatedUserDateTime);
		map.put("comment", comment);
		return map;
	}
	
	public ChangeAuditVO() {
		super();
	}

	public ChangeAuditVO(long applicationId, int updatedUserId,	String updatedUserName, String comment) {
		super();
		this.applicationId = applicationId;
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

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
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
	
	@Override
	public int compareTo(ChangeAuditVO o) {
		if(!(this.updatedUserDateTime==null || o.getUpdatedUserDateTime()==null)){
			return o.getUpdatedUserDateTime().compareTo(this.updatedUserDateTime);
		}
		return (new Long(this.id).compareTo(new Long(o.getId())));
	}
	
}
