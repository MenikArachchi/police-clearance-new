package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.vo.BlackListVO;

import org.apache.log4j.Logger;

public class BlackListDAO {

  private static BlackListDAO instance = null;
  private static final Logger LOGGER = Logger.getLogger(BlackListDAO.class);

  public static BlackListDAO getInstance() {
    synchronized (BlackListDAO.class) {
      if (instance == null) {
        instance = new BlackListDAO();
      }
      return instance;
    }
  }

  private BlackListDAO() {

  }


  public long addBlackList(Connection conn, BlackListVO blackListVO) throws Exception {
    PreparedStatement pstm = null;
//    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.ADD_NEW_BLACKLISTED_APP, PreparedStatement.RETURN_GENERATED_KEYS);
      pstm.setString(1, blackListVO.getAddress());
      pstm.setString(2, blackListVO.getApplicationReferenceNumber());
      pstm.setString(3, blackListVO.getName());
      pstm.setString(4, blackListVO.getNic());
      pstm.setString(5, blackListVO.getPassport());
      pstm.setString(6, blackListVO.getTel());
      pstm.setLong(7, blackListVO.getApplicationId());
      pstm.setString(8, blackListVO.getComment());
      pstm.setString(9, blackListVO.getNewNic());

      int result = pstm.executeUpdate();
      long blackListId = 0l;
      if (result > 0) {
        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
          blackListId = rs.getInt(1);
        }
      }

      blackListVO.setBlackListId(blackListId);
      DatabaseManager.close(result);
      LOGGER.info("blackListId :" + blackListId);
//	  rst.close();
      pstm.close();
      return blackListId;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
 //     DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return 0;
  }

  public int countBlackListRecordsByNICOrPPT(Connection conn, String nic, String passport) {

    PreparedStatement pstm = null;
    ResultSet rst = null;
    int blackListRecordCount = 0;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_BLACK_LIST_RECORD_COUNT);
      pstm.setString(1, nic);
      pstm.setString(2, passport);
      pstm.setString(3, nic);

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          blackListRecordCount = rst.getInt("BLACK_LIST_RECORD_COUNT");
          // LOGGER.info("BLACK_LIST_RECORD_COUNT WHILE --> " + blackListRecordCount);
        }
      }
      // LOGGER.info("BLACK_LIST_RECORD_COUNT --> " + blackListRecordCount);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return blackListRecordCount;
  }

  public int countBlackListRecordsByNICOrPPT(Connection conn, String nic, String newNic, String passport) {

    PreparedStatement pstm = null;
    ResultSet rst = null;
    int blackListRecordCount = 0;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_BLACK_LIST_RECORD_COUNT);
      pstm.setString(1, nic);
      pstm.setString(2, passport);
      pstm.setString(3, newNic);

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          blackListRecordCount = rst.getInt("BLACK_LIST_RECORD_COUNT");
          // LOGGER.info("BLACK_LIST_RECORD_COUNT WHILE --> " + blackListRecordCount);
        }
      }
      // LOGGER.info("BLACK_LIST_RECORD_COUNT --> " + blackListRecordCount);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return blackListRecordCount;
  }


  public List<BlackListVO> getAllBlacklistRecords(Connection conn) {
    PreparedStatement pstm = null;
    ResultSet rst = null;
    List<BlackListVO> blackListVOs = new ArrayList<BlackListVO>();
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_BLACK_LIST_RECORDS);

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          BlackListVO blackListVO = new BlackListVO();
          blackListVO.setNic(rst.getString(DBConstant.BLACK_LIST_COL.NIC));
          blackListVO.setNewNic(rst.getString(DBConstant.BLACK_LIST_COL.NEW_NIC));
          blackListVO.setPassport(rst.getString(DBConstant.BLACK_LIST_COL.PASSPORT));
          blackListVO.setName(rst.getString(DBConstant.BLACK_LIST_COL.NAME));
          blackListVO.setApplicationId(rst.getLong(DBConstant.BLACK_LIST_COL.APPLICATION_ID));
          blackListVOs.add(blackListVO);
        }
      }
      // LOGGER.info("BLACK_LIST_RECORD_COUNT --> " + blackListRecordCount);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return blackListVOs;
  }

}
