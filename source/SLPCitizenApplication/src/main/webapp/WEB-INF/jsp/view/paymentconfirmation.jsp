<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><s:text name="Payment Confirmation"></s:text></title>

</head>

<body class="oneColElsCtrHdr" onload="noBack();" onpageshow="if (event.persisted) noBack();">
<!--main container start -->
	<div id="es-container" class="container">
		
		<jsp:include page="../common/header.jsp" />
		
			<!-- Starting the page Title -->
	
		<div id="es-content">
		
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle"><s:text name="Payment Confirmation"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
			    <jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
      		<div class="middle_content">
      			<form action='<s:property value="invocationURL"/>' method="post">
					<div class="col-lg-9">	
							<p>Transaction Reference Number : ${referenceNumber}</p>
							
							<table class="table table-striped">
								<tr>
									<td>Application Fee</td><td>Rs.${applicationFee}</td>
								</tr>
<!-- 								<tr> -->
<%-- 									<td>Service Fee</td><td>Rs.${serviceFee}</td> --%>
<!-- 								</tr> -->
								<tr>
									<td>Postage</td><td>Rs.${postageFee}</td>
								</tr>
							</table>
							
							<br/>
							
							<p>*Note:The payment is non-refundable. A convenience fee will be charged based on your payment method.</p>
							
							<input type="hidden" value='<s:property value="enPaymentRequest"/>'	name="clientPaymentRequest"> 				
							<button type="submit" class="submit es-submit">
				  				&nbsp;&nbsp;<s:text name="global.button.text.confirm"></s:text>&nbsp;&nbsp;
				  			</button>
					
					</div>
				</form>
			</div>
			
			<div style="clear: both;"></div>
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />
			
		</div>	
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
<script language="javascript" type="text/javascript">
		window.history.forward();
		function noBack() { 
			window.history.forward(); 
		}	
		
		function goToHome(){
			var con=confirm('<s:text name="doc.global.confirm.msg.leave.page" />');
			if(con){
				window.location='home.action';
			}
		}
</script>
</html>