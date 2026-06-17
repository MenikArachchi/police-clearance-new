package lk.icta.police.business;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationClearenceStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.PaymentStatus;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.TransactionDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.DateUtil;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditApplicationBusiness {

  private static final Logger LOGGER = Logger.getLogger(EditApplicationBusiness.class);
  private static EditApplicationBusiness instance = null;
  private static String datePattern = "dd/MM/yyyy";

  /**
   * Singleton Implementation
   * 
   */
  public static EditApplicationBusiness getInstance() {
    synchronized (EditApplicationBusiness.class) {
      if (instance == null) {
        instance = new EditApplicationBusiness();
      }
      return instance;
    }
  }

  private EditApplicationBusiness() {

  }

  public String editApplicationFromVerification(ApplicationVO applicationVO, UserVO userFromSession) {
    Connection con = null;
    String returnString = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      ApplicationVO applicationVOFromDb =
          ApplicationDAO.getInstance().getApplicationById(applicationVO.getApplicationId(), con);
      String comment = getConstructedCommentForApplicationUpdate(applicationVO, applicationVOFromDb);
      if (StringUtils.isNotEmpty(comment)) {
        returnString = ApplicationDAO.getInstance().editApplicationFromVerification(con, applicationVO);
        if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
          ChangeAuditVO changeAuditVO = new ChangeAuditVO();
          changeAuditVO.setApplicationId(applicationVO.getApplicationId());
          changeAuditVO.setComment(comment);
          changeAuditVO.setUpdatedUserDateTime(Calendar.getInstance().getTime());
          changeAuditVO.setUpdatedUserName(userFromSession.getFullName());
          changeAuditVO.setUpdatedUserId(userFromSession.getId());
          returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
        }

        if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
          con.commit();
        } else {
          con.rollback();
        }
      } else {
        returnString = "NO_CHANGES_TO_UPDATE";
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
    return returnString;
  }

  public String modifyAddressesOfApplication(List<AddressVO> certificateAddressList, long applicationId, int userId,
      String userName) {
    Connection con = null;
    String returnString = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      String status =
          checkAndUpdateEditedAddressList(certificateAddressList, applicationId, userId, userName, con, false);

      if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
        returnString = PoliceConstant.SUCCESS;
        con.commit();
      } else if (StringUtils.equals(PoliceConstant.NO_CHANGES_TO_UPDATE, status)) {
        returnString = PoliceConstant.NO_CHANGES_TO_UPDATE;
      } else {
        returnString = PoliceConstant.ERROR;
        con.rollback();
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
    return returnString;
  }

  private String checkAndUpdateEditedAddressList(List<AddressVO> certificateAddressList, long applicationId,
      int userId, String userName, Connection con, boolean isMarkingPending) throws ParseException {
    // first separate the new addresses and the old addresses
    List<AddressVO> newlyAddedAddressList = new ArrayList<AddressVO>();
    List<AddressVO> oldModifiedAddressList = new ArrayList<AddressVO>();
    SimpleDateFormat format = new SimpleDateFormat(datePattern);
    for (AddressVO addressVO : certificateAddressList) {
      if (!(addressVO == null || StringUtils.isEmpty(addressVO.getAddress()))) {
        if (addressVO.getAddressId() <= 0) {
          // this is a new address
          addressVO.setFromDate(DateUtil.convertStringToDate(addressVO.getFromDateStr(), datePattern));
          addressVO.setToDate(DateUtil.convertStringToDate(addressVO.getToDateStr(), datePattern));
          newlyAddedAddressList.add(addressVO);
          StringBuilder comment =
              new StringBuilder("The address with values; address:").append(addressVO.getAddress())
                  .append(", police area:").append(addressVO.getPoliceArea()).append(", from date:")
                  .append(format.format(addressVO.getFromDate())).append(", to date:")
                  .append(format.format(addressVO.getToDate())).append(" was added to application!");
          addressVO.setModificationComment(comment.toString());
        } else {
          // this is an old one
          oldModifiedAddressList.add(addressVO);
        }
      }
    }

    String status = PoliceConstant.ERROR;

    // first do modifications/remove the old addresses
    List<AddressVO> removedOldAddedAddressList = new ArrayList<AddressVO>();
    List<AddressVO> modifiedOldAddedAddressList = new ArrayList<AddressVO>();
    List<AddressVO> currentAddressListFromDb =
        AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,
            PoliceEnumConstant.AddressType.AC.toString(), con);
    for (AddressVO addressVOFromDb : currentAddressListFromDb) {
      boolean hasRemoved = true;
      StringBuilder modificationComment = new StringBuilder("The address was modified; ");
      for (AddressVO addressVOModified : oldModifiedAddressList) {
        addressVOModified.setFromDate(DateUtil.convertStringToDate(addressVOModified.getFromDateStr(), datePattern));
        addressVOModified.setToDate(DateUtil.convertStringToDate(addressVOModified.getToDateStr(), datePattern));
        if (addressVOFromDb.getAddressId() == addressVOModified.getAddressId()) {
          hasRemoved = false;
          boolean hasModified = false;
          // check if the address has been modified
          if (!(StringUtils.equals(addressVOFromDb.getAddress(), addressVOModified.getAddress()))) {
            modificationComment.append(" address was changed from ").append(addressVOFromDb.getAddress())
                .append(" to ").append(addressVOModified.getAddress()).append(". ");
            hasModified = true;
          }
          if (!(addressVOFromDb.getPoliceAreaId() == addressVOModified.getPoliceAreaId())) {
            modificationComment.append(" police area was changed from ").append(addressVOFromDb.getPoliceArea())
                .append(" to ").append(addressVOModified.getPoliceArea()).append(". ");
            hasModified = true;
          }
          if (!((addressVOFromDb.getFromDate().compareTo(addressVOModified.getFromDate())) == 0)) {
            modificationComment.append(" from date area was changed from ")
                .append(format.format(addressVOFromDb.getFromDate())).append(" to ")
                .append(format.format(addressVOModified.getFromDate())).append(". ");
            hasModified = true;
          }
          if (!((addressVOFromDb.getToDate().compareTo(addressVOModified.getToDate())) == 0)) {
            modificationComment.append(" to date area was changed from ")
                .append(format.format(addressVOFromDb.getToDate())).append(" to ")
                .append(format.format(addressVOModified.getToDate())).append(". ");
            hasModified = true;
          }

          if (hasModified) {
            addressVOModified.setModificationComment(modificationComment.toString());
            modifiedOldAddedAddressList.add(addressVOModified);
          }
        }
      }
      if (hasRemoved) {
        StringBuilder comment =
            new StringBuilder("The address with values; address:").append(addressVOFromDb.getAddress())
                .append(", police area:").append(addressVOFromDb.getPoliceArea()).append(", from date:")
                .append(format.format(addressVOFromDb.getFromDate())).append(", to date:")
                .append(format.format(addressVOFromDb.getToDate())).append(" was removed from application!");
        addressVOFromDb.setModificationComment(comment.toString());
        removedOldAddedAddressList.add(addressVOFromDb);
      }
    }


    boolean anyModificationsAvailable = false;

    // check for modified addresses and update database
    if (!(modifiedOldAddedAddressList == null || modifiedOldAddedAddressList.isEmpty())) {
      // there are modified addresses, update the database
      for (AddressVO addressVO : modifiedOldAddedAddressList) {
        status = AddressDAO.getInstance().updateAddressModifications(addressVO, userId, userName, con);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          AddressChangeAuditVO changeAuditVO =
              new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, addressVO.getModificationComment());
          status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
          if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
            break;
          }
        } else {
          break;
        }
      }
      anyModificationsAvailable = true;
    }


    // check for removed addresses and update database
    if (!(removedOldAddedAddressList == null || removedOldAddedAddressList.isEmpty())) {
      // there are removed addresses, update the database
      for (AddressVO addressVO : removedOldAddedAddressList) {
        status = AddressDAO.getInstance().removeAddressFromApplication(addressVO.getAddressId(), userId, userName, con);
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
          AddressChangeAuditVO changeAuditVO =
              new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, addressVO.getModificationComment());
          status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
          if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
            break;
          }
        } else {
          break;
        }
      }
      anyModificationsAvailable = true;
    }

    // add new addresses to database
    if (!(newlyAddedAddressList == null || newlyAddedAddressList.isEmpty())) {
      for (AddressVO addressVO : newlyAddedAddressList) {
        addressVO.setType(PoliceEnumConstant.AddressType.AC.toString());
        addressVO.setFromDate(DateUtil.convertStringToDate(addressVO.getFromDateStr(), datePattern));
        addressVO.setToDate(DateUtil.convertStringToDate(addressVO.getToDateStr(), datePattern));
        addressVO.setApplicationId(applicationId);
        if (isMarkingPending) {
          addressVO.setPoliceStatus(PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
        }
      }
      boolean hasAdded = AddressDAO.getInstance().addAddressList(con, newlyAddedAddressList);
      if (hasAdded) {
        for (AddressVO addressVO : newlyAddedAddressList) {
          AddressChangeAuditVO changeAuditVO =
              new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, addressVO.getModificationComment());
          status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
          if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
            break;
          }
        }
        status = PoliceConstant.SUCCESS;
      } else {
        status = PoliceConstant.ERROR;
      }
      anyModificationsAvailable = true;
    }

    if (!(anyModificationsAvailable)) {
      status = PoliceConstant.NO_CHANGES_TO_UPDATE;
    }

    return status;
  }


  public String updateCompleteApplication(ApplicationVO applicationVO, UserVO userFromSession,
      TransactionVO transactionVO, List<AddressVO> certificateAddressList) {
    Connection con = null;
    String returnString = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      ApplicationVO applicationVOFromDb =
          ApplicationDAO.getInstance().getApplicationById(applicationVO.getApplicationId(), con);
      TransactionVO transactionVOFromDb =
          TransactionDAO.getInstance().getTransaction(con, applicationVO.getTransactionId());
      String comment = getConstructedCommentForApplicationCompleteUpdate(applicationVO, applicationVOFromDb);
      boolean isOnlinePayment = checkIfOnlinePayment(transactionVOFromDb);
      if (!(isOnlinePayment)) {
        comment = getConstructedCommentForTransactionUpdate(transactionVO, transactionVOFromDb, comment);
      }
      if (StringUtils.isNotEmpty(comment)) {
        returnString = ApplicationDAO.getInstance().editCompleteApplication(con, applicationVO);
        if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
          if (!(StringUtils.equals(applicationVO.getDeliveryType(), applicationVOFromDb.getDeliveryType()))) {
            String deliveryType = applicationVO.getDeliveryType();;
            // modifying reference number
            String referenceNo = applicationVO.getReferenceNo();
            if (StringUtils.isNotEmpty(referenceNo)) {
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.FM.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "E";
              }
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.SB.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "B";
              }
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.NP.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "N";
              }
            }
            if (!(StringUtils.equals(referenceNo, applicationVOFromDb.getReferenceNo()))) {
              comment =
                  comment + "<ul><li>The application reference number was modified from :"
                      + applicationVOFromDb.getReferenceNo() + " to " + referenceNo + "</li></ul>";
              returnString =
                  ApplicationDAO.getInstance().updateApplicationReferenceNumber(con, referenceNo,
                      applicationVOFromDb.getApplicationId());
            }
          }
          if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
            returnString =
                checkAndUpdateEditedAddressList(certificateAddressList, applicationVO.getApplicationId(),
                    userFromSession.getId(), userFromSession.getUserName(), con, false);

            if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)
                || StringUtils.equals(returnString, PoliceConstant.NO_CHANGES_TO_UPDATE)) {

              if (!(isOnlinePayment)) {
                transactionVO.setTransactionId(transactionVOFromDb.getTransactionId());
                returnString = TransactionDAO.getInstance().updateManualTransaction(con, transactionVO);
              }

              // if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
              ChangeAuditVO changeAuditVO = new ChangeAuditVO();
              changeAuditVO.setApplicationId(applicationVO.getApplicationId());
              changeAuditVO.setComment(comment);
              changeAuditVO.setUpdatedUserDateTime(Calendar.getInstance().getTime());
              changeAuditVO.setUpdatedUserName(userFromSession.getFullName());
              changeAuditVO.setUpdatedUserId(userFromSession.getId());
              returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
              // }
            }
          }
        }
        if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
          con.commit();
        } else {
          con.rollback();
        }
      } else {
        returnString =
            checkAndUpdateEditedAddressList(certificateAddressList, applicationVO.getApplicationId(),
                userFromSession.getId(), userFromSession.getUserName(), con, false);
        if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
          con.commit();
        } else {
          con.rollback();
        }
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
    return returnString;
  }


  private boolean checkIfOnlinePayment(TransactionVO transactionVO) {
    if (!(transactionVO == null)) {
      if (org.apache.commons.lang3.StringUtils.isNotEmpty(transactionVO.getPaymentStatus())) {
        if (org.apache.commons.lang3.StringUtils
            .equals(transactionVO.getPaymentStatus(), PaymentStatus.OCNF.toString())) {
          return true;
        }
      }
    }
    return false;
  }

  public String updateCompleteApplicationAfterVerification(ApplicationVO applicationVO, UserVO userFromSession,
      TransactionVO transactionVO, List<AddressVO> certificateAddressList) {
    Connection con = null;
    String returnString = PoliceConstant.ERROR;
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);

      ApplicationVO applicationVOFromDb =
          ApplicationDAO.getInstance().getApplicationById(applicationVO.getApplicationId(), con);

      TransactionVO transactionVOFromDb =
          TransactionDAO.getInstance().getTransaction(con, applicationVO.getTransactionId());

      String comment = getConstructedCommentForApplicationCompleteUpdate(applicationVO, applicationVOFromDb);

      boolean isOnlinePayment = checkIfOnlinePayment(transactionVOFromDb);
      if (!(isOnlinePayment)) {
        comment = getConstructedCommentForTransactionUpdate(transactionVO, transactionVOFromDb, comment);
      }

      if (StringUtils.isNotEmpty(comment)) {

        returnString = ApplicationDAO.getInstance().editCompleteApplication(con, applicationVO);

        if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
          if (!(StringUtils.equals(applicationVO.getDeliveryType(), applicationVOFromDb.getDeliveryType()))) {
            String deliveryType = applicationVO.getDeliveryType();;
            // modifying reference number
            String referenceNo = applicationVO.getReferenceNo();
            if (StringUtils.isNotEmpty(referenceNo)) {
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.FM.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "E";
              }
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.SB.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "B";
              }
              if (deliveryType.equals(PoliceEnumConstant.DeliveryType.NP.toString())) {
                referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "N";
              }
            }
            if (!(StringUtils.equals(referenceNo, applicationVOFromDb.getReferenceNo()))) {
              comment =
                  comment + "<ul><li>The application reference number was modified from :"
                      + applicationVOFromDb.getReferenceNo() + " to " + referenceNo + "</li></ul>";
              returnString =
                  ApplicationDAO.getInstance().updateApplicationReferenceNumber(con, referenceNo,
                      applicationVOFromDb.getApplicationId());
            }
          }
          if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
            returnString =
                checkAndUpdateEditedAddressList(certificateAddressList, applicationVO.getApplicationId(),
                    userFromSession.getId(), userFromSession.getUserName(), con, true);
            if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)
                || StringUtils.equals(returnString, PoliceConstant.NO_CHANGES_TO_UPDATE)) {

              if (!(isOnlinePayment)) {
                transactionVO.setTransactionId(transactionVOFromDb.getTransactionId());
                returnString = TransactionDAO.getInstance().updateManualTransaction(con, transactionVO);
              }

              // if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
              ChangeAuditVO changeAuditVO = new ChangeAuditVO();
              changeAuditVO.setApplicationId(applicationVO.getApplicationId());
              changeAuditVO.setComment(comment);
              changeAuditVO.setUpdatedUserDateTime(Calendar.getInstance().getTime());
              changeAuditVO.setUpdatedUserName(userFromSession.getFullName());
              changeAuditVO.setUpdatedUserId(userFromSession.getId());
              returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
              // }
            }
          }
        }
        if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
          // REVERTING APPLICATION STATUS AS IT WAS JUST VERIFIED - RESENDING FOR CLEARANCE
          returnString = revertApplicationToVerificationStage(applicationVOFromDb, userFromSession, con);
          if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
            con.commit();
          } else {
            con.rollback();
          }
        } else {
          con.rollback();
        }
      } else {
        returnString =
            checkAndUpdateEditedAddressList(certificateAddressList, applicationVO.getApplicationId(),
                userFromSession.getId(), userFromSession.getUserName(), con, true);
        if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
          // REVERTING APPLICATION STATUS AS IT WAS JUST VERIFIED - RESENDING FOR CLEARANCE
          returnString = revertApplicationToVerificationStage(applicationVOFromDb, userFromSession, con);
          if (StringUtils.equals(PoliceConstant.SUCCESS, returnString)) {
            con.commit();
          } else {
            con.rollback();
          }
        } else {
          con.rollback();
        }
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
    return returnString;
  }

  private String revertApplicationToVerificationStage(ApplicationVO applicationVOFromDb, UserVO userFromSession,
      Connection con) {
    String returnString = PoliceConstant.ERROR;
    
    boolean dorevertNic=true;
	if(StringUtils.isEmpty(applicationVOFromDb.getNic()) && StringUtils.isEmpty(applicationVOFromDb.getNewNic())){
		dorevertNic=false;
	}

    if (!(StringUtils.equals(applicationVOFromDb.getApplicationClearanceStatus(),
        ApplicationClearenceStatus.GC.toString()) || StringUtils.equals(
        applicationVOFromDb.getApplicationClearanceStatus(), ApplicationClearenceStatus.EI.toString()))) {
    	
      returnString =
          ApplicationDAO.getInstance()
              .revertApplicationToVerificationStage(applicationVOFromDb.getApplicationId(), con,dorevertNic,applicationVOFromDb.getNicStatus());
    } else {
      returnString =
          ApplicationDAO.getInstance().revertApplicationToPendingClearanceStage(applicationVOFromDb.getApplicationId(),
              con,dorevertNic,applicationVOFromDb.getNicStatus());
    }


    if (StringUtils.equals(returnString, PoliceConstant.SUCCESS)) {
      String reasonToResend = "Application details has been updated after the approval";
      String mainComment = "Application was updated after approval, hence all the status were reverted back";

      ChangeAuditVO mainChangeAuditVO =
          new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
              userFromSession.getUserName(), mainComment);
      returnString = ApplicationDAO.getInstance().addChangeAudit(mainChangeAuditVO, con);

      reasonToResend = "Application details has been updated after the approval";

      PoliceEnumConstant.UserDepartment department = null;
      List<AddressVO> addressVOs =
          AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVOFromDb.getApplicationId(),
              PoliceEnumConstant.AddressType.AC.toString(), con);
      // check if resend is required for POLICE;
      for (AddressVO addressVO : addressVOs) {
//        if (!(StringUtils.isEmpty(addressVO.getPoliceStatus()) || StringUtils.equals(addressVO.getPoliceStatus(),
//            PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
    	  if (!(StringUtils.isEmpty(addressVO.getPoliceStatus()) )) {
          // RESND TO POL
          department = PoliceEnumConstant.UserDepartment.POL;

          returnString =
              AddressDAO.getInstance().updateAddressPoliceStatusByAddressId(addressVO.getAddressId(),
                  PoliceEnumConstant.ApprovalStatus.PN.toString(), con);

          String comment =
              "Application was updated after approval, hence the application was resent for clearance to police";

          ApplicationClearanceDate clearanceDate =
              new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), addressVO.getAddressId(),
                  department.toString(), Calendar.getInstance().getTime(), userFromSession.getId(),
                  PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);

          returnString = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(clearanceDate, con);

          AddressChangeAuditVO changeAuditVO =
              new AddressChangeAuditVO(addressVO.getAddressId(), userFromSession.getId(),
                  userFromSession.getUserName(), comment);
          returnString = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);

          // RESND TO PHQ
          department = PoliceEnumConstant.UserDepartment.PHQ;

          returnString =
              AddressDAO.getInstance().updateAddressPoliceStatusByAddressId(addressVO.getAddressId(),
                  PoliceEnumConstant.ApprovalStatus.PN.toString(), con);

          comment = "Application was updated after approval, hence the application was resent for clearance to PHQ";

          clearanceDate =
              new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), addressVO.getAddressId(),
                  department.toString(), Calendar.getInstance().getTime(), userFromSession.getId(),
                  PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);

          returnString = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(clearanceDate, con);

          changeAuditVO =
              new AddressChangeAuditVO(addressVO.getAddressId(), userFromSession.getId(),
                  userFromSession.getUserName(), comment);
          returnString = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);


        }
      }

      // check if resend is required for CID;
      if (!(StringUtils.isEmpty(applicationVOFromDb.getCidStatus()) || StringUtils.equals(
          applicationVOFromDb.getCidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
        department = PoliceEnumConstant.UserDepartment.CID;
        // not resent yet, resend the application
        ApplicationClearanceDate clearanceDate =
            new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), 0, department.toString(), Calendar
                .getInstance().getTime(), userFromSession.getId(),
                PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);
        returnString = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, con);
        String comment =
            "Application was updated after approval, hence the application was resent for clearance to department - "
                + department.getDisplayName();
        // insert the audit action
        ChangeAuditVO changeAuditVO =
            new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
                userFromSession.getUserName(), comment);
        returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
      }

      // check if resend is required for NIC;
      if (!(StringUtils.isEmpty(applicationVOFromDb.getNicStatus()) || StringUtils.equals(
          applicationVOFromDb.getNicStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
        department = PoliceEnumConstant.UserDepartment.NIC;
        // not resent yet, resend the application
        ApplicationClearanceDate clearanceDate =
            new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), 0, department.toString(), Calendar
                .getInstance().getTime(), userFromSession.getId(),
                PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);
        returnString = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, con);
        String comment =
            "Application was updated after approval, hence the application was resent for clearance to department - "
                + department.getDisplayName();
        // insert the audit action
        ChangeAuditVO changeAuditVO =
            new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
                userFromSession.getUserName(), comment);
        returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
      }

      // check if resend is required for SIS;
      if (!(StringUtils.isEmpty(applicationVOFromDb.getSisStatus()) || StringUtils.equals(
          applicationVOFromDb.getSisStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
        department = PoliceEnumConstant.UserDepartment.SIS;
        // not resent yet, resend the application
        ApplicationClearanceDate clearanceDate =
            new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), 0, department.toString(), Calendar
                .getInstance().getTime(), userFromSession.getId(),
                PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);
        returnString = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, con);
        String comment =
            "Application was updated after approval, hence the application was resent for clearance to department - "
                + department.getDisplayName();
        // insert the audit action
        ChangeAuditVO changeAuditVO =
            new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
                userFromSession.getUserName(), comment);
        returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
      }

      // check if resend is required for IMI;
      if (!(StringUtils.isEmpty(applicationVOFromDb.getImiStatus()) || StringUtils.equals(
          applicationVOFromDb.getImiStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
        department = PoliceEnumConstant.UserDepartment.IMI;
        // not resent yet, resend the application
        ApplicationClearanceDate clearanceDate =
            new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), 0, department.toString(), Calendar
                .getInstance().getTime(), userFromSession.getId(),
                PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);
        returnString = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, con);
        String comment =
            "Application was updated after approval, hence the application was resent for clearance to department - "
                + department.getDisplayName();
        // insert the audit action
        ChangeAuditVO changeAuditVO =
            new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
                userFromSession.getUserName(), comment);
        returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
      }

      // check if resend is required for TID;
      if (!(StringUtils.isEmpty(applicationVOFromDb.getTidStatus()) || StringUtils.equals(
          applicationVOFromDb.getTidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
        department = PoliceEnumConstant.UserDepartment.TID;
        // not resent yet, resend the application
        ApplicationClearanceDate clearanceDate =
            new ApplicationClearanceDate(applicationVOFromDb.getApplicationId(), 0, department.toString(), Calendar
                .getInstance().getTime(), userFromSession.getId(),
                PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), reasonToResend);
        returnString = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, con);
        String comment =
            "Application was updated after approval, hence the application was resent for clearance to department - "
                + department.getDisplayName();
        // insert the audit action
        ChangeAuditVO changeAuditVO =
            new ChangeAuditVO(applicationVOFromDb.getApplicationId(), userFromSession.getId(),
                userFromSession.getUserName(), comment);
        returnString = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
      }

    }
    return returnString;
  }


  private String getConstructedCommentForApplicationCompleteUpdate(ApplicationVO applicationVO,
      ApplicationVO applicationVOFromDb) {
    applicationVO.trimTextFields();
    applicationVOFromDb.trimTextFields();
    SimpleDateFormat format = new SimpleDateFormat(datePattern);
    boolean hasAnyModififed = false;
    StringBuilder comment = new StringBuilder("Application was updated, the values were changed as : - <ul>");

    boolean hasCitizenCommentAdded = false;
    if (!(applicationVO.getNationalityId() == applicationVOFromDb.getNationalityId())) {
      hasAnyModififed = true;
      comment.append("<li> Nationality from ").append(applicationVOFromDb.getNationality()).append(" to ")
          .append(applicationVO.getNationality()).append(".").append("</li>");
      if (applicationVOFromDb.getNationalityId() == 199) {
        if (!(applicationVO.getNationalityId() == 199)) {
          String citizenOfSriLanka = "NO";
          if (applicationVO.getCitizenOfSriLanka() == 1) {
            citizenOfSriLanka = "YES";
          } else {
            applicationVO.setCitizenshipObtainedDate(null);
          }
          hasCitizenCommentAdded = true;
          comment.append("<li> Were you a citizen of Sri Lanka added as :").append(citizenOfSriLanka).append("</li>");
        }
      }
    }

    if (!(hasCitizenCommentAdded)) {
      if (!(applicationVO.getCitizenOfSriLanka() == applicationVOFromDb.getCitizenOfSriLanka())) {
        if (!(applicationVO.getNationalityId() == applicationVOFromDb.getNationalityId())) {
          if (!(applicationVO.getNationalityId() == 199)) {
            hasAnyModififed = true;
            String citizenOfSriLanka = "NO";
            String citizenOfSriLankaDb = "NO";
            if (applicationVO.getCitizenOfSriLanka() == 1) {
              citizenOfSriLanka = "YES";
            } else {
              if ((applicationVOFromDb.getCitizenOfSriLanka() == 1)) {
                applicationVO.setCitizenshipObtainedDate(null);
              }
            }
            if (applicationVOFromDb.getCitizenOfSriLanka() == 1) {
              citizenOfSriLankaDb = "YES";
            }
            comment.append("<li> Were you a citizen of Sri Lanka from ").append(citizenOfSriLankaDb).append(" to ")
                .append(citizenOfSriLanka).append(".").append("</li>");
          }
        } else {
          hasAnyModififed = true;
          String citizenOfSriLanka = "NO";
          String citizenOfSriLankaDb = "NO";
          if (applicationVO.getCitizenOfSriLanka() == 1) {
            citizenOfSriLanka = "YES";
          } else {
            if ((applicationVOFromDb.getCitizenOfSriLanka() == 1)) {
              applicationVO.setCitizenshipObtainedDate(null);
            }
          }
          if (applicationVOFromDb.getCitizenOfSriLanka() == 1) {
            citizenOfSriLankaDb = "YES";
          }
          comment.append("<li> Were you a citizen of Sri Lanka from ").append(citizenOfSriLankaDb).append(" to ")
              .append(citizenOfSriLanka).append(".").append("</li>");
        }
      }
    }


    if (!(applicationVO.getCitizenshipObtainedDate() == null || applicationVOFromDb.getCitizenshipObtainedDate() == null)) {
      if (!(applicationVO.getCitizenshipObtainedDate().equals(applicationVOFromDb.getCitizenshipObtainedDate()))) {
        hasAnyModififed = true;
        comment.append("<li> Date citizenship obtained from another country ")
            .append(format.format(applicationVOFromDb.getCitizenshipObtainedDate())).append(" to ")
            .append(format.format(applicationVO.getCitizenshipObtainedDate())).append(".").append("</li>");
      }
    } else {
      if (applicationVO.getCitizenshipObtainedDate() == null) {
        if (!(applicationVOFromDb.getCitizenshipObtainedDate() == null)) {
          comment.append("<li> Date citizenship obtained from another country was removed: ").append(
              format.format(applicationVOFromDb.getCitizenshipObtainedDate()));
        }
      } else if (applicationVOFromDb.getCitizenshipObtainedDate() == null) {
        if (!(applicationVO.getCitizenshipObtainedDate() == null)) {
          comment.append("<li> Date citizenship obtained from another country was added: ").append(
              format.format(applicationVO.getCitizenshipObtainedDate()));
        }
      }
    }

    if (!(applicationVO.getDateOfBirth() == null || applicationVOFromDb.getDateOfBirth() == null)) {
      if (!(applicationVO.getDateOfBirth().equals(applicationVOFromDb.getDateOfBirth()))) {
        hasAnyModififed = true;
        comment.append("<li> Date Of Birth from ").append(format.format(applicationVOFromDb.getDateOfBirth()))
            .append(" to ").append(format.format(applicationVO.getDateOfBirth())).append(".").append("</li>");
        if (!(applicationVO.getAge() == applicationVOFromDb.getAge())) {
          comment.append("<li> Age from ").append(applicationVOFromDb.getAge()).append(" to ")
              .append(applicationVO.getAge()).append(".").append("</li>");
        }
      }
    } else {
      if (applicationVO.getDateOfBirth() == null) {
        if (!(applicationVOFromDb.getDateOfBirth() == null)) {
          hasAnyModififed = true;
          comment.append("<li> Date Of Birth was removed ").append(format.format(applicationVOFromDb.getDateOfBirth()))
              .append("</li>");
        }
      } else if (applicationVOFromDb.getDateOfBirth() == null) {
        if (!(applicationVO.getDateOfBirth() == null)) {
          hasAnyModififed = true;
          comment.append("<li> Date Of Birth was added ").append(format.format(applicationVOFromDb.getDateOfBirth()))
              .append("</li>");
        }
      }
    }

    if (!(StringUtils.equals(applicationVO.getSelectedNameOption(), applicationVOFromDb.getSelectedNameOption()))) {
      hasAnyModififed = true;
      comment.append("<li> Selected name option from ").append(applicationVOFromDb.getSelectedNameOption()).append(" to ")
              .append(applicationVO.getSelectedNameOption()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getNic(), applicationVOFromDb.getNic()))) {
      hasAnyModififed = true;
      comment.append("<li> NIC No from ").append(applicationVOFromDb.getNic()).append(" to ")
          .append(applicationVO.getNic()).append(".").append("</li>");
    }

      if (!(StringUtils.equals(applicationVO.getNewNic(), applicationVOFromDb.getNewNic()))) {
          hasAnyModififed = true;
          comment.append("<li>New NIC No from ").append(applicationVOFromDb.getNewNic()).append(" to ")
                  .append(applicationVO.getNewNic()).append(".").append("</li>");
      }

    if (!(StringUtils.equals(applicationVO.getPassport(), applicationVOFromDb.getPassport()))) {
      hasAnyModififed = true;
      comment.append("<li> Passport No from ").append(applicationVOFromDb.getPassport()).append(" to ")
          .append(applicationVO.getPassport()).append(".").append("</li>");
    }

    if (!(applicationVO.getCountryId() == applicationVOFromDb.getCountryId())) {
      hasAnyModififed = true;
      comment.append("<li> Country from ").append(applicationVOFromDb.getCountryName()).append(" to ")
          .append(applicationVO.getCountryName()).append(".").append("</li>");
    }

    if (!(StringUtils
        .equals(applicationVO.getHighCommisionReference(), applicationVOFromDb.getHighCommisionReference()))) {
      hasAnyModififed = true;
      comment.append("<li> High Commision Reference from ").append(applicationVOFromDb.getHighCommisionReference())
          .append(" to ").append(applicationVO.getHighCommisionReference()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getHighCommisionReferenceAddress(),
        applicationVOFromDb.getHighCommisionReferenceAddress()))) {
      hasAnyModififed = true;
      comment.append("<li> High Commision Reference Address from ")
          .append(applicationVOFromDb.getHighCommisionReferenceAddress()).append(" to ")
          .append(applicationVO.getHighCommisionReferenceAddress()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getApplicantNameAsNic(), applicationVOFromDb.getApplicantNameAsNic()))) {
      hasAnyModififed = true;
      comment.append("<li> Applicant Name as in NIC from ").append(applicationVOFromDb.getApplicantNameAsNic())
          .append(" to ").append(applicationVO.getApplicantNameAsNic()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getApplicantNameAsPassport(),
        applicationVOFromDb.getApplicantNameAsPassport()))) {
      hasAnyModififed = true;
      comment.append("<li> Applicant Name as in Passport from ")
          .append(applicationVOFromDb.getApplicantNameAsPassport()).append(" to ")
          .append(applicationVO.getApplicantNameAsPassport()).append(".").append("</li>");
    }

    if (!(applicationVO.getPassportIssueDate() == null || applicationVOFromDb.getPassportIssueDate() == null)) {
      if (!(applicationVO.getPassportIssueDate().equals(applicationVOFromDb.getPassportIssueDate()))) {
        hasAnyModififed = true;
        comment.append("<li> Passport Issue Date ").append(format.format(applicationVOFromDb.getPassportIssueDate()))
            .append(" to ").append(format.format(applicationVO.getPassportIssueDate())).append(".").append("</li>");
      }
    } else {
      if (applicationVO.getPassportIssueDate() == null) {
        if (!(applicationVOFromDb.getPassportIssueDate() == null)) {
          hasAnyModififed = true;
          comment.append("<li> Passport Issue Date has removed:- ")
              .append(format.format(applicationVOFromDb.getPassportIssueDate())).append("</li>");
        }
      } else if (applicationVOFromDb.getPassportIssueDate() == null) {
        if (!(applicationVO.getPassportIssueDate() == null)) {
          hasAnyModififed = true;
          comment.append("<li> Passport Issue Date has added:- ")
              .append(format.format(applicationVO.getPassportIssueDate())).append("</li>");
        }
      }
    }



    if (!(StringUtils.equals(applicationVO.getPresentAddressLocal(), applicationVOFromDb.getPresentAddressLocal()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getPresentAddressLocal())) {
        comment.append("<li> Present Address Local was removed: ").append(applicationVOFromDb.getPresentAddressLocal());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getPresentAddressLocal())) {
        comment.append("<li> Present Address Local was added: ").append(applicationVO.getPresentAddressLocal());
      } else {
        comment.append("<li> Present Address Local from ").append(applicationVOFromDb.getPresentAddressLocal())
            .append(" to ").append(applicationVO.getPresentAddressLocal()).append(".").append("</li>");
      }
    }

    if (!(StringUtils
        .equals(applicationVO.getPresentAddressOverseas(), applicationVOFromDb.getPresentAddressOverseas()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getPresentAddressOverseas())) {
        comment.append("<li> Present Address Overseas was removed: ").append(
            applicationVOFromDb.getPresentAddressOverseas());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getPresentAddressOverseas())) {
        comment.append("<li> Present Address Overseas was added: ").append(applicationVO.getPresentAddressOverseas());
      } else {
        comment.append("<li> Present Address Overseas from ").append(applicationVOFromDb.getPresentAddressOverseas())
            .append(" to ").append(applicationVO.getPresentAddressOverseas()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getSex(), applicationVOFromDb.getSex()))) {
      hasAnyModififed = true;
      String sex = "Male";
      String sexFromDb = "Male";
      if (StringUtils.equals(applicationVO.getSex(), "F")) {
        sex = "Female";
      }
      if (StringUtils.equals(applicationVOFromDb.getSex(), "F")) {
        sexFromDb = "Female";
      }
      comment.append("<li> Sex from ").append(sexFromDb).append(" to ").append(sex).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getApplicantStatus(), applicationVOFromDb.getApplicantStatus()))) {
      hasAnyModififed = true;
      String applicantStatus = applicationVO.getApplicantStatus();;
      String applicantStatusFromDb = applicationVOFromDb.getApplicantStatus();
      try {
        applicantStatus =
            PoliceEnumConstant.ApplicantStatus.fromCode(applicationVO.getApplicantStatus()).getDisplayName();
        applicantStatusFromDb =
            PoliceEnumConstant.ApplicantStatus.fromCode(applicationVOFromDb.getApplicantStatus()).getDisplayName();
      } catch (Exception e) {
        e.printStackTrace();
      }
      comment.append("<li> Applicant Status from ").append(applicantStatusFromDb).append(" to ")
          .append(applicantStatus).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getOccupation(), applicationVOFromDb.getOccupation()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getOccupation())) {
        comment.append("<li> Occupation was removed: ").append(applicationVOFromDb.getOccupation());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getOccupation())) {
        comment.append("<li> Occupation was added: ").append(applicationVO.getOccupation());
      } else {
        comment.append("<li> Occupation from ").append(applicationVOFromDb.getOccupation()).append(" to ")
            .append(applicationVO.getOccupation()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getPurpose(), applicationVOFromDb.getPurpose()))) {
      hasAnyModififed = true;
      String purpose = applicationVO.getPurpose();;
      String purposeFromDb = applicationVOFromDb.getPurpose();
      try {
        purpose = PoliceEnumConstant.ApplicationPurpose.fromCode(applicationVO.getPurpose()).getDisplayName();
        purposeFromDb =
            PoliceEnumConstant.ApplicationPurpose.fromCode(applicationVOFromDb.getPurpose()).getDisplayName();
      } catch (Exception e) {
        e.printStackTrace();
      }
      comment.append("<li> Purpose from ").append(purposeFromDb).append(" to ").append(purpose).append(".")
          .append("</li>");
    }

    if (!(applicationVO.getPreviousCertificateApply() == applicationVOFromDb.getPreviousCertificateApply())) {
      StringBuilder innerComment = new StringBuilder("");
      hasAnyModififed = true;
      String previousApply = "NO";
      String previousApplyFromDb = "NO";
      if ((applicationVO.getPreviousCertificateApply() == 1)) {
        previousApply = "YES";
        if ((applicationVOFromDb.getPreviousCertificateApply() == 0)) {
          if (applicationVO.getPreviousCertificateIssueStatus() == 1) {
            if (StringUtils.isEmpty(applicationVO.getPreviousCertificateReferenceNo())) {
              innerComment.append("<li> Reference No. of the last certificate was removed: ").append(
                  applicationVOFromDb.getPreviousCertificateReferenceNo());
            } else if (StringUtils.isEmpty(applicationVOFromDb.getPreviousCertificateReferenceNo())) {
              innerComment.append("<li> Reference No. of the last certificate was added: ").append(
                  applicationVO.getPreviousCertificateReferenceNo());
            } else {
              innerComment.append("<li> Reference No. of the last certificate from ")
                  .append(applicationVOFromDb.getPreviousCertificateReferenceNo()).append(" to ")
                  .append(applicationVO.getPreviousCertificateReferenceNo()).append(".").append("</li>");
            }

            if (StringUtils.isEmpty(applicationVO.getPreviousCertificateCountryName())) {
              innerComment.append("<li> Country of the last certificate was removed: ").append(
                  applicationVOFromDb.getPreviousCertificateCountryName());
            } else if (StringUtils.isEmpty(applicationVOFromDb.getPreviousCertificateCountryName())) {
              innerComment.append("<li> Country of the last certificate was added: ").append(
                  applicationVO.getPreviousCertificateCountryName());
            } else {
              if (applicationVO.getPreviousCertificateIssueStatus() == 1
                  && applicationVOFromDb.getPreviousCertificateIssueStatus() == 1) {
                innerComment.append("<li> Country of the last certificate from ")
                    .append(applicationVOFromDb.getPreviousCertificateCountryName()).append(" to ")
                    .append(applicationVO.getPreviousCertificateCountryName()).append(".").append("</li>");
              } else if (applicationVOFromDb.getPreviousCertificateIssueStatus() == 0) {
                innerComment.append("<li> Country of the last certificate was added: ").append(
                    applicationVO.getPreviousCertificateCountryName());
              }
            }

            if (!(applicationVOFromDb.getPreviousCertificateIssueDate() == null || applicationVO
                .getPreviousCertificateIssueDate() == null)) {
              if (!(applicationVOFromDb.getPreviousCertificateIssueDate().compareTo(
                  applicationVO.getPreviousCertificateIssueDate()) == 0)) {
                innerComment.append("<li> Date of Issue from ")
                    .append(format.format(applicationVOFromDb.getPreviousCertificateIssueDate())).append(" to ")
                    .append(format.format(applicationVO.getPreviousCertificateIssueDate())).append(".").append("</li>");
              }
            } else if (applicationVOFromDb.getPreviousCertificateIssueDate() == null) {
              if (!(applicationVO.getPreviousCertificateIssueDate() == null)) {
                innerComment.append("<li> Date of Issue added ").append(
                    format.format(applicationVO.getPreviousCertificateIssueDate()));
              }
            } else if (applicationVO.getPreviousCertificateIssueDate() == null) {
              if (!(applicationVOFromDb.getPreviousCertificateIssueDate() == null)) {
                innerComment.append("<li> Date of Issue removed ").append(
                    format.format(applicationVOFromDb.getPreviousCertificateIssueDate()));
              }
            }
          } else {
            applicationVO.setPreviousCertificateReferenceNo(null);
            applicationVO.setPreviousCertificateCountryName(null);
            applicationVO.setPreviousCertificateCountryId(0);
            applicationVO.setPreviousCertificateIssueDate(null);
          }
        }
      } else {
        if ((applicationVOFromDb.getPreviousCertificateApply() == 1)) {
          applicationVO.setPreviousCertificateReferenceNo(null);
          applicationVO.setPreviousCertificateCountryName(null);
          applicationVO.setPreviousCertificateCountryId(0);
          applicationVO.setPreviousCertificateIssueDate(null);
        }
      }
      if ((applicationVOFromDb.getPreviousCertificateApply() == 1)) {
        previousApplyFromDb = "YES";
      }
      comment.append("<li> Have you applied for a certificate previously  from ").append(previousApplyFromDb)
          .append(" to ").append(previousApply).append(".").append("</li>");

      if ((applicationVO.getPreviousCertificateApply() == 1)) {
        if ((applicationVOFromDb.getPreviousCertificateApply() == 0)) {
          String previousCertificateApply = "NO";
          String previousCertificateApplyDb = "NO";
          if (applicationVO.getPreviousCertificateIssueStatus() == 1) {
            previousCertificateApply = "YES";
          }
          if (applicationVOFromDb.getPreviousCertificateIssueStatus() == 1) {
            previousCertificateApplyDb = "YES";
          }

          if (!(StringUtils.equals(previousCertificateApplyDb, previousCertificateApply))) {
            comment.append("<li> If so, was a certificate issued to you from ").append(previousCertificateApplyDb)
                .append(" to ").append(previousCertificateApply).append(".").append("</li>");
          }
        }
      }
      comment.append(innerComment.toString());
    } else if (applicationVO.getPreviousCertificateApply() == 1) {
      StringBuilder innerComment = new StringBuilder("");
      if (!(applicationVO.getPreviousCertificateIssueStatus() == applicationVOFromDb
          .getPreviousCertificateIssueStatus())) {
        hasAnyModififed = true;
        String previousCertificateApply = "NO";
        String previousCertificateApplyDb = "NO";
        if (applicationVO.getPreviousCertificateIssueStatus() == 1) {
          previousCertificateApply = "YES";
        }
        if (applicationVOFromDb.getPreviousCertificateIssueStatus() == 1) {
          previousCertificateApplyDb = "YES";
        }
        if (!(StringUtils.equals(previousCertificateApplyDb, previousCertificateApply))) {
          comment.append("<li> If so, was a certificate issued to you from ").append(previousCertificateApplyDb)
              .append(" to ").append(previousCertificateApply).append(".").append("</li>");
        }
      }

      if (applicationVO.getPreviousCertificateIssueStatus() == 1) {
        if (!(StringUtils.equals(applicationVO.getPreviousCertificateReferenceNo(),
            applicationVOFromDb.getPreviousCertificateReferenceNo()))) {
          hasAnyModififed = true;
          if (StringUtils.isEmpty(applicationVO.getPreviousCertificateReferenceNo())) {
            innerComment.append("<li> Reference No. of the last certificate was removed: ").append(
                applicationVOFromDb.getPreviousCertificateReferenceNo());
          } else if (StringUtils.isEmpty(applicationVOFromDb.getPreviousCertificateReferenceNo())) {
            innerComment.append("<li> Reference No. of the last certificate was added: ").append(
                applicationVO.getPreviousCertificateReferenceNo());
          } else {
            innerComment.append("<li> Reference No. of the last certificate from ")
                .append(applicationVOFromDb.getPreviousCertificateReferenceNo()).append(" to ")
                .append(applicationVO.getPreviousCertificateReferenceNo()).append(".").append("</li>");
          }
        }

        if (!(StringUtils.equals(applicationVO.getPreviousCertificateCountryName(),
            applicationVOFromDb.getPreviousCertificateCountryName()))) {
          hasAnyModififed = true;
          if (StringUtils.isEmpty(applicationVO.getPreviousCertificateCountryName())) {
            innerComment.append("<li> Country of the last certificate was removed: ").append(
                applicationVOFromDb.getPreviousCertificateCountryName());
          } else if (StringUtils.isEmpty(applicationVOFromDb.getPreviousCertificateCountryName())) {
            innerComment.append("<li> Country of the last certificate was added: ").append(
                applicationVO.getPreviousCertificateCountryName());
          } else {
            if (applicationVO.getPreviousCertificateIssueStatus() == 1
                && applicationVOFromDb.getPreviousCertificateIssueStatus() == 1) {
              innerComment.append("<li> Country of the last certificate from ")
                  .append(applicationVOFromDb.getPreviousCertificateCountryName()).append(" to ")
                  .append(applicationVO.getPreviousCertificateCountryName()).append(".").append("</li>");
            } else if (applicationVOFromDb.getPreviousCertificateIssueStatus() == 0) {
              innerComment.append("<li> Country of the last certificate was added: ").append(
                  applicationVO.getPreviousCertificateCountryName());
            }
          }
        }


        if (!(applicationVOFromDb.getPreviousCertificateIssueDate() == null || applicationVO
            .getPreviousCertificateIssueDate() == null)) {
          hasAnyModififed = true;
          if (!(applicationVOFromDb.getPreviousCertificateIssueDate().compareTo(
              applicationVO.getPreviousCertificateIssueDate()) == 0)) {
            innerComment.append("<li> Date of Issue from ")
                .append(format.format(applicationVOFromDb.getPreviousCertificateIssueDate())).append(" to ")
                .append(format.format(applicationVO.getPreviousCertificateIssueDate())).append(".").append("</li>");
          }
        } else if (applicationVOFromDb.getPreviousCertificateIssueDate() == null) {
          if (!(applicationVO.getPreviousCertificateIssueDate() == null)) {
            innerComment.append("<li> Date of Issue added ").append(
                format.format(applicationVO.getPreviousCertificateIssueDate()));
          }
        } else if (applicationVO.getPreviousCertificateIssueDate() == null) {
          if (!(applicationVOFromDb.getPreviousCertificateIssueDate() == null)) {
            innerComment.append("<li> Date of Issue removed ").append(
                format.format(applicationVOFromDb.getPreviousCertificateIssueDate()));
          }
        }
      } else {
        applicationVO.setPreviousCertificateReferenceNo(null);
        applicationVO.setPreviousCertificateCountryName(null);
        applicationVO.setPreviousCertificateCountryId(0);
        applicationVO.setPreviousCertificateIssueDate(null);
      }
      comment.append(innerComment.toString());
    }


    if (!(StringUtils.equals(applicationVO.getAuthorizedHandoverPerson(),
        applicationVOFromDb.getAuthorizedHandoverPerson()))) {
      hasAnyModififed = true;
      String personModified = applicationVO.getAuthorizedHandoverPerson();;
      String personModifiedFromDb = applicationVOFromDb.getAuthorizedHandoverPerson();
      try {
        personModified =
            PoliceEnumConstant.HandOverPerson.fromCode(applicationVO.getAuthorizedHandoverPerson()).getDisplayName();
        personModifiedFromDb =
            PoliceEnumConstant.HandOverPerson.fromCode(applicationVOFromDb.getAuthorizedHandoverPerson())
                .getDisplayName();
      } catch (Exception e) {
        e.printStackTrace();
      }
      comment.append("<li> Person authorised by the applicant who are in foreign to handover the application from ")
          .append(personModifiedFromDb).append(" to ").append(personModified).append(".").append("</li>");
      if (StringUtils.equals(applicationVO.getAuthorizedHandoverPerson(),
          PoliceEnumConstant.HandOverPerson.NOA.toString())) {
        applicationVO.setAuthorizedHandoverPersonName(StringUtils.EMPTY);
        applicationVO.setAuthorizedHandoverPersonNicPassport(StringUtils.EMPTY);
      }
    }

    if (!(StringUtils.equals(applicationVO.getAuthorizedHandoverPersonName(),
        applicationVOFromDb.getAuthorizedHandoverPersonName()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getAuthorizedHandoverPersonName())) {
        if (StringUtils.isNotEmpty(applicationVOFromDb.getAuthorizedHandoverPersonName())) {
          comment.append("<li> Person authorised Name was removed: ").append(
              applicationVOFromDb.getAuthorizedHandoverPersonName());
        }
      } else if (StringUtils.isEmpty(applicationVOFromDb.getAuthorizedHandoverPersonName())) {
        if (StringUtils.isNotEmpty(applicationVO.getAuthorizedHandoverPersonName())) {
          comment.append("<li> Person authorised Name was added: ").append(
              applicationVO.getAuthorizedHandoverPersonName());
        }
      } else {
        comment.append("<li> Person authorised Name from ")
            .append(applicationVOFromDb.getAuthorizedHandoverPersonName()).append(" to ")
            .append(applicationVO.getAuthorizedHandoverPersonName()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getAuthorizedHandoverPersonNicPassport(),
        applicationVOFromDb.getAuthorizedHandoverPersonNicPassport()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getAuthorizedHandoverPersonNicPassport())) {
        if (StringUtils.isNotEmpty(applicationVOFromDb.getAuthorizedHandoverPersonNicPassport())) {
          comment.append("<li> Person authorised nic/passport was removed: ").append(
              applicationVOFromDb.getAuthorizedHandoverPersonNicPassport());
        }
      } else if (StringUtils.isEmpty(applicationVOFromDb.getAuthorizedHandoverPersonNicPassport())) {
        if (StringUtils.isNotEmpty(applicationVO.getAuthorizedHandoverPersonNicPassport())) {
          comment.append("<li> Person authorised nic/passport was added: ").append(
              applicationVO.getAuthorizedHandoverPersonNicPassport());
        }
      } else {
        comment.append("<li> Person authorised nic/passport from ")
            .append(applicationVOFromDb.getAuthorizedHandoverPersonNicPassport()).append(" to ")
            .append(applicationVO.getAuthorizedHandoverPersonNicPassport()).append(".").append("</li>");
      }
    }


    String postal = StringUtils.trim(constructPostalAddress(applicationVO));
    String postalFromDb = StringUtils.trim(constructPostalAddress(applicationVOFromDb));

    if (!(StringUtils.equals(postal, postalFromDb))) {
      hasAnyModififed = true;
      comment.append("<li> Certificate postal address from ").append(postalFromDb).append(" to ").append(postal)
          .append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getMobileCountryCode(), applicationVOFromDb.getMobileCountryCode()))) {
      hasAnyModififed = true;
      comment.append("<li> Mobile country code from ").append(applicationVOFromDb.getMobileCountryCode())
          .append(" to ").append(applicationVO.getMobileCountryCode()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getMobileNo(), applicationVOFromDb.getMobileNo()))) {
      hasAnyModififed = true;
      comment.append("<li> Mobile Number from ").append(applicationVOFromDb.getMobileNo()).append(" to ")
          .append(applicationVO.getMobileNo()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getEmail(), applicationVOFromDb.getEmail()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getEmail())) {
        comment.append("<li> Email was removed: ").append(applicationVOFromDb.getEmail());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getEmail())) {
        comment.append("<li> Email was added: ").append(applicationVO.getEmail());
      } else {
        comment.append("<li> Email from ").append(applicationVOFromDb.getEmail()).append(" to ")
            .append(applicationVO.getEmail()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getSpouseFullName(), applicationVOFromDb.getSpouseFullName()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getSpouseFullName())) {
        comment.append("<li> Spouse's Full Name was removed: ").append(applicationVOFromDb.getSpouseFullName());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getSpouseFullName())) {
        comment.append("<li> Spouse's Full Name was added: ").append(applicationVO.getSpouseFullName());
      } else {
        comment.append("<li> Spouse's Full Name from ").append(applicationVOFromDb.getSpouseFullName()).append(" to ")
            .append(applicationVO.getSpouseFullName()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getSpouseNationality(), applicationVOFromDb.getSpouseNationality()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getSpouseNationality())) {
        comment.append("<li> Spouse's Nationality was removed: ").append(applicationVOFromDb.getSpouseNationality());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getSpouseNationality())) {
        comment.append("<li> Spouse's Nationality was added: ").append(applicationVO.getSpouseNationality());
      } else {
        comment.append("<li> Spouse's Nationality from ").append(applicationVOFromDb.getSpouseNationality())
            .append(" to ").append(applicationVO.getSpouseNationality()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getSpousePassport(), applicationVOFromDb.getSpousePassport()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getSpousePassport())) {
        comment.append("<li> Spouse's passport was removed: ").append(applicationVOFromDb.getSpousePassport());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getSpousePassport())) {
        comment.append("<li> Spouse's passport was added: ").append(applicationVO.getSpousePassport());
      } else {
        comment.append("<li> Spouse's passport from ").append(applicationVOFromDb.getSpousePassport()).append(" to ")
            .append(applicationVO.getSpousePassport()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getSpouseNic(), applicationVOFromDb.getSpouseNic()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getSpouseNic())) {
        comment.append("<li> Spouse's nic was removed: ").append(applicationVOFromDb.getSpouseNic());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getSpouseNic())) {
        comment.append("<li> Spouse's nic was added: ").append(applicationVO.getSpouseNic());
      } else {
        comment.append("<li> Spouse's nic from ").append(applicationVOFromDb.getSpouseNic()).append(" to ")
            .append(applicationVO.getSpouseNic()).append(".").append("</li>");
      }
    }


    if (!(StringUtils.equals(applicationVO.getPassportAttachPath(), applicationVOFromDb.getPassportAttachPath()))) {
      hasAnyModififed = true;
      comment.append("<li> Passport - Personal detail page from ").append(applicationVOFromDb.getPassportAttachPath())
          .append(" to ").append(applicationVO.getPassportAttachPath()).append(".").append("</li>");
    }

    if (!(StringUtils
        .equals(applicationVO.getPassportBackAttachPath(), applicationVOFromDb.getPassportBackAttachPath()))) {
      hasAnyModififed = true;
      comment.append("<li> Passport - Countries allowed page from ")
          .append(applicationVOFromDb.getPassportBackAttachPath()).append(" to ")
          .append(applicationVO.getPassportBackAttachPath()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getNicAttachPath(), applicationVOFromDb.getNicAttachPath()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getNicAttachPath())) {
        comment.append("<li>  NIC front page was removed: ").append(applicationVOFromDb.getNicAttachPath());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getNicAttachPath())) {
        comment.append("<li> NIC front page was added: ").append(applicationVO.getNicAttachPath());
      } else {
        comment.append("<li> NIC front page from ").append(applicationVOFromDb.getNicAttachPath()).append(" to ")
            .append(applicationVO.getNicAttachPath()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getNicBackAttachPath(), applicationVOFromDb.getNicBackAttachPath()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getNicBackAttachPath())) {
        comment.append("<li> NIC back page was removed: ").append(applicationVOFromDb.getNicBackAttachPath());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getNicBackAttachPath())) {
        comment.append("<li> NIC back page was added: ").append(applicationVO.getNicBackAttachPath());
      } else {
        comment.append("<li> NIC back page from ").append(applicationVOFromDb.getNicBackAttachPath()).append(" to ")
            .append(applicationVO.getNicBackAttachPath()).append(".").append("</li>");
      }
    }

      if (!(StringUtils.equals(applicationVO.getNewNicAttachPath(), applicationVOFromDb.getNewNicAttachPath()))) {
          hasAnyModififed = true;
          if (StringUtils.isEmpty(applicationVO.getNewNicAttachPath())) {
              comment.append("<li> New NIC front page was removed: ").append(applicationVOFromDb.getNewNicAttachPath());
          } else if (StringUtils.isEmpty(applicationVOFromDb.getNewNicAttachPath())) {
              comment.append("<li> New NIC front page was added: ").append(applicationVO.getNewNicAttachPath());
          } else {
              comment.append("<li> New NIC front page from ").append(applicationVOFromDb.getNicAttachPath()).append(" to ")
                      .append(applicationVO.getNewNicAttachPath()).append(".").append("</li>");
          }
      }

      if (!(StringUtils.equals(applicationVO.getNewNicBackAttachPath(), applicationVOFromDb.getNewNicBackAttachPath()))) {
          hasAnyModififed = true;
          if (StringUtils.isEmpty(applicationVO.getNewNicBackAttachPath())) {
              comment.append("<li> New NIC back page was removed: ").append(applicationVOFromDb.getNewNicBackAttachPath());
          } else if (StringUtils.isEmpty(applicationVOFromDb.getNewNicBackAttachPath())) {
              comment.append("<li> New NIC back page was added: ").append(applicationVO.getNewNicBackAttachPath());
          } else {
              comment.append("<li> New NIC back page from ").append(applicationVOFromDb.getNewNicBackAttachPath()).append(" to ")
                      .append(applicationVO.getNewNicBackAttachPath()).append(".").append("</li>");
          }
      }


      if (!(StringUtils.equals(applicationVO.getBirthCertificatePath(), applicationVOFromDb.getBirthCertificatePath()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getBirthCertificatePath())) {
        comment.append("<li> Birth certificate front page was removed: ").append(
            applicationVOFromDb.getBirthCertificatePath());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getBirthCertificatePath())) {
        comment.append("<li> Birth certificate front page was added: ").append(applicationVO.getBirthCertificatePath());
      } else {
        comment.append("<li> Birth certificate front page from ").append(applicationVOFromDb.getBirthCertificatePath())
            .append(" to ").append(applicationVO.getBirthCertificatePath()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(applicationVO.getBirthCertificateBackPath(),
        applicationVOFromDb.getBirthCertificateBackPath()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getBirthCertificateBackPath())) {
        comment.append("<li> Birth certificate back page was removed: ").append(
            applicationVOFromDb.getBirthCertificateBackPath());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getBirthCertificateBackPath())) {
        comment.append("<li> Birth certificate back page was added: ").append(
            applicationVO.getBirthCertificateBackPath());
      } else {
        comment.append("<li> Birth certificate back page from ")
            .append(applicationVOFromDb.getBirthCertificateBackPath()).append(" to ")
            .append(applicationVO.getBirthCertificateBackPath()).append(".").append("</li>");
      }
    }

    if (!(applicationVO.getReferredThroughBereau() == applicationVOFromDb.getReferredThroughBereau())) {
      hasAnyModififed = true;
      String refereedThroughBureau = "NO";
      String refereedThroughBureauDb = "NO";
      if (applicationVO.getReferredThroughBereau() == 1) {
        refereedThroughBureau = "YES";
      } else {
        if ((applicationVOFromDb.getReferredThroughBereau() == 1)) {
          applicationVO.setLetterOfReferencePath(StringUtils.EMPTY);
        }
      }
      if (applicationVOFromDb.getReferredThroughBereau() == 1) {
        refereedThroughBureauDb = "YES";
      }
      comment.append("<li> Are you referred applicant through the Sri Lankan Foreign Employment Bureau(SLBFE) from ")
          .append(refereedThroughBureauDb).append(" to ").append(refereedThroughBureau).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getLetterOfReferencePath(), applicationVOFromDb.getLetterOfReferencePath()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getLetterOfReferencePath())) {
        comment.append("<li> letter of reference from SLBFE was removed: ").append(
            applicationVOFromDb.getLetterOfReferencePath());
      } else if (StringUtils.isEmpty(applicationVOFromDb.getLetterOfReferencePath())) {
        comment.append("<li> letter of reference from SLBFE was added: ").append(
            applicationVO.getLetterOfReferencePath());
      } else {
        comment.append("<li>  letter of reference from SLBFE from ")
            .append(applicationVOFromDb.getLetterOfReferencePath()).append(" to ")
            .append(applicationVO.getLetterOfReferencePath()).append(".").append("</li>");
      }
    }

      if (!(StringUtils.equals(applicationVO.getAffidavitAttachPath(), applicationVOFromDb.getAffidavitAttachPath()))) {
          hasAnyModififed = true;
          if (StringUtils.isEmpty(applicationVO.getAffidavitAttachPath())) {
              comment.append("<li> Affidavit  was removed: ").append(applicationVOFromDb.getAffidavitAttachPath());
          } else if (StringUtils.isEmpty(applicationVOFromDb.getAffidavitAttachPath())) {
              comment.append("<li> Affidavit  was added: ").append(applicationVO.getAffidavitAttachPath());
          } else {
              comment.append("<li> Affidavit  from ").append(applicationVOFromDb.getNicAttachPath()).append(" to ")
                      .append(applicationVO.getAffidavitAttachPath()).append(".").append("</li>");
          }
      }

    if (!(StringUtils.equals(applicationVO.getDeliveryType(), applicationVOFromDb.getDeliveryType()))) {
      hasAnyModififed = true;
      String deliveryType = applicationVO.getDeliveryType();;
      String deliveryTypeFromDb = applicationVOFromDb.getDeliveryType();
      try {
        deliveryType = PoliceEnumConstant.DeliveryType.fromCode(applicationVO.getDeliveryType()).getDisplayName();
        deliveryTypeFromDb =
            PoliceEnumConstant.DeliveryType.fromCode(applicationVOFromDb.getDeliveryType()).getDisplayName();
      } catch (Exception e) {
        e.printStackTrace();
      }
      comment.append("<li> Delivery Type from ").append(deliveryTypeFromDb).append(" to ").append(deliveryType)
          .append(".").append("</li>");
      if (!(StringUtils.equals(applicationVO.getDeliveryType(), PoliceEnumConstant.DeliveryType.FM.toString()))) {
        applicationVO.setForiegnMinistryInvertNo(StringUtils.EMPTY);
      }

      // modifying reference number
      String referenceNo = applicationVO.getReferenceNo();
      if (StringUtils.isNotEmpty(referenceNo)) {
        if (deliveryType.equals(PoliceEnumConstant.DeliveryType.FM.toString())) {
          referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "E";
        }
        if (deliveryType.equals(PoliceEnumConstant.DeliveryType.SB.toString())) {
          referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "B";
        }
        if (deliveryType.equals(PoliceEnumConstant.DeliveryType.NP.toString())) {
          referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "N";
        }
      }

    }

    if (!(StringUtils.equals(applicationVO.getForiegnMinistryInvertNo(),
        applicationVOFromDb.getForiegnMinistryInvertNo()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(applicationVO.getForiegnMinistryInvertNo())) {
        if (StringUtils.isNotEmpty(applicationVOFromDb.getForiegnMinistryInvertNo())) {
          comment.append("<li> Foreign Ministry Invert Number was removed: ").append(
              applicationVOFromDb.getForiegnMinistryInvertNo());
        }
      } else if (StringUtils.isEmpty(applicationVOFromDb.getForiegnMinistryInvertNo())) {
        if (StringUtils.isNotEmpty(applicationVO.getForiegnMinistryInvertNo())) {
          comment.append("<li> Foreign Ministry Invert Number was added: ").append(
              applicationVO.getForiegnMinistryInvertNo());
        }
      } else {
        comment.append("<li> Foreign Ministry Invert Number from ")
            .append(applicationVOFromDb.getForiegnMinistryInvertNo()).append(" to ")
            .append(applicationVO.getForiegnMinistryInvertNo()).append(".").append("</li>");
      }
    }

    if (hasAnyModififed) {
      comment.append("</ul>");
      return comment.toString();
    } else {
      return null;
    }
  }



  private String getConstructedCommentForApplicationUpdate(ApplicationVO applicationVO,
      ApplicationVO applicationVOFromDb) {
    boolean hasAnyModififed = false;
    StringBuilder comment = new StringBuilder("Application was updated, the values were changed as : - <ul>");

    if (!(StringUtils.equals(applicationVO.getApplicantNameAsNic(), applicationVOFromDb.getApplicantNameAsNic()))) {
      hasAnyModififed = true;
      comment.append("<li> Applicant Name as in NIC from ").append(applicationVOFromDb.getApplicantNameAsNic())
          .append(" to ").append(applicationVO.getApplicantNameAsNic()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getApplicantNameAsPassport(),
        applicationVOFromDb.getApplicantNameAsPassport()))) {
      hasAnyModififed = true;
      comment.append("<li> Applicant Name as in Passport from ")
          .append(applicationVOFromDb.getApplicantNameAsPassport()).append(" to ")
          .append(applicationVO.getApplicantNameAsPassport()).append(".").append("</li>");
    }


    if (!(StringUtils.equals(applicationVO.getPresentAddressLocal(), applicationVOFromDb.getPresentAddressLocal()))) {
      hasAnyModififed = true;
      comment.append("<li> Present Address Local from ").append(applicationVOFromDb.getPresentAddressLocal())
          .append(" to ").append(applicationVO.getPresentAddressLocal()).append(".").append("</li>");
    }

    if (!(StringUtils
        .equals(applicationVO.getPresentAddressOverseas(), applicationVOFromDb.getPresentAddressOverseas()))) {
      hasAnyModififed = true;
      comment.append("<li> Present Address Overseas from ").append(applicationVOFromDb.getPresentAddressOverseas())
          .append(" to ").append(applicationVO.getPresentAddressOverseas()).append(".").append("</li>");
    }

    if (!(StringUtils
        .equals(applicationVO.getHighCommisionReference(), applicationVOFromDb.getHighCommisionReference()))) {
      hasAnyModififed = true;
      comment.append("<li> High Commision Reference from ").append(applicationVOFromDb.getHighCommisionReference())
          .append(" to ").append(applicationVO.getHighCommisionReference()).append(".").append("</li>");
    }

    if (!(StringUtils.equals(applicationVO.getHighCommisionReferenceAddress(),
        applicationVOFromDb.getHighCommisionReferenceAddress()))) {
      hasAnyModififed = true;
      comment.append("<li> High Commision Reference Address from ")
          .append(applicationVOFromDb.getHighCommisionReferenceAddress()).append(" to ")
          .append(applicationVO.getHighCommisionReferenceAddress()).append(".").append("</li>");
    }

    String postal = constructPostalAddress(applicationVO);
    String postalFromDb = constructPostalAddress(applicationVOFromDb);

    if (!(StringUtils.equals(postal, postalFromDb))) {
      hasAnyModififed = true;
      comment.append("<li> Certificate postal address from ").append(postalFromDb).append(" to ").append(postal)
          .append(".").append("</li>");
    }

    if (hasAnyModififed) {
      comment.append("</ui>");
      return comment.toString();
    } else {
      return null;
    }

  }


  private String getConstructedCommentForTransactionUpdate(TransactionVO transactionVO,
      TransactionVO transactionVOFromDb, String commentFromApplication) {
    boolean hasAnyModififed = false;
    if (StringUtils.isEmpty(commentFromApplication)) {
      commentFromApplication = "Application was updated, the values were changed as : -";
    } else {
      hasAnyModififed = true;
    }
    StringBuilder comment = new StringBuilder(commentFromApplication);
    comment.append("<ul>");

    if (!(StringUtils.equals(transactionVOFromDb.getPaymentMode(), transactionVO.getPaymentMode()))) {
      hasAnyModififed = true;
      String paymentMode = transactionVO.getPaymentMode();
      String paymentModeFromDb = transactionVOFromDb.getPaymentMode();
      try {
        paymentMode = PoliceEnumConstant.PaymentMode.fromCode(transactionVO.getPaymentMode()).getDisplayName();
        paymentModeFromDb =
            PoliceEnumConstant.PaymentMode.fromCode(transactionVOFromDb.getPaymentMode()).getDisplayName();
      } catch (Exception e) {
        e.printStackTrace();
      }
      comment.append("<li> Mode of Payment from ").append(paymentModeFromDb).append(" to ").append(paymentMode)
          .append(".").append("</li>");
    }

    if (!(transactionVOFromDb.getTotalFee() == null || transactionVO.getTotalFee() == null)) {
      if (!(transactionVOFromDb.getTotalFee().compareTo(transactionVO.getTotalFee()) == 0)) {
        comment.append("<li> Amount from ").append(transactionVOFromDb.getTotalFee()).append(" to ")
            .append(transactionVO.getTotalFee()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(transactionVOFromDb.getChequeNo(), transactionVO.getChequeNo()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(transactionVO.getChequeNo())) {
        if (StringUtils.isNotEmpty(transactionVOFromDb.getChequeNo())) {
          comment.append("<li> Cheque No was removed: ").append(transactionVOFromDb.getChequeNo());
        }
      } else if (StringUtils.isEmpty(transactionVOFromDb.getChequeNo())) {
        if (StringUtils.isNotEmpty(transactionVO.getChequeNo())) {
          comment.append("<li> Cheque No was added: ").append(transactionVO.getChequeNo());
        }
      } else {
        comment.append("<li> Cheque No from ").append(transactionVOFromDb.getChequeNo()).append(" to ")
            .append(transactionVO.getChequeNo()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(transactionVOFromDb.getAccountNo(), transactionVO.getAccountNo()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(transactionVO.getAccountNo())) {
        if (StringUtils.isNotEmpty(transactionVOFromDb.getAccountNo())) {
          comment.append("<li> Account No was removed: ").append(transactionVOFromDb.getAccountNo());
        }
      } else if (StringUtils.isEmpty(transactionVOFromDb.getAccountNo())) {
        if (StringUtils.isNotEmpty(transactionVO.getAccountNo())) {
          comment.append("<li> Account No was added: ").append(transactionVO.getAccountNo());
        }
      } else {
        comment.append("<li> Account No from ").append(transactionVOFromDb.getAccountNo()).append(" to ")
            .append(transactionVO.getAccountNo()).append(".").append("</li>");
      }
    }

    if (!(StringUtils.equals(transactionVOFromDb.getAccountHolderName(), transactionVO.getAccountHolderName()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(transactionVO.getAccountHolderName())) {
        if (StringUtils.isNotEmpty(transactionVOFromDb.getAccountHolderName())) {
          comment.append("<li> Account Holder Name was removed: ").append(transactionVOFromDb.getAccountHolderName());
        }
      } else if (StringUtils.isEmpty(transactionVOFromDb.getAccountHolderName())) {
        if (StringUtils.isNotEmpty(transactionVO.getAccountHolderName())) {
          comment.append("<li> Account Holder Name was added: ").append(transactionVO.getAccountHolderName());
        }
      } else {
        comment.append("<li> Account Holder Name from ").append(transactionVOFromDb.getAccountHolderName())
            .append(" to ").append(transactionVO.getAccountHolderName()).append(".").append("</li>");
      }

    }

    if (!(StringUtils.equals(transactionVOFromDb.getDescription(), transactionVO.getDescription()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(transactionVO.getDescription())) {
        if (StringUtils.isNotEmpty(transactionVOFromDb.getDescription())) {
          comment.append("<li> Detail was removed: ").append(transactionVOFromDb.getDescription());
        }
      } else if (StringUtils.isEmpty(transactionVOFromDb.getDescription())) {
        if (StringUtils.isNotEmpty(transactionVO.getDescription())) {
          comment.append("<li> Detail was added: ").append(transactionVO.getDescription());
        }
      } else {
        comment.append("<li> Detail from ").append(transactionVOFromDb.getDescription()).append(" to ")
            .append(transactionVO.getDescription()).append(".").append("</li>");
      }

    }

    if (!(StringUtils.equals(transactionVOFromDb.getBookReceiptNo(), transactionVO.getBookReceiptNo()))) {
      hasAnyModififed = true;
      if (StringUtils.isEmpty(transactionVO.getBookReceiptNo())) {
        if (StringUtils.isNotEmpty(transactionVOFromDb.getBookReceiptNo())) {
          comment.append("<li> Book Receipt No was removed: ").append(transactionVOFromDb.getBookReceiptNo());
        }
      } else if (StringUtils.isEmpty(transactionVOFromDb.getBookReceiptNo())) {
        if (StringUtils.isNotEmpty(transactionVO.getBookReceiptNo())) {
          comment.append("<li> Book Receipt No was added: ").append(transactionVO.getBookReceiptNo());
        }
      } else {
        comment.append("<li> Book Receipt No from ").append(transactionVOFromDb.getBookReceiptNo()).append(" to ")
            .append(transactionVO.getBookReceiptNo()).append(".").append("</li>");
      }

    }


    if (hasAnyModififed) {
      comment.append("</ul>");
      return comment.toString();
    } else {
      return null;
    }

  }


  private String constructPostalAddress(ApplicationVO applicationVO) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineOne())) {
      sb.append(applicationVO.getCertificatePostAddressLineOne()).append(", ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineTwo())) {
      sb.append(applicationVO.getCertificatePostAddressLineTwo()).append(", ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCity())) {
      sb.append(applicationVO.getCertificatePostAddressCity()).append(", ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressState())) {
      sb.append(applicationVO.getCertificatePostAddressState()).append(", ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressPostal())) {
      sb.append(applicationVO.getCertificatePostAddressPostal()).append(", ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCountryName())) {
      sb.append(applicationVO.getCertificatePostAddressCountryName()).append(", ");
    }
    return StringUtils.trim(sb.toString());
  }


}
