package lk.icta.commonuser.framework.constant;

public abstract class DBConstant {
	
	private DBConstant() {

	}
	
	public class ALIAS {
		public static final String USERCOUNT = "userCount";
	}
	
	public class COL_DEPT_MASTER {
		public static final String ID = "id";
		public static final String NAME = "name";
	}
	
	public class COL_SERVICE_MASTER {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PARENT_ID = "parent_id";
		public static final String DEPT_ID = "department_id";
	}
	
	public class COL_USER_SERVICE_MAPPING {
		public static final String USER_ID = "user_id";
		public static final String SERVICE_ID = "service_id";
		public static final String CREATED_BY = "created_by";
		public static final String CREATED_TIME = "created_time";
	}
	
	public class COL_USER_TYPE_MASTER {
		public static final String NAME = "name";
	}
	
	public class COL_ASSIGNMENT_MAPPING {
		public static final String ASSIGNABLE_USER_TYPE_ID = "assignable_user_type_id";
	}
	
	
	public class COL_USER_MASTER {
		public static final String ID = "id";
		public static final String USER_FULL_NAME = "user_full_name";
		public static final String EMAIL_ID = "email_id";
		public static final String USER_TYPE = "user_type";
		public static final String USER_NAME = "user_name";
		public static final String PASSWORD = "password";
		public static final String DEPT_ID = "dept_id";
		public static final String STATUS = "status";
		public static final String CREATED_BY = "created_by";
		public static final String CREATED_TIME = "created_time";
		public static final String MODIFIED_BY = "modified_by";
		public static final String MODIFIED_TIME = "modified_time";
		public static final String ASSIGNED_LOCATION = "assigned_location";
		public static final String CURRENT_NIC = "current_nic";
	}
	
	public class COL_PORTS_MASTER {
		public static final String LOCATION_ID = "location_id";
		public static final String LOCATION_NAME = "location_name";
	}
	
}
