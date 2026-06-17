package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.UserDepartment;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.LockedRecordDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class LockedRecordBussiness {

  private static final Logger LOGGER = Logger.getLogger(LockedRecordBussiness.class);
  private static LockedRecordBussiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static LockedRecordBussiness getInstance() {
    synchronized (LockedRecordBussiness.class) {
      if (instance == null) {
        instance = new LockedRecordBussiness();
      }
      return instance;
    }
  }

  private LockedRecordBussiness() {

  }

  public List<ApplicationVO> getLockedRecords(PoliceEnumConstant.UserDepartment userDepartmentEnum, int userId) {
    Connection con = null;
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    List<Long> applicationIds = new ArrayList<Long>();
    List<ApplicationVO> returnedApplicationVOs = new ArrayList<ApplicationVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      if (userDepartmentEnum == PoliceEnumConstant.UserDepartment.POL) {
        if (userId == 0) {
          List<AddressVO> adrsList = LockedRecordDAO.getInstance().getAllLockedAddressesList(con);
          if (!(adrsList == null || adrsList.isEmpty())) {
            for (AddressVO addressVO : adrsList) {
              ApplicationVO vo = ApplicationDAO.getInstance().getApplicationById(addressVO.getApplicationId(), con);
              vo.setPhqRecordLockStatus(addressVO.getPoliceRecordLockStatus());
              returnedApplicationVOs.add(vo);
            }
          }
        } else {
          List<AddressVO> adrsList = LockedRecordDAO.getInstance().getLockedAddressesListByUser(userId, con);
          if (!(adrsList == null || adrsList.isEmpty())) {
            for (AddressVO addressVO : adrsList) {
              ApplicationVO vo = ApplicationDAO.getInstance().getApplicationById(addressVO.getApplicationId(), con);
              vo.setPhqRecordLockStatus(addressVO.getPoliceRecordLockStatus());
              returnedApplicationVOs.add(vo);
            }
          }
        }
      } else if (userDepartmentEnum == PoliceEnumConstant.UserDepartment.PHQ) {
        if (userId == 0) {
          returnedApplicationVOs = LockedRecordDAO.getInstance().getAllLockedApplicationsList(userDepartmentEnum, con);
          List<AddressVO> adrsList = LockedRecordDAO.getInstance().getAllLockedAddressesList(con);
          if (!(adrsList == null || adrsList.isEmpty())) {
            for (AddressVO addressVO : adrsList) {
              ApplicationVO vo = ApplicationDAO.getInstance().getApplicationById(addressVO.getApplicationId(), con);
              vo.setPhqRecordLockStatus(addressVO.getPoliceRecordLockStatus());
              returnedApplicationVOs.add(vo);
            }
          }
        } else {
          returnedApplicationVOs =
              LockedRecordDAO.getInstance().getLockedApplicationsListByUser(userId, userDepartmentEnum, con);
          List<AddressVO> adrsList = LockedRecordDAO.getInstance().getLockedAddressesListByUser(userId, con);
          if (!(adrsList == null || adrsList.isEmpty())) {
            for (AddressVO addressVO : adrsList) {
              ApplicationVO vo = ApplicationDAO.getInstance().getApplicationById(addressVO.getApplicationId(), con);
              vo.setPhqRecordLockStatus(addressVO.getPoliceRecordLockStatus());
              returnedApplicationVOs.add(vo);
            }
          }
        }
      } else {
        if (userId == 0) {
          returnedApplicationVOs = LockedRecordDAO.getInstance().getAllLockedApplicationsList(userDepartmentEnum, con);
        } else {
          returnedApplicationVOs =
              LockedRecordDAO.getInstance().getLockedApplicationsListByUser(userId, userDepartmentEnum, con);
        }
      }

      if (!(returnedApplicationVOs == null || returnedApplicationVOs.isEmpty())) {
        for (ApplicationVO applicationVO : returnedApplicationVOs) {
          if (!(applicationIds.contains(applicationVO.getApplicationId()))) {
            String userFullName = getUserFullName(applicationVO, userDepartmentEnum);
            if (StringUtils.isNotEmpty(userFullName)) {
              applicationVO.setUserFullName(userFullName);
              applicationIds.add(applicationVO.getApplicationId());
              applicationVOs.add(applicationVO);
            }

          }

        }
      }

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return applicationVOs;
  }


  private String getUserFullName(ApplicationVO applicationVO, UserDepartment userDepartmentEnum)
      throws lk.icta.commonuser.framework.exception.BusinessException {
    String userName = "";
    int userId = 0;
    switch (userDepartmentEnum) {
      case POL:
        userId = applicationVO.getPhqRecordLockStatus();
        break;
      case PHQ:
        userId = applicationVO.getPhqRecordLockStatus();
        break;
      case CID:
        userId = applicationVO.getCidRecordLockStatus();
        break;
      case TID:
        userId = applicationVO.getTidRecordLockStatus();
        break;
      case SIS:
        userId = applicationVO.getSisRecordLockStatus();
        break;
      case NIC:
        userId = applicationVO.getNicRecordLockStatus();
        break;
      case IMI:
        userId = applicationVO.getImiRecordLockStatus();
        break;
      default:
        LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
        break;
    }
    if (!(userId == 0)) {
      UserVO selectedUser = new UserVO();
      selectedUser.setId(userId);
      UserVO userVO = CommonUserBusiness.getInstance().getUserDetails(selectedUser);
      if (!(userVO == null)) {
        if (userVO.getDept().getId() == userDepartmentEnum.getCode()) {
          userName = userVO.getFullName();
        }
      }

    }
    return userName;
  }

  public String unlockRecords(PoliceEnumConstant.UserDepartment userDepartmentEnum, List<Long> applicationIds) {
    Connection con = null;
    String status = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      if (!(applicationIds == null || applicationIds.isEmpty())) {
        if (userDepartmentEnum == PoliceEnumConstant.UserDepartment.POL) {
          for (Long applicationId : applicationIds) {
            status = LockedRecordDAO.getInstance().unlockAddresses(applicationId, con);
            if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
              break;
            }
          }
        } else if (userDepartmentEnum == PoliceEnumConstant.UserDepartment.PHQ) {
          for (Long applicationId : applicationIds) {
            status = LockedRecordDAO.getInstance().unlockAddresses(applicationId, con);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
              status = LockedRecordDAO.getInstance().unlockApplications(applicationId, userDepartmentEnum, con);
            } else {
              break;
            }
            if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
              break;
            }
          }
        } else {
          for (Long applicationId : applicationIds) {
            status = LockedRecordDAO.getInstance().unlockApplications(applicationId, userDepartmentEnum, con);
            if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
              break;
            }
          }
        }
      }

      if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
        con.commit();
      }


    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return status;
  }

}
