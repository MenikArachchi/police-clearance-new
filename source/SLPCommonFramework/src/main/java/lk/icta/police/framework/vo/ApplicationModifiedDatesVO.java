package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationModifiedDatesVO  implements Serializable, Comparable<ApplicationModifiedDatesVO> {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long applicationId;
	private Date modifiedDate;
	private String dateType;
	private String modification;
	private int modifiedUserId;
	private String modifiedUserName;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("applicationId", applicationId);
		map.put("modifiedDate", modifiedDate);
		map.put("dateType", dateType);
		map.put("modification", modification);
		map.put("modifiedUserId", modifiedUserId);
		map.put("modifiedUserName", modifiedUserName);		
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

	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}

	public int getModifiedUserId() {
		return modifiedUserId;
	}
	public void setModifiedUserId(int modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}


	public String getModifiedUserName() {
		return modifiedUserName;
	}
	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	//sort in decending order
	@Override
	public int compareTo(ApplicationModifiedDatesVO datesVO) {
		return datesVO.getModifiedDate().compareTo(this.modifiedDate);
	}

	
	
	
}
