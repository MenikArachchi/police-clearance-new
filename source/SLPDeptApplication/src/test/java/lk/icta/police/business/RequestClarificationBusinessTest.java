package lk.icta.police.business;

import static org.junit.Assert.*;

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
import lk.icta.police.framework.mock.MockRequestClarificationVOUtil;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.RequestClarificationVO;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RequestClarificationBusinessTest {

private static final Logger LOGGER = Logger.getLogger(ApplicationBusinessTest.class);
	
	@BeforeClass
	public static void clearAndPreparePreConditions() throws BusinessException, SQLException{
		LOGGER.info("CLEARING AN DTRUNCATING TABLES OF TEST DATABASE :");
		clearTables();
		addApplication();
		addRequestClarification();

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
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.APPLICATION.getCode());
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.REQUEST_CLARIFICATION.getCode());	
		String result=MockDAOUtil.getInstance().truncateTestDatabasetables(databasetablesToTruncate,conn);
		Assert.assertEquals("SUCCESS", result);	
		conn.commit();
		DatabaseManager.close(conn);
	}
	
	private static void addApplication() throws BusinessException{
		LOGGER.info("ApplicationBusinessTest -> addApplication() ");	
		long applicationId = 0L;
		for (int i = 0; i < 3; i++) {
			ApplicationVO applicationVO = MockApplicationVOUtil.getApplicationVO(i);	
			applicationId = ApplicationBusiness.getInstance().addApplication(applicationVO);	
			LOGGER.info("applicationId :" + applicationId);
		}		
	}
	
	private static void addRequestClarification() throws BusinessException{
		LOGGER.info("RequestClarificationBusinessTest -> addRequestClarification() ");	
		long requestClarificationId = 0L;
		for (int i = 0; i < 3; i++) {
			RequestClarificationVO requestClarificationVO = MockRequestClarificationVOUtil.getRequestClarificationVO(i);
			requestClarificationId = RequestClarificationBusiness.getInstance().saveRequestClarification(requestClarificationVO,null);
			LOGGER.info("requestClarificationId :" + requestClarificationId);
		}		
	}
	
	@Test
	public void testCountRequestClarificationForApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveRequestClarification() {
		fail("Not yet implemented");
	}

}
