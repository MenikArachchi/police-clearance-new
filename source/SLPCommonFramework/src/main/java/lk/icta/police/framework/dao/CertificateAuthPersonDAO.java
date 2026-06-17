package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.DBConstant.CERTIFICATE_AUTH_PERSON_COL;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.CertificateAuthPersonVO;

import org.apache.log4j.Logger;

public class CertificateAuthPersonDAO {
	
	private static CertificateAuthPersonDAO instance = null;
	private static final Logger LOGGER = Logger.getLogger(CertificateAuthPersonDAO.class);
	
	public static CertificateAuthPersonDAO getInstance() {
		synchronized(CertificateAuthPersonDAO.class) {
			if (instance == null) {
				instance = new CertificateAuthPersonDAO();
			}
			return instance;
		}
	}
	
	private CertificateAuthPersonDAO(){
		
	}
	
	public List<CertificateAuthPersonVO> getCertificateAuthPersonList(Connection conn) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rst = null;
		List<CertificateAuthPersonVO> authPersonVOs = new ArrayList<CertificateAuthPersonVO>();
		try {
			pstm=conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_CERTIFICATE_AUTH_PERSON_LIST);	
			rst = pstm.executeQuery();	
			
			while (rst.next()){		    	
				authPersonVOs.add(getConstructedCertificateAuthPersonVOFromResultSet(rst));
		    }
			rst.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return authPersonVOs;
	}
	
	public CertificateAuthPersonVO getCurrentCertificateAuthPerson(Connection conn) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rst = null;
		CertificateAuthPersonVO authPersonVO = null;
		try {
			LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_CURRENT_CERTIFICATE_AUTH_PERSON);
			pstm=conn.prepareStatement(DBConstant.QUERY.SELECT_CURRENT_CERTIFICATE_AUTH_PERSON);	
			pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(Calendar.getInstance().getTime()));
			rst = pstm.executeQuery();	
			while (rst.next()){		    	
				authPersonVO=getConstructedCertificateAuthPersonVOFromResultSet(rst);
		    }
			rst.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return authPersonVO;
	}
	
	private CertificateAuthPersonVO getConstructedCertificateAuthPersonVOFromResultSet(ResultSet rst) throws SQLException {
		CertificateAuthPersonVO authPersonVO = new CertificateAuthPersonVO();
		authPersonVO.setEffectiveEndDate(rst.getTimestamp(DBConstant.CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_END_DATE));
		authPersonVO.setEffectiveStartDate(rst.getTimestamp(DBConstant.CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_START_DATE));
		authPersonVO.setId(rst.getLong(DBConstant.CERTIFICATE_AUTH_PERSON_COL.ID));
		authPersonVO.setName(rst.getString(DBConstant.CERTIFICATE_AUTH_PERSON_COL.NAME));
		authPersonVO.setType(rst.getString(DBConstant.CERTIFICATE_AUTH_PERSON_COL.TYPE));
		authPersonVO.setAddress(rst.getString(DBConstant.CERTIFICATE_AUTH_PERSON_COL.ADDRESS));
		authPersonVO.setDesignation(rst.getString(DBConstant.CERTIFICATE_AUTH_PERSON_COL.DESIGNATION));
		return authPersonVO;
	}

	public long saveCertificateAuthPerson(CertificateAuthPersonVO certificateAuthPersonVO, Connection conn) {
		PreparedStatement pstm = null;
		long certificateAuthPersonId = 0L;
		
		try {
			LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_CERTIFICATE_AUTH_PERSON);
			pstm=conn.prepareStatement(DBConstant.QUERY.ADD_CERTIFICATE_AUTH_PERSON, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, certificateAuthPersonVO.getName());
			pstm.setString(2, ""); // ?
			pstm.setDate(3, CommonUtil.getSqlDateFromUtilDate(certificateAuthPersonVO.getEffectiveStartDate()));
			pstm.setDate(4, null);
			pstm.setString(5, certificateAuthPersonVO.getDesignation());
			pstm.setString(6, certificateAuthPersonVO.getAddress());
			
			int result = pstm.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstm.getGeneratedKeys();
				while (rs.next()) {
					certificateAuthPersonId = rs.getInt(1);					
				}
			}	
			
			certificateAuthPersonVO.setId(certificateAuthPersonId);
			pstm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(pstm);
		}
		return certificateAuthPersonId;
	}
	
	public String updateCertificateAuthPerson (long authPersonId,Date endDate, Connection con) {
		PreparedStatement pstm = null;
		String returnValue=PoliceConstant.SUCCESS;
		try {	
			LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_CERTIFICATE_AUTH_PERSON);
			pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_CERTIFICATE_AUTH_PERSON);	
			pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(endDate));
			pstm.setLong(2, authPersonId);
		
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
}
