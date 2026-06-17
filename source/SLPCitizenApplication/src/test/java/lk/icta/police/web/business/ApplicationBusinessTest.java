package lk.icta.police.web.business;

import junit.framework.Assert;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.mock.MockApplicationVOUtil;
import lk.icta.police.framework.mock.MockDAOUtil;
import lk.icta.police.framework.mock.MockDBConstants;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CountryVO;
import lk.icta.police.framework.vo.PoliceAreaVO;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(ApplicationBusinessTest.class);
	
	@BeforeClass
	public static void clearAndPreparePreConditions() throws BusinessException, SQLException{
		LOGGER.info("CLEARING AN DTRUNCATING TABLES OF TEST DATABASE :");
		clearTables();

	}
	
	@AfterClass
	public static void deleteRecordsTestDatabase() throws BusinessException, SQLException{
		clearTables();		
	}
	
	private static void clearTables() throws BusinessException, SQLException{
		Connection conn=DatabaseManager.getPOLDBConnection();
		conn.setAutoCommit(false);
		LOGGER.info("DELETE APPLICATION FROM TEST DATABASE");
		List<String> databasetablesToTruncate=new ArrayList<String>();
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.APPLICATION.getCode());	
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.ADDRESS.getCode());
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
	public void testVerifyApplication() {
		int applicationId = 1;
		String nicNo = "8745362" + applicationId + "V";
		String passportNo = "111112" + applicationId;
		long country = 1;
		boolean result = ApplicationBusiness.getInstance().verifyApplication(nicNo,null, passportNo, country);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testGetApplication() {
		int applicationId = 1;
		String referenceNo = "REF000" + applicationId;
		ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplication(referenceNo);
		Assert.assertEquals(referenceNo, applicationVO.getReferenceNo());
	}
	
	@Test
	public void testGetAuthApplication() {
		int applicationId = 1;
		String referenceNo = "REF000" + applicationId;
		String userEmail = "user"+applicationId+"@gmail.com";
		String authProvider = PoliceEnumConstant.ApplicationAuthProvider.GM.toString();
		
		ApplicationVO applicationVO = ApplicationBusiness.getInstance().getAuthApplicationWithAddresses(referenceNo, userEmail, authProvider);
		Assert.assertEquals(referenceNo, applicationVO.getReferenceNo());
	}

	@Test
	public void testUpdateApplication() {
		ApplicationVO applicationVO = MockApplicationVOUtil.getApplicationVO(1);
		boolean result = ApplicationBusiness.getInstance().updateApplication(applicationVO);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testGetCountryList() {
		List<CountryVO> countryList = ApplicationBusiness.getInstance().getCountryList();
		LOGGER.warn("country List Size = "+countryList.size());
		Assert.assertTrue(countryList.size()>0);
	}
	
	@Test
	public void testGetCountry() {
		long countryId = 1;
		CountryVO country = ApplicationBusiness.getInstance().getCountryVO(countryId);
		LOGGER.warn("country id = "+country.getId());
		Assert.assertEquals(1, countryId);
	}

	@Test
	public void testGetPoliceAreaList() {
		List<PoliceAreaVO> policeAreaList = ApplicationBusiness.getInstance().getPoliceAreaList();
		LOGGER.info("police Area List ="+policeAreaList.size());
		Assert.assertTrue(policeAreaList.size()>0);
	}
	
	@Test
	public void testGetApplicationByApplicationId(){
		ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(1);
		Assert.assertEquals(1, applicationVO.getApplicationId());
		Assert.assertEquals("REF0001", applicationVO.getReferenceNo());
	}
	
//	@Test
//	public void testGetApplicationByReferenceNoAndEmail() {
//		int applicationId = 1;
//		String testEmail="test@gmail.com";
//		String testRefNo="REF000"+applicationId;
//		ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNoAndEmail(testRefNo, testEmail);
//		Assert.assertNotNull(applicationVO);
//		
//	}
	
	
}
