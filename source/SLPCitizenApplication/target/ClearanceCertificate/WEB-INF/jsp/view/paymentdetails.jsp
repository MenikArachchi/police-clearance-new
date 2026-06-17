<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:i18n name="lk.icta.resources.global">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><s:text name="Payment Confirmation"></s:text></title>

</head>


<body id="bd" >

	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" /> 
			
		<div id="es-content"> 
			
			<c:set var="pageTitle"><s:text name="Payment Confirmation"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
			<div class="alert alert-error" id="man_errorDiv" style="display:none;"></div>
		
			<s:form id="payment_confirmation_form_id" role="form" cssClass="form-horizontal"  theme="simple"> 
			
				<div class="middle_content">
				
					<span style="float:left;margin-right:10px;"><s:text name="global.coo.labl.detail.regarding.payment.one"/></span>
					<br><br />
					
					<div class="col-lg-6">
						<div class="form-group">
							<div class="col-sm-5" >
								<s:label cssClass="control-label bold-label" key="Transaction Reference No"></s:label>
		   					</div>
		   					<div class="col-sm-4">
		   						<div class="input_div"><s:property value="transactionReferenceNoPay"/></div>
		   					</div>
		   				</div>
					
						<div class="form-group">
							<div class="col-sm-5" >
								<s:label cssClass="control-label bold-label" key="Application Fee (Rs.)"></s:label>
		   					</div>
		   					<div class="col-sm-4">
		   						<div class="input_div" align="right"><s:property value="applicationfeePay"/></div>
		   					</div>
		   				</div>
					
						<div class="form-group">
							<div class="col-sm-5" >
								<s:label cssClass="control-label bold-label" key="Fee for Additional Copies (Rs.)"></s:label>
		   					</div>
		   					<div class="col-sm-4">
		   						<div class="input_div" align="right"><s:property value="feeForCopiespay"/></div>
		   					</div>
		   				</div>
					
						<div class="form-group">
							<div class="col-sm-5" >
								<s:label cssClass="control-label bold-label" key="Delivery Cost (Rs.)"></s:label>
		   					</div>
		   					<div class="col-sm-4">
		   						<div class="input_div" align="right"><s:property value="deliveryCostPay"/></div>
		   					</div>
		   				</div>
				
						<div class="form-group">
							<div class="col-sm-5" >
								<s:label cssClass="control-label bold-label" key="Total Fee (Rs.)"></s:label>
		   					</div>
		   					<div class="col-sm-4">
		   						<div class="input_div" align="right"><s:property value="totalFeePay"/></div>
		   					</div>
		   				</div>
					</div>	
					
					<div style="clear:both;"></div>	
					<br>
					<span style="float:left;margin-right:10px;"><s:text name="global.coo.labl.detail.regarding.payment.two"/></span>
					
					<br>
					<div class="col-sm-12">
						<div style="text-align:right;">
							<s:submit id="pay_id" key="Pay" action="constructConfirmPaymentPage" cssClass="btn btn-primary es-buttton" ></s:submit>
							<%-- <button type="button" id="btn_cancel_id" onclick="goToHome()" class="btn btn-primary es-buttton"><s:label key="Cancel"></s:label></button> --%>
							
						</div>
					</div>
				</div>

				
				
<script language="javascript" type="text/javascript">
$(document).ready(function() {
	
	$("#messagesDiv").fadeIn(700).delay(7000).fadeOut(5000);	
	
});

function goToHome(){
	var con=confirm('<s:text name="doc.global.confirm.msg.leave.page" />');
	if(con){
		window.location='home.action';
	}
}

</script>
				
				
</s:form>
<div style="clear: both;"></div>
						
			<!-- social icons -->
<div style="clear: both;"></div>
<jsp:include page="../common/socialshareicons.jsp" />
			
</div>	
<jsp:include page="../common/footer.jsp" />
</div>

</body>
</html>
</s:i18n>