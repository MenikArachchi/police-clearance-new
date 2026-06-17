package lk.icta.police.business;

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
import lk.icta.police.framework.mock.MockAddressTempVOUtil;
import lk.icta.police.framework.mock.MockApplicationVOUtil;
import lk.icta.police.framework.mock.MockDAOUtil;
import lk.icta.police.framework.mock.MockDBConstants;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CommentsVO;
import lk.icta.police.framework.vo.PoliceAreaVO;
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
		databasetablesToTruncate.add(MockDBConstants.TEST_DB_TABLE.ADDRESS_TEMP.getCode());
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
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setCidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
			applicationVO.setTidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
			applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
			applicationVO.setSisStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
			applicationVO.setImiStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
			applicationId = ApplicationBusiness.getInstance().addApplication(applicationVO);	
			LOGGER.info("applicationId :" + applicationId);
		}		
	}

	@Test
	public void testSearchApplication() {
		ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO=new ReviewApplicationExternalSearchCriteriaVO(null, null, null, null, null);
		searchCriteriaVO.setLimit(999999);
		int userRole=PoliceEnumConstant.UserDepartment.POL.getCode();
		int location=1;
		searchCriteriaVO.updateSearchCriteriaVO(userRole, location);
		int userId=4;
		Map<String, Object> session = null;
		
		LOGGER.info("searchCriteriaVO :" + searchCriteriaVO);
		List<ApplicationVO> vos=ReviewApplicationExternalBusiness.getInstance().searchApplication(searchCriteriaVO, userRole, location,userId, session);
		LOGGER.info("vos :" + vos);
		LOGGER.info("vos.size() :" + vos.size());
		Assert.assertEquals(3, vos.size());
	}
	
	
//	//@Test
//	public void testGetTotalApplicationListCount() {
//		ReviewApplicationExternalSearchCriteriaVO searchCriteriaVO=new ReviewApplicationExternalSearchCriteriaVO(null, null, null, null, null);
//		int userRole=PoliceEnumConstant.UserDepartment.NIC.getCode();
//		int location=0;
//		searchCriteriaVO.updateSearchCriteriaVO(userRole, location);
//		int userId=1;
//		LOGGER.info("searchCriteriaVO :" + searchCriteriaVO);
//		long count=ReviewApplicationExternalBusiness.getInstance().getTotalApplicationListCount(searchCriteriaVO, userRole, location, userId);
//		LOGGER.info("count :" + count);
//		Assert.assertEquals(3, count);
//	}

	//@Test
	public void testCheckAndLockRecord() {
		long applicationId=1;
		int lockedUserId=3;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=1;
		Map<String,Object> map=CertificateIssuanceBusiness.getInstance().checkAndLockRecord(applicationId, lockedUserId);
		String status=(String) map.get("MESSAGE");
		LOGGER.info("status :" + status);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
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
	
	
	//@Test
	public void testUnlockCooRecord() {
		long applicationId=1;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=1;
		String status=ReviewApplicationExternalBusiness.getInstance().unlockCooRecord(applicationId, userRole, locationId);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

	//@Test
	public void testGetAddressByTypeApplicationLockedUser() {
		long applicationId=1;
		int userId=1;
		long locationId=1;
		AddressVO addressVO=ReviewApplicationExternalBusiness.getInstance().getAddressByTypeApplicationLockedUser(applicationId, userId, locationId);
		Assert.assertNotNull(addressVO);
	}

	@Test
	public void testCheckAndLockAddressRecord() {
		long applicationId=1;
		int lockedUserId=1;
		int userRole=PoliceEnumConstant.UserDepartment.POL.getCode();
		long locationId=1;
		long addressId=1;
		Map<String,Object> map=CertificateIssuanceBusiness.getInstance().checkAndLockRecord(applicationId, lockedUserId);
		String status=(String) map.get("MESSAGE");
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

	//@Test
	public void testCheckAndUnLockAddressRecord() {
		long applicationId=1;
		int lockedUserId=1;
		int userRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		long locationId=1;
		long addressId=2;
		String status=ReviewApplicationExternalBusiness.getInstance().checkAndUnLockAddressRecord(applicationId, addressId, lockedUserId, locationId, userRole);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

	//@Test
	public void testListAllPoliceAreas() {
		List<PoliceAreaVO> policeAreaVOs=ReviewApplicationExternalBusiness.getInstance().listAllPoliceAreas();
		Assert.assertNotNull(policeAreaVOs);
	}
	
	//@Test
	public void testGetAddressById(){
		long addressId=1;
		AddressVO addressVO=ReviewApplicationExternalBusiness.getInstance().getAddressById(addressId);
		Assert.assertNotNull(addressVO);
	}
	
	
	//@Test
	public void testSaveAddressTemp() {
		AddressTempVO addressTempVO=MockAddressTempVOUtil.getAddressTempVO(1, 1, 1);
		String res=ReviewApplicationExternalBusiness.getInstance().saveAddressTemp(addressTempVO);
		Assert.assertEquals(PoliceConstant.SUCCESS, res);
	}
	
	
	//@Test
	public void testGetActiveAddressTempByIdAddressId() {
		AddressTempVO addressTempVO=ReviewApplicationExternalBusiness.getInstance().getActiveAddressTempByIdAddressId(1);
		Assert.assertNotNull(addressTempVO);
	}
	
	//@Test
	public void testUpdateAddressReviewApprovalStatus(){
		String comment="Fist Comment";
		int createdUserId=1;
		String createdUserName="Comenter";
		int createdUserRole=PoliceEnumConstant.UserDepartment.POL.getCode();
		Date createdDateTime=Calendar.getInstance().getTime();
		String commentType=PoliceEnumConstant.CommentType.POL.toString();
		long addressId=1;
		long applicationId=1;
		String approvalStatus=PoliceEnumConstant.ApprovalStatus.AP.toString();
		CommentsVO commentsVO=new CommentsVO(comment, createdUserId, createdUserName, createdUserRole, createdDateTime, commentType, applicationId, addressId);
		String status=ReviewApplicationExternalBusiness.getInstance().updateAddressReviewApprovalStatus(addressId, approvalStatus, commentsVO);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}
	
	@Test
	public void testgetEmailContentByEmailType(){
		PoliceEnumConstant.EmailType emailTypeEnum=PoliceEnumConstant.EmailType.UP;
		AddressTempVO addressTempVO=MockAddressTempVOUtil.getAddressTempVO(1, 1, 2);
		String emailContent=ReviewApplicationExternalBusiness.getInstance().getEmailContentByEmailType(emailTypeEnum, addressTempVO);
	}
	
	
	@Test
	public void testUpdateAddressEditPhq(){
		AddressTempVO addressTempVO=MockAddressTempVOUtil.getAddressTempVO(1, 1, 2);
		String comment="Fist Comment";
		int createdUserId=1;
		String createdUserName="Comenter";
		int createdUserRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		Date createdDateTime=Calendar.getInstance().getTime();
		String commentType=PoliceEnumConstant.CommentType.POL.toString();
		long applicationId=1;
		long addressId=1;
		
		CommentsVO commentsVO=new CommentsVO(comment, createdUserId, createdUserName, createdUserRole, createdDateTime, commentType, applicationId, addressId);
		
		String status=ReviewApplicationExternalBusiness.getInstance().updateAddressEditPhq(addressTempVO, commentsVO);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}

}
