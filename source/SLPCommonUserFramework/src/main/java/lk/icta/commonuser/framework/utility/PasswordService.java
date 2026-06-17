package lk.icta.commonuser.framework.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * To encrypt a plain string input for password field.
 * 
 * @author koushikm000
 */
public final class PasswordService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PasswordService.class);

	/** The instance. */
	private static PasswordService instance;

	// Singleton instance
	/**
	 * Instantiates a new password service.
	 */
	private PasswordService() {

	}

	/**
	 * Gets the single instance of PasswordService.
	 * 
	 * @return single instance of PasswordService
	 */
	public static synchronized PasswordService getInstance() {
		if (instance == null) {
			instance = new PasswordService();
		}
		return instance;
	}

	/**
	 * To encrypt password.
	 * 
	 * @param plaintext
	 *            the plaintext
	 * @return encrypted password
	 * @throws Exception
	 *             the exception
	 */
	public synchronized String encrypt(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException in encrypt: ", e);
			throw new Exception(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException in encrypt: ", e);
			throw new Exception(e.getMessage());
		}

		byte raw[] = md.digest();
		return Base64.encodeBase64String(raw);
	}

	/**
	 * To generate random alphanumeric password.
	 * 
	 * @param range
	 *            of generated text
	 * @return random alphanumeric password
	 */
	public static String generateRandomPassword(int range) {
		String randString = "";
		if (range > 0) {
			StringBuffer sb = new StringBuffer();
			String block = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFIJKLMNOPQRSTUVWXYZ";
			sb.append(block).append(block.toUpperCase()).append("0123456789");
			block = sb.toString();
			sb = new StringBuffer();
			Random random = new Random();
			try {
				for (int i = 0; i < range; i++) {
					sb.append(Character.toString(block.charAt(random
							.nextInt(block.length() - 1))));
				}
				randString = sb.toString();
			} catch (ArrayIndexOutOfBoundsException e) {
				LOGGER.error(
						"ArrayIndexOutOfBoundsException in generateRandomPassword: ",
						e);
				randString = "P@5Sw0RD4U";
			} catch (NumberFormatException e) {
				LOGGER.error(
						"NumberFormatException in generateRandomPassword: ", e);
				randString = "P@5Sw0RD4U";
			} catch (Exception e) {
				LOGGER.error("Exception in generateRandomPassword: ", e);
				randString = "P@5Sw0RD4U";
			}
		}
		return randString;

	}
}
