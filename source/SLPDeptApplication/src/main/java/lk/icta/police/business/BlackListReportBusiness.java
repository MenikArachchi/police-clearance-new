package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.dao.BlacklistReportDAO;
import lk.icta.police.framework.dao.DailyTransactionReportDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.BlacklistSearchCriteriaVO;
import lk.icta.police.framework.vo.DailyTransactionReportSearchCriteriaVO;
import lk.icta.police.framework.vo.DailyTransactionVO;

import org.apache.log4j.Logger;

public class BlackListReportBusiness {
	private static final Logger LOGGER = Logger.getLogger(BlackListReportBusiness.class);
	private static BlackListReportBusiness instance = null;
	
	/**
	 * Singleton Implementation
	 * 
	 */
	public static BlackListReportBusiness getInstance() {
		synchronized (BlackListReportBusiness.class) {
			if (instance == null) {
				instance = new BlackListReportBusiness();
			}
			return instance;
		}
	}
	
	private BlackListReportBusiness() {
		
	}
	
	public List<BlackListVO> getAllocationList(BlacklistSearchCriteriaVO blacklistSearchCriteriaVO, int userId) {
		Connection con = null;
		List<BlackListVO> blacklistVOs = new ArrayList<BlackListVO>();
		int requestClarificationCount = 0;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			blacklistVOs =  BlacklistReportDAO.getInstance().fetchApplicationVerificationList(blacklistSearchCriteriaVO, con);
			
//			for (TransactionVO transactionVO : transactionVOs) {
//				transactionVO.setApplicationReviewStatus(ApplicationReviewStatus.fromCode(transactionVO.getApplicationReviewStatus()).getDisplayName());
//			}

//			for (ApplicationVO applicationVO : applicationVOs) {
//				
//				requestClarificationCount = RequestClarificationBusiness.getInstance().countRequestClarificationForApplication(applicationVO.getApplicationId());
//				
//				if(requestClarificationCount == 0 
//						&& applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.ApplicationReviewStatus.RC.toString())) {
//					applicationVO.setHasRequestClarification(1);
//				} else {
//					applicationVO.setHasRequestClarification(0);
//				}
//				
//				if(userId == applicationVO.getPhqRecordLockStatus()){
//					applicationVO.setHasCurrentUserLocked(1);
//				} else {
//					applicationVO.setHasCurrentUserLocked(0);
//				}
//				
//				applicationVO.allocateFileTypes();
//				
//			}
			
			return blacklistVOs;
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
}
