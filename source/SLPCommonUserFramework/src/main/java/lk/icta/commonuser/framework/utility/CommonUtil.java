package lk.icta.commonuser.framework.utility;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import lk.icta.commonuser.framework.exception.BusinessException;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * This class is responsible to provide common framework level functionality.
 * 
 * @author Koushik Mondal
 * 
 */
public final class CommonUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

	/** The file name properties mapping. */
	private static Map<String, Properties> fileNamePropertiesMapping = new HashMap<String, Properties>();

	// No initialization allowed
	/**
	 * Instantiates a new common util.
	 */
	private CommonUtil() {

	}

	/**
	 * To check whether a string is empty or null.
	 * 
	 * @param strValue
	 *            the str value
	 * @return true if empty or null
	 */
	public static boolean checkBlank(String strValue) {
		return (strValue == null || strValue.trim().isEmpty());
	}

	/**
	 * To get all the key value pairs from the property file.
	 * 
	 * @param filePath
	 *            the file path
	 * @return Properties
	 */
	public static Properties getPropertiesFromFile(String filePath) {

		Properties properties = new Properties();

		if (fileNamePropertiesMapping.containsKey(filePath)) {
			properties = fileNamePropertiesMapping.get(filePath);
		} else {
			try {
				ResourceBundle resourceBundle = ResourceBundle
						.getBundle(filePath);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("loaded local Resource Bundle File:"
							+ filePath);
				}

				String key = null;
				if (resourceBundle != null) {
					Enumeration<String> localenum = resourceBundle.getKeys();
					while (localenum.hasMoreElements()) {
						key = localenum.nextElement();
						properties.put(key, resourceBundle.getString(key));
					}
				}

				fileNamePropertiesMapping.put(filePath, properties);

			} catch (MissingResourceException ex) {
				LOGGER.error("could not find file:" + ex);
			}
		}

		return properties;
	}

	/**
	 * To get the value of a particular key from a specified properties file.
	 * 
	 * @param filePath
	 *            the file path
	 * @param key
	 *            the key
	 * @param params
	 *            the params
	 * @return the value
	 */
	public static String getValueFromFile(String filePath, String key,	Object... params) {
		String valueFromFile = "";
		try {
			if (params == null) {
				valueFromFile = getPropertiesFromFile(filePath)	.getProperty(key);
			} else {
				valueFromFile = MessageFormat.format(getPropertiesFromFile(filePath).getProperty(key),	params);
			}
		} catch (Exception e) {
			LOGGER.warn("could not get value from file:" + e);
		}
		LOGGER.error("value from file:" + valueFromFile);
		System.out.println("value from file:" + valueFromFile);
		return valueFromFile;
	}

	public static String getErrorKey(BusinessException bex) {
		
		if (bex.getErrorCode() == 0) {
			return bex.getErrorKey();
		} else {
			return ErrorKeyLoader.getErrorKey(bex.getErrorCode());
		}
	}

}
