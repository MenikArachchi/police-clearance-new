package lk.icta.commonuser.framework.constant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUserEnumConstant.
 */
public class CommonUserEnumConstant {

	/**
	 * The Enum UserStatus.
	 */
	public enum UserStatus {

		/** The ACTIVE. */
		ACTIVE(1, "Active"),

		/** The INACTIVE. */
		INACTIVE(2, "Inactive");

		/** The code. */
		private int code;

		/** The value. */
		private String value;

		/**
		 * Instantiates a new user status.
		 * 
		 * @param code
		 *            the code
		 * @param value
		 *            the value
		 */
		private UserStatus(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/** The Constant LOOKUP. */
		private static final Map<Integer, UserStatus> LOOKUP = new HashMap<Integer, UserStatus>();

		static {
			for (UserStatus userStatus : EnumSet.allOf(UserStatus.class)) {
				LOOKUP.put(userStatus.getCode(), userStatus);
			}
		}

		/**
		 * From code.
		 * 
		 * @param code
		 *            the code
		 * @return the user status
		 */
		public static UserStatus fromCode(int code) {
			return LOOKUP.get(code);
		}
	}

	/**
	 * The Enum UserType.
	 */
	public enum UserType {
		
//		'1', 'Super User'
//		'2', 'Department Admin'
//		'3', 'Department User'
//		'4', 'Checking Officer no Adverse'
//		'5', 'Checking Officer Adverse'
//		'6', 'OIC'
//		'7', 'ASP'
//		'8', 'DHA'
//		'9', 'DIG'
//		'10', 'Posting Officer'

		/** The SUPE r_ user. */
		SUPER_USER(1, "Super User"),
		/** The DEPARTMEN t_ admin. */
		DEPARTMENT_ADMIN(2, "Department Admin"),
		/** The DEPARTMEN t_ user. */
		DEPARTMENT_USER(3, "Department User"),	
		
		CHECKING_OFFICER_NO_ADVERSE(4, "Checking Officer No Adverse"),	
		CHECKING_OFFICER_ADVERSE(5, "Checking Officer Adverse"),
		OIC(6, "OIC"),		
		ASP(7, "ASP"),		
		DHA(8, "DHA"),	
		DIG(9, "DIG"),
		POSTING_OFFICER(10, "Posting Officer");
		

		/** The code. */
		private int code;

		/** The value. */
		private String value;

		/**
		 * Instantiates a new user type.
		 * 
		 * @param code
		 *            the code
		 * @param value
		 *            the value
		 */
		private UserType(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/** The Constant LOOKUP. */
		private static final Map<Integer, UserType> LOOKUP = new HashMap<Integer, UserType>();

		static {
			for (UserType userType : EnumSet.allOf(UserType.class)) {
				LOOKUP.put(userType.getCode(), userType);
			}
		}

		/**
		 * From code.
		 * 
		 * @param code
		 *            the code
		 * @return the user type
		 */
		public static UserType fromCode(int code) {
			return LOOKUP.get(code);
		}
	}

	/**
	 * The Enum UserOperation.
	 */
	public enum UserOperation {

		/** The LOGIN. */
		LOGIN(1),

		/** The LOGOUT. */
		LOGOUT(2),

		/** The CHANG e_ password. */
		CHANGE_PASSWORD(3),

		/** The RESE t_ password. */
		RESET_PASSWORD(4),

		/** The AD d_ user. */
		ADD_USER(5),

		/** The EDI t_ user. */
		EDIT_USER(6);

		/** The code. */
		private int code;

		/**
		 * Instantiates a new user operation.
		 * 
		 * @param code
		 *            the code
		 */
		private UserOperation(int code) {
			this.code = code;
		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/** The Constant LOOKUP. */
		private static final Map<Integer, UserOperation> LOOKUP = new HashMap<Integer, UserOperation>();

		static {
			for (UserOperation userOp : EnumSet.allOf(UserOperation.class)) {
				LOOKUP.put(userOp.getCode(), userOp);
			}
		}

		/**
		 * From code.
		 * 
		 * @param code
		 *            the code
		 * @return the user operation
		 */
		public static UserOperation fromCode(int code) {
			return LOOKUP.get(code);
		}
	}

	/**
	 * The Enum UserOperationResult.
	 */
	public enum UserOperationResult {

		/** The SUCCESS. */
		SUCCESS(1),

		/** The FAILURE. */
		FAILURE(2);

		/** The code. */
		private int code;

		/**
		 * Instantiates a new user operation result.
		 * 
		 * @param code
		 *            the code
		 */
		private UserOperationResult(int code) {
			this.code = code;
		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/** The Constant LOOKUP. */
		private static final Map<Integer, UserOperationResult> LOOKUP = new HashMap<Integer, UserOperationResult>();

		static {
			for (UserOperationResult userOpResult : EnumSet
					.allOf(UserOperationResult.class)) {
				LOOKUP.put(userOpResult.getCode(), userOpResult);
			}
		}

		/**
		 * From code.
		 * 
		 * @param code
		 *            the code
		 * @return the user operation result
		 */
		public static UserOperationResult fromCode(int code) {
			return LOOKUP.get(code);
		}
	}
}
