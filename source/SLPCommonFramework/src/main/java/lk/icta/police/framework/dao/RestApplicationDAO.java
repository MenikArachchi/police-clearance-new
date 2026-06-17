package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.database.DatabaseManager;

import org.apache.log4j.Logger;

public class RestApplicationDAO {

	private static RestApplicationDAO instance = null;
	private static final Logger LOGGER = Logger.getLogger(RestApplicationDAO.class);
	
	public static RestApplicationDAO getInstance() {
		synchronized(RestApplicationDAO.class) {
			if (instance == null) {
				instance = new RestApplicationDAO();
			}
			return instance;
		}
	}
	
	private RestApplicationDAO(){
		
	}
	
	public String checkApplicationStatus(Connection conn, String referenceNo) {
		String appStatus=PoliceConstant.ERROR;
		
		appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_SUB);
		
		if(appStatus.trim().equals("")) {
			
			appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_ACC);
			if(appStatus.trim().equals("")) {
				
				appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_VER);
				if(appStatus.trim().equals("")) {
					
					appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_REJ);
					if(appStatus.trim().equals("")) {
						
						appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_DEL);
						if(appStatus.trim().equals("")) {
							
							appStatus = getApplicationStatus(conn, referenceNo, DBConstant.QUERY.SELECT_APPLICATION_STATUS_CHECK_POS);
							if(appStatus.trim().equals("")) {								
//								appStatus = PoliceConstant.ERROR;
								appStatus = PoliceConstant.APP_STATUS_NOT_VISIBLE;
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		return appStatus;
		
	}
	
	public String getApplicationStatus(Connection conn, String referenceNo, String query) {
		
		PreparedStatement pstm = null;
		ResultSet rst = null;
		String appicationStatus="";
		try{
			LOGGER.info("QUERY -> " + query);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, referenceNo);
		
			rst = pstm.executeQuery();
			if(rst != null){
				while(rst.next()){
					appicationStatus = rst.getString("APPLICATION_STATUS");
				}
			}
			LOGGER.info("APPLICATION_STATUS --> " + appicationStatus);
			rst.close();
			pstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return appicationStatus;
	}

	public int clearanceCertificateVerification(Connection conn,String nicNo, String passportNo, String certificateNo) {
		
		PreparedStatement pstm = null;
		ResultSet rst = null;
		int applicationCount=0;
		try{
			LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_CLEARANCE_CERTIFICATE_VERIFICATION);
			pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_CLEARANCE_CERTIFICATE_VERIFICATION);
			pstm.setString(1, nicNo);
			pstm.setString(2, nicNo);
			pstm.setString(3, passportNo);
			pstm.setString(4, certificateNo);
		
			rst = pstm.executeQuery();
			if(rst != null){
				while(rst.next()){
					applicationCount = rst.getInt("APPLICATRION_COUNT");
				}
			}
			LOGGER.info("APPLICATRION_COUNT --> " + applicationCount);
			rst.close();
			pstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return applicationCount;
	}
}
