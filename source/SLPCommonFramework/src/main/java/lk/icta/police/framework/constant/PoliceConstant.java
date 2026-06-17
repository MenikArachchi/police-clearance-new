package lk.icta.police.framework.constant;

/**
 * The Class DOCConstant.
 * 
 * @author nsenevirat001
 * 
 */
public class PoliceConstant {


  // EMAIL VARIABLES
  public static final String EMAIL_SUBJECT = "EMAIL_SUBJECT";
  public static final String MAIL_TITLE = "mailTitle";
  public static final String RECEIVER_NAME = "receiverName";
  public static final String BODY_TEXT = "bodyText";
  public static String TITLE = "title";

  // IREPORT VARIABLES
  public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";


  // media setting for a pdf file
  public static final String MEDIA_TYPE_PDF = "application/pdf";


  // media setting for a ms doc file
  public static final String MEDIA_TYPE_WORD =
      "application/vnd.openxmlformats-officedocument.wordprocessingml.document";


  // media setting for a csv file
  public static final String MEDIA_TYPE_CSV = "text/csv";


  public static final String EXTENSION_TYPE_CSV = ".csv";
  public static final String EXTENSION_TYPE_EXCEL = ".xls";

  public static final String EXTENSION_TYPE_PDF = ".pdf";
  public static final String EXTENSION_TYPE_WORD_NEW = ".docx";
  public static final String EXTENSION_TYPE_WORD_OLD = ".doc";
  public static final String EXTENSION_TYPE_PNG = ".png";
  public static final String EXTENSION_TYPE_JPG = ".jpg";

  public static final String FILE_TYPE_PDF = "PDF";
  public static final String FILE_TYPE_WORD = "WORD";
  public static final String FILE_TYPE_IMAGE = "IMAGE";
  public static final String FILE_TYPE_EXCEL = "EXCEL";

  public static final String FROM_DATE = "FROM_DATE";
  public static final String TO_DATE = "TO_DATE";

  // Template used for ReportFileLocation
  // public static final String REPORT_FILE_LOCATION= "/home/Deployments/Police/Report_Files/";
  public static final String REPORT_FILE_LOCATION_FINDER = "/home/Deployments/Police/Report_Files";

  // public static String
  // NIC_PASSPORT_FILE_UPLOADED_LOCATION="/home/Deployments/Police/uploadFiles/";
  // public static String
  // NIC_PASSPORT_FILE_UPLOADED_LOCATION_FINDER="/home/Deployments/Police/uploadFiles";

  public static final String SUCCESS = "SUCCESS";
  public static final String UNSUCCESS = "unsuccess";
  public static final String ERROR = "ERROR";
  public static final String SELECT = "--Select--";

  // FOR PRINTING PERMITS
  public static final String WDR_PERMIT_TEMPLATE = "/permit.jrxml";

  public static String LOGO_IMAGE_PATH = "/doc_logo.png";

  public static final String EXCLUDE_SESSION_CHECK =
      "authorizationAction,login,forgotPassword,backLogin,resetPassword,sessionInvalidate,unauthorized,logout";
  public static final String EXCLUDE_AUTHORIZATION_CHECK =
      "authorizationAction,login,forgotPassword,backLogin,resetPassword,changePassword,"
          + "processChangePassword,changeLocale,home,sessionInvalidate,unauthorized,logout,loadActiveFtaList,loadCOOStatusList,listCountApplicationsToBeVerified,"
          + "listCountResubmissionPending,listCountRevisionUpdatesPending,listCountExternalClearancePending,listCountPoliceClearancePending,listCountCertificateCheckingPending,"
          + "listCountCertificatePrintingPending,listCountCertificateToBePosted,"
          + "listCountExternalClearancePendingByDepartment,listCountInternalApprovalPending,validateDateRange";

  public static final String SESSION_USER = "user";
  public static final String ASSIGNED_SERVICE_ID_LIST = "assignedServiceIdList";
  public static final String ASSIGNED_SERVICE_ID_MAP = "ASSIGNED_SERVICE_ID_MAP";

  public static String SESSION_USER_USER_NAME = "userName";
  public static String SESSION_USER_FULL_NAME = "userFullName";
  public static String SESSION_USER_ID = "userId";
  public static String SESSION_USER_ROLE = "userRole";
  public static String SESSION_USER_LOCATION = "userLocation";
  public static String SESSION_LOCKED_APPLICATION_ID_MAP = "lockedCooIdMap";
  public static String SESSION_USER_EMAIL = "email";
  public static String SESSION_TRANSACTION_ID = "transactionId";
  public static String SESSION_APPLICATION_ID = "applicationId";
  public static String SESSION_USER_TYPE = "userType";
  public static String SESSION_USER_DISPLAY_VO = "userDisplayVO";


  public static final String YEAR = "YEAR";
  public static final String MONTH = "MONTH";
  public static final String DATE = "DATE";

  public static final int DEPARTMENT_ADMIN_USER_TYPE = 2;
  public static final int DEPARTMENT_USER_TYPE = 3;

  public static final String FTA_COUNTRY_CODE = "FTA_COUNTRY_CODE";
  public static final String FTA_CODE = "FTA_CODE";
  public static final String EXPORTER_REGISTRATION_NO = "EXPORTER_REGISTRATION_NO";
  public static final String ALL = "ALL";

  public static String UNABLE_TO_CRETATE_EXPORTER_LOGIN = "UNABLE_TO_CRETATE_EXPORTER_LOGIN";
  public static String DUPLICATE_BUSINESS_REGISTRATION_NUMBER = "DUPLICATE_BUSINESS_REGISTRATION_NUMBER";
  public static String APP_STATUS_NOT_VISIBLE = "APP_STATUS_NOT_VISIBLE";


  public static String ADDED_EXPORTER = "ADDED_EXPORTER";

  public static int UNLIMITED_RECORDS_LIMIT = 999999999;

  public static String UPDATED_EXPORTER = "UPDATED_EXPORTER";

  public static String EXPORTER_LIST_REPORT_TEMPLATE = "/exporter_list.jrxml";


  public static String PAYMENT_TOTAL_FEE = "PAYMENT_TOTAL_FEE";
  public static String PAYMENT_DELIVERY_COST = "PAYMENT_DELIVERY_COST";
  public static String PAYMENT_FEE_FOR_COPIES = "PAYMENT_FEE_FOR_COPIES";
  public static String POSTAL_CHARGE = "POSTAL_CHARGE";

  public static final String PAYMENT_REFERENCE_NUMBER = "PAYMENT_REFERENCE_NUMBER";
  public static final String INFORMATION_TYPE = "INFORMATION_TYPE";
  public static final String TEMPORY_REF_NUMBER = "TEMPORY_REF_NUMBER";
  public static final String EXPORTER_REG_NUMBER = "EXPORTER_REG_NUMBER";
  public static final String EXPORTER_NAME = "EXPORTER_NAME";
  public static final String FEE_FOR_ADITIONAL_COPIES = "FEE_FOR_ADITIONAL_COPIES";
  public static final String APPLICATION_FEE = "APPLICATION_FEE";
  public static final String CONVENIENT_FEE = "CONVENIENT_FEE";
  public static final String PAYMENT_INITIATED_DATE = "PAYMENT_INITIATED_DATE";
  public static final String TYPE_OF_APPLICATION = "TYPE_OF_APPLICATION";
  public static final String SAVE_OR_UPDATE = "SAVE_OR_UPDATE";

  public static final String SUCCESS_MESSAGE = "SUCCESS_MESSAGE";
  public static final String ERROR_MESSAGE = "ERROR_MESSAGE";

  public static String RECORD_IS_LOCKED_BY_ANOTHER_USER = "RECORD_IS_LOCKED_BY_ANOTHER_USER";

  public static final String CLEARANCE_REPORT_FILE_NAME = "clearance_report";
  public static final String APPLICATION_DETAILS_REPORT_FILE_NAME = "application_details_report";
  public static final String DAILY_TRANSACTION_REPORT_FILE_NAME = "daily_transaction_report";
  public static final String BLACKLIST_REPORT_FILE_NAME = "blacklist_report";

  public static String CLEARANCE_REPORT = "/clearance_report.jrxml";
  public static String CLEARANCE_REPORT_SUB_REPORT = "/clearance_report_subreport1.jrxml";
  public static String APPLICATION_DETAILS_REPORT = "/application_details_report.jrxml";
  public static String DAILY_TRANSACTION_REPORT = "/daily_transaction_report.jrxml";
  public static String DAILY_TRANSACTION_REPORT_SUB_REPORT = "/daily_transaction_report_Sub_report.jrxml";
  public static String BLACKLIST_REPORT = "/blacklist_report.jrxml";
  public static String BLACKLIST_REPORT_SUB_REPORT = "/blacklist_report_Sub_report.jrxml";

  public static String POL_ADDRESS_PRINT_REPORT = "/police_print_adress_list.jrxml";

  public static final String POL_CLEARENCE_CERTIFICATE_PRINT = "/clearence_certificate_print.jrxml";
  public static final String POL_CLEARENCE_CERTIFICATE_WITH_CLAUSE_PRINT =
      "/clearence_certificate_with_clause_print.jrxml";
  public static final String POL_CLEARENCE_LETTER_PRINT = "/clearence_letter_print.jrxml";


  public static String HEAD_LOGO = "/police_head_image.png";

  public static String POL_PAYMENT_REPORT_TEMPLATE = "/pol_payment_report.jrxml";
  public static String STATISTICS_LIST_TEMPLATE = "/statistics_list.jrxml";
  public static String ONE_RECORD_IS_ALREADY_LOCKED = "ONE_RECORD_IS_ALREADY_LOCKED";
  public static String NO_RECORDS_TO_LOCK = "NO_RECORDS_TO_LOCK";
  public static String RECORD_LOCKED_IS_NOT_AVAILABLE = "RECORD_LOCKED_IS_NOT_AVAILABLE";
  public static String DO_NOT_OWN_LOCK = "DO_NOT_OWN_LOCK";
  public static String NO_CHANGES_TO_UPDATE = "NO_CHANGES_TO_UPDATE";
  public static String RECORD_UPDATED_BY_ANOTHER_USER = "RECORD_UPDATED_BY_ANOTHER_USER";

  public static String SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP = "lockedClearenceAppIdMap";

  public static final int DEFAULT_BUFFER_SIZE = 1024 * 8;
  public static final String GOV_LOGO = "/sl_gov_logo.png";
  public static final String POL_LOGO = "/police_logo.png";



  public static String SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP = "lockedApplicationVerificationMap";

  public static String MAIL_PROPERTY_FILE = "mail";
  public static String INCORRECT_ADDRESS_PERIOD_OF_STAY_MAIL_CONTENT =
      "police.mail.content.incorrect.address.period.of.stay";
  public static String UPDATE_IN_POLICE_AREA_MAIL_CONTENT = "police.mail.content.update.in.police.area";
  public static String UPDATE_IN_NIC_NAME = "police.mail.content.update.in.nic.name";
  public static String UNAUTHORIZED = "unauthorized";

  public static String IMAGE_NOT_FOUND_IMAGE = "file_not_found.png";

  public static String POL_POST_LIST_PRINT_REPORT = "/police_print_post_list.jrxml";

  public static final String EXTERNAL_DEPARTMENT_PRINT_APP_LIST = "/external_department_print_app_list.jrxml";
  public static final String EXTERNAL_DEPARTMENT_PRINT_APP_NIC = "/external_department_print_app_nic.jrxml";
  public static final String EXTERNAL_DEPARTMENT_PRINT_APP_IMI = "/external_department_print_app_imi.jrxml";
  public static final String EXTERNAL_DEPARTMENT_PRINT_APP_LIST_POLICE =
      "/external_department_print_app_list_police.jrxml";

  public static final String POLICE_FORM_PRINT = "/police_form_print.jrxml";
  public static final String POLICE_POLICE_FORM_PRINT = "/police_police_form_print.jrxml";
  public static final String POLICE_FORM_SUB_REPORT = "/police_form_print_sub_report.jrxml";
  public static final String YES = "YES";
  public static final String AP = "AP";
  
  public static final int CLEARANCE_APP_SEARCH_LIMITS = 5000;
  public static final int HOME_COUNT_SEARCH_LIMIT = 25000;

  public static String CLEARANCE_GRID_BUTTONS = "clearance_grid_buttons";
  public static String REVIEW_GRID_BUTTONS = "review_grid_buttons";

  public static String APPLICATION_STATUS_PRINT = "/application_status_report.jrxml";
  public static String APPLICATION_STATUS_PRINT_SUB_REPORT_CID = "/application_status_report_cid_sub_report.jrxml";
  public static String APPLICATION_STATUS_PRINT_SUB_REPORT_POL = "/application_status_report_police_sub_report.jrxml";

  public static String APPLICATION_LIST_STATUS_REPORT = "/application_list_status_report.jrxml";

  public static String PHQ_REQUEST = "phq_request";

}
