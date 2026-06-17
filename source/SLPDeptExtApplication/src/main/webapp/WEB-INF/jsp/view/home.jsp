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
			
			<div>
				<div class="col-lg-5">				
					<div class="well new_bullet">
						<ul>
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Review Application')}"> 
								<li><a href="viewReviewApplication.action" id="review_application_id"><s:text name="Review Application"/></a></li>
							</c:if>	
							<c:if test="${sessionScope.ASSIGNED_SERVICE_ID_MAP.containsValue('Review Application')}"> 
								<li><a href="viewPrintList.action" id="view_print_list"><s:text name="Print Application List"/></a></li>
							</c:if>						
						</ul>
					</div>
				</div>
			</div>
			
			<div>
				<div class="col-lg-7">	
					<div class="well new_star">
						<strong>Notifications</strong>
						<ul>							
							<li>
								<div class="col-sm-8" >
									<strong>External clearance pending</strong>
			   					</div>
			   					<div class="col-sm-4" align="right">
			   						<strong><c:out value="0"/></strong>
			   					</div>
							</li>							
						</ul>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
						
	</div>
	<jsp:include page="../common/footer.jsp" />
	</div>
	
	
	
</body>
</html>
</s:i18n>