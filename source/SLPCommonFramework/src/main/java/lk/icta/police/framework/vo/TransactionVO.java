package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TransactionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long transactionId; 			//BIGINT
	private String transactionReferenceNo; 	//VARCHAR(45)
	private String chequeNo;
	private String accountNo;
	private String accountHolderName;
	private String bookReceiptNo;
	private String description; 			//VARCHAR(4000)
	private BigDecimal applicationFee; 		//DECIMAL(18,2)
	private BigDecimal postageFee; 	//DECIMAL(18,2)
	private BigDecimal serviceFee; 		//DECIMAL(18,2)
	private BigDecimal convenienceFee; 		//DECIMAL(18,2)
	private BigDecimal totalFee; 			//DECIMAL(18,2)
	private Date paymentDate; 				//DATETIME
	private String paymentStatus; 			//VARCHAR(4)
	private String paymentGatewayName; 		//VARCHAR(45)
	private Date paymentInitiatedTime; 		//DATETIME
	private Date paymentConfirmedTime; 		//DATETIME
	private String paymentType; 			//VARCHAR(4)
	private String paymentMode; 			//VARCHAR(4)
	private Date createdDate; 				//DATETIME
	private int versionId; 					//INT
	private long applicationId; 			//BIGINT
	private String userFullName;
	private String userEmail;
	private String authProvider;
	
	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("transactionId", transactionId);
		map.put("transactionReferenceNo", transactionReferenceNo);
		map.put("description", description);
		map.put("applicationFee", applicationFee);
		map.put("convenienceFee", convenienceFee);
		map.put("totalFee", totalFee);
		map.put("paymentDate", paymentDate);
		map.put("paymentStatus", paymentStatus);
		map.put("paymentGatewayName", paymentGatewayName);
		map.put("paymentInitiatedTime", paymentInitiatedTime);
		map.put("paymentConfirmedTime", paymentConfirmedTime);
		map.put("paymentType", paymentType);
		map.put("paymentMode", paymentMode);
		map.put("createdDate", createdDate);
		map.put("versionId", versionId);
		map.put("applicationId", applicationId);
		return map;
	}
	
	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionReferenceNo() {
		return transactionReferenceNo;
	}

	public void setTransactionReferenceNo(String transactionReferenceNo) {
		this.transactionReferenceNo = transactionReferenceNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(BigDecimal applicationFee) {
		this.applicationFee = applicationFee;
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentGatewayName() {
		return paymentGatewayName;
	}

	public void setPaymentGatewayName(String paymentGatewayName) {
		this.paymentGatewayName = paymentGatewayName;
	}

	public Date getPaymentInitiatedTime() {
		return paymentInitiatedTime;
	}

	public void setPaymentInitiatedTime(Date paymentInitiatedTime) {
		this.paymentInitiatedTime = paymentInitiatedTime;
	}

	public Date getPaymentConfirmedTime() {
		return paymentConfirmedTime;
	}

	public void setPaymentConfirmedTime(Date paymentConfirmedTime) {
		this.paymentConfirmedTime = paymentConfirmedTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBookReceiptNo() {
		return bookReceiptNo;
	}

	public void setBookReceiptNo(String bookReceiptNo) {
		this.bookReceiptNo = bookReceiptNo;
	}

	public BigDecimal getPostageFee() {
		return postageFee;
	}

	public void setPostageFee(BigDecimal postageFee) {
		this.postageFee = postageFee;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	
}
