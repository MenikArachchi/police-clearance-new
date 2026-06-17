package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PoliceAreaVO implements Serializable,Comparable<PoliceAreaVO> {

	private static final long serialVersionUID = 1L;
	
	private long id;				// BIGINT
	private String policeArea;		
	
	public PoliceAreaVO(long id, String policeArea) {
		super();
		this.id = id;
		this.policeArea = policeArea;
	}
	
	public PoliceAreaVO() {
		super();
	}

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("policeArea", policeArea);
		return map;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean sameSame = false;
        if (object != null && object instanceof PoliceAreaVO){
            sameSame = (this.id == (((PoliceAreaVO) object).id));
        }
        return sameSame;
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

	public String getPoliceArea() {
		return policeArea;
	}

	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}

	@Override
	public int compareTo(PoliceAreaVO o) {
		return (new Long(this.id)).compareTo((new Long(o.getId())));
	}

}
