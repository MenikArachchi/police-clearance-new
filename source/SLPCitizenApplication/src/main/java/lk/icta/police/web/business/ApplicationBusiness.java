package lk.icta.police.web.business;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.AddressType;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.DateUtil;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public ApplicationVO getApplication(String referenceNo){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getApplication(con, referenceNo);
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
	
	public ApplicationVO getApplicationByApplicationId(long applicationId){
		Connection con = null;
		ApplicationVO applicationVO = new ApplicationVO();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			applicationVO =  ApplicationDAO.getInstance().getApplicationById(applicationId, con);

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
	
	public ApplicationVO getApplicationWithAddresses(String referenceNo, String userEmail){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			
			ApplicationVO applicationVO = ApplicationDAO.getInstance().getCitizenApplicationView(conn, referenceNo, userEmail);
			
			if(applicationVO != null){
				List<AddressVO> certificateAddressList = AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(), AddressType.AC.toString(), conn);
				applicationVO.allocateFileTypes();
				applicationVO.setApplicantCertificateAddresses(certificateAddressList);
				
				return applicationVO;
			}else{
				return null;
			}
			
		} catch (BusinessException e) {
			LOGGER.error(e);

		} catch (SQLException e) {
			LOGGER.error(e);
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
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
	
	public boolean verifyApplication(String nicNo, String newNicNo, String passportNo, long country){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().verifyApplication(con, nicNo,newNicNo, passportNo, country);
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
						addressVO.setPoliceStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
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
	
	public CountryVO getCountryVO(long countryId){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getCountryVO(con, countryId);
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
	
	public List<PoliceAreaVO> getPoliceAreaList(){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			List<PoliceAreaVO> areaVOs= ApplicationDAO.getInstance().getPoliceAreaList(con);
			Collections.sort(areaVOs,new Comparator<PoliceAreaVO>(){
			   @Override
			   public int compare(final PoliceAreaVO lhs,PoliceAreaVO rhs) {
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
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(con);
		}
		return null;
	}
	
	public String getUserEmailByReferenceNo(String referenceNo){
		Connection con = null;
		String userEmail = "";
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			userEmail =  ApplicationDAO.getInstance().getUserEmailByReferenceNo(referenceNo, con);
			
			return userEmail;
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
		return userEmail;
	}
	
	public ApplicationVO getApplicationByReferenceNoAndEmail(String referenceNo, String userEmail){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getApplicationByReferenceNoAndEmail(referenceNo, userEmail, con);
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

	public String updateReviewStatus (long applicationId, String reviewStatus) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			ApplicationVO applicationVO=ApplicationDAO.getInstance().getApplicationById(applicationId, con);
			status= ApplicationDAO.getInstance().updateReviewStatus(applicationVO , reviewStatus,  con);
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
	
	public ApplicationVO getApplicationByReferenceNumber(String referenceNo) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            return ApplicationDAO.getInstance().getApplication(conn, referenceNo);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(conn);
        }
        return null;
    }
}
