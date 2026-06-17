<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${! empty clearenceGridButtons}">	
		<div id="gridButtons" style="padding: 5px;float: right;margin-right: 10px;">
		    <table class="table table-striped">
				<tr>
					<td><label class="control-label"><b>No of Records Per Page: </b></label></td>
					<td>
						<c:set var="recordCount" value="10,20,30,40,50" scope="page" />
						<select id="noOfRecordsPerPage" onchange="setLimit(this.value)" class="form-control">
							<c:forEach items="${pageScope.recordCount}" var="currentPageCount">
								<c:choose>
									<c:when test="${currentPageCount == searchCriteriaVO.limit}">
										<option selected="selected" value="${currentPageCount}">${currentPageCount}</option>
									</c:when>
									<c:otherwise>
										<option value="${currentPageCount}">${currentPageCount}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td><label class="control-label"><b>Pages: </b></label></td>
					<c:forEach items="${clearenceGridButtons}" var="button">
						<td style="padding-left: 5px;">
							<c:if test="${button.displayStatus==1}">
								<c:choose>
									<c:when test="${button.currentButtonStatus == 1}">
										<input type="button" value="${button.label}" disabled="disabled" class="btn btn-default">
									</c:when>
									<c:otherwise>
										<input type="button" onclick="goToSelectedPage(${button.maxId},${button.limit},${button.pageNo})" value="${button.label}" class="btn btn-primary">
									</c:otherwise>
								</c:choose>	
							</c:if>										
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</c:if>
	
<script type="text/javascript">

 function setLimit(value){
	  blockUI();
	  $('#limit').val(value);
	  $('#maxId').val(0);
	  $('#myForm').submit();		  
  }
  
  function goToSelectedPage(maxId,limit,pageNo){
	  blockUI();
	  $('#maxId').val(maxId);
	  $('#limit').val(limit);
	  $('#currentPage').val(pageNo);
	  $('#myForm').submit();
  }

</script>