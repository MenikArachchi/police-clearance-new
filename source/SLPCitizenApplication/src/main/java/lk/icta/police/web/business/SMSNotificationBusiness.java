package lk.icta.police.web.business;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.parsers.ParserConfigurationException;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.web.util.PoliceSMSWSInvorkerClient;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class SMSNotificationBusiness {
	private static final Logger LOGGER = Logger.getLogger(SMSNotificationBusiness.class);
	private static SMSNotificationBusiness instance = null;

	/**
	 * Singleton Implementation
	 * 
	 */
	public static SMSNotificationBusiness getInstance() {
		synchronized (SMSNotificationBusiness.class) {
			if (instance == null) {
				instance = new SMSNotificationBusiness();
			}
			return instance;
		}
	}

	// SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");

	private String sendSMSMessage(String message, String recepient)
			throws IOException, ParserConfigurationException, SAXException {
		String endPointURL = CommonUtil.getValueFromFile("sms_config", "sms.end.point.url");
		PoliceSMSWSInvorkerClient client = new PoliceSMSWSInvorkerClient(endPointURL);
		String ackMessage = client.sendSMSRequest(message, recepient);
		LOGGER.info("SMS SENDING ACKNOWLEDGEMENT :- " + ackMessage);
		if (StringUtils.equals(ackMessage, "success")) {
			return PoliceConstant.SUCCESS;
		}
		return PoliceConstant.ERROR;
	}

	public String sendRequestManualSubmissionSMS(ApplicationVO applicationVO) {
		try {

			StringBuilder sb = new StringBuilder("Request Manual Submission");
			return sendSMSMessage(sb.toString(), applicationVO.getMobileNo());
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}
	}

	public String sendRequestClarificationSMS(ApplicationVO applicationVO) {
		try {

			StringBuilder sb = new StringBuilder("Request Clarification");
			return sendSMSMessage(sb.toString(), applicationVO.getMobileNo());
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}
	}

	public String sendPaymentSuccessConfirmationSMS(ApplicationVO applicationVO, BigDecimal totalPayment) {
		try {
			if (totalPayment == null) {
				totalPayment = BigDecimal.ZERO;
			}
			DecimalFormat formatter = new DecimalFormat("###,###,###.00");
			StringBuilder sb = new StringBuilder("Police Clearance: We have received your payment of Rs. "
					+ formatter.format(totalPayment) + " for your clearance " + "application with reference number "
					+ applicationVO.getReferenceNo() + ". Please check your email for more information. - PHQ");

			String mobileNumberToSent = applicationVO.getMobileNo();

			if (!(mobileNumberToSent.substring(0, 1)).equals("0")) {
				mobileNumberToSent = "0" + mobileNumberToSent;
			}
			
			LOGGER.info("MOBILE NO TO SEND SMS: " + mobileNumberToSent);
			return sendSMSMessage(sb.toString(), mobileNumberToSent);
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}
	}

}
