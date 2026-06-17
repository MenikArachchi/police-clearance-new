package lk.icta.police.framework.mock;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class MockDBConstants {
	
	    // test database table names
		public enum TEST_DB_TABLE {		
			
			SEQUENCE_NUMBER_MASTER("sequence_number_master"),		
			TRANSACTION("transaction"),			
			COMMENTS("comments"),			
			ADDRESS_AUDIT("address_audit"),			
			ADDRESS("address"),			
			APPLICATION_AUDIT("application_audit"),			
			APPLICATION("application"),			
			COUNTRY("country"),
			REQUEST_CLARIFICATION("request_clarification"),
			CHANGE_AUDIT("change_audit"),
			BLACK_LIST("black_list"),
			CERTIFICATE_AUTH_PERSON("certificate_auth_person"),
			ADDRESS_CHANGE_AUDIT("address_change_audit"),
			COMMISSIONER("commissioner"),
			ADDRESS_TEMP("address_temp"),
			NOTIFICATION("notification");

			private String code;
			
			private TEST_DB_TABLE(String code) {
				this.code=code;
			}

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}
			
			private static final Map<String, TEST_DB_TABLE> LOOKUP = new HashMap<String, TEST_DB_TABLE>();

			static {
				for (TEST_DB_TABLE  table : EnumSet.allOf(TEST_DB_TABLE.class)) {
					LOOKUP.put(table.getCode(), table);
				}
			}
			
			public static TEST_DB_TABLE fromCode(String code) {
				return LOOKUP.get(code);
			}
		}
		
		
		public static class TEST_QUERY {
		
			public static final String SET_FORIEGN_KEY_CHECKS=new StringBuilder("SET FOREIGN_KEY_CHECKS = ?;").toString();
			public static final String TRUNCATE_TABLE=new StringBuilder("TRUNCATE TABLE ").toString();
		}

}
