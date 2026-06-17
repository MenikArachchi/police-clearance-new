<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<s:i18n name="lk.icta.resources.global">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Police Headquarters Clearance Certificate Issuance eServices</title>
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
				    <jsp:param name="homePage" value="YES" />
				</jsp:include>	

			<div class="middle_content">
				<s:form theme="simple" cssClass="form-horizontal" >
					<div class="col-lg-6">		
						<div class="well">		  
		   					<div class="form-group">
			   					<div class="col-sm-3" >
			   						<s:label for="userName" cssClass="control-label" value="User Name"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-6">
			   						<s:textfield cssClass="form-control" name="userName" id="userName" required="true"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-3" >
			   						<s:label for="password" cssClass="control-label" value="Password"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-6">
			   						<s:password cssClass="form-control" name="password" id="password" required="true"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-3" ></div>
			   					<div class="col-sm-6">
			   						<s:submit cssStyle="float: right;" cssClass="btn btn-primary es-buttton" value="Login" action="authorizationAction" onClick="return validateMandatoryFields();"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-3" ></div>
			   					<div class="col-sm-6">
			   						<s:submit cssStyle="float: right;" cssClass="btn btn-primary es-buttton" value="Forgot Password" action="forgotPassword"/>
			   					</div>
		   					</div>	
		   				</div>				   
					</div>
				<div style="clear: both;"></div>
				</s:form>

			</div>	
						
	</div>
	<jsp:include page="../common/footer.jsp" />
	</div>

</body>
</html>
</s:i18n>