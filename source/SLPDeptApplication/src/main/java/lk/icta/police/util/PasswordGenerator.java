package lk.icta.police.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public final class PasswordGenerator {

	public static final char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', '@', '#', '$', '%', '&', '*', '+' };

	public static final char[] DIGITS = { '2', '3', '4', '5', '6', '7', '8',
			'9', };

	public static String generate(int length, int digitGoAfterNumOfChars) {

		Random r = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char[] charset = 0 == (i + 1) % (digitGoAfterNumOfChars + 1) ? DIGITS
					: CHARS;

			sb.append(charset[r.nextInt(charset.length)]);
		}
		return sb.toString();
	}
	
	/**
	 * To encrypt password
	 * @param plaintext
	 * @return encrypted password
	 * @throws Exception
	 */
	public static synchronized String encrypt(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); 
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}

		byte raw[] = md.digest();
		String hash = Base64.encodeBase64String(raw);
		return hash;
	}
}
