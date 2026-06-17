package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyTransactionReportPrintVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<DailyTransactionVO> dailyTransactionVOs;
	private String fileName;
	
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("dailyTransactionVOs", dailyTransactionVOs);
		map.put("fileName", fileName);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	
	public List<DailyTransactionVO> getDailyTransactionVOs() {
		return dailyTransactionVOs;
	}
	public void setDailyTransactionVOs(List<DailyTransactionVO> dailyTransactionVOs) {
		this.dailyTransactionVOs = dailyTransactionVOs;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
