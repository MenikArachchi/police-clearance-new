package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.UserDepartment;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;

import org.apache.log4j.Logger;

public class LockedRecordDAO {

  private static LockedRecordDAO instance = null;
  private static final Logger LOGGER = Logger.getLogger(LockedRecordDAO.class);

  public static LockedRecordDAO getInstance() {
    synchronized (LockedRecordDAO.class) {
      if (instance == null) {
        instance = new LockedRecordDAO();
      }
      return instance;
    }
  }

  private LockedRecordDAO() {

  }

  public List<AddressVO> getLockedAddressesListByUser(int userId, Connection conn) {
    List<AddressVO> addressList = new ArrayList<AddressVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info(criteriaVO);
      // LOGGER.info(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
      pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_LOCKED_ADDRESSES_BY_USER);
      pstm.setInt(1, userId);
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          addressList.add(AddressDAO.getInstance().getConstructedAddressVOFromResultSet(rst));
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return addressList;
  }

  public List<AddressVO> getAllLockedAddressesList(Connection conn) {
    List<AddressVO> addressList = new ArrayList<AddressVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info(criteriaVO);
      // LOGGER.info(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
      pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_LOCKED_ADDRESSES);
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          addressList.add(AddressDAO.getInstance().getConstructedAddressVOFromResultSet(rst));
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return addressList;
  }


  public List<ApplicationVO> getLockedApplicationsListByUser(int userId,
      PoliceEnumConstant.UserDepartment userDepartmentEnum, Connection conn) {
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info(criteriaVO);
      // LOGGER.info(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
      switch (userDepartmentEnum) {
        case PHQ:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_PHQ_LOCKED_APPLICATIONS_BY_USER);
          break;
        case CID:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_CID_LOCKED_APPLICATIONS_BY_USER);
          break;
        case TID:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_TID_LOCKED_APPLICATIONS_BY_USER);
          break;
        case SIS:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_SIS_LOCKED_APPLICATIONS_BY_USER);
          break;
        case NIC:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_NIC_LOCKED_APPLICATIONS_BY_USER);
          break;
        case IMI:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_IMI_LOCKED_APPLICATIONS_BY_USER);
          break;
        default:
          LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
          break;
      }

      if (!(pstm == null)) {
        pstm.setInt(1, userId);
        rst = pstm.executeQuery();
        if (rst != null) {
          while (rst.next()) {
            applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
          }
        }
		rst.close();
        pstm.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return applicationVOs;
  }

  public List<ApplicationVO> getAllLockedApplicationsList(PoliceEnumConstant.UserDepartment userDepartmentEnum,
      Connection conn) {
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info(criteriaVO);
      // LOGGER.info(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
      switch (userDepartmentEnum) {
        case PHQ:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_PHQ_LOCKED_APPLICATIONS);
          break;
        case CID:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_CID_LOCKED_APPLICATIONS);
          break;
        case TID:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_TID_LOCKED_APPLICATIONS);
          break;
        case SIS:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_SIS_LOCKED_APPLICATIONS);
          break;
        case NIC:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_NIC_LOCKED_APPLICATIONS);
          break;
        case IMI:
          pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_IMI_LOCKED_APPLICATIONS);
          break;
        default:
          LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
          break;
      }

      if (!(pstm == null)) {
        rst = pstm.executeQuery();
        if (rst != null) {
          while (rst.next()) {
            applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
          }
        }
		rst.close();
        pstm.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return applicationVOs;
  }

  public String unlockAddresses(Long applicationId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.SUCCESS;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.UNLOCK_ADDRESSES_BY_APPLICATION_ID);
      pstm.setNull(1, java.sql.Types.INTEGER);
      pstm.setLong(2, applicationId);
      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
      if (result > 0) {
        returnValue = PoliceConstant.SUCCESS;
      }
	  pstm.close();
    } catch (Exception e) {
      returnValue = PoliceConstant.ERROR;
      e.printStackTrace();
    } finally {
      DatabaseManager.close(pstm);
    }
    return returnValue;
  }

  public String unlockApplications(Long applicationId, UserDepartment userDepartmentEnum, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.SUCCESS;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);

      switch (userDepartmentEnum) {
        case PHQ:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.PHQ_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        case CID:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.CID_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        case TID:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.TID_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        case SIS:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.SIS_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        case NIC:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.NIC_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        case IMI:
          pstm = con.prepareStatement(DBConstant.QUERY_NEW.IMI_UNLOCK_APPLICATION_BY_APPLICATION_ID);
          break;
        default:
          LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
          break;
      }

      pstm.setNull(1, java.sql.Types.INTEGER);
      pstm.setLong(2, applicationId);


      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
      if (result > 0) {
        returnValue = PoliceConstant.SUCCESS;
      }
	  pstm.close();
    } catch (Exception e) {
      returnValue = PoliceConstant.ERROR;
      e.printStackTrace();
    } finally {
      DatabaseManager.close(pstm);
    }
    return returnValue;
  }

}
