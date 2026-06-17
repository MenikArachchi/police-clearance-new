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

<title>Review Application</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Internal - Posting Officer</c:set>
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
						<c:set var="submitUrl">searchPostingOfficer.action</c:set>
						<c:set var="includeChekBox">YES</c:set>
						<c:set var="checkboxMessage">
							Note :- Select the check box above and click 'Search' to filter the applications on your queue. 							
						</c:set>
						<c:set var="checkboxLabel">
							Display only pending approval list 							
						</c:set>
						<jsp:include page="../common/clearencecommonsearch.jsp">
						    <jsp:param name="formSubmitUrl" value="${submitUrl}" />
						    <jsp:param name="includeChekBox" value="${includeChekBox}" />
						    <jsp:param name="checkboxMessage" value="${checkboxMessage}" />
						    <jsp:param name="checkboxLabel" value="${checkboxLabel}" />
						</jsp:include>
						
							<div style="clear: both;"></div>
							<div class="form-group">
							
							<c:if test="${! empty applicationList && userType==userTypeDisplayVO.postingOfficer}">							
									<div class="col-lg-13" style="max-width: 100%;">	
										<div class="table-responsive" style="max-width: 100%;">
											<table class="table table-bordered" style="max-width: 100%;">
												<thead>
													<tr>
													
														<th class="text-center"><strong>Select</strong></th>
														<th class="text-center"><strong>Application Date</strong></th>
														<th class="text-center"><strong>Reference</strong></th>	
														<th class="text-center"><strong>Current NIC No</strong></th>
														<th class="text-center"><strong>Passport No</strong></th>
														<th class="text-center" style="width: 20%"><strong>Name</strong></th>
														<th class="text-center" style="width: 35%"><strong>Address</strong></th>
														
														<th class="text-center" style="width: 15%"><strong>Certificate Serial #</strong></th>														
														<th class="text-center"  style="width: 15%"><strong>Post/Delivered Details</strong></th>															
														<th class="text-center"><strong>Save</strong></th>	
														<th class="text-center"><strong>Print</strong></th>											
													</tr>
												</thead>
												<tbody>
													<c:set var="canAnyAddressBePrinted">0</c:set>
													<c:forEach items="${applicationList}" var="applicationVO">
																					
															<c:choose>
																<c:when test="${applicationVO.hasGreenChanneled==1 || applicationClearanceStatus=='GC'}">
																		<tr style="background-color: #7DFB7F;">
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${applicationVO.hasAnyBlacklitedRecord==1}">
																			<tr style="background-color: #F9C5C5;">
																		</c:when>
																		<c:otherwise>
																			<c:choose>
																				<c:when test="${applicationVO.hasAnyAdverseRecord==1}">
																					<tr style="background-color: #FBE199;">
																				</c:when>
																				<c:otherwise>
																					<tr>
																				</c:otherwise>
																			</c:choose>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
															
																<td class="text-center" style="vertical-align: middle;">
																	<input type="hidden" id="constructedComment_${applicationVO.applicationId}" value="${applicationVO.constructedComment}" />
																	<input type="hidden" id="commentAvailable_${applicationVO.applicationId}" value='<c:out escapeXml="false"  value="${applicationVO.commentAvailable}"></c:out>' />
																	<input type="hidden" id="hiddenVersionId_${applicationVO.applicationId}" value="${applicationVO.versionId}" />
																	
																	
																	<c:choose>
																		<c:when test="${applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/lock.png"  alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="cancelEdit(${applicationVO.applicationId})" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/unlock.png" alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="editRow(${applicationVO.applicationId},'${applicationVO.referenceNo}')" />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.updatedDateTime}</td>
																<td class="text-center" style="vertical-align: middle;">
																	<a href="viewApplicationByReferenceNo.action?referenceNo=${applicationVO.referenceNo}" >
																		${applicationVO.referenceNo}
																	</a> 
																</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.currentNicNo}</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.passport}</td>
																<td style="vertical-align: middle;">${applicationVO.applicantName}</td>
																<td style="vertical-align: middle;" class="wrapword">${applicationVO.certificatePostalAddress}</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																	<input type="text" readonly="readonly" disabled="disabled" class="form-control" value="${applicationVO.certificateSerialNo}" id="certificateCerialNo_${applicationVO.applicationId}" name="certificateCerialNo_${applicationVO.applicationId}" />
																</td>
																
																<td  class="text-center" style="vertical-align: middle;width: 25%;">
																	<c:choose>
																		<c:when test="${applicationVO.canEditRegPost==1 && applicationVO.hasCurrentUserLocked == 1}">
																			<input type="text" class="form-control" value="${applicationVO.regPostNo}" id="registeredPostNo_${applicationVO.applicationId}" name="registeredPostNo_${applicationVO.applicationId}" />
																		</c:when>
																		<c:otherwise>
																			<input type="text" class="form-control" readonly="readonly" disabled="disabled" value="${applicationVO.regPostNo}"  id="registeredPostNo_${applicationVO.applicationId}" name="registeredPostNo_${applicationVO.applicationId}" />				
																		</c:otherwise>												
																	</c:choose> 																
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">															
																	<c:choose>
																		<c:when test="${applicationVO.canEditRegPost==1 && applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button" onclick="saveRow(${applicationVO.applicationId})" />												
																		</c:when>
																		<c:otherwise>
																			<img src="images/save.png" style="width: 20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button disabled_image" />					
																		</c:otherwise>												
																	</c:choose> 								
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.canPrintAddress==1}">
																			<c:set var="canAnyAddressBePrinted">1</c:set>
																			<input type="checkbox" id="printAddress_${applicationVO.applicationId}" value="${applicationVO.applicationId}" name="printAddressId" />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" id="printAddress_${applicationVO.applicationId}" readonly="readonly" disabled="disabled" />
																		</c:otherwise>
																	</c:choose>																			
																</td>
																
															</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
										
										<div style="clear: both;"></div>
										<br />
									</div>
								
								<div style="margin: 10px">
									<div class="col-lg-7">&nbsp;</div>
									<div class="col-lg-5" style="text-align: right;">
										<c:choose>
											<c:when test="${canAnyAddressBePrinted ==1}">
												<form action="#" id="printAddressSubmitForm" style="display: inline;">
													<div id="hiddenInputAppendDiv"></div>
													<input type="button" onclick="return validatePostPrintForm()" value="Print Post List" class="btn btn-primary es-buttton">
													<input type="button" onclick="return validateAddressPrintForm()" value="Print Addresses" class="btn btn-primary es-buttton">
													<input type="button" value="Refresh" onclick="refreshPage()" class="btn btn-primary es-buttton" style="display: inline;">
												</form>												
											</c:when>
											<c:otherwise>
												<input type="button" onclick="return false" disabled="disabled" value="Print Post List" class="btn btn-primary es-buttton" style="display: inline;">
												<input type="button" onclick="return false" disabled="disabled" value="Print Addresses" class="btn btn-primary es-buttton" style="display: inline;">
												<input type="button" value="Refresh" onclick="refreshPage()" class="btn btn-primary es-buttton" style="display: inline;">
											</c:otherwise>
										</c:choose>	
																				
									</div>
								</div>
								<div style="clear: both;"></div>
								<br />
									
							
								<!-- including Grid Butons -->
								<jsp:include page="../common/clearancegridbuttons.jsp" />
							
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
		
		
		
		<div style="display: none;" id="hiddenMainForm">
			<form action="updateRegisteredPostNo.action" method="post" id="hiddenFormUpdateRegisteredPostNo" onsubmit="return validateFormRegForm()">
				<input type="hidden" name="clearenceVO.applicationId" readonly="readonly" id="hidden_main_applicationId" value="0" />
				<input type="hidden" name="clearenceVO.registeredPostNo" readonly="readonly" id="hidden_main_regPostNo" value="0" />
				<input type="hidden" name="clearenceVO.versionId" readonly="readonly" id="hidden_main_versionId" value="0" />
				
				<input type="hidden" name="fromDate" readonly="readonly" id="hidden_main_fromDate"/>
				<input type="hidden" name="toDate" readonly="readonly" id="hidden_main_toDate" />
				<input type="hidden" name="searchCriteriaVO.clearenceStatus" readonly="readonly" id="hidden_main_clearenceStatus" />
				<input type="hidden" name="searchCriteriaVO.referenceNo" readonly="readonly" id="hidden_main_referenceNo" />
				
				<input type="hidden" name="searchCriteriaVO.maxId" id="hidden_main_maxId" />
				<input type="hidden" name="searchCriteriaVO.limit" id="hidden_main_limit"/>
				<input type="hidden" name="searchCriteriaVO.defaultViewFromUi" id="hidden_main_defaultViewFromUi" />
				<input type="hidden" name="searchCriteriaVO.defaultView" id="hidden_main_defaultView" />
				<input type="hidden" name="searchCriteriaVO.currentPage" id="hidden_main_currentPage"/>
				<input type="hidden" name="searchCriteriaVO.lockedRefresh" id="hidden_main_lockedRefresh"/>
				
			</form>	
		</div>
		
		<!-- including common popups -->
		<jsp:include page="../common/clearencecommonpopups.jsp" />
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />

	</div>
	<script type="text/javascript" src="js/jquery.numeric.js" ></script>
	<script language="javascript" type="text/javascript">
	
	
	function refreshPage(){
		var conf=confirm('Are you sure you want to refersh the page?');
		if(conf){
			$('#myForm').submit();
		}
	}
	

	function saveRow(applicationId){
		var regPostNo=$('#registeredPostNo_' + applicationId).val();
		var versionId=parseInt($('#hiddenVersionId_' + applicationId).val());
		var fromDate=$('#fromDate_id').val();
		var toDate=$('#toDate_id').val(); 
		var referenceNo=$('#referenceNo').val();
		var clearenceStatus=$('#clearenceStatus').val();
		$('#hidden_main_applicationId').val(applicationId);
		$('#hidden_main_regPostNo').val(regPostNo);
		$('#hidden_main_versionId').val(versionId);
		
		
		$('#hidden_main_fromDate').val(fromDate);
		$('#hidden_main_toDate').val(toDate);
		$('#hidden_main_clearenceStatus').val(clearenceStatus);
		$('#hidden_main_referenceNo').val(referenceNo);
		
		$('#hidden_main_maxId').val($('#maxId').val());
		$('#hidden_main_limit').val($('#limit').val());
		$('#hidden_main_defaultViewFromUi').val($('#defaultViewFromUi').val());
		$('#hidden_main_defaultView').val($('#defaultView').val());
		$('#hidden_main_currentPage').val($('#currentPage').val());
		$('#hidden_main_lockedRefresh').val($('#lockedRefresh').val());
		
		
		$('#hiddenFormUpdateRegisteredPostNo').submit();
		
	}
	
	
	
	function validateFormRegForm(){
		var returnValue=true;
		
		var regPostNo=$.trim($('#hidden_main_regPostNo').val());
		
	     if((regPostNo=='' || regPostNo==null || regPostNo=='undefined')){
				alert('Please enter the registered post no!');
				returnValue=false;
		}			
		 
		 if(returnValue){
			 var conf=confirm('Are you sure you want to save this record?');
			 if(!(conf)){
				 returnValue=false;
			 }
		 }
		
		 return returnValue;
	}
	
	function editRow(applicationId,referenceNo){
		 blockUI();
		 $.get('checkAndLockClearenceRecord',{applicationId:applicationId},function(data){
			  var recordLockstatus=data.recordLockstatus;
			  var userName=data.lockedUserName;
			  if(recordLockstatus=='NO_RECORDS_TO_LOCK'){
				  alert('Internal Error!');
			  }else if(recordLockstatus=='RECORD_IS_LOCKED_BY_ANOTHER_USER'){
				  alert('Sorry, this record is already locked by ' + userName + '!');				  
			  }else if(recordLockstatus=='ONE_RECORD_IS_ALREADY_LOCKED'){
				  alert('Sorry, you have already locked another record!');
			  }else if(recordLockstatus=='ERROR'){
				  alert('Internal Error!');
			  }else if(recordLockstatus=='SUCCESS'){
				  var defaultView=$('#defaultView').val();
				  if(defaultView==true || defaultView=='true'){
					  $('#defaultViewFromUi').val(1);
				  }				  
				  $('#referenceNo').val(referenceNo);
				  $('#lockedRefresh').val(1);
				  $('#maxId').val(0);
				  $('#myForm').submit();
			  }			  
			  unBlockUI();
		 });
	 }
	
	
	 
	 function cancelEdit(applicationId){
		 blockUI();
		 $.get('checkAndRemoveLockClearence',{applicationId:applicationId},function(data){
			  var recordLockstatus=data.recordLockstatus;				 		
			  if(recordLockstatus=='SUCCESS'){
				  var defaultView=$('#defaultView').val();
				  if(defaultView==true || defaultView=='true'){
					  $('#defaultViewFromUi').val(1);
				  }	
				  $('#myForm').submit();
			  }			  
			  unBlockUI();
		 });
	 }
	  
	 
	 function validateAddressPrintForm(){
			var oneAvailable=0;
			$('#hiddenInputAppendDiv').html('');
			$('input[name="printAddressId"]:checked').each(function() {
			   var applicationId=parseInt(this.value);
			   if(applicationId>0){
				   oneAvailable=1;
				   $('#hiddenInputAppendDiv').append('<input type="hidden" value="' + applicationId + '" name="applicationIdList" />');
			   }
			});
			if(oneAvailable>0){
				var conf=confirm('Are you sure you want to print the selected addresses?');
				if(conf){
					$('#printAddressSubmitForm').attr('action','printAddresses.action');
					$('#printAddressSubmitForm').submit();
				}else{
					return false;
				}
			}else{
				alert('Please select the addresses to be printed!');
				return false;
			}
			
		}
	 
	 
	 function validatePostPrintForm(){
			var oneAvailable=0;
			$('#hiddenInputAppendDiv').html('');
			$('input[name="printAddressId"]:checked').each(function() {
			   var applicationId=parseInt(this.value);
			   if(applicationId>0){
				   oneAvailable=1;
				   $('#hiddenInputAppendDiv').append('<input type="hidden" value="' + applicationId + '" name="applicationIdList" />');
			   }
			});
			if(oneAvailable>0){
				var conf=confirm('Are you sure you want to print the selected addresses?');
				if(conf){
					$('#printAddressSubmitForm').attr('action','printPostList.action');
					$('#printAddressSubmitForm').submit();
				}else{
					return false;
				}
			}else{
				alert('Please select the addresses to be printed!');
				return false;
			}
			
		}
	 
	  

	</script>
</body>
</html>
</s:i18n>