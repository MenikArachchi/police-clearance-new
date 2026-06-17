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

<title>View Application Status</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Search Application Status</c:set>
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
						<s:form cssClass="form-horizontal" id="myForm"  theme="simple" action="searchApplicationStatus.action" onsubmit="return validateForm()"> 
							<div class="col-lg-3">
								<div class="form-group">
									<div class="col-sm-2" >
										<strong><s:label cssClass="control-label bold-label">From:</s:label></strong>
				   					</div>
				   					<div class="col-sm-9">
				   						<c:set var="customStartDate">
				   							<fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="fromDate" value="${customStartDate}" id="fromDate_id" class="form-control">
				   					</div>
			   					</div>
			   				</div>
			   				<div class="col-lg-3">
			   					<div class="form-group">
									<div class="col-sm-1" >
										<strong><s:label cssClass="control-label bold-label">To:</s:label></strong>
				   					</div>
				   					<div class="col-sm-9">
				   						<c:set var="customEndDate">
				   							<fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy"/>
				   						</c:set>
				   						<input type="text" readonly="readonly" name="toDate" value="${customEndDate}" id="toDate_id" class="form-control">
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="referenceNo">Reference No :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.referenceNo" cssClass="form-control" id="referenceNo"/>
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="nicNo">NIC :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.nicNo" cssClass="form-control" id="nicNo"/>
				   					</div>
			   					</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="nicNo">New NIC :</s:label></strong>
									</div>
									<div class="col-sm-8">
										<s:textfield name="searchCriteriaVO.newNicNo" cssClass="form-control" id="nicNewNo"/>
									</div>
								</div>
							</div>
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="pptNo">PPT :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.pptNo" cssClass="form-control" id="pptNo"/>
				   					</div>
				   					<div class="col-sm-1" >
										&nbsp;
				   					</div>
			   					</div>
							</div>
							
							<div style="clear: both;"></div>
							<div class="col-lg-6">				
								<div class="form-group">
									<div class="col-sm-3" >
										<strong><s:label cssClass="control-label bold-label" for="name">Name :</s:label></strong>
				   					</div>
				   					<div class="col-sm-8">
				   						<s:textfield name="searchCriteriaVO.name" cssClass="form-control" id="name"/>
				   					</div>
				   					<div class="col-sm-1" >
										&nbsp;
				   					</div>
			   					</div>
							</div>
							
							<div style="clear: both;"></div>
							<div class="col-lg-8">				
								<div class="form-group">
									<div class="col-sm-8" >
										<div style="text-align:right;">
											<input type="submit" value="Search" class="btn btn-primary es-buttton" id="searchReviewApplication" />
											<input type="button" value="Clear" class="btn btn-primary es-buttton" id="clearReviewApplication" />
										</div>
				   					</div>
			   					</div>
							</div>
							
							</s:form>
							<div style="clear: both;"></div>
							<div class="form-group">
							
							<c:if test="${! empty applicationList}">							
									<div class="col-lg-12" style="max-width: 100%;">	
										<div class="table-responsive" style="max-width: 100%;">
											<table class="table table-bordered table-striped" style="max-width: 100%;">
												<thead>
													<tr>
														<th class="text-center"><strong>Reference</strong></th>	
														<th class="text-center"><strong>Name</strong></th>
														<th class="text-center"><strong>Current NIC No</strong></th>
														<th class="text-center"><strong>Passport No</strong></th>
														<th class="text-center"><strong>Application Date</strong></th>
														<th class="text-center"><strong>Certificate No</strong></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${applicationList}" var="applicationVO">
														<tr>
															<td class="text-center-middle" style="vertical-align: middle;">
																<a href="viewApplicationStatus.action?aid=${applicationVO.applicationId}" target="_blank" >${applicationVO.referenceNo}</a>
															</td>
															<td style="vertical-align: middle;text-align: left;">${applicationVO.applicantNameAsNic}</td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.currentNicNo}</td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.passport}</td>
															<td class="text-center-middle" style="vertical-align: middle;"><fmt:formatDate value="${applicationVO.submittedDate}" pattern="dd/MM/yyyy hh:mm aa"/></td>
															<td class="text-center-middle" style="vertical-align: middle;">${applicationVO.certificateSerialNo}</td>
														</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
									</div>
							</c:if>
							
							<c:if test="${empty applicationList}">
								<div class="col-lg-12">	
									<div class="table-responsive">
										<div class="alert alert-warning">
											No Records are available!
										</div>	
									</div>
								</div>
							</c:if>
						</div>
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
	
	$('#clearReviewApplication').click(function(){
		$.datepicker._clearDate('#fromDate_id');
		$.datepicker._clearDate('#toDate_id');
		$('#pptNo').val('');
		$('#referenceNo').val('');
		$('#nicNo').val('');
		$('#nicNewNo').val('');
		$('#name').val('');
	});
	
	function initializeDateTimePickers(){
		$( "#fromDate_id" ).datepicker({
	      defaultDate: "+0d",
	      dateFormat:"dd/mm/yy",
	      onClose: function( selectedDate ) {
	        $( "#toDate_id" ).datepicker( "option", "minDate", selectedDate );
		}});
		
	    $( "#toDate_id" ).datepicker({
	      defaultDate: "+0d",
	      dateFormat:"dd/mm/yy",
	      onClose: function( selectedDate ) {
	        $( "#fromDate_id" ).datepicker( "option", "maxDate", selectedDate );
	    }});
	}

	</script>
</body>
</html>
</s:i18n>