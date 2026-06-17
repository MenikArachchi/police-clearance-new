package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.DBConstant.TRANSACTION_COL;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.TransactionVO;

import org.apache.log4j.Logger;

public class TransactionDAO {
  private static TransactionDAO instance = null;
  private static final Logger LOGGER = Logger.getLogger(TransactionDAO.class);

  public static TransactionDAO getInstance() {
    synchronized (AddressDAO.class) {
      if (instance == null) {
        instance = new TransactionDAO();
      }
      return instance;
    }
  }

  private TransactionDAO() {

  }

  public long addTransaction(Connection conn, TransactionVO transactionVO) {
    PreparedStatement pstm = null;
  //  ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.ADD_NEW_TRANSACTION, PreparedStatement.RETURN_GENERATED_KEYS);

      pstm.setString(1, transactionVO.getTransactionReferenceNo());
      pstm.setString(2, transactionVO.getChequeNo());
      pstm.setString(3, transactionVO.getAccountNo());
      pstm.setString(4, transactionVO.getAccountHolderName());
      pstm.setString(5, transactionVO.getBookReceiptNo());
      pstm.setString(6, transactionVO.getDescription());
      pstm.setBigDecimal(7, transactionVO.getApplicationFee());
      pstm.setBigDecimal(8, transactionVO.getPostageFee());
      pstm.setBigDecimal(9, transactionVO.getServiceFee());
      pstm.setBigDecimal(10, transactionVO.getConvenienceFee());
      pstm.setBigDecimal(11, transactionVO.getTotalFee());
      pstm.setTimestamp(12, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentDate()));
      pstm.setString(13, transactionVO.getPaymentStatus());
      pstm.setString(14, transactionVO.getPaymentGatewayName());
      pstm.setTimestamp(15, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentInitiatedTime()));
      pstm.setTimestamp(16, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentConfirmedTime()));
      pstm.setString(17, transactionVO.getPaymentType());
      pstm.setString(18, transactionVO.getPaymentMode());
      pstm.setTimestamp(19, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getCreatedDate()));
      pstm.setInt(20, 0);
      pstm.setLong(21, transactionVO.getApplicationId());
      pstm.setString(22, transactionVO.getUserFullName());
      pstm.setString(23, transactionVO.getUserEmail());
      pstm.setString(24, transactionVO.getAuthProvider());

      int result = pstm.executeUpdate();
      long transactionId = 0l;
      if (result > 0) {
        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
          transactionId = rs.getInt(1);
        }
      }

      transactionVO.setTransactionId(transactionId);
      DatabaseManager.close(result);
//	  rst.close();
	  pstm.close();
      return transactionId;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
 //     DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return 0;
  }

  public boolean updateTransaction(Connection conn, TransactionVO transactionVO) {
    PreparedStatement pstm = null;
 //   ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_TRANSACTION);

      pstm.setString(1, transactionVO.getChequeNo());
      pstm.setString(2, transactionVO.getAccountNo());
      pstm.setString(3, transactionVO.getAccountHolderName());
      pstm.setString(4, transactionVO.getBookReceiptNo());
      pstm.setString(5, transactionVO.getDescription());
      pstm.setBigDecimal(6, transactionVO.getApplicationFee());
      pstm.setBigDecimal(7, transactionVO.getPostageFee());
      pstm.setBigDecimal(8, transactionVO.getServiceFee());
      pstm.setBigDecimal(9, transactionVO.getConvenienceFee());
      pstm.setBigDecimal(10, transactionVO.getTotalFee());
      pstm.setTimestamp(11, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentDate()));
      pstm.setString(12, transactionVO.getPaymentStatus());
      pstm.setString(13, transactionVO.getPaymentGatewayName());
      pstm.setTimestamp(14, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentInitiatedTime()));
      pstm.setTimestamp(15, CommonUtil.getSqlTimeStampFromUtilDate(transactionVO.getPaymentConfirmedTime()));
      pstm.setString(16, transactionVO.getPaymentType());
      pstm.setString(17, transactionVO.getPaymentMode());

      pstm.setLong(18, transactionVO.getTransactionId());

      pstm.executeUpdate();
//	  rst.close();
	  pstm.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
 //     DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return false;
  }

  public TransactionVO getTransaction(Connection conn, long transactionId) throws Exception {
    PreparedStatement pstm = null;
    ResultSet rst = null;
    TransactionVO transactionVO = null;
    try {
      // get the application record from reference no
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_TRANSACTION_BY_ID);
      pstm.setLong(1, transactionId);
      rst = pstm.executeQuery();

      LOGGER.info("No application records found for reference no :" + transactionId);
      while (rst.next()) {
        transactionVO = getConstructedTransactionVOFromResultSet(rst);
      }
	  rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return transactionVO;
  }


  private TransactionVO getConstructedTransactionVOFromResultSet(ResultSet rst) throws SQLException {
    TransactionVO transactionVO = new TransactionVO();

    transactionVO.setTransactionId(rst.getLong(TRANSACTION_COL.TRANSACTION_ID));
    transactionVO.setTransactionReferenceNo(rst.getString(TRANSACTION_COL.TRANSACTION_REFERENCE_NO));
    transactionVO.setChequeNo(rst.getString(TRANSACTION_COL.CHEQUE_NO));
    transactionVO.setAccountNo(rst.getString(TRANSACTION_COL.ACCOUNT_NO));
    transactionVO.setAccountHolderName(rst.getString(TRANSACTION_COL.ACCOUNT_HOLDER_NAME));
    transactionVO.setBookReceiptNo(rst.getString(TRANSACTION_COL.BOOK_RECEIPT_NO));
    transactionVO.setDescription(rst.getString(TRANSACTION_COL.DESCRIPTION));
    transactionVO.setApplicationFee(rst.getBigDecimal(TRANSACTION_COL.APPLICATION_FEE));
    transactionVO.setPostageFee(rst.getBigDecimal(TRANSACTION_COL.POSTAGE_FEE));
    transactionVO.setServiceFee(rst.getBigDecimal(TRANSACTION_COL.SERVICE_FEE));
    transactionVO.setConvenienceFee(rst.getBigDecimal(TRANSACTION_COL.CONVENIENCE_FEE));
    transactionVO.setTotalFee(rst.getBigDecimal(TRANSACTION_COL.TOTAL_FEE));
    transactionVO.setPaymentDate(rst.getTimestamp(TRANSACTION_COL.PAYMENT_DATE));
    transactionVO.setPaymentStatus(rst.getString(TRANSACTION_COL.PAYMENT_STATUS));
    transactionVO.setPaymentGatewayName(rst.getString(TRANSACTION_COL.PAYMENT_GATEWAY_NAME));
    transactionVO.setPaymentInitiatedTime(rst.getTimestamp(TRANSACTION_COL.PAYMENT_INITIATED_TIME));
    transactionVO.setPaymentConfirmedTime(rst.getTimestamp(TRANSACTION_COL.PAYMENT_CONFIRMED_TIME));
    transactionVO.setPaymentType(rst.getString(TRANSACTION_COL.PAYMENT_TYPE));
    transactionVO.setPaymentMode(rst.getString(TRANSACTION_COL.PAYMENT_MODE));
    transactionVO.setCreatedDate(rst.getTimestamp(TRANSACTION_COL.CREATED_DATE));
    transactionVO.setApplicationId(rst.getLong(TRANSACTION_COL.APPLICATION_ID));
    transactionVO.setUserFullName(rst.getString(TRANSACTION_COL.USER_FULL_NAME));
    transactionVO.setUserEmail(rst.getString(TRANSACTION_COL.USER_EMAIL));
    transactionVO.setAuthProvider(rst.getString(TRANSACTION_COL.AUTH_PROVIDER));
    transactionVO.setVersionId(rst.getInt(DBConstant.TRANSACTION_COL.VERSION_ID));

    return transactionVO;
  }

  public String updateManualTransaction(Connection con, TransactionVO transactionVO) {
    String result = PoliceConstant.ERROR;
    PreparedStatement pstm = null;
 //   ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_MANUAL_TRANSACTION);
      pstm.setString(1, transactionVO.getPaymentMode());
      pstm.setBigDecimal(2, transactionVO.getTotalFee());
      pstm.setString(3, transactionVO.getChequeNo());
      pstm.setString(4, transactionVO.getAccountNo());
      pstm.setString(5, transactionVO.getAccountHolderName());
      pstm.setString(6, transactionVO.getDescription());
      pstm.setString(7, transactionVO.getBookReceiptNo());
      pstm.setLong(8, transactionVO.getTransactionId());

      int i = pstm.executeUpdate();
      if (i > 0) {
        result = PoliceConstant.SUCCESS;
      }
//	  rst.close();
	  pstm.close();
    } catch (Exception e) {
      result = PoliceConstant.ERROR;
      e.printStackTrace();
    } finally {
//      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return result;
  }
}
