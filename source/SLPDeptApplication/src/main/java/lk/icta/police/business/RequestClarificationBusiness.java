package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.dao.RequestClarificationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.RequestClarificationVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RequestClarificationBusiness {

	private static final Logger LOGGER = Logger.getLogger(RequestClarificationBusiness.class);
	private static RequestClarificationBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static RequestClarificationBusiness getInstance() {
		synchronized (RequestClarificationBusiness.class) {
			if (instance == null) {
				instance = new RequestClarificationBusiness();
			}
			return instance;
		}
	}
	
    private RequestClarificationBusiness() {
		
	}

    public int countRequestClarificationForApplication(long applicationId,Connection con) {
		boolean doClose=false;
		try {
			if(con==null){
				doClose=true;
				con = DatabaseManager.getPOLDBConnection();
				con.setAutoCommit(false);
			}
			return RequestClarificationDAO.getInstance().countRequestClarificationForApplication(con, applicationId);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			if(doClose){
				DatabaseManager.close(con);
			}
		}
		return 0;
	}
    
    public long saveRequestClarification (RequestClarificationVO requestClarificationVO,Connection con) {
		long requestClarificationId=0L;
		boolean doClose=false;
		try {
			if(con==null){
				doClose=true;
				con = DatabaseManager.getPOLDBConnection();
				con.setAutoCommit(false);
			}
			requestClarificationId= RequestClarificationDAO.getInstance().saveRequestClarification(requestClarificationVO,  con);
			if(doClose){
				if(requestClarificationId > 0){
					con.commit();
				}else{
					con.rollback();
				}
			}
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			if(doClose){
				DatabaseManager.close(con);
			}
		}
		return requestClarificationId;
	}
    
    public RequestClarificationVO getRequestClarificationByReferenceNoForDept(String referenceNo) {
		Connection con = null;
		RequestClarificationVO requestClarificationVO = new RequestClarificationVO();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			requestClarificationVO =  RequestClarificationDAO.getInstance().getRequestClarificationByReferenceNoForDept(referenceNo, con);
			
			requestClarificationVO.allocateFileTypes();
			
			return requestClarificationVO;
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
    
    public String updateRequestClarification (RequestClarificationVO requestClarificationVO,Connection con) {
		boolean doClose=false;
		String status=PoliceConstant.ERROR;
		try {
			if(con==null){
				doClose=true;
				con = DatabaseManager.getPOLDBConnection();
				con.setAutoCommit(false);
			}
			status= RequestClarificationDAO.getInstance().updateRequestClarification(requestClarificationVO,  con);
			if(doClose){
				if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
					con.commit();
				}else{
					con.rollback();
				}
			}
			
			return status;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			if(doClose){
				DatabaseManager.close(con);
			}
		}
		return status;
	}
}
