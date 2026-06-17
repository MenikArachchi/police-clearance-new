package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationReviewStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationType;
import lk.icta.police.framework.dao.ApplicationDetailsReportDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationDetailsReportSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ApplicationDetailsReportBusiness {
  private static final Logger LOGGER = Logger.getLogger(ApplicationDetailsReportBusiness.class);
  private static ApplicationDetailsReportBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static ApplicationDetailsReportBusiness getInstance() {
    synchronized (ApplicationDetailsReportBusiness.class) {
      if (instance == null) {
        instance = new ApplicationDetailsReportBusiness();
      }
      return instance;
    }
  }

  private ApplicationDetailsReportBusiness() {

  }

  public List<ApplicationVO> getAllocationList(
      ApplicationDetailsReportSearchCriteriaVO applicationDetailsReportSearchCriteriaVO, int userId) {
    Connection con = null;
    List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
    int requestClarificationCount = 0;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      applicationVOs =
          ApplicationDetailsReportDAO.getInstance().fetchApplicationVerificationList(
              applicationDetailsReportSearchCriteriaVO, con);

      for (ApplicationVO applicationVO : applicationVOs) {
        if (StringUtils.isNotEmpty(applicationVO.getApplicationType())) {
          applicationVO.setApplicationType(ApplicationType.fromCode(applicationVO.getApplicationType())
              .getDisplayName());
        }

        if (StringUtils.isNotEmpty(applicationVO.getApplicationReviewStatus())) {
          applicationVO.setApplicationReviewStatus(ApplicationReviewStatus.fromCode(
              applicationVO.getApplicationReviewStatus()).getDisplayName());
        }

        if (StringUtils.isNotEmpty(applicationVO.getApplicationClearanceStatus())) {
          applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.fromCode(
              applicationVO.getApplicationClearanceStatus()).getDisplayName());
        }

        if (StringUtils.isEmpty(applicationVO.getApplicantNameAsNic())) {
          applicationVO.setApplicantNameAsNic(applicationVO.getApplicantNameAsPassport());
        }

        applicationVO.constructcertificatepostalAddress(true);

      }

      // for (ApplicationVO applicationVO : applicationVOs) {
      //
      // requestClarificationCount =
      // RequestClarificationBusiness.getInstance().countRequestClarificationForApplication(applicationVO.getApplicationId());
      //
      // if(requestClarificationCount == 0
      // &&
      // applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RC.toString()))
      // {
      // applicationVO.setHasRequestClarification(1);
      // } else {
      // applicationVO.setHasRequestClarification(0);
      // }
      //
      // if(userId == applicationVO.getPhqRecordLockStatus()){
      // applicationVO.setHasCurrentUserLocked(1);
      // } else {
      // applicationVO.setHasCurrentUserLocked(0);
      // }
      //
      // applicationVO.allocateFileTypes();
      //
      // }

      return applicationVOs;
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return null;
  }
}
