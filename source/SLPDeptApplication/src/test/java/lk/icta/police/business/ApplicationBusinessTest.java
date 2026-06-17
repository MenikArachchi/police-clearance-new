package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.mock.MockApplicationVOUtil;
import lk.icta.police.framework.mock.MockDAOUtil;
import lk.icta.police.framework.mock.MockDBConstants;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ApplicationVerificationSearchCriteriaVO;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApplicationBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(ApplicationBusinessTest.class);
	
	@BeforeClass
	public static void clearAndPreparePreConditions() throws BusinessException, SQLException{
		LOGGER.info("CLEARING AN DTRUNCATING TABLES OF TEST DATABASE :");
		clearTables();
		addApplication();

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
	
	@Test
	public void testAddApplication() {
		LOGGER.info("TESTING SAVE APPLICATION FUNCTIONALITY ...");
		List<ApplicationVO> applicationVOs=MockApplicationVOUtil.getApplicationVOList(1, 5);
		for (ApplicationVO applicationVO : applicationVOs) {
			ApplicationBusiness.getInstance().addApplication(applicationVO);
		}
		Assert.assertNotNull(applicationVOs);	
		Assert.assertEquals(true, applicationVOs.size()==5);
	}

	@Test
	public void testGetAllocationList() {
		LOGGER.info("TESTING GET ALLOCATION LIST FUNCTIONALITY ...");
		ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO = new ApplicationVerificationSearchCriteriaVO();
		applicationVerificationSearchCriteriaVO.setFromDate(new Date());
		applicationVerificationSearchCriteriaVO.setToDate(new Date());
		applicationVerificationSearchCriteriaVO.setReferenceNo("");
		applicationVerificationSearchCriteriaVO.setStatus("");
		applicationVerificationSearchCriteriaVO.updateApplicationVerificationSearchCriteriaVO();
		
		List<ApplicationVO> applicationVOs = ApplicationBusiness.getInstance().getAllocationList(applicationVerificationSearchCriteriaVO, 1);
		LOGGER.info("LIST SIZE -> " + applicationVOs.size());
		Assert.assertNotNull(applicationVOs);	
	}

	@Test
	public void testLockPHQRecord() {
		LOGGER.info("TESTING PHQ LOCK RECORD FUNCTIONALITY ...");
		Map<String,Object> map = ApplicationBusiness.getInstance().lockPHQRecord(1L,1);
		Assert.assertEquals(PoliceConstant.SUCCESS, map.get("STATUS"));
	}

	@Test
	public void testUnlockPHQRecord() {
		LOGGER.info("TESTING PHQ UNLOCK RECORD FUNCTIONALITY ...");
		String status = ApplicationBusiness.getInstance().unlockPHQRecord(1L,null);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

	@Test
	public void testUpdateReviewStatus() {
		LOGGER.info("TESTING UPDATE REVIEW STATUS FUNCTIONALITY ...");
		String status = ApplicationBusiness.getInstance().updateReviewStatus(1L, "NW",null);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}
	
	@Test
	public void testReviewApplcationStatusList () {
		Map<String,PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap = 
				PoliceEnumConstant.ApplicationReviewStatus.getApplicationReviewStatusMap();

		for (Map.Entry<String,PoliceEnumConstant.ApplicationReviewStatus> entry : reviewStatusMap.entrySet()){
			LOGGER.info("KEY -> " + entry.getKey() + " VALUE -> " + entry.getValue());
		}
		Assert.assertNotNull(reviewStatusMap);	
	}
	

	@Test
	public void testAddApplicationModifiedDate() {
		long applicationId=15;
		String dateType="VFC";
		String modification="This is a long long modification by OIC";
		int modifiedUserId=5;
		String modifiedUserName="Test User";
		String status=ApplicationBusiness.getInstance().addApplicationModifiedDate(applicationId, dateType, modification, modifiedUserId, modifiedUserName, null);
		Assert.assertEquals(status, PoliceConstant.SUCCESS);
	}

}
