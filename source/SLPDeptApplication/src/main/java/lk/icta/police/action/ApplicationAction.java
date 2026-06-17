package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.SequenceNumberBusiness;
import lk.icta.police.business.TransactionBussiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicantStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationPurpose;
import lk.icta.police.framework.constant.PoliceEnumConstant.DeliveryType;
import lk.icta.police.framework.constant.PoliceEnumConstant.HandOverPerson;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;
import lk.icta.police.util.DateRangeValidator;
import lk.icta.police.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApplicationAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = -8517227229292215672L;
    private static final Logger LOGGER = Logger.getLogger(ApplicationAction.class);
    private Map<String, Object> session;

    private List<CountryVO> countryList;
    private List<CommissionerVO> highCommissionList;
    private List<PoliceAreaVO> policeAreaList;
    private Map<String, ApplicationPurpose> applicationPurposeMap;
    private Map<String, ApplicantStatus> applicantStatus;
    private Map<String, HandOverPerson> handOverPersons;
    private Map<String, DeliveryType> deliveryTypes;

    private ApplicationVO applicationVO;
    private TransactionVO transactionVO;
    private CommissionerVO commissionerVO;
    private List<AddressVO> certificateAddressList;

    private int verificationStatus;
    private int reApplyStatus;

    private String countryId;
    private String commissionerId;

    // NICF("NIC Front Side"), NICB("NIC Back Side"), PASF("Passport Front Side"),
    // PASB("Passport Back Side"),
    // BIRF("Birth certificate Front Side"), BIRB("Birth certificate Back Side"),
    // LETR("Letter Of reference"),LETA("Letter Of Approval");
    private String fileType;

    // UPLOAD, REUPVRF, REUPCLR
    private String uploadType;

    // IMAGE,WORD etc.
    private String fileCategory;

    private File file;
    private String fileExtension;
    private String fileName;

    private Date dateOfBirth;
    private Date previousCertificateIssueDate;
    private Date dateOfCitizenshipObtained;
    private Date passportIssueDate;

    private String paymentFeeString;
    private double paymentFee;
    private double serviceFee;
    private double postageFee;
    private double applicationFee;
    private double convenienceFee;
    private String referenceNumber;
    private String applicationReferenceNumber;
    private String enPaymentRequest;
    private String invocationURL;
    private String paymentStatus = "";
    private long transactionId;
    private long applicationId;

    private String referenceNo;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;
    private static final String PROP_FILE_NAME = "police";


    private int addressPeriodValidStatus;
    private String addressPeriodValidMessage;
    private String dateValidateJsonString;

    private String isLeftSriLanka;

    public int getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(int verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String execute() {
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

        verificationStatus = 1;
        reApplyStatus = 0;
    }

    public String loadPreviousApplication() {
        loadInitialData();

        applicationVO =
                ApplicationBusiness.getInstance().getAuthApplicationWithAddresses(applicationVO.getPreviousReferenceNo(),
                        this.getUserEmailFromSession(), PoliceEnumConstant.ApplicationAuthProvider.CU.toString());

        if (applicationVO == null && reApplyStatus == 0) {
            addActionError("No certificate was issued for this application reference number during the past 12 months.");
            return ERROR;
        } else {
            verificationStatus = 0;
            reApplyStatus = 1;

            dateOfBirth = applicationVO.getDateOfBirth();
            dateOfCitizenshipObtained = applicationVO.getCitizenshipObtainedDate();
            previousCertificateIssueDate = applicationVO.getPreviousCertificateIssueDate();
            highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(applicationVO.getCountryId());
            return SUCCESS;
        }
    }

    // public String verifyApplication(){
    // loadInitialData();
    //
    // String nicNo = (!StringUtils.isEmpty(applicationVO.getNic()))?applicationVO.getNic():"1";
    // String passportNo =
    // (!StringUtils.isEmpty(applicationVO.getPassport()))?applicationVO.getPassport():"1";
    // long country = applicationVO.getCountryId();
    //
    // //set High Commission drop down
    // // CountryVO countryVO = ApplicationBusiness.getInstance().getCountryVO(country);
    //
    //
    // highCommissionList =
    // ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(Long.parseLong(countryId));
    //
    // if(ApplicationBusiness.getInstance().verifyApplication(nicNo, passportNo, country)){
    // addActionError("Application is processing for the given criteria.");
    // verificationStatus = 1;
    // }else{
    // addActionMessage("Your details are verified, please scroll down and continue filling the remaining of your application.");
    // verificationStatus = 0;
    // }
    //
    // return SUCCESS;
    // }

    public String verifyApplication() {
        loadInitialData();

        String nicNo = StringUtils.trim(applicationVO.getNic());
        String nicNewNo = StringUtils.trim(applicationVO.getNewNic());
        String passportNo = StringUtils.trim(applicationVO.getPassport());
        long country = applicationVO.getCountryId();


        highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(Long.parseLong(countryId));

        String verificationSatus = ApplicationBusiness.getInstance().verifyApplication(nicNo,nicNewNo, passportNo, country);

        if (StringUtils.equals(verificationSatus, "APPLICATION_AVAILABLE_FOR_PASSPORT_AND_COUNTRY")) {
            addActionError("Application is processing for the given criteria.");
            verificationStatus = 2;
        } else if (StringUtils.equals(verificationSatus, "APPLICATION_AVAILABLE_FOR_NIC_PASSPORT_AND_COUNTRY") ) {
            addActionError("Application is processing for the given criteria.");
            verificationStatus = 1;
        } else if (StringUtils.equals(verificationSatus, "APPLICATION_NOT_AVAILABLE_FOR_NIC_PASSPORT_AND_COUNTRY") ) {
            addActionMessage("Your details are verified, please scroll down and continue filling the remaining of your application.");
            verificationStatus = 0;
        } else {
            addActionError("Internal Error Occurred, please contact system administrator.");
            verificationStatus = 1;
        }

        return SUCCESS;
    }

    public String loadCommisionerVOList() {
        highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(Long.parseLong(countryId));
        return SUCCESS;
    }

    public String loadCommissionVO() {
        commissionerVO = ApplicationBusiness.getInstance().getCommissionerVOById(Long.parseLong(commissionerId));
        return SUCCESS;
    }

    public String saveApplication() {

        try {

            if (reApplyStatus == 1) {

                String nicNo = StringUtils.trim(applicationVO.getNic());
                String newNicNo = StringUtils.trim(applicationVO.getNewNic());
                String passportNo = StringUtils.trim(applicationVO.getPassport());
                long country = applicationVO.getCountryId();

                String verificationSatus = ApplicationBusiness.getInstance().verifyApplication(nicNo,newNicNo, passportNo, country);
                
                ApplicationVO previousApplicationVO = new ApplicationVO();
                previousApplicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNumber(applicationVO.getReferenceNo());
                List<AddressVO> previousAddressList = ApplicationBusiness.getInstance().getAddressListByApplicationId(previousApplicationVO.getApplicationId());
                
                for (AddressVO prevAdressVO : previousAddressList) {
                	prevAdressVO.setAddressId(0);
                }
                
                if (StringUtils.equals(verificationSatus, "APPLICATION_AVAILABLE_FOR_PASSPORT_AND_COUNTRY")) {
                    addActionError("Application is processing for the given criteria.");
                    verificationStatus = 2;
                    return ERROR;
                } else if (StringUtils.equals(verificationSatus, "APPLICATION_AVAILABLE_FOR_NIC_PASSPORT_AND_COUNTRY")) {
                    addActionError("Application is processing for the given criteria.");
                    verificationStatus = 1;
                    return ERROR;
                }

                applicationVO.setPreviousReferenceNo(applicationVO.getReferenceNo());
                
                applicationVO.setPolStatus(previousApplicationVO.getPolStatus());
                applicationVO.setCidStatus(previousApplicationVO.getCidStatus());
                applicationVO.setTidStatus(previousApplicationVO.getTidStatus());
                applicationVO.setSisStatus(previousApplicationVO.getSisStatus());
                applicationVO.setNicStatus(previousApplicationVO.getNicStatus());
                applicationVO.setImiStatus(previousApplicationVO.getImiStatus());
                
                applicationVO.setApplicantCertificateAddresses(previousAddressList);
            } else {    
            	applicationVO.setApplicantCertificateAddresses(certificateAddressList);
            	/*if (StringUtils.isEmpty(applicationVO.getNic()) && StringUtils.isEmpty(applicationVO.getNewNic()))  {
                    applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.AP.toString());
                }else {
                    applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
                } */           	
            }

            applicationReferenceNumber =
                    SequenceNumberBusiness.getInstance().getNextReferenceNumberForApplicationReference(
                            PoliceEnumConstant.SequenceNumbers.APPLICATION_REFERENCE_NUMBER.getCode(),
                            applicationVO.getDeliveryType());
            applicationVO.setReferenceNo(applicationReferenceNumber);            

            applicationVO.setUpdatedUserId(this.getUserIdFromSession());
            applicationVO.setUpdatedUserName(this.getUserNameFromSession());
            applicationVO.setUpdatedDateTime(new Date());

            applicationVO.setApplicationType(PoliceEnumConstant.ApplicationType.MA.toString());

            applicationVO.setUserFullName(this.getUserNameFromSession());
            applicationVO.setUserEmail(this.getUserEmailFromSession());
            applicationVO.setAuthProvider(PoliceEnumConstant.ApplicationAuthProvider.CU.toString());

            applicationVO.setDateOfBirth(dateOfBirth);
            applicationVO.setPreviousCertificateIssueDate(previousCertificateIssueDate);
            applicationVO.setCitizenshipObtainedDate(dateOfCitizenshipObtained);
            applicationVO.setPassportIssueDate(passportIssueDate);

            HttpServletRequest request = ServletActionContext.getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            applicationVO.setIpAddress(ipAddress);

            applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.TM.toString());

            if (reApplyStatus == 1) {
                applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.EI.toString());
                applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());

            } else {
                applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
                applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.NW.toString());
            }           

            applicationId = ApplicationBusiness.getInstance().addApplication(applicationVO);

            // save transaction VO
            serviceFee = 0.00;
            postageFee = 0.00;
            applicationFee = 0.00;
            if (applicationVO.getCertificatePostAddressCountry() == 209) {
                applicationFee = 1500.00;
            } else {
                applicationFee = 1500.00;
            }
            paymentFee = serviceFee + postageFee + applicationFee;
            referenceNumber =
                    SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(
                            PoliceEnumConstant.SequenceNumbers.PAYMENT_REFERENCE_NUMBER.getCode());

            // save in transaction table
            TransactionVO newTransactionVO = new TransactionVO();
            newTransactionVO.setTransactionReferenceNo(referenceNumber);
            newTransactionVO.setChequeNo(transactionVO.getChequeNo());
            newTransactionVO.setAccountNo(transactionVO.getAccountNo());
            newTransactionVO.setAccountHolderName(transactionVO.getAccountHolderName());
            newTransactionVO.setBookReceiptNo(transactionVO.getBookReceiptNo());
            newTransactionVO.setDescription(transactionVO.getDescription());

            newTransactionVO.setApplicationFee(BigDecimal.valueOf(applicationFee));
            newTransactionVO.setTotalFee(BigDecimal.valueOf(paymentFee));

            try {
                newTransactionVO.setApplicationFee(transactionVO.getTotalFee());
                newTransactionVO.setTotalFee(transactionVO.getTotalFee());
            } catch (Exception e) {
                e.printStackTrace();
            }

            newTransactionVO.setServiceFee(BigDecimal.valueOf(0));
            newTransactionVO.setPostageFee(BigDecimal.valueOf(postageFee));
            newTransactionVO.setConvenienceFee(BigDecimal.valueOf(convenienceFee));
            newTransactionVO.setPaymentDate(new Date());
            newTransactionVO.setPaymentStatus(PoliceEnumConstant.PaymentStatus.MCNF.toString());
            newTransactionVO.setPaymentGatewayName(null);
            newTransactionVO.setPaymentInitiatedTime(new Date());
            newTransactionVO.setPaymentConfirmedTime(new Date());
            newTransactionVO.setPaymentType(null);

            newTransactionVO.setPaymentMode(transactionVO.getPaymentMode());
            newTransactionVO.setCreatedDate(new Date());
            newTransactionVO.setApplicationId(applicationId);
            newTransactionVO.setUserFullName(this.getUserNameFromSession());
            newTransactionVO.setUserEmail(this.getUserEmailFromSession());
            newTransactionVO.setAuthProvider(PoliceEnumConstant.ApplicationAuthProvider.CU.toString());

            transactionId = TransactionBussiness.getInstance().addTransaction(newTransactionVO);

            ApplicationVO existApplicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            existApplicationVO.setTransactionId(transactionId);
            boolean result = ApplicationBusiness.getInstance().updateCompletedApplication(existApplicationVO);

            session.put(PoliceConstant.SESSION_TRANSACTION_ID, transactionId);
            session.put(PoliceConstant.SESSION_APPLICATION_ID, applicationVO.getApplicationId());


            if (applicationId > 0 && transactionId > 0 && result == true) {
                addActionMessage("Application Successfully saved.");
                return SUCCESS;
            } else {
                addActionError("Error occurred...!!!");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String validateDateRange() {
        System.out.println("addressPeriodValidStatus " + addressPeriodValidStatus);
        System.out.println("dateValidateJsonString :" + dateValidateJsonString);
        addressPeriodValidStatus = 0;

        Map<String, Object> map = DateRangeValidator.validateAddressdateRange(dateValidateJsonString);

        boolean isValidDateRange = (Boolean) map.get("VALIDITY");

        if (isValidDateRange) {
            addressPeriodValidStatus = 1;
            addressPeriodValidMessage = "Successful!";
        } else {
            addressPeriodValidMessage = (String) map.get("MESSAGE");
            ;
        }
        return SUCCESS;
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

    public String uploadFile() {
        File fileToCreate = null;
        String generatedfileName = null;

        // String uploadPath=PoliceConstant.NIC_PASSPORT_FILE_UPLOADED_LOCATION;
        String uploadPath = CommonUtil.getValueFromFile(PROP_FILE_NAME, "police.nic.passport.file.upload.location");
        if (!(new File(uploadPath).exists())) {
            new File(uploadPath).mkdirs();
        }

        PoliceEnumConstant.UploadedFileType uploadedFileType = PoliceEnumConstant.UploadedFileType.fromCode(fileType);
        if (!(fileType == null)) {
            generatedfileName = generateUniqueName(uploadedFileType, uploadType);
            fileName = generatedfileName + "." + fileExtension;
            fileToCreate = new File(uploadPath, fileName);
            try {
                // copyFileUsingFileChannel(this.nicFile, nicFileToCreate);
                FileUtil.encryptFile(new FileInputStream(this.file), new FileOutputStream(fileToCreate));
                this.fileCategory = CommonUtil.getFileTypeFromExtension("." + fileExtension);
            } catch (IOException e) {
                fileName = null;
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    private String generateUniqueName(PoliceEnumConstant.UploadedFileType uploadedFileType, String uplaodType) {
        String uniqueFolderName = uplaodType + "_" + uploadedFileType.toString() + System.currentTimeMillis();
        return uniqueFolderName;
    }

    // private static void copyFileUsingFileChannel(File inputFile,File outputFile) throws IOException
    // {
    // FileChannel source = null;
    // FileChannel destination = null;
    //
    // try {
    // source = new FileInputStream(inputFile).getChannel();
    // destination = new FileOutputStream(outputFile).getChannel();
    // ByteBuffer buf = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    // while((source.read(buf)) != -1) {
    // buf.flip();
    // destination.write(buf);
    // buf.clear();
    // }
    // } finally {
    // if (source != null) {
    // source.close();
    // }
    // if (destination != null) {
    // destination.close();
    // }
    // }
    // }

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

    public void setApplicationPurposeMap(Map<String, ApplicationPurpose> applicationPurposeMap) {
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

    public TransactionVO getTransactionVO() {
        return transactionVO;
    }

    public void setTransactionVO(TransactionVO transactionVO) {
        this.transactionVO = transactionVO;
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

    public int getReApplyStatus() {
        return reApplyStatus;
    }

    public void setReApplyStatus(int reApplyStatus) {
        this.reApplyStatus = reApplyStatus;
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

    public String getPaymentFeeString() {
        return paymentFeeString;
    }

    public void setPaymentFeeString(String paymentFeeString) {
        this.paymentFeeString = paymentFeeString;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getPostageFee() {
        return postageFee;
    }

    public void setPostageFee(double postageFee) {
        this.postageFee = postageFee;
    }

    public double getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(double applicationFee) {
        this.applicationFee = applicationFee;
    }

    public double getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(double convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getApplicationReferenceNumber() {
        return applicationReferenceNumber;
    }

    public void setApplicationReferenceNumber(String applicationReferenceNumber) {
        this.applicationReferenceNumber = applicationReferenceNumber;
    }

    public String getEnPaymentRequest() {
        return enPaymentRequest;
    }

    public void setEnPaymentRequest(String enPaymentRequest) {
        this.enPaymentRequest = enPaymentRequest;
    }

    public String getInvocationURL() {
        return invocationURL;
    }

    public void setInvocationURL(String invocationURL) {
        this.invocationURL = invocationURL;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
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

    public int getAddressPeriodValidStatus() {
        return addressPeriodValidStatus;
    }

    public void setAddressPeriodValidStatus(int addressPeriodValidStatus) {
        this.addressPeriodValidStatus = addressPeriodValidStatus;
    }

    public String getAddressPeriodValidMessage() {
        return addressPeriodValidMessage;
    }

    public void setAddressPeriodValidMessage(String addressPeriodValidMessage) {
        this.addressPeriodValidMessage = addressPeriodValidMessage;
    }


    public String getDateValidateJsonString() {
        return dateValidateJsonString;
    }

    public void setDateValidateJsonString(String dateValidateJsonString) {
        this.dateValidateJsonString = dateValidateJsonString;
    }

    public String getIsLeftSriLanka() {
        return isLeftSriLanka;
    }

    public void setIsLeftSriLanka(String isLeftSriLanka) {
        this.isLeftSriLanka = isLeftSriLanka;
    }
}
