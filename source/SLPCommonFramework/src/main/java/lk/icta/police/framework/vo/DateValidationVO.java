package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateValidationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String addressValue;
	private String policeArea;
	private String fromDate;
	private String toDate;
	
	public String getAddressValue() {
		return addressValue;
	}
	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}
	public String getPoliceArea() {
		return policeArea;
	}
	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public Date getFromDateAsDate() throws ParseException{
		if(!(StringUtils.isEmpty(fromDate))){
			//22/03/2016
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			return format.parse(fromDate);
		}
		return null;
	}
	
	public Date getToDateAsDate() throws ParseException{
		if(!(StringUtils.isEmpty(toDate))){
			//22/03/2016
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			return format.parse(toDate);
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return "DateValidationVO [addressValue=" + addressValue
				+ ", policeArea=" + policeArea + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	
	

}
