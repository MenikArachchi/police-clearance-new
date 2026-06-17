package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ExternalDeptPrintSearchCriteriaVO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExternalDeptPrintSearchDAO {

    private static ExternalDeptPrintSearchDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(ExternalDeptPrintSearchDAO.class);

    public static ExternalDeptPrintSearchDAO getInstance() {
        synchronized (ExternalDeptPrintSearchDAO.class) {
            if (instance == null) {
                instance = new ExternalDeptPrintSearchDAO();
            }
            return instance;
        }
    }

    private ExternalDeptPrintSearchDAO() {

    }


    public List<ApplicationVO> searchApplicationForExternalReview(ExternalDeptPrintSearchCriteriaVO criteriaVO,
                                                                  Connection conn) {
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            LOGGER.info(criteriaVO);
            LOGGER.info(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_DEPARTMENT_PRINT_LIST);
            System.out.println("criteriaVO " + criteriaVO);
            System.out.println(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_DEPARTMENT_PRINT_LIST);
            pstm =
                    conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_DEPARTMENT_PRINT_LIST);

            pstm.setString(1, criteriaVO.getDepartment());

            pstm.setString(2, criteriaVO.getReferenceNo());
            pstm.setInt(3, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(4, criteriaVO.getNicNo() + "%");
            pstm.setInt(5, criteriaVO.getNicNoSqlInjection());

            pstm.setString(6, criteriaVO.getNewNicNo() + "%");
            pstm.setInt(7, criteriaVO.getNewNicNoSqlInjection());

            pstm.setString(8, criteriaVO.getPptNo() + "%");
            pstm.setInt(9, criteriaVO.getPptNoSqlInjection());

            pstm.setString(10, "%" + criteriaVO.getName() + "%");
            pstm.setString(11, "%" + criteriaVO.getName() + "%");
            pstm.setInt(12, criteriaVO.getNameSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(13, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromSentDate()));
            pstm.setInt(14, criteriaVO.getFromSentDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(15, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToSentDate()));
            pstm.setInt(16, criteriaVO.getToSentDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(17, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromReceivedDate()));
            pstm.setInt(18, criteriaVO.getFromReceivedDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(19, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToReceivedDate()));
            pstm.setInt(20, criteriaVO.getToReceivedDateSqlInjection());


            pstm.setString(21, criteriaVO.getCidStatus());
            pstm.setInt(22, criteriaVO.getCidStatusSqlInjection());

            pstm.setString(23, criteriaVO.getTidStatus());
            pstm.setInt(24, criteriaVO.getTidStatusSqlInjection());

            pstm.setString(25, criteriaVO.getSisStatus());
            pstm.setInt(26, criteriaVO.getSisStatusSqlInjection());

            pstm.setString(27, criteriaVO.getNicStatus());
            pstm.setInt(28, criteriaVO.getNicStatusSqlInjection());

            pstm.setString(29, criteriaVO.getImiStatus());
            pstm.setInt(30, criteriaVO.getImiStatusSqlInjection());

            pstm.setString(31, PoliceConstant.YES);
            pstm.setString(32, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
            pstm.setString(33, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());

            pstm.setInt(34, criteriaVO.getLimit());

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE searchApplicationForExternalReview Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOs;
    }


    public List<ApplicationVO> searchApplicationForExternalReviewPrintPolice(
            ExternalDeptPrintSearchCriteriaVO criteriaVO, int userRole, Connection con) {
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        int poliAreaSQLInjection = 0;
        int approvalStatusSqlInjection = 0;
        if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
            poliAreaSQLInjection = 1;
            approvalStatusSqlInjection = 1;
        }

        List<Long> applicationIdList = new ArrayList<Long>();

        try {
            // LOGGER.info(criteriaVO);
            // LOGGER.info("userRole :" + userRole);
            LOGGER.info("criteriaVO.getLocationId() :" + criteriaVO.getLocationId());
            LOGGER.info(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_PRINT_LIST_POLICE);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_PRINT_LIST_POLICE);

            pstm.setString(1, criteriaVO.getDepartment());

            pstm.setString(2, criteriaVO.getReferenceNo());
            pstm.setInt(3, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(4, criteriaVO.getNicNo() + "%");
            pstm.setInt(5, criteriaVO.getNicNoSqlInjection());

            pstm.setString(6, criteriaVO.getNewNicNo() + "%");
            pstm.setInt(7, criteriaVO.getNewNicNoSqlInjection());

            pstm.setString(8, criteriaVO.getPptNo() + "%");
            pstm.setInt(9, criteriaVO.getPptNoSqlInjection());

            pstm.setString(10, "%" + criteriaVO.getName() + "%");
            pstm.setString(11, "%" + criteriaVO.getName() + "%");
            pstm.setInt(12, criteriaVO.getNameSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(13, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromSentDate()));
            pstm.setInt(14, criteriaVO.getFromSentDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(15, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToSentDate()));
            pstm.setInt(16, criteriaVO.getToSentDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(17, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromReceivedDate()));
            pstm.setInt(18, criteriaVO.getFromReceivedDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(19, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToReceivedDate()));
            pstm.setInt(20, criteriaVO.getToReceivedDateSqlInjection());

            pstm.setLong(21, criteriaVO.getLocationId());
            pstm.setInt(22, poliAreaSQLInjection);

            pstm.setString(23, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(24, PoliceEnumConstant.ApprovalStatus.TU.toString());
            pstm.setInt(25, criteriaVO.getPoliceStatusStatusSqlInjection());

            pstm.setString(26, PoliceEnumConstant.AddressType.AC.toString());

            pstm.setString(27, PoliceConstant.YES);

            pstm.setString(28, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            pstm.setString(29, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());

            pstm.setInt(30, criteriaVO.getStartFrom());
            pstm.setInt(31, criteriaVO.getLimit());

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    long applicationId = rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID);
                    if (!(applicationIdList.contains(applicationId))) {
                        applicationIdList.add(applicationId);
                        applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
                    }
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getCOOList Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOs;
    }

    public List<ApplicationVO> getApplicationListByIds(List<Long> applicationIdList, Connection con) {
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            String sql = DBConstant.QUERY.SELECT_APPLICATIONS_BY_ID_LIST;
            for (Long appId : applicationIdList) {
                sql = sql + "'" + appId + "',";
            }
            sql = sql.substring(0, (sql.length() - 1)) + ");";
            LOGGER.info("sql :- " + sql);
            pstm = con.prepareStatement(sql);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationListByIds Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOs;
    }

    public String updateApplicationPrintedStatusExtrenalDept(long applicationId, String department, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.ERROR;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_PRINTED_STATUS_EXTERNAL_DEPT);
            pstm.setLong(1, applicationId);
            pstm.setString(2, department);

            int result = pstm.executeUpdate();
            // LOGGER.info("HAS UPDATE EXECUTED: applicationId" + applicationId + " department: " +
            // department + " result: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            } else {
                returnValue = PoliceConstant.ERROR;
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

    public String updateApplicationPrintedStatusExtrenalDeptPolice(long addressId, String department, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.ERROR;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_PRINTED_STATUS_EXTERNAL_DEPT_POLICE);
            pstm.setLong(1, addressId);
            pstm.setString(2, department);

            int result = pstm.executeUpdate();
            // LOGGER.info("HAS UPDATE EXECUTED: addressId" + addressId + " department: " + department +
            // " result: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            } else {
                returnValue = PoliceConstant.ERROR;
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
