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
	<title>Clearance Certificate Verification</title>
	
</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
				
		<div id="es-content" >	
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle">Clearance Certificate Verification</c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
			<div>
				<div class="col-lg-12">	
					<h2 style="font-weight:bold;">Please enter the Clearance certificate number, NIC number and Passport number to verify details.</h2>
				</div>
				<br />
				<br />
				<div>
					<div class="col-lg-3"></div>
			  	</div>
				<div class="col-lg-6" >		
					<div class="well">
						<br />
						<div>
							<div class="col-sm-4">
		   						<label for="nicNo" class="control-label"><b>NIC No : <span style="color:red">*</span></b></label>
		   					</div>
		   					
		   					<div class="col-lg-7">
			   					<input type="text" name="nicNo" id="nicNo" class="form-control">
			   					<input type="hidden" name="urlPath" id="urlPath" value="${urlPath}">
							</div>
							
							<div class="col-sm-1">
		   						&nbsp;
		   					</div>
						</div>
						<br /><br />
						<div>
							<div class="col-sm-4">
		   						<label for="passportNo" class="control-label"><b>Passport No : <span style="color:red">*</span></b></label>
		   					</div>
		   					
		   					<div class="col-lg-7">
			   					<input type="text" name="passportNo" id="passportNo" class="form-control">
							</div>

							<div class="col-sm-1">
		   						&nbsp;
		   					</div>
						</div>
						<br /><br />		
						<div>
							<div class="col-sm-4">
		   						<label for="certificateNo" class="control-label"><b>Certificate No : <span style="color:red">*</span></b></label>
		   					</div>
		   					
		   					<div class="col-lg-7">
			   					<input type="text" name="certificateNo" id="certificateNo" class="form-control">
							</div>
							
							<div class="col-sm-1">
		   						&nbsp;
		   					</div>
						</div>	
						<br /><br />		
						<div>
							<div class="col-sm-9">
		   						&nbsp;
		   					</div>
		   					
							<div class="col-lg-3" style="margin-left: -24px;">
								<button type="button" class="btn btn-primary" onclick="verifyClearanceCertificate();">Submit</button>
							</div>
						</div>	
						<br /><br />						
					</div>
				</div>
				<div>
					<div class="col-lg-3"></div>
			  	</div>
			</div>
			
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />					
		</div>
		<jsp:include page="../common/footer.jsp" />
		
		<div style="display: none;">
			<a data-toggle="modal" id="clearanceCertificateVerification" href="#clearanceCertificateVerificationModalPopUp" >Clearance Certificate Verification</a>
		</div>
		
	  	<!--  #####################################################	 CLEARANCE CERTIFICATE VERIFICATION MODEl POPUP   ######################################################## -->
		<div class="modal fade" id="clearanceCertificateVerificationModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog modal-lg">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">Clearance Certificate Verification</h4>
				</div>
								          
				<div class="modal-body">
					<div id="clearanceCertificateVerificationView"></div>
				 </div>
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
	<!--  #####################################################	CLEARANCE CERTIFICATE VERIFICATION MODEl POPUP ######################################################## -->
	</div>	
	<script language="javascript" type="text/javascript">
	function verifyClearanceCertificate() {
		var nicNo = $('#nicNo').val();
		var passportNo = $('#passportNo').val();
		var certificateNo = $('#certificateNo').val();
		var urlPath = $('#urlPath').val();
		//alert(urlPath)
		if(nicNo.trim().length == 0) {
			alert("Nic number is required");
			return false;
		} else if(passportNo.trim().length == 0) {
			alert("Passport number is required");
			return false;
		} else if(certificateNo.trim().length == 0) {
			alert("Certificate number is required");
			return false;
		} else {
			$.get(urlPath+'clearanceCertificateVerification.action',
					{nicNo:nicNo, passportNo:passportNo, certificateNo:certificateNo},
					function(data){
						var message=data.statusMessage;
						$('#clearanceCertificateVerificationView').html('');
						if(message != ""){
							$('#clearanceCertificateVerificationView').html('<h2 style="text-align:center;font-weight:bold;">'+message+'</h2>');
						} else {
							$('#clearanceCertificateVerificationView').html("Please try again later.");
						}
						$('#clearanceCertificateVerification').click();	
				});
		}

	}
	
	</script>
</body>
</html>
</s:i18n>