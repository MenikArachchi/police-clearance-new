package lk.icta.slprest.business;

import java.sql.Connection;
import java.sql.SQLException;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.RestApplicationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;

import org.apache.log4j.Logger;

public class RestApplicationBusiness {
	private static final Logger LOGGER = Logger.getLogger(RestApplicationBusiness.class);
	private static RestApplicationBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static RestApplicationBusiness getInstance() {
		synchronized (RestApplicationBusiness.class) {
			if (instance == null) {
				instance = new RestApplicationBusiness();
			}
			return instance;
		}
	}
	
    private RestApplicationBusiness() {
		
	}
    
    public String checkApplicationStatus(String referenceNo) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return RestApplicationDAO.getInstance().checkApplicationStatus(con, referenceNo);
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

    public int isExistsApplication(String referenceNo) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return ApplicationDAO.getInstance().isExistsApplication(con, referenceNo);
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
    
    public int clearanceCertificateVerification(String nicNo, String passportNo, String certificateNo) {
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			return RestApplicationDAO.getInstance().clearanceCertificateVerification(con, nicNo, passportNo, certificateNo);
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
}
