package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by snandasena001 on 2/21/2017.
 */
public class DocumentVO implements Serializable {

    private static final long serialVersionUID = 370509403354934452L;

    private Long id;
    private Long applicationId;
    private String referenceNo;
    private String documentName;
    private String filePath;
    private String createdBy;
    private Date createdDate;
    private String comment;
    private String fileType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "DocumentVO{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", referenceNo='" + referenceNo + '\'' +
                ", documentName='" + documentName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", comment='" + comment + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
