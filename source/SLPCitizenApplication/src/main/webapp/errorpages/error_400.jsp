<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link rel="shortcut icon" href="images/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css"  rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.css"  rel="stylesheet">
<link href="css/bootstrap.min.css"  rel="stylesheet" media="screen">
<script type="text/javascript" src="js/jquery-1.9.1.js" ></script>

<style type="text/css">
<!--
#footer {
    background: url("images/header_bg.jpg") repeat-x scroll center top rgba(0, 0, 0, 0);
}
.footer_label {
    color: #F6E7E8 !important;
}
-->
</style>

<!-- SETTING THE LOCALE -->
<c:choose>
	<c:when test="${!empty sessionScope.preferredLocale}">
		<fmt:setLocale value="${sessionScope.preferredLocale}" />
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="en" />		
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="global" var="global"/>


<title>400 Error - Bad Request</title>
</head>
<body class="oneColElsCtrHdr">
	<div id="es-container">
		<!--main container start -->
		<div id="container" class="container">
	
		<div id="es-header" class="es-header">
			<div class="top_banner">
				<div style="position: absolute;height: 100px;width:100%;">			
					<table style="position: absolute;display: table-cell;margin: 0px 0px 0px 5px;">
						<tr>
							<td><img src="images/police_logo_small.png"  alt="Sri Lanka Police"  /></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><span class="logo">Sri Lanka Police</span></td>
						</tr>
					</table>
				</div>	
				
			</div>
			<div style="clear: both;"></div>
		</div>
		<div style="clear: both;"></div>
			
			
		<div id="es-content">
			
			<div class="alert alert-error" >
				<h4><fmt:message key="global.error.page.400.error.heading" bundle="${global}"/></h4>
			</div>
		
			
			<div class="col-lg-12">			
					<div style="margin: auto;text-align: center;">
						<h5>
						 <fmt:message key="global.error.page.400.error.description" bundle="${global}"/>
						</h5>
						<h4>
							 <fmt:message key="global.error.page.common_1" bundle="${global}"/>
							 <a href="home" ><fmt:message key="global.error.page.common_2" bundle="${global}"/></a> 
							 <fmt:message key="global.error.page.common_3" bundle="${global}"/>
						</h4>
					</div>	
			</div>			
			
		
		</div>
			<div style="clear:both;"></div>
			
			<br />
			
			
			<div id="es-footer" class="es-footer">
				<div id="footer" class="help-block">
				<table class="tableclass" width="100% " border="0">
				    <tr>
				    <!--  <td>&nbsp;<a href="backToHome">home</a></td>-->
				      <td align="center">
				      	<span class="style2">
				        	<fmt:message key="global.footermsg1" bundle="${global}"/>&nbsp;&copy;&nbsp;
				        	<label class="footer_label">&nbsp;&copy;&nbsp;</label>
				        	<fmt:message key="global.footermsg1_1" bundle="${global}"/>
				      	   </span>
				      	   <div align="center"><span class="style2 footer_label">				      	
				      	 		 <fmt:message key="global.footermsg2" bundle="${global}"/></span>.
				      	   </div>
				      </td>
				    </tr>
				  </table>
			  </div>
			</div>
	       
		</div>
		
	</div>
</body>
</html>