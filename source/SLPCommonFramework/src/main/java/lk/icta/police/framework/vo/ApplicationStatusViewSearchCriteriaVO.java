package lk.icta.police.framework.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationStatusViewSearchCriteriaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date fromDate;
	private int fromDateSqlInjection;
	
	private Date toDate;
	private int toDateSqlInjection;
	
	private String referenceNo;
	private int referenceNoSqlInjection;
	
	private String nicNo;
	private int nicNoSqlInjection;

	private String newNicNo;
	private int newNicNoSqlInjection;
	
	private String pptNo;
	private int pptNoSqlInjection;
	
	private String name;
	private int nameSqlInjection;
	

	public ApplicationStatusViewSearchCriteriaVO() {
		super();
	}

	public ApplicationStatusViewSearchCriteriaVO(String referenceNo,
			String nicNo, String pptNo, Date fromDate, Date toDate) {
		super();
		this.referenceNo = referenceNo;
		this.nicNo = nicNo;
		this.pptNo = pptNo;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public ApplicationStatusViewSearchCriteriaVO(String referenceNo,
												 String nicNo, String newNicNo ,String pptNo, Date fromDate, Date toDate) {
		super();
		this.referenceNo = referenceNo;
		this.nicNo = nicNo;
		this.newNicNo = newNicNo;
		this.pptNo = pptNo;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}


	public void updateSearchCriteriaVO(int userRole, long location){
		
		if(this.referenceNo==null || this.referenceNo.trim().equals("")){
			this.referenceNoSqlInjection=1;
		}else{
			this.referenceNo = this.referenceNo.trim();
		}
		
		if(this.nicNo==null || this.nicNo.trim().equals("")){
			this.nicNoSqlInjection=1;
		}else{
			this.nicNo = this.nicNo.trim();
		}

		if(this.newNicNo==null || this.newNicNo.trim().equals("")){
			this.newNicNoSqlInjection=1;
		}else{
			this.newNicNo = this.newNicNo.trim();
		}
		if(this.pptNo==null || this.pptNo.trim().equals("")){
			this.pptNoSqlInjection=1;
		}else{
			this.pptNo = this.pptNo.trim();
		}
		
		if(this.name==null || this.name.trim().equals("")){
			this.nameSqlInjection=1;
		}else{
			this.name = StringUtils.upperCase(this.name.trim());
		}
		
		if(!(this.fromDate==null)){
			Calendar cal=Calendar.getInstance();
			cal.setTime(this.fromDate);
			cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
			cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
			cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
			this.fromDate=cal.getTime();
		}else{
			fromDateSqlInjection=1;
		}
		
		if(!(this.toDate==null)){
			Calendar cal=Calendar.getInstance();
			cal.setTime(this.toDate);
			cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
			cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
			cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
			this.toDate=cal.getTime();
		}else{
			toDateSqlInjection=1;
		}
		
	}
	
	
	

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("referenceNo", referenceNo);
		map.put("referenceNoSqlInjection", referenceNoSqlInjection);
		map.put("nicNo", nicNo);
		map.put("newNicNo", newNicNo);
		map.put("nicNoSqlInjection", nicNoSqlInjection);
		map.put("newNicNoSqlInjection", newNicNoSqlInjection);
		map.put("pptNo", pptNo);
		map.put("pptNoSqlInjection", pptNoSqlInjection);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("fromDateSqlInjection", fromDateSqlInjection);
		map.put("toDateSqlInjection", toDateSqlInjection);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public int getReferenceNoSqlInjection() {
		return referenceNoSqlInjection;
	}
	public void setReferenceNoSqlInjection(int referenceNoSqlInjection) {
		this.referenceNoSqlInjection = referenceNoSqlInjection;
	}

	public String getNicNo() {
		return nicNo;
	}
	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

	public int getNicNoSqlInjection() {
		return nicNoSqlInjection;
	}
	public void setNicNoSqlInjection(int nicNoSqlInjection) {
		this.nicNoSqlInjection = nicNoSqlInjection;
	}

	public String getPptNo() {
		return pptNo;
	}
	public void setPptNo(String pptNo) {
		this.pptNo = pptNo;
	}

	public int getPptNoSqlInjection() {
		return pptNoSqlInjection;
	}
	public void setPptNoSqlInjection(int pptNoSqlInjection) {
		this.pptNoSqlInjection = pptNoSqlInjection;
	}

	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getFromDateSqlInjection() {
		return fromDateSqlInjection;
	}
	public void setFromDateSqlInjection(int fromDateSqlInjection) {
		this.fromDateSqlInjection = fromDateSqlInjection;
	}

	public int getToDateSqlInjection() {
		return toDateSqlInjection;
	}
	public void setToDateSqlInjection(int toDateSqlInjection) {
		this.toDateSqlInjection = toDateSqlInjection;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNameSqlInjection() {
		return nameSqlInjection;
	}
	public void setNameSqlInjection(int nameSqlInjection) {
		this.nameSqlInjection = nameSqlInjection;
	}

	public String getNewNicNo() {
		return newNicNo;
	}

	public void setNewNicNo(String newNicNo) {
		this.newNicNo = newNicNo;
	}

	public int getNewNicNoSqlInjection() {
		return newNicNoSqlInjection;
	}

	public void setNewNicNoSqlInjection(int newNicNoSqlInjection) {
		this.newNicNoSqlInjection = newNicNoSqlInjection;
	}
}
