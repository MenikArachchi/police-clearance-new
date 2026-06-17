package lk.icta.commonuser.framework.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import lk.icta.commonuser.framework.constant.CommonUserConstant;


public class ErrorKeyLoader {
	
	// Map<ErrorKey, ErrorString>
	private static Map<Integer, String> errorKeyMap =  new HashMap<Integer, String>();
	
	private static Properties errorKeyProperties =  new Properties();
	
	public static void loadErrorKeysIntoMap() {
	
		errorKeyProperties = CommonUtil.getPropertiesFromFile(CommonUserConstant.ERRORKEY_FILENAME);
		
		for(Entry<Object, Object> entry : errorKeyProperties.entrySet()) {
			int key = Integer.parseInt((String)entry.getKey());
			String value = (String) entry.getValue();
			errorKeyMap.put(key, value);
		}
	}
	
	public static String getErrorKey(int errorCode) {
		
		if (errorKeyMap.isEmpty()) {
			loadErrorKeysIntoMap();
		}
		
		return errorKeyMap.get(errorCode);
	}

}
