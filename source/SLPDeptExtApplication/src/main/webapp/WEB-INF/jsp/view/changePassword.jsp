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
				<c:set var="pageTitle">Change Password</c:set>
				<jsp:include page="../common/commonPage.jsp">
				    <jsp:param name="title" value="${pageTitle}" />
				</jsp:include>	

			<div class="middle_content">
				<s:form theme="simple" cssClass="form-horizontal" >
					<div class="col-lg-6">		
						<div class="well">
							<div class="form-group">
			   					<div class="col-sm-4" >
			   						<s:label for="email" cssClass="control-label" value="Email Address"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-8">
			   						<input type="text" class="form-control" name="email" id="email" value="<c:out value="${sessionScope.email}"/>" disabled="disabled"/>
			   					</div>
		   					</div>	  
		   					<div class="form-group">
			   					<div class="col-sm-4" >
			   						<s:label for="currPassword" cssClass="control-label" value="Current Password"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-8">
			   						<s:password cssClass="form-control" name="oldPassword" id="currPassword" required="true"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-4" >
			   						<s:label for="newPassword" cssClass="control-label" value="New Password"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-8">
			   						<s:password cssClass="form-control" name="newPassword" id="newPassword" required="true"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-4" >
			   						<s:label for="confirmNewPassword" cssClass="control-label" value="Confirm New Password"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-8">
			   						<s:password cssClass="form-control" name="confirmNewPassword" id="confirmNewPassword" required="true"/>
			   					</div>
		   					</div>
		   					<div class="form-group">
			   					<div class="col-sm-4" ></div>
			   					<div class="col-sm-6">
			   						<s:submit cssClass="btn btn-primary es-buttton" value="Submit" action="processChangePassword" onClick="return validateMandatoryFields();"/>
			   						<s:reset cssClass="btn btn-primary es-buttton" value="Reset" />
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