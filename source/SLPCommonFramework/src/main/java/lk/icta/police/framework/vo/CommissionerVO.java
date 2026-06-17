package lk.icta.police.framework.vo;

import java.util.HashMap;
import java.util.Map;

public class CommissionerVO {

private static final long serialVersionUID = 1L;
	
	private long id;				// BIGINT
	private String commissionEmbassyConsultantType;
	private int activeStatus;
	private String commissionEmbassyConsultantName;
	private String commissionEmbassyConsultantAddress;
	private long countryId;
	private String addressee;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("commissionEmbassyConsultantName", commissionEmbassyConsultantName);
		map.put("commissionEmbassyConsultantType", commissionEmbassyConsultantType);
		map.put("commissionEmbassyConsultantAddress", commissionEmbassyConsultantAddress);
		map.put("countryId", countryId);
		map.put("addressee", addressee);
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

	public String getCommissionEmbassyConsultantType() {
		return commissionEmbassyConsultantType;
	}

	public void setCommissionEmbassyConsultantType(
			String commissionEmbassyConsultantType) {
		this.commissionEmbassyConsultantType = commissionEmbassyConsultantType;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getCommissionEmbassyConsultantName() {
		return commissionEmbassyConsultantName;
	}

	public void setCommissionEmbassyConsultantName(
			String commissionEmbassyConsultantName) {
		this.commissionEmbassyConsultantName = commissionEmbassyConsultantName;
	}

	public String getCommissionEmbassyConsultantAddress() {
		return commissionEmbassyConsultantAddress;
	}

	public void setCommissionEmbassyConsultantAddress(
			String commissionEmbassyConsultantAddress) {
		this.commissionEmbassyConsultantAddress = commissionEmbassyConsultantAddress;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
	
}
