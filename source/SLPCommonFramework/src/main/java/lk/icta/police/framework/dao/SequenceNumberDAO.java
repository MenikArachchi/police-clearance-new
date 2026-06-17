package lk.icta.police.framework.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.vo.SequenceNumberMasterVO;

import org.apache.log4j.Logger;

/**
 * @author Nadeeshani Senevirathna
 *
 */
public class SequenceNumberDAO {

	private static SequenceNumberDAO instance = null;
	private static final Logger LOGGER = Logger.getLogger(SequenceNumberDAO.class);
	
	public static SequenceNumberDAO getInstance() {
		synchronized(SequenceNumberDAO.class) {
			if (instance == null) {
				instance = new SequenceNumberDAO();
			}
			return instance;
		}
	}
	
	private SequenceNumberDAO(){
		
	}
	
	
	
	public SequenceNumberMasterVO getNextReferenceSequenceNumber(Connection conn, int id) throws Exception {
		PreparedStatement pstm = null;
		PreparedStatement pstmUpdate = null;
		ResultSet rst = null;
		SequenceNumberMasterVO sequenceNumberMasterVO=null;
		try {
			//update the current sequence number
			pstmUpdate = conn.prepareStatement(DBConstant.QUERY.UPDATE_CURRENT_SEQUENCE_NUMBER);	
			pstmUpdate.setInt(1, id);
			pstmUpdate.executeUpdate();	
			
			//get the next sequence number
			pstm=conn.prepareStatement(DBConstant.QUERY.SELECT_NEXT_SEQUENCE_NUMBER);	
			pstm.setInt(1, id);
			rst = pstm.executeQuery();	
			
			if (!rst.next()) {
			    LOGGER.info("No records found for sequence no with id :" + id);
			} else {
			    do {
			    	sequenceNumberMasterVO=new SequenceNumberMasterVO();			    	
			    	sequenceNumberMasterVO.setCurrentSequenceNumber(rst.getInt(DBConstant.SEQUENCE_NUMBER_MASTER_COL.CURRENT_SEQUENCE_NUMBER));
			    	sequenceNumberMasterVO.setDescription(rst.getString(DBConstant.SEQUENCE_NUMBER_MASTER_COL.DESCRIPTION));
			    	sequenceNumberMasterVO.setNumberLength(rst.getInt(DBConstant.SEQUENCE_NUMBER_MASTER_COL.NUMBER_LENGTH));
			    	sequenceNumberMasterVO.setPrefixValue(rst.getString(DBConstant.SEQUENCE_NUMBER_MASTER_COL.PREFIX_VALUE));
			    	sequenceNumberMasterVO.setSequenceNumberMasterId(rst.getInt(DBConstant.SEQUENCE_NUMBER_MASTER_COL.SEQUENCE_NUMBER_MASTER_ID));
			    } while (rst.next());
			}			
			conn.commit();		
			rst.close();
			pstm.close();		
			pstmUpdate.close();					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseManager.close(rst);
			DatabaseManager.close(pstm);
			DatabaseManager.close(pstmUpdate);
		} 
		return sequenceNumberMasterVO;
	}
	
	
	
	
		
}
