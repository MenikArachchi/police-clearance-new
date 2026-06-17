package lk.icta.police.external.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.CommentDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressChangeAuditVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CommentsVO;

import org.apache.log4j.Logger;

public class UpdatePoliceMessageBusiness {

	private static final Logger LOGGER = Logger.getLogger(UpdatePoliceMessageBusiness.class);
	private static UpdatePoliceMessageBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static UpdatePoliceMessageBusiness getInstance() {
		synchronized (UpdatePoliceMessageBusiness.class) {
			if (instance == null) {
				instance = new UpdatePoliceMessageBusiness();
			}
			return instance;
		}
	}
	
    private UpdatePoliceMessageBusiness() {
		
	}
    
    public ApplicationVO getApplicationByApplicationId(long applicationId){
		Connection con = null;
		ApplicationVO applicationVO = new ApplicationVO();
		List<AddressVO> addressList = new ArrayList<AddressVO>();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			applicationVO =  ApplicationDAO.getInstance().getApplicationById(applicationId, con);
			
			addressList = AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId, PoliceEnumConstant.AddressType.AC.toString(), con);
			applicationVO.setApplicantCertificateAddresses(addressList);
			
			return applicationVO;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return null;
	}
    
    public long saveComment(CommentsVO commentsVO){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			long commentId = CommentDAO.getInstance().addComments(con, commentsVO);
			return commentId;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			DatabaseManager.close(con);
		}
		return 0;
	}
    
    public boolean updateAddressVOForPoliceMessage(AddressVO addressVO, int userId, String username){
		Connection con = null;
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			System.out.println("addressVO.getFromMessage()---"+addressVO.getFromMessage());
			System.out.println("addressVO.getfrom()---"+addressVO.getFromSentBy());
			System.out.println("addressVO.getFromSentDate()---"+addressVO.getFromSentDate());
			System.out.println("addressVO.getFromReceiveBy()---"+addressVO.getFromReceiveBy());
			System.out.println("addressVO.getResponseMessage()---"+addressVO.getResponseMessage());
			System.out.println("addressVO.getResponseSentBy()---"+addressVO.getResponseSentBy());
			System.out.println("addressVO.getResponseSentDate()---"+addressVO.getResponseSentDate());
			System.out.println("addressVO.getResponseSentTo()---"+addressVO.getResponseSentTo());
			System.out.println("addressVO.getPoliceStatus()---"+addressVO.getPoliceStatus());
			
			boolean result = AddressDAO.getInstance().updateAddressForPoliceMessage(con, addressVO.getAddressId(), addressVO);
			
			AddressChangeAuditVO addressChangeAuditVO = new AddressChangeAuditVO();
			addressChangeAuditVO.setAddressId(addressVO.getAddressId());
			addressChangeAuditVO.setUpdatedUserId(userId);
			addressChangeAuditVO.setUpdatedUserName(username);
			addressChangeAuditVO.setUpdatedUserDateTime(new Date());
			addressChangeAuditVO.setComment("");
			
			if(result){
				con.commit();
			}else{
				con.rollback();
			}
			
			return result;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			DatabaseManager.close(con);
		}
		return false;
	}
    
    public AddressVO getAddressVOByApplicationIdAndPoliceareaId(long applicationId, long policeAreaId){
		Connection con = null;
		AddressVO addressVO = new AddressVO();
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			addressVO = AddressDAO.getInstance().getAddressListByApplicationIdAndPoliceareaId(applicationId, policeAreaId, con);
			
			return addressVO;
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return null;
	}
    
    public List<AddressVO> getAddressListForUpdate(long applicationId){
    	ApplicationVO applicationVO = UpdatePoliceMessageBusiness.getInstance().getApplicationByApplicationId(applicationId);
		
		List<Long> newAddressIdList = new ArrayList<Long>();
		if(applicationVO != null && applicationVO.getApplicantCertificateAddresses().size()>0){
			for (AddressVO addressVO : applicationVO.getApplicantCertificateAddresses()) {
				long policeAreaId = addressVO.getPoliceAreaId();
				newAddressIdList.add(policeAreaId);
			}
		}
		
		HashSet<Long> addressIdHashSet = new HashSet<Long>();
		addressIdHashSet.addAll(newAddressIdList);
		newAddressIdList.clear();
		newAddressIdList.addAll(addressIdHashSet);
		
		List<AddressVO> addressVoList = new ArrayList<AddressVO>();
		for (Long policeAreaId : newAddressIdList) {
			AddressVO newAddressVO = UpdatePoliceMessageBusiness.getInstance().getAddressVOByApplicationIdAndPoliceareaId(applicationId, policeAreaId);
			addressVoList.add(newAddressVO);
		}
		
		return addressVoList;
	}
	
}
