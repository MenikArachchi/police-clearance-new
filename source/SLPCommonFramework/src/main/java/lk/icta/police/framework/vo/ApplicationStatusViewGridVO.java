package lk.icta.police.framework.vo;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.CommentDAO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationStatusViewGridVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long applicationId;                        //BIGINT
    private String referenceNo;                        //VARCHAR(10)
    private String nic;                                //VARCHAR(10)
    private String newNic;                                //VARCHAR(10)
    private String passport;                        //VARCHAR(45)
    private String applicantNameAsNic;
    private String applicantNameAsPassport;
    private Date dateOfBirth;
    private String applicantStatus;                    //VARCHAR(3)
    private String sex;                                //VARCHAR(3)
    private String occupation;                        //VARCHAR(100)
    private String purpose;                            //VARCHAR(3)
    private String mobileNo;
    private String email;                            //VARCHAR(500)
    private Date submittedDate;
    private Date applicationVerifiedDate;
    private String certificateSerialNo;

    private String applicationClearanceStatus;
    private String applicationReviewStatus;

    private Date certificateIssuedDate;
    private Date certificatePostedDate;

    private String recommendedOfficerName;
    private String recommendedOfficerRank;
    private Date recommendedDate;
    private String recommendedComment;

    private String coApproved;                            //VARCHAR(2)
    private String oicApproved;                        //VARCHAR(2)
    private String aspApproved;                        //VARCHAR(2)
    private String dhaApproved;                        //VARCHAR(2)
    private String digApproved;                        //VARCHAR(2)
    private String postedStatus;
    private String regPostNo;

    private List<ClearanceDateGridVO> addressStatusViewGridVOsPolice;
    private List<ClearanceDateGridVO> addressStatusViewGridVOsPhq;
    private List<ClearanceDateGridVO> cidStatusViewGridVOs;
    private List<ClearanceDateGridVO> sisStatusViewGridVOs;
    private List<ClearanceDateGridVO> nicStatusViewGridVOs;
    private List<ClearanceDateGridVO> tidStatusViewGridVOs;
    private List<ClearanceDateGridVO> imiStatusViewGridVOs;

    public ApplicationStatusViewGridVO() {
        super();
    }

    public ApplicationStatusViewGridVO(ApplicationVO applicationVO, List<ApplicationClearanceDate> applicationClearanceDates, Connection conn) {
        super();
        this.applicationId = applicationVO.getApplicationId();
        this.referenceNo = applicationVO.getReferenceNo();
        this.nic = applicationVO.getNic();
        this.newNic = applicationVO.getNewNic();
        this.passport = applicationVO.getPassport();
        this.applicantNameAsNic = applicationVO.getApplicantNameAsNic();
        this.applicantNameAsPassport = applicationVO.getApplicantNameAsPassport();
        this.dateOfBirth = applicationVO.getDateOfBirth();
        this.applicantStatus = applicationVO.getApplicantStatus();
        this.sex = applicationVO.getSex();
        this.occupation = applicationVO.getOccupation();
        this.purpose = applicationVO.getPurpose();
        this.mobileNo = applicationVO.getMobileNo();
        this.email = applicationVO.getEmail();
        this.submittedDate = applicationVO.getSubmittedDate();
        this.certificateSerialNo = applicationVO.getCertificateSerialNo();
        this.certificateIssuedDate = applicationVO.getCertificateIssueDate();
        this.certificatePostedDate = applicationVO.getCertificatePostedDate();
        this.postedStatus = "Pending";
        if (StringUtils.isNotEmpty(applicationVO.getRegPostNo())) {
            this.regPostNo = applicationVO.getRegPostNo();
            this.postedStatus = "Posted - " + applicationVO.getRegPostNo();
        }

        if (StringUtils.isNotEmpty(applicationVO.getCoApproved())) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getCoApproved());
            if (!(approvalStatus == null)) {
                this.coApproved = approvalStatus.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(applicationVO.getOicApproved())) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getOicApproved());
            if (!(approvalStatus == null)) {
                this.oicApproved = approvalStatus.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(applicationVO.getAspApproved())) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getAspApproved());
            if (!(approvalStatus == null)) {
                this.aspApproved = approvalStatus.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(applicationVO.getDigApproved())) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getDigApproved());
            if (!(approvalStatus == null)) {
                this.digApproved = approvalStatus.getDisplayName();
            }
        }


        if (StringUtils.isNotEmpty(applicationVO.getDhaApproved())) {
            PoliceEnumConstant.ApprovalStatus approvalStatus = PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getDhaApproved());
            if (!(approvalStatus == null)) {
                this.dhaApproved = approvalStatus.getDisplayName();
            }
        }


        if (StringUtils.isNotEmpty(applicationVO.getApplicationReviewStatus())) {
            PoliceEnumConstant.ApplicationReviewStatus reviewStatus = PoliceEnumConstant.ApplicationReviewStatus.fromCode(applicationVO.getApplicationReviewStatus());
            if (!(reviewStatus == null)) {
                this.applicationReviewStatus = reviewStatus.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(applicationVO.getApplicationClearanceStatus())) {
            PoliceEnumConstant.ApplicationClearenceStatus clearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
            if (!(clearenceStatus == null)) {
                this.applicationClearanceStatus = clearenceStatus.getDisplayName();
            }
        }

        addressStatusViewGridVOsPolice = new ArrayList<ClearanceDateGridVO>();
        addressStatusViewGridVOsPhq = new ArrayList<ClearanceDateGridVO>();
        cidStatusViewGridVOs = new ArrayList<ClearanceDateGridVO>();
        sisStatusViewGridVOs = new ArrayList<ClearanceDateGridVO>();
        nicStatusViewGridVOs = new ArrayList<ClearanceDateGridVO>();
        tidStatusViewGridVOs = new ArrayList<ClearanceDateGridVO>();
        imiStatusViewGridVOs = new ArrayList<ClearanceDateGridVO>();


        if (StringUtils.isNotEmpty(this.purpose)) {
            PoliceEnumConstant.ApplicationPurpose applicationPurpose = PoliceEnumConstant.ApplicationPurpose.fromCode(this.purpose);
            if (!(applicationPurpose == null)) {
                this.purpose = applicationPurpose.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(this.applicantStatus)) {
            PoliceEnumConstant.ApplicantStatus applicantStatus = PoliceEnumConstant.ApplicantStatus.fromCode(this.applicantStatus);
            if (!(applicantStatus == null)) {
                this.applicantStatus = applicantStatus.getDisplayName();
            }
        }

        if (StringUtils.isNotEmpty(this.sex)) {
            if (StringUtils.equalsIgnoreCase(this.sex, "f")) {
                this.sex = "Female";
            } else {
                this.sex = "Male";
            }
        }

        if (!(applicationClearanceDates == null || applicationClearanceDates.isEmpty())) {
            for (ApplicationClearanceDate applicationClearanceDate : applicationClearanceDates) {
                String comment = "";
                List<CommentsVO> commentsVOs = null;
                PoliceEnumConstant.UserDepartment department = PoliceEnumConstant.UserDepartment.fromStringCode(applicationClearanceDate.getDepartment());
                switch (department) {
                    case CID:
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.CID.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        cidStatusViewGridVOs.add(new ClearanceDateGridVO(applicationClearanceDate, applicationVO.getCidStatus(), comment));
                        break;
                    case IMI:
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.IMI.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        imiStatusViewGridVOs.add(new ClearanceDateGridVO(applicationClearanceDate, applicationVO.getImiStatus(), comment));
                        break;
                    case NIC:
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.NIC.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        nicStatusViewGridVOs.add(new ClearanceDateGridVO(applicationClearanceDate, applicationVO.getNicStatus(), comment));
                        break;
                    case POL:
                        AddressVO addressVO = AddressDAO.getInstance().getAddressById(applicationClearanceDate.getAddressId(), conn);
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndAddressId(applicationClearanceDate.getAddressId(), PoliceEnumConstant.CommentType.POL.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        ClearanceDateGridVO clearanceDateGridVO = new ClearanceDateGridVO(applicationClearanceDate, addressVO, comment);
                        addressStatusViewGridVOsPolice.add(clearanceDateGridVO);
//					boolean isAlreadyAvailable=checkIfAlreadyAdded(clearanceDateGridVO,addressStatusViewGridVOs);
//					if(!(isAlreadyAvailable)){
//						addressStatusViewGridVOs.add(clearanceDateGridVO);
//					}else{
//						updateAvailableGridVO(clearanceDateGridVO,addressStatusViewGridVOs,comment);
//					}
                        break;
                    case PHQ:
                        addressVO = AddressDAO.getInstance().getAddressById(applicationClearanceDate.getAddressId(), conn);
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndAddressId(applicationClearanceDate.getAddressId(), PoliceEnumConstant.CommentType.PHQ.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        clearanceDateGridVO = new ClearanceDateGridVO(applicationClearanceDate, addressVO, comment);
                        addressStatusViewGridVOsPhq.add(clearanceDateGridVO);
//					isAlreadyAvailable=checkIfAlreadyAdded(clearanceDateGridVO,addressStatusViewGridVOs);
//					if(!(isAlreadyAvailable)){
//						addressStatusViewGridVOs.add(clearanceDateGridVO);
//					}else{
//						updateAvailableGridVO(clearanceDateGridVO,addressStatusViewGridVOs,comment);
//					}
                        break;
                    case SIS:
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.SIS.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        sisStatusViewGridVOs.add(new ClearanceDateGridVO(applicationClearanceDate, applicationVO.getSisStatus(), comment));
                        break;
                    case TID:
                        commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.TID.toString(), conn);
                        if (!(commentsVOs == null || commentsVOs.isEmpty())) {
                            for (CommentsVO commentsVO : commentsVOs) {
                                comment = comment + " " + commentsVO.getComment();
                            }
                        }
                        tidStatusViewGridVOs.add(new ClearanceDateGridVO(applicationClearanceDate, applicationVO.getTidStatus(), comment));
                        break;
                    default:
                        break;
                }
            }
        }
    }

//	private void updateAvailableGridVO(ClearanceDateGridVO clearanceDateGridVO,	List<ClearanceDateGridVO> addressStatusViewGridVOs2, String comment) {
//		ClearanceDateGridVO clearanceDateGridVOAvailable=null;
//		for (ClearanceDateGridVO clearanceDateGridVO2 : addressStatusViewGridVOs2) {
//			if(clearanceDateGridVO.getAddressId()==clearanceDateGridVO2.getAddressId()){
//				clearanceDateGridVOAvailable=clearanceDateGridVO2;
//			}
//		}
//		if(!(clearanceDateGridVOAvailable==null)){
//			clearanceDateGridVOAvailable.setComment(clearanceDateGridVOAvailable.getComment() + " " + comment);
//			if(clearanceDateGridVO.getRespondedUserId()>0){
//				if(clearanceDateGridVOAvailable.getRespondedUserId()<=0){
//					clearanceDateGridVOAvailable.setRespondedUserId(clearanceDateGridVO.getRespondedUserId());
//				}
//			}
//			if(StringUtils.isNotEmpty(clearanceDateGridVO.getRespondedUserName())){
//				if(StringUtils.isEmpty(clearanceDateGridVOAvailable.getRespondedUserName())){
//					clearanceDateGridVOAvailable.setRespondedUserName(clearanceDateGridVO.getRespondedUserName());
//				}
//			}
//			
//			if(StringUtils.isNotEmpty(clearanceDateGridVO.getAdverse())){
//				if(StringUtils.isEmpty(clearanceDateGridVOAvailable.getAdverse())){
//					clearanceDateGridVOAvailable.setAdverse(clearanceDateGridVO.getAdverse());
//				}
//			}
//			
//			if(!(clearanceDateGridVO.getReceivedDate()==null)){
//				if(clearanceDateGridVOAvailable.getReceivedDate()==null){
//					clearanceDateGridVOAvailable.setReceivedDate(clearanceDateGridVO.getReceivedDate());
//				}
//			}
//			
//		}		
//	}


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

    public String getApplicantNameAsNic() {
        return applicantNameAsNic;
    }

    public void setApplicantNameAsNic(String applicantNameAsNic) {
        this.applicantNameAsNic = applicantNameAsNic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
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

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Date getApplicationVerifiedDate() {
        return applicationVerifiedDate;
    }

    public void setApplicationVerifiedDate(Date applicationVerifiedDate) {
        this.applicationVerifiedDate = applicationVerifiedDate;
    }


    public Date getRecommendedDate() {
        return recommendedDate;
    }

    public void setRecommendedDate(Date recommendedDate) {
        this.recommendedDate = recommendedDate;
    }


    public List<ClearanceDateGridVO> getAddressStatusViewGridVOsPolice() {
        return addressStatusViewGridVOsPolice;
    }

    public void setAddressStatusViewGridVOsPolice(
            List<ClearanceDateGridVO> addressStatusViewGridVOsPolice) {
        this.addressStatusViewGridVOsPolice = addressStatusViewGridVOsPolice;
    }

    public List<ClearanceDateGridVO> getAddressStatusViewGridVOsPhq() {
        return addressStatusViewGridVOsPhq;
    }

    public void setAddressStatusViewGridVOsPhq(
            List<ClearanceDateGridVO> addressStatusViewGridVOsPhq) {
        this.addressStatusViewGridVOsPhq = addressStatusViewGridVOsPhq;
    }

    public List<ClearanceDateGridVO> getCidStatusViewGridVOs() {
        return cidStatusViewGridVOs;
    }

    public void setCidStatusViewGridVOs(
            List<ClearanceDateGridVO> cidStatusViewGridVOs) {
        this.cidStatusViewGridVOs = cidStatusViewGridVOs;
    }

    public List<ClearanceDateGridVO> getSisStatusViewGridVOs() {
        return sisStatusViewGridVOs;
    }

    public void setSisStatusViewGridVOs(
            List<ClearanceDateGridVO> sisStatusViewGridVOs) {
        this.sisStatusViewGridVOs = sisStatusViewGridVOs;
    }

    public List<ClearanceDateGridVO> getNicStatusViewGridVOs() {
        return nicStatusViewGridVOs;
    }

    public void setNicStatusViewGridVOs(
            List<ClearanceDateGridVO> nicStatusViewGridVOs) {
        this.nicStatusViewGridVOs = nicStatusViewGridVOs;
    }

    public List<ClearanceDateGridVO> getTidStatusViewGridVOs() {
        return tidStatusViewGridVOs;
    }

    public void setTidStatusViewGridVOs(
            List<ClearanceDateGridVO> tidStatusViewGridVOs) {
        this.tidStatusViewGridVOs = tidStatusViewGridVOs;
    }

    public List<ClearanceDateGridVO> getImiStatusViewGridVOs() {
        return imiStatusViewGridVOs;
    }

    public void setImiStatusViewGridVOs(
            List<ClearanceDateGridVO> imiStatusViewGridVOs) {
        this.imiStatusViewGridVOs = imiStatusViewGridVOs;
    }

    public String getCertificateSerialNo() {
        return certificateSerialNo;
    }

    public void setCertificateSerialNo(String certificateSerialNo) {
        this.certificateSerialNo = certificateSerialNo;
    }

    public String getRecommendedOfficerName() {
        return recommendedOfficerName;
    }

    public void setRecommendedOfficerName(String recommendedOfficerName) {
        this.recommendedOfficerName = recommendedOfficerName;
    }

    public String getRecommendedOfficerRank() {
        return recommendedOfficerRank;
    }

    public void setRecommendedOfficerRank(String recommendedOfficerRank) {
        this.recommendedOfficerRank = recommendedOfficerRank;
    }

    public String getRecommendedComment() {
        return recommendedComment;
    }

    public void setRecommendedComment(String recommendedComment) {
        this.recommendedComment = recommendedComment;
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

    public Date getCertificateIssuedDate() {
        return certificateIssuedDate;
    }

    public void setCertificateIssuedDate(Date certificateIssuedDate) {
        this.certificateIssuedDate = certificateIssuedDate;
    }

    public Date getCertificatePostedDate() {
        return certificatePostedDate;
    }

    public void setCertificatePostedDate(Date certificatePostedDate) {
        this.certificatePostedDate = certificatePostedDate;
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

    public String getDigApproved() {
        return digApproved;
    }

    public void setDigApproved(String digApproved) {
        this.digApproved = digApproved;
    }

    public String getPostedStatus() {
        return postedStatus;
    }

    public void setPostedStatus(String postedStatus) {
        this.postedStatus = postedStatus;
    }

    public String getRegPostNo() {
        return regPostNo;
    }

    public void setRegPostNo(String regPostNo) {
        this.regPostNo = regPostNo;
    }

    public String getNewNic() {
        return newNic;
    }

    public void setNewNic(String newNic) {
        this.newNic = newNic;
    }

    public String getApplicantNameAsPassport() {
        return applicantNameAsPassport;
    }

    public void setApplicantNameAsPassport(String applicantNameAsPassport) {
        this.applicantNameAsPassport = applicantNameAsPassport;
    }
}
