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

<title>Clearance Report</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Clearance Report</c:set>
				<jsp:include page="../common/commonPage.jsp">
				    <jsp:param name="title" value="${pageTitle}" />
				</jsp:include>	
				
				<div id="messagesDiv" style="margin:2px 0px;">
					<s:if test="customErrorMessage != null">
						<div class="alert alert-error">
							<s:property value="customErrorMessage" escapeHtml="false" />
						</div>
					</s:if>
				</div>

				<div class="middle_content">
					<div>
						<s:form id="clearance_report_form_id" cssClass="form-horizontal"  theme="simple" action="" onsubmit="return validateForm()"> 
							
							<div class="col-lg-4">
								<div class="form-group">
									<div class="col-sm-5" >
										<strong><s:label cssClass="control-label bold-label">From</s:label></strong>
				   					</div>
				   					<div class="col-sm-6">
				   						<c:set var="customStartDate">
				   							<fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="fromDate" value="${customStartDate}" id="fromDate_id" class="form-control">
				   					</div>
			   					</div>
			   				</div>
			   				<div class="col-lg-4">
			   					<div class="form-group">
									<div class="col-sm-2" >
										<strong><s:label cssClass="control-label bold-label">To</s:label></strong>
				   					</div>
				   					<div class="col-sm-6">
				   						<c:set var="customEndDate">
				   							<fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="toDate" value="${customEndDate}" id="toDate_id" class="form-control">
				   					</div>
			   					</div>
			   					<br>
			   					<div class="form-group">
									<div class="col-sm-2" ></div>
				   					<div class="col-sm-6"><div style="text-align:right;">
				   						<s:submit key="Search" action="loadClearanceReport" onclick="" cssClass="btn btn-primary es-buttton" 
							   							id="btn_search_data_id"></s:submit></div>
				   					</div>
			   					</div>
			   					
			   					
											
								
							</div>
							
							
							<br><br />
							<br><br />
							<br><br />
							
							<div style="clear:both;"></div>
							<div class="col-lg-12" style="padding-left:0px; padding-right:0px;max-width: 100%;">
								<div class="table-responsive" style="max-width: 100%;">
								<table class="table table-bordered" style="max-width: 100%;">
									<thead>
										<tr>
											<th class="text-center"><strong>Application Ref No</strong></th>
											<th class="text-center"><strong>Name</strong></th>
											<th class="text-center"><strong>Curernt NIC No</strong></th>
											<th class="text-center"><strong>Passport No</strong></th>
											<th class="text-center"><strong>Address</strong></th>
											<th class="text-center"><strong>Date of Birth</strong></th>
										</tr>
									</thead>
									<tbody >
										<c:forEach items="${applicationList}" var="applicationVO">
											<c:choose>
												<c:when test="${applicationVO.referenceNo!=null}">
													<tr style="background-color: #DFF0D8;">
														<td class="text-center">${applicationVO.referenceNo}</td>
														<td class="text-center">${applicationVO.applicantNameAsNic}</td>
														<td class="text-center">${applicationVO.currentNicNo}</td>
														<td class="text-center">${applicationVO.passport}</td>
														<td class="text-center wrapword">${applicationVO.presentAddressLocal}</td>
														<td class="text-center">${applicationVO.dateOfBirth}</td>
													</tr>
												</c:when>
											</c:choose>
										</c:forEach>
									</tbody>					
								</table>
								</div>
							</div>	
							
							<div style="clear:both;"></div>
					
							<div class="col-lg-12" style="padding-left:0px; padding-right:0px;">
								<div style="text-align:right;">
									<button type="button" id="btn_print_id" class="btn btn-primary es-buttton"onclick="printClearanceReportAction()"><s:label key="Print"  
													cssClass="bold-label"></s:label></button>
								</div>
							</div>
						
						</s:form>
					</div>
		   		</div>
			<div style="clear: both;"></div>
		</div>
		
	
		
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />

	</div>
	<script type="text/javascript" src="js/jquery.numeric.js" ></script>
	<script language="javascript" type="text/javascript">

	$(document).ready(function() {		 
		initializeDateTimePickers();
	});
	
	function initializeDateTimePickers(){
		  $('#fromDate_id').datepicker({
			  dateFormat:"dd/mm/yy"
		  });
		  $('#toDate_id').datepicker({
			  dateFormat:"dd/mm/yy"
		  });	 
	}
	
	
	function printClearanceReportAction(){
		window.open("printClearanceReportAction");
	}
	
	
	
	
	
	
	 
	

	</script>
</body>
</html>
</s:i18n>