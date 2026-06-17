package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant;
import lk.icta.commonuser.framework.vo.UserTypeVO;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.DocumentBusiness;
import lk.icta.police.business.EditApplicationBusiness;
import lk.icta.police.business.TransactionBussiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicantStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationPurpose;
import lk.icta.police.framework.constant.PoliceEnumConstant.DeliveryType;
import lk.icta.police.framework.constant.PoliceEnumConstant.HandOverPerson;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EditApplicationAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = -8517227229292215672L;
    private static final Logger LOGGER = Logger.getLogger(EditApplicationAction.class);
    private Map<String, Object> session;

    private List<CountryVO> countryList;
    private List<CommissionerVO> highCommissionList;
    private List<PoliceAreaVO> policeAreaList;
    private Map<String, ApplicationPurpose> applicationPurposeMap;
    private Map<String, ApplicantStatus> applicantStatus;
    private Map<String, HandOverPerson> handOverPersons;
    private Map<String, DeliveryType> deliveryTypes;

    private ApplicationVO applicationVO;
    private CommissionerVO commissionerVO;
    private List<AddressVO> certificateAddressList;

    private String countryId;
    private String commissionerId;

    //	NICF("NIC Front Side"), NICB("NIC Back Side"), PASF("Passport Front Side"), PASB("Passport Back Side"),
//	BIRF("Birth certificate Front Side"), BIRB("Birth certificate Back Side"), LETR("Letter Of reference"),LETA("Letter Of Approval");
    private String fileType;

    //UPLOAD, REUPVRF, REUPCLR
    private String uploadType;

    //IMAGE,WORD etc.
    private String fileCategory;

    private File file;
    private String fileExtension;
    private String fileName;

    private Date dateOfBirth;
    private Date previousCertificateIssueDate;
    private Date dateOfCitizenshipObtained;
    private Date passportIssueDate;

    private long applicationId;

    private String referenceNo;

    private TransactionVO transactionVO;

    private String message;

    private String documentName;
    private String filePath;

    private DocumentVO savedDocumentVO;
    private List<DocumentVO> documentVOS;

    private String isOicUserStr;
    private String documentComment;
    private String docFileType;

    public String execute() {
        if (applicationId > 0) {
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            certificateAddressList = ApplicationBusiness.getInstance().getAddressListByApplicationId(applicationId);
            transactionVO = TransactionBussiness.getInstance().getTransaction(applicationVO.getTransactionId());
        }
        loadInitialData();
        return SUCCESS;
    }

    public String loadEditApplicationAfterVerification() {
        if (applicationId > 0) {
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            certificateAddressList = ApplicationBusiness.getInstance().getAddressListByApplicationId(applicationId);
            transactionVO = TransactionBussiness.getInstance().getTransaction(applicationVO.getTransactionId());
        }
        loadInitialData();
        return SUCCESS;
    }

    private void loadInitialData() {
        countryList = ApplicationBusiness.getInstance().getCountryList();
        policeAreaList = ApplicationBusiness.getInstance().getPoliceAreaList();
        applicationPurposeMap = PoliceEnumConstant.ApplicationPurpose.getApplicationPurposeMap();
        applicantStatus = PoliceEnumConstant.ApplicantStatus.getApplicantStatusMap();
        handOverPersons = PoliceEnumConstant.HandOverPerson.getHandOverPersonMap();
        deliveryTypes = PoliceEnumConstant.DeliveryType.getDeliveryTypeMap();
        if (!(applicationVO == null)) {
            highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(applicationVO.getCountryId());
        }

    }


    public String loadCommisionerVOList() {
        highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(Long.parseLong(countryId));
        return SUCCESS;
    }

    public String loadCommissionVO() {
        commissionerVO = ApplicationBusiness.getInstance().getCommissionerVOById(Long.parseLong(commissionerId));
        return SUCCESS;
    }

    public String updateApplication() {
        try {
            applicationVO.setApplicantCertificateAddresses(certificateAddressList);

            applicationVO.setUpdatedUserId(this.getUserIdFromSession());
            applicationVO.setUpdatedUserName(this.getUserNameFromSession());
            applicationVO.setUpdatedDateTime(new Date());

            applicationVO.setDateOfBirth(dateOfBirth);
            applicationVO.setPreviousCertificateIssueDate(previousCertificateIssueDate);
            applicationVO.setCitizenshipObtainedDate(dateOfCitizenshipObtained);
            applicationVO.setPassportIssueDate(passportIssueDate);

            String status = EditApplicationBusiness.getInstance().updateCompleteApplication(applicationVO, getUserFromSession(), transactionVO, certificateAddressList);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                addActionMessage("The application was updated successfully ");
            } else if (StringUtils.equals(status, PoliceConstant.NO_CHANGES_TO_UPDATE)) {
                addActionError("There were no changes to update in the application!");
            } else {
                addActionError("An error occurred when trying to update application!");
            }
            this.applicationId = applicationVO.getApplicationId();
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            certificateAddressList = ApplicationBusiness.getInstance().getAddressListByApplicationId(applicationId);
            transactionVO = TransactionBussiness.getInstance().getTransaction(applicationVO.getTransactionId());
            loadInitialData();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String updateApplicationAfterVerification() {
        try {
            applicationVO.setApplicantCertificateAddresses(certificateAddressList);

            applicationVO.setUpdatedUserId(this.getUserIdFromSession());
            applicationVO.setUpdatedUserName(this.getUserNameFromSession());
            applicationVO.setUpdatedDateTime(new Date());

            applicationVO.setDateOfBirth(dateOfBirth);
            applicationVO.setPreviousCertificateIssueDate(previousCertificateIssueDate);
            applicationVO.setCitizenshipObtainedDate(dateOfCitizenshipObtained);
            applicationVO.setPassportIssueDate(passportIssueDate);

            String status = EditApplicationBusiness.getInstance().updateCompleteApplicationAfterVerification(applicationVO, getUserFromSession(), transactionVO, certificateAddressList);
            if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                addActionMessage("The application was updated successfully ");
            } else if (StringUtils.equals(status, PoliceConstant.NO_CHANGES_TO_UPDATE)) {
                addActionError("There were no changes to update in the application!");
            } else {
                addActionError("An error occurred when trying to update application!");
            }
            this.applicationId = applicationVO.getApplicationId();
            applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            certificateAddressList = ApplicationBusiness.getInstance().getAddressListByApplicationId(applicationId);
            transactionVO = TransactionBussiness.getInstance().getTransaction(applicationVO.getTransactionId());
            loadInitialData();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadSearchEditApplication() {
        applicationId = 0;
        referenceNo = null;
        return SUCCESS;
    }

    public String loadSearchUploadAdditionalDocs() {
        applicationId = 0;
        referenceNo = null;
        return SUCCESS;
    }


    public String searchUploadDocumentApplication() {
        LOGGER.info("Entered searchUploadDocumentApplication()");
        isOicUser();
        documentVOS = DocumentBusiness.getInstance().getAllUploadedDocumentsByApplicationId(applicationId);
        applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
        return SUCCESS;
        
    }

    public String uploadAdditionalApplicationDocuments() {
        LOGGER.info("Entered uploadAdditionalApplicationDocuments()");

        if (applicationId > 0 && referenceNo != null) {
            DocumentVO documentVO = new DocumentVO();
            documentVO.setApplicationId(applicationId);
            documentVO.setReferenceNo(referenceNo);
            documentVO.setDocumentName(documentName);
            documentVO.setFilePath(filePath);
            documentVO.setComment(StringUtils.trim(documentComment));
            UserVO userVO = getUserFromSession();
            assert userVO != null;
            String loggingUser = userVO.getFullName();
            documentVO.setCreatedBy(loggingUser);
            documentVO.setCreatedDate(CommonUtil.getSqlTimeStampFromUtilDate(new Date()));

            if (PoliceEnumConstant.DocumentTypes.IMAGE.toString().equals(docFileType)) {
                documentVO.setFileType(PoliceEnumConstant.DocumentTypes.IMAGE.toString());
            } else if (PoliceEnumConstant.DocumentTypes.DOC.toString().equals(docFileType)) {
                documentVO.setFileType(PoliceEnumConstant.DocumentTypes.DOC.toString());
            }else {
                addActionError("Cannot find valid a document type.");
            }

            documentVO.setFileType(docFileType);
            
            ChangeAuditVO changeAuditVO = constructChangeAudit(); 
            
            Long savedDocId = DocumentBusiness.getInstance().addAdditionalDocument(documentVO, changeAuditVO);
            if (savedDocId != null && savedDocId > 0) {
                documentVO.setId(savedDocId);
                savedDocumentVO = documentVO;
            }
        } else {
            addActionError("An error occurred : Found invalid Application Id or Reference Number.");
        }

        isOicUser();
        return SUCCESS;
    }

    private ChangeAuditVO constructChangeAudit() {
        ChangeAuditVO changeAuditVO = new ChangeAuditVO();
        changeAuditVO.setApplicationId(applicationId);
        changeAuditVO.setUpdatedUserId(getUserIdFromSession());
        changeAuditVO.setUpdatedUserName(getUserNameFromSession());
        changeAuditVO.setUpdatedUserDateTime(new Date());
        changeAuditVO.setComment("A special document was uploaded for this application");
        return changeAuditVO;
    }
    
    private String findApplicationByRefNo(String referenceNo) {
        isOicUser();
        applicationId = 0;
        if (StringUtils.isNotEmpty(referenceNo)) {
            ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNumber(referenceNo);
            if (!(applicationVO == null)) {
                if (!(applicationVO.getCertificatePostedDate() == null || StringUtils.isEmpty(applicationVO.getRegPostNo()))) {
                    message = "This application is already marked as posted, therefore it is not allowed to edit!";
                } else {
                    applicationId = applicationVO.getApplicationId();
                }
            } else {
                message = "Theres no application available for the given reference number. Please enter a valid application reference number to proceed!";
            }
        } else {
            message = "Please enter an application reference number to proceed!";
        }
        return SUCCESS;
    }
    
    private String findApplicationByRefNoForUploadAndPrint(String referenceNo) {
        isOicUser();
        applicationId = 0;
        if (StringUtils.isNotEmpty(referenceNo)) {
            ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNumber(referenceNo);
            if (!(applicationVO == null)) {
//                if (!(applicationVO.getCertificatePostedDate() == null || StringUtils.isEmpty(applicationVO.getRegPostNo()))) {
//                    message = "This application is already marked as posted, therefore it is not allowed to edit!";
//                } else {
                    applicationId = applicationVO.getApplicationId();
//                }
            } else {
                message = "Theres no application available for the given reference number. Please enter a valid application reference number to proceed!";
            }
        } else {
            message = "Please enter an application reference number to proceed!";
        }
        return SUCCESS;
    }

    public String searchEditApplication() {
        return findApplicationByRefNo(referenceNo);
    }
    
    public String searchApplicationForUploadAndPrint() {
        return findApplicationByRefNoForUploadAndPrint(referenceNo);
    }

    private Boolean isOicUser() {
        UserVO loggingUser = getUserFromSession();
        assert loggingUser != null;
        UserTypeVO loggingUserType = loggingUser.getUserType();
        String userType = loggingUserType.getName().getValue();
        if (CommonUserEnumConstant.UserType.OIC.getValue().equals(userType)) {
            isOicUserStr = "true";
            return Boolean.TRUE;
        } else {
            isOicUserStr = "false";
            return Boolean.FALSE;
        }
    }

    private String getUserEmailFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getEmailId();
        }
        return null;
    }

    private int getUserIdFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getId();
        }
        return 0;
    }

    private String getUserNameFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getFullName();
        }
        return null;
    }

    private UserVO getUserFromSession() {
        if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
            return (UserVO) session.get(PoliceConstant.SESSION_USER);
        }
        return null;
    }


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<CountryVO> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryVO> countryList) {
        this.countryList = countryList;
    }

    public List<CommissionerVO> getHighCommissionList() {
        return highCommissionList;
    }

    public void setHighCommissionList(List<CommissionerVO> highCommissionList) {
        this.highCommissionList = highCommissionList;
    }

    public List<PoliceAreaVO> getPoliceAreaList() {
        return policeAreaList;
    }

    public void setPoliceAreaList(List<PoliceAreaVO> policeAreaList) {
        this.policeAreaList = policeAreaList;
    }

    public Map<String, ApplicationPurpose> getApplicationPurposeMap() {
        return applicationPurposeMap;
    }

    public void setApplicationPurposeMap(
            Map<String, ApplicationPurpose> applicationPurposeMap) {
        this.applicationPurposeMap = applicationPurposeMap;
    }

    public Map<String, ApplicantStatus> getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(Map<String, ApplicantStatus> applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public Map<String, HandOverPerson> getHandOverPersons() {
        return handOverPersons;
    }

    public void setHandOverPersons(Map<String, HandOverPerson> handOverPersons) {
        this.handOverPersons = handOverPersons;
    }

    public Map<String, DeliveryType> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(Map<String, DeliveryType> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public ApplicationVO getApplicationVO() {
        return applicationVO;
    }

    public void setApplicationVO(ApplicationVO applicationVO) {
        this.applicationVO = applicationVO;
    }

    public CommissionerVO getCommissionerVO() {
        return commissionerVO;
    }

    public void setCommissionerVO(CommissionerVO commissionerVO) {
        this.commissionerVO = commissionerVO;
    }

    public List<AddressVO> getCertificateAddressList() {
        return certificateAddressList;
    }

    public void setCertificateAddressList(List<AddressVO> certificateAddressList) {
        this.certificateAddressList = certificateAddressList;
    }


    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCommissionerId() {
        return commissionerId;
    }

    public void setCommissionerId(String commissionerId) {
        this.commissionerId = commissionerId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getPreviousCertificateIssueDate() {
        return previousCertificateIssueDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setPreviousCertificateIssueDate(Date previousCertificateIssueDate) {
        this.previousCertificateIssueDate = previousCertificateIssueDate;
    }

    public Date getDateOfCitizenshipObtained() {
        return dateOfCitizenshipObtained;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setDateOfCitizenshipObtained(Date dateOfCitizenshipObtained) {
        this.dateOfCitizenshipObtained = dateOfCitizenshipObtained;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getFileCategory() {
        return fileCategory;
    }

    public void setFileCategory(String fileCategory) {
        this.fileCategory = fileCategory;
    }

    public TransactionVO getTransactionVO() {
        return transactionVO;
    }

    public void setTransactionVO(TransactionVO transactionVO) {
        this.transactionVO = transactionVO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DocumentVO getSavedDocumentVO() {
        return savedDocumentVO;
    }

    public void setSavedDocumentVO(DocumentVO savedDocumentVO) {
        this.savedDocumentVO = savedDocumentVO;
    }

    public List<DocumentVO> getDocumentVOS() {
        return documentVOS;
    }

    public void setDocumentVOS(List<DocumentVO> documentVOS) {
        this.documentVOS = documentVOS;
    }

    public String getIsOicUserStr() {
        return isOicUserStr;
    }

    public void setIsOicUserStr(String isOicUserStr) {
        this.isOicUserStr = isOicUserStr;
    }

    public String getDocumentComment() {
        return documentComment;
    }

    public void setDocumentComment(String documentComment) {
        this.documentComment = documentComment;
    }

    public String getDocFileType() {
        return docFileType;
    }

    public void setDocFileType(String docFileType) {
        this.docFileType = docFileType;
    }
}
