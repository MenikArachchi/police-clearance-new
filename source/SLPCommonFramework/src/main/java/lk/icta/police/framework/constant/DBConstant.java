package lk.icta.police.framework.constant;

/**
 * @author nsenevirat001
 */
public class DBConstant {
  //@formatter:off
    public static final String EMPTY_SPACE = " ";
    public static final String SELECT = "SELECT";
    public static final String INSERT = "INSERT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";
    public static final String INTO = "INTO";
    public static final String VALUES = "VALUES";
    public static final String ORDER_BY = "ORDER BY";
    public static final String GROUP_BY = "GROUP BY";
    public static final String ASCENDING = "ASC";
    public static final String DECENDING = "DESC";
    public static final String LIMIT = "LIMIT";
    public static final String SEPERATOR = ",";
    public static final String ALL_FIELDS = "*";
    public static final String INSERT_INTO = "INSERT INTO";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String UPDATE = "UPDATE";
    public static final String SET = "SET";
    public static final String EQUALS = "=";
    public static final String BETWEEN = "BETWEEN";
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String COUNT = "COUNT";
    public static final String AS = "AS";
    public static final String DOT = ".";
    public static final String NOT_IN = "NOT IN";
    public static final String NOT_EQUALS = "!=";
    public static final String LESS_OR_EQUALS = "<=";
    public static final String GRATER_OR_EQUALS = ">=";
    public static final String DISTINCT = "DISTINCT";
    public static final String INNER_JOIN = "INNER JOIN";
    public static final String ON = "ON";
    public static final String LESSTHAN = "<";
    public static final String GRATERTHAN = ">";
    public static final String ISNULL = "IS NULL";
    public static final String ISNOTNULL = "IS NOT NULL";
    public static final String DELETE = "DELETE";
    public static final String LIKE = "LIKE";
    public static final String CONCAT = "CONCAT";
    public static final String UPPER = "UPPER";
    public static final String LOWER = "LOWER";
    public static final String CAST = "CAST";
    public static final String DATE = "DATE";
    public static final Object PLUS = "+";
    public static final String IN = "IN";
    public static final String SUM = "SUM";
    public static final String LEFT_JOIN = "LEFT JOIN";
    public static final String RIGHT_JOIN = "RIGHT JOIN";
    public static final String WHEN = "WHEN";
    public static final String CASE = "CASE";
    public static final String END = "END";
    public static final String THEN = "THEN";
    public static final String NOT = "NOT";

    // table names
    public class DB_TABLE {

        public static final String SEQUENCE_NUMBER_MASTER = "sequence_number_master";

        public static final String TRANSACTION = "transaction";

        public static final String APPLICATION = "application";

        public static final String APPLICATION_AUDIT = "application_audit";

        public static final String ADDRESS = "address";

        public static final String ADDRESS_AUDIT = "address_audit";

        public static final String COUNTRY = "country";

        public static final String COMMENTS = "comments";

        public static final String POLICE_AREA = "police_area";

        public static final String REQUEST_CLARIFICATION = "request_clarification";

        public static final String CHANGE_AUDIT = "change_audit";

        public static final String BLACK_LIST = "black_list";

        public static final String CERTIFICATE_AUTH_PERSON = "certificate_auth_person";

        public static final String ADDRESS_CHANGE_AUDIT = "address_change_audit";

        public static final String COMMISSIONER = "commissioner";

        public static final String ADDRESS_TEMP = "address_temp";

        public static final String NOTIFICATION = "notification";

        public static final String APPLICATION_CLEARANCE_DATE = "application_clearance_date";

        public static final String APPLICATION_MODIFIED_DATES = "application_modified_dates";

        private DB_TABLE() {

        }
    }

    // column names of SEQUENCE_NUMBER_MASTER
    public class SEQUENCE_NUMBER_MASTER_COL {
        public static final String SEQUENCE_NUMBER_MASTER_ID = "sequence_number_master_id";
        public static final String CURRENT_SEQUENCE_NUMBER = "current_sequence_number";
        public static final String PREFIX_VALUE = "prefix_value";
        public static final String NUMBER_LENGTH = "number_length";
        public static final String DESCRIPTION = "description";

        private SEQUENCE_NUMBER_MASTER_COL() {
        }
    }

    // column names of Application
    public class APPLICATION_COL {
        public static final String APPLICATION_ID = "application_id";
        public static final String REFERENCE_NO = "reference_no";
        public static final String PREVIOUS_REFERENCE_NO = "previous_reference_no";
        public static final String NATIONALITY_ID = "nationality_id";
        public static final String NATIONALITY = "nationality";
        public static final String CITIZEN_OF_LANKA = "citizen_of_lanka";
        public static final String CITIZENSHIP_OBTAINED_DATE = "citizenship_obtained_date";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String AGE = "age";
        public static final String NIC = "nic";
        public static final String NEW_NIC = "new_nic";
        public static final String PASSPORT = "passport";
        public static final String COUNTRY_ID = "country_id";
        public static final String COUNTRY_NAME = "country_name";
        public static final String HIGH_COMMISION_REFERENCE_ID = "high_commision_reference_id";
        public static final String HIGH_COMMISION_REFERENCE = "high_commision_reference";
        public static final String APPLICANT_NAME_AS_NIC = "applicant_name_as_nic";
        public static final String APPLICANT_NAME_AS_PASSPORT = "applicant_name_as_passport";
        public static final String PASSPORT_ISSUE_DATE = "passport_issue_date";
        public static final String PRESENT_ADDRESS_LOCAL = "present_address_local";
        public static final String PRESENT_ADDRESS_OVERSEAS = "present_address_overseas";
        public static final String SEX = "sex";
        public static final String APPLICANT_STATUS = "applicant_status";
        public static final String OCCUPATION = "occupation";
        public static final String PURPOSE = "purpose";
        public static final String PREVIOUS_CERTIFICATE_APPLY = "previous_certificate_apply";
        public static final String PREVIOUS_CERTIFICATE_COUNTRY_ID = "previous_certificate_country_id";
        public static final String PREVIOUS_CERTIFICATE_COUNTRY_NAME = "previous_certificate_country_name";
        public static final String PREVIOUS_CERTIFICATE_ISSUE_STATUS = "previous_certificate_issue_status";
        public static final String PREVIOUS_CERTIFICATE_REFERENCE_NO = "previous_certificate_reference_no";
        public static final String PREVIOUS_CERTIFICATE_ISSUE_DATE = "previous_certificate_issue_date";
        public static final String AUTHORIZED_HANDOVER_PERSON = "authorized_handover_person";
        public static final String AUTHORIZED_HANDOVER_PERSON_NAME = "authorized_handover_person_name";
        public static final String AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT = "authorized_handover_person_nic_passport";
        public static final String HIGH_COMMISION_REFERENCE_ADDRESS = "high_commision_reference_address";
        public static final String CERTIFICATE_POST_ADDRESS_LINE_1 = "certificate_post_address_line_1";
        public static final String CERTIFICATE_POST_ADDRESS_LINE_2 = "certificate_post_address_line_2";
        public static final String CERTIFICATE_POST_ADDRESS_CITY = "certificate_post_address_city";
        public static final String CERTIFICATE_POST_ADDRESS_STATE = "certificate_post_address_state";
        public static final String CERTIFICATE_POST_ADDRESS_POSTAL = "certificate_post_address_postal";
        public static final String CERTIFICATE_POST_ADDRESS_COUNTRY = "certificate_post_address_country";
        public static final String CERTIFICATE_POST_ADDRESS_COUNTRY_NAME = "certificate_post_address_country_name";
        public static final String MOBILE_COUNTRY_CODE_ID = "mobile_country_code_id";
        public static final String MOBILE_COUNTRY_CODE = "mobile_country_code";
        public static final String MOBILE_NO = "mobile_no";
        public static final String EMAIL = "email";
        public static final String SPOUSE_FULL_NAME = "spouse_full_name";
        public static final String SPOUSE_ADDRESS = "spouse_address";
        public static final String SPOUSE_NATIONALITY = "spouse_nationality";
        public static final String SPOUSE_PASSPORT = "spouse_passport";
        public static final String SPOUSE_NIC = "spouse_nic";
        public static final String DELIVERY_TYPE = "delivery_type";
        public static final String FORIEGN_MINISTRY_INVERT_NO = "foriegn_ministry_invert_no";
        public static final String PASSPORT_ATTACH_PATH = "passport_attach_path";
        public static final String PASSPORT_BACK_ATTACH_PATH = "passport_back_attach_path";
        public static final String NIC_ATTACH_PATH = "nic_attach_path";
        public static final String NIC_BACK_ATTACH_PATH = "nic_back_attach_path";
        public static final String BIRTH_CERTIFICATE_PATH = "birth_certificate_path";
        public static final String BIRTH_CERTIFICATE_BACK_PATH = "birth_certificate_back_path";
        public static final String REFERRED_THROUGH_BEREAU = "referred_through_bereau";
        public static final String LETTER_OF_REFERENCE_PATH = "letter_of_reference_path";
        public static final String APPLICATION_CLEARANCE_STATUS = "application_clearance_status";
        public static final String APPLICATION_REVIEW_STATUS = "application_review_status";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_DATE_TIME = "updated_date_time";
        public static final String POL_STATUS = "pol_status";
        public static final String CID_STATUS = "cid_status";
        public static final String DHA_REVIEW_STATUS = "dha_review_status";
        public static final String TID_STATUS = "tid_status";
        public static final String SIS_STATUS = "sis_status";
        public static final String NIC_STATUS = "nic_status";
        public static final String IMI_STATUS = "imi_status";
        public static final String PHQ_RECORD_LOCK_STATUS = "phq_record_lock_status";
        public static final String CID_RECORD_LOCK_STATUS = "cid_record_lock_status";
        public static final String TID_RECORD_LOCK_STATUS = "tid_record_lock_status";
        public static final String SIS_RECORD_LOCK_STATUS = "sis_record_lock_status";
        public static final String NIC_RECORD_LOCK_STATUS = "nic_record_lock_status";
        public static final String IMI_RECORD_LOCK_STATUS = "imi_record_lock_status";
        public static final String CO_APPROVED = "co_approved";
        public static final String OIC_APPROVED = "oic_approved";
        public static final String ASP_APPROVED = "asp_approved";
        public static final String DHA_APPROVED = "dha_approved";
        public static final String DIG_APPROVED = "dig_approved";
        public static final String CERTIFICATE_SERIAL_NO = "certificate_serial_no";
        public static final String APPLICATION_QUEUE = "application_queue";
        public static final String REG_POST_NO = "reg_post_no";
        public static final String CERTIFICATE_POSTED_DATE = "certificate_posted_date";
        public static final String IP_ADDRESS = "ip_address";
        public static final String USER_FULL_NAME = "user_full_name";
        public static final String USER_EMAIL = "user_email";
        public static final String AUTH_PROVIDER = "auth_provider";
        public static final String CERTIFICATE_PRINTED_STATUS = "certificate_printed_status";
        public static final String CERTIFICATE_ISSUE_DATE = "certificate_issue_date";
        public static final String APPLICATION_TYPE = "application_type";
        public static final String CERTIFICATE_AUTH_PERSON_ID = "certificate_auth_person_id";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String VERSION_ID = "version_id";
        public static final String NOTIFICATION_EMAIL_SENT_STATUS = "notification_email_sent_status";
        public static final String NIC_LETTER_ISSUE_STATUS = "nic_letter_issue_status";
        public static final String ADDRESS_PRINTED_STATUS = "address_printed_status";
        public static final String CERTIFICATE_TYPE = "certificate_type";
        public static final String APPROVAL_LETTER_PATH = "approval_letter_path";
        public static final String RECOMMENDED_OFFICER_NAME = "recommended_officer_name";
        public static final String SUBMITTED_DATE = "submitted_date";
        public static final String IS_UPLOADED_RECORD = "is_uploaded_record";
        public static final String NEW_NIC_ATTACH_PATH = "new_nic_attach_path";
        public static final String NEW_NIC_BACK_ATTACH_PATH = "new_nic_back_attach_path";
        public static final String AFFIDAVIT_ATTACH_PATH = "affidavit_attach_path";
        public static final String SELECTED_NAME_OPTION = "selected_name_option";
        public static final String CITIZEN_APP_VIEW_STATUS = "citizen_application_view_status";
        public static final String ASP_REVIEW_STATUS = "asp_review_status";

        private APPLICATION_COL() {
        }
    }

    // column names of APPLICATION_AUDIT
    public class APPLICATION_AUDIT_COL {

        public static final String ID = "id";
        public static final String APPLICATION_ID = "application_id";
        public static final String REFERENCE_NO = "reference_no";
        public static final String PREVIOUS_REFERENCE_NO = "previous_reference_no";
        public static final String NATIONALITY_ID = "nationality_id";
        public static final String NATIONALITY = "nationality";
        public static final String CITIZEN_OF_LANKA = "citizen_of_lanka";
        public static final String CITIZENSHIP_OBTAINED_DATE = "citizenship_obtained_date";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String AGE = "age";
        public static final String NIC = "nic";
        public static final String PASSPORT = "passport";
        public static final String COUNTRY_ID = "country_id";
        public static final String COUNTRY_NAME = "country_name";
        public static final String HIGH_COMMISION_REFERENCE_ID = "high_commision_reference_id";
        public static final String HIGH_COMMISION_REFERENCE = "high_commision_reference";
        public static final String APPLICANT_NAME_AS_NIC = "applicant_name_as_nic";
        public static final String APPLICANT_NAME_AS_PASSPORT = "applicant_name_as_passport";
        public static final String PASSPORT_ISSUE_DATE = "passport_issue_date";
        public static final String PRESENT_ADDRESS_LOCAL = "present_address_local";
        public static final String PRESENT_ADDRESS_OVERSEAS = "present_address_overseas";
        public static final String SEX = "sex";
        public static final String APPLICANT_STATUS = "applicant_status";
        public static final String OCCUPATION = "occupation";
        public static final String PURPOSE = "purpose";
        public static final String PREVIOUS_CERTIFICATE_APPLY = "previous_certificate_apply";
        public static final String PREVIOUS_CERTIFICATE_COUNTRY_ID = "previous_certificate_country_id";
        public static final String PREVIOUS_CERTIFICATE_COUNTRY_NAME = "previous_certificate_country_name";
        public static final String PREVIOUS_CERTIFICATE_ISSUE_STATUS = "previous_certificate_issue_status";
        public static final String PREVIOUS_CERTIFICATE_REFERENCE_NO = "previous_certificate_reference_no";
        public static final String PREVIOUS_CERTIFICATE_ISSUE_DATE = "previous_certificate_issue_date";
        public static final String AUTHORIZED_HANDOVER_PERSON = "authorized_handover_person";
        public static final String AUTHORIZED_HANDOVER_PERSON_NAME = "authorized_handover_person_name";
        public static final String AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT = "authorized_handover_person_nic_passport";
        public static final String HIGH_COMMISION_REFERENCE_ADDRESS = "high_commision_reference_address";
        public static final String CERTIFICATE_POST_ADDRESS_LINE_1 = "certificate_post_address_line_1";
        public static final String CERTIFICATE_POST_ADDRESS_LINE_2 = "certificate_post_address_line_2";
        public static final String CERTIFICATE_POST_ADDRESS_CITY = "certificate_post_address_city";
        public static final String CERTIFICATE_POST_ADDRESS_STATE = "certificate_post_address_state";
        public static final String CERTIFICATE_POST_ADDRESS_POSTAL = "certificate_post_address_postal";
        public static final String CERTIFICATE_POST_ADDRESS_COUNTRY = "certificate_post_address_country";
        public static final String CERTIFICATE_POST_ADDRESS_COUNTRY_NAME = "certificate_post_address_country_name";
        public static final String MOBILE_COUNTRY_CODE_ID = "mobile_country_code_id";
        public static final String MOBILE_COUNTRY_CODE = "mobile_country_code";
        public static final String MOBILE_NO = "mobile_no";
        public static final String EMAIL = "email";
        public static final String SPOUSE_FULL_NAME = "spouse_full_name";
        public static final String SPOUSE_ADDRESS = "spouse_address";
        public static final String SPOUSE_NATIONALITY = "spouse_nationality";
        public static final String SPOUSE_PASSPORT = "spouse_passport";
        public static final String SPOUSE_NIC = "spouse_nic";
        public static final String DELIVERY_TYPE = "delivery_type";
        public static final String FORIEGN_MINISTRY_INVERT_NO = "foriegn_ministry_invert_no";
        public static final String PASSPORT_ATTACH_PATH = "passport_attach_path";
        public static final String PASSPORT_BACK_ATTACH_PATH = "passport_back_attach_path";
        public static final String NIC_ATTACH_PATH = "nic_attach_path";
        public static final String NIC_BACK_ATTACH_PATH = "nic_back_attach_path";
        public static final String BIRTH_CERTIFICATE_PATH = "birth_certificate_path";
        public static final String BIRTH_CERTIFICATE_BACK_PATH = "birth_certificate_back_path";
        public static final String REFERRED_THROUGH_BEREAU = "referred_through_bereau";
        public static final String LETTER_OF_REFERENCE_PATH = "letter_of_reference_path";
        public static final String APPLICATION_CLEARANCE_STATUS = "application_clearance_status";
        public static final String APPLICATION_REVIEW_STATUS = "application_review_status";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_DATE_TIME = "updated_date_time";
        public static final String POL_STATUS = "pol_status";
        public static final String CID_STATUS = "cid_status";
        public static final String TID_STATUS = "tid_status";
        public static final String SIS_STATUS = "sis_status";
        public static final String NIC_STATUS = "nic_status";
        public static final String IMI_STATUS = "imi_status";
        public static final String PHQ_RECORD_LOCK_STATUS = "phq_record_lock_status";
        public static final String CID_RECORD_LOCK_STATUS = "cid_record_lock_status";
        public static final String TID_RECORD_LOCK_STATUS = "tid_record_lock_status";
        public static final String SIS_RECORD_LOCK_STATUS = "sis_record_lock_status";
        public static final String NIC_RECORD_LOCK_STATUS = "nic_record_lock_status";
        public static final String IMI_RECORD_LOCK_STATUS = "imi_record_lock_status";
        public static final String CO_APPROVED = "co_approved";
        public static final String OIC_APPROVED = "oic_approved";
        public static final String ASP_APPROVED = "asp_approved";
        public static final String DHA_APPROVED = "dha_approved";
        public static final String DIG_APPROVED = "dig_approved";
        public static final String CERTIFICATE_SERIAL_NO = "certificate_serial_no";
        public static final String APPLICATION_QUEUE = "application_queue";
        public static final String REG_POST_NO = "reg_post_no";
        public static final String CERTIFICATE_POSTED_DATE = "certificate_posted_date";
        public static final String IP_ADDRESS = "ip_address";
        public static final String USER_FULL_NAME = "user_full_name";
        public static final String USER_EMAIL = "user_email";
        public static final String AUTH_PROVIDER = "auth_provider";
        public static final String CERTIFICATE_PRINTED_STATUS = "certificate_printed_status";
        public static final String CERTIFICATE_ISSUE_DATE = "certificate_issue_date";
        public static final String APPLICATION_TYPE = "application_type";
        public static final String CERTIFICATE_AUTH_PERSON_ID = "certificate_auth_person_id";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String VERSION_ID = "version_id";
        public static final String NOTIFICATION_EMAIL_SENT_STATUS = "notification_email_sent_status";
        public static final String NIC_LETTER_ISSUE_STATUS = "nic_letter_issue_status";
        public static final String ADDRESS_PRINTED_STATUS = "address_printed_status";
        public static final String CERTIFICATE_TYPE = "certificate_type";
        public static final String APPROVAL_LETTER_PATH = "approval_letter_path";
        public static final String RECOMMENDED_OFFICER_NAME = "recommended_officer_name";
        public static final String SUBMITTED_DATE = "submitted_date";

        private APPLICATION_AUDIT_COL() {
        }
    }

    // column names of TRANSACTION
    public class TRANSACTION_COL {

        public static final String TRANSACTION_ID = "transaction_id";
        public static final String TRANSACTION_REFERENCE_NO = "transaction_reference_no";
        public static final String CHEQUE_NO = "cheque_no";
        public static final String ACCOUNT_NO = "account_no";
        public static final String ACCOUNT_HOLDER_NAME = "account_holder_name";
        public static final String BOOK_RECEIPT_NO = "book_receipt_no";
        public static final String DESCRIPTION = "description";
        public static final String APPLICATION_FEE = "application_fee";
        public static final String POSTAGE_FEE = "postage_fee";
        public static final String SERVICE_FEE = "service_fee";
        public static final String CONVENIENCE_FEE = "convenience_fee";
        public static final String TOTAL_FEE = "total_fee";
        public static final String PAYMENT_DATE = "payment_date";
        public static final String PAYMENT_STATUS = "payment_status";
        public static final String PAYMENT_GATEWAY_NAME = "payment_gateway_name";
        public static final String PAYMENT_INITIATED_TIME = "payment_initiated_time";
        public static final String PAYMENT_CONFIRMED_TIME = "payment_confirmed_time";
        public static final String PAYMENT_TYPE = "payment_type";
        public static final String PAYMENT_MODE = "payment_mode";
        public static final String CREATED_DATE = "created_date";
        public static final String VERSION_ID = "version_id";
        public static final String APPLICATION_ID = "application_id";
        public static final String USER_FULL_NAME = "user_full_name";
        public static final String USER_EMAIL = "user_email";
        public static final String AUTH_PROVIDER = "auth_provider";

        private TRANSACTION_COL() {
        }
    }

    // column names of REQUEST_CLARIFICATION
    public class REQUEST_CLARIFICATION_COL {

        public static final String REQUEST_CLARIFICATION_ID = "request_clarification_id";
        public static final String NIC = "nic";
        public static final String NIC_MESSAGE = "nic_message";
        public static final String PASSPORT = "passport";
        public static final String PASSPORT_MESSAGE = "passport_message";
        public static final String VERIFY_NAME = "verify_name";
        public static final String NAME_MESSAGE = "name_message";
        public static final String VERIFY_DATE_OF_BIRTH = "verify_date_of_birth";
        public static final String DATE_OF_BIRTH_MESSAGE = "date_of_birth_message";
        public static final String REFERENCE_NO = "reference_no";
        public static final String NIC_PATH = "nic_path";
        public static final String NIC_PATH_BACK = "nic_path_back";
        public static final String NIC_ACCEPT_STATUS = "nic_accept_status";
        public static final String PASSPORT_PATH = "passport_path";
        public static final String PASSPORT_PATH_BACK = "passport_path_back";
        public static final String PASSPORT_ACCEPT_STATUS = "passport_accept_status";
        public static final String NAME = "name";
        public static final String NAME_ACCEPT_STATUS = "name_accept_status";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String DATE_OF_BIRTH_ACCEPT_STATUS = "date_of_birth_accept_status";
        public static final String COMMENT = "comment";
        public static final String REQUESTED_USER_ID = "requested_user_id";
        public static final String REQUESTED_USER_NAME = "requested_user_name";
        public static final String REQUESTED_DATE_TIME = "requested_date_time";
        public static final String RESUBMITTED_USER_ID = "resubmitted_user_id";
        public static final String RESUBMITTED_USER_NAME = "resubmitted_user_name";
        public static final String RESUBMITTED_DATE_TIME = "resubmitted_date_time";
        public static final String REVIEWED_USER_ID = "reviewed_user_id";
        public static final String REVIEWED_USER_NAME = "reviewed_user_name";
        public static final String REVIEWED_DATE_TIME = "reviewed_date_time";
        public static final String APPLICATION_ID = "application_id";

        private REQUEST_CLARIFICATION_COL() {
        }
    }

    // column names of ADDRESS
    public class ADDRESS_COL {

        public static final String ADDRESS_ID = "address_id";
        public static final String ADDRESS = "address";
        public static final String POLICE_AREA_ID = "police_area_id";
        public static final String POLICE_AREA = "police_area";
        public static final String FROM_DATE = "from_date";
        public static final String TO_DATE = "to_date";
        public static final String FROM_MESSAGE = "from_message";
        public static final String FROM_SENT_BY = "from_sent_by";
        public static final String FROM_SENT_DATE = "from_sent_date";
        public static final String FROM_RECEIVE_BY = "from_receive_by";
        public static final String FROM_CREATED_DATE_TIME = "from_created_date_time";
        public static final String RESPONSE_MESSAGE = "response_message";
        public static final String RESPONSE_SENT_BY = "response_sent_by";
        public static final String RESPONSE_SENT_DATE = "response_sent_date";
        public static final String RESPONSE_SENT_TO = "response_sent_to";
        public static final String RESPONSE_CREATED_DATE_TIME = "response_created_date_time";
        public static final String APPLICATION_ID = "application_id";
        public static final String POLICE_STATUS = "police_status";
        public static final String TYPE = "type";
        public static final String POLICE_RECORD_LOCK_STATUS = "police_record_lock_status";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_DATE_TIME = "updated_date_time";
        public static final String VERSION_ID = "version_id";

        private ADDRESS_COL() {
        }
    }

    // column names of ADDRESS_AUDIT
    public class ADDRESS_AUDIT_COL {

        public static final String ID = "id";
        public static final String ADDRESS_ID = "address_id";
        public static final String ADDRESS = "address";
        public static final String POLICE_AREA_ID = "police_area_id";
        public static final String POLICE_AREA = "police_area";
        public static final String FROM_DATE = "from_date";
        public static final String TO_DATE = "to_date";
        public static final String FROM_MESSAGE = "from_message";
        public static final String FROM_SENT_BY = "from_sent_by";
        public static final String FROM_SENT_DATE = "from_sent_date";
        public static final String FROM_RECEIVE_BY = "from_receive_by";
        public static final String FROM_CREATED_DATE_TIME = "from_created_date_time";
        public static final String RESPONSE_MESSAGE = "response_message";
        public static final String RESPONSE_SENT_BY = "response_sent_by";
        public static final String RESPONSE_SENT_DATE = "response_sent_date";
        public static final String RESPONSE_SENT_TO = "response_sent_to";
        public static final String RESPONSE_CREATED_DATE_TIME = "response_created_date_time";
        public static final String APPLICATION_ID = "application_id";
        public static final String POLICE_STATUS = "police_status";
        public static final String TYPE = "type";
        public static final String POLICE_RECORD_LOCK_STATUS = "police_record_lock_status";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_DATE_TIME = "updated_date_time";
        public static final String VERSION_ID = "version_id";

        private ADDRESS_AUDIT_COL() {
        }
    }

    // column names of ADDRESS_AUDIT
    public class ADDRESS_TEMP_COL {

        public static final String ADDRESS_TEMP_ID = "address_temp_id";
        public static final String ADDRESS_ID = "address_id";
        public static final String ADDRESS = "address";
        public static final String ADDRESS_STATUS = "address_status";
        public static final String POLICE_AREA_ID = "police_area_id";
        public static final String POLICE_AREA = "police_area";
        public static final String POLICE_AREA_STATUS = "police_area_status";
        public static final String FROM_DATE = "from_date";
        public static final String TO_DATE = "to_date";
        public static final String STAY_PERIOD_STATUS = "stay_period_status";
        public static final String COMMENT = "comment";
        public static final String ACTIVE_STATUS = "active_status";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_DATE = "updated_date";


        private ADDRESS_TEMP_COL() {
        }
    }


    // column names of CHANGE_AUDIT
    public class CHANGE_AUDIT_COL {

        public static final String ID = "id";
        public static final String APPLICATION_ID = "application_id";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_USER_DATE_TIME = "updated_user_date_time";
        public static final String COMMENT = "comment";

        private CHANGE_AUDIT_COL() {
        }
    }

    // column names of COUNTRY
    public class COUNTRY_COL {

        public static final String ID = "id";
        public static final String COUNTRY_CODE = "country_code";
        public static final String COUNTRY_NAME = "country_name";
        public static final String NATIONALITY = "nationality";
        public static final String MOBILE_COUNTRY_CODE = "mobile_country_code";

        private COUNTRY_COL() {
        }
    }

    // column names of COMMISSIONER
    public class COMMISSIONER_COL {

        public static final String ID = "id";
        public static final String CEC_NAME = "cec_name";
        public static final String CEC_TYPE = "cec_type";
        public static final String CEC_ADDRESS = "cec_address";
        public static final String ACTIVE_STATUS = "active_status";
        public static final String COUNTRY_ID = "country_id";
        public static final String ADDRESSEE = "addressee";

        private COMMISSIONER_COL() {
        }
    }

    // column names of POLICE_AREA
    public class POLICE_AREA_COL {

        public static final String ID = "id";
        public static final String POLICE_AREA = "police_area";

        private POLICE_AREA_COL() {
        }
    }

    // column names of COMMENTS
    public class COMMENTS_COL {

        public static final String COMMENT_ID = "comment_id";
        public static final String COMMENT = "comment";
        public static final String CREATED_USER_ID = "created_user_id";
        public static final String CREATED_USER_NAME = "created_user_name";
        public static final String CREATED_USER_ROLE = "created_user_role";
        public static final String CREATED_DATE_TIME = "created_date_time";
        public static final String COMMENT_TYPE = "comment_type";
        public static final String APPLICATION_ID = "application_id";
        public static final String ADDRESS_ID = "address_id";

        private COMMENTS_COL() {
        }
    }


    // column names of BLACK_LIST_COL
    public class BLACK_LIST_COL {

        public static final String BLACK_LIST_ID = "black_list_id";
        public static final String APPLICATION_REFERENCE_NUMBER = "application_reference_number";
        public static final String NIC = "nic";
        public static final String NEW_NIC = "new_nic";
        public static final String PASSPORT = "passport";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String TEL = "tel";
        public static final String CREATED_DATE = "created_date";
        public static final String APPLICATION_ID = "application_id";
        public static final String COMMENT = "comment";

        private BLACK_LIST_COL() {
        }
    }


    // column names of BLACK_LIST_COL
    public class CERTIFICATE_AUTH_PERSON_COL {

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String EFFECTIVE_START_DATE = "effective_start_date";
        public static final String EFFECTIVE_END_DATE = "effective_end_date";
        public static final String DESIGNATION = "designation";
        public static final String ADDRESS = "address";

        private CERTIFICATE_AUTH_PERSON_COL() {
        }
    }

    // column names of BLACK_LIST_COL
    public class ADDRESS_CHANGE_AUDIT_COL {

        public static final String ID = "id";
        public static final String ADDRESS_ID = "address_id";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String UPDATED_USER_DATE_TIME = "updated_user_date_time";
        public static final String COMMENT = "comment";

        private ADDRESS_CHANGE_AUDIT_COL() {
        }
    }


    // column names of NOTIFICATION_COL
    public class NOTIFICATION_COL {

        public static final String NOTIFICATION_ID = "notification_id";
        public static final String APPLICATION_ID = "application_id";
        public static final String ADDRESS_ID = "address_id";
        public static final String SENDER_NAME = "sender_name";
        public static final String SENDER_ADDRESS = "sender_address";
        public static final String RECIEVER_NAME = "reciever_name";
        public static final String RECIEVER_ADDRESS = "reciever_address";
        public static final String MESSAGE_CONTENT = "message_content";
        public static final String NOTIFICATION_TYPE = "notification_type";
        public static final String SENT_STATUS = "sent_status";
        public static final String UPDATED_DATE = "updated_date";
        public static final String UPDATED_USER_ID = "updated_user_id";
        public static final String UPDATED_USER_NAME = "updated_user_name";
        public static final String COMMENT = "comment";

        private NOTIFICATION_COL() {
        }
    }

    // column names of APPLICATION_CLEARANCE_DATE_COL
    public class APPLICATION_CLEARANCE_DATE_COL {

        public static final String ID = "id";
        public static final String APPLICATION_ID = "application_id";
        public static final String ADDRESS_ID = "address_id";
        public static final String DEPARTMENT = "department";
        public static final String SENT_DATE = "sent_date";
        public static final String RESPONSED_DATE = "responsed_date";
        public static final String PRINTED_STATUS = "printed_status";
        public static final String SENT_USER_ID = "sent_user_id";
        public static final String RESPONSED_USER_ID = "responsed_user_id";
        public static final String COMMENT = "comment";
        public static final String TYPE = "type";

        private APPLICATION_CLEARANCE_DATE_COL() {
        }
    }

    // column names of APPLICATION_MODIFIED_DATES_COL
    public class APPLICATION_MODIFIED_DATES_COL {

        public static final String ID = "id";
        public static final String APPLICATION_ID = "application_id";
        public static final String MODIFIED_DATE = "modified_date";
        public static final String DATE_TYPE = "date_type";
        public static final String MODIFICATION = "modification";
        public static final String MODIFIED_USER_ID = "modified_user_id";
        public static final String MODIFIED_USER_NAME = "modified_user_name";

        private APPLICATION_MODIFIED_DATES_COL() {
        }
    }

    public static class QUERY {

        public static String UPDATE_CURRENT_SEQUENCE_NUMBER = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.SEQUENCE_NUMBER_MASTER)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(SEQUENCE_NUMBER_MASTER_COL.CURRENT_SEQUENCE_NUMBER).append(EQUALS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(SEQUENCE_NUMBER_MASTER_COL.CURRENT_SEQUENCE_NUMBER).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(SEQUENCE_NUMBER_MASTER_COL.SEQUENCE_NUMBER_MASTER_ID).append("=?").toString();

        public static String SELECT_NEXT_SEQUENCE_NUMBER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.SEQUENCE_NUMBER_MASTER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(SEQUENCE_NUMBER_MASTER_COL.SEQUENCE_NUMBER_MASTER_ID).append("=?").toString();


        public static String SELECT_APPLICATOIN = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(LIKE).append("?").toString();

        public static String SELECT_CITIZEN_APPLICATOIN = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_APP_VIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("FALSE")
                .toString();

        public static String DISABLE_CITIZEN_APPLICATION_VIEW = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_APP_VIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .toString();




        public static String SELECT_APPLICATOIN_AUTH = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append("=?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append("is not null")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(">")
                .append("ADDDATE(CURDATE(), INTERVAL -1 YEAR)").toString();


        public static String VERIFY_APPLICATOIN = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.NIC).append("=?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1=?")
                .append(RIGHT_BRACKET).append(EMPTY_SPACE)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.PASSPORT).append("=?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1=?")
                .append(RIGHT_BRACKET).append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.COUNTRY_ID)
                .append("=?").append(EMPTY_SPACE)
                .append(RIGHT_BRACKET).append(EMPTY_SPACE)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
//
//                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.REFERENCE_NO)
//                .append(" is not null").append(EMPTY_SPACE)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.NEW_NIC).append("=?").append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1=?")
//                .append(RIGHT_BRACKET).append(EMPTY_SPACE)
                .append(RIGHT_BRACKET).toString();

        public static String VERIFY_APPLICATOIN_BY_DEPARTMENT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append("=?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1=?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append("=?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1=?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_ID).append("=?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS)
                .append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append("=?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1=?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();


        public static String ADD_APPLICATION = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_OF_LANKA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZENSHIP_OBTAINED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DATE_OF_BIRTH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_LOCAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SEX).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OCCUPATION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PURPOSE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DELIVERY_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.FORIEGN_MINISTRY_INVERT_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_BACK_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_BACK_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_BACK_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERRED_THROUGH_BEREAU).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.LETTER_OF_REFERENCE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IP_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTH_PROVIDER).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NOTIFICATION_EMAIL_SENT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ADDRESS_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SUBMITTED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_BACK_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SELECTED_NAME_OPTION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AFFIDAVIT_ATTACH_PATH)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,0,0,0,NOW(),?, ?, ?, ?, ?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_COMPLETED_APPLICATION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TRANSACTION_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_APPLICATION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_OF_LANKA).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZENSHIP_OBTAINED_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DATE_OF_BIRTH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ISSUE_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_LOCAL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SEX).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OCCUPATION).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PURPOSE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.EMAIL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_FULL_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NATIONALITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DELIVERY_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.FORIEGN_MINISTRY_INVERT_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_BACK_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERRED_THROUGH_BEREAU).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.LETTER_OF_REFERENCE_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IP_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_FULL_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTH_PROVIDER).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_AUTH_PERSON_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TRANSACTION_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SELECTED_NAME_OPTION).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AFFIDAVIT_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.VERSION_ID).append(" + 1")
                .append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();


        public static String SEARCH_APPLICATION_VERIFICATION = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(NOT_EQUALS).append("''")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.NIC)
                .append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(NOT_EQUALS).append(EMPTY_SPACE).append("?").append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(NOT_EQUALS).append(EMPTY_SPACE).append("?").append(EMPTY_SPACE)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.NEW_NIC)
                .append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(ORDER_BY).append(EMPTY_SPACE).append("TIMESTAMP").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET)
                .toString();

        public static String UPDATE_PHQ_RECORD_LOCK_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ADDRESS_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append("=?").toString();

        public static String SELECT_ALL_ADDRESS_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append("=?").toString();

        public static String SELECT_PHQ_LOCK_STATUS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COUNTRY_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COUNTRY).toString();

        public static String SELECT_COUNTRY_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COUNTRY)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COUNTRY_COL.ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMISSIONER_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMISSIONER_BY_COUNTRY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.COUNTRY_ID).append(EQUALS).append("?").toString();

        public static String SELECT_POLICE_AREA_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.POLICE_AREA).toString();

        public static String ADD_ADDRESS = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_ADDRESS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_ADDRESS_FOR_POLICE_MESSAGE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?")
                .toString();

        public static String DELETE_ADDRESS_BY_ID = new StringBuilder(DELETE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append("=?").toString();

        public static String DELETE_ADDRESS_BY_APPLICATION_ID = new StringBuilder(DELETE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append("=?").toString();

        public static String ADD_ADDRESS_AUDIT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_AREA_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(SELECT)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();


        public static String UPDATE_REVIEW_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_REVIEW_STATUS_AND_CLEARANCE_STATUS_ON_REJECT = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_VERIFIED_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_ADDRESS_POLICE_STATUS_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?").toString();

        public static String UPDATE_ADDRESS_POLICE_STATUS_BY_ADDRESS_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?").toString();

        public static String ADD_NEW_TRANSACTION = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.TRANSACTION)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TRANSACTION_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CHEQUE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_HOLDER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.BOOK_RECEIPT_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.DESCRIPTION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.APPLICATION_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.POSTAGE_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.SERVICE_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CONVENIENCE_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TOTAL_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_GATEWAY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_INITIATED_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_CONFIRMED_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_MODE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CREATED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.VERSION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.USER_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.USER_EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.AUTH_PROVIDER)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_TRANSACTION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.TRANSACTION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CHEQUE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_HOLDER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.BOOK_RECEIPT_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.DESCRIPTION).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.APPLICATION_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.POSTAGE_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.SERVICE_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CONVENIENCE_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TOTAL_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_GATEWAY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_INITIATED_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_CONFIRMED_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_MODE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.VERSION_ID).append(EQUALS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(TRANSACTION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TRANSACTION_ID).append(EQUALS).append("?")
                .toString();

        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(GRATERTHAN).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING)
                .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();


        public static String GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)


                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)


                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_ADDRESS_RECORD_LOCK_STATUS_POLICE_PHQ = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();


        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_CID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_TID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_SIS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_NIC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_IMI = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATOIN_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ADDRESS_LIST_BY_TYPE_POLICE_STATUS_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ADDRESS_LIST_BY_TYPE_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ADDRESS_LIST_BY_APPLICATION_ID_AND_POLICEAREA_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_PENDING_ADDRESS_LIST_BY_TYPE_LOCATION_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ADDRESS_LIST_BY_TYPE_LOCATION_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_ADDRESS_APPROVAL_STATUS_POLICE_PHQ = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_APPLICATION_APPROVAL_STATUS_CID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_APPROVAL_STATUS_TID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_APPROVAL_STATUS_SIS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_APPROVAL_STATUS_NIC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_APPROVAL_STATUS_IMI = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMENTS_LIST_BY_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String SELECT_COMMENTS_LIST_BY_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMENTS_LIST_BY_APPLICATION_ID_AND_TYPE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(COMMENTS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String SELECT_COMMENTS_LIST_BY_ADDRESS_ID_AND_TYPE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(COMMENTS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();


        public static String ADD_COMMENT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.CREATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.CREATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.CREATED_USER_ROLE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.ADDRESS_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,NOW(),?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_APPLICATION_APPROVAL_STATUS_POLICE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_CID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_TID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_SIS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_NIC = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_IMI = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_PHQ = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

        public static String GET_ALL_VERIFIED_APPLICATION_IDS_LOCKED_BY_POL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String SELECT_ADDRESS_BY_TYPE_AND_LOCKED_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_PHQ_USER_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("USER_ID_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_REQUEST_CLARIFICATION_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(REQUEST_CLARIFICATION_COL.REQUEST_CLARIFICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("REQUEST_CLARIFICATION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.REQUEST_CLARIFICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String ADD_REQUEST_CLARIFICATION = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.REQUEST_CLARIFICATION)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.VERIFY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.VERIFY_DATE_OF_BIRTH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_PATH_BACK).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_ACCEPT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_PATH_BACK).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_ACCEPT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME_ACCEPT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_ACCEPT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)

                .append(EMPTY_SPACE).append(LEFT_JOIN).append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(EMPTY_SPACE).append(ON)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)

                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_AREA_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append("IS NULL")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(GRATERTHAN).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING)
                .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();       
        
        public static String ADD_APPLICATION_TO_AUDIT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NATIONALITY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CITIZEN_OF_LANKA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CITIZENSHIP_OBTAINED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.DATE_OF_BIRTH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.AGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.HIGH_COMMISION_REFERENCE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.HIGH_COMMISION_REFERENCE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICANT_NAME_AS_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICANT_NAME_AS_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PASSPORT_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PRESENT_ADDRESS_LOCAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PRESENT_ADDRESS_OVERSEAS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SEX).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICANT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.OCCUPATION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PURPOSE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_APPLY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.AUTHORIZED_HANDOVER_PERSON).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.AUTHORIZED_HANDOVER_PERSON_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_CITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_STATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.MOBILE_COUNTRY_CODE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.MOBILE_COUNTRY_CODE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.MOBILE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SPOUSE_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SPOUSE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SPOUSE_NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SPOUSE_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SPOUSE_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.DELIVERY_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.FORIEGN_MINISTRY_INVERT_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PASSPORT_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NIC_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.BIRTH_CERTIFICATE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.REFERRED_THROUGH_BEREAU).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.LETTER_OF_REFERENCE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICATION_CLEARANCE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICATION_REVIEW_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.POL_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.TID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SIS_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NIC_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.IMI_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.PHQ_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.TID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SIS_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NIC_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.IMI_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CO_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.OIC_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.ASP_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.DHA_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.DIG_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_SERIAL_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICATION_QUEUE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.REG_POST_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_POSTED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.IP_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.USER_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.USER_EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.AUTH_PROVIDER).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPLICATION_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_AUTH_PERSON_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.TRANSACTION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.VERSION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NOTIFICATION_EMAIL_SENT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.NIC_LETTER_ISSUE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.ADDRESS_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.CERTIFICATE_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.APPROVAL_LETTER_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.RECOMMENDED_OFFICER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_AUDIT_COL.SUBMITTED_DATE)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(SELECT)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_OF_LANKA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZENSHIP_OBTAINED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DATE_OF_BIRTH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_LOCAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SEX).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OCCUPATION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PURPOSE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NATIONALITY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DELIVERY_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.FORIEGN_MINISTRY_INVERT_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERRED_THROUGH_BEREAU).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.LETTER_OF_REFERENCE_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POSTED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IP_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_FULL_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTH_PROVIDER).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_AUTH_PERSON_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TRANSACTION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NOTIFICATION_EMAIL_SENT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_LETTER_ISSUE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ADDRESS_PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPROVAL_LETTER_PATH).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SUBMITTED_DATE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE = new StringBuilder(SELECT)
		        .append(EMPTY_SPACE).append(ALL_FIELDS)
		        .append(EMPTY_SPACE).append(FROM)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(WHERE)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(CAST)
		        .append(LEFT_BRACKET)
		        .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		        .append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(CAST)
		        .append(LEFT_BRACKET)
		        .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		        .append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(LESSTHAN).append("?")
		        
		        .append(EMPTY_SPACE).append(ORDER_BY)
		        
		//        .append(EMPTY_SPACE).append("TIMESTAMP(").append(APPLICATION_COL.UPDATED_DATE_TIME).append(")")
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
		        
		        .append(EMPTY_SPACE).append(DECENDING)
		        .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();


        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE_POSTING = new StringBuilder(SELECT)
		        .append(EMPTY_SPACE).append(ALL_FIELDS)
		        .append(EMPTY_SPACE).append(FROM)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(WHERE)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(CAST)
		        .append(LEFT_BRACKET)
		        .append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		        .append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(CAST)
		        .append(LEFT_BRACKET)
		        .append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		        .append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(LESSTHAN).append("?")
		
		        .append(EMPTY_SPACE).append(AND)
		
		        .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EMPTY_SPACE).append(EQUALS).append("?")
		        
		        .append(EMPTY_SPACE).append(ORDER_BY)
		        
		//        .append(EMPTY_SPACE).append("TIMESTAMP(").append(APPLICATION_COL.UPDATED_DATE_TIME).append(")")
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
		        
		        .append(EMPTY_SPACE).append(DECENDING)
		        .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();
        
        public static String GET_TOTAL_ROW_COUNT_OF_SEARCHED_APPLICATIONS_FOR_CERTIFICATE_ISSUANCE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)


                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_CERTIFICATE_ISSUANCE_LOCK = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_RECORD_LOCK_STATUS_CERTIFICATE_ISSUANCE_UNLOCK = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String ADD_CHANGE_AUDIT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.COMMENT)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,NOW(),?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String COUNT_ALL_VERIFIED_APPLICATIONS_LOCKED_FOR_CERTIFICATE_ISSUANCE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_CO = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC_RJ_AS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_OIC_RJ_CN = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_ASP = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_ASP_GC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPROVAL_LETTER_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DHA_GC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_GC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DH = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DH_RJ_AS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DI_RJ_DH = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_DI = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_IO = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_CLEARENCE_CERTIFICATE_ISSUANCE_STATUS_AND_UNLOCK_PO = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POSTED_DATE).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_TRANSACTION_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.TRANSACTION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TRANSACTION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_USER_EMAIL_BY_REFERENCE_NO = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATION_BY_REFERENCE_NO_AND_EMAIL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.USER_EMAIL).append(EQUALS).append("?").toString();

        public static String SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.REQUEST_CLARIFICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REFERENCE_NO).append(EQUALS).append("?").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID).append(EMPTY_SPACE).append(ISNULL).append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID).append(EQUALS).append("?").append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_REQUEST_CLARIFICATION_BY_REFERENCE_NO_FOR_DEPT_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.REQUEST_CLARIFICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REFERENCE_NO).append(EQUALS).append("?").toString();


        public static String UPDATE_REQUEST_CLARIFICATION_BY_REFERENCE_NO = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.REQUEST_CLARIFICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.VERIFY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.VERIFY_DATE_OF_BIRTH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_MESSAGE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_PATH_BACK).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NIC_ACCEPT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_PATH_BACK).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.PASSPORT_ACCEPT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.NAME_ACCEPT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.DATE_OF_BIRTH_ACCEPT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.COMMENT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUESTED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.RESUBMITTED_DATE_TIME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REVIEWED_DATE_TIME).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.REQUEST_CLARIFICATION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(REQUEST_CLARIFICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATIO_FOR_CEARANCE_REPORT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME)
                .append(EMPTY_SPACE).append(BETWEEN)
                .append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append("?").toString();


        public static String ADD_NEW_BLACKLISTED_APP = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.BLACK_LIST)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.APPLICATION_REFERENCE_NUMBER).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.CREATED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NIC).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.PASSPORT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.TEL).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NEW_NIC)

                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,NOW(),?,?,?,?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_APPLICATION_REQUEST_FOR_UPDATE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DATE_OF_BIRTH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_EMAIL_SENT_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NOTIFICATION_EMAIL_SENT_STATUS).append(EQUALS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.NOTIFICATION_EMAIL_SENT_STATUS).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_BLACK_LIST_RECORD_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("BLACK_LIST_RECORD_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.BLACK_LIST)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NIC).append(EQUALS).append("?").append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.PASSPORT).append(EQUALS).append("?").append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NEW_NIC).append(EQUALS).append("?").
                        toString();


        public static String SEARCH_ApplicationdetailsReport = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(ORDER_BY).append(EMPTY_SPACE).append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(DECENDING).toString();

        public static String SEARCH_DailyTransactionReport = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TRANSACTION_REFERENCE_NO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TOTAL_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CONVENIENCE_FEE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_TYPE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.TRANSACTION).append(EMPTY_SPACE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EQUALS).append(DB_TABLE.TRANSACTION).append(DOT).append(TRANSACTION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append("date").append(LEFT_BRACKET).append(APPLICATION_COL.SUBMITTED_DATE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET).append(EMPTY_SPACE)
                .append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET).append(EMPTY_SPACE)

                .append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_TYPE).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append("1").append(EQUALS).append("?").append(RIGHT_BRACKET).append(EMPTY_SPACE)
                .append(AND).append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(" is not null").append(EMPTY_SPACE)

                .append(EMPTY_SPACE).append(ORDER_BY).append(EMPTY_SPACE).append("TIMESTAMP(payment_date)")
                .append(EMPTY_SPACE).append(DECENDING).toString();


        public static String SELECT_BLACKLIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.BLACK_LIST)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(BLACK_LIST_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(BLACK_LIST_COL.NAME).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(BLACK_LIST_COL.NAME).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append("1").append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).toString();

        public static String UPDATE_ADDRESS_PRINTED_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ADDRESS_PRINTED_STATUS).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.ADDRESS_PRINTED_STATUS).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_CERTIFICATE_AUTH_PERSON_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.CERTIFICATE_AUTH_PERSON)
                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.ID)
                .append(EMPTY_SPACE).append(ASCENDING)
                .toString();

        public static String SELECT_CURRENT_CERTIFICATE_AUTH_PERSON = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.CERTIFICATE_AUTH_PERSON)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append("?").append(EMPTY_SPACE).append(BETWEEN)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_START_DATE).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_END_DATE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_END_DATE)
                .append(EMPTY_SPACE).append(ISNULL).toString();

        public static String UPDATE_CERTIFICATE_PRINTED_STATUS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String ADD_ADDRESS_TO_AUDIT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(SELECT)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();


        public static String UPDATE_ADDRESS_RECORD_LOCK_STATUS_PHQ = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?")
                .toString();

        public static String SELECT_ADDRESS_BY_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String COUNT_ALL_ADDRESSES_LOCKED_BY_PHQ = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();


        public static String ADD_ADDRESS_CHANGE_AUDIT = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_CHANGE_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.COMMENT)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,NOW(),?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String ADD_ADDRESS_TEMP = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_TEMP)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ADDRESS_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.POLICE_AREA_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.POLICE_AREA_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.STAY_PERIOD_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ACTIVE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.UPDATED_DATE)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,NOW()")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String SELECT_ADDRESS_TEMP_BY_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_TEMP)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ADDRESS_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ACTIVE_STATUS).append(EQUALS).append("?").toString();

        public static String UPDATE_ADDRESS_CLERARENCE_POLICE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?")
                .toString();

        public static String DEACTIVATE_ADDRESS_TEMP_BY_ADDRESS_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_TEMP)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ACTIVE_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_TEMP_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_ADDRESS_BY_PHQ = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(ADDRESS_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(NOT_EQUALS).append("?")

                .toString();


        public static String COUNT_ALL_APPLICATOINS_WITH_ADVERSE_BY_NIC_PASSPORT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(NOT_EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(NOT_EQUALS).append("?")

                .toString();


        public static String UPDATE_APPLICATION_NIC_FILE_NAME_AND_UNLOCK = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_APPLICATION_NIC_NAME_AND_UNLOCK = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String UPDATE_ALL_APPLICATION_APPROVAL_STATUSES = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String ADD_ADDRESS_TO_AUDIT_BY_APPLICATION_ID = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_AUDIT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_AUDIT_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(SELECT)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_RECEIVE_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_MESSAGE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_BY).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_SENT_TO).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.RESPONSE_CREATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_ADDRESSES_APPROVAL_STATUSES_BY_APLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

//		public static String SELECT_CHANGE_AUDIT_BY_REFERENCE_NO=new StringBuilder(SELECT)
//				.append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME).append(SEPERATOR)
//				.append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.COMMENT).append(SEPERATOR)
//				.append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.UPDATED_USER_NAME).append(EMPTY_SPACE)
//				.append(EMPTY_SPACE).append(FROM)
//				.append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(SEPERATOR).append(DB_TABLE.APPLICATION)
//				.append(EMPTY_SPACE).append(WHERE)
//				.append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS)
//				.append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.APPLICATION_ID)
//				.append(EMPTY_SPACE).append(AND).append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
//				.toString();

        public static String UPDATE_CERTIFICATE_AUTH_PERSON = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.CERTIFICATE_AUTH_PERSON)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_END_DATE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.ID).append(EQUALS).append("?").toString();

        public static String ADD_CERTIFICATE_AUTH_PERSON = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.CERTIFICATE_AUTH_PERSON)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_START_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.EFFECTIVE_END_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.DESIGNATION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(CERTIFICATE_AUTH_PERSON_COL.ADDRESS)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String SELECT_COMMISSIONER_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("COMMISSIONER_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_NAME).append(EQUALS).append("?").append(AND)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_TYPE).append(EQUALS).append("?").append(AND)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_ADDRESS).append(EQUALS).append("?").append(AND)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.COUNTRY_ID).append(EQUALS).append("?")
                .toString();

        public static String ADD_COMMISSIONER = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_NAME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_ADDRESS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ACTIVE_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.COUNTRY_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ADDRESSEE)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();

        public static String UPDATE_COMMISSIONER = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.CEC_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ACTIVE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.COUNTRY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ADDRESSEE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ID).append(EQUALS).append("?").toString();

        public static String UPDATE_COMMENT_BY_TYPE_AND_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(COMMENTS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMISSIONER_LIST_BY_COUNTRY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.COUNTRY_ID).append(EQUALS).append("?")
                .toString();

        public static String DELETE_COMMISSIONER_BY_ID = new StringBuilder(DELETE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ID).append(EMPTY_SPACE).append(EQUALS).append("?").toString();

        public static String SELECT_COMMISSIONER_COUNT_IN_APPLICATION = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("HC_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_TO_BE_VERIFIED_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("NW_APPLICATION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .toString();

        public static String SELECT_RE_SUBMISSION_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("RC_APPLICATION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .toString();

        public static String SELECT_REVISION_UPDATES_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("RV_APPLICATION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?")
                .toString();

//		public static String SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT=new StringBuilder(SELECT)
//				.append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
//				.append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("EX_CLR_PEND_COUNT")
//				.append(EMPTY_SPACE).append(FROM)
//				.append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(EMPTY_SPACE).append(SEPERATOR)
//				.append(EMPTY_SPACE).append(LEFT_BRACKET).append(SELECT).append(EMPTY_SPACE).append("@clearancestatus := ").append("?").append(RIGHT_BRACKET)
//				.append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("clearancestatustmp")
//				.append(EMPTY_SPACE).append(WHERE)
//				.append(EMPTY_SPACE).append(CASE)
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='CID'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("'PN'")
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='TID'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("'PN'")
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='SIS'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("'PN'")
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='NIC'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("'PN'")
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='IMI'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("'PN'")
//					.append(EMPTY_SPACE).append(WHEN).append(EMPTY_SPACE).append("@clearancestatus='PHQ'").append(EMPTY_SPACE).append(THEN).append(EMPTY_SPACE)
//						.append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("'PN'").append(EMPTY_SPACE)
//				.append(EMPTY_SPACE).append(END)
//				.append(EMPTY_SPACE).append(AND)
//				.append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
//				.toString();

        public static String SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("EX_CLR_PEND_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .toString();


        public static String SELECT_EXTERNAL_CLEARANCE_PENDING_CID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT)
                .append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DISTINCT)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("EX_CLR_PEND_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .toString();

        public static String SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT_BY_DEPARTMENT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("EX_CLR_PEND_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(EMPTY_SPACE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(SELECT).append(EMPTY_SPACE).append("@clearancestatus := ").append("?").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("clearancestatustmp")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(CASE)
                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='CID'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='TID'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='SIS'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='NIC'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='IMI'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(WHEN)
                .append(EMPTY_SPACE).append("@clearancestatus='PHQ'")
                .append(EMPTY_SPACE).append(THEN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EMPTY_SPACE).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(END)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .toString();

//		public static String SELECT_POLICE_CLEARANCE_PENDING_COUNT=new StringBuilder(SELECT)
//				.append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
//				.append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("POLICE_CLEARANCE_PENDING_COUNT")
//				.append(EMPTY_SPACE).append(FROM)
//				.append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
//				.append(EMPTY_SPACE).append(WHERE)
//				.append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("'PN'").append(EMPTY_SPACE)
//				.append(EMPTY_SPACE).append(AND)
//				.append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
//				.toString();

        public static String SELECT_OIC_APPROVAL_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("OIC_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(NOT).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();


        public static String SELECT_OIC_APPROVAL_PENDING_COUNT_GREEN_CHANNAL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("OIC_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_ASP_APPROVAL_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("ASP_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(NOT).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_ASP_APPROVAL_PENDING_COUNT_GREEN_CHANNAL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("ASP_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_CERTIFICATE_PRINTING_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("CERTIFICATE_PRINTING_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(EQUALS).append("0").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(EMPTY_SPACE).append(ISNULL).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_CERTIFICATE_TOBE_POSTED_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("CERTIFICATE_TOBE_POSTED_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(GRATERTHAN).append("0").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.REG_POST_NO).append(EQUALS).append("''").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append(ISNULL).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_SUB = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'SUB'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'NW'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_ACC = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'ACC'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'").append(EMPTY_SPACE).append(AND)
//                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'PN'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'PN'").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'EI'").append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)                
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_VER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'VER'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'RC'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_REJ = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'REJ'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'RJ'")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'RM'")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'RJ'")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'BL'")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_DEL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'DEL'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'DC'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_APPLICATION_STATUS_CHECK_POS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append("'POS'").append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_STATUS")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(GRATERTHAN).append("0").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'IS'").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'").append(RIGHT_BRACKET).append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.REG_POST_NO).append(NOT_EQUALS).append("''").append(EMPTY_SPACE)
                .append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append(ISNOTNULL).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_APPLICATION_COUNT_BY_REFERENCE_NO = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATRION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?")
                .toString();

        public static String SELECT_CERTIFICATE_CHECKING_PENDING_ALL_NO_ADVERSE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("'NI'")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("'NI'")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("'AP'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .toString();

        public static String SELECT_APPLICATION_EXISTS_COUNT_NIC = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_EXISTS_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("'RJ'")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("'OI'").append(RIGHT_BRACKET)
                /*.append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")*/
                .toString();

        public static String SELECT_APPLICATION_EXISTS_COUNT_PPT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATION_EXISTS_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("'RJ'")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("'OI'").append(RIGHT_BRACKET)
				/*.append(EMPTY_SPACE).append(AND)
				.append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")*/
                .toString();

        public static String SELECT_CLEARANCE_CERTIFICATE_VERIFICATION = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(ALL_FIELDS).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("APPLICATRION_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(APPLICATION_COL.NIC).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR).append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EQUALS).append("?").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?").append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_CERIFICATE_SERIAL_NUMBER = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMENT_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_COMMENT_BY_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMENTS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMENTS_COL.COMMENT_ID).append(EQUALS).append("?").toString();

        public static String SELECT_COMMISSIONER_LIST_BY_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.COMMISSIONER)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(COMMISSIONER_COL.ID).append(EQUALS).append("?")
                .toString();

        public static String INSERT_APPLIACTION_CLEARENCE_DATE = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.SENT_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.TYPE)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES).append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,NOW(),?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String INSERT_APPLIACTION_CLEARENCE_DATE_POLICE = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.SENT_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.TYPE)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES).append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,?,?,NOW(),?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_APPLICATIONS_BY_ID_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(IN)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).toString();


        public static String SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_APPLICATION_CLEARANCE_DATES_BY_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID)
                .append(EMPTY_SPACE).append(EQUALS).append("?").toString();


        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_PRINT_LIST_POLICE = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)

                .append(EMPTY_SPACE).append(LEFT_JOIN).append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(EMPTY_SPACE).append(ON)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)

                .append(EMPTY_SPACE).append(LEFT_JOIN).append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(ON)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(EQUALS)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT)
                .append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_AREA_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append("IS NULL")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING)
                .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?,?").toString();


        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_DEPARTMENT_PRINT_LIST = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(LEFT_JOIN).append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(ON)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(EQUALS)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT)
                .append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.REFERENCE_NO)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)


                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NIC)
                .append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NEW_NIC)
                .append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.PASSPORT)
                .append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC)
                .append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT)
                .append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.SENT_DATE)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(DB_TABLE.APPLICATION_CLEARANCE_DATE).append(DOT).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.CID_STATUS)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.TID_STATUS)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.SIS_STATUS)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NIC_STATUS)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.IMI_STATUS)
                .append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.IS_UPLOADED_RECORD)
                .append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING)
                .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();


        public static String UPDATE_APPLICATION_PRINTED_STATUS_EXTERNAL_DEPT = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_APPLICATION_PRINTED_STATUS_EXTERNAL_DEPT_POLICE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_CLEARANCE_DATE_COL.PRINTED_STATUS).append(" +1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_APPLICATION_CLEARANCE_DATE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_USER_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EMPTY_SPACE).append(ISNULL)
                .toString();

        public static String UPDATE_ADDRESS_CLEARANCE_DATE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_USER_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.RESPONSED_DATE).append(EMPTY_SPACE).append(ISNULL)
                .toString();


        public static String INSERT_APPLICATION_MODIFIED_DATES = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_MODIFIED_DATES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.APPLICATION_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.MODIFIED_DATE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.DATE_TYPE).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.MODIFICATION).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.MODIFIED_USER_ID).append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.MODIFIED_USER_NAME)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES).append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?,NOW(),?,?,?,?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();

        public static String SELECT_ADDRESS_CHANGE_AUDITS_BY_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS_CHANGE_AUDIT)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_CHANGE_AUDIT_COL.ADDRESS_ID).append(EQUALS).append("?").toString();


        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_STATUS_VIEW = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)


                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC)
                .append(RIGHT_BRACKET).append(EMPTY_SPACE)
                .append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append(EMPTY_SPACE).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING).toString();

        public static String SELECT_APPLICATION_MODIFIED_DATES_BY_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATION_MODIFIED_DATE_BY_TYPE_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_MODIFIED_DATES)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.DATE_TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_MODIFIED_DATES_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATION_CLEARANCE_DATE_BY_DEPARTMENT_AND_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATION_CLEARANCE_DATE_BY_DEPARTMENT_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(EQUALS).append("?").toString();


        public static String SELECT_APPLICATION_CLEARANCE_DATE_BY_TYPE_DEPARTMENT_AND_ADDRESS_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        public static String SELECT_APPLICATION_CLEARANCE_DATE_BY_TYPE_DEPARTMENT_AND_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION_CLEARANCE_DATE)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.TYPE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.DEPARTMENT).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_CLEARANCE_DATE_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_CID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_IMI = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_NIC = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_POL = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_SIS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_EXTERNAL_CLEARANCE_STATUS_BY_APPLICATION_ID_TID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_ADDRESS_MODIFICATIONS = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_AREA).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.FROM_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TO_DATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(ADDRESS_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();

        //not deleting address from db, just updating its type
        public static String REMOVE_ADDRESS_FROM_APPLICATION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.UPDATED_USER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(ADDRESS_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(ADDRESS_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.ADDRESS_ID).append(EQUALS).append("?").toString();


        public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_POLICE_FORM = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(CAST)
                .append(LEFT_BRACKET)
                .append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
                .append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)

                .append(EMPTY_SPACE).append(AND)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

                .append(EMPTY_SPACE).append(ORDER_BY)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
                .append(EMPTY_SPACE).append(ASCENDING).toString();


        public static String UPDATE_APPLICATION_FROM_VERIFICATION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_LOCAL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS).append(EQUALS).append("?").append(SEPERATOR)

                //.append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(EQUALS).append("?").append(SEPERATOR)
                //.append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_COMPLETE_APPLICATION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)

                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NATIONALITY).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZEN_OF_LANKA).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CITIZENSHIP_OBTAINED_DATE).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.DATE_OF_BIRTH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AGE).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.HIGH_COMMISION_REFERENCE_ADDRESS).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ISSUE_DATE).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_LOCAL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PRESENT_ADDRESS_OVERSEAS).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.SEX).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICANT_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OCCUPATION).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PURPOSE).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_APPLY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_CERTIFICATE_ISSUE_DATE).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AUTHORIZED_HANDOVER_PERSON_NIC_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)


                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_1).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_LINE_2).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_CITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_STATE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_POSTAL).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POST_ADDRESS_COUNTRY_NAME).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE_ID).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_COUNTRY_CODE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.MOBILE_NO).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.EMAIL).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_FULL_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NATIONALITY).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_PASSPORT).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SPOUSE_NIC).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.DELIVERY_TYPE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.FORIEGN_MINISTRY_INVERT_NO).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.BIRTH_CERTIFICATE_BACK_PATH).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERRED_THROUGH_BEREAU).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.LETTER_OF_REFERENCE_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC_BACK_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NEW_NIC).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.AFFIDAVIT_ATTACH_PATH).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SELECTED_NAME_OPTION).append(EQUALS).append("?").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)

                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();

        public static String UPDATE_MANUAL_TRANSACTION = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.TRANSACTION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.PAYMENT_MODE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TOTAL_FEE).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.CHEQUE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.ACCOUNT_HOLDER_NAME).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.DESCRIPTION).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.BOOK_RECEIPT_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.VERSION_ID).append(EQUALS)
                .append(EMPTY_SPACE).append(LEFT_BRACKET).append(TRANSACTION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(TRANSACTION_COL.TRANSACTION_ID).append(EQUALS).append("?").toString();

        public static String UPDATE_APPLICATION_REFERENCE_NUMBER = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.UPDATED_DATE_TIME).append(EQUALS).append("NOW()").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();

        public static String SELECT_CHANGE_AUDIT_BY_APPLICATION_ID = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.UPDATED_USER_DATE_TIME).append(SEPERATOR)
                .append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.COMMENT).append(SEPERATOR)
                .append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT).append(DOT).append(CHANGE_AUDIT_COL.UPDATED_USER_NAME).append(EMPTY_SPACE)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.CHANGE_AUDIT)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(CHANGE_AUDIT_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();

        public static String ADD_POLICE_AREA = new StringBuilder(INSERT_INTO)
                .append(EMPTY_SPACE).append(DB_TABLE.POLICE_AREA)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(POLICE_AREA_COL.POLICE_AREA)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(VALUES)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append("?")
                .append(EMPTY_SPACE).append(RIGHT_BRACKET).toString();        

    }

    public static class QUERY_NEW {
      
    	public static String SELECT_DHA_APPROVAL_PENDING_COUNT = new StringBuilder(SELECT)
		        .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("DHA_APPROVAL_PENDING_COUNT")
		        .append(EMPTY_SPACE).append(FROM)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(WHERE)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("'DH'").append(EMPTY_SPACE)
		        .append(EMPTY_SPACE).append(AND)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
		        .append(EMPTY_SPACE).append(AND)
		        .append(EMPTY_SPACE).append(NOT).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'BL'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'RJ'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(AND)
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("'PN'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EMPTY_SPACE).append(ISNULL)
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		        .toString();

		public static String SELECT_DHA_APPROVAL_PENDING_COUNT_GREEN_CHANNAL = new StringBuilder(SELECT)
		        .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("DHA_APPROVAL_PENDING_COUNT")
		        .append(EMPTY_SPACE).append(FROM)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(WHERE)
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		        .append(EMPTY_SPACE).append(AND)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
		        .append(EMPTY_SPACE).append(AND)
		        .append(EMPTY_SPACE).append(LEFT_BRACKET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("'PN'")
		        .append(EMPTY_SPACE).append(OR)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EMPTY_SPACE).append(ISNULL)
		        .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		        .toString();
		
		public static String GET_APPLICATION_PREVIOUS_REFERENCE_NO_BY_REFERENCE_NO = new StringBuilder(SELECT)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.PREVIOUS_REFERENCE_NO)
		        .append(EMPTY_SPACE).append(FROM)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(WHERE)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EQUALS).append("?").toString();

        public static String SELECT_DIG_APPROVAL_PENDING_COUNT = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("DIG_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("'DI'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(EQUALS).append("'LT'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(NOT).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();
          
          public static String SELECT_DIG_APPROVAL_PENDING_COUNT_GREEN_CHANNAL = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(COUNT).append(LEFT_BRACKET).append(DISTINCT).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append("DIG_APPROVAL_PENDING_COUNT")
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("'GC'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EMPTY_SPACE).append(ISNOTNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("'DI'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("'VF'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_TYPE).append(EQUALS).append("'LT'")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("'PN'")
                .append(EMPTY_SPACE).append(OR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(RIGHT_BRACKET)
                .toString();


          
        public static String SELECT_ALL_VERIFIED_APPLICATIONS_FOR_DASHBOARD = new StringBuilder(SELECT)
              .append(EMPTY_SPACE).append(ALL_FIELDS)
              .append(EMPTY_SPACE).append(FROM)
              .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
              .append(EMPTY_SPACE).append(WHERE)
              .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(AND)
              .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
              .append(EMPTY_SPACE).append(AND)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EMPTY_SPACE).append(ISNULL)
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(ISNOTNULL)
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
              .append(EMPTY_SPACE).append(AND)
              .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(GRATERTHAN).append("?")
              .append(EMPTY_SPACE).append(ORDER_BY)
              .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
              .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();    
        


        public static String SELECT_ALL_APPLICATOINS_WITH_ADVERSES = new StringBuilder(SELECT)
        
              .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(SEPERATOR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(SEPERATOR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT)
        
              .append(EMPTY_SPACE).append(FROM)
              .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
              .append(EMPTY_SPACE).append(WHERE)
        
              .append(EMPTY_SPACE).append(NOT)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(ISNULL)
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
              .append(EMPTY_SPACE).append(AND)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(ISNULL)
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.PASSPORT).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
        
              .append(EMPTY_SPACE).append(AND)
        
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(LEFT_BRACKET)
              .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(OR)
              .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?")
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
              .append(EMPTY_SPACE).append(RIGHT_BRACKET)
        
              .toString();
    	
    	public static String SELECT_ALL_BLACK_LIST_RECORDS = new StringBuilder(SELECT)
              .append(EMPTY_SPACE).append(ALL_FIELDS)
              .append(EMPTY_SPACE).append(FROM)
              .append(EMPTY_SPACE).append(DB_TABLE.BLACK_LIST).toString();


        public static String REVERT_APPLICATION_TO_VERIFICATION_STAGE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EQUALS).append("?").append(SEPERATOR)
        
                //.append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EQUALS).append("?").append(SEPERATOR)
                //.append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_POSTED_DATE).append(EQUALS).append("?").append(SEPERATOR)
                //.append(EMPTY_SPACE).append(APPLICATION_COL.REG_POST_NO).append(EQUALS).append("?").append(SEPERATOR)
                //.append(EMPTY_SPACE).append(APPLICATION_COL.RECOMMENDED_OFFICER_NAME).append(EQUALS).append("?").append(SEPERATOR)
        
                //.append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_PRINTED_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                //.append(EMPTY_SPACE).append(APPLICATION_COL.CERTIFICATE_SERIAL_NO).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();
        
        public static String REVERT_APPLICATION_TO_PENDING_CLEARANCE_STAGE = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.POL_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_STATUS).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_STATUS).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.CO_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.OIC_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DIG_APPROVED).append(EQUALS).append("?").append(SEPERATOR)
        
                .append(EMPTY_SPACE).append(APPLICATION_COL.VERSION_ID).append(EQUALS).append(LEFT_BRACKET).append(APPLICATION_COL.VERSION_ID).append(" + 1").append(RIGHT_BRACKET)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();
        
        
        public static String SELECT_ALL_VERIFIED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EQUALS).append("?").toString();
        
        public static String UPDATE_DHA_STATUS_BY_USING_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.DHA_REVIEW_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
                .toString();
        
        public static String UPDATE_ASP_STATUS_BY_USING_APPLICATION_ID = new StringBuilder(UPDATE)
		        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		        .append(EMPTY_SPACE).append(SET)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.ASP_REVIEW_STATUS).append(EQUALS).append("?")
		        .append(EMPTY_SPACE).append(WHERE)
		        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?")
		        .toString();
    	
        //1. POLICE LOCKED
        public static String SELECT_ALL_LOCKED_ADDRESSES_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_LOCKED_ADDRESSES = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();

        //2. PHQ LOCKED
        public static String SELECT_ALL_PHQ_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_PHQ_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();

        //3. CID LOCKED
        public static String SELECT_ALL_CID_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_CID_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();

        //4. NIC LOCKED
        public static String SELECT_ALL_NIC_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_NIC_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();

        //5. SIS LOCKED
        public static String SELECT_ALL_SIS_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_SIS_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();


        //. TID LOCKED
        public static String SELECT_ALL_TID_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_TID_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();


        //7. IMI LOCKED
        public static String SELECT_ALL_IMI_LOCKED_APPLICATIONS_BY_USER = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EQUALS).append("?").toString();

        public static String SELECT_ALL_IMI_LOCKED_APPLICATIONS = new StringBuilder(SELECT)
                .append(EMPTY_SPACE).append(ALL_FIELDS)
                .append(EMPTY_SPACE).append(FROM)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(NOT_EQUALS).append("0")
                .append(EMPTY_SPACE).append(AND)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EMPTY_SPACE).append(ISNOTNULL).toString();


        public static String UNLOCK_ADDRESSES_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(ADDRESS_COL.POLICE_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String PHQ_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.PHQ_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String SIS_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.SIS_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String NIC_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.NIC_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String TID_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.TID_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String CID_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.CID_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();

        public static String IMI_UNLOCK_APPLICATION_BY_APPLICATION_ID = new StringBuilder(UPDATE)
                .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
                .append(EMPTY_SPACE).append(SET)
                .append(EMPTY_SPACE).append(APPLICATION_COL.IMI_RECORD_LOCK_STATUS).append(EQUALS).append("?")
                .append(EMPTY_SPACE).append(WHERE)
                .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID).append(EQUALS).append("?").toString();
        
      
       public static String SELECT_MAX_SEARCHED_APPLICATION_ID_FOR_CERTIFICATE_ISSUANCE = new StringBuilder(SELECT)
        .append(EMPTY_SPACE).append("MAX(").append(APPLICATION_COL.APPLICATION_ID).append(")")
        .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
        .append(EMPTY_SPACE).append(FROM)
        .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
        .append(EMPTY_SPACE).append(WHERE)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(CAST)
        .append(LEFT_BRACKET)
        .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
        .append(RIGHT_BRACKET)
        .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(CAST)
        .append(LEFT_BRACKET)
        .append(APPLICATION_COL.UPDATED_DATE_TIME).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
        .append(RIGHT_BRACKET)
        .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(LEFT_BRACKET)
        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(OR)
        .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
        .append(EMPTY_SPACE).append(RIGHT_BRACKET)

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

        .append(EMPTY_SPACE).append(AND)

        .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();
       
       public static String SELECT_MAX_SEARCHED_APPLICATION_ID_FOR_CERTIFICATE_ISSUANCE_POSTING = new StringBuilder(SELECT)
       .append(EMPTY_SPACE).append("MAX(").append(APPLICATION_COL.APPLICATION_ID).append(")")
       .append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_ID)
       .append(EMPTY_SPACE).append(FROM)
       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
       .append(EMPTY_SPACE).append(WHERE)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(CAST)
       .append(LEFT_BRACKET)
       .append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
       .append(RIGHT_BRACKET)
       .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(CAST)
       .append(LEFT_BRACKET)
       .append(APPLICATION_COL.CERTIFICATE_ISSUE_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
       .append(RIGHT_BRACKET)
       .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(LEFT_BRACKET)
       .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_QUEUE).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(OR)
       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
       .append(EMPTY_SPACE).append(RIGHT_BRACKET)

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")

       .append(EMPTY_SPACE).append(AND)

       .append(EMPTY_SPACE).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?").toString();

       public static String SELECT_ALL_SEARCHED_APPLICATIONS_FOR_EXTERNAL_REVIEW_POLICE_ASP = new StringBuilder(SELECT)
		       .append(EMPTY_SPACE).append(ALL_FIELDS)
		       .append(EMPTY_SPACE).append(FROM)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION)
		
		       .append(EMPTY_SPACE).append(LEFT_JOIN).append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(EMPTY_SPACE).append(ON)
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.APPLICATION_ID).append(EQUALS)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
		
		       .append(EMPTY_SPACE).append(WHERE)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.REFERENCE_NO).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.NEW_NIC).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.PASSPORT).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_NIC).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(UPPER).append(LEFT_BRACKET).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICANT_NAME_AS_PASSPORT).append(RIGHT_BRACKET).append(EMPTY_SPACE).append(LIKE).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(CAST)
		       .append(LEFT_BRACKET)
		       .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		       .append(RIGHT_BRACKET)
		       .append(EMPTY_SPACE).append(GRATER_OR_EQUALS).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(CAST)
		       .append(LEFT_BRACKET)
		       .append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.SUBMITTED_DATE).append(EMPTY_SPACE).append(AS).append(EMPTY_SPACE).append(DATE)
		       .append(RIGHT_BRACKET)
		       .append(EMPTY_SPACE).append(LESS_OR_EQUALS).append(EMPTY_SPACE).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_AREA_ID).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append("IS NULL")
		       .append(EMPTY_SPACE).append(OR)
		       .append(EMPTY_SPACE).append(LEFT_BRACKET)
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.POLICE_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(AND)
		       .append(EMPTY_SPACE).append(1).append(EMPTY_SPACE).append(EQUALS).append("?")
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		       .append(EMPTY_SPACE).append(RIGHT_BRACKET)
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.ADDRESS).append(DOT).append(ADDRESS_COL.TYPE).append(EMPTY_SPACE).append(EQUALS).append("?")
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.IS_UPLOADED_RECORD).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_CLEARANCE_STATUS).append(EMPTY_SPACE).append(NOT_EQUALS).append("?")
		
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID).append(EMPTY_SPACE).append(GRATERTHAN).append("?")
		       
		       .append(EMPTY_SPACE).append(AND)
		
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.ASP_REVIEW_STATUS).append(EMPTY_SPACE).append(EQUALS).append("?")
		
		       .append(EMPTY_SPACE).append(ORDER_BY)
		       .append(EMPTY_SPACE).append(DB_TABLE.APPLICATION).append(DOT).append(APPLICATION_COL.APPLICATION_ID)
		       .append(EMPTY_SPACE).append(ASCENDING)
		       .append(EMPTY_SPACE).append(LIMIT).append(EMPTY_SPACE).append("?").toString();

    }



}
