package lk.icta.police.framework.dao;

import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.framework.vo.DocumentVO;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by snandasena001 on 2/21/2017.
 */
public class DocumentDAO implements Serializable {
    private static final long serialVersionUID = -5585604847513575770L;

    private static final Logger LOGGER = Logger.getLogger(DocumentDAO.class);

    private static class DocumentDbConstant {
        private static final String DOCUMENT = "application_additional_docs";
        private static final String DOC_NAME = "doc_name";
        private static final String FILE_PATH = "file_path";
        private static final String APPLICATION_ID = "application_id";
        private static final String REFERENCE_NO = "reference_no";
        private static final String CREATED_DATE = "created_date";
        private static final String CREATED_BY = "created_by";
        private static final String COMMENT = "comment";
        private static final String FILE_TYPE = "file_type";

    }

    private static final String ADD_DOCUMENT = "INSERT INTO application_additional_docs(doc_name, application_id, " +
            "reference_no, file_path, created_date, created_by, comment, file_type) VALUE (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_UPLOADED_DOCS_BY_APPLICATION_ID = "SELECT * " +
            " FROM application_additional_docs WHERE application_id = ? ORDER BY created_date DESC ";

    private static DocumentDAO instance = new DocumentDAO();

    public static DocumentDAO getInstance() {
        synchronized (DocumentDAO.class) {
            return instance;
        }
    }

    private DocumentDAO() {

    }

    public Long addAdditionalDocument(DocumentVO documentVO) {
        LOGGER.info("Entered addAdditionalDocument " + documentVO);
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
            connection = DatabaseManager.getPOLDBConnection();
            pstm = connection.prepareStatement(ADD_DOCUMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            Integer i = 0;
            pstm.setString(++i, documentVO.getDocumentName());
            pstm.setLong(++i, documentVO.getApplicationId());
            pstm.setString(++i, documentVO.getReferenceNo());
            pstm.setString(++i, documentVO.getFilePath());
            pstm.setTimestamp(++i, CommonUtil.getSqlTimeStampFromUtilDate(documentVO.getCreatedDate()));
            pstm.setString(++i, documentVO.getCreatedBy());
            pstm.setString(++i, documentVO.getComment());
            pstm.setString(++i, documentVO.getFileType());

            int result = pstm.executeUpdate();
            Long documentId = 0L;
            if (result > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    documentId = (long) rs.getInt(1);
                    LOGGER.info("Saved successfully");
                }
            }
            DatabaseManager.close(result);
            pstm.close();
            return documentId;

        } catch (Exception e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        } finally {
            DatabaseManager.close(pstm);
            DatabaseManager.close(connection);
        }
        return 0L;
    }

    public List<DocumentVO> getAllUploadedDocumentsByApplicationId(Long applicationId) {
        LOGGER.info("Entered getAllUploadedDocumentsByApplicationId(" + applicationId + ")");
        PreparedStatement pstm = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<DocumentVO> documentVOS = null;
        try {
            connection = DatabaseManager.getPOLDBConnection();
            pstm = connection.prepareStatement(GET_ALL_UPLOADED_DOCS_BY_APPLICATION_ID);
            Integer i = 0;
            pstm.setLong(++i, applicationId);

            resultSet = pstm.executeQuery();
            if (resultSet != null) {
                documentVOS = new ArrayList<DocumentVO>();
                while (resultSet.next()) {
                    DocumentVO documentVO = new DocumentVO();
                    String docName = resultSet.getString(DocumentDbConstant.DOC_NAME);
                    documentVO.setDocumentName(docName);
                    String filePath = resultSet.getString(DocumentDbConstant.FILE_PATH);
                    documentVO.setFilePath(filePath);
                    String createdBy = resultSet.getString(DocumentDbConstant.CREATED_BY);
                    documentVO.setCreatedBy(createdBy);
                    Date createdDate = resultSet.getTimestamp(DocumentDbConstant.CREATED_DATE);
                    documentVO.setCreatedDate(createdDate);
                    String comment = resultSet.getString(DocumentDbConstant.COMMENT);
                    documentVO.setComment(comment);
                    String docFileType = resultSet.getString(DocumentDbConstant.FILE_TYPE);
                    documentVO.setFileType(docFileType);
                    documentVOS.add(documentVO);
                }
            }
			resultSet.close();
            pstm.close();
        } catch (Exception e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(pstm);
            DatabaseManager.close(connection);
        }
        return documentVOS;
    }
}
