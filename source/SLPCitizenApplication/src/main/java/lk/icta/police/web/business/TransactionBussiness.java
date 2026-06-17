package lk.icta.police.web.business;

import java.sql.Connection;
import java.sql.SQLException;

import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.TransactionDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.TransactionVO;

import org.apache.log4j.Logger;

public class TransactionBussiness {
	private static final Logger LOGGER = Logger.getLogger(TransactionBussiness.class);
	private static TransactionBussiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static TransactionBussiness getInstance() {
		synchronized (TransactionBussiness.class) {
			if (instance == null) {
				instance = new TransactionBussiness();
			}
			return instance;
		}
	}
	
	public long addTransaction(TransactionVO transactionVO){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			long transactionId = TransactionDAO.getInstance().addTransaction(conn, transactionVO);
			if(transactionId>0){
				conn.commit();
				return transactionId;
			}else{
				conn.rollback();
			}
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
		}
		return 0;
	}
	
	public boolean updateTransaction(TransactionVO transactionVO){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			boolean result = TransactionDAO.getInstance().updateTransaction(conn, transactionVO);
			if(result){
				conn.commit();
				return result;
			}else{
				conn.rollback();
			}
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
		}
		return false;
	}
	
	public TransactionVO getTransaction(long transactionId){
		Connection conn = null;
		try {
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			return TransactionDAO.getInstance().getTransaction(conn, transactionId);
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e){
			LOGGER.error(e);
		}finally {
			DatabaseManager.close(conn);
		}
		return null;
	}
	
}
