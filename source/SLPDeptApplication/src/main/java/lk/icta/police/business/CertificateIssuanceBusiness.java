package lk.icta.police.business;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.UserDepartment;
import lk.icta.police.framework.dao.*;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Nadeeshani Senevirathna
 */
public class CertificateIssuanceBusiness {

    private static final Logger LOGGER = Logger.getLogger(CertificateIssuanceBusiness.class);
    private static CertificateIssuanceBusiness instance = null;

    /**
     * Singleton Implementation
     */
    public static CertificateIssuanceBusiness getInstance() {
        synchronized (CertificateIssuanceBusiness.class) {
            if (instance == null) {
                instance = new CertificateIssuanceBusiness();
            }
            return instance;
        }
    }

    private CertificateIssuanceBusiness() {

    }

    public String updateApplicationNICRevisionClearenceStatus(NicRevisionClearenceVO nicRevisionClearenceVO) {
        Connection con = null;
        LOGGER.info("nicRevisionClearenceVO :" + nicRevisionClearenceVO);
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(nicRevisionClearenceVO.getApplicationId(), con);
            LOGGER.info("applicationVO :" + applicationVO);
            if (!(applicationVO == null)) {

                if (!(applicationVO.getVersionId() == nicRevisionClearenceVO.getNicRevisionVersionId())) {
                    return PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
                }

                String comment = getConstructedCommentForNicRevisionClearence(nicRevisionClearenceVO, applicationVO);
                LOGGER.info("comment :" + comment);

                LOGGER.info("nicRevisionClearenceVO after comment :" + nicRevisionClearenceVO);

                if (StringUtils.isNotEmpty(comment)) {
                    //check if the current user has the record lock
                    boolean hasGainedLock = CertificateIssuanceBusiness.getInstance().checkIfUserHasLock(nicRevisionClearenceVO.getApplicationId(), nicRevisionClearenceVO.getUpdatedUserId(), con);
                    LOGGER.info("update app review approval hasGainedLock" + hasGainedLock);
                    LOGGER.info("hasGainedLock :" + hasGainedLock);
                    if (hasGainedLock) {

                        //first update the application status;
                        String status = CertificateIssuanceDAO.getInstance().updateApplicationNICRevisionClearenceStatus(nicRevisionClearenceVO, con);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {

                            if (nicRevisionClearenceVO.isSendApplicationForApprovalAgain()) {
                                status = sendApplicationForApprovalAgain(nicRevisionClearenceVO.getApplicationId(), nicRevisionClearenceVO.getUpdatedUserId(), nicRevisionClearenceVO.getUpdatedUserName(), con);
                            }

                            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                                //insert the audit action
                                ChangeAuditVO changeAuditVO = new ChangeAuditVO(nicRevisionClearenceVO.getApplicationId(), nicRevisionClearenceVO.getUpdatedUserId(),
                                        nicRevisionClearenceVO.getUpdatedUserName(), comment);
                                status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);

                                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                                    //unlock the record
                                    con.commit();
                                    return PoliceConstant.SUCCESS;
                                } else {
                                    con.rollback();
                                    return PoliceConstant.ERROR;
                                }
                            }

                        } else if (StringUtils.equals(status, PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER)) {
                            return PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
                        }

                    } else {
                        return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
                    }
                } else {
                    return PoliceConstant.NO_CHANGES_TO_UPDATE;
                }
            } else {
                return PoliceConstant.ERROR;
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
            DatabaseManager.close(con);
        }
        return PoliceConstant.ERROR;
    }


    private String getConstructedCommentForNicRevisionClearence(NicRevisionClearenceVO nicRevisionClearenceVO, ApplicationVO applicationVO) {
        String comment = "";

        if (StringUtils.isNotEmpty(nicRevisionClearenceVO.getNicRevisionChangedName())) {
            if (!(StringUtils.equals(applicationVO.getApplicantNameAsNic(), nicRevisionClearenceVO.getNicRevisionChangedName()))) {
                comment = comment + "Nic name was changed from " + applicationVO.getApplicantNameAsNic() + " to " + nicRevisionClearenceVO.getNicRevisionChangedName() + ".";
            }
        }

        if (StringUtils.isNotEmpty(nicRevisionClearenceVO.getNicFileName())) {
            comment = comment + " A new NIC file was uploaded, the uploaded file name changed from " + applicationVO.getNicAttachPath() + " to " + nicRevisionClearenceVO.getNicFileName() + ".";
            nicRevisionClearenceVO.setHasUploadedNicFile(true);
        }

        if (nicRevisionClearenceVO.isUpdateClearenceStatus()) {
            nicRevisionClearenceVO.setClearenceStatus(PoliceEnumConstant.ApplicationClearenceStatus.NC.toString());
            if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(), PoliceEnumConstant.ApplicationClearenceStatus.NC.toString()))) {

                //resend the application to all departments
                nicRevisionClearenceVO.setSendApplicationForApprovalAgain(true);
                PoliceEnumConstant.ApplicationClearenceStatus prevClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
                PoliceEnumConstant.ApplicationClearenceStatus afterClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(PoliceEnumConstant.ApplicationClearenceStatus.NC.toString());
                comment = comment + " The Application Clearence Status was changed from " + prevClearenceStatus.getDisplayName() + " to " + afterClearenceStatus.getDisplayName();
            }
        } else {
            nicRevisionClearenceVO.setClearenceStatus(applicationVO.getApplicationClearanceStatus());
        }

        return comment;
    }

    private String sendApplicationForApprovalAgain(long applicationId, int updatedUserId, String updatedUserName, Connection con) {
        boolean hasConCreated = false;
        String result = PoliceConstant.ERROR;
        try {
            if (con == null) {
                con = DatabaseManager.getPOLDBConnection();
                con.setAutoCommit(false);
                hasConCreated = true;
            }

            result = CertificateIssuanceDAO.getInstance().sendApplicationForApprovalAgain(applicationId, updatedUserId, updatedUserName, con);
            if (StringUtils.equals(result, PoliceConstant.SUCCESS)) {
                result = CertificateIssuanceDAO.getInstance().sendAddressesForApprovalAgain(applicationId, updatedUserId, updatedUserName, con);
            }

        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } finally {
            if (hasConCreated) {
                DatabaseManager.close(con);
            }
        }
        return result;
    }


    public Map<String, Object> checkAndLockRecord(long applicationId, int lockedUserId) {
        Connection con = null;
        boolean hasgainedLock = true;
        boolean hasAnyLockedByCurrentUser = false;
        boolean noRecordsToLock = false;

        String returnMessage = PoliceConstant.ERROR;
        int lockedOtherUserId = 0;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
            if (!(applicationVO == null)) {
                if (applicationVO.getPhqRecordLockStatus() > 0 && applicationVO.getPhqRecordLockStatus() != lockedUserId) {
                    lockedOtherUserId = applicationVO.getPhqRecordLockStatus();
                    hasgainedLock = false;
                }
            }

            long count = CertificateIssuanceDAO.getInstance().getOtherLockedAppListByCurrentUserForCertificateIssuance(lockedUserId, applicationId, con);
            LOGGER.info("count fsdfsdf:" + count);
            if (count > 0) {
                hasAnyLockedByCurrentUser = true;
            }

            if (!(hasAnyLockedByCurrentUser)) {
                if (hasgainedLock) {
                    String status = CertificateIssuanceDAO.getInstance().lockCertificateIssuanceRecord(applicationId, lockedUserId, con);
                    if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                        con.commit();
                        returnMessage = PoliceConstant.SUCCESS;
                    } else {
                        con.rollback();
                    }
                } else {
                    if (noRecordsToLock) {
                        returnMessage = PoliceConstant.NO_RECORDS_TO_LOCK;
                    } else {
                        returnMessage = PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER;
                    }

                }
            } else {
                returnMessage = PoliceConstant.ONE_RECORD_IS_ALREADY_LOCKED;
            }

            map.put("MESSAGE", returnMessage);
            map.put("LOCKED_USER", lockedOtherUserId);

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

    public String checkAndUnLockRecord(long applicationId, int lockedUserId) {
        Connection con = null;
        boolean hasgainedLock = false;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            hasgainedLock = checkIfUserHasLock(applicationId, lockedUserId, con);
            LOGGER.info("hasgainedLock :" + hasgainedLock);
            if (hasgainedLock) {
                String status = CertificateIssuanceDAO.getInstance().unlockCertificateIssuanceRecord(applicationId, lockedUserId, con);
                LOGGER.info("status :: :" + status);
                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                    con.commit();
                    return PoliceConstant.SUCCESS;
                } else {
                    con.rollback();
                }
            } else {
                return PoliceConstant.DO_NOT_OWN_LOCK;
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
        return PoliceConstant.ERROR;
    }


    public boolean checkIfUserHasLock(long applicationId, int lockedUserId, Connection con) {
        boolean hasGainedLock = false;
        ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
        if (!(applicationVO == null)) {
            if ((applicationVO.getPhqRecordLockStatus() > 0) && (applicationVO.getPhqRecordLockStatus() == lockedUserId)) {
                hasGainedLock = true;
            }
        }
        return hasGainedLock;
    }

    public List<CommentsVO> getCommentListByApplicationId(long applicationId, Connection conn) {
        boolean hasCreated = false;
        List<CommentsVO> commentsVOs = null;
        try {
            if (conn == null) {
                hasCreated = true;
                conn = DatabaseManager.getPOLDBConnection();
                conn.setAutoCommit(false);
            }
            commentsVOs = CommentDAO.getInstance().getCommentList(conn, applicationId);
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
        return commentsVOs;
    }


    public String updateApplicationReviewApprovalStatus(CertificateClearenceVO clearenceVO) {
        LOGGER.warn("updateApplicationReviewApprovalStatus -> " + clearenceVO);
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(clearenceVO.getApplicationId(), con);
            if (!(applicationVO == null)) {

                if (!(applicationVO.getVersionId() == clearenceVO.getVersionId())) {
                    return PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
                }


                if (clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.DI.getCode()) {
                    List<CommentsVO> commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.LTR.toString(), con);
                    if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                        applicationVO.setLetterConent(commentsVOs.get(0).getComment());
                    }
                }

                String comment = getConstructedCommentForClearence(clearenceVO, applicationVO);
                LOGGER.warn("updateApplicationReviewApprovalStatus comment -> " + comment);
                if (StringUtils.isNotEmpty(comment)) {
                    //check if the current user has the record lock
                    boolean hasGainedLock = checkIfUserHasLock(clearenceVO.getApplicationId(), clearenceVO.getUpdatedUserId(), con);

                    if (hasGainedLock) {

                        boolean hasAnyAdverse = checkAnyCurrentAdverseAvailable(applicationVO);
                        if (!(hasAnyAdverse)) {
                            long adverseRecordCount = ApplicationDAO.getInstance().getCountOfPreviousAdverseRecords(applicationVO.getNic(), applicationVO.getNewNic(), applicationVO.getPassport(), applicationVO.getApplicationId(), con);
                            if (adverseRecordCount > 0) {
                                hasAnyAdverse = true;
                            }
                        }

                        if (hasAnyAdverse) {
                            clearenceVO.setHasAnyAdverse(1);
                        }

                        long blackListCount =
                                BlackListDAO.getInstance().countBlackListRecordsByNICOrPPT(con, applicationVO.getNic(), applicationVO.getNewNic(), applicationVO.getPassport());
                        if (blackListCount > 0) {
                            clearenceVO.setHasBlackListed(1);
                        }

                        //first update the application status;
                        String status = CertificateIssuanceDAO.getInstance().updateApplicationCertificateIssuanceStatus(clearenceVO, applicationVO, con);
                        LOGGER.warn("updateApplicationReviewApprovalStatus status -> " + status);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {

                            //insert the comment if applicable
                            if (clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.CN.getCode() ||
                                    clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.OI.getCode() ||
                                    clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.AS.getCode() ||
                                    clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.DH.getCode() ||
                                    clearenceVO.getUpdatedUserType() == PoliceEnumConstant.UserType.DI.getCode()) {
                                if (StringUtils.isNotEmpty(clearenceVO.getComment())) {
                                    CommentsVO commentsVO = new CommentsVO(clearenceVO);
                                    long commentId = CommentDAO.getInstance().addComments(con, commentsVO);
                                    LOGGER.warn("updateApplicationReviewApprovalStatus commentId -> " + commentId);
                                    if (commentId > 0) {
                                        status = PoliceConstant.SUCCESS;
                                    } else {
                                        status = PoliceConstant.ERROR;
                                    }
                                }
                            }


                            //insert the audit action
                            ChangeAuditVO changeAuditVO = new ChangeAuditVO(clearenceVO.getApplicationId(), clearenceVO.getUpdatedUserId(),
                                    clearenceVO.getUpdatedUserName(), comment);
                            status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
                            LOGGER.warn("updateApplicationReviewApprovalStatus changeAuditVO status -> " + status);

                            String statusDateAdded = PoliceConstant.ERROR;

                            PoliceEnumConstant.UserType userTypeEnum = PoliceEnumConstant.UserType.fromCode(clearenceVO.getUpdatedUserType());
                            switch (userTypeEnum) {
                                case CN:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.CNA.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case AS:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.ASP.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case CA:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.CAD.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case DA:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.DAU.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case DH:
                                    //check if the letter content is modified, if so update the letter content
                                    if (StringUtils.equals(clearenceVO.getClearenceStatus(), PoliceEnumConstant.ApplicationClearenceStatus.IS.toString()) ||
                                            StringUtils.equals(clearenceVO.getClearenceStatus(), PoliceEnumConstant.ApplicationClearenceStatus.CP.toString())) {
                                        if ((StringUtils.equals(clearenceVO.getCertificateType(), PoliceEnumConstant.CertificateType.LT.toString()) ||
                                                StringUtils.equals(clearenceVO.getCertificateType(), PoliceEnumConstant.CertificateType.CL.toString())
                                                        && StringUtils.isNotEmpty(clearenceVO.getLetterContent()))) {
                                            //The letter content is modified, if so update the letter content
                                            if (clearenceVO.getLetterContentCommentId() <= 0) {
                                                CommentsVO commentsVO = new CommentsVO(clearenceVO);
                                                commentsVO.setCommentType(PoliceEnumConstant.CommentType.LTR.toString());
                                                commentsVO.setComment(clearenceVO.getLetterContent());
                                                commentsVO.setApplicationId(applicationVO.getApplicationId());
                                                long commentId = CommentDAO.getInstance().addComments(con, commentsVO);
                                                if (commentId <= 0) {
                                                    status = PoliceConstant.ERROR;
                                                }
                                            } else {
                                                CommentsVO commentsVO = CommentDAO.getInstance().getCommentById(clearenceVO.getLetterContentCommentId(), con);
                                                if (!(commentsVO == null)) {
                                                    commentsVO.setCommentType(PoliceEnumConstant.CommentType.LTR.toString());
                                                    commentsVO.setComment(clearenceVO.getLetterContent());
                                                    status = CommentDAO.getInstance().updateComment(commentsVO, con);
                                                }
                                            }
                                            LOGGER.info("LETTER CONTENT UPDATED status" + status);
                                        }
                                    }
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.DHA.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case DI:
                                    //check if the letter content is modified, if so update the letter content
                                    if (!(StringUtils.equals(clearenceVO.getApprovalStatus(), applicationVO.getDigApproved()))) {
                                        if (StringUtils.isNotEmpty(clearenceVO.getLetterContent()) && (!(StringUtils.equals(clearenceVO.getLetterContent(), applicationVO.getLetterConent())))) {
                                            //The letter content is modified, if so update the letter content
                                            status = CommentDAO.getInstance().updateCommentByTypeAndApplicationId(applicationVO.getApplicationId(), clearenceVO.getLetterContent(), PoliceEnumConstant.CommentType.LTR.toString(), con);
                                            LOGGER.info("LETTER CONTENT UPDATED status" + status);
                                        }
                                    }
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.DIG.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case DU:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.DUU.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case OI:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.OIC.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case PO:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.POF.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                case SA:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.SAD.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;
                                default:
                                    statusDateAdded = ApplicationBusiness.getInstance().addApplicationModifiedDate(clearenceVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.UNK.toString(), comment, clearenceVO.getUpdatedUserId(), clearenceVO.getUpdatedUserName(), con);
                                    LOGGER.warn("CLEARANCE VERIFICATION DATE ADDED STATUS -> " + statusDateAdded);
                                    break;

                            }
                            if (!(StringUtils.equals(clearenceVO.getClearenceStatus(), applicationVO.getApplicationClearanceStatus()))) {
                                if (StringUtils.equals(clearenceVO.getClearenceStatus(), PoliceEnumConstant.ApplicationClearenceStatus.BL.toString())) {
                                    BlackListVO blackListVO = new BlackListVO(applicationVO, clearenceVO.getComment());
                                    long blackListId = BlackListDAO.getInstance().addBlackList(con, blackListVO);
                                    LOGGER.info("blackListId :" + blackListId);
                                }
                            }
                            LOGGER.info("statusDateAdded :" + statusDateAdded);

                            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                                //unlock the record
                                con.commit();
                                return PoliceConstant.SUCCESS;
                            } else {
                                con.rollback();
                                return PoliceConstant.ERROR;
                            }

                        } else if (StringUtils.equals(status, PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER)) {
                            return PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER;
                        }

                    } else {
                        return PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE;
                    }
                } else {
                    return PoliceConstant.NO_CHANGES_TO_UPDATE;
                }
            } else {
                return PoliceConstant.ERROR;
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
            DatabaseManager.close(con);
        }
        return PoliceConstant.ERROR;
    }


    private String getConstructedCommentForClearence(CertificateClearenceVO clearenceVO, ApplicationVO applicationVO) {
        String comment = null;
        String approvalStatus = null;
        boolean hasApprovedModified = false;
        boolean hasClearenceModified = false;
        PoliceEnumConstant.UserType userTypeEnum = PoliceEnumConstant.UserType.fromCode(clearenceVO.getUpdatedUserType());
        switch (userTypeEnum) {
            case CN:
                if (!(StringUtils.equals(clearenceVO.getClearenceStatus(), applicationVO.getApplicationClearanceStatus()))) {

                    PoliceEnumConstant.ApplicationClearenceStatus prevClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
                    PoliceEnumConstant.ApplicationClearenceStatus afterClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(clearenceVO.getClearenceStatus());
                    comment = "Application Clearence Status was changed from " + prevClearenceStatus.getDisplayName() + " to " + afterClearenceStatus.getDisplayName();
                    hasClearenceModified = true;
                }
                break;
            case OI:
                approvalStatus = applicationVO.getOicApproved();
                if (StringUtils.isEmpty(approvalStatus)) {
                    approvalStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                }
                if (StringUtils.isNotEmpty(clearenceVO.getApprovalStatus())) {
                    if (!(StringUtils.equals(clearenceVO.getApprovalStatus(), approvalStatus))) {
                        hasApprovedModified = true;

                        PoliceEnumConstant.ApprovalStatus prevApproval = PoliceEnumConstant.ApprovalStatus.fromCode(approvalStatus);
                        PoliceEnumConstant.ApprovalStatus afterApproval = PoliceEnumConstant.ApprovalStatus.fromCode(clearenceVO.getApprovalStatus());

                        comment = "Application OIC Approval Status was changed from " + prevApproval.getDisplayName() + " to " + afterApproval.getDisplayName();
                    }
                }

                break;
            case AS:
                approvalStatus = applicationVO.getAspApproved();
                if (StringUtils.isEmpty(approvalStatus)) {
                    approvalStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                }
                if (StringUtils.isNotEmpty(clearenceVO.getApprovalStatus())) {
                    if (!(StringUtils.equals(clearenceVO.getApprovalStatus(), approvalStatus))) {
                        hasApprovedModified = true;

                        PoliceEnumConstant.ApprovalStatus prevApproval = PoliceEnumConstant.ApprovalStatus.fromCode(approvalStatus);
                        PoliceEnumConstant.ApprovalStatus afterApproval = PoliceEnumConstant.ApprovalStatus.fromCode(clearenceVO.getApprovalStatus());

                        comment = "Application ASP Approval Status was changed from " + prevApproval.getDisplayName() + " to " + afterApproval.getDisplayName() + ".";
                    }
                }


                if (StringUtils.isNotEmpty(clearenceVO.getClearenceStatus())) {
                    if (!(StringUtils.equals(clearenceVO.getClearenceStatus(), applicationVO.getApplicationClearanceStatus()))) {
                        hasClearenceModified = true;

                        PoliceEnumConstant.ApplicationClearenceStatus prevClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
                        PoliceEnumConstant.ApplicationClearenceStatus afterClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(clearenceVO.getClearenceStatus());

                        if (hasApprovedModified) {
                            comment = comment + "and Application Clearence Status was changed from " + prevClearenceStatus.getDisplayName() + " to " + afterClearenceStatus.getDisplayName();
                        } else {
                            comment = "Application Clearence Status was changed from " + prevClearenceStatus.getDisplayName() + " to " + afterClearenceStatus.getDisplayName();
                        }
                    }
                }

                break;
            case DH:
                approvalStatus = applicationVO.getDhaApproved();
                if (StringUtils.isEmpty(approvalStatus)) {
                    approvalStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                }
                if (StringUtils.isNotEmpty(clearenceVO.getApprovalStatus())) {
                    if (!(StringUtils.equals(clearenceVO.getApprovalStatus(), approvalStatus))) {
                        hasApprovedModified = true;
                        PoliceEnumConstant.ApprovalStatus prevApproval = PoliceEnumConstant.ApprovalStatus.fromCode(approvalStatus);
                        PoliceEnumConstant.ApprovalStatus afterApproval = PoliceEnumConstant.ApprovalStatus.fromCode(clearenceVO.getApprovalStatus());

                        comment = "Application DHA Signed Status was changed from " + prevApproval.getDisplayName() + " to " + afterApproval.getDisplayName();
                    }
                }


                if (StringUtils.isNotEmpty(clearenceVO.getClearenceStatus())) {
                    if (!(StringUtils.equals(clearenceVO.getClearenceStatus(), applicationVO.getApplicationClearanceStatus()))) {
                        hasClearenceModified = true;

                        PoliceEnumConstant.ApplicationClearenceStatus prevClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
                        PoliceEnumConstant.ApplicationClearenceStatus afterClearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(clearenceVO.getClearenceStatus());

                        comment = "Application Clearence Status was changed from " + prevClearenceStatus.getDisplayName() + " to " + afterClearenceStatus.getDisplayName();

                        if (StringUtils.equals(afterClearenceStatus.toString(), PoliceEnumConstant.ApplicationClearenceStatus.CP.toString())) {
                            clearenceVO.setClearenceStatus(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString());
                            comment = comment + ", and then from " + afterClearenceStatus.getDisplayName() + " to " + PoliceEnumConstant.ApplicationClearenceStatus.IS.getDisplayName() + " by system.";
                        }
                    }
                }

                break;
            case DI:
                approvalStatus = applicationVO.getDigApproved();
                if (StringUtils.isEmpty(approvalStatus)) {
                    approvalStatus = PoliceEnumConstant.ApprovalStatus.PN.toString();
                }
                hasApprovedModified = true;
                PoliceEnumConstant.ApprovalStatus prevApproval = PoliceEnumConstant.ApprovalStatus.fromCode(approvalStatus);
                PoliceEnumConstant.ApprovalStatus afterApproval = PoliceEnumConstant.ApprovalStatus.fromCode(clearenceVO.getApprovalStatus());
                comment = "Application DIG Approval Status was changed from " + prevApproval.getDisplayName() + " to " + afterApproval.getDisplayName() + " .";
                if (StringUtils.isNotEmpty(clearenceVO.getLetterContent()) && (!(StringUtils.equals(clearenceVO.getLetterContent(), applicationVO.getLetterConent())))) {
                    comment = comment + " The letter content was changed from " + applicationVO.getLetterConent() + " to " + clearenceVO.getLetterContent();
                }
                break;
            case PO:
                if (StringUtils.isNotEmpty(clearenceVO.getRegisteredPostNo())) {
                    if (!(StringUtils.equals(clearenceVO.getRegisteredPostNo(), applicationVO.getRegPostNo()))) {
                        comment = "Registered post number: " + clearenceVO.getRegisteredPostNo() + " was added";
                    }
                }
                break;
            default:
                System.out.println("NEW USER TYPE :" + userTypeEnum);
                break;
        }

        clearenceVO.setHasApprovedModified(hasApprovedModified);
        clearenceVO.setHasClearenceModified(hasClearenceModified);
        return comment;
    }

    public String unlockClearenceRecord(long applicationId, int lockedUserId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            String status = CertificateIssuanceDAO.getInstance().unlockCertificateIssuanceRecord(applicationId, lockedUserId, con);
            LOGGER.info("status :: :" + status);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                con.commit();
                return PoliceConstant.SUCCESS;
            } else {
                con.rollback();
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
        return PoliceConstant.ERROR;
    }

    public String sendNotificationEmailToApplicant(long applicationId, String emailText, int updatedUserId, String updatedUserName) {
        Connection con = null;
        String status = PoliceConstant.ERROR;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
            status = EmailNotificationBusiness.getInstance().sendNicRevisionEmailToApplicant(applicationVO, emailText);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                status = CertificateIssuanceDAO.getInstance().updateNotificationEmailSentStatus(applicationId, con);
                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                    ChangeAuditVO changeAuditVO = new ChangeAuditVO(applicationId, updatedUserId, updatedUserName, " Sent NIC revision notification Email:- " + emailText);
                    status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
                    if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                }
            }
            LOGGER.info("status :: :" + status);
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


    public String updateApplicationAddressPrintedStatus(List<ApplicationVO> list, int userIdFromSession, String userNameFromSession) {
        Connection con = null;
        boolean isSuccess = true;
        LOGGER.info("updateApplicationAddressPrintedStatus list :" + list);
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            for (ApplicationVO applicationVO : list) {
                LOGGER.info("applicationVO :" + applicationVO);
                if (!(applicationVO == null)) {

                    String comment = "The address of the application with refernce no " + applicationVO.getReferenceNo() + " was printed, no of times printed :" + (applicationVO.getAddressPrintedStatus() + 1);
                    //check if the current user has the record lock
                    LOGGER.info("comment :" + comment);

                    //first update the application status;
                    String status = CertificateIssuanceDAO.getInstance().updateApplicationAddressPrintedStatus(applicationVO.getApplicationId(), con);

                    LOGGER.info("hasGainedLock  update app review approval updated status" + status);
                    if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                        //insert the audit action
                        ChangeAuditVO changeAuditVO = new ChangeAuditVO(applicationVO.getApplicationId(), userIdFromSession, userNameFromSession, comment);
                        status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);

                        if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
                            isSuccess = false;
                            break;
                        }
                    } else {
                        isSuccess = false;
                        break;
                    }
                } else {
                    isSuccess = false;
                    break;
                }
            }

            if (isSuccess) {
                con.commit();
            } else {
                con.rollback();
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
            DatabaseManager.close(con);
        }
        if (isSuccess) {
            return PoliceConstant.SUCCESS;
        }
        return PoliceConstant.ERROR;
    }


    public List<AddressVO> getAddressVOsByApplicationIdAndType(long applicationId, String addressType, Connection conn) {
        boolean hasCreated = false;
        List<AddressVO> addressVOs = null;
        try {
            if (conn == null) {
                hasCreated = true;
                conn = DatabaseManager.getPOLDBConnection();
                conn.setAutoCommit(false);
            }
            addressVOs = AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId, addressType, conn);
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


    public CertificateAuthPersonVO getCurrentCertificateAuthPerson() {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            return CertificateAuthPersonDAO.getInstance().getCurrentCertificateAuthPerson(conn);
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
            DatabaseManager.close(conn);
        }
        return null;
    }

    public String updateApplicationCertificatePrintedStatus(ApplicationVO applicationVO, int userIdFromSession, String userNameFromSession) {
        Connection con = null;
        boolean isSuccess = true;
        LOGGER.info("updateApplicationCertificatePrintedStatus applicationVO :" + applicationVO);
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);

            LOGGER.info("applicationVO :" + applicationVO);
            if (!(applicationVO == null)) {

                String comment = "The application clearence certificate with refernce no " + applicationVO.getReferenceNo() + " was printed, no of times printed :" + (applicationVO.getCertificatePrintedStatus() + 1);
                //check if the current user has the record lock
                LOGGER.info("comment :" + comment);

                //first update the application status;
                String status = CertificateIssuanceDAO.getInstance().updateCertificatePrintedStatus(applicationVO.getApplicationId(), con);

                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                    //insert the audit action
                    ChangeAuditVO changeAuditVO = new ChangeAuditVO(applicationVO.getApplicationId(), userIdFromSession, userNameFromSession, comment);
                    status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);

                    if (!(StringUtils.equals(status, PoliceConstant.SUCCESS))) {
                        isSuccess = false;
                    }
                } else {
                    isSuccess = false;
                }
            } else {
                isSuccess = false;
            }

            if (isSuccess) {
                con.commit();
            } else {
                con.rollback();
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
            DatabaseManager.close(con);
        }
        if (isSuccess) {
            return PoliceConstant.SUCCESS;
        }
        return PoliceConstant.ERROR;
    }

    public boolean checkAnyCurrentAdverseAvailable(ApplicationVO applicationVO) {
        boolean hasAnyAdverseAvailable = false;

        if (StringUtils.equals(applicationVO.getPolStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
            hasAnyAdverseAvailable = true;
        }

        if (StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
            hasAnyAdverseAvailable = true;
        }

        if (StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
            hasAnyAdverseAvailable = true;
        }

        if (StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
            hasAnyAdverseAvailable = true;
        }

        if (StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString()) ||
                StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.OI.toString())) {
            hasAnyAdverseAvailable = true;
        }

        if (StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.RJ.toString()) ||
                StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.OI.toString())) {
            hasAnyAdverseAvailable = true;
        }
        return hasAnyAdverseAvailable;
    }

    public CommentsVO getCommentByCommentTypeAndApplicationId(long applicationId, String commentType) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            List<CommentsVO> commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationId, commentType, conn);
            if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
                return commentsVOs.get(0);
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
            DatabaseManager.close(conn);
        }
        return null;
    }


    public boolean checkIfButtonAvailable(List<ClearenceGridButton> clearenceGridButtons, long maxId) {
        if (!(clearenceGridButtons == null || clearenceGridButtons.isEmpty())) {
            for (ClearenceGridButton clearenceGridButton : clearenceGridButtons) {
                if (clearenceGridButton.getMaxId() == maxId) {
                    return true;
                }
            }
        }
        return false;
    }


    public ClearenceGridButton getCurrentPageButton(List<ClearenceGridButton> clearenceGridButtons, int currentPage) {
        ClearenceGridButton currentPageButton = null;
        ClearenceGridButton lastPageButton = null;
        ClearenceGridButton firstPageButton = null;
        ClearenceGridButton previousPageButton = null;


        if (!(clearenceGridButtons == null || clearenceGridButtons.isEmpty())) {
            for (ClearenceGridButton clearenceGridButton : clearenceGridButtons) {

                if (clearenceGridButton.isLastPage()) {
                    lastPageButton = clearenceGridButton;
                }


                if (clearenceGridButton.getPageNo() == 1) {
                    firstPageButton = clearenceGridButton;
                }

                clearenceGridButton.setCurrentButtonStatus(0);
                clearenceGridButton.setDisplayStatus(0);

                if (currentPage == clearenceGridButton.getPageNo()) {
                    currentPageButton = clearenceGridButton;
                }

                if ((clearenceGridButton.getPageNo() + 1) == currentPage) {
                    previousPageButton = clearenceGridButton;
                }

            }

            if ((!(previousPageButton == null)) && clearenceGridButtons.size() > 0) {
                previousPageButton.setDisplayStatus(1);
                previousPageButton.setLabel("Previous");
            }

            if (!(firstPageButton == null)) {
                firstPageButton.setDisplayStatus(1);
                firstPageButton.setLabel("First");
            }

            if (!(lastPageButton == null)) {
                lastPageButton.setDisplayStatus(1);
                lastPageButton.setLabel("Last");
            }

            if (!(currentPageButton == null)) {
                currentPageButton.setDisplayStatus(1);
                currentPageButton.setCurrentButtonStatus(1);

                boolean isLastOrFirst = false;

                if (!(lastPageButton == null)) {
                    if ((currentPageButton.equals(lastPageButton))) {
                        isLastOrFirst = true;
                    }
                }

                if (!(firstPageButton == null)) {
                    if ((currentPageButton.equals(firstPageButton))) {
                        isLastOrFirst = true;
                    }
                }

                if (!(isLastOrFirst)) {
                    currentPageButton.setLabel("Current");
                }
            }

        }
        return currentPageButton;
    }

    public List<UserDepartment> getResendingDepartmentsByApplicationId(long applicationId) {
        List<UserDepartment> list = new ArrayList<PoliceEnumConstant.UserDepartment>();
        ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
        if (!(StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.NIC);
        }
        if (!(StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.IMI);
        }
        if (!(StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.SIS);
        }
        if (!(StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.TID);
        }
        if (!(StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.CID);
        }
        if (!(StringUtils.equals(applicationVO.getPolStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
            list.add(UserDepartment.POL);
        }
        return list;
    }

    public List<PoliceAreaVO> getPoliceAreaVOsByApplicationIdAndType(long applicationId) {
        Connection conn = null;
        List<PoliceAreaVO> areaVOs = new ArrayList<PoliceAreaVO>();
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            List<AddressVO> addressVOs = getAddressVOsByApplicationIdAndType(applicationId, PoliceEnumConstant.AddressType.AC.toString(), conn);
            if (!(addressVOs == null || addressVOs.isEmpty())) {
                for (AddressVO addressVO : addressVOs) {
                    PoliceAreaVO areaVO = new PoliceAreaVO(addressVO.getPoliceAreaId(), addressVO.getPoliceArea());
                    if (!(areaVOs.contains(areaVO))) {
                        areaVOs.add(areaVO);
                    }
                }
                Collections.sort(areaVOs, new Comparator<PoliceAreaVO>() {
                    @Override
                    public int compare(final PoliceAreaVO lhs, PoliceAreaVO rhs) {
                        return lhs.getPoliceArea().compareTo(rhs.getPoliceArea());
                    }
                });
                return areaVOs;
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
            DatabaseManager.close(conn);
        }
        return null;
    }

    public List<ResendClearanceViewVO> getCurrentCommentDetailsForPoliceArea(long applicationId, long policeAreaId) {
        Connection conn = null;
        List<ResendClearanceViewVO> clearanceViewVOs = new ArrayList<ResendClearanceViewVO>();
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            List<AddressVO> addressVOs = AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationId, PoliceEnumConstant.AddressType.AC.toString(), policeAreaId, conn);
            if (!(addressVOs == null || addressVOs.isEmpty())) {
                for (AddressVO addressVO : addressVOs) {
                    String status = addressVO.getPoliceStatus();
                    if (StringUtils.isNotEmpty(status)) {
                        PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(status);
                        if (!(approvalStatus == null)) {
                            status = approvalStatus.getDisplayName();
                        }
                    }

                    String currentComment = "";
                    List<CommentsVO> commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndAddressId(addressVO.getAddressId(), PoliceEnumConstant.CommentType.POL.toString(), conn);
                    Date currentClearanceDate = null;
                    if (!(commentsVOs == null)) {
                        for (CommentsVO commentsVO : commentsVOs) {
                            currentComment = currentComment + " " + commentsVO.getComment();
                            currentClearanceDate = commentsVO.getCreatedDateTime();
                        }
                    }

                    List<ApplicationClearanceDate> clearanceDates = ApplicationDAO.getInstance().getApplicationClearanceDatesByAddressIdAndDepartment(addressVO.getAddressId(), PoliceEnumConstant.UserDepartment.POL.toString(), conn);
                    if (!(clearanceDates == null || clearanceDates.isEmpty())) {
                        Collections.sort(clearanceDates);
                        ApplicationClearanceDate applicationClearanceDate = clearanceDates.get((clearanceDates.size() - 1));
                        if (!(applicationClearanceDate.getResponsedDate() == null)) {
                            currentClearanceDate = applicationClearanceDate.getResponsedDate();
                        }

                    }
                    String date = "";
                    if (!(currentClearanceDate == null)) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                        date = format.format(currentClearanceDate);
                    }
                    clearanceViewVOs.add(new ResendClearanceViewVO(addressVO.getAddressId(), addressVO.getAddress(), status, currentComment, date));

                }
                return clearanceViewVOs;
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
            DatabaseManager.close(conn);
        }
        return null;
    }

    public ResendClearanceViewVO getCurrentCommentDetailsByDepartmentAndApplicationId(long applicationId, int department) {
        Connection conn = null;
        ResendClearanceViewVO clearanceViewVO = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, conn);

            if (!(applicationVO == null)) {
                String status = getClearanceStatusByDepartment(applicationVO, department);
                String commentType = getCommentTypeByDepartment(department);
                String currentComment = "";
                List<CommentsVO> commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationId, commentType, conn);
                Date currentClearanceDate = null;
                if (!(commentsVOs == null)) {
                    for (CommentsVO commentsVO : commentsVOs) {
                        currentComment = currentComment + " " + commentsVO.getComment();
                        currentClearanceDate = commentsVO.getCreatedDateTime();
                    }
                }

                List<ApplicationClearanceDate> clearanceDates = ApplicationDAO.getInstance().getApplicationClearanceDatesByApplicationIdAndDepartment(applicationId, PoliceEnumConstant.UserDepartment.POL.toString(), conn);
                if (!(clearanceDates == null || clearanceDates.isEmpty())) {
                    Collections.sort(clearanceDates);
                    ApplicationClearanceDate applicationClearanceDate = clearanceDates.get((clearanceDates.size() - 1));
                    if (!(applicationClearanceDate.getResponsedDate() == null)) {
                        currentClearanceDate = applicationClearanceDate.getResponsedDate();
                    }
                }

                String date = "";
                if (!(currentClearanceDate == null)) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                    date = format.format(currentClearanceDate);
                }

                clearanceViewVO = new ResendClearanceViewVO(0, null, status, currentComment, date);

                return clearanceViewVO;
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
            DatabaseManager.close(conn);
        }
        return null;
    }

    private String getCommentTypeByDepartment(int department) {
        String commentType = null;
        PoliceEnumConstant.UserDepartment dept = PoliceEnumConstant.UserDepartment.fromCode(department);
        if (!(dept == null)) {
            switch (dept) {
                case CID:
                    commentType = PoliceEnumConstant.CommentType.CID.toString();
                    break;
                case TID:
                    commentType = PoliceEnumConstant.CommentType.TID.toString();
                    break;
                case IMI:
                    commentType = PoliceEnumConstant.CommentType.IMI.toString();
                    break;
                case NIC:
                    commentType = PoliceEnumConstant.CommentType.NIC.toString();
                    break;
                case POL:
                    commentType = PoliceEnumConstant.CommentType.POL.toString();
                    break;
                case SIS:
                    commentType = PoliceEnumConstant.CommentType.SIS.toString();
                    break;
                case PHQ:
                    commentType = PoliceEnumConstant.CommentType.PHQ.toString();
                    break;
                default:
                    break;
            }
        }
        return commentType;
    }

    private String getClearanceStatusByDepartment(ApplicationVO applicationVO, int department) {
        String status = null;

        PoliceEnumConstant.UserDepartment dept = PoliceEnumConstant.UserDepartment.fromCode(department);
        if (!(dept == null)) {
            switch (dept) {
                case CID:
                    status = applicationVO.getCidStatus();
                    break;
                case TID:
                    status = applicationVO.getTidStatus();
                    break;
                case IMI:
                    status = applicationVO.getImiStatus();
                    break;
                case NIC:
                    status = applicationVO.getNicStatus();
                    break;
                case POL:
                    status = applicationVO.getPolStatus();
                    break;
                case SIS:
                    status = applicationVO.getSisStatus();
                    break;
                default:
                    break;
            }
        }

        if (StringUtils.isNotEmpty(status)) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(status);
            if (!(approvalStatus == null)) {
                status = approvalStatus.getDisplayName();
            }
        }
        return status;
    }

    //application_clearance_date add a new filed as comment and type - SEND/RESEND
    public String resendApplicationForExternalDepartment(CertificateClearenceVO clearenceVO, int userId, String userName) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);
            String status = PoliceConstant.ERROR;
            //first check if the application has been resent already
            int deptId = clearenceVO.getDepartmentId();
            PoliceEnumConstant.UserDepartment department = PoliceEnumConstant.UserDepartment.fromCode(deptId);
            if (!(department == null)) {
                if (StringUtils.equals(department.toString(), PoliceEnumConstant.UserDepartment.POL.toString())) {
                    List<AddressVO> addressVOs = AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(clearenceVO.getApplicationId(), PoliceEnumConstant.AddressType.AC.toString(), clearenceVO.getPoliceAreaId(), conn);

                    boolean hasResend = false;
                    if (!(addressVOs == null)) {
                        for (AddressVO addressVO : addressVOs) {
//							if(StringUtils.equals(addressVO.getPoliceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString())){
                            ApplicationClearanceDate existingDate = ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeAddressId(addressVO.getAddressId(),
                                    department.toString(), PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), conn);
                            if (!(existingDate == null)) {
                                hasResend = true;
                                break;
                            }
//							}

                        }
                    }

                    if (hasResend) {
                        return "ALREADY_RESENT";
                    } else {
                        //not resent yet, resend the application
                        //update the application status
                        status = ApplicationDAO.getInstance().updateExternalClearanceStatusByDepartmentAndApplicationId(department, clearenceVO.getApplicationId(), conn);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            for (AddressVO addressVO : addressVOs) {
                                status = AddressDAO.getInstance().updateAddressPoliceStatusByAddressId(addressVO.getAddressId(),
                                        PoliceEnumConstant.ApprovalStatus.PN.toString(), conn);

                                ApplicationClearanceDate clearanceDate = new ApplicationClearanceDate(clearenceVO.getApplicationId(),
                                        addressVO.getAddressId(), department.toString(), Calendar.getInstance().getTime(),
                                        userId, PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), clearenceVO.getReasonToResend());
                                status = ApplicationDAO.getInstance().addApplicationClearanceDatePolice(clearanceDate, conn);

                                String comment = "The address was resent for clearance to department - " + department.getDisplayName();

                                AddressChangeAuditVO changeAuditVO = new AddressChangeAuditVO(addressVO.getAddressId(), userId, userName, comment);
                                status = AddressDAO.getInstance().addAddressChangeAudit(changeAuditVO, conn);
                            }
                        }


                    }
                } else {
//					String clearanceStatus=null;
//					ApplicationVO  applicationVO=ApplicationDAO.getInstance().getApplicationById(clearenceVO.getApplicationId(), conn);
//					if(!(applicationVO==null)){
//						clearanceStatus=getClearanceStatusByDepartment(applicationVO,department);
//					}

                    ApplicationClearanceDate existingDate = ApplicationDAO.getInstance().getApplicationClearanceDateByDeptTypeApplicationId(clearenceVO.getApplicationId(),
                            department.toString(), PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), conn);

//					if(!(StringUtils.isEmpty(clearanceStatus))){
//						if(!(StringUtils.equals(clearanceStatus,  PoliceEnumConstant.ApprovalStatus.PN.toString()))){
//							existingDate=null;
//						}
//					}


                    if (!(existingDate == null)) {
                        return "ALREADY_RESENT";
                    } else {

                        //update the application status
                        status = ApplicationDAO.getInstance().updateExternalClearanceStatusByDepartmentAndApplicationId(department, clearenceVO.getApplicationId(), conn);
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            //not resent yet, resend the application
                            ApplicationClearanceDate clearanceDate = new ApplicationClearanceDate(clearenceVO.getApplicationId(), 0, department.toString(),
                                    Calendar.getInstance().getTime(), userId, PoliceEnumConstant.ExternalClearanceSendType.RSD.toString(), clearenceVO.getReasonToResend());
                            status = ApplicationDAO.getInstance().addApplicationClearanceDateDept(clearanceDate, conn);

                            String comment = "The application was resent for clearance to department - " + department.getDisplayName();

                            //insert the audit action
                            ChangeAuditVO changeAuditVO = new ChangeAuditVO(clearenceVO.getApplicationId(), userId, userName, comment);
                            status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, conn);

                        }
                    }
                }
                if ((StringUtils.equals(status, PoliceConstant.SUCCESS))) {
                    status = CertificateIssuanceDAO.getInstance().unlockCertificateIssuanceRecord(clearenceVO.getApplicationId(), userId, conn);
                    conn.commit();
                    return PoliceConstant.SUCCESS;
                } else {
                    conn.rollback();
                }


            } else {
                return "INVALID_DEPARTMENT";
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
            DatabaseManager.close(conn);
        }
        return PoliceConstant.ERROR;

    }

    private String getClearanceStatusByDepartment(ApplicationVO applicationVO, UserDepartment department) {
        String clearanceStatus = null;
        switch (department) {
            case CID:
                clearanceStatus = applicationVO.getCidStatus();
                break;
            case IMI:
                clearanceStatus = applicationVO.getImiStatus();
                break;
            case NIC:
                clearanceStatus = applicationVO.getNicStatus();
                break;
            case SIS:
                clearanceStatus = applicationVO.getSisStatus();
                break;
            case TID:
                clearanceStatus = applicationVO.getTidStatus();
                break;
            case POL:
                clearanceStatus = applicationVO.getPolStatus();
                break;
            case PHQ:
                clearanceStatus = applicationVO.getPolStatus();
                break;
            default:
                break;
        }
        return clearanceStatus;
    }

    public CommentTypeDisplayVO getCommentListForApplication(long applicationId) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            conn.setAutoCommit(false);

            List<CommentsVO> commentsList = CertificateIssuanceBusiness.getInstance().getCommentListByApplicationId(applicationId, conn);
            List<ApplicationVO> historyAdverseList = ApplicationBusiness.getInstance().loadPreviousAdverseRecords(applicationId, conn);

            List<String> advrseRecordList = new ArrayList<String>();
            if (!(historyAdverseList == null || historyAdverseList.isEmpty())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (ApplicationVO applicationVO : historyAdverseList) {
                    Date date = applicationVO.getUpdatedDateTime();
                    if (date == null) {
                        date = Calendar.getInstance().getTime();
                    }
                    advrseRecordList.add(applicationVO.getReferenceNo() + " on " + dateFormat.format(date));
                }
            }
            ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            CommentTypeDisplayVO typeDisplayVO = new CommentTypeDisplayVO(commentsList, applicationVO, advrseRecordList, conn);
            return typeDisplayVO;


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
            DatabaseManager.close(conn);
        }
        return null;
    }

    public int getCountOfPreviousAdverseRecords(List<ApplicationVO> adverseRecords, String nic, String passport, long applicationId) {
        int adverseCount = 0;
        if (!(adverseRecords == null)) {
            for (ApplicationVO applicationVOAdverse : adverseRecords) {
                if (!(applicationId == applicationVOAdverse.getApplicationId())) {
                    if (StringUtils.equalsIgnoreCase(applicationVOAdverse.getNic(), nic)) {
                        if (StringUtils.isNotEmpty(nic)) {
                            adverseCount++;
                        }
                    } else if (StringUtils.equalsIgnoreCase(applicationVOAdverse.getPassport(), passport)) {
                        if (StringUtils.isNotEmpty(passport)) {
                            adverseCount++;
                        }
                    }
                }
            }
        }
        return adverseCount;
    }

    public long countBlackListRecordsByNICOrPPT(List<BlackListVO> blackListRecords, String nic, String passport) {
        int blackListCount = 0;
        if (!(blackListRecords == null)) {
            for (BlackListVO blackListVO : blackListRecords) {
                if (StringUtils.equalsIgnoreCase(blackListVO.getNic(), nic)) {
                    if (StringUtils.isNotEmpty(nic)) {
                        blackListCount++;
                    }
                } else if (StringUtils.equalsIgnoreCase(blackListVO.getPassport(), passport)) {
                    if (StringUtils.isNotEmpty(passport)) {
                        blackListCount++;
                    }
                }
            }
        }
        return blackListCount;
    }


}
