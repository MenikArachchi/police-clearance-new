package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationAuditVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;								//BIGINT
	private long applicationId;						//BIGINT
	private String referenceNo;						//VARCHAR(10)
	private String previousReferenceNo;				//VARCHAR(10)
	
	private long nationalityId;
	private String nationality;
	
	private int citizenOfSriLanka;
	private Date citizenshipObtainedDate;
	private Date dateOfBirth;
	private int age;								//INT
	
	private String nic;								//VARCHAR(10)
	private String passport;						//VARCHAR(45)
	
	private long highCommisionReferenceId;
	private String highCommisionReference;
	private String applicantNameAsNic;
	private String applicantNameAsPassport;
	private Date passportIssueDate;
	
	private String presentAddressLocal;				//VARCHAR(1000)
	private String presentAddressOverseas;			//VARCHAR(1000)
	
	private String sex;								//VARCHAR(3)
	private String occupation;						//VARCHAR(100)
	private String purpose;							//VARCHAR(3)
	private String applicantStatus;					//VARCHAR(3)
	
	private int previousCertificateApply;			//INT
	private long previousCertificateCountryId;		//BIGINT
	private String previousCertificateCountryName;	//VARCHAR(100)
	private int previousCertificateIssueStatus;		//INT
	private String previousCertificateReferenceNo;	//VARCHAR(45)
	private Date previousCertificateIssueDate;		//DATETIME
	
	private String authorizedHandoverPerson;		//VARCHAR(3)
	private String authorizedHandoverPersonName;	//VARCHAR(1000)
	private String authorizedHandoverPersonNicPassport;		//VARCHAR(10)
	
	private String highCommisionReferenceAddress;
	private String certificatePostAddressLineOne;	//VARCHAR(1000)
	private String certificatePostAddressLineTwo;	//VARCHAR(1000)
	
	private String certificatePostAddressCity;		//VARCHAR(500)
	private String certificatePostAddressState;		//VARCHAR(500)
	private String certificatePostAddressPostal;	//VARCHAR(500)
	private long certificatePostAddressCountry;				//BIGINT
	private String certificatePostAddressCountryName;		//VARCHAR(45)
	
	private long mobileCountryCodeId;
	private String mobileCountryCode;
	
	private String mobileNo;						//VARCHAR(20)
	private String email;							//VARCHAR(500)
	private String spouseFullName;					//VARCHAR(1000)
	private String spouseAddress;
	private String spouseNationality;
	private String spousePassport;
	private String spouseNic;
	
	private String passportAttachPath;				//VARCHAR(1500)
	private String nicAttachPath;					//VARCHAR(1500)
	private String birthCertificatePath;					//VARCHAR(1500)
	private String letterOfReferencePath;
	
	private int referredThroughBereau;
	
	private String applicationClearanceStatus;		//VARCHAR(2) 
	private String applicationReviewStatus;
	
	private int updatedUserId;						//INT
	private String updatedUserName;					//VARCHAR(500)
	private Date updatedDateTime;					//DATETIME
	
	private String polStatus;						//VARCHAR(2)
	private String cidStatus;						//VARCHAR(2)
	private String tidStatus;						//VARCHAR(2)
	private String sisStatus;						//VARCHAR(2)
	private String nicStatus;						//VARCHAR(2)
	private String imiStatus;						//VARCHAR(2)
	
	private int phqRecordLockStatus;			//INT
	private int cidRecordLockStatus;			//INT
	private int tidRecordLockStatus;			//INT
	private int sisRecordLockStatus;			//INT
	private int nicRecordLockStatus;			//INT
	private int imiRecordLockStatus;			//INT
	
	private String coApproved;							//VARCHAR(2)
	private String oicApproved;						//VARCHAR(2)
	private String aspApproved;						//VARCHAR(2)
	private String dhaApproved;						//VARCHAR(2)
	
	private String certificateSerialNo;				//VARCHAR(100)
	private String applicationQueue;					//VARCHAR(2)
	private String regPostNo;
	private String ipAddress;
	private String userFullName;
	private String userEmail;
	private String authProvider;
	
	private String certificateType;
	
	private int versionId; //INT
	
	private int certificatePrintedStatus;
	
	private List<AddressVO> applicantCertificateAddresses;
	
	//to store the constructed postal address to display in grids - by nadeeshani
	private String certificatePostalAddress;
	private int hasCurrentUserLocked;
	private String nicFileType;
	private String pptFileType;
	private int hasRequestClarification;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("applicationId", applicationId);
		map.put("referenceNo", referenceNo);
		map.put("nic", nic);
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
		map.put("mobileNo", mobileNo);
		map.put("email", email);
		map.put("passportAttachPath", passportAttachPath);
		map.put("nicAttachPath", nicAttachPath);
		map.put("spouseFullName", spouseFullName);
		map.put("spouseNationality", spouseNationality);
		map.put("spousePassport", spousePassport);
		map.put("spouseNic", spouseNic);
		map.put("applicationClearanceStatus", applicationClearanceStatus);
		map.put("updatedUserId", updatedUserId);
		map.put("updatedUserName", updatedUserName);
		map.put("updatedDateTime", updatedDateTime);
		map.put("polStatus", polStatus);
		map.put("cidStatus", cidStatus);
		map.put("tidStatus", tidStatus);
		map.put("sisStatus", sisStatus);
		map.put("nicStatus", nicStatus);
		map.put("imiStatus", imiStatus);
		map.put("coApproved", coApproved);
		map.put("oicApproved", oicApproved);
		map.put(" aspApproved", aspApproved);
		map.put("certificateSerialNo", certificateSerialNo);
		return map;
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

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
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

	public void setPreviousCertificateCountryName(
			String previousCertificateCountryName) {
		this.previousCertificateCountryName = previousCertificateCountryName;
	}

	public String getPreviousCertificateReferenceNo() {
		return previousCertificateReferenceNo;
	}

	public void setPreviousCertificateReferenceNo(
			String previousCertificateReferenceNo) {
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

	public void setCertificatePostAddressLineOne(
			String certificatePostAddressLineOne) {
		this.certificatePostAddressLineOne = certificatePostAddressLineOne;
	}

	public String getCertificatePostAddressLineTwo() {
		return certificatePostAddressLineTwo;
	}

	public void setCertificatePostAddressLineTwo(
			String certificatePostAddressLineTwo) {
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

	public String getApplicationClearanceStatus() {
		return applicationClearanceStatus;
	}

	public void setApplicationClearanceStatus(String applicationClearanceStatus) {
		this.applicationClearanceStatus = applicationClearanceStatus;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPreviousReferenceNo() {
		return previousReferenceNo;
	}

	public void setPreviousReferenceNo(String previousReferenceNo) {
		this.previousReferenceNo = previousReferenceNo;
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

	public int getPreviousCertificateIssueStatus() {
		return previousCertificateIssueStatus;
	}

	public void setPreviousCertificateIssueStatus(int previousCertificateIssueStatus) {
		this.previousCertificateIssueStatus = previousCertificateIssueStatus;
	}

	public String getAuthorizedHandoverPersonNicPassport() {
		return authorizedHandoverPersonNicPassport;
	}

	public void setAuthorizedHandoverPersonNicPassport(
			String authorizedHandoverPersonNicPassport) {
		this.authorizedHandoverPersonNicPassport = authorizedHandoverPersonNicPassport;
	}

	public String getHighCommisionReferenceAddress() {
		return highCommisionReferenceAddress;
	}

	public void setHighCommisionReferenceAddress(
			String highCommisionReferenceAddress) {
		this.highCommisionReferenceAddress = highCommisionReferenceAddress;
	}

	public String getCertificatePostAddressCountryName() {
		return certificatePostAddressCountryName;
	}

	public void setCertificatePostAddressCountryName(
			String certificatePostAddressCountryName) {
		this.certificatePostAddressCountryName = certificatePostAddressCountryName;
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

	public String getApplicationReviewStatus() {
		return applicationReviewStatus;
	}

	public void setApplicationReviewStatus(String applicationReviewStatus) {
		this.applicationReviewStatus = applicationReviewStatus;
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

	public String getApplicationQueue() {
		return applicationQueue;
	}

	public void setApplicationQueue(String applicationQueue) {
		this.applicationQueue = applicationQueue;
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

	public int getCertificatePrintedStatus() {
		return certificatePrintedStatus;
	}

	public void setCertificatePrintedStatus(int certificatePrintedStatus) {
		this.certificatePrintedStatus = certificatePrintedStatus;
	}

	public List<AddressVO> getApplicantCertificateAddresses() {
		return applicantCertificateAddresses;
	}

	public void setApplicantCertificateAddresses(
			List<AddressVO> applicantCertificateAddresses) {
		this.applicantCertificateAddresses = applicantCertificateAddresses;
	}

	public String getCertificatePostalAddress() {
		return certificatePostalAddress;
	}

	public void setCertificatePostalAddress(String certificatePostalAddress) {
		this.certificatePostalAddress = certificatePostalAddress;
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

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	
}
