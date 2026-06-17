package lk.icta.police.external.business;

import java.util.HashMap;
import java.util.Map;

import lk.icta.police.external.util.MailUtil;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.ApplicationVO;

import org.apache.log4j.Logger;

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
	private String sendEmailMessageEmptyTemplate(String emailTitle,String emailSubject, String message,String recieverEmailAddress, String recieverName) throws Exception{
		Map<String,Object> valueMap=new HashMap<String, Object>();
		valueMap.put(PoliceConstant.TITLE, emailTitle);
		valueMap.put(PoliceConstant.MAIL_TITLE, emailTitle);
		valueMap.put(PoliceConstant.EMAIL_SUBJECT, emailSubject);
		valueMap.put(PoliceConstant.RECEIVER_NAME, recieverName);
		valueMap.put(PoliceConstant.BODY_TEXT, message);
		MailUtil mailUtil=new MailUtil();
		boolean status=mailUtil.sendMailFromEmptyTemplate(valueMap, recieverEmailAddress, recieverName);
		if(status){
			return PoliceConstant.SUCCESS;
		}
		return PoliceConstant.ERROR;
	}



	public String sendAddressModificationNotificationMail(AddressTempVO addressTempVO, ApplicationVO applicationVO) {
		String emailTitle="Police Clearence Certificate eService - Notification";
		String emailSubject="Address Modification";
		PoliceEnumConstant.EmailType emailType=PoliceEnumConstant.EmailType.fromCode(addressTempVO.getEmailType());
		switch (emailType) {
		case IA:
			emailSubject="Incorrect Address or Period of stay";
			break;
		case UP:
			emailSubject="Update in Police Area";
			break;
		default:
			break;
		}
		String recieverEmailAddress=applicationVO.getEmail();
		String recieverName=applicationVO.getApplicantNameAsNic();
		try {
			return sendEmailMessageEmptyTemplate(emailTitle, emailSubject, addressTempVO.getEmailContent(), recieverEmailAddress, recieverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PoliceConstant.ERROR;
	}

	
	
}
