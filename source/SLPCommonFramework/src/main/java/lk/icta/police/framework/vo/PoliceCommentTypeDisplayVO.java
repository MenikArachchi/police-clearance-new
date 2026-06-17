package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.AddressDAO;

import org.apache.commons.lang3.StringUtils;

public class PoliceCommentTypeDisplayVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//PHQ(1), POL(2), CID(3), TID(4), SIS(5), NIC(6), IMI(7);
	
	private String policeComment;
	private String phqComment;
	private String policeCommentDate;
	private String phqCommentDate;
	private String polStatusText;	
	private long addressId;
	private String address;
	private String policeArea;
	

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("policeComment", policeComment);
		map.put("phqComment", phqComment);
		map.put("policeCommentDate", policeCommentDate);
		map.put("phqCommentDate", phqCommentDate);
		map.put("addressId", addressId);
		map.put("address", address);
		map.put("policeArea", policeArea);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}
	
	public PoliceCommentTypeDisplayVO(CommentsVO commentVo, Connection conn) {
		super();
		try {
			//System.out.println("commentVo :" + commentVo);
			if(!(commentVo.getAddressId()<=0)){
				AddressVO addressVO=AddressDAO.getInstance().getAddressById(commentVo.getAddressId(), conn);
				//System.out.println("addressVO :" + addressVO);
				if(!(addressVO==null)){
					PoliceEnumConstant.ApprovalStatus polApproval=PoliceEnumConstant.ApprovalStatus.fromCode(addressVO.getPoliceStatus());
					if(!(polApproval==null)){
						this.polStatusText=polApproval.getDisplayName();
					}
					this.addressId=addressVO.getAddressId();
					this.address=addressVO.getAddress();
					this.policeArea=addressVO.getPoliceArea();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.updatePoliceCommentTypeDisplayVO(commentVo);
	}
	
	public void updatePoliceCommentTypeDisplayVO(CommentsVO commentVo){
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		this.policeComment="No comment is available!";
		this.phqComment="No comment is available!";
		
		if(!(commentVo==null)){
			 if(StringUtils.equals(commentVo.getCommentType(), PoliceEnumConstant.CommentType.POL.toString())){
				policeComment=commentVo.getComment();
				if(!(commentVo.getCreatedDateTime()==null)){
					this.policeCommentDate=format.format(commentVo.getCreatedDateTime());
				}
			 }else if(StringUtils.equals(commentVo.getCommentType(), PoliceEnumConstant.CommentType.PHQ.toString())){
				phqComment=commentVo.getComment();
				if(!(commentVo.getCreatedDateTime()==null)){
					this.phqCommentDate=format.format(commentVo.getCreatedDateTime());
				}
			 }
		}
		
		if(StringUtils.isEmpty(this.policeComment)){
			this.policeComment="No comment is available!";
		}
		if(StringUtils.isEmpty(this.phqComment)){
			this.phqComment="No comment is available!";
		}
		
		if(StringUtils.isEmpty(this.policeCommentDate)){
			this.policeCommentDate="";
		}
		
		if(StringUtils.isEmpty(this.phqCommentDate)){
			this.phqCommentDate="";
		}
		
		if(StringUtils.isEmpty(this.polStatusText)){
			this.polStatusText="";
		}
		if(StringUtils.isEmpty(this.address)){
			this.address="";
		}
		
		if(StringUtils.isEmpty(this.policeArea)){
			this.policeArea="";
		}
		
	} 
	
	public PoliceCommentTypeDisplayVO() {
		super();
		
	}

	public String getPoliceComment() {
		return policeComment;
	}
	public void setPoliceComment(String policeComment) {
		this.policeComment = policeComment;
	}

	public String getPhqComment() {
		return phqComment;
	}
	public void setPhqComment(String phqComment) {
		this.phqComment = phqComment;
	}

	public String getPoliceCommentDate() {
		return policeCommentDate;
	}
	public void setPoliceCommentDate(String policeCommentDate) {
		this.policeCommentDate = policeCommentDate;
	}

	public String getPhqCommentDate() {
		return phqCommentDate;
	}
	public void setPhqCommentDate(String phqCommentDate) {
		this.phqCommentDate = phqCommentDate;
	}

	public String getPolStatusText() {
		return polStatusText;
	}
	public void setPolStatusText(String polStatusText) {
		this.polStatusText = polStatusText;
	}

	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getPoliceArea() {
		return policeArea;
	}
	public void setPoliceArea(String policeArea) {
		this.policeArea = policeArea;
	}



	
	

}
