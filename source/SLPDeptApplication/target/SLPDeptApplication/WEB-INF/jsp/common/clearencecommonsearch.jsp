<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-horizontal" id="myForm" action="${param.formSubmitUrl}" method="post" onsubmit="return validateForm()"> 
	<input type="hidden" name="searchCriteriaVO.maxId" id="maxId" value="0" />
	<input type="hidden" name="searchCriteriaVO.limit" id="limit" value="20" />
	<input type="hidden" name="searchCriteriaVO.defaultViewFromUi" id="defaultViewFromUi" value="0" />
	<input type="hidden" name="searchCriteriaVO.defaultView" id="defaultView" value="${searchCriteriaVO.defaultView}" />
	<input type="hidden" name="searchCriteriaVO.currentPage" id="currentPage"/>
	<input type="hidden" name="searchCriteriaVO.lockedRefresh" id="lockedRefresh"/>
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
				<strong><s:label cssClass="control-label bold-label" for="pptNo">Clearance Status :</s:label></strong>
 					</div>				   					
 					<div class="col-sm-8">				   					 
 						<select id="clearenceStatus" name="searchCriteriaVO.clearenceStatus" class="form-control" >
 							<option value="ALL">All</option>
 							<c:forEach items="${clearenceStatusList}" var="clearenceStatus">
 								<c:choose>
 									<c:when test="${clearenceStatus.value.code == searchCriteriaVO.clearenceStatus}">
 										<option value="${clearenceStatus.value.code}" selected="selected">${clearenceStatus.key}</option>
 									</c:when>
 									<c:otherwise>
 										<option value="${clearenceStatus.value.code}">${clearenceStatus.key}</option>
 									</c:otherwise>
 								</c:choose>			   								
 							</c:forEach>
 						</select>
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
 					<div class="col-sm-1" >
				<div style="text-align:right;">
					&nbsp;					
				</div>
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
 						<s:textfield name="searchCriteriaVO.name" cssClass="form-control" id="name_id"/>
 					</div>
 					<div class="col-sm-1" >
				<div style="text-align:right;">
					&nbsp;					
				</div>
				</div>
			</div>
	</div>
	<c:if test="${param.includeChekBox =='YES'}">
		<div style="clear: both;"></div>							
		<div class="col-lg-6">				
			<div class="form-group">
						<div class="col-sm-3" >
							&nbsp;
	 					</div>
	 					<div class="col-sm-8">
	 						<table>
	 							<tr>
	 								<td>
	 									<c:choose>
	 										<c:when test="${searchCriteriaVO.printPendingOnly==true}">
	 											<input type="checkbox" checked="checked" name="searchCriteriaVO.printPendingOnly" value="true" class="form-control" id="displayOnlyPrintPending" />
	 										</c:when>
	 										<c:otherwise>
	 											<input type="checkbox" name="searchCriteriaVO.printPendingOnly" value="true" class="form-control" id="displayOnlyPrintPending" />
	 										</c:otherwise>
	 									</c:choose>
	 									
	 								</td>
	 								<td>&nbsp;&nbsp;</td>
	 								<td>
	 									<strong>
				 							<label class="control-label bold-label" for="displayOnlyPrintPending">${param.checkboxLabel}</label>
				 						</strong>
	 								</td>
	 							</tr>
	 						</table>	 						
	 					</div>
	 					<div class="col-sm-1" >
					<div style="text-align:right;">
						&nbsp;					
					</div>
					</div>
				</div>
		</div>
		<div class="col-lg-12">				
			<div class="col-sm-12">	 						
				<div>
					<p class="mandatory_field" >
						${param.checkboxMessage}
 				    </p>
				</div>
			</div>
		</div>
		
		
	</c:if>
	
	<div style="clear: both;"></div>
	<div class="col-lg-8">				
		<div class="form-group">
			<div class="col-sm-8" >
				<div style="text-align:right;">
					<input type="submit" value="Search" class="btn btn-primary es-buttton" id="searchCertificateIssuanceApplication" />
					<input type="button" value="Clear" class="btn btn-primary es-buttton" id="clearReviewApplication" />
				</div>
 					</div>
					</div>
	</div>
	
</form>

<script type="text/javascript">

$(document).ready(function() {
	initializeDateTimePickers();
	
	$('#clearReviewApplication').click(function(){
		$.datepicker._clearDate('#fromDate_id');
		$.datepicker._clearDate('#toDate_id');
		$('#clearenceStatus').val('ALL');
		$('#referenceNo').val('');
	});
});

function initializeDateTimePickers(){
	$( "#fromDate_id" ).datepicker({
      defaultDate: "+0d",
      maxDate: "+0d",
      dateFormat:"dd/mm/yy",
      onClose: function( selectedDate ) {
        $( "#toDate_id" ).datepicker( "option", "minDate", selectedDate );
	}});
	
    $( "#toDate_id" ).datepicker({
      defaultDate: "+0d",
      maxDate: "+0d",
      dateFormat:"dd/mm/yy",
      onClose: function( selectedDate ) {
    	if(!(selectedDate=="")){
    		 $( "#fromDate_id" ).datepicker( "option", "maxDate", selectedDate );
    	}else{
    		 $( "#fromDate_id" ).datepicker( "option", "maxDate", new Date());
    	}	       
    }});
}


function validateForm(){
	  blockUI();
	  return true;
}

</script>