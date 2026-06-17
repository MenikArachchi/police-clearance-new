package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ApplicationVerificationSearchCriteriaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fromDate;
    private int fromDateSqlInjection;

    private Date toDate;
    private int toDateSqlInjection;

    private String status;
    private int statusSqlInjection;

    private String referenceNo;
    private int referenceNoSqlInjection;

    private String nic;
    private String newNic;
    private int nicSqlInjection;
    private int newNicSqlInjection;

    private String passport;
    private int passportSqlInjection;

    private String name;
    private int nameSqlInjection;

    public ApplicationVerificationSearchCriteriaVO() {
        super();
    }

    public ApplicationVerificationSearchCriteriaVO(Date fromDate, Date toDate,
                                                   String status, String referenceNo, String name) {
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

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

    }

    public void updateApplicationVerificationSearchCriteriaVO() {

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

        if (nic == null || nic.trim().equals("")) {
            this.nicSqlInjection = 1;
        } else {
            this.nic = nic.trim();
        }

        if (newNic == null || newNic.trim().equals("")) {
            this.newNicSqlInjection = 1;
        } else {
            this.newNic= newNic.trim();
        }

        if (passport == null || passport.trim().equals("")) {
            this.passportSqlInjection = 1;
        } else {
            this.passport = passport.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getNicSqlInjection() {
        return nicSqlInjection;
    }

    public void setNicSqlInjection(int nicSqlInjection) {
        this.nicSqlInjection = nicSqlInjection;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getPassportSqlInjection() {
        return passportSqlInjection;
    }

    public void setPassportSqlInjection(int passportSqlInjection) {
        this.passportSqlInjection = passportSqlInjection;
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

    public String getNewNic() {
        return newNic;
    }

    public void setNewNic(String newNic) {
        this.newNic = newNic;
    }

    public int getNewNicSqlInjection() {
        return newNicSqlInjection;
    }

    public void setNewNicSqlInjection(int newNicSqlInjection) {
        this.newNicSqlInjection = newNicSqlInjection;
    }
}
