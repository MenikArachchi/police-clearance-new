package lk.icta.police.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.NoAdverseCheckBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationClearenceStatus;
import lk.icta.police.framework.vo.CertificateClearenceGridVO;
import lk.icta.police.framework.vo.CertificateClearenceVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;
import lk.icta.police.framework.vo.ClearenceGridButton;
import lk.icta.police.framework.vo.CommentTypeDisplayVO;
import lk.icta.police.framework.vo.NicRevisionClearenceVO;
import lk.icta.police.framework.vo.UserTypeDisplayVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class NoAdverseCheckAction extends ActionSupport implements SessionAware {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(NoAdverseCheckAction.class);
  private Map<String, Object> session;

  private Date fromDate;
  private Date toDate;

  private long applicationId;

  private CertificateIssuanceSearchCriteriaVO searchCriteriaVO;

  private LinkedList<CertificateClearenceGridVO> applicationList;

  private String recordLockstatus;

  private CommentTypeDisplayVO typeDisplayVO;

  private List<ClearenceGridButton> clearenceGridButtons;

  private CertificateClearenceVO clearenceVO;

  private int userType;
  private UserTypeDisplayVO userTypeDisplayVO;

  private String currentUserFullName;

  private Map<String, ApplicationClearenceStatus> clearenceStatusList;

  private String emailSentStatus;

  private String emailText;

  private File nicFile;
  private String nicFileExtension;
  private String fileType;
  private String nicFileName;

  private NicRevisionClearenceVO nicRevisionClearenceVO;

  private List<Integer> applicationIdList;
  private String fileName;


  private int updateClearenceStatus;

  public String viewNoAdverseCheck() {
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.CN.getCode()) {
      initializeSearchCriteria();
      clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
      int userId = getUserIdFromSession();

      LOGGER.info("userId ACTION||||||| " + userId);
      LOGGER.info("userId searchCriteriaVO.getPrintPendingOnly()||||||| " + searchCriteriaVO.isPrintPendingOnly());

      applicationList = NoAdverseCheckBusiness.getInstance().searchApplications(searchCriteriaVO, userId);

      if (!(applicationList == null || applicationList.isEmpty())) {
        loadGridButtons();
        loadUserRoleData();
      }
      searchCriteriaVO.setDefaultViewFromUi(0);
      if (searchCriteriaVO.getLockedRefresh() == 1) {
        searchCriteriaVO.setReferenceNo(StringUtils.EMPTY);
        searchCriteriaVO.setLockedRefresh(0);
      }
      return SUCCESS;
    } else {
      return PoliceConstant.UNAUTHORIZED;
    }
  }

  public String searchNoAdverseCheck() {
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.CN.getCode()) {
      initializeSearchCriteria();
      clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
      int userId = getUserIdFromSession();
      LOGGER.info("userId searchCriteriaVO.getPrintPendingOnly()||||||| " + searchCriteriaVO.isPrintPendingOnly());
      applicationList = NoAdverseCheckBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
      if (!(applicationList == null || applicationList.isEmpty())) {
        loadGridButtons();
        loadUserRoleData();
      }
      searchCriteriaVO.setDefaultViewFromUi(0);
      if (searchCriteriaVO.getLockedRefresh() == 1) {
        searchCriteriaVO.setReferenceNo(StringUtils.EMPTY);
        searchCriteriaVO.setLockedRefresh(0);
      }
      return SUCCESS;
    } else {
      return PoliceConstant.UNAUTHORIZED;
    }
  }

  public String resendClearanceNoAdverse() {
    if (!(clearenceVO.getApplicationId() <= 0)) {
      if (!(clearenceVO.getDepartmentId() <= 0)) {
        if (clearenceVO.getDepartmentId() == PoliceEnumConstant.UserDepartment.POL.getCode()) {
          if (clearenceVO.getPoliceAreaId() <= 0) {
            addActionError("Please select a police area!");
            return SUCCESS;
          }
        }

        if (StringUtils.isEmpty(clearenceVO.getReasonToResend())) {
          addActionError("Please enter the reason to resend!");
        }

        String result =
            CertificateIssuanceBusiness.getInstance().resendApplicationForExternalDepartment(clearenceVO,
                getUserIdFromSession(), getUserNameFromSession());
        if (StringUtils.equals(result, PoliceConstant.SUCCESS)) {
          addActionMessage("Application was resent successfully!");
        } else if (StringUtils.equals(result, "ALREADY_RESENT")) {
          addActionError("The application has been already resnt for this department!");
        } else if (StringUtils.equals(result, "INVALID_DEPARTMENT")) {
          addActionError("Please select the department to resend!");
        } else if (StringUtils.equals(result, PoliceConstant.ERROR)) {
          addActionError("Could not resend the application! Internal Error");
        }

        initializeSearchCriteria();
        clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
        int userId = getUserIdFromSession();
        searchCriteriaVO.setReferenceNo(clearenceVO.getReferenceNo());
        searchCriteriaVO.setReferenceNoSqlInjection(0);
        searchCriteriaVO.setDefaultView(false);
        searchCriteriaVO.setDefaultViewFromUi(0);
        applicationList = NoAdverseCheckBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
        if (!(applicationList == null || applicationList.isEmpty())) {
          loadGridButtons();
          loadUserRoleData();
        }

      } else {
        addActionError("Please select a department!");
      }
    } else {
      addActionError("Please select the application to be resent!");
    }
    return SUCCESS;
  }

  public String updateNicrevisionClearenceNa() {
    LOGGER.info("updateClearenceStatus :" + updateClearenceStatus);
    LOGGER.info("nicRevisionClearenceVO :" + nicRevisionClearenceVO);

    if (!(nicRevisionClearenceVO == null)) {
      if (updateClearenceStatus == 1) {
        nicRevisionClearenceVO.setUpdateClearenceStatus(true);
      } else {
        nicRevisionClearenceVO.setUpdateClearenceStatus(false);
      }

      nicRevisionClearenceVO.updateNicRevisionClearenceVO(getUserIdFromSession(), getUserNameFromSession(),
          getUserTypeFromSession());
      boolean isValid = true;

      if (!(getUserTypeFromSession() == PoliceEnumConstant.UserType.CN.getCode())) {
        addActionError("You don't have permission to update NIC Revision!");
        isValid = false;
      }

      if (isValid) {
        String status =
            CertificateIssuanceBusiness.getInstance().updateApplicationNICRevisionClearenceStatus(
                nicRevisionClearenceVO);
        if (StringUtils.equals(status, PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE)) {
          addActionError("You do not own the lock of this record currently!");
        } else if (StringUtils.equals(status, PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER)) {
          addActionMessage("Failed to update the record, it has been updated by another user. Please try again!");
        } else if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          addActionMessage("Record was updated successfully!");
        } else {
          addActionError("Internal Error Occurred, could not update the record!");
        }
      }
    }

    initializeSearchCriteria();
    clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
    int userId = getUserIdFromSession();
    searchCriteriaVO.setPrintPendingOnly(true);
    applicationList = NoAdverseCheckBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
    if (!(applicationList == null || applicationList.isEmpty())) {
      loadGridButtons();
      loadUserRoleData();
    }
    searchCriteriaVO.setDefaultViewFromUi(0);
    return SUCCESS;
  }


  private void initializeSearchCriteria() {
    if (searchCriteriaVO == null) {
      searchCriteriaVO = new CertificateIssuanceSearchCriteriaVO(null, null, null, null);
      searchCriteriaVO.setMaxId(0);
      searchCriteriaVO.setLimit(20);
    }
    if (searchCriteriaVO.getCurrentPage() == 0) {
      searchCriteriaVO.setCurrentPage(1);
    }
    int userType = getUserTypeFromSession();
    LOGGER.info("userType ACTION||||||| " + userType);
    searchCriteriaVO.setUserType(userType);

    searchCriteriaVO.setFromDate(fromDate);
    searchCriteriaVO.setToDate(toDate);
    searchCriteriaVO.updateSearchCriteriaVO(getUserTypeFromSession());
    currentUserFullName = getUserNameFromSession();
    if (searchCriteriaVO.getMaxId() == 0) {
      initializeGridButtonMap();
    }
  }



  private void loadUserRoleData() {
    this.userType = getUserTypeFromSession();
    this.userTypeDisplayVO = new UserTypeDisplayVO();

  }

  private void initializeGridButtonMap() {
    if (!(session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS) == null)) {
      this.clearenceGridButtons = (List<ClearenceGridButton>) session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS);
    }
    if (this.clearenceGridButtons == null) {
      this.clearenceGridButtons = new ArrayList<ClearenceGridButton>();
    }
    this.clearenceGridButtons.clear();
    ClearenceGridButton button =
        new ClearenceGridButton("First", searchCriteriaVO.getMaxId(), 1, 1, 1, 1, searchCriteriaVO.getLimit());
    this.clearenceGridButtons.add(button);
    Collections.sort(this.clearenceGridButtons);
    session.put(PoliceConstant.CLEARANCE_GRID_BUTTONS, this.clearenceGridButtons);
  }

  private void loadGridButtons() {
    if (!(session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS) == null)) {
      this.clearenceGridButtons = (List<ClearenceGridButton>) session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS);
    }
    if (this.clearenceGridButtons == null) {
      this.clearenceGridButtons = new ArrayList<ClearenceGridButton>();
    }
    int currentPageNo = 1;
    if (!(applicationList.size() <= 0)) {
      ClearenceGridButton button = null;
      ClearenceGridButton currentButton =
          CertificateIssuanceBusiness.getInstance().getCurrentPageButton(this.clearenceGridButtons,
              searchCriteriaVO.getCurrentPage());
      currentPageNo = currentButton.getPageNo() + 1;
      if ((searchCriteriaVO.isLastPage())) {
        button =
            new ClearenceGridButton("Last", searchCriteriaVO.getMaxId(), currentPageNo, 1, 0, 0,
                searchCriteriaVO.getLimit());
      } else {
        button =
            new ClearenceGridButton("Next", searchCriteriaVO.getMaxId(), currentPageNo, 1, 0, 0,
                searchCriteriaVO.getLimit());
      }
      boolean isButtonAvailable =
          CertificateIssuanceBusiness.getInstance().checkIfButtonAvailable(this.clearenceGridButtons,
              searchCriteriaVO.getMaxId());
      if (!(isButtonAvailable)) {
        if (!(searchCriteriaVO.isLastPage())) {
          this.clearenceGridButtons.add(button);
        } else {
          currentButton.setCurrentButtonStatus(1);
          currentButton.setLabel("Last");
        }
      }
      Collections.sort(this.clearenceGridButtons);
      session.put(PoliceConstant.CLEARANCE_GRID_BUTTONS, this.clearenceGridButtons);
      LOGGER.info("clearenceGridButtons: " + clearenceGridButtons);
    }
  }



  public String updateCertificateIssuanceNa() {

    LOGGER.info("updateClearenceStatus :" + updateClearenceStatus);
    LOGGER.info("clearenceVO :" + clearenceVO);
    if (!(clearenceVO == null)) {
      boolean isValid = true;

      if (!(getUserTypeFromSession() == PoliceEnumConstant.UserType.CN.getCode())) {
        addActionError("You don't have permission to update Certificate Issuance as a No Adverse Checking Officer!");
        isValid = false;
      }

      clearenceVO.updateClearenceVO(getUserIdFromSession(), getUserNameFromSession(), getUserTypeFromSession());

      if (StringUtils.equals(clearenceVO.getClearenceStatus(),
          PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
        if (StringUtils.isEmpty(clearenceVO.getRecomendedOfficerName())
            || StringUtils.isEmpty(clearenceVO.getComment())) {
          isValid = false;
          addActionError("Please enter the comment and the recommended officer name!");
        }
      } else if (StringUtils.equals(clearenceVO.getClearenceStatus(),
          PoliceEnumConstant.ApplicationClearenceStatus.BL.toString())
          || StringUtils.equals(clearenceVO.getClearenceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString())) {
        if (StringUtils.isEmpty(clearenceVO.getComment())) {
          isValid = false;
          addActionError("Please enter the comment!");
        }
      }

      if (StringUtils.isNotEmpty(clearenceVO.getApprovalStatus())) {
        if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
          if (StringUtils.isEmpty(clearenceVO.getComment())) {
            isValid = false;
            addActionError("Please enter the comment!");
          }
        }
      }

      if (isValid) {
        String status = CertificateIssuanceBusiness.getInstance().updateApplicationReviewApprovalStatus(clearenceVO);
        if (StringUtils.equals(status, PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE)) {
          addActionError("You do not own the lock of this record currently!");
        } else if (StringUtils.equals(status, PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER)) {
          addActionMessage("Failed to update the record, it has been updated by another user. Please try again!");
        } else if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          addActionMessage("Record was updated successfully!");
        } else {
          addActionError("Internal Error Occurred, could not update the record!");
        }
      }
    }

    initializeSearchCriteria();
    clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
    int userId = getUserIdFromSession();
    applicationList = NoAdverseCheckBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
    if (!(applicationList == null || applicationList.isEmpty())) {
      loadGridButtons();
      loadUserRoleData();
    }
    searchCriteriaVO.setDefaultViewFromUi(0);
    searchCriteriaVO.setPrintPendingOnly(true);
    return SUCCESS;
  }


  private int getUserTypeFromSession() {
    UserVO userVO = getUserFromSession();
    if (!(userVO == null || userVO.getUserType() == null)) {
      return userVO.getUserType().getId();
    }
    return 0;
  }


  private int getUserIdFromSession() {
    UserVO userVO = getUserFromSession();
    if (!(userVO == null)) {
      return userVO.getId();
    }
    return 0;
  }

  private String getUserNameFromSession() {
    UserVO userVO = getUserFromSession();
    if (!(userVO == null)) {
      return userVO.getFullName();
    }
    return null;
  }


  private UserVO getUserFromSession() {
    if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
      return (UserVO) session.get(PoliceConstant.SESSION_USER);
    }
    return null;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }


  public Date getFromDate() {
    return fromDate;
  }

  @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }


  public Date getToDate() {
    return toDate;
  }

  @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }

  public String getRecordLockstatus() {
    return recordLockstatus;
  }

  public void setRecordLockstatus(String recordLockstatus) {
    this.recordLockstatus = recordLockstatus;
  }


  public CertificateIssuanceSearchCriteriaVO getSearchCriteriaVO() {
    return searchCriteriaVO;
  }

  public void setSearchCriteriaVO(CertificateIssuanceSearchCriteriaVO searchCriteriaVO) {
    this.searchCriteriaVO = searchCriteriaVO;
  }

  public LinkedList<CertificateClearenceGridVO> getApplicationList() {
    return applicationList;
  }

  public void setApplicationList(LinkedList<CertificateClearenceGridVO> applicationList) {
    this.applicationList = applicationList;
  }



  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public UserTypeDisplayVO getUserTypeDisplayVO() {
    return userTypeDisplayVO;
  }

  public void setUserTypeDisplayVO(UserTypeDisplayVO userTypeDisplayVO) {
    this.userTypeDisplayVO = userTypeDisplayVO;
  }

  public CertificateClearenceVO getClearenceVO() {
    return clearenceVO;
  }

  public void setClearenceVO(CertificateClearenceVO clearenceVO) {
    this.clearenceVO = clearenceVO;
  }

  public CommentTypeDisplayVO getTypeDisplayVO() {
    return typeDisplayVO;
  }

  public void setTypeDisplayVO(CommentTypeDisplayVO typeDisplayVO) {
    this.typeDisplayVO = typeDisplayVO;
  }

  public Map<String, ApplicationClearenceStatus> getClearenceStatusList() {
    return clearenceStatusList;
  }

  public void setClearenceStatusList(Map<String, ApplicationClearenceStatus> clearenceStatusList) {
    this.clearenceStatusList = clearenceStatusList;
  }

  public String getCurrentUserFullName() {
    return currentUserFullName;
  }

  public void setCurrentUserFullName(String currentUserFullName) {
    this.currentUserFullName = currentUserFullName;
  }

  public String getEmailSentStatus() {
    return emailSentStatus;
  }

  public void setEmailSentStatus(String emailSentStatus) {
    this.emailSentStatus = emailSentStatus;
  }

  public String getEmailText() {
    return emailText;
  }

  public void setEmailText(String emailText) {
    this.emailText = emailText;
  }

  public File getNicFile() {
    return nicFile;
  }

  public void setNicFile(File nicFile) {
    this.nicFile = nicFile;
  }


  public String getNicFileExtension() {
    return nicFileExtension;
  }

  public void setNicFileExtension(String nicFileExtension) {
    this.nicFileExtension = nicFileExtension;
  }



  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public NicRevisionClearenceVO getNicRevisionClearenceVO() {
    return nicRevisionClearenceVO;
  }

  public void setNicRevisionClearenceVO(NicRevisionClearenceVO nicRevisionClearenceVO) {
    this.nicRevisionClearenceVO = nicRevisionClearenceVO;
  }

  public String getNicFileName() {
    return nicFileName;
  }

  public void setNicFileName(String nicFileName) {
    this.nicFileName = nicFileName;
  }

  public List<Integer> getApplicationIdList() {
    return applicationIdList;
  }

  public void setApplicationIdList(List<Integer> applicationIdList) {
    this.applicationIdList = applicationIdList;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public int getUpdateClearenceStatus() {
    return updateClearenceStatus;
  }

  public void setUpdateClearenceStatus(int updateClearenceStatus) {
    this.updateClearenceStatus = updateClearenceStatus;
  }

  public List<ClearenceGridButton> getClearenceGridButtons() {
    return clearenceGridButtons;
  }

  public void setClearenceGridButtons(List<ClearenceGridButton> clearenceGridButtons) {
    this.clearenceGridButtons = clearenceGridButtons;
  }



}
