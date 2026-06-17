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
public class PostingOfficerBusiness {

  private static final Logger LOGGER = Logger.getLogger(PostingOfficerBusiness.class);
  private static PostingOfficerBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static PostingOfficerBusiness getInstance() {
    synchronized (PostingOfficerBusiness.class) {
      if (instance == null) {
        instance = new PostingOfficerBusiness();
      }
      return instance;
    }
  }

  private PostingOfficerBusiness() {

  }


  public LinkedList<CertificateClearenceGridVO> searchApplications(
      CertificateIssuanceSearchCriteriaVO searchCriteriaVO, int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = null;
    // System.out.println("searchCriteriaVO :" + searchCriteriaVO);
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
                CertificateIssuanceDAO.getInstance().searchApplicationForCertificateIssuanceForPostingOfficer(searchCriteriaVO, con,
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
                    checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con, adverseRecordCount, blackListCount,
                        hasAnyCurrentAdverse);

                applicationVO.constructcertificatepostalAddress(true);
                applicationVO.allocateFileTypes();
                if (applicationVO.getPhqRecordLockStatus() == userId && applicationVO.getPhqRecordLockStatus() != 0) {
                  applicationVO.setHasCurrentUserLocked(1);
                }

                if (doAddApplicationVO) {
                  if (searchCriteriaVO.isDefaultView()) {
                    if ((!(applicationIds.contains(applicationVO.getApplicationId())))) {
                      applicationIds.add(applicationVO.getApplicationId());
                      CertificateClearenceGridVO clearenceGridVO =
                          new CertificateClearenceGridVO(applicationVO, searchCriteriaVO.getUserType());
                      certificateClearenceGridVOs.add(clearenceGridVO);
                    }
                  } else {
                    if (!(applicationIds.contains(applicationVO.getApplicationId()))) {
                      if (applicationVO.getCertificatePrintedStatus() > 0
                          || StringUtils.isNotEmpty(applicationVO.getRegPostNo())) {
                        applicationIds.add(applicationVO.getApplicationId());
                        CertificateClearenceGridVO clearenceGridVO =
                            new CertificateClearenceGridVO(applicationVO, searchCriteriaVO.getUserType());
                        certificateClearenceGridVOs.add(clearenceGridVO);
                      }
                    }
                  }
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


    // LOGGER.info("adverseRecordCount :" + adverseRecordCount);
    if (adverseRecordCount > 0) {
      hasAnyAdverse = true;
    }


    // LOGGER.info("blackListCount :" + blackListCount);
    if (blackListCount > 0) {
      applicationVO.setHasBlackListed(1);
    }

    // check if there's any current adverse
    if (!(hasAnyAdverse)) {
      hasAnyAdverse = hasAnyCurrentAdverse;
    }


    boolean doAddApplicationVO = false;

    if (hasAnyAdverse) {
      applicationVO.setAnyAdverseAvailable(1);
    }


    if (StringUtils.equals(applicationVO.getApplicationQueue(), PoliceEnumConstant.ApplicationQueue.PO.toString())) {
      if ((searchCriteriaVO.isPrintPendingOnly())) {
        if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
          doAddApplicationVO = true;
        }
      } else {
        doAddApplicationVO = true;
      }
    }

    if (!(hasAnyAdverse)) {
      if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
        if ((searchCriteriaVO.isPrintPendingOnly())) {
          if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
            doAddApplicationVO = true;
          }
        } else {
          doAddApplicationVO = true;
        }
      }
    } else {
      if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
        if ((searchCriteriaVO.isPrintPendingOnly())) {
          if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
            doAddApplicationVO = true;
          }
        } else {
          doAddApplicationVO = true;
        }
      }
    }

    return doAddApplicationVO;
  }



}
