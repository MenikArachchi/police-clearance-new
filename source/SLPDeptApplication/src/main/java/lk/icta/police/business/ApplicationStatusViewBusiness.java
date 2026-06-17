package lk.icta.police.business;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.ApplicationStatusViewDAO;
import lk.icta.police.framework.dao.CommentDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationClearanceDate;
import lk.icta.police.framework.vo.ApplicationModifiedDatesVO;
import lk.icta.police.framework.vo.ApplicationStatusViewGridVO;
import lk.icta.police.framework.vo.ApplicationStatusViewSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ClearanceDateGridVO;
import lk.icta.police.framework.vo.CommentsVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ApplicationStatusViewBusiness implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = Logger.getLogger(ApplicationStatusViewBusiness.class);
  private static ApplicationStatusViewBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static ApplicationStatusViewBusiness getInstance() {
    synchronized (ApplicationStatusViewBusiness.class) {
      if (instance == null) {
        instance = new ApplicationStatusViewBusiness();
      }
      return instance;
    }
  }

  private ApplicationStatusViewBusiness() {

  }

  public List<ApplicationVO> searchApplication(ApplicationStatusViewSearchCriteriaVO searchCriteriaVO, int userRole,
      long location, int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = new ArrayList<ApplicationVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      returnedApplicationVOs =
          ApplicationStatusViewDAO.getInstance().searchApplicationForStatusView(searchCriteriaVO, con);
      for (ApplicationVO applicationVO : returnedApplicationVOs) {
        applicationVO.constructcertificatepostalAddress(true);
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
    return returnedApplicationVOs;
  }

  public ApplicationStatusViewGridVO getSelectedApplicationStatusByApplicationId(long applicationId) {
    Connection con = null;
    ApplicationStatusViewGridVO gridVO = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
      if (!(applicationVO == null)) {
        List<ApplicationClearanceDate> applicationClearanceDates =
            ApplicationBusiness.getInstance().getApplicationClearanceDateListByApplicationId(
                applicationVO.getApplicationId(), con);
        gridVO = new ApplicationStatusViewGridVO(applicationVO, applicationClearanceDates, con);
        // check for green channal
        if (StringUtils.isNotEmpty(applicationVO.getRecommendedOfficerName())) {
          gridVO.setRecommendedOfficerName(applicationVO.getRecommendedOfficerName());
          List<CommentsVO> commentsVOs =
              CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationId,
                  PoliceEnumConstant.CommentType.ASP.toString(), con);
          if (!(commentsVOs == null || commentsVOs.isEmpty())) {
            CommentsVO commentsVO = commentsVOs.get(0);
            gridVO.setRecommendedDate(commentsVO.getCreatedDateTime());
            gridVO.setRecommendedComment(commentsVO.getComment());
            gridVO.setRecommendedOfficerRank("ASP");
          }
        }

        if (!(gridVO == null)) {
          updateUserNamesInGridVOList(gridVO.getAddressStatusViewGridVOsPhq());
          updateUserNamesInGridVOList(gridVO.getAddressStatusViewGridVOsPolice());
          updateUserNamesInGridVOList(gridVO.getCidStatusViewGridVOs());
          updateUserNamesInGridVOList(gridVO.getTidStatusViewGridVOs());
          updateUserNamesInGridVOList(gridVO.getSisStatusViewGridVOs());
          updateUserNamesInGridVOList(gridVO.getNicStatusViewGridVOs());
          updateUserNamesInGridVOList(gridVO.getImiStatusViewGridVOs());
        }

        gridVO.setCoApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getCoApproved(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.CNA.toString(), con));
        if (StringUtils.isEmpty(gridVO.getCoApproved())) {
          // try as cho adverse
          gridVO.setCoApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getCoApproved(),
              PoliceEnumConstant.ApplicationModifiedDateTypes.CAD.toString(), con));
        }
        gridVO.setOicApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getOicApproved(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.OIC.toString(), con));
        gridVO.setAspApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getAspApproved(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.ASP.toString(), con));
        gridVO.setDhaApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getDhaApproved(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.DHA.toString(), con));
        gridVO.setDigApproved(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getDigApproved(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.DIG.toString(), con));
        gridVO.setPostedStatus(getConstructedApprovalText(applicationVO.getApplicationId(), gridVO.getPostedStatus(),
            PoliceEnumConstant.ApplicationModifiedDateTypes.POF.toString(), con));

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
    return gridVO;
  }

  private String getConstructedApprovalText(long applicationId, String hasApproved, String dateType, Connection con) {
    if (StringUtils.isNotEmpty(hasApproved)) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      List<ApplicationModifiedDatesVO> modifiedDatesVOs =
          ApplicationDAO.getInstance().getApplicationModifiedDateByTypeAndApplicationId(applicationId, dateType, con);
      if (!(modifiedDatesVOs == null || modifiedDatesVOs.isEmpty())) {
        Collections.sort(modifiedDatesVOs);
        ApplicationModifiedDatesVO datesVO = modifiedDatesVOs.get(0);
        if (!(datesVO == null || datesVO.getModifiedDate() == null)) {
          hasApproved = hasApproved + " (" + dateFormat.format(datesVO.getModifiedDate()) + ")";
        }
      }
    }
    return hasApproved;
  }

  private void updateUserNamesInGridVOList(List<ClearanceDateGridVO> addressStatusViewGridVOs) {
    if (!(addressStatusViewGridVOs == null || addressStatusViewGridVOs.isEmpty())) {
      for (ClearanceDateGridVO clearanceDateGridVO : addressStatusViewGridVOs) {
        clearanceDateGridVO.setSentUserName(getUserNameById(clearanceDateGridVO.getSentUserId()));
        clearanceDateGridVO.setRespondedUserName(getUserNameById(clearanceDateGridVO.getRespondedUserId()));
      }
    }

  }

  public String getUserNameById(int userId) {
    String userName = "";
    if (userId > 0) {
      UserVO userVOTemp = new UserVO();
      userVOTemp.setId(userId);
      UserVO user;
      try {
        user = CommonUserBusiness.getInstance().getUserDetails(userVOTemp);
        if (!(user == null)) {
          userName = user.getFullName();
        }
      } catch (lk.icta.commonuser.framework.exception.BusinessException e) {
        e.printStackTrace();
      }
    }
    return userName;
  }



}
