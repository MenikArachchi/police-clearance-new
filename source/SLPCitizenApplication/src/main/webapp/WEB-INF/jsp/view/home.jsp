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

<style type="text/css">
	.new_bullets li{
	  font-weight: normal;
	}
	
	.distinct_div{
		padding-top: 3px;
		padding-bottom: 3px;
	}
	
	.well_tittle {
	    font-size: 16px;
	    font-weight: 600;
	}
	
	.well_inner_text{
		padding: 8px;
		font-weight: 400px;		
	}
	.well li{
		padding: 2px !important; 
	}
</style>
</head>

<body id="bd" onload="noBack();">
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
				
		<div id="es-content" >	
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle">Welcome to the Police Clearance Certificates Issuance e-Service Online Application</c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
				<jsp:param name="homePage" value="HOME" />
			</jsp:include>	
			
			<div style="clear: both;"></div>
			<div class="col-lg-12" style="padding-top: 5px;padding-bottom:10px;font-weight: bold;">
				Online Clearance Issuance System is another remarkable step taken by Sri Lanka Police to enhance people friendly professional service.
			</div>
			<div style="clear: both;"></div>
			<div>
				<div class="col-lg-12" >				
					
					<div class="well new_bullets">
						<div class="well_fixed_content">
							<div class="well_tittle">Application</div>
							<div>
									<div style="padding-top: 2px;">
										<div class="well_inner_text">
											<div class="distinct_div">This allows you to apply online for clearance certificates.</div>
											<div class="distinct_div">Application fee for a single application is 5000 Sri Lankan Rupees and can be paid using VISA/Master/American Express/Ecash.</div>
											<div class="distinct_div">Please read the instructions given below carefully and validate before applying.&nbsp;&nbsp;<a href="javascript:viewApplicationFullInformation()"><b><span id="applicationViewMore">View more...</span></b></a></div>
											<div id="viewApplicationFullInformationDiv" style="display: none;">
												<div class="distinct_div">When filling the clearance application form, please draw your attention for the following facts:</div>
												<div>
													<ol style="list-style-type: decimal;!important;font-weight: normal;">
														<li style="list-style-type: decimal;!important;font-weight: normal;">
															Fill the name in English block letters in the application, according to the name mentioned in the National Identity Card.
															<ul style="list-style-type: disc;!important;font-weight: normal;">
																<li style="list-style-type: disc;!important;font-weight: normal;">
																	The Police clearance report will be issued only to the name mentioned in the National Identity Card.
																</li>
															</ul>
														</li>
													    <li style="list-style-type: decimal;!important;font-weight: normal;">
													    	If a different name is being used in the passport rather than the one mentioned in the National Identity Card, it should be written in the box named “Name in the passport”.
													    </li>												    
													
														<li style="list-style-type: decimal;!important;font-weight: normal;">
															The duration of residence in Sri Lanka should only be included. (Except the period in abroad)
															<ul style="list-style-type: disc;!important;font-weight: normal;">
																<li style="list-style-type: disc;!important;font-weight: normal;">
																	The durations of residences should be mentioned correctly. (Year/Month/day)
																</li>
																<li style="list-style-type: disc;!important;font-weight: normal;">
																	Residential addresses & the relevant Police areas should be mentioned correctly.
																</li>
															</ul>
															<b><span style="color:#ff0000;">***</span> Important:</b> - Providing accurate information on above will enable to issue the clearance certificate within 14 working days.
														</li>
													    <li style="list-style-type: decimal;!important;font-weight: normal;">
													    	Only a relation in Sri Lanka, whose name has been mentioned in the application which was fulfilled by the applicants in abroad, can be submitted to the office for accepting clearance applications, situated at Olcort Mawatha, Colombo 11.
													    </li>												    
													</ol>
												</div>
												<div class="distinct_div">
													<b>N.B.</b>
													<div class="distinct_div">Renew your National Identity Card if it is not clear.</div>
													<div class="distinct_div">Please kindly notice that the clearance report is issued by the Director Headquarters Administration, based on the background investigation reports of you.</div>
												</div>
												<div>
													<ul style="list-style-type: disc;!important;font-weight: normal;">
														<li style="list-style-type: disc;!important;font-weight: normal;">
															The Police Clearance report is issued to officers in active service in Police, Tri forces, Civil Security force and coastal security force, only through the written permission of licensed officers.
														</li>
													</ul>
												</div>
												</div>
											</div>
									</div>
								</div>									
							</div>
							<div class="well_fixed_bottom" style="float: right;">
									<button class="btn btn-primary submit es-submit" type="button" onclick="loadEntryPage('APPLICATION')">
											&nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
									</button>
								</div>
								<div style="clear: both;"></div>
							
						<div style="clear: both;"></div>			
					</div>
				</div>
				
				<div style="clear: both;"></div>
				
				<div class="col-lg-4">		
					<div class="well new_bullets">
							<div class="well_fixed_content" style="min-height: 200px;">
								<div class="well_tittle">Application Status Check</div>
								<div>
										<div style="padding-top: 2px;">
											<div class="well_inner_text">
												<div class="distinct_div">To check the present status of a clearance application or for any inquiry.</div>
												<div class="distinct_div">Facilities are provided to applicants to check the present status of his/her application.</div>												
											</div>
										</div>
								</div>
							</div>
							<div class="well_fixed_bottom" style="float: right;">
								<button class="btn btn-primary submit es-submit" type="button" onclick="loadCheckStatusOfApplication()">
										&nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
								</button>
							</div>
							<div style="clear: both;"></div>
					</div>
				</div>
				
				<div class="col-lg-4">		
					<div class="well new_bullets">
							<div class="well_fixed_content" style="min-height: 200px;">
								<div class="well_tittle">Request For Clarification</div>
								<div>
										<div style="padding-top: 2px;">
											<div class="well_inner_text">
												<div class="distinct_div">To update following information.</div>
												<div class="distinct_div">
													<ul style="list-style-type: disc;!important;font-weight: normal;">
														<li style="list-style-type: disc;!important;font-weight: normal;">
															Re submit NIC copy
														</li>
														<li style="list-style-type: disc;!important;font-weight: normal;">
															Re submit passport copy
														</li>
														<li style="list-style-type: disc;!important;font-weight: normal;">
															Verify Name
														</li>
														<li style="list-style-type: disc;!important;font-weight: normal;">
															Verify Date of Birth
														</li>
													</ul>
												</div>												
											</div>
										</div>
								</div>
							</div>
							<div class="well_fixed_bottom" style="float: right;">
								<button class="btn btn-primary submit es-submit" type="button" onclick="loadEntryPage('CLARIFICATION')">
										&nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
								</button>
							</div>
							<div style="clear: both;"></div>
					</div>
				</div>
				
				<div class="col-lg-4">		
					<div class="well new_bullets">
							<div class="well_fixed_content" style="min-height: 200px;">
								<div class="well_tittle">Clearance Certificate Verification</div>
								<div>
										<div style="padding-top: 2px;">
											<div class="well_inner_text">
												<div class="distinct_div">To verify an issued Clearance Certificate.</div>
											</div>
										</div>
								</div>
							</div>
							<div class="well_fixed_bottom" style="float: right;">
								<button class="btn btn-primary submit es-submit" type="button" onclick="loadClearanceCertificateVerification()">
										&nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
								</button>
							</div>
							<div style="clear: both;"></div>
					</div>
				</div>
				
			</div>
			<div style="clear: both;"></div>
			
			
		  	</div>

			<form name="dummyForm" id="dummyForm" action="viewOAuthLogin.action"></form>		
			<s:form name="index" id="index" action="indexOauth" method="post" windowState="maximized">
			</s:form>
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />
			<div style="clear: both;"></div>
			<jsp:include page="../common/footer.jsp" />					
		</div>
		
		<div style="display: none;">
			<s:form theme="simple" id="applicationAction" method="get" action="application.action"></s:form>	
			<s:form theme="simple" id="requestClarificationAction" method="get" action="displayRequestForUpdateForm.action"></s:form>	
			<s:form theme="simple" id="tempRedirectPageAction" method="get" action="tempredirectpage.action"></s:form>	
			<s:form theme="simple" id="checkAuthAction" method="get" action="checkAuthentication.action"></s:form>	
			<s:form theme="simple" id="checkLoggedInStatusAction" method="get" action="checkLoggedInStatus.action"></s:form>	
			<s:form theme="simple" id="loadHomeData" method="get" action="loadHomeData.action"></s:form>	
			<s:form theme="simple" id="checkApplicationStatus" method="get" action="checkApplicationStatus.action"></s:form>
			<s:form theme="simple" id="clearanceCertificateVerification" method="get" action="clearanceCertificateVerification.action"></s:form>	
		</div>
		
		
</body>
</html>
</s:i18n>

<script type="text/javascript" src="js/oauth/common.js" ></script>
<script language="javascript" type="text/javascript">

function viewApplicationFullInformation(){
	$( "#viewApplicationFullInformationDiv" ).slideToggle( "slow", function() {
		if($('#viewApplicationFullInformationDiv').is(":visible")){
			$('#applicationViewMore').html('View less...');
		}else{
			$('#applicationViewMore').html('View more...');
		}
    });
}

function loadClearanceCertificateVerification(){
	window.location=$('#clearanceCertificateVerification').attr('action');
}

var timeVar;
window.history.forward();
function noBack() { window.history.forward(); }	

var loginOpened = false;

function loadCheckStatusOfApplication(){
	window.location=$('#checkApplicationStatus').attr('action');
}

function loadEntryPage(clientRequestType){
    var w = window.open( ($('#tempRedirectPageAction').attr('action')),"OAuth",'width=650, height=520, toolbar=no, location=no, directories=no, status=no, menubar=no');
    //loginOpened = true;
	$.get(($('#checkAuthAction').attr('action')),{clientRequestType:clientRequestType},function(data) {
		 var checkAuthenticationStatus=data.checkAuthenticationStatus;
		 if(checkAuthenticationStatus=='NO'){
			// checkLogInStatus();
			<s:if test='%{#session.EMAIL == null || #session.EMAIL == ""}'>
			 	w.location = document.getElementById("dummyForm").action;
			 	if (window.focus) {
                     w.focus();
                }
			 	timeVar=setTimeout(loginChecker, 1000);
			</s:if>
		 }else{
			 if(clientRequestType=='APPLICATION'){
				 w.close();
				 window.location=$('#applicationAction').attr('action');
			 }else{
				 w.close();
				 window.location=$('#requestClarificationAction').attr('action');
			 }
		 }
	});
	return false;
}

function viewOAuthWindow() {	   
    <s:if test='%{#session.EMAIL == null || #session.EMAIL == ""}'>
            loginOpened = true;
            
            /* var loginWindow=window.open('viewOAuthLogin.action','<s:text name="dmt.login.page.terms.and.conditions"></s:text>','width=900,scrollbars=yes');
            loginWindow.focus(); */
            newwindow = window.open(document.getElementById("dummyForm").action, "OAuth",'width=650, height=520, toolbar=no, location=no, directories=no, status=no, menubar=no');
            if (window.focus) {
                newwindow.focus();
            }
    </s:if>
 }

function checkLogInStatus() {
   viewOAuthWindow();
   timeVar=setTimeout(loginChecker, 1000);
}


function loginChecker() {
	$.ajax(
		{
			type: "GET",
			url: $('#checkLoggedInStatusAction').attr('action')
		}
	).done(
			function(data) {							
				var success = false;
				try {
					var jsonData = JSON.parse(data);
					if (jsonData.result == "done") {
						success = true;
					}else{
						if (jsonData.loginAttemp == "FAILED") {									
							window.clearTimeout(timeVar);
						}else{
							timeVar=setTimeout(loginChecker, 1000);
						}
					}
				} catch (e) {
					var subResult = data.indexOf("done");
					if (subResult != -1) {
						success = true;
					}else{
						var subResultOne = data.indexOf("FAILED");
						if (subResultOne != -1) {									
							window.clearTimeout(timeVar);
						}else{
							timeVar=setTimeout(loginChecker, 1000);
						}
					}
				}
				if (success) {
					$("#index").submit();
				} 
			}
	);
}

/* Following is for handling the user logout with the OAuth provider logout. */
var logoutPrompt = true;
var providerLogoutUrl = '<s:property value="#session.LOGOUT_URL" />';
providerLogoutUrl = providerLogoutUrl.replace("&amp;","&");

function logoutProvider() {
	if (logoutPrompt) {
		if (confirm("<s:text name='oauth.provider.logout.question' /> (<s:property value='#session.OAUTH_PROVIDER' />) ?")) {
			var newWindow = window.open(providerLogoutUrl, "LogOut Provider", "width=800, height=600");
			if (window.focus) {
				newWindow.focus();
			}
		}
		logoutPrompt = false;	
	}
	
	var logOutLink = document.getElementById("logOutLink");
	logOutLink.href = document.getElementById("logoutUrlAnchor").href;
	logOutLink.click();
	
	return true;
}

</script>
