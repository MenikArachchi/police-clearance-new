package lk.icta.police.framework.vo;

import lk.icta.police.framework.utility.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

public class ApplicationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long applicationId; // BIGINT
    private String referenceNo; // VARCHAR(10)
    private String previousReferenceNo; // VARCHAR(10)

    private long nationalityId;
    private String nationality;

    private int citizenOfSriLanka;
    private Date citizenshipObtainedDate;
    private Date dateOfBirth;
    private int age; // INT

    private String nic; // VARCHAR(10)
    private String newNic; // VARCHAR(12)

    private String passport; // VARCHAR(45)

    private long countryId;
    private String countryName;

    private long highCommisionReferenceId;
    private String highCommisionReference;
    private String applicantNameAsNic;
    private String applicantNameAsPassport;
    private Date passportIssueDate;

    private String presentAddressLocal; // VARCHAR(1000)
    private String presentAddressOverseas; // VARCHAR(1000)

    private String sex; // VARCHAR(3)
    private String occupation; // VARCHAR(100)
    private String purpose; // VARCHAR(3)
    private String applicantStatus; // VARCHAR(3)

    private int previousCertificateApply; // INT
    private long previousCertificateCountryId; // BIGINT
    private String previousCertificateCountryName; // VARCHAR(100)
    private int previousCertificateIssueStatus; // INT
    private String previousCertificateReferenceNo; // VARCHAR(45)
    private Date previousCertificateIssueDate; // DATETIME

    private String authorizedHandoverPerson; // VARCHAR(3)
    private String authorizedHandoverPersonName; // VARCHAR(1000)
    private String authorizedHandoverPersonNicPassport; // VARCHAR(10)

    private String highCommisionReferenceAddress;
    private String certificatePostAddressLineOne; // VARCHAR(1000)
    private String certificatePostAddressLineTwo; // VARCHAR(1000)

    private String certificatePostAddressCity; // VARCHAR(500)
    private String certificatePostAddressState; // VARCHAR(500)
    private String certificatePostAddressPostal; // VARCHAR(500)
    private long certificatePostAddressCountry; // BIGINT
    private String certificatePostAddressCountryName; // VARCHAR(45)

    private long mobileCountryCodeId;
    private String mobileCountryCode;

    private String mobileNo; // VARCHAR(20)
    private String email; // VARCHAR(500)
    private String spouseFullName; // VARCHAR(1000)
    private String spouseAddress;
    private String spouseNationality;
    private String spousePassport;
    private String spouseNic;

    private String deliveryType;
    private String foriegnMinistryInvertNo;

    private String passportAttachPath; // VARCHAR(1500)
    private String passportBackAttachPath; // VARCHAR(1500)

    private String nicAttachPath; // VARCHAR(1500)
    private String nicBackAttachPath;

    private String newNicAttachPath; // VARCHAR(1500)
    private String newNicBackAttachPath;


    private String birthCertificatePath; // VARCHAR(1500)
    private String birthCertificateBackPath;

    private String letterOfReferencePath;

    private int referredThroughBereau;

    private String applicationClearanceStatus; // VARCHAR(2)
    private String applicationReviewStatus;

    private int updatedUserId; // INT
    private String updatedUserName; // VARCHAR(500)
    private Date updatedDateTime; // DATETIME

    private String polStatus; // VARCHAR(2)
    private String cidStatus; // VARCHAR(2)
    private String tidStatus; // VARCHAR(2)
    private String sisStatus; // VARCHAR(2)
    private String nicStatus; // VARCHAR(2)
    private String imiStatus; // VARCHAR(2)

    private int phqRecordLockStatus; // INT
    private int cidRecordLockStatus; // INT
    private int tidRecordLockStatus; // INT
    private int sisRecordLockStatus; // INT
    private int nicRecordLockStatus; // INT
    private int imiRecordLockStatus; // INT

    private String coApproved; // VARCHAR(2)
    private String oicApproved; // VARCHAR(2)
    private String aspApproved; // VARCHAR(2)
    private String dhaApproved; // VARCHAR(2)
    private String digApproved; // VARCHAR(2)

    private String certificateSerialNo; // VARCHAR(100)
    private String applicationQueue; // VARCHAR(2)
    private String regPostNo;
    private Date certificatePostedDate;
    private String ipAddress;
    private String userFullName;
    private String userEmail;
    private String authProvider;

    private int certificatePrintedStatus;
    private Date certificateIssueDate;
    private String applicationType;
    private long certificateAuthPersonId;
    private long transactionId;

    private int addressPrintedStatus; // INT

    private String certificateType;

    private int versionId; // INT

    private String recommendedOfficerName;

    private Date submittedDate;

    private List<AddressVO> applicantCertificateAddresses;
    private int notificationEmailSentStatus;
    private int nicCertificateIssueStatus;


    // to store the constructed postal address to display in grids - by nadeeshani
    private String certificatePostalAddress;
    private int hasCurrentUserLocked;

    private int hasRequestClarification;
    private String nicFileType;
    private String pptFileType;
    private String birthCertificateFileType;
    private String letterOfReferenceFileType;


    private String nicBackFileType;
    private String pptBackFileType;
    private String birthCertificateFileBackType;

    private int hasBlackListed;
    private int anyAdverseAvailable;
    private String letterConent;
    private int hasAlreadyPrinted;
    private String dateSentForClarification;
    private String dateRecievedClarification;
    private String dateOfBirthString;
    private String externalApprovalStatus;
    private String externalApprovedUser;
    private String internalSentUser;
    private String givenComment;


    // for printing list only - by nadeeshani
    private AddressVO printAddressVO;
    private AddressTempVO printAddressTempVO;
    private long printAddressId;

    private int hasResent;
    private int rowNumber;

    private List<Date> datesSentForClarification;
    private List<Date> datesRecievedClarification;

    private String currentNicNo;
    private String selectedNameOption;
    private String affidavitAttachPath;

    private String dhaReviewStatus;
    private String aspReviewStatus;

    public ApplicationVO(ApplicationVO applicationVO, boolean copyPassportName) {
        this.applicationId = applicationVO.getApplicationId();
        this.referenceNo = applicationVO.getReferenceNo();
        this.nic = applicationVO.getNic();
        this.newNic = applicationVO.getNewNic();
        this.currentNicNo = applicationVO.getCurrentNicNo();
        this.passport = applicationVO.getPassport();
        this.applicantNameAsNic = applicationVO.getApplicantNameAsNic();
        this.applicantNameAsPassport = applicationVO.getApplicantNameAsPassport();
        if (copyPassportName) {
            if (StringUtils.isEmpty(this.applicantNameAsNic)) {
                this.applicantNameAsNic = applicationVO.getApplicantNameAsPassport();
            }
        }
        this.certificatePostalAddress = applicationVO.getCertificatePostalAddress();
        this.dateOfBirthString = applicationVO.getDateOfBirthString();
        this.mobileNo = applicationVO.getMobileNo();
        this.setDatesSentForClarification(new ArrayList<Date>());
        this.setDatesRecievedClarification(new ArrayList<Date>());
    }
    private List<String> additionalDocsPaths;


    public ApplicationVO() {
        super();
    }


    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicationId", applicationId);
        map.put("referenceNo", referenceNo);
        map.put("nic", nic);
        map.put("newNic", newNic);
        map.put("passport", passport);
        map.put("highCommisionReference", highCommisionReference);
        map.put("nationality", nationality);
        map.put("dateOfBirth", dateOfBirth);
        map.put("age", age);
        map.put("sex", sex);
        map.put("occupation", occupation);
        map.put("purpose", purpose);
        map.put("applicantStatus", applicantStatus);
        map.put("previousCertificateApply", previousCertificateApply);
        map.put("previousCertificateCountryId", previousCertificateCountryId);
        map.put("previousCertificateCountryName", previousCertificateCountryName);
        map.put("previousCertificateIssue_status", previousCertificateIssueStatus);
        map.put("previousCertificateReferenceNo", previousCertificateReferenceNo);
        map.put("previousCertificateIssueDate", previousCertificateIssueDate);
        map.put("presentAddressLocal", presentAddressLocal);
        map.put("presentAddressOverseas", presentAddressOverseas);
        map.put("authorizedHandoverPerson", authorizedHandoverPerson);
        map.put("authorizedHandoverPersonName", authorizedHandoverPersonName);
        map.put("certificatePostAddressLineOne", certificatePostAddressLineOne);
        map.put("certificatePostAddressLineTwo", certificatePostAddressLineTwo);
        map.put("certificatePostAddressCity", certificatePostAddressCity);
        map.put("certificatePostAddressState", certificatePostAddressState);
        map.put("certificatePostAddressPostal", certificatePostAddressPostal);
        map.put("certificatePostAddressCountry", certificatePostAddressCountry);
        map.put("certificatePostAddressCountryName", certificatePostAddressCountryName);
        map.put("mobileNo", mobileNo);
        map.put("email", email);
        map.put("passportAttachPath", passportAttachPath);
        map.put("nicAttachPath", nicAttachPath);
        map.put("newNicAttachPath", newNicAttachPath);
        map.put("birthCertificatePath", birthCertificatePath);
        map.put("letterOfReferencePath", letterOfReferencePath);
        map.put("spouseFullName", spouseFullName);
        map.put("spouseNationality", spouseNationality);
        map.put("spousePassport", spousePassport);
        map.put("spouseNic", spouseNic);
        map.put("deliveryType", deliveryType);
        map.put("foriegnMinistryInvertNo", foriegnMinistryInvertNo);
        map.put("applicationClearanceStatus", applicationClearanceStatus);
        map.put("applicationVerificationStatus", applicationReviewStatus);
        map.put("updatedUserId", updatedUserId);
        map.put("updatedUserName", updatedUserName);
        map.put("updatedDateTime", updatedDateTime);
        map.put("polStatus", polStatus);
        map.put("cidStatus", cidStatus);
        map.put("tidStatus", tidStatus);
        map.put("sisStatus", sisStatus);
        map.put("nicStatus", nicStatus);
        map.put("imiStatus", imiStatus);
        map.put("polRecordLock_status", phqRecordLockStatus);
        map.put("cidRecordLock_status", cidRecordLockStatus);
        map.put("tidRecordLock_status", tidRecordLockStatus);
        map.put("sisRecordLock_status", sisRecordLockStatus);
        map.put("nicRecordLock_status", nicRecordLockStatus);
        map.put("imiRecordLock_status", imiRecordLockStatus);
        map.put("coApproved", coApproved);
        map.put("oicApproved", oicApproved);
        map.put("aspApproved", aspApproved);
        map.put("dhaApproved", dhaApproved);
        map.put("digApproved", digApproved);
        map.put("certificateSerialNo", certificateSerialNo);
        map.put("applicationQue", applicationQueue);
        map.put("regPostNo", regPostNo);
        map.put("ipAddress", ipAddress);
        map.put("userFullName", userFullName);
        map.put("userEmail", userEmail);
        map.put("authProvider", authProvider);
        map.put("certificateType", certificateType);
        map.put("letterConent", letterConent);
        map.put("submittedDate", submittedDate);
        map.put("dateSentForClarification", dateSentForClarification);
        map.put("dateRecievedClarification", dateRecievedClarification);
        map.put("passportBackAttachPath", passportBackAttachPath);
        map.put("nicBackAttachPath", nicBackAttachPath);
        map.put("newNicBackAttachPath", newNicBackAttachPath);
        map.put("birthCertificateBackPath", birthCertificateBackPath);
        map.put("selectedNameOption", selectedNameOption);
        map.put("affidavitAttachPath", affidavitAttachPath);
        return map;
    }

    public void constructcertificatepostalAddress(boolean copyPassportName) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(certificatePostAddressLineOne)) {
            sb.append(certificatePostAddressLineOne).append(", ");
        }
        if (StringUtils.isNotEmpty(certificatePostAddressLineTwo)) {
            sb.append(certificatePostAddressLineTwo).append(", ");
        }
        if (StringUtils.isNotEmpty(certificatePostAddressCity)) {
            sb.append(certificatePostAddressCity).append(", ");
        }
        if (StringUtils.isNotEmpty(certificatePostAddressState)) {
            sb.append(certificatePostAddressState).append(", ");
        }
        if (StringUtils.isNotEmpty(certificatePostAddressPostal)) {
            sb.append(certificatePostAddressPostal).append(", ");
        }
        if (StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
            sb.append(certificatePostAddressCountryName).append(", ");
        }

        this.certificatePostalAddress = StringUtils.trim(sb.toString());

        if (this.certificatePostalAddress.length() > 0
                && this.certificatePostalAddress.charAt(this.certificatePostalAddress.length() - 1) == ',') {
            this.certificatePostalAddress =
                    this.certificatePostalAddress.substring(0, this.certificatePostalAddress.length() - 1);
        }

        if (copyPassportName) {
            if (StringUtils.isEmpty(this.applicantNameAsNic)) {
                this.applicantNameAsNic = this.applicantNameAsPassport;
            }
        }


        if (StringUtils.isEmpty(this.presentAddressLocal)) {
            this.presentAddressLocal = this.presentAddressOverseas;
        }
    }


    public void constructcertificatepostalAddressHtml(boolean copyPassportName) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(applicantNameAsNic)) {
            this.applicantNameAsNic = this.applicantNameAsPassport;
        }
        sb.append("<div style='padding:2px;'>").append(applicantNameAsNic).append(", ").append("</div>");
        if (StringUtils.isNotEmpty(certificatePostAddressLineOne)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressLineOne).append(", ").append("</div>");
        }

        if (StringUtils.isNotEmpty(certificatePostAddressLineTwo)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressLineTwo);
            if (StringUtils.isNotEmpty(certificatePostAddressState) || StringUtils.isNotEmpty(certificatePostAddressPostal)
                    || StringUtils.isNotEmpty(certificatePostAddressCountryName)
                    || StringUtils.isNotEmpty(certificatePostAddressCity)) {
                sb.append(", ");
            } else {
                sb.append(". ");
            }
            sb.append("</div>");
        }


        if (StringUtils.isNotEmpty(certificatePostAddressCity)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressCity);
            if (StringUtils.isNotEmpty(certificatePostAddressState) || StringUtils.isNotEmpty(certificatePostAddressPostal)
                    || StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
                sb.append(", ");
            } else {
                sb.append(". ");
            }
            sb.append("</div>");
        }


        if (StringUtils.isNotEmpty(certificatePostAddressState)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressState);
            if (StringUtils.isNotEmpty(certificatePostAddressState)
                    || StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
                sb.append(", ");
            } else {
                sb.append(". ");
            }
            sb.append("</div>");
        }

        if (StringUtils.isNotEmpty(certificatePostAddressPostal)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressPostal);
            if (StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
                sb.append(", ");
            } else {
                sb.append(". ");
            }
            sb.append("</div>");
        }

        if (StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
            sb.append("<div style='padding:2px;'>").append(certificatePostAddressCountryName).append(". ").append("</div>");
        }

        this.certificatePostalAddress = StringUtils.trim(sb.toString());

        if ((this.certificatePostalAddress.length() > 0)
                && (this.certificatePostalAddress.charAt((this.certificatePostalAddress.length() - 1)) == ',')) {
            this.certificatePostalAddress =
                    this.certificatePostalAddress.substring(0, (this.certificatePostalAddress.length() - 1));
        }

        if (copyPassportName) {
            if (StringUtils.isEmpty(this.applicantNameAsNic)) {
                this.applicantNameAsNic = this.applicantNameAsPassport;
            }
        }

    }

    @Override
    public String toString() {
        return this.toBasicMap().toString();
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPreviousReferenceNo() {
        return previousReferenceNo;
    }

    public void setPreviousReferenceNo(String previousReferenceNo) {
        this.previousReferenceNo = previousReferenceNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getNewNic() {
        return newNic;
    }

    public void setNewNic(String newNic) {
        this.newNic = newNic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getHighCommisionReference() {
        return highCommisionReference;
    }

    public void setHighCommisionReference(String highCommisionReference) {
        this.highCommisionReference = highCommisionReference;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public int getPreviousCertificateApply() {
        return previousCertificateApply;
    }

    public void setPreviousCertificateApply(int previousCertificateApply) {
        this.previousCertificateApply = previousCertificateApply;
    }

    public long getPreviousCertificateCountryId() {
        return previousCertificateCountryId;
    }

    public void setPreviousCertificateCountryId(long previousCertificateCountryId) {
        this.previousCertificateCountryId = previousCertificateCountryId;
    }

    public String getPreviousCertificateCountryName() {
        return previousCertificateCountryName;
    }

    public void setPreviousCertificateCountryName(String previousCertificateCountryName) {
        this.previousCertificateCountryName = previousCertificateCountryName;
    }

    public int getPreviousCertificateIssueStatus() {
        return previousCertificateIssueStatus;
    }

    public void setPreviousCertificateIssueStatus(int previousCertificateIssueStatus) {
        this.previousCertificateIssueStatus = previousCertificateIssueStatus;
    }

    public String getPreviousCertificateReferenceNo() {
        return previousCertificateReferenceNo;
    }

    public void setPreviousCertificateReferenceNo(String previousCertificateReferenceNo) {
        this.previousCertificateReferenceNo = previousCertificateReferenceNo;
    }

    public Date getPreviousCertificateIssueDate() {
        return previousCertificateIssueDate;
    }

    public void setPreviousCertificateIssueDate(Date previousCertificateIssueDate) {
        this.previousCertificateIssueDate = previousCertificateIssueDate;
    }

    public String getPresentAddressLocal() {
        return presentAddressLocal;
    }

    public void setPresentAddressLocal(String presentAddressLocal) {
        this.presentAddressLocal = presentAddressLocal;
    }

    public String getPresentAddressOverseas() {
        return presentAddressOverseas;
    }

    public void setPresentAddressOverseas(String presentAddressOverseas) {
        this.presentAddressOverseas = presentAddressOverseas;
    }

    public String getAuthorizedHandoverPerson() {
        return authorizedHandoverPerson;
    }

    public void setAuthorizedHandoverPerson(String authorizedHandoverPerson) {
        this.authorizedHandoverPerson = authorizedHandoverPerson;
    }

    public String getAuthorizedHandoverPersonName() {
        return authorizedHandoverPersonName;
    }

    public void setAuthorizedHandoverPersonName(String authorizedHandoverPersonName) {
        this.authorizedHandoverPersonName = authorizedHandoverPersonName;
    }

    public String getCertificatePostAddressLineOne() {
        return certificatePostAddressLineOne;
    }

    public void setCertificatePostAddressLineOne(String certificatePostAddressLineOne) {
        this.certificatePostAddressLineOne = certificatePostAddressLineOne;
    }

    public String getCertificatePostAddressLineTwo() {
        return certificatePostAddressLineTwo;
    }

    public void setCertificatePostAddressLineTwo(String certificatePostAddressLineTwo) {
        this.certificatePostAddressLineTwo = certificatePostAddressLineTwo;
    }

    public String getCertificatePostAddressCity() {
        return certificatePostAddressCity;
    }

    public void setCertificatePostAddressCity(String certificatePostAddressCity) {
        this.certificatePostAddressCity = certificatePostAddressCity;
    }

    public String getCertificatePostAddressState() {
        return certificatePostAddressState;
    }

    public void setCertificatePostAddressState(String certificatePostAddressState) {
        this.certificatePostAddressState = certificatePostAddressState;
    }

    public String getCertificatePostAddressPostal() {
        return certificatePostAddressPostal;
    }

    public void setCertificatePostAddressPostal(String certificatePostAddressPostal) {
        this.certificatePostAddressPostal = certificatePostAddressPostal;
    }

    public long getCertificatePostAddressCountry() {
        return certificatePostAddressCountry;
    }

    public void setCertificatePostAddressCountry(long certificatePostAddressCountry) {
        this.certificatePostAddressCountry = certificatePostAddressCountry;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportAttachPath() {
        return passportAttachPath;
    }

    public void setPassportAttachPath(String passportAttachPath) {
        this.passportAttachPath = passportAttachPath;
    }

    public String getNicAttachPath() {
        return nicAttachPath;
    }

    public void setNicAttachPath(String nicAttachPath) {
        this.nicAttachPath = nicAttachPath;
    }

    public String getNewNicAttachPath() {
        return newNicAttachPath;
    }

    public void setNewNicAttachPath(String newNicAttachPath) {
        this.newNicAttachPath = newNicAttachPath;
    }

    public String getSpouseFullName() {
        return spouseFullName;
    }

    public void setSpouseFullName(String spouseFullName) {
        this.spouseFullName = spouseFullName;
    }

    public String getSpouseNationality() {
        return spouseNationality;
    }

    public void setSpouseNationality(String spouseNationality) {
        this.spouseNationality = spouseNationality;
    }

    public String getSpousePassport() {
        return spousePassport;
    }

    public void setSpousePassport(String spousePassport) {
        this.spousePassport = spousePassport;
    }

    public String getSpouseNic() {
        return spouseNic;
    }

    public void setSpouseNic(String spouseNic) {
        this.spouseNic = spouseNic;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getForiegnMinistryInvertNo() {
        return foriegnMinistryInvertNo;
    }

    public void setForiegnMinistryInvertNo(String foriegnMinistryInvertNo) {
        this.foriegnMinistryInvertNo = foriegnMinistryInvertNo;
    }

    public String getApplicationClearanceStatus() {
        return applicationClearanceStatus;
    }

    public void setApplicationClearanceStatus(String applicationClearanceStatus) {
        this.applicationClearanceStatus = applicationClearanceStatus;
    }

    public String getApplicationReviewStatus() {
        return applicationReviewStatus;
    }

    public void setApplicationReviewStatus(String applicationReviewStatus) {
        this.applicationReviewStatus = applicationReviewStatus;
    }

    public int getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(int updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getApplicationQueue() {
        return applicationQueue;
    }

    public void setApplicationQueue(String applicationQueue) {
        this.applicationQueue = applicationQueue;
    }

    public String getPolStatus() {
        return polStatus;
    }

    public void setPolStatus(String polStatus) {
        this.polStatus = polStatus;
    }

    public String getCidStatus() {
        return cidStatus;
    }

    public void setCidStatus(String cidStatus) {
        this.cidStatus = cidStatus;
    }

    public String getTidStatus() {
        return tidStatus;
    }

    public void setTidStatus(String tidStatus) {
        this.tidStatus = tidStatus;
    }

    public String getSisStatus() {
        return sisStatus;
    }

    public void setSisStatus(String sisStatus) {
        this.sisStatus = sisStatus;
    }

    public String getNicStatus() {
        return nicStatus;
    }

    public void setNicStatus(String nicStatus) {
        this.nicStatus = nicStatus;
    }

    public String getImiStatus() {
        return imiStatus;
    }

    public void setImiStatus(String imiStatus) {
        this.imiStatus = imiStatus;
    }

    public String getCoApproved() {
        return coApproved;
    }

    public void setCoApproved(String coApproved) {
        this.coApproved = coApproved;
    }

    public String getOicApproved() {
        return oicApproved;
    }

    public void setOicApproved(String oicApproved) {
        this.oicApproved = oicApproved;
    }

    public String getAspApproved() {
        return aspApproved;
    }

    public void setAspApproved(String aspApproved) {
        this.aspApproved = aspApproved;
    }

    public String getDhaApproved() {
        return dhaApproved;
    }

    public void setDhaApproved(String dhaApproved) {
        this.dhaApproved = dhaApproved;
    }

    public String getCertificateSerialNo() {
        return certificateSerialNo;
    }

    public void setCertificateSerialNo(String certificateSerialNo) {
        this.certificateSerialNo = certificateSerialNo;
    }

    public List<AddressVO> getApplicantCertificateAddresses() {
        return applicantCertificateAddresses;
    }

    public void setApplicantCertificateAddresses(List<AddressVO> applicantCertificateAddresses) {
        this.applicantCertificateAddresses = applicantCertificateAddresses;
    }

    public String getRegPostNo() {
        return regPostNo;
    }

    public void setRegPostNo(String regPostNo) {
        this.regPostNo = regPostNo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCertificatePostalAddress() {
        return certificatePostalAddress;
    }

    public void setCertificatePostalAddress(String certificatePostalAddress) {
        this.certificatePostalAddress = certificatePostalAddress;
    }

    public String getCertificatePostAddressCountryName() {
        return certificatePostAddressCountryName;
    }

    public void setCertificatePostAddressCountryName(String certificatePostAddressCountryName) {
        this.certificatePostAddressCountryName = certificatePostAddressCountryName;
    }

    public int getPhqRecordLockStatus() {
        return phqRecordLockStatus;
    }

    public void setPhqRecordLockStatus(int phqRecordLockStatus) {
        this.phqRecordLockStatus = phqRecordLockStatus;
    }

    public int getCidRecordLockStatus() {
        return cidRecordLockStatus;
    }

    public void setCidRecordLockStatus(int cidRecordLockStatus) {
        this.cidRecordLockStatus = cidRecordLockStatus;
    }

    public int getTidRecordLockStatus() {
        return tidRecordLockStatus;
    }

    public void setTidRecordLockStatus(int tidRecordLockStatus) {
        this.tidRecordLockStatus = tidRecordLockStatus;
    }

    public int getSisRecordLockStatus() {
        return sisRecordLockStatus;
    }

    public void setSisRecordLockStatus(int sisRecordLockStatus) {
        this.sisRecordLockStatus = sisRecordLockStatus;
    }

    public int getNicRecordLockStatus() {
        return nicRecordLockStatus;
    }

    public void setNicRecordLockStatus(int nicRecordLockStatus) {
        this.nicRecordLockStatus = nicRecordLockStatus;
    }

    public int getImiRecordLockStatus() {
        return imiRecordLockStatus;
    }

    public void setImiRecordLockStatus(int imiRecordLockStatus) {
        this.imiRecordLockStatus = imiRecordLockStatus;
    }

    public int getHasCurrentUserLocked() {
        return hasCurrentUserLocked;
    }

    public void setHasCurrentUserLocked(int hasCurrentUserLocked) {
        this.hasCurrentUserLocked = hasCurrentUserLocked;
    }

    public String getNicFileType() {
        return nicFileType;
    }

    public void setNicFileType(String nicFileType) {
        this.nicFileType = nicFileType;
    }

    public String getPptFileType() {
        return pptFileType;
    }

    public void setPptFileType(String pptFileType) {
        this.pptFileType = pptFileType;
    }

    public int getHasRequestClarification() {
        return hasRequestClarification;
    }

    public void setHasRequestClarification(int hasRequestClarification) {
        this.hasRequestClarification = hasRequestClarification;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public int getCitizenOfSriLanka() {
        return citizenOfSriLanka;
    }

    public void setCitizenOfSriLanka(int citizenOfSriLanka) {
        this.citizenOfSriLanka = citizenOfSriLanka;
    }

    public Date getCitizenshipObtainedDate() {
        return citizenshipObtainedDate;
    }

    public void setCitizenshipObtainedDate(Date citizenshipObtainedDate) {
        this.citizenshipObtainedDate = citizenshipObtainedDate;
    }

    public long getHighCommisionReferenceId() {
        return highCommisionReferenceId;
    }

    public void setHighCommisionReferenceId(long highCommisionReferenceId) {
        this.highCommisionReferenceId = highCommisionReferenceId;
    }

    public String getApplicantNameAsNic() {
        return applicantNameAsNic;
    }

    public void setApplicantNameAsNic(String applicantNameAsNic) {
        this.applicantNameAsNic = applicantNameAsNic;
    }

    public String getApplicantNameAsPassport() {
        return applicantNameAsPassport;
    }

    public void setApplicantNameAsPassport(String applicantNameAsPassport) {
        this.applicantNameAsPassport = applicantNameAsPassport;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getAuthorizedHandoverPersonNicPassport() {
        return authorizedHandoverPersonNicPassport;
    }

    public void setAuthorizedHandoverPersonNicPassport(String authorizedHandoverPersonNicPassport) {
        this.authorizedHandoverPersonNicPassport = authorizedHandoverPersonNicPassport;
    }

    public String getHighCommisionReferenceAddress() {
        return highCommisionReferenceAddress;
    }

    public void setHighCommisionReferenceAddress(String highCommisionReferenceAddress) {
        this.highCommisionReferenceAddress = highCommisionReferenceAddress;
    }

    public long getMobileCountryCodeId() {
        return mobileCountryCodeId;
    }

    public void setMobileCountryCodeId(long mobileCountryCodeId) {
        this.mobileCountryCodeId = mobileCountryCodeId;
    }

    public String getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(String mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public String getSpouseAddress() {
        return spouseAddress;
    }

    public void setSpouseAddress(String spouseAddress) {
        this.spouseAddress = spouseAddress;
    }

    public String getBirthCertificatePath() {
        return birthCertificatePath;
    }

    public void setBirthCertificatePath(String birthCertificatePath) {
        this.birthCertificatePath = birthCertificatePath;
    }

    public String getLetterOfReferencePath() {
        return letterOfReferencePath;
    }

    public void setLetterOfReferencePath(String letterOfReferencePath) {
        this.letterOfReferencePath = letterOfReferencePath;
    }

    public int getReferredThroughBereau() {
        return referredThroughBereau;
    }

    public void setReferredThroughBereau(int referredThroughBereau) {
        this.referredThroughBereau = referredThroughBereau;
    }

    public int getCertificatePrintedStatus() {
        return certificatePrintedStatus;
    }

    public void setCertificatePrintedStatus(int certificatePrintedStatus) {
        this.certificatePrintedStatus = certificatePrintedStatus;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public long getCertificateAuthPersonId() {
        return certificateAuthPersonId;
    }

    public void setCertificateAuthPersonId(long certificateAuthPersonId) {
        this.certificateAuthPersonId = certificateAuthPersonId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void allocateFileTypes() {
        if (!(this.nicAttachPath == null || this.nicAttachPath.equals(""))) {
            if (this.nicAttachPath.contains("\\.") || this.nicAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.nicAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.nicFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.newNicAttachPath == null || this.newNicAttachPath.equals(""))) {
            if (this.newNicAttachPath.contains("\\.") || this.newNicAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.newNicAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.nicFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.nicBackAttachPath == null || this.nicBackAttachPath.equals(""))) {
            if (this.nicBackAttachPath.contains("\\.") || this.nicBackAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.nicBackAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.nicBackFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.newNicBackAttachPath == null || this.newNicBackAttachPath.equals(""))) {
            if (this.newNicBackAttachPath.contains("\\.") || this.newNicBackAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.newNicBackAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.nicBackFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.passportAttachPath == null || this.passportAttachPath.equals(""))) {
            if (this.passportAttachPath.contains("\\.") || this.passportAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.passportAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.pptFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.passportBackAttachPath == null || this.passportBackAttachPath.equals(""))) {
            if (this.passportBackAttachPath.contains("\\.") || this.passportBackAttachPath.contains(".")) {
                String extension = ".";
                String[] array = this.passportBackAttachPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.pptBackFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.birthCertificatePath == null || this.birthCertificatePath.equals(""))) {
            if (this.birthCertificatePath.contains("\\.") || this.birthCertificatePath.contains(".")) {
                String extension = ".";
                String[] array = this.birthCertificatePath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.birthCertificateFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.birthCertificateBackPath == null || this.birthCertificateBackPath.equals(""))) {
            if (this.birthCertificateBackPath.contains("\\.") || this.birthCertificateBackPath.contains(".")) {
                String extension = ".";
                String[] array = this.birthCertificateBackPath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.birthCertificateFileBackType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

        if (!(this.letterOfReferencePath == null || this.letterOfReferencePath.equals(""))) {
            if (this.letterOfReferencePath.contains("\\.") || this.letterOfReferencePath.contains(".")) {
                String extension = ".";
                String[] array = this.letterOfReferencePath.split("\\.");
                if (array.length > 1) {
                    extension = extension + array[1];
                }
                this.letterOfReferenceFileType = CommonUtil.getFileTypeFromExtension(extension);
            }
        }

    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getBirthCertificateFileType() {
        return birthCertificateFileType;
    }

    public void setBirthCertificateFileType(String birthCertificateFileType) {
        this.birthCertificateFileType = birthCertificateFileType;
    }

    public String getLetterOfReferenceFileType() {
        return letterOfReferenceFileType;
    }

    public void setLetterOfReferenceFileType(String letterOfReferenceFileType) {
        this.letterOfReferenceFileType = letterOfReferenceFileType;
    }

    public int getNotificationEmailSentStatus() {
        return notificationEmailSentStatus;
    }

    public void setNotificationEmailSentStatus(int notificationEmailSentStatus) {
        this.notificationEmailSentStatus = notificationEmailSentStatus;
    }

    public int getHasBlackListed() {
        return hasBlackListed;
    }

    public void setHasBlackListed(int hasBlackListed) {
        this.hasBlackListed = hasBlackListed;
    }

    public Date getCertificateIssueDate() {
        return certificateIssueDate;
    }

    public void setCertificateIssueDate(Date certificateIssueDate) {
        this.certificateIssueDate = certificateIssueDate;
    }

    public int getNicCertificateIssueStatus() {
        return nicCertificateIssueStatus;
    }

    public void setNicCertificateIssueStatus(int nicCertificateIssueStatus) {
        this.nicCertificateIssueStatus = nicCertificateIssueStatus;
    }

    public int getAddressPrintedStatus() {
        return addressPrintedStatus;
    }

    public void setAddressPrintedStatus(int addressPrintedStatus) {
        this.addressPrintedStatus = addressPrintedStatus;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getAnyAdverseAvailable() {
        return anyAdverseAvailable;
    }

    public void setAnyAdverseAvailable(int anyAdverseAvailable) {
        this.anyAdverseAvailable = anyAdverseAvailable;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getDigApproved() {
        return digApproved;
    }

    public void setDigApproved(String digApproved) {
        this.digApproved = digApproved;
    }

    public String getLetterConent() {
        return letterConent;
    }

    public void setLetterConent(String letterConent) {
        this.letterConent = letterConent;
    }

    public AddressVO getPrintAddressVO() {
        return printAddressVO;
    }

    public void setPrintAddressVO(AddressVO printAddressVO) {
        this.printAddressVO = printAddressVO;
    }

    public AddressTempVO getPrintAddressTempVO() {
        return printAddressTempVO;
    }

    public void setPrintAddressTempVO(AddressTempVO printAddressTempVO) {
        this.printAddressTempVO = printAddressTempVO;
    }

    public int getHasAlreadyPrinted() {
        return hasAlreadyPrinted;
    }

    public void setHasAlreadyPrinted(int hasAlreadyPrinted) {
        this.hasAlreadyPrinted = hasAlreadyPrinted;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getDateSentForClarification() {
        return dateSentForClarification;
    }

    public void setDateSentForClarification(String dateSentForClarification) {
        this.dateSentForClarification = dateSentForClarification;
    }

    public String getDateRecievedClarification() {
        return dateRecievedClarification;
    }

    public void setDateRecievedClarification(String dateRecievedClarification) {
        this.dateRecievedClarification = dateRecievedClarification;
    }

    public String getExternalApprovalStatus() {
        return externalApprovalStatus;
    }

    public void setExternalApprovalStatus(String externalApprovalStatus) {
        this.externalApprovalStatus = externalApprovalStatus;
    }


    public String getExternalApprovedUser() {
        return externalApprovedUser;
    }

    public void setExternalApprovedUser(String externalApprovedUser) {
        this.externalApprovedUser = externalApprovedUser;
    }

    public String getInternalSentUser() {
        return internalSentUser;
    }

    public void setInternalSentUser(String internalSentUser) {
        this.internalSentUser = internalSentUser;
    }

    public long getPrintAddressId() {
        return printAddressId;
    }

    public void setPrintAddressId(long printAddressId) {
        this.printAddressId = printAddressId;
    }

    public Date getCertificatePostedDate() {
        return certificatePostedDate;
    }

    public void setCertificatePostedDate(Date certificatePostedDate) {
        this.certificatePostedDate = certificatePostedDate;
    }

    public String getGivenComment() {
        return givenComment;
    }

    public void setGivenComment(String givenComment) {
        this.givenComment = givenComment;
    }

    public String getRecommendedOfficerName() {
        return recommendedOfficerName;
    }

    public void setRecommendedOfficerName(String recommendedOfficerName) {
        this.recommendedOfficerName = recommendedOfficerName;
    }

    public String getDateOfBirthString() {
        return dateOfBirthString;
    }

    public void setDateOfBirthString(String dateOfBirthString) {
        this.dateOfBirthString = dateOfBirthString;
    }

    public int getHasResent() {
        return hasResent;
    }

    public void setHasResent(int hasResent) {
        this.hasResent = hasResent;
    }

    public String getPassportBackAttachPath() {
        return passportBackAttachPath;
    }

    public void setPassportBackAttachPath(String passportBackAttachPath) {
        this.passportBackAttachPath = passportBackAttachPath;
    }

    public String getNicBackAttachPath() {
        return nicBackAttachPath;
    }

    public void setNicBackAttachPath(String nicBackAttachPath) {
        this.nicBackAttachPath = nicBackAttachPath;
    }

    public String getNewNicBackAttachPath() {
        return newNicBackAttachPath;
    }

    public void setNewNicBackAttachPath(String newNicBackAttachPath) {
        this.newNicBackAttachPath = newNicBackAttachPath;
    }

    public String getBirthCertificateBackPath() {
        return birthCertificateBackPath;
    }

    public void setBirthCertificateBackPath(String birthCertificateBackPath) {
        this.birthCertificateBackPath = birthCertificateBackPath;
    }

    public String getNicBackFileType() {
        return nicBackFileType;
    }

    public void setNicBackFileType(String nicBackFileType) {
        this.nicBackFileType = nicBackFileType;
    }

    public String getPptBackFileType() {
        return pptBackFileType;
    }

    public void setPptBackFileType(String pptBackFileType) {
        this.pptBackFileType = pptBackFileType;
    }

    public String getBirthCertificateFileBackType() {
        return birthCertificateFileBackType;
    }

    public void setBirthCertificateFileBackType(String birthCertificateFileBackType) {
        this.birthCertificateFileBackType = birthCertificateFileBackType;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }


    public void trimTextFields() {
        if (StringUtils.isNotEmpty(referenceNo)) {
            this.referenceNo = StringUtils.trim(referenceNo);
        }
        if (StringUtils.isNotEmpty(previousReferenceNo)) {
            this.previousReferenceNo = StringUtils.trim(previousReferenceNo);
        }
        if (StringUtils.isNotEmpty(nationality)) {
            this.nationality = StringUtils.trim(nationality);
        }
        if (StringUtils.isNotEmpty(nic)) {
            this.nic = StringUtils.trim(nic);
        }
        if (StringUtils.isEmpty(newNic)) {
            this.newNic = StringUtils.trim(newNic);
        }
        if (StringUtils.isNotEmpty(passport)) {
            this.passport = StringUtils.trim(passport);
        }
        if (StringUtils.isNotEmpty(countryName)) {
            this.countryName = StringUtils.trim(countryName);
        }
        if (StringUtils.isNotEmpty(highCommisionReference)) {
            this.highCommisionReference = StringUtils.trim(highCommisionReference);
        }

        if (StringUtils.isNotEmpty(applicantNameAsNic)) {
            this.applicantNameAsNic = StringUtils.trim(applicantNameAsNic);
        }
        if (StringUtils.isNotEmpty(applicantNameAsPassport)) {
            this.applicantNameAsPassport = StringUtils.trim(applicantNameAsPassport);
        }
        if (StringUtils.isNotEmpty(presentAddressLocal)) {
            this.presentAddressLocal = StringUtils.trim(presentAddressLocal);
        }
        if (StringUtils.isNotEmpty(presentAddressOverseas)) {
            this.presentAddressOverseas = StringUtils.trim(presentAddressOverseas);
        }

        if (StringUtils.isNotEmpty(sex)) {
            this.sex = StringUtils.trim(sex);
        }
        if (StringUtils.isNotEmpty(occupation)) {
            this.occupation = StringUtils.trim(occupation);
        }
        if (StringUtils.isNotEmpty(purpose)) {
            this.purpose = StringUtils.trim(purpose);
        }
        if (StringUtils.isNotEmpty(applicantStatus)) {
            this.applicantStatus = StringUtils.trim(applicantStatus);
        }

        if (StringUtils.isNotEmpty(previousCertificateCountryName)) {
            this.previousCertificateCountryName = StringUtils.trim(previousCertificateCountryName);
        }
        if (StringUtils.isNotEmpty(previousCertificateReferenceNo)) {
            this.previousCertificateReferenceNo = StringUtils.trim(previousCertificateReferenceNo);
        }

        if (StringUtils.isNotEmpty(authorizedHandoverPerson)) {
            this.authorizedHandoverPerson = StringUtils.trim(authorizedHandoverPerson);
        }
        if (StringUtils.isNotEmpty(authorizedHandoverPersonName)) {
            this.authorizedHandoverPersonName = StringUtils.trim(authorizedHandoverPersonName);
        }
        if (StringUtils.isNotEmpty(authorizedHandoverPersonNicPassport)) {
            this.authorizedHandoverPersonNicPassport = StringUtils.trim(authorizedHandoverPersonNicPassport);
        }


        if (StringUtils.isNotEmpty(highCommisionReferenceAddress)) {
            this.highCommisionReferenceAddress = StringUtils.trim(highCommisionReferenceAddress);
        }
        if (StringUtils.isNotEmpty(certificatePostAddressLineOne)) {
            this.certificatePostAddressLineOne = StringUtils.trim(certificatePostAddressLineOne);
        }
        if (StringUtils.isNotEmpty(certificatePostAddressLineTwo)) {
            this.certificatePostAddressLineTwo = StringUtils.trim(certificatePostAddressLineTwo);
        }

        if (StringUtils.isNotEmpty(certificatePostAddressCity)) {
            this.certificatePostAddressCity = StringUtils.trim(certificatePostAddressCity);
        }
        if (StringUtils.isNotEmpty(certificatePostAddressState)) {
            this.certificatePostAddressState = StringUtils.trim(certificatePostAddressState);
        }
        if (StringUtils.isNotEmpty(certificatePostAddressPostal)) {
            this.certificatePostAddressPostal = StringUtils.trim(certificatePostAddressPostal);
        }
        if (StringUtils.isNotEmpty(certificatePostAddressCountryName)) {
            this.certificatePostAddressCountryName = StringUtils.trim(certificatePostAddressCountryName);
        }

        if (StringUtils.isNotEmpty(mobileCountryCode)) {
            this.mobileCountryCode = StringUtils.trim(mobileCountryCode);
        }

        if (StringUtils.isNotEmpty(mobileNo)) {
            this.mobileNo = StringUtils.trim(mobileNo);
        }
        if (StringUtils.isNotEmpty(email)) {
            this.email = StringUtils.trim(email);
        }
        if (StringUtils.isNotEmpty(spouseFullName)) {
            this.spouseFullName = StringUtils.trim(spouseFullName);
        }
        if (StringUtils.isNotEmpty(spouseAddress)) {
            this.spouseAddress = StringUtils.trim(spouseAddress);
        }
        if (StringUtils.isNotEmpty(spouseNationality)) {
            this.spouseNationality = StringUtils.trim(spouseNationality);
        }
        if (StringUtils.isNotEmpty(spousePassport)) {
            this.spousePassport = StringUtils.trim(spousePassport);
        }
        if (StringUtils.isNotEmpty(spouseNic)) {
            this.spouseNic = StringUtils.trim(spouseNic);
        }


        if (StringUtils.isNotEmpty(deliveryType)) {
            this.deliveryType = StringUtils.trim(deliveryType);
        }
        if (StringUtils.isNotEmpty(foriegnMinistryInvertNo)) {
            this.foriegnMinistryInvertNo = StringUtils.trim(foriegnMinistryInvertNo);
        }


        if (StringUtils.isNotEmpty(passportAttachPath)) {
            this.passportAttachPath = StringUtils.trim(passportAttachPath);
        }
        if (StringUtils.isNotEmpty(passportBackAttachPath)) {
            this.passportBackAttachPath = StringUtils.trim(passportBackAttachPath);
        }

        if (StringUtils.isNotEmpty(nicAttachPath)) {
            this.nicAttachPath = StringUtils.trim(nicAttachPath);
        }

        if (StringUtils.isNotEmpty(newNicAttachPath)) {
            this.newNicAttachPath = StringUtils.trim(newNicAttachPath);
        }
        if (StringUtils.isNotEmpty(nicBackAttachPath)) {
            this.nicBackAttachPath = StringUtils.trim(nicBackAttachPath);
        }

        if (StringUtils.isNotEmpty(newNicBackAttachPath)) {
            this.newNicBackAttachPath = StringUtils.trim(newNicBackAttachPath);
        }

        if (StringUtils.isNotEmpty(birthCertificatePath)) {
            this.birthCertificatePath = StringUtils.trim(birthCertificatePath);
        }
        if (StringUtils.isNotEmpty(birthCertificateBackPath)) {
            this.birthCertificateBackPath = StringUtils.trim(birthCertificateBackPath);
        }


        if (StringUtils.isNotEmpty(letterOfReferencePath)) {
            this.letterOfReferencePath = StringUtils.trim(letterOfReferencePath);
        }
        if (StringUtils.isNotEmpty(applicationClearanceStatus)) {
            this.applicationClearanceStatus = StringUtils.trim(applicationClearanceStatus);
        }
        if (StringUtils.isNotEmpty(applicationReviewStatus)) {
            this.applicationReviewStatus = StringUtils.trim(applicationReviewStatus);
        }

        if (StringUtils.isNotEmpty(updatedUserName)) {
            this.updatedUserName = StringUtils.trim(updatedUserName);
        }


        if (StringUtils.isNotEmpty(polStatus)) {
            this.polStatus = StringUtils.trim(polStatus);
        }
        if (StringUtils.isNotEmpty(cidStatus)) {
            this.cidStatus = StringUtils.trim(cidStatus);
        }
        if (StringUtils.isNotEmpty(tidStatus)) {
            this.tidStatus = StringUtils.trim(tidStatus);
        }
        if (StringUtils.isNotEmpty(sisStatus)) {
            this.sisStatus = StringUtils.trim(sisStatus);
        }
        if (StringUtils.isNotEmpty(nicStatus)) {
            this.nicStatus = StringUtils.trim(nicStatus);
        }
        if (StringUtils.isNotEmpty(imiStatus)) {
            this.imiStatus = StringUtils.trim(imiStatus);
        }


        if (StringUtils.isNotEmpty(coApproved)) {
            this.coApproved = StringUtils.trim(coApproved);
        }
        if (StringUtils.isNotEmpty(oicApproved)) {
            this.oicApproved = StringUtils.trim(oicApproved);
        }
        if (StringUtils.isNotEmpty(aspApproved)) {
            this.aspApproved = StringUtils.trim(aspApproved);
        }
        if (StringUtils.isNotEmpty(dhaApproved)) {
            this.dhaApproved = StringUtils.trim(dhaApproved);
        }
        if (StringUtils.isNotEmpty(digApproved)) {
            this.digApproved = StringUtils.trim(digApproved);
        }


        if (StringUtils.isNotEmpty(certificateSerialNo)) {
            this.certificateSerialNo = StringUtils.trim(certificateSerialNo);
        }
        if (StringUtils.isNotEmpty(applicationQueue)) {
            this.applicationQueue = StringUtils.trim(applicationQueue);
        }
        if (StringUtils.isNotEmpty(regPostNo)) {
            this.regPostNo = StringUtils.trim(regPostNo);
        }
        if (StringUtils.isNotEmpty(ipAddress)) {
            this.ipAddress = StringUtils.trim(ipAddress);
        }
        if (StringUtils.isNotEmpty(userFullName)) {
            this.userFullName = StringUtils.trim(userFullName);
        }
        if (StringUtils.isNotEmpty(userEmail)) {
            this.userEmail = StringUtils.trim(userEmail);
        }
        if (StringUtils.isNotEmpty(authProvider)) {
            this.authProvider = StringUtils.trim(authProvider);
        }


        if (StringUtils.isNotEmpty(applicationType)) {
            this.applicationType = StringUtils.trim(applicationType);
        }
        if (StringUtils.isNotEmpty(certificateType)) {
            this.certificateType = StringUtils.trim(certificateType);
        }
        if (StringUtils.isNotEmpty(recommendedOfficerName)) {
            this.recommendedOfficerName = StringUtils.trim(recommendedOfficerName);
        }
        if (StringUtils.isNotEmpty(certificatePostalAddress)) {
            this.certificatePostalAddress = StringUtils.trim(certificatePostalAddress);
        }

        if (StringUtils.isNotEmpty(nicFileType)) {
            this.nicFileType = StringUtils.trim(nicFileType);
        }
        if (StringUtils.isNotEmpty(pptFileType)) {
            this.pptFileType = StringUtils.trim(pptFileType);
        }
        if (StringUtils.isNotEmpty(birthCertificateFileType)) {
            this.birthCertificateFileType = StringUtils.trim(birthCertificateFileType);
        }
        if (StringUtils.isNotEmpty(letterOfReferenceFileType)) {
            this.letterOfReferenceFileType = StringUtils.trim(letterOfReferenceFileType);
        }


        if (StringUtils.isNotEmpty(nicBackFileType)) {
            this.nicBackFileType = StringUtils.trim(nicBackFileType);
        }
        if (StringUtils.isNotEmpty(pptBackFileType)) {
            this.pptBackFileType = StringUtils.trim(pptBackFileType);
        }
        if (StringUtils.isNotEmpty(birthCertificateFileBackType)) {
            this.birthCertificateFileBackType = StringUtils.trim(birthCertificateFileBackType);
        }

        if (StringUtils.isNotEmpty(letterConent)) {
            this.letterConent = StringUtils.trim(letterConent);
        }
        if (StringUtils.isNotEmpty(dateSentForClarification)) {
            this.dateSentForClarification = StringUtils.trim(dateSentForClarification);
        }
        if (StringUtils.isNotEmpty(dateRecievedClarification)) {
            this.dateRecievedClarification = StringUtils.trim(dateRecievedClarification);
        }
        if (StringUtils.isNotEmpty(dateOfBirthString)) {
            this.dateOfBirthString = StringUtils.trim(dateOfBirthString);
        }
        if (StringUtils.isNotEmpty(externalApprovalStatus)) {
            this.externalApprovalStatus = StringUtils.trim(externalApprovalStatus);
        }

        if (StringUtils.isNotEmpty(externalApprovedUser)) {
            this.externalApprovedUser = StringUtils.trim(externalApprovedUser);
        }
        if (StringUtils.isNotEmpty(internalSentUser)) {
            this.internalSentUser = StringUtils.trim(internalSentUser);
        }
        if (StringUtils.isNotEmpty(givenComment)) {
            this.givenComment = StringUtils.trim(givenComment);
        }


    }


    public List<Date> getDatesSentForClarification() {
        return datesSentForClarification;
    }


    public void setDatesSentForClarification(List<Date> datesSentForClarification) {
        this.datesSentForClarification = datesSentForClarification;
    }


    public List<Date> getDatesRecievedClarification() {
        return datesRecievedClarification;
    }


    public void setDatesRecievedClarification(List<Date> datesRecievedClarification) {
        this.datesRecievedClarification = datesRecievedClarification;
    }

  public String getCurrentNicNo() {
    return currentNicNo;
  }

    public void setCurrentNicNo(String currentNicNo) {
        this.currentNicNo = currentNicNo;
    }

    public String getSelectedNameOption() {
        return selectedNameOption;
    }

    public void setSelectedNameOption(String selectedNameOption) {
        this.selectedNameOption = selectedNameOption;
    }

    public String getAffidavitAttachPath() {
        return affidavitAttachPath;
    }

    public void setAffidavitAttachPath(String affidavitAttachPath) {
        this.affidavitAttachPath = affidavitAttachPath;
    }

    public String getDhaReviewStatus() {
        return dhaReviewStatus;
    }

    public void setDhaReviewStatus(String dhaReviewStatus) {
        this.dhaReviewStatus = dhaReviewStatus;
    }

    public String getAspReviewStatus() {
		return aspReviewStatus;
	}


	public void setAspReviewStatus(String aspReviewStatus) {
		this.aspReviewStatus = aspReviewStatus;
	}


	public List<String> getAdditionalDocsPaths() {
        return additionalDocsPaths;
    }

    public void setAdditionalDocsPaths(List<String> additionalDocsPaths) {
        this.additionalDocsPaths = additionalDocsPaths;
    }
}
