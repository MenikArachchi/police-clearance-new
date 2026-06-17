package lk.icta.police.web.business;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.TransactionVO;
import lk.icta.police.web.util.MailUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EmailNotificationBusiness {
	private static final Logger LOGGER = Logger.getLogger(EmailNotificationBusiness.class);
	private static EmailNotificationBusiness instance = null;
	/**
	 * Singleton Implementation
	 *
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
	private String sendEmailMessage(String emailTitle, String emailSubject,String message,String recieverEmailAddress, String recieverName) throws Exception{
		Map<String,Object> valueMap=new HashMap<String, Object>();
		valueMap.put(PoliceConstant.TITLE, emailTitle);
		valueMap.put(PoliceConstant.EMAIL_SUBJECT, emailSubject);
		valueMap.put(PoliceConstant.MAIL_TITLE, emailTitle);
		valueMap.put(PoliceConstant.RECEIVER_NAME, recieverName);
		valueMap.put(PoliceConstant.BODY_TEXT, message);
		MailUtil mailUtil=new MailUtil();
		boolean status=mailUtil.sendMail(valueMap, recieverEmailAddress, recieverName);
		if(status){
			return PoliceConstant.SUCCESS;
		}
		return PoliceConstant.ERROR;
	}


	public String sendPaymentSuccessMail(ApplicationVO applicationVO, TransactionVO transactionVO) throws BusinessException {
		String status = PoliceConstant.ERROR;
		try {

			String messageBody =
			"<div>Your payment for the clearance certificate application is successful.</div>"+
			"<br/><div>Please find your transaction details below:</div>"+
			"<table>"+
				"<tr><td>Payment Date</td><td>"+transactionVO.getCreatedDate()+"</td></tr>"+
				"<tr><td>Application Reference Number</td><td>"+applicationVO.getReferenceNo()+"</td></tr>"+
				"<tr><td>Transaction Number</td><td>"+transactionVO.getTransactionReferenceNo()+"</td></tr>"+
			"</table>"+
			"<br/><div>Payment details:</div>"+
			"<table>"+
				"<tr><td>Application Fee</td><td>"+transactionVO.getApplicationFee()+"</td></tr>"+
				"<tr><td>Service Fee</td><td>"+transactionVO.getServiceFee()+"</td></tr>"+
				"<tr><td>Postage</td><td>"+transactionVO.getPostageFee()+"</td></tr>"+
				"<tr><td>Convenience Fee</td><td>"+transactionVO.getConvenienceFee()+"</td></tr>"+
				"<tr style='font-weight:bold;'><td>Total Fee</td><td>"+transactionVO.getTotalFee()+"</td></tr>"+
			"</table>"+
			"<div>Please take note of your application reference number for future reference.</div>";

			String name = applicationVO.getApplicantNameAsNic() == null
					|| applicationVO.getApplicantNameAsNic().isEmpty() ? applicationVO.getApplicantNameAsPassport(): applicationVO.getApplicantNameAsNic();
			status = sendEmailMessage("Department of Police",
						"Payment Receipt", messageBody, applicationVO.getEmail(), name);
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

		return status;
	}













}
