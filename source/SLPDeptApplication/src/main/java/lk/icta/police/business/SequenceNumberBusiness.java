package lk.icta.police.business;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.SequenceNumberDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.SequenceNumberMasterVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author nsenevirat001
 *
 */
public class SequenceNumberBusiness {
	private static final Logger LOGGER = Logger.getLogger(SequenceNumberBusiness.class);
	private static SequenceNumberBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static SequenceNumberBusiness getInstance() {
		synchronized (SequenceNumberBusiness.class) {
			if (instance == null) {
				instance = new SequenceNumberBusiness();
			}
			return instance;
		}
	}
	

	public String getNextReferenceNumberByNumberType(int sequenceNumberId) throws Exception {
		// TODO Auto-generated method stub
		SequenceNumberMasterVO nextSeqNumber=getNextSequenceNumberFromDB(sequenceNumberId);  
		
		String paddedNo = StringUtils.leftPad(Long.toString(nextSeqNumber.getCurrentSequenceNumber()), nextSeqNumber.getNumberLength(), "0");
		
		String referenceNoPrefix=nextSeqNumber.getPrefixValue();
		
		if(StringUtils.isNotEmpty(referenceNoPrefix)){
			if(referenceNoPrefix.contains(PoliceConstant.YEAR)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				referenceNoPrefix=referenceNoPrefix.replace(PoliceConstant.YEAR, sdf.format(Calendar.getInstance().getTime()));
			}
			if(referenceNoPrefix.contains(PoliceConstant.MONTH)){
				SimpleDateFormat sdf = new SimpleDateFormat("MMM");
				referenceNoPrefix=referenceNoPrefix.replace(PoliceConstant.MONTH, sdf.format(Calendar.getInstance().getTime()));
			}
			if(referenceNoPrefix.contains(PoliceConstant.DATE)){
				SimpleDateFormat sdf = new SimpleDateFormat("EEE");
				referenceNoPrefix=referenceNoPrefix.replace(PoliceConstant.DATE, sdf.format(Calendar.getInstance().getTime()));
			}
		}
		
		if(sequenceNumberId==PoliceEnumConstant.SequenceNumbers.CERTIFICATE_CERIAL_NUMBER.getCode()){
			return "C" + paddedNo + referenceNoPrefix;
		}		
		if(sequenceNumberId==PoliceEnumConstant.SequenceNumbers.LETTER_CERIAL_NUMBER.getCode()){
			return "L" + paddedNo + referenceNoPrefix;
		}
		
		return referenceNoPrefix + paddedNo;
	}
	
	public String getNextReferenceNumberForApplicationReference(int sequenceNumberId, String deliveryType) throws Exception {

		SequenceNumberMasterVO nextSeqNumber=getNextSequenceNumberFromDB(sequenceNumberId);  
		
		String paddedNo = StringUtils.leftPad(Long.toString(nextSeqNumber.getCurrentSequenceNumber()), nextSeqNumber.getNumberLength(), "0");
		
		String referenceNoPrefix=nextSeqNumber.getPrefixValue();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		paddedNo = paddedNo + "/" + sdf.format(Calendar.getInstance().getTime());
		
		if(deliveryType.equals(PoliceEnumConstant.DeliveryType.FM.toString())){
			paddedNo = paddedNo + "/E";
		}
		if(deliveryType.equals(PoliceEnumConstant.DeliveryType.SB.toString())){
			paddedNo = paddedNo + "/B";
		}
		if(deliveryType.equals(PoliceEnumConstant.DeliveryType.NP.toString())){
			paddedNo = paddedNo + "/N";
		}
		
		//return referenceNoPrefix + paddedNo;
		return paddedNo;
	}

	private SequenceNumberMasterVO getNextSequenceNumberFromDB(int sequenceNumberId) throws BusinessException {
		Connection conn=null;
		SequenceNumberMasterVO seqNumber=null;
		try {			
			conn = DatabaseManager.getPOLDBConnection();
			conn.setAutoCommit(false);
			seqNumber = SequenceNumberDAO.getInstance().getNextReferenceSequenceNumber(conn, sequenceNumberId);			
		} catch (BusinessException be) {
			LOGGER.error("Throwing bussiness exception from business :: getNextSequenceNumberFromDB "	+ be.toString());
			throw be;
		} catch (Exception e) {
			LOGGER.error("Exception from business " + e.toString());
			throw new BusinessException(e);
		} finally {
			DatabaseManager.close(conn);
		}
		return seqNumber;
	}
	
	

	
	
	


	


	
}
