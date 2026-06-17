package lk.icta.police.business;

import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.RequestClarificationVO;
import lk.icta.police.util.MailUtil;

import org.apache.log4j.Logger;

public class EmailNotificationBusiness {
    private static final Logger LOGGER = Logger.getLogger(EmailNotificationBusiness.class);
    private static EmailNotificationBusiness instance = null;

    /**
     * Singleton Implementation
     */
    public static EmailNotificationBusiness getInstance() {
        synchronized (EmailNotificationBusiness.class) {
            if (instance == null) {
                instance = new EmailNotificationBusiness();
            }
            return instance;
        }
    }


    //EMAIL_SUBJECT,MAIL_TITLE,RECEIVER_NAME,BODY_TEXT
    private String sendEmailMessage(String emailTitle, String emailSubject, String message, String recieverEmailAddress, String recieverName) throws Exception {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(PoliceConstant.TITLE, emailTitle);
        valueMap.put(PoliceConstant.EMAIL_SUBJECT, emailSubject);
        valueMap.put(PoliceConstant.MAIL_TITLE, emailTitle);
        valueMap.put(PoliceConstant.RECEIVER_NAME, recieverName);
        valueMap.put(PoliceConstant.BODY_TEXT, message);
        MailUtil mailUtil = new MailUtil();
        boolean status = mailUtil.sendMail(valueMap, recieverEmailAddress, recieverName);
        if (status) {
            return PoliceConstant.SUCCESS;
        }
        return PoliceConstant.ERROR;
    }


    //EMAIL_SUBJECT,MAIL_TITLE,RECEIVER_NAME,BODY_TEXT
    private String sendEmailMessageEmptyTemplate(String emailTitle, String emailSubject, String message, String recieverEmailAddress, String recieverName) throws Exception {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(PoliceConstant.TITLE, emailTitle);
        valueMap.put(PoliceConstant.MAIL_TITLE, emailTitle);
        valueMap.put(PoliceConstant.EMAIL_SUBJECT, emailSubject);
        valueMap.put(PoliceConstant.RECEIVER_NAME, recieverName);
        valueMap.put(PoliceConstant.BODY_TEXT, message);
        MailUtil mailUtil = new MailUtil();
        boolean status = mailUtil.sendMailFromEmptyTemplate(valueMap, recieverEmailAddress, recieverName);
        if (status) {
            return PoliceConstant.SUCCESS;
        }
        return PoliceConstant.ERROR;
    }

    public String sendApplicationAcceptedMail(ApplicationVO applicationVO) throws BusinessException {
        String ststus = PoliceConstant.ERROR;
        try {

            String messageBody = "You application accpted";

            ststus = sendEmailMessage("Department of Police",
                    "Application Accepted", messageBody, applicationVO.getEmail(), applicationVO.getApplicantNameAsNic());
            LOGGER.warn("EMAIL SEND SUCCESSFULLY to address -> " + applicationVO.getEmail());
        } catch (BusinessException be) {
            LOGGER.warn("Unable to send e mail Business Exception");
            be.printStackTrace();
            throw be;
        } catch (Exception e) {
            LOGGER.warn("Unable to send e mail Exception");
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

        return ststus;
    }

    public String sendRequestManualSubmissionMail(ApplicationVO applicationVO) throws BusinessException {
        String ststus = PoliceConstant.ERROR;
        try {

            String messageBody = "With reference to your clearance certificate application " + applicationVO.getReferenceNo() + ". We kindly request you to re-submit the application manually due to ambiguity in the information you provided.<br><br>";

            ststus = sendEmailMessage("Department of Police",
                    "Request Manual Submission", messageBody, applicationVO.getEmail(), applicationVO.getApplicantNameAsNic());
            LOGGER.warn("EMAIL SEND SUCCESSFULLY to address -> " + applicationVO.getEmail());
        } catch (BusinessException be) {
            LOGGER.warn("Unable to send e mail Business Exception");
            be.printStackTrace();
            throw be;
        } catch (Exception e) {
            LOGGER.warn("Unable to send e mail Exception");
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

        return ststus;
    }

    public String sendRequestClarificationMail(ApplicationVO applicationVO, RequestClarificationVO requestClarificationVO) throws BusinessException {
        String ststus = PoliceConstant.ERROR;
        try {

            String description = "With reference to your clearance certificate application " + applicationVO.getReferenceNo()
                    + ", We kindly request you to re-send the following data due to ambiguity in the information you provided:<br><br>";

            String middlePart = (requestClarificationVO.getNic() == 1 ? "NIC Copy<br>" : "")
                    + (requestClarificationVO.getPassport() == 1 ? "Passport Copy<br>" : "")
                    + (requestClarificationVO.getVerifyName() == 1 ? "Verify Name<br>" : "")
                    + (requestClarificationVO.getVerifyDateOfBirth() == 1 ? "Verify Date of Birth<br>" : "");

            String endPart = "<br>Please login using the same credentials used to submit the application to send the clarification.<br><br>";

            String messageBody = description + "" + middlePart + "" + endPart;
            ststus = sendEmailMessage("Department of Police",
                    "Request Clarification", messageBody, applicationVO.getEmail(), applicationVO.getApplicantNameAsNic());
            LOGGER.warn("EMAIL SEND SUCCESSFULLY to address -> " + applicationVO.getEmail());
        } catch (BusinessException be) {
            LOGGER.warn("Unable to send e mail Business Exception");
            be.printStackTrace();
            throw be;
        } catch (Exception e) {
            LOGGER.warn("Unable to send e mail Exception");
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

        return ststus;
    }


    public String sendNicRevisionEmailToApplicant(ApplicationVO applicationVO, String emailText) {
        String emailTitle = "Police Clearence Certificate eService - Notification";
        String emailSubject = "NIC Name Issue";
        String recieverEmailAddress = applicationVO.getEmail();
        String recieverName = applicationVO.getApplicantNameAsNic();
        try {
            return sendEmailMessageEmptyTemplate(emailTitle, emailSubject, emailText, recieverEmailAddress, recieverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PoliceConstant.ERROR;
    }


    public String sendAddressModificationNotificationMail(AddressTempVO addressTempVO, ApplicationVO applicationVO) {
        String emailTitle = "Police Clearence Certificate eService - Notification";
        String emailSubject = "Address Modification";
        PoliceEnumConstant.EmailType emailType = PoliceEnumConstant.EmailType.fromCode(addressTempVO.getEmailType());
        switch (emailType) {
            case IA:
                emailSubject = "Incorrect Address or Period of stay";
                break;
            case UP:
                emailSubject = "Update in Police Area";
                break;
            case NU:
                emailSubject = "NIC Revision Requirement";
                break;
            default:
                break;
        }
        String recieverEmailAddress = applicationVO.getEmail();
        String recieverName = applicationVO.getApplicantNameAsNic();
        try {
            return sendEmailMessageEmptyTemplate(emailTitle, emailSubject, addressTempVO.getEmailContent(), recieverEmailAddress, recieverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PoliceConstant.ERROR;
    }


}
