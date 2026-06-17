package lk.icta.commonuser.framework.database;

import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * The Class DatabaseManager. This class is responsible for creating connection
 * from data source look up. Also for closing connection, result set etc.
 */
public final class DatabaseManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(DatabaseManager.class);

	/** The Constant COMMUSRDB_DS. */
//	private static final String COMMUSRDB_DS = "java:jdbc/COMMUSRDB_SLP";
	private static final String COMMUSRDB_DS = "java:jboss/datasources/COMMUSRDB_SLP";

	/** The comm usr data source. */
	private static DataSource commUsrDataSource;

	/** The Constant UT_MODE. */
	private static final String UT_MODE = "UT";

	/** The Constant LIVE_MODE. */
	private static final String LIVE_MODE = "LIVE";

	/** The mode. */
	private static String mode;

	/** The common user url. */
	private static String commonUserURL;

	/** The common user driver class. */
	private static String commonUserDriverClass;

	/** The common user user name. */
	private static String commonUserUserName;

	/** The common user password. */
	private static String commonUserPassword;

	// Stops initialization of it's object
	/**
	 * Instantiates a new database manager.
	 */
	private DatabaseManager() {

	}

	static {
		mode = CommonUtil.getValueFromFile("mode", "mode");
		System.out.println("MODEcP :" +  mode);
		if (LIVE_MODE.equals(mode)) {
			DatabaseManager.init();
		} else if (UT_MODE.equals(mode)) {
			commonUserURL = CommonUtil.getValueFromFile("mode", "commonUserURL");
			commonUserDriverClass = CommonUtil.getValueFromFile("mode",	"commonUserDriverClass");
			commonUserUserName = CommonUtil.getValueFromFile("mode","commonUserUserName");
			commonUserPassword = CommonUtil.getValueFromFile("mode","commonUserPassword");
		}
	}

	/**
	 * initializes the datasource look up.
	 */
	private static void init() {
		try {
			DatabaseManager.prepareDataSource();
		} catch (BusinessException ex) {
			LOGGER.fatal("Datasource initailization exception occured", ex);
		}
	}

	/**
	 * Prepare data source.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	private static void prepareDataSource() throws BusinessException {

		try {
			InitialContext ic = new InitialContext();
			commUsrDataSource = (DataSource) ic
					.lookup(DatabaseManager.COMMUSRDB_DS);
            // TODO: 1/24/2017 added by sajith for tomcat datasource running
//            Context ctx = new InitialContext();
//            commUsrDataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/COMMUSRDB_SLP");
			if (commUsrDataSource == null) {
				LOGGER.fatal("Datasource is null");
				throw new BusinessException("commusr.datasource.lookup.failed");
			}

		} catch (NamingException ne) {
			LOGGER.fatal("NamingException in prepareDataSource: ", ne);
			throw new BusinessException(ne);
		}
	}

	/**
	 * Gets the COMMUSR DB connection.
	 * 
	 * @return the COMMUSR connection
	 * @throws BusinessException
	 *             the business exception
	 */
	public static Connection getCOMMUSRConnection() throws BusinessException {

		Connection conn = null;
		if (LIVE_MODE.equals(mode)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("STARTING getCOMMUSRConnection for Live mode...");
			}
			if (DatabaseManager.commUsrDataSource == null) {
				LOGGER.fatal("Datasource initailization exception occured");
				throw new BusinessException("commusr.datasource.lookup.failed");
			}
			try {
				conn = DatabaseManager.commUsrDataSource.getConnection();
			} catch (SQLException sqlEx) {
				LOGGER.fatal("Datasource initailization exception occured",
						sqlEx);
				throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Successfully getCOMMUSRConnection for Live mode");
			}
		} else {
			if (UT_MODE.equals(mode)) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("STARTING getCOMMUSRConnection for UT mode...");
				}
				try {
					Class.forName(commonUserDriverClass).newInstance();
					conn = DriverManager.getConnection(commonUserURL,commonUserUserName, commonUserPassword);
				} catch (SQLException sqlEx) {
					LOGGER.fatal(
							"SQLException >> Get COMMUSRConnection failed for UT: ",
							sqlEx);
					throw new BusinessException(sqlEx);
				} catch (Exception e) {
					LOGGER.fatal(
							"Exception >> Get COMMUSRConnection failed for UT: ",
							e);
					throw new BusinessException(e);
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Successfully getCOMMUSRConnection for UT mode");
				}
			}
		}

		return conn;
	}

	/**
	 * This method will close the provided Connection.
	 * 
	 * @param conn
	 *            the conn
	 */
	private static void closeConnection(Connection conn) {
		try {
			if (null != conn && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method will rollback the provided Connection.
	 * 
	 * @param conn
	 *            the conn
	 */
	public static void rollbackConnection(Connection conn) {
		try {
			if (null != conn && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method will close the provided Statement.
	 * 
	 * @param statement
	 *            the statement
	 */
	private static void closeStatement(Statement statement) {
		try {
			if (null != statement) {
				statement.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method will close the provided PreparedStatement.
	 * 
	 * @param pStatement
	 *            the statement
	 */
	private static void closePreparedStatement(PreparedStatement pStatement) {
		try {
			if (null != pStatement) {
				pStatement.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method will close the provided CallableStatement.
	 * 
	 * @param cStatement
	 *            the c statement
	 */
	private static void closeCallableStatement(CallableStatement cStatement) {
		try {
			if (null != cStatement) {
				cStatement.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method will close the provided ResultSet.
	 * 
	 * @param resultSet
	 *            the result set
	 */
	private static void closeResultSet(ResultSet resultSet) {
		try {
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This is a generic method to close any database component.
	 * 
	 * @param obj
	 *            the obj
	 */
	public static void close(Object obj) {
		if (obj == null) {
			return;
		}
		try {
			if (obj instanceof Connection) {
				closeConnection((Connection) obj);
			} else if (obj instanceof Statement) {
				closeStatement((Statement) obj);
			} else if (obj instanceof ResultSet) {
				closeResultSet((ResultSet) obj);
			} else if (obj instanceof PreparedStatement) {
				closePreparedStatement((PreparedStatement) obj);
			} else if (obj instanceof CallableStatement) {
				closeCallableStatement((CallableStatement) obj);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
