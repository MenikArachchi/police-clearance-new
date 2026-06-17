package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CertificateAuthPersonVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	  private long id; //bigint(20) NOT NULL,
	  private String name; //varchar(1000) DEFAULT NULL,
	  private String type; //varchar(2) DEFAULT NULL,
	  private Date effectiveStartDate; //datetime DEFAULT NULL,
	  private Date effectiveEndDate; //datetime DEFAULT NULL,
	  
	  private String designation;
	  private String address;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("type", type);
		map.put("effectiveStartDate", effectiveStartDate);
		map.put("effectiveEndDate", effectiveEndDate);
		map.put("designation", designation);
		map.put("address", address);
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}


	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}


	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}


	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

	
	
}
