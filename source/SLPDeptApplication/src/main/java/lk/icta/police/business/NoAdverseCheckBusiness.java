package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.BlackListDAO;
import lk.icta.police.framework.dao.CertificateIssuanceDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.CertificateClearenceGridVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Nadeeshani Senevirathna
 * 
 */
public class NoAdverseCheckBusiness {

  private static final Logger LOGGER = Logger.getLogger(NoAdverseCheckBusiness.class);
  private static NoAdverseCheckBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static NoAdverseCheckBusiness getInstance() {
    synchronized (NoAdverseCheckBusiness.class) {
      if (instance == null) {
        instance = new NoAdverseCheckBusiness();
      }
      return instance;
    }
  }

  private NoAdverseCheckBusiness() {

  }


  public LinkedList<CertificateClearenceGridVO> searchApplications(
      CertificateIssuanceSearchCriteriaVO searchCriteriaVO, int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = null;
    LinkedList<CertificateClearenceGridVO> certificateClearenceGridVOs = new LinkedList<CertificateClearenceGridVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      if (!(searchCriteriaVO == null)) {

        int limit = searchCriteriaVO.getLimit();

        long maximumAppId =
            CertificateIssuanceDAO.getInstance().maxApplicationIdForCertificateIssuance(searchCriteriaVO, con);

        System.out.println("searchCriteriaVO.getMaxId() " + searchCriteriaVO.getMaxId());
        System.out.println("maximimAppId " + maximumAppId);

        if (!(maximumAppId <= 0)) {
          // since next query is less than
          maximumAppId = maximumAppId + 1;
          if (searchCriteriaVO.getMaxId() == 0) {
            searchCriteriaVO.setMaxId(maximumAppId);
          }

          List<Long> applicationIds = new ArrayList<Long>();

          List<ApplicationVO> adverseRecords = ApplicationDAO.getInstance().getAllPreviousAdverseRecords(con);
          System.out.println("adverseRecords :" + adverseRecords.size());
          List<BlackListVO> blackListRecords = BlackListDAO.getInstance().getAllBlacklistRecords(con);
          System.out.println("blackListVOs :" + blackListRecords.size());

          mainwhile: while ((certificateClearenceGridVOs.size() < limit)) {

            returnedApplicationVOs =
                CertificateIssuanceDAO.getInstance().searchApplicationForCertificateIssuance(searchCriteriaVO, con,
                    PoliceConstant.CLEARANCE_APP_SEARCH_LIMITS);
            System.out.println("returnedApplicationVOs :" + returnedApplicationVOs.size());

            if (!(returnedApplicationVOs == null || returnedApplicationVOs.isEmpty())) {
              for (ApplicationVO applicationVO : returnedApplicationVOs) {

                if (certificateClearenceGridVOs.size() >= limit) {
                  System.out.println("break mainwhile max id :" + searchCriteriaVO.getMaxId());
                  break mainwhile;
                }

                // check if theres any other adverse records
                long adverseRecordCount =
                    CertificateIssuanceBusiness.getInstance().getCountOfPreviousAdverseRecords(adverseRecords,
                        applicationVO.getNic(), applicationVO.getPassport(), applicationVO.getApplicationId());
                // check if theres any blacklisted records
                long blackListCount =
                    CertificateIssuanceBusiness.getInstance().countBlackListRecordsByNICOrPPT(blackListRecords,
                        applicationVO.getNic(), applicationVO.getPassport());

                boolean hasAnyCurrentAdverse =
                    CertificateIssuanceBusiness.getInstance().checkAnyCurrentAdverseAvailable(applicationVO);

                boolean doAddApplicationVO =
                    checkIfToAddApplicationVO(applicationVO, con, searchCriteriaVO.isPrintPendingOnly(),
                        adverseRecordCount, blackListCount, hasAnyCurrentAdverse);

                applicationVO.constructcertificatepostalAddress(true);
                applicationVO.allocateFileTypes();
                if (applicationVO.getPhqRecordLockStatus() == userId && applicationVO.getPhqRecordLockStatus() != 0) {
                  applicationVO.setHasCurrentUserLocked(1);
                }

                if (doAddApplicationVO && (!(applicationIds.contains(applicationVO.getApplicationId())))) {
                  applicationIds.add(applicationVO.getApplicationId());
                  certificateClearenceGridVOs.add(new CertificateClearenceGridVO(applicationVO, searchCriteriaVO
                      .getUserType()));
                }
                searchCriteriaVO.setMaxId(applicationVO.getApplicationId());
              }
            } else {
              searchCriteriaVO.setLastPage(true);
              break;
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
    Collections.sort(certificateClearenceGridVOs);
    return certificateClearenceGridVOs;
  }

  public boolean checkIfAllExternalClearanceGiven(ApplicationVO applicationVO) {
    boolean hasAllExternalCleared = true;
    if (!(StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getPolStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    return hasAllExternalCleared;
  }



  public boolean checkIfToAddApplicationVO(ApplicationVO applicationVO, Connection con, boolean isOnlyPrintPending,
      long adverseRecordCount, long blackListCount, boolean hasAnyCurrentAdverse) {
    boolean doAddApplicationVO = true;
    // LOGGER.info("NO ADVERSE CHECK BUSINESS : checkIfToAddApplicationVO");

    // check if any current adverse available
    boolean hasAnyCurrentAdverseAvailable = hasAnyCurrentAdverse;
    if (hasAnyCurrentAdverseAvailable) {
      doAddApplicationVO = false;
      applicationVO.setAnyAdverseAvailable(1);
    }

    // check if clearence status is PN/NC/GC/EI/IS
    if (doAddApplicationVO) {
      PoliceEnumConstant.ApplicationClearenceStatus clearenceStatusEnum =
          PoliceEnumConstant.ApplicationClearenceStatus.PN;
      // LOGGER.info("clearenceStatusEnum before" + clearenceStatusEnum);
      if (StringUtils.isNotEmpty(applicationVO.getApplicationClearanceStatus())) {
        clearenceStatusEnum =
            PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
      }
      // LOGGER.info("clearenceStatusEnum after " + clearenceStatusEnum);
      switch (clearenceStatusEnum) {
        case PN:
          doAddApplicationVO = true;
          break;
        case NC:
          doAddApplicationVO = true;
          break;
        case GC:
          doAddApplicationVO = true;
          break;
        case EI:
          doAddApplicationVO = false; // Renewal applications should not be shown to Checking officer  
          break;
        case IS:
          doAddApplicationVO = true;
          break;
        default:
          System.out.println("NEW clearenceStatusEnum :" + clearenceStatusEnum);
          break;
      }
    }

    // check if OIC has approved already
    if (doAddApplicationVO) {
      if (StringUtils.isNotEmpty(applicationVO.getOicApproved())) {
        if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
          doAddApplicationVO = false;
        }
      }
    }

    // check if there's any blacklisted records
    if (doAddApplicationVO) {
      // LOGGER.info("blackListCount :" + blackListCount);
      if (blackListCount > 0) {
        doAddApplicationVO = false;
        applicationVO.setHasBlackListed(1);
      }
    }

    // check if there's any other adverse records
    if (doAddApplicationVO) {
      // LOGGER.info("adverseRecordCount :" + adverseRecordCount);
      if (adverseRecordCount > 0) {
        doAddApplicationVO = false;
        applicationVO.setAnyAdverseAvailable(1);
      }
    }

    if (isOnlyPrintPending) {
      boolean hasAllExternalCleared = checkIfAllExternalClearanceGiven(applicationVO);
      if (!(hasAllExternalCleared)) {
        doAddApplicationVO = false;
      } else {
        if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
            PoliceEnumConstant.ApplicationClearenceStatus.IS.getCode())) {
          if (applicationVO.getCertificatePrintedStatus() > 0) {
            doAddApplicationVO = false;
          }
        }
      }
    }

    return doAddApplicationVO;
  }



}
