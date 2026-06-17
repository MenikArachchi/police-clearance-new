package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.BlacklistSearchCriteriaVO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlacklistReportDAO {
    private static BlacklistReportDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(BlacklistReportDAO.class);

    public static BlacklistReportDAO getInstance() {
        synchronized (BlacklistReportDAO.class) {
            if (instance == null) {
                instance = new BlacklistReportDAO();
            }
            return instance;
        }
    }

    private BlacklistReportDAO() {

    }

    public List<BlackListVO> fetchApplicationVerificationList(BlacklistSearchCriteriaVO blacklistSearchCriteriaVO,
                                                              Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        BlackListVO blacklistVO = null;
        List<BlackListVO> blacklistVOs = new ArrayList<BlackListVO>();

        try {
//			LOGGER.info("QUERY -> " + DBConstant.QUERY.SEARCH_ApplicationdetailsReport);
//			LOGGER.info("1 -> " + CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getFromDate()));
//			LOGGER.info("2 -> " + applicationVerificationSearchCriteriaVO.getFromDateSqlInjection());
//			LOGGER.info("3 -> " + CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getToDate()));
//			LOGGER.info("4 -> " + applicationVerificationSearchCriteriaVO.getToDateSqlInjection());
//			LOGGER.info("5 -> " + applicationVerificationSearchCriteriaVO.getStatus());
//			LOGGER.info("6 -> " + applicationVerificationSearchCriteriaVO.getStatusSqlInjection());
//			LOGGER.info("7 -> " + applicationVerificationSearchCriteriaVO.getReferenceNo());
//			LOGGER.info("8 -> " + applicationVerificationSearchCriteriaVO.getReferenceNoSqlInjection());
//			LOGGER.info("9 -> " + PoliceEnumConstant.ApplicationReviewStatus.TS.toString());

            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_BLACKLIST);

            pstm.setString(1, blacklistSearchCriteriaVO.getNicNo() + "%");
            pstm.setInt(2, blacklistSearchCriteriaVO.getNicNoSqlInjection());

            pstm.setString(3, blacklistSearchCriteriaVO.getNewNicNo() + "%");
            pstm.setInt(4, blacklistSearchCriteriaVO.getNewNicNoSqlInjection());

            pstm.setString(5, blacklistSearchCriteriaVO.getPassportNo() + "%");
            pstm.setInt(6, blacklistSearchCriteriaVO.getPassportNoSqlInjection());

            pstm.setString(7, "%" + blacklistSearchCriteriaVO.getName() + "%");
            pstm.setString(8, "%" + blacklistSearchCriteriaVO.getName() + "%");
            pstm.setInt(9, blacklistSearchCriteriaVO.getNameSqlInjection());


            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("Empty Set");
            } else {
                do {
                    blacklistVO = new BlackListVO();
                    blacklistVO = getConstructedApplicationFromResultSet(rst);
                    blacklistVOs.add(blacklistVO);
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

        return blacklistVOs;
    }

    public BlackListVO getConstructedApplicationFromResultSet(ResultSet rst) throws SQLException {
        BlackListVO blacklistVO = new BlackListVO();

        blacklistVO.setApplicationReferenceNumber(rst.getString(DBConstant.BLACK_LIST_COL.APPLICATION_REFERENCE_NUMBER));

        String nic = rst.getString(DBConstant.BLACK_LIST_COL.NIC);
        blacklistVO.setNic(nic);

        String newNic = rst.getString(DBConstant.BLACK_LIST_COL.NEW_NIC);
        blacklistVO.setNewNic(newNic);
        blacklistVO.setPassport(rst.getString(DBConstant.BLACK_LIST_COL.PASSPORT));
        blacklistVO.setName(rst.getString(DBConstant.BLACK_LIST_COL.NAME));
        blacklistVO.setAddress(rst.getString(DBConstant.BLACK_LIST_COL.ADDRESS));
        blacklistVO.setTel(rst.getString(DBConstant.BLACK_LIST_COL.TEL));
        blacklistVO.setCreatedDate(rst.getTimestamp(DBConstant.BLACK_LIST_COL.CREATED_DATE));
        blacklistVO.setApplicationId(rst.getLong(DBConstant.BLACK_LIST_COL.APPLICATION_ID));
        blacklistVO.setComment(rst.getString(DBConstant.BLACK_LIST_COL.COMMENT));

        String currentNIC = "";

        if (newNic != null && !newNic.equals("")) {
            currentNIC = newNic;
        } else if ((newNic == null || newNic.equals("")) && nic != null && !nic.equals("")) {
            currentNIC = nic;
        } else {
            currentNIC = "N/A";
        }

        blacklistVO.setCurrentNic(currentNIC);
        return blacklistVO;
    }
}
