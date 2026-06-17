package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.DailyTransactionReportSearchCriteriaVO;
import lk.icta.police.framework.vo.DailyTransactionVO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyTransactionReportDAO {
    private static DailyTransactionReportDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(DailyTransactionReportDAO.class);

    public static DailyTransactionReportDAO getInstance() {
        synchronized (DailyTransactionReportDAO.class) {
            if (instance == null) {
                instance = new DailyTransactionReportDAO();
            }
            return instance;
        }
    }

    private DailyTransactionReportDAO() {

    }

    public List<DailyTransactionVO> fetchApplicationVerificationList(DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO,
                                                                     Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        DailyTransactionVO dailyTransVO = null;
        List<DailyTransactionVO> dailyTransVOs = new ArrayList<DailyTransactionVO>();

        try {
            LOGGER.info("QUERY -> " + DBConstant.QUERY.SEARCH_DailyTransactionReport);
            LOGGER.info("1 -> " + CommonUtil.getSqlDateFromUtilDate(dailyTransactionReportSearchCriteriaVO.getFromDate()));
            LOGGER.info("2 -> " + dailyTransactionReportSearchCriteriaVO.getFromDateSqlInjection());
            LOGGER.info("3 -> " + CommonUtil.getSqlDateFromUtilDate(dailyTransactionReportSearchCriteriaVO.getToDate()));
            LOGGER.info("4 -> " + dailyTransactionReportSearchCriteriaVO.getToDateSqlInjection());
            LOGGER.info("5 -> " + dailyTransactionReportSearchCriteriaVO.getStatus());
            LOGGER.info("6 -> " + dailyTransactionReportSearchCriteriaVO.getStatusSqlInjection());
            LOGGER.info("7 -> " + dailyTransactionReportSearchCriteriaVO.getSubmission());
            LOGGER.info("8 -> " + dailyTransactionReportSearchCriteriaVO.getSubmissionSqlInjection());
//			LOGGER.info("9 -> " + PoliceEnumConstant.ApplicationReviewStatus.TS.toString());

            pstm = conn.prepareStatement(DBConstant.QUERY.SEARCH_DailyTransactionReport);
            pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(dailyTransactionReportSearchCriteriaVO.getFromDate()));
            pstm.setInt(2, dailyTransactionReportSearchCriteriaVO.getFromDateSqlInjection());
            pstm.setDate(3, CommonUtil.getSqlDateFromUtilDate(dailyTransactionReportSearchCriteriaVO.getToDate()));
            pstm.setInt(4, dailyTransactionReportSearchCriteriaVO.getToDateSqlInjection());
            pstm.setString(5, dailyTransactionReportSearchCriteriaVO.getStatus());
            pstm.setInt(6, dailyTransactionReportSearchCriteriaVO.getStatusSqlInjection());
            pstm.setString(7, dailyTransactionReportSearchCriteriaVO.getSubmission());
            pstm.setInt(8, dailyTransactionReportSearchCriteriaVO.getSubmissionSqlInjection());

            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("Empty Set");
            } else {
                do {
                    dailyTransVO = new DailyTransactionVO();
                    dailyTransVO = getConstructedApplicationFromResultSet(rst, conn);
                    dailyTransVOs.add(dailyTransVO);
                } while (rst.next());
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }

        return dailyTransVOs;
    }

    public DailyTransactionVO getConstructedApplicationFromResultSet(ResultSet rst, Connection conn) throws SQLException {
        DailyTransactionVO dailytransactionVO = new DailyTransactionVO();

        String referenceNo = rst.getString(DBConstant.APPLICATION_COL.REFERENCE_NO);
        dailytransactionVO.setApplicationReferenceNo(referenceNo);
        dailytransactionVO.setTransactionReferenceNo(rst.getString(DBConstant.TRANSACTION_COL.TRANSACTION_REFERENCE_NO));
        dailytransactionVO.setPaymentDate(rst.getTimestamp(DBConstant.TRANSACTION_COL.PAYMENT_DATE));
        dailytransactionVO.setTotalFee(rst.getBigDecimal(DBConstant.TRANSACTION_COL.TOTAL_FEE));
        dailytransactionVO.setConvenienceFee(rst.getBigDecimal(DBConstant.TRANSACTION_COL.CONVENIENCE_FEE));
        dailytransactionVO.setPaymentStatus(rst.getString(DBConstant.TRANSACTION_COL.PAYMENT_STATUS));
        dailytransactionVO.setApplicationType(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_TYPE));
//		dailytransactionVO.setSumconvenienceFee(rst.getBigDecimal(DBConstant.DailyTransactionReport_COL.sumconvenienceFee));
//		dailytransactionVO.setSumtotalFee(rst.getBigDecimal(DBConstant.DailyTransactionReport_COL.sumtotalFee));


        String applicationStatus = getApplicationPreviousReferenceNoByReferenceNo(referenceNo, conn);
        dailytransactionVO.setApplicationStatus(applicationStatus);

        return dailytransactionVO;
    }

    private String getApplicationPreviousReferenceNoByReferenceNo(String referenceNumber, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;

        String applicationStatus = "";
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY_NEW.GET_APPLICATION_PREVIOUS_REFERENCE_NO_BY_REFERENCE_NO);
            pstm.setString(1, referenceNumber);
            rst = pstm.executeQuery();

            if (rst.next()) {
                String previousRefNo = rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_REFERENCE_NO);
                if (previousRefNo == null || previousRefNo.isEmpty()) {
                    applicationStatus = "N";
                } else {
                    applicationStatus = "R";
                }
            }
			rst.close();
            pstm.close();

        } catch (Exception e) {
            LOGGER.error("An error occurred " + e.getMessage());
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }

        return applicationStatus;
    }
}
