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

<title>Print Application List - External</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Print Application List - External</c:set>
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
						<s:form cssClass="form-horizontal" id="myForm"  theme="simple" action="searchPrintList.action" onsubmit="return validateForm()"> 
							<input type="hidden" name="searchCriteriaVO.startFrom" id="startFrom" value="0" />
							<input type="hidden" name="searchCriteriaVO.limit" id="limit" value="20" />
							<div class="col-lg-3">
								<div class="form-group">
									<div class="col-sm-2" >
										<strong><s:label cssClass="control-label bold-label">From:</s:label></strong>
				   					</div>
				   					<div class="col-sm-9">
				   						<c:set var="customStartDate">
				   							<fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="fromDate" value="${customStartDate}" id="fromDate_id" class="form-control">
				   					</div>
			   					</div>
			   				</div>
			   				<div class="col-lg-3">
			   					<div class="form-group">
									<div class="col-sm-1" >
										<strong><s:label cssClass="control-label bold-label">To:</s:label></strong>
				   					</div>
				   					<div class="col-sm-9">
				   						<c:set var="customEndDate">
				   							<fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="toDate" value="${customEndDate}" id="toDate_id" class="form-control">
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="referenceNo">Reference No :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.referenceNo" cssClass="form-control" id="referenceNo"/>
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="nicNo">NIC :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.nicNo" cssClass="form-control" id="nicNo"/>
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="pptNo">PPT :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.pptNo" cssClass="form-control" id="pptNo"/>
				   					</div>
				   					<div class="col-sm-1" >
										<div style="text-align:right;">&nbsp;</div>
				   					</div>
			   					</div>
							</div>
							
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="clearance_status">Clearance Status :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<select name="searchCriteriaVO.clearanceStatus" class="form-control" id="clearance_status">
				   							<c:choose>
				   								<c:when test="${searchCriteriaVO.clearanceStatus=='CM'}">
				   									<option value="PN">Pending</option>
				   									<option value="CM" selected="selected">Completed</option>
				   								</c:when>
				   								<c:otherwise>
				   									<option value="PN" selected="selected">Pending</option>
				   									<option value="CM">Completed</option>
				   								</c:otherwise>
				   							</c:choose>
				   							
				   						</select>
				   					</div>
				   					<div class="col-sm-1" >
										<div style="text-align:right;">&nbsp;</div>
				   					</div>
			   					</div>
							</div>
							
							<div style="clear: both;"></div>
							<div class="col-lg-8">				
								<div class="form-group">
									<div class="col-sm-8" >
										<div style="text-align:right;">
											<input type="submit" value="Search" class="btn btn-primary es-buttton" id="searchReviewApplication" />
											<input type="button" value="Clear" class="btn btn-primary es-buttton" id="clearReviewApplication" />
										</div>
				   					</div>
			   					</div>
							</div>
							
							</s:form>
							<div style="clear: both;"></div>
							<div class="form-group">
							
							<c:if test="${! empty applicationList}">							
									<div class="col-lg-12" style="max-width: 100%;">	
										<div class="table-responsive" style="max-width: 100%;">
											<table class="table table-bordered" style="max-width: 100%;">
												<thead>
													<tr>
														<th class="text-center">
															<input type="checkbox" id="selectAllCheckBox" />
														</th>
														<th class="text-center"><strong>Reference No</strong></th>	
														<th class="text-center"><strong>Nic No</strong></th>	
														<th class="text-center"><strong>Passport No</strong></th>
														<th class="text-center"><strong>Name</strong></th>
														<th class="text-center"><strong>DOB</strong></th>
														<th class="text-center"><strong>Address</strong></th>
														<th class="text-center"><strong>Date Recieved</strong></th>
														<th class="text-center"><strong>Date Responded</strong></th>
														<th class="text-center"><strong>Comment</strong></th>
														<th class="text-center"><strong>Clearance</strong></th>
														<th class="text-center"><strong>Cleared User</strong></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${applicationList}" var="applicationVO">
														<tr>	
															<c:choose>
																<c:when test="${applicationVO.hasAlreadyPrinted==1}">
																	<tr style="background-color: #ACD4F7;">
																</c:when>
																<c:otherwise>
																	<tr>
																</c:otherwise>														
															</c:choose>
															
															
															<td class="text-center-middle">																
																<input type="checkbox" class="selectBoxPrintApplication" name="selectedRowId" id="selectedRowId_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.applicationId}_${applicationVO.printAddressId}" />
																
																<input type="hidden" id="selectedAppId_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.applicationId}" />
																<input type="hidden" id="selectedAddressId_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.printAddressId}" />
																<input type="hidden" id="selectedReferenceNo_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.referenceNo}" />
																<input type="hidden" id="selectedNic_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.nic}" />
																<input type="hidden" id="selectedPassport_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.passport}" />
																<input type="hidden" id="selectedApplicantNameAsNic_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.applicantNameAsNic}" />
																<input type="hidden" id="selectedDateOfBirthString_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.dateOfBirthString}" />
																<input type="hidden" id="selectedCertificatePostalAddress_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.certificatePostalAddress}" />
																<input type="hidden" id="selectedDateSentForClarification_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.dateSentForClarification}" />
																<input type="hidden" id="selectedDateRecievedClarification_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.dateRecievedClarification}" />
																<input type="hidden" id="selectedExternalApprovalStatus_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.externalApprovalStatus}" />
																<input type="hidden" id="selectedExternalApprovedUser_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.externalApprovedUser}" />
																<input type="hidden" id="selectedGivenComment_${applicationVO.applicationId}_${applicationVO.printAddressId}" value="${applicationVO.givenComment}" />
																
															</td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.referenceNo}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.nic}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.passport}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.applicantNameAsNic}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.dateOfBirthString}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.certificatePostalAddress}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.dateSentForClarification}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.dateRecievedClarification}</td>
															<td class="text-center-middle" style="vertical-align: middle;text-align: left;">${applicationVO.givenComment}</td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.externalApprovalStatus}</td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.externalApprovedUser}</td>	
														</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
									</div>
									<div style="clear: both;"></div>
									
									<div id="gridButtons" style="padding: 5px;float: right;margin-right: 10px;">
									    <table class="table table-striped">
											<tr>
												<td>
													<input type="button" value="Print" class="btn btn-primary es-buttton" id="submitSelectedApplications" />
													<input type="button" value="Clear" class="btn btn-primary es-buttton" id="clearSelectedApplications" />
												</td>
											</tr>
										</table>
									</div>
									
									<form action="loadSelectedApplicationList.action" method="post" id="loadSelectedApplicationListForm">	
										
									</form>
									
							</c:if>
							
							<c:if test="${empty applicationList}">
								<div class="col-lg-12">	
									<div class="table-responsive">
										<div class="alert alert-warning">
											No Records are available!
										</div>	
									</div>
								</div>
							</c:if>
							
						</div>
						</div>
		   			</div>

		
			<div style="clear: both;"></div>
		</div>
		
		
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />

	</div>
	<script type="text/javascript" src="js/jquery.numeric.js" ></script>
	<script type="text/javascript" src="js/jquery.tmpl.min.js" ></script>
	<script language="javascript" type="text/javascript">

	$(document).ready(function() {
		initializeDateTimePickers();
		
		
		
		$('#clearReviewApplication').click(function(){
			$.datepicker._clearDate('#fromDate_id');
			$.datepicker._clearDate('#toDate_id');
			$('#pptNo').val('');
			$('#referenceNo').val('');
			$('#nicNo').val('');
		});
		
		$('#submitSelectedApplications').click(function(){
			saveRow();
		});
		
		$('#clearSelectedApplications').click(function(){
			$(".selectBoxPrintApplication").each( function () {
				$(this).attr('checked',false);
			});
		});
		
		
		$('#selectAllCheckBox').change(function(){
			var checkedStatus=$(this).is(':checked');
			$(".selectBoxPrintApplication").each( function () {
				if(checkedStatus){
					this.checked = true;
				}else{
					this.checked = false;
				}				
			});
		});
		
		
		
	});
	
	function initializeDateTimePickers(){
		$( "#fromDate_id" ).datepicker({
	      defaultDate: "+0d",
	      dateFormat:"dd/mm/yy",
	      onClose: function( selectedDate ) {
	        $( "#toDate_id" ).datepicker( "option", "minDate", selectedDate );
		}});
		
	    $( "#toDate_id" ).datepicker({
	      defaultDate: "+0d",
	      dateFormat:"dd/mm/yy",
	      onClose: function( selectedDate ) {
	        $( "#fromDate_id" ).datepicker( "option", "maxDate", selectedDate );
	    }});
	}
	
	function editAddressPolice(){
		blockUI();
	}
	
	
	function saveRow(){
		 var ans=confirm('Are you sure you want to print the selected records?');
		 if(ans){
			   var selectedCounter=0;
			   $("input[name=selectedRowId]").each( function () {
				   var checkedStatus=$(this).is(':checked');
			       if(checkedStatus){
			    	   var uniqueId=$(this).val();
			    	   var appId=$('#selectedAppId_' + uniqueId).val();
			    	   var addressId=$('#selectedAddressId_' + uniqueId).val();
			    	   var referenceNo=$('#selectedReferenceNo_' + uniqueId).val();
			    	   var nic=$('#selectedNic_' + uniqueId).val();
			    	   var passport=$('#selectedPassport_' + uniqueId).val();
			    	   var name=$('#selectedApplicantNameAsNic_' + uniqueId).val();
			    	   var dob=$('#selectedDateOfBirthString_' + uniqueId).val();
			    	   var address=$('#selectedCertificatePostalAddress_' + uniqueId).val();
			    	   var dateSent=$('#selectedDateSentForClarification_' + uniqueId).val();
			    	   var dateReceived=$('#selectedDateRecievedClarification_' + uniqueId).val();
			    	   var approvalStatus=$('#selectedExternalApprovalStatus_' + uniqueId).val();
			    	   var approvedUser=$('#selectedExternalApprovedUser_' + uniqueId).val();
			    	   var givenComment=$('#selectedGivenComment_' + uniqueId).val();
			    	   
			    	   
			    	   var applicationDisplay=new ApplicationDisplay(selectedCounter,appId, addressId, referenceNo, nic, passport, name, address, dateSent, dateReceived, approvalStatus, approvedUser, givenComment, dob);
			    	   
			    	   $("#appTemplate").tmpl(applicationDisplay).appendTo("#loadSelectedApplicationListForm");
			    	   
			    	   selectedCounter=selectedCounter + 1;
			       }
			   });
			 
			   if(selectedCounter>0){
				   $('#loadSelectedApplicationListForm').submit();
			   }else{
				   alert('Please select the applications to be prinetd');
			   }
		 }
	}
	
       
    function ApplicationDisplay(counter,appId,addressId,referenceNo,nic,passport,name,address,dateSent,dateReceived,approvalStatus,approvedUser,givenComment,dob){
    	this.counter=counter;
    	this.appId=appId;
    	this.addressId=addressId;
    	this.referenceNo=referenceNo;
    	this.nic=nic;
    	this.passport=passport;
    	this.name=name;
    	this.address=address;
    	this.dateSent=dateSent;
    	this.dateReceived=dateReceived;
    	this.approvalStatus=approvalStatus;
    	this.approvedUser=approvedUser;
    	this.givenComment=givenComment;
    	this.dob=dob;
    }
    
		
</script>


<script id="appTemplate" type="text/html">
	<input type="hidden" name="selectedList[\${counter}].applicationId" value="\${appId}" />
	<input type="hidden" name="selectedList[\${counter}].printAddressId" value="\${addressId}" />
	<input type="hidden" name="selectedList[\${counter}].referenceNo" value="\${referenceNo}" />
	<input type="hidden" name="selectedList[\${counter}].nic" value="\${nic}" />
	<input type="hidden" name="selectedList[\${counter}].passport" value="\${passport}" />
	<input type="hidden" name="selectedList[\${counter}].applicantNameAsNic" value="\${name}" />
	<input type="hidden" name="selectedList[\${counter}].certificatePostalAddress" value="\${address}" />
	<input type="hidden" name="selectedList[\${counter}].dateSentForClarification" value="\${dateSent}" />
	<input type="hidden" name="selectedList[\${counter}].dateRecievedClarification" value="\${dateReceived}" />
	<input type="hidden" name="selectedList[\${counter}].externalApprovalStatus" value="\${approvalStatus}" />
	<input type="hidden" name="selectedList[\${counter}].externalApprovedUser" value="\${approvedUser}" />   
	<input type="hidden" name="selectedList[\${counter}].givenComment" value="\${givenComment}" />   
	<input type="hidden" name="selectedList[\${counter}].dateOfBirthString" value="\${dob}" />        
</script>

	
</body>
</html>
</s:i18n>