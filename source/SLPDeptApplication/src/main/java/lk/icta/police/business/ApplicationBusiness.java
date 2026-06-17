package lk.icta.police.business;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.AddressType;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.BlackListDAO;
import lk.icta.police.framework.dao.RequestClarificationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.utility.DateUtil;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ApplicationBusiness {

    private static final Logger LOGGER = Logger.getLogger(ApplicationBusiness.class);
    private static ApplicationBusiness instance = null;
    private static String datePattern = "dd/MM/yyyy";

    /**
     * Singleton Implementation
     */
    public static ApplicationBusiness getInstance() {
        synchronized (ApplicationBusiness.class) {
            if (instance == null) {
                instance = new ApplicationBusiness();
            }
            return instance;
        }
    }

    private ApplicationBusiness() {

    }

    public long addApplication(ApplicationVO applicationVO) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            boolean isExpressIssue = false;

            LOGGER.warn("applicationVO.getApplicationClearanceStatus() :" + applicationVO.getApplicationClearanceStatus());
            LOGGER.warn("applicationVO.getApplicationReviewStatus() :" + applicationVO.getApplicationReviewStatus());

            if (!(StringUtils.isEmpty(applicationVO.getApplicationClearanceStatus()) || StringUtils.isEmpty(applicationVO
                    .getApplicationReviewStatus()))) {
                if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                        PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())
                        && StringUtils.equals(applicationVO.getApplicationReviewStatus(),
                        PoliceEnumConstant.ApplicationReviewStatus.VF.toString())) {
                    isExpressIssue = true;

//                    applicationVO.setPolStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//                    applicationVO.setCidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//                    applicationVO.setTidStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//                    applicationVO.setSisStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//
//                    if (StringUtils.isEmpty(applicationVO.getNic()) && StringUtils.isEmpty(applicationVO.getNewNic())) {
//                        applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.AP.toString());
//                    } else {
//                        applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//                    }
//
//                    applicationVO.setImiStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
                    
                    applicationVO.setApplicationQueue(PoliceEnumConstant.ApplicationQueue.CN.toString());

                }
            }
            LOGGER.warn("isExpressIssue :" + isExpressIssue);

            long applicationId = ApplicationDAO.getInstance().addApplication(con, applicationVO);

            boolean addressAdded = false;
            if (applicationId != 0) {
                if (!(applicationVO.getApplicantCertificateAddresses() == null || applicationVO
                        .getApplicantCertificateAddresses().isEmpty())) {
                    for (AddressVO addressVO : applicationVO.getApplicantCertificateAddresses()) {
                        addressVO.setType(PoliceEnumConstant.AddressType.AC.toString());
                        if(addressVO.getFromDateStr() != null){
                        	addressVO.setFromDate(DateUtil.convertStringToDate(addressVO.getFromDateStr(), datePattern));
                        }
                        if(addressVO.getToDateStr() != null){
                            addressVO.setToDate(DateUtil.convertStringToDate(addressVO.getToDateStr(), datePattern));
                        }                        
                        addressVO.setApplicationId(applicationId);
//                        if (isExpressIssue) {
//                            addressVO.setPoliceStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
//                        }
                    }
                }

                addressAdded = AddressDAO.getInstance().addAddressList(con, applicationVO.getApplicantCertificateAddresses());


                if (addressAdded) {

                    if (applicationId > 0 && isExpressIssue) {
                        String status =
                                this.saveApplicationClearanceDate(applicationId, applicationVO.getUpdatedUserId(), con,
                                        applicationVO.getApplicantCertificateAddresses());
                        LOGGER.warn("CLEARANCE DATA UPDATES STATUS -> " + status);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            String comment = "Express issue application - by default set to Verified state";
                            status =
                                    this.addApplicationModifiedDate(applicationId,
                                            PoliceEnumConstant.ApplicationModifiedDateTypes.VFM.toString(), comment,
                                            applicationVO.getUpdatedUserId(), applicationVO.getUpdatedUserName(), con);
                            LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + status);
                        }
                    }


                    con.commit();
                    return applicationId;
                }
            }
            con.rollback();
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return 0;
    }

    public List<ApplicationVO> getAllocationList(
            ApplicationVerificationSearchCriteriaVO applicationVerificationSearchCriteriaVO, int userId) {
        Connection con = null;
        List<ApplicationVO> applicationVOs = new ArrayList<ApplicationVO>();
        int requestClarificationCount = 0;
        int blackListedCount = 0;

        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            applicationVOs =
                    ApplicationDAO.getInstance().fetchApplicationVerificationList(applicationVerificationSearchCriteriaVO, con);

            for (ApplicationVO applicationVO : applicationVOs) {

                if (applicationVO.getApplicantNameAsNic() == null || applicationVO.getApplicantNameAsNic().isEmpty()) {
                    applicationVO.setApplicantNameAsNic(applicationVO.getApplicantNameAsPassport());
                }
                requestClarificationCount =
                        RequestClarificationBusiness.getInstance().countRequestClarificationForApplication(
                                applicationVO.getApplicationId(), con);
                // LOGGER.info("requestClarificationCount -> " + requestClarificationCount +
                // " getApplicationReviewStatus -> " + applicationVO.getApplicationReviewStatus());
                if (requestClarificationCount == 0
                        && applicationVO.getApplicationReviewStatus().trim()
                        .equals(PoliceEnumConstant.ApplicationReviewStatus.RC.toString())) {
                    applicationVO.setHasRequestClarification(1);
                } else if (requestClarificationCount == 1
                        && applicationVO.getApplicationReviewStatus().trim()
                        .equals(PoliceEnumConstant.ApplicationReviewStatus.RV.toString())) {
                    applicationVO.setHasRequestClarification(2);

                    RequestClarificationVO requestClarificationVO =
                            RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept(
                                    applicationVO.getReferenceNo());
                    // LOGGER.info("getReviewedUserId=================== " +
                    // requestClarificationVO.getReviewedUserId());
                    // Update the request to update in to application table and then mark review user id in to
                    // request clarification
                    if (requestClarificationVO.getReviewedUserId() != 0) {
                        applicationVO.setHasRequestClarification(3);
                    }
                } else {
                    // LOGGER.info("getApplicationReviewStatus=================== " +
                    // applicationVO.getApplicationReviewStatus());
          /*
           * if(applicationVO.getApplicationReviewStatus().trim().equals(PoliceEnumConstant.
           * ApplicationReviewStatus.RV.toString())) { RequestClarificationVO requestClarificationVO
           * =
           * RequestClarificationBusiness.getInstance().getRequestClarificationByReferenceNoForDept
           * (applicationVO.getReferenceNo()); LOGGER.info("getReviewedUserId=================== " +
           * requestClarificationVO.getReviewedUserId()); //Update the request to update in to
           * application table and then mark review user id in to request clarification
           * if(requestClarificationVO.getReviewedUserId() != 0) {
           * applicationVO.setHasRequestClarification(3); } } else {
           */
                    applicationVO.setHasRequestClarification(0);
          /* } */

                }


                if (userId == applicationVO.getPhqRecordLockStatus()) {
                    applicationVO.setHasCurrentUserLocked(1);
                } else {
                    applicationVO.setHasCurrentUserLocked(0);
                }

                blackListedCount = countBlackListRecordsByNICOrPPT(applicationVO.getNic(), applicationVO.getPassport(), con);

                if (blackListedCount >= 1) {
                    applicationVO.setHasBlackListed(1);
                } else {
                    applicationVO.setHasBlackListed(0);
                }
                applicationVO.allocateFileTypes();

            }

            return applicationVOs;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public Map<String, Object> lockPHQRecord(long lockedApplicationId, int lockedUserId) {
        Connection con = null;
        String status = PoliceConstant.ERROR;

        // String phqLockStatus = PoliceConstant.ERROR;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("STATUS", status);
        try {
            int userCount = countPHQLocs(lockedUserId);
            if (userCount > 0) {
                map.put("STATUS", PoliceConstant.ONE_RECORD_IS_ALREADY_LOCKED);
            } else {
                int lockedId = checkPHQLockStatus(lockedApplicationId);
                if (lockedId <= 0) {
                    con = DatabaseManager.getPOLDBConnection();
                    con.setAutoCommit(false);
                    status = ApplicationDAO.getInstance().lockPHQRecord(lockedApplicationId, lockedUserId, con);
                    if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                    map.put("LOCKED_USER", lockedId);
                    map.put("STATUS", PoliceConstant.SUCCESS);
                    return map;
                } else {
                    map.put("LOCKED_USER", lockedId);
                    map.put("STATUS", PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER);
                }
            }
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return map;
    }

    public String unlockPHQRecord(long lockedApplicationId, Connection con) {
        boolean doClose = false;
        String status = PoliceConstant.ERROR;
        try {
            if (con == null) {
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
                doClose = true;
            }
            status = ApplicationDAO.getInstance().unlockPHQRecord(lockedApplicationId, con);
            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }
        }
        return status;
    }


    // new method was added
    public Map<String, Object> updateApplicationVerificationStatus(long applicationId, String reviewStatus,
                                                                   UserVO userVO, String referenceNo, ChangeAuditVO changeAuditVO, Connection con) {
        boolean doClose = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (con == null) {
                doClose = true;
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }


            String status = this.updateReviewStatus(applicationId, reviewStatus, con);

            LOGGER.warn("REVIEW UPDATE STATUS -> " + status);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {

                status = this.saveApplicationClearanceDate(applicationId, userVO.getId(), con, null);

                LOGGER.warn("CLEARANCE DATA UPDATES STATUS -> " + status);
                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                    status =
                            this.addApplicationModifiedDate(applicationId,
                                    PoliceEnumConstant.ApplicationModifiedDateTypes.VFM.toString(), changeAuditVO.getComment(),
                                    userVO.getId(), userVO.getUserName(), con);

                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + status);
                    if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                        status = this.addChangeAudit(changeAuditVO, con);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            map.put("STATUS", PoliceConstant.SUCCESS);
                            map.put("MESSAGE", "Successfully Updated!");
                        } else {
                            map.put("STATUS", PoliceConstant.ERROR);
                            map.put("MESSAGE", "Unable to update the review status!");
                        }
                    } else {
                        map.put("STATUS", PoliceConstant.ERROR);
                        map.put("MESSAGE", "Unable to update the review status!");
                    }
                } else {
                    map.put("STATUS", PoliceConstant.ERROR);
                    map.put("MESSAGE", "Unable to update the review status!");
                }
            } else {
                map.put("STATUS", PoliceConstant.ERROR);
                map.put("MESSAGE", "Unable to update the review status!");
            }

            this.unlockPHQRecord(applicationId, con);

            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }


        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }
        }

        return map;
    }

    public String updateReviewStatus(long applicationId, String reviewStatus, Connection con) {
        boolean doClose = false;
        String status = PoliceConstant.ERROR;
        try {
            if (con == null) {
                doClose = true;
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
            status = ApplicationDAO.getInstance().updateReviewStatus(applicationVO, reviewStatus, con);
            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }
        }
        return status;
    }

    public int checkPHQLockStatus(long applicationId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return ApplicationDAO.getInstance().checkPHQLockStatus(applicationId, con);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return -1;
    }

    public int countPHQLocs(int userId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return ApplicationDAO.getInstance().countPHQLocs(con, userId);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return 0;
    }

    public ApplicationVO getApplicationByApplicationId(long applicationId) {
        Connection con = null;
        ApplicationVO applicationVO = new ApplicationVO();
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
            if (!(applicationVO == null)) {
                applicationVO.allocateFileTypes();
                applicationVO.constructcertificatepostalAddress(false);
            }
            return applicationVO;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }


    public boolean updateCompletedApplication(ApplicationVO applicationVO) {
        Connection con = null;
        boolean result;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            result = ApplicationDAO.getInstance().updateCompletedApplication(con, applicationVO);
            con.commit();
            return result;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        } finally {
            DatabaseManager.close(con);
        }
    }


    public String moveApplicationToAudit(long applicationId) {
        Connection con = null;
        String status = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            status = ApplicationDAO.getInstance().moveApplicationToAudit(applicationId, con);
            if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                con.commit();
            } else {
                con.rollback();
            }
            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return status;
    }

    private String updateRequestClarification(RequestClarificationVO requestClarificationVO, long applicationId,
                                              String reviewStatus, Connection con) {
        boolean doClose = false;
        long requestClarificationId = 0L;
        String status = PoliceConstant.ERROR;

        try {
            if (con == null) {
                doClose = true;
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }

            requestClarificationId =
                    RequestClarificationBusiness.getInstance().saveRequestClarification(requestClarificationVO, con);
            status = updateReviewStatus(applicationId, reviewStatus, con);
            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status) && requestClarificationId > 0) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }
        }
        return status;
    }

    public String addChangeAudit(ChangeAuditVO changeAuditVO, Connection con) {
        boolean doClose = false;
        String status = PoliceConstant.ERROR;
        try {
            if (con == null) {
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }

            status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);

            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }

        }
        return status;
    }


    /**
     * @param from
     * @param to
     * @return
     */
    public List<ApplicationVO> loadApplicationToClearanceReport(Date from, Date to) {
        Connection con = null;
        List<ApplicationVO> applicationList = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            applicationList = ApplicationDAO.getInstance().loadApplicationToClearanceReport(from, to, con);
            for (ApplicationVO applicationVO : applicationList) {
                applicationVO.constructcertificatepostalAddress(true);
            }

        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return applicationList;
    }

    public boolean updateApplication(ApplicationVO applicationVO) {
        Connection con = null;
        boolean result;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            result = ApplicationDAO.getInstance().updateApplication(con, applicationVO);
            con.commit();
            return result;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        } finally {
            DatabaseManager.close(con);
        }
    }

    // public String applicationUpdateForRequest (ApplicationVO applicationVO) {
    // Connection con = null;
    // String status=PoliceConstant.ERROR;
    // try {
    // con = DatabaseManager.getPOLDBConnection();
    // con.setAutoCommit(false);
    // status= ApplicationDAO.getInstance().applicationUpdateForRequest(applicationVO, con);
    // if(StringUtils.equals(PoliceConstant.SUCCESS, status)){
    // con.commit();
    // }else{
    // con.rollback();
    // }
    // return status;
    // } catch (BusinessException e) {
    // LOGGER.error(e);
    // e.printStackTrace();
    // } catch (SQLException e) {
    // LOGGER.error(e);
    // e.printStackTrace();
    // } finally {
    // DatabaseManager.close(con);
    // }
    // return status;
    // }

    public ApplicationVO getApplicationWithAddresses(String referenceNo) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplication(conn, referenceNo);

            if (applicationVO != null) {
                List<AddressVO> certificateAddressList =
                        AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(),
                                AddressType.AC.toString(), conn);

                applicationVO.setApplicantCertificateAddresses(certificateAddressList);
                applicationVO.allocateFileTypes();
                return applicationVO;
            } else {
                return null;
            }

        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(conn);
        }
        return null;
    }


    public ApplicationVO getApplicationByReferenceNumber(String referenceNo) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            return ApplicationDAO.getInstance().getApplication(conn, referenceNo);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(conn);
        }
        return null;
    }

    public List<CountryVO> getCountryList() {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return ApplicationDAO.getInstance().getCountryList(con);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public int countBlackListRecordsByNICOrPPT(String nic, String passport) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return BlackListDAO.getInstance().countBlackListRecordsByNICOrPPT(con, nic, passport);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return 0;
    }

    public int countBlackListRecordsByNICOrPPT(String nic, String passport, Connection con) {
        try {
            return BlackListDAO.getInstance().countBlackListRecordsByNICOrPPT(con, nic, passport);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return 0;
    }

    public List<PoliceAreaVO> getPoliceAreaList() {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            List<PoliceAreaVO> areaVOs = ApplicationDAO.getInstance().getPoliceAreaList(con);
            Collections.sort(areaVOs, new Comparator<PoliceAreaVO>() {
                @Override
                public int compare(final PoliceAreaVO lhs, PoliceAreaVO rhs) {
                    return lhs.getPoliceArea().compareTo(rhs.getPoliceArea());
                }
            });
            return areaVOs;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public List<CommissionerVO> getCommissionerVOListByCountryId(long countryId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return ApplicationDAO.getInstance().getCommissionerVOListByCountryId(con, countryId);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    // public boolean verifyApplication(String nicNo, String passportNo, long country){
    // Connection con = null;
    // try {
    // con = DatabaseManager.getPOLDBConnection();
    // con.setAutoCommit(false);
    // return ApplicationDAO.getInstance().verifyApplication(con, nicNo, passportNo, country);
    // } catch (BusinessException e) {
    // LOGGER.error(e);
    // e.printStackTrace();
    // } catch (SQLException e) {
    // LOGGER.error(e);
    // e.printStackTrace();
    // } catch (Exception e){
    // LOGGER.error(e);
    // }finally {
    // DatabaseManager.close(con);
    // }
    // return false;
    // }

    public String verifyApplication(String nicNo, String newNicNo, String passportNo, long country) {
        Connection con = null;
        String returnString = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            boolean isNicAvailable = true;
            boolean isPassportAvailable = true;

            if (StringUtils.isEmpty(nicNo)) {
                isNicAvailable = false;
                nicNo = "1";
            }

            if (StringUtils.isEmpty(passportNo)) {
                isPassportAvailable = false;
                passportNo = "1";
            }
            // Connection conn, String nicNo, String passportNo, long country
            boolean isApplicationExist =
                    ApplicationDAO.getInstance().verifyApplicationByDepartment(con, nicNo, newNicNo, passportNo, country);

            if (isApplicationExist) {
                if (!(isNicAvailable)) {
                    returnString = "APPLICATION_AVAILABLE_FOR_PASSPORT_AND_COUNTRY";
                } else {
                    returnString = "APPLICATION_AVAILABLE_FOR_NIC_PASSPORT_AND_COUNTRY";
                }
            } else {
                returnString = "APPLICATION_NOT_AVAILABLE_FOR_NIC_PASSPORT_AND_COUNTRY";
            }


        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return returnString;
    }

    public CommissionerVO getCommissionerVOById(long commissionerId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return ApplicationDAO.getInstance().getCommissionerVO(con, commissionerId);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public ApplicationVO getAuthApplicationWithAddresses(String referenceNo, String userEmail, String authProvider) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getAuthApplication(conn, referenceNo);

            if (applicationVO != null) {
                List<AddressVO> certificateAddressList =
                        AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(),
                                AddressType.AC.toString(), conn);

                applicationVO.setApplicantCertificateAddresses(certificateAddressList);

                return applicationVO;
            } else {
                return null;
            }

        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(conn);
        }
        return null;
    }


    /**
     * @param applicationId
     * @param con
     * @return
     */
    public List<ApplicationVO> loadPreviousAdverseRecords(long applicationId, Connection con) {
        boolean hasCreated = false;
        List<ApplicationVO> applicationList = null;
        try {
            if (con == null) {
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
                hasCreated = true;
            }
            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
            if (!(applicationVO == null)) {
                applicationList =
                        ApplicationDAO.getInstance().loadPreviousAdverseRecords(applicationVO.getNic(),
                                applicationVO.getPassport(), applicationId, con);
                for (ApplicationVO application : applicationList) {
                    application.constructcertificatepostalAddress(true);
                }
            }

        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (hasCreated) {
                DatabaseManager.close(con);
            }
        }
        return applicationList;
    }

    public List<AddressVO> getAddressListByUserRole(long applicationId, int userRole, long locationId, Connection conn) {
        boolean hasCreated = false;
        List<AddressVO> addressVOs = null;
        try {
            if (conn == null) {
                hasCreated = true;
                conn = DatabaseManager.getPOLDBConnection();
                conn.setAutoCommit(false);
            }

            if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
                addressVOs =
                        AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId,
                                PoliceEnumConstant.AddressType.AC.toString(), conn);

            }

            if (!(addressVOs == null)) {
                for (AddressVO addressVO : addressVOs) {
                    if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
                        // check and add if an active temp address is avalable;
                        AddressTempVO addressTempVO =
                                AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), conn);
                        addressVO.setAddressTempVO(addressTempVO);
                    }
                    addressVO.constructDateStrings();
                }
            }


        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (hasCreated) {
                DatabaseManager.close(conn);
            }
        }
        return addressVOs;
    }

    public List<ChangeAuditVO> loadChangeAuditByReferenceNo(long applicationId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            List<ChangeAuditVO> auditVOs = ApplicationDAO.getInstance().loadChangeAuditByReferenceNo(applicationId, con);
            Collections.sort(auditVOs);
            return auditVOs;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public String updateCerificateSerialNumber(long applicationId, String certificateCerialNo) {
        Connection con = null;
        String status = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            status = ApplicationDAO.getInstance().updateCerificateSerialNumber(applicationId, certificateCerialNo, con);
            if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                con.commit();
            } else {
                con.rollback();
            }
            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return status;
    }

    public String saveApplicationClearanceDate(long applicationId, int userId, Connection con, List<AddressVO> addressVOs) {
        String status = PoliceConstant.ERROR;
        boolean doClose = false;
        ApplicationClearanceDate applicationClearanceDate = null;
        String policeDeptStatus = PoliceConstant.ERROR;
        String phqDeptStatus = PoliceConstant.ERROR;
        String deptStatus = PoliceConstant.ERROR;

        try {
            if (con == null) {
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
                doClose = true;
            }

            if (addressVOs == null) {
                addressVOs = AddressDAO.getInstance().getAddressList(con, applicationId);
            }


            if (!(addressVOs == null)) {
                for (AddressVO addressVO : addressVOs) {
                    applicationClearanceDate =
                            constructApplicationClearanceDateVO(applicationId, PoliceEnumConstant.UserDepartment.POL.toString(),
                                    userId, PoliceEnumConstant.ExternalClearanceSendType.SND.toString(), null);
                    applicationClearanceDate.setAddressId(addressVO.getAddressId());
                    policeDeptStatus =
                            ApplicationDAO.getInstance().addApplicationClearanceDatePolice(applicationClearanceDate, con);
                    LOGGER.warn("POLICE DEPT STATUS -> " + policeDeptStatus);
                    if (policeDeptStatus == PoliceConstant.ERROR) {
                        break;
                    }

                    applicationClearanceDate =
                            constructApplicationClearanceDateVO(applicationId, PoliceEnumConstant.UserDepartment.PHQ.toString(),
                                    userId, PoliceEnumConstant.ExternalClearanceSendType.SND.toString(), null);
                    applicationClearanceDate.setAddressId(addressVO.getAddressId());
                    phqDeptStatus = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(applicationClearanceDate, con);
                    LOGGER.warn("PHQ DEPT STATUS -> " + phqDeptStatus);
                    if (policeDeptStatus == PoliceConstant.ERROR) {
                        break;
                    }

                }
            }

            Map<Integer, PoliceEnumConstant.UserDepartment> userDepartments = PoliceEnumConstant.UserDepartment.getLookup();

            for (Map.Entry<Integer, PoliceEnumConstant.UserDepartment> entry : userDepartments.entrySet()) {

                if (!((StringUtils.equals(entry.getValue().toString(), PoliceEnumConstant.UserDepartment.POL.toString())) || (StringUtils
                        .equals(entry.getValue().toString(), PoliceEnumConstant.UserDepartment.PHQ.toString())))) {
                    applicationClearanceDate =
                            constructApplicationClearanceDateVO(applicationId, entry.getValue().toString(), userId,
                                    PoliceEnumConstant.ExternalClearanceSendType.SND.toString(), null);
                    deptStatus = ApplicationDAO.getInstance().addApplicationClearanceDateDept(applicationClearanceDate, con);
                    if (deptStatus == PoliceConstant.ERROR) {
                        break;
                    }
                }
                LOGGER.warn("DEPT STATUS -> " + deptStatus);
            }


            if (StringUtils.equals(PoliceConstant.SUCCESS, policeDeptStatus)
                    && StringUtils.equals(PoliceConstant.SUCCESS, deptStatus)) {
                status = PoliceConstant.SUCCESS;
                if (doClose) {
                    con.commit();
                }
            } else {
                status = PoliceConstant.ERROR;
                if (doClose) {
                    con.rollback();
                }
            }

            status = PoliceConstant.SUCCESS;
        } catch (BusinessException e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }
        }
        return status;
    }

    private ApplicationClearanceDate constructApplicationClearanceDateVO(long applicationId, String department,
                                                                         int userId, String sendType, String comment) {
        ApplicationClearanceDate applicationClearanceDate = new ApplicationClearanceDate();

        applicationClearanceDate.setApplicationId(applicationId);
        applicationClearanceDate.setDepartment(department);
        applicationClearanceDate.setSentDate(new Date());
        applicationClearanceDate.setPrintedStatus(0);
        applicationClearanceDate.setSentUserId(userId);
        applicationClearanceDate.setType(sendType);
        applicationClearanceDate.setComment(comment);
        return applicationClearanceDate;

    }


    public String addApplicationModifiedDate(long applicationId, String dateType, String modification,
                                             int modifiedUserId, String modifiedUserName, Connection con) {
        String status = PoliceConstant.ERROR;
        boolean hasConnCreated = false;
        try {
            if (con == null) {
                hasConnCreated = true;
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }

            ApplicationModifiedDatesVO modifiedDate = new ApplicationModifiedDatesVO();
            modifiedDate.setApplicationId(applicationId);
            modifiedDate.setDateType(dateType);
            modifiedDate.setModification(modification);
            modifiedDate.setModifiedUserId(modifiedUserId);
            modifiedDate.setModifiedUserName(modifiedUserName);

            status = ApplicationDAO.getInstance().addApplicationModifiedDate(modifiedDate, con);

            if (hasConnCreated) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            status = PoliceConstant.SUCCESS;
        } catch (BusinessException e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            status = PoliceConstant.ERROR;
            LOGGER.error(e);
        } finally {
            if (hasConnCreated) {
                DatabaseManager.close(con);
            }
        }
        return status;
    }

    public List<AddressVO> getAddressListByApplicationId(long applicationId) {
        Connection con = null;
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            addressVOs = AddressDAO.getInstance().getAddressList(con, applicationId);
            return addressVOs;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public List<AddressChangeAuditVO> getAddressChangeAuditListByApplicationId(long applicationId) {
        Connection con = null;
        List<AddressChangeAuditVO> addressChangeAuditVOs = new ArrayList<AddressChangeAuditVO>();
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            List<AddressVO> addressVOs = AddressDAO.getInstance().getAllAddressList(con, applicationId);
            if (!(addressVOs == null || addressVOs.isEmpty())) {
                for (AddressVO addressVO : addressVOs) {
                    long addressId = addressVO.getAddressId();
                    List<AddressChangeAuditVO> changeAuditVOs =
                            AddressDAO.getInstance().getAddressChangeAuditVOsByAddressId(addressId, con);
                    if (!(changeAuditVOs == null || changeAuditVOs.isEmpty())) {
                        for (AddressChangeAuditVO addressChangeAuditVO : changeAuditVOs) {
                            addressChangeAuditVO.setAddress(addressVO.getAddress());
                            addressChangeAuditVO.setPoliceArea(addressVO.getPoliceArea());
                            addressChangeAuditVOs.add(addressChangeAuditVO);
                        }
                    }
                }
            }
            Collections.sort(addressChangeAuditVOs);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(con);
        }
        return addressChangeAuditVOs;
    }


    public List<ApplicationClearanceDate> getApplicationClearanceDateListByApplicationId(long applicationId,
                                                                                         Connection con) {
        List<ApplicationClearanceDate> applicationClearanceDates = new ArrayList<ApplicationClearanceDate>();
        boolean hasConCreated = false;
        try {
            if (con == null) {
                hasConCreated = true;
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
            }
            applicationClearanceDates =
                    AddressDAO.getInstance().getApplicationClearanceDateListByApplicationId(con, applicationId);
            return applicationClearanceDates;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (hasConCreated) {
                DatabaseManager.close(con);
            }
        }
        return null;
    }

    public Map<String, Object> updateRequestClarificationWrapper(RequestClarificationVO requestClarificationVO,
                                                                 ChangeAuditVO changeAuditVO, long applicationId, String reviewStatus, UserVO userVO) {
        Connection con = null;
        Map<String, Object> map = new HashMap<String, Object>();
        String status = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            int requestApplicationCount =
                    RequestClarificationDAO.getInstance().countRequestClarificationForApplication(con, applicationId);
            System.out.println("requestApplicationCount :" + requestApplicationCount);
            if (StringUtils.equals(StringUtils.trim(reviewStatus.trim()),
                    PoliceEnumConstant.ApplicationReviewStatus.RC.toString())
                    && requestApplicationCount == 0) {
                status =
                        ApplicationBusiness.getInstance().updateRequestClarification(requestClarificationVO, applicationId,
                                reviewStatus, con);
                if (status.equals(PoliceConstant.SUCCESS)) {
                    status =
                            ApplicationBusiness.getInstance().addApplicationModifiedDate(applicationId,
                                    PoliceEnumConstant.ApplicationModifiedDateTypes.RQC.toString(), changeAuditVO.getComment(),
                                    userVO.getId(), userVO.getUserName(), con);
                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + status);
                    if (status.equals(PoliceConstant.SUCCESS)) {
                        status = this.addChangeAudit(changeAuditVO, con);
                        LOGGER.warn("CHANGE AUDIT ADDED STATUS -> " + status);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
                            map.put("STATUS", PoliceConstant.SUCCESS);
                            map.put("MESSAGE", "Successfully Updated");
                            map.put("APPLICATION_VO", applicationVO);
                        } else {
                            map.put("STATUS", PoliceConstant.ERROR);
                            map.put("MESSAGE", "Unable to update the review status!");
                        }
                    } else {
                        map.put("STATUS", PoliceConstant.ERROR);
                        map.put("MESSAGE", "Unable to update the review status!");
                    }
                } else {
                    map.put("STATUS", PoliceConstant.ERROR);
                    map.put("MESSAGE", "Unable to update the review status.");
                }
            } else {
                if (requestApplicationCount > 0) {
                    map.put("STATUS", PoliceConstant.ERROR);
                    map.put("MESSAGE", "Request clarification already sent for this application.");
                }
            }

            if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
            map.put("STATUS", PoliceConstant.ERROR);
            map.put("MESSAGE", "Internal Error! Unable to update the review status.");
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
            map.put("STATUS", PoliceConstant.ERROR);
            map.put("MESSAGE", "Internal Error! Unable to update the review status.");
        } finally {
            DatabaseManager.close(con);
        }
        return map;

    }

    public Map<String, Object> saveRequestForUpdate(RequestClarificationVO requestClarificationVOObj,
                                                    ChangeAuditVO changeAuditVO, UserVO userVO, String reviewStatus, boolean updateRequestForUpdate_dob,
                                                    boolean updateRequestForUpdate_name, boolean updateRequestForUpdate_passport, boolean updateRequestForUpdate_nic,
                                                    long reqClarificationApplicationNo, String reqClarificationReferenceNo,
                                                    String updateRequestForUpdate_nicAttachPath, String updateRequestForUpdate_nicAttachPathBack,
                                                    String updateRequestForUpdate_passportAttachPath, String updateRequestForUpdate_passportAttachPathBack,
                                                    String updateRequestForUpdate_changeName, Date updateRequestForUpdateChangeDob) {
        Connection con = null;
        Map<String, Object> map = new HashMap<String, Object>();
        String status = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            status = RequestClarificationBusiness.getInstance().updateRequestClarification(requestClarificationVOObj, con);
            if (status.equals(PoliceConstant.SUCCESS)) {

                String commentStr = changeAuditVO.getComment();

                ApplicationVO applicationVOfromDB =
                        ApplicationDAO.getInstance().getApplicationById(reqClarificationApplicationNo, con);
                // VALIDATION COMMENTED ACCORDING TO THE REQUIREMENT. USER CAN SUBMIT NIC, PPT, NAME AND DOB
                // EVEN PHQ NOT REQUESTED. IF PHQ WILLING TO ACCEPT THE CHANGE THE CAN ACCEPT IF THEY NOT
                // REQUESTED
                if (updateRequestForUpdate_nic) {
                    if (StringUtils.isEmpty(updateRequestForUpdate_nicAttachPath)) {
                        commentStr +=
                                "Update NIC " + applicationVOfromDB.getNicAttachPath() + " to "
                                        + requestClarificationVOObj.getNicPath() + ", ";
                        applicationVOfromDB.setNicAttachPath(requestClarificationVOObj.getNicPath());
                        applicationVOfromDB.setNicBackAttachPath(requestClarificationVOObj.getNicPathBack());
                    } else {
                        commentStr +=
                                "Update NIC(Change NIC by PHQ Manually) " + applicationVOfromDB.getNicAttachPath() + " to "
                                        + updateRequestForUpdate_nicAttachPath + ", ";
                        applicationVOfromDB.setNicAttachPath(updateRequestForUpdate_nicAttachPath);
                        applicationVOfromDB.setNicBackAttachPath(updateRequestForUpdate_nicAttachPathBack);
                    }
                }

                if (updateRequestForUpdate_passport) {
                    if (CommonUtil.checkBlank(updateRequestForUpdate_passportAttachPath)) {
                        commentStr +=
                                "Update Passport " + applicationVOfromDB.getPassportAttachPath() + " to "
                                        + requestClarificationVOObj.getPassportPath() + ", ";
                        applicationVOfromDB.setPassportAttachPath(requestClarificationVOObj.getPassportPath());
                        applicationVOfromDB.setPassportBackAttachPath(requestClarificationVOObj.getPassportPathBack());
                    } else {
                        commentStr +=
                                "Update Passport(Change Passport by PHQ Manually) " + applicationVOfromDB.getPassportAttachPath()
                                        + " to " + updateRequestForUpdate_passportAttachPath + ", ";
                        applicationVOfromDB.setPassportAttachPath(updateRequestForUpdate_passportAttachPath);
                        applicationVOfromDB.setPassportBackAttachPath(updateRequestForUpdate_passportAttachPathBack);
                    }
                }

                if (/* requestClarificationVOObj.getVerifyName() == 1 && */updateRequestForUpdate_name) {

                    if (requestClarificationVOObj.getName().equals(updateRequestForUpdate_changeName.trim())) {
                        commentStr +=
                                "Update Name " + applicationVOfromDB.getApplicantNameAsNic() + " to "
                                        + requestClarificationVOObj.getName() + ", ";
                        applicationVOfromDB.setApplicantNameAsNic(requestClarificationVOObj.getName());
                    } else {
                        commentStr +=
                                "Update Name(Change Name by PHQ Manually) " + applicationVOfromDB.getApplicantNameAsNic() + " to "
                                        + updateRequestForUpdate_changeName + ", ";
                        applicationVOfromDB.setApplicantNameAsNic(updateRequestForUpdate_changeName);
                    }
                }

                if (/* requestClarificationVOObj.getVerifyDateOfBirth() == 1 && */updateRequestForUpdate_dob) {

                    if (requestClarificationVOObj.getDateOfBirth().compareTo(
                            CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob)) == 0) {
                        commentStr +=
                                "Update Date of Birth " + CommonUtil.getSqlDateFromUtilDate(applicationVOfromDB.getDateOfBirth())
                                        + " to " + CommonUtil.getSqlDateFromUtilDate(requestClarificationVOObj.getDateOfBirth()) + ", ";
                        applicationVOfromDB.setDateOfBirth(requestClarificationVOObj.getDateOfBirth());
                    } else {
                        commentStr +=
                                "Update Date of Birth(Change DOB by PHQ Manually) "
                                        + CommonUtil.getSqlDateFromUtilDate(applicationVOfromDB.getDateOfBirth()) + " to "
                                        + CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob) + ", ";
                        applicationVOfromDB.setDateOfBirth(CommonUtil.getSqlDateFromUtilDate(updateRequestForUpdateChangeDob));
                    }
                }

                status = ApplicationDAO.getInstance().applicationUpdateForRequest(applicationVOfromDB, con);
                LOGGER.warn("applicationUpdateForRequest STATUS -> " + status);
                if (status.equals(PoliceConstant.SUCCESS)) {
                    commentStr +=
                            "Update Review Status " + reviewStatus + " for application with reference no "
                                    + reqClarificationReferenceNo + " by " + userVO.getUserName();
                    status =
                            ApplicationBusiness.getInstance().addApplicationModifiedDate(applicationVOfromDB.getApplicationId(),
                                    PoliceEnumConstant.ApplicationModifiedDateTypes.RQU.toString(), commentStr, userVO.getId(),
                                    userVO.getUserName(), con);
                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + status);
                    if (status.equals(PoliceConstant.SUCCESS)) {
                        status = this.addChangeAudit(changeAuditVO, con);
                        LOGGER.warn("CHANGE AUDIT ADDED STATUS -> " + status);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            map.put("STATUS", PoliceConstant.SUCCESS);
                            map.put("MESSAGE", "Successfully Updated");
                        } else {
                            map.put("STATUS", PoliceConstant.ERROR);
                            map.put("MESSAGE", "Unable to update the review status!");
                        }
                    } else {
                        map.put("STATUS", PoliceConstant.ERROR);
                        map.put("MESSAGE", "Unable to update the review status!");
                    }
                } else {
                    map.put("STATUS", PoliceConstant.ERROR);
                    map.put("MESSAGE", "Unable to update the Applcaition");
                }
            } else {
                map.put("STATUS", PoliceConstant.ERROR);
                map.put("MESSAGE", "Unable to update the record.");
            }
            if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
            map.put("STATUS", PoliceConstant.ERROR);
            map.put("MESSAGE", "Internal Error! Unable to update the review status.");
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
            map.put("STATUS", PoliceConstant.ERROR);
            map.put("MESSAGE", "Internal Error! Unable to update the review status.");
        } finally {
            DatabaseManager.close(con);
        }
        return map;
    }


    public String updateDhaStatusByUsingApplicationId(PoliceEnumConstant.DHAReviewStatus dhaReviewStatus,
                                                      long applicationId) {
        LOGGER.info("Entered updateDhaStatusByUsingApplicationId(" + dhaReviewStatus + ", " + applicationId + ")");

        return ApplicationDAO.getInstance().updateDhaStatusByUsingApplicationId(dhaReviewStatus, applicationId);
    }
    
    public String updateAspStatusByUsingApplicationId(PoliceEnumConstant.ASPReviewStatus aspReviewStatus,
            long applicationId) {
	LOGGER.info("Entered updateAspStatusByUsingApplicationId(" + aspReviewStatus + ", " + applicationId + ")");
	
	return ApplicationDAO.getInstance().updateAspStatusByUsingApplicationId(aspReviewStatus, applicationId);
	}


}
