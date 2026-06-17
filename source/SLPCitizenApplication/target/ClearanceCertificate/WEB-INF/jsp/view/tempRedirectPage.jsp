<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>
	<s:text name="dmt.authentication.page.title"></s:text>
</title>

<script language="javascript" type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }	
		
</script>

</head>

<body class="oneColElsCtrHdr" onload="noBack();blockUI();" onpageshow="if (event.persisted) noBack();">
<!--main container start -->
	<div id="es-container" class="container">
		
<%-- 		<jsp:include page="../common/headerLogin.jsp" /> --%>
		
			<!-- Starting the page Title -->

		<div id="es-content">
		
			<!-- Including the common page with title bar and help content -->
<%-- 			<c:set var="pageTitle"><s:text name="dmt.authentication.page.title"></s:text></c:set> --%>
<%-- 			<jsp:include page="../common/commonPage.jsp"> --%>
<%-- 			    <jsp:param name="title" value="${pageTitle}" /> --%>
<%-- 			</jsp:include>	 --%>
							

      		<div>
				<div class="col-lg-12">
      				
      				<div style="clear: both;"></div>
      					
      					 <h4>Please wait...!</h4>
      					 <div id='overlay'>
							<div style="vertical-align:middle;display: table-cell;">
								<img src="images/preloaders.GIF" />
							</div>
						</div>
      				
						
      			</div>				
			</div>
			<div style="clear: both;"></div>
			
			<br />
			
			<!-- social icons -->
			<div style="clear: both;"></div>
<%-- 			<jsp:include page="../common/socialshareicons.jsp" /> --%>
		</div>	
<%-- 		<jsp:include page="../common/footer.jsp" /> --%>
	</div>
</body>
<script type="text/javascript">
function blockUI(){
	 var docHeight = $(document).height();

	   $("#overlay")
	      .height(docHeight)
	      .css({
	         'opacity' : 0.4,
	         'position': 'absolute',
	         'top': 0,
	         'left': 0,
	         'background-color': 'black',
	         'width': '100%',
	         'z-index': 5000,
	         'text-align':'center',
	         'vertical-align':'middle',
	         'display': 'table'
	      });

}

</script>
</html>