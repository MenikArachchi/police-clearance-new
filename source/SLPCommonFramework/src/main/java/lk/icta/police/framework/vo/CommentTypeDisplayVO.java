package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lk.icta.police.framework.constant.PoliceEnumConstant;

public class CommentTypeDisplayVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//PHQ(1), POL(2), CID(3), TID(4), SIS(5), NIC(6), IMI(7);
	private String nicComment;
	private String cidComment;
	private String tidComment;
	private String sisComment;
	private String imiComment;
	private String checkingOfficerComment;
	private String oicComment;
	private String aspComment;
	private String dhaComment;
	private String digComment;
	
	private String nicCommentDate;
	private String cidCommentDate;
	private String tidCommentDate;
	private String sisCommentDate;
	private String imiCommentDate;
	private String checkingOfficerCommentDate;
	private String oicCommentDate;
	private String aspCommentDate;
	private String dhaCommentDate;
	private String digCommentDate;
	
	private String cidStatusText;						
	private String tidStatusText;						
	private String sisStatusText;						
	private String nicStatusText;						
	private String imiStatusText;
	private String polStatusText;
	
	private List<String> historyAdverseRecords;
	private List<PoliceCommentTypeDisplayVO> policeCommentTypeDisplayVOs;

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("nicComment", nicComment);
		map.put("cidComment", cidComment);
		map.put("tidComment", tidComment);
		map.put("sisComment", sisComment);
		map.put("imiComment", imiComment);
		map.put("checkingOfficerComment", checkingOfficerComment);
		map.put("oicComment", oicComment);
		map.put("aspComment", aspComment);
		map.put("policeComment", policeCommentTypeDisplayVOs);
		
		map.put("nicCommentDate", nicCommentDate);
		map.put("cidCommentDate", cidCommentDate);
		map.put("tidCommentDate", tidCommentDate);
		map.put("sisCommentDate", sisCommentDate);
		map.put("imiCommentDate", imiCommentDate);
		map.put("checkingOfficerCommentDate", checkingOfficerCommentDate);
		map.put("oicCommentDate", oicCommentDate);
		map.put("aspCommentDate", aspCommentDate);
		
		map.put("cidStatusText", cidStatusText);
		map.put("tidStatusText", tidStatusText);
		map.put("sisStatusText", sisStatusText);
		map.put("nicStatusText", nicStatusText);
		map.put("imiStatusText", imiStatusText);
		map.put("polStatusText", polStatusText);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	
	
	public CommentTypeDisplayVO(List<CommentsVO> commentVos, ApplicationVO applicationVO,List<String> historyAdverseRecords, Connection conn) {
		super();
		String defaultComment="No comment is available!";
		this.policeCommentTypeDisplayVOs=new ArrayList<PoliceCommentTypeDisplayVO>();
		
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		
		PoliceEnumConstant.ApprovalStatus policeApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getPolStatus());
		if(!(policeApproval==null)){
			this.polStatusText=policeApproval.getDisplayName();
		}
		
		PoliceEnumConstant.ApprovalStatus cidApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getCidStatus());
		if(!(cidApproval==null)){
			this.cidStatusText=cidApproval.getDisplayName();
		}
		
		PoliceEnumConstant.ApprovalStatus tidApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getTidStatus());
		if(!(tidApproval==null)){
			this.tidStatusText=tidApproval.getDisplayName();
		}
		
		PoliceEnumConstant.ApprovalStatus sisApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getSisStatus());
		if(!(sisApproval==null)){
			this.sisStatusText=sisApproval.getDisplayName();
		}
		
		PoliceEnumConstant.ApprovalStatus nicApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getNicStatus());
		if(!(nicApproval==null)){
			this.nicStatusText=nicApproval.getDisplayName();
		}
		
		PoliceEnumConstant.ApprovalStatus imiApproval=PoliceEnumConstant.ApprovalStatus.fromCode(applicationVO.getImiStatus());
		if(!(imiApproval==null)){
			this.imiStatusText=imiApproval.getDisplayName();
		}
		
		this.historyAdverseRecords=historyAdverseRecords;
		
		this.aspComment=defaultComment;
		this.checkingOfficerComment=defaultComment;
		this.cidComment=defaultComment;
		this.imiComment=defaultComment;
		this.nicComment=defaultComment;
		this.oicComment=defaultComment;
		this.sisComment=defaultComment;
		this.tidComment=defaultComment;
		this.digComment=defaultComment;
		this.dhaComment=defaultComment;
		if(!(commentVos==null || commentVos.isEmpty())){
			for (CommentsVO commentsVO : commentVos) {	
				PoliceCommentTypeDisplayVO policeCommentTypeDisplayVO=null;
				String commentType=commentsVO.getCommentType();
				PoliceEnumConstant.CommentType type=PoliceEnumConstant.CommentType.fromCode(commentType);
				switch (type) {
				case NIC:
					nicComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.nicCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case CID:
					cidComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.cidCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case TID:
					tidComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.tidCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case SIS:
					sisComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.sisCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case IMI:
					imiComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.imiCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case ASP:
					aspComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.aspCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case CHK:
					checkingOfficerComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.checkingOfficerCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case OIC:
					oicComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.oicCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case POL:
					policeCommentTypeDisplayVO=getAddedPoliceCommentTypeDisplayVO(commentsVO.getAddressId());
					if(policeCommentTypeDisplayVO==null){
						policeCommentTypeDisplayVO=new PoliceCommentTypeDisplayVO(commentsVO, conn);
						this.policeCommentTypeDisplayVOs.add(policeCommentTypeDisplayVO);
					}else{
						policeCommentTypeDisplayVO.updatePoliceCommentTypeDisplayVO(commentsVO);
					}
					break;
				case DHA:
					dhaComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.dhaCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case DIG:
					digComment=commentsVO.getComment();
					if(!(commentsVO.getCreatedDateTime()==null)){
						this.digCommentDate=format.format(commentsVO.getCreatedDateTime());
					}
					break;
				case PHQ:
					policeCommentTypeDisplayVO=getAddedPoliceCommentTypeDisplayVO(commentsVO.getAddressId());
					if(policeCommentTypeDisplayVO==null){
						policeCommentTypeDisplayVO=new PoliceCommentTypeDisplayVO(commentsVO, conn);
						this.policeCommentTypeDisplayVOs.add(policeCommentTypeDisplayVO);
					}else{
						policeCommentTypeDisplayVO.updatePoliceCommentTypeDisplayVO(commentsVO);
					}
					break;
				default:
					System.out.println("UNKNOWN COMMENT TYPE :" + type);
					break;
				}
			}
		}
		
		if(StringUtils.isEmpty(nicComment)){
			this.nicComment=defaultComment;
		}
		if(StringUtils.isEmpty(cidComment)){
			this.cidComment=defaultComment;
		}
		if(StringUtils.isEmpty(tidComment)){
			this.tidComment=defaultComment;
		}
		if(StringUtils.isEmpty(sisComment)){
			this.sisComment=defaultComment;
		}
		if(StringUtils.isEmpty(imiComment)){
			this.imiComment=defaultComment;
		}
		if(StringUtils.isEmpty(checkingOfficerComment)){
			this.checkingOfficerComment=defaultComment;
		}
		if(StringUtils.isEmpty(oicComment)){
			this.oicComment=defaultComment;
		}
		if(StringUtils.isEmpty(aspComment)){
			this.aspComment=defaultComment;
		}
		if(StringUtils.isEmpty(dhaComment)){
			this.dhaComment=defaultComment;
		}
		if(StringUtils.isEmpty(digComment)){
			this.digComment=defaultComment;
		}
		
	}
	
	private PoliceCommentTypeDisplayVO getAddedPoliceCommentTypeDisplayVO(long addressId) {
		if(!(this.policeCommentTypeDisplayVOs==null || this.policeCommentTypeDisplayVOs.isEmpty())){
			for (PoliceCommentTypeDisplayVO commentTypeDisplayVO : this.policeCommentTypeDisplayVOs) {
				if(commentTypeDisplayVO.getAddressId()==addressId){
					return commentTypeDisplayVO;
				}
			}
		}
		return null;
	}



	public CommentTypeDisplayVO() {
		super();
		
	}

	public String getNicComment() {
		return nicComment;
	}
	public void setNicComment(String nicComment) {
		this.nicComment = nicComment;
	}

	public String getCidComment() {
		return cidComment;
	}
	public void setCidComment(String cidComment) {
		this.cidComment = cidComment;
	}

	public String getTidComment() {
		return tidComment;
	}
	public void setTidComment(String tidComment) {
		this.tidComment = tidComment;
	}

	public String getSisComment() {
		return sisComment;
	}
	public void setSisComment(String sisComment) {
		this.sisComment = sisComment;
	}

	public String getImiComment() {
		return imiComment;
	}
	public void setImiComment(String imiComment) {
		this.imiComment = imiComment;
	}

	public String getCheckingOfficerComment() {
		return checkingOfficerComment;
	}
	public void setCheckingOfficerComment(String checkingOfficerComment) {
		this.checkingOfficerComment = checkingOfficerComment;
	}

	public String getOicComment() {
		return oicComment;
	}
	public void setOicComment(String oicComment) {
		this.oicComment = oicComment;
	}

	public String getAspComment() {
		return aspComment;
	}
	public void setAspComment(String aspComment) {
		this.aspComment = aspComment;
	}

	public List<String> getHistoryAdverseRecords() {
		return historyAdverseRecords;
	}
	public void setHistoryAdverseRecords(List<String> historyAdverseRecords) {
		this.historyAdverseRecords = historyAdverseRecords;
	}

	public String getCidStatusText() {
		return cidStatusText;
	}
	public void setCidStatusText(String cidStatusText) {
		this.cidStatusText = cidStatusText;
	}

	public String getTidStatusText() {
		return tidStatusText;
	}
	public void setTidStatusText(String tidStatusText) {
		this.tidStatusText = tidStatusText;
	}

	public String getSisStatusText() {
		return sisStatusText;
	}
	public void setSisStatusText(String sisStatusText) {
		this.sisStatusText = sisStatusText;
	}

	public String getNicStatusText() {
		return nicStatusText;
	}
	public void setNicStatusText(String nicStatusText) {
		this.nicStatusText = nicStatusText;
	}

	public String getImiStatusText() {
		return imiStatusText;
	}
	public void setImiStatusText(String imiStatusText) {
		this.imiStatusText = imiStatusText;
	}
	public String getNicCommentDate() {
		return nicCommentDate;
	}
	public void setNicCommentDate(String nicCommentDate) {
		this.nicCommentDate = nicCommentDate;
	}

	public String getCidCommentDate() {
		return cidCommentDate;
	}
	public void setCidCommentDate(String cidCommentDate) {
		this.cidCommentDate = cidCommentDate;
	}

	public String getTidCommentDate() {
		return tidCommentDate;
	}
	public void setTidCommentDate(String tidCommentDate) {
		this.tidCommentDate = tidCommentDate;
	}
	public String getSisCommentDate() {
		return sisCommentDate;
	}
	public void setSisCommentDate(String sisCommentDate) {
		this.sisCommentDate = sisCommentDate;
	}

	public String getImiCommentDate() {
		return imiCommentDate;
	}
	public void setImiCommentDate(String imiCommentDate) {
		this.imiCommentDate = imiCommentDate;
	}

	public String getCheckingOfficerCommentDate() {
		return checkingOfficerCommentDate;
	}
	public void setCheckingOfficerCommentDate(String checkingOfficerCommentDate) {
		this.checkingOfficerCommentDate = checkingOfficerCommentDate;
	}

	public String getOicCommentDate() {
		return oicCommentDate;
	}
	public void setOicCommentDate(String oicCommentDate) {
		this.oicCommentDate = oicCommentDate;
	}

	public String getAspCommentDate() {
		return aspCommentDate;
	}
	public void setAspCommentDate(String aspCommentDate) {
		this.aspCommentDate = aspCommentDate;
	}

	public String getDhaComment() {
		return dhaComment;
	}
	public void setDhaComment(String dhaComment) {
		this.dhaComment = dhaComment;
	}

	public String getDigComment() {
		return digComment;
	}
	public void setDigComment(String digComment) {
		this.digComment = digComment;
	}

	public String getDhaCommentDate() {
		return dhaCommentDate;
	}
	public void setDhaCommentDate(String dhaCommentDate) {
		this.dhaCommentDate = dhaCommentDate;
	}

	public String getDigCommentDate() {
		return digCommentDate;
	}
	public void setDigCommentDate(String digCommentDate) {
		this.digCommentDate = digCommentDate;
	}

	public String getPolStatusText() {
		return polStatusText;
	}
	public void setPolStatusText(String polStatusText) {
		this.polStatusText = polStatusText;
	}
	public List<PoliceCommentTypeDisplayVO> getPoliceCommentTypeDisplayVOs() {
		return policeCommentTypeDisplayVOs;
	}
	public void setPoliceCommentTypeDisplayVOs(
			List<PoliceCommentTypeDisplayVO> policeCommentTypeDisplayVOs) {
		this.policeCommentTypeDisplayVOs = policeCommentTypeDisplayVOs;
	}






	

	
}
