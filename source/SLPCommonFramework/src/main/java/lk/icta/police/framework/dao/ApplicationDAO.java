package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.UserDepartment;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Isuru Dewasurendra
 */

public class ApplicationDAO {

    private static ApplicationDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(ApplicationDAO.class);

    public static ApplicationDAO getInstance() {
        synchronized (ApplicationDAO.class) {
            if (instance == null) {
                instance = new ApplicationDAO();
            }
            return instance;
        }
    }

    private ApplicationDAO() {

    }

    public ApplicationVO getApplication(Connection conn, String referenceNo) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
//        referenceNo = referenceNo.substring(0, referenceNo.length() - 1) + "%";
        LOGGER.info("getApplication :: reference no :" + referenceNo);
        
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATOIN);
            pstm.setString(1, referenceNo);
            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("No application records found for reference no :" + referenceNo);
            } else {
                do {
                    applicationVO = getConstructedApplicationFromResultSet(rst);

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
        return applicationVO;
    }

    public ApplicationVO getCitizenApplicationView(Connection conn, String referenceNo, String userEmail) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;

        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_CITIZEN_APPLICATOIN);
            Integer i = 0;
            pstm.setString(++i, referenceNo);
            pstm.setString(++i, userEmail);
            rst = pstm.executeQuery();
            if (!rst.next()) {
                LOGGER.info("No application records found for reference no :" + referenceNo);
            } else {
                do {
                    applicationVO = getConstructedApplicationFromResultSet(rst);

                } while (rst.next());

                disableCitizenApplicationView(referenceNo);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.error(e.getCause());
        } finally {

            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVO;
    }

    private void disableCitizenApplicationView(String referenceNo) {
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
            connection = DatabaseManager.getPOLDBConnection();
            pstm = connection.prepareStatement(DBConstant.QUERY.DISABLE_CITIZEN_APPLICATION_VIEW);
            Integer i = 0;
            pstm.setBoolean(++i, Boolean.TRUE);
            pstm.setString(++i, referenceNo);
            pstm.executeUpdate();
            LOGGER.info("Updated successfully!");
			
            pstm.close();
        } catch (Exception e) {
            LOGGER.error(e.getCause());
        } finally {
            DatabaseManager.close(pstm);
            DatabaseManager.close(connection);
        }
    }

    public ApplicationVO getAuthApplication(Connection conn, String referenceNo) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATOIN_AUTH);
            pstm.setString(1, referenceNo);

            // System.out.println("---===---===---===-----"+DBConstant.QUERY.SELECT_APPLICATOIN_AUTH);

            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("No application records found for reference no :" + referenceNo);
            } else {
                do {
                    applicationVO = getConstructedApplicationFromResultSet(rst);

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
        return applicationVO;
    }

    public boolean verifyApplication(Connection conn, String nicNo, String newNicNo, String passportNo, long country) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.VERIFY_APPLICATOIN);
            // System.out.println(DBConstant.QUERY.VERIFY_APPLICATOIN);
            pstm.setString(1, nicNo);
            pstm.setString(2, nicNo);
            pstm.setString(3, passportNo);
            pstm.setString(4, passportNo);
            pstm.setLong(5, country);
            pstm.setString(6, newNicNo);
            pstm.setString(7, newNicNo);
            rst = pstm.executeQuery();

            if (!rst.next()) {
                // System.out.println("No application records found for nic no :" + nicNo + " passport : " +
                // passportNo + " country : " + country);
                LOGGER.info("No application records found for nic no :" + nicNo + " passport : " + passportNo + " country : "
                        + country);
                return false;
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return true;
    }


    public long addApplication(Connection conn, ApplicationVO applicationVO) throws Exception {
        PreparedStatement pstm = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.ADD_APPLICATION, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, applicationVO.getReferenceNo());
            pstm.setString(2, applicationVO.getPreviousReferenceNo());
            pstm.setLong(3, applicationVO.getNationalityId());
            pstm.setString(4, applicationVO.getNationality());
            pstm.setInt(5, applicationVO.getCitizenOfSriLanka());
            pstm.setDate(6, CommonUtil.getSqlDateFromUtilDate(applicationVO.getCitizenshipObtainedDate()));
            pstm.setTimestamp(7, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getDateOfBirth()));
            pstm.setInt(8, applicationVO.getAge());
            pstm.setString(9, applicationVO.getNic());
            pstm.setString(10, applicationVO.getPassport());
            pstm.setLong(11, applicationVO.getCountryId());
            pstm.setString(12, applicationVO.getCountryName());
            if (applicationVO.getHighCommisionReferenceId() == 0) {
                pstm.setNull(13, Types.BIGINT);
            } else {
                pstm.setLong(13, applicationVO.getHighCommisionReferenceId());
            }
            pstm.setString(14, applicationVO.getHighCommisionReference());
            pstm.setString(15, applicationVO.getApplicantNameAsNic());
            pstm.setString(16, applicationVO.getApplicantNameAsPassport());
            pstm.setDate(17, CommonUtil.getSqlDateFromUtilDate(applicationVO.getPassportIssueDate()));
            pstm.setString(18, applicationVO.getPresentAddressLocal());
            pstm.setString(19, applicationVO.getPresentAddressOverseas());
            pstm.setString(20, applicationVO.getSex());
            pstm.setString(21, applicationVO.getApplicantStatus());
            pstm.setString(22, applicationVO.getOccupation());
            pstm.setString(23, applicationVO.getPurpose());
            pstm.setInt(24, applicationVO.getPreviousCertificateApply());
            pstm.setLong(25, applicationVO.getPreviousCertificateCountryId());
            pstm.setString(26, applicationVO.getPreviousCertificateCountryName());
            pstm.setInt(27, applicationVO.getPreviousCertificateIssueStatus());
            pstm.setString(28, applicationVO.getPreviousCertificateReferenceNo());
            pstm.setTimestamp(29, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getPreviousCertificateIssueDate()));
            pstm.setString(30, applicationVO.getAuthorizedHandoverPerson());
            pstm.setString(31, applicationVO.getAuthorizedHandoverPersonName());
            pstm.setString(32, applicationVO.getAuthorizedHandoverPersonNicPassport());
            pstm.setString(33, applicationVO.getHighCommisionReferenceAddress());
            pstm.setString(34, applicationVO.getCertificatePostAddressLineOne());
            pstm.setString(35, applicationVO.getCertificatePostAddressLineTwo());
            pstm.setString(36, applicationVO.getCertificatePostAddressCity());
            pstm.setString(37, applicationVO.getCertificatePostAddressState());
            pstm.setString(38, applicationVO.getCertificatePostAddressPostal());
            if (applicationVO.getCertificatePostAddressCountry() == 0) {
                pstm.setNull(39, Types.BIGINT);
            } else {
                pstm.setLong(39, applicationVO.getCertificatePostAddressCountry());
            }
            pstm.setString(40, applicationVO.getCertificatePostAddressCountryName());
            if (applicationVO.getMobileCountryCodeId() == 0) {
                pstm.setNull(41, Types.BIGINT);
            } else {
                pstm.setLong(41, applicationVO.getMobileCountryCodeId());
            }
            pstm.setString(42, applicationVO.getMobileCountryCode());
            pstm.setString(43, applicationVO.getMobileNo());
            pstm.setString(44, applicationVO.getEmail());
            pstm.setString(45, applicationVO.getSpouseFullName());
            pstm.setString(46, applicationVO.getSpouseAddress());
            pstm.setString(47, applicationVO.getSpouseNationality());
            pstm.setString(48, applicationVO.getSpousePassport());
            pstm.setString(49, applicationVO.getSpouseNic());
            pstm.setString(50, applicationVO.getDeliveryType());
            // System.out.println("------applicationVO.getDeliveryType()_in DAO----"+applicationVO.getDeliveryType());
            pstm.setString(51, applicationVO.getForiegnMinistryInvertNo());
            pstm.setString(52, applicationVO.getPassportAttachPath());
            pstm.setString(53, applicationVO.getPassportBackAttachPath());
            pstm.setString(54, applicationVO.getNicAttachPath());
            pstm.setString(55, applicationVO.getNicBackAttachPath());
            pstm.setString(56, applicationVO.getBirthCertificatePath());
            pstm.setString(57, applicationVO.getBirthCertificateBackPath());
            pstm.setInt(58, applicationVO.getReferredThroughBereau());
            pstm.setString(59, applicationVO.getLetterOfReferencePath());
            pstm.setString(60, applicationVO.getApplicationClearanceStatus());
            pstm.setString(61, applicationVO.getApplicationReviewStatus());
            pstm.setInt(62, applicationVO.getUpdatedUserId());
            pstm.setString(63, applicationVO.getUpdatedUserName());
            pstm.setTimestamp(64, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getUpdatedDateTime()));
            pstm.setString(65, applicationVO.getPolStatus());
            pstm.setString(66, applicationVO.getCidStatus());
            pstm.setString(67, applicationVO.getTidStatus());
            pstm.setString(68, applicationVO.getSisStatus());
            pstm.setString(69, applicationVO.getNicStatus());
            pstm.setString(70, applicationVO.getImiStatus());
            pstm.setInt(71, applicationVO.getPhqRecordLockStatus());
            pstm.setInt(72, applicationVO.getCidRecordLockStatus());
            pstm.setInt(73, applicationVO.getTidRecordLockStatus());
            pstm.setInt(74, applicationVO.getSisRecordLockStatus());
            pstm.setInt(75, applicationVO.getNicRecordLockStatus());
            pstm.setInt(76, applicationVO.getImiRecordLockStatus());
            pstm.setString(77, applicationVO.getCoApproved());
            pstm.setString(78, applicationVO.getOicApproved());
            pstm.setString(79, applicationVO.getAspApproved());
            pstm.setString(80, applicationVO.getDhaApproved());
            pstm.setString(81, applicationVO.getCertificateSerialNo());
            pstm.setString(82, applicationVO.getApplicationQueue());
            pstm.setString(83, applicationVO.getRegPostNo());
            pstm.setString(84, applicationVO.getIpAddress());
            pstm.setString(85, applicationVO.getUserFullName());
            pstm.setString(86, applicationVO.getUserEmail());
            pstm.setString(87, applicationVO.getAuthProvider());
            pstm.setInt(88, applicationVO.getCertificatePrintedStatus());
            pstm.setString(89, applicationVO.getApplicationType());
            pstm.setString(90, applicationVO.getNewNic());
            pstm.setString(91, applicationVO.getNewNicAttachPath());
            pstm.setString(92, applicationVO.getNewNicBackAttachPath());
            pstm.setString(93, applicationVO.getSelectedNameOption());
            pstm.setString(94, applicationVO.getAffidavitAttachPath());

            int result = pstm.executeUpdate();
            long applicationId = 0l;
            if (result > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    applicationId = rs.getInt(1);
                }
            }

            applicationVO.setApplicationId(applicationId);
            DatabaseManager.close(result);
			
            pstm.close();
            return applicationId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return 0;
    }

    public boolean verifyApplicationByDepartment(Connection conn, String nicNo, String newNicNo, String passportNo, long country)
            throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.VERIFY_APPLICATOIN_BY_DEPARTMENT);
            // System.out.println(DBConstant.QUERY.VERIFY_APPLICATOIN);
            pstm.setString(1, nicNo);
            pstm.setString(2, nicNo);
            pstm.setString(3, passportNo);
            pstm.setString(4, passportNo);
            pstm.setLong(5, country);
            pstm.setString(6, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());
            pstm.setString(7, PoliceEnumConstant.ApplicationReviewStatus.RJ.toString());
            pstm.setString(8, newNicNo);
            pstm.setString(9, newNicNo);
            rst = pstm.executeQuery();

            if (!rst.next()) {
                // System.out.println("No application records found for nic no :" + nicNo + " passport : " +
                // passportNo + " country : " + country);
                LOGGER.info("No application records found for nic no :" + nicNo + " passport : " + passportNo + " country : "
                        + country);
                return false;
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return true;
    }

    public boolean updateApplication(Connection conn, ApplicationVO applicationVO) throws Exception {
        PreparedStatement pstm = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION);

            pstm.setString(1, applicationVO.getReferenceNo());
            pstm.setString(2, applicationVO.getPreviousReferenceNo());
            pstm.setLong(3, applicationVO.getNationalityId());
            pstm.setString(4, applicationVO.getNationality());
            pstm.setInt(5, applicationVO.getCitizenOfSriLanka());
            pstm.setDate(6, CommonUtil.getSqlDateFromUtilDate(applicationVO.getCitizenshipObtainedDate()));
            pstm.setTimestamp(7, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getDateOfBirth()));
            pstm.setInt(8, applicationVO.getAge());
            pstm.setString(9, applicationVO.getNic());
            pstm.setString(10, applicationVO.getPassport());
            pstm.setLong(11, applicationVO.getHighCommisionReferenceId());
            pstm.setString(12, applicationVO.getHighCommisionReference());
            pstm.setString(13, applicationVO.getApplicantNameAsNic());
            pstm.setString(14, applicationVO.getApplicantNameAsPassport());
            pstm.setDate(15, CommonUtil.getSqlDateFromUtilDate(applicationVO.getPassportIssueDate()));
            pstm.setString(16, applicationVO.getPresentAddressLocal());
            pstm.setString(17, applicationVO.getPresentAddressOverseas());
            pstm.setString(18, applicationVO.getSex());
            pstm.setString(19, applicationVO.getApplicantStatus());
            pstm.setString(20, applicationVO.getOccupation());
            pstm.setString(21, applicationVO.getPurpose());
            pstm.setInt(22, applicationVO.getPreviousCertificateApply());
            pstm.setLong(23, applicationVO.getPreviousCertificateCountryId());
            pstm.setString(24, applicationVO.getPreviousCertificateCountryName());
            pstm.setInt(25, applicationVO.getPreviousCertificateIssueStatus());
            pstm.setString(26, applicationVO.getPreviousCertificateReferenceNo());
            pstm.setTimestamp(27, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getPreviousCertificateIssueDate()));
            pstm.setString(28, applicationVO.getAuthorizedHandoverPerson());
            pstm.setString(29, applicationVO.getAuthorizedHandoverPersonName());
            pstm.setString(30, applicationVO.getAuthorizedHandoverPersonNicPassport());
            pstm.setString(31, applicationVO.getHighCommisionReferenceAddress());
            pstm.setString(32, applicationVO.getCertificatePostAddressLineOne());
            pstm.setString(33, applicationVO.getCertificatePostAddressLineTwo());
            pstm.setString(34, applicationVO.getCertificatePostAddressCity());
            pstm.setString(35, applicationVO.getCertificatePostAddressState());
            pstm.setString(36, applicationVO.getCertificatePostAddressPostal());
            pstm.setLong(37, applicationVO.getCertificatePostAddressCountry());
            pstm.setString(38, applicationVO.getCertificatePostAddressCountryName());
            pstm.setLong(39, applicationVO.getMobileCountryCodeId());
            pstm.setString(40, applicationVO.getMobileCountryCode());
            pstm.setString(41, applicationVO.getMobileNo());
            pstm.setString(42, applicationVO.getEmail());
            pstm.setString(43, applicationVO.getSpouseFullName());
            pstm.setString(44, applicationVO.getSpouseAddress());
            pstm.setString(45, applicationVO.getSpouseNationality());
            pstm.setString(46, applicationVO.getSpousePassport());
            pstm.setString(47, applicationVO.getSpouseNic());
            pstm.setString(48, applicationVO.getDeliveryType());
            pstm.setString(49, applicationVO.getForiegnMinistryInvertNo());
            pstm.setString(50, applicationVO.getPassportAttachPath());
            pstm.setString(51, applicationVO.getPassportBackAttachPath());
            pstm.setString(52, applicationVO.getNicAttachPath());
            pstm.setString(53, applicationVO.getNicBackAttachPath());
            pstm.setString(54, applicationVO.getBirthCertificatePath());
            pstm.setString(55, applicationVO.getBirthCertificateBackPath());
            pstm.setInt(56, applicationVO.getReferredThroughBereau());
            pstm.setString(57, applicationVO.getLetterOfReferencePath());
            pstm.setString(58, applicationVO.getApplicationClearanceStatus());
            pstm.setString(59, applicationVO.getApplicationReviewStatus());
            pstm.setInt(60, applicationVO.getUpdatedUserId());
            pstm.setString(61, applicationVO.getUpdatedUserName());
            pstm.setTimestamp(62, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getUpdatedDateTime()));
            pstm.setString(63, applicationVO.getPolStatus());
            pstm.setString(64, applicationVO.getCidStatus());
            pstm.setString(65, applicationVO.getTidStatus());
            pstm.setString(66, applicationVO.getSisStatus());
            pstm.setString(67, applicationVO.getNicStatus());
            pstm.setString(68, applicationVO.getImiStatus());
            pstm.setInt(69, applicationVO.getPhqRecordLockStatus());
            pstm.setInt(70, applicationVO.getCidRecordLockStatus());
            pstm.setInt(71, applicationVO.getTidRecordLockStatus());
            pstm.setInt(72, applicationVO.getSisRecordLockStatus());
            pstm.setInt(73, applicationVO.getNicRecordLockStatus());
            pstm.setInt(74, applicationVO.getImiRecordLockStatus());
            pstm.setString(75, applicationVO.getCoApproved());
            pstm.setString(76, applicationVO.getOicApproved());
            pstm.setString(77, applicationVO.getAspApproved());
            pstm.setString(78, applicationVO.getDhaApproved());
            pstm.setString(79, applicationVO.getCertificateSerialNo());
            pstm.setString(80, applicationVO.getApplicationQueue());
            pstm.setString(81, applicationVO.getRegPostNo());
            pstm.setString(82, applicationVO.getIpAddress());
            pstm.setString(83, applicationVO.getUserFullName());
            pstm.setString(84, applicationVO.getUserEmail());
            pstm.setString(85, applicationVO.getAuthProvider());
            pstm.setInt(86, applicationVO.getCertificatePrintedStatus());
            pstm.setString(87, applicationVO.getApplicationType());
            pstm.setLong(88, applicationVO.getCertificateAuthPersonId());
            pstm.setLong(89, applicationVO.getTransactionId());

            pstm.setLong(90, applicationVO.getTransactionId());

            pstm.setString(91, applicationVO.getSelectedNameOption());
            pstm.setString(92, applicationVO.getNewNicAttachPath());
            pstm.setString(93, applicationVO.getNewNicBackAttachPath());
            pstm.setString(94, applicationVO.getNewNic());
            pstm.setString(95, applicationVO.getAffidavitAttachPath());

            pstm.executeUpdate();
			
            pstm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.close(pstm);
        }
    }

    public boolean updateCompletedApplication(Connection conn, ApplicationVO applicationVO) throws Exception {
        PreparedStatement pstm = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_COMPLETED_APPLICATION);

            pstm.setString(1, applicationVO.getReferenceNo());
            pstm.setString(2, applicationVO.getApplicationReviewStatus());
            pstm.setLong(3, applicationVO.getTransactionId());
            pstm.setLong(4, applicationVO.getApplicationId());

            pstm.executeUpdate();
            pstm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.close(pstm);
        }
    }

    // public boolean addApplicationAudit(Connection conn, ApplicationVO applicationVO) throws
    // Exception {
    // PreparedStatement pstm = null;
    // try {
    // //get the application record from reference no
    // pstm=conn.prepareStatement(DBConstant.QUERY.ADD_APPLICATION_AUDIT);
    // pstm.setLong(1 ,applicationVO.getApplicationId());
    // pstm.setString(2 ,applicationVO.getReferenceNo());
    // pstm.setString(3 ,applicationVO.getPreviousReferenceNo());
    // pstm.setString(4 ,applicationVO.getNic());
    // pstm.setString(5 ,applicationVO.getPassport());
    // pstm.setLong(6 ,applicationVO.getCountryId());
    // pstm.setString(7 ,applicationVO.getCountryName());
    // pstm.setString(8 ,applicationVO.getApplicantName());
    // pstm.setString(9 ,applicationVO.getHighCommisionReference());
    // pstm.setString(10 ,applicationVO.getNationality());
    // pstm.setTimestamp(11 ,CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getDateOfBirth()));
    // pstm.setInt(12 ,applicationVO.getAge());
    // pstm.setString(13 ,applicationVO.getSex());
    // pstm.setString(14 ,applicationVO.getOccupation());
    // pstm.setString(15 ,applicationVO.getPurpose());
    // pstm.setString(16 ,applicationVO.getApplicantStatus());
    // pstm.setInt(17 ,applicationVO.getPreviousCertificateApply());
    // pstm.setLong(18 ,applicationVO.getPreviousCertificateCountryId());
    // pstm.setString(19 ,applicationVO.getPreviousCertificateCountryName());
    // pstm.setInt(20 ,applicationVO.getPreviousCertificateIssueStatus());
    // pstm.setString(21 ,applicationVO.getPreviousCertificateReferenceNo());
    // pstm.setTimestamp(22
    // ,CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getPreviousCertificateIssueDate()));
    // pstm.setString(23 ,applicationVO.getPresentAddressLocal());
    // pstm.setString(24 ,applicationVO.getPresentAddressOverseas());
    // pstm.setString(25 ,applicationVO.getAuthorizedHandoverPerson());
    // pstm.setString(26 ,applicationVO.getAuthorizedHandoverPersonName());
    // pstm.setString(27 ,applicationVO.getAuthorizedHandoverPersonNic());
    // pstm.setString(28 ,applicationVO.getAuthorizedHandoverPersonPassport());
    // pstm.setString(29 ,applicationVO.getCertificateIndicateAddress());
    // pstm.setString(30 ,applicationVO.getCertificatePostAddressLineOne());
    // pstm.setString(31 ,applicationVO.getCertificatePostAddressLineTwo());
    // pstm.setString(32 ,applicationVO.getCertificatePostAddressCity());
    // pstm.setString(33 ,applicationVO.getCertificatePostAddressState());
    // pstm.setString(34 ,applicationVO.getCertificatePostAddressPostal());
    // pstm.setLong(35 ,applicationVO.getCertificatePostAddressCountry());
    // pstm.setString(36 ,applicationVO.getCertificatePostAddressCountryName());
    // pstm.setString(37 ,applicationVO.getMobileNo());
    // pstm.setString(38 ,applicationVO.getEmail());
    // pstm.setString(39 ,applicationVO.getPassportAttachPath());
    // pstm.setString(40 ,applicationVO.getNicAttachPath());
    // pstm.setString(41 ,applicationVO.getSpouseFullName());
    // pstm.setString(42 ,applicationVO.getSpouseNationality());
    // pstm.setString(43 ,applicationVO.getSpousePassport());
    // pstm.setString(44 ,applicationVO.getSpouseNic());
    // pstm.setString(45 ,applicationVO.getBookRecieptNo());
    // pstm.setString(46 ,applicationVO.getApplicationClearanceStatus());
    // pstm.setString(47 ,applicationVO.getApplicationReviewStatus());
    // pstm.setInt(48 ,applicationVO.getUpdatedUserId());
    // pstm.setString(49 ,applicationVO.getUpdatedUserName());
    // pstm.setTimestamp(50
    // ,CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getUpdatedDateTime()));
    // pstm.setString(51 ,applicationVO.getPolStatus());
    // pstm.setString(52 ,applicationVO.getCidStatus());
    // pstm.setString(53 ,applicationVO.getTidStatus());
    // pstm.setString(54 ,applicationVO.getSisStatus());
    // pstm.setString(55 ,applicationVO.getNicStatus());
    // pstm.setString(56 ,applicationVO.getImiStatus());
    // pstm.setInt(57 ,applicationVO.getPhqRecordLockStatus());
    // pstm.setInt(58 ,applicationVO.getCidRecordLockStatus());
    // pstm.setInt(59 ,applicationVO.getTidRecordLockStatus());
    // pstm.setInt(60 ,applicationVO.getSisRecordLockStatus());
    // pstm.setInt(61 ,applicationVO.getNicRecordLockStatus());
    // pstm.setInt(62 ,applicationVO.getImiRecordLockStatus());
    // pstm.setInt(63 ,applicationVO.getCoApproved());
    // pstm.setInt(64 ,applicationVO.getOicApproved());
    // pstm.setInt(65 ,applicationVO.getAspApproved());
    // pstm.setInt(66 ,applicationVO.getDhaApproved());
    // pstm.setString(67 ,applicationVO.getCertificateSerialNo());
    // pstm.setString(68 ,applicationVO.getApplicationQueue());
    // pstm.setString(69 ,applicationVO.getRegPostNo());
    // pstm.setString(70 ,applicationVO.getIpAddress());
    //
    // pstm.executeQuery();
    // return true;
    // } catch (Exception e) {
    // e.printStackTrace();
    // return false;
    // }finally{
    // DatabaseManager.close(pstm);
    // }
    //
    // }

    public String moveApplicationToAudit(long applicationId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_APPLICATION_TO_AUDIT);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_APPLICATION_TO_AUDIT);
            pstm.setLong(1, applicationId);
            int result = pstm.executeUpdate();
            // LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
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
        String newNic = rst.getString(DBConstant.APPLICATION_COL.NEW_NIC);
        applicationVO.setNewNic(newNic);
        applicationVO.setPassport(rst.getString(DBConstant.APPLICATION_COL.PASSPORT));
        applicationVO.setCountryId(rst.getLong(DBConstant.APPLICATION_COL.COUNTRY_ID));
        applicationVO.setCountryName(rst.getString(DBConstant.APPLICATION_COL.COUNTRY_NAME));
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
        applicationVO.setPreviousCertificateCountryId(rst
                .getLong(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID));
        applicationVO.setPreviousCertificateCountryName(rst
                .getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME));
        applicationVO.setPreviousCertificateIssueStatus(rst
                .getInt(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS));
        applicationVO.setPreviousCertificateReferenceNo(rst
                .getString(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO));
        applicationVO.setPreviousCertificateIssueDate(rst
                .getTimestamp(DBConstant.APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE));
        applicationVO.setAuthorizedHandoverPerson(rst.getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON));
        applicationVO.setAuthorizedHandoverPersonName(rst
                .getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME));
        applicationVO.setAuthorizedHandoverPersonNicPassport(rst
                .getString(DBConstant.APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT));
        applicationVO.setHighCommisionReferenceAddress(rst
                .getString(DBConstant.APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS));
        applicationVO.setCertificatePostAddressLineOne(rst
                .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1));
        applicationVO.setCertificatePostAddressLineTwo(rst
                .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2));
        applicationVO
                .setCertificatePostAddressCity(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY));
        applicationVO.setCertificatePostAddressState(rst
                .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE));
        applicationVO.setCertificatePostAddressPostal(rst
                .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL));
        applicationVO.setCertificatePostAddressCountry(rst
                .getLong(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY));
        applicationVO.setCertificatePostAddressCountryName(rst
                .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME));
        applicationVO.setMobileCountryCodeId(rst.getLong(DBConstant.APPLICATION_COL.MOBILE_COUNTRY_CODE_ID));
        applicationVO.setMobileCountryCode(rst.getString(DBConstant.APPLICATION_COL.MOBILE_COUNTRY_CODE));
        applicationVO.setMobileNo(rst.getString(DBConstant.APPLICATION_COL.MOBILE_NO));
        applicationVO.setEmail(rst.getString(DBConstant.APPLICATION_COL.EMAIL));
        applicationVO.setSpouseFullName(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_FULL_NAME));
        applicationVO.setSpouseAddress(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_ADDRESS));
        applicationVO.setSpouseNationality(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NATIONALITY));
        applicationVO.setSpousePassport(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_PASSPORT));
        applicationVO.setSpouseNic(rst.getString(DBConstant.APPLICATION_COL.SPOUSE_NIC));
        applicationVO.setDeliveryType(rst.getString(DBConstant.APPLICATION_COL.DELIVERY_TYPE));
        applicationVO.setForiegnMinistryInvertNo(rst.getString(DBConstant.APPLICATION_COL.FORIEGN_MINISTRY_INVERT_NO));
        applicationVO.setPassportAttachPath(rst.getString(DBConstant.APPLICATION_COL.PASSPORT_ATTACH_PATH));
        applicationVO.setPassportBackAttachPath(rst.getString(DBConstant.APPLICATION_COL.PASSPORT_BACK_ATTACH_PATH));
        applicationVO.setNicAttachPath(rst.getString(DBConstant.APPLICATION_COL.NIC_ATTACH_PATH));
        applicationVO.setNicBackAttachPath(rst.getString(DBConstant.APPLICATION_COL.NIC_BACK_ATTACH_PATH));
        applicationVO.setBirthCertificatePath(rst.getString(DBConstant.APPLICATION_COL.BIRTH_CERTIFICATE_PATH));
        applicationVO.setBirthCertificateBackPath(rst.getString(DBConstant.APPLICATION_COL.BIRTH_CERTIFICATE_BACK_PATH));
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
        applicationVO.setDigApproved(rst.getString(DBConstant.APPLICATION_COL.DIG_APPROVED));
        applicationVO.setCertificateSerialNo(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_SERIAL_NO));
        applicationVO.setApplicationQueue(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_QUEUE));
        applicationVO.setRegPostNo(rst.getString(DBConstant.APPLICATION_COL.REG_POST_NO));
        applicationVO.setCertificatePostedDate(rst.getTimestamp(DBConstant.APPLICATION_COL.CERTIFICATE_POSTED_DATE));
        applicationVO.setIpAddress(rst.getString(DBConstant.APPLICATION_COL.IP_ADDRESS));
        applicationVO.setUserFullName(rst.getString(DBConstant.APPLICATION_COL.USER_FULL_NAME));
        applicationVO.setUserEmail(rst.getString(DBConstant.APPLICATION_COL.USER_EMAIL));
        applicationVO.setAuthProvider(rst.getString(DBConstant.APPLICATION_COL.AUTH_PROVIDER));
        applicationVO.setCertificatePrintedStatus(rst.getInt(DBConstant.APPLICATION_COL.CERTIFICATE_PRINTED_STATUS));
        applicationVO.setApplicationType(rst.getString(DBConstant.APPLICATION_COL.APPLICATION_TYPE));
        applicationVO.setCertificateAuthPersonId(rst.getLong(DBConstant.APPLICATION_COL.CERTIFICATE_AUTH_PERSON_ID));
        applicationVO.setTransactionId(rst.getLong(DBConstant.APPLICATION_COL.TRANSACTION_ID));
        applicationVO.setAddressPrintedStatus(rst.getInt(DBConstant.APPLICATION_COL.ADDRESS_PRINTED_STATUS));
        applicationVO.setNotificationEmailSentStatus(rst.getInt(DBConstant.APPLICATION_COL.NOTIFICATION_EMAIL_SENT_STATUS));
        applicationVO.setVersionId(rst.getInt(DBConstant.APPLICATION_COL.VERSION_ID));
        applicationVO.setCertificateType(rst.getString(DBConstant.APPLICATION_COL.CERTIFICATE_TYPE));
        applicationVO.setSubmittedDate(rst.getTimestamp(DBConstant.APPLICATION_COL.SUBMITTED_DATE));
        applicationVO.setRecommendedOfficerName(rst.getString(DBConstant.APPLICATION_COL.RECOMMENDED_OFFICER_NAME));
        applicationVO.setCertificateIssueDate(rst.getTimestamp(DBConstant.APPLICATION_COL.CERTIFICATE_ISSUE_DATE));

        applicationVO.setNewNicAttachPath(rst.getString(DBConstant.APPLICATION_COL.NEW_NIC_ATTACH_PATH));
        applicationVO.setNewNicBackAttachPath(rst.getString(DBConstant.APPLICATION_COL.NEW_NIC_BACK_ATTACH_PATH));
        applicationVO.setAspReviewStatus(rst.getString(DBConstant.APPLICATION_COL.ASP_REVIEW_STATUS));

        String currentNIC = "";

        if (newNic != null && !newNic.equals("")) {
            currentNIC = newNic;
        } else if ((newNic == null || newNic.equals("")) && nic != null && !nic.equals("")) {
            currentNIC = nic;
        } else {
            currentNIC = "N/A";
        }

        applicationVO.setCurrentNicNo(currentNIC);

        applicationVO.setAffidavitAttachPath(rst.getString(DBConstant.APPLICATION_COL.AFFIDAVIT_ATTACH_PATH));

        String selectedNameOption = rst.getString(DBConstant.APPLICATION_COL.SELECTED_NAME_OPTION);

        if (selectedNameOption == null || selectedNameOption.isEmpty()) {
            applicationVO.setSelectedNameOption("nic");
        } else {
            applicationVO.setSelectedNameOption(selectedNameOption);
        }

//        String dhaStatus = rst.getString(DBConstant.APPLICATION_COL.DHA_REVIEW_STATUS);
//
//        if ("NULL".equals(dhaStatus)) {
//            applicationVO.setDhaReviewStatus(PoliceEnumConstant.DHAReviewStatus.NOT_APPLICABLE.getStatus());
//        } else {
//            applicationVO.setDhaReviewStatus(dhaStatus);
//        }
        
        String aspStatus = rst.getString(DBConstant.APPLICATION_COL.ASP_REVIEW_STATUS);

        if (("NULL".equals(aspStatus)) || ("null".equals(aspStatus))) {
            applicationVO.setAspReviewStatus(PoliceEnumConstant.ASPReviewStatus.NOT_APPLICABLE.getStatus());
        } else {
            applicationVO.setAspReviewStatus(aspStatus);
        }
        return applicationVO;

    }

    public List<ApplicationVO> fetchApplicationVerificationList(
            ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SEARCH_APPLICATION_VERIFICATION);
            pstm = conn.prepareStatement(DBConstant.QUERY.SEARCH_APPLICATION_VERIFICATION);
            pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getFromDate()));
            pstm.setInt(2, applicationVerificationSearchCriteriaVO.getFromDateSqlInjection());
            pstm.setDate(3, CommonUtil.getSqlDateFromUtilDate(applicationVerificationSearchCriteriaVO.getToDate()));
            pstm.setInt(4, applicationVerificationSearchCriteriaVO.getToDateSqlInjection());
            pstm.setString(5, applicationVerificationSearchCriteriaVO.getStatus());
            pstm.setInt(6, applicationVerificationSearchCriteriaVO.getStatusSqlInjection());
            pstm.setString(7, applicationVerificationSearchCriteriaVO.getReferenceNo());
            pstm.setInt(8, applicationVerificationSearchCriteriaVO.getReferenceNoSqlInjection());

            pstm.setString(9, applicationVerificationSearchCriteriaVO.getNic() + "%");
            pstm.setInt(10, applicationVerificationSearchCriteriaVO.getNicSqlInjection());

            pstm.setString(11, applicationVerificationSearchCriteriaVO.getPassport() + "%");
            pstm.setInt(12, applicationVerificationSearchCriteriaVO.getPassportSqlInjection());

            pstm.setString(13, "%" + applicationVerificationSearchCriteriaVO.getName() + "%");
            pstm.setString(14, "%" + applicationVerificationSearchCriteriaVO.getName() + "%");
            pstm.setInt(15, applicationVerificationSearchCriteriaVO.getNameSqlInjection());

            pstm.setString(16, PoliceEnumConstant.ApplicationReviewStatus.TS.toString());
            pstm.setString(17, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            pstm.setString(18, applicationVerificationSearchCriteriaVO.getNewNic() + "%");
            pstm.setInt(19, applicationVerificationSearchCriteriaVO.getNewNicSqlInjection());

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

    public String lockPHQRecord(long lockedApplicationId, int lockedUserId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {

            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);
            pstm.setInt(1, lockedUserId);
            pstm.setLong(2, lockedApplicationId);
            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public String unlockPHQRecord(long lockedApplicationId, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_PHQ_RECORD_LOCK_STATUS);
            pstm.setInt(1, 0);
            pstm.setLong(2, lockedApplicationId);
            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public String updateReviewStatus(ApplicationVO applicationVO, String reviewStatus, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            long applicationId = applicationVO.getApplicationId();
            moveApplicationToAudit(applicationId, con);
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_REVIEW_STATUS);
            if (reviewStatus.equals(PoliceEnumConstant.ApplicationReviewStatus.RJ.toString())) {
                pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_REVIEW_STATUS_AND_CLEARANCE_STATUS_ON_REJECT);
                pstm.setString(1, reviewStatus);
                pstm.setString(2, PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString());
                pstm.setLong(3, applicationId);
            } else {
                pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_REVIEW_STATUS);
                pstm.setString(1, reviewStatus);
                pstm.setLong(2, applicationId);

                if (reviewStatus.equals(PoliceEnumConstant.ApplicationReviewStatus.VF.toString())) {

                    updateVerifiedStatus(applicationVO, con);

                    AddressDAO.getInstance().updateAddressPoliceStatusByApplicationId(applicationId,
                            PoliceEnumConstant.ApprovalStatus.PN.toString(), con);
                }
            }

            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public List<CountryVO> getCountryList(Connection conn) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CountryVO> countryVOs = new ArrayList<CountryVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COUNTRY_LIST);
            rst = pstm.executeQuery();

            while (rst.next()) {
                CountryVO countryVO = new CountryVO();
                countryVO.setId(rst.getLong(DBConstant.COUNTRY_COL.ID));
                countryVO.setCountryCode(rst.getString(DBConstant.COUNTRY_COL.COUNTRY_CODE));
                countryVO.setCountryName(rst.getString(DBConstant.COUNTRY_COL.COUNTRY_NAME));
                countryVO.setNationality(rst.getString(DBConstant.COUNTRY_COL.NATIONALITY));
                countryVO.setMobileCountryCode(rst.getString(DBConstant.COUNTRY_COL.MOBILE_COUNTRY_CODE));

                countryVOs.add(countryVO);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return countryVOs;
    }

    public CountryVO getCountryVO(Connection conn, long countryId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CountryVO countryVO = new CountryVO();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COUNTRY_BY_ID);
            pstm.setLong(1, countryId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                countryVO.setId(rst.getLong(DBConstant.COUNTRY_COL.ID));
                countryVO.setCountryCode(rst.getString(DBConstant.COUNTRY_COL.COUNTRY_CODE));
                countryVO.setCountryName(rst.getString(DBConstant.COUNTRY_COL.COUNTRY_NAME));
                countryVO.setNationality(rst.getString(DBConstant.COUNTRY_COL.NATIONALITY));
                countryVO.setMobileCountryCode(rst.getString(DBConstant.COUNTRY_COL.MOBILE_COUNTRY_CODE));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return countryVO;
    }

    public List<CommissionerVO> getCommissionerVOListByCountryId(Connection conn, long countryId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommissionerVO> commissionerVOList = new ArrayList<CommissionerVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_BY_COUNTRY_ID);
            pstm.setLong(1, countryId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                CommissionerVO commissionerVO = new CommissionerVO();
                commissionerVO.setId(rst.getLong(DBConstant.COUNTRY_COL.ID));
                commissionerVO.setCommissionEmbassyConsultantName(rst.getString(DBConstant.COMMISSIONER_COL.CEC_NAME));
                commissionerVO.setCommissionEmbassyConsultantAddress(rst.getString(DBConstant.COMMISSIONER_COL.CEC_ADDRESS));
                commissionerVO.setCommissionEmbassyConsultantType(rst.getString(DBConstant.COMMISSIONER_COL.CEC_TYPE));

                commissionerVOList.add(commissionerVO);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerVOList;
    }

    public CommissionerVO getCommissionerVO(Connection conn, long commissionerId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CommissionerVO commissionerVO = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_BY_ID);
            pstm.setLong(1, commissionerId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                commissionerVO = new CommissionerVO();
                commissionerVO.setId(rst.getLong(DBConstant.COMMISSIONER_COL.ID));
                commissionerVO.setCommissionEmbassyConsultantName(rst.getString(DBConstant.COMMISSIONER_COL.CEC_NAME));
                commissionerVO.setCommissionEmbassyConsultantAddress(rst.getString(DBConstant.COMMISSIONER_COL.CEC_ADDRESS));
                commissionerVO.setCommissionEmbassyConsultantType(rst.getString(DBConstant.COMMISSIONER_COL.CEC_TYPE));
                commissionerVO.setAddressee(rst.getString(DBConstant.COMMISSIONER_COL.ADDRESSEE));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerVO;
    }

    public ApplicationVO getApplicationById(long applicationId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        try {
            // get the application record from reference no
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATOIN_BY_ID);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                applicationVO = getConstructedApplicationFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVO;
    }

    public List<PoliceAreaVO> getPoliceAreaList(Connection conn) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<PoliceAreaVO> policeAreaVOs = new ArrayList<PoliceAreaVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_POLICE_AREA_LIST);
            rst = pstm.executeQuery();

            while (rst.next()) {
                PoliceAreaVO policeAreaVO = new PoliceAreaVO();
                policeAreaVO.setId(rst.getLong(DBConstant.POLICE_AREA_COL.ID));
                policeAreaVO.setPoliceArea(rst.getString(DBConstant.POLICE_AREA_COL.POLICE_AREA));

                policeAreaVOs.add(policeAreaVO);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return policeAreaVOs;
    }

    public int checkPHQLockStatus(long applicationId, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        int phqLockId = -1;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_PHQ_LOCK_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_PHQ_LOCK_STATUS);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();

            if (!rst.next()) {
                LOGGER.info("No records found");
            } else {
                phqLockId = rst.getInt(DBConstant.APPLICATION_COL.PHQ_RECORD_LOCK_STATUS);
                // if(phqLockId != 0) {
                // LOGGER.info("1 PHQ LOCK ID --> " + phqLockId);
                // returnValue=PoliceConstant.ERROR;
                // } else {
                // LOGGER.info("2 PHQ LOCK ID --> " + phqLockId);
                // returnValue=PoliceConstant.SUCCESS;
                // }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            // returnValue=PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return phqLockId;
    }

    public String updateVerifiedStatus(ApplicationVO applicationVO, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_VERIFIED_STATUS);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_VERIFIED_STATUS);
            pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(2, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());

            if (StringUtils.isEmpty(applicationVO.getNic()) && StringUtils.isEmpty(applicationVO.getNewNic())) {
                pstm.setString(5, PoliceEnumConstant.ApprovalStatus.AP.toString());
            } else {
                pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
            }

            pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(8, PoliceEnumConstant.ApplicationQueue.CN.toString());
            pstm.setLong(9, applicationVO.getApplicationId());

            int result = pstm.executeUpdate();
            // LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public int countPHQLocs(Connection conn, int userId) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int userIdCount = 0;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_PHQ_USER_COUNT);
            pstm.setInt(1, userId);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    userIdCount = rst.getInt("USER_ID_COUNT");
                    LOGGER.info("USER_ID_COUNT WHILE --> " + userIdCount);
                }
            }
            LOGGER.info("USER_ID_COUNT --> " + userIdCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return userIdCount;
    }

    public String addChangeAudit(ChangeAuditVO changeAuditVO, Connection con) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_CHANGE_AUDIT);
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_CHANGE_AUDIT);
            pstm.setLong(1, changeAuditVO.getApplicationId());
            pstm.setInt(2, changeAuditVO.getUpdatedUserId());
            pstm.setString(3, changeAuditVO.getUpdatedUserName());
            pstm.setString(4, changeAuditVO.getComment());

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;

    }

    public ApplicationVO getApplicationByReferenceNoAndEmail(String referenceNo, String userEmail, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_APPLICATION_BY_REFERENCE_NO_AND_EMAIL);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_BY_REFERENCE_NO_AND_EMAIL);
            pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.RC.toString());
            pstm.setString(2, referenceNo);
            pstm.setString(3, userEmail);
            rst = pstm.executeQuery();

            while (rst.next()) {
                applicationVO = getConstructedApplicationFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVO;
    }

    public String getUserEmailByReferenceNo(String referenceNo, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String userEmail = "";
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_USER_EMAIL_BY_REFERENCE_NO);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_USER_EMAIL_BY_REFERENCE_NO);
            pstm.setString(1, referenceNo);
            rst = pstm.executeQuery();

            if (rst != null) {
                while (rst.next()) {
                    userEmail = rst.getString(DBConstant.APPLICATION_COL.USER_EMAIL);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return userEmail;
    }


    /**
     * @param from
     * @param to
     * @param con
     * @return
     */
    public List<ApplicationVO> loadApplicationToClearanceReport(Date from, Date to, Connection con) {
        // LOGGER.info("ApplicationDAO :: loadApplicationToClearanceReport() :: Method called.");
        List<ApplicationVO> applicationVOList = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATIO_FOR_CEARANCE_REPORT);
            pstm.setDate(1, CommonUtil.getSqlDateFromUtilDate(from));
            pstm.setDate(2, CommonUtil.getSqlDateFromUtilDate(to));
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationVO = new ApplicationVO();
                    applicationVO.setReferenceNo(rst.getString(DBConstant.APPLICATION_COL.REFERENCE_NO));

                    String name = rst.getString(DBConstant.APPLICATION_COL.APPLICANT_NAME_AS_NIC);

                    if (name == null || name.isEmpty()) {
                        name = rst.getString(DBConstant.APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT);
                    }
                    applicationVO.setApplicantNameAsNic(name);

                    String nic = rst.getString(DBConstant.APPLICATION_COL.NIC);
                    applicationVO.setNic(nic);
                    String newNic = rst.getString(DBConstant.APPLICATION_COL.NEW_NIC);
                    applicationVO.setNewNic(newNic);
                    applicationVO.setPassport(rst.getString(DBConstant.APPLICATION_COL.PASSPORT));
                    applicationVO.setCertificatePostAddressLineOne(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1));
                    applicationVO.setCertificatePostAddressLineTwo(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2));
                    applicationVO.setCertificatePostAddressCity(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY));
                    applicationVO.setCertificatePostAddressState(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE));
                    applicationVO.setCertificatePostAddressCountryName(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME));
                    applicationVO.setCertificatePostAddressPostal(rst
                            .getString(DBConstant.APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL));
                    applicationVO.setDateOfBirth(rst.getDate(DBConstant.APPLICATION_COL.DATE_OF_BIRTH));
                    applicationVO.setPresentAddressLocal(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_LOCAL));
                    applicationVO.setPresentAddressOverseas(rst.getString(DBConstant.APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS));

                    String currentNIC = "";
                    if (newNic != null && !newNic.isEmpty()) {
                        currentNIC = newNic;
                    } else if ((newNic == null || newNic.isEmpty()) && nic != null && !nic.isEmpty()) {
                        currentNIC = nic;
                    } else {
                        currentNIC = "N/A";
                    }

                    applicationVO.setCurrentNicNo(currentNIC);
                    applicationVOList.add(applicationVO);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE loadApplicationToClearanceReport Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOList;
    }

    public String applicationUpdateForRequest(ApplicationVO applicationVO, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_APPLICATION_REQUEST_FOR_UPDATE);
            moveApplicationToAudit(applicationVO.getApplicationId(), con);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_REQUEST_FOR_UPDATE);
            pstm.setString(1, applicationVO.getNicAttachPath());
            pstm.setString(2, applicationVO.getNicBackAttachPath());
            pstm.setString(3, applicationVO.getPassportAttachPath());
            pstm.setString(4, applicationVO.getPassportBackAttachPath());
            pstm.setString(5, applicationVO.getApplicantNameAsNic());
            pstm.setDate(6, CommonUtil.getSqlDateFromUtilDate(applicationVO.getDateOfBirth()));
            pstm.setLong(7, applicationVO.getApplicationId());

            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public List<ApplicationVO> loadPreviousAdverseRecords(String nic, String passport, long applicationId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        // System.out.println("applicationId :" + applicationId);
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        try {
            // get the application record from reference no
            LOGGER.info("loadPreviousAdverseRecords :" + DBConstant.QUERY.SELECT_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT);
            pstm.setString(1, nic);
            pstm.setString(2, StringUtils.EMPTY);
            pstm.setString(3, passport);
            pstm.setString(4, StringUtils.EMPTY);

            pstm.setString(5, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(6, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(8, PoliceEnumConstant.ApprovalStatus.RJ.toString());

            pstm.setString(9, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(10, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(11, PoliceEnumConstant.ApprovalStatus.OI.toString());

            pstm.setString(12, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(13, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(14, PoliceEnumConstant.ApprovalStatus.OI.toString());

            pstm.setLong(15, applicationId);

            rst = pstm.executeQuery();
            while (rst.next()) {
                applicationVOs.add(getConstructedApplicationFromResultSet(rst));
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

    public long getCountOfPreviousAdverseRecords(String nic, String newNic, String passport, long applicationId,
                                                 Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long count = 0;
        try {
            // get the application record from reference no
            // LOGGER.info("loadPreviousAdverseRecords :" +
            // DBConstant.QUERY.COUNT_ALL_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT);
            pstm = conn.prepareStatement(DBConstant.QUERY.COUNT_ALL_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT);
            pstm.setString(1, nic);
            pstm.setString(2, StringUtils.EMPTY);
            pstm.setString(3, newNic);
            pstm.setString(4, StringUtils.EMPTY);
            pstm.setString(5, passport);
            pstm.setString(6, StringUtils.EMPTY);

            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(8, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(9, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(10, PoliceEnumConstant.ApprovalStatus.RJ.toString());

            pstm.setString(11, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(12, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(13, PoliceEnumConstant.ApprovalStatus.OI.toString());

            pstm.setString(14, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(15, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(16, PoliceEnumConstant.ApprovalStatus.OI.toString());

            pstm.setLong(17, applicationId);

            if (!(pstm == null)) {
                rst = pstm.executeQuery();
                if (!rst.next()) {
                    // System.out.println("No records found");
                    LOGGER.info("No records found");
                } else {
                    count = rst.getLong("COUNT");
                    // System.out.println("count ddd:" + count);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return count;
    }

    public List<ChangeAuditVO> loadChangeAuditByReferenceNo(long applicationId, Connection con) {
        // LOGGER.info("ApplicationDAO :: loadChangeAuditByReferenceNo() :: Method called.");
        // LOGGER.info("CHANGE AUDIT REFERENCE NO -> " + referenceNo);
        List<ChangeAuditVO> changeAuditVOList = new ArrayList<ChangeAuditVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ChangeAuditVO changeAuditVO = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_CHANGE_AUDIT_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    changeAuditVO = new ChangeAuditVO();
                    changeAuditVO.setUpdatedUserDateTime(rst.getTimestamp(DBConstant.CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME));
                    changeAuditVO.setComment(rst.getString(DBConstant.CHANGE_AUDIT_COL.COMMENT));
                    changeAuditVO.setUpdatedUserName(rst.getString(DBConstant.CHANGE_AUDIT_COL.UPDATED_USER_NAME));
                    changeAuditVOList.add(changeAuditVO);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE loadChangeAuditByReferenceNo Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return changeAuditVOList;
    }

    public long saveCommissioner(CommissionerVO commissionerVO, Connection conn) {
        PreparedStatement pstm = null;
        long commissionerId = 0L;

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.ADD_COMMISSIONER);
            pstm = conn.prepareStatement(DBConstant.QUERY.ADD_COMMISSIONER, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, commissionerVO.getCommissionEmbassyConsultantName());
            pstm.setString(2, commissionerVO.getCommissionEmbassyConsultantType());
            pstm.setString(3, commissionerVO.getCommissionEmbassyConsultantAddress());
            pstm.setInt(4, commissionerVO.getActiveStatus());
            pstm.setLong(5, commissionerVO.getCountryId());
            pstm.setString(6, commissionerVO.getAddressee());

            int result = pstm.executeUpdate();
            if (result > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    commissionerId = rs.getInt(1);
                }
            }

            commissionerVO.setId(commissionerId);
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return commissionerId;
    }

    public String updateCommissioner(CommissionerVO commissionerVO, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_COMMISSIONER);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_COMMISSIONER);
            pstm.setString(1, commissionerVO.getCommissionEmbassyConsultantName());
            pstm.setString(2, commissionerVO.getCommissionEmbassyConsultantType());
            pstm.setString(3, commissionerVO.getCommissionEmbassyConsultantAddress());
            pstm.setInt(4, commissionerVO.getActiveStatus());
            pstm.setLong(5, commissionerVO.getCountryId());
            pstm.setString(6, commissionerVO.getAddressee());
            pstm.setLong(7, commissionerVO.getId());

            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public int isExistsCommissioner(Connection conn, CommissionerVO commissionerVO) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int commissionerCount = 0;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_COMMISSIONER_COUNT);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_COUNT);
            pstm.setString(1, commissionerVO.getCommissionEmbassyConsultantName());
            pstm.setString(2, commissionerVO.getCommissionEmbassyConsultantType());
            pstm.setString(3, commissionerVO.getCommissionEmbassyConsultantAddress());
            pstm.setLong(4, commissionerVO.getCountryId());

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    commissionerCount = rst.getInt("COMMISSIONER_COUNT");
                    LOGGER.info("COMMISSIONER_COUNT WHILE --> " + commissionerCount);
                }
            }
            LOGGER.info("COMMISSIONER_COUNT --> " + commissionerCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerCount;
    }

    public List<CommissionerVO> getCommissionerListByCountry(long countryId, Connection con) {
        // LOGGER.info("ApplicationDAO :: getCommissionerListByCountry() :: Method called.");
        List<CommissionerVO> commissionerVOList = new ArrayList<CommissionerVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CommissionerVO commissionerVO = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_LIST_BY_COUNTRY_ID);
            pstm.setLong(1, countryId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    commissionerVO = new CommissionerVO();
                    commissionerVO.setId(rst.getLong(DBConstant.COMMISSIONER_COL.ID));
                    commissionerVO.setCommissionEmbassyConsultantName(rst.getString(DBConstant.COMMISSIONER_COL.CEC_NAME));
                    commissionerVO.setCommissionEmbassyConsultantType(rst.getString(DBConstant.COMMISSIONER_COL.CEC_TYPE));
                    commissionerVO.setCommissionEmbassyConsultantAddress(rst.getString(DBConstant.COMMISSIONER_COL.CEC_ADDRESS));
                    commissionerVO.setActiveStatus(rst.getInt(DBConstant.COMMISSIONER_COL.ACTIVE_STATUS));
                    commissionerVO.setCountryId(rst.getLong(DBConstant.COMMISSIONER_COL.COUNTRY_ID));
                    commissionerVO.setAddressee(rst.getString(DBConstant.COMMISSIONER_COL.ADDRESSEE));
                    commissionerVOList.add(commissionerVO);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getCommissionerListByCountry Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerVOList;
    }

    public boolean deleteCommissionerById(Connection conn, long id) throws Exception {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.DELETE_COMMISSIONER_BY_ID);
            pstm.setLong(1, id);

            pstm.executeUpdate();
            pstm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.close(pstm);
        }
    }

    public int isExistsCommissionerApplication(Connection conn, long commissionerId) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int commissionerCount = 0;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_COMMISSIONER_COUNT_IN_APPLICATION);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_COUNT_IN_APPLICATION);
            pstm.setLong(1, commissionerId);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    commissionerCount = rst.getInt("HC_COUNT");
                    LOGGER.info("HC_COUNT WHILE --> " + commissionerCount);
                }
            }
            LOGGER.info("HC_COUNT --> " + commissionerCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerCount;
    }

    public int isExistsApplication(Connection conn, String referenceNo) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int applicationCount = 0;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_APPLICATION_COUNT_BY_REFERENCE_NO);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_COUNT_BY_REFERENCE_NO);
            pstm.setString(1, referenceNo);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationCount = rst.getInt("APPLICATRION_COUNT");
                }
            }
            LOGGER.info("APPLICATRION_COUNT --> " + applicationCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationCount;
    }

    public List<ApplicationVO> getCertificateCheckingPendingApplicationListNoAdverse(Connection con) {
        List<ApplicationVO> applicationVOList = new ArrayList<ApplicationVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            // LOGGER.info("QUERY -> " +
            // DBConstant.QUERY.SELECT_CERTIFICATE_CHECKING_PENDING_ALL_NO_ADVERSE);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_CERTIFICATE_CHECKING_PENDING_ALL_NO_ADVERSE);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationVOList.add(getConstructedApplicationFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getCertificateCheckingPendingApplicationListNoAdverse Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationVOList;
    }

    public int isExistsertificateCheckingForApplicationNic(Connection conn, String nic) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int applicationCount = 0;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_APPLICATION_EXISTS_COUNT_NIC);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_EXISTS_COUNT_NIC);
            pstm.setString(1, nic);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationCount = rst.getInt("APPLICATION_EXISTS_COUNT");
                }
            }
            // LOGGER.info("APPLICATION_EXISTS_COUNT NIC --> " + applicationCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationCount;
    }

    public int isExistsertificateCheckingForApplicationPpt(Connection conn, String passport) {

        PreparedStatement pstm = null;
        ResultSet rst = null;
        int applicationCount = 0;
        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_APPLICATION_EXISTS_COUNT_PPT);
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_EXISTS_COUNT_PPT);
            pstm.setString(1, passport);

            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationCount = rst.getInt("APPLICATION_EXISTS_COUNT");
                }
            }
            // LOGGER.info("APPLICATION_EXISTS_COUNT PPT --> " + applicationCount);
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationCount;
    }

    public String updateCerificateSerialNumber(long applicationId, String certificateCerialNo, Connection con) {
        PreparedStatement pstm = null;
        String returnValue = PoliceConstant.SUCCESS;
        try {

            moveApplicationToAudit(applicationId, con);

            // LOGGER.info("QUERY -> " + DBConstant.QUERY.UPDATE_CERIFICATE_SERIAL_NUMBER);
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_CERIFICATE_SERIAL_NUMBER);
            pstm.setString(1, certificateCerialNo);
            pstm.setLong(2, applicationId);

            int result = pstm.executeUpdate();
            LOGGER.info("HAS UPDATE EXECUTED: " + result);
            if (result > 0) {
                returnValue = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            returnValue = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return returnValue;
    }

    public CommissionerVO getCommissionerById(Connection con, long highCommisionReferenceId) {
        // LOGGER.info("ApplicationDAO :: getCommissionerById() :: Method called.");
        CommissionerVO commissionerVO = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_COMMISSIONER_LIST_BY_ID);
            pstm.setLong(1, highCommisionReferenceId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    commissionerVO = new CommissionerVO();
                    commissionerVO.setId(rst.getLong(DBConstant.COMMISSIONER_COL.ID));
                    commissionerVO.setCommissionEmbassyConsultantName(rst.getString(DBConstant.COMMISSIONER_COL.CEC_NAME));
                    commissionerVO.setCommissionEmbassyConsultantType(rst.getString(DBConstant.COMMISSIONER_COL.CEC_TYPE));
                    commissionerVO.setCommissionEmbassyConsultantAddress(rst.getString(DBConstant.COMMISSIONER_COL.CEC_ADDRESS));
                    commissionerVO.setActiveStatus(rst.getInt(DBConstant.COMMISSIONER_COL.ACTIVE_STATUS));
                    commissionerVO.setAddressee(rst.getString(DBConstant.COMMISSIONER_COL.ADDRESSEE));
                    commissionerVO.setCountryId(rst.getLong(DBConstant.COMMISSIONER_COL.COUNTRY_ID));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getCommissionerListByCountry Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commissionerVO;
    }

    public String addApplicationClearanceDateDept(ApplicationClearanceDate applicationClearanceDate, Connection conn) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.INSERT_APPLIACTION_CLEARENCE_DATE);
            pstm = conn.prepareStatement(DBConstant.QUERY.INSERT_APPLIACTION_CLEARENCE_DATE);

            pstm.setLong(1, applicationClearanceDate.getApplicationId());
            pstm.setString(2, applicationClearanceDate.getDepartment());
            pstm.setInt(3, applicationClearanceDate.getPrintedStatus());
            pstm.setInt(4, applicationClearanceDate.getSentUserId());
            pstm.setString(5, applicationClearanceDate.getComment());
            pstm.setString(6, applicationClearanceDate.getType());

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
            pstm.close();

        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public String addApplicationClearanceDatePolice(ApplicationClearanceDate applicationClearanceDate, Connection conn) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.INSERT_APPLIACTION_CLEARENCE_DATE_POLICE);
            pstm = conn.prepareStatement(DBConstant.QUERY.INSERT_APPLIACTION_CLEARENCE_DATE_POLICE);

            pstm.setLong(1, applicationClearanceDate.getApplicationId());
            pstm.setLong(2, applicationClearanceDate.getAddressId());
            pstm.setString(3, applicationClearanceDate.getDepartment());
            pstm.setInt(4, applicationClearanceDate.getPrintedStatus());
            pstm.setInt(5, applicationClearanceDate.getSentUserId());
            pstm.setString(6, applicationClearanceDate.getComment());
            pstm.setString(7, applicationClearanceDate.getType());

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
            pstm.close();

        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public List<ApplicationClearanceDate> listAllApplicationClearanceDatesByApplicationId(long applicationId,
                                                                                          Connection con) {
        // LOGGER.info("ApplicationDAO :: listAllApplicationClearanceDateByApplicationId() :: Method called.");
        List<ApplicationClearanceDate> applicationClearanceDates = new ArrayList<ApplicationClearanceDate>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            // LOGGER.info("QUERY -> " +
            // DBConstant.QUERY.SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_APPLICATION_ID);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationClearanceDates.add(getConstructedApplicationClearanceDateFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE listAllApplicationClearanceDatesByApplicationId Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationClearanceDates;
    }

    public List<ApplicationClearanceDate> listAllApplicationClearanceDatesByAddressId(long addressId, Connection con) {
        // LOGGER.info("ApplicationDAO :: listAllApplicationClearanceDatesByAddressId() :: Method called.");
        List<ApplicationClearanceDate> applicationClearanceDates = new ArrayList<ApplicationClearanceDate>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            // LOGGER.info("QUERY -> " +
            // DBConstant.QUERY.SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_ADDRESS_ID);
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_ADDRESS_ID);
            pstm.setLong(1, addressId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationClearanceDates.add(getConstructedApplicationClearanceDateFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE listAllApplicationClearanceDatesByAddressId Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationClearanceDates;
    }

    public ApplicationClearanceDate getConstructedApplicationClearanceDateFromResultSet(ResultSet rst)
            throws SQLException {
        ApplicationClearanceDate applicationClearanceDateVO = new ApplicationClearanceDate();
        applicationClearanceDateVO.setAddressId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID));
        applicationClearanceDateVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID));
        applicationClearanceDateVO.setDepartment(rst.getString(DBConstant.APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT));
        applicationClearanceDateVO.setId(rst.getLong(DBConstant.APPLICATION_CLEARANCE_DATE_COL.ID));
        applicationClearanceDateVO.setPrintedStatus(rst.getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS));
        applicationClearanceDateVO.setResponsedDate(rst
                .getTimestamp(DBConstant.APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE));
        applicationClearanceDateVO.setSentDate(rst.getTimestamp(DBConstant.APPLICATION_CLEARANCE_DATE_COL.SENT_DATE));
        applicationClearanceDateVO.setSentUserId(rst.getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.SENT_USER_ID));
        applicationClearanceDateVO.setResponsedUserId(rst
                .getInt(DBConstant.APPLICATION_CLEARANCE_DATE_COL.RESPONSED_USER_ID));
        applicationClearanceDateVO.setType(rst.getString(DBConstant.APPLICATION_CLEARANCE_DATE_COL.TYPE));
        applicationClearanceDateVO.setComment(rst.getString(DBConstant.APPLICATION_CLEARANCE_DATE_COL.COMMENT));
        return applicationClearanceDateVO;
    }


    public String addApplicationModifiedDate(ApplicationModifiedDatesVO modifiedDate, Connection conn) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.INSERT_APPLICATION_MODIFIED_DATES);
            pstm = conn.prepareStatement(DBConstant.QUERY.INSERT_APPLICATION_MODIFIED_DATES);

            pstm.setLong(1, modifiedDate.getApplicationId());
            pstm.setString(2, modifiedDate.getDateType());
            pstm.setString(3, modifiedDate.getModification());
            pstm.setInt(4, modifiedDate.getModifiedUserId());
            pstm.setString(5, modifiedDate.getModifiedUserName());

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
            pstm.close();

        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public List<ApplicationModifiedDatesVO> getApplicationModifiedDateByTypeAndApplicationId(long applicationId,
                                                                                             String dateType, Connection con) {
        List<ApplicationModifiedDatesVO> datesVOs = new ArrayList<ApplicationModifiedDatesVO>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_MODIFIED_DATE_BY_TYPE_AND_APPLICATION_ID);
            pstm.setString(1, dateType);
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    datesVOs.add(getConstructedApplicationModifiedDatesFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationModifiedDateByTypeAndApplicationId Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return datesVOs;
    }

    private ApplicationModifiedDatesVO getConstructedApplicationModifiedDatesFromResultSet(ResultSet rst)
            throws SQLException {
        ApplicationModifiedDatesVO datesVO = new ApplicationModifiedDatesVO();
        datesVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_MODIFIED_DATES_COL.APPLICATION_ID));
        datesVO.setDateType(rst.getString(DBConstant.APPLICATION_MODIFIED_DATES_COL.DATE_TYPE));
        datesVO.setId(rst.getLong(DBConstant.APPLICATION_MODIFIED_DATES_COL.ID));
        datesVO.setModification(rst.getString(DBConstant.APPLICATION_MODIFIED_DATES_COL.MODIFICATION));
        datesVO.setModifiedDate(rst.getTimestamp(DBConstant.APPLICATION_MODIFIED_DATES_COL.MODIFIED_DATE));
        datesVO.setModifiedUserId(rst.getInt(DBConstant.APPLICATION_MODIFIED_DATES_COL.MODIFIED_USER_ID));
        datesVO.setModifiedUserName(rst.getString(DBConstant.APPLICATION_MODIFIED_DATES_COL.MODIFIED_USER_NAME));
        return datesVO;
    }

    public List<ApplicationClearanceDate> getApplicationClearanceDatesByAddressIdAndDepartment(long addressId,
                                                                                               String department, Connection con) {
        List<ApplicationClearanceDate> datesVOs = new ArrayList<ApplicationClearanceDate>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_CLEARANCE_DATE_BY_DEPARTMENT_AND_ADDRESS_ID);
            pstm.setString(1, department);
            pstm.setLong(2, addressId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    datesVOs.add(getConstructedApplicationClearanceDateFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationClearanceDatesByAddressIdAndDepartment Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return datesVOs;
    }

    public List<ApplicationClearanceDate> getApplicationClearanceDatesByApplicationIdAndDepartment(long applicationId,
                                                                                                   String department, Connection con) {
        List<ApplicationClearanceDate> datesVOs = new ArrayList<ApplicationClearanceDate>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_CLEARANCE_DATE_BY_DEPARTMENT_AND_APPLICATION_ID);
            pstm.setString(1, department);
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    datesVOs.add(getConstructedApplicationClearanceDateFromResultSet(rst));
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationClearanceDatesByApplicationIdAndDepartment Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return datesVOs;
    }

    public ApplicationClearanceDate getApplicationClearanceDateByDeptTypeAddressId(long addressId, String department,
                                                                                   String sentType, Connection con) {
        ApplicationClearanceDate applicationClearanceDate = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_CLEARANCE_DATE_BY_TYPE_DEPARTMENT_AND_ADDRESS_ID);
            pstm.setString(1, sentType);
            pstm.setString(2, department);
            pstm.setLong(3, addressId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationClearanceDate = getConstructedApplicationClearanceDateFromResultSet(rst);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationClearanceDateByDeptTypeAddressId Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationClearanceDate;
    }

    public ApplicationClearanceDate getApplicationClearanceDateByDeptTypeApplicationId(long applicationId,
                                                                                       String department, String sentType, Connection con) {
        ApplicationClearanceDate applicationClearanceDate = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            pstm =
                    con.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_CLEARANCE_DATE_BY_TYPE_DEPARTMENT_AND_APPLICATION_ID);
            pstm.setString(1, sentType);
            pstm.setString(2, department);
            pstm.setLong(3, applicationId);
            rst = pstm.executeQuery();
            if (rst != null) {
                while (rst.next()) {
                    applicationClearanceDate = getConstructedApplicationClearanceDateFromResultSet(rst);
                }
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("INSIDE getApplicationClearanceDateByDeptTypeAddressId Exception");
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return applicationClearanceDate;
    }

    public String updateExternalClearanceStatusByDepartmentAndApplicationId(UserDepartment department,
                                                                            long applicationId, Connection conn) {
        PreparedStatement pstm = null;
        String status = PoliceConstant.ERROR;
        try {

            moveApplicationToAudit(applicationId, conn);

            switch (department) {
                case CID:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_CID);
                    break;
                case IMI:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_IMI);
                    break;
                case NIC:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_NIC);
                    break;
                case PHQ:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_POL);
                    break;
                case POL:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_POL);
                    break;
                case SIS:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_SIS);
                    break;
                case TID:
                    pstm = conn.prepareStatement(DBConstant.QUERY.UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_TID);
                    break;
                default:
                    break;
            }

            pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setLong(2, applicationId);

            int result = pstm.executeUpdate();
            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
            pstm.close();

        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return status;
    }


    public String editApplicationFromVerification(Connection con, ApplicationVO applicationVO) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveApplicationToAudit(applicationVO.getApplicationId(), con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_FROM_VERIFICATION);
            pstm.setString(1, applicationVO.getApplicantNameAsNic());
            pstm.setString(2, applicationVO.getApplicantNameAsPassport());

            pstm.setString(3, applicationVO.getPresentAddressLocal());
            pstm.setString(4, applicationVO.getPresentAddressOverseas());

            // pstm.setLong(5, applicationVO.getHighCommisionReferenceId());
            pstm.setString(5, applicationVO.getHighCommisionReference());
            pstm.setString(6, applicationVO.getHighCommisionReferenceAddress());

            pstm.setString(7, applicationVO.getCertificatePostAddressLineOne());
            pstm.setString(8, applicationVO.getCertificatePostAddressLineTwo());
            pstm.setString(9, applicationVO.getCertificatePostAddressCity());
            pstm.setString(10, applicationVO.getCertificatePostAddressState());
            pstm.setString(11, applicationVO.getCertificatePostAddressPostal());
            // pstm.setLong(13, applicationVO.getCertificatePostAddressCountry());
            pstm.setString(12, applicationVO.getCertificatePostAddressCountryName());

            pstm.setLong(13, applicationVO.getApplicationId());

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("editApplicationFromVerification HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    public String editCompleteApplication(Connection con, ApplicationVO applicationVO) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveApplicationToAudit(applicationVO.getApplicationId(), con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_COMPLETE_APPLICATION);

            pstm.setLong(1, applicationVO.getNationalityId());
            pstm.setString(2, applicationVO.getNationality());

            pstm.setInt(3, applicationVO.getCitizenOfSriLanka());
            pstm.setDate(4, CommonUtil.getSqlDateFromUtilDate(applicationVO.getCitizenshipObtainedDate()));

            pstm.setTimestamp(5, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getDateOfBirth()));
            pstm.setInt(6, applicationVO.getAge());

            pstm.setString(7, applicationVO.getNic());
            pstm.setString(8, applicationVO.getPassport());

            pstm.setLong(9, applicationVO.getCountryId());
            pstm.setString(10, applicationVO.getCountryName());

            if (applicationVO.getHighCommisionReferenceId() == 0) {
                pstm.setNull(11, Types.BIGINT);
            } else {
                pstm.setLong(11, applicationVO.getHighCommisionReferenceId());
            }
            pstm.setString(12, applicationVO.getHighCommisionReference());
            pstm.setString(13, applicationVO.getHighCommisionReferenceAddress());

            pstm.setString(14, applicationVO.getApplicantNameAsNic());
            pstm.setString(15, applicationVO.getApplicantNameAsPassport());

            pstm.setDate(16, CommonUtil.getSqlDateFromUtilDate(applicationVO.getPassportIssueDate()));

            pstm.setString(17, applicationVO.getPresentAddressLocal());
            pstm.setString(18, applicationVO.getPresentAddressOverseas());

            pstm.setString(19, applicationVO.getSex());
            pstm.setString(20, applicationVO.getApplicantStatus());
            pstm.setString(21, applicationVO.getOccupation());
            pstm.setString(22, applicationVO.getPurpose());

            pstm.setInt(23, applicationVO.getPreviousCertificateApply());
            if (applicationVO.getPreviousCertificateCountryId() == 0) {
                pstm.setNull(24, Types.BIGINT);
            } else {
                pstm.setLong(24, applicationVO.getPreviousCertificateCountryId());
            }
            pstm.setString(25, applicationVO.getPreviousCertificateCountryName());
            pstm.setInt(26, applicationVO.getPreviousCertificateIssueStatus());
            pstm.setString(27, applicationVO.getPreviousCertificateReferenceNo());
            pstm.setTimestamp(28, CommonUtil.getSqlTimeStampFromUtilDate(applicationVO.getPreviousCertificateIssueDate()));

            pstm.setString(29, applicationVO.getAuthorizedHandoverPerson());
            pstm.setString(30, applicationVO.getAuthorizedHandoverPersonName());
            pstm.setString(31, applicationVO.getAuthorizedHandoverPersonNicPassport());

            pstm.setString(32, applicationVO.getCertificatePostAddressLineOne());
            pstm.setString(33, applicationVO.getCertificatePostAddressLineTwo());
            pstm.setString(34, applicationVO.getCertificatePostAddressCity());
            pstm.setString(35, applicationVO.getCertificatePostAddressState());
            pstm.setString(36, applicationVO.getCertificatePostAddressPostal());
            if (applicationVO.getCertificatePostAddressCountry() == 0) {
                pstm.setNull(37, Types.BIGINT);
            } else {
                pstm.setLong(37, applicationVO.getCertificatePostAddressCountry());
            }
            pstm.setString(38, applicationVO.getCertificatePostAddressCountryName());


            if (applicationVO.getMobileCountryCodeId() == 0) {
                pstm.setNull(39, Types.BIGINT);
            } else {
                pstm.setLong(39, applicationVO.getMobileCountryCodeId());
            }
            pstm.setString(40, applicationVO.getMobileCountryCode());
            pstm.setString(41, applicationVO.getMobileNo());

            pstm.setString(42, applicationVO.getEmail());

            pstm.setString(43, applicationVO.getSpouseFullName());
            pstm.setString(44, applicationVO.getSpouseNationality());
            pstm.setString(45, applicationVO.getSpousePassport());
            pstm.setString(46, applicationVO.getSpouseNic());

            pstm.setString(47, applicationVO.getDeliveryType());
            pstm.setString(48, applicationVO.getForiegnMinistryInvertNo());

            pstm.setString(49, applicationVO.getPassportAttachPath());
            pstm.setString(50, applicationVO.getPassportBackAttachPath());

            pstm.setString(51, applicationVO.getNicAttachPath());
            pstm.setString(52, applicationVO.getNicBackAttachPath());

            pstm.setString(53, applicationVO.getBirthCertificatePath());
            pstm.setString(54, applicationVO.getBirthCertificateBackPath());

            pstm.setInt(55, applicationVO.getReferredThroughBereau());
            pstm.setString(56, applicationVO.getLetterOfReferencePath());
            pstm.setString(57, applicationVO.getNewNicAttachPath());
            pstm.setString(58, applicationVO.getNewNicBackAttachPath());
            pstm.setString(59, applicationVO.getNewNic());
            pstm.setString(60, applicationVO.getAffidavitAttachPath());
            pstm.setString(61, applicationVO.getSelectedNameOption());

            pstm.setLong(62, applicationVO.getApplicationId());

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("editCompleteApplication HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    public String updateApplicationReferenceNumber(Connection con, String referenceNo, long applicationId) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveApplicationToAudit(applicationId, con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_APPLICATION_REFERENCE_NUMBER);
            pstm.setString(1, referenceNo);
            pstm.setLong(2, applicationId);
            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("updateApplicationReferenceNumber HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    public long savePoliceArea(PoliceAreaVO policeAreaVO, Connection con) {
        PreparedStatement pstm = null;
        long policeAreaId = 0L;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.ADD_POLICE_AREA, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, policeAreaVO.getPoliceArea());
            int result = pstm.executeUpdate();
            if (result > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    policeAreaId = rs.getInt(1);
                }
            }
            policeAreaVO.setId(policeAreaId);
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return policeAreaId;
    }


    public List<ApplicationVO> getAllPreviousAdverseRecords(Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        try {
            // get the application record from reference no
            // LOGGER.info("loadPreviousAdverseRecords :" +
            // DBConstant.QUERY.COUNT_ALL_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT);
            pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_APPLICATOINS_WITH_ADVERSES);
            pstm.setString(1, StringUtils.EMPTY);
            pstm.setString(2, StringUtils.EMPTY);

            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(4, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(5, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(6, PoliceEnumConstant.ApprovalStatus.RJ.toString());

            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(8, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(9, PoliceEnumConstant.ApprovalStatus.OI.toString());

            pstm.setString(10, PoliceEnumConstant.ApprovalStatus.RJ.toString());
            pstm.setString(11, PoliceEnumConstant.ApprovalStatus.NI.toString());
            pstm.setString(12, PoliceEnumConstant.ApprovalStatus.OI.toString());

            if (!(pstm == null)) {
                rst = pstm.executeQuery();
                if (rst != null) {
                    while (rst.next()) {
                        ApplicationVO applicationVO = new ApplicationVO();
                        applicationVO.setApplicationId(rst.getLong(DBConstant.APPLICATION_COL.APPLICATION_ID));
                        applicationVO.setNic(rst.getString(DBConstant.APPLICATION_COL.NIC));
                        applicationVO.setPassport(rst.getString(DBConstant.APPLICATION_COL.PASSPORT));
                        applicationVOs.add(applicationVO);
                    }
                }
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


    public String revertApplicationToPendingClearanceStage(long applicationId, Connection con, boolean dorevertNic, String nicStatus) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveApplicationToAudit(applicationId, con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY_NEW.REVERT_APPLICATION_TO_PENDING_CLEARANCE_STAGE);

            pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(2, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
            
            if(dorevertNic){
            	pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
            }else{
            	pstm.setString(5, nicStatus);
            }
            
            pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());

            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(8, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(9, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(10, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(11, PoliceEnumConstant.ApprovalStatus.PN.toString());

            pstm.setLong(12, applicationId);

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("revertApplicationToPendingClearanceStage HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    public String revertApplicationToVerificationStage(long applicationId, Connection con, boolean dorevertNic, String nicStatus) {
        String result = PoliceConstant.ERROR;
        PreparedStatement pstm = null;
        moveApplicationToAudit(applicationId, con);
        try {
            pstm = con.prepareStatement(DBConstant.QUERY_NEW.REVERT_APPLICATION_TO_VERIFICATION_STAGE);

            pstm.setString(1, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(2, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(3, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(4, PoliceEnumConstant.ApprovalStatus.PN.toString());
            
            if(dorevertNic){
            	pstm.setString(5, PoliceEnumConstant.ApprovalStatus.PN.toString());
            }else{
            	pstm.setString(5, nicStatus);
            }
            
            pstm.setString(6, PoliceEnumConstant.ApprovalStatus.PN.toString());

            pstm.setString(7, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(8, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(9, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(10, PoliceEnumConstant.ApprovalStatus.PN.toString());
            pstm.setString(11, PoliceEnumConstant.ApprovalStatus.PN.toString());

            pstm.setString(12, PoliceEnumConstant.ApplicationQueue.CN.toString());

            pstm.setString(13, PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());

            // pstm.setNull(14, Types.DATE);
            // pstm.setNull(15, Types.DATE);
            // pstm.setNull(16, Types.VARCHAR);
            // pstm.setNull(17, Types.VARCHAR);
            //
            // pstm.setNull(18, Types.INTEGER);
            // pstm.setNull(19, Types.VARCHAR);

            pstm.setLong(14, applicationId);

            int hasUpdated = pstm.executeUpdate();
            if (hasUpdated > 0) {
                LOGGER.info("revertApplicationToVerificationStage HAS UPDATE EXECUTED: " + hasUpdated);
                result = PoliceConstant.SUCCESS;
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
        }
        return result;
    }

    public List<ApplicationVO> getAllVerifiedApplications(Connection conn, Date fromDate, Date toDate) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ApplicationVO applicationVO = null;
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();

        try {
            // LOGGER.info("QUERY -> " + DBConstant.QUERY.SEARCH_APPLICATION_VERIFICATION);
            pstm = conn.prepareStatement(DBConstant.QUERY_NEW.SELECT_ALL_VERIFIED_APPLICATIONS);
            pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
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


    public String updateDhaStatusByUsingApplicationId(PoliceEnumConstant.DHAReviewStatus dhaReviewStatus,
                                                      long applicationId) {
        LOGGER.info("Entered updateDhaStatusByUsingApplicationId(" + dhaReviewStatus + ", " + applicationId + ")");
        PreparedStatement pstm = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(DBConstant.QUERY_NEW.UPDATE_DHA_STATUS_BY_USING_APPLICATION_ID);

            pstm.setString(1, dhaReviewStatus.getStatus());
            pstm.setLong(2, applicationId);

            int result = pstm.executeUpdate();
            if (result > 0) {
                LOGGER.info("Application updated success");
            }
            conn.commit();
            pstm.close();
        } catch (Exception e1) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                LOGGER.error("An error occurred ");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.error("Error: " + e.getMessage());
                }
            }
            return PoliceConstant.ERROR;
        } finally {
            DatabaseManager.close(pstm);
            DatabaseManager.close(conn);
        }

        return PoliceConstant.SUCCESS;
    }
    
    public String updateAspStatusByUsingApplicationId(PoliceEnumConstant.ASPReviewStatus aspReviewStatus,
            long applicationId) {
		LOGGER.info("Entered updateAspStatusByUsingApplicationId(" + aspReviewStatus + ", " + applicationId + ")");
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
		conn = DatabaseManager.getPOLDBConnection();
		conn.setAutoCommit(false);
		pstm = conn.prepareStatement(DBConstant.QUERY_NEW.UPDATE_ASP_STATUS_BY_USING_APPLICATION_ID);
		
		pstm.setString(1, aspReviewStatus.getStatus());
		pstm.setLong(2, applicationId);
		
		int result = pstm.executeUpdate();
		if (result > 0) {
		LOGGER.info("Application updated success");
		}
		conn.commit();
		pstm.close();
		} catch (Exception e1) {
		try {
		conn.rollback();
		} catch (SQLException e) {
		LOGGER.error("An error occurred ");
		if (LOGGER.isDebugEnabled()) {
		LOGGER.error("Error: " + e.getMessage());
		}
		}
        
		return PoliceConstant.ERROR;
		} finally {
		DatabaseManager.close(pstm);
		DatabaseManager.close(conn);
		}
		
		return PoliceConstant.SUCCESS;
	}
    
}
