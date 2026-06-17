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
public class OicClearenceBusiness {

  private static final Logger LOGGER = Logger.getLogger(OicClearenceBusiness.class);
  private static OicClearenceBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static OicClearenceBusiness getInstance() {
    synchronized (OicClearenceBusiness.class) {
      if (instance == null) {
        instance = new OicClearenceBusiness();
      }
      return instance;
    }
  }

  private OicClearenceBusiness() {

  }


  public LinkedList<CertificateClearenceGridVO> searchApplication(CertificateIssuanceSearchCriteriaVO searchCriteriaVO,
      int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = null;
    LinkedList<CertificateClearenceGridVO> certificateClearenceGridVOs = new LinkedList<CertificateClearenceGridVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      if (!(searchCriteriaVO == null)) {

        List<Long> applicationIds = new ArrayList<Long>();

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

          List<ApplicationVO> adverseRecords = ApplicationDAO.getInstance().getAllPreviousAdverseRecords(con);
          System.out.println("adverseRecords :" + adverseRecords.size());

          List<BlackListVO> blackListRecords = BlackListDAO.getInstance().getAllBlacklistRecords(con);
          System.out.println("blackListVOs :" + blackListRecords.size());
          System.out.println("limit :" + limit);

          mainwhile: while ((certificateClearenceGridVOs.size() < limit)) {

            returnedApplicationVOs =
                CertificateIssuanceDAO.getInstance().searchApplicationForCertificateIssuance(searchCriteriaVO, con,
                    PoliceConstant.CLEARANCE_APP_SEARCH_LIMITS);

            System.out.println("limit :" + limit);
            System.out.println("certificateClearenceGridVOs.size() :" + certificateClearenceGridVOs.size());
            System.out.println("returnedApplicationVOs :" + returnedApplicationVOs.size());
            System.out.println("searchCriteriaVO.getMaxId() " + searchCriteriaVO.getMaxId());

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
                    checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con, adverseRecordCount, blackListCount,
                        hasAnyCurrentAdverse);

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


  public boolean checkIfToAddApplicationVO(ApplicationVO applicationVO,
      CertificateIssuanceSearchCriteriaVO searchCriteriaVO, Connection con, long adverseRecordCount,
      long blackListCount, boolean hasAnyCurrentAdverse) {

    boolean hasAnyAdverse = false;

    // check if theres any other adverse records
    if (adverseRecordCount > 0) {
      hasAnyAdverse = true;
    }

    // check if theres any blacklisted records
    if (blackListCount > 0) {
      applicationVO.setHasBlackListed(1);
    }

    // check if there's any current adverse
    if (!(hasAnyAdverse)) {
      hasAnyAdverse = hasAnyCurrentAdverse;
    }

    boolean doAddApplicationVO = false;
    if (!(hasAnyAdverse)) {
      if (StringUtils.equals(applicationVO.getApplicationQueue(), PoliceEnumConstant.ApplicationQueue.OI.toString())
          || (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.GC.toString()) && (StringUtils.equals(
              applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
              .isEmpty(applicationVO.getOicApproved())))) {
        doAddApplicationVO = true;
      }

      if (!(searchCriteriaVO.isPrintPendingOnly())) {
        if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
            PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
          if (StringUtils.isEmpty(applicationVO.getAspApproved())
              || StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
            doAddApplicationVO = true;
          }
        }
      } else {
        if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
            PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
          if (StringUtils.isEmpty(applicationVO.getOicApproved())
              || StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
            doAddApplicationVO = true;
          }
        }
      }


    } else {
      applicationVO.setAnyAdverseAvailable(1);
      if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
          PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())
          && (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
              .isEmpty(applicationVO.getOicApproved()))) {
        doAddApplicationVO = true;
      }

      if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
          PoliceEnumConstant.ApplicationClearenceStatus.DC.toString())
          && (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
              .isEmpty(applicationVO.getOicApproved()))) {
        doAddApplicationVO = true;
      }

      if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
        if ((StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
            .isEmpty(applicationVO.getOicApproved()))) {
        	 if (StringUtils.isEmpty(applicationVO.getPreviousReferenceNo())) {
        		 doAddApplicationVO = true;
        	 }        	
         
        } else {
          if (!(searchCriteriaVO.isPrintPendingOnly())) {
            if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
              doAddApplicationVO = true;
            }
          }
        }
      }

      if (!(searchCriteriaVO.isPrintPendingOnly())) {
        if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())
            && (applicationVO.getCertificatePrintedStatus() <= 0)) {
          doAddApplicationVO = true;
        }
      }


    }

    return doAddApplicationVO;
  }



}
