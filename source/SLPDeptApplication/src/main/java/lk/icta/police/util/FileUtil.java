package lk.icta.police.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
 * FileUtil Class.
 * 
 */
public class FileUtil {
	
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);


	private static final String KEY_128BIT = "x7QKe+D3cgrbf7rE9M6eTA==";

	private static final int BUFFER_SIZE = 1024*4;
	
	public static void doCopy(InputStream is, OutputStream os) throws Exception {

        byte[] buffer = new byte[BUFFER_SIZE];

		try {
			int length   = 0;
	        while ((length = is.read(buffer)) > 0) {
	        	os.write(buffer, 0, length);
	        }
		} finally {
			os.flush();
			os.close();
			is.close();
		}
	}

	public static void encryptFile(InputStream inputStream,
			OutputStream outputStream){

		try {
			
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
			
			CipherInputStream cipt = new CipherInputStream(new BufferedInputStream(inputStream), cipher);

			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			
			doCopy(cipt, bos); // copy


		} catch (Exception e) {
			LOGGER.fatal("Error while encrypting file",e);
			throw new RuntimeException(e);
		}

	}

	private static Cipher getCipher(int encryptMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		new Base64();
		byte[] key = Base64.decodeBase64(KEY_128BIT);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(encryptMode, secretKeySpec);
		return cipher;
		
	}

	public static void decryptFile(InputStream inputStream, OutputStream outputStream){

		try {
			
			Cipher cipher = getCipher(Cipher.DECRYPT_MODE);


			CipherInputStream cipherInputStream = new CipherInputStream(
					new BufferedInputStream(inputStream), cipher);
			
			
			doCopy(cipherInputStream, new BufferedOutputStream(outputStream, BUFFER_SIZE)); // copy
			
			
		} catch (Exception e) {
			LOGGER.fatal("Error while decrypting file",e);
			throw new RuntimeException(e);
		}

	}

	
	

}
