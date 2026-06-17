package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.commons.lang3.StringUtils;

public class ExternalDeptPrintSearchCriteriaVO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private Date fromSentDate;
    private Date toSentDate;

    private int fromSentDateSqlInjection;
    private int toSentDateSqlInjection;

    private Date fromReceivedDate;
    private Date toReceivedDate;

    private int fromReceivedDateSqlInjection;
    private int toReceivedDateSqlInjection;

    private int startFrom;
    private int limit;

    private String cidStatus;
    private int cidStatusSqlInjection;

    private String tidStatus;
    private int tidStatusSqlInjection;

    private String sisStatus;
    private int sisStatusSqlInjection;

    private String nicStatus;
    private int nicStatusSqlInjection;

    private String imiStatus;
    private int imiStatusSqlInjection;

    private String policeStatus;
    private int policeStatusStatusSqlInjection;

    private String clearanceStatus;

    private String department;

    private long locationId;


    public ExternalDeptPrintSearchCriteriaVO() {
        super();
    }

    public ExternalDeptPrintSearchCriteriaVO(String referenceNo, String nicNo, String pptNo, Date fromSentDate,
                                             Date toSentDate, Date fromReceivedDate, Date toReceivedDate) {
        super();
        this.referenceNo = referenceNo;
        this.nicNo = nicNo;
        this.pptNo = pptNo;
        this.fromSentDate = fromSentDate;
        this.toSentDate = toSentDate;
        this.fromReceivedDate = fromReceivedDate;
        this.toReceivedDate = toReceivedDate;
    }

    public ExternalDeptPrintSearchCriteriaVO(String referenceNo, String nicNo, String newNicNo, String pptNo, Date fromSentDate,
                                             Date toSentDate, Date fromReceivedDate, Date toReceivedDate) {
        super();
        this.referenceNo = referenceNo;
        this.nicNo = nicNo;
        this.newNicNo = newNicNo;
        this.pptNo = pptNo;
        this.fromSentDate = fromSentDate;
        this.toSentDate = toSentDate;
        this.fromReceivedDate = fromReceivedDate;
        this.toReceivedDate = toReceivedDate;
    }

    public void updateSearchCriteriaVO(int userRole, long location) {

        if (this.referenceNo == null || this.referenceNo.trim().equals("")) {
            this.referenceNoSqlInjection = 1;
        } else {
            this.referenceNo = this.referenceNo.trim();
        }

        if (this.nicNo == null || this.nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = this.nicNo.trim();
        }

        if (this.newNicNo == null || this.newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            this.newNicNo = this.newNicNo.trim();
        }

        if (this.pptNo == null || this.pptNo.trim().equals("")) {
            this.pptNoSqlInjection = 1;
        } else {
            this.pptNo = this.pptNo.trim();
        }

        if (this.name == null || this.name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(this.name.trim());
        }

        if (!(this.clearanceStatus == null || this.clearanceStatus.trim().equals(""))) {
            this.clearanceStatus = this.clearanceStatus.trim();
        }

        if (!(this.fromSentDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.fromSentDate);
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            this.fromSentDate = cal.getTime();
        } else {
            fromSentDateSqlInjection = 1;
        }

        if (!(this.toSentDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.toSentDate);
            cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
            this.toSentDate = cal.getTime();
        } else {
            toSentDateSqlInjection = 1;
        }

        if (!(this.fromReceivedDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.fromReceivedDate);
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            this.fromReceivedDate = cal.getTime();
        } else {
            fromReceivedDateSqlInjection = 1;
        }

        if (!(this.toReceivedDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.toReceivedDate);
            cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
            this.toReceivedDate = cal.getTime();
        } else {
            toReceivedDateSqlInjection = 1;
        }


        PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
        this.department = userDepartmentEnum.toString();
        switch (userDepartmentEnum) {
            case PHQ:
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.policeStatusStatusSqlInjection = 0;
                } else {
                    this.policeStatusStatusSqlInjection = 1;
                }
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case POL:
                this.locationId = location;
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.policeStatusStatusSqlInjection = 0;
                } else {
                    this.policeStatusStatusSqlInjection = 1;
                }
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case CID:
                this.cidStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.cidStatusSqlInjection = 0;
                } else {
                    this.cidStatusSqlInjection = 1;
                }
                this.policeStatusStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case TID:
                this.tidStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.tidStatusSqlInjection = 0;
                } else {
                    this.tidStatusSqlInjection = 1;
                }
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case SIS:
                this.sisStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.sisStatusSqlInjection = 0;
                } else {
                    this.sisStatusSqlInjection = 1;
                }
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case NIC:
                this.nicStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.nicStatusSqlInjection = 0;
                } else {
                    this.nicStatusSqlInjection = 1;
                }
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case IMI:
                this.imiStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                if (StringUtils.equals(this.clearanceStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                    this.imiStatusSqlInjection = 0;
                } else {
                    this.imiStatusSqlInjection = 1;
                }
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                break;
            default:
                break;
        }


    }


    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("referenceNo", referenceNo);
        map.put("referenceNoSqlInjection", referenceNoSqlInjection);
        map.put("nicNo", nicNo);
        map.put("nicNoSqlInjection", nicNoSqlInjection);
        map.put("newNicNo", newNicNo);
        map.put("newNicNoSqlInjection", newNicNoSqlInjection);
        map.put("pptNo", pptNo);
        map.put("pptNoSqlInjection", pptNoSqlInjection);
        map.put("fromSentDate", fromSentDate);
        map.put("toSentDate", toSentDate);
        map.put("fromSentDateSqlInjection", fromSentDateSqlInjection);
        map.put("toSentDateSqlInjection", toSentDateSqlInjection);
        map.put("fromReceivedDate", fromReceivedDate);
        map.put("toReceivedDate", toReceivedDate);
        map.put("fromReceivedDateSqlInjection", fromReceivedDateSqlInjection);
        map.put("toReceivedDateSqlInjection", toReceivedDateSqlInjection);
        map.put("startFrom", startFrom);
        map.put("limit", limit);

        map.put("cidStatus", cidStatus);
        map.put("cidStatusSqlInjection", cidStatusSqlInjection);
        map.put("tidStatus", tidStatus);
        map.put("tidStatusSqlInjection", tidStatusSqlInjection);
        map.put("sisStatus", sisStatus);
        map.put("sisStatusSqlInjection", sisStatusSqlInjection);
        map.put("nicStatus", nicStatus);
        map.put("nicStatusSqlInjection", nicStatusSqlInjection);
        map.put("imiStatus", imiStatus);
        map.put("imiStatusSqlInjection", imiStatusSqlInjection);
        map.put("policeStatus", policeStatus);
        map.put("policeStatusStatusSqlInjection", policeStatusStatusSqlInjection);

        map.put("clearanceStatus", clearanceStatus);

        map.put("locationId", locationId);
        map.put("department", department);
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

    public int getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(int startFrom) {
        this.startFrom = startFrom;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCidStatus() {
        return cidStatus;
    }

    public void setCidStatus(String cidStatus) {
        this.cidStatus = cidStatus;
    }

    public int getCidStatusSqlInjection() {
        return cidStatusSqlInjection;
    }

    public void setCidStatusSqlInjection(int cidStatusSqlInjection) {
        this.cidStatusSqlInjection = cidStatusSqlInjection;
    }

    public String getTidStatus() {
        return tidStatus;
    }

    public void setTidStatus(String tidStatus) {
        this.tidStatus = tidStatus;
    }

    public int getTidStatusSqlInjection() {
        return tidStatusSqlInjection;
    }

    public void setTidStatusSqlInjection(int tidStatusSqlInjection) {
        this.tidStatusSqlInjection = tidStatusSqlInjection;
    }

    public String getSisStatus() {
        return sisStatus;
    }

    public void setSisStatus(String sisStatus) {
        this.sisStatus = sisStatus;
    }

    public int getSisStatusSqlInjection() {
        return sisStatusSqlInjection;
    }

    public void setSisStatusSqlInjection(int sisStatusSqlInjection) {
        this.sisStatusSqlInjection = sisStatusSqlInjection;
    }

    public String getNicStatus() {
        return nicStatus;
    }

    public void setNicStatus(String nicStatus) {
        this.nicStatus = nicStatus;
    }

    public int getNicStatusSqlInjection() {
        return nicStatusSqlInjection;
    }

    public void setNicStatusSqlInjection(int nicStatusSqlInjection) {
        this.nicStatusSqlInjection = nicStatusSqlInjection;
    }

    public String getImiStatus() {
        return imiStatus;
    }

    public void setImiStatus(String imiStatus) {
        this.imiStatus = imiStatus;
    }

    public int getImiStatusSqlInjection() {
        return imiStatusSqlInjection;
    }

    public void setImiStatusSqlInjection(int imiStatusSqlInjection) {
        this.imiStatusSqlInjection = imiStatusSqlInjection;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getPoliceStatus() {
        return policeStatus;
    }

    public void setPoliceStatus(String policeStatus) {
        this.policeStatus = policeStatus;
    }

    public int getPoliceStatusStatusSqlInjection() {
        return policeStatusStatusSqlInjection;
    }

    public void setPoliceStatusStatusSqlInjection(int policeStatusStatusSqlInjection) {
        this.policeStatusStatusSqlInjection = policeStatusStatusSqlInjection;
    }

    public String getClearanceStatus() {
        return clearanceStatus;
    }

    public void setClearanceStatus(String clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
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

    public Date getFromSentDate() {
        return fromSentDate;
    }

    public void setFromSentDate(Date fromSentDate) {
        this.fromSentDate = fromSentDate;
    }

    public Date getToSentDate() {
        return toSentDate;
    }

    public void setToSentDate(Date toSentDate) {
        this.toSentDate = toSentDate;
    }

    public int getFromSentDateSqlInjection() {
        return fromSentDateSqlInjection;
    }

    public void setFromSentDateSqlInjection(int fromSentDateSqlInjection) {
        this.fromSentDateSqlInjection = fromSentDateSqlInjection;
    }

    public int getToSentDateSqlInjection() {
        return toSentDateSqlInjection;
    }

    public void setToSentDateSqlInjection(int toSentDateSqlInjection) {
        this.toSentDateSqlInjection = toSentDateSqlInjection;
    }

    public Date getFromReceivedDate() {
        return fromReceivedDate;
    }

    public void setFromReceivedDate(Date fromReceivedDate) {
        this.fromReceivedDate = fromReceivedDate;
    }

    public Date getToReceivedDate() {
        return toReceivedDate;
    }

    public void setToReceivedDate(Date toReceivedDate) {
        this.toReceivedDate = toReceivedDate;
    }

    public int getFromReceivedDateSqlInjection() {
        return fromReceivedDateSqlInjection;
    }

    public void setFromReceivedDateSqlInjection(int fromReceivedDateSqlInjection) {
        this.fromReceivedDateSqlInjection = fromReceivedDateSqlInjection;
    }

    public int getToReceivedDateSqlInjection() {
        return toReceivedDateSqlInjection;
    }

    public void setToReceivedDateSqlInjection(int toReceivedDateSqlInjection) {
        this.toReceivedDateSqlInjection = toReceivedDateSqlInjection;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
