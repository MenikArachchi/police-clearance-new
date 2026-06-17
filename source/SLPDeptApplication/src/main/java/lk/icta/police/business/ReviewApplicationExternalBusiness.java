package lk.icta.police.business;

import lk.icta.commonuser.framework.constant.CommonUserEnumConstant;
import lk.icta.commonuser.framework.vo.UserTypeVO;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.EmailType;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.CommentDAO;
import lk.icta.police.framework.dao.ReviewApplicationExternalDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Nadeeshani Senevirathna
 */
public class ReviewApplicationExternalBusiness {

  private static final Logger LOGGER = Logger.getLogger(ReviewApplicationExternalBusiness.class);
  private static ReviewApplicationExternalBusiness instance = null;
  private String isAspUser;

  /**
   * Singleton Implementation
   */
  public static ReviewApplicationExternalBusiness getInstance() {
    synchronized (ReviewApplicationExternalBusiness.class) {
      if (instance == null) {
        instance = new ReviewApplicationExternalBusiness();
      }
      return instance;
    }
  }

  private ReviewApplicationExternalBusiness() {

  }


  public List<ApplicationVO> searchApplication(ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO,
      int userRole, long location, int userId, Map<String, Object> session ) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = new ArrayList<ApplicationVO>();
    List<ApplicationVO> finalApplicationVOs = new ArrayList<ApplicationVO>();
    List<ApplicationVO> applicationList = new ArrayList<ApplicationVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      
      UserVO loggingUser = getUserFromSession(session);
      assert loggingUser != null;
      UserTypeVO loggingUserType = loggingUser.getUserType();
      String userType = loggingUserType.getName().getValue();
      
      if (!(searchCriteriaVO == null)) {


        searchCriteriaVO.setLocationId(location);

        int limit = searchCriteriaVO.getLimit();
        // System.out.println("limit :" + limit);
        // System.out.println("searchCriteriaVO.getMaxId() :" + searchCriteriaVO.getMaxId());

        mainwhile: while ((applicationList.size() < limit)) {

          LOGGER.info("userRole :" + userRole);
          // System.out.println("limit :" + limit);
          // System.out.println("finalApplicationVOs.size() :" + finalApplicationVOs.size());

          if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()
              || userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
            LOGGER.info("userRole POL OR PHQ:");            
            
            if (CommonUserEnumConstant.UserType.ASP.getValue().equals(userType)) {
            	 returnedApplicationVOs =
                         ReviewApplicationExternalDAO.getInstance().searchApplicationForExternalReviewPoliceASP(searchCriteriaVO,
                             userRole, con);    	
            	
            } else{
            	 returnedApplicationVOs =
                         ReviewApplicationExternalDAO.getInstance().searchApplicationForExternalReviewPolice(searchCriteriaVO,
                             userRole, con); 
            }

          } else {
            LOGGER.info("userRole NOT POL OR PHQ:");
            returnedApplicationVOs =
                ReviewApplicationExternalDAO.getInstance().searchApplicationForExternalReview(searchCriteriaVO, con);
          }

          System.out.println("returnedApplicationVOs.size() :" + returnedApplicationVOs.size());

          // LOGGER.info("returnedApplicationVOs EXT :" + returnedApplicationVOs);
          ApplicationClearanceDate clearanceDate = null;
          if (!(returnedApplicationVOs == null || returnedApplicationVOs.isEmpty())) {
            List<AddressVO> addressVOs = null;
            for (ApplicationVO applicationVO : returnedApplicationVOs) {
              applicationVO.constructcertificatepostalAddress(false);
              applicationVO.allocateFileTypes();
              // LOGGER.info("applicationVO.getPptFileType() :" + applicationVO.getPptFileType());
              // LOGGER.info("applicationVO.getPassportAttachPath() :" +
              // applicationVO.getPassportAttachPath());
              // LOGGER.info("applicationVO.getNicFileType() :" + applicationVO.getNicFileType());
              // LOGGER.info("applicationVO.getNicAttachPath() :" +
              // applicationVO.getNicAttachPath());
              PoliceEnumConstant.UserDepartment userRoleEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
              switch (userRoleEnum) {
                case CID:
                  if (applicationVO.getCidRecordLockStatus() == userId && applicationVO.getCidRecordLockStatus() != 0) {
                    applicationVO.setHasCurrentUserLocked(1);
                  }
                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.CID.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }
                  break;
                case TID:
                  if (applicationVO.getTidRecordLockStatus() == userId && applicationVO.getTidRecordLockStatus() != 0) {
                    applicationVO.setHasCurrentUserLocked(1);
                  }
                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.TID.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }
                  break;
                case SIS:
                  if (applicationVO.getSisRecordLockStatus() == userId && applicationVO.getSisRecordLockStatus() != 0) {
                    applicationVO.setHasCurrentUserLocked(1);
                  }
                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.SIS.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }
                  break;
                case NIC:
                  if (applicationVO.getNicRecordLockStatus() == userId && applicationVO.getNicRecordLockStatus() != 0) {
                    applicationVO.setHasCurrentUserLocked(1);
                  }
                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.NIC.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }
                  break;
                case IMI:
                  if (applicationVO.getImiRecordLockStatus() == userId && applicationVO.getImiRecordLockStatus() != 0) {
                    applicationVO.setHasCurrentUserLocked(1);
                  }

                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.IMI.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }
                  break;
                case PHQ:
                  // check for lock status in address
                  // addressVOs=AddressDAO.getInstance().getPendingAddressListByTypeAndApplicationId(applicationVO.getApplicationId(),
                  // PoliceEnumConstant.AddressType.AC.toString(), con);
                  addressVOs =
                      AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(),
                          PoliceEnumConstant.AddressType.AC.toString(), con);

                  if (!(addressVOs == null || addressVOs.isEmpty())) {
                    for (AddressVO addressVO : addressVOs) {
                      if ((addressVO.getPoliceRecordLockStatus() == userId)
                          && (addressVO.getPoliceRecordLockStatus() != 0)) {
                        applicationVO.setHasCurrentUserLocked(1);
                      }

                      if ((StringUtils.equals(addressVO.getPoliceStatus(),
                          PoliceEnumConstant.ApprovalStatus.PN.toString())
                          || StringUtils.equals(addressVO.getPoliceStatus(),
                              PoliceEnumConstant.ApprovalStatus.TU.toString()) || StringUtils.isEmpty(addressVO
                          .getPoliceStatus()))) {

                        AddressTempVO addressTempVO =
                            AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), con);
                        if (!(addressTempVO == null)) {
                          applicationVO.setHasRequestClarification(1);
                        }
                      }

                    }
                  }

                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.POL.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }

                  break;
                case POL:
                  // check for lock status in address
                  // addressVOs=AddressDAO.getInstance().getPendingAddressListByTypeAndApplicationId(applicationVO.getApplicationId(),
                  // PoliceEnumConstant.AddressType.AC.toString(), con);

                  addressVOs =
                      AddressDAO.getInstance()
                          .getAddressListByTypeLocationAndApplicationId(applicationVO.getApplicationId(),
                              PoliceEnumConstant.AddressType.AC.toString(), location, con);

                  if (!(addressVOs == null || addressVOs.isEmpty())) {
                    for (AddressVO addressVO : addressVOs) {
                      if ((addressVO.getPoliceRecordLockStatus() == userId)
                          && (addressVO.getPoliceRecordLockStatus() != 0)) {
                        applicationVO.setHasCurrentUserLocked(1);
                      }
                    }
                  }

                  clearanceDate =
                      ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(
                          applicationVO.getApplicationId(), PoliceEnumConstant.UserDepartment.POL.toString(),
                          PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), con);
                  if (!(clearanceDate == null)) {
                    applicationVO.setHasResent(1);
                  }

                  break;
                default:
                  LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
                  break;
              }

              searchCriteriaVO.setMaxId(applicationVO.getApplicationId());


              if (PoliceEnumConstant.UserDepartment.NIC.equals(userRoleEnum)){
                  if (!applicationVO.getCurrentNicNo().equals("N/A")){
                      finalApplicationVOs.add(applicationVO);
                  }
              }else {
                  finalApplicationVOs.add(applicationVO);
              }
              
              
              UserVO userVO = getUserFromSession(session);
              assert userVO != null;
              if (PoliceEnumConstant.UserDepartment.POL.getCode() == userVO.getDept().getId()
                      || PoliceEnumConstant.UserDepartment.PHQ.getCode() == userVO.getDept().getId()){
                  applicationList = filterApplications(finalApplicationVOs, session);
              }else {
                  applicationList = finalApplicationVOs;
              }

              if (applicationList.size() >= limit) {
                // System.out.println("BREAKING MAIN WHILE");
                break mainwhile;
              }
            }

          } else {
            searchCriteriaVO.setLastPage(true);
            break;
          }

        }

        // System.out.println("searchCriteriaVO.setLastPage :" + searchCriteriaVO.isLastPage());
        // System.out.println("searchCriteriaVO.setMaxId :" + searchCriteriaVO.getMaxId());
        // System.out.println("returnedApplicationVOs.size() :" + finalApplicationVOs.size());

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
    return applicationList;
  }

  private List<ApplicationVO> filterApplications(List<ApplicationVO> applicationVOS, Map<String, Object> session) {

      isAspUser(session);
      UserVO loggingUser = getUserFromSession(session);
      assert loggingUser != null;
      UserTypeVO loggingUserType = loggingUser.getUserType();
      String userType = loggingUserType.getName().getValue();

      List<ApplicationVO> filteredApplicationVOList = new ArrayList<ApplicationVO>();

      if (CommonUserEnumConstant.UserType.ASP.getValue().equals(userType)) {
          if (!applicationVOS.isEmpty()) {
              for (ApplicationVO applicationVO : applicationVOS) {
                  String aspReviewStatus = applicationVO.getAspReviewStatus();
                  if (PoliceEnumConstant.ASPReviewStatus.PHQ_REQUEST.getStatus().equals(aspReviewStatus)) {
                      filteredApplicationVOList.add(applicationVO);
                  }
              }
          }
      } else if (CommonUserEnumConstant.UserType.DHA.getValue().equals(userType)) {
          if (!applicationVOS.isEmpty()) {
              for (ApplicationVO applicationVO : applicationVOS) {
                  String aspReviewStatus = applicationVO.getAspReviewStatus();
                  if (PoliceEnumConstant.ASPReviewStatus.PHQ_REQUEST.getStatus().equals(aspReviewStatus)) {
                      filteredApplicationVOList.add(applicationVO);
                  }
              }
          }
      } else if (CommonUserEnumConstant.UserType.DEPARTMENT_USER.getValue().equals(userType)) {
          if (!applicationVOS.isEmpty()) {
              for (ApplicationVO applicationVO : applicationVOS) {
                  String aspReviewStatus = applicationVO.getAspReviewStatus();
                  if (!PoliceEnumConstant.ASPReviewStatus.PHQ_REQUEST.getStatus().equals(aspReviewStatus)) {
                      filteredApplicationVOList.add(applicationVO);
                  }
              }
          }

      } else if (CommonUserEnumConstant.UserType.DEPARTMENT_ADMIN.getValue().equals(userType)){
          if (!applicationVOS.isEmpty()) {
              for (ApplicationVO applicationVO : applicationVOS) {
                  String aspReviewStatus = applicationVO.getAspReviewStatus();
                  String applicationClearanceStatus = applicationVO.getApplicationClearanceStatus();
                  if (!PoliceEnumConstant.ASPReviewStatus.PHQ_REQUEST.getStatus().equals(aspReviewStatus) && !StringUtils.equals(applicationClearanceStatus,
  	                    PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())) {
                      filteredApplicationVOList.add(applicationVO);
                  }
              }
          }
      }else {
          filteredApplicationVOList = applicationVOS;
      }

      return filteredApplicationVOList;
  }
  
  private UserVO getUserFromSession(Map<String, Object> session) {
      if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
          return (UserVO) session.get(PoliceConstant.SESSION_USER);
      }
      return null;
  }
  
  private Boolean isAspUser(Map<String, Object> session) {
      UserVO loggingUser = getUserFromSession(session);
      assert loggingUser != null;
      UserTypeVO loggingUserType = loggingUser.getUserType();
      String userType = loggingUserType.getName().getValue();
      if (CommonUserEnumConstant.UserType.ASP.getValue().equals(userType)) {
          isAspUser = "true";
          return Boolean.TRUE;
      } else {
          isAspUser = "false";
          return Boolean.FALSE;
      }
  }
  // public long getTotalApplicationListCount(ReviewApplicationExternalSearchCriteriaVO
  // searchCriteriaVO, int userRole, long location, int userId) {
  // Connection con = null;
  // try {
  // con = DatabaseManager.getPOLDBConnection();
  // con.setAutoCommit(false);
  // int startFrom=searchCriteriaVO.getStartFrom();
  // int limit=searchCriteriaVO.getLimit();
  //
  // searchCriteriaVO.setStartFrom(0);
  // searchCriteriaVO.setLimit(PoliceConstant.UNLIMITED_RECORDS_LIMIT);
  // if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() ||
  // userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
  // List<ApplicationVO> applicationVOs=searchApplication(searchCriteriaVO, userRole, location,
  // userId);
  // if(!(applicationVOs==null)){
  // searchCriteriaVO.setStartFrom(startFrom);
  // searchCriteriaVO.setLimit(limit);
  // return applicationVOs.size();
  // }
  // }else{
  // searchCriteriaVO.setStartFrom(startFrom);
  // searchCriteriaVO.setLimit(limit);
  // return
  // ReviewApplicationExternalDAO.getInstance().getTotalApplicationListCount(searchCriteriaVO, con);
  // }
  //
  //
  // } catch (BusinessException e) {
  // LOGGER.error(e);
  // e.printStackTrace();
  // } catch (SQLException e) {
  // LOGGER.error(e);
  // e.printStackTrace();
  // } finally {
  // DatabaseManager.close(con);
  // }
  // return 0;
  // }
  //

  public Map<String, Object> checkAndLockRecord(long applicationId, int lockedUserId, int userRole, long locationId) {
    Connection con = null;
    boolean hasgainedLock = true;
    boolean hasAnyLockedByCurrentUser = false;
    boolean noRecordsToLock = false;
    String message = "Sorry, You have already locked the record with reference number: ";
    int lockedOtherUserId = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    String returnMessage = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
        List<AddressVO> addressVOs =
            AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,
                PoliceEnumConstant.AddressType.AC.toString(), con);
        LOGGER.info("addressVOs PHQ " + addressVOs);
        if (!(addressVOs == null || addressVOs.isEmpty())) {
          for (AddressVO addressVO : addressVOs) {
            if (addressVO.getPoliceRecordLockStatus() > 0 && addressVO.getPoliceRecordLockStatus() != lockedUserId) {
              lockedOtherUserId = addressVO.getPoliceRecordLockStatus();
              hasgainedLock = false;
            }
          }
        } else {
          hasgainedLock = false;
          noRecordsToLock = true;
        }
      } else if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
        List<AddressVO> addressVOs =
            AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationId,
                PoliceEnumConstant.AddressType.AC.toString(), locationId, con);
        LOGGER.info("addressVOs POL " + addressVOs);
        if (!(addressVOs == null || addressVOs.isEmpty())) {
          for (AddressVO addressVO : addressVOs) {
            if (addressVO.getPoliceRecordLockStatus() > 0 && addressVO.getPoliceRecordLockStatus() != lockedUserId) {
              lockedOtherUserId = addressVO.getPoliceRecordLockStatus();
              hasgainedLock = false;
            }
          }
        } else {
          hasgainedLock = false;
          noRecordsToLock = true;
        }
      } else {
        ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
        if (!(applicationVO == null)) {
          PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
          switch (userDepartmentEnum) {
            case CID:
              if (applicationVO.getCidRecordLockStatus() > 0 && applicationVO.getCidRecordLockStatus() != lockedUserId) {
                lockedOtherUserId = applicationVO.getCidRecordLockStatus();
                hasgainedLock = false;
              }
              break;
            case TID:
              if (applicationVO.getTidRecordLockStatus() > 0 && applicationVO.getTidRecordLockStatus() != lockedUserId) {
                lockedOtherUserId = applicationVO.getTidRecordLockStatus();
                hasgainedLock = false;
              }
              break;
            case SIS:
              if (applicationVO.getSisRecordLockStatus() > 0 && applicationVO.getSisRecordLockStatus() != lockedUserId) {
                lockedOtherUserId = applicationVO.getSisRecordLockStatus();
                hasgainedLock = false;
              }
              break;
            case NIC:
              if (applicationVO.getNicRecordLockStatus() > 0 && applicationVO.getNicRecordLockStatus() != lockedUserId) {
                lockedOtherUserId = applicationVO.getNicRecordLockStatus();
                hasgainedLock = false;
              }
              break;
            case IMI:
              if (applicationVO.getImiRecordLockStatus() > 0 && applicationVO.getImiRecordLockStatus() != lockedUserId) {
                lockedOtherUserId = applicationVO.getImiRecordLockStatus();
                hasgainedLock = false;
              }
              break;
            default:
              hasgainedLock = false;
              LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
              break;
          }
        }
      }

      List<Long> applicationIds =
          ReviewApplicationExternalDAO.getInstance().getOtherLockedAppListByCurrentUser(lockedUserId, userRole,
              applicationId, locationId, con);
      long count = 0;
      if (applicationIds != null) {
        count = applicationIds.size();
      }

      LOGGER.info("count FSD FSDF:" + count);
      if (count > 0) {
        hasAnyLockedByCurrentUser = true;
      }

      if (!(hasAnyLockedByCurrentUser)) {
        if (hasgainedLock) {
          String status =
              ReviewApplicationExternalDAO.getInstance().lockCooRecord(applicationId, lockedUserId, userRole,
                  locationId, con);
          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            con.commit();
            returnMessage = PoliceConstant.SUCCESS;
          } else {
            con.rollback();
          }
        } else {
          if (noRecordsToLock) {
            returnMessage = PoliceConstant.NO_RECORDS_TO_LOCK;
          } else {
            returnMessage = PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
          }

        }
      } else {
        if (!(applicationIds == null || applicationIds.isEmpty())) {
          for (Long appId : applicationIds) {
            if (appId > 0) {
              ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(appId, con);
              message = message + applicationVO.getReferenceNo() + " ";
            }
          }
        }
        returnMessage = PoliceConstant.ONE_RECORD_IS_ALREADY_LOCKED;
      }

      map.put("MESSAGE", returnMessage);
      map.put("LOCKED_USER", lockedOtherUserId);
      map.put("LOCKED_MESSAGE", message);

    } catch (BusinessException e) {
      e.printStackTrace();
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return map;
  }

  public String checkAndUnLockRecord(long applicationId, int lockedUserId, long locationId, int userRole) {
    Connection con = null;
    boolean hasgainedLock = false;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      hasgainedLock = checkIfUserHasLock(applicationId, lockedUserId, locationId, userRole, con);
      LOGGER.info("hasgainedLock :" + hasgainedLock);
      if (hasgainedLock) {
        String status =
            ReviewApplicationExternalDAO.getInstance().unlockCooRecord(applicationId, userRole, locationId, con);
        LOGGER.info("status :: :" + status);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          con.commit();
          return PoliceConstant.SUCCESS;
        } else {
          con.rollback();
        }
      } else {
        return PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
      }

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return PoliceConstant.ERROR;
  }


  private boolean checkIfUserHasLock(long applicationId, int lockedUserId, long locationId, int userRole, Connection con) {
    boolean hasGainedLock = false;
    if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
      List<AddressVO> addressVOs =
          AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,
              PoliceEnumConstant.AddressType.AC.toString(), con);
      if (!(addressVOs == null || addressVOs.isEmpty())) {
        for (AddressVO addressVO : addressVOs) {
          if (addressVO.getPoliceRecordLockStatus() > 0 && addressVO.getPoliceRecordLockStatus() == lockedUserId) {
            hasGainedLock = true;
          }
        }
      } else {
        hasGainedLock = false;
      }
    } else if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
      List<AddressVO> addressVOs =
          AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationId,
              PoliceEnumConstant.AddressType.AC.toString(), locationId, con);
      if (!(addressVOs == null || addressVOs.isEmpty())) {
        for (AddressVO addressVO : addressVOs) {
          if (addressVO.getPoliceRecordLockStatus() > 0 && addressVO.getPoliceRecordLockStatus() == lockedUserId) {
            hasGainedLock = true;
          }
        }
      }
    } else {
      ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
      if (!(applicationVO == null)) {
        PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.fromCode(userRole);
        switch (userDepartmentEnum) {
          case CID:
            if (applicationVO.getCidRecordLockStatus() > 0 && applicationVO.getCidRecordLockStatus() == lockedUserId) {
              hasGainedLock = true;
            }
            break;
          case TID:
            if (applicationVO.getTidRecordLockStatus() > 0 && applicationVO.getTidRecordLockStatus() == lockedUserId) {
              hasGainedLock = true;
            }
            break;
          case SIS:
            if (applicationVO.getSisRecordLockStatus() > 0 && applicationVO.getSisRecordLockStatus() == lockedUserId) {
              hasGainedLock = true;
            }
            break;
          case NIC:
            if (applicationVO.getNicRecordLockStatus() > 0 && applicationVO.getNicRecordLockStatus() == lockedUserId) {
              hasGainedLock = true;
            }
            break;
          case IMI:
            if (applicationVO.getImiRecordLockStatus() > 0 && applicationVO.getImiRecordLockStatus() == lockedUserId) {
              hasGainedLock = true;
            }
            break;
          default:
            LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
            break;
        }
      }
    }
    return hasGainedLock;
  }

  public List<AddressVO> getAddressListByUserRole(long applicationId, int userRole, long locationId, Connection conn) {
    boolean hasCreated = false;
    List<AddressVO> addressVOs = null;
    try {
      if (conn == null) {
        hasCreated = true;
        conn = DatabaseManager.getPOLDBConnection();
        conn.setAutoCommit(false);
      }

      if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
        addressVOs =
            AddressDAO.getInstance().getPendingAddressListByTypeAndApplicationId(applicationId,
                PoliceEnumConstant.AddressType.AC.toString(), conn);

      } else if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
        addressVOs =
            AddressDAO.getInstance().getPendingAddressListByTypeLocationAndApplicationId(applicationId,
                PoliceEnumConstant.AddressType.AC.toString(), locationId, conn);
      }

      if (!(addressVOs == null)) {
        for (AddressVO addressVO : addressVOs) {
          if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
            // check and add if an active temp address is avalable;
            AddressTempVO addressTempVO =
                AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), conn);
            addressVO.setAddressTempVO(addressTempVO);
          }
          addressVO.constructDateStrings();
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
      if (hasCreated) {
        DatabaseManager.close(conn);
      }
    }
    return addressVOs;
  }

  public String updateApplicationReviewApprovalStatus(String approvalStatus, long locationId, CommentsVO commentsVO) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      // check if the current user has the record lock
      boolean hasGainedLock =
          checkIfUserHasLock(commentsVO.getApplicationId(), commentsVO.getCreatedUserId(), locationId,
              commentsVO.getCreatedUserRole(), con);
      LOGGER.info("update app review approval hasGainedLock" + hasGainedLock);

      PoliceEnumConstant.UserDepartment userRoleEnum =
          PoliceEnumConstant.UserDepartment.fromCode(commentsVO.getCreatedUserRole());

      if (hasGainedLock) {
        // first update the application status;
        String status =
            ReviewApplicationExternalDAO.getInstance().updateApplicationReviewApprovalStatus(
                commentsVO.getApplicationId(), commentsVO.getAddressId(), approvalStatus, locationId,
                commentsVO.getCreatedUserRole(), con);
        LOGGER.info("hasGainedLock  update app review approval updated status" + status);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          // insert the comment
          long commentId = CommentDAO.getInstance().addComments(con, commentsVO);
          LOGGER.info("update app review approval comment added status" + commentId);
          if (commentId > 0) {
            if (commentsVO.getCreatedUserRole() == PoliceEnumConstant.UserDepartment.POL.getCode()
                || commentsVO.getCreatedUserRole() == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
              boolean hasAllApproved = true;
              boolean hasAtLeastOneRejected = false;
              List<AddressVO> addressVOs =
                  AddressDAO.getInstance().getAddressListByTypeAndApplicationId(commentsVO.getApplicationId(),
                      PoliceEnumConstant.AddressType.AC.toString(), con);
              if (!(addressVOs == null || addressVOs.isEmpty())) {
                for (AddressVO addressVO : addressVOs) {
                  if (addressVO.getPoliceAreaId() == locationId) {
                    status =
                        ReviewApplicationExternalDAO.getInstance().updateAddressClearanceDate(addressVO.getAddressId(),
                            commentsVO.getCreatedUserId(), userRoleEnum.toString(), con);
                    LOGGER.info("ADDRESS CLEARANCE DATE UPDATED STATUS " + status);
                  }

                  if (StringUtils.isEmpty(addressVO.getPoliceStatus())
                      || addressVO.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.PN.toString())
                      || addressVO.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.TU.toString())) {
                    hasAllApproved = false;
                  } else if (addressVO.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
                    hasAtLeastOneRejected = true;
                  }
                }
              }

              LOGGER.info("update app review approval hasAllApproved " + hasAllApproved);
              LOGGER.info("update app review approval hasAtLeastOneRejected " + hasAtLeastOneRejected);


              if (hasAllApproved) {
                approvalStatus = PoliceEnumConstant.ApprovalStatus.AP.toString();
                if (hasAtLeastOneRejected) {
                  approvalStatus = PoliceEnumConstant.ApprovalStatus.RJ.toString();
                }
                // update the final police status of aspplicatiojn
                status =
                    ReviewApplicationExternalDAO.getInstance().updateApplicationReviewApprovalStatusPolice(
                        commentsVO.getApplicationId(), approvalStatus, con);
                LOGGER.info("update app review approval status " + status);
              }
            } else {
              // update the updated date for application
              status =
                  ReviewApplicationExternalDAO.getInstance().updateApplicationClearanceDate(
                      commentsVO.getApplicationId(), commentsVO.getCreatedUserId(), userRoleEnum.toString(), con);
              LOGGER.info("APPLICATION CLEARANCE DATE UPDATED STATUS " + status);
            }

            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {

              int userRole = commentsVO.getCreatedUserRole();
              PoliceEnumConstant.UserDepartment department = PoliceEnumConstant.UserDepartment.fromCode(userRole);
              PoliceEnumConstant.ApprovalStatus appStatus = PoliceEnumConstant.ApprovalStatus.fromCode(approvalStatus);
              if (!(department == null || appStatus == null)) {
                String comment =
                    "Application external clearance status was changed to " + appStatus.getDisplayName() + " by "
                        + department.getDisplayName();
                ChangeAuditVO changeAuditVO =
                    new ChangeAuditVO(commentsVO.getApplicationId(), commentsVO.getCreatedUserId(),
                        commentsVO.getCreatedUserName(), comment);
                status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
              }
              LOGGER.info("Application Change audit added " + status);
              // unlock the record
              status =
                  ReviewApplicationExternalDAO.getInstance().unlockCooRecord(commentsVO.getApplicationId(),
                      commentsVO.getCreatedUserRole(), locationId, con);
              con.commit();
              return PoliceConstant.SUCCESS;
            } else {
              con.rollback();
              return PoliceConstant.ERROR;
            }
          }
        }
      } else {
        return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
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
    return PoliceConstant.ERROR;
  }

  public String unlockCooRecord(long applicationId, int userRole, long locationId) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      String status =
          ReviewApplicationExternalDAO.getInstance().unlockCooRecord(applicationId, userRole, locationId, con);
      LOGGER.info("status :: :" + status);
      if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
        con.commit();
        return PoliceConstant.SUCCESS;
      } else {
        con.rollback();
      }

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return PoliceConstant.ERROR;
  }

  public AddressVO getAddressByTypeApplicationLockedUser(long applicationId, int userId, long locationId) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      return AddressDAO.getInstance().getAddressByTypeApplicationLockedUser(applicationId, userId, locationId, con);

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


  public Map<String, Object> checkAndLockAddressRecord(long addressId, long applicationId, int lockedUserId,
      int userRole, long locationId) {
    Connection con = null;
    boolean hasgainedLock = true;
    boolean hasAnyLockedByCurrentUser = false;
    boolean noRecordsToLock = false;

    Map<String, Object> map = new HashMap<String, Object>();
    int lockedOtherUserId = 0;
    String returnMessage = PoliceConstant.ERROR;

    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      List<AddressVO> addressVOs =
          AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,
              PoliceEnumConstant.AddressType.AC.toString(), con);
      LOGGER.info("addressVOs PHQ " + addressVOs);
      if (!(addressVOs == null || addressVOs.isEmpty())) {
        for (AddressVO addressVO : addressVOs) {
          if (addressVO.getPoliceRecordLockStatus() > 0 && addressVO.getPoliceRecordLockStatus() != lockedUserId) {
            lockedOtherUserId = addressVO.getPoliceRecordLockStatus();
            hasgainedLock = false;
          }
        }
      } else {
        hasgainedLock = false;
        noRecordsToLock = true;
      }

      long count =
          ReviewApplicationExternalDAO.getInstance().getOtherLockedAddressListByCurrentUser(lockedUserId, addressId,
              con);
      LOGGER.info("count fsdfsdf:" + count);
      if (count > 0) {
        hasAnyLockedByCurrentUser = true;
      }

      if (!(hasAnyLockedByCurrentUser)) {
        if (hasgainedLock) {
          String status = ReviewApplicationExternalDAO.getInstance().lockAddressRecord(addressId, lockedUserId, con);
          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            con.commit();
            returnMessage = PoliceConstant.SUCCESS;
          } else {
            con.rollback();
          }
        } else {
          if (noRecordsToLock) {
            returnMessage = PoliceConstant.NO_RECORDS_TO_LOCK;
          } else {
            returnMessage = PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
          }
        }
      } else {
        returnMessage = PoliceConstant.ONE_RECORD_IS_ALREADY_LOCKED;
      }
      map.put("MESSAGE", returnMessage);
      map.put("LOCKED_USER", lockedOtherUserId);
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


  public String checkAndUnLockAddressRecord(long applicationId, long addressId, int lockedUserId, long locationId,
      int userRole) {
    Connection con = null;
    boolean hasgainedLock = false;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      hasgainedLock = checkIfUserHasLock(applicationId, lockedUserId, locationId, userRole, con);
      LOGGER.info("hasgainedLock :" + hasgainedLock);
      if (hasgainedLock) {
        String status = ReviewApplicationExternalDAO.getInstance().unlockAddressRecord(addressId, con);
        LOGGER.info("status :: :" + status);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          con.commit();
          return PoliceConstant.SUCCESS;
        } else {
          con.rollback();
        }
      } else {
        return PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
      }

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return PoliceConstant.ERROR;
  }


  public List<PoliceAreaVO> listAllPoliceAreas() {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      List<PoliceAreaVO> areaVOs = ApplicationDAO.getInstance().getPoliceAreaList(con);
      Collections.sort(areaVOs, new Comparator<PoliceAreaVO>() {
        @Override
        public int compare(final PoliceAreaVO lhs, PoliceAreaVO rhs) {
          return lhs.getPoliceArea().compareTo(rhs.getPoliceArea());
        }
      });
      return areaVOs;

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return null;
  }

  public AddressVO getAddressById(long addressId) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      return AddressDAO.getInstance().getAddressById(addressId, con);

    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return null;
  }


  public String saveAddressTemp(AddressTempVO addressTempVO) {
    Connection con = null;
    String res = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      addressTempVO.setActiveStatus(1);
      res = AddressDAO.getInstance().addAddressTemp(addressTempVO, con);
      if (StringUtils.equals(res, PoliceConstant.SUCCESS)) {
        con.commit();
      }
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return res;
  }


  public AddressTempVO getActiveAddressTempByIdAddressId(long addressId) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressTempVO addressTempVO = AddressDAO.getInstance().getActiveAddressTempByAddressId(addressId, con);
      if (!(addressTempVO == null)) {
        AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressId, con);
        addressTempVO.initializeStatusText(addressVO);
        return addressTempVO;
      }
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    return null;
  }

  public String updateAddressReviewApprovalStatus(long addressId, String approvalStatus, CommentsVO commentsVO) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressId, con);
      commentsVO.setApplicationId(addressVO.getApplicationId());
      commentsVO.setAddressId(addressId);
      if (!(addressVO == null)) {
        // check if the current user has the record lock
        // boolean
        // hasGainedLock=checkIfUserHasLock(addressVO.getApplicationId(),commentsVO.getCreatedUserId(),addressVO.getPoliceAreaId(),commentsVO.getCreatedUserRole(),con);
        // if(hasGainedLock){
        // first update the application status;
        String status =
            ReviewApplicationExternalDAO.getInstance().updateAddressClearenceStatusPolice(addressId, approvalStatus,
                con);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          // insert the comment
          PoliceEnumConstant.UserDepartment userRoleEnum =
              PoliceEnumConstant.UserDepartment.fromCode(commentsVO.getCreatedUserRole());
          status =
              ReviewApplicationExternalDAO.getInstance().updateAddressClearanceDate(addressId,
                  commentsVO.getCreatedUserId(), userRoleEnum.toString(), con);

          // insert the comment
          long commentId = CommentDAO.getInstance().addComments(con, commentsVO);

          AddressChangeAuditVO changeAuditVO =
              new AddressChangeAuditVO(addressId, commentsVO.getCreatedUserId(), commentsVO.getCreatedUserName(),
                  commentsVO.getComment());
          status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);

          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            // unlock the record
            if (commentsVO.getCreatedUserRole() == PoliceEnumConstant.UserDepartment.POL.getCode()
                || commentsVO.getCreatedUserRole() == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
              boolean hasAllApproved = true;
              boolean hasAtLeastOneRejected = false;
              List<AddressVO> addressVOs =
                  AddressDAO.getInstance().getAddressListByTypeAndApplicationId(commentsVO.getApplicationId(),
                      PoliceEnumConstant.AddressType.AC.toString(), con);

              if (!(addressVOs == null || addressVOs.isEmpty())) {
                for (AddressVO address : addressVOs) {
                  if (StringUtils.isEmpty(address.getPoliceStatus())
                      || address.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.PN.toString())
                      || address.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.TU.toString())) {
                    hasAllApproved = false;
                  } else if (address.getPoliceStatus().equals(PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
                    hasAtLeastOneRejected = true;
                  }
                }
              }

              if (StringUtils.equals(approvalStatus, PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
                hasAtLeastOneRejected = true;
              }


              if (hasAllApproved) {
                approvalStatus = PoliceEnumConstant.ApprovalStatus.AP.toString();
                if (hasAtLeastOneRejected) {
                  approvalStatus = PoliceEnumConstant.ApprovalStatus.RJ.toString();
                }
                // update the final police status of aspplicatiojn
                status =
                    ReviewApplicationExternalDAO.getInstance().updateApplicationReviewApprovalStatusPolice(
                        commentsVO.getApplicationId(), approvalStatus, con);
              }
            }


            status = ReviewApplicationExternalDAO.getInstance().unlockAddressRecord(addressId, con);
            con.commit();
            return PoliceConstant.SUCCESS;
          } else {
            con.rollback();
            return PoliceConstant.ERROR;
          }
        }
        // }else{
        // return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
        // }
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
    return PoliceConstant.ERROR;
  }

  public String updateAddressEditPolice(AddressTempVO addressTempVO, CommentsVO commentsVO) {
    LOGGER.info("updateAddressEditPolice" + addressTempVO);
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressTempVO.getAddressId(), con);
      if (!(addressVO == null)) {
        // check if the current user has the record lock
        boolean hasGainedLock =
            checkIfUserHasLock(addressVO.getApplicationId(), commentsVO.getCreatedUserId(),
                addressVO.getPoliceAreaId(), commentsVO.getCreatedUserRole(), con);
        LOGGER.info("address and police area hasGainedLock" + hasGainedLock);
        if (hasGainedLock) {
          // first update the application status;
          String status =
              ReviewApplicationExternalDAO.getInstance().updateAddressClearenceStatusPolice(addressVO.getAddressId(),
                  PoliceEnumConstant.ApprovalStatus.TU.toString(), con);
          LOGGER.info("address clearenc status updated status: " + status);
          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            status = AddressDAO.getInstance().addAddressTemp(addressTempVO, con);
            LOGGER.info("address temp inserted status :" + status);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
              // insert the comment by user
              AddressChangeAuditVO changeAuditVO =
                  new AddressChangeAuditVO(addressTempVO.getAddressId(), commentsVO.getCreatedUserId(),
                      commentsVO.getCreatedUserName(), commentsVO.getComment());

              PoliceEnumConstant.UserDepartment userRoleEnum =
                  PoliceEnumConstant.UserDepartment.fromCode(commentsVO.getCreatedUserRole());
              status =
                  ReviewApplicationExternalDAO.getInstance().updateAddressClearanceDate(addressTempVO.getAddressId(),
                      commentsVO.getCreatedUserId(), userRoleEnum.toString(), con);
              LOGGER.info("ADDRESS CLEARANCE DATE UPDATED STATUS " + status);

              // insert the comment
              long commentId = CommentDAO.getInstance().addComments(con, commentsVO);
              LOGGER.info("update app review approval comment added status" + commentId);

              status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
              LOGGER.info("address change audit added status :" + status);
              if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                // insert the comment by system
                String comment = getConstructedCommentAddressUpdate(addressTempVO, addressVO);
                if (StringUtils.isNotEmpty(comment)) {
                  changeAuditVO =
                      new AddressChangeAuditVO(addressTempVO.getAddressId(), commentsVO.getCreatedUserId(),
                          commentsVO.getCreatedUserName(), comment);
                  status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
                  LOGGER.info("address change audit added status :" + status);
                }

                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                  // unlock the record
                  status =
                      ReviewApplicationExternalDAO.getInstance().unlockAddressRecord(addressTempVO.getAddressId(), con);
                  con.commit();
                  return PoliceConstant.SUCCESS;
                } else {
                  con.rollback();
                  return PoliceConstant.ERROR;
                }
              }
            }
          }
        } else {
          return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
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
    return PoliceConstant.ERROR;
  }

  public String getEmailContentByEmailType(EmailType emailTypeEnum, AddressTempVO addressTempVO) {
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressTempVO.getAddressId(), con);
      if (!(addressVO == null)) {
        ApplicationVO applicationVO =
            ApplicationBusiness.getInstance().getApplicationByApplicationId(addressVO.getApplicationId());
        if (!(applicationVO == null)) {
          String emailContent = null;
          switch (emailTypeEnum) {
            case IA:
              // <p>Dear: {0},</p>\
              // <p>The police area that you provided: ''{1}'' for the address: ''{2}'' \
              // has been corrected as: ''{3}'' for your application with reference no: {4}. \
              // Please be informed that this will cause a delay in processing your
              // application.</p>\
              // <p>The Police Headquarters</p>
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
              String addressPeriodFromToText =
                  addressVO.getAddress() + " / from " + dateFormat.format(addressVO.getFromDate()) + " to "
                      + dateFormat.format(addressVO.getToDate());

              emailContent =
                  CommonUtil.getValueFromFile(PoliceConstant.MAIL_PROPERTY_FILE,
                      PoliceConstant.INCORRECT_ADDRESS_PERIOD_OF_STAY_MAIL_CONTENT);
              Object[] argumentsUp =
                  {applicationVO.getApplicantNameAsNic(), addressPeriodFromToText, applicationVO.getReferenceNo()

                  };
              return MessageFormat.format(emailContent, argumentsUp);
            case UP:
              // <p>Dear: {0},</p>\
              // <p>You have incorrectly stated your address/period as ''{1}'' for your application
              // with reference no. {2}. \
              // You are required to be present at the police Headquarters on <insert date and time>
              // to\
              // make the corrections to your application.Please be informed that this will cause a
              // delay\
              // in processing your application</p>.<p>The Police Headquarters</p>

              emailContent =
                  CommonUtil.getValueFromFile(PoliceConstant.MAIL_PROPERTY_FILE,
                      PoliceConstant.UPDATE_IN_POLICE_AREA_MAIL_CONTENT);

              Object[] arguments =
                  {applicationVO.getApplicantNameAsNic(), addressVO.getPoliceArea(), addressVO.getAddress(),
                      addressTempVO.getPoliceArea(), applicationVO.getReferenceNo()};

              return MessageFormat.format(emailContent, arguments);
            case NU:
              // // <p>Date: {0}</p>\
              // <p>My Number :{1} </p> \
              // <p>{2}</p> \
              // <p>Dear Sir/Madam,</p> \
              // <p><u><b>With regard to correcting the name that appears on the National Identity
              // Card bearing number {3}</b></u></p> \
              // <p>The name that appears on your application does not match with the one that
              // appears on your National Identity Card.</p> \
              // <p>You are hereby informed to correct your name by bringing the originals and the
              // photocopies of the following documents and by producing them to the Legal Division,
              // Department for Registration of Persons at C45, Keppetipola Mawatha, Colombo 05.
              // </p> \
              // <ol><li>National Identity Card (Original)</li><li>Birth Certificate (Original)
              // </li><li>Marriage Certificate (Original), if the name was altered after marriage
              // </li><li>This letter sent to you by the Clearance Branch</li></ol> \
              // <p>I wish to inform you that we are unable to issue a clearance certificate until
              // you resolve this discrepancy with regard to your name by producing the above
              // mentioned documents to the Legal Division, Department for Registration of Persons
              // .</p> \
              // <p>Officer in Charge <br />Clearance Branch</p>
              SimpleDateFormat dateFormatNu = new SimpleDateFormat("dd/MM/yyyy");

              emailContent =
                  CommonUtil.getValueFromFile(PoliceConstant.MAIL_PROPERTY_FILE,
                      PoliceConstant.UPDATE_IN_POLICE_AREA_MAIL_CONTENT);
              Object[] argumentsNu =
                  {dateFormatNu.format(Calendar.getInstance().getTime()), applicationVO.getReferenceNo(),
                      getConstructedAddress(applicationVO), applicationVO.getNic()

                  };
              return MessageFormat.format(emailContent, argumentsNu);
            default:
              break;
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
    return null;
  }

  private String getConstructedAddress(ApplicationVO applicationVO) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineOne())) {
      sb.append(applicationVO.getCertificatePostAddressLineOne()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineTwo())) {
      sb.append(applicationVO.getCertificatePostAddressLineTwo()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCity())) {
      sb.append(applicationVO.getCertificatePostAddressCity()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressState())) {
      sb.append(applicationVO.getCertificatePostAddressState()).append(", <br />");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressPostal())) {
      sb.append(applicationVO.getCertificatePostAddressPostal()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCountryName())) {
      sb.append(applicationVO.getCertificatePostAddressCountryName()).append(".<br /> ");
    }
    return sb.toString();
  }

  public String updateAddressEditPhq(AddressTempVO addressTempVO, CommentsVO commentsVO) {
    LOGGER.info("updateAddressEditPhq" + addressTempVO);
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressTempVO.getAddressId(), con);
      if (!(addressVO == null)) {
        // check if the current user has the record lock
        boolean hasGainedLock =
            checkIfUserHasLock(addressVO.getApplicationId(), commentsVO.getCreatedUserId(),
                addressVO.getPoliceAreaId(), commentsVO.getCreatedUserRole(), con);
        if (hasGainedLock) {
          // first update the application status and application;
          String status = ReviewApplicationExternalDAO.getInstance().updateAddressByPHQ(addressTempVO, con);

          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {

            status = AddressDAO.getInstance().discardAddressTempByAddressId(addressTempVO.getAddressId(), con);

            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
              // insert the comment by user
              AddressChangeAuditVO changeAuditVO =
                  new AddressChangeAuditVO(addressTempVO.getAddressId(), commentsVO.getCreatedUserId(),
                      commentsVO.getCreatedUserName(), commentsVO.getComment());

              status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);

              if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                // insert the comment by system
                String comment = getConstructedCommentAddressUpdate(addressTempVO, addressVO);
                if (StringUtils.isNotEmpty(comment)) {
                  changeAuditVO =
                      new AddressChangeAuditVO(addressTempVO.getAddressId(), commentsVO.getCreatedUserId(),
                          commentsVO.getCreatedUserName(), comment);

                  status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
                }

                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                  // unlock the record
//                  status =
//                      ReviewApplicationExternalDAO.getInstance().unlockAddressRecord(addressTempVO.getAddressId(), con);
                	status =
                            ReviewApplicationExternalDAO.getInstance().unlockCooRecord(addressVO.getApplicationId(), commentsVO.getCreatedUserRole(), addressVO.getPoliceAreaId(), con);
                	
                  // if(addressTempVO.getSendEmailByPhq()==1){
                  // try {
                  // //send emails to the user
                  // ApplicationVO
                  // applicationVO=ApplicationDAO.getInstance().getApplicationById(addressVO.getAddressId(),
                  // con);
                  // String
                  // emailStatus=EmailNotificationBusiness.getInstance().sendAddressModificationNotificationMail(addressTempVO,applicationVO);
                  // LOGGER.info("emailStatus status :" + emailStatus);
                  //
                  // String
                  // smsStatus=SMSNotificationBusiness.getInstance().sendAddressModificationNotificationSms(applicationVO);
                  // LOGGER.info("smsStatus status :" + smsStatus);
                  //
                  // } catch (Exception e) {
                  // e.printStackTrace();
                  // }
                  // }
                  con.commit();
                  return PoliceConstant.SUCCESS;
                } else {
                  con.rollback();
                  return PoliceConstant.ERROR;
                }
              }
            }
          }

        } else {
          return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
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
    return PoliceConstant.ERROR;
  }


  public String sendEmailAddressEditPhq(AddressTempVO addressTempVO, CommentsVO commentsVO) {
    LOGGER.info("sendEmailAddressEditPhq" + addressTempVO);
    Connection con = null;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      AddressVO addressVO = AddressDAO.getInstance().getAddressById(addressTempVO.getAddressId(), con);
      if (!(addressVO == null)) {
        // check if the current user has the record lock
        boolean hasGainedLock =
            checkIfUserHasLock(addressVO.getApplicationId(), commentsVO.getCreatedUserId(),
                addressVO.getPoliceAreaId(), commentsVO.getCreatedUserRole(), con);
        if (hasGainedLock) {
          String status = PoliceConstant.ERROR;
          // insert the comment by system
          String comment = "Email was sent to the applicant";
          if (StringUtils.isNotEmpty(comment)) {
            AddressChangeAuditVO changeAuditVO =
                new AddressChangeAuditVO(addressTempVO.getAddressId(), commentsVO.getCreatedUserId(),
                    commentsVO.getCreatedUserName(), comment);
            status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
          }

          if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            // unlock the record
            status = ReviewApplicationExternalDAO.getInstance().unlockAddressRecord(addressTempVO.getAddressId(), con);

            // if(addressTempVO.getSendEmailByPhq()==1){
            try {
              // send emails to the user
              ApplicationVO applicationVO =
                  ApplicationDAO.getInstance().getApplicationById(addressVO.getAddressId(), con);
              String emailStatus =
                  EmailNotificationBusiness.getInstance().sendAddressModificationNotificationMail(addressTempVO,
                      applicationVO);
              LOGGER.info("emailStatus status :" + emailStatus);

              String smsStatus =
                  SMSNotificationBusiness.getInstance().sendAddressModificationNotificationSms(applicationVO);
              LOGGER.info("smsStatus status :" + smsStatus);

            } catch (Exception e) {
              e.printStackTrace();
            }
            // }
            con.commit();
            return PoliceConstant.SUCCESS;
          } else {
            con.rollback();
            return PoliceConstant.ERROR;
          }
        } else {
          return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
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
    return PoliceConstant.ERROR;
  }

  private String getConstructedCommentAddressUpdate(AddressTempVO addressTempVO, AddressVO addressVO) {
    StringBuilder builder = new StringBuilder("");
    if (!(addressTempVO.getPoliceAreaId() == addressVO.getPoliceAreaId())) {
      builder.append("The police area was changed from ").append(addressVO.getPoliceArea()).append(" to ")
          .append(addressTempVO.getPoliceArea()).append(". ");
    }
    if (!(StringUtils.equals(addressTempVO.getAddress(), addressVO.getAddress()))) {
      builder.append("The address was changed from ").append(addressVO.getAddress()).append(" to ")
          .append(addressTempVO.getAddress()).append(". ");
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    String addressPeriodFromToText =
        dateFormat.format(addressVO.getFromDate()) + " - " + dateFormat.format(addressVO.getToDate());
    String addressPeriodFromToTextTemp =
        dateFormat.format(addressTempVO.getFromDate()) + " - " + dateFormat.format(addressTempVO.getToDate());

    if (!(addressTempVO.getFromDate().equals(addressVO.getFromDate()) || addressTempVO.getToDate().equals(
        addressVO.getToDate()))) {
      builder.append("The stay period was changed from ").append(addressPeriodFromToTextTemp).append(" to ")
          .append(addressPeriodFromToText).append(". ");
    }
    return builder.toString();
  }

  public List<String> findDhaCommentsByApplicationId(long applicationId) {
    return CommentDAO.getInstance().findDhaCommentsByApplicationId(applicationId);
  }

public String getIsAspUser() {
	return isAspUser;
}

public void setIsAspUser(String isAspUser) {
	this.isAspUser = isAspUser;
}
}
