package lk.icta.commonuser.framework.utility;

import java.text.MessageFormat;
import java.util.Properties;

import lk.icta.commonuser.framework.constant.CommonUserConstant;

public class LanguageBasedPopupLoader {
	
	private static Properties popUpEnglish =  new Properties();
	private static Properties popUpSinhala = new Properties();
	private static Properties popUpTamil = new Properties();

	public static void loadPopupMessageIntoMaps() {
		popUpEnglish = CommonUtil.getPropertiesFromFile(CommonUserConstant.POPUP_FILENAME_ENGLISH);
		popUpSinhala = CommonUtil.getPropertiesFromFile(CommonUserConstant.POPUP_FILENAME_SINHALA);
		popUpTamil = CommonUtil.getPropertiesFromFile(CommonUserConstant.POPUP_FILENAME_TAMIL);
	}
	
	public static String getLocaleBasedValue(String key, String locale, Object... params) {
		
		String value = "";
		if (CommonUserConstant.LOCALE_ENGLISH.equals(locale)) {
			value = popUpEnglish.getProperty(key); 
		} else if (CommonUserConstant.LOCALE_SINHALA.equals(locale)) {
			value = popUpSinhala.getProperty(key); 
		} else if (CommonUserConstant.LOCALE_TAMIL.equals(locale)) {
			value = popUpTamil.getProperty(key); 
		}
		
		
		if (params != null && !CommonUtil.checkBlank(value)) {       
			value = MessageFormat.format(value, params);
		}
		
		return value;
	}
}
