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

<title><s:text name="Police Form"></s:text></title>

</head>

<body id="bd" >

	<div id="es-container" class="container" >
		
	<jsp:include page="../common/header.jsp" /> 
			
		<div id="es-content"> 
			
			<c:set var="pageTitle"><s:text name="Search Edit Application"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
			<div id="messagesDiv" style="margin:2px 0px;">
				<s:if test="customErrorMessage != null">
					<div class="alert alert-error">
						<s:property value="customErrorMessage" escapeHtml="false" />
					</div>
				</s:if>
			</div>
			
			
			
			<div class="middle_content">
				<div>
					<s:form cssClass="form-horizontal" id="myForm"  theme="simple" action="searchEditApplication.action" onsubmit="return validateForm()"> 
							<div class="col-lg-8">
								<div class="form-group">
									<div class="col-sm-2" >
										<strong><s:label cssClass="control-label bold-label">Reference Number:</s:label></strong>
				   					</div>
				   					<div class="col-sm-6">
				   						<input type="text"  name="referenceNo" id="referenceNo_id" class="form-control">
				   					</div>
			   					</div>
			   				</div>
			   				<div style="clear: both;"></div>
							<div class="col-lg-8">				
								<div class="form-group">
									<div class="col-sm-8" >
										<div style="text-align:right;">
											<input type="submit" value="Search" class="btn btn-primary es-buttton" id="searchReviewApplication" />
											<input type="button" value="Clear" class="btn btn-primary es-buttton" id="clearReviewApplication" onclick="return clearReferenceNo()" />
										</div>
				   					</div>
			   					</div>
							</div>
			   			</s:form>
			   		</div>
			   </div>
			   
			   <div style="display: none;">
				   	<form action="loadEditApplicationAfterVerification.action" method="post" id="applicationCompleteModificationForm">	
						<s:hidden name="applicationId" id="modifyApplicationCompleteFormAppId" />
					</form>
			   </div>
			   				
							
		</div>	
		<jsp:include page="../common/footer.jsp" />
	</div>
	<script language="javascript" type="text/javascript">
		$(document).ready(function() {
			$("#messagesDiv").fadeIn(700).delay(7000).fadeOut(5000);	
		});
		
		function editRow(applicationId){
			applicationId=parseInt(applicationId);
			if(applicationId>0){
				var con=confirm('Are you sure you want to modify the application?');
				if(con){
					$('#modifyApplicationCompleteFormAppId').val(applicationId);
					$('#applicationCompleteModificationForm').submit();
				}
			}
		}
		
		function validateForm(){
			var referenceNumber=$.trim($('#referenceNo_id').val());
			if(referenceNumber==null || referenceNumber==''){
				alert('Please enter an application reference number to proceed');
				return false;
			}
			
			$.get('searchEditApplication.action',{
				referenceNo:referenceNumber,
			},function(data){
			  	var applicationId = data.applicationId;
			  	var message = data.message;
			  	if(applicationId>0){
			  		editRow(applicationId);
				} else {
					alert(message);
				}  	
			});
			
			return false;
		}
		
		function clearReferenceNo(){
			$('#referenceNo_id').val('');
			$('#modifyApplicationCompleteFormAppId').val('0');
		}
	</script>
</body>
</html>
</s:i18n>


