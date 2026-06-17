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
<title>Master Files</title>
<style type="text/css">
	
	.radio {
		display: inline !important;
		padding: 10px 40px 10px 5px !important;
		min-height: 0px !important;
		font-weight: bold !important;
	}
	
	.radioHC {
		display: inline !important;
		padding: 10px 10px 10px 5px !important;
		min-height: 0px !important;
	}
</style>
</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Master Files</c:set>
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
					<div class="col-lg-5">
						<div class="col-sm-10">
							<s:radio id="masterFileType" name="masterFileType" list="masterFileTypeList" listKey="value" listValue="text" cssClass="radio" theme="simple" onclick="showHidePanels();"/>
							<input type="hidden" name="showHideFlag" id="showHideFlag" 
								<c:choose>
									<c:when test="${showHideFlag == 1}">value="1"</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${showHideFlag == 2}">value="2"</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${showHideFlag == 3}">value="3"</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${showHideFlag == 4}">value="4"</c:when>
								</c:choose>
							/>
						</div>
						<div class="col-sm-2">
							&nbsp;
						</div>
					</div>
					<dir style="clear: both;"></dir>
					
					<br />
					<br />
					<div id="certificateSigningPanel">
						<div class="col-lg-9">
							<div class="col-sm-4">
								Name of the current authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" name="curAuthPerson" cssClass="form-control" readonly="true"/>
								<input type="hidden" value="${curAuthPersonId}" id="curAuthPersonId"  readonly="readonly"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Designation of the current authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" name="curAuthPersonDesignation" cssClass="form-control" readonly="true"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Address of the current authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" name="curAuthPersonAddress" cssClass="form-control" readonly="true"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Active from : 
							</div>
							<div class="col-sm-4">
								<c:set var="customEndDate">
		   							<fmt:formatDate value="${activeDate}" pattern="dd/MM/yyyy"/>
		   						</c:set>
								<input type="text" readonly="readonly" name="activeDate" value="${customEndDate}" id="activeDate" class="form-control">
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Name of the New authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" id="newAuthPerson" cssClass="form-control"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Designation of the New authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" id="designation" cssClass="form-control"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Address of the New authorized person : 
							</div>
							<div class="col-sm-8">
								<s:textarea id="authPersonAddress" cssClass="form-control"></s:textarea>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Effective Date : 
							</div>
							<div class="col-sm-4">
								<c:set var="customEndDate">
		   							<fmt:formatDate value="${effectiveDate}" pattern="dd/MM/yyyy"/>
		   						</c:set>
		   						<input type="text" readonly="readonly" value="${customEndDate}" id="effectiveDate" class="form-control">
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-8">
								&nbsp; 
							</div>
							<div class="col-sm-4">
		   						<div style="text-align:right;">
									<button type="button" class="btn btn-primary" onclick="saveCertificateSigning();">Submit</button>
					 				<a href="home.action"><button type="button" class="btn btn-primary" >Cancel</button></a>
								</div>
							</div>
						</div>
						<dir style="clear: both;"></dir>
					</div>
					
					<div id="highCommissionsPanel">
						<div class="col-lg-9">
							<div class="col-sm-4">
								Country : 
							</div>
							<div class="col-sm-5">
								<select id="country" class="form-control" onchange="loadAvailableHighCommissionerList();">
									<option value="0">-- Please Select --</option>
		   							<c:forEach var="country" items="${countryList}">
		   								<option value="${country.id}">${country.countryName}</option>
		   							</c:forEach>
		   						</select>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Type : 
							</div>
							<div class="col-sm-8">
								<s:radio name="highcommissionType" id="highcommissionType" list="highcommissionTypeList" listKey="value" listValue="text" cssClass="radioHC" theme="simple" onclick="setAddressee();"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Addressee : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" id="addressee" cssClass="form-control"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Name of the authority : 
							</div>
							<div class="col-sm-8">
								<s:textfield type="text" id="nameOfAuthority" cssClass="form-control"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-4">
								Address : 
							</div>
							<div class="col-sm-4">
								<s:textarea id="address" cols="98" rows="2"/>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						
						<br />
						
						<div class="col-lg-9">
							<div class="col-sm-8">
								&nbsp; 
							</div>
							<div class="col-sm-4">
		   						<div style="text-align:right;">
		   							<button type="button" class="btn btn-primary" onclick="saveHighCommissions();">Add</button>
									<button type="button" class="btn btn-primary" onclick="updateHighCommissions();">Modify</button>
					 				<button type="button" class="btn btn-primary" onclick="resetHighCommissions();">Reset</button>
								</div>
							</div>
						</div>
						<dir style="clear: both;"></dir>
						<br />
						<br />
						<div  id="availableHighCommissionerModelAppendDiv">
						
						</div>
						
					</div>
					
	   			</div>
				<form action="saveCertificateSigning.action" method="post" id="saveCertificateSigningForm" onsubmit="return validateCertificateSigning();">
					<input type="hidden" name="newAuthPerson" id="newAuthPersonForm" />
					<input type="hidden" name="effectiveDate" id="effectiveDateForm" />
					<input type="hidden" name="curAuthPersonId" id="curAuthPersonIdForm" />
					<input type="hidden" name="designation" id="curAuthPersonDesignationForm" />
					<input type="hidden" name="authPersonAddress" id="curAuthPersonAddressForm" />
				</form>
				
				<form action="saveHighCommissions.action" method="post" id="saveHighCommissionsForm" onsubmit="return validateHighCommissions();">
					<input type="hidden" name="countryId" id="countryIdForm" />
					<input type="hidden" name="hcType" id="highcommissionTypeForm" />
					<input type="hidden" name="nameOfAuthority" id="nameOfAuthorityForm" />
					<input type="hidden" name="address" id="addressForm" />
					<input type="hidden" name="addressee" id="addresseeForm" />
				</form>
						
				<form action="updateHighCommissions.action" method="post" id="updateHighCommissionsForm" onsubmit="return validateHighCommissions();">
					<input type="hidden" name="countryId" id="countryIdUpdateForm" />
					<input type="hidden" name="hcType" id="highcommissionTypeUpdateForm" />
					<input type="hidden" name="nameOfAuthority" id="nameOfAuthorityUpdateForm" />
					<input type="hidden" name="address" id="addressUpdateForm" />
					<input type="hidden" id="hcId" name="hcId"/>
					<input type="hidden" name="addressee" id="addresseeUpdateForm" />
				</form>
				
				<form action="deleteHighCommissionsForm.action" method="post" id="deleteHighCommissionsForm">
					<input type="hidden" id="hcIdDelete" name="hcId"/>
				</form>
		</div>
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />
	</div>
	
	<script language="javascript" type="text/javascript">
	$(document).ready(function() {		 
		initializeDateTimePickers();
		$('#availableHighCommissionerModelAppendDiv').html("");
		var showHideFlag = $('#showHideFlag').val();
		$('#effectiveDate').val("");
		if(showHideFlag == 1 || showHideFlag == 2 ) {
			$("#masterFileType1").prop("checked", true);
			$("#highcommissionTypeHC").prop("checked", true);
			$('#highCommissionsPanel').hide();
			$('#certificateSigningPanel').show();
		} else {
			$("#masterFileType2").prop("checked", true);
			$("#highcommissionTypeHC").prop("checked", true);
			$('#certificateSigningPanel').hide();
			$('#highCommissionsPanel').show();
		}
		
		$('#hcId').val(""); // highcommissioner edit mode reset to add mode
		$('#addressee').attr('readonly', true);
		$('#addressee').val("H.E. THE HIGH COMMISSIONER");
	});
	
	function initializeDateTimePickers(){

		$( "#effectiveDate" ).datepicker({
	      defaultDate: "+0d",
	      dateFormat:"dd/mm/yy",
	      minDate: 0
	      });
	}
	
	function showHidePanels() {
		var showHideFlag = $('input[name="masterFileType"]:checked').val();

		if(showHideFlag == 1) {
			$('#highCommissionsPanel').hide();
			$('#certificateSigningPanel').show();
		} else {
			$('#certificateSigningPanel').hide();
			$('#highCommissionsPanel').show();
		}
	}
	
	function saveCertificateSigning() {
		var newAuthPerson = $('#newAuthPerson').val();
		var effectiveDate = $('#effectiveDate').val();
		var curAuthPersonId = $('#curAuthPersonId').val();
		var address=$('#authPersonAddress').val();
		var designation=$('#designation').val();
		
		var ans=confirm('Are you sure you want to update this record?');
		if(ans){
			$('#newAuthPersonForm').val(newAuthPerson);			
			$('#effectiveDateForm').val(effectiveDate);
			$('#curAuthPersonIdForm').val(curAuthPersonId);
			
			$('#curAuthPersonDesignationForm').val(designation);
			$('#curAuthPersonAddressForm').val(address);			
			
			$('#saveCertificateSigningForm').submit();
		} else {
			return false;
		}
	}
	
	function validateCertificateSigning(){
		if($('#newAuthPerson').val().trim().length == 0) {
			alert("Name of the new authorized person is required.");
			return false;
		}

		if($('#effectiveDate').val().trim().length == 0) {
			alert("Effective date is required.");
			return false;
		}
	}
	
	function saveHighCommissions() {
		var country = $('#country').val();
		var highcommissionType = $('input[name="highcommissionType"]:checked').val();
		var nameOfAuthority = $('#nameOfAuthority').val();
		var address = $('#address').val();
		var addressee = $('#addressee').val();
		//alert(country + " " + highcommissionType + " " + nameOfAuthority + " " + address);
		var ans=confirm('Are you sure you want to update this record?');
		if(ans){
			$('#countryIdForm').val(country);			
			$('#highcommissionTypeForm').val(highcommissionType);
			$('#nameOfAuthorityForm').val(nameOfAuthority);
			$('#addressForm').val(address);
			$('#addresseeForm').val(addressee);
			
			$('#saveHighCommissionsForm').submit();
		} else {
			return false;
		}
	}
	
	function updateHighCommissions() {
		var country = $('#country').val();
		var highcommissionType = $('input[name="highcommissionType"]:checked').val();
		var nameOfAuthority = $('#nameOfAuthority').val();
		var address = $('#address').val();
		var addressee = $('#addressee').val();
		//alert(country + " " + highcommissionType + " " + nameOfAuthority + " " + address);
		var ans=confirm('Are you sure you want to update this record?');
		if(ans){
			$('#countryIdUpdateForm').val(country);			
			$('#highcommissionTypeUpdateForm').val(highcommissionType);
			$('#nameOfAuthorityUpdateForm').val(nameOfAuthority);
			$('#addressUpdateForm').val(address);
			$('#addresseeUpdateForm').val(addressee);
			
			$('#updateHighCommissionsForm').submit();
		} else {
			return false;
		}
	}
	
	function validateHighCommissions() {
		
		if($('input[name="highcommissionType"]:checked').val().trim().length == 0) {
			alert("High commission type is required.");
			return false;
		}

		if($('#nameOfAuthority').val().trim().length == 0) {
			alert("Name of authority is required.");
			return false;
		}
		
		if($('#address').val().trim().length == 0) {
			alert("Address is required.");
			return false;
		}
		
		if($('input[name="highcommissionType"]:checked').val() == 'OT' && $('#addressee').val().trim().length == 0) {
			alert("Addressee is required.");
			return false;
		}
	}
	
	function loadAvailableHighCommissionerList() {
		var country = $('#country').val();
		
		if(country == 0){
			alert("Please select country");
		} else {
			 $('#availableHighCommissionerModelAppendDiv').html('');
			 $.get('loadAvailableHighCommissionerList',{
				 searchCountry:country,
				 },function(data){
			  	 	var commissionerVOs=data.commissionerVOs;
				  	var appendText="";
			  		appendText=appendText + '<table class="table table-striped table-bordered">';
			  		appendText=appendText + '<thead>';
			  		appendText=appendText + '<tr>';
			  		appendText=appendText + '<th style="width:15%;">Type</th>';
			  		appendText=appendText + '<th style="width:25%;">Name</th>';
			  		appendText=appendText + '<th style="width:30%;">Address</th>';
			  		appendText=appendText + '<th style="width:20%;">Addressee</th>';
			  		appendText=appendText + '<th style="width:5%;text-align: center;">Edit</th>';
			  		appendText=appendText + '<th style="width:5%;text-align: center;">Delete</th>';
			  		appendText=appendText + '</tr>';
			  		appendText=appendText + '</thead>';
			  		appendText=appendText + '<tbody>';
			  		
			  		
				  	if(commissionerVOs.length > 0){
				  		

				  		 $.each(commissionerVOs,function( index, item ){
				  			appendText=appendText + '<tr>';
				  			appendText=appendText + '<td id=cect' + item.id + ' style="width:15%;">' + item.commissionEmbassyConsultantType + '</td>';
				  			appendText=appendText + '<td id=cecn' + item.id + ' style="width:25%;">' + item.commissionEmbassyConsultantName + '</td>';
				  			appendText=appendText + '<td id=ceca' + item.id + ' style="width:30%;">' + item.commissionEmbassyConsultantAddress + '</td>';
				  			appendText=appendText + '<td id=cecad' + item.id + ' style="width:20%;">' + item.addressee + '</td>';
				  			appendText=appendText + '<td style="width:5%;text-align: center;"><img src="images/edit.png" onclick="editRecords('+ item.id +',' + country + ');"></td>';
				  			appendText=appendText + '<td style="width:5%;text-align: center;"><img src="images/cancel.png" onclick="deleteRecords('+ item.id +');"></td>';
				  			appendText=appendText + '</tr>';
				  		 });
				  		
				  		$('#availableHighCommissionerModelAppendDiv').html(appendText);
				  	 }else{
				  		$('#searchTotalAllocations_id').val("");
				  		$('#searchRemainingAllocations_id').val("");
				  		appendText=appendText + '<tr><td colspan="5" align="center">No Records Found</td></tr>';
				  		$('#availableHighCommissionerModelAppendDiv').html(appendText);
				  	 }
				  	appendText=appendText + '</tbody>';
				  	appendText=appendText + '</table>';
			 });
		}
	}
	
	function editRecords(recordId, country) {
		
		var type 	= $('#cect' + recordId +'').html();
		var name 	= $('#cecn' + recordId +'').html();
		var address = $('#ceca' + recordId +'').html();
		var addressee = $('#cecad' + recordId +'').html();

		$('#country').val(country);
		
		if(type=="High Commission"){
			$('#highcommissionTypeHC').prop('checked', true);
			$('input[name="highcommissionType"]:checked').val("HC");
			$('#addressee').attr('readonly', true);
		}
		if(type=="Embassy"){
			$('#highcommissionTypeEM').prop('checked', true);
			$('input[name="highcommissionType"]:checked').val("EM");
			$('#addressee').attr('readonly', true);
		}
		if(type=="Consulate"){
			$('#highcommissionTypeCO').prop('checked', true);
			$('input[name="highcommissionType"]:checked').val("CO");
			$('#addressee').attr('readonly', true);
		}
		if(type=="Immigration Department"){
			$('#highcommissionTypeIG').prop('checked', true);
			$('input[name="highcommissionType"]:checked').val("IG");
			$('#addressee').attr('readonly', true);
		}
		if(type=="Other"){
			$('#highcommissionTypeOT').prop('checked', true);
			$('input[name="highcommissionType"]:checked').val("OT");
			$('#addressee').attr('readonly', false);
		}

		$('#nameOfAuthority').val(name);
		$('#address').val(address);
		$('#hcId').val(recordId);
		$('#addressee').val(addressee);
	}
	
	function deleteRecords(recordId) {
		var ans=confirm('Are you sure you want to delete this record?');
		if(ans){
			$('#hcIdDelete').val(recordId);
			
			$('#deleteHighCommissionsForm').submit();
		} else {
			return false;
		}
	}
	
	function resetHighCommissions() {
		$('#country').val(0);
		$('#highcommissionTypeHC').prop('checked', true);
		$('#nameOfAuthority').val("");
		$('#address').val("");
		$('#hcId').val("");
		$('#addressee').val("");
	}
	
	function setAddressee() {
		var hcType = $('input[name="highcommissionType"]:checked').val();
		
		if(hcType=="HC") {
			$('#addressee').val("");
			$('#addressee').attr('readonly', true);
			$('#addressee').val("H.E. THE HIGH COMMISSIONER");
		}
		
		if(hcType=="EM") {
			$('#addressee').val("");
			$('#addressee').attr('readonly', true);
			$('#addressee').val("H.E. THE AMBASSADOR");
		}
		
		if(hcType=="CO") {
			$('#addressee').attr('readonly', true);
			$('#addressee').val("THE CONSUL GENERAL");
		}
		
		if(hcType=="IG") {
			$('#addressee').attr('readonly', true);
			$('#addressee').val("THE IMMIGRATION OFFICER");
		}
		
		if(hcType=="OT") {
			$('#addressee').attr('readonly', false);
			$('#addressee').val("");
		}
	}
	</script>
</body>
</html>
</s:i18n>