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

<title>Review Application</title>

</head>

<body id="bd" >
     <!--main container start -->
	<div id="es-container" class="container" >
			
		<!-- including header -->
		<jsp:include page="../common/header.jsp" />
		
		<!-- Starting the page Title -->
		<div id="es-content" >	
	
				<!-- Including the common page with title bar and help content -->
				<c:set var="pageTitle">Internal - DIG</c:set>
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
						<c:set var="submitUrl">searchDigClearence.action</c:set>
						<c:set var="includeChekBox">YES</c:set>
						<c:set var="checkboxMessage">
							Note :- Select the check box above and click 'Search' to filter the applications on your queue. 							
						</c:set>
						<c:set var="checkboxLabel">
							Display only pending approval list 							
						</c:set>
						<jsp:include page="../common/clearencecommonsearch.jsp">
						    <jsp:param name="formSubmitUrl" value="${submitUrl}" />
						     <jsp:param name="includeChekBox" value="${includeChekBox}" />
						    <jsp:param name="checkboxMessage" value="${checkboxMessage}" />
						    <jsp:param name="checkboxLabel" value="${checkboxLabel}" />
						</jsp:include>
						
							<div style="clear: both;"></div>
							<div class="form-group">
							
							<c:if test="${! empty applicationList && userType==userTypeDisplayVO.digUser}">							
									<div class="col-lg-13" style="max-width: 100%;">	
										<div class="table-responsive" style="max-width: 100%;">
											<table class="table table-bordered" style="max-width: 100%;">
												<thead>
													<tr>
													
														<th class="text-center"><strong>Select</strong></th>
														<th class="text-center"><strong>Application Date</strong></th>
														<th class="text-center"><strong>Reference</strong></th>	
														<th class="text-center"><strong>Nic No</strong></th>	
														<th class="text-center"><strong>Passport No</strong></th>
														<th class="text-center"><strong>Name</strong></th>
														
														<th class="text-center"><strong>Pol.</strong></th>
														<th class="text-center"><strong>CID</strong></th>
														<th class="text-center"><strong>TID</strong></th>
														<th class="text-center"><strong>SIS</strong></th>
														<th class="text-center"><strong>NIC</strong></th>
														<th class="text-center"><strong>Immi.</strong></th>
														
														<th class="text-center"><strong>Comments</strong></th>														
														<th class="text-center"><strong>Attachments</strong></th>															
														<th class="text-center"><strong>Letter Approval</strong></th>											
													</tr>
												</thead>
												<tbody>
													<c:set var="canAnyAddressBePrinted">0</c:set>
													<c:forEach items="${applicationList}" var="applicationVO">
																					
															<c:choose>
																<c:when test="${applicationVO.hasGreenChanneled==1 || applicationClearanceStatus=='GC'}">
																		<tr style="background-color: #7DFB7F;">
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${applicationVO.hasAnyBlacklitedRecord==1}">
																			<tr style="background-color: #F9C5C5;">
																		</c:when>
																		<c:otherwise>
																			<c:choose>
																				<c:when test="${applicationVO.hasAnyAdverseRecord==1}">
																					<tr style="background-color: #FBE199;">
																				</c:when>
																				<c:otherwise>
																					<tr>
																				</c:otherwise>
																			</c:choose>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
															
																<td class="text-center" style="vertical-align: middle;">
																	<input type="hidden" id="constructedComment_${applicationVO.applicationId}" value="${applicationVO.constructedComment}" />
																	<input type="hidden" id="commentAvailable_${applicationVO.applicationId}" value='<c:out escapeXml="false"  value="${applicationVO.commentAvailable}"></c:out>' />
																	<input type="hidden" id="hiddenVersionId_${applicationVO.applicationId}" value="${applicationVO.versionId}" />
																	
																	<input type="hidden" id="canDigApprove_${applicationVO.applicationId}" value="${applicationVO.canAspApprove}" />
																	<input type="hidden" id="certificateLetterContent_${applicationVO.applicationId}" value="${applicationVO.certificateLetterContent}" />
																	
																	<c:choose>
																		<c:when test="${applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/lock.png"  alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="cancelEdit(${applicationVO.applicationId})" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/unlock.png" alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="editRow(${applicationVO.applicationId},'${applicationVO.referenceNo}')" />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.updatedDateTime}</td>
																<td class="text-center" style="vertical-align: middle;">
																	<a href="viewApplicationByReferenceNo.action?referenceNo=${applicationVO.referenceNo}" >
																		${applicationVO.referenceNo}
																	</a> 
																</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.nic}</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.passport}</td>
																<td class="text-center" style="vertical-align: middle;">${applicationVO.applicantName}</td>
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.polStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.polStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.cidStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.cidStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.tidStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.tidStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.sisStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.sisStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.nicStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.nicStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:when test="${applicationVO.nicStatus=='OI'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:when test="${applicationVO.nicStatus=='NI'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																
																<td class="text-center"  style="vertical-align: middle;">
																	<c:choose>
																		<c:when test="${applicationVO.imiStatus=='AP'}">
																			<img src="images/approved.png" alt="Approved" class="basic_image_button_smaller" style="cursor:default;" title="Approved" />
																		</c:when>
																		<c:when test="${applicationVO.imiStatus=='RJ'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:when test="${applicationVO.imiStatus=='OI'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:when test="${applicationVO.imiStatus=='NI'}">
																			<img src="images/rejected.png" alt="Rejected" class="basic_image_button_smaller" style="cursor:default;" title="Rejected" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/pending.png" alt="Pending" class="basic_image_button_smaller" style="cursor:default;" title="Pending" />
																		</c:otherwise>
																	</c:choose>
																</td>
																
																<td class="text-center" style="vertical-align: middle;">
																	<img src="images/comments_list.png" alt="Comments" class="basic_image_button_smaller" title="Comments" onclick="viewCommentsList(${applicationVO.applicationId})" />
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">
																		<c:if test="${! empty  applicationVO.nicAttachPath}">
																			<a href="javascript:void(0)" onclick="viewNicPopup(${applicationVO.applicationId})">
																				<table>
																					<tr>
																						<td>
																							<input type="hidden" id="hiddenNicFileName_${applicationVO.applicationId}" value="${applicationVO.nicAttachPath}" />
																							<input type="hidden" id="hiddenNicFileType_${applicationVO.applicationId}" value="${applicationVO.nicFileType}" />
																							<input type="hidden" id="hiddenNicFileNameBack_${applicationVO.applicationId}" value="${applicationVO.nicBackAttachPath}" />
																							<input type="hidden" id="hiddenNicFileTypeBack_${applicationVO.applicationId}" value="${applicationVO.nicBackFileType}" />
																							<input type="hidden" id="hiddenNicNo_${applicationVO.applicationId}" value="${applicationVO.nic}" />
																							<img alt="NIC" src="images/zoom_in.png" style="width:15px;height:15px;" />
																						</td>
																						<td>&nbsp;NIC</td>
																					</tr>
																				</table>
																			</a>
																		</c:if>
																	
																		<c:if test="${! empty  applicationVO.passportAttachPath}">
																			<a href="javascript:void(0)" onclick="viewPptPopup(${applicationVO.applicationId})">										 
																				<table>
																					<tr>
																						<td>
																							<input type="hidden" id="hiddenPptFileName_${applicationVO.applicationId}" value="${applicationVO.passportAttachPath}" />
																							<input type="hidden" id="hiddenPptFileType_${applicationVO.applicationId}" value="${applicationVO.pptFileType}" />
																							<input type="hidden" id="hiddenPptFileNameBack_${applicationVO.applicationId}" value="${applicationVO.passportBackAttachPath}" />
																							<input type="hidden" id="hiddenPptFileTypeBack_${applicationVO.applicationId}" value="${applicationVO.pptBackFileType}" />
																							<input type="hidden" id="hiddenPptNo_${applicationVO.applicationId}" value="${applicationVO.passport}" />
																							<img alt="NIC" src="images/zoom_in.png" style="width:15px;height:15px;" />
																						</td>
																						<td>&nbsp;PPT</td>
																					</tr>
																				</table>
																			</a>
																		</c:if>
																		
																		<c:if test="${! empty  applicationVO.birthCertificatePath}">
																			<a href="javascript:void(0)" onclick="viewBcPopup(${applicationVO.applicationId})">										 
																				<table>
																					<tr>
																						<td>
																							<input type="hidden" id="hiddenBcFileName_${applicationVO.applicationId}" value="${applicationVO.birthCertificatePath}" />
																							<input type="hidden" id="hiddenBcFileType_${applicationVO.applicationId}" value="${applicationVO.birthCertificateFileType}" />
																							<input type="hidden" id="hiddenBcFileName_back_${applicationVO.applicationId}" value="${applicationVO.birthCertificateBackPath}" />
																							<input type="hidden" id="hiddenBcFileType_back_${applicationVO.applicationId}" value="${applicationVO.birthCertificateFileBackType}" />
																							<img alt="Birth Certificate" src="images/zoom_in.png" style="width:15px;height:15px;" />
																						</td>
																						<td>&nbsp;Birth Cert.</td>
																					</tr>
																				</table>
																			</a>
																		</c:if>
																		
																		<c:if test="${! empty  applicationVO.letterOfReferencePath}">
																			<a href="javascript:void(0)" onclick="viewSlbfePopup(${applicationVO.applicationId})">										 
																				<table>
																					<tr>
																						<td>
																							<input type="hidden" id="hiddenSlbfeFileName_${applicationVO.applicationId}" value="${applicationVO.letterOfReferencePath}" />
																							<input type="hidden" id="hiddenSlbfeFileType_${applicationVO.applicationId}" value="${applicationVO.letterOfReferenceFileType}" />
																							<img alt="Birth Certificate" src="images/zoom_in.png" style="width:15px;height:15px;" />
																						</td>
																						<td>&nbsp;SLBFE Lett.</td>
																					</tr>
																				</table>
																			</a>
																		</c:if>
																</td>
																
																
																<td class="text-center" style="vertical-align: middle;">															
																	<c:choose>
																		<c:when test="${applicationVO.canDigApprove==1 && applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/edit.png" style="width:20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button" onclick="saveRow(${applicationVO.applicationId})" />												
																		</c:when>
																		<c:otherwise>
																			<img src="images/edit.png" style="width: 20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button disabled_image" />					
																		</c:otherwise>												
																	</c:choose> 								
																</td>
																
															</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
										
										<div style="clear: both;"></div>
										<br />
									</div>
									
							
								<!-- including Grid Butons -->
								<jsp:include page="../common/clearancegridbuttons.jsp" />
							
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
		
		<div style="display:none;">
			<a data-toggle="modal" id="commentInputModalLink" href="#commentInputModalPopUp">View</a>
			<a data-toggle="modal" id="commentModalLink" href="#commentModalPopUp">View</a>
		</div>
		
		
		
		<!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
		<div class="modal fade" id="commentInputModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
			 <form action="updateCertificateIssuanceDig.action" method="post" id="certificateIssuanceUpdateForm" onsubmit="return validateUpdateForm()">	
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">View/Update and Approve Letter Content</h4>
				</div>
				<div class="modal-body">
					
							<s:hidden name="applicationVO.applicationId"/>							
							<s:hidden name="clearenceVO.applicationId" id="applicationId_clearence" />
							<input type="hidden" name="clearenceVO.versionId" id="versionId_clearence" />
							
							<s:hidden name="clearenceVO.digApprovedStatus" id="digApprovedStatus_clearence" />
							
							<table class="table borderless">
									<tr><td colspan="2"><b>DIG Approved:</b></td></tr>
									<tr>
										<td colspan="2">
											<input type="radio" id="digApprovedStatusYes" name="digApprovedStatus" value="Y" onclick="toggleLetterComment()" /> Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										    <input type="radio" id="digApprovedStatusNo" name="digApprovedStatus" value="N" onclick="toggleLetterComment()" /> No
										</td>
									</tr>
									<tr><td colspan="2">&nbsp;</td></tr>
							</table>
							
							<table class="table borderless" id="letterContentTable">
									<tr><td><b>Clearance Letter Content:</b></td></tr>
									<tr><td><textarea name="clearenceVO.letterContent" rows="12" id="letterContentInput" class="form-control"></textarea></td></tr>
									<tr><td>&nbsp;</td></tr>
							</table>
							
							<table class="table borderless" id="commentInputTable" style="display: none;">
									<tr><td><b>Please add a comment for your action:</b></td></tr>
									<tr><td><textarea name="clearenceVO.comment" rows="8" id="commentInput" class="form-control"></textarea></td></tr>
									<tr><td>&nbsp;</td></tr>
							</table>
					
				 </div>
				
				 <div class="modal-footer">
				 	<button type="button" onclick="updateApplication()" class="btn btn-primary">OK</button>
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
				 </div>
				 
				</div>
				</form>
			</div>
		</div>
		
		<!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
		<div class="modal fade" id="commentModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">View Comments</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered">
							<tr><td colspan="2"><b>History/Related Adverse Records:</b></td></tr>
							<tr>
								<td colspan="2" id="historyAdverseRecords"></td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
							<tr><td colspan="2"><b>Current Adverse Records:</b></td></tr>
							<tr><td colspan="2"><b>NIC: <span id="nicStatusText"></span></b></td></tr>
							<tr>
								<td id="nicCommentAppendDiv">No comment is available!</td>
								<td id="nicCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>CID: <span id="cidStatusText"></span></b></td></tr>
							<tr>
								<td id="cidCommentAppendDiv">No comment is available!</td>
								<td id="cidCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>TID: <span id="tidStatusText"></span></b></td></tr>
							<tr>
								<td id="tidCommentAppendDiv">No comment is available!</td>
								<td id="tidCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>SIS: <span id="sisStatusText"></span></b></td></tr>
							<tr>
								<td id="sisCommentAppendDiv">No comment is available!</td>
								<td id="sisCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>Immigration: <span id="imiStatusText"></span></b></td></tr>
							<tr>
								<td id="immiCommentAppendDiv">No comment is available!</td>
								<td id="immiCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>Police: <span id="polStatusText"></span></b></td></tr>
							<tr>
								<td colspan="2">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>Police Area</th>
												<th>Address</th>
												<th>Status</th>
												<th>Police Comment</th>
												<th>Date</th>
											</tr>
										</thead>
										<tbody id="policeCommentAppendDiv">
										
										</tbody>
									</table>
								</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
							<tr><td colspan="2">Comments</td></tr>
							<tr><td colspan="2"><b>Checking Officer Comment:</b></td></tr>
							<tr>
								<td id="checkOffCommentAppendDiv">No comment is available!</td>
								<td id="checkOffCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>OIC Comment:</b></td></tr>
							<tr>
								<td id="oicCommentAppendDiv">No comment is available!</td>
								<td id="oicCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>ASP Comment:</b></td></tr>
							<tr>
								<td id="aspCommentAppendDiv">No comment is available!</td>
								<td id="aspCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>DHA Comment:</b></td></tr>
							<tr>
								<td id="dhaCommentAppendDiv">No comment is available!</td>
								<td id="dhaCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>DIG Comment:</b></td></tr>
							<tr>
								<td id="digCommentAppendDiv">No comment is available!</td>
								<td id="digCommentDateAppendDiv"></td>
							</tr>
					</table>								
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" style="display: none;" id="commentModalCloseButton" class="btn btn-primary" data-dismiss="modal">&nbsp;</button>
				 </div>
				</div>
			</div>
		</div>
		
		<div style="display: none;" id="hiddenForms">
			<form action="printcertificate.action" id="hiddenFormPrintCertificate" target="_blank">
				<input type="hidden" name="applicationId" readonly="readonly" id="printCertificateFormApplicationId" value="0" />
			</form>	
		</div>
		
		<!-- including common popups -->
		<jsp:include page="../common/clearencecommonpopups.jsp" />
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />

	</div>
	<script type="text/javascript" src="js/jquery.numeric.js" ></script>
	<script language="javascript" type="text/javascript">
	
	$(document).ready(function(){
		$('#digApprovedStatusYes').attr('checked','checked');
		toggleLetterComment();
	});
	
	var canDigApprove =0;
	
	function refreshPage(){
		var conf=confirm('Are you sure you want to refersh the page?');
		if(conf){
			$('#myForm').submit();
		}
	}
	
	
	
	function viewCommentsList(applicationId){
		 blockUI();
		 clearAppendDivs();
		 $.get('loadCommentList.action',{applicationId:applicationId},function(data){
			  	 var commentTypeVO=data.typeDisplayVO;
			  	 if(!(commentTypeVO==null)){
			  		$('#nicCommentAppendDiv').html(commentTypeVO.nicComment);
			  		$('#nicCommentDateAppendDiv').html(commentTypeVO.nicCommentDate);
			  		$('#cidCommentAppendDiv').html(commentTypeVO.cidComment);
			  		$('#cidCommentDateAppendDiv').html(commentTypeVO.cidCommentDate);
			  		$('#tidCommentAppendDiv').html(commentTypeVO.tidComment);
			  		$('#tidCommentDateAppendDiv').html(commentTypeVO.tidCommentDate);
			  		$('#sisCommentAppendDiv').html(commentTypeVO.sisComment);
			  		$('#sisCommentDateAppendDiv').html(commentTypeVO.sisCommentDate);
			  		$('#immiCommentAppendDiv').html(commentTypeVO.imiComment);
			  		$('#immiCommentDateAppendDiv').html(commentTypeVO.imiCommentDate);
			  		
			  		$('#checkOffCommentAppendDiv').html(commentTypeVO.checkingOfficerComment);
			  		$('#checkOffCommentDateAppendDiv').html(commentTypeVO.checkingOfficerCommentDate);
			  		$('#oicCommentAppendDiv').html(commentTypeVO.oicComment);
			  		$('#oicCommentDateAppendDiv').html(commentTypeVO.oicCommentDate);
			  		$('#aspCommentAppendDiv').html(commentTypeVO.aspComment);
			  		$('#aspCommentDateAppendDiv').html(commentTypeVO.aspCommentDate);
			  		$('#dhaCommentAppendDiv').html(commentTypeVO.dhaComment);
			  		$('#dhaCommentDateAppendDiv').html(commentTypeVO.dhaCommentDate);
			  		$('#digCommentAppendDiv').html(commentTypeVO.digComment);
			  		$('#digCommentDateAppendDiv').html(commentTypeVO.digCommentDate);
			  		
			  		$('#polStatusText').html(commentTypeVO.polStatusText);
			  		$('#nicStatusText').html(commentTypeVO.nicStatusText);
			  		$('#cidStatusText').html(commentTypeVO.cidStatusText);
			  		$('#tidStatusText').html(commentTypeVO.tidStatusText);
			  		$('#sisStatusText').html(commentTypeVO.sisStatusText);
			  		$('#imiStatusText').html(commentTypeVO.imiStatusText);
			  		
			  		var str='';
			  		if(commentTypeVO.historyAdverseRecords.length>0){
			  			$.each(commentTypeVO.historyAdverseRecords, function(index,item){
			  				str=str + '<div>' + item + '</div>';
			  			})
			  		}
			  		$('#historyAdverseRecords').html(str);
			  		
			  		var polStr='';
			  		if(commentTypeVO.policeCommentTypeDisplayVOs.length>0){
			  			$.each(commentTypeVO.policeCommentTypeDisplayVOs, function(index,item){
			  				polStr=polStr +'<tr>'
											+ '<td>' + item.policeArea + '</td>'
											+ '<td>' + item.address + '</td>'
											+ '<td>' + item.polStatusText + '</td>'
											+ '<td>' + item.policeComment + '</td>'
											+ '<td>' + item.policeCommentDate + '</td>'
										'</tr>';
			  			});
			  		}
			  		$('#policeCommentAppendDiv').html(polStr);
			  	 }else{
			  		alert('Sorry, Could not load the comments for this Application!');
			  		clearAppendDivs();
			  	 }		
			  	 unBlockUI();
			  	 
		  		 $('#commentModalLink').click();			  		 
		  });
	}
	
	function clearAppendDivs(){
		var defaultComment='No comment is available!';
  		$('#nicCommentAppendDiv').html(defaultComment);
  		$('#cidCommentAppendDiv').html(defaultComment);
  		$('#tidCommentAppendDiv').html(defaultComment);
  		$('#sisCommentAppendDiv').html(defaultComment);
  		$('#immiCommentAppendDiv').html(defaultComment);
  		$('#oicCommentAppendDiv').html(defaultComment);
  		$('#aspCommentAppendDiv').html(defaultComment);
  		$('#checkOffCommentAppendDiv').html(defaultComment);
  		$('#dhaCommentAppendDiv').html(defaultComment);
  		$('#digCommentAppendDiv').html(defaultComment);
  		
  		$('#nicCommentDateAppendDiv').html('');
  		$('#cidCommentDateAppendDiv').html('');
  		$('#tidCommentDateAppendDiv').html('');
  		$('#sisCommentDateAppendDiv').html('');
  		$('#immiCommentDateAppendDiv').html('');
  		$('#oicCommentDateAppendDiv').html('');
  		$('#aspCommentDateAppendDiv').html('');
  		$('#checkOffCommentDateAppendDiv').html('');
  		$('#dhaCommentDateAppendDiv').html('');
  		$('#digCommentDateAppendDiv').html('');
  		
  		$('#historyAdverseRecords').html('');
  		
  		$('#nicStatusText').html('');
  		$('#cidStatusText').html('');
  		$('#tidStatusText').html('');
  		$('#sisStatusText').html('');
  		$('#imiStatusText').html('');
  		$('#polStatusText').html('');
  		$('#policeCommentAppendDiv').html('');
	}

	function saveRow(applicationId){
		$('#applicationId_clearence').val(applicationId);
		var versionId=parseInt($('#hiddenVersionId_' + applicationId).val());
		var letterContent=$.trim($('#certificateLetterContent_' + applicationId).val());
		$('#letterContentInput').val(letterContent);
		$('#versionId_clearence').val(versionId);
		$('#commentInputModalLink').click();
	}
	
	
	function toggleLetterComment(){
		var checked=$('input[name=digApprovedStatus]:checked').val();
		if(checked=='Y'){
// 			$('#letterContentTable').show();
			$('#commentInputTable').hide();
			$('#digApprovedStatus_clearence').val('Y');
		}else{
// 			$('#letterContentTable').hide();
			$('#commentInputTable').show();
			$('#digApprovedStatus_clearence').val('N');
		}
	}
	
	 function updateApplication(){
		  $('#certificateIssuanceUpdateForm').submit();
	  }
	
	function validateUpdateForm(){
		var returnValue=true;
		
		var letterContent=$.trim($('#letterContentInput').val());
		var comment=$.trim($('#commentInput').val());
		
		var checked=$('#digApprovedStatus_clearence').val();
		if(checked=='Y'){			
			if((letterContent=='' || letterContent==null || letterContent=='undefined')){
				alert('Please enter the letter content!');
				returnValue=false;
			}			
		}else{
			if((comment=='' || comment==null || comment=='undefined')){
				alert('Please enter the comment!');
				returnValue=false;
			}
		}
		
		 
		 if(returnValue){
			 var conf=confirm('Are you sure you want to save this record?');
			 if(!(conf)){
				 returnValue=false;
			 }
		 }
		
		 return returnValue;
	}
	
	function editRow(applicationId,referenceNo){
		 blockUI();
		 $.get('checkAndLockClearenceRecord',{applicationId:applicationId},function(data){
			  var recordLockstatus=data.recordLockstatus;
			  var userName=data.lockedUserName;
			  if(recordLockstatus=='NO_RECORDS_TO_LOCK'){
				  alert('Internal Error!');
			  }else if(recordLockstatus=='RECORD_IS_LOCKED_BY_ANOTHER_USER'){
				  alert('Sorry, this record is already locked by ' + userName + '!');				  
			  }else if(recordLockstatus=='ONE_RECORD_IS_ALREADY_LOCKED'){
				  alert('Sorry, you have already locked another record!');
			  }else if(recordLockstatus=='ERROR'){
				  alert('Internal Error!');
			  }else if(recordLockstatus=='SUCCESS'){
				  var defaultView=$('#defaultView').val();
				  if(defaultView==true || defaultView=='true'){
					  $('#defaultViewFromUi').val(1);
				  }				  
				  $('#referenceNo').val(referenceNo);
				  $('#lockedRefresh').val(1);
				  $('#maxId').val(0);
				  $('#myForm').submit();
			  }			  
			  unBlockUI();
		 });
	 }
	
	
	 
	 function cancelEdit(applicationId){
		 blockUI();
		 $.get('checkAndRemoveLockClearence',{applicationId:applicationId},function(data){
			  var recordLockstatus=data.recordLockstatus;				 		
			  if(recordLockstatus=='SUCCESS'){
				  var defaultView=$('#defaultView').val();
				  if(defaultView==true || defaultView=='true'){
					  $('#defaultViewFromUi').val(1);
				  }	
				  $('#myForm').submit();
			  }			  
			  unBlockUI();
		 });
	 }
	  
	 
	  

	</script>
</body>
</html>
</s:i18n>