package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationReviewStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationType;
import lk.icta.police.framework.constant.PoliceEnumConstant.PaymentStatus;
import lk.icta.police.framework.dao.ApplicationDetailsReportDAO;
import lk.icta.police.framework.dao.DailyTransactionReportDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationDetailsReportSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.DailyTransactionReportSearchCriteriaVO;
import lk.icta.police.framework.vo.DailyTransactionVO;
import lk.icta.police.framework.vo.TransactionVO;

import org.apache.log4j.Logger;

public class DailyTransactionReportBusiness {
	private static final Logger LOGGER = Logger.getLogger(DailyTransactionReportBusiness.class);
	private static DailyTransactionReportBusiness instance = null;
	
	/**
	 * Singleton Implementation
	 * 
	 */
	public static DailyTransactionReportBusiness getInstance() {
		synchronized (DailyTransactionReportBusiness.class) {
			if (instance == null) {
				instance = new DailyTransactionReportBusiness();
			}
			return instance;
		}
	}
	
	private DailyTransactionReportBusiness() {
		
	}
	
	public List<DailyTransactionVO> getAllocationList(DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO, int userId) {
		Connection con = null;
		List<DailyTransactionVO> transactionVOs = new ArrayList<DailyTransactionVO>();
		int requestClarificationCount = 0;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			transactionVOs =  DailyTransactionReportDAO.getInstance().fetchApplicationVerificationList(dailyTransactionReportSearchCriteriaVO, con);
			
			for (DailyTransactionVO transactionVO : transactionVOs) {
				transactionVO.setApplicationType(ApplicationType.fromCode(transactionVO.getApplicationType()).getDisplayName());
				transactionVO.setPaymentStatus(PaymentStatus.fromCode(transactionVO.getPaymentStatus()).getDisplayName());
			}

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
			
			return transactionVOs;
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
