package lk.icta.police.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.lgps.lgpsclient.LGPSClient;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicantStatus;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationPurpose;
import lk.icta.police.framework.constant.PoliceEnumConstant.HandOverPerson;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.*;
import lk.icta.police.web.app.constant.ApplicationConstants;
import lk.icta.police.web.business.*;
import lk.icta.police.web.oauth.util.SessionConstants;
import lk.icta.police.web.servlet.CaptchaGeneratorServlet;
import lk.icta.police.web.util.DateRangeValidator;
import lk.icta.police.web.util.FileUtil;
import lk.icta.police.web.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.rmi.RemoteException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApplicationAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = -8517227229292215672L;
    private static final Logger LOGGER = Logger.getLogger(HomeAction.class);

    private Map<String, Object> session;

    private List<CountryVO> countryList;
    private List<CommissionerVO> highCommissionList;
    private List<PoliceAreaVO> policeAreaList;
    private Map<String, ApplicationPurpose> applicationPurposeMap;
    private Map<String, ApplicantStatus> applicantStatus;
    private Map<String, HandOverPerson> handOverPersons;

    private ApplicationVO applicationVO;
    private TransactionVO transactionVO;
    private CommissionerVO commissionerVO;
    private List<AddressVO> certificateAddressList;

    private int verificationStatus;
    private int captchaStatus;
    private int reApplyStatus;

    private String countryId;
    private String commissionerId;

    // NICF("NIC Front Side"), NICB("NIC Back Side"), PASF("Passport Front Side"),
    // PASB("Passport Back Side"),
    // BIRF("Birth certificate Front Side"), BIRB("Birth certificate Back Side"),
    // LETR("Letter Of reference")
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

    private String checkAuthenticationStatus;
    private String clientRequestType;

    private String uword;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;
    private static final String PROP_FILE_NAME = "police";

    private int addressPeriodValidStatus;
    private String addressPeriodValidMessage;
    private String dateValidateJsonString;

    private String isLeftSriLanka;

    private String serverDate;

    private String previousApplicationViewMessage;

    public String execute() {
    	LOGGER.info("----------------------LOGGER INFO----------------------------");
    	LOGGER.error("-----------------LOGGER error-----------------------------");
    	LOGGER.debug("----------------------LOGGER debug----------------");
    	System.out.println("-------------------sys out--------------------");
    	
    	
        loadInitialData();

        return SUCCESS;
    }

    public String checkAuthentication() {
        session.put(ApplicationConstants.CLIENT_REQUEST_TYPE, clientRequestType);
        LOGGER.info("clientRequestType :" + clientRequestType);

        // to test the citizen application
//       session.put(ApplicationConstants.EMAIL, "praveeni.fernando@pwc.com");
//       session.put(ApplicationConstants.USER_NAME, "Do_Not_Submit_Error");

        boolean isUserLoggedIn = checkIfUserLoggedIn();
        checkAuthenticationStatus = "NO";
        if (isUserLoggedIn) {
            checkAuthenticationStatus = "YES";
        }
        return SUCCESS;
    }

    private boolean checkIfUserLoggedIn() {
        System.out.println("session.get(ApplicationConstants.EMAIL) :k" + session.get(ApplicationConstants.EMAIL));
        if (!(StringUtils.isEmpty((String) session.get(ApplicationConstants.EMAIL)) || StringUtils.isEmpty((String) session
                .get(ApplicationConstants.USER_NAME)))) {
            return true;
        }
        return false;
    }

    private void loadInitialData() {
        countryList = ApplicationBusiness.getInstance().getCountryList();
        policeAreaList = ApplicationBusiness.getInstance().getPoliceAreaList();
        applicationPurposeMap = PoliceEnumConstant.ApplicationPurpose.getApplicationPurposeMap();
        applicantStatus = PoliceEnumConstant.ApplicantStatus.getApplicantStatusMap();
        handOverPersons = PoliceEnumConstant.HandOverPerson.getHandOverPersonMap();
        verificationStatus = 1;
        reApplyStatus = 0;

    }

    public String loadPreviousApplication() {
        loadInitialData();

        applicationVO =
                ApplicationBusiness.getInstance().getAuthApplicationWithAddresses(applicationVO.getPreviousReferenceNo(),
                        getUserEmail(), getAuthProvider());

        if (applicationVO == null && reApplyStatus == 0) {
            addActionError("No certificate was issued for this application reference number during the past 12 months.");
            return ERROR;
        } else {
            verificationStatus = 0;
            reApplyStatus = 1;

            dateOfBirth = applicationVO.getDateOfBirth();
            passportIssueDate = applicationVO.getPassportIssueDate();
            dateOfCitizenshipObtained = applicationVO.getCitizenshipObtainedDate();
            previousCertificateIssueDate = applicationVO.getPreviousCertificateIssueDate();
            highCommissionList = ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(applicationVO.getCountryId());
            return SUCCESS;
        }
    }

    public String verifyApplication() {
        loadInitialData();

        try {
            // captcha
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession(false);
            String captchaText = (String) session.getAttribute(CaptchaGeneratorServlet.CAPTCHA_KEY);
            if (!getUword().equals(captchaText)) {
                addActionError("Please enter the text verification correctly.");
                uword = "";
                captchaStatus = 1;
                return ERROR;
            } else {

                String nicNo = (!StringUtils.isEmpty(applicationVO.getNic())) ? applicationVO.getNic() : "1";
                String newNicNo = (!StringUtils.isEmpty(applicationVO.getNewNic())) ? applicationVO.getNewNic() : "1";
                String passportNo = (!StringUtils.isEmpty(applicationVO.getPassport())) ? applicationVO.getPassport() : "1";
                long country = applicationVO.getCountryId();

                // set High Commission drop down
                // CountryVO countryVO = ApplicationBusiness.getInstance().getCountryVO(country);
                highCommissionList =
                        ApplicationBusiness.getInstance().getCommissionerVOListByCountryId(Long.parseLong(countryId));

                if (ApplicationBusiness.getInstance().verifyApplication(nicNo, newNicNo, passportNo, country)) {
                    addActionError("Valid certificate already available for the same criteria");
                    verificationStatus = 1;
                } else {
                    addActionMessage("Your details are verified, please scroll down and continue filling the remaining of your application.");
                    verificationStatus = 0;
                }

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                serverDate = df.format(new Date());

                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
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

    public String saveApplication() {

        try {
            System.out.println("reapply status----->>>" + reApplyStatus);
            if (reApplyStatus == 1) {

                String nicNo = StringUtils.trim(applicationVO.getNic());
                String newNicNo = StringUtils.trim(applicationVO.getNewNic());
                String passportNo = StringUtils.trim(applicationVO.getPassport());
                long country = applicationVO.getCountryId();

                ApplicationVO previousApplicationVO = new ApplicationVO();
                previousApplicationVO = ApplicationBusiness.getInstance().getApplicationByReferenceNumber(applicationVO.getReferenceNo());
                
                boolean verificationSatus = ApplicationBusiness.getInstance().verifyApplication(nicNo, newNicNo, passportNo, country);

                if (verificationSatus) {
                    addActionError("Valid certificate already available for the same criteria");
                    verificationStatus = 2;
                    return ERROR;
                }

                applicationVO.setPreviousReferenceNo(applicationVO.getReferenceNo());
                
                applicationVO.setPolStatus(previousApplicationVO.getPolStatus());
                applicationVO.setCidStatus(previousApplicationVO.getCidStatus());
                applicationVO.setTidStatus(previousApplicationVO.getTidStatus());
                applicationVO.setSisStatus(previousApplicationVO.getSisStatus());
                applicationVO.setNicStatus(previousApplicationVO.getNicStatus());
                applicationVO.setImiStatus(previousApplicationVO.getImiStatus());
            }
            applicationVO.setReferenceNo(null);

            applicationVO.setApplicantCertificateAddresses(certificateAddressList);

            applicationVO.setUpdatedUserId(1);
            applicationVO.setUpdatedUserName("username");
            applicationVO.setUpdatedDateTime(new Date());

            applicationVO.setApplicationType(PoliceEnumConstant.ApplicationType.ON.toString());

            applicationVO.setUserFullName(this.getUserFullName());
            applicationVO.setUserEmail(this.getUserEmail());
            applicationVO.setAuthProvider(this.getAuthProvider());

            applicationVO.setDateOfBirth(dateOfBirth);
            applicationVO.setPreviousCertificateIssueDate(previousCertificateIssueDate);
            applicationVO.setCitizenshipObtainedDate(dateOfCitizenshipObtained);
            applicationVO.setPassportIssueDate(passportIssueDate);

            HttpServletRequest request = ServletActionContext.getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            System.out.println("ipaddress--->>" + ipAddress);
            if (StringUtils.isEmpty(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            applicationVO.setIpAddress(ipAddress);

            applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.TM.toString());

            if (reApplyStatus == 1) {
                applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.EI.toString());
            } else {
                applicationVO.setApplicationClearanceStatus(PoliceEnumConstant.ApplicationClearenceStatus.PN.toString());
            }

            /*if (StringUtils.isEmpty(applicationVO.getNic()) && StringUtils.isEmpty(applicationVO.getNewNic())) {
                applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.AP.toString());
            } else {
                applicationVO.setNicStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
            }*/

            // set delivery type
            if (applicationVO.getReferredThroughBereau() == 1) {
                applicationVO.setDeliveryType(PoliceEnumConstant.DeliveryType.SB.toString());
            } else {
                applicationVO.setDeliveryType(PoliceEnumConstant.DeliveryType.NP.toString());
            }
            System.out.println("------applicationVO.getDeliveryType()----" + applicationVO.getDeliveryType());

            applicationId = ApplicationBusiness.getInstance().addApplication(applicationVO);

            // save transaction VO
            serviceFee = 0.00;
            postageFee = 0.00;
            applicationFee = 0.00;
            if (applicationVO.getCertificatePostAddressCountry() == 199) {// sri lanka
                applicationFee = 5000.00;
            } else {
                applicationFee = 5000.00;
            }
            paymentFee = serviceFee + postageFee + applicationFee;
            referenceNumber =
                    SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(
                            PoliceEnumConstant.SequenceNumbers.PAYMENT_REFERENCE_NUMBER.getCode());

            // save in transaction table
            TransactionVO transactionVO = new TransactionVO();
            transactionVO.setTransactionReferenceNo(referenceNumber);
            transactionVO.setDescription("New application payment");
            transactionVO.setApplicationFee(BigDecimal.valueOf(applicationFee));
            transactionVO.setServiceFee(BigDecimal.valueOf(0));
            transactionVO.setPostageFee(BigDecimal.valueOf(postageFee));
            transactionVO.setConvenienceFee(BigDecimal.valueOf(convenienceFee));
            transactionVO.setTotalFee(BigDecimal.valueOf(paymentFee));
            transactionVO.setPaymentDate(new Date());
            transactionVO.setPaymentStatus(PoliceEnumConstant.PaymentStatus.PEND.toString());
            transactionVO.setPaymentGatewayName(null);
            transactionVO.setPaymentInitiatedTime(new Date());
            transactionVO.setPaymentConfirmedTime(null);
            transactionVO.setPaymentType(null);
            transactionVO.setPaymentMode(PoliceEnumConstant.PaymentMode.ONLN.toString());
            transactionVO.setCreatedDate(new Date());
            transactionVO.setApplicationId(applicationId);
            transactionVO.setUserFullName(this.getUserFullName());
            transactionVO.setUserEmail(this.getUserEmail());
            transactionVO.setAuthProvider(this.getAuthProvider());

            transactionId = TransactionBussiness.getInstance().addTransaction(transactionVO);
            System.out.println("transactionId----add----" + transactionId);

            session.put(PoliceConstant.SESSION_TRANSACTION_ID, transactionId);
            session.put(PoliceConstant.SESSION_APPLICATION_ID, applicationVO.getApplicationId());

            if (applicationId > 0 && transactionId > 0) {
                applicationVO.setApplicationId(applicationId);
                constructConfirmPaymentPage();
                System.out.println("PAYMENT CONFIRMATION PAGE IS CONSTRUCTING ---");
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

    public String viewApplication() {

        try {
            countryList = ApplicationBusiness.getInstance().getCountryList();
            applicantStatus = PoliceEnumConstant.ApplicantStatus.getApplicantStatusMap();
            applicationPurposeMap = PoliceEnumConstant.ApplicationPurpose.getApplicationPurposeMap();
            handOverPersons = PoliceEnumConstant.HandOverPerson.getHandOverPersonMap();

            applicationVO = ApplicationBusiness.getInstance().getApplicationWithAddresses(referenceNo, getUserEmail());

            if (applicationVO == null){
                previousApplicationViewMessage = "First time view only";
            }
            return SUCCESS;
        } catch (Exception e) {

            return ERROR;
        }

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
                System.out.println("fileCategory ++ :" + fileCategory);
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

    /**
     * To populate payment page after registration
     */
    private void constructConfirmPaymentPage() {

        String invokationURL = "";
        String serviceCode = ""; // Service code of this merchant application
        // String keyPath = "";
        String deploymentMode = CommonUtil.getValueFromFile("payment", "POLICE.DEPLOYMENT_MODE");
    /* totalFeeTest will be used when the deploymentMode is "Test" */
        // String totalFeeTest =
        // CommonUtil.getValueFromFile("payment","POLICE.PAYMENT.TRANSACTION_AMOUNT.TEST");
        String returnURLPart2 = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.RETURN_URL");

        String keyStorePath = "";
        String keyStorePasswd = "";
        String trustStorePath = "";
        String trustStorePasswd = "";
        String clientKeyAlias = "";
        String lgpsPubKeyAlias = "";

        DecimalFormat df = new DecimalFormat("#.00");
        HttpServletRequest request = ServletActionContext.getRequest();
        String returnURLPart1 = request.getHeader("referer");
        String returnURL = returnURLPart2;
        returnURL = returnURLPart1.substring(0, returnURLPart1.lastIndexOf("/") + 1) + returnURLPart2;

        paymentFeeString = Double.toString(Double.valueOf(df.format(paymentFee)));

        LOGGER.info("deploymentMode :" + deploymentMode);
        LOGGER.info("returnURLPart2 :" + returnURLPart2);

        if (deploymentMode.equals("Live")) {
            keyStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPATH.LIVE");
            keyStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPWRD.LIVE");
            trustStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPATH.LIVE");
            trustStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPWRD.LIVE");
            clientKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.CLIENTKEYALIAS.LIVE");
            lgpsPubKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.LGPSPUBKEYALIAS.LIVE");

            invokationURL = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.INVOKATION_URL_PROD");

            serviceCode = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.SERVICE_CODE.LIVE");

        } else {
            keyStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPATH.TEST");
            keyStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPWRD.TEST");
            trustStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPATH.TEST");
            trustStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPWRD.TEST");
            clientKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.CLIENTKEYALIAS.TEST");
            lgpsPubKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.LGPSPUBKEYALIAS.TEST");

            // paymentFeeString = totalFeeTest;
            // totalFeeString = Double.toString(Double.valueOf(df.format(totalFee)));
            invokationURL = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.INVOKATION_URL_STAGING");
            serviceCode = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.SERVICE_CODE.TEST");
        }

        LOGGER.info("PAYMENT referenceNumber :" + referenceNumber);
        LOGGER.info("PAYMENT paymentFeeString :" + paymentFeeString);

        LGPSClient lgpsClient = new LGPSClient(); // initiates LGPS client Object
        lgpsClient.setClientKeyStore(keyStorePath, keyStorePasswd); // Set keystore paths and password
        // to access client keystore
        lgpsClient.setClientTrustStore(trustStorePath, trustStorePasswd); // Set truststore and password
        // to access truststore
        lgpsClient.setClientKeyAlias(clientKeyAlias); // Set key alias to access client key
        lgpsClient.setLgpsPubKeyAlias(lgpsPubKeyAlias);// Set alias to access lgps public key

        lgpsClient.setServiceCode(serviceCode); // Set the Service Code
        lgpsClient.setTransactionAmount(paymentFeeString); // Set the transaction amount
        lgpsClient.setTransactionRefNo(referenceNumber); // Set the transaction ref. no.

        lgpsClient.setReturnURL(returnURL); // Set the return URL

        try {
            enPaymentRequest = lgpsClient.getPaymentRequest();// Generate encrypted request
        } catch (Exception e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        }

        setInvocationURL(invokationURL);
    }


    /**
     * To call return action from IPG and populate payment confirmation
     *
     * @throws GetPaymentSavedDetailsFaultException
     * @throws SavePaymentDetailsFaultException
     * @throws GetPaymentInfoFaultException
     * @throws RemoteException
     */
    public String retrieveReturnPaymentConfirmation() throws RemoteException {
    	LOGGER.info("----------INSIDE retrieveReturnPaymentConfirmation METHOD------");
        
    	applicationId = (Long) session.get(PoliceConstant.SESSION_APPLICATION_ID);
        transactionId = (Long) session.get(PoliceConstant.SESSION_TRANSACTION_ID);
       
        LOGGER.info("------applicationId: " + applicationId);
        LOGGER.info("---------transactionId: " + transactionId);

        String resultString = SUCCESS;

        String deploymentMode = CommonUtil.getValueFromFile("payment", "POLICE.DEPLOYMENT_MODE");
        LOGGER.info("------deploymentMode: " + deploymentMode);
        String theTransactionStatus = "";
        String keyStorePath = "";
        String keyStorePasswd = "";
        String trustStorePath = "";
        String trustStorePasswd = "";
        String clientKeyAlias = "";
        String lgpsPubKeyAlias = "";

        // Initialize lgpsClient object
        // Configure values to access client keystore,truststore and key aliases
        try {

            if (deploymentMode.equals("Live")) {
                keyStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPATH.LIVE");
                keyStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPWRD.LIVE");
                trustStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPATH.LIVE");
                trustStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPWRD.LIVE");
                clientKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.CLIENTKEYALIAS.LIVE");
                lgpsPubKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.LGPSPUBKEYALIAS.LIVE");
            } else {
                keyStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPATH.TEST");
                keyStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.KEYPWRD.TEST");
                trustStorePath = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPATH.TEST");
                trustStorePasswd = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.TRUSTPWRD.TEST");
                clientKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.CLIENTKEYALIAS.TEST");
                lgpsPubKeyAlias = CommonUtil.getValueFromFile("payment", "POLICE.PAYMENT.LGPSPUBKEYALIAS.TEST");
            }

            LGPSClient lgpsClientRes = new LGPSClient(); // initiates LGPS client Object
            HttpServletRequest request = ServletActionContext.getRequest();
            String paymentResponse = request.getParameter("lgpsPaymentResponse");// Extract encrypted
            // payment response sent by LGPS request object
         
            lgpsClientRes.setClientKeyStore(keyStorePath, keyStorePasswd);
            lgpsClientRes.setClientTrustStore(trustStorePath, trustStorePasswd);
            lgpsClientRes.setClientKeyAlias(clientKeyAlias);
            lgpsClientRes.setLgpsPubKeyAlias(lgpsPubKeyAlias);

            lgpsClientRes.setPaymentResponse(paymentResponse);// Set encrypted payment response to
            // lgpsClient object

            String transactionRefNo = lgpsClientRes.getResponseTransactionRefNo(); // Retrieve transaction
            // ref. no.
            boolean transactionStatus = lgpsClientRes.getResponseTransactionStatus(); // Retrieve
            // transaction
            // status
            String paymentGatewayName = lgpsClientRes.getResponsePgName(); // Retrieve payment gateway
            // name
            String convenienceFee = lgpsClientRes.getConvenienceFee();// Retrieve convenience fee of
            // payment gateway name

            LOGGER.info("------transactionRefNo---------" + transactionRefNo);
            LOGGER.info("------transactionStatus---------" + transactionStatus);
            LOGGER.info("------paymentGatewayName---------" + paymentGatewayName);
            LOGGER.info("------convenienceFee---------" + convenienceFee);


            boolean emailSentStatus = false;

            ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);
            System.out.println(applicationVO);

            // save transaction table
            TransactionVO transactionVO = TransactionBussiness.getInstance().getTransaction(transactionId);
            System.out.println(transactionVO);

            if (transactionStatus) {
                transactionVO.setPaymentStatus(PoliceEnumConstant.PaymentStatus.OCNF.toString());
                transactionVO.setPaymentGatewayName(paymentGatewayName);
                transactionVO.setConvenienceFee(new BigDecimal(convenienceFee, MathContext.DECIMAL64));
                transactionVO.setPaymentConfirmedTime(Calendar.getInstance().getTime());
                transactionVO.setTotalFee(transactionVO.getPostageFee().add(
                        transactionVO.getApplicationFee().add(transactionVO.getConvenienceFee())));

                // generate application reference number
                try {
                    applicationReferenceNumber =
                            SequenceNumberBusiness.getInstance().getNextReferenceNumberForApplicationReference(
                                    PoliceEnumConstant.SequenceNumbers.APPLICATION_REFERENCE_NUMBER.getCode(),
                                    applicationVO.getDeliveryType());
                    applicationVO.setReferenceNo(applicationReferenceNumber);
                    if (StringUtils.isNotEmpty(applicationVO.getPreviousReferenceNo())) {
                        applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.VF.toString());
                    } else {
                        applicationVO.setApplicationReviewStatus(PoliceEnumConstant.ApplicationReviewStatus.NW.toString());
                    }
                    applicationVO.setTransactionId(transactionId);
                    boolean result = ApplicationBusiness.getInstance().updateCompletedApplication(applicationVO);
                } catch (Exception e) {
                    LOGGER.error(e);
                    e.printStackTrace();
                }

                LOGGER.info("GENERATED APPLICATION REFERENCE NUMBER FOR CITIZEN AFTER PAYMENT: "
                        + applicationReferenceNumber);

                // send email
                try {
                    EmailNotificationBusiness.getInstance().sendPaymentSuccessMail(applicationVO, transactionVO);
                    emailSentStatus = true;
                } catch (BusinessException e) {
                    e.printStackTrace();
                }

                LOGGER.info("EmailSentStatus: " + emailSentStatus);

                String smsSentStatus = null;
                // send sms
                try {
                    smsSentStatus =
                            SMSNotificationBusiness.getInstance().sendPaymentSuccessConfirmationSMS(applicationVO,
                                    transactionVO.getTotalFee());
                } catch (Exception e) {
                    LOGGER.error(e);
                    e.printStackTrace();
                }

                LOGGER.info("SMSSentStatus: " + smsSentStatus);

                String res = printDailyTransactionReport(applicationVO, transactionVO);

                LOGGER.info("DAILY TRANSACTION REPORT PRINT STATUS: " + res);

                theTransactionStatus = "SUCCESFUL";
            } else {
                transactionVO.setPaymentStatus(PoliceEnumConstant.PaymentStatus.FAIL.toString());
                theTransactionStatus = "UNSUCCESFUL";
                boolean result = TransactionBussiness.getInstance().updateTransaction(transactionVO);
                addActionError("Payment was unsuccessful! Please try again!");
                return ERROR;
            }

            if (StringUtils.isEmpty(transactionVO.getPaymentMode())) {
                transactionVO.setPaymentMode(PoliceEnumConstant.PaymentMode.ONLN.toString());
            }
            boolean result = TransactionBussiness.getInstance().updateTransaction(transactionVO);
                       
            setPaymentStatus(theTransactionStatus);
            setReferenceNumber(transactionRefNo);
            // set the required variables
            setPaymentStatus(theTransactionStatus);      

        } catch (SignatureException e) {
            e.printStackTrace();
            LOGGER.error("Signature verification failed..!!! " + e);
            addActionError("Error occurred in payment gateway, Please try again later.");
            e.printStackTrace();
        } finally {
            // clear session
            session.remove(PoliceConstant.PAYMENT_REFERENCE_NUMBER);
            session.remove(PoliceConstant.SESSION_APPLICATION_ID);
            session.remove(PoliceConstant.SESSION_TRANSACTION_ID);
        }

        LOGGER.error("PAYMENT_RESULT :" + resultString);
        session.put("PAYMENT_RESULT", resultString);

        return SUCCESS;
    }

    private String getPaymentReferenceNumber() {
        try {
            if (StringUtils.isEmpty((String) session.get(PoliceConstant.PAYMENT_REFERENCE_NUMBER))) {
                referenceNumber =
                        SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(
                                PoliceEnumConstant.SequenceNumbers.PAYMENT_REFERENCE_NUMBER.getCode());
                session.put(PoliceConstant.PAYMENT_REFERENCE_NUMBER, referenceNumber);
                return referenceNumber;
            }
            return ((String) session.get(PoliceConstant.PAYMENT_REFERENCE_NUMBER));
        } catch (Exception e) {
            return null;
        }

    }

    private String printDailyTransactionReport(ApplicationVO applicationVO, TransactionVO transactionVO) {
        String resultType = SUCCESS;
        try {

            Date reportDate = Calendar.getInstance().getTime();

            List<ApplicationVO> list = new ArrayList<ApplicationVO>();
            list.add(applicationVO);

            // 1. Add report parameters
            JRDataSource reportData = new JRBeanCollectionDataSource(list);

            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();

            params.put("TRANSACTION_REF_NO", transactionVO.getTransactionReferenceNo());
            params.put("PAYMENT_DATE", reportDate);
            params.put("APPLICATION_REF_NO", applicationVO.getReferenceNo());
            params.put("APPLICATION_FEE", transactionVO.getApplicationFee());
            params.put("SERVICE_FEE", transactionVO.getServiceFee());
            params.put("POSTAGE_FEE", transactionVO.getPostageFee());
            params.put("CONVENIENE_FEE", transactionVO.getConvenienceFee());
            params.put("TOTAL_FEE", transactionVO.getTotalFee());

            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource(PoliceConstant.HEAD_LOGO));
                params.put("LOGO_IMAGE", image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // 2. Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.POL_PAYMENT_REPORT_TEMPLATE);
            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);
            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);
            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            String fileType = PoliceConstant.FILE_TYPE_PDF;

            fileName = "tempexporterlistfile_" + System.currentTimeMillis();
            // String fullFilePath = PoliceConstant.REPORT_FILE_LOCATION;
            String fullFilePath = CommonUtil.getValueFromFile(PROP_FILE_NAME, "police.report.file.location");
            try {
                fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fullFilePath = fullFilePath + fileName;
            return resultType;
        } catch (JRException e1) {
            e1.printStackTrace();
        }
        addActionError("Could not generate the file. Internal Server Error!");
        return resultType;
    }


    public String validateDateRange() {
        System.out.println("addressPeriodValidStatus " + addressPeriodValidStatus);
        System.out.println("dateValidateJsonString :" + dateValidateJsonString);
        addressPeriodValidStatus = 0;

        System.out.println("ESCAPED dateValidateJsonString " + dateValidateJsonString);

        dateValidateJsonString = StringEscapeUtils.unescapeHtml(dateValidateJsonString);

        System.out.println("UNESCAPED dateValidateJsonString " + dateValidateJsonString);

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

    private String getUserFullName() {
        return (String) session.get(ApplicationConstants.USER_NAME);
    }

    private String getUserEmail() {
        return (String) session.get(ApplicationConstants.EMAIL);
    }

    private String getAuthProvider() {
        if ("google".equalsIgnoreCase((String) getSession().get(SessionConstants.OAUTH_PROVIDER))) {
            return PoliceEnumConstant.ApplicationAuthProvider.GM.toString();
        } else if ("facebook".equalsIgnoreCase((String) getSession().get(SessionConstants.OAUTH_PROVIDER))) {
            return PoliceEnumConstant.ApplicationAuthProvider.FB.toString();
        } else if ("linkedin".equalsIgnoreCase((String) getSession().get(SessionConstants.OAUTH_PROVIDER))) {
            return PoliceEnumConstant.ApplicationAuthProvider.LI.toString();
        } else {
            return "NO";
        }
    }

    @Override
    public void setSession(Map<String, Object> arg0) {
        this.session = arg0;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public List<CountryVO> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryVO> countryList) {
        this.countryList = countryList;
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

    public ApplicationVO getApplicationVO() {
        return applicationVO;
    }

    public void setApplicationVO(ApplicationVO applicationVO) {
        this.applicationVO = applicationVO;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @TypeConversion(converter = "lk.icta.police.web.util.StringToDateConverter")
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getPreviousCertificateIssueDate() {
        return previousCertificateIssueDate;
    }

    @TypeConversion(converter = "lk.icta.police.web.util.StringToDateConverter")
    public void setPreviousCertificateIssueDate(Date previousCertificateIssueDate) {
        this.previousCertificateIssueDate = previousCertificateIssueDate;
    }

    public Date getDateOfCitizenshipObtained() {
        return dateOfCitizenshipObtained;
    }

    @TypeConversion(converter = "lk.icta.police.web.util.StringToDateConverter")
    public void setDateOfCitizenshipObtained(Date dateOfCitizenshipObtained) {
        this.dateOfCitizenshipObtained = dateOfCitizenshipObtained;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    @TypeConversion(converter = "lk.icta.police.web.util.StringToDateConverter")
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

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getApplicationReferenceNumber() {
        return applicationReferenceNumber;
    }

    public void setApplicationReferenceNumber(String applicationReferenceNumber) {
        this.applicationReferenceNumber = applicationReferenceNumber;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TransactionVO getTransactionVO() {
        return transactionVO;
    }

    public void setTransactionVO(TransactionVO transactionVO) {
        this.transactionVO = transactionVO;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public List<CommissionerVO> getHighCommissionList() {
        return highCommissionList;
    }

    public void setHighCommissionList(List<CommissionerVO> highCommissionList) {
        this.highCommissionList = highCommissionList;
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

    public CommissionerVO getCommissionerVO() {
        return commissionerVO;
    }

    public void setCommissionerVO(CommissionerVO commissionerVO) {
        this.commissionerVO = commissionerVO;
    }

    public String getCheckAuthenticationStatus() {
        return checkAuthenticationStatus;
    }

    public void setCheckAuthenticationStatus(String checkAuthenticationStatus) {
        this.checkAuthenticationStatus = checkAuthenticationStatus;
    }

    public int getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(int verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getClientRequestType() {
        return clientRequestType;
    }

    public void setClientRequestType(String clientRequestType) {
        this.clientRequestType = clientRequestType;
    }

    public String getUword() {
        return uword;
    }

    public void setUword(String uword) {
        this.uword = uword;
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

    public int getCaptchaStatus() {
        return captchaStatus;
    }

    public void setCaptchaStatus(int captchaStatus) {
        this.captchaStatus = captchaStatus;
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

    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public String getPreviousApplicationViewMessage() {
        return previousApplicationViewMessage;
    }
}
