package lk.icta.police.framework.database;

import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.utility.CommonUtil;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;


/**
 * The Class DatabaseManager.
 * This class is responsible for creating connection from data source look up.
 * Also for closing connection, result set etc.
 *
 * @author Udaya Nakandala
 */
public final class DatabaseManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class);
//    private static final String POL_DS = "java:jdbc/POLDB";// FOR SERVER DEPLOYMENT
    private static final String POL_DS = "java:jboss/datasources/POLDB";// FOR STAGING DEPLOYMENT
    private static DataSource polDataSource;
    private static final String MSG_STRING_DB_UNAVAILABLE = "DBUNAVALABLE";

    private static String mode = "";
    private static String driver;
    private static String urlPOL;
    private static String polDBUserName;
    private static String polDBPassword;
    private static final String UT_MODE = "UT";
//	private static final String LIVE_MODE = "LIVE";

    static {
        mode = CommonUtil.getValueFromFile("mode", "mode");
        LOGGER.info("DatabaseManager :: mode - " + mode);
        if (UT_MODE.equals(mode)) {
            driver = CommonUtil.getValueFromFile("mode", "driver");
            LOGGER.info("DatabaseManager :: driver - " + driver);
            urlPOL = CommonUtil.getValueFromFile("mode", "urlPOL");
            LOGGER.info("DatabaseManager :: urlPOL - " + urlPOL);
            polDBUserName = CommonUtil.getValueFromFile("mode", "polDBUserName");
            LOGGER.info("DatabaseManager :: polDBUserName - " + polDBUserName);
            polDBPassword = CommonUtil.getValueFromFile("mode", "polDBPassword");
            LOGGER.info("DatabaseManager :: polDBPassword - " + polDBPassword);
        } else {
            DatabaseManager.init();
        }
    }

    // Stops initialization of it's object
    private DatabaseManager() {

    }

    /**
     * inits the datasource look up.
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
     * @throws BusinessException the business exception
     */
    private static void prepareDataSource() throws BusinessException {

        try {
            if (!UT_MODE.equals(mode)) {
				InitialContext ic = new InitialContext();
				polDataSource = (DataSource) ic.lookup(DatabaseManager.POL_DS);

                // TODO: 1/24/2017 added by sajith for tomcat datasource running
//                Context ctx = new InitialContext();
//                polDataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/POLDB");

                if (polDataSource == null) {
                    throw new BusinessException("etfb.datasource.lookup.failed");
                }
            }
        } catch (NamingException ne) {
            throw new BusinessException(ne);
        }
    }

    /**
     * Gets the POL DB connection.
     *
     * @return the POL connection
     * @throws BusinessException the business exception
     */
    public static Connection getPOLDBConnection() throws BusinessException {
        Connection conn = null;
        if (UT_MODE.equals(mode)) {

            if (LOGGER.isDebugEnabled()) {
                //LOGGER.debug("STARTING getConnection for UT mode...");
            }

            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(urlPOL, polDBUserName, polDBPassword);
            } catch (SQLException sqlEx) {
                LOGGER.fatal("SQLException >> Get connection failed for UT: ",
                        sqlEx);
                throw new BusinessException(sqlEx);
            } catch (Exception e) {
                LOGGER.fatal("Exception >> Get connection failed for UT: ", e);
                throw new BusinessException(e);
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Successfully getConnection for UT mode");
            }

        } else {
            if (LOGGER.isDebugEnabled()) {
                //LOGGER.debug("STARTING getConnection for Live mode...");
            }

            if (DatabaseManager.polDataSource == null) {
                LOGGER.fatal("Datasource initailization exception occured");
                throw new BusinessException("etfb.datasource.lookup.failed");
            }
            try {
                conn = DatabaseManager.polDataSource.getConnection();
            } catch (SQLException sqlEx) {
                LOGGER.fatal("Datasource initailization exception occured",
                        sqlEx);
                throw new BusinessException(DatabaseManager.MSG_STRING_DB_UNAVAILABLE);
            }

            if (LOGGER.isDebugEnabled()) {
                //LOGGER.debug("Successfully getConnection for Live mode");
            }
        }

        return conn;
    }

    /**
     * This method will close the provided Connection.
     *
     * @param conn the conn
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
     * @param conn the conn
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
     * @param statement the statement
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
     * @param pStatement the statement
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
     * @param cStatement the c statement
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
     * @param resultSet the result set
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
