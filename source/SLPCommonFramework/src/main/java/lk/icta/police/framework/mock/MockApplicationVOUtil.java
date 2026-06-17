package lk.icta.police.framework.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.TransactionVO;

public class MockApplicationVOUtil {
	
	public static ApplicationVO getApplicationVO(int applicationId) {
		ApplicationVO applicationVO = new ApplicationVO();

		//applicationVO.setApplicationId();
		applicationVO.setReferenceNo("REF000" + applicationId);
		applicationVO.setPreviousReferenceNo("");
		applicationVO.setNationalityId(1);
		applicationVO.setNationality("sri lankan");
		applicationVO.setCitizenOfSriLanka(1);
		applicationVO.setCitizenshipObtainedDate(new Date());
		applicationVO.setDateOfBirth(new Date());
		applicationVO.setAge(25);
		applicationVO.setNic("8745362" + applicationId + "V");
		applicationVO.setPassport("111112" + applicationId);
		applicationVO.setHighCommisionReferenceId(1);
		applicationVO.setHighCommisionReference("xyz");
		applicationVO.setApplicantNameAsNic("Test User "+applicationId);
		applicationVO.setApplicantNameAsPassport("Test User "+applicationId);
		applicationVO.setPassportIssueDate(new Date());
		applicationVO.setPresentAddressLocal("Test Address "+applicationId);
		applicationVO.setPresentAddressOverseas("Test Address "+applicationId);
		applicationVO.setSex("M");
		applicationVO.setOccupation("SE");
		applicationVO.setPurpose("EMP");
		applicationVO.setApplicantStatus("MAR");
		applicationVO.setPreviousCertificateApply(0);
		applicationVO.setPreviousCertificateCountryId(1);
		applicationVO.setPreviousCertificateCountryName("Tst Country");
		applicationVO.setPreviousCertificateIssueStatus(1);
		applicationVO.setPreviousCertificateReferenceNo("xyz0001"+applicationId);
		applicationVO.setPreviousCertificateIssueDate(new Date());
		applicationVO.setAuthorizedHandoverPerson("MOT");
		applicationVO.setAuthorizedHandoverPersonName("Test Person Name "+applicationId);
		applicationVO.setAuthorizedHandoverPersonNicPassport("nicpassport "+applicationId);
		applicationVO.setHighCommisionReferenceAddress("REF_Address" + applicationId);
		applicationVO.setCertificatePostAddressLineOne("address Line one "+applicationId);
		applicationVO.setCertificatePostAddressLineTwo("address Line two "+applicationId);
		applicationVO.setCertificatePostAddressCity("city");
		applicationVO.setCertificatePostAddressState("state");
		applicationVO.setCertificatePostAddressPostal("2000");
		applicationVO.setCertificatePostAddressCountry(1);
		applicationVO.setCertificatePostAddressCountryName("sri lanka");
		applicationVO.setMobileCountryCodeId(1);
		applicationVO.setMobileCountryCode("+94");
		applicationVO.setMobileNo("098098098");
		applicationVO.setEmail("test@gmail.com");
		applicationVO.setSpouseFullName("spouse");
		applicationVO.setSpouseNationality("sri lankan");
		applicationVO.setSpousePassport("123123123");
		applicationVO.setSpouseNic("123456789V");
		applicationVO.setNicAttachPath("image.jpg");
		applicationVO.setPassportAttachPath("nic.jpg");
		applicationVO.setBirthCertificatePath("image.jpg");
		applicationVO.setLetterOfReferencePath("image.jpg");
		applicationVO.setReferredThroughBereau(1);
		
		applicationVO.setCountryId(1);
		applicationVO.setCountryName("Test Country");
		
		
		applicationVO.setPolStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setCidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setTidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setSisStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setImiStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		
		
		
		if(applicationId==1){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.NC.toString());	
			applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.RJ.toString());
			applicationVO.setEmail("nadee158@gmail.com");
		}
		
		if(applicationId%2==0){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
			applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.RJ.toString());
			applicationVO.setEmail("nadee158@gmail.com");
		}
		
		if(applicationId%3==0){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString());
			applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.RJ.toString());
			applicationVO.setEmail("nadee158@gmail.com");
		}
		
		if(applicationId%4==0){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.EI.toString());		
		}
		
		if(applicationId%5==0){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.GC.toString());		
		}
		
		
		if(applicationId==0){
			applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
			applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());	
			applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.RJ.toString());
			applicationVO.setEmail("nadee158@gmail.com");
		}
				
		
		applicationVO.setUpdatedUserId(1);
		applicationVO.setUpdatedUserName("UserName"+applicationId);
		applicationVO.setUpdatedDateTime(new Date());
		
		applicationVO.setPhqRecordLockStatus(0);
		applicationVO.setCidRecordLockStatus(0);
		applicationVO.setTidRecordLockStatus(0);
		applicationVO.setSisRecordLockStatus(0);
		applicationVO.setNicRecordLockStatus(0);
		applicationVO.setImiRecordLockStatus(0);
		applicationVO.setCoApproved(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setOicApproved(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setAspApproved(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setDhaApproved(PoliceEnumConstant.ApprovalStatus.PN.toString());
		applicationVO.setCertificateSerialNo("123");
		applicationVO.setApplicationQueue("");
		applicationVO.setRegPostNo("123");
		applicationVO.setIpAddress("12.12.12.12.");
		applicationVO.setUserFullName("user full name "+applicationId);
		applicationVO.setUserEmail("user"+applicationId+"@gmail.com");
		applicationVO.setAuthProvider(PoliceEnumConstant.ApplicationAuthProvider.GM.toString());
		applicationVO.setCertificatePrintedStatus(0);
		applicationVO.setApplicationType(PoliceEnumConstant.ApplicationType.MA.toString());
		applicationVO.setCertificateAuthPersonId(1);
		applicationVO.setTransactionId(1);
		
		applicationVO.setApplicantCertificateAddresses(MockAddressVOUtil.getApplicationVOListByAddressType(0, 2, applicationId, PoliceEnumConstant.AddressType.AC.toString()));
		
		return applicationVO;
	}
	
	public static List<ApplicationVO> getApplicationVOList(int startFrom, int limit) {
		List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
		
		for (int i = startFrom; i <= limit; i++) {
			applicationVOs.add(getApplicationVO(i));
		}
		
		return applicationVOs;
	}
	
	public static TransactionVO getTransactionVO(int transactionId) {
		TransactionVO transactionVO = new TransactionVO();
		
		transactionVO.setTransactionReferenceNo("TR000"+transactionId);
		transactionVO.setChequeNo("ch"+transactionId);
		transactionVO.setAccountNo("acc"+transactionId);
		transactionVO.setAccountHolderName("accountHolderName"+transactionId);
		transactionVO.setBookReceiptNo("btn"+transactionId);
		transactionVO.setDescription("description"+transactionId);
		transactionVO.setApplicationFee(new BigDecimal(100));
		transactionVO.setPostageFee(new BigDecimal(100));
		transactionVO.setServiceFee(new BigDecimal(100));
		transactionVO.setConvenienceFee(new BigDecimal(100));
		transactionVO.setTotalFee(new BigDecimal(100));
		transactionVO.setPaymentDate(new Date());
		transactionVO.setPaymentStatus(PoliceEnumConstant.PaymentStatus.PEND.toString());
		transactionVO.setPaymentGatewayName("sss");
		transactionVO.setPaymentInitiatedTime(new Date());
		transactionVO.setPaymentConfirmedTime(new Date());
		transactionVO.setPaymentType(null);
		transactionVO.setPaymentMode(PoliceEnumConstant.PaymentMode.CHEQ.toString());
		transactionVO.setCreatedDate(new Date());
		transactionVO.setVersionId(1);
		transactionVO.setApplicationId(1);
		transactionVO.setUserFullName("test User");
		transactionVO.setUserEmail("test@gmail.com");
		transactionVO.setAuthProvider("FB");
		
		return transactionVO;
	}

}
