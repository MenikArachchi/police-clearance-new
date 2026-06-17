package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.AddressChangeAuditVO;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationClearanceDate;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    private static AddressDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(AddressDAO.class);

    public static AddressDAO getInstance() {
        synchronized (AddressDAO.class) {
            if (instance == null) {
                instance = new AddressDAO();
            }
            return instance;
        }
    }

    private AddressDAO() {

    }

    public List<AddressVO> getAddressList(Connection conn, long applicationId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_LIST);
            pstm.setString(1, PoliceEnumConstant.AddressType.RM.toString());
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }

    public List<AddressVO> getAllAddressList(Connection conn, long applicationId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_ADDRESS_LIST);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }

    public AddressVO getConstructedAddressVOFromResultSet(ResultSet rst) throws SQLException {
        AddressVO addressVO = new AddressVO();
        addressVO.setAddressId(rst.getLong(DBConstant.ADDRESS_COL.ADDRESS_ID));
        addressVO.setAddress(rst.getString(DBConstant.ADDRESS_COL.ADDRESS));
        addressVO.setPoliceAreaId(rst.getLong(DBConstant.ADDRESS_COL.POLICE_AREA_ID));
        addressVO.setPoliceArea(rst.getString(DBConstant.ADDRESS_COL.POLICE_AREA));
        addressVO.setFromDate(rst.getTimestamp(DBConstant.ADDRESS_COL.FROM_DATE));
        addressVO.setToDate(rst.getTimestamp(DBConstant.ADDRESS_COL.TO_DATE));
        addressVO.setFromMessage(rst.getString(DBConstant.ADDRESS_COL.FROM_MESSAGE));
        addressVO.setFromSentBy(rst.getString(DBConstant.ADDRESS_COL.FROM_SENT_BY));
        addressVO.setFromSentDate(rst.getTimestamp(DBConstant.ADDRESS_COL.FROM_SENT_DATE));
        addressVO.setFromReceiveBy(rst.getString(DBConstant.ADDRESS_COL.FROM_RECEIVE_BY));
        addressVO.setFromCreatedDateTime(rst.getTimestamp(DBConstant.ADDRESS_COL.FROM_CREATED_DATE_TIME));
        addressVO.setResponseMessage(rst.getString(DBConstant.ADDRESS_COL.RESPONSE_MESSAGE));
        addressVO.setResponseSentBy(rst.getString(DBConstant.ADDRESS_COL.RESPONSE_SENT_BY));
        addressVO.setResponseSentDate(rst.getTimestamp(DBConstant.ADDRESS_COL.RESPONSE_SENT_DATE));
        addressVO.setResponseSentTo(rst.getString(DBConstant.ADDRESS_COL.RESPONSE_SENT_TO));
        addressVO.setResponseCreatedDateTime(rst.getTimestamp(DBConstant.ADDRESS_COL.RESPONSE_CREATED_DATE_TIME));
        addressVO.setApplicationId(rst.getLong(DBConstant.ADDRESS_COL.APPLICATION_ID));
        addressVO.setPoliceStatus(rst.getString(DBConstant.ADDRESS_COL.POLICE_STATUS));
        addressVO.setType(rst.getString(DBConstant.ADDRESS_COL.TYPE));
        addressVO.setPoliceRecordLockStatus(rst.getInt(DBConstant.ADDRESS_COL.POLICE_RECORD_LOCK_STATUS));
        addressVO.setVersionId(rst.getInt(DBConstant.ADDRESS_COL.VERSION_ID));
        return addressVO;
    }

    public long addAddress(Connection conn, AddressVO addressVO) throws Exception {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.ADD_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, addressVO.getAddress());
            pstm.setLong(2, addressVO.getPoliceAreaId());
            pstm.setString(3, addressVO.getPoliceArea());
            pstm.setTimestamp(4, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromDate()));
            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getToDate()));
            pstm.setString(6, addressVO.getFromMessage());
            pstm.setString(7, addressVO.getFromSentBy());
            pstm.setTimestamp(8, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromSentDate()));
            pstm.setString(9, addressVO.getFromReceiveBy());
            pstm.setTimestamp(10, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromCreatedDateTime()));
            pstm.setString(11, addressVO.getResponseMessage());
            pstm.setString(12, addressVO.getResponseSentBy());
            pstm.setTimestamp(13, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseSentDate()));
            pstm.setString(14, addressVO.getResponseSentTo());
            pstm.setTimestamp(15, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseCreatedDateTime()));
            pstm.setLong(16, addressVO.getApplicationId());
            pstm.setString(17, addressVO.getPoliceStatus());
            pstm.setString(18, addressVO.getType());
            pstm.setInt(19, addressVO.getPoliceRecordLockStatus());

            int result = pstm.executeUpdate();
            long addressId = 0l;
            if (result > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    addressId = rs.getInt(1);
                }
            }

            addressVO.setAddressId(addressId);
            DatabaseManager.close(result);
			pstm.close();
            return addressId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return 0;
    }

    public boolean updateAddress(Connection conn, long addressId, AddressVO addressVO) throws Exception {
        PreparedStatement pstm = null;
  //      ResultSet rst = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS);
            pstm.setString(1, addressVO.getAddress());
            pstm.setLong(2, addressVO.getPoliceAreaId());
            pstm.setString(3, addressVO.getPoliceArea());
            pstm.setTimestamp(4, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromDate()));
            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getToDate()));
            pstm.setString(6, addressVO.getFromMessage());
            pstm.setString(7, addressVO.getFromSentBy());
            pstm.setTimestamp(8, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromSentDate()));
            pstm.setString(9, addressVO.getFromReceiveBy());
            pstm.setTimestamp(10, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromCreatedDateTime()));
            pstm.setString(11, addressVO.getResponseMessage());
            pstm.setString(12, addressVO.getResponseSentBy());
            pstm.setTimestamp(13, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseSentDate()));
            pstm.setString(14, addressVO.getResponseSentTo());
            pstm.setTimestamp(15, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseCreatedDateTime()));
            pstm.setLong(16, addressVO.getApplicationId());
            pstm.setString(17, addressVO.getPoliceStatus());
            pstm.setString(18, addressVO.getType());
            pstm.setInt(19, addressVO.getPoliceRecordLockStatus());
            pstm.setLong(20, addressId);
			
            pstm.executeUpdate();
//			rst.close();
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
 //           DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return true;
    }

    public boolean updateAddressForPoliceMessage(Connection conn, long addressId, AddressVO addressVO) throws Exception {
        PreparedStatement pstm = null;
 //       ResultSet rst = null;
        try {
            this.moveAddressToAudit(addressId, conn);

            pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_FOR_POLICE_MESSAGE);

            // System.out.println("DBConstant.QUERY.UPDATE_ADDRESS_FOR_POLICE_MESSAGE-->"+DBConstant.QUERY.UPDATE_ADDRESS_FOR_POLICE_MESSAGE.toString());

            pstm.setString(1, addressVO.getFromMessage());
            pstm.setString(2, addressVO.getFromSentBy());
            pstm.setTimestamp(3, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromSentDate()));
            pstm.setString(4, addressVO.getFromReceiveBy());
            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromCreatedDateTime()));
            pstm.setString(6, addressVO.getResponseMessage());
            pstm.setString(7, addressVO.getResponseSentBy());
            pstm.setTimestamp(8, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseSentDate()));
            pstm.setString(9, addressVO.getResponseSentTo());
            pstm.setTimestamp(10, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getResponseCreatedDateTime()));
            pstm.setString(11, addressVO.getPoliceStatus());
            pstm.setLong(12, addressId);

            pstm.executeUpdate();

	//		rst.close();
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return true;
    }

    public boolean deleteAddress(Connection conn, long addressId) throws Exception {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.DELETE_ADDRESS_BY_ID);
            pstm.setLong(1, addressId);

            pstm.executeUpdate();

			pstm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.close(pstm);
        }
    }

    public boolean deleteAddressByApplication(Connection conn, long applicationId) throws Exception {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.DELETE_ADDRESS_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);

            pstm.executeUpdate();
			pstm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.close(pstm);
        }
    }

    public boolean addAddressList(Connection conn, List<AddressVO> addressVOs) {
        try {
            for (AddressVO addressVO : addressVOs) {
                long addressId = addAddress(conn, addressVO);
                addressVO.setAddressId(addressId);
                if (addressId <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<AddressVO> getPendingAddressListByTypeAndApplicationId(long applicationId, String addressType,
                                                                       Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_TYPE_POLICE_STATUS_AND_APPLICATION_ID);
            // LOGGER.info(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_TYPE_POLICE_STATUS_AND_APPLICATION_ID);
            // LOGGER.info("applicationId :" + applicationId);
            // LOGGER.info("addressType :" + addressType);
            pstm.setString(1, addressType);
            pstm.setString(2, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.TU.toString());
            pstm.setLong(4, applicationId);
            rst = pstm.executeQuery();
            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }

    public List<AddressVO> getAddressListByTypeAndApplicationId(long applicationId, String addressType, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_TYPE_AND_APPLICATION_ID);
            // LOGGER.info(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_TYPE_AND_APPLICATION_ID);
            // LOGGER.info("applicationId :" + applicationId);
            // LOGGER.info("addressType :" + addressType);
            pstm.setString(1, addressType);
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();
            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }


    public AddressVO getAddressListByApplicationIdAndPoliceareaId(long applicationId, long policeAreaId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        AddressVO addressVo = new AddressVO();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_APPLICATION_ID_AND_POLICEAREA_ID);
            // LOGGER.info(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_APPLICATION_ID_AND_POLICEAREA_ID);
            // LOGGER.info("applicationId :" + applicationId);
            // LOGGER.info("policeAreaId :" + policeAreaId);

            pstm.setLong(1, policeAreaId);
            pstm.setString(2, PoliceEnumConstant.AddressType.RM.toString());
            pstm.setLong(3, applicationId);

            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVo = getConstructedAddressVOFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVo;
    }

    public List<AddressVO> getPendingAddressListByTypeLocationAndApplicationId(long applicationId, String addressType,
                                                                               long locationId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_PENDING_ADDRESS_LIST_BY_TYPE_LOCATION_AND_APPLICATION_ID);
            pstm.setString(1, addressType);
            pstm.setLong(2, locationId);
            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setLong(4, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.info("e:" + e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }

    public List<AddressVO> getAddressListByTypeLocationAndApplicationId(long applicationId, String addressType,
                                                                        long locationId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_LIST_BY_TYPE_LOCATION_AND_APPLICATION_ID);
            pstm.setString(1, addressType);
            pstm.setLong(2, locationId);
            pstm.setLong(3, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVOs.add(getConstructedAddressVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.info("e:" + e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVOs;
    }

    public String updateAddressPoliceStatusByApplicationId(long applicationId, String approvalStatus, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_ADDRESS_POLICE_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_POLICE_STATUS_BY_APPLICATION_ID);
            pstm.setString(1, approvalStatus);
            pstm.setLong(2, applicationId);
            pstm.setString(3, PoliceEnumConstant.AddressType.AC.toString());
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

    public String updateAddressPoliceStatusByAddressId(long addressId, String approvalStatus, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_ADDRESS_POLICE_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_POLICE_STATUS_BY_ADDRESS_ID);
            pstm.setString(1, approvalStatus);
            pstm.setLong(2, addressId);
            pstm.setString(3, PoliceEnumConstant.AddressType.AC.toString());
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

    public AddressVO getAddressByTypeApplicationLockedUser(long applicationId, int userId, long locationId,
                                                           Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        AddressVO addressVO = new AddressVO();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_BY_TYPE_AND_LOCKED_USER);
            pstm.setLong(1, userId);
            pstm.setString(2, PoliceEnumConstant.AddressType.AC.toString());
            pstm.setLong(3, locationId);
            pstm.setLong(4, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVO = getConstructedAddressVOFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVO;
    }

    public String moveAddressToAudit(long addressId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_ADDRESS_TO_AUDIT);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_ADDRESS_TO_AUDIT);
            pstm.setLong(1, addressId);
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

    public AddressVO getAddressById(long addressId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        AddressVO addressVo = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_BY_ADDRESS_ID);
            // /LOGGER.info(DBConstant.QUERY.SELECT_ADDRESS_BY_ADDRESS_ID);

            pstm.setLong(1, addressId);

            rst = pstm.executeQuery();

            while (rst.next()) {
                addressVo = getConstructedAddressVOFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressVo;
    }

    public String addAddressChangeAudit(AddressChangeAuditVO changeAuditVO, Connection con) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_ADDRESS_CHANGE_AUDIT);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_ADDRESS_CHANGE_AUDIT);
            pstm.setLong(1, changeAuditVO.getAddressId());
            pstm.setInt(2, changeAuditVO.getUpdatedUserId());
            pstm.setString(3, changeAuditVO.getUpdatedUserName());
            pstm.setString(4, changeAuditVO.getComment());

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public String addAddressTemp(AddressTempVO addressTempVO, Connection con) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_ADDRESS_TEMP);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_ADDRESS_TEMP);
            pstm.setLong(1, addressTempVO.getAddressId());
            pstm.setString(2, addressTempVO.getAddress());
            pstm.setString(3, addressTempVO.getAddressStatus());
            pstm.setLong(4, addressTempVO.getPoliceAreaId());
            pstm.setString(5, addressTempVO.getPoliceArea());
            pstm.setString(6, addressTempVO.getPoliceAreaStatus());
            pstm.setTimestamp(7, CommonUtil.getSqlTimeStampFromUtilDate(addressTempVO.getFromDate()));
            pstm.setTimestamp(8, CommonUtil.getSqlTimeStampFromUtilDate(addressTempVO.getToDate()));
            pstm.setString(9, addressTempVO.getStayPeriodStatus());
            pstm.setString(10, addressTempVO.getComment());
            pstm.setInt(11, addressTempVO.getActiveStatus());
            pstm.setInt(12, addressTempVO.getUpdatedUserId());
            pstm.setString(13, addressTempVO.getUpdatedUserName());
            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public AddressTempVO getActiveAddressTempByAddressId(long addressId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        AddressTempVO addressTempVO = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_TEMP_BY_ADDRESS_ID);
            pstm.setLong(1, addressId);
            pstm.setInt(2, 1);
            rst = pstm.executeQuery();
            while (rst.next()) {
                addressTempVO = getConstructedAddressTempVOFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.info("e:" + e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressTempVO;
    }

    public String discardAddressTempByAddressId(long addressId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.DEACTIVATE_ADDRESS_TEMP_BY_ADDRESS_ID);
            pstm = con.prepareStatement(DBConstant.QUERY.DEACTIVATE_ADDRESS_TEMP_BY_ADDRESS_ID);
            pstm.setInt(1, 0);
            pstm.setLong(2, addressId);
            int result = pstm.executeUpdate();
            LOGGER.info("discard Address Temp By Address Id HAS UPDATE EXECUTED: " + result);
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

    private AddressTempVO getConstructedAddressTempVOFromResultSet(ResultSet rst) throws SQLException {
        AddressTempVO addressTempVO = new AddressTempVO();
        addressTempVO.setActiveStatus(rst.getInt(DBConstant.ADDRESS_TEMP_COL.ACTIVE_STATUS));
        addressTempVO.setAddress(rst.getString(DBConstant.ADDRESS_TEMP_COL.ADDRESS));
        addressTempVO.setAddressId(rst.getLong(DBConstant.ADDRESS_TEMP_COL.ADDRESS_ID));
        addressTempVO.setAddressStatus(rst.getString(DBConstant.ADDRESS_TEMP_COL.ADDRESS_STATUS));
        addressTempVO.setAddressTempId(rst.getLong(DBConstant.ADDRESS_TEMP_COL.ADDRESS_TEMP_ID));
        addressTempVO.setComment(rst.getString(DBConstant.ADDRESS_TEMP_COL.COMMENT));
        addressTempVO.setFromDate(rst.getTimestamp(DBConstant.ADDRESS_TEMP_COL.FROM_DATE));
        addressTempVO.setPoliceArea(rst.getString(DBConstant.ADDRESS_TEMP_COL.POLICE_AREA));
        addressTempVO.setPoliceAreaId(rst.getLong(DBConstant.ADDRESS_TEMP_COL.POLICE_AREA_ID));
        addressTempVO.setPoliceAreaStatus(rst.getString(DBConstant.ADDRESS_TEMP_COL.POLICE_AREA_STATUS));
        addressTempVO.setStayPeriodStatus(rst.getString(DBConstant.ADDRESS_TEMP_COL.STAY_PERIOD_STATUS));
        addressTempVO.setToDate(rst.getTimestamp(DBConstant.ADDRESS_TEMP_COL.TO_DATE));
        addressTempVO.setUpdatedDate(rst.getTimestamp(DBConstant.ADDRESS_TEMP_COL.UPDATED_DATE));
        addressTempVO.setUpdatedUserId(rst.getInt(DBConstant.ADDRESS_TEMP_COL.UPDATED_USER_ID));
        addressTempVO.setUpdatedUserName(rst.getString(DBConstant.ADDRESS_TEMP_COL.UPDATED_USER_NAME));
        return addressTempVO;
    }

    public String moveAddressesToAuditByApplicationid(long applicationId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_ADDRESS_TO_AUDIT_BY_APPLICATION_ID);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_ADDRESS_TO_AUDIT_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);
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

    public List<AddressChangeAuditVO> getAddressChangeAuditVOsByAddressId(long addressId, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AddressChangeAuditVO> addressChangeAuditVOs = new ArrayList<AddressChangeAuditVO>();
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_ADDRESS_CHANGE_AUDITS_BY_ADDRESS_ID);
            pstm.setLong(1, addressId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                addressChangeAuditVOs.add(getConstructedAddressChangeAuditVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.info("e:" + e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return addressChangeAuditVOs;
    }

    private AddressChangeAuditVO getConstructedAddressChangeAuditVOFromResultSet(ResultSet rst) throws SQLException {
        AddressChangeAuditVO addressChangeAuditVO = new AddressChangeAuditVO();
        addressChangeAuditVO.setAddressId(rst.getLong(DBConstant.ADDRESS_CHANGE_AUDIT_COL.ADDRESS_ID));
        addressChangeAuditVO.setComment(rst.getString(DBConstant.ADDRESS_CHANGE_AUDIT_COL.COMMENT));
        addressChangeAuditVO.setId(rst.getLong(DBConstant.ADDRESS_CHANGE_AUDIT_COL.ID));
        addressChangeAuditVO.setUpdatedUserDateTime(rst
                .getTimestamp(DBConstant.ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME));
        addressChangeAuditVO.setUpdatedUserId(rst.getInt(DBConstant.ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_ID));
        addressChangeAuditVO.setUpdatedUserName(rst.getString(DBConstant.ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_NAME));
        return addressChangeAuditVO;
    }

    public List<ApplicationClearanceDate> getApplicationClearanceDateListByApplicationId(Connection con,
                                                                                         long applicationId) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ApplicationClearanceDate> applicationClearanceDates = new ArrayList<ApplicationClearanceDate>();
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_MODIFIED_DATES_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                applicationClearanceDates.add(getConstructedApplicationClearanceDatesVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.info("e:" + e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationClearanceDates;
    }

    private ApplicationClearanceDate getConstructedApplicationClearanceDatesVOFromResultSet(ResultSet rst)
            throws SQLException {
        ApplicationClearanceDate datesVO = new ApplicationClearanceDate();
        datesVO.setAddressId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID));
        datesVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID));
        datesVO.setDepartment(rst.getString(DBConstant.APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT));
        datesVO.setId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.ID));
        datesVO.setPrintedStatus(rst.getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS));
        datesVO.setResponsedDate(rst.getTimestamp(DBConstant.APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE));
        datesVO.setResponsedUserId(rst.getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.RESPONSED_USER_ID));
        datesVO.setSentDate(rst.getTimestamp(DBConstant.APPLICATION_CLEARANCE_DATE_COL.SENT_DATE));
        datesVO.setSentUserId(rst.getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.SENT_USER_ID));
        return datesVO;
    }

    public String updateAddressModifications(AddressVO addressVO, int userId, String userName, Connection con) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveAddressToAudit(addressVO.getAddressId(), con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_MODIFICATIONS);
            pstm.setString(1, addressVO.getAddress());
            pstm.setLong(2, addressVO.getPoliceAreaId());
            pstm.setString(3, addressVO.getPoliceArea());
            pstm.setTimestamp(4, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getFromDate()));
            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(addressVO.getToDate()));
            pstm.setInt(6, userId);
            pstm.setString(7, userName);
            pstm.setLong(8, addressVO.getAddressId());

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("updateAddressModifications HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    // not deleting address from db, just updating its type
    public String removeAddressFromApplication(long addressId, int userId, String userName, Connection con) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveAddressToAudit(addressId, con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.REMOVE_ADDRESS_FROM_APPLICATION);
            pstm.setString(1, PoliceEnumConstant.AddressType.RM.toString());
            pstm.setInt(2, userId);
            pstm.setString(3, userName);
            pstm.setLong(4, addressId);

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("removeAddressFromApplication HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }


}
