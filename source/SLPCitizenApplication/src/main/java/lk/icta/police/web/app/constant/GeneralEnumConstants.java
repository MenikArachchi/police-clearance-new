package lk.icta.police.web.app.constant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nadeeshani Senevirathna
 *
 */
public class GeneralEnumConstants {
	
	public enum InformationType{
		FULL("FULL"),
		LIMITED("LIMITED");
		
		private String code;
		
		private InformationType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

		
		private static final Map<String, InformationType> LOOKUP = new HashMap<String, InformationType>();

		static {
			for (InformationType informationType : EnumSet.allOf(InformationType.class)) {
				LOOKUP.put(informationType.getCode(), informationType);
			}
		}
		
		public static InformationType fromCode(String code) {
			return LOOKUP.get(code);
		}
		
		
	}
	
	
	
}
