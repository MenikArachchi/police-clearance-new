package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DailyTransactionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String applicationReferenceNo;    //VARCHAR(45)
    private String transactionReferenceNo;    //VARCHAR(45)
    private Date paymentDate;                //DATETIME
    private BigDecimal convenienceFee;        //DECIMAL(18,2)
    private BigDecimal totalFee;            //DECIMAL(18,2)
    private String paymentStatus;            //VARCHAR(4)
    private BigDecimal sumconvenienceFee;        //DECIMAL(18,2)
    private BigDecimal sumtotalFee;            //DECIMAL(18,2)
    private String applicationType;    //VARCHAR(2)
    private String applicationStatus ;


    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicationReferenceNo", applicationReferenceNo);
        map.put("transactionReferenceNo", transactionReferenceNo);
        map.put("paymentDate", paymentDate);
        map.put("convenienceFee", convenienceFee);
        map.put("totalFee", totalFee);
        map.put("paymentStatus", paymentStatus);
        map.put("sumconvenienceFee", sumconvenienceFee);
        map.put("sumtotalFee", sumtotalFee);
        return map;
    }


    @Override
    public String toString() {
        return this.toBasicMap().toString();
    }

    public String getTransactionReferenceNo() {
        return transactionReferenceNo;
    }

    public void setTransactionReferenceNo(String transactionReferenceNo) {
        this.transactionReferenceNo = transactionReferenceNo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(BigDecimal convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getSumconvenienceFee() {
        return sumconvenienceFee;
    }

    public void setSumconvenienceFee(BigDecimal sumconvenienceFee) {
        this.sumconvenienceFee = sumconvenienceFee;
    }

    public BigDecimal getSumtotalFee() {
        return sumtotalFee;
    }

    public void setSumtotalFee(BigDecimal sumtotalFee) {
        this.sumtotalFee = sumtotalFee;
    }


    public String getApplicationReferenceNo() {
        return applicationReferenceNo;
    }


    public void setApplicationReferenceNo(String applicationReferenceNo) {
        this.applicationReferenceNo = applicationReferenceNo;
    }


    public String getApplicationType() {
        return applicationType;
    }


    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
