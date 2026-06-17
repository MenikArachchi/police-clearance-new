package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoliceFormVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -364863082913158636L;
	private long applicationId;
	private String referenceNo;	
	private long policeAreaId;
	private String policeArea;
	private List<PoliceFormItemVO> policeFormItemVOs;
	
	

	public PoliceFormVO(long applicationId, String referenceNo,
			long policeAreaId, String policeArea,
			List<PoliceFormItemVO> policeFormItemVOs) {
		super();
		this.applicationId = applicationId;
		this.referenceNo = referenceNo;
		this.policeAreaId = policeAreaId;
		this.policeArea = policeArea;
		this.policeFormItemVOs = policeFormItemVOs;
	}

	public PoliceFormVO() {
		super();
	}

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("applicationId", applicationId);
		map.put("referenceNo", referenceNo);
		map.put("policeAreaId", policeAreaId);
		map.put("policeArea", policeArea);
		map.put("policeFormGridVOList", policeFormItemVOs);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	
	public String getPoliceArea() {
		return policeArea;
	}
	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public long getPoliceAreaId() {
		return policeAreaId;
	}

	public void setPoliceAreaId(long policeAreaId) {
		this.policeAreaId = policeAreaId;
	}

	public List<PoliceFormItemVO> getPoliceFormItemVOs() {
		return policeFormItemVOs;
	}

	public void setPoliceFormItemVOs(List<PoliceFormItemVO> policeFormItemVOs) {
		this.policeFormItemVOs = policeFormItemVOs;
	}
	
	
}
