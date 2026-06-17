package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Shamil
 *
 */
public class ClearanceReportPrintVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ApplicationVO> applicationVOs;
	private String fileName;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("applicationVOs", applicationVOs);
		map.put("fileName", fileName);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	public List<ApplicationVO> getApplicationVOs() {
		return applicationVOs;
	}
	public void setApplicationVOs(List<ApplicationVO> applicationVOs) {
		this.applicationVOs = applicationVOs;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
