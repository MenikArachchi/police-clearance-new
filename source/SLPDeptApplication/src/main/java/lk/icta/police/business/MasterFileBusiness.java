package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.CertificateAuthPersonDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.CertificateAuthPersonVO;
import lk.icta.police.framework.vo.CommissionerVO;
import lk.icta.police.framework.vo.PoliceAreaVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MasterFileBusiness {
	
	private static final Logger LOGGER = Logger.getLogger(MasterFileBusiness.class);
	private static MasterFileBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static MasterFileBusiness getInstance() {
		synchronized (MasterFileBusiness.class) {
			if (instance == null) {
				instance = new MasterFileBusiness();
			}
			return instance;
		}
	}
	
    private MasterFileBusiness() {
		
	}
    
    public long saveCertificateAuthPerson(CertificateAuthPersonVO certificateAuthPersonVO){
		Connection con = null;
		long certificateAuthPersonId=0L;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			certificateAuthPersonId= CertificateAuthPersonDAO.getInstance().saveCertificateAuthPerson(certificateAuthPersonVO,  con);
			if(certificateAuthPersonId > 0){
				con.commit();
			}else{
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
		return certificateAuthPersonId;
	}
    
    public String updateCertificateAuthPerson (long authPersonId, Date endDate) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= CertificateAuthPersonDAO.getInstance().updateCertificateAuthPerson(authPersonId, endDate, con);
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
    
    public int isExistsCommissioner(CommissionerVO commissionerVO) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().isExistsCommissioner(con, commissionerVO);
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
    
    public long saveCommissioner(CommissionerVO commissionerVO){
		Connection con = null;
		long commissionerId=0L;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			commissionerId= ApplicationDAO.getInstance().saveCommissioner(commissionerVO,  con);
			if(commissionerId > 0){
				con.commit();
			}else{
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
		return commissionerId;
	}
    
    public String updateCommissioner (CommissionerVO commissionerVO) {
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().updateCommissioner(commissionerVO, con);
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
    
    public List<CommissionerVO> getCommissionerListByCountry(long countryId){
		Connection con = null;
		List<CommissionerVO> commissionerVOList = null ;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			commissionerVOList = ApplicationDAO.getInstance().getCommissionerListByCountry(countryId, con);
			
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return commissionerVOList;
	}
    
    public boolean deleteCommissionerById (long id) throws Exception {
		Connection con = null;
		boolean status=false;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			status= ApplicationDAO.getInstance().deleteCommissionerById(con, id);
			if(status){
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
    
    public int isExistsCommissionerApplication(long commissionerId) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().isExistsCommissionerApplication(con, commissionerId);
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

	public CommissionerVO getCommissionerById(long highCommisionReferenceId) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().getCommissionerById(con, highCommisionReferenceId);
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
	
	public String savePoliceArea(PoliceAreaVO policeAreaVO){
		Connection con = null;
		String status=PoliceConstant.ERROR;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			long id= ApplicationDAO.getInstance().savePoliceArea(policeAreaVO,  con);
			if(id>0){
				status=PoliceConstant.SUCCESS;
				boolean flag=CommonUserBusiness.getInstance().addLocationMaster(policeAreaVO.getId(),policeAreaVO.getPoliceArea());
				if(flag){
					status=PoliceConstant.SUCCESS;
					con.commit();
				}else{
					con.rollback();
				}			
			}else{
				con.rollback();
			}
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (lk.icta.commonuser.framework.exception.BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return status;
	}
}
