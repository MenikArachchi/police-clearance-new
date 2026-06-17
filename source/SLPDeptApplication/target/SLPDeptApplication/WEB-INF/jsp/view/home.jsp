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

<title>Home</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
		
			<!-- Starting the page Title -->
		
		<div id="es-content" >
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Welcome to the Police Headquarters Clearance Certificate Issuance eServices</c:set>
				<jsp:include page="../common/commonPage.jsp">
				    <jsp:param name="title" value="${pageTitle}" />
				    <jsp:param name="homePage" value="HOME" />
				</jsp:include>	
			
			<c:if test="${param['msg'] == 'suc'}">
				<div class="alert alert-success"> Application Saved Successfully. </div>
			</c:if>
			
			<div>
				<div class="col-lg-5">				
					<div class="well new_bullet">
						<ul>
							<c:if test="${!(sessionScope.userRole==sessionScope.userDisplayVO.cidUser
											 || sessionScope.userRole==sessionScope.userDisplayVO.imiUser
											 	|| sessionScope.userRole==sessionScope.userDisplayVO.nicUser
											 		|| sessionScope.userRole==sessionScope.userDisplayVO.sisUser
											 			|| sessionScope.userRole==sessionScope.userDisplayVO.tidUser
											 				|| sessionScope.userRole==sessionScope.userDisplayVO.polUser)}">
								<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Application Verification')}">
									<li><a href="viewApplicationVerification.action" id="application_verification_id"><s:text name="Application Verification"/></a></li>
								</c:if>
							</c:if>
							<%-- <c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Request for Updates')}"> 
								<li><a href="#" id="request_for_updates_id"><s:text name="Request for Updates"/></a></li>
							</c:if> --%>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Add Application')}"> 
								<li><a href="application.action" id="application_id">Application</a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Police Form')}"> 
								<li><a href="searchPoliceForm.action" id="police_forms_id">Police Forms</a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Review Application')}"> 
								<li><a href="viewReviewApplication.action" id="review_application_id"><s:text name="Review Application"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Review Application')}"> 
								<li><a href="viewPrintList.action" id="view_print_list"><s:text name="Print Application List"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Certificate Issuance')}"> 
								<c:choose>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.checkingOfficerNoAdverse}">
										<li><a href="viewNoAdverseCheck.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.checkingOfficerAdverse}">
										<li><a href="viewAdverseCheck.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.oicUser}">
										<li><a href="viewOicClearence.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.aspUser}">
										<li><a href="viewAspClearence.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.dhaUser}">
										<li><a href="viewDhaClearence.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.digUser}">
										<li><a href="viewDigClearence.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:when test="${sessionScope.userType==sessionScope.userDisplayVO.postingOfficer}">
										<li><a href="viewPostingOfficer.action" id="certificate_issuance_id"><s:text name="Certificate Issuance"/></a></li>
									</c:when>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose>								
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Edit Application')}"> 
								<li><a href="loadSearchEditApplication.action" id="loadSearchEditApplication"><s:text name="Edit Application"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Daily Transaction Report')}"> 
								<li><a href="viewDailyTransactionReport.action" id="viewDailyTransactionReport"><s:text name="Daily Transaction Report"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Application Details Report')}"> 
								<li><a href="viewApplicationDetailsReport.action" id="viewApplicationDetailsReport"><s:text name="Application Details Report"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('View Application Status')}"> 
								<li><a href="searchApplicationStatus.action" id="searchApplicationStatus"><s:text name="View Application Status"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Blacklist Report')}"> 
								<li><a href="viewBlacklist.action" id="viewBlacklist"><s:text name="Blacklist"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Daily Transaction Report')}"> 
								<li><a href="showClearanceReport.action" id="clearance_report_id"><s:text name="Clearance Report"/></a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Master Files')}"> 
								<li><a href="populateMasterFiles.action" id="populateMasterFiles">Master Files</a></li>
							</c:if>
							<c:if test="${sessionScope.userType==sessionScope.userDisplayVO.oicUser}">
								<li><a href="viewLockedRecords.action" id="viewLockedRecords">View Locked Records</a></li>
							</c:if>
							<c:if test="${sessionScope.userType==sessionScope.userDisplayVO.oicUser ||
							            sessionScope.userType==sessionScope.userDisplayVO.aspUser ||
							             sessionScope.userType==sessionScope.userDisplayVO.dhaUser }">
								<li><a href="loadSearchUploadAdditionalDocs.action" id="loadSearchUploadAdditionalDocs">Upload Additional Documents</a></li>
							</c:if>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Print Application')}"> 
								<li><a href="loadSearchPrintApplication.action" id="print_application_id">Print Application</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- Notifications display only for PHQ users -->
			<c:if test="${! empty sessionScope.user.userType.name}">
			
<%-- 			<c:if test="${(sessionScope.user.userType.name=='DEPARTMENT_USER' && sessionScope.user.dept.id==1)  --%>
<%-- 			|| (sessionScope.user.userType.name=='DEPARTMENT_ADMIN' && sessionScope.user.dept.id==1)}"> --%>

		<c:if test="${sessionScope.user.dept.id==1}">			
			<div>
				<div class="col-lg-7">	
					<div class="well new_star">
						<strong>Notifications</strong>
						<ul>
							<li>
								<div class="col-sm-8" >
									<strong>Applications to be verified</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countApplicationsToBeVerified">
<%-- 			   						<strong><c:out value="${sessionScope.countApplicationsToBeVerified}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>Re-submission pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countResubmissionPending">
<%-- 			   						<strong><c:out value="${sessionScope.countResubmissionPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>Revision updates pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countRevisionUpdatesPending">
<%-- 			   						<strong><c:out value="${sessionScope.countRevisionUpdatesPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li id="externalClearanceDetailDiv">
								<div class="col-sm-8" >
									<strong>External clearance pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countExternalClearancePending">
<%-- 			   						<strong><c:out value="${sessionScope.countExternalClearancePending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>Police clearance pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right"  id="countPoliceClearancePending">
<%-- 			   						<strong><c:out value="${sessionScope.countPoliceClearancePending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>Certificate checking pending - No Adverse</strong>
			   					</div>
			   					<div class="col-sm-4" align="right"  id="countCertificateCheckingPendingNoAdverse">
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>Certificate checking pending - Adverse</strong>
			   					</div>
			   					<div class="col-sm-4" align="right"  id="countCertificateCheckingPendingAdverse">
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>OIC approval pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right"  id="countOICApprovalPending">
<%-- 			   						<strong><c:out value="${sessionScope.countOICApprovalPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>ASP approval pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countASPApprovalPending">
<%-- 			   						<strong><c:out value="${sessionScope.countASPApprovalPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>DHA approval pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countDHAApprovalPending">
<%-- 			   						<strong><c:out value="${sessionScope.countASPApprovalPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
							<li>
								<div class="col-sm-8" >
									<strong>DIG approval pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countDIGApprovalPending">
<%-- 			   						<strong><c:out value="${sessionScope.countASPApprovalPending}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
<!-- 							<li> -->
<!-- 								<div class="col-sm-8" > -->
<%-- 									<strong>Certificate printing pending</strong> --%>
<!-- 			   					</div> -->
<!-- 			   					<div class="col-sm-4" align="right" id="countCertificatePrintingPending"> -->
<%-- 			   						<strong><c:out value="${sessionScope.countCertificatePrintingPending}"/></strong> --%> 
<!-- 										<img src="images/small_preloader.gif" /> -->
<!-- 			   					</div> -->
<!-- 							</li> -->
							<li>
								<div class="col-sm-8" >
									<strong>Certificates to be posted</strong>
			   					</div>
			   					<div class="col-sm-4" align="right" id="countCertificateToBePosted">
<%-- 			   						<strong><c:out value="${sessionScope.countCertificateToBePosted}"/></strong> --%>
										<img src="images/small_preloader.gif" />
			   					</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
				<script type="text/javascript">
					$( document ).ready(function() {
						loadAllCountData();
						//setInterval(function(){ loadAllCountData(); }, (1800000));
					});
					
					
					function loadAllCountData(){
						loadCountApplicationsToBeVerified();
					    loadCountResubmissionPending();
					    loadCountRevisionUpdatesPending();
					    loadCountExternalClearancePending();
					    loadCountPoliceClearancePending();
					    loadCountInternalClearancePending();
					}
					
					function loadCountApplicationsToBeVerified(){
						$('#countApplicationsToBeVerified').html('<img src="images/small_preloader.gif" />');
						$.get('listCountApplicationsToBeVerified.action',function(data,status,xhr){
							var countApplicationsToBeVerified=parseInt(data.countApplicationsToBeVerified);
							if(isNaN(countApplicationsToBeVerified)){
								countApplicationsToBeVerified=0;
							}
							$('#countApplicationsToBeVerified').html('<strong>' + countApplicationsToBeVerified + '</strong>');
						});
						
					}
					
					function loadCountResubmissionPending(){
						$('#countResubmissionPending').html('<img src="images/small_preloader.gif" />');
						$.get('listCountResubmissionPending.action',function(data,status,xhr){
							var countResubmissionPending=parseInt(data.countResubmissionPending);
							if(isNaN(countResubmissionPending)){
								countResubmissionPending=0;
							}
							$('#countResubmissionPending').html('<strong>' + countResubmissionPending + '</strong>');
						});
						
					}
					
					function loadCountRevisionUpdatesPending(){
						$('#countRevisionUpdatesPending').html('<img src="images/small_preloader.gif" />');
						$.get('listCountRevisionUpdatesPending.action',function(data,status,xhr){
							var countRevisionUpdatesPending=parseInt(data.countRevisionUpdatesPending);
							if(isNaN(countRevisionUpdatesPending)){
								countRevisionUpdatesPending=0;
							}
							$('#countRevisionUpdatesPending').html('<strong>' + countRevisionUpdatesPending + '</strong>');
						});
						
					}
					
					function loadCountExternalClearancePending(){
						$('#countExternalClearancePending').html('<img src="images/small_preloader.gif" />');
						$.get('listCountExternalClearancePending.action',function(data,status,xhr){
							var countExternalClearancePending=parseInt(data.countExternalClearancePending);
							if(isNaN(countExternalClearancePending)){
								countExternalClearancePending=0;
							}
							$('#countExternalClearancePending').html('<strong>' + countExternalClearancePending + '</strong>');
						});
						
					}
					
					function loadCountPoliceClearancePending(){
						$('#countPoliceClearancePending').html('<img src="images/small_preloader.gif" />');
						$.get('listCountPoliceClearancePending.action',function(data,status,xhr){
							var countPoliceClearancePending=parseInt(data.countPoliceClearancePending);
							if(isNaN(countPoliceClearancePending)){
								countPoliceClearancePending=0;
							}
							$('#countPoliceClearancePending').html('<strong>' + countPoliceClearancePending + '</strong>');
						});
						
					}
					
					function loadCountInternalClearancePending(){
						$('#countCertificateCheckingPendingNoAdverse').html('<img src="images/small_preloader.gif" />');
						$('#countCertificateCheckingPendingAdverse').html('<img src="images/small_preloader.gif" />');
						$('#countOICApprovalPending').html('<img src="images/small_preloader.gif" />');
						$('#countASPApprovalPending').html('<img src="images/small_preloader.gif" />');
						$('#countDIGApprovalPending').html('<img src="images/small_preloader.gif" />');
						$('#countDHAApprovalPending').html('<img src="images/small_preloader.gif" />');
						$('#countCertificateToBePosted').html('<img src="images/small_preloader.gif" />');
						
						$.get('listCountInternalApprovalPending.action',function(data,status,xhr){
							var countCertificateCheckingPendingNoAdverse=parseInt(data.countCertificateCheckingPendingNoAdverse);
							if(isNaN(countCertificateCheckingPendingNoAdverse)){
								countCertificateCheckingPendingNoAdverse=0;
							}
							$('#countCertificateCheckingPendingNoAdverse').html('<strong>' + countCertificateCheckingPendingNoAdverse + '</strong>');
							
							var countCertificateCheckingPendingAdverse=parseInt(data.countCertificateCheckingPendingAdverse);
							if(isNaN(countCertificateCheckingPendingAdverse)){
								countCertificateCheckingPendingAdverse=0;
							}
							$('#countCertificateCheckingPendingAdverse').html('<strong>' + countCertificateCheckingPendingAdverse + '</strong>');
							
							var countOICApprovalPending=parseInt(data.countOICApprovalPending);
							if(isNaN(countOICApprovalPending)){
								countOICApprovalPending=0;
							}
							$('#countOICApprovalPending').html('<strong>' + countOICApprovalPending + '</strong>');
							
							var countASPApprovalPending=parseInt(data.countASPApprovalPending);
							if(isNaN(countASPApprovalPending)){
								countASPApprovalPending=0;
							}
							$('#countASPApprovalPending').html('<strong>' + countASPApprovalPending + '</strong>');
							
							var countDIGApprovalPending=parseInt(data.countDIGApprovalPending);
							if(isNaN(countDIGApprovalPending)){
								countDIGApprovalPending=0;
							}
							$('#countDIGApprovalPending').html('<strong>' + countDIGApprovalPending + '</strong>');
							
							var countDHAApprovalPending=parseInt(data.countDHAApprovalPending);
							if(isNaN(countDHAApprovalPending)){
								countDHAApprovalPending=0;
							}
							$('#countDHAApprovalPending').html('<strong>' + countDHAApprovalPending + '</strong>');
							
							var countCertificateToBePosted=parseInt(data.countCertificateToBePosted);
							if(isNaN(countCertificateToBePosted)){
								countCertificateToBePosted=0;
							}
							$('#countCertificateToBePosted').html('<strong>' + countCertificateToBePosted + '</strong>');
							
							
						});
						
					}
					
					 
				        
// 					function loadCountCertificatePrintingPending(){
// 						$('#countCertificatePrintingPending').html('<img src="images/small_preloader.gif" />');
// 						$.get('listCountCertificatePrintingPending.action',function(data,status,xhr){
// 							var countCertificatePrintingPending=parseInt(data.countCertificatePrintingPending);
// 							if(isNaN(countCertificatePrintingPending)){
// 								countCertificatePrintingPending=0;
// 							}
// 							$('#countCertificatePrintingPending').html('<strong>' + countCertificatePrintingPending + '</strong>');
// 						});
// 					}     
				        
					
					
					function showDetailDiv(){
						$('#externalClearanceDetailDiv').html('<img src="images/small_preloader.gif" />');
						$.get('listCountExternalClearancePendingByDepartment.action',function(data,status,xhr){
							
							var appendText='<ul>';
							
							
							var countExternalClearancePendingCid=parseInt(data.countExternalClearancePendingCid);
							if(isNaN(countExternalClearancePendingCid)){
								countExternalClearancePendingCid=0;
							}
							
							appendText=appendText + '<li>'
												  + '<div class="col-sm-8" >'
												  + '<strong>CID Clearance pending</strong>'
												  + '</div>'
												  + '<div class="col-sm-3" align="right" id="countCertificatePrintingPending">'
												  + '<strong>' + countExternalClearancePendingCid  + '</strong>'
												  + '</div>'
												  + '</li>';
							
							var countExternalClearancePendingTid=parseInt(data.countExternalClearancePendingTid);
							if(isNaN(countExternalClearancePendingTid)){
								countExternalClearancePendingTid=0;
							}
							
							appendText=appendText + '<li>'
												  + '<div class="col-sm-8" >'
												  + '<strong>TID Clearance pending</strong>'
												  + '</div>'
												  + '<div class="col-sm-3" align="right" id="countCertificatePrintingPending">'
												  + '<strong>' + countExternalClearancePendingTid  + '</strong>'
												  + '</div>'
												  + '</li>';
							  
							var countExternalClearancePendingSis=parseInt(data.countExternalClearancePendingSis);
							if(isNaN(countExternalClearancePendingSis)){
								countExternalClearancePendingSis=0;
							}
							
							appendText=appendText + '<li>'
												  + '<div class="col-sm-8" >'
												  + '<strong>SIS Clearance pending</strong>'
												  + '</div>'
												  + '<div class="col-sm-3" align="right" id="countCertificatePrintingPending">'
												  + '<strong>' + countExternalClearancePendingSis  + '</strong>'
												  + '</div>'
												  + '</li>';
							  
							var countExternalClearancePendingNic=parseInt(data.countExternalClearancePendingNic);
							if(isNaN(countExternalClearancePendingNic)){
								countExternalClearancePendingNic=0;
							}
							
							appendText=appendText + '<li>'
												  + '<div class="col-sm-8" >'
												  + '<strong>NIC Clearance pending</strong>'
												  + '</div>'
												  + '<div class="col-sm-3" align="right" id="countCertificatePrintingPending">'
												  + '<strong>' + countExternalClearancePendingNic  + '</strong>'
												  + '</div>'
												  + '</li>';
												  
							var countExternalClearancePendingImi=parseInt(data.countExternalClearancePendingImi);
							if(isNaN(countExternalClearancePendingImi)){
								countExternalClearancePendingImi=0;
							}
							
							appendText=appendText + '<li>'
												  + '<div class="col-sm-8" >'
												  + '<strong>IMI Clearance pending</strong>'
												  + '</div>'
												  + '<div class="col-sm-3" align="right" id="countCertificatePrintingPending">'
												  + '<strong>' + countExternalClearancePendingImi  + '</strong>'
												  + '</div>'
												  + '</li>';
							
							appendText=appendText + '<ul>';
							
							$('#externalClearanceDetailDiv').html(appendText);
							$('#externalClearanceDetailDiv').show();
						});
					}
					
				
				</script>
			
			</c:if>
			</c:if>			
	</div>
	<jsp:include page="../common/footer.jsp" />
	</div>
	
	
	
</body>
</html>
</s:i18n>