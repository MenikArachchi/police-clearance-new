package lk.icta.police.web.util;

import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public final class MailUtil implements Serializable {
	
	private static final long serialVersionUID = 649816335599665740L;
	private static final Logger LOG = Logger.getLogger(MailUtil.class);
	private static final String PROP_FILE_NAME = "mail";
	
	private static String sHost = "";
	private static String sPort = "";
	private static String senderEmail = "";
	private static Authenticator auth = null;
	private static String authenticate = "";
	private static String authenticateUserId = "";
	private static String authenticatePassword = "";
	private static String sslFactory = "";
	
	private static Configuration cfg;
	
	static {
		MailUtil.init();
	}
	
	private static void init() {
		sHost = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.host");
		sPort = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.port");
		senderEmail = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.sender");
		authenticate = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.authenticate");
		authenticateUserId = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.authenticateUserId");
		authenticatePassword = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.authenticatePwd");
		auth = new MailAuthenticator(authenticateUserId, authenticatePassword);
		sslFactory = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.mail.ssLFactory");
		
		// 1. Configure FreeMarker
	    //
	    // You should do this ONLY ONCE, when your application starts,
	    // then reuse the same Configuration object elsewhere.
	    
	    cfg = new Configuration();
	    cfg.setClassForTemplateLoading(MailUtil.class, "/");	   	        
	    // Some other recommended settings:	   
	    cfg.setDefaultEncoding("UTF-8");
	    cfg.setLocale(Locale.US);
	    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
	}
	
	
	/**
	 * @param valueMap -should contain TITLE,EMAIL_SUBJECT,MAIL_TITLE,RECEIVER_NAME,BODY_TEXT
	 * @param recieverEmailAddress
	 * @param recieverName
	 * @return
	 * @throws Exception
	 */
	public boolean sendMail(Map<String,Object> valueMap, String recieverEmailAddress, String recieverName) throws Exception {
	 LOG.info("Inside EmailUtil::sendMail::");
	 boolean mailSent = false;
	 try{
		 
		LOG.info("Result of valueMap: " + valueMap);
		
		Properties props = System.getProperties();
		Session session;
		
		String mailSendingMode=CommonUtil.getValueFromFile("mail", "police.mail.sending.mode");		
		LOG.info("mailSendingMode: " + mailSendingMode);
		
		if(StringUtils.equals(mailSendingMode, "STAGING_ALTERNATIVE")){
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", CommonUtil.getValueFromFile("mail", "police.mail.alternative.host"));
			props.put("mail.smtp.port", CommonUtil.getValueFromFile("mail", "police.mail.alternative.port"));
			
			final String userName=CommonUtil.getValueFromFile("mail", "police.mail.alternative.username");
			final String password=CommonUtil.getValueFromFile("mail", "police.mail.alternative.password");
			senderEmail=userName;
			session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, password);
						}
					  });
			
		}else{			
//			// Setup mail server
			System.setProperty("javax.net.debug", "ssl,handshake");
			props.put("mail.smtp.host", sHost);
			props.put("mail.smtp.port", sPort);
			props.setProperty("mail.smtp.starttls.enable", "true");
			LOG.info("mail sHost: " + sHost);
			LOG.info("mail sPort: " + sPort);
			// Get session
			if (authenticate.equalsIgnoreCase("TRUE")) {
				// set Authentication True
				props.put("mail.smtp.auth", "true");
				props.setProperty("mail.pop3.socketFactory.class", sslFactory);
				props.setProperty("mail.smtp.socketFactory.class", sslFactory);
				session = Session.getInstance(props, auth);
			} else {
				session = Session.getDefaultInstance(props);
			}
		}

		// Define message
		MimeMessage message = new MimeMessage(session);	
		
		String mailSubject =(String) valueMap.get(PoliceConstant.EMAIL_SUBJECT);
				
		
		message.setSubject(mailSubject);		
		message.setFrom(new InternetAddress(senderEmail));
		LOG.info("senderEmail: " + senderEmail);

		LOG.info("recieverEmailAddress: " + recieverEmailAddress);
		
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(StringUtils.trim(recieverEmailAddress)));
		
		// 2. Proccess template(s)
	    //
	    // You will do this for several times in typical applications.
	    
	    // 2.1. Prepare the template input:
	   	    // 2.2. Get the template
	    Template template = cfg.getTemplate("police_email_template.ftl");
		//LOG.debug("freemarkerTemplate: " + template);
	      
	    // 2.3. Generate the output
	    // Write output to the console
		Writer out = new StringWriter();
		template.process(valueMap, out);

	   
	    try {
	    	BodyPart body = new MimeBodyPart();

			/* you can add html tags in your text to decorate it. */
			body.setContent(out.toString(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);

			message.setContent(multipart);
			
			//System.out.println("Email Message :" + out.toString());
			
			Transport.send(message);
			mailSent = true;
			
	    } catch (Exception ex) {
	    	ex.printStackTrace();
			LOG.error("MailUtil # sendMail Exception : " + ex);
			throw new Exception(ex);
		} 

		} catch (AddressException ex) {
			ex.printStackTrace();
			LOG.error("MailUtil # sendMail AddressException : " + ex);
			throw new Exception(ex);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			LOG.error("MailUtil # sendMail MessagingException : " + ex);
			throw new Exception(ex);
		} catch (Exception ex) {
			LOG.error("MailUtil # sendMail Exception : " + ex);
			ex.printStackTrace();
			throw new Exception(ex);
		} 
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("EXIT sendMail in DWC with return value: " + mailSent);
		}
	
		return mailSent;

	}

	


	
	
	
}
