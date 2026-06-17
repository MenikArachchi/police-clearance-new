package lk.icta.police.external.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.mock.MockDAOUtil;
import lk.icta.police.framework.mock.MockDBConstants;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CommentsVO;
import lk.icta.police.framework.vo.ReviewApplicationExternalSearchCriteriaVO;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Nadeeshani Senevirathna
 *
 */
public class ReviewApplicationExternalBusinessTest {
	
	private static final Logger LOGGER = Logger.getLogger(ReviewApplicationExternalBusinessTest.class);
	
	@BeforeClass
	public static void clearAndPreparePreConditions() throws BusinessException, SQLException{
		LOGGER.info("CLEARING AN DTRUNCATING TABLES OF TEST DATABASE :");
		//clearTables();
		
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
		String result=MockDAOUtil.getInstance().truncateTestDatabasetables(databasetablesToTruncate,conn);
		Assert.assertEquals("SUCCESS", result);	
		conn.commit();
		DatabaseManager.close(conn);
	}
	
	

	@Test
	public void testSearchApplication() {
		ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO=new ReviewApplicationExternalSearchCriteriaVO(null, null, null, null, null);
		searchCriteriaVO.setLimit(999999);
		int userRole=PoliceEnumConstant.UserDepartment.POL.getCode();
		int location=1;
		searchCriteriaVO.updateSearchCriteriaVO(userRole, location);
		int userId=4;
		LOGGER.info("searchCriteriaVO :" + searchCriteriaVO);
		List<ApplicationVO> vos=ReviewApplicationExternalBusiness.getInstance().searchApplication(searchCriteriaVO, userRole, location,userId);
		LOGGER.info("vos :" + vos);
		LOGGER.info("vos.size() :" + vos.size());
		Assert.assertEquals(3, vos.size());
	}
	
	
	//@Test
	public void testGetTotalApplicationListCount() {
		ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO=new ReviewApplicationExternalSearchCriteriaVO(null, null, null, null, null);
		int userRole=PoliceEnumConstant.UserDepartment.NIC.getCode();
		int location=0;
		searchCriteriaVO.updateSearchCriteriaVO(userRole, location);
		int userId=1;
		LOGGER.info("searchCriteriaVO :" + searchCriteriaVO);
		long count=ReviewApplicationExternalBusiness.getInstance().getTotalApplicationListCount(searchCriteriaVO, userRole, location, userId);
		LOGGER.info("count :" + count);
		Assert.assertEquals(3, count);
	}

	//@Test
	public void testCheckAndLockRecord() {
		long applicationId=1;
		int lockedUserId=3;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=1;
		 Map<String,Object> map=ReviewApplicationExternalBusiness.getInstance().checkAndLockRecord(applicationId, lockedUserId, userRole, locationId);
		LOGGER.info("status :" + map.get("MESSAGE"));
		Assert.assertEquals(PoliceConstant.SUCCESS, map.get("MESSAGE"));
	}

	

	//@Test
	public void testGetAddressListByUserRole() {
		long applicationId=1;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=2;
		List<AddressVO> addresses=ReviewApplicationExternalBusiness.getInstance().getAddressListByUserRole(applicationId, userRole, locationId, null);
		LOGGER.info("addresses :" + addresses);
		LOGGER.info("addresses.size() :" + addresses.size());
		Assert.assertEquals(3, addresses.size());
	}

	//@Test
	public void testUpdateApplicationReviewApprovalStatus() {
		String approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
		long locationId=1;
		
		String comment="Fist Comment";
		int createdUserId=3;
		String createdUserName="Comenter";
		int createdUserRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		Date createdDateTime=Calendar.getInstance().getTime();
		String commentType=PoliceEnumConstant.CommentType.POL.toString();
		long applicationId=1;
		long addressId=1;
		
		CommentsVO commentsVO=new CommentsVO(comment, createdUserId, createdUserName, createdUserRole, createdDateTime, commentType, applicationId, addressId);
		String status=ReviewApplicationExternalBusiness.getInstance().updateApplicationReviewApprovalStatus(approvalStatus, locationId, commentsVO);
		LOGGER.info("status :" + status);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}
	
	
	//@Test
	public void testCheckAndUnLockRecord() {
		long applicationId=1;
		int lockedUserId=3;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=1;
		String status=ReviewApplicationExternalBusiness.getInstance().checkAndUnLockRecord(applicationId, lockedUserId, locationId, userRole);
		LOGGER.info("status :" + status);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

}
