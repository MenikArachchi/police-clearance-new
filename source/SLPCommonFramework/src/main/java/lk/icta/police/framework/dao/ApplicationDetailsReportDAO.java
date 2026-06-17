package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationDetailsReportSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDetailsReportDAO {
    private static ApplicationDetailsReportDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(ApplicationDetailsReportDAO.class);

    public static ApplicationDetailsReportDAO getInstance() {
        synchronized (ApplicationDetailsReportDAO.class) {
            if (instance == null) {
                instance = new ApplicationDetailsReportDAO();
            }
            return instance;
        }
    }

    private ApplicationDetailsReportDAO() {

    }

    public List<ApplicationVO> fetchApplicationVerificationList(ApplicationDetailsReportSearchCriteriaVO applicationVerificationSearchCriteriaVO,
                                                                Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();

        try {
            LOGGER.info("QUERY -> " + DBConstant.QUERY.SEARCH_ApplicationdetailsReport);
//			LOGGER.info("1 -> " + CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getFromDate()));
//			LOGGER.info("2 -> " + applicationVerificationSearchCriteriaVO.getFromDateSqlInjection());
//			LOGGER.info("3 -> " + CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getToDate()));
//			LOGGER.info("4 -> " + applicationVerificationSearchCriteriaVO.getToDateSqlInjection());
//			LOGGER.info("5 -> " + applicationVerificationSearchCriteriaVO.getStatus());
//			LOGGER.info("6 -> " + applicationVerificationSearchCriteriaVO.getStatusSqlInjection());
//			LOGGER.info("5 -> " + applicationVerificationSearchCriteriaVO.getClearanceStatus());
//			LOGGER.info("6 -> " + applicationVerificationSearchCriteriaVO.getClearanceStatusSqlInjection());
//			LOGGER.info("7 -> " + applicationVerificationSearchCriteriaVO.getReferenceNo());
//			LOGGER.info("8 -> " + applicationVerificationSearchCriteriaVO.getReferenceNoSqlInjection());
//			LOGGER.info("9 -> " + PoliceEnumConstant.ApplicationReviewStatus.TS.toString());

            pstm = conn.prepareStatement(DBConstant.QUERY.SEARCH_ApplicationdetailsReport);
            pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getFromDate()));
            pstm.setInt(2, applicationVerificationSearchCriteriaVO.getFromDateSqlInjection());
            pstm.setDate(3, CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getToDate()));
            pstm.setInt(4, applicationVerificationSearchCriteriaVO.getToDateSqlInjection());
            pstm.setString(5, applicationVerificationSearchCriteriaVO.getStatus());
            pstm.setInt(6, applicationVerificationSearchCriteriaVO.getStatusSqlInjection());
            pstm.setString(7, applicationVerificationSearchCriteriaVO.getClearanceStatus());
            pstm.setInt(8, applicationVerificationSearchCriteriaVO.getClearanceStatusSqlInjection());
            pstm.setString(9, applicationVerificationSearchCriteriaVO.getReferenceNo());
            pstm.setInt(10, applicationVerificationSearchCriteriaVO.getReferenceNoSqlInjection());

            pstm.setString(11, applicationVerificationSearchCriteriaVO.getNicNo() + "%");
            pstm.setInt(12, applicationVerificationSearchCriteriaVO.getNicNoSqlInjection());

            pstm.setString(13, applicationVerificationSearchCriteriaVO.getPassportNo() + "%");
            pstm.setInt(14, applicationVerificationSearchCriteriaVO.getPassportNoSqlInjection());

            pstm.setString(15, "%" + applicationVerificationSearchCriteriaVO.getName() + "%");
            pstm.setString(16, "%" + applicationVerificationSearchCriteriaVO.getName() + "%");
            pstm.setInt(17, applicationVerificationSearchCriteriaVO.getNameSqlInjection());

            pstm.setLong(18, applicationVerificationSearchCriteriaVO.getCountryId());
            pstm.setInt(19, applicationVerificationSearchCriteriaVO.getCountrySqlInjection());

            pstm.setString(20, applicationVerificationSearchCriteriaVO.getNewNicNo() + "%");
            pstm.setInt(21, applicationVerificationSearchCriteriaVO.getNewNicNoSqlInjection());
//			pstm.setString(9, PoliceEnumConstant.ApplicationReviewStatus.TS.toString());
            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("Empty Set");
            } else {
                do {
                    applicationVO = new ApplicationVO();
                    applicationVO = getConstructedApplicationFromResultSet(rst);
                    applicationVOs.add(applicationVO);
                } while (rst.next());
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }

        return applicationVOs;
    }

    public ApplicationVO getConstructedApplicationFromResultSet(ResultSet rst) throws SQLException {
        ApplicationVO applicationVO = new ApplicationVO();

        applicationVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID));
        applicationVO.setReferenceNo(rst.getString(DBConstant.APPLICATION_COL.REFERENCE_NO));
        applicationVO.setPreviousReferenceNo(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_REFERENCE_NO));
        applicationVO.setNationalityId(rst.getLong(DBConstant.APPLICATION_COL.NATIONALITY_ID));
        applicationVO.setNationality(rst.getString(DBConstant.APPLICATION_COL.NATIONALITY));
        applicationVO.setCitizenOfSriLanka(rst.getInt(DBConstant.APPLICATION_COL.CITIZEN_OF_LANKA));
        applicationVO.setCitizenshipObtainedDate(rst.getDate(DBConstant.APPLICATION_COL.CITIZENSHIP_OBTAINED_DATE));
        applicationVO.setDateOfBirth(rst.getTimestamp(DBConstant.APPLICATION_COL.DATE_OF_BIRTH));
        applicationVO.setAge(rst.getInt(DBConstant.APPLICATION_COL.AGE));

        String nic = rst.getString(DBConstant.APPLICATION_COL.NIC);
        applicationVO.setNic(nic);

        String newNIC = rst.getString(DBConstant.APPLICATION_COL.NEW_NIC);
        applicationVO.setNewNic(newNIC);

        applicationVO.setPassport(rst.getString(DBConstant.APPLICATION_COL.PASSPORT));
        applicationVO.setHighCommisionReferenceId(rst.getLong(DBConstant.APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID));
        applicationVO.setHighCommisionReference(rst.getString(DBConstant.APPLICATION_COL.HIGH_COMMISION_REFERENCE));
        applicationVO.setApplicantNameAsNic(rst.getString(DBConstant.APPLICATION_COL.APPLICANT_NAME_AS_NIC));
        applicationVO.setApplicantNameAsPassport(rst.getString(DBConstant.APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT));
        applicationVO.setPassportIssueDate(rst.getDate(DBConstant.APPLICATION_COL.PASSPORT_ISSUE_DATE));
        applicationVO.setPresentAddressLocal(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_LOCAL));
        applicationVO.setPresentAddressOverseas(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS));
        applicationVO.setSex(rst.getString(DBConstant.APPLICATION_COL.SEX));
        applicationVO.setApplicantStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICANT_STATUS));
        applicationVO.setOccupation(rst.getString(DBConstant.APPLICATION_COL.OCCUPATION));
        applicationVO.setPurpose(rst.getString(DBConstant.APPLICATION_COL.PURPOSE));
        applicationVO.setPreviousCertificateApply(rst.getInt(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY));
        applicationVO.setPreviousCertificateCountryId(rst.getLong(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID));
        applicationVO.setPreviousCertificateCountryName(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME));
        applicationVO.setPreviousCertificateIssueStatus(rst.getInt(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS));
        applicationVO.setPreviousCertificateReferenceNo(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO));
        applicationVO.setPreviousCertificateIssueDate(rst.getTimestamp(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE));
        applicationVO.setAuthorizedHandoverPerson(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON));
        applicationVO.setAuthorizedHandoverPersonName(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME));
        applicationVO.setAuthorizedHandoverPersonNicPassport(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT));
        applicationVO.setHighCommisionReferenceAddress(rst.getString(DBConstant.APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS));
        applicationVO.setCertificatePostAddressLineOne(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1));
        applicationVO.setCertificatePostAddressLineTwo(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2));
        applicationVO.setCertificatePostAddressCity(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY));
        applicationVO.setCertificatePostAddressState(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE));
        applicationVO.setCertificatePostAddressPostal(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL));
        applicationVO.setCertificatePostAddressCountry(rst.getLong(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY));
        applicationVO.setCertificatePostAddressCountryName(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME));
        applicationVO.setMobileCountryCode(rst.getString(DBConstant.APPLICATION_COL.MOBILE_COUNTRY_CODE));
        applicationVO.setMobileNo(rst.getString(DBConstant.APPLICATION_COL.MOBILE_NO));
        applicationVO.setEmail(rst.getString(DBConstant.APPLICATION_COL.EMAIL));
        applicationVO.setSpouseFullName(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_FULL_NAME));
        applicationVO.setSpouseAddress(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_ADDRESS));
        applicationVO.setSpouseNationality(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NATIONALITY));
        applicationVO.setSpousePassport(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_PASSPORT));
        applicationVO.setSpouseNic(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NIC));
        applicationVO.setPassportAttachPath(rst.getString(DBConstant.APPLICATION_COL.PASSPORT_ATTACH_PATH));
        applicationVO.setNicAttachPath(rst.getString(DBConstant.APPLICATION_COL.NIC_ATTACH_PATH));
        applicationVO.setBirthCertificatePath(rst.getString(DBConstant.APPLICATION_COL.BIRTH_CERTIFICATE_PATH));
        applicationVO.setReferredThroughBereau(rst.getInt(DBConstant.APPLICATION_COL.REFERRED_THROUGH_BEREAU));
        applicationVO.setLetterOfReferencePath(rst.getString(DBConstant.APPLICATION_COL.LETTER_OF_REFERENCE_PATH));
        applicationVO.setApplicationClearanceStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_CLEARANCE_STATUS));
        applicationVO.setApplicationReviewStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_REVIEW_STATUS));
        applicationVO.setUpdatedUserId(rst.getInt(DBConstant.APPLICATION_COL.UPDATED_USER_ID));
        applicationVO.setUpdatedUserName(rst.getString(DBConstant.APPLICATION_COL.UPDATED_USER_NAME));
        applicationVO.setUpdatedDateTime(rst.getTimestamp(DBConstant.APPLICATION_COL.UPDATED_DATE_TIME));
        applicationVO.setPolStatus(rst.getString(DBConstant.APPLICATION_COL.POL_STATUS));
        applicationVO.setCidStatus(rst.getString(DBConstant.APPLICATION_COL.CID_STATUS));
        applicationVO.setTidStatus(rst.getString(DBConstant.APPLICATION_COL.TID_STATUS));
        applicationVO.setSisStatus(rst.getString(DBConstant.APPLICATION_COL.SIS_STATUS));
        applicationVO.setNicStatus(rst.getString(DBConstant.APPLICATION_COL.NIC_STATUS));
        applicationVO.setImiStatus(rst.getString(DBConstant.APPLICATION_COL.IMI_STATUS));
        applicationVO.setPhqRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.PHQ_RECORD_LOCK_STATUS));
        applicationVO.setCidRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.CID_RECORD_LOCK_STATUS));
        applicationVO.setTidRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.TID_RECORD_LOCK_STATUS));
        applicationVO.setSisRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.SIS_RECORD_LOCK_STATUS));
        applicationVO.setNicRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.NIC_RECORD_LOCK_STATUS));
        applicationVO.setImiRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.IMI_RECORD_LOCK_STATUS));
        applicationVO.setCoApproved(rst.getString(DBConstant.APPLICATION_COL.CO_APPROVED));
        applicationVO.setOicApproved(rst.getString(DBConstant.APPLICATION_COL.OIC_APPROVED));
        applicationVO.setAspApproved(rst.getString(DBConstant.APPLICATION_COL.ASP_APPROVED));
        applicationVO.setDhaApproved(rst.getString(DBConstant.APPLICATION_COL.DHA_APPROVED));
        applicationVO.setCertificateSerialNo(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_SERIAL_NO));
        applicationVO.setApplicationQueue(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_QUEUE));
        applicationVO.setRegPostNo(rst.getString(DBConstant.APPLICATION_COL.REG_POST_NO));
        applicationVO.setIpAddress(rst.getString(DBConstant.APPLICATION_COL.IP_ADDRESS));
        applicationVO.setUserFullName(rst.getString(DBConstant.APPLICATION_COL.USER_FULL_NAME));
        applicationVO.setUserEmail(rst.getString(DBConstant.APPLICATION_COL.USER_EMAIL));
        applicationVO.setAuthProvider(rst.getString(DBConstant.APPLICATION_COL.AUTH_PROVIDER));
        applicationVO.setCertificatePrintedStatus(rst.getInt(DBConstant.APPLICATION_COL.CERTIFICATE_PRINTED_STATUS));
        applicationVO.setApplicationType(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_TYPE));
        applicationVO.setCertificateAuthPersonId(rst.getLong(DBConstant.APPLICATION_COL.CERTIFICATE_AUTH_PERSON_ID));
        applicationVO.setTransactionId(rst.getLong(DBConstant.APPLICATION_COL.TRANSACTION_ID));
        applicationVO.setSubmittedDate(rst.getTimestamp(DBConstant.APPLICATION_COL.SUBMITTED_DATE));
        applicationVO.setCertificatePostedDate(rst.getTimestamp(DBConstant.APPLICATION_COL.CERTIFICATE_POSTED_DATE));
        applicationVO.setCountryId(rst.getLong(DBConstant.APPLICATION_COL.COUNTRY_ID));
        applicationVO.setCountryName(rst.getString(DBConstant.APPLICATION_COL.COUNTRY_NAME));

        String currentNIC = "";

        if (newNIC != null && !newNIC.equals("")) {
            currentNIC = newNIC;
        } else if ((newNIC == null || newNIC.equals("")) && nic != null && !nic.equals("")) {
            currentNIC = nic;
        } else {
            currentNIC = "N/A";
        }

        applicationVO.setCurrentNicNo(currentNIC);
        return applicationVO;
    }

    //	public ApplicationVO getConstructedApplicationFromResultSet(ResultSet rst) throws SQLException{
//		ApplicationVO applicationVO=new ApplicationVO();
//    	applicationVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID));
//    	applicationVO.setReferenceNo(rst.getString(DBConstant.APPLICATION_COL.REFERENCE_NO));
//    	applicationVO.setPreviousReferenceNo(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_REFERENCE_NO));
//    	applicationVO.setNic(rst.getString(DBConstant.APPLICATION_COL.NIC));
//    	applicationVO.setPassport(rst.getString(DBConstant.APPLICATION_COL.PASSPORT));
//    	applicationVO.setCountryId(rst.getInt(DBConstant.APPLICATION_COL.COUNTRY_ID));
//    	applicationVO.setCountryName(rst.getString(DBConstant.APPLICATION_COL.COUNTRY_NAME));
//    	applicationVO.setApplicantName(rst.getString(DBConstant.APPLICATION_COL.APPLICANT_NAME));
//    	applicationVO.setHighCommisionReference(rst.getString(DBConstant.APPLICATION_COL.HIGH_COMMISION_REFERENCE));
//    	applicationVO.setNationality(rst.getString(DBConstant.APPLICATION_COL.NATIONALITY));
//    	applicationVO.setDateOfBirth(rst.getTimestamp(DBConstant.APPLICATION_COL.DATE_OF_BIRTH));
//    	applicationVO.setAge(rst.getInt(DBConstant.APPLICATION_COL.AGE));
//    	applicationVO.setSex(rst.getString(DBConstant.APPLICATION_COL.SEX));
//    	applicationVO.setOccupation(rst.getString(DBConstant.APPLICATION_COL.OCCUPATION));
//    	applicationVO.setPurpose(rst.getString(DBConstant.APPLICATION_COL.PURPOSE));
//    	applicationVO.setApplicantStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICANT_STATUS));
//    	applicationVO.setPreviousCertificateApply(rst.getInt(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY));
//    	applicationVO.setPreviousCertificateCountryId(rst.getLong(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID));
//    	applicationVO.setPreviousCertificateCountryName(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME));
//    	applicationVO.setPreviousCertificateIssueStatus(rst.getInt(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS));
//    	applicationVO.setPreviousCertificateReferenceNo(rst.getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO));
//    	applicationVO.setPreviousCertificateIssueDate(rst.getTimestamp(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE));
//    	applicationVO.setPresentAddressLocal(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_LOCAL));
//    	applicationVO.setPresentAddressOverseas(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS));
//    	applicationVO.setAuthorizedHandoverPerson(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON));
//    	applicationVO.setAuthorizedHandoverPersonName(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME));
//    	applicationVO.setAuthorizedHandoverPersonNic(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC));
//    	applicationVO.setAuthorizedHandoverPersonPassport(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_PASSPORT));
//    	applicationVO.setCertificateIndicateAddress(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_INDICATE_ADDRESS));
//    	applicationVO.setCertificatePostAddressLineOne(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1));
//    	applicationVO.setCertificatePostAddressLineTwo(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2));
//    	applicationVO.setCertificatePostAddressCity(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY));
//    	applicationVO.setCertificatePostAddressState(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE));
//    	applicationVO.setCertificatePostAddressPostal(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL));
//    	applicationVO.setCertificatePostAddressCountry(rst.getLong(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY));
//    	applicationVO.setCertificatePostAddressCountryName(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME));
//    	applicationVO.setMobileNo(rst.getString(DBConstant.APPLICATION_COL.MOBILE_NO));
//    	applicationVO.setEmail(rst.getString(DBConstant.APPLICATION_COL.EMAIL));
//    	applicationVO.setPassportAttachPath(rst.getString(DBConstant.APPLICATION_COL.PASSPORT_ATTACH_PATH));
//    	applicationVO.setNicAttachPath(rst.getString(DBConstant.APPLICATION_COL.NIC_ATTACH_PATH));
//    	applicationVO.setSpouseFullName(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_FULL_NAME));
//    	applicationVO.setSpouseNationality(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NATIONALITY));
//    	applicationVO.setSpousePassport(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_PASSPORT));
//    	applicationVO.setSpouseNic(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NIC));
//    	applicationVO.setBookRecieptNo(rst.getString(DBConstant.APPLICATION_COL.BOOK_RECIEPT_NO));
//    	applicationVO.setApplicationClearanceStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_CLEARANCE_STATUS));
//    	applicationVO.setApplicationReviewStatus(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_REVIEW_STATUS));
//    	applicationVO.setUpdatedUserId(rst.getInt(DBConstant.APPLICATION_COL.UPDATED_USER_ID));
//    	applicationVO.setUpdatedUserName(rst.getString(DBConstant.APPLICATION_COL.UPDATED_USER_NAME));
//    	applicationVO.setUpdatedDateTime(rst.getTimestamp(DBConstant.APPLICATION_COL.UPDATED_DATE_TIME));
//    	applicationVO.setPolStatus(rst.getString(DBConstant.APPLICATION_COL.POL_STATUS));
//    	applicationVO.setCidStatus(rst.getString(DBConstant.APPLICATION_COL.CID_STATUS));
//    	applicationVO.setTidStatus(rst.getString(DBConstant.APPLICATION_COL.TID_STATUS));
//    	applicationVO.setSisStatus(rst.getString(DBConstant.APPLICATION_COL.SIS_STATUS));
//    	applicationVO.setNicStatus(rst.getString(DBConstant.APPLICATION_COL.NIC_STATUS));
//    	applicationVO.setImiStatus(rst.getString(DBConstant.APPLICATION_COL.IMI_STATUS));
//    	applicationVO.setPhqRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.PHQ_RECORD_LOCK_STATUS));
//    	applicationVO.setCidRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.CID_RECORD_LOCK_STATUS));
//    	applicationVO.setTidRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.TID_RECORD_LOCK_STATUS));
//    	applicationVO.setSisRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.SIS_RECORD_LOCK_STATUS));
//    	applicationVO.setNicRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.NIC_RECORD_LOCK_STATUS));
//    	applicationVO.setImiRecordLockStatus(rst.getInt(DBConstant.APPLICATION_COL.IMI_RECORD_LOCK_STATUS));
//    	applicationVO.setCoApproved(rst.getString(DBConstant.APPLICATION_COL.CO_APPROVED));
//    	applicationVO.setOicApproved(rst.getString(DBConstant.APPLICATION_COL.OIC_APPROVED));
//    	applicationVO.setAspApproved(rst.getString(DBConstant.APPLICATION_COL.ASP_APPROVED));
//    	applicationVO.setDhaApproved(rst.getString(DBConstant.APPLICATION_COL.DHA_APPROVED));
//    	applicationVO.setCertificateSerialNo(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_SERIAL_NO));
//    	applicationVO.setApplicationQueue(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_QUEUE));
//    	applicationVO.setRegPostNo(rst.getString(DBConstant.APPLICATION_COL.REG_POST_NO));
//    	applicationVO.setIpAddress(rst.getString(DBConstant.APPLICATION_COL.IP_ADDRESS));
//    	
//    	return applicationVO;
//	}
}
