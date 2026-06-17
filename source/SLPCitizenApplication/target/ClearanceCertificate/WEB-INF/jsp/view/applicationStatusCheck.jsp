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
	<title>Application Status Check</title>
	
</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
				
		<div id="es-content" >	
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle">Application Status Check</c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
			<div>
				<div class="col-lg-12">	
					<h2 style="font-weight:bold;">Please enter the Application Reference Number to view status of your application</h2>
				</div>
				<br />
				<br />
				<div>
					<div class="col-lg-2"></div>
			  	</div>
				<div class="col-lg-9" >		
					<div class="well">
						<ul>
							<li>
								<div class="col-sm-4">
			   						<label for="nicUploadPath" class="control-label"><b>Application Reference No : <span style="color:red">*</span></b></label>
			   					</div>
			   					
			   					<div class="col-lg-5">
				   					<input type="text" name="referenceNo" id="referenceNo" class="form-control">
				   					<input type="hidden" name="urlPath" id="urlPath" value="${urlPath}">
								</div>
								
								<div class="col-lg-3">
									<button type="button" class="btn btn-primary" onclick="checkApplicationStatus();">Submit</button>
								</div>
							</li>
						</ul>	
						<br />				
					</div>
				</div>
				<div>
					<div class="col-lg-2"></div>
			  	</div>
			</div>
			
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />					
		</div>
		<jsp:include page="../common/footer.jsp" />
		
		<div style="display: none;">
			<a data-toggle="modal" id="applicationStatusCheck" href="#applicationStatusCheckModalPopUp" >Application Status Check</a>
		</div>
		
	  	<!--  #####################################################	 APPLICATION STATUS CHECK MODEl POPUP   ######################################################## -->
		<div class="modal fade" id="applicationStatusCheckModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog modal-lg">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">Application Status Check</h4>
				</div>
								          
				<div class="modal-body">
					<div id="applicationStatusCheckView"></div>
				 </div>
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
	<!--  #####################################################	APPLICATION STATUS CHECK MODEl POPUP ######################################################## -->
	</div>	
	<script language="javascript" type="text/javascript">
	function checkApplicationStatus() {
		var referenceNo = $('#referenceNo').val();
		var urlPath = $('#urlPath').val();
		
		 if(referenceNo.trim().length == 0) {
				alert("Reference number is required");
				return false;
		} else {
			$.get(urlPath+'checkApplicationStatus.action',
				{referenceNo:referenceNo},
				function(data){
					var message=data.message;
					$('#applicationStatusCheckView').html('');
					if(message != ""){
						$('#applicationStatusCheckView').html('<h2 style="text-align:center;font-weight:bold;">'+message+'</h2>');
					} else {
						$('#applicationStatusCheckView').html("Please try again later.");
					}
					$('#applicationStatusCheck').click();	
			});
		}
	}
	
	</script>
</body>
</html>
</s:i18n>