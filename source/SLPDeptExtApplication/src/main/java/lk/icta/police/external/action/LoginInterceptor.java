package lk.icta.police.external.action;

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
 * 
 * @author 
 *
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
					
					List<Integer> serviceIdList=getServiceIdFromActionName(currentAction);
					
					LOGGER.warn("Assigned Service List : " + sessionMap.get(PoliceConstant.ASSIGNED_SERVICE_ID_LIST));
					
					LOGGER.warn("Session Map Size : " + assignedServiceIdList.size());
					
					LOGGER.warn("Session Map : " + assignedServiceIdList);
					
					LOGGER.warn("SERVICE ID LIST FOR ACTION---------------------------- >>>>>>> " + serviceIdList);
					
					boolean isAuthorized=false;
					
					if(!(serviceIdList==null || serviceIdList.isEmpty())){
						for (Integer serviceId : serviceIdList) {
							if(!(serviceId==0)){
								if(assignedServiceIdList.contains(serviceId)){
									isAuthorized=true;
								}
							}							
						}
					}
					
					LOGGER.warn("isAuthorized aaa:  " + isAuthorized);
					
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
			LOGGER.error(e.getMessage(),e);
		}
		
		LOGGER.warn("RETURN STRING : " + returnString);
		return returnString;
	}
	
	/*
		2,8,14,20,26,32,38	    Application Verification
		3,9,15,21,27,33,39	    Request for Updates
		4,10,16,22,28,34,40	 	Review Application
		5,11,17,23,29,35,41	 	Certificate Issuance
		6,12,18,24,30,36,42		Daily Transaction Report
		
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
		
		serviceIdAllActionsMap.put(1, "");
		serviceIdAllActionsMap.put(2, "viewApplicationVerification,updateReviewStatus,searchApplicationVerification,lockRecoed,unlockRecoed,loadAddressList,saveRequestClarification,getRequestClarificationCountForAppId,fetchRequestClarificationFromRefNo,policeFileFinder,saveRequestForUpdate,viewApplicationByReferenceNo,reuploadNICFiles,reuploadPassportFiles");
		serviceIdAllActionsMap.put(3, "");
		serviceIdAllActionsMap.put(4, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,loadAddressListClearence,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,policeFileFinder,updatePoliceMessageRow,checkAndLockAddressRecord,checkAndRemoveAddressLock,updateAddress,loadEditAddress,loadEmailContentPhq,updateAddressPhq,updateAddressEditPhq,sendEmailAddressEditPhq,viewPrintList,searchPrintList,loadSelectedApplicationList");
		serviceIdAllActionsMap.put(5, "viewNoAdverseCheck,searchNoAdverseCheck,updateEmailSentStatus,updateNicrevisionClearenceNa,loadCommentList,checkAndLockClearenceRecord,checkAndRemoveLockClearence,policeFileFinder,printcertificate,reportfilefinder,updateCertificateIssuanceNa,uploadFiles,viewApplicationByReferenceNo,printAddresses,viewOicClearence,searchOicClearence,updateCertificateIssuanceOic,viewAspClearence,searchAspClearence,updateCertificateIssuanceAsp,viewDigClearence,searchDigClearence,updateCertificateIssuanceDig,viewDhaClearence,searchDhaClearence,updateCertificateIssuanceDha,viewAdverseCheck,searchAdverseCheck,updateNicrevisionClearenceAd,updateCertificateIssuanceAd,viewPostingOfficer,searchPostingOfficer,updateRegisteredPostNo,printPostList");
		serviceIdAllActionsMap.put(6, "showClearanceReport,loadClearanceReport,printClearanceReportAction,reportfilefinder,viewDailyTransactionReport,searchDailyTransactionReport,printDailyTransactionReport,printDailyTransactionReportExcel");
		serviceIdAllActionsMap.put(7, "application, loadPreviousApplication, verifyApplication, saveApplication, uploadFilesForApplication, loadCommissionersByCountry, loadCommissionerById");
		serviceIdAllActionsMap.put(8, "populateMasterFiles,saveCertificateSigning,saveHighCommissions,loadAvailableHighCommissionerList,updateHighCommissions,deleteHighCommissionsForm");
		serviceIdAllActionsMap.put(9, "reportfilefinder,viewApplicationDetailsReport,searchApplicationDetailsReport,printApplicationDetailsReport,printApplicationDetailsReportExcel");
		serviceIdAllActionsMap.put(10, "reportfilefinder,viewBlacklist,searchBlacklist,printBlacklistReport,printBlacklistReportExcel");
		
		//		11 Police			
		//		12	Review Application
		//		13	Add Application
		serviceIdAllActionsMap.put(11, "");	
		serviceIdAllActionsMap.put(12, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,updateAddress,loadEditAddress,updateAddressPolice,loadAddressListClearence,updateAddressEditPolice,viewPrintList,searchPrintList,loadSelectedApplicationList");
		serviceIdAllActionsMap.put(13, "application, loadPreviousApplication, verifyApplication, saveApplication, uploadFilesForApplication, loadCommissionersByCountry, loadCommissionerById");
	
		//		14	CID
		//		15	Review Application
		serviceIdAllActionsMap.put(14, "");
		serviceIdAllActionsMap.put(15, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,updatePoliceMessage,updateAddress,loadEditAddress,updateAddressPolice,loadAddressListClearence,updateAddressEditPolice,viewPrintList,searchPrintList,loadSelectedApplicationList");
		
		//		16	TID
		//		17	Review Application
		serviceIdAllActionsMap.put(16, "");
		serviceIdAllActionsMap.put(17, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList");
		
		//		18	SIS
		//		19	Review Application
		serviceIdAllActionsMap.put(18, "");
		serviceIdAllActionsMap.put(19, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,viewPrintList,searchPrintList,loadSelectedApplicationList");

		//		20	NIC
		//		21	Review Application
		serviceIdAllActionsMap.put(20, "");
		serviceIdAllActionsMap.put(21, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList");
		
		//		22	Immigration Department
		//		23	Application Verification
		serviceIdAllActionsMap.put(22, "");
		serviceIdAllActionsMap.put(23, "viewReviewApplication,searchReviewApplication,updateClearenceStatus,checkAndLockCooRecord,checkAndRemoveLock,policeFileFinder,updateAdverseClearenceStatus,viewPrintList,searchPrintList,loadSelectedApplicationList");
		
		for (Entry<Integer, String> entry : serviceIdAllActionsMap.entrySet()) {
			String allActions = entry.getValue();
			if (allActions.contains(currentAction)) {
				serviceId = entry.getKey();
				serviceIdList.add(serviceId);
			}
		}
		LOGGER.warn("serviceIdList SIZE ====================> " + serviceIdList.size());
		for (Integer serviceeId : serviceIdList) {
			LOGGER.warn("* serviceeId - " + serviceeId);
		}
		return serviceIdList;
	}	
}
