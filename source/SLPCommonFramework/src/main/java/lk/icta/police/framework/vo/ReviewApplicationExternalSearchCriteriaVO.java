package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lk.icta.police.framework.constant.PoliceEnumConstant;

public class ReviewApplicationExternalSearchCriteriaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String referenceNo;
    private int referenceNoSqlInjection;

    private String newNicNo;
    private int newNicNoSqlInjection;

    private String nicNo;
    private int nicNoSqlInjection;

    private String pptNo;
    private int pptNoSqlInjection;

    private String name;
    private int nameSqlInjection;

    private Date fromDate;
    private Date toDate;

    private int fromDateSqlInjection;
    private int toDateSqlInjection;

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

    private long locationId;

    private long maxId;
    private int limit;
    private int currentPage;
    private boolean lastPage;


    public ReviewApplicationExternalSearchCriteriaVO() {
        super();
    }

    public ReviewApplicationExternalSearchCriteriaVO(String referenceNo,
                                                     String nicNo, String pptNo, Date fromDate, Date toDate) {
        super();
        this.referenceNo = referenceNo;
        this.nicNo = nicNo;
        this.pptNo = pptNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ReviewApplicationExternalSearchCriteriaVO(String referenceNo,
                                                     String nicNo, String newNicNo, String pptNo, Date fromDate, Date toDate) {
        super();
        this.referenceNo = referenceNo;
        this.nicNo = nicNo;
        this.newNicNo = newNicNo;
        this.pptNo = pptNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public void updateSearchCriteriaVO(int userRole, long location) {

        boolean isDefaultView = true;

        if (this.referenceNo == null || this.referenceNo.trim().equals("")) {
            this.referenceNoSqlInjection = 1;
        } else {
            isDefaultView = false;
            this.referenceNo = this.referenceNo.trim();
        }

        if (this.nicNo == null || this.nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            isDefaultView = false;
            this.nicNo = this.nicNo.trim();
        }

        if (this.newNicNo == null || this.newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            isDefaultView = false;
            this.newNicNo = this.newNicNo.trim();
        }

        if (this.pptNo == null || this.pptNo.trim().equals("")) {
            this.pptNoSqlInjection = 1;
        } else {
            isDefaultView = false;
            this.pptNo = this.pptNo.trim();
        }

        if (this.name == null || this.name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            isDefaultView = false;
            this.name = StringUtils.upperCase(this.name.trim());
        }

        if (!(this.fromDate == null)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.fromDate);
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            this.fromDate = cal.getTime();
            isDefaultView = false;
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
            isDefaultView = false;
        } else {
            toDateSqlInjection = 1;
        }


        PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
        switch (userDepartmentEnum) {
            case PHQ:
                this.policeStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.policeStatusStatusSqlInjection = 0;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case POL:
                this.locationId = location;
                this.policeStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.policeStatusStatusSqlInjection = 0;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case CID:
                this.cidStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.cidStatusSqlInjection = 0;
                this.policeStatusStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case TID:
                this.tidStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.tidStatusSqlInjection = 0;
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case SIS:
                this.sisStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.sisStatusSqlInjection = 0;
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case NIC:
                this.nicStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.nicStatusSqlInjection = 0;
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.imiStatusSqlInjection = 1;
                break;
            case IMI:
                this.imiStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                this.imiStatusSqlInjection = 0;
                this.policeStatusStatusSqlInjection = 1;
                this.cidStatusSqlInjection = 1;
                this.tidStatusSqlInjection = 1;
                this.sisStatusSqlInjection = 1;
                this.nicStatusSqlInjection = 1;
                break;
            default:
                break;
        }

        //if this is not the default view, then display anything
//		if(!(isDefaultView)){
//			this.nicStatusSqlInjection=1;
//			this.policeStatusStatusSqlInjection=1;
//			this.cidStatusSqlInjection=1;
//			this.tidStatusSqlInjection=1;
//			this.sisStatusSqlInjection=1;
//			this.nicStatusSqlInjection=1;
//			this.imiStatusSqlInjection=1;
//		}

    }


    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
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

        map.put("maxId", maxId);
        map.put("limit", limit);
        map.put("currentPage", currentPage);
        map.put("lastPage", lastPage);

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

        map.put("locationId", locationId);
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


    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
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
