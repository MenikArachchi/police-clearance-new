package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.app.business.CommonUserClientBusiness;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.*;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicantStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationPurpose;
import lk.icta.police.framework.constant.PoliceEnumConstant.HandOverPerson;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicationVerificationAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ApplicationVerificationAction.class);
    private Map<String, Object> session;

    private Map<String, PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap;
    private Date fromDate;
    private Date toDate;
    private String referenceNo;

    private List<PoliceAreaVO> policeAreaList;

    private String nic;
    private String newNic;
    private String passport;
    private String name;

    private String reviewStatus;
    private List<ApplicationVO> applicationList;
    private String searchReviewStatus;
    private String searchReferenceNo;
    private long applicationId;
    private String lockRecordStatus;
    private String lockRecordUserName;
    private List<AddressVO> addressVOList;

    //Request Clarification related
    private boolean resubmitNICCheck;
    private boolean resubmitPassportCheck;
    private boolean resubmitNameCheck;
    private boolean resubmitDOBCheck;
    private String resubmitNIC;
    private String resubmitPassport;
    private String resubmitName;
    private String resubmitDOB;
    private int requestApplicationCountForAppId;

    //Update Request Clarification
    private RequestClarificationVO requestClarificationVO;
    private boolean updateRequestForUpdate_dob;
    private boolean updateRequestForUpdate_name;
    private boolean updateRequestForUpdate_passport;
    private boolean updateRequestForUpdate_nic;
    private long reqClarificationApplicationNo;
    private String reqClarificationReferenceNo;


    private String updateRequestForUpdate_nicAttachPath;
    private String updateRequestForUpdate_nicAttachPathBack;
    private String updateRequestForUpdate_passportAttachPath;
    private String updateRequestForUpdate_passportAttachPathBack;

    private String updateRequestForUpdate_changeName;

    private Date dateOfBirth;

    //View Application by reference no
    private ApplicationVO applicationVOView;
    private TransactionVO transactionVO;
    private List<CountryVO> countryList;
    private Map<String, ApplicantStatus> applicantStatus;
    private Map<String, ApplicationPurpose> applicationPurposeMap;
    private Map<String, HandOverPerson> handOverPersons;
    private List<ChangeAuditVO> changeAuditVOs;
    private List<AddressChangeAuditVO> addressChangeAuditVOs;

    private Date updateRequestForUpdateChangeDob;

    private List<AddressVO> certificateAddressList;

    private String comment;
    private static final String PROP_FILE_NAME = "police";


    private List<CommissionerVO> highCommissionList;

    private ApplicationVO applicationVO;

    private String applicantNameAsNic;
    private String applicantNameAsPassport;

    private String presentAddressLocal;
    private String presentAddressOverseas;

    private long highCommisionReferenceId;
    private String highCommisionReference;
    private String highCommisionReferenceAddress;

    private String certificatePostAddressLineOne;
    private String certificatePostAddressLineTwo;
    private String certificatePostAddressCity;
    private String certificatePostAddressState;
    private String certificatePostAddressPostal;
    private long certificatePostAddressCountry;
    private String certificatePostAddressCountryName;


    public String execute() {
        LOGGER.info("REVIEW APPLICATION -> EXECUTE");
        setToDate(new Date());
        setFromDate(new Date());
        loadReviewStatusList();
        loadDailyApplicationVerificationList();
        return SUCCESS;
    }


    public String updateReviewStatus() {

//		LOGGER.info("APPLICATION ID -> " + applicationId);
//		LOGGER.info("REFERENCE NO   -> " + referenceNo);
//		LOGGER.info("REVIEWS TATUS  -> " + reviewStatus);
        loadReviewStatusList();
        setSearchReferenceNo(referenceNo);
        if (reviewStatus.trim().equals(PoliceEnumConstant.ApplicationReviewStatus.NW.toString())) {
            addActionError("Unable to update the record as 'New'");
            lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
            searchApplicationVerification();
            return ERROR;
        }

        if (reviewStatus.trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RV.toString())) {
            addActionError("Unable to update the record as 'Revised'");
            lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
            searchApplicationVerification();
            return ERROR;
        }

        if (reviewStatus.trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RC.toString())) {
            addActionError("Unable to update the record as 'Request Clarification', You need to submit the Request Clarification form.");
            lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
            searchApplicationVerification();
            return ERROR;
        }

        String comment = "Update Review Status " + reviewStatus + " for application with reference no " + referenceNo + " by " + getUserNameFromSession();
        LOGGER.warn("CLEARANCE VERIFICATION COMMENT -> " + comment);
        setComment(comment);

        ChangeAuditVO changeAuditVO = constructChangeAudit();

        Map<String, Object> map = ApplicationBusiness.getInstance().updateApplicationVerificationStatus(applicationId, reviewStatus, getUserFromSession(), comment, changeAuditVO, null);

        String status = (String) map.get("STATUS");
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            addActionMessage((String) map.get("MESSAGE"));
        } else {
            addActionError((String) map.get("MESSAGE"));
        }

        //No need to send e mail - 27/3/2015
        /*if(reviewStatus.trim().equals(PoliceEnumConstant.ApplicationReviewStatus.VF.toString())) {
			ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
			try {
				String mailSendStatus = EmailNotificationBusiness.getInstance().sendApplicationAcceptedMail(applicationVO);
				
				if(mailSendStatus.equals(PoliceConstant.ERROR)){
					addActionError("Successfully Updated the record. E mail send failed.");
					lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId);
					searchApplicationVerification();
					return SUCCESS;
				}
			} catch (BusinessException e) {
				addActionError("Successfully Updated the record. E mail send failed.");
				lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId);
				searchApplicationVerification();
				return SUCCESS;
			}
		}*/

        if (reviewStatus.trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RM.toString())) {

            ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);

            try {
                String mailSendStatus = EmailNotificationBusiness.getInstance().sendRequestManualSubmissionMail(applicationVO);

                String smsSendStatus = SMSNotificationBusiness.getInstance().sendRequestManualSubmissionSMS(applicationVO);

                if (mailSendStatus.equals(PoliceConstant.ERROR)) {
                    addActionError("E mail sending failed.");
                }

                if (smsSendStatus.equals(PoliceConstant.ERROR)) {
                    addActionError("SMS sending failed.");
                }
            } catch (BusinessException e) {
                addActionError("E mail/SMS sending failed.");
                lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
                searchApplicationVerification();
                return SUCCESS;
            }
        }
        searchApplicationVerification();
        return SUCCESS;
    }

    public String saveRequestClarification() {
        loadReviewStatusList();
        setSearchReferenceNo(referenceNo);
        //loadDailyApplicationVerificationList();
//		LOGGER.info("APPLICATION ID -> " + applicationId);
//		LOGGER.info("REFERENCE NO   -> " + referenceNo);
//		LOGGER.info("REVIEWS TATUS  -> " + reviewStatus);
		/*LOGGER.info("resubmitNICCheck      -> " + resubmitNICCheck);
		LOGGER.info("resubmitPassportCheck -> " + resubmitPassportCheck);
		LOGGER.info("resubmitNameCheck     -> " + resubmitNameCheck);
		LOGGER.info("resubmitDOBCheck      -> " + resubmitDOBCheck);
		LOGGER.info("resubmitNIC           -> " + resubmitNIC);
		LOGGER.info("resubmitPassport      -> " + resubmitPassport);
		LOGGER.info("resubmitName          -> " + resubmitName);
		LOGGER.info("resubmitDOB           -> " + resubmitDOB);*/

        RequestClarificationVO requestClarificationVO = new RequestClarificationVO();
        requestClarificationVO.setNic(resubmitNICCheck ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVO.setNicMessage(resubmitNICCheck ? resubmitNIC : "");
        requestClarificationVO.setPassport(resubmitPassportCheck ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVO.setPassportMessage(resubmitPassportCheck ? resubmitPassport : "");
        requestClarificationVO.setVerifyName(resubmitNameCheck ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVO.setNameMessage(resubmitNameCheck ? resubmitName : "");
        requestClarificationVO.setVerifyDateOfBirth(resubmitDOBCheck ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVO.setDateOfBirthMessage(resubmitDOBCheck ? resubmitDOB : "");
        requestClarificationVO.setReferenceNo(referenceNo);
        requestClarificationVO.setApplicationId(applicationId);
        requestClarificationVO.setRequestedUserId(getUserIdFromSession());
        requestClarificationVO.setRequestedUserName(getUserNameFromSession());
        requestClarificationVO.setRequestedDateTime(new Date());

        String commentStr = "Request update the application for "
                + (resubmitNICCheck ? "Resubmit NIC Copy, " : "")
                + (resubmitPassportCheck ? "Resubmit Passport Copy, " : "")
                + (resubmitNameCheck ? "Resubmit Name, " : "")
                + (resubmitDOBCheck ? "Resubmit Date of Birth, " : "");
        commentStr += "Update Review Status " + reviewStatus + " for application with reference no " + referenceNo + " by " + getUserNameFromSession();
        setComment(commentStr);
        ChangeAuditVO changeAuditVO = constructChangeAudit();

        Map<String, Object> map = ApplicationBusiness.getInstance().updateRequestClarificationWrapper(requestClarificationVO, changeAuditVO, applicationId, reviewStatus, getUserFromSession());
        String status = (String) map.get("STATUS");
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            addActionMessage((String) map.get("MESSAGE"));
        } else {
            addActionError((String) map.get("MESSAGE"));
        }

        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            if (!(map.get("APPLICATION_VO") == null)) {
                applicationVO = (ApplicationVO) map.get("APPLICATION_VO");
                try {
                    String mailSendStatus = EmailNotificationBusiness.getInstance().sendRequestClarificationMail(applicationVO, requestClarificationVO);
                    String smsSendStatus = SMSNotificationBusiness.getInstance().sendRequestClarificationSMS(applicationVO);

                    if (mailSendStatus.equals(PoliceConstant.ERROR)) {
                        addActionError("E mail sending failed.");
                    }

                    if (smsSendStatus.equals(PoliceConstant.ERROR)) {
                        addActionError("SMS sending failed.");
                    }
                } catch (BusinessException e) {
                    addActionError("Successfully Updated the record. E mail send failed.");
                    lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
                    searchApplicationVerification();
                    return SUCCESS;
                }

            }
            addActionMessage((String) map.get("MESSAGE"));
        } else {
            addActionError((String) map.get("MESSAGE"));
        }
        lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
        searchApplicationVerification();
        return SUCCESS;
    }

    public String saveRequestForUpdate() {
        loadReviewStatusList();
        setSearchReferenceNo(reqClarificationReferenceNo);
        //loadDailyApplicationVerificationList();
//		LOGGER.info("CHECKNIC                      -> " + updateRequestForUpdate_nic);
//		LOGGER.info("CHECKPASSPORT                 -> " + updateRequestForUpdate_passport);
//		LOGGER.info("CHECKNAME                     -> " + updateRequestForUpdate_name);
//		LOGGER.info("CHECKDATEOFBIRTH              -> " + updateRequestForUpdate_dob);
//		LOGGER.info("REQCLARIFICATIONAPPLICATIONNO -> " + reqClarificationApplicationNo);
//		LOGGER.info("REQCLARIFICATIONREFERENCENO   -> " + reqClarificationReferenceNo);


        RequestClarificationVO requestClarificationVOObj = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept(reqClarificationReferenceNo);
        requestClarificationVOObj.setNicAcceptStatus(updateRequestForUpdate_nic ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVOObj.setPassportAcceptStatus(updateRequestForUpdate_passport ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVOObj.setNameAcceptStatus(updateRequestForUpdate_name ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVOObj.setDateOfBirthAcceptStatus(updateRequestForUpdate_dob ? PoliceEnumConstant.YesNoStatus.YES.getCode() : PoliceEnumConstant.YesNoStatus.NO.getCode());
        requestClarificationVOObj.setReviewedUserId(getUserIdFromSession());
        requestClarificationVOObj.setReviewedUserName(getUserNameFromSession());
        requestClarificationVOObj.setReviewedDateTime(new Date());

        String commentStrMain = constructMainCommentString(requestClarificationVOObj);
        setComment(commentStrMain);
        setApplicationId(reqClarificationApplicationNo);
        ChangeAuditVO changeAuditVO = constructChangeAudit();

        Map<String, Object> map = ApplicationBusiness.getInstance().saveRequestForUpdate(requestClarificationVOObj, changeAuditVO, getUserFromSession(), reviewStatus,
                updateRequestForUpdate_dob,
                updateRequestForUpdate_name,
                updateRequestForUpdate_passport,
                updateRequestForUpdate_nic,
                reqClarificationApplicationNo,
                reqClarificationReferenceNo,
                updateRequestForUpdate_nicAttachPath,
                updateRequestForUpdate_nicAttachPathBack,
                updateRequestForUpdate_passportAttachPath,
                updateRequestForUpdate_passportAttachPathBack,
                updateRequestForUpdate_changeName,
                updateRequestForUpdateChangeDob
        );

        String status = (String) map.get("STATUS");
        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
            addActionMessage((String) map.get("MESSAGE"));
        } else {
            addActionError((String) map.get("MESSAGE"));
        }

        searchApplicationVerification();
        return SUCCESS;
    }

    private String constructMainCommentString(RequestClarificationVO requestClarificationVOObj) {
        String commentStr = "";
        if (updateRequestForUpdate_nic) {
            if (!CommonUtil.checkBlank(updateRequestForUpdate_nicAttachPath)) {
                commentStr += "Update NIC for Request clarification, nic front image -  " + requestClarificationVOObj.getNicPath()
                        + " to " + updateRequestForUpdate_nicAttachPath + ", ";
                requestClarificationVOObj.setNicPath(updateRequestForUpdate_nicAttachPath);
            }

            if (!CommonUtil.checkBlank(updateRequestForUpdate_nicAttachPathBack)) {
                commentStr += " nic back image " + requestClarificationVOObj.getNicPathBack()
                        + " to " + updateRequestForUpdate_nicAttachPathBack + ", ";
                requestClarificationVOObj.setNicPathBack(updateRequestForUpdate_nicAttachPathBack);
            }
        }

        if (updateRequestForUpdate_passport) {
            if (!CommonUtil.checkBlank(updateRequestForUpdate_passportAttachPath)) {
                commentStr += "Update Passport for Request clarification, passport front image - " + requestClarificationVOObj.getPassportPath()
                        + " to " + updateRequestForUpdate_passportAttachPath + ", ";
                requestClarificationVOObj.setPassportPath(updateRequestForUpdate_passportAttachPath);
            }

            if (!CommonUtil.checkBlank(updateRequestForUpdate_passportAttachPathBack)) {
                commentStr += " passport back image " + requestClarificationVOObj.getPassportPathBack()
                        + " to " + updateRequestForUpdate_passportAttachPathBack + ", ";
                requestClarificationVOObj.setPassportPathBack(updateRequestForUpdate_passportAttachPathBack);
            }
        }

        if (updateRequestForUpdate_name) {
            if (!requestClarificationVOObj.getName().equals(updateRequestForUpdate_changeName.trim())) {
                commentStr += "Update Name for Request clarification " + requestClarificationVOObj.getName()
                        + " to " + updateRequestForUpdate_changeName + ", ";
                requestClarificationVOObj.setName(updateRequestForUpdate_changeName);
            }
        }

        if (updateRequestForUpdate_dob) {
            if (requestClarificationVOObj.getDateOfBirth().compareTo(CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob)) != 0) {
                commentStr += "Update DOB for Request clarification " + CommonUtil.getSqlDateFromUtilDate(requestClarificationVOObj.getDateOfBirth())
                        + " to " + CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob) + ", ";
                requestClarificationVOObj.setDateOfBirth(CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob));
            }
        }
        return commentStr;
    }


    public String searchApplicationVerification() {
        loadReviewStatusList();
//		LOGGER.info("TO DATE       -> " + toDate);
//		LOGGER.info("FROM DATE     -> " + fromDate);
//		LOGGER.info("REVIEW STATUS -> " + searchReviewStatus);
//		LOGGER.info("REFERENCE NO  -> " + searchReferenceNo);

        ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO = new ApplicationVerificationSearchCriteriaVO();

        applicationVerificationSearchCriteriaVO.setToDate(toDate);
        applicationVerificationSearchCriteriaVO.setFromDate(fromDate);
        applicationVerificationSearchCriteriaVO.setStatus(searchReviewStatus);
        applicationVerificationSearchCriteriaVO.setReferenceNo(searchReferenceNo);
        applicationVerificationSearchCriteriaVO.setNic(nic);
        applicationVerificationSearchCriteriaVO.setNewNic(newNic);
        applicationVerificationSearchCriteriaVO.setPassport(passport);
        applicationVerificationSearchCriteriaVO.setName(name);
        applicationVerificationSearchCriteriaVO.updateApplicationVerificationSearchCriteriaVO();
        applicationList = ApplicationBusiness.getInstance().getAllocationList(applicationVerificationSearchCriteriaVO, getUserIdFromSession());
        policeAreaList = ApplicationBusiness.getInstance().getPoliceAreaList();
//		LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
        return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    private String loadDailyApplicationVerificationList() {
        ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO = new ApplicationVerificationSearchCriteriaVO();
        applicationVerificationSearchCriteriaVO.setToDate(toDate == null ? new Date() : toDate);
        applicationVerificationSearchCriteriaVO.setFromDate(fromDate == null ? new Date() : fromDate);
        applicationVerificationSearchCriteriaVO.setStatus((searchReviewStatus == null || searchReviewStatus.trim().equals("")) ? "" : searchReviewStatus);
        applicationVerificationSearchCriteriaVO.setReferenceNo((searchReferenceNo == null || searchReferenceNo.trim().equals("")) ? "" : searchReferenceNo);
        applicationVerificationSearchCriteriaVO.setNic(nic);
        applicationVerificationSearchCriteriaVO.setPassport(passport);
        applicationVerificationSearchCriteriaVO.updateApplicationVerificationSearchCriteriaVO();
        applicationList = ApplicationBusiness.getInstance().getAllocationList(applicationVerificationSearchCriteriaVO, getUserIdFromSession());

        policeAreaList = ApplicationBusiness.getInstance().getPoliceAreaList();

//		for (ApplicationVO applicationVO : applicationList) {
//			LOGGER.info("APPLICATION ID -> " + applicationVO.getApplicationId());
//		}
        return SUCCESS;
    }

    private String loadReviewStatusList() {
        reviewStatusMap = PoliceEnumConstant.ApplicationReviewStatus.getApplicationReviewStatusMap();
        reviewStatusMap.remove(PoliceEnumConstant.ApplicationReviewStatus.TM.getDisplayName());
        reviewStatusMap.remove(PoliceEnumConstant.ApplicationReviewStatus.TS.getDisplayName());
//		LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
        return SUCCESS;
    }

    public String lockRecoed() {

        Map<String, Object> map = ApplicationBusiness.getInstance().lockPHQRecord(applicationId, getUserIdFromSession());

        lockRecordStatus = (String) map.get("STATUS");

        if (StringUtils.equals(lockRecordStatus, PoliceConstant.SUCCESS)) {
            addRecordLockedStatusToSession();
        }

        if (StringUtils.equals(lockRecordStatus, PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER)) {
            try {
                CommonUserClientBusiness commonUserClientBusiness = new CommonUserClientBusiness();
                UserVO userVOObj = new UserVO();
                userVOObj.setId((Integer) map.get("LOCKED_USER"));

                userVOObj = commonUserClientBusiness.getUserDetails(userVOObj);
                lockRecordUserName = userVOObj.getFullName();
            } catch (Exception e) {
                return SUCCESS;
            }

        }

        LOGGER.info("LOCKRECORDSTATUS -----------> " + lockRecordStatus);
        return SUCCESS;
    }

    public String unlockRecoed() {
        lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
        if (StringUtils.equals(lockRecordStatus, PoliceConstant.SUCCESS)) {
            removeRecordLockedStatusFromSession(applicationId);
        }
        LOGGER.info("UNLOCKRECORDSTATUS -----------> " + lockRecordStatus);
        return SUCCESS;
    }

    public String loadAddressList() {
        addressVOList = ApplicationBusiness.getInstance().getAddressListByUserRole(applicationId, getUserRoleFromSession(), 0L, null);
        return SUCCESS;
    }

    public String getRequestClarificationCountForAppId() {
        requestApplicationCountForAppId = RequestClarificationBusiness.getInstance().countRequestClarificationForApplication(applicationId, null);
        return SUCCESS;
    }

    public String fetchRequestClarificationFromRefNo() {
        requestClarificationVO = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept(referenceNo);
        return SUCCESS;
    }

    public String viewApplicationByReferenceNo() {
        countryList = ApplicationBusiness.getInstance().getCountryList();
        applicantStatus = PoliceEnumConstant.ApplicantStatus.getApplicantStatusMap();
        applicationPurposeMap = PoliceEnumConstant.ApplicationPurpose.getApplicationPurposeMap();
        handOverPersons = PoliceEnumConstant.HandOverPerson.getHandOverPersonMap();
        applicationVOView = ApplicationBusiness.getInstance().getApplicationWithAddresses(referenceNo);
        if (!(applicationVOView == null)) {
            applicationId = applicationVOView.getApplicationId();
//            if(applicationVOView.getNationalityId() != 199 && applicationVOView.getCitizenOfSriLanka() != 1){
//                applicationVOView.setApplicantNameAsNic(null);
//            }
            transactionVO = TransactionBussiness.getInstance().getTransaction(applicationVOView.getTransactionId());
            changeAuditVOs = ApplicationBusiness.getInstance().loadChangeAuditByReferenceNo(applicationId);
            addressChangeAuditVOs = ApplicationBusiness.getInstance().getAddressChangeAuditListByApplicationId(applicationId);
        }
        return SUCCESS;
    }

    public TransactionVO getTransactionVO() {
        return transactionVO;
    }


    public void setTransactionVO(TransactionVO transactionVO) {
        this.transactionVO = transactionVO;
    }


    private int getUserRoleFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null || userVO.getDept() == null)) {
            return userVO.getDept().getId();
        }
        return 0;
    }

    private String getUserNameFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getFullName();
        }
        return null;
    }

    private int getUserIdFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getId();
        }
        return 0;
    }

    private UserVO getUserFromSession() {
        if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
            return (UserVO) session.get(PoliceConstant.SESSION_USER);
        }
        return null;
    }

    private ChangeAuditVO constructChangeAudit() {
        ChangeAuditVO changeAuditVO = new ChangeAuditVO();
        changeAuditVO.setApplicationId(applicationId);
        changeAuditVO.setUpdatedUserId(getUserIdFromSession());
        changeAuditVO.setUpdatedUserName(getUserNameFromSession());
        changeAuditVO.setUpdatedUserDateTime(new Date());
        changeAuditVO.setComment(comment);
        return changeAuditVO;
    }

    private void addRecordLockedStatusToSession() {
        Map<Long, Integer> recordLockedMap = null;
        if (!(session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP) == null)) {
            recordLockedMap = (Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP);
        } else {
            recordLockedMap = new LinkedHashMap<Long, Integer>();
        }
        recordLockedMap.put(applicationId, getUserIdFromSession());
        session.put(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP, recordLockedMap);
    }

    private void removeRecordLockedStatusFromSession(long unlockedApplicationId) {
        if (!(session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP) == null)) {
            Map<Long, Integer> recordLockedMap = (Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP);
            if (!(recordLockedMap == null)) {
                recordLockedMap.remove(unlockedApplicationId);
                session.put(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP, recordLockedMap);
            }
        }
    }


    public String modifyAddressListOfApplication() {
        if (!(applicationId <= 0)) {
            if (!(certificateAddressList == null || certificateAddressList.isEmpty())) {

                String status = EditApplicationBusiness.getInstance().modifyAddressesOfApplication(certificateAddressList, applicationId, getUserIdFromSession(), getUserNameFromSession());
                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                    addActionMessage("Addresses were modified successfully!");
                    lockRecordStatus = ApplicationBusiness.getInstance().unlockPHQRecord(applicationId, null);
                } else if (StringUtils.equals(status, PoliceConstant.NO_CHANGES_TO_UPDATE)) {
                    addActionError("There were no changes to update!");
                } else {
                    addActionError("Could not update the addresses, an error occurred!");
                }

            } else {
                addActionError("Please enter at least one address!");
            }
        } else {
            addActionError("Please select the application!");
        }
        searchApplicationVerification();
        return SUCCESS;
    }
	
	
	
	/*private static void copyFileUsingFileChannel(File inputFile,File outputFile) throws IOException {
        FileChannel source = null;
        FileChannel destination = null;
         
        try {
            source = new FileInputStream(inputFile).getChannel();
            destination = new FileOutputStream(outputFile).getChannel();             
            ByteBuffer buf = ByteBuffer.allocateDirect(PoliceConstant.DEFAULT_BUFFER_SIZE);
            while((source.read(buf)) != -1) {
                    buf.flip();
                    destination.write(buf);
                    buf.clear();
            }
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }*/


    public String loadEditApplicationVerification() {
        if (!(applicationId == 0)) {
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            if (!(applicationVO == null)) {
                this.applicantNameAsNic = applicationVO.getApplicantNameAsNic();
                this.applicantNameAsPassport = applicationVO.getApplicantNameAsPassport();

                this.presentAddressLocal = applicationVO.getPresentAddressLocal();
                this.presentAddressOverseas = applicationVO.getPresentAddressOverseas();

                this.highCommisionReferenceId = applicationVO.getHighCommisionReferenceId();
                this.highCommisionReference = applicationVO.getHighCommisionReference();
                this.highCommisionReferenceAddress = applicationVO.getHighCommisionReferenceAddress();

                this.certificatePostAddressLineOne = applicationVO.getCertificatePostAddressLineOne();
                this.certificatePostAddressLineTwo = applicationVO.getCertificatePostAddressLineTwo();
                this.certificatePostAddressCity = applicationVO.getCertificatePostAddressCity();
                this.certificatePostAddressState = applicationVO.getCertificatePostAddressState();
                this.certificatePostAddressPostal = applicationVO.getCertificatePostAddressPostal();
                this.certificatePostAddressCountry = applicationVO.getCertificatePostAddressCountry();
                this.certificatePostAddressCountryName = applicationVO.getCertificatePostAddressCountryName();

                return SUCCESS;
            }
        }
        return ERROR;
    }

    //edit applicants name as in nic and as in passport, current address (the one printed on the certificate), certificate posted address, embassy name and embassy address
    public String editApplicationFromVerification() {
        if (!(applicationId == 0)) {
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            if (!(applicationVO == null)) {
                boolean isValid = true;
                if (StringUtils.isEmpty(highCommisionReference)) {
                    addActionError("Applicant High Commision Reference cannot be empty");
                    isValid = false;
                }

                if (StringUtils.isEmpty(highCommisionReferenceAddress)) {
                    addActionError("Applicant High Commision Reference Address cannot be empty");
                    isValid = false;
                }

                if (StringUtils.isEmpty(certificatePostAddressLineOne)) {
                    addActionError("Certificate postal Address line one cannot be empty");
                    isValid = false;
                }

                if (StringUtils.isEmpty(certificatePostAddressCountryName)) {
                    addActionError("Certificate postal Address country cannot be empty");
                    isValid = false;
                }

                if (StringUtils.isNotEmpty(applicationVO.getApplicantNameAsNic())) {
                    if (StringUtils.isEmpty(applicantNameAsNic)) {
                        addActionError("Applicant Name as NIC cannot be empty");
                        isValid = false;
                    }
                }

                if (StringUtils.isNotEmpty(applicationVO.getPresentAddressOverseas())) {
                    if (StringUtils.isEmpty(presentAddressOverseas)) {
                        addActionError("Applicant Present Address Overseas cannot be empty");
                        isValid = false;
                    }
                }

                if (StringUtils.isNotEmpty(applicationVO.getPresentAddressLocal())) {
                    if (StringUtils.isEmpty(presentAddressLocal)) {
                        addActionError("Applicant Present Address Local cannot be empty");
                        isValid = false;
                    }
                }

                if (!(isValid)) {
                    return ERROR;
                }

                updateApplicationVoWithEditedValues();

                String result = EditApplicationBusiness.getInstance().editApplicationFromVerification(applicationVO, getUserFromSession());

                searchApplicationVerification();
                if (StringUtils.equals(result, PoliceConstant.SUCCESS)) {
                    addActionMessage("Application was successfully updated!");
                    return SUCCESS;
                } else if (StringUtils.equals(result, "NO_CHANGES_TO_UPDATE")) {
                    addActionError("There were no changes to update in application!");
                    return ERROR;
                } else {
                    addActionError("Could not update the record, internal error!");
                    return ERROR;
                }
            }
        } else {
            addActionError("Invalid Application");
        }
        return ERROR;
    }


    private void updateApplicationVoWithEditedValues() {
        applicationVO.setApplicantNameAsNic(applicantNameAsNic);
        applicationVO.setApplicantNameAsPassport(applicantNameAsPassport);

        applicationVO.setPresentAddressLocal(presentAddressLocal);
        applicationVO.setPresentAddressOverseas(presentAddressOverseas);

        applicationVO.setHighCommisionReferenceId(highCommisionReferenceId);
        applicationVO.setHighCommisionReference(highCommisionReference);
        applicationVO.setHighCommisionReferenceAddress(highCommisionReferenceAddress);

        applicationVO.setCertificatePostAddressLineOne(certificatePostAddressLineOne);
        applicationVO.setCertificatePostAddressLineTwo(certificatePostAddressLineTwo);
        applicationVO.setCertificatePostAddressCity(certificatePostAddressCity);
        applicationVO.setCertificatePostAddressState(certificatePostAddressState);
        applicationVO.setCertificatePostAddressPostal(certificatePostAddressPostal);
        applicationVO.setCertificatePostAddressCountry(certificatePostAddressCountry);
        applicationVO.setCertificatePostAddressCountryName(certificatePostAddressCountryName);

    }

    public Date getFromDate() {
        return fromDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Map<String, PoliceEnumConstant.ApplicationReviewStatus> getReviewStatusMap() {
        return reviewStatusMap;
    }

    public void setReviewStatusMap(
            Map<String, PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap) {
        this.reviewStatusMap = reviewStatusMap;
    }

    public List<ApplicationVO> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<ApplicationVO> applicationList) {
        this.applicationList = applicationList;
    }

    public String getSearchReviewStatus() {
        return searchReviewStatus;
    }

    public void setSearchReviewStatus(String searchReviewStatus) {
        this.searchReviewStatus = searchReviewStatus;
    }

    public String getSearchReferenceNo() {
        return searchReferenceNo;
    }

    public void setSearchReferenceNo(String searchReferenceNo) {
        this.searchReferenceNo = searchReferenceNo;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getLockRecordStatus() {
        return lockRecordStatus;
    }

    public void setLockRecordStatus(String lockRecordStatus) {
        this.lockRecordStatus = lockRecordStatus;
    }

    public List<AddressVO> getAddressVOList() {
        return addressVOList;
    }

    public void setAddressVOList(List<AddressVO> addressVOList) {
        this.addressVOList = addressVOList;
    }

    public boolean isResubmitNICCheck() {
        return resubmitNICCheck;
    }

    public void setResubmitNICCheck(boolean resubmitNICCheck) {
        this.resubmitNICCheck = resubmitNICCheck;
    }

    public boolean isResubmitPassportCheck() {
        return resubmitPassportCheck;
    }

    public void setResubmitPassportCheck(boolean resubmitPassportCheck) {
        this.resubmitPassportCheck = resubmitPassportCheck;
    }

    public boolean isResubmitNameCheck() {
        return resubmitNameCheck;
    }

    public void setResubmitNameCheck(boolean resubmitNameCheck) {
        this.resubmitNameCheck = resubmitNameCheck;
    }

    public boolean isResubmitDOBCheck() {
        return resubmitDOBCheck;
    }

    public void setResubmitDOBCheck(boolean resubmitDOBCheck) {
        this.resubmitDOBCheck = resubmitDOBCheck;
    }

    public String getResubmitNIC() {
        return resubmitNIC;
    }

    public void setResubmitNIC(String resubmitNIC) {
        this.resubmitNIC = resubmitNIC;
    }

    public String getResubmitPassport() {
        return resubmitPassport;
    }

    public void setResubmitPassport(String resubmitPassport) {
        this.resubmitPassport = resubmitPassport;
    }

    public String getResubmitName() {
        return resubmitName;
    }

    public void setResubmitName(String resubmitName) {
        this.resubmitName = resubmitName;
    }

    public String getResubmitDOB() {
        return resubmitDOB;
    }

    public void setResubmitDOB(String resubmitDOB) {
        this.resubmitDOB = resubmitDOB;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public int getRequestApplicationCountForAppId() {
        return requestApplicationCountForAppId;
    }

    public void setRequestApplicationCountForAppId(
            int requestApplicationCountForAppId) {
        this.requestApplicationCountForAppId = requestApplicationCountForAppId;
    }

    public RequestClarificationVO getRequestClarificationVO() {
        return requestClarificationVO;
    }

    public void setRequestClarificationVO(
            RequestClarificationVO requestClarificationVO) {
        this.requestClarificationVO = requestClarificationVO;
    }

    public long getReqClarificationApplicationNo() {
        return reqClarificationApplicationNo;
    }

    public void setReqClarificationApplicationNo(long reqClarificationApplicationNo) {
        this.reqClarificationApplicationNo = reqClarificationApplicationNo;
    }

    public String getReqClarificationReferenceNo() {
        return reqClarificationReferenceNo;
    }

    public void setReqClarificationReferenceNo(String reqClarificationReferenceNo) {
        this.reqClarificationReferenceNo = reqClarificationReferenceNo;
    }

    public boolean isUpdateRequestForUpdate_dob() {
        return updateRequestForUpdate_dob;
    }

    public void setUpdateRequestForUpdate_dob(boolean updateRequestForUpdate_dob) {
        this.updateRequestForUpdate_dob = updateRequestForUpdate_dob;
    }

    public boolean isUpdateRequestForUpdate_name() {
        return updateRequestForUpdate_name;
    }

    public void setUpdateRequestForUpdate_name(boolean updateRequestForUpdate_name) {
        this.updateRequestForUpdate_name = updateRequestForUpdate_name;
    }

    public boolean isUpdateRequestForUpdate_passport() {
        return updateRequestForUpdate_passport;
    }

    public void setUpdateRequestForUpdate_passport(
            boolean updateRequestForUpdate_passport) {
        this.updateRequestForUpdate_passport = updateRequestForUpdate_passport;
    }

    public boolean isUpdateRequestForUpdate_nic() {
        return updateRequestForUpdate_nic;
    }

    public void setUpdateRequestForUpdate_nic(boolean updateRequestForUpdate_nic) {
        this.updateRequestForUpdate_nic = updateRequestForUpdate_nic;
    }

    public ApplicationVO getApplicationVOView() {
        return applicationVOView;
    }

    public void setApplicationVOView(ApplicationVO applicationVOView) {
        this.applicationVOView = applicationVOView;
    }

    public List<CountryVO> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryVO> countryList) {
        this.countryList = countryList;
    }

    public Map<String, ApplicantStatus> getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(Map<String, ApplicantStatus> applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public Map<String, ApplicationPurpose> getApplicationPurposeMap() {
        return applicationPurposeMap;
    }

    public void setApplicationPurposeMap(
            Map<String, ApplicationPurpose> applicationPurposeMap) {
        this.applicationPurposeMap = applicationPurposeMap;
    }

    public Map<String, HandOverPerson> getHandOverPersons() {
        return handOverPersons;
    }

    public void setHandOverPersons(Map<String, HandOverPerson> handOverPersons) {
        this.handOverPersons = handOverPersons;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getUpdateRequestForUpdate_nicAttachPath() {
        return updateRequestForUpdate_nicAttachPath;
    }

    public void setUpdateRequestForUpdate_nicAttachPath(
            String updateRequestForUpdate_nicAttachPath) {
        this.updateRequestForUpdate_nicAttachPath = updateRequestForUpdate_nicAttachPath;
    }

    public String getUpdateRequestForUpdate_passportAttachPath() {
        return updateRequestForUpdate_passportAttachPath;
    }

    public void setUpdateRequestForUpdate_passportAttachPath(
            String updateRequestForUpdate_passportAttachPath) {
        this.updateRequestForUpdate_passportAttachPath = updateRequestForUpdate_passportAttachPath;
    }

    public String getUpdateRequestForUpdate_changeName() {
        return updateRequestForUpdate_changeName;
    }

    public void setUpdateRequestForUpdate_changeName(
            String updateRequestForUpdate_changeName) {
        this.updateRequestForUpdate_changeName = updateRequestForUpdate_changeName;
    }


	/*public Date getDateOfBirth() {
		return dateOfBirth;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}*/

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public Date getUpdateRequestForUpdateChangeDob() {
        return updateRequestForUpdateChangeDob;
    }

    public void setUpdateRequestForUpdateChangeDob(
            Date updateRequestForUpdateChangeDob) {
        this.updateRequestForUpdateChangeDob = updateRequestForUpdateChangeDob;
    }

    public List<ChangeAuditVO> getChangeAuditVOs() {
        return changeAuditVOs;
    }

    public void setChangeAuditVOs(List<ChangeAuditVO> changeAuditVOs) {
        this.changeAuditVOs = changeAuditVOs;
    }

    public String getLockRecordUserName() {
        return lockRecordUserName;
    }

    public void setLockRecordUserName(String lockRecordUserName) {
        this.lockRecordUserName = lockRecordUserName;
    }

    public List<AddressChangeAuditVO> getAddressChangeAuditVOs() {
        return addressChangeAuditVOs;
    }

    public void setAddressChangeAuditVOs(
            List<AddressChangeAuditVO> addressChangeAuditVOs) {
        this.addressChangeAuditVOs = addressChangeAuditVOs;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<PoliceAreaVO> getPoliceAreaList() {
        return policeAreaList;
    }

    public void setPoliceAreaList(List<PoliceAreaVO> policeAreaList) {
        this.policeAreaList = policeAreaList;
    }

    public List<AddressVO> getCertificateAddressList() {
        return certificateAddressList;
    }

    public void setCertificateAddressList(List<AddressVO> certificateAddressList) {
        this.certificateAddressList = certificateAddressList;
    }

    public String getUpdateRequestForUpdate_nicAttachPathBack() {
        return updateRequestForUpdate_nicAttachPathBack;
    }

    public void setUpdateRequestForUpdate_nicAttachPathBack(
            String updateRequestForUpdate_nicAttachPathBack) {
        this.updateRequestForUpdate_nicAttachPathBack = updateRequestForUpdate_nicAttachPathBack;
    }

    public String getUpdateRequestForUpdate_passportAttachPathBack() {
        return updateRequestForUpdate_passportAttachPathBack;
    }

    public void setUpdateRequestForUpdate_passportAttachPathBack(
            String updateRequestForUpdate_passportAttachPathBack) {
        this.updateRequestForUpdate_passportAttachPathBack = updateRequestForUpdate_passportAttachPathBack;
    }

    public List<CommissionerVO> getHighCommissionList() {
        return highCommissionList;
    }

    public void setHighCommissionList(List<CommissionerVO> highCommissionList) {
        this.highCommissionList = highCommissionList;
    }

    public ApplicationVO getApplicationVO() {
        return applicationVO;
    }

    public void setApplicationVO(ApplicationVO applicationVO) {
        this.applicationVO = applicationVO;
    }

    public String getApplicantNameAsNic() {
        return applicantNameAsNic;
    }

    public void setApplicantNameAsNic(String applicantNameAsNic) {
        this.applicantNameAsNic = applicantNameAsNic;
    }

    public String getApplicantNameAsPassport() {
        return applicantNameAsPassport;
    }

    public void setApplicantNameAsPassport(String applicantNameAsPassport) {
        this.applicantNameAsPassport = applicantNameAsPassport;
    }

    public String getPresentAddressLocal() {
        return presentAddressLocal;
    }

    public void setPresentAddressLocal(String presentAddressLocal) {
        this.presentAddressLocal = presentAddressLocal;
    }

    public String getPresentAddressOverseas() {
        return presentAddressOverseas;
    }

    public void setPresentAddressOverseas(String presentAddressOverseas) {
        this.presentAddressOverseas = presentAddressOverseas;
    }

    public long getHighCommisionReferenceId() {
        return highCommisionReferenceId;
    }

    public void setHighCommisionReferenceId(long highCommisionReferenceId) {
        this.highCommisionReferenceId = highCommisionReferenceId;
    }

    public String getHighCommisionReference() {
        return highCommisionReference;
    }

    public void setHighCommisionReference(String highCommisionReference) {
        this.highCommisionReference = highCommisionReference;
    }

    public String getHighCommisionReferenceAddress() {
        return highCommisionReferenceAddress;
    }

    public void setHighCommisionReferenceAddress(
            String highCommisionReferenceAddress) {
        this.highCommisionReferenceAddress = highCommisionReferenceAddress;
    }

    public String getCertificatePostAddressLineOne() {
        return certificatePostAddressLineOne;
    }

    public void setCertificatePostAddressLineOne(
            String certificatePostAddressLineOne) {
        this.certificatePostAddressLineOne = certificatePostAddressLineOne;
    }

    public String getCertificatePostAddressLineTwo() {
        return certificatePostAddressLineTwo;
    }

    public void setCertificatePostAddressLineTwo(
            String certificatePostAddressLineTwo) {
        this.certificatePostAddressLineTwo = certificatePostAddressLineTwo;
    }

    public String getCertificatePostAddressCity() {
        return certificatePostAddressCity;
    }

    public void setCertificatePostAddressCity(String certificatePostAddressCity) {
        this.certificatePostAddressCity = certificatePostAddressCity;
    }

    public String getCertificatePostAddressState() {
        return certificatePostAddressState;
    }

    public void setCertificatePostAddressState(String certificatePostAddressState) {
        this.certificatePostAddressState = certificatePostAddressState;
    }

    public String getCertificatePostAddressPostal() {
        return certificatePostAddressPostal;
    }

    public void setCertificatePostAddressPostal(String certificatePostAddressPostal) {
        this.certificatePostAddressPostal = certificatePostAddressPostal;
    }

    public long getCertificatePostAddressCountry() {
        return certificatePostAddressCountry;
    }

    public void setCertificatePostAddressCountry(long certificatePostAddressCountry) {
        this.certificatePostAddressCountry = certificatePostAddressCountry;
    }

    public String getCertificatePostAddressCountryName() {
        return certificatePostAddressCountryName;
    }

    public void setCertificatePostAddressCountryName(
            String certificatePostAddressCountryName) {
        this.certificatePostAddressCountryName = certificatePostAddressCountryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewNic() {
        return newNic;
    }

    public void setNewNic(String newNic) {
        this.newNic = newNic;
    }
}
