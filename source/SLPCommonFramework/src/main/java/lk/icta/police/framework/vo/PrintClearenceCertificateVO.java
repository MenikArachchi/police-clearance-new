package lk.icta.police.framework.vo;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrintClearenceCertificateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String highCommisionReferenceAddress;
    private String applicantNameAsNic;
    private String nic;
    private String certificatePostalAddress;
    private String applicationRequestedPeriod;
    private String certificateAuthPersonName;
    private String certificateAuthPersonDesignation;
    private String certificateAuthPersonAddress;
    private String certificateSerialNo;
    private String letterContent;
    private String passportNo;

    private String highCommisionner;
    private String deliveryType;

    private String title;

    private String applicantNameAsPassport;
    
    private String selectedNameOption;

    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("highCommisionReferenceAddress", highCommisionReferenceAddress);
        map.put("applicantNameAsNic", applicantNameAsNic);
        map.put("nic", nic);
        map.put("certificatePostalAddress", certificatePostalAddress);
        map.put("applicationRequestedPeriod", applicationRequestedPeriod);
        map.put("certificateAuthPersonName", certificateAuthPersonName);
        map.put("certificateSerialNo", certificateSerialNo);
        map.put("letterContent", letterContent);
        map.put("highCommisionner", highCommisionner);
        map.put("deliveryType", deliveryType);
        map.put("passportNo", passportNo);
        map.put("certificateAuthPersonDesignation", certificateAuthPersonDesignation);
        map.put("certificateAuthPersonAddress", certificateAuthPersonAddress);
        map.put("applicantNameAsPassport", applicantNameAsPassport);
        map.put("selectedNameOption", selectedNameOption);
        return map;
    }


    public PrintClearenceCertificateVO() {
        super();
    }

    public PrintClearenceCertificateVO(ApplicationVO applicationVO, String certificateAuthPersonName,
                                       String certificateAuthPersonDesignation, String certificateAuthPersonAddress, CommissionerVO commissionerVO) {
        super();
        this.highCommisionReferenceAddress = StringUtils.upperCase(applicationVO.getHighCommisionReferenceAddress());

        String nameSource = applicationVO.getSelectedNameOption();

        if (PoliceEnumConstant.NameSource.NIC.getSource().equals(nameSource)) {
            this.applicantNameAsNic = StringUtils.upperCase(applicationVO.getApplicantNameAsNic());
        } else if (PoliceEnumConstant.NameSource.PASSPORT.getSource().equals(nameSource)) {
            this.applicantNameAsNic = StringUtils.upperCase(applicationVO.getApplicantNameAsPassport());
        }

        this.nic = StringUtils.upperCase(applicationVO.getNic());
        this.passportNo = StringUtils.upperCase(applicationVO.getPassport());
        if (StringUtils.isNotEmpty(applicationVO.getPresentAddressLocal())) {
            this.certificatePostalAddress = StringUtils.upperCase(applicationVO.getPresentAddressLocal());
        } else {
            this.certificatePostalAddress = StringUtils.upperCase(applicationVO.getPresentAddressOverseas());
        }
        this.applicationRequestedPeriod = constructApplicationRequestPeriod(applicationVO.getApplicantCertificateAddresses());
        this.certificateAuthPersonName = certificateAuthPersonName;
        this.certificateSerialNo = applicationVO.getCertificateSerialNo();
        this.letterContent = applicationVO.getLetterConent();

        this.certificateAuthPersonDesignation = certificateAuthPersonDesignation;
        this.certificateAuthPersonAddress = certificateAuthPersonAddress;

        this.highCommisionner = StringUtils.upperCase(commissionerVO.getCommissionEmbassyConsultantName()) + ", " + StringUtils.upperCase(commissionerVO.getCommissionEmbassyConsultantAddress());
        this.selectedNameOption = StringUtils.upperCase(applicationVO.getSelectedNameOption());
        
        if (StringUtils.isNotEmpty(applicationVO.getDeliveryType())) {
            this.deliveryType = StringUtils.upperCase(applicationVO.getDeliveryType());
        }

        if (StringUtils.isNotEmpty(applicationVO.getApplicantStatus())) {
            PoliceEnumConstant.ApplicantStatus status = PoliceEnumConstant.ApplicantStatus.fromCode(applicationVO.getApplicantStatus());
            switch (status) {
                case DIV:
                    if (StringUtils.equalsIgnoreCase(applicationVO.getSex(), "F")) {
                        this.title = "Ms.";
                    } else {
                        this.title = "Mr.";
                    }
                    break;
                case MAR:
                    if (StringUtils.equalsIgnoreCase(applicationVO.getSex(), "F")) {
                        this.title = "Ms.";
                    } else {
                        this.title = "Mr.";
                    }
                    break;
                case REV:
                    if (StringUtils.equalsIgnoreCase(applicationVO.getSex(), "F")) {
                        this.title = "Rev.";
                    } else {
                        this.title = "Rev.";
                    }
                    break;
                case UNM:
                    if (StringUtils.equalsIgnoreCase(applicationVO.getSex(), "F")) {
                        this.title = "Ms.";
                    } else {
                        this.title = "Mr.";
                    }
                    break;
                case WID:
                    if (StringUtils.equalsIgnoreCase(applicationVO.getSex(), "F")) {
                        this.title = "Ms.";
                    } else {
                        this.title = "Mr.";
                    }
                    break;
                default:
                    break;
            }
        }

    }


    private String constructApplicationRequestPeriod(List<AddressVO> applicantCertificateAddresses) {
        if (!(applicantCertificateAddresses == null || applicantCertificateAddresses.isEmpty())) {
            //get the initial from date first
            Collections.sort(applicantCertificateAddresses, AddressVO.AddressFromDateComparator);

            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (AddressVO addressVO : applicantCertificateAddresses) {
                if (!(addressVO.getFromDate() == null || addressVO.getToDate() == null)) {
                    i++;
                    sb.append(constructDateStrings(addressVO.getFromDate()))
                            .append(" to ")
                            .append(constructDateStrings(addressVO.getToDate()));

                    if (!(i >= (applicantCertificateAddresses.size()))) {
                        sb.append(", ");
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }


    public String constructDateStrings(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (!(date == null)) {
            return dateFormat.format(date);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.toBasicMap().toString();
    }

    public String getHighCommisionReferenceAddress() {
        return highCommisionReferenceAddress;
    }

    public void setHighCommisionReferenceAddress(
            String highCommisionReferenceAddress) {
        this.highCommisionReferenceAddress = highCommisionReferenceAddress;
    }

    public String getApplicantNameAsNic() {
        return applicantNameAsNic;
    }

    public void setApplicantNameAsNic(String applicantNameAsNic) {
        this.applicantNameAsNic = applicantNameAsNic;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCertificatePostalAddress() {
        return certificatePostalAddress;
    }

    public void setCertificatePostalAddress(String certificatePostalAddress) {
        this.certificatePostalAddress = certificatePostalAddress;
    }

    public String getApplicationRequestedPeriod() {
        return applicationRequestedPeriod;
    }

    public void setApplicationRequestedPeriod(String applicationRequestedPeriod) {
        this.applicationRequestedPeriod = applicationRequestedPeriod;
    }

    public String getCertificateAuthPersonName() {
        return certificateAuthPersonName;
    }

    public void setCertificateAuthPersonName(String certificateAuthPersonName) {
        this.certificateAuthPersonName = certificateAuthPersonName;
    }

    public String getCertificateSerialNo() {
        return certificateSerialNo;
    }

    public void setCertificateSerialNo(String certificateSerialNo) {
        this.certificateSerialNo = certificateSerialNo;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getHighCommisionner() {
        return highCommisionner;
    }

    public void setHighCommisionner(String highCommisionner) {
        this.highCommisionner = highCommisionner;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getCertificateAuthPersonDesignation() {
        return certificateAuthPersonDesignation;
    }

    public void setCertificateAuthPersonDesignation(
            String certificateAuthPersonDesignation) {
        this.certificateAuthPersonDesignation = certificateAuthPersonDesignation;
    }

    public String getCertificateAuthPersonAddress() {
        return certificateAuthPersonAddress;
    }

    public void setCertificateAuthPersonAddress(String certificateAuthPersonAddress) {
        this.certificateAuthPersonAddress = certificateAuthPersonAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicantNameAsPassport() {
        return applicantNameAsPassport;
    }

    public void setApplicantNameAsPassport(String applicantNameAsPassport) {
        this.applicantNameAsPassport = applicantNameAsPassport;
    }


	public String getSelectedNameOption() {
		return selectedNameOption;
	}


	public void setSelectedNameOption(String selectedNameOption) {
		this.selectedNameOption = selectedNameOption;
	}
}
