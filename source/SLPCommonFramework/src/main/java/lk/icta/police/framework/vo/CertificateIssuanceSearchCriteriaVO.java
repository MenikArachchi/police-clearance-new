package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.commons.lang3.StringUtils;

public class CertificateIssuanceSearchCriteriaVO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String referenceNo;
  private int referenceNoSqlInjection;

  private String clearenceStatus;
  private int clearenceStatusSqlInjection;

  private Date fromDate;
  private Date toDate;

  private int fromDateSqlInjection;
  private int toDateSqlInjection;

  private String name;
  private int nameSqlInjection;

  private long maxId;
  private int limit;
  private int currentPage;

  private String applicationQueue;
  private int applicationQueueSqlInjection;

  private int lockedRefresh;

  private int userType;

  private boolean defaultView;

  private int defaultViewFromUi;

  private boolean lastPage;

  private boolean printPendingOnly;

  public CertificateIssuanceSearchCriteriaVO() {
    super();
  }



  public CertificateIssuanceSearchCriteriaVO(String referenceNo, String clearenceStatus, Date fromDate, Date toDate,
      long maxTime, int limit, String applicationQueue, int userType) {
    super();
    this.referenceNo = referenceNo;
    this.clearenceStatus = clearenceStatus;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.maxId = maxId;
    this.limit = limit;
    this.applicationQueue = applicationQueue;
    this.userType = userType;
  }



  public CertificateIssuanceSearchCriteriaVO(String referenceNo, String clearenceStatus, Date fromDate, Date toDate) {
    super();
    this.referenceNo = referenceNo;
    this.clearenceStatus = clearenceStatus;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }


  public void updateSearchCriteriaVO(int userType) {

    this.defaultView = true;

    this.applicationQueueSqlInjection = 0;

    if (this.referenceNo == null || this.referenceNo.trim().equals("")) {
      this.referenceNoSqlInjection = 1;
    } else {
      this.defaultView = false;
      this.referenceNo = this.referenceNo.trim();
    }

    if (this.name == null || this.name.trim().equals("")) {
      this.nameSqlInjection = 1;
    } else {
      this.defaultView = false;
      this.name = StringUtils.upperCase(this.name.trim());
    }


    if ((this.clearenceStatus == null || this.clearenceStatus.trim().equals(""))) {
      this.applicationQueueSqlInjection = 1;
      this.clearenceStatusSqlInjection = 1;
    } else if (this.clearenceStatus.equals("ALL")) {
      this.applicationQueueSqlInjection = 1;
      this.clearenceStatusSqlInjection = 1;
      this.defaultView = false;
    } else {
      this.defaultView = false;
    }



    if (!(this.fromDate == null)) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(this.fromDate);
      cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
      cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
      cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
      cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
      this.fromDate = cal.getTime();
      this.defaultView = false;
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
      this.defaultView = false;
    } else {
      toDateSqlInjection = 1;
    }


    PoliceEnumConstant.UserType userTypeEnum = PoliceEnumConstant.UserType.fromCode(userType);
    if (!(userTypeEnum == null)) {
      switch (userTypeEnum) {
        case CN:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.CN.toString();
          break;
        case CA:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.CA.toString();
          break;
        case OI:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.OI.toString();
          break;
        case AS:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.AS.toString();
          break;
        case DH:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.DH.toString();
          break;
        case DI:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.DI.toString();
          break;
        case PO:
          this.applicationQueue = PoliceEnumConstant.ApplicationQueue.PO.toString();
          break;
        default:
          System.out.println("NEW USER TYPE :" + userTypeEnum);
          break;
      }
    }


    if (defaultViewFromUi == 1) {
      defaultView = true;
    }

    if (!(defaultView)) {
      this.applicationQueueSqlInjection = 1;
    }

    if (!(userTypeEnum == null)) {
      if ((StringUtils.equals(userTypeEnum.toString(), PoliceEnumConstant.UserType.DI.toString()))) {
        this.applicationQueueSqlInjection = 0;
      }
    } else {
      this.applicationQueueSqlInjection = 0;
    }


    System.out.println("defaultView **** :" + defaultView);


  }



  public Map<String, Object> toBasicMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("referenceNo", referenceNo);
    map.put("referenceNoSqlInjection", referenceNoSqlInjection);
    map.put("clearenceStatus", clearenceStatus);
    map.put("clearenceStatusSqlInjection", clearenceStatusSqlInjection);
    map.put("fromDate", fromDate);
    map.put("toDate", toDate);
    map.put("fromDateSqlInjection", fromDateSqlInjection);
    map.put("toDateSqlInjection", toDateSqlInjection);
    map.put("maxId", maxId);
    map.put("limit", limit);
    map.put("applicationQueue", applicationQueue);
    map.put("applicationQueueSqlInjection", applicationQueueSqlInjection);
    map.put("userType", userType);
    map.put("defaultView", defaultView);
    map.put("currentPage", currentPage);
    map.put("lockedRefresh", lockedRefresh);
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

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getClearenceStatus() {
    return clearenceStatus;
  }

  public void setClearenceStatus(String clearenceStatus) {
    this.clearenceStatus = clearenceStatus;
  }

  public int getClearenceStatusSqlInjection() {
    return clearenceStatusSqlInjection;
  }

  public void setClearenceStatusSqlInjection(int clearenceStatusSqlInjection) {
    this.clearenceStatusSqlInjection = clearenceStatusSqlInjection;
  }

  public String getApplicationQueue() {
    return applicationQueue;
  }

  public void setApplicationQueue(String applicationQueue) {
    this.applicationQueue = applicationQueue;
  }

  public int getApplicationQueueSqlInjection() {
    return applicationQueueSqlInjection;
  }

  public void setApplicationQueueSqlInjection(int applicationQueueSqlInjection) {
    this.applicationQueueSqlInjection = applicationQueueSqlInjection;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public boolean isDefaultView() {
    return defaultView;
  }

  public void setDefaultView(boolean defaultView) {
    this.defaultView = defaultView;
  }

  public int getDefaultViewFromUi() {
    return defaultViewFromUi;
  }

  public void setDefaultViewFromUi(int defaultViewFromUi) {
    this.defaultViewFromUi = defaultViewFromUi;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public boolean isLastPage() {
    return lastPage;
  }

  public void setLastPage(boolean lastPage) {
    this.lastPage = lastPage;
  }

  // public long getMaxId() {
  // return maxId;
  // }
  //
  // public void setMaxId(long maxId) {
  // this.maxId = maxId;
  // }



  public int getLockedRefresh() {
    return lockedRefresh;
  }


  public long getMaxId() {
    return maxId;
  }

  public void setMaxId(long maxId) {
    this.maxId = maxId;
  }

  public void setLockedRefresh(int lockedRefresh) {
    this.lockedRefresh = lockedRefresh;
  }

  public boolean isPrintPendingOnly() {
    return printPendingOnly;
  }

  public void setPrintPendingOnly(boolean printPendingOnly) {
    this.printPendingOnly = printPendingOnly;
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



}
