package lk.icta.police.web.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.mock.MockApplicationVOUtil;
import lk.icta.police.framework.mock.MockDAOUtil;
import lk.icta.police.framework.mock.MockDBConstants;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.TransactionVO;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransactionBussinessTest {

	private static final Logger LOGGER = Logger.getLogger(TransactionBussinessTest.class);
	
	@BeforeClass
	public static void clearAndPreparePreConditions() throws BusinessException, SQLException{
		LOGGER.info("CLEARING AN DTRUNCATING TABLES OF TEST DATABASE :");
		clearTables();

	}
	
	@AfterClass
	public static void deleteRecordsTestDatabase() throws BusinessException, SQLException{
		//clearTables();		
	}
	
	private static void clearTables() throws BusinessException, SQLException{
		Connection conn=DatabaseManager.getPOLDBConnection();
		conn.setAutoCommit(false);
		LOGGER.info("DELETE APPLICATION FROM TEST DATABASE");
		List<String> databasetablesToTruncate=new ArrayList<String>();
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.TRANSACTION.getCode());
		String result=MockDAOUtil.getInstance().truncateTestDatabasetables(databasetablesToTruncate,conn);
		Assert.assertEquals("SUCCESS", result);	
		conn.commit();
		DatabaseManager.close(conn);
	}
	
	@Test
	public void testAddApplication() {
		ApplicationVO applicationVO = MockApplicationVOUtil.getApplicationVO(1);
		long result = ApplicationBusiness.getInstance().addApplication(applicationVO);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testAddTransaction() {
		TransactionVO transactionVO = MockApplicationVOUtil.getTransactionVO(1);
		long transactionId = TransactionBussiness.getInstance().addTransaction(transactionVO);
		Assert.assertEquals(1, transactionId);
	}

	@Test
	public void testUpdateTransaction() {
		TransactionVO transactionVO = MockApplicationVOUtil.getTransactionVO(1);
		Boolean result = TransactionBussiness.getInstance().updateTransaction(transactionVO);
		Assert.assertTrue(result);
	}

	@Test
	public void testGetTransaction() {
		TransactionVO transactionVO = TransactionBussiness.getInstance().getTransaction(1);
		Assert.assertEquals(1, transactionVO.getTransactionId());
	}

}
