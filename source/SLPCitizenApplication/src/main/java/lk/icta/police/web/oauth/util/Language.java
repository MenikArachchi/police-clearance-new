package lk.icta.police.web.oauth.util;

/**
 * 
 * @author kishantha.nanayakkara@lk.pwc.com
 *
 */

public enum Language {

	ENGLISH("en"){
		@Override
		public String getFileName() {
			return filenamePrefix+"_en";
		}
	},SINHALA("si") {
		@Override
		public String getFileName() {
			return filenamePrefix+"_si";
		}
	},TAMIL("ta") {
		@Override
		public String getFileName() {
			return filenamePrefix+"_ta";
		}
	};

	private static final String filenamePrefix = "global";
	private String languageCode;


	Language(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public static Language fromLanguageCode(String languageCode) {
		if (languageCode != null) {
			for (Language b : Language.values()) {
				if (languageCode.equalsIgnoreCase(b.languageCode)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException("Invalid language code " + languageCode );
	}

	public abstract String getFileName();
}
