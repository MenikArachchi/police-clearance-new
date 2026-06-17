package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DailyTransactionReportSearchCriteriaVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date fromDate;
	private int fromDateSqlInjection;
	
	private Date toDate;
	private int toDateSqlInjection;
	
	private String status;
	private int statusSqlInjection;
	
	private String submission;
	private int submissionSqlInjection;
	
	public DailyTransactionReportSearchCriteriaVO () {
		super();
	}
	
	public DailyTransactionReportSearchCriteriaVO (Date fromDate, Date toDate,
			String status, String submission) {
		super();
		
		this.fromDate = fromDate;
		this.toDate = toDate;
		
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
		
		if(status==null || status.trim().equals("")){
			this.statusSqlInjection=1;
		}else{
			this.status = status.trim();
		}
		
		if(submission==null || submission.trim().equals("")){
			this.submissionSqlInjection=1;
		}else{
			this.submission = submission.trim();
		}
		
		
		
	}
	
public void updateDailyTransactionReportSearchCriteriaVO () {
		
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
		
		if(status==null || status.trim().equals("")){
			this.statusSqlInjection=1;
		}else{
			this.status = status.trim();
		}
		
		if(submission==null || submission.trim().equals("")){
			this.submissionSqlInjection=1;
		}else{
			this.submission = submission.trim();
		}
		

	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public int getFromDateSqlInjection() {
		return fromDateSqlInjection;
	}


	public void setFromDateSqlInjection(int fromDateSqlInjection) {
		this.fromDateSqlInjection = fromDateSqlInjection;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public int getToDateSqlInjection() {
		return toDateSqlInjection;
	}


	public void setToDateSqlInjection(int toDateSqlInjection) {
		this.toDateSqlInjection = toDateSqlInjection;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getStatusSqlInjection() {
		return statusSqlInjection;
	}


	public void setStatusSqlInjection(int statusSqlInjection) {
		this.statusSqlInjection = statusSqlInjection;
	}


	public String getSubmission() {
		return submission;
	}


	public void setSubmission(String submission) {
		this.submission = submission;
	}


	public int getSubmissionSqlInjection() {
		return submissionSqlInjection;
	}


	public void setSubmissionSqlInjection(int submissionSqlInjection) {
		this.submissionSqlInjection = submissionSqlInjection;
	}

}
