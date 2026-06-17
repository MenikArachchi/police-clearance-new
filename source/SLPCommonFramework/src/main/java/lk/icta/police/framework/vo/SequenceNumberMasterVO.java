package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SequenceNumberMasterVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int sequenceNumberMasterId;
	private int currentSequenceNumber;
	private String prefixValue;
	private int numberLength;
	private String description;
	
	
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sequenceNumberMasterId", sequenceNumberMasterId);
		map.put("currentSequenceNumber", currentSequenceNumber);
		map.put("prefixValue", prefixValue);
		map.put("numberLength", numberLength);
		map.put("description", description);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public int getSequenceNumberMasterId() {
		return sequenceNumberMasterId;
	}

	public void setSequenceNumberMasterId(int sequenceNumberMasterId) {
		this.sequenceNumberMasterId = sequenceNumberMasterId;
	}

	public int getCurrentSequenceNumber() {
		return currentSequenceNumber;
	}

	public void setCurrentSequenceNumber(int currentSequenceNumber) {
		this.currentSequenceNumber = currentSequenceNumber;
	}

	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	public int getNumberLength() {
		return numberLength;
	}

	public void setNumberLength(int numberLength) {
		this.numberLength = numberLength;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
