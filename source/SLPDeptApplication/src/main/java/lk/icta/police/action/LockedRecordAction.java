package lk.icta.police.action;

import java.util.List;
import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.LockedRecordBussiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.UserTypeDisplayVO;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LockedRecordAction extends ActionSupport implements SessionAware {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(LockedRecordAction.class);
  private Map<String, Object> session;

  private List<ApplicationVO> lockedApplications;

  private int userDepartment;

  private List<Long> lockedIdList;

  private int userType;
  private UserTypeDisplayVO userTypeDisplayVO;

  public String viewLockedRecords() {
    loadUserRoleData();
    userDepartment = getUserRoleFromSession();
    int userRole = getUserRoleFromSession();
    int userId = getUserIdFromSession();
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.OI.getCode()) {
      userId = 0;
    }
    PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
    lockedApplications = LockedRecordBussiness.getInstance().getLockedRecords(userDepartmentEnum, userId);
    return SUCCESS;
  }

  public String searchLockedRecords() {
    loadUserRoleData();
    if (userDepartment == 0) {
      userDepartment = getUserRoleFromSession();
    }
    int userId = getUserIdFromSession();
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.OI.getCode()) {
      userId = 0;
    }
    PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userDepartment);
    lockedApplications = LockedRecordBussiness.getInstance().getLockedRecords(userDepartmentEnum, userId);
    return SUCCESS;
  }

  public String unlockRecords() {
    loadUserRoleData();
    System.out.println("userDepartment :" + userDepartment);
    if (userDepartment == 0) {
      userDepartment = getUserRoleFromSession();
    }
    PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userDepartment);
    System.out.println("userDepartmentEnum :" + userDepartmentEnum);
    System.out.println("lockedIdList :" + lockedIdList);
    if (!(lockedIdList == null || lockedIdList.isEmpty())) {
      String status = LockedRecordBussiness.getInstance().unlockRecords(userDepartmentEnum, lockedIdList);
      System.out.println("status :" + status);
    }
    int userId = getUserIdFromSession();
    if (getUserTypeFromSession() == PoliceEnumConstant.UserType.OI.getCode()) {
      userId = 0;
    }
    lockedApplications = LockedRecordBussiness.getInstance().getLockedRecords(userDepartmentEnum, userId);
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


  private int getUserRoleFromSession() {
    UserVO userVO = getUserFromSession();
    if (!(userVO == null || userVO.getDept() == null)) {
      return userVO.getDept().getId();
    }
    return 0;
  }

  private UserVO getUserFromSession() {
    if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
      return (UserVO) session.get(PoliceConstant.SESSION_USER);
    }
    return null;
  }

  private void loadUserRoleData() {
    this.userType = getUserTypeFromSession();
    this.userTypeDisplayVO = new UserTypeDisplayVO();

  }

  @Override
  public void setSession(Map<String, Object> arg0) {
    this.session = arg0;
  }

  public List<ApplicationVO> getLockedApplications() {
    return lockedApplications;
  }

  public void setLockedApplications(List<ApplicationVO> lockedApplications) {
    this.lockedApplications = lockedApplications;
  }

  public int getUserDepartment() {
    return userDepartment;
  }

  public void setUserDepartment(int userDepartment) {
    this.userDepartment = userDepartment;
  }

  public List<Long> getLockedIdList() {
    return lockedIdList;
  }

  public void setLockedIdList(List<Long> lockedIdList) {
    this.lockedIdList = lockedIdList;
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



}
