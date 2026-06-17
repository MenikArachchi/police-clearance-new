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

<title>Update Police Messages</title>

</head>

<style type="text/css">
	.dateField{
		width:80px !important;
	}
</style>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Update Police Messages</c:set>
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
				
					<div class="col-lg-12">
						<div class="col-sm-2" >
	   						<label class="control-label"><b>Application Date :</b></label>
	   					</div>
	   					<div class="col-sm-2">
	   						<b>${applicationVO.updatedDateTime}</b>
	   					</div>
						<div class="col-sm-2" >
	   						<label class="control-label"><b>Ref No :</b></label>
	   					</div>
	   					<div class="col-sm-2">
	   						<b>${applicationVO.referenceNo}</b>
	   					</div>
						<div style="clear: both;"></div>
					</div>
					
					<div class="col-lg-12">
						<div class="col-sm-2" >
	   						<label class="control-label"><b>Name :</b></label>
	   					</div>
	   					<div class="col-sm-2">
	   						<b>${applicationVO.applicantNameAsNic}</b>
	   					</div>
						<div class="col-sm-2" >
	   						<label class="control-label"><b>Nic No :</b></label>
	   					</div>
	   					<div class="col-sm-2">
	   						<b>${applicationVO.nic}</b>
	   					</div>
						<div style="clear: both;"></div>
					</div>
				
					<div class="col-lg-12">
						<div class="col-sm-2" >
	   						<label class="control-label"><b>Passport No :</b></label>
	   					</div>
	   					<div class="col-sm-2">
	   						<b>${applicationVO.passport}</b>
	   					</div>
						<div style="clear: both;"></div>
					</div>
					
					<div style="clear:both;"></div><hr /><div style="clear:both;"></div>
					
					<div class="col-lg-12">	
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="1" class="text-center"><strong></strong></th>
										<th colspan="4" class="text-center"><strong>Message From Head Quarters</strong></th>	
										<th colspan="4" class="text-center"><strong>Response From Police Station</strong></th>	
										<th colspan="3" class="text-center"><strong></strong></th>
									</tr>
									<tr>
										<th class="text-center"><strong>Police Station</strong></th>	
										<th class="text-center"><strong>Message</strong></th>	
										<th class="text-center"><strong>Sent by</strong></th>
										<th class="text-center"><strong>Sent Date</strong></th>
										<th class="text-center"><strong>Received By</strong></th>
										<th class="text-center"><strong>Response</strong></th>	
										<th class="text-center"><strong>Sent by</strong></th>
										<th class="text-center"><strong>Sent Date</strong></th>
										<th class="text-center"><strong>Sent to</strong></th>
										<th class="text-center"><strong>Cleared</strong></th>
										<th class="text-center"><strong>Comment</strong></th>
										<th class="text-center"><strong>Save</strong></th>	
									</tr>
								</thead>
								<tbody>
									<c:forEach var="certificateAddress" items="${addressVoList}" varStatus="counter">
									<tr>
										<td class="text-center">${certificateAddress.policeArea}</td>	
										<td class="text-center"><input type="text" name="messageFrom" id="messageFrom_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.fromMessage}"/></td>	
										<td class="text-center"><input type="text" name="messageSentBy" id="messageSentBy_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.fromSentBy}"/></td>
										<td class="text-center"><input type="text" name="messageFromdate" id="messageFromdate_${certificateAddress.addressId}" class="form-control dateField" value="<fmt:formatDate value='${certificateAddress.fromSentDate}' pattern='dd/MM/yyyy' />"/></td>
										<td class="text-center"><input type="text" name="messageReceivedBy" id="messageReceivedBy_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.fromReceiveBy}"/></td>
										<td class="text-center"><input type="text" name="response" id="response_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.responseMessage}"/></td>	
										<td class="text-center"><input type="text" name="responseSentBy" id="responseSentBy_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.responseSentBy}"/></td>
										<td class="text-center"><input type="text" name="responseSentDate" id="responseSentDate_${certificateAddress.addressId}" class="form-control dateField" value="<fmt:formatDate value='${certificateAddress.responseSentDate}' pattern='dd/MM/yyyy' />"/></td>
										<td class="text-center"><input type="text" name="responseSentTo" id="responseSentTo_${certificateAddress.addressId}" class="form-control" value="${certificateAddress.responseSentTo}"/></td>
										<td class="text-center">
											<table>
												<tr id="radioRow_${certificateAddress.addressId}">
													<c:choose>
														<c:when test="${certificateAddress.policeStatus == 'AP'}">
															<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" name="clearenceStatus_${certificateAddress.addressId}" value="Y" checked="checked"></td>
															<td style="vertical-align: middle;">Y</td>
															<td>&nbsp;&nbsp;</td>
															<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" name="clearenceStatus_${certificateAddress.addressId}" value="N" ></td>
															<td style="vertical-align: middle;">N</td>
														</c:when>
														<c:otherwise>
															<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" name="clearenceStatus_${certificateAddress.addressId}" value="Y" ></td>
															<td style="vertical-align: middle;">Y</td>
															<td>&nbsp;&nbsp;</td>
															<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" name="clearenceStatus_${certificateAddress.addressId}" value="N" checked="checked"></td>
															<td style="vertical-align: middle;">N</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</table>
										</td>
										<td class="text-center">
											<input type="text" class="form-control"  id="comment_${certificateAddress.addressId}" name="comment_${certificateAddress.addressId}" value="${certificateAddress.responseSentTo}"/>
										</td>
										<td class="text-center">
											<img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="save_image" alt="Save" title="Save" class="basic_image_button" onclick="saveRow(${certificateAddress.addressId})" />
										</td>
									</tr>
									</c:forEach>	
								</tbody>
							</table>
						</div>
					</div>
					
					<form name="policeUpdateMessageForm" id="policeUpdateMessageForm" action="updatePoliceMessageRow" method="post">
						<s:hidden name="addressVO.addressId" id="addressId" />
						<s:hidden name="addressVO.fromMessage" id="fromMessage" />
						<s:hidden name="addressVO.fromSentBy" id="fromSentBy" />
						<s:hidden name="fromSentDate" id="fromSentDate" />
						<s:hidden name="addressVO.fromReceiveBy" id="fromReceiveBy" />
						<s:hidden name="addressVO.responseMessage" id="responseMessage" />
						<s:hidden name="addressVO.responseSentBy" id="responseSentBy" />
						<s:hidden name="toSentDate" id="responseSentDate" />
						<s:hidden name="addressVO.responseSentTo" id="responseSentTo" />
						<s:hidden name="clearedStatus" id="clearedStatus" />
						<s:hidden name="comment" id="comment" />
						<s:hidden name="applicationId" id="applicationId"/>
					</form>
					
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
		$('.dateField').datepicker({
			dateFormat:"dd/mm/yy"
	  	});
	}
	
	function saveRow(addressId){
		
		var fromMessage = $("#messageFrom_"+addressId).val();
		var fromSentBy = $("#messageSentBy_"+addressId).val();
		var fromSentDate = $("#messageFromdate_"+addressId).val();
		var fromReceivedBy = $("#messageReceivedBy_"+addressId).val();
		var responseMessage = $("#response_"+addressId).val();
		var responseSentBy = $("#responseSentBy_"+addressId).val();
		var responseSentDate = $("#responseSentDate_"+addressId).val();
		var responseSentTo = $("#responseSentTo_"+addressId).val();
		var clearedStatus = $("#radioRow_"+addressId+" input[type='radio']:checked").val();
		var comment = $("#comment_"+addressId).val();
		
		$("#addressId").val(addressId);
		$("#fromMessage").val(fromMessage);
		$("#fromSentBy").val(fromSentBy);
		$("#fromSentDate").val(fromSentDate);
		$("#fromReceiveBy").val(fromReceivedBy);
		$("#responseMessage").val(responseMessage);
		$("#responseSentBy").val(responseSentBy);
		$("#responseSentDate").val(responseSentDate);
		$("#responseSentTo").val(responseSentTo);
		$("#clearedStatus").val(clearedStatus);
		$("#comment").val(comment);
		
		$("#policeUpdateMessageForm").submit();
	}

	</script>
</body>
</html>
</s:i18n>