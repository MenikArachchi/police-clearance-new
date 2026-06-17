package lk.icta.police.framework.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;

import org.apache.log4j.Logger;

public class MockDAOUtil {
	
	private static final Logger LOGGER = Logger.getLogger(MockDAOUtil.class);
	
	private static MockDAOUtil instance;
	
	public static MockDAOUtil getInstance() {
		synchronized(MockDAOUtil.class) {
			if (instance == null) {
				instance = new MockDAOUtil();
			}
			return instance;
		}		
	}
	
	// No Initialization from outside: Singleton pattern	
	private MockDAOUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String truncateTestDatabasetables(List<String> tablesToTruncate,Connection conn) throws BusinessException, SQLException {
		String retValue = null;
		PreparedStatement pstmFC = null;		
		PreparedStatement pstm = null;		
		try {
			//set foriegn key check to 0
			pstmFC=conn.prepareStatement(MockDBConstants.TEST_QUERY.SET_FORIEGN_KEY_CHECKS);
			pstmFC.setInt(1, 0);
			pstmFC.executeUpdate();	
			conn.commit();
			
			for (String tableName : tablesToTruncate) {
				LOGGER.info("TRUNCATING TABLE :- " +  tableName);
				pstm = conn.prepareStatement(MockDBConstants.TEST_QUERY.TRUNCATE_TABLE + tableName + ";");
				pstm.execute();
				conn.commit();
			}			
			
			
			//set foriegn key check to 1
			pstmFC=conn.prepareStatement(MockDBConstants.TEST_QUERY.SET_FORIEGN_KEY_CHECKS);
			pstmFC.setInt(1, 1);
			pstmFC.executeUpdate();
			conn.commit();
			
			retValue = "SUCCESS";
		} catch (SQLException e) {
			retValue = "ERROR";
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e);
		} catch (Exception e) {
			retValue = "ERROR";
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e);	
		} finally {
			DatabaseManager.close(pstmFC);
			DatabaseManager.close(pstm);			
		}		
		return retValue;
	}

	

}
