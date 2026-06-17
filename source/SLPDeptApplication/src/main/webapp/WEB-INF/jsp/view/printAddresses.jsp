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

<title>Print Address List</title>

</head>

<body id="bd" >

	<div id="es-container" class="container" >
		
	<jsp:include page="../common/header.jsp" /> 
			
		<div id="es-content"> 
			
			<c:set var="pageTitle"><s:text name="Print Address List"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
				<jsp:param name="title" value="${pageTitle}" />
			</jsp:include>	
			
				<s:form id="advanid" role="form" cssClass="form-horizontal"  theme="simple"> 
					<div id="form-id" class="middle_content">
						<table width="100%" border="0">
							<tr>
								<td width="100%" valign="top" align="center">
									<div class="main_body span8">
										<iframe name="preview" id="preview" style="width:1000px; height:500px;" frameborder="0" 
												src="reportfilefinder.htm?fileName=${fileName}"></iframe>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
				
			<!-- social icons -->
			<div style="clear: both;"></div>
			
		</div>	
		<jsp:include page="../common/footer.jsp" />
	</div>
	<script language="javascript" type="text/javascript">
		$(document).ready(function() {
			$("#messagesDiv").fadeIn(700).delay(7000).fadeOut(5000);	
		});
	</script>
</body>
</html>
</s:i18n>


