package lk.icta.police.framework.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ApplicationDetailsReportSearchCriteriaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date fromDate;
    private int fromDateSqlInjection;

    private Date toDate;
    private int toDateSqlInjection;

    private String status;
    private int statusSqlInjection;

    private String clearanceStatus;
    private int clearanceStatusSqlInjection;

    private String referenceNo;
    private int referenceNoSqlInjection;

    private String nicNo;
    private int nicNoSqlInjection;

    private String newNicNo;
    private int newNicNoSqlInjection;

    private String passportNo;
    private int passportNoSqlInjection;

    private String name;
    private int nameSqlInjection;

    private long countryId;
    private int countrySqlInjection;


    public ApplicationDetailsReportSearchCriteriaVO() {
        super();
    }

    public ApplicationDetailsReportSearchCriteriaVO(Date fromDate, Date toDate,
                                                    String status, String referenceNo) {
        super();

        this.fromDate = fromDate;
        this.toDate = toDate;

        if (!(this.fromDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.fromDate);
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            this.fromDate = cal.getTime();
        } else {
            fromDateSqlInjection = 1;
        }

        if (!(this.toDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.toDate);
            cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
            this.toDate = cal.getTime();
        } else {
            toDateSqlInjection = 1;
        }

        if (status == null || status.trim().equals("")) {
            this.statusSqlInjection = 1;
        } else {
            this.status = status.trim();
        }

        if (referenceNo == null || referenceNo.trim().equals("")) {
            this.referenceNoSqlInjection = 1;
        } else {
            this.referenceNo = referenceNo.trim();
        }

        if (nicNo == null || nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = nicNo.trim();
        }
        if (newNicNo == null || newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            this.newNicNo = newNicNo.trim();
        }

        if (passportNo == null || passportNo.trim().equals("")) {
            this.passportNoSqlInjection = 1;
        } else {
            this.passportNo = passportNo.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

        if (countryId <= 0) {
            this.countrySqlInjection = 1;
        }

    }

    public void updateApplicationDetailsReportSearchCriteriaVO() {

        if (!(this.fromDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.fromDate);
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            this.fromDate = cal.getTime();
        } else {
            fromDateSqlInjection = 1;
        }

        if (!(this.toDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.toDate);
            cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
            this.toDate = cal.getTime();
        } else {
            toDateSqlInjection = 1;
        }

        if (status == null || status.trim().equals("")) {
            this.statusSqlInjection = 1;
        } else {
            this.status = status.trim();
        }

        if (clearanceStatus == null || clearanceStatus.trim().equals("")) {
            this.clearanceStatusSqlInjection = 1;
        } else {
            this.clearanceStatus = clearanceStatus.trim();
        }

        if (referenceNo == null || referenceNo.trim().equals("")) {
            this.referenceNoSqlInjection = 1;
        } else {
            this.referenceNo = referenceNo.trim();
        }

        if (nicNo == null || nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = nicNo.trim();
        }


        if (newNicNo == null || newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            this.newNicNo = newNicNo.trim();
        }
        if (passportNo == null || passportNo.trim().equals("")) {
            this.passportNoSqlInjection = 1;
        } else {
            this.passportNo = passportNo.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

        if (countryId <= 0) {
            this.countrySqlInjection = 1;
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

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public int getPassportNoSqlInjection() {
        return passportNoSqlInjection;
    }

    public void setPassportNoSqlInjection(int passportNoSqlInjection) {
        this.passportNoSqlInjection = passportNoSqlInjection;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public int getCountrySqlInjection() {
        return countrySqlInjection;
    }

    public void setCountrySqlInjection(int countrySqlInjection) {
        this.countrySqlInjection = countrySqlInjection;
    }

    public String getClearanceStatus() {
        return clearanceStatus;
    }

    public void setClearanceStatus(String clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
    }

    public int getClearanceStatusSqlInjection() {
        return clearanceStatusSqlInjection;
    }

    public void setClearanceStatusSqlInjection(int clearanceStatusSqlInjection) {
        this.clearanceStatusSqlInjection = clearanceStatusSqlInjection;
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
