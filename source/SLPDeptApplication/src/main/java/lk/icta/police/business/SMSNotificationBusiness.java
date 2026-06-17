package lk.icta.police.business;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.util.PoliceSMSWSInvorkerClient;



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
	
	//SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");

	private String sendSMSMessage(String message,String recepient) throws IOException, ParserConfigurationException, SAXException{
		String endPointURL=CommonUtil.getValueFromFile("sms_config", "sms.end.point.url");
		PoliceSMSWSInvorkerClient client=new PoliceSMSWSInvorkerClient(endPointURL); 
		String ackMessage=client.sendSMSRequest(message, recepient);
		LOGGER.info("SMS SENDING ACKNOWLEDGEMENT :- " + ackMessage);
		if(StringUtils.equals(ackMessage, "success")){
			return PoliceConstant.SUCCESS;
		}
		return PoliceConstant.ERROR;
	}
	
	public String sendRequestManualSubmissionSMS(ApplicationVO applicationVO) {
		try {
			
			StringBuilder sb=new StringBuilder("Police Clearance: We kindly request you to re-submit the application manually due to ambiguity in the information you provided. - PHQ");
			return sendSMSMessage(sb.toString(), applicationVO.getMobileNo());	
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}	
	}
	
	public String sendRequestClarificationSMS(ApplicationVO applicationVO) {
		try {
			
			StringBuilder sb=new StringBuilder("Police Clearance: We request further clarification due to ambiguity in the information you provided. Please check your email for more information. - PHQ");
			return sendSMSMessage(sb.toString(), applicationVO.getMobileNo());	
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}	
	}

	public String sendAddressModificationNotificationSms(ApplicationVO applicationVO) {
		try {
			StringBuilder sb=new StringBuilder("Police Clearence: You have incorrectly provided your address/period of stay. Please check your email for more information. - PHQ");
			return sendSMSMessage(sb.toString(), applicationVO.getMobileNo());	
		} catch (Exception e) {
			e.printStackTrace();
			return PoliceConstant.ERROR;
		}	
	}

	
}
