<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>
	<s:text name="global.error.page.500.error.heading"></s:text>
</title>

<script language="javascript" type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }	
		
</script>

</head>

<body class="oneColElsCtrHdr" onload="noBack();" onpageshow="if (event.persisted) noBack();">
<!--main container start -->
	<div id="es-container" class="container">
		
		<jsp:include page="../common/headerLogin.jsp" />
		
			<!-- Starting the page Title -->

		<div id="es-content">

			<!-- Starting the page Title -->
			
			<!-- Including the common page with title bar and help content -->
			<c:set var="pageTitle"><s:text name="internal.error.occurred.try.again.later"></s:text></c:set>
			<jsp:include page="../common/commonPage.jsp">
			    <jsp:param name="title" value="${pageTitle}" />
			</jsp:include>
					
			

      		<div>
				<div class="col-lg-12">
      				
      				<div style="clear: both;"></div>
      				
      				<s:text name="global.error.page.common_1"></s:text>&nbsp;
      				<a href="home.action"><s:text name="global.error.page.common_2"></s:text></a>&nbsp;
      				<s:text name="global.error.page.common_3"></s:text>
						
      			</div>				
			</div>
			<div style="clear: both;"></div>
			
			<br />
			
			<!-- social icons -->
			<div style="clear: both;"></div>
			<jsp:include page="../common/socialshareicons.jsp" />
			
		</div>	
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>