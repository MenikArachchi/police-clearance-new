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

<title>Locked Records</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Locked Records</c:set>
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
						
							<div class="form-group">
							<c:if test="${userType==userTypeDisplayVO.oicUser}">
							    <form class="form-horizontal" id="myForm" action="searchLockedRecords.action" method="post"> 
							    <div class="col-lg-6">				
									<div class="form-group">
						 					<div class="col-sm-3" >
												<strong><s:label cssClass="control-label bold-label" for="pptNo">Select Department</s:label></strong>
						 					</div>				   					
						 					<div class="col-sm-8">				   					 
						 						<select id="userDepartment" name="userDepartment" class="form-control" >
						 							<c:choose>
						 								<c:when test="${userDepartment==1}">
							 								<option selected="selected" value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==2}">
						 									<option value="1">Police Head Quarters</option>
								 							<option selected="selected" value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==3}">
						 									<option value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option selected="selected" value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==4}">
						 									<option value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option selected="selected" value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==5}">
						 									<option value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option selected="selected" value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==6}">
						 									<option value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option selected="selected" value="6">NIC</option>
								 							<option value="7">IMI</option>
						 								</c:when>
						 								<c:when test="${userDepartment==7}">
						 									<option value="1">Police Head Quarters</option>
								 							<option value="2">Police Station</option>
								 							<option value="3">CID</option>
								 							<option value="4">TID</option>
								 							<option value="5">SIS</option>
								 							<option value="6">NIC</option>
								 							<option selected="selected" value="7">IMI</option>
						 								</c:when>
						 								
						 							</c:choose>
						 						</select>
						 					</div>
										</div>
									</div>
									<div style="clear: both;"></div>
									<div class="col-lg-8">				
										<div class="form-group">
											<div class="col-sm-8" >
												<div style="text-align:right;">
													<input type="button" value="Search" class="btn btn-primary es-buttton" onclick="searchApps()" />													
												</div>
								 			</div>
										</div>
									</div>
							    </form>
							
							</c:if>
							
							
							<c:if test="${! empty lockedApplications}">							
									<div class="col-lg-6" style="max-width: 100%;">	
										<div class="table-responsive" style="max-width: 100%;">
											<table class="table table-bordered table-striped" style="max-width: 100%;">
												<thead>
													<tr>
														<th class="text-center"><strong><input type="checkbox" id="checkAll"/> Select all</label></strong></th>
														<th class="text-center"><strong>Application Reference Number</strong></th>	
														<th class="text-center"><strong>Locked User</strong></th>																													
													</tr>
												</thead>
												<tbody>													
													<c:forEach items="${lockedApplications}" var="applicationVO">
															<tr>						
																<td class="text-center" style="vertical-align: middle;">
																	<input type="checkbox" id="lockedIdListName_${applicationVO.applicationId}" value="${applicationVO.applicationId}" name="lockedIdListName" />
																	
																</td>
																<td class="text-center" style="vertical-align: middle;">
																		${applicationVO.referenceNo}
																</td>
																<td class="text-center" style="vertical-align: middle;">
																		${applicationVO.userFullName}
																</td>
															</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
										
										<div style="clear: both;"></div>
										<br />
										<div style="float: right;">
											<form action="#" id="submitForm" style="display: inline;">
												<div id="hiddenInputAppendDiv"></div>
												<input type="hidden" name="userDepartment" id="hidden_userDepartment" />
												<input type="button"  onclick="return validateForm()" value="Unlock Records" class="btn btn-primary es-buttton" />												
											</form>	
										</div>
									</div>
								
								<div style="clear: both;"></div>
								<br />
									
							</c:if>
							
							<c:if test="${empty lockedApplications}">
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
	
	<script language="javascript" type="text/javascript">
	
	$(document).ready(function(){
		$("#checkAll").change(function () {
		    $("input:checkbox").prop('checked', $(this).prop("checked"));
		});
	})
	
	 function validateForm(){
			var oneAvailable=0;
			 $('#hidden_userDepartment').val($('#userDepartment').val());
			$('#hiddenInputAppendDiv').html('');
			$('input[name="lockedIdListName"]:checked').each(function() {
			   var applicationId=parseInt(this.value);
			   if(applicationId>0){
				   oneAvailable=1;
				   $('#hiddenInputAppendDiv').append('<input type="hidden" value="' + applicationId + '" name="lockedIdList" />');
			   }
			});
			if(oneAvailable>0){
				var conf=confirm('Are you sure you want to unlock selected applications?');
				if(conf){
					$('#submitForm').attr('action','unlockRecords.action');
					$('#submitForm').submit();
				}else{
					return false;
				}
			}else{
				alert('Please select the application to be unlocked!');
				return false;
			}
			
		}
	 
	 function searchApps(){
		 $('#hidden_userDepartment').val($('#userDepartment').val());
		 $('#myForm').submit();
	 }
	 
	   
	
	 
	  

	</script>
</body>
</html>
</s:i18n>