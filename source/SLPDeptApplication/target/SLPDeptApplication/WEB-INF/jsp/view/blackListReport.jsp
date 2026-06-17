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

<title>Blacklist</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Blacklist</c:set>
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
				<s:form cssClass="form-horizontal"  id="myForm" theme="simple" action="searchBlacklist.action" method="post"> 
					<div class="middle_content">
						<div>
						
<!-- 							<div style="clear: both;"></div> -->
							<div class="col-lg-4">				
								<div class="form-group">
									<div class="col-sm-5" >
										<strong><s:label cssClass="control-label bold-label" for="nicNo_id">NIC No </s:label></strong>
				   					</div>
				   					<div class="col-sm-7">
				   						<s:textfield name="nicNo" id="nicNo_id" cssClass="form-control"/>
				   					</div>
			   					</div>
							</div>
							
							<div style="clear: both;"></div>
							<div class="col-lg-4">
								<div class="form-group">
									<div class="col-sm-5" >
										<strong><s:label cssClass="control-label bold-label" for="new_nicNo_id">New NIC No </s:label></strong>
									</div>
									<div class="col-sm-7">
										<s:textfield name="newNicNo" id="new_nicNo_id" cssClass="form-control"/>
									</div>
								</div>
							</div>

							<div style="clear: both;"></div>

							<div class="col-lg-4">				
								<div class="form-group">
									<div class="col-sm-5" >
										<strong><s:label cssClass="control-label bold-label" for="passportNo_id">Passport No </s:label></strong>
				   					</div>
				   					<div class="col-sm-7">
				   						<s:textfield name="passportNo" id="passportNo_id" cssClass="form-control"/>
				   					</div>
			   					</div>
							</div>
							
							
							<div style="clear: both;"></div>
							<div class="col-lg-4">				
								<div class="form-group">
									<div class="col-sm-5" >
										<strong><s:label cssClass="control-label bold-label" for="name_id">Name </s:label></strong>
				   					</div>
				   					<div class="col-sm-7">
				   						<s:textfield name="name" id="name_id" cssClass="form-control"/>
				   					</div>
			   					</div>
							</div>
							

							
							<div class="col-lg-4">
			   					<div class="form-group">
									<div class="col-sm-4" >
										<div style="text-align:right;">
											<s:submit value="Search" cssClass="btn btn-primary es-buttton" ></s:submit>
										</div>
				   					</div>
				   					<div class="col-sm-4" >
										<div style="text-align:right;">
											<input type="button" onclick="reSetform()" value="Reset" class="btn btn-primary es-buttton">
										</div>
				   					</div>
			   					</div>
							</div>
						</div>
		   			</div>
		   		
				<div style="clear: both;"></div>
				<div class="form-group">
				<div class="col-lg-12">	
					<div class="table-responsive">
						<table class="table table-bordered">
						<thead>
											<tr>
												<th class="text-center"><strong>Reference No</strong></th>
												<th class="text-center"><strong>Curernt NIC No</strong></th>
												<th class="text-center"><strong>Passport No</strong></th>	
												<th class="text-center"><strong>Name</strong></th>	
												<th class="text-center"><strong>Address</strong></th>
												<th class="text-center"><strong>Telephone No</strong></th>
												<th class="text-center"><strong>Blacklisted Date</strong></th>		
												<th class="text-center"><strong>Comments</strong></th>	
<%-- 												<th class="text-center"><strong>Rivisions</strong></th> --%>
<%-- 												<th class="text-center"><strong>Review Status</strong></th>		 --%>
<%-- 												<th class="text-center" style="width: 5%;"><strong>Save</strong></th>	 --%>
											</tr>
						</thead>
						<tbody>
							<c:forEach items="${applicationList}" var="applicationVO">
								<tr style="background-color: #DFF0D8;">
									<td class="text-center">${applicationVO.applicationReferenceNumber}</td>
									<td class="text-center">${applicationVO.currentNic}</td>
									<td class="text-center">${applicationVO.passport}</td>
									<td class="text-center">${applicationVO.name}</td>
									<td class="text-center">${applicationVO.address}</td>
									<td class="text-center">${applicationVO.tel}</td>
									<td class="text-center">${applicationVO.createdDate}</td>
									<td class="text-center">${applicationVO.comment}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
				</div>
				</div>
				
				
				<div style="clear: both;"></div>
			
			<div class="col-lg-4">
		   					<div class="form-group">
								<div class="col-sm-4" >
									<div style="text-align:right;">
									<input type="hidden" id="rptType" name="rptType" />
<%-- 										<s:submit value="Print" cssClass="btn btn-primary es-buttton" onclick="printApplicationDetailsReport()" ></s:submit> --%>
										<input type="button" onclick="printApplicationDetailsReport()" value="Print" class="btn btn-primary es-buttton">
									</div>
			   					</div>
			   					<div class="col-sm-4" >
									<div style="text-align:right;">
<%-- 										<s:submit value="Export to Excel" cssClass="btn btn-primary es-buttton" onclick="printApplicationDetailsReport_Excel()" ></s:submit> --%>
										<input type="button" onclick="printApplicationDetailsReport_Excel()" value="Export to Excel" class="btn btn-primary es-buttton">
									</div>
			   					</div>
		   					</div>
			</div>
				
				
				
				</s:form>
				</div>
		   	</div>
			
			
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
	
	function reSetform(){
		window.location="viewBlacklist";
	 }
	
	function printApplicationDetailsReport(){
		window.open("printBlacklistReport");
	}
	
	function printApplicationDetailsReport_Excel(){
		window.open("printBlacklistReportExcel");
	}
	
	function abc(){
		alert('abc');
	}
	
	

	</script>
	<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		$("#messagesDiv").fadeIn(700).delay(7000).fadeOut(5000);	
	});
</script>
	
</body>
</html>
</s:i18n>