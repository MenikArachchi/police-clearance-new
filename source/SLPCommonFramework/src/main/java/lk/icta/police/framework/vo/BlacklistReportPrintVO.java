package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlacklistReportPrintVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<BlackListVO> blacklistVOs;
	private String fileName;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("blacklistVOs", blacklistVOs);
		map.put("fileName", fileName);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	
	public List<BlackListVO> getBlacklistVOs() {
		return blacklistVOs;
	}
	public void setBlacklistVOs(List<BlackListVO> blacklistVOs) {
		this.blacklistVOs = blacklistVOs;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
