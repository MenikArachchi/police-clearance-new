package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.RequestClarificationVO;

import org.apache.log4j.Logger;

public class RequestClarificationDAO {

	private static RequestClarificationDAO instance = null;
	private static final Logger LOGGER = Logger.getLogger(RequestClarificationDAO.class);
	
	public static RequestClarificationDAO getInstance() {
		synchronized(RequestClarificationDAO.class) {
			if (instance == null) {
				instance = new RequestClarificationDAO();
			}
			return instance;
		}
	}
	
	private RequestClarificationDAO(){
		
	}
	
	public int countRequestClarificationForApplication(Connection conn,long applicationId) {
		
		PreparedStatement pstm = null;
		ResultSet rst = null;
		int requestClarificationCount=0;
		try{
			pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_REQUEST_CLARIFICATION_COUNT);
			pstm.setLong(1, applicationId);
		
			rst = pstm.executeQuery();
			if(rst != null){
				while(rst.next()){
					requestClarificationCount = rst.getInt("REQUEST_CLARIFICATION_COUNT");
					//LOGGER.info("REQUEST_CLARIFICATION_COUNT WHILE --> " + requestClarificationCount);
				}
			}
			//LOGGER.info("REQUEST_CLARIFICATION_COUNT --> " + requestClarificationCount);
			rst.close();
			pstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return requestClarificationCount;
	}
	
	public long saveRequestClarification(RequestClarificationVO requestClarificationVO, Connection conn) {
		long requestClarificationId = 0l;
		PreparedStatement pstm = null;
		try {
			/*LOGGER.info("resubmitNICCheck      -> " + requestClarificationVO.getNic());
			LOGGER.info("resubmitPassportCheck -> " + requestClarificationVO.getNicMessage());
			LOGGER.info("resubmitNameCheck     -> " + requestClarificationVO.getPassport());
			LOGGER.info("resubmitDOBCheck      -> " + requestClarificationVO.getPassportMessage());
			LOGGER.info("resubmitNIC           -> " + requestClarificationVO.getVerifyName());
			LOGGER.info("resubmitPassport      -> " + requestClarificationVO.getNameMessage());
			LOGGER.info("resubmitName          -> " + requestClarificationVO.getVerifyDateOfBirth());
			LOGGER.info("resubmitDOB           -> " + requestClarificationVO.getDateOfBirthMessage());*/
			LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_REQUEST_CLARIFICATION);
			pstm=conn.prepareStatement(DBConstant.QUERY.ADD_REQUEST_CLARIFICATION, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, requestClarificationVO.getNic());
			pstm.setString(2, requestClarificationVO.getNicMessage());
			pstm.setInt(3, requestClarificationVO.getPassport());
			pstm.setString(4, requestClarificationVO.getPassportMessage());
			pstm.setInt(5, requestClarificationVO.getVerifyName());
			pstm.setString(6, requestClarificationVO.getNameMessage());
			pstm.setInt(7, requestClarificationVO.getVerifyDateOfBirth());
			pstm.setString(8, requestClarificationVO.getDateOfBirthMessage());
			pstm.setString(9, requestClarificationVO.getReferenceNo());
			pstm.setString(10, requestClarificationVO.getNicPath());
			pstm.setString(11, requestClarificationVO.getNicPathBack());
			pstm.setInt(12, requestClarificationVO.getNicAcceptStatus());
			pstm.setString(13, requestClarificationVO.getPassportPath());
			pstm.setString(14, requestClarificationVO.getPassportPathBack());
			pstm.setInt(15, requestClarificationVO.getPassportAcceptStatus());
			pstm.setString(16, requestClarificationVO.getName());
			pstm.setInt(17, requestClarificationVO.getNameAcceptStatus());
			pstm.setDate(18, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getDateOfBirth()));
			pstm.setInt(19, requestClarificationVO.getDateOfBirthAcceptStatus());
			pstm.setString(20, requestClarificationVO.getComment());
			pstm.setInt(21, requestClarificationVO.getRequestedUserId());
			pstm.setString(22, requestClarificationVO.getRequestedUserName());
			pstm.setDate(23, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getRequestedDateTime()));
			pstm.setInt(24, requestClarificationVO.getResubmittedUserId());
			pstm.setString(25, requestClarificationVO.getResubmittedUserName());
			pstm.setDate(26, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getResubmittedDateTime()));
			pstm.setInt(27, requestClarificationVO.getReviewedUserId());
			pstm.setString(28, requestClarificationVO.getReviewedUserName());
			pstm.setDate(29, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getReviewedDateTime()));
			pstm.setLong(30, requestClarificationVO.getApplicationId());
			
			int result = pstm.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstm.getGeneratedKeys();
				while (rs.next()) {
					requestClarificationId = rs.getInt(1);					
				}
			}	
			
			requestClarificationVO.setRequestClarificationId(requestClarificationId);
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(pstm);
		} 
		
		LOGGER.info("REQUEST CLARIFICATION ID --------> " + requestClarificationId);
		return requestClarificationId;
	} 
	
	public RequestClarificationVO getRequestClarificationByReferenceNo(String referenceNo, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rst = null;
		RequestClarificationVO requestClarificationVO=null;
		try {
			//System.out.println("QUERY -> " + DBConstant.QUERY.SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO);
			pstm=conn.prepareStatement(DBConstant.QUERY.SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO);	
			pstm.setString(1, referenceNo);
			pstm.setInt(2, 0);
			rst = pstm.executeQuery();	
			
			while (rst.next()){
		    	requestClarificationVO = new RequestClarificationVO();
		    	requestClarificationVO.setRequestClarificationId(rst.getLong(DBConstant.REQUEST_CLARIFICATION_COL.REQUEST_CLARIFICATION_ID));
		    	requestClarificationVO.setNic(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NIC));
				requestClarificationVO.setNicMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_MESSAGE));
				requestClarificationVO.setPassport(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT));
				requestClarificationVO.setPassportMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_MESSAGE));
				requestClarificationVO.setVerifyName(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.VERIFY_NAME));
				requestClarificationVO.setNameMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NAME_MESSAGE));
				requestClarificationVO.setVerifyDateOfBirth(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.VERIFY_DATE_OF_BIRTH));
				requestClarificationVO.setDateOfBirthMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_MESSAGE));
				requestClarificationVO.setReferenceNo(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REFERENCE_NO));
				requestClarificationVO.setNicPath(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_PATH));
				requestClarificationVO.setNicPathBack(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_PATH_BACK));
				requestClarificationVO.setNicAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NIC_ACCEPT_STATUS));
				requestClarificationVO.setPassportPath(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_PATH));
				requestClarificationVO.setPassportPathBack(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_PATH_BACK));
				requestClarificationVO.setPassportAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_ACCEPT_STATUS));
				requestClarificationVO.setName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NAME));
				requestClarificationVO.setNameAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NAME_ACCEPT_STATUS));
				requestClarificationVO.setDateOfBirth(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH));
				requestClarificationVO.setDateOfBirthAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_ACCEPT_STATUS));
				requestClarificationVO.setComment(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.COMMENT));
				requestClarificationVO.setRequestedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_USER_ID));
				requestClarificationVO.setRequestedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_USER_NAME));
				requestClarificationVO.setRequestedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_DATE_TIME));
				requestClarificationVO.setResubmittedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID));
				requestClarificationVO.setResubmittedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_NAME));
				requestClarificationVO.setResubmittedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_DATE_TIME));
				requestClarificationVO.setReviewedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_USER_ID));
				requestClarificationVO.setReviewedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_USER_NAME));
				requestClarificationVO.setReviewedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_DATE_TIME));
				requestClarificationVO.setApplicationId(rst.getLong(DBConstant.REQUEST_CLARIFICATION_COL.APPLICATION_ID));
		    }
			rst.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return requestClarificationVO;
	}
	
	public String updateRequestClarification (RequestClarificationVO requestClarificationVO, Connection con) {
		PreparedStatement pstm = null;
		String returnValue=PoliceConstant.SUCCESS;
		try {	
			LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_REQUEST_CLARIFICATION_BY_REFERENCE_NO);
			pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_REQUEST_CLARIFICATION_BY_REFERENCE_NO);	
			pstm.setInt(1, requestClarificationVO.getNic());
			pstm.setString(2, requestClarificationVO.getNicMessage());
			pstm.setInt(3, requestClarificationVO.getPassport());
			pstm.setString(4, requestClarificationVO.getPassportMessage());
			pstm.setInt(5, requestClarificationVO.getVerifyName());
			pstm.setString(6, requestClarificationVO.getNameMessage());
			pstm.setInt(7, requestClarificationVO.getVerifyDateOfBirth());
			pstm.setString(8, requestClarificationVO.getDateOfBirthMessage());
			pstm.setString(9, requestClarificationVO.getNicPath());
			pstm.setString(10, requestClarificationVO.getNicPathBack());
			pstm.setInt(11, requestClarificationVO.getNicAcceptStatus());
			pstm.setString(12, requestClarificationVO.getPassportPath());
			pstm.setString(13, requestClarificationVO.getPassportPathBack());
			pstm.setInt(14, requestClarificationVO.getPassportAcceptStatus());
			pstm.setString(15, requestClarificationVO.getName());
			pstm.setInt(16, requestClarificationVO.getNameAcceptStatus());
			pstm.setDate(17, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getDateOfBirth()));
			pstm.setInt(18, requestClarificationVO.getDateOfBirthAcceptStatus());
			pstm.setString(19, requestClarificationVO.getComment());
			pstm.setInt(20, requestClarificationVO.getRequestedUserId());
			pstm.setString(21, requestClarificationVO.getRequestedUserName());
			pstm.setDate(22, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getRequestedDateTime()));
			pstm.setInt(23, requestClarificationVO.getResubmittedUserId());
			pstm.setString(24, requestClarificationVO.getResubmittedUserName());
			pstm.setDate(25, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getResubmittedDateTime()));
			pstm.setInt(26, requestClarificationVO.getReviewedUserId());
			pstm.setString(27, requestClarificationVO.getReviewedUserName());
			pstm.setDate(28, CommonUtil.getSqlDateFromUtilDate(requestClarificationVO.getReviewedDateTime()));
			pstm.setLong(29, requestClarificationVO.getRequestClarificationId());
			pstm.setLong(30, requestClarificationVO.getApplicationId());
			
			int result = pstm.executeUpdate();			
			LOGGER.info("HAS UPDATE EXECUTED: " + result);
			if (result > 0) {
				returnValue=PoliceConstant.SUCCESS;
			}
			pstm.close();			
		} catch (Exception e) {
			returnValue=PoliceConstant.ERROR;
			e.printStackTrace();
		}finally{			
			DatabaseManager.close(pstm);
		}		
		return returnValue;
	}
	
	public RequestClarificationVO getRequestClarificationByReferenceNoForDept(String referenceNo, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rst = null;
		RequestClarificationVO requestClarificationVO=null;
		try {
			//System.out.println("QUERY -> " + DBConstant.QUERY.SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO_FOR_DEPT_USER);
			pstm=conn.prepareStatement(DBConstant.QUERY.SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO_FOR_DEPT_USER);	
			pstm.setString(1, referenceNo);
			rst = pstm.executeQuery();	
			
			while (rst.next()){
		    	requestClarificationVO = new RequestClarificationVO();
		    	requestClarificationVO.setRequestClarificationId(rst.getLong(DBConstant.REQUEST_CLARIFICATION_COL.REQUEST_CLARIFICATION_ID));
		    	requestClarificationVO.setNic(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NIC));
				requestClarificationVO.setNicMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_MESSAGE));
				requestClarificationVO.setPassport(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT));
				requestClarificationVO.setPassportMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_MESSAGE));
				requestClarificationVO.setVerifyName(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.VERIFY_NAME));
				requestClarificationVO.setNameMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NAME_MESSAGE));
				requestClarificationVO.setVerifyDateOfBirth(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.VERIFY_DATE_OF_BIRTH));
				requestClarificationVO.setDateOfBirthMessage(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_MESSAGE));
				requestClarificationVO.setReferenceNo(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REFERENCE_NO));
				requestClarificationVO.setNicPath(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_PATH));
				requestClarificationVO.setNicPathBack(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NIC_PATH_BACK));
				requestClarificationVO.setNicAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NIC_ACCEPT_STATUS));
				requestClarificationVO.setPassportPath(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_PATH));
				requestClarificationVO.setPassportPathBack(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_PATH_BACK));
				requestClarificationVO.setPassportAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.PASSPORT_ACCEPT_STATUS));
				requestClarificationVO.setName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.NAME));
				requestClarificationVO.setNameAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.NAME_ACCEPT_STATUS));
				requestClarificationVO.setDateOfBirth(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH));
				requestClarificationVO.setDateOfBirthAcceptStatus(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_ACCEPT_STATUS));
				requestClarificationVO.setComment(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.COMMENT));
				requestClarificationVO.setRequestedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_USER_ID));
				requestClarificationVO.setRequestedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_USER_NAME));
				requestClarificationVO.setRequestedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.REQUESTED_DATE_TIME));
				requestClarificationVO.setResubmittedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID));
				requestClarificationVO.setResubmittedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_NAME));
				requestClarificationVO.setResubmittedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.RESUBMITTED_DATE_TIME));
				requestClarificationVO.setReviewedUserId(rst.getInt(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_USER_ID));
				requestClarificationVO.setReviewedUserName(rst.getString(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_USER_NAME));
				requestClarificationVO.setReviewedDateTime(rst.getDate(DBConstant.REQUEST_CLARIFICATION_COL.REVIEWED_DATE_TIME));
				requestClarificationVO.setApplicationId(rst.getLong(DBConstant.REQUEST_CLARIFICATION_COL.APPLICATION_ID));
		    }
			rst.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return requestClarificationVO;
	}
}
