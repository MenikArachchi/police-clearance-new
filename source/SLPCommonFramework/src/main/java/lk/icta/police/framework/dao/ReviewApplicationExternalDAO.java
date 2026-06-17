package lk.icta.police.framework.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ReviewApplicationExternalSearchCriteriaVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Nadeeshani Senevirathna
 */
public class ReviewApplicationExternalDAO {

    private static ReviewApplicationExternalDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(ReviewApplicationExternalDAO.class);

    public static ReviewApplicationExternalDAO getInstance() {
        synchronized (ReviewApplicationExternalDAO.class) {
            if (instance == null) {
                instance = new ReviewApplicationExternalDAO();
            }
            return instance;
        }
    }

    private ReviewApplicationExternalDAO() {

    }


    public List<ApplicationVO> searchApplicationForExternalReview(ReviewApplicationExternalSearchCriteriaVO criteriaVO,
                                                                  Connection conn) {
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            LOGGER.info(criteriaVO);
            LOGGER.info(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW);

            pstm.setString(1, criteriaVO.getReferenceNo());
            pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(3, criteriaVO.getNicNo() + "%");
            pstm.setInt(4, criteriaVO.getNicNoSqlInjection());

            pstm.setString(5, criteriaVO.getNewNicNo() + "%");
            pstm.setInt(6, criteriaVO.getNewNicNoSqlInjection());

            pstm.setString(7, criteriaVO.getPptNo() + "%");
            pstm.setInt(8, criteriaVO.getPptNoSqlInjection());

            pstm.setString(9, "%" + criteriaVO.getName() + "%");
            pstm.setString(10, "%" + criteriaVO.getName() + "%");
            pstm.setInt(11, criteriaVO.getNameSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(12, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
            pstm.setInt(13, criteriaVO.getFromDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(14, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
            pstm.setInt(15, criteriaVO.getToDateSqlInjection());


            pstm.setString(16, criteriaVO.getCidStatus());
            pstm.setInt(17, criteriaVO.getCidStatusSqlInjection());

            pstm.setString(18, criteriaVO.getTidStatus());
            pstm.setInt(19, criteriaVO.getTidStatusSqlInjection());

            pstm.setString(20, criteriaVO.getSisStatus());
            pstm.setInt(21, criteriaVO.getSisStatusSqlInjection());

            pstm.setString(22, criteriaVO.getNicStatus());
            pstm.setInt(23, criteriaVO.getNicStatusSqlInjection());

            pstm.setString(24, criteriaVO.getImiStatus());
            pstm.setInt(25, criteriaVO.getImiStatusSqlInjection());

            pstm.setString(26, PoliceConstant.YES);
            pstm.setString(27, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            pstm.setString(28, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());

            pstm.setLong(29, criteriaVO.getMaxId());
            pstm.setInt(30, criteriaVO.getLimit());           

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
            LOGGER.info("INSIDE getCOOList Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOs;
    }


    public List<ApplicationVO> searchApplicationForExternalReviewPolice(
            ReviewApplicationExternalSearchCriteriaVO criteriaVO, int userRole, Connection con) {
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
            LOGGER.info("CRITERIA" + criteriaVO);
            LOGGER.info("userRole :" + userRole);
            System.out.println("criteriaVO.getMaxId() :" + criteriaVO.getMaxId());
            System.out.println("criteriaVO.getLimit() :" + criteriaVO.getLimit());
            System.out.println("criteriaVO.getLocationId() :" + criteriaVO.getLocationId());
            LOGGER.info(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE);

            pstm.setString(1, criteriaVO.getReferenceNo());
            pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(3, criteriaVO.getNicNo() + "%");
            pstm.setInt(4, criteriaVO.getNicNoSqlInjection());

            pstm.setString(5, criteriaVO.getNewNicNo() + "%");
            pstm.setInt(6, criteriaVO.getNewNicNoSqlInjection());


            pstm.setString(7, criteriaVO.getPptNo() + "%");
            pstm.setInt(8, criteriaVO.getPptNoSqlInjection());

            pstm.setString(9, "%" + criteriaVO.getName() + "%");
            pstm.setString(10, "%" + criteriaVO.getName() + "%");
            pstm.setInt(11, criteriaVO.getNameSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(12, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
            pstm.setInt(13, criteriaVO.getFromDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(14, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
            pstm.setInt(15, criteriaVO.getToDateSqlInjection());


            pstm.setLong(16, criteriaVO.getLocationId());
            pstm.setInt(17, poliAreaSQLInjection);

            pstm.setString(18, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(19, PoliceEnumConstant.ApprovalStatus.TU.toString());
            pstm.setInt(20, approvalStatusSqlInjection);

            pstm.setString(21, PoliceEnumConstant.AddressType.AC.toString());

            pstm.setString(22, PoliceConstant.YES);

            pstm.setString(23, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            pstm.setString(24, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());

            pstm.setLong(25, criteriaVO.getMaxId());
            pstm.setInt(26, criteriaVO.getLimit());

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
    
    public List<ApplicationVO> searchApplicationForExternalReviewPoliceASP(
            ReviewApplicationExternalSearchCriteriaVO criteriaVO, int userRole, Connection con) {
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
            LOGGER.info("CRITERIA" + criteriaVO);
            LOGGER.info("userRole :" + userRole);
            System.out.println("criteriaVO.getMaxId() :" + criteriaVO.getMaxId());
            System.out.println("criteriaVO.getLimit() :" + criteriaVO.getLimit());
            System.out.println("criteriaVO.getLocationId() :" + criteriaVO.getLocationId());
            LOGGER.info(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE_ASP);
            pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE_ASP);

            pstm.setString(1, criteriaVO.getReferenceNo());
            pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(3, criteriaVO.getNicNo() + "%");
            pstm.setInt(4, criteriaVO.getNicNoSqlInjection());

            pstm.setString(5, criteriaVO.getNewNicNo() + "%");
            pstm.setInt(6, criteriaVO.getNewNicNoSqlInjection());


            pstm.setString(7, criteriaVO.getPptNo() + "%");
            pstm.setInt(8, criteriaVO.getPptNoSqlInjection());

            pstm.setString(9, "%" + criteriaVO.getName() + "%");
            pstm.setString(10, "%" + criteriaVO.getName() + "%");
            pstm.setInt(11, criteriaVO.getNameSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(12, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
            pstm.setInt(13, criteriaVO.getFromDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(14, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
            pstm.setInt(15, criteriaVO.getToDateSqlInjection());


            pstm.setLong(16, criteriaVO.getLocationId());
            pstm.setInt(17, poliAreaSQLInjection);

            pstm.setString(18, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(19, PoliceEnumConstant.ApprovalStatus.TU.toString());
            pstm.setInt(20, approvalStatusSqlInjection);

            pstm.setString(21, PoliceEnumConstant.AddressType.AC.toString());

            pstm.setString(22, PoliceConstant.YES);

            pstm.setString(23, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            pstm.setString(24, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());

            pstm.setLong(25, criteriaVO.getMaxId());
            
            pstm.setString(26, PoliceConstant.PHQ_REQUEST);
            
            pstm.setInt(27, criteriaVO.getLimit());

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

    public long getTotalApplicationListCount(ReviewApplicationExternalSearchCriteriaVO criteriaVO, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long count = 0;
        try {
            LOGGER.info(criteriaVO);
            LOGGER.info(DBConstant.QUERY.GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW);
            pstm = conn.prepareStatement(DBConstant.QUERY.GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW);

            pstm.setString(1, criteriaVO.getReferenceNo());
            pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

            pstm.setString(3, criteriaVO.getNicNo() + "%");
            pstm.setInt(4, criteriaVO.getNicNoSqlInjection());

            pstm.setString(5, criteriaVO.getPptNo() + "%");
            pstm.setInt(6, criteriaVO.getPptNoSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
            pstm.setDate(7, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
            pstm.setInt(8, criteriaVO.getFromDateSqlInjection());

            // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
            pstm.setDate(9, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
            pstm.setInt(10, criteriaVO.getToDateSqlInjection());


            pstm.setString(11, criteriaVO.getCidStatus());
            pstm.setInt(12, criteriaVO.getCidStatusSqlInjection());

            pstm.setString(13, criteriaVO.getTidStatus());
            pstm.setInt(14, criteriaVO.getTidStatusSqlInjection());

            pstm.setString(15, criteriaVO.getSisStatus());
            pstm.setInt(16, criteriaVO.getSisStatusSqlInjection());

            pstm.setString(17, criteriaVO.getNicStatus());
            pstm.setInt(18, criteriaVO.getNicStatusSqlInjection());

            pstm.setString(19, criteriaVO.getImiStatus());
            pstm.setInt(20, criteriaVO.getImiStatusSqlInjection());

            pstm.setString(21, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            rst = pstm.executeQuery();

            if (!rst.next()) {
                // System.out.println("No records found");
                LOGGER.info("No records found");
            } else {
                count = rst.getLong("COUNT");
                System.out.println("count :" + count);
            }
			rst.close();
			pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return count;
    }


    public String lockCooRecord(long applicationId, int lockedUserId, int userRole, long locationId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
            switch (userDepartmentEnum) {
                case PHQ:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_RECORD_LOCK_STATUS_POLICE_PHQ);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, locationId);
                    pstm.setInt(3, 1);
                    pstm.setString(4, PoliceEnumConstant.AddressType.AC.toString());
                    pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
                    pstm.setString(6, PoliceEnumConstant.ApprovalStatus.TU.toString());
                    pstm.setLong(7, applicationId);
                    break;
                case POL:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_RECORD_LOCK_STATUS_POLICE_PHQ);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, locationId);
                    pstm.setInt(3, 0);
                    pstm.setString(4, PoliceEnumConstant.AddressType.AC.toString());
                    pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
                    pstm.setString(6, PoliceEnumConstant.ApprovalStatus.TU.toString());
                    pstm.setLong(7, applicationId);
                    break;
                case CID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_CID);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationId);
                    break;
                case TID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_TID);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationId);
                    break;
                case SIS:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_SIS);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationId);
                    break;
                case NIC:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_NIC);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationId);
                    break;
                case IMI:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_IMI);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationId);
                    break;
                default:
                    LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
                    break;
            }
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

    public String unlockCooRecord(long applicationId, int userRole, long locationId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
            switch (userDepartmentEnum) {
                case PHQ:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_RECORD_LOCK_STATUS_POLICE_PHQ);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, locationId);
                    pstm.setInt(3, 1);
                    pstm.setString(4, PoliceEnumConstant.AddressType.AC.toString());
                    pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
                    pstm.setString(6, PoliceEnumConstant.ApprovalStatus.TU.toString());
                    pstm.setLong(7, applicationId);
                    break;
                case POL:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_RECORD_LOCK_STATUS_POLICE_PHQ);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, locationId);
                    pstm.setInt(3, 0);
                    pstm.setString(4, PoliceEnumConstant.AddressType.AC.toString());
                    pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
                    pstm.setString(6, PoliceEnumConstant.ApprovalStatus.TU.toString());
                    pstm.setLong(7, applicationId);
                    break;
                case CID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_CID);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, applicationId);
                    break;
                case TID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_TID);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, applicationId);
                    break;
                case SIS:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_SIS);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, applicationId);
                    break;
                case NIC:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_NIC);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, applicationId);
                    break;
                case IMI:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_IMI);
                    pstm.setNull(1, Types.INTEGER);
                    pstm.setLong(2, applicationId);
                    break;
                default:
                    LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
                    break;
            }

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

    public String updateApplicationReviewApprovalStatus(long applicationId, long addressId, String approvalStatus,
                                                        long locationId, int userRole, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {

            // try and move application to audit
            returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);


            PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
            switch (userDepartmentEnum) {
                case PHQ:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_APPROVAL_STATUS_POLICE_PHQ);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, addressId);
                    break;
                case POL:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_ADDRESS_APPROVAL_STATUS_POLICE_PHQ);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, addressId);
                    break;
                case CID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_CID);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, applicationId);
                    break;
                case TID:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_TID);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, applicationId);
                    break;
                case SIS:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_SIS);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, applicationId);
                    break;
                case NIC:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_NIC);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, applicationId);
                    break;
                case IMI:
                    pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_IMI);
                    pstm.setString(1, approvalStatus);
                    pstm.setLong(2, applicationId);
                    break;
                default:
                    LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
                    break;
            }

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

    public String updateApplicationReviewApprovalStatusPolice(long applicationId, String approvalStatus, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // System.out.println("updateApplicationReviewApprovalStatusPolice");
            // System.out.println("approvalStatus :" + approvalStatus);
            // System.out.println("applicationId :" + applicationId);
            // try and move application to audit
            returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);

            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_APPROVAL_STATUS_POLICE);
            pstm.setString(1, approvalStatus);
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

    public List<Long> getOtherLockedAppListByCurrentUser(int lockedUserId, int userRole, long applicationid,
                                                         long locationId, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Long> idList = new ArrayList<Long>();
        try {
            PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
            switch (userDepartmentEnum) {
                case PHQ:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_PHQ);
                    pstm.setInt(1, lockedUserId);
                    pstm.setString(2, PoliceEnumConstant.AddressType.RM.toString());
                    pstm.setLong(3, applicationid);
                    break;
                case POL:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_POL);
                    pstm.setInt(1, lockedUserId);
                    pstm.setString(2, PoliceEnumConstant.AddressType.AC.toString());
                    pstm.setLong(3, applicationid);
                    pstm.setLong(4, locationId);
                    break;
                case CID:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_CID);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationid);
                    break;
                case TID:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_TID);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationid);
                    break;
                case SIS:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_SIS);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationid);
                    break;
                case NIC:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_NIC);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationid);
                    break;
                case IMI:
                    pstm = con.prepareStatement(DBConstant.QUERY.GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_IMI);
                    pstm.setInt(1, lockedUserId);
                    pstm.setLong(2, applicationid);
                    break;
                default:
                    LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
                    break;
            }

            if (!(pstm == null)) {
                rst = pstm.executeQuery();
                if (!rst.next()) {
                    System.out.println("No records found");
                    LOGGER.info("No records found");
                } else {
                    idList.add(rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID));
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
        return idList;
    }

    public String lockAddressRecord(long addressId, int lockedUserId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_RECORD_LOCK_STATUS_PHQ);
            pstm.setInt(1, lockedUserId);
            pstm.setLong(2, addressId);

            int result = pstm.executeUpdate();
            LOGGER.info("LOCK ADDRESS RECORD HAS UPDATE EXECUTED: " + result);
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

    public String unlockAddressRecord(long addressId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_RECORD_LOCK_STATUS_PHQ);
            pstm.setNull(1, Types.INTEGER);
            pstm.setLong(2, addressId);
            int result = pstm.executeUpdate();
            LOGGER.info(" UNLOCK ADDRESS RECORD HAS UPDATE EXECUTED: " + result);
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


    public long getOtherLockedAddressListByCurrentUser(int lockedUserId, long addressId, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long count = 0;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.COUNT_ALL_ADDRESSES_LOCKED_BY_PHQ);
            pstm.setInt(1, lockedUserId);
            pstm.setString(2, PoliceEnumConstant.AddressType.RM.toString());
            pstm.setLong(3, addressId);

            if (!(pstm == null)) {
                rst = pstm.executeQuery();
                if (!rst.next()) {
                    System.out.println("No records found");
                    LOGGER.info("No records found");
                } else {
                    count = rst.getLong("COUNT");
                    System.out.println("count ddd:" + count);
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
        return count;
    }

    public String updateAddressClearenceStatusPolice(long addressId, String approvalStatus, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // try and move address to audit
            returnValue = AddressDAO.getInstance().moveAddressToAudit(addressId, con);

            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_CLERARENCE_POLICE);
            pstm.setString(1, approvalStatus);
            pstm.setLong(2, addressId);
            int result = pstm.executeUpdate();
            LOGGER.info("updateAddressClearenceStatusPolice HAS UPDATE EXECUTED: " + result);
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

    public String updateAddressByPHQ(AddressTempVO addressTempVO, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // try and move address to audit
            returnValue = AddressDAO.getInstance().moveAddressToAudit(addressTempVO.getAddressId(), con);
            // System.out.println("updateAddressByPHQ :" + DBConstant.QUERY.UPDATE_ADDRESS_BY_PHQ);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_BY_PHQ);
            pstm.setLong(1, addressTempVO.getPoliceAreaId());
            pstm.setString(2, addressTempVO.getPoliceArea());
            pstm.setString(3, addressTempVO.getAddress());
            pstm.setTimestamp(4, CommonUtil.getSqlTimeStampFromUtilDate(addressTempVO.getFromDate()));
            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(addressTempVO.getToDate()));


            if (StringUtils.equals(addressTempVO.getAddressStatus(),
                    PoliceEnumConstant.AddressModificationStatus.CF.toString())
                    && StringUtils.equals(addressTempVO.getPoliceAreaStatus(),
                    PoliceEnumConstant.AddressModificationStatus.CF.toString())
                    && StringUtils.equals(addressTempVO.getStayPeriodStatus(),
                    PoliceEnumConstant.AddressModificationStatus.CF.toString())) {
                pstm.setString(6, PoliceEnumConstant.ApprovalStatus.AP.toString());
            } else {
                pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());
            }


            pstm.setInt(7, addressTempVO.getUpdatedUserId());
            pstm.setString(8, addressTempVO.getUpdatedUserName());
            pstm.setLong(9, addressTempVO.getAddressId());
            int result = pstm.executeUpdate();
            LOGGER.info("updateAddress By PHQ HAS UPDATE EXECUTED: " + result);
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

    public String updateApplicationClearanceDate(long applicationId, int createdUserId, String department, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // System.out.println("updateApplicationClearanceDate :" +
            // DBConstant.QUERY.UPDATE_APPLICATION_CLEARANCE_DATE);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARANCE_DATE);
            pstm.setInt(1, createdUserId);
            pstm.setLong(2, applicationId);
            pstm.setString(3, department);
            int result = pstm.executeUpdate();
            LOGGER.info("updateApplicationClearanceDate HAS UPDATE EXECUTED: " + result);
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


    public String updateAddressClearanceDate(long addressId, int createdUserId, String department, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // System.out.println("updateAddressClearanceDate :" +
            // DBConstant.QUERY.UPDATE_ADDRESS_CLEARANCE_DATE);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_CLEARANCE_DATE);
            pstm.setInt(1, createdUserId);
            pstm.setLong(2, addressId);
            pstm.setString(3, department);
            int result = pstm.executeUpdate();
            LOGGER.info("updateAddressClearanceDate HAS UPDATE EXECUTED: " + result);
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
