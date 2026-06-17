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
	<title>Request for Update</title>
	<style type="text/css">
		.wall_override{
			background-color: #fff;
		    border: 0px solid #e1e2e3;
		    border-radius: 4px;
		    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.05) inset;
		    margin-bottom: 20px;
		    min-height: 20px;
		    padding: 25px;
		} 
	</style>
</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
				
		<div id="es-content" >	
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle">Request for Update</c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
			<div>
				<div class="col-lg-5"></div>
		  	</div>
			<div>
				<form action="loadRequestForUpdate.action" method="post" id="loadRequestForUpdateForm">	
					<div class="col-lg-6" style="float: right;">		
						<div class="form-group">
							<div class="col-sm-4" >
								<strong><s:label cssClass="control-label bold-label" for="referenceNo_id">Application Reference No </s:label></strong>
		   					</div>
		   					<div class="col-sm-6">
		   						<s:textfield name="searchReferenceNo" id="searchReferenceNoId" cssClass="form-control"/>
		   					</div>
		   					<div class="col-sm-2" >
		   						<input type="submit" class="btn btn-primary es-buttton" value="Load" style="float: right;"/>
		   					</div>
	   					</div>
					</div>
				</form>
		  	</div>
		  	<div id="showHideForm">
			  	<c:if test="${requestClarificationVO != null}">
			  		<form action="saveRequestForUpdate.action" method="post" id="saveRequestForUpdateForm" onsubmit="return validateForm();">	
			  			<input type="hidden" name="nicStatus" id="nicStatus" value="${requestClarificationVO.nic}">
			  			<input type="hidden" name="passportStatus" id="passportStatus" value="${requestClarificationVO.passport}">
			  			<input type="hidden" name="nameStatus" id="nameStatus" value="${requestClarificationVO.verifyName}">
			  			<input type="hidden" name="dobStatus" id="dobStatus" value="${requestClarificationVO.verifyDateOfBirth}">
			  			<input type="hidden" name="referenceNo" id="referenceNo" value="${requestClarificationVO.referenceNo}">
					  	<br />
					  	<br />
					  	<div>
							<div class="col-lg-12">	
								Please submit the following requested information.
							</div>
					  	</div>
					  	<br />
					  	<br />
				  		<div>
							<div class="col-lg-6">		
								<p style="text-align: center;"><strong>Replace With</strong></p>
							</div>
					  	</div>
						
						<div>
							<div class="col-lg-6">		
								<p style="text-align: center;"><strong>Message from PHQ</strong></p>
							</div>
					  	</div>
					  	<!-- Change for DSRS version 1.2 -->
						<%-- <c:if test="${requestClarificationVO.nic == 1}"> --%>
							<div>
								<div class="col-lg-8">		
									<div class="well new_bullet">
										<ul>
											<li>
												<table style="width:100%">
													<tr>
														<td>
															<div class="col-sm-3">
										   						<label for="nicUploadPath" class="control-label"><b>NIC front side:<c:if test="${requestClarificationVO.nic == 1}"><span style="color:red">*</span></c:if></b></label>
										   					</div>
										   					<div class="col-sm-5">
										   						<input id="nicFileUpload" name="nicFileUpload" type="file" style=""/>
										   						<s:hidden name="nicAttachPath" id="nicUploadPath" cssClass="form-control" />		   						
										   					</div>	
										   					<div class="col-lg-3">
											   					<img alt="Upload NIC" src="images/upload.png" style="width: 50px; height: 45px;margin-top: -8px;cursor: pointer;" onclick="uploadNICFile();">
											   					<img src="images/ajax-loader.gif" id="ajax_loader_nic" style="display:none;"/>
										        				<img src="images/tick.png" id="upload_complete_nic" style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="col-sm-3">
										   						<label for="nicUploadPathBack" class="control-label"><b>NIC back side:<c:if test="${requestClarificationVO.nic == 1}"><span style="color:red">*</span></c:if></b></label>
										   					</div>
										   					<div class="col-sm-5">
										   						<input id="nicFileUploadBack" name="nicFileUploadBack" type="file" style=""/>
										   						<s:hidden name="nicAttachPathBack" id="nicUploadPathBack" cssClass="form-control" />		   						
										   					</div>	
										   					<div class="col-lg-3">
											   					<img alt="Upload NIC" src="images/upload.png" style="width: 50px; height: 45px;margin-top: -8px;cursor: pointer;" onclick="uploadNICFileBack();">
											   					<img src="images/ajax-loader.gif" id="ajax_loader_nic_back" style="display:none;"/>
										        				<img src="images/tick.png" id="upload_complete_nic_back" style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
															</div>
														</td>
													</tr>												
												</table>											
											</li>
										</ul>					
									</div>
								</div>
						  	</div>
							
						  	<div>
								<div class="col-lg-4">		
									<c:if test="${requestClarificationVO.nic == 1}">
										<div class="well new_bullet">
											<ul>
												<li>${requestClarificationVO.nicMessage}</li>
											</ul>
										</div>
									</c:if>
									<c:if test="${requestClarificationVO.nic == 0}">
										<div class="wall_override">
											&nbsp;
										</div>
									</c:if>
								</div>
						  	</div>
						<%-- </c:if>
							
						<c:if test="${requestClarificationVO.passport == 1}"> --%>
							<div>
								<div class="col-lg-8">		
									<div class="well new_bullet">
										<ul>
											<li>
												<table style="width:100%">
													<tr>
														<td>
															<div class="col-sm-5">
										   						<label for="passportUploadPath" class="control-label"><b>Passport-Personal detail page:<c:if test="${requestClarificationVO.passport == 1}"><span style="color:red">*</span></c:if></b></label>
										   					</div>
										   					<div class="col-sm-4">
										   						<input id="passportFileUpload" name="passportFileUpload" type="file" style=""/>
										   						<s:hidden name="passportAttachPath" id="passportUploadPath" cssClass="form-control" />		   						
										   					</div>
									   						<div class="col-lg-3">
											   					<img alt="Upload Passport" src="images/upload.png" style="width: 50px; height: 45px;margin-top: -8px;cursor: pointer;" onclick="uploadPassportFile();">
											   					<img src="images/ajax-loader.gif" id="ajax_loader_passport" style="display:none;"/>
										        				<img src="images/tick.png" id="upload_complete_passport" style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="col-sm-5">
										   						<label for="passportUploadPathBack" class="control-label"><b>Passport-Countries allowed page:<c:if test="${requestClarificationVO.passport == 1}"><span style="color:red">*</span></c:if></b></label>
										   					</div>
										   					<div class="col-sm-4">
										   						<input id="passportFileUploadBack" name="passportFileUploadBack" type="file" style=""/>
										   						<s:hidden name="passportAttachPathBack" id="passportUploadPathBack" cssClass="form-control" />		   						
										   					</div>
									   						<div class="col-lg-3">
											   					<img alt="Upload Passport" src="images/upload.png" style="width: 50px; height: 45px;margin-top: -8px;cursor: pointer;" onclick="uploadPassportFileBack();">
											   					<img src="images/ajax-loader.gif" id="ajax_loader_passport_back" style="display:none;"/>
										        				<img src="images/tick.png" id="upload_complete_passport_back" style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
															</div>
														</td>
													</tr>
												</table>
											</li>
										</ul>
									</div>
								</div>
						  	</div>
						  	<div>
								<div class="col-lg-4">		
									<c:if test="${requestClarificationVO.passport == 1}">
										<div class="well new_bullet">
											<ul>
												<li>${requestClarificationVO.passportMessage}</li>
											</ul>
										</div>
									</c:if>
									<c:if test="${requestClarificationVO.passport == 0}">
										<div class="wall_override">
											&nbsp;
										</div>
									</c:if>
								</div>
						  	</div>
						<%-- </c:if>
							
						<c:if test="${requestClarificationVO.verifyName == 1}"> --%>
							<div>
								<div class="col-lg-8">		
									<div class="well new_bullet" style="height: 100px;">
										<ul>
											<li>
												<div class="col-sm-2">
							   						<label for="name" class="control-label"><b>Name:<c:if test="${requestClarificationVO.verifyName == 1}"><span style="color:red">*</span></c:if></b></label>
							   					</div>
							   					<div class="col-sm-8">
							   						<s:textarea name="name" cssClass="form-control" id="name" cols="32" rows="2"/>
							   						 						
							   					</div>
											</li>
										</ul>
									</div>
								</div>
						  	</div>
						  	<div>
								<div class="col-lg-4">		
									<c:if test="${requestClarificationVO.verifyName == 1}">
										<div class="well new_bullet" style="height: 100px;" >
											<ul>
												<li>${requestClarificationVO.nameMessage}</li>
											</ul>
										</div>
									</c:if>
									<c:if test="${requestClarificationVO.verifyName == 0}">
										<div class="wall_override" style="padding: 42px !important;">
											&nbsp;
										</div>
									</c:if>
								</div>
						  	</div>
						<%-- </c:if>
							
						<c:if test="${requestClarificationVO.verifyDateOfBirth == 1}"> --%>
							<div>
								<div class="col-lg-8">		
									<div class="well new_bullet">
										<ul>
											<li>
												<div class="col-sm-2">
							   						<label for="dateOfBirth" class="control-label" style="margin-top:-12px;"><b>Date of Birth:<c:if test="${requestClarificationVO.verifyDateOfBirth == 1}"><span style="color:red">*</span></c:if></b></label>
							   					</div>
							   					<div class="col-sm-8">
							   						<c:set var="customDOB">
							   							<fmt:formatDate value="${dateOfBirth}" pattern="dd/MM/yyyy"/>
							   						</c:set>
							   						<input type="text" class="form-control" readonly="readonly" name="dateOfBirth" value="${customDOB}" id="dateOfBirth_id" class="form-control" style="background-color: #ffffff;">				
							   					</div>
											</li>
										</ul>
									</div>
								</div>
						  	</div>
						  	<div>
								<div class="col-lg-4">		
									<c:if test="${requestClarificationVO.verifyDateOfBirth == 1}">
									<div class="well new_bullet">
										<ul>
											<li>${requestClarificationVO.dateOfBirthMessage}</li>
										</ul>
									</div>
									</c:if>
									<c:if test="${requestClarificationVO.verifyDateOfBirth == 0}">
										<div class="wall_override">
											&nbsp;
										</div>
									</c:if>
								</div>
						  	</div>
						<%-- </c:if> --%>
						<c:if test="${requestClarificationVO.nic == 1 || requestClarificationVO.passport == 1 || requestClarificationVO.verifyName == 1 || requestClarificationVO.verifyDateOfBirth == 1}">
							<hr />
						  	<br />
						  	
						  	<div>
								<div class="col-lg-8">		
									
									<div class="col-sm-2">
				   						<label for="comment" class="control-label"><b>Comment:<span style="color:red">*</span></b></label>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textarea name="comment" id="comment" cssClass="form-control" cols="58" rows="5"/>						
				   					</div>  
								</div>
						  	</div>
						  	<div>
								<div class="col-lg-4">		
									
								</div>
						  	</div>
						  	
						  	<div>
								<div class="col-lg-12" style="float: right;">	
									<div style="float: right;">
										<input id="requestForUpdateBtn" type="submit" class="btn btn-primary es-buttton" value="Submit"/>
										<input type="button" class="btn btn-primary es-buttton" value="Cancel" onclick="hideForm();"/>
									</div>
								</div>
							</div>
						</c:if>
					</form>
				</c:if>
			</div>
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />					
		</div>
		<jsp:include page="../common/footer.jsp" />
	</div>	
	<script language="javascript" type="text/javascript">

	$(document).ready(function() {		 
		initializeDateTimePickers();
	});
	
	function initializeDateTimePickers(){
		  $('#dateOfBirth_id').datepicker({
			  changeYear : true,
			  yearRange: "-120:+0",
			  changeMonth : true,
			  dateFormat:"dd/mm/yy",
			  maxDate: new Date()
		  });	 
	}

	function validateForm() {
		var nicStatus=$('#nicStatus').val();
		var passportStatus=$('#passportStatus').val();
		var nameStatus=$('#nameStatus').val();
		var dobStatus=$('#dobStatus').val();
		
		if(nicStatus == 1){
			var nicUploadPath = $.trim($("#nicUploadPath").val());
			if(nicUploadPath == ''){
				alert('NIC front copy is required.');
				return false;
			}
			
			var nicUploadPathBack = $.trim($("#nicUploadPathBack").val());
			if(nicUploadPathBack == ''){
				alert('NIC back copy is required.');
				return false;
			}
		}

		if(passportStatus == 1){
			var passportUploadPath = $.trim($("#passportUploadPath").val());
			if(passportUploadPath == ''){
				alert('Passport Personal detail page is required.');
				return false;
			}
			
			var passportUploadPathBack = $.trim($("#passportUploadPathBack").val());
			if(passportUploadPathBack == ''){
				alert('Passport Countries allowed page is required.');
				return false;
			}
		}
		
		if(nameStatus == 1){
			var name = $.trim($("#name").val());
			if(name == ''){
				alert('Name is required.');
				return false;
			}
		}

		if(dobStatus == 1){
			var dob = $.trim($("#dateOfBirth_id").val());
			if(dob == ''){
				alert('Date of Birth is required.');
				return false;
			}
		}

		if(nicStatus == 1 || passportStatus == 1 || nameStatus == 1 || dobStatus == 1) {
			var comment = $.trim($("#comment").val());
			if(comment == ''){
				alert('Comment is required.');
				return false;
			}
		}
		
		var conf=confirm('Are you sure you want to submit this details?');
		if(!(conf)){
			return false;
		}
		return true;
	}
	
	//function for check the upload is supported for using browser
    function supportAjaxUploadWithProgress() {
  	  return supportFileAPI() && supportAjaxUploadProgressEvents() && supportFormData();
  	     function supportFileAPI() {
  	       var fi = document.createElement('INPUT');
  	       fi.type = 'file';
  	       return 'files' in fi;
  	     };
  	    function supportAjaxUploadProgressEvents() {
  	      var xhr = new XMLHttpRequest();
  	     return !! (xhr && ('upload' in xhr) && ('onprogress' in xhr.upload));
  	    };
  	    function supportFormData() {
  	      return !! window.FormData;
  	    }
  	}

    function uploadNICFile(){
  	  //check the browser
  	  if(!supportAjaxUploadWithProgress){
  		  alert("Please update your browser to upload");
  		  return false;
  	  }
  	
  	  var xhr = new XMLHttpRequest();
  	  var nicInput = document.getElementById('nicFileUpload');
  	  
  	  var nicFile = nicInput.files[0];
  	  
  	   var maximumFileLimit=parseFloat($('#maximumFileLimit').val());
  	  
  	  //validate upload
  	  var fileNicPath = document.getElementById('nicFileUpload').value;
  	  var nicExtension = fileNicPath.split(".").pop().trim();
	  //var fileExtensionArray = ["pdf","doc","docx","png","jpg","jpeg"];
  	  if(fileNicPath == ''){
  		  alert("please select a file before upload for nic");
  		  return false;
  	  }else if(nicExtension != "pdf" && nicExtension != "PDF" && nicExtension != "docx" && nicExtension != "DOCX" && nicExtension != "doc" && nicExtension != "DOC" && nicExtension != "png" && nicExtension != "PNG" && nicExtension != "jpg" && nicExtension != "JPG"){
  	  //as inArray will return -1, if the element was not found.
  	 //}else if($.inArray(nicExtension.toLowerCase(), fileExtensionArray) == -1) {
  		  alert("invalid nic file format");
  		  return false;
  	  }else if(nicFile.size > maximumFileLimit){
  		  alert("nic file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
  		  return false;
  	  }
  	  
  	  //show ajax upload gif
  	  $("#ajax_loader_nic").show();
  	  
  	  var formData = new FormData();
  	  formData.append('file', nicFile);
  	  formData.append('fileExtension', nicExtension);
  	  formData.append('fileType', "NICF");
  	  formData.append('uploadType', "REUPVRFC");
  	  
  	  xhr.open('POST', 'uploadFile.action', true);
  	  xhr.send(formData);
  	  
  	  xhr.onreadystatechange=function()
  	  {
  	  	if (xhr.readyState==4 && xhr.status==200)
  	    {
  	  		var jsonData = JSON.parse(xhr.responseText);
  	  		var nicFileName = jsonData.fileName;
  	  		
  	  		if(nicFileName != ''){
  	  			$("#nicUploadPath").val(nicFileName);
  	  		}
  	  		
  	  		$("#ajax_loader_nic").hide();
  	  		$("#upload_complete_nic").show();
  	  		//clear the ppt upload field for prevent the multiple upload of same file
	  		//document.getElementById('nicFileUpload').value="";
  	  		//add comment bcoz defect 0001237 (System does not show the uploaded file name after the user upload requested documents.)
  	    }
  	  }
  	  
    }
    
    
    function uploadNICFileBack(){
    	  //check the browser
    	  if(!supportAjaxUploadWithProgress){
    		  alert("Please update your browser to upload");
    		  return false;
    	  }
    	
    	  var xhr = new XMLHttpRequest();
    	  var nicInput = document.getElementById('nicFileUploadBack');
    	  
    	  var nicFile = nicInput.files[0];
    	  
    	   var maximumFileLimit=parseFloat($('#maximumFileLimit').val());
    	  
    	  //validate upload
    	  var fileNicPath = document.getElementById('nicFileUploadBack').value;
    	  var nicExtension = fileNicPath.split(".").pop().trim();
  	  //var fileExtensionArray = ["pdf","doc","docx","png","jpg","jpeg"];
    	  if(fileNicPath == ''){
    		  alert("please select a file before upload for nic");
    		  return false;
    	  }else if(nicExtension != "pdf" && nicExtension != "PDF" && nicExtension != "docx" && nicExtension != "DOCX" && nicExtension != "doc" && nicExtension != "DOC" && nicExtension != "png" && nicExtension != "PNG" && nicExtension != "jpg" && nicExtension != "JPG"){
    	  //as inArray will return -1, if the element was not found.
    	 //}else if($.inArray(nicExtension.toLowerCase(), fileExtensionArray) == -1) {
    		  alert("invalid nic file format");
    		  return false;
    	  }else if(nicFile.size > maximumFileLimit){
    		  alert("nic file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
    		  return false;
    	  }
    	  
    	  //show ajax upload gif
    	  $("#ajax_loader_nic_back").show();
    	  
    	  var formData = new FormData();
    	  formData.append('file', nicFile);
    	  formData.append('fileExtension', nicExtension);
    	  formData.append('fileType', "NICB");
    	  formData.append('uploadType', "REUPVRFC");
    	  
    	  xhr.open('POST', 'uploadFile.action', true);
    	  xhr.send(formData);
    	  
    	  xhr.onreadystatechange=function()
    	  {
    	  	if (xhr.readyState==4 && xhr.status==200)
    	    {
    	  		var jsonData = JSON.parse(xhr.responseText);
    	  		var nicFileName = jsonData.fileName;
    	  		
    	  		if(nicFileName != ''){
    	  			$("#nicUploadPathBack").val(nicFileName);
    	  		}
    	  		
    	  		$("#ajax_loader_nic_back").hide();
    	  		$("#upload_complete_nic_back").show();
    	  		//clear the ppt upload field for prevent the multiple upload of same file
  	  		//document.getElementById('nicFileUpload').value="";
    	  		//add comment bcoz defect 0001237 (System does not show the uploaded file name after the user upload requested documents.)
    	    }
    	  }
    	  
      }

    function uploadPassportFile(){
    	  var maximumFileLimit=parseFloat($('#maximumFileLimit').val());
    	  //check the browser
    	  if(!supportAjaxUploadWithProgress){
    		  alert("Please update your browser to upload");
    		  return false;
    	  }
    	
    	  var xhr = new XMLHttpRequest();
    	  var passportInput = document.getElementById('passportFileUpload');
    	  
    	  var passportFile = passportInput.files[0];
    	  
    	  //validate upload
    	  var filePassportPath = document.getElementById('passportFileUpload').value;
    	  var passportExtension = filePassportPath.split(".").pop().trim();
    	  
    	  if(filePassportPath == ''){
    		  alert("please select a file before upload for passport");
  		  return false;
    	  }else if(passportExtension != "pdf" && passportExtension != "PDF" && passportExtension != "docx" && passportExtension != "DOCX" && passportExtension != "doc" && passportExtension != "DOC" && passportExtension != "png" && passportExtension != "PNG" && passportExtension != "jpg" && passportExtension != "JPG"){
    		  alert("invalid passport file format");
    		  return false;
    	  }else if(passportFile.size > maximumFileLimit){
    		  alert("passport file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
    		  return false;
    	  }
    	  
    	  //show ajax upload gif
    	  $("#ajax_loader_passport").show();
    	  
    	  var formData = new FormData();
    	  formData.append('file', passportFile);
  	  	  formData.append('fileExtension', passportExtension);
  	  	  formData.append('fileType', "PASF");
   	      formData.append('uploadType', "REUPVRFC");
   	  
   	      xhr.open('POST', 'uploadFile.action', true);
    	  xhr.send(formData);
    	  
    	  xhr.onreadystatechange=function()
    	  {
    	  	if (xhr.readyState==4 && xhr.status==200)
    	    {
    	  		var jsonData = JSON.parse(xhr.responseText);
    	  		var passFileName = jsonData.fileName;

    	  		if(passFileName != ''){
    	  			$("#passportUploadPath").val(passFileName);
    	  		}
    	  		
    	  		$("#ajax_loader_passport").hide();
    	  		$("#upload_complete_passport").show();
    	  		//clear the ppt upload field for prevent the multiple upload of same file
    	  		//document.getElementById('passportFileUpload').value="";
    	  		//add comment bcoz defect 0001237 (System does not show the uploaded file name after the user upload requested documents.)
    	    }
    	  }
    	  
      }
    
    
    function uploadPassportFileBack(){
  	  var maximumFileLimit=parseFloat($('#maximumFileLimit').val());
  	  //check the browser
  	  if(!supportAjaxUploadWithProgress){
  		  alert("Please update your browser to upload");
  		  return false;
  	  }
  	
  	  var xhr = new XMLHttpRequest();
  	  var passportInput = document.getElementById('passportFileUploadBack');
  	  
  	  var passportFile = passportInput.files[0];
  	  
  	  //validate upload
  	  var filePassportPath = document.getElementById('passportFileUploadBack').value;
  	  var passportExtension = filePassportPath.split(".").pop().trim();
  	  
  	  if(filePassportPath == ''){
  		  alert("please select a file before upload for passport");
		  return false;
  	  }else if(passportExtension != "pdf" && passportExtension != "PDF" && passportExtension != "docx" && passportExtension != "DOCX" && passportExtension != "doc" && passportExtension != "DOC" && passportExtension != "png" && passportExtension != "PNG" && passportExtension != "jpg" && passportExtension != "JPG"){
  		  alert("invalid passport file format");
  		  return false;
  	  }else if(passportFile.size > maximumFileLimit){
  		  alert("passport file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
  		  return false;
  	  }
  	  
  	  //show ajax upload gif
  	  $("#ajax_loader_passport_back").show();
  	  
  	  var formData = new FormData();
  	  formData.append('file', passportFile);
	  formData.append('fileExtension', passportExtension);
	  formData.append('fileType', "PASB");
 	  formData.append('uploadType', "REUPVRFC");
 	  
 	  xhr.open('POST', 'uploadFile.action', true);
  	  xhr.send(formData);
  	  
  	  xhr.onreadystatechange=function()
  	  {
  	  	if (xhr.readyState==4 && xhr.status==200)
  	    {
  	  		var jsonData = JSON.parse(xhr.responseText);
  	  		var passFileName = jsonData.fileName;

  	  		if(passFileName != ''){
  	  			$("#passportUploadPathBack").val(passFileName);
  	  		}
  	  		
  	  		$("#ajax_loader_passport_back").hide();
  	  		$("#upload_complete_passport_back").show();
  	  		//clear the ppt upload field for prevent the multiple upload of same file
  	  		//document.getElementById('passportFileUpload').value="";
  	  		//add comment bcoz defect 0001237 (System does not show the uploaded file name after the user upload requested documents.)
  	    }
  	  }
  	  
    }
    
    function hideForm() {    	
    	$("#showHideForm").html("");
    	$("#searchReferenceNoId").val("");
    }
	</script>
</body>
</html>
</s:i18n>