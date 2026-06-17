package lk.icta.police.framework.constant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class DOCEnumConstant class is used to maintain code type and code value
 *
 * @author
 */
public class PoliceEnumConstant {

    public enum YesNoStatus {
        YES(1), NO(0);

        private int code;

        private YesNoStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private static final Map<Integer, YesNoStatus> LOOKUP = new HashMap<Integer, YesNoStatus>();

        static {
            for (YesNoStatus yesNoStatus : EnumSet.allOf(YesNoStatus.class)) {
                LOOKUP.put(yesNoStatus.getCode(), yesNoStatus);
            }
        }

        public static YesNoStatus fromCode(int code) {
            return LOOKUP.get(code);
        }

    }

    public enum SequenceNumbers {

        PAYMENT_REFERENCE_NUMBER(1), APPLICATION_REFERENCE_NUMBER(2), CERTIFICATE_CERIAL_NUMBER(3), LETTER_CERIAL_NUMBER(4);

        private int code;

        private SequenceNumbers(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        private static final Map<Integer, SequenceNumbers> LOOKUP = new HashMap<Integer, SequenceNumbers>();

        static {
            for (SequenceNumbers number : EnumSet.allOf(SequenceNumbers.class)) {
                LOOKUP.put(number.getCode(), number);
            }
        }

        public static SequenceNumbers fromCode(int code) {
            return LOOKUP.get(code);
        }
    }


    public enum ApplicationReviewStatus {

        TS("Temporary Saved"), NW("New"), RV("revised"), VF("Verified"), RC("Request Clarification"), RM("Request Manual Resubmission"), RJ("Reject"), TM("Temp");

        private String displayName;

        private ApplicationReviewStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationReviewStatus> LOOKUP = new LinkedHashMap<String, ApplicationReviewStatus>();
        private static final Map<String, ApplicationReviewStatus> CODE_LOOKUP = new LinkedHashMap<String, ApplicationReviewStatus>();

        static {
            for (ApplicationReviewStatus applicationReviewStatus : EnumSet.allOf(ApplicationReviewStatus.class)) {
                LOOKUP.put(applicationReviewStatus.getDisplayName(), applicationReviewStatus);
                CODE_LOOKUP.put(applicationReviewStatus.toString(), applicationReviewStatus);
            }
        }

        public static ApplicationReviewStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationReviewStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationReviewStatus> getApplicationReviewStatusMap() {
            return LOOKUP;
        }

    }

    public enum ApplicationClearenceStatus {

        PN("Pending"), IS("Issued"), NC("Name Issue Cleared"), CP("Cleared By PHQ"), DC("Due Course"), BL("Blacklist"), RJ("Rejected"), EI("Express Issue"), GC("Green Channel"), RC("Resend Clearance");

        private String displayName;
        private String code;

        private ApplicationClearenceStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getCode() {
            return this.toString();
        }

        private static final Map<String, ApplicationClearenceStatus> LOOKUP = new LinkedHashMap<String, ApplicationClearenceStatus>();
        private static final Map<String, ApplicationClearenceStatus> CODE_LOOKUP = new LinkedHashMap<String, ApplicationClearenceStatus>();

        static {
            for (ApplicationClearenceStatus applicationClearenceStatus : EnumSet.allOf(ApplicationClearenceStatus.class)) {
                LOOKUP.put(applicationClearenceStatus.getDisplayName(), applicationClearenceStatus);
                CODE_LOOKUP.put(applicationClearenceStatus.toString(), applicationClearenceStatus);
            }
        }

        public static ApplicationClearenceStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationClearenceStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationClearenceStatus> getApplicationClearenceStatusMap() {
            return LOOKUP;
        }

    }


    public enum ApplicationQueue {

        NN("None"), CN("Checking Officer No Adverse"), CA("Checking Officer Adverse"), OI("OIC"), AS("ASP"), DH("DHA"), PO("Posting Officer"), DI("DIG"), CP("Complete");

        private String displayName;

        private ApplicationQueue(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationQueue> LOOKUP = new LinkedHashMap<String, ApplicationQueue>();
        private static final Map<String, ApplicationQueue> CODE_LOOKUP = new LinkedHashMap<String, ApplicationQueue>();

        static {
            for (ApplicationQueue applicationQueue : EnumSet.allOf(ApplicationQueue.class)) {
                LOOKUP.put(applicationQueue.getDisplayName(), applicationQueue);
                CODE_LOOKUP.put(applicationQueue.toString(), applicationQueue);
            }
        }

        public static ApplicationQueue fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationQueue fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationQueue> getApplicationQueueMap() {
            return LOOKUP;
        }

    }


    public enum ApprovalStatus {

        PN("Pending"), AP("Approved"), RJ("Rejected"), OI("Other Issue"), NI("Name Issue"), TU("Temporarily Updated");

        private String displayName;

        private ApprovalStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApprovalStatus> LOOKUP = new LinkedHashMap<String, ApprovalStatus>();
        private static final Map<String, ApprovalStatus> CODE_LOOKUP = new LinkedHashMap<String, ApprovalStatus>();

        static {
            for (ApprovalStatus approvalStatus : EnumSet.allOf(ApprovalStatus.class)) {
                LOOKUP.put(approvalStatus.getDisplayName(), approvalStatus);
                CODE_LOOKUP.put(approvalStatus.toString(), approvalStatus);
            }
        }

        public static ApprovalStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApprovalStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApprovalStatus> getApprovalStatusMap() {
            return LOOKUP;
        }

    }


    public enum ApplicationPurpose {

        RES("Resident Visa"), TMP("Temporary Visa"), EMP("Employment"), STU("Student"), SCH("Scholarship"), NOT("Not Given");

        private String displayName;

        private ApplicationPurpose(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationPurpose> LOOKUP = new LinkedHashMap<String, ApplicationPurpose>();
        private static final Map<String, ApplicationPurpose> CODE_LOOKUP = new LinkedHashMap<String, ApplicationPurpose>();

        static {
            for (ApplicationPurpose applicationPurpose : EnumSet.allOf(ApplicationPurpose.class)) {
                LOOKUP.put(applicationPurpose.getDisplayName(), applicationPurpose);
                CODE_LOOKUP.put(applicationPurpose.toString(), applicationPurpose);
            }
        }

        public static ApplicationPurpose fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationPurpose fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationPurpose> getApplicationPurposeMap() {
            return LOOKUP;
        }

    }


    public enum ApplicantStatus {

        REV("Rev."), MAR("Married"), UNM("Unmarried"), DIV("Divorced"), WID("Widow");

        private String displayName;

        private ApplicantStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicantStatus> LOOKUP = new LinkedHashMap<String, ApplicantStatus>();
        private static final Map<String, ApplicantStatus> CODE_LOOKUP = new LinkedHashMap<String, ApplicantStatus>();

        static {
            for (ApplicantStatus applicantStatus : EnumSet.allOf(ApplicantStatus.class)) {
                LOOKUP.put(applicantStatus.getDisplayName(), applicantStatus);
                CODE_LOOKUP.put(applicantStatus.toString(), applicantStatus);
            }
        }

        public static ApplicantStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicantStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicantStatus> getApplicantStatusMap() {
            return LOOKUP;
        }

    }


    public enum HandOverPerson {

        NOA("Not Applicable"), MOT("Mother"), FAT("Father"), WIF("Wife"), HUS("Husband"), BRO("Brother"), SIS("Sister"), OTH("Other");

        private String displayName;

        private HandOverPerson(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, HandOverPerson> LOOKUP = new LinkedHashMap<String, HandOverPerson>();
        private static final Map<String, HandOverPerson> CODE_LOOKUP = new LinkedHashMap<String, HandOverPerson>();

        static {
            for (HandOverPerson handOverPerson : EnumSet.allOf(HandOverPerson.class)) {
                LOOKUP.put(handOverPerson.getDisplayName(), handOverPerson);
                CODE_LOOKUP.put(handOverPerson.toString(), handOverPerson);
            }
        }

        public static HandOverPerson fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static HandOverPerson fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, HandOverPerson> getHandOverPersonMap() {
            return LOOKUP;
        }

    }


    public enum UserDepartment {
        PHQ(1, "Police Head Quarters"), POL(2, "Police Station"), CID(3, "CID"), TID(4, "TID"), SIS(5, "SIS"), NIC(6, "NIC"), IMI(7, "IMI");

//		'3', 'CID'
//		'7', 'Immigration Department'
//		'6', 'NIC'
//		'1', 'Police Head Quarter'
//		'2', 'Police Station'
//		'5', 'SIS'
//		'4', 'TID'


        private int code;
        private String displayName;

        private UserDepartment(int code, String displayName) {
            this.code = code;
            this.displayName = displayName;
        }

        public int getCode() {
            return code;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static Map<Integer, UserDepartment> getLookup() {
            return LOOKUP;
        }

        public static Map<String, UserDepartment> getNameLookup() {
            return NAME_LOOKUP;
        }

        private static final Map<Integer, UserDepartment> LOOKUP = new HashMap<Integer, UserDepartment>();
        private static final Map<String, UserDepartment> NAME_LOOKUP = new HashMap<String, UserDepartment>();

        static {
            for (UserDepartment userDepartment : EnumSet.allOf(UserDepartment.class)) {
                LOOKUP.put(userDepartment.getCode(), userDepartment);
                NAME_LOOKUP.put(userDepartment.toString(), userDepartment);
            }
        }

        public static UserDepartment fromCode(int code) {
            return LOOKUP.get(code);
        }

        public static UserDepartment fromStringCode(String code) {
            return NAME_LOOKUP.get(code);
        }

    }

    public enum UserType {
        SA(1), DA(2), DU(3), CN(4), CA(5), OI(6), AS(7), DH(8), DI(9), PO(10);

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


        private int code;

        private UserType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private static final Map<Integer, UserType> LOOKUP = new HashMap<Integer, UserType>();

        static {
            for (UserType userType : EnumSet.allOf(UserType.class)) {
                LOOKUP.put(userType.getCode(), userType);
            }
        }

        public static UserType fromCode(int code) {
            return LOOKUP.get(code);
        }

    }


    public enum AddressType {
        //applicantCertificateAddresses - AC
        //applicantResidenceAddresses - AR
        //spousResidenceAddresses - SR
        //Removed Address (By PHQ at verification)- RM
        AC(1), AR(2), SR(3), RM(4);

        private int code;

        private AddressType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private static final Map<Integer, AddressType> LOOKUP = new HashMap<Integer, AddressType>();

        static {
            for (AddressType addressType : EnumSet.allOf(AddressType.class)) {
                LOOKUP.put(addressType.getCode(), addressType);
            }
        }

        public static AddressType fromCode(int code) {
            return LOOKUP.get(code);
        }

    }

    //CommentType
    public enum CommentType {
        OIC("OIC coment"), ASP("ASP coment"), CHK("CHK coment"), CHA("CHK Adverse coment"), POL("POL coment"), CID("CID coment"),
        TID("TID coment"), SIS("SIS coment"), NIC("NIC coment"), IMI("IMI coment"), PHQ("PHQ coment"),
        DIG("DIG coment"), DHA("DHA coment"), LTR("Letter Content");

        private String displayName;

        private CommentType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, CommentType> LOOKUP = new LinkedHashMap<String, CommentType>();
        private static final Map<String, CommentType> CODE_LOOKUP = new LinkedHashMap<String, CommentType>();

        static {
            for (CommentType commentType : EnumSet.allOf(CommentType.class)) {
                LOOKUP.put(commentType.getDisplayName(), commentType);
                CODE_LOOKUP.put(commentType.toString(), commentType);
            }
        }

        public static CommentType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static CommentType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, CommentType> getCommentTypeMap() {
            return LOOKUP;
        }

    }

    public enum PaymentStatus {

        PEND("Pending"), MCNF("Manually Confirmed"), OCNF("Online Confirmed"), FAIL("Failed"), CNCL("Cancelled");

        private String displayName;

        private PaymentStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, PaymentStatus> LOOKUP = new LinkedHashMap<String, PaymentStatus>();
        private static final Map<String, PaymentStatus> CODE_LOOKUP = new LinkedHashMap<String, PaymentStatus>();

        static {
            for (PaymentStatus paymentStatus : EnumSet.allOf(PaymentStatus.class)) {
                LOOKUP.put(paymentStatus.getDisplayName(), paymentStatus);
                CODE_LOOKUP.put(paymentStatus.toString(), paymentStatus);
            }
        }

        public static PaymentStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static PaymentStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, PaymentStatus> getPaymentStatusMap() {
            return LOOKUP;
        }

    }

    public enum PaymentMode {

        ONLN("Online"), CASH("Cash"), CHEQ("Cheque"), WIRE("Wire Transfer");

        private String displayName;

        private PaymentMode(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, PaymentMode> LOOKUP = new LinkedHashMap<String, PaymentMode>();
        private static final Map<String, PaymentMode> CODE_LOOKUP = new LinkedHashMap<String, PaymentMode>();

        static {
            for (PaymentMode paymentMode : EnumSet.allOf(PaymentMode.class)) {
                LOOKUP.put(paymentMode.getDisplayName(), paymentMode);
                CODE_LOOKUP.put(paymentMode.toString(), paymentMode);
            }
        }

        public static PaymentMode fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static PaymentMode fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, PaymentMode> getPaymentModeMap() {
            return LOOKUP;
        }

    }

    public enum ApplicationAuthProvider {

        FB("FaceBook"), GM("Gmail"), LI("Linkedin"), CU("Common User");

        private String displayName;

        private ApplicationAuthProvider(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationAuthProvider> LOOKUP = new LinkedHashMap<String, ApplicationAuthProvider>();
        private static final Map<String, ApplicationAuthProvider> CODE_LOOKUP = new LinkedHashMap<String, ApplicationAuthProvider>();

        static {
            for (ApplicationAuthProvider applicationAuthProvider : EnumSet.allOf(ApplicationAuthProvider.class)) {
                LOOKUP.put(applicationAuthProvider.getDisplayName(), applicationAuthProvider);
                CODE_LOOKUP.put(applicationAuthProvider.toString(), applicationAuthProvider);
            }
        }

        public static ApplicationAuthProvider fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationAuthProvider fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationAuthProvider> getPaymentModeMap() {
            return LOOKUP;
        }

    }

    public enum ApplicationType {

        ON("Online Submission"), MA("Manual Submission");

        private String displayName;

        private ApplicationType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationType> LOOKUP = new LinkedHashMap<String, ApplicationType>();
        private static final Map<String, ApplicationType> CODE_LOOKUP = new LinkedHashMap<String, ApplicationType>();

        static {
            for (ApplicationType applicationType : EnumSet.allOf(ApplicationType.class)) {
                LOOKUP.put(applicationType.getDisplayName(), applicationType);
                CODE_LOOKUP.put(applicationType.toString(), applicationType);
            }
        }

        public static ApplicationType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationType> getPaymentModeMap() {
            return LOOKUP;
        }

    }

    public enum AddressModificationStatus {

        CF("Confirm"), MF("Modified"), RM("Request to Modify");

        private String displayName;

        private AddressModificationStatus(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, AddressModificationStatus> LOOKUP = new LinkedHashMap<String, AddressModificationStatus>();
        private static final Map<String, AddressModificationStatus> CODE_LOOKUP = new LinkedHashMap<String, AddressModificationStatus>();

        static {
            for (AddressModificationStatus addressModificationStatus : EnumSet.allOf(AddressModificationStatus.class)) {
                LOOKUP.put(addressModificationStatus.getDisplayName(), addressModificationStatus);
                CODE_LOOKUP.put(addressModificationStatus.toString(), addressModificationStatus);
            }
        }

        public static AddressModificationStatus fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static AddressModificationStatus fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, AddressModificationStatus> getAddressModificationStatusMap() {
            return LOOKUP;
        }

    }


    public enum EmailType {

        UP("Update in police Area"), IA("Incorrect Address or Period of stay"), NU("NIC Name Update");

        private String displayName;

        private EmailType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, EmailType> LOOKUP = new LinkedHashMap<String, EmailType>();
        private static final Map<String, EmailType> CODE_LOOKUP = new LinkedHashMap<String, EmailType>();

        static {
            for (EmailType emailType : EnumSet.allOf(EmailType.class)) {
                LOOKUP.put(emailType.getDisplayName(), emailType);
                CODE_LOOKUP.put(emailType.toString(), emailType);
            }
        }

        public static EmailType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static EmailType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, EmailType> getEmailTypeMap() {
            return LOOKUP;
        }

    }

    public enum HighcommissionTypes {

        HC("High Commission", "H.E. THE HIGH COMMISSIONER"), EM("Embassy", "H.E. THE AMBASSADOR"), CO("Consulate", "THE CONSUL GENERAL"), IG("Immigration Department", "THE IMMIGRATION OFFICER"), OT("Other", "");

        private String displayName;
        private String addresse;

        private HighcommissionTypes(String name, String addresse) {
            this.displayName = name;
            this.addresse = addresse;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getAddresse() {
            return addresse;
        }

        private static final Map<String, HighcommissionTypes> LOOKUP = new LinkedHashMap<String, HighcommissionTypes>();
        private static final Map<String, HighcommissionTypes> CODE_LOOKUP = new LinkedHashMap<String, HighcommissionTypes>();

        static {
            for (HighcommissionTypes highcommissionTypes : EnumSet.allOf(HighcommissionTypes.class)) {
                LOOKUP.put(highcommissionTypes.getDisplayName(), highcommissionTypes);
                CODE_LOOKUP.put(highcommissionTypes.toString(), highcommissionTypes);
            }
        }

        public static HighcommissionTypes fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static HighcommissionTypes fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, HighcommissionTypes> getHighcommissionTypesMap() {
            return LOOKUP;
        }

    }

    public enum CertificateType {

        CT("Certificate"), CL("Certificate with clause"), LT("Letter");

        private String displayName;

        private CertificateType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, CertificateType> LOOKUP = new LinkedHashMap<String, CertificateType>();
        private static final Map<String, CertificateType> CODE_LOOKUP = new LinkedHashMap<String, CertificateType>();

        static {
            for (CertificateType certificateType : EnumSet.allOf(CertificateType.class)) {
                LOOKUP.put(certificateType.getDisplayName(), certificateType);
                CODE_LOOKUP.put(certificateType.toString(), certificateType);
            }
        }

        public static CertificateType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static CertificateType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, CertificateType> getCertificateTypeMap() {
            return LOOKUP;
        }

    }

    public enum ApplicationStatusCheck {

        SUB("Submitted"), ACC("Accepted"), VER("Verification"), REJ("Rejected"), DEL("Delayed"), POS("Posted");

        private String displayName;

        private ApplicationStatusCheck(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationStatusCheck> LOOKUP = new LinkedHashMap<String, ApplicationStatusCheck>();
        private static final Map<String, ApplicationStatusCheck> CODE_LOOKUP = new LinkedHashMap<String, ApplicationStatusCheck>();

        static {
            for (ApplicationStatusCheck applicationStatusCheck : EnumSet.allOf(ApplicationStatusCheck.class)) {
                LOOKUP.put(applicationStatusCheck.getDisplayName(), applicationStatusCheck);
                CODE_LOOKUP.put(applicationStatusCheck.toString(), applicationStatusCheck);
            }
        }

        public static ApplicationStatusCheck fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationStatusCheck fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationStatusCheck> getApplicationStatusCheckMap() {
            return LOOKUP;
        }

    }

    public enum DeliveryType {

        FM("Foriegn Ministry"), SB("SLBFE"), NP("Normal Post");

        private String displayName;

        private DeliveryType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, DeliveryType> LOOKUP = new LinkedHashMap<String, DeliveryType>();
        private static final Map<String, DeliveryType> CODE_LOOKUP = new LinkedHashMap<String, DeliveryType>();

        static {
            for (DeliveryType deliveryType : EnumSet.allOf(DeliveryType.class)) {
                LOOKUP.put(deliveryType.getDisplayName(), deliveryType);
                CODE_LOOKUP.put(deliveryType.toString(), deliveryType);
            }
        }

        public static DeliveryType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static DeliveryType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, DeliveryType> getDeliveryTypeMap() {
            return LOOKUP;
        }

    }

    public enum ApplicationModifiedDateTypes {

        VFM("Verification Status Modification"), RQC("Requested Clarification"), RQU("Requested for Update"),
        CNA("Checking officer no adverse updated"), ASP("ASP updated"), CAD("Checking officer adverse updated"), DAU("Department Admin updated"),
        DHA("DHA updated"), DIG("DIG updated"), DUU("DUU updated"), OIC("OIC updated"), POF("Posting officer updated"), SAD("OIC updated"), UNK("Unknown user type updated");

        private String displayName;

        private ApplicationModifiedDateTypes(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ApplicationModifiedDateTypes> LOOKUP = new LinkedHashMap<String, ApplicationModifiedDateTypes>();
        private static final Map<String, ApplicationModifiedDateTypes> CODE_LOOKUP = new LinkedHashMap<String, ApplicationModifiedDateTypes>();

        static {
            for (ApplicationModifiedDateTypes applicationModifiedDateType : EnumSet.allOf(ApplicationModifiedDateTypes.class)) {
                LOOKUP.put(applicationModifiedDateType.getDisplayName(), applicationModifiedDateType);
                CODE_LOOKUP.put(applicationModifiedDateType.toString(), applicationModifiedDateType);
            }
        }

        public static ApplicationModifiedDateTypes fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ApplicationModifiedDateTypes fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ApplicationModifiedDateTypes> getApplicationModifiedDateTypesMap() {
            return LOOKUP;
        }

    }


    public enum ExternalClearanceSendType {

        SND("SEND"), RSD("RESEND");

        private String displayName;

        private ExternalClearanceSendType(String name) {
            this.displayName = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, ExternalClearanceSendType> LOOKUP = new LinkedHashMap<String, ExternalClearanceSendType>();
        private static final Map<String, ExternalClearanceSendType> CODE_LOOKUP = new LinkedHashMap<String, ExternalClearanceSendType>();

        static {
            for (ExternalClearanceSendType clearanceSendType : EnumSet.allOf(ExternalClearanceSendType.class)) {
                LOOKUP.put(clearanceSendType.getDisplayName(), clearanceSendType);
                CODE_LOOKUP.put(clearanceSendType.toString(), clearanceSendType);
            }
        }

        public static ExternalClearanceSendType fromCode(String code) {
            return CODE_LOOKUP.get(code);
        }

        public static ExternalClearanceSendType fromDisplayName(String displayName) {
            return LOOKUP.get(displayName);
        }

        public static Map<String, ExternalClearanceSendType> getExternalClearanceSendTypeMap() {
            return LOOKUP;
        }

    }


    public enum UploadedFileType {
        NICF("NIC Front Side"), NICB("NIC Back Side"), PASF("Passport Front Side"), PASB("Passport Back Side"),
        BIRF("Birth certificate Front Side"), BIRB("Birth certificate Back Side"), LETR("Letter Of reference"),
        LETA("Letter Of Approval"), NEWNICF("New NIC Front Side"), NEWNICB("New NIC Back Side"), AFFIDAVIT("Affidavit"),
        ADDI_DOCUMENT("Additional document ");

        private String displayName;

        private UploadedFileType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }


        private static final Map<String, UploadedFileType> LOOKUP = new HashMap<String, UploadedFileType>();

        static {
            for (UploadedFileType uploadedFileType : EnumSet.allOf(UploadedFileType.class)) {
                LOOKUP.put(uploadedFileType.toString(), uploadedFileType);
            }
        }

        public static UploadedFileType fromCode(String code) {
            return LOOKUP.get(code);
        }

    }

    public enum NameSource {
        NIC("nic"),
        PASSPORT("passport");

        private String source;


        NameSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return source;
        }
    }


    public enum DHAReviewStatus{
        APPROVED("approved"),
        NOT_APPROVED("not_approved"),
        NOT_APPLICABLE("not_applicable"),
        APPLICABLE("applicable"),
        PHQ_REQUEST("phq_request");

        private String status;

        DHAReviewStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

    }
    
    public enum ASPReviewStatus{
        APPROVED("approved"),
        NOT_APPROVED("not_approved"),
        NOT_APPLICABLE("not_applicable"),
        APPLICABLE("applicable"),
        PHQ_REQUEST("phq_request");

        private String status;

        ASPReviewStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

    }

    public enum DocumentTypes{
        IMAGE("Images png, jpj, gif"),
        DOC("doc, docx");

        private String docType;

        DocumentTypes(String docType) {
            this.docType = docType;
        }

        public String getDocType() {
            return docType;
        }
    }
}
