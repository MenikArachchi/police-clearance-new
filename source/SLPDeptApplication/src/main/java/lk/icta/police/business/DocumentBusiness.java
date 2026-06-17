package lk.icta.police.business;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.DocumentDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ChangeAuditVO;
import lk.icta.police.framework.vo.DocumentVO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by snandasena001 on 2/21/2017.
 */
public class DocumentBusiness implements Serializable {
    private static final long serialVersionUID = 9049254930756950015L;
    private static final Logger LOGGER = Logger.getLogger(DocumentBusiness.class);

    private static DocumentBusiness instance = new DocumentBusiness();

    public static DocumentBusiness getInstance() {
        synchronized (DocumentBusiness.class) {
            return instance;
        }
    }

    private DocumentBusiness() {
    }

    public Long addAdditionalDocument(DocumentVO documentVO, ChangeAuditVO changeAuditVO) {    	
    	LOGGER.info("Entered addAdditionalDocument(" + documentVO + ") "); 
        
        Long savedDocId = DocumentDAO.getInstance().addAdditionalDocument(documentVO);
        
        if (savedDocId != null && savedDocId > 0) {
        	this.addChangeAudit(changeAuditVO);
        	
        }
        
        return savedDocId;
    }

    public List<DocumentVO> getAllUploadedDocumentsByApplicationId(Long applicationId) {
        LOGGER.info("Entered getAllUploadedDocumentsByApplicationId(" + applicationId + ")");
        return DocumentDAO.getInstance().getAllUploadedDocumentsByApplicationId(applicationId);
    }

    public String addChangeAudit(ChangeAuditVO changeAuditVO) {
        boolean doClose = false;
        String status = PoliceConstant.ERROR;
        Connection con = null;
        try {
            if (con == null) {
            	doClose = true;
                con = DatabaseManager.getPOLDBConnection();
            }

            status = ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);

            if (doClose) {
                if (StringUtils.equals(PoliceConstant.SUCCESS, status)) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            return status;
        } catch (BusinessException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (doClose) {
                DatabaseManager.close(con);
            }

        }
        return status;
    }
    
}
