package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.BlackListDAO;
import lk.icta.police.framework.dao.CertificateIssuanceDAO;
import lk.icta.police.framework.dao.DepartmentDashboardDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;

import org.apache.log4j.Logger;

public class DepartmentDashboardBusiness {
  private static final Logger LOGGER = Logger.getLogger(DepartmentDashboardBusiness.class);
  private static DepartmentDashboardBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static DepartmentDashboardBusiness getInstance() {
    synchronized (DepartmentDashboardBusiness.class) {
      if (instance == null) {
        instance = new DepartmentDashboardBusiness();
      }
      return instance;
    }
  }

  private DepartmentDashboardBusiness() {

  }

  public int countApplicationsToBeVerified() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countApplicationsToBeVerified(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public int countResubmissionPending() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countResubmissionPending(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public int countRevisionUpdatesPending() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countRevisionUpdatesPending(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public int countExternalClearancePending() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countExternalClearancePending(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public int countExternalClearancePendingByDepartment(String department) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countExternalClearancePendingByDepartment(con, department);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public int countPoliceClearancePending() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countPoliceClearancePending(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }

  public Map<String, Integer> countInternalApprovalPending() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      CertificateIssuanceSearchCriteriaVO searchCriteriaVO =
          new CertificateIssuanceSearchCriteriaVO(null, null, null, null, 0, PoliceConstant.UNLIMITED_RECORDS_LIMIT,
              null, 0);
      searchCriteriaVO.setDefaultViewFromUi(0);
      searchCriteriaVO.updateSearchCriteriaVO(0);
      searchCriteriaVO.setPrintPendingOnly(true);

      List<ApplicationVO> adverseRecords = ApplicationDAO.getInstance().getAllPreviousAdverseRecords(con);
      System.out.println("adverseRecords :" + adverseRecords.size());
      List<BlackListVO> blackListRecords = BlackListDAO.getInstance().getAllBlacklistRecords(con);
      System.out.println("blackListVOs :" + blackListRecords.size());



      List<Long> applicationIds = new ArrayList<Long>();

      int oicCount = 0;
      int aspCount = 0;
      int digCount = 0;
      int dhaCount = 0;
      int postalCount = 0;
      int noAdverseCount = 0;
      int adverseCount = 0;

      long maxApplicationId = 0;
      int limit = PoliceConstant.HOME_COUNT_SEARCH_LIMIT;

      mainwhile: while (true) {

        List<ApplicationVO> returnedApplicationVOs =
            CertificateIssuanceDAO.getInstance().loadAllVerifiedApplications(con, maxApplicationId, limit);
        System.out.println("maxApplicationId :" + maxApplicationId);
        System.out.println("limit :" + limit);
        System.out.println("returnedApplicationVOs :" + returnedApplicationVOs.size());

        if ((returnedApplicationVOs == null || returnedApplicationVOs.isEmpty())) {
          break mainwhile;
        } else {
          for (ApplicationVO applicationVO : returnedApplicationVOs) {

            if (!(applicationIds.contains(applicationVO.getApplicationId()))) {
              maxApplicationId = applicationVO.getApplicationId();
              applicationIds.add(applicationVO.getApplicationId());
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

              boolean doAddApplicationVOOic =
                  OicClearenceBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationVOOic) {
                oicCount++;
              }

              boolean doAddApplicationVOAsp =
                  AspClearenceBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationVOAsp) {
                aspCount++;
              }

              boolean doAddApplicationVODig =
                  DigClearenceBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationVODig) {
                digCount++;
              }

              boolean doAddApplicationVODha =
                  DhaClearenceBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationVODha) {
                dhaCount++;
              }

              boolean doAddApplicationVOPostal =
                  PostingOfficerBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationVOPostal) {
                postalCount++;
              }

              boolean doAddApplicationNoAdverse =
                  NoAdverseCheckBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, con, true,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationNoAdverse) {
                noAdverseCount++;
              }

              boolean doAddApplicationAdverse =
                  AdverseCheckBusiness.getInstance().checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con,
                      adverseRecordCount, blackListCount, hasAnyCurrentAdverse);
              if (doAddApplicationAdverse && applicationVO.getCertificatePrintedStatus() <= 0) {
                adverseCount++;
              }


            }
          }
        }

      }



      map.put("OIC_COUNT", oicCount);
      map.put("ASP_COUNT", aspCount);
      map.put("DIG_COUNT", digCount);
      map.put("DHA_COUNT", dhaCount);
      map.put("POSTAL_COUNT", postalCount);
      map.put("NO_ADVERSE_COUNT", noAdverseCount);
      map.put("ADVERSE_COUNT", adverseCount);


    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }


    return map;
  }



  public int countCertificatePrintingPending() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      return DepartmentDashboardDAO.getInstance().countCertificatePrintingPending(con);
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return 0;
  }



}
