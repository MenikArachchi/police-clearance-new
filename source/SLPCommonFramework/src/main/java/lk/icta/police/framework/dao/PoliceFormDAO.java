package lk.icta.police.framework.dao;


import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationStatusViewSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nadeeshani Senevirathna
 *
 */
public class PoliceFormDAO {

	private static PoliceFormDAO instance = null;
	private static final Logger LOGGER = Logger.getLogger(PoliceFormDAO.class);
	
	public static PoliceFormDAO getInstance() {
		synchronized(PoliceFormDAO.class) {
			if (instance == null) {
				instance = new PoliceFormDAO();
			}
			return instance;
		}
	}
	
	private PoliceFormDAO(){
		
	}

			
	
	public List<ApplicationVO> searchApplicationsForPoliceForm(ApplicationStatusViewSearchCriteriaVO criteriaVO,Connection conn) {
		List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
		PreparedStatement pstm = null;
		ResultSet rst = null;
		try{
			//LOGGER.info(criteriaVO);
			//LOGGER.info(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
			pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM);
			
			pstm.setString(1, criteriaVO.getReferenceNo());
			pstm.setInt(2, criteriaVO.getReferenceNoSqlInjection());
			
			pstm.setString(3, criteriaVO.getNicNo() + "%");
			pstm.setInt(4, criteriaVO.getNicNoSqlInjection());

			pstm.setString(5, criteriaVO.getNewNicNo() + "%");
			pstm.setInt(6, criteriaVO.getNewNicNoSqlInjection());
			
			pstm.setString(7, criteriaVO.getPptNo() + "%");
			pstm.setInt(8, criteriaVO.getPptNoSqlInjection());
			
			pstm.setString(9, "%" + criteriaVO.getName() + "%");
			pstm.setString(10, "%" + criteriaVO.getName() + "%");
			pstm.setInt(11, criteriaVO.getNameSqlInjection());
			
			//LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getFromDate()));
			pstm.setDate(12, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getFromDate()));
			pstm.setInt(13, criteriaVO.getFromDateSqlInjection());
			
			//LOGGER.info(CommonUtil.getSqlTimeStampFromUtilDate(criteriaVO.getToDate()));
			pstm.setDate(14, CommonUtil.getSqlDateFromUtilDate(criteriaVO.getToDate()));
			pstm.setInt(15, criteriaVO.getToDateSqlInjection());
			
			pstm.setString(16, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			
			rst = pstm.executeQuery();			
			if(rst != null ){
				while(rst.next()){
					applicationVOs.add(ApplicationDAO.getInstance().getConstructedApplicationFromResultSet(rst));
				}
			}
			rst.close();
			pstm.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
		} 
		return applicationVOs;
	}
	
	
}
