package lk.icta.commonuser.framework.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Security;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
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
import javax.activation.DataHandler;
import lk.icta.commonuser.framework.exception.BusinessException;
import javax.activation.FileDataSource;
import org.apache.log4j.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * The Class MailUtil.
 */
public final class MailUtil {

	/** The s host. */
	private static String sHost = "";

	/** The s port. */
	private static String sPort = "";

	/** The sender email. */
	private static String senderEmail = "";

	/** The auth. */
	private static Authenticator auth = null;

	/** The authenticate. */
	private static String authenticate = "";

	/** The authenticate user id. */
	private static String authenticateUserId = "";

	/** The authenticate password. */
	private static String authenticatePassword = "";

	/** The rand. */
	private static Random rand = new Random(1000L);

	/** The ssl factory. */
	private static String sslFactory = "";

	/** The Constant PROP_FILE_NAME. */
	private static final String PROP_FILE_NAME = "CommonUserProp";
	
	private static Configuration cfg;
	
	//EMAIL VARIABLES
	public static  final String EMAIL_SUBJECT="EMAIL_SUBJECT";
	public static  final String MAIL_TITLE="mailTitle";
	public static  final String RECEIVER_NAME="receiverName";
	public static  final String BODY_TEXT="bodyText";
	public static String TITLE="title";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MailUtil.class);

	// Stops initialization of it's object
	/**
	 * Instantiates a new mail util.
	 */
	private MailUtil() {

	}

	static {
		MailUtil.init();
	}

	/**
	 * Inits values
	 */
	private static void init() {
		sHost = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.host");
		sPort = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.port");
		senderEmail = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.sender");
		authenticate = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.authenticate");
		authenticateUserId = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.authenticateUserId");
		authenticatePassword = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.authenticatePwd");
		auth = new MailAuthenticator(authenticateUserId, authenticatePassword);
		sslFactory = CommonUtil.getValueFromFile(PROP_FILE_NAME,
				"commonuser.mail.ssLFactory");
		
		cfg = new Configuration();
		 cfg.setClassForTemplateLoading(MailUtil.class, "/");	   	        
		 // Some other recommended settings:	   
		 cfg.setDefaultEncoding("UTF-8");
		 cfg.setLocale(Locale.US);
		 cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		 
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("EXIT MailUtil-init@@" + "#sHost:" + sHost
					+ " #sPort:" + sPort + " #senderEmail:" + senderEmail
					+ " #authenticate:" + authenticate
					+ " #authenticateUserId: " + authenticateUserId + "#auth:"
					+ auth + "##");

		}

	}

	/**
	 * Send mail.
	 * 
	 * @param sToAddress
	 *            the s to address
	 * @param sSubject
	 *            the s subject
	 * @param sText
	 *            the s text
	 * @return true, if successful
	 * @throws BusinessException
	 *             the business exception
	 */
	public static boolean sendMail(String sToAddress, String sSubject,
			String sText) throws BusinessException {

		File fp = null;
		boolean mailSent = false;
		try {
			Properties props = System.getProperties();
			// Setup mail server
			System.setProperty("javax.net.debug", "ssl,handshake");
			props.put("mail.smtp.host", sHost);
			props.put("mail.smtp.port", sPort);
			//props.setProperty("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.enable","true");
			
			/**added for port 465**/
			//Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			props.put("mail.transport.protocol", "smtp");
			props.put( "mail.smtp.auth ", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", sPort);
			//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.class", sslFactory);
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			/**end**/
			
			// Get session
			Session session;
			if (authenticate.equalsIgnoreCase("TRUE")) {
				// set Authentication True

				props.put("mail.smtp.auth", "true");
				System.setProperty("javax.net.debug", "ssl,handshake");
				session = Session.getDefaultInstance(props, auth);
			} else {
				session = Session.getDefaultInstance(props);
			}
			session.setDebug(true);

			// Define message
			MimeMessage message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(senderEmail));
			// Set the to address
			if (sToAddress != null && sToAddress.trim().length() > 0) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(sToAddress));
			}

			String htmlContent = "<html><head><meta http-equiv='Content-Type' content='text/html' charset=iso-8859-1'/></head>"
					+ "<body><Table>"
					+ "<tr><td>"
					+ sText.replaceAll("\n", "<BR>")
					+ "</td></tr>"
					+ "</Table></body>" + "</html>";

			message.setSubject(sSubject);
			Multipart mp = new MimeMultipart("alternative");

			BodyPart bpMain = new MimeBodyPart();

			String fileName = "temp" + rand.nextLong() + ".html";
			fp = new File(fileName);

			fp.createNewFile();
			FileWriter fw = new FileWriter(fp);

			fw.write(htmlContent);
			fw.flush();
			fw.close();

			bpMain.setDataHandler(new DataHandler(new FileDataSource(fp)));

			mp.addBodyPart(bpMain);

			message.setContent(mp);

			// Send message
			Transport.send(message);
			mailSent = true;

		} catch (AddressException ex) {
			LOGGER.error("MailUtil # sendMail AddressException : ", ex);
			throw new BusinessException("error.commonUser.MailSentFail");
		} catch (MessagingException ex) {
			LOGGER.error("MailUtil # sendMail MessagingException : ", ex);
			throw new BusinessException("error.commonUser.MailSentFail");
		} catch (Exception ex) {
			LOGGER.error("MailUtil # sendMail Exception : ", ex);
			throw new BusinessException("error.commonUser.MailSentFail");
		} finally {
			try {
				if (fp != null) {
					fp.delete();
					fp = null;
				}
			} catch (Exception ex) {
				LOGGER.error("MailUtil # sendMail error : " + ex);
			}
		}

		return mailSent;
	}
	
	/**
	 * Send mail.
	 * 
	 * @param sToAddress
	 *            the s to address
	 * @param sSubject
	 *            the s subject
	 * @param sText
	 *            the s text
	 * @return true, if successful
	 * @throws Exception 
	 */
	private static boolean sendMail(Map<String,Object> valueMap, String recieverEmailAddress, String recieverName) throws Exception { 
		LOGGER.info("Inside EmailUtil::sendMail");
		boolean mailSent = false;
		try{
		 
			LOGGER.info("Result of valueMap: " + valueMap);
		
			Properties props = System.getProperties();
			Session session;
		
			String mailSendingMode=CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.sending.mode");
			if(mailSendingMode.equals("STAGING_ALTERNATIVE")){
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.alternative.host"));
				props.put("mail.smtp.port", CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.alternative.port"));
				
				props.put("mail.smtp.connectiontimeout", (1000*5));
				props.put("mail.smtp.timeout", (1000*5));
			
				final String userName=CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.alternative.username");
				final String password=CommonUtil.getValueFromFile("CommonUserProp", "commonuser.mail.alternative.password");
				senderEmail=userName;
				session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, password);
						}
					});
			}else{	
				// Setup mail server
				System.setProperty("javax.net.debug", "ssl,handshake");
				props.put("mail.smtp.host", sHost); 
				props.put("mail.smtp.port", sPort);
				props.setProperty("mail.smtp.starttls.enable", "true");
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
			
			String mailSubject =(String) valueMap.get(EMAIL_SUBJECT);
					
			
			message.setSubject(mailSubject);		
			message.setFrom(new InternetAddress(senderEmail));
			LOGGER.info("senderEmail: " + senderEmail);
	
			LOGGER.info("recieverEmailAddress: " + recieverEmailAddress);
			
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recieverEmailAddress.trim()));
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
				LOGGER.error("MailUtil_@0 # sendMail Exception : " + ex);
				throw new Exception(ex);
			} 
		    
		} catch (AddressException ex) {
			ex.printStackTrace();
			LOGGER.error("MailUtil_@1 # sendMail AddressException : " + ex);
			throw new Exception(ex);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			LOGGER.error("MailUtil_@2 # sendMail MessagingException : " + ex);
			throw new Exception(ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("MailUtil_@3 # sendMail Exception : " + ex);
			throw new Exception(ex);
		} 
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("EXIT sendMail in DOC with return value: " + mailSent);
		}
	
		return mailSent;}
	
	
	//EMAIL_SUBJECT,MAIL_TITLE,RECEIVER_NAME,BODY_TEXT
	public static boolean sendEmailMessage(String emailTitle, String emailSubject,String message,String recieverEmailAddress, String recieverName) throws Exception{
		Map<String,Object> valueMap=new HashMap<String, Object>();
		valueMap.put(TITLE, emailTitle);
		valueMap.put(EMAIL_SUBJECT, emailSubject);
		valueMap.put(MAIL_TITLE, emailTitle);
		valueMap.put(RECEIVER_NAME, recieverName);
		valueMap.put(BODY_TEXT, message);
		boolean status=MailUtil.sendMail(valueMap, recieverEmailAddress, recieverName);
		if(status){
			return true;
		}
			return false;
	}
}
