package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import lk.icta.police.framework.vo.CertificateClearenceVO;
import lk.icta.police.framework.vo.CommentsVO;
import lk.icta.police.framework.vo.NicRevisionClearenceVO;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CertificateIssuanceBusinessTest {
	
	private static final Logger LOGGER = Logger.getLogger(CertificateIssuanceBusinessTest.class);
	
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
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.ADDRESS.getCode());	
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.COMMENTS.getCode());	
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.CHANGE_AUDIT.getCode());	
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

	//@Test
	public void testCheckAndLockRecord() {
		long applicationId=3;
		int lockedUserId=1;
		Map<String,Object> map=CertificateIssuanceBusiness.getInstance().checkAndLockRecord(applicationId, lockedUserId);
		String status=(String) map.get("MESSAGE");
		LOGGER.info("status :" + status);
		Assert.assertEquals(status, PoliceConstant.SUCCESS);
	}
	

	//@Test
	public void testGetCommentListByApplicationId() {
		long applicationId=3;
		List<CommentsVO> commentsVOs=CertificateIssuanceBusiness.getInstance().getCommentListByApplicationId(applicationId, null);
		LOGGER.info("commentsVOs :" + commentsVOs);
		Assert.assertNotNull(commentsVOs);
	}

	//@Test
	public void testUpdateApplicationReviewApprovalStatus() {
		long applicationId=3;
		String clearenceStatus=PoliceEnumConstant.ApplicationClearenceStatus.BL.toString();
		String approvalStatus=PoliceEnumConstant.ApprovalStatus.RJ.toString();
		int updatedUserId=1;
		String updatedUserName="Test User";
		int updatedUserType=PoliceEnumConstant.UserType.CN.getCode();
		String certificateSeriaNo=null;
		String registeredPostNo=null;
		String recomendedOfficerName="Test Officer";
		String comment="Test Comment";
		int versionId=0;
		CertificateClearenceVO clearenceVO=new CertificateClearenceVO(applicationId, clearenceStatus, approvalStatus, updatedUserId, updatedUserName, updatedUserType, certificateSeriaNo, registeredPostNo, recomendedOfficerName, comment,versionId);
		String updatedStatus=CertificateIssuanceBusiness.getInstance().updateApplicationReviewApprovalStatus(clearenceVO);
		LOGGER.info("updatedStatus :" + updatedStatus);
		Assert.assertEquals(PoliceConstant.SUCCESS,updatedStatus);
	}
	
	//@Test
	public void testCheckAndUnLockRecord() {
		long applicationId=3;
		int lockedUserId=1;
		String status=CertificateIssuanceBusiness.getInstance().checkAndUnLockRecord(applicationId, lockedUserId);
		LOGGER.info("status :" + status);
		Assert.assertEquals(PoliceConstant.DO_NOT_OWN_LOCK,status );
	}

	//@Test
	public void testUnlockClearenceRecord() {
		long applicationId=3;
		int lockedUserId=1;
		String status=CertificateIssuanceBusiness.getInstance().unlockClearenceRecord(applicationId, lockedUserId);
		LOGGER.info("status#### :" + status);
		Assert.assertEquals(PoliceConstant.ERROR,status);
	}
	
	//@Test
	public void testSendNotificationEmailToApplicant(){
		long applicationId=2;
		String emailText="This is a very very long email text, should replace the actual text retrievd jhjkfdh kldfjv jklb klgbjklbjfg nlk fjhfkl";
		int updatedUserId=1;
		String updatedUserName="Test user";
		String status=CertificateIssuanceBusiness.getInstance().sendNotificationEmailToApplicant(applicationId, emailText, updatedUserId, updatedUserName);
		Assert.assertEquals(PoliceConstant.SUCCESS,status);
	}
	
	
	

	@Test
	public void testUpdateApplicationNICRevisionClearenceStatus() {
		long applicationId=1;
		int lockedUserId=1;
		CertificateIssuanceBusiness.getInstance().checkAndLockRecord(applicationId, lockedUserId);
		String nicFileName="testnic.jpg";
		String nicFileNameBack="testnic.jpg";
		String nicRevisionChangedName="User name in nic changed";
		int nicRevisionVersionId=0;
		int updatedUserId=1;
		String updatedUserName="Test Updated User";
		int updatedUserType=PoliceEnumConstant.UserType.CN.getCode();
		NicRevisionClearenceVO nicRevisionClearenceVO=new NicRevisionClearenceVO(applicationId, nicFileName,nicFileNameBack,
				nicRevisionChangedName, true, nicRevisionVersionId,	updatedUserId, updatedUserName,	updatedUserType);
		nicRevisionClearenceVO.updateNicRevisionClearenceVO(updatedUserId, updatedUserName, updatedUserType);
		nicRevisionClearenceVO.setUpdateClearenceStatus(true);
		String status=CertificateIssuanceBusiness.getInstance().updateApplicationNICRevisionClearenceStatus(nicRevisionClearenceVO);
		Assert.assertEquals(PoliceConstant.SUCCESS,status);
	}


}
