<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<s:i18n name="lk.icta.resources.global">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Police Headquarters Clearance Certificate Issuance eServices</title>
<script type="text/javascript">
	window.history.forward(1);
	
	function Reset(){
		document.getElementById("userName").value = "";
		document.getElementById("emailId").value = "";
		document.getElementById("uword").value = "";

	}	
		var defaultColor = "#FFFFFF";
		var mandatoryColor = "#FFFFCC";
		
		/* function validateMandatoryFields(){
			document.getElementById("userName").style.background=defaultColor;
			document.getElementById("emailId").style.background=defaultColor;
			document.getElementById("uword").style.background=defaultColor;
			var stat = 0;
				if (document.getElementById("userName").value == ""){
					document.getElementById("userName").style.background=mandatoryColor;
					stat = 1;
				}
				if (document.getElementById("emailId").value == ""){
					document.getElementById("emailId").style.background=mandatoryColor;
					stat = 1;
				}
				if (document.getElementById("uword").value == ""){
					document.getElementById("uword").style.background=mandatoryColor;
					stat = 1;
				}
			
			if (stat == 1){
				alert('You have not entered all required data. Please check again');
				return false;
			}
		
	} */

		
</script>
</head>
<s:url id="RefreshCaptcha" action="RefreshCaptcha" />
	     <script type="text/javascript">
	     function resetText(){
	    		var verficationText = document.getElementById('uword').value;
	    		if(verficationText != null && verficationText.length > 0){
	    			document.getElementById('uword').value = '';
	    		}
	    	}
		function refreashCaptcha(){
		document.getElementById('capcha_id').src = '<%=request.getContextPath()%>/Captcha.jpg?' + Math.random(); 
		resetText();
		
	  }
	    </script>
<body id="bd" onload="Reset();resetText();">
     <!--main container start -->
	<div id="es-container" class="container" >
		
		<jsp:include page="../common/header.jsp" />
		
			<!-- Starting the page Title -->
		
		<div id="es-content" >
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Forgot Password</c:set>
				<jsp:include page="../common/commonPage.jsp">
				    <jsp:param name="title" value="${pageTitle}" />
				    <jsp:param name="homePage" value="RESET" />
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
			   						<s:label for="emailId" cssClass="control-label" value="E Mail"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-6">
			   						<s:textfield cssClass="form-control" name="email" id="emailId" required="true"/>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-3" >
			   						<s:label for="captcha" cssClass="control-label" value="Captcha Text"/>
			   						<span class="mandatory_field">*</span>
			   					</div>
			   					<div class="col-sm-6">
			   						<table>
										<tr>
			        						<td><s:textfield cssClass="form-control" name="uword" id="uword" size="40" maxLength="100" /></td>
			        						<td><img src="<%=request.getContextPath()%>/Captcha.jpg" border="1" id="capcha_id" height="20" width="80"/></td>
			        						<td><img style="cursor: hand" src="<%=request.getContextPath()%>/images/refresh.jpg" border="0" height="20" width="20" title="Refresh" onclick="refreashCaptcha()" /></td>
			        					</tr>
									</table>
			   					</div>
		   					</div>	
		   					<div class="form-group">
			   					<div class="col-sm-3" ></div>
			   					<div class="col-sm-6">
			   						<s:submit cssClass="btn btn-primary es-buttton" value="Save" action="resetPassword" onClick="return validateMandatoryFields();"/>
			   						<s:submit cssClass="btn btn-primary es-buttton" value="Cancel" action="login"/>
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