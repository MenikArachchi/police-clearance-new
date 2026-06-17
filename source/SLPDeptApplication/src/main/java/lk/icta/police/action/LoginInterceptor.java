package lk.icta.police.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * @author
 */
public class LoginInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3994375727904060401L;
    private static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class);

    @SuppressWarnings("unchecked")
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        String returnString = null;
        try {
            String currentAction = invocation.getProxy().getActionName();
            LOGGER.info("Currently processing action--" + currentAction);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Currently processing action--" + currentAction);
            }
            if (!PoliceConstant.EXCLUDE_SESSION_CHECK.contains(currentAction)) {
                // Get the session object from the invocation
                final Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
                // Is there a "user" object stored in the user's HttpSession?
                UserVO user = (UserVO) sessionMap.get(PoliceConstant.SESSION_USER);
                if (user == null) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("user object not found");
                    }
                    returnString = "invalid-session";
                }
                // Check user authorization
                if (!PoliceConstant.EXCLUDE_AUTHORIZATION_CHECK.contains(currentAction) && user != null) {
                    List<Integer> assignedServiceIdList = (List<Integer>) sessionMap.get(PoliceConstant.ASSIGNED_SERVICE_ID_LIST);

                    List<Integer> serviceIdList = getServiceIdFromActionName(currentAction);

//					LOGGER.warn("Assigned Service List : " + sessionMap.get(PoliceConstant.ASSIGNED_SERVICE_ID_LIST));

//					LOGGER.warn("Session Map Size : " + assignedServiceIdList.size());

//					LOGGER.warn("Session Map : " + assignedServiceIdList);

//					LOGGER.warn("SERVICE ID LIST FOR ACTION---------------------------- >>>>>>> " + serviceIdList);

                    boolean isAuthorized = false;

                    if (!(serviceIdList == null || serviceIdList.isEmpty())) {
                        for (Integer serviceId : serviceIdList) {
                            if (!(serviceId == 0)) {
                                if (assignedServiceIdList.contains(serviceId)) {
                                    isAuthorized = true;
                                }
                            }
                        }
                    }

                    //LOGGER.warn("isAuthorized aaa:  " + isAuthorized);

                    if (!(isAuthorized)) {
                        LOGGER.warn("SERVICE ID UNAUTHORIZED USER ");
                        returnString = "unauthorized";
                    }
                }
            }
            if (returnString == null) {
                returnString = invocation.invoke();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        //LOGGER.warn("RETURN STRING : " + returnString);
        return returnString;
    }

	/*
	/*
	2,8,14,20,26,32,38	    Application Verification
	3,9,15,21,27,33,39	    Request for Updates
	4,10,16,22,28,34,40	 	Review Application
	5,11,17,23,29,35,41	 	Certificate Issuance
	6,12,18,24,30,36,42		Daily Transaction Report
	7,13,19,25,31,37,43     Application Detail Report
	8,14,20,26,32,38,46     Black list report
	
	Clearance Report Actions - showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder
	
	 *//*
	public static List<Integer> getServiceIdFromActionName(String currentAction) {
		int serviceId = 0;
		List<Integer> serviceIdList=new ArrayList<Integer>();
		Map<Integer, String> serviceIdAllActionsMap = new HashMap<Integer, String>();
		//1 PHQ
			//		2	Application Verification
			//		3	Request for Updates
			//		4	Review Application
			//		5	Certificate Issuance
			//		6	Daily Transaction Report
//			//      44	Master Files	
			//		45  Add Application
		    //		46   Application Detail Report
		    //		47   Black list report
			//		48   Police Form
			//		50	 View Application Status
		serviceIdAllActionsMap.put(2, "viewApplicationVerification,updateReviewStatus,searchApplicationVerification,lockRecoed,unlockRecoed,loadAddressList,saveRequestClarification,getRequestClarificationCountForAppId,fetchRequestClarificationFromRefNo,policeFileFinder,saveRequestForUpdate,viewApplicationByReferenceNo,uploadFile,modifyAddressListOfApplication,loadEditApplicationVerification,editApplicationFromVerification");
		serviceIdAllActionsMap.put(3, "");
		serviceIdAllActionsMap.put(4, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,loadAddressListClearence,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,policeFileFinder,updatePoliceMessageRow,checkAndLockAddressRecord,checkAndRemoveAddressLock,updateAddress,loadEditAddress,loadEmailContentPhq,updateAddressPhq,updateAddressEditPhq,sendEmailAddressEditPhq,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(5, "viewNoAdverseCheck,searchNoAdverseCheck,updateEmailSentStatus,updateNicrevisionClearenceNa,loadCommentList,checkAndLockClearenceRecord,checkAndRemoveLockClearence,policeFileFinder,printcertificate,reportfilefinder,updateCertificateIssuanceNa,uploadFile,viewApplicationByReferenceNo,printAddresses,viewOicClearence,searchOicClearence,updateCertificateIssuanceOic,viewAspClearence,searchAspClearence,updateCertificateIssuanceAsp,viewDigClearence,searchDigClearence,updateCertificateIssuanceDig,viewDhaClearence,searchDhaClearence,updateCertificateIssuanceDha,viewAdverseCheck,searchAdverseCheck,updateNicrevisionClearenceAd,updateCertificateIssuanceAd,viewPostingOfficer,searchPostingOfficer,updateRegisteredPostNo,printPostList,loadResendingDepartments,loadPoliceAreaListForApplication,loadCurrentCommentDetailsForPoliceArea,loadCurrentCommentDetailsForDepartment,resendClearanceNoAdverse,resendClearanceDha");
		serviceIdAllActionsMap.put(6, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder,viewDailyTransactionReport,searchDailyTransactionReport,printDailyTransactionReport,printDailyTransactionReportExcel");
		serviceIdAllActionsMap.put(44, "populateMasterFiles,saveCertificateSigning,saveHighCommissions,loadAvailableHighCommissionerList,updateHighCommissions,deleteHighCommissionsForm");
		serviceIdAllActionsMap.put(45, "application, loadPreviousApplication, verifyApplication, saveApplication, uploadFile, loadCommissionersByCountry, loadCommissionerById");
		serviceIdAllActionsMap.put(46, "reportfilefinder,viewApplicationDetailsReport,searchApplicationDetailsReport,printApplicationDetailsReport,printApplicationDetailsReportExcel");
		serviceIdAllActionsMap.put(47, "reportfilefinder,viewBlacklist,searchBlacklist,printBlacklistReport,printBlacklistReportExcel");
		serviceIdAllActionsMap.put(48, "reportfilefinder,searchPoliceForm,printPoliceForm");
		serviceIdAllActionsMap.put(50, "reportfilefinder,searchApplicationStatus,viewApplicationStatus,printApplicationStatus");
		
		//7 Police
			//		8	Application Verification
			//		9	Request for Updates
			//		10	Review Application
			//		11	Certificate Issuance
			//		12	Daily Transaction Report
			//		43	Add Application
		    //		13 Application Detail Report
			//			14   Black list report
			//		49   Police Form
		serviceIdAllActionsMap.put(8, "");
		serviceIdAllActionsMap.put(9, "");
		serviceIdAllActionsMap.put(10, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,updateAddress,loadEditAddress,updateAddressPolice,loadAddressListClearence,updateAddressEditPolice,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(11, "");
//		serviceIdAllActionsMap.put(12, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder,viewApplicationDetailsReport,viewDailyTransactionReport,viewBlacklist,searchApplicationDetailsReport,printApplicationDetailsReport,printApplicationDetailsReportExcel,searchDailyTransactionReport,printDailyTransactionReport,printDailyTransactionReportExcel,searchBlacklist,printBlacklistReport,printBlacklistReportExcel");
		serviceIdAllActionsMap.put(12, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder");
		serviceIdAllActionsMap.put(43, "application, loadPreviousApplication, verifyApplication, saveApplication, uploadFile, loadCommissionersByCountry, loadCommissionerById");
		serviceIdAllActionsMap.put(49, "reportfilefinder,searchPoliceForm,printPoliceForm");
//		13	CID
			//		14	Application Verification
			//		15	Request for Updates
			//		16	Review Application
			//		17	Certificate Issuance
			//		18	Daily Transaction Report
			//		19 Application Detail Report
	    //			20   Black list report
		serviceIdAllActionsMap.put(14, "");
		serviceIdAllActionsMap.put(15, "");
		serviceIdAllActionsMap.put(16, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(17, "");
		serviceIdAllActionsMap.put(18, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder");
		
//		19	TID
			//		20	Application Verification
			//		21	Request for Updates
			//		22	Review Application
			//		23	Certificate Issuance
			//		24	Daily Transaction Report
			//		25 Application Detail Report
	    //			26   Black list report
		serviceIdAllActionsMap.put(20, "");
		serviceIdAllActionsMap.put(21, "");
		serviceIdAllActionsMap.put(22, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(23, "");
		serviceIdAllActionsMap.put(24, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder");
		
//		25	SIS
			//		26	Application Verification
			//		27	Request for Updates
			//		28	Review Application
			//		29	Certificate Issuance
			//		30	Daily Transaction Report
			//		31 Application Detail Report
	    //			32   Black list report
		serviceIdAllActionsMap.put(26, "");
		serviceIdAllActionsMap.put(27, "");
		serviceIdAllActionsMap.put(28, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(29, "");
		serviceIdAllActionsMap.put(30, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder,viewApplicationDetailsReport");

//		31	NIC
			//		32	Application Verification
			//		33	Request for Updates
			//		34	Review Application
			//		35	Certificate Issuance
			//		36	Daily Transaction Report
			//		37 Application Detail Report
	    //			38   Black list report
		serviceIdAllActionsMap.put(32, "");
		serviceIdAllActionsMap.put(33, "");
		serviceIdAllActionsMap.put(34, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(35, "");
		serviceIdAllActionsMap.put(36, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder");
		
//		37	Immigration Department
			//		38	Application Verification
			//		39	Request for Updates
			//		40	Review Application
			//		41	Certificate Issuance
			//		42	Daily Transaction Report
			//		43 Application Detail Report
	    //			46   Black list report
		serviceIdAllActionsMap.put(38, "");
		serviceIdAllActionsMap.put(39, "");
		serviceIdAllActionsMap.put(40, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(41, "");
		serviceIdAllActionsMap.put(42, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder");
		
		for (Entry<Integer, String> entry : serviceIdAllActionsMap.entrySet()) {
			String allActions = entry.getValue();
			if (allActions.contains(currentAction)) {
				serviceId = entry.getKey();
				serviceIdList.add(serviceId);
			}
		}
//		LOGGER.warn("serviceIdList SIZE ====================> " + serviceIdList.size());
//		for (Integer serviceeId : serviceIdList) {
//			LOGGER.warn("* serviceeId - " + serviceeId);
//		}
		return serviceIdList;
	}*/
	
	
	/*
		2	    				Application Verification
		3	    				Request for Updates
		4,12,15,17,19,21,23	 	Review Application
		5	 					Certificate Issuance
		6						Daily Transaction Report
		7,13   					Add Application
		8						Master Files
		9						Application Details Report
		10						Blacklist Report
		24						Police Form
		26						View Application Status
		Clearance Report Actions - showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder
		
	*/
	
	public static List<Integer> getServiceIdFromActionName(String currentAction) {
		int serviceId = 0;
		List<Integer> serviceIdList=new ArrayList<Integer>();
		Map<Integer, String> serviceIdAllActionsMap = new HashMap<Integer, String>();
		//1 PHQ		
		//		2	    				Application Verification
		//		3	    				Request for Updates
		//		4,12,15,17,19,21,23	 	Review Application
		//		5	 					Certificate Issuance
		//		6						Daily Transaction Report
		//		7,13   					Add Application
		//		8						Master Files
		//		9						Application Details Report
		//		10						Blacklist Report
		//		24						Police Form
		//		26						View Application Status
		//		27						Edit Application
		serviceIdAllActionsMap.put(1, "");
		serviceIdAllActionsMap.put(2, "viewLockedRecords,searchLockedRecords,unlockRecords,viewApplicationVerification,updateReviewStatus,searchApplicationVerification,lockRecoed,unlockRecoed,loadAddressList,saveRequestClarification,getRequestClarificationCountForAppId,fetchRequestClarificationFromRefNo,policeFileFinder,saveRequestForUpdate,viewApplicationByReferenceNo,uploadFile,modifyAddressListOfApplication,loadEditApplicationVerification,editApplicationFromVerification,loadEditApplication,updateApplication");
		serviceIdAllActionsMap.put(3, "");
		serviceIdAllActionsMap.put(4, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,loadAddressListClearence,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,policeFileFinder,updatePoliceMessageRow,checkAndLockAddressRecord,checkAndRemoveAddressLock,updateAddress,loadEditAddress,loadEmailContentPhq,updateAddressPhq,updateAddressEditPhq,sendEmailAddressEditPhq,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(5, "viewLockedRecords,searchLockedRecords,unlockRecords,viewNoAdverseCheck,searchNoAdverseCheck,updateEmailSentStatus,updateNicrevisionClearenceNa,loadCommentList,checkAndLockClearenceRecord,checkAndRemoveLockClearence,policeFileFinder,printcertificate,reportfilefinder,updateCertificateIssuanceNa,uploadFile,viewApplicationByReferenceNo,printAddresses,viewOicClearence,searchOicClearence,updateCertificateIssuanceOic,viewAspClearence,searchAspClearence,updateCertificateIssuanceAsp,viewDigClearence,searchDigClearence,updateCertificateIssuanceDig,viewDhaClearence,searchDhaClearence,updateCertificateIssuanceDha,viewAdverseCheck,searchAdverseCheck,updateNicrevisionClearenceAd,updateCertificateIssuanceAd,viewPostingOfficer,searchPostingOfficer,updateRegisteredPostNo,printPostList,loadResendingDepartments,loadPoliceAreaListForApplication,loadCurrentCommentDetailsForPoliceArea,loadCurrentCommentDetailsForDepartment,resendClearanceNoAdverse,resendClearanceDha, loadSearchUploadAdditionalDocs, searchUploadDocumentApplication, uploadAdditionalApplicationDocuments, loadSearchUploadAdditionalDocs,searchUploadDocumentApplication,uploadAdditionalApplicationDocuments, searchEditApplication, searchApplicationForUploadAndPrint");
		serviceIdAllActionsMap.put(6, "viewLockedRecords,searchLockedRecords,unlockRecords,showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder,viewDailyTransactionReport,searchDailyTransactionReport,printDailyTransactionReport,printDailyTransactionReportExcel");
		serviceIdAllActionsMap.put(7, "viewLockedRecords,searchLockedRecords,unlockRecords,application, loadPreviousApplication, verifyApplication, saveApplication, uploadFile, loadCommissionersByCountry, loadCommissionerById,viewApplicationByReferenceNo");
		serviceIdAllActionsMap.put(8, "viewLockedRecords,searchLockedRecords,unlockRecords,populateMasterFiles,saveCertificateSigning,saveHighCommissions,loadAvailableHighCommissionerList,updateHighCommissions,deleteHighCommissionsForm");
		serviceIdAllActionsMap.put(9, "viewLockedRecords,searchLockedRecords,unlockRecords,reportfilefinder,viewApplicationDetailsReport,searchApplicationDetailsReport,printApplicationDetailsReport,printApplicationDetailsReportExcel");
		serviceIdAllActionsMap.put(10, "viewLockedRecords,searchLockedRecords,unlockRecords,reportfilefinder,viewBlacklist,searchBlacklist,printBlacklistReport,printBlacklistReportExcel");
		serviceIdAllActionsMap.put(24, "viewLockedRecords,searchLockedRecords,unlockRecords,reportfilefinder,searchPoliceForm,printPoliceForm,loadSearchUploadAdditionalDocs,searchUploadDocumentApplication,uploadAdditionalApplicationDocuments");
		serviceIdAllActionsMap.put(26, "viewLockedRecords,searchLockedRecords,unlockRecords,reportfilefinder,searchApplicationStatus,viewApplicationStatus,printApplicationStatus");
		serviceIdAllActionsMap.put(27, "viewLockedRecords,searchLockedRecords,unlockRecords,loadSearchEditApplication,searchEditApplication,searchApplicationForUploadAndPrint,updateApplication,loadCommisionerVOList,loadEditApplication,loadCommissionVO,uploadFile, loadCommissionersByCountry, loadCommissionerById,viewApplicationByReferenceNo,loadEditApplicationAfterVerification,updateApplicationAfterVerification");
		serviceIdAllActionsMap.put(28, "loadSearchPrintApplication,viewPrintApplicationByReferenceNo");
		
		
		//		11 Police			
		//		12	Review Application
		//		13	Add Application
	   //		25	Police Form
		serviceIdAllActionsMap.put(11, "");	
		serviceIdAllActionsMap.put(12, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,updateAddress,loadEditAddress,updateAddressPolice,loadAddressListClearence,updateAddressEditPolice,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		serviceIdAllActionsMap.put(13, "viewLockedRecords,searchLockedRecords,unlockRecords,application, loadPreviousApplication, verifyApplication, saveApplication, uploadFile, loadCommissionersByCountry, loadCommissionerById,viewApplicationByReferenceNo");
	    serviceIdAllActionsMap.put(25, "viewLockedRecords,searchLockedRecords,unlockRecords,reportfilefinder,searchPoliceForm,printPoliceForm");
	
		//		14	CID
		//		15	Review Application
		serviceIdAllActionsMap.put(14, "");
		serviceIdAllActionsMap.put(15, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,updateAddress,loadEditAddress,updateAddressPolice,loadAddressListClearence,updateAddressEditPolice,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		
		//		16	TID
		//		17	Review Application
		serviceIdAllActionsMap.put(16, "");
		serviceIdAllActionsMap.put(17, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");
		
		//		18	SIS
		//		19	Review Application
		serviceIdAllActionsMap.put(18, "");
		serviceIdAllActionsMap.put(19, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");

        //		20	NIC
        //		21	Review Application
        serviceIdAllActionsMap.put(20, "");
        serviceIdAllActionsMap.put(21, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");

        //		22	Immigration Department
        //		23	Review Application
        serviceIdAllActionsMap.put(22, "");
        serviceIdAllActionsMap.put(23, "viewLockedRecords,searchLockedRecords,unlockRecords,viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList,reportfilefinder");

        for (Entry<Integer, String> entry : serviceIdAllActionsMap.entrySet()) {
            String allActions = entry.getValue();
            if (allActions.contains(currentAction)) {
                serviceId = entry.getKey();
                serviceIdList.add(serviceId);
            }
        }
//		LOGGER.warn("serviceIdList SIZE ====================> " + serviceIdList.size());
//		for (Integer serviceeId : serviceIdList) {
//			LOGGER.warn("* serviceeId - " + serviceeId);
//		}
        return serviceIdList;
    }

}
