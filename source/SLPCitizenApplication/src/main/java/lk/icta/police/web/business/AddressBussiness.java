package lk.icta.police.web.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant.AddressType;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressVO;

import org.apache.log4j.Logger;

public class AddressBussiness {
    private static final Logger LOGGER = Logger.getLogger(AddressBussiness.class);
    private static AddressBussiness instance = null;

    /**
     * Singleton Implementation
     */
    public static AddressBussiness getInstance() {
        synchronized (AddressBussiness.class) {
            if (instance == null) {
                instance = new AddressBussiness();
            }
            return instance;
        }
    }

    public List<AddressVO> getAddressList(long applicationId) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return AddressDAO.getInstance().getAddressList(con, applicationId);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

    public List<AddressVO> getAddressListByTypeAndApplicationId(long applicationId, String addressType) {
        Connection con = null;
        try {
            con = DatabaseManager.getPOLDBConnection();
            con.setAutoCommit(false);
            return AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationId, addressType, con);
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            DatabaseManager.close(con);
        }
        return null;
    }

}
