package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.CommentDAO;

import org.apache.commons.lang3.StringUtils;

public class PoliceApplicationIssueReportVO  implements Serializable,Comparable<PoliceApplicationIssueReportVO> {
	
	private static final long serialVersionUID = 1L;
	
	private int indexId;
	
	private long applicationId;						//BIGINT
	private String referenceNo;						//VARCHAR(10)
	
	private String nameAndAddress;
	private String nic;	
	private String mobileNo;
	
	private Date submittedDate;
	
	private Date certificateIssuedDate;
	
	private String certificateSerialNo;	
	
	private Date verifiedDate;
	
	private String recommendedOfficer;
	
	private Date cidReceivedDate;
	private String cidComment;
	private String cidAdverse;
	
	private Date tidReceivedDate;
	private String tidComment;
	private String tidAdverse;
	
	private Date nicReceivedDate;
	private String nicComment;
	private String nicAdverse;
	
	private Date sisReceivedDate;
	private String sisComment;
	private String sisAdverse;
	
	private Date imiReceivedDate;
	private String imiComment;
	private String imiAdverse;
	
	private Date polReceivedDate;
	private String polComment;
	private String polAdverse;
	
	private Date phqReceivedDate;
	private String phqComment;
	private String phqAdverse;
	
	
	

	public PoliceApplicationIssueReportVO(int indexId,Date verifiedDate,ApplicationVO applicationVO, List<ApplicationClearanceDate> applicationClearanceDates,Connection conn) {
		this.indexId=indexId;
		this.verifiedDate=verifiedDate;
		this.applicationId=applicationVO.getApplicationId();
		this.referenceNo=applicationVO.getReferenceNo();
		this.nameAndAddress=getNameAndAddress(applicationVO);
		this.nic=applicationVO.getNic();
		this.mobileNo=applicationVO.getMobileNo();
		this.submittedDate=applicationVO.getSubmittedDate();
		this.certificateIssuedDate=applicationVO.getCertificateIssueDate();
		this.certificateSerialNo=applicationVO.getCertificateSerialNo();
		this.recommendedOfficer=applicationVO.getRecommendedOfficerName();
		if(!(applicationClearanceDates==null || applicationClearanceDates.isEmpty())){
			for (ApplicationClearanceDate applicationClearanceDate : applicationClearanceDates) {
				String comment="";
				List<CommentsVO> commentsVOs=null;
				PoliceEnumConstant.UserDepartment department=PoliceEnumConstant.UserDepartment.fromStringCode(applicationClearanceDate.getDepartment());
				switch (department) {
				case CID:
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.CID.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					cidReceivedDate=applicationClearanceDate.getResponsedDate();
					cidComment=comment;
					cidAdverse=applicationVO.getCidStatus();
					break;
				case IMI:
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.IMI.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					imiReceivedDate=applicationClearanceDate.getResponsedDate();
					imiComment=comment;
					imiAdverse=applicationVO.getImiStatus();
					break;
				case NIC:
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.NIC.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					nicReceivedDate=applicationClearanceDate.getResponsedDate();
					nicComment=comment;
					nicAdverse=applicationVO.getNicStatus();
					break;
				case POL:
					AddressVO addressVO=AddressDAO.getInstance().getAddressById(applicationClearanceDate.getAddressId(), conn);
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndAddressId(applicationClearanceDate.getAddressId(), PoliceEnumConstant.CommentType.POL.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					polReceivedDate=applicationClearanceDate.getResponsedDate();
					polComment=comment;
					polAdverse=addressVO.getPoliceStatus();
					break;
				case PHQ:
					addressVO=AddressDAO.getInstance().getAddressById(applicationClearanceDate.getAddressId(), conn);
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndAddressId(applicationClearanceDate.getAddressId(), PoliceEnumConstant.CommentType.PHQ.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}					
					phqReceivedDate=applicationClearanceDate.getResponsedDate();
					phqComment=comment;
					phqAdverse=addressVO.getPoliceStatus();
					break;
				case SIS:
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.SIS.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					sisReceivedDate=applicationClearanceDate.getResponsedDate();
					sisComment=comment;
					sisAdverse=applicationVO.getSisStatus();
					break;
				case TID:
					commentsVOs=CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.CommentType.TID.toString(), conn);
					if(!(commentsVOs==null || commentsVOs.isEmpty())){
						for (CommentsVO commentsVO : commentsVOs) {
							comment = comment + " " + commentsVO.getComment();
						}
					}
					tidReceivedDate=applicationClearanceDate.getResponsedDate();
					tidComment=comment;
					tidAdverse=applicationVO.getTidStatus();
					break;
				default:
					break;
				}
			}
		}
	}



	private String getNameAndAddress(ApplicationVO applicationVO) {
		StringBuilder nameAndAddress=new StringBuilder();
		if(StringUtils.isNotEmpty(applicationVO.getApplicantNameAsNic())){
			nameAndAddress.append(applicationVO.getApplicantNameAsNic());
		}else if(StringUtils.isNotEmpty(applicationVO.getApplicantNameAsPassport())){
			nameAndAddress.append(applicationVO.getApplicantNameAsPassport());
		}
		
		if(StringUtils.isNotEmpty(applicationVO.getPresentAddressLocal())){
			nameAndAddress.append(", ").append(applicationVO.getPresentAddressLocal());
		}else if(StringUtils.isNotEmpty(applicationVO.getPresentAddressOverseas())){
			nameAndAddress.append(", ").append(applicationVO.getPresentAddressOverseas());
		}
		
		return nameAndAddress.toString();
	}



	public PoliceApplicationIssueReportVO() {
		super();
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



	public String getNameAndAddress() {
		return nameAndAddress;
	}



	public void setNameAndAddress(String nameAndAddress) {
		this.nameAndAddress = nameAndAddress;
	}



	public String getNic() {
		return nic;
	}



	public void setNic(String nic) {
		this.nic = nic;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public Date getSubmittedDate() {
		return submittedDate;
	}



	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}







	public Date getCertificateIssuedDate() {
		return certificateIssuedDate;
	}



	public void setCertificateIssuedDate(Date certificateIssuedDate) {
		this.certificateIssuedDate = certificateIssuedDate;
	}



	@Override
	public int compareTo(PoliceApplicationIssueReportVO o) {
		return (new Long(this.getApplicationId())).compareTo(new Long(o.getApplicationId()));
	}



	public String getCertificateSerialNo() {
		return certificateSerialNo;
	}



	public void setCertificateSerialNo(String certificateSerialNo) {
		this.certificateSerialNo = certificateSerialNo;
	}



	public Date getCidReceivedDate() {
		return cidReceivedDate;
	}



	public void setCidReceivedDate(Date cidReceivedDate) {
		this.cidReceivedDate = cidReceivedDate;
	}



	public String getCidComment() {
		return cidComment;
	}



	public void setCidComment(String cidComment) {
		this.cidComment = cidComment;
	}



	public String getCidAdverse() {
		return cidAdverse;
	}



	public void setCidAdverse(String cidAdverse) {
		this.cidAdverse = cidAdverse;
	}



	public Date getTidReceivedDate() {
		return tidReceivedDate;
	}



	public void setTidReceivedDate(Date tidReceivedDate) {
		this.tidReceivedDate = tidReceivedDate;
	}



	public String getTidComment() {
		return tidComment;
	}



	public void setTidComment(String tidComment) {
		this.tidComment = tidComment;
	}



	public String getTidAdverse() {
		return tidAdverse;
	}



	public void setTidAdverse(String tidAdverse) {
		this.tidAdverse = tidAdverse;
	}



	public Date getNicReceivedDate() {
		return nicReceivedDate;
	}



	public void setNicReceivedDate(Date nicReceivedDate) {
		this.nicReceivedDate = nicReceivedDate;
	}



	public String getNicComment() {
		return nicComment;
	}



	public void setNicComment(String nicComment) {
		this.nicComment = nicComment;
	}



	public String getNicAdverse() {
		return nicAdverse;
	}



	public void setNicAdverse(String nicAdverse) {
		this.nicAdverse = nicAdverse;
	}



	public Date getSisReceivedDate() {
		return sisReceivedDate;
	}



	public void setSisReceivedDate(Date sisReceivedDate) {
		this.sisReceivedDate = sisReceivedDate;
	}



	public String getSisComment() {
		return sisComment;
	}



	public void setSisComment(String sisComment) {
		this.sisComment = sisComment;
	}



	public String getSisAdverse() {
		return sisAdverse;
	}



	public void setSisAdverse(String sisAdverse) {
		this.sisAdverse = sisAdverse;
	}



	public Date getImiReceivedDate() {
		return imiReceivedDate;
	}



	public void setImiReceivedDate(Date imiReceivedDate) {
		this.imiReceivedDate = imiReceivedDate;
	}



	public String getImiComment() {
		return imiComment;
	}



	public void setImiComment(String imiComment) {
		this.imiComment = imiComment;
	}



	public String getImiAdverse() {
		return imiAdverse;
	}



	public void setImiAdverse(String imiAdverse) {
		this.imiAdverse = imiAdverse;
	}



	public Date getPolReceivedDate() {
		return polReceivedDate;
	}



	public void setPolReceivedDate(Date polReceivedDate) {
		this.polReceivedDate = polReceivedDate;
	}



	public String getPolComment() {
		return polComment;
	}



	public void setPolComment(String polComment) {
		this.polComment = polComment;
	}



	public String getPolAdverse() {
		return polAdverse;
	}



	public void setPolAdverse(String polAdverse) {
		this.polAdverse = polAdverse;
	}



	public Date getPhqReceivedDate() {
		return phqReceivedDate;
	}



	public void setPhqReceivedDate(Date phqReceivedDate) {
		this.phqReceivedDate = phqReceivedDate;
	}



	public String getPhqComment() {
		return phqComment;
	}



	public void setPhqComment(String phqComment) {
		this.phqComment = phqComment;
	}



	public String getPhqAdverse() {
		return phqAdverse;
	}



	public void setPhqAdverse(String phqAdverse) {
		this.phqAdverse = phqAdverse;
	}



	public int getIndexId() {
		return indexId;
	}



	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}



	public Date getVerifiedDate() {
		return verifiedDate;
	}



	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}



	public String getRecommendedOfficer() {
		return recommendedOfficer;
	}



	public void setRecommendedOfficer(String recommendedOfficer) {
		this.recommendedOfficer = recommendedOfficer;
	}
	
	
}
