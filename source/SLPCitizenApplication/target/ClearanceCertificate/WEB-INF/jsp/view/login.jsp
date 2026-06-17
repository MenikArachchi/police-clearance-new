<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common/css.jspf"  %>
<%@ include file="/WEB-INF/jsp/common/javascript.jspf"  %>


<s:i18n name="lk.icta.resources.global">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description=" content="Welcome to the Wildlife Circuit Bungalows Reservation eService offered by the Department of Wildlife Conservation - Sri Lanka. Department of Wildlife has several Wildlife Bungalows on the wildlife sanctuaries and parks under their management. These facilities are available for the public to be reserved and used. Make Reservation Online,Check Reservation Status"/>

<noscript>
	<link rel="stylesheet" href="css/oauth/noscript.css">
</noscript>
<link href="css/oauth/reset.css" rel="stylesheet" />
<link href="css/oauth/erl_styles.css" rel="stylesheet" media="screen" />
<link href="css/oauth/desktop.css" rel="stylesheet" />
<link href="css/oauth/tablet.css" media="screen and (max-width: 960px)"	rel="stylesheet" />
<link href="css/oauth/mobile.css" media="screen and (max-width: 649px)"	rel="stylesheet" />
<link href="css/oauth/framework.css" rel="stylesheet" media="screen" />
<link href="css/oauth/iframe.theme.css" rel="stylesheet" media="screen" />

<link href="css/oauth/jquery-ui.css" rel="stylesheet" media="screen" />
<!-- shortcut icon -->
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"	href="images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114" href="#">

<%-- <script	src="${pageContext.request.contextPath}/js/oauth/jquery-2.0.3.min.js"	type="text/javascript"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/oauth/jquery-ui.min.js"	type="text/javascript"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/oauth/common.js"	type="text/javascript"></script> --%>

<script type="text/javascript" src="js/oauth/jquery-2.0.3.min.js" ></script>
<script type="text/javascript" src="js/oauth/jquery-ui.min.js" ></script>
<script type="text/javascript" src="js/oauth/common.js" ></script>


<title>
	<s:text name="dmt.home.title"></s:text>
</title>

<script language="javascript" type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }	
		
		
		
		
		var loginOpened = false;

		$(document).ready(function(){
		    $.ajaxSetup({cache: false});
		});
		
		 function viewOAuthWindow() {	   
	         <s:if test='%{#session.EMAIL == null || #session.EMAIL == ""}'>
	                 loginOpened = true;
	                 
	                 /* var loginWindow=window.open('viewOAuthLogin.action','<s:text name="dmt.login.page.terms.and.conditions"></s:text>','width=900,scrollbars=yes');
	                 loginWindow.focus(); */
	                 newwindow = window.open(document.getElementById("dummyForm").action, "OAuth",'width=900,scrollbars=yes, toolbar=no, location=no, directories=no, status=no, menubar=no');
	                 if (window.focus) {
	                     newwindow.focus();
	                 }
	         </s:if>
	         }
		 
		function checkLogInStatus() {
	        viewOAuthWindow();
	        setTimeout(loginChecker, 1000);
	    }
		
		
		 function loginChecker() {
	     	$.ajax(
	     		{
	     			type: "GET",
	     			url: "checkLoggedInStatus.action"
	     		}
	     	).done(
					function(data) {
						var success = false;
						try {
							var jsonData = JSON.parse(data);
							if (jsonData.result == "done") {
								success = true;
							}
						} catch (e) {
							var subResult = data.indexOf("done");
							if (subResult != -1) {
								success = true;
							}
						}
						if (success) {
							$("#index").submit();
						} else {
							setTimeout(loginChecker, 1000);
						}
					}
	     	);
	     }
		 
		 function viewPopup(url, title) {
	     	window.open(url, title, "width=500, height=450");
	     	return false;
	     }
</script>

</head>

<body class="oneColElsCtrHdr" onload="noBack();" onpageshow="if (event.persisted) noBack();">
<!--main container start -->
	<div id="es-container" class="container">
		<form name="dummyForm" id="dummyForm" action="viewOAuthLogin.action"></form>
		
		<jsp:include page="../common/headerLogin.jsp" />
		
			<!-- Starting the page Title -->

		<div id="es-content">
		<s:form name="index" id="index" action="indexOauth" method="post" windowState="maximized">
		
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle"><s:text name="dmt.login.page.title"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
			    <jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	

			<div class="clear-float">
						<div class="es-errormsg">
							<s:fielderror cssErrorClass="errorMessage" />
						</div>
						<div class="es-errormsg">
							<s:actionerror cssErrorClass="errorMessage" />
						</div>
						<div class="es-errormsg">
							<s:actionmessage cssErrorClass="errorMessage" />
						</div>
					</div>			

      		<div>
				<div class="col-lg-12">
      				
      				<div style="clear: both;"></div>
      				<div class="well">
      					
      					<div class="login_home_text es-label"><s:text name="dmt.login.pagewelcome.text.row.one"></s:text></div>
	      				<div class="login_home_text es-label"><s:text name="dmt.login.pagewelcome.text.row.two"></s:text></div>
	      				<div class="login_home_text es-label"><s:text name="dmt.login.pagewelcome.text.row.three"></s:text></div>   
      				
						<div class="button-row">
						    <input type="button" class="es-button" id="btnLogin" name="btnLogin" 
						    		value="<s:text name='global.button.text.login'/>" onClick="checkLogInStatus();" />
						</div>
					</div>		
						
      			</div>				
			</div>
			<div style="clear: both;"></div>
			
			<br />
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />
			
			</s:form>
		</div>	
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html></s:i18n>