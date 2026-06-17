package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ClearenceGridButton implements Serializable,Comparable<ClearenceGridButton> {
	
	private static final long serialVersionUID = 1L;
	
	private String label;
	private long maxId;
	private int pageNo;
	private int displayStatus;
	private int currentButtonStatus;
	private int limit;
	
	private boolean lastPage;
	
	
	public ClearenceGridButton() {
		super();
	}
	
	

	public ClearenceGridButton(String label, long maxId, int pageNo,
			int displayStatus, int noOfRecords,int currentButtonStatus,int limit) {
		super();
		this.label = label;
		this.maxId = maxId;
		this.pageNo = pageNo;
		this.displayStatus = displayStatus;
		this.currentButtonStatus=currentButtonStatus;
		this.limit=limit;
	}



	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("label", label);
		map.put("maxId", maxId);
		map.put("pageNo", pageNo);
		map.put("displayStatus", displayStatus);
		map.put("currentButtonStatus", currentButtonStatus);
		map.put("limit", limit);
		map.put("lastPage", lastPage);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getDisplayStatus() {
		return displayStatus;
	}
	public void setDisplayStatus(int displayStatus) {
		this.displayStatus = displayStatus;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCurrentButtonStatus() {
		return currentButtonStatus;
	}
	public void setCurrentButtonStatus(int currentButtonStatus) {
		this.currentButtonStatus = currentButtonStatus;
	}

	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getMaxId() {
		return maxId;
	}
	public void setMaxId(long maxId) {
		this.maxId = maxId;
	}

	@Override
	public int compareTo(ClearenceGridButton o) {
		return this.pageNo-o.getPageNo();
	}

	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
	

}
