package lk.icta.police.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.DigClearenceBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationClearenceStatus;
import lk.icta.police.framework.vo.CertificateClearenceGridVO;
import lk.icta.police.framework.vo.CertificateClearenceVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;
import lk.icta.police.framework.vo.ClearenceGridButton;
import lk.icta.police.framework.vo.UserTypeDisplayVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class DigClearenceAction extends ActionSupport implements SessionAware {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(DigClearenceAction.class);
  private Map<String, Object> session;

  private Date fromDate;
  private Date toDate;

  private CertificateIssuanceSearchCriteriaVO searchCriteriaVO;

  private LinkedList<CertificateClearenceGridVO> applicationList;

  private List<ClearenceGridButton> clearenceGridButtons;

  private CertificateClearenceVO clearenceVO;

  private int userType;
  private UserTypeDisplayVO userTypeDisplayVO;

  private String currentUserFullName;

  private Map<String, ApplicationClearenceStatus> clearenceStatusList;


  public String viewDigClearence() {
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.DI.getCode()) {
      initializeSearchCriteria();
      clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
      int userId = getUserIdFromSession();

      LOGGER.info("userId ACTION||||||| " + userId);

      applicationList = DigClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);

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

  public String searchDigClearence() {
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.DI.getCode()) {
      initializeSearchCriteria();
      clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
      int userId = getUserIdFromSession();
      applicationList = DigClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
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



  public String updateCertificateIssuanceDig() {

    LOGGER.info("clearenceVO :" + clearenceVO);
    if (!(clearenceVO == null)) {
      boolean isValid = true;

      if (!(getUserTypeFromSession() == PoliceEnumConstant.UserType.DI.getCode())) {
        addActionError("You don't have permission to update Certificate Issuance as an ASP!");
        isValid = false;
      }

      clearenceVO.updateClearenceVO(getUserIdFromSession(), getUserNameFromSession(), getUserTypeFromSession());

      if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
        if (StringUtils.isEmpty(clearenceVO.getLetterContent())) {
          isValid = false;
          addActionError("Please enter the letter conent!");
        }
      } else if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
        if (StringUtils.isEmpty(clearenceVO.getComment())) {
          isValid = false;
          addActionError("Please enter the comment!");
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
    applicationList = DigClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
    if (!(applicationList == null || applicationList.isEmpty())) {
      loadGridButtons();
      loadUserRoleData();
    }
    searchCriteriaVO.setDefaultViewFromUi(0);
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


  public List<ClearenceGridButton> getClearenceGridButtons() {
    return clearenceGridButtons;
  }

  public void setClearenceGridButtons(List<ClearenceGridButton> clearenceGridButtons) {
    this.clearenceGridButtons = clearenceGridButtons;
  }

  public CertificateClearenceVO getClearenceVO() {
    return clearenceVO;
  }

  public void setClearenceVO(CertificateClearenceVO clearenceVO) {
    this.clearenceVO = clearenceVO;
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

  public String getCurrentUserFullName() {
    return currentUserFullName;
  }

  public void setCurrentUserFullName(String currentUserFullName) {
    this.currentUserFullName = currentUserFullName;
  }

  public Map<String, ApplicationClearenceStatus> getClearenceStatusList() {
    return clearenceStatusList;
  }

  public void setClearenceStatusList(Map<String, ApplicationClearenceStatus> clearenceStatusList) {
    this.clearenceStatusList = clearenceStatusList;
  }

  public Map<String, Object> getSession() {
    return session;
  }



}
