package lk.icta.police.external.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.AddressType;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.BlackListDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.DateUtil;
import lk.icta.police.framework.vo.AddressChangeAuditVO;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationClearanceDate;
import lk.icta.police.framework.vo.ApplicationModifiedDatesVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ApplicationVerificationSearchCriteriaVO;
import lk.icta.police.framework.vo.ChangeAuditVO;
import lk.icta.police.framework.vo.CommissionerVO;
import lk.icta.police.framework.vo.CountryVO;
import lk.icta.police.framework.vo.PoliceAreaVO;
import lk.icta.police.framework.vo.RequestClarificationVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ApplicationBusiness {

	private static final Logger LOGGER = Logger.getLogger(ApplicationBusiness.class);
	private static ApplicationBusiness instance = null;
	private static String datePattern = "dd/MM/yyyy";
	/**
	 * Singleton Implementation
	 * 
	 */
	public static ApplicationBusiness getInstance() {
		synchronized (ApplicationBusiness.class) {
			if (instance == null) {
				instance = new ApplicationBusiness();
			}
			return instance;
		}
	}
	
    private ApplicationBusiness() {
		
	}
    
    public long addApplication(ApplicationVO applicationVO){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			long applicationId = ApplicationDAO.getInstance().addApplication(con, applicationVO);
			
			
			System.out.println("------"+applicationId);
			System.out.println(applicationVO);
			
			boolean addressAdded = false;
			boolean resAddressAdded = false;
			boolean spousAddressAdded = false;
			if(applicationId != 0){
				
				System.out.println("applicationId :" + applicationId);
				
				if(!(applicationVO.getApplicantCertificateAddresses()==null || applicationVO.getApplicantCertificateAddresses().isEmpty())){
					for (AddressVO addressVO : applicationVO.getApplicantCertificateAddresses()) {
						addressVO.setType(PoliceEnumConstant.AddressType.AC.toString());
						addressVO.setFromDate(DateUtil.convertStringToDate(addressVO.getFromDateStr(), datePattern));
						addressVO.setToDate(DateUtil.convertStringToDate(addressVO.getToDateStr(), datePattern));
						addressVO.setApplicationId(applicationId);
					}
				}
				
				addressAdded = AddressDAO.getInstance().addAddressList(con, applicationVO.getApplicantCertificateAddresses());
				
				System.out.println("-----addressAdded-----"+addressAdded);
				
				if(addressAdded){
					con.commit();
					return applicationId;
				}
			}
			con.rollback();
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return 0;
	}
    
    public List<ApplicationVO> getAllocationList(ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO, int userId) {
		Connection con = null;
		List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
		int requestClarificationCount = 0;
		int blackListedCount = 0;
		
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			applicationVOs =  ApplicationDAO.getInstance().fetchApplicationVerificationList(applicationVerificationSearchCriteriaVO, con);

			for (ApplicationVO applicationVO : applicationVOs) {
				
				requestClarificationCount = RequestClarificationBusiness.getInstance().countRequestClarificationForApplication(applicationVO.getApplicationId(), con);
				LOGGER.info("requestClarificationCount -> " + requestClarificationCount + " getApplicationReviewStatus -> " + applicationVO.getApplicationReviewStatus());
				if(requestClarificationCount == 0 
						&& applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RC.toString())) {
					applicationVO.setHasRequestClarification(1);
				} else if(requestClarificationCount == 1 
						&& applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RV.toString())) {
					applicationVO.setHasRequestClarification(2);
					
					RequestClarificationVO requestClarificationVO = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept(applicationVO.getReferenceNo());
					LOGGER.info("getReviewedUserId=================== " + requestClarificationVO.getReviewedUserId());
					//Update the request to update in to application table and then mark review user id in to request clarification
					if(requestClarificationVO.getReviewedUserId() != 0) {
						applicationVO.setHasRequestClarification(3);
					}
				} else {
					//LOGGER.info("getApplicationReviewStatus=================== " + applicationVO.getApplicationReviewStatus());
					/*if(applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RV.toString())) {
						RequestClarificationVO requestClarificationVO = RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept(applicationVO.getReferenceNo());
						LOGGER.info("getReviewedUserId=================== " + requestClarificationVO.getReviewedUserId());
						//Update the request to update in to application table and then mark review user id in to request clarification
						if(requestClarificationVO.getReviewedUserId() != 0) {
							applicationVO.setHasRequestClarification(3);
						}
					} else {*/
						applicationVO.setHasRequestClarification(0);
				/*	}*/
					
				}
				
				
				if(userId == applicationVO.getPhqRecordLockStatus()){
					applicationVO.setHasCurrentUserLocked(1);
				} else {
					applicationVO.setHasCurrentUserLocked(0);
				}
				
				blackListedCount = countBlackListRecordsByNICOrPPT(applicationVO.getNic(), applicationVO.getPassport(), con);
				
				if(blackListedCount >= 1) {
					applicationVO.setHasBlackListed(1);
				}else {
					applicationVO.setHasBlackListed(0);
				}
				applicationVO.allocateFileTypes();
				
			}
			
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
    
    public String lockPHQRecord(long lockedApplicationId, int lockedUserId) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		String phqLockStatus = PoliceConstant.ERROR;
		try {
			int userCount = countPHQLocs(lockedUserId);
			if(userCount > 0){
				status=PoliceConstant.ONE_RECORD_IS_ALREADY_LOCKED;
			} else {
				phqLockStatus = checkPHQLockStatus(lockedApplicationId);
				if(phqLockStatus.equals(PoliceConstant.SUCCESS)){
					con = DatabaseManager.getPOLDBConnection();
					con.setAutoCommit(false);
					status= ApplicationDAO.getInstance().lockPHQRecord(lockedApplicationId,lockedUserId, con);
					if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
						con.commit();
					}else{
						con.rollback();
					}
					return status;
				} else {
					status = PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
				}
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
		return status;
	}

	public String unlockPHQRecord(long lockedApplicationId) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().unlockPHQRecord(lockedApplicationId, con);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	public String updateReviewStatus (long applicationId, String reviewStatus) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().updateReviewStatus(applicationId , reviewStatus,  con);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}

	public String checkPHQLockStatus(long applicationId) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().checkPHQLockStatus(applicationId , con);
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
	
	public int countPHQLocs(int userId) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().countPHQLocs(con, userId);
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
	
	public ApplicationVO getApplicationByApplicationId(long applicationId){
		Connection con = null;
		ApplicationVO applicationVO = new ApplicationVO();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			applicationVO =  ApplicationDAO.getInstance().getApplicationById(applicationId, con);
			if(!(applicationVO==null)){
				applicationVO.allocateFileTypes();
				applicationVO.constructcertificatepostalAddress();
			}
			return applicationVO;
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
	
	
	public boolean updateCompletedApplication(ApplicationVO applicationVO){
		Connection con = null;
		boolean result;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			result = ApplicationDAO.getInstance().updateCompletedApplication(con, applicationVO);
			con.commit();
			return result;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
			return false;
		} catch (Exception e){
			LOGGER.error(e);
			return false;
		}finally {
			DatabaseManager.close(con);
		}
	}
	
	
	public String moveApplicationToAudit(long applicationId) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	public String updateRequestClarification(RequestClarificationVO requestClarificationVO, long applicationId, String reviewStatus){
		Connection con = null;
		long requestClarificationId=0L;
		String status=PoliceConstant.ERROR;
		
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			requestClarificationId = RequestClarificationBusiness.getInstance().saveRequestClarification(requestClarificationVO);
			status = updateReviewStatus(applicationId, reviewStatus);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status) && requestClarificationId > 0){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}

	public String addChangeAudit(ChangeAuditVO changeAuditVO){
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
			
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<ApplicationVO> loadApplicationToClearanceReport(Date from, Date to){
		Connection con = null;
		List<ApplicationVO> applicationList = null ;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			applicationList = ApplicationDAO.getInstance().loadApplicationToClearanceReport(from, to, con);
			for (ApplicationVO applicationVO : applicationList) {
				applicationVO.constructcertificatepostalAddress();
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
		return applicationList;
	}
	
	public boolean updateApplication(ApplicationVO applicationVO){
		Connection con = null;
		boolean result;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			result = ApplicationDAO.getInstance().updateApplication(con, applicationVO);
			con.commit();
			return result;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
			return false;
		} catch (Exception e){
			LOGGER.error(e);
			return false;
		}finally {
			DatabaseManager.close(con);
		}
	}
	
	public String applicationUpdateForRequest (ApplicationVO applicationVO) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().applicationUpdateForRequest(applicationVO,  con);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	public ApplicationVO getApplicationWithAddresses(String referenceNo){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			
			ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplication(conn, referenceNo);
			
			if(applicationVO != null){
				List<AddressVO> certificateAddressList = AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(), AddressType.AC.toString(), conn);
				
				applicationVO.setApplicantCertificateAddresses(certificateAddressList);
				applicationVO.allocateFileTypes();
				return applicationVO;
			}else{
				return null;
			}
			
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
		}
		return null;
	}
	
	public List<CountryVO> getCountryList(){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getCountryList(con);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}
	
	public int countBlackListRecordsByNICOrPPT(String nic, String passport) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return BlackListDAO.getInstance().countBlackListRecordsByNICOrPPT(con, nic, passport);
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

	public int countBlackListRecordsByNICOrPPT(String nic, String passport, Connection con) {
		try {
			return BlackListDAO.getInstance().countBlackListRecordsByNICOrPPT(con, nic, passport);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<PoliceAreaVO> getPoliceAreaList(){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getPoliceAreaList(con);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}
	
	public List<CommissionerVO> getCommissionerVOListByCountryId(long countryId){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getCommissionerVOListByCountryId(con, countryId);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}
	
	public boolean verifyApplication(String nicNo, String passportNo, long country){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().verifyApplication(con, nicNo, passportNo, country);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return false;
	}
	
	public CommissionerVO getCommissionerVOById(long commissionerId){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getCommissionerVO(con, commissionerId);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}
	
	public ApplicationVO getAuthApplicationWithAddresses(String referenceNo, String userEmail, String authProvider){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			
			ApplicationVO applicationVO = ApplicationDAO.getInstance().getAuthApplication(conn, referenceNo);
			
			if(applicationVO != null){
				List<AddressVO> certificateAddressList = AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(), AddressType.AC.toString(), conn);
				
				applicationVO.setApplicantCertificateAddresses(certificateAddressList);
				
				return applicationVO;
			}else{
				return null;
			}
			
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<ApplicationVO> loadPreviousAdverseRecords(long applicationId){
		Connection con = null;
		List<ApplicationVO> applicationList = null ;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			ApplicationVO applicationVO=ApplicationDAO.getInstance().getApplicationById(applicationId, con);
			if(!(applicationVO==null)){
				applicationList = ApplicationDAO.getInstance().loadPreviousAdverseRecords(applicationVO.getNic(), applicationVO.getPassport(), applicationId, con);
				for (ApplicationVO application : applicationList) {
					application.constructcertificatepostalAddress();
				}
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
		return applicationList;
	}
	
	public List<AddressVO> getAddressListByUserRole(long applicationId, int userRole, long locationId, Connection conn) {
		boolean hasCreated=false;
		List<AddressVO> addressVOs=null;
		try {
			if(conn==null){
				hasCreated=true;
				conn = DatabaseManager.getPOLDBConnection();
				conn.setAutoCommit(false);
			}		
			
			if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
				addressVOs= AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,PoliceEnumConstant.AddressType.AC.toString(),conn);
					
			}	
			
			if(!(addressVOs==null)){
				for (AddressVO addressVO : addressVOs) {
					if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
						//check and add if an active temp address is avalable;
						AddressTempVO addressTempVO=AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), conn);
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
			if(hasCreated){
				DatabaseManager.close(conn);
			}			
		}
		return addressVOs;
	}
	
	public List<ChangeAuditVO> loadChangeAuditByReferenceNo(String referenceNo){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().loadChangeAuditByReferenceNo(referenceNo,con);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}

	public String updateCerificateSerialNumber(long applicationId,String certificateCerialNo) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().updateCerificateSerialNumber(applicationId,certificateCerialNo,  con);
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				con.commit();
			}else{
				con.rollback();
			}
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	public String saveApplicationClearanceDate(long applicationId, int userId){
		String status = PoliceConstant.ERROR;
		List<AddressVO> addressVOs = null;
		Connection con = null;
		ApplicationClearanceDate applicationClearanceDate = null;
		String policeDeptStatus = PoliceConstant.ERROR;
		String phqDeptStatus = PoliceConstant.ERROR;
		String deptStatus = PoliceConstant.ERROR;
		
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			addressVOs = AddressDAO.getInstance().getAddressList(con, applicationId);
			
			if(!(addressVOs==null)){
				for (AddressVO addressVO : addressVOs) {
					applicationClearanceDate = constructApplicationClearanceDateVO(applicationId, PoliceEnumConstant.UserDepartment.POL.toString(), 
							userId, PoliceEnumConstant.ExternalClearanceSendType.SND.toString(),null);
					applicationClearanceDate.setAddressId(addressVO.getAddressId());
					policeDeptStatus = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(applicationClearanceDate, con);
					LOGGER.warn("POLICE DEPT STATUS -> " + policeDeptStatus);
					if(policeDeptStatus == PoliceConstant.ERROR) {
						break;
					}
					
					applicationClearanceDate = constructApplicationClearanceDateVO(applicationId, PoliceEnumConstant.UserDepartment.PHQ.toString(), userId,
							PoliceEnumConstant.ExternalClearanceSendType.SND.toString(),null);
					applicationClearanceDate.setAddressId(addressVO.getAddressId());
					phqDeptStatus = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(applicationClearanceDate, con);
					LOGGER.warn("PHQ DEPT STATUS -> " + phqDeptStatus);
					if(policeDeptStatus == PoliceConstant.ERROR) {
						break;
					}
					
				}
			}
			
			Map<Integer,PoliceEnumConstant.UserDepartment> userDepartments = PoliceEnumConstant.UserDepartment.getLookup();

			for (Map.Entry<Integer,PoliceEnumConstant.UserDepartment> entry : userDepartments.entrySet()){
			    
			    if(!((StringUtils.equals(entry.getValue().toString(), PoliceEnumConstant.UserDepartment.POL.toString())) || 
			    		(StringUtils.equals(entry.getValue().toString(), PoliceEnumConstant.UserDepartment.PHQ.toString())))) {
			    	System.out.println(entry.getKey() + " / " + entry.getValue().toString());
			    	applicationClearanceDate = constructApplicationClearanceDateVO(applicationId, entry.getValue().toString(), userId,
			    			PoliceEnumConstant.ExternalClearanceSendType.SND.toString(),null);
			    	deptStatus = ApplicationDAO.getInstance().addApplicationClearanceDateDept(applicationClearanceDate, con);
			    	if(deptStatus == PoliceConstant.ERROR) {
						break;
					}
			    }
			    LOGGER.warn("DEPT STATUS -> " + deptStatus);
			}
			
			if(StringUtils.equals(PoliceConstant.SUCCESS, policeDeptStatus) && StringUtils.equals(PoliceConstant.SUCCESS, deptStatus)){
				con.commit();
			}else{
				con.rollback();
			}
			
			status = PoliceConstant.SUCCESS;
		} catch (BusinessException e) {
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return status;
	}
	
	private ApplicationClearanceDate constructApplicationClearanceDateVO(long applicationId, String department, int userId, String sendType, String comment) {
		ApplicationClearanceDate applicationClearanceDate = new ApplicationClearanceDate();
		
		applicationClearanceDate.setApplicationId(applicationId);
		applicationClearanceDate.setDepartment(department);
		applicationClearanceDate.setSentDate(new Date());
		applicationClearanceDate.setPrintedStatus(0);
		applicationClearanceDate.setSentUserId(userId);
		applicationClearanceDate.setType(sendType);
		applicationClearanceDate.setComment(comment);
		return applicationClearanceDate;
		
	}
	
	
	public String addApplicationModifiedDate(long applicationId, String dateType, String modification, int modifiedUserId, String modifiedUserName, Connection con){
		String status = PoliceConstant.ERROR;
		boolean hasConnCreated=false;
		try {
			if(con==null){
				hasConnCreated=true;
				con = DatabaseManager.getPOLDBConnection();
				con.setAutoCommit(false);
			}
			
			ApplicationModifiedDatesVO modifiedDate=new ApplicationModifiedDatesVO();
			modifiedDate.setApplicationId(applicationId);
			modifiedDate.setDateType(dateType);
			modifiedDate.setModification(modification);
			modifiedDate.setModifiedUserId(modifiedUserId);
			modifiedDate.setModifiedUserName(modifiedUserName);
			
			status=ApplicationDAO.getInstance().addApplicationModifiedDate(modifiedDate, con);
			
			if(hasConnCreated){
				if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
					con.commit();
				}else{
					con.rollback();
				}
			}
			
			status = PoliceConstant.SUCCESS;
		} catch (BusinessException e) {
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			status = PoliceConstant.ERROR;
			LOGGER.error(e);
		}finally {
			if(hasConnCreated){
				DatabaseManager.close(con);
			}
		}
		return status;
	}
	
	public List<AddressVO> getAddressListByApplicationId(long applicationId){
		Connection con = null;
		List<AddressVO> addressVOs=new ArrayList<AddressVO>();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			addressVOs=AddressDAO.getInstance().getAddressList(con, applicationId);
			return addressVOs;
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
	
	public List<AddressChangeAuditVO> getAddressChangeAuditListByApplicationId(long applicationId){
		Connection con = null;
		List<AddressChangeAuditVO> addressChangeAuditVOs=new ArrayList<AddressChangeAuditVO>();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			List<AddressVO> addressVOs=AddressDAO.getInstance().getAllAddressList(con, applicationId);
			if(!(addressVOs==null || addressVOs.isEmpty())){
				for (AddressVO addressVO : addressVOs) {
					long addressId=addressVO.getAddressId();
					List<AddressChangeAuditVO> changeAuditVOs=AddressDAO.getInstance().getAddressChangeAuditVOsByAddressId(addressId,con);
					if(!(changeAuditVOs==null || changeAuditVOs.isEmpty())){
						for (AddressChangeAuditVO addressChangeAuditVO : changeAuditVOs) {
							addressChangeAuditVO.setAddress(addressVO.getAddress());
							addressChangeAuditVO.setPoliceArea(addressVO.getPoliceArea());
							addressChangeAuditVOs.add(addressChangeAuditVO);
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
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return addressChangeAuditVOs;
	}
	
	
	public List<ApplicationClearanceDate> getApplicationClearanceDateListByApplicationId(long applicationId,Connection con){
		List<ApplicationClearanceDate> applicationClearanceDates=new ArrayList<ApplicationClearanceDate>();
		boolean hasConCreated=false;
		try {
			if(con==null){
				hasConCreated=true;
				con = DatabaseManager.getPOLDBConnection();
				con.setAutoCommit(false);
			}
			applicationClearanceDates=AddressDAO.getInstance().getApplicationClearanceDateListByApplicationId(con, applicationId);
			return applicationClearanceDates;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(hasConCreated){
				DatabaseManager.close(con);
			}
		}
		return null;
	}

	public String modifyAddressesOfApplication(List<AddressVO> certificateAddressList, long applicationId,int userId, String userName) {
		Connection con = null;
		String returnString=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			//first separate the new addresses and the old addresses
			List<AddressVO> newlyAddedAddressList=new ArrayList<AddressVO>();
			List<AddressVO> oldModifiedAddressList=new ArrayList<AddressVO>();
			
			for (AddressVO addressVO : certificateAddressList) {
				if(!(addressVO==null || StringUtils.isEmpty(addressVO.getAddress()))){
					if(addressVO.getAddressId()<=0){
						//this is a new address
						newlyAddedAddressList.add(addressVO);
					}else{
						//this is an old one
						oldModifiedAddressList.add(addressVO);
					}
				}				
			}
			
			String status=PoliceConstant.ERROR;
			
			//first do modifications/remove the old addresses
			List<AddressVO> removedOldAddedAddressList=new ArrayList<AddressVO>();
			List<AddressVO> modifiedOldAddedAddressList=new ArrayList<AddressVO>();
			SimpleDateFormat format=new SimpleDateFormat(datePattern);
			List<AddressVO> currentAddressListFromDb=AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId, PoliceEnumConstant.AddressType.AC.toString(), con);
			for (AddressVO addressVOFromDb : currentAddressListFromDb) {
				boolean hasRemoved=true;
				StringBuilder modificationComment=new StringBuilder("The address was modified; ");
				for (AddressVO addressVOModified : oldModifiedAddressList) {
					addressVOModified.setFromDate(DateUtil.convertStringToDate(addressVOModified.getFromDateStr(), datePattern));
					addressVOModified.setToDate(DateUtil.convertStringToDate(addressVOModified.getToDateStr(), datePattern));
					if(addressVOFromDb.getAddressId()==addressVOModified.getAddressId()){
						hasRemoved=false;
						boolean hasModified=false;
						//check if the address has been modified
						 if(!(StringUtils.equals(addressVOFromDb.getAddress(), addressVOModified.getAddress()))){
						    modificationComment.append(" address was changed from ").append(addressVOFromDb.getAddress())
						    		.append(" to ").append(addressVOModified.getAddress()).append(". ");
							hasModified=true;
			  			 }
		  				 if(!(addressVOFromDb.getPoliceAreaId()==addressVOModified.getPoliceAreaId())){
		  					modificationComment.append(" police area was changed from ").append(addressVOFromDb.getPoliceArea())
					    		.append(" to ").append(addressVOModified.getPoliceArea()).append(". ");
		  					hasModified=true;
			  			 }
		  				 if(!((addressVOFromDb.getFromDate().compareTo(addressVOModified.getFromDate()))==0)){
		  					modificationComment.append(" from date area was changed from ").append(format.format(addressVOFromDb.getFromDate()))
				    		.append(" to ").append(format.format(addressVOModified.getFromDate())).append(". ");
		  					hasModified=true;
			  			 }
		  				 if(!((addressVOFromDb.getToDate().compareTo(addressVOModified.getToDate()))==0)){
		  					modificationComment.append(" to date area was changed from ").append(format.format(addressVOFromDb.getToDate()))
				    		.append(" to ").append(format.format(addressVOModified.getToDate())).append(". ");
		  					hasModified=true;
		  				 }
						
						if(hasModified){
							addressVOModified.setModificationComment(modificationComment.toString());
							modifiedOldAddedAddressList.add(addressVOModified);
						}
					}
				}
				if(hasRemoved){
					StringBuilder comment=new StringBuilder("The address with values; address:").append(addressVOFromDb.getAddress())
							.append(", police area:").append(addressVOFromDb.getPoliceArea())
							.append(", from date:").append(format.format(addressVOFromDb.getFromDate()))
							.append(", to date:").append(format.format(addressVOFromDb.getToDate())).append(" was removed from application!");
					addressVOFromDb.setModificationComment(comment.toString());
					removedOldAddedAddressList.add(addressVOFromDb);
				}
			}
			
			System.out.println("modifiedOldAddedAddressList :" + modifiedOldAddedAddressList);
			System.out.println("removedOldAddedAddressList :" + removedOldAddedAddressList);
			System.out.println("newlyAddedAddressList :" + newlyAddedAddressList);
			
			//check for modified addresses and update database
			if(!(modifiedOldAddedAddressList==null || modifiedOldAddedAddressList.isEmpty())){
				//there are modified addresses, update the database
				for (AddressVO addressVO : modifiedOldAddedAddressList) {
					status=AddressDAO.getInstance().updateAddressModifications(addressVO,userId,userName,con);
					if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
						AddressChangeAuditVO changeAuditVO=new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, addressVO.getModificationComment());
						status=AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
						if(!(StringUtils.equals(status, PoliceConstant.SUCCESS))){
							break;
						}
					}else{
						break;
					}
				}
			}
			
			
			//check for removed addresses and update database
			if(!(removedOldAddedAddressList==null || removedOldAddedAddressList.isEmpty())){
				//there are removed addresses, update the database
				for (AddressVO addressVO : removedOldAddedAddressList) {
					status=AddressDAO.getInstance().removeAddressFromApplication(addressVO.getAddressId(), userId, userName, con);
					if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
						AddressChangeAuditVO changeAuditVO=new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, addressVO.getModificationComment());
						status=AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, con);
						if(!(StringUtils.equals(status, PoliceConstant.SUCCESS))){
							break;
						}
					}else{
						break;
					}
				}
			}
			
			//add new addresses to database
			if(!(newlyAddedAddressList==null || newlyAddedAddressList.isEmpty())){
				for (AddressVO addressVO : newlyAddedAddressList) {
					addressVO.setType(PoliceEnumConstant.AddressType.AC.toString());
					addressVO.setFromDate(DateUtil.convertStringToDate(addressVO.getFromDateStr(), datePattern));
					addressVO.setToDate(DateUtil.convertStringToDate(addressVO.getToDateStr(), datePattern));
					addressVO.setApplicationId(applicationId);
				}
				boolean hasAdded = AddressDAO.getInstance().addAddressList(con, newlyAddedAddressList);
				if(hasAdded){
					status=PoliceConstant.SUCCESS;
				}else{
					status=PoliceConstant.ERROR;
				}
				System.out.println("-----new addressAdded status----- "+ status);
			}
			
			
			if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
				returnString=PoliceConstant.SUCCESS;
				con.commit();
			}else{
				returnString=PoliceConstant.ERROR;
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
	
	
}
