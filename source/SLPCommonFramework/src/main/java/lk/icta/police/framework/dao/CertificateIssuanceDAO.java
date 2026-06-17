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
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CertificateClearenceVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;
import lk.icta.police.framework.vo.NicRevisionClearenceVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Nadeeshani Senevirathna
 * 
 */
public class CertificateIssuanceDAO {

  private static CertificateIssuanceDAO instance = null;
  private static final Logger LOGGER = Logger.getLogger(CertificateIssuanceDAO.class);

  public static CertificateIssuanceDAO getInstance() {
    synchronized (CertificateIssuanceDAO.class) {
      if (instance == null) {
        instance = new CertificateIssuanceDAO();
      }
      return instance;
    }
  }

  private CertificateIssuanceDAO() {

  }



  public List<ApplicationVO> searchApplicationForCertificateIssuance(CertificateIssuanceSearchCriteriaVO criteriaVO,
      Connection conn, int manualLimit) {
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE);

      pstm.setString(1, criteriaVO.getReferenceNo());
      pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

      pstm.setString(3, "%" + criteriaVO.getName() + "%");
      pstm.setString(4, "%" + criteriaVO.getName() + "%");
      pstm.setInt(5, criteriaVO.getNameSqlInjection());

      pstm.setString(6, criteriaVO.getClearenceStatus());
      pstm.setInt(7, criteriaVO.getClearenceStatusSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
      pstm.setDate(8, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
      pstm.setInt(9, criteriaVO.getFromDateSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
      pstm.setDate(10, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
      pstm.setInt(11, criteriaVO.getToDateSqlInjection());

      pstm.setString(12, criteriaVO.getApplicationQueue());
      pstm.setInt(13, criteriaVO.getApplicationQueueSqlInjection());

      pstm.setString(14, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

      pstm.setString(15, PoliceConstant.YES);

      pstm.setLong(16, criteriaVO.getMaxId());

      if (manualLimit == 0) {
        pstm.setInt(17, criteriaVO.getLimit());
      } else {
        pstm.setInt(17, manualLimit);
      }

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

  public List<ApplicationVO> searchApplicationForCertificateIssuanceForPostingOfficer(CertificateIssuanceSearchCriteriaVO criteriaVO,
	      Connection conn, int manualLimit) {
	    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
	    PreparedStatement pstm = null;
	    ResultSet rst = null;
	    try {
	      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE_POSTING);

	      pstm.setString(1, criteriaVO.getReferenceNo());
	      pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

	      pstm.setString(3, "%" + criteriaVO.getName() + "%");
	      pstm.setString(4, "%" + criteriaVO.getName() + "%");
	      pstm.setInt(5, criteriaVO.getNameSqlInjection());

	      pstm.setString(6, criteriaVO.getClearenceStatus());
	      pstm.setInt(7, criteriaVO.getClearenceStatusSqlInjection());

	      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
	      pstm.setDate(8, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
	      pstm.setInt(9, criteriaVO.getFromDateSqlInjection());

	      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
	      pstm.setDate(10, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
	      pstm.setInt(11, criteriaVO.getToDateSqlInjection());

	      pstm.setString(12, criteriaVO.getApplicationQueue());
	      pstm.setInt(13, criteriaVO.getApplicationQueueSqlInjection());

	      pstm.setString(14, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

	      pstm.setString(15, PoliceConstant.YES);
	      
	      pstm.setLong(16, criteriaVO.getMaxId());

	      pstm.setString(17, PoliceConstant.AP);
	      
	      if (manualLimit == 0) {
	        pstm.setInt(18, criteriaVO.getLimit());
	      } else {
	        pstm.setInt(18, manualLimit);
	      }

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


  public long getTotalApplicationListCount(CertificateIssuanceSearchCriteriaVO criteriaVO, Connection conn) {
    PreparedStatement pstm = null;
    ResultSet rst = null;
    long count = 0;
    try {
      LOGGER.info(criteriaVO);
      LOGGER.info(DBConstant.QUERY.GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE);
      pstm =
          conn.prepareStatement(DBConstant.QUERY.GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE);

      pstm.setString(1, criteriaVO.getReferenceNo());
      pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

      pstm.setString(3, criteriaVO.getClearenceStatus());
      pstm.setInt(4, criteriaVO.getClearenceStatusSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
      pstm.setDate(5, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
      pstm.setInt(6, criteriaVO.getFromDateSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
      pstm.setDate(7, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
      pstm.setInt(8, criteriaVO.getToDateSqlInjection());

      pstm.setString(9, criteriaVO.getApplicationQueue());
      pstm.setInt(10, criteriaVO.getApplicationQueueSqlInjection());

      pstm.setString(11, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

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


  public String lockCertificateIssuanceRecord(long applicationId, int lockedUserId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.SUCCESS;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_CERTIFICATE_ISSUANCE_LOCK);
      pstm.setInt(1, lockedUserId);
      pstm.setString(2, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
      pstm.setLong(3, applicationId);
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

  public String unlockCertificateIssuanceRecord(long applicationId, int lockedUserId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_RECORD_LOCK_STATUS_CERTIFICATE_ISSUANCE_UNLOCK);
      pstm.setNull(1, Types.INTEGER);
      pstm.setLong(2, lockedUserId);
      pstm.setLong(3, applicationId);

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

  public String updateApplicationCertificateIssuanceStatus(CertificateClearenceVO clearenceVO,
      ApplicationVO applicationVO, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {
      if (StringUtils.isEmpty(clearenceVO.getCertificateType())) {
        clearenceVO.setCertificateType(applicationVO.getCertificateType());
      }
      // try and move application to audit
      returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(clearenceVO.getApplicationId(), con);
      String applicationQueue = CommonUtil.getApplicationQueueByUserType(clearenceVO);

      PoliceEnumConstant.UserType userTypeEnum = PoliceEnumConstant.UserType.fromCode(clearenceVO.getUpdatedUserType());
      switch (userTypeEnum) {
        case CN:
          pstm =
              con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_CO);
          pstm.setString(1, clearenceVO.getClearenceStatus());
          pstm.setString(2, clearenceVO.getApprovalStatus());
          pstm.setString(3, applicationQueue);
          pstm.setNull(4, Types.INTEGER);
          pstm.setInt(5, clearenceVO.getUpdatedUserId());
          pstm.setInt(6, clearenceVO.getVersionId());
          pstm.setLong(7, clearenceVO.getApplicationId());
          break;
        case OI:
          if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
            if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.AS.toString())) {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC_RJ_AS);
              pstm.setString(1, clearenceVO.getApprovalStatus());
              pstm.setString(2, applicationQueue);
              pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
              pstm.setNull(4, Types.INTEGER);
              pstm.setInt(5, clearenceVO.getUpdatedUserId());
              pstm.setInt(6, clearenceVO.getVersionId());
              pstm.setLong(7, clearenceVO.getApplicationId());
              break;
            } else {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC_RJ_CN);
              pstm.setString(1, clearenceVO.getApprovalStatus());
              pstm.setString(2, applicationQueue);
              pstm.setString(3, PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
              pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
              pstm.setNull(5, Types.INTEGER);
              pstm.setInt(6, clearenceVO.getUpdatedUserId());
              pstm.setInt(7, clearenceVO.getVersionId());
              pstm.setLong(8, clearenceVO.getApplicationId());
              break;
            }
          } else {
            pstm =
                con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC);
            pstm.setString(1, clearenceVO.getApprovalStatus());
            pstm.setString(2, applicationQueue);
            pstm.setNull(3, Types.INTEGER);
            pstm.setInt(4, clearenceVO.getUpdatedUserId());
            pstm.setInt(5, clearenceVO.getVersionId());
            pstm.setLong(6, clearenceVO.getApplicationId());
            break;
          }
        case AS:
          if (clearenceVO.isHasClearenceModified()) {
            if (StringUtils.equals(clearenceVO.getClearenceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_ASP_GC);
              pstm.setString(1, clearenceVO.getClearenceStatus());
              pstm.setString(2, clearenceVO.getRecomendedOfficerName());
              pstm.setString(3, clearenceVO.getApprovalLetterName());
              pstm.setString(4, applicationQueue);
              pstm.setNull(5, Types.INTEGER);
              pstm.setInt(6, clearenceVO.getUpdatedUserId());
              pstm.setInt(7, clearenceVO.getVersionId());
              pstm.setLong(8, clearenceVO.getApplicationId());
            } else {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_ASP);
              pstm.setString(1, clearenceVO.getClearenceStatus());
              pstm.setString(2, clearenceVO.getApprovalStatus());
              pstm.setString(3, applicationQueue);
              if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
                if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.OI.toString())) {
                  pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
                  pstm.setString(5, applicationVO.getDhaApproved());
                } else if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.DH.toString())) {
                  pstm.setString(4, applicationVO.getOicApproved());
                  pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
                }
              } else {
                pstm.setString(4, applicationVO.getOicApproved());
                pstm.setString(5, applicationVO.getDhaApproved());
              }
              pstm.setNull(6, Types.INTEGER);
              pstm.setInt(7, clearenceVO.getUpdatedUserId());
              pstm.setInt(8, clearenceVO.getVersionId());
              pstm.setLong(9, clearenceVO.getApplicationId());
            }
          } else {
            pstm =
                con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_GC);
            pstm.setString(1, clearenceVO.getApprovalStatus());
            pstm.setString(2, applicationQueue);
            if (StringUtils.equals(clearenceVO.getApprovalStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
              if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.OI.toString())) {
                pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
                pstm.setString(4, applicationVO.getDhaApproved());
                pstm.setString(5, applicationVO.getApplicationClearanceStatus());
                pstm.setString(6, applicationVO.getDigApproved());
              } else if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.DH.toString())) {
                pstm.setString(3, applicationVO.getOicApproved());
                pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
                pstm.setString(5, PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
                pstm.setString(6, applicationVO.getDigApproved());
              } else if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.DI.toString())) {
                pstm.setString(3, applicationVO.getOicApproved());
                pstm.setString(4, applicationVO.getDhaApproved());
                pstm.setString(5, applicationVO.getApplicationClearanceStatus());
                pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());
              }
            } else {
              pstm.setString(3, applicationVO.getOicApproved());
              pstm.setString(4, applicationVO.getDhaApproved());
              pstm.setString(5, applicationVO.getApplicationClearanceStatus());
              pstm.setString(6, applicationVO.getDigApproved());
            }
            pstm.setNull(7, Types.INTEGER);
            pstm.setInt(8, clearenceVO.getUpdatedUserId());
            pstm.setInt(9, clearenceVO.getVersionId());
            pstm.setLong(10, clearenceVO.getApplicationId());
          }
          break;
        case DH:
          if (clearenceVO.isHasClearenceModified()) {
            pstm =
                con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DHA_GC);
            pstm.setString(1, clearenceVO.getClearenceStatus());
            pstm.setString(2, applicationQueue);
            pstm.setString(3, clearenceVO.getCertificateType());
            pstm.setNull(4, Types.INTEGER);
            pstm.setInt(5, clearenceVO.getUpdatedUserId());
            pstm.setInt(6, clearenceVO.getVersionId());
            pstm.setLong(7, clearenceVO.getApplicationId());
          } else {
            if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.AS.toString())) {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DH_RJ_AS);
              pstm.setString(1, clearenceVO.getApprovalStatus());
              pstm.setString(2, applicationQueue);
              pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
              pstm.setString(4, clearenceVO.getCertificateType());
              pstm.setNull(5, Types.INTEGER);
              pstm.setInt(6, clearenceVO.getUpdatedUserId());
              pstm.setInt(7, clearenceVO.getVersionId());
              pstm.setLong(8, clearenceVO.getApplicationId());
            } else {
              pstm =
                  con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DH);
              pstm.setString(1, clearenceVO.getApprovalStatus());
              pstm.setString(2, applicationQueue);
              pstm.setString(3, clearenceVO.getCertificateType());
              pstm.setNull(4, Types.INTEGER);
              pstm.setInt(5, clearenceVO.getUpdatedUserId());
              pstm.setInt(6, clearenceVO.getVersionId());
              pstm.setLong(7, clearenceVO.getApplicationId());
            }
          }
          break;
        case DI:
          if (StringUtils.equals(applicationQueue, PoliceEnumConstant.ApplicationQueue.DH.toString())) {
            pstm =
                con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DI_RJ_DH);
            pstm.setString(1, clearenceVO.getApprovalStatus());
            pstm.setString(2, applicationQueue);
            pstm.setString(3, PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
            pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setNull(5, Types.INTEGER);
            pstm.setInt(6, clearenceVO.getUpdatedUserId());
            pstm.setInt(7, clearenceVO.getVersionId());
            pstm.setLong(8, clearenceVO.getApplicationId());
            break;
          } else {
            pstm =
                con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DI);
            pstm.setString(1, clearenceVO.getApprovalStatus());
            pstm.setString(2, applicationQueue);
            pstm.setNull(3, Types.INTEGER);
            pstm.setInt(4, clearenceVO.getUpdatedUserId());
            pstm.setInt(5, clearenceVO.getVersionId());
            pstm.setLong(6, clearenceVO.getApplicationId());
            break;
          }
        case PO:
          pstm =
              con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_PO);
          pstm.setString(1, clearenceVO.getRegisteredPostNo());
          pstm.setString(2, applicationQueue);
          pstm.setNull(3, Types.INTEGER);
          pstm.setInt(4, clearenceVO.getUpdatedUserId());
          pstm.setInt(5, clearenceVO.getVersionId());
          pstm.setLong(6, clearenceVO.getApplicationId());
          break;
        default:
          System.out.println("NEW USER TYPE :" + userTypeEnum);
          break;
      }
      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
      if (result > 0) {
        returnValue = PoliceConstant.SUCCESS;
      } else {
        returnValue = PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
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



  public long getOtherLockedAppListByCurrentUserForCertificateIssuance(int lockedUserId, long applicationid,
      Connection con) {
    PreparedStatement pstm = null;
    ResultSet rst = null;
    long count = 0;
    try {

      pstm = con.prepareStatement(DBConstant.QUERY.COUNT_ALL_VERIFIED_APPLICATIONS_LOCKED_FOR_CERTIFICATE_ISSUANCE);
      pstm.setInt(1, lockedUserId);
      pstm.setLong(2, applicationid);

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


  public String updateNotificationEmailSentStatus(long applicationId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.SUCCESS;
    try {

      // try and move application to audit
      ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);

      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_EMAIL_SENT_STATUS);
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

  public String updateApplicationNICRevisionClearenceStatus(NicRevisionClearenceVO nicRevisionClearenceVO,
      Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {
      // try and move application to audit
      returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(nicRevisionClearenceVO.getApplicationId(), con);
      // System.out.println("nicRevisionClearenceVO.getNicRevisionChangedName() :" +
      // nicRevisionClearenceVO.getNicRevisionChangedName());
      // System.out.println("nicRevisionClearenceVO.getNicFileName( :" +
      // nicRevisionClearenceVO.getNicFileName());
      if (nicRevisionClearenceVO.isHasUploadedNicFile()) {
        pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_NIC_FILE_NAME_AND_UNLOCK);
        pstm.setString(1, nicRevisionClearenceVO.getNicRevisionChangedName());
        pstm.setString(2, nicRevisionClearenceVO.getNicFileName());
        pstm.setString(3, nicRevisionClearenceVO.getNicFileNameBack());
        pstm.setString(4, nicRevisionClearenceVO.getClearenceStatus());
        pstm.setNull(5, Types.INTEGER);
        pstm.setInt(6, nicRevisionClearenceVO.getUpdatedUserId());
        pstm.setInt(7, nicRevisionClearenceVO.getNicRevisionVersionId());
        pstm.setLong(8, nicRevisionClearenceVO.getApplicationId());

      } else {
        pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_NIC_NAME_AND_UNLOCK);
        pstm.setString(1, nicRevisionClearenceVO.getNicRevisionChangedName());
        pstm.setString(2, nicRevisionClearenceVO.getClearenceStatus());
        pstm.setNull(3, Types.INTEGER);
        pstm.setInt(4, nicRevisionClearenceVO.getUpdatedUserId());
        pstm.setInt(5, nicRevisionClearenceVO.getNicRevisionVersionId());
        pstm.setLong(6, nicRevisionClearenceVO.getApplicationId());
      }

      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
      if (result > 0) {
        returnValue = PoliceConstant.SUCCESS;
      } else {
        returnValue = PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
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

  public String updateApplicationAddressPrintedStatus(long applicationId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {

      // try and move application to audit
      returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);

      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESS_PRINTED_STATUS);
      pstm.setLong(1, applicationId);

      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
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


  public String updateCertificatePrintedStatus(long applicationId, Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {

      // try and move application to audit
      returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);

      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_CERTIFICATE_PRINTED_STATUS);
      pstm.setLong(1, applicationId);

      int result = pstm.executeUpdate();
      LOGGER.info("HAS UPDATE EXECUTED: " + result);
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

  public String sendApplicationForApprovalAgain(long applicationId, int updatedUserId, String updatedUserName,
      Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {
      // try and move application to audit
      returnValue = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);

      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ALL_APPLICATION_APPROVAL_STATUSES);
      pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setString(2, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setInt(7, updatedUserId);
      pstm.setString(8, updatedUserName);
      pstm.setLong(9, applicationId);

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

  public String sendAddressesForApprovalAgain(long applicationId, int updatedUserId, String updatedUserName,
      Connection con) {
    PreparedStatement pstm = null;
    String returnValue = PoliceConstant.ERROR;
    try {
      // try and move application to audit
      returnValue = AddressDAO.getInstance().moveAddressesToAuditByApplicationid(applicationId, con);

      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_ADDRESSES_APPROVAL_STATUSES_BY_APLICATION_ID);
      pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
      pstm.setInt(2, updatedUserId);
      pstm.setString(3, updatedUserName);
      pstm.setLong(4, applicationId);

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

  public List<ApplicationVO> loadAllVerifiedApplications(Connection con, long maxApplicationId, int limit) {
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_VERIFIED_APPLICATIONS_FOR_DASHBOARD);
      pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
      pstm.setString(2, PoliceConstant.YES);
      pstm.setLong(3, maxApplicationId);
      pstm.setInt(4, limit);
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

  public long maxApplicationIdForCertificateIssuance(CertificateIssuanceSearchCriteriaVO criteriaVO, Connection con) {
    long maxId = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
    	
      if(criteriaVO.getApplicationQueue() == "PO"){
		pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_MAX_SEARCHED_APPLICATION_ID_FOR_CERTIFICATE_ISSUANCE_POSTING);
	  }else{
		pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_MAX_SEARCHED_APPLICATION_ID_FOR_CERTIFICATE_ISSUANCE);		
	  }      

      pstm.setString(1, criteriaVO.getReferenceNo());
      pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());

      pstm.setString(3, "%" + criteriaVO.getName() + "%");
      pstm.setString(4, "%" + criteriaVO.getName() + "%");
      pstm.setInt(5, criteriaVO.getNameSqlInjection());

      pstm.setString(6, criteriaVO.getClearenceStatus());
      pstm.setInt(7, criteriaVO.getClearenceStatusSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
      pstm.setDate(8, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
      pstm.setInt(9, criteriaVO.getFromDateSqlInjection());

      // LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
      pstm.setDate(10, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
      pstm.setInt(11, criteriaVO.getToDateSqlInjection());

      pstm.setString(12, criteriaVO.getApplicationQueue());
      pstm.setInt(13, criteriaVO.getApplicationQueueSqlInjection());

      pstm.setString(14, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

      pstm.setString(15, PoliceConstant.YES);


      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          maxId = rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID);
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
    return maxId;
  }



}
