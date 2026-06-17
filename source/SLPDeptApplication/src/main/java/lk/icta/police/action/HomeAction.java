package lk.icta.police.action;

import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.DepartmentDashboardBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -8517227229292215672L;
	private static final Logger LOGGER = Logger.getLogger(HomeAction.class);
	private Map<String, Object> session;
	
	private int countApplicationsToBeVerified;
	private int countResubmissionPending;
	private int countRevisionUpdatesPending;
	private int countExternalClearancePending;
	private int countPoliceClearancePending;
	private int countOICApprovalPending;
	private int countASPApprovalPending;
	private int countDIGApprovalPending;
	private int countDHAApprovalPending;
	private int countCertificatePrintingPending;
	private int countCertificateToBePosted;
	
	private int countCertificateCheckingPendingNoAdverse;
	private int countCertificateCheckingPendingAdverse;
	
	private int countExternalClearancePendingCid;
	private int countExternalClearancePendingTid;
	private int countExternalClearancePendingSis;
	private int countExternalClearancePendingNic;
	private int countExternalClearancePendingImi;
	
	public String execute(){
		LOGGER.warn("HomeAction --> execute --> START");
		//session.put("countApplicationsToBeVerified", DepartmentDashboardBusiness.getInstance().countApplicationsToBeVerified());
		//session.put("countResubmissionPending", DepartmentDashboardBusiness.getInstance().countResubmissionPending());
		//session.put("countRevisionUpdatesPending", DepartmentDashboardBusiness.getInstance().countRevisionUpdatesPending());

//		session.put("countExternalClearancePending", DepartmentDashboardBusiness.getInstance().countExternalClearancePending(PoliceEnumConstant.UserDepartment.fromCode(userRole).toString()));
//		session.put("countPoliceClearancePending", DepartmentDashboardBusiness.getInstance().countPoliceClearancePending());
//		session.put("countCertificateCheckingPending", DepartmentDashboardBusiness.getInstance().countCertificateCheckingPending());
//		session.put("countOICApprovalPending", DepartmentDashboardBusiness.getInstance().countOICApprovalPending());
//		session.put("countASPApprovalPending", DepartmentDashboardBusiness.getInstance().countASPApprovalPending());
//		session.put("countCertificatePrintingPending", DepartmentDashboardBusiness.getInstance().countCertificatePrintingPending());
//		session.put("countCertificateToBePosted", DepartmentDashboardBusiness.getInstance().countCertificateToBePosted());
		LOGGER.warn("HomeAction --> execute --> END");
		return SUCCESS;
	}
	
	public String listCountApplicationsToBeVerified(){
		countApplicationsToBeVerified=DepartmentDashboardBusiness.getInstance().countApplicationsToBeVerified();
		return SUCCESS;		
	}
	
	public String listCountResubmissionPending(){
		countResubmissionPending=DepartmentDashboardBusiness.getInstance().countResubmissionPending();
		return SUCCESS;		
	}
	
	public String listCountRevisionUpdatesPending(){
		countRevisionUpdatesPending=DepartmentDashboardBusiness.getInstance().countRevisionUpdatesPending();
		return SUCCESS;		
	}
	
	public String listCountExternalClearancePending(){
		int userRole=getUserRoleFromSession();
		LOGGER.info("USER ROLE -> "+userRole);
		LOGGER.info("USER ROLE STR-> " + PoliceEnumConstant.UserDepartment.fromCode(userRole).toString());
		countExternalClearancePending=DepartmentDashboardBusiness.getInstance().countExternalClearancePending();
		return SUCCESS;		
	}
	
	public String listCountPoliceClearancePending(){
		countPoliceClearancePending=DepartmentDashboardBusiness.getInstance().countPoliceClearancePending();
		return SUCCESS;		
	}
	
	
	public String listCountInternalApprovalPending(){
		Map<String, Integer> map=DepartmentDashboardBusiness.getInstance().countInternalApprovalPending();
		countOICApprovalPending=map.get("OIC_COUNT");
		countASPApprovalPending=map.get("ASP_COUNT");
		countDIGApprovalPending=map.get("DIG_COUNT");
		countDHAApprovalPending=map.get("DHA_COUNT");
		countCertificateToBePosted=map.get("POSTAL_COUNT");
		countCertificateCheckingPendingNoAdverse=map.get("NO_ADVERSE_COUNT");
		countCertificateCheckingPendingAdverse=map.get("ADVERSE_COUNT");
		return SUCCESS;		
	}
	
	
	public String listCountCertificatePrintingPending(){
		countCertificatePrintingPending=DepartmentDashboardBusiness.getInstance().countCertificatePrintingPending();
		return SUCCESS;		
	}
	
	
	public String listCountExternalClearancePendingByDepartment(){
		this.countExternalClearancePendingCid=DepartmentDashboardBusiness.getInstance().countExternalClearancePendingByDepartment(PoliceEnumConstant.UserDepartment.CID.toString());
		this.countExternalClearancePendingTid=DepartmentDashboardBusiness.getInstance().countExternalClearancePendingByDepartment(PoliceEnumConstant.UserDepartment.TID.toString());
		this.countExternalClearancePendingSis=DepartmentDashboardBusiness.getInstance().countExternalClearancePendingByDepartment(PoliceEnumConstant.UserDepartment.SIS.toString());
		this.countExternalClearancePendingNic=DepartmentDashboardBusiness.getInstance().countExternalClearancePendingByDepartment(PoliceEnumConstant.UserDepartment.NIC.toString());
		this.countExternalClearancePendingImi=DepartmentDashboardBusiness.getInstance().countExternalClearancePendingByDepartment(PoliceEnumConstant.UserDepartment.IMI.toString());
		return SUCCESS;		
	}
	
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private int getUserRoleFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null || userVO.getDept()==null)){
			return userVO.getDept().getId();
		}
		return 0;
	}
	
	private UserVO getUserFromSession(){
		if(!(session.get(PoliceConstant.SESSION_USER)==null)){
			return (UserVO) session.get(PoliceConstant.SESSION_USER);
		}
		return null;
	}

	public int getCountApplicationsToBeVerified() {
		return countApplicationsToBeVerified;
	}

	public void setCountApplicationsToBeVerified(int countApplicationsToBeVerified) {
		this.countApplicationsToBeVerified = countApplicationsToBeVerified;
	}

	public int getCountResubmissionPending() {
		return countResubmissionPending;
	}

	public void setCountResubmissionPending(int countResubmissionPending) {
		this.countResubmissionPending = countResubmissionPending;
	}

	public int getCountRevisionUpdatesPending() {
		return countRevisionUpdatesPending;
	}

	public void setCountRevisionUpdatesPending(int countRevisionUpdatesPending) {
		this.countRevisionUpdatesPending = countRevisionUpdatesPending;
	}

	public int getCountExternalClearancePending() {
		return countExternalClearancePending;
	}

	public void setCountExternalClearancePending(int countExternalClearancePending) {
		this.countExternalClearancePending = countExternalClearancePending;
	}

	public int getCountPoliceClearancePending() {
		return countPoliceClearancePending;
	}

	public void setCountPoliceClearancePending(int countPoliceClearancePending) {
		this.countPoliceClearancePending = countPoliceClearancePending;
	}


	public int getCountCertificateCheckingPendingNoAdverse() {
		return countCertificateCheckingPendingNoAdverse;
	}

	public void setCountCertificateCheckingPendingNoAdverse(
			int countCertificateCheckingPendingNoAdverse) {
		this.countCertificateCheckingPendingNoAdverse = countCertificateCheckingPendingNoAdverse;
	}

	public int getCountCertificateCheckingPendingAdverse() {
		return countCertificateCheckingPendingAdverse;
	}

	public void setCountCertificateCheckingPendingAdverse(
			int countCertificateCheckingPendingAdverse) {
		this.countCertificateCheckingPendingAdverse = countCertificateCheckingPendingAdverse;
	}

	public int getCountOICApprovalPending() {
		return countOICApprovalPending;
	}

	public void setCountOICApprovalPending(int countOICApprovalPending) {
		this.countOICApprovalPending = countOICApprovalPending;
	}

	public int getCountASPApprovalPending() {
		return countASPApprovalPending;
	}

	public void setCountASPApprovalPending(int countASPApprovalPending) {
		this.countASPApprovalPending = countASPApprovalPending;
	}

	public int getCountCertificatePrintingPending() {
		return countCertificatePrintingPending;
	}

	public void setCountCertificatePrintingPending(
			int countCertificatePrintingPending) {
		this.countCertificatePrintingPending = countCertificatePrintingPending;
	}

	public int getCountCertificateToBePosted() {
		return countCertificateToBePosted;
	}

	public void setCountCertificateToBePosted(int countCertificateToBePosted) {
		this.countCertificateToBePosted = countCertificateToBePosted;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getCountExternalClearancePendingCid() {
		return countExternalClearancePendingCid;
	}

	public void setCountExternalClearancePendingCid(
			int countExternalClearancePendingCid) {
		this.countExternalClearancePendingCid = countExternalClearancePendingCid;
	}

	public int getCountExternalClearancePendingTid() {
		return countExternalClearancePendingTid;
	}

	public void setCountExternalClearancePendingTid(
			int countExternalClearancePendingTid) {
		this.countExternalClearancePendingTid = countExternalClearancePendingTid;
	}

	public int getCountExternalClearancePendingSis() {
		return countExternalClearancePendingSis;
	}

	public void setCountExternalClearancePendingSis(
			int countExternalClearancePendingSis) {
		this.countExternalClearancePendingSis = countExternalClearancePendingSis;
	}

	public int getCountExternalClearancePendingNic() {
		return countExternalClearancePendingNic;
	}

	public void setCountExternalClearancePendingNic(
			int countExternalClearancePendingNic) {
		this.countExternalClearancePendingNic = countExternalClearancePendingNic;
	}

	public int getCountExternalClearancePendingImi() {
		return countExternalClearancePendingImi;
	}

	public void setCountExternalClearancePendingImi(
			int countExternalClearancePendingImi) {
		this.countExternalClearancePendingImi = countExternalClearancePendingImi;
	}

	public int getCountDIGApprovalPending() {
		return countDIGApprovalPending;
	}

	public void setCountDIGApprovalPending(int countDIGApprovalPending) {
		this.countDIGApprovalPending = countDIGApprovalPending;
	}

	public int getCountDHAApprovalPending() {
		return countDHAApprovalPending;
	}

	public void setCountDHAApprovalPending(int countDHAApprovalPending) {
		this.countDHAApprovalPending = countDHAApprovalPending;
	}
	
	
}
