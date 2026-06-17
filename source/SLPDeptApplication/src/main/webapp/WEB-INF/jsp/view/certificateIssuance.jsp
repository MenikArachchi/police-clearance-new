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
				<c:set var="pageTitle">Review Application</c:set>
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
						<s:form cssClass="form-horizontal" id="myForm"  theme="simple" action="searchCertificateIssuanceApplication.action" onsubmit="return validateForm()"> 
							<input type="hidden" name="searchCriteriaVO.startFrom" id="startFrom" value="0" />
							<input type="hidden" name="searchCriteriaVO.limit" id="limit" value="20" />
							<input type="hidden" name="searchCriteriaVO.defaultViewFromUi" id="defaultViewFromUi" value="0" />
							<input type="hidden" name="searchCriteriaVO.defaultView" id="defaultView" value="${searchCriteriaVO.defaultView}" />
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
											<input type="submit" value="Search" class="btn btn-primary es-buttton" id="searchCertificateIssuanceApplication" />
										</div>
				   					</div>
			   					</div>
							</div>
							
							</s:form>
							<div style="clear: both;"></div>
							<div class="form-group">
							
							<c:if test="${! empty applicationList}">							
									<div class="col-lg-13">	
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
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
														
														<c:if test="${userType==userTypeDisplayVO.checkingOfficer 
																|| userType==userTypeDisplayVO.oicUser
																	|| userType==userTypeDisplayVO.aspUser
																		|| userType==userTypeDisplayVO.dhaUser}">																				
															<th class="text-center"><strong>Clearance Status</strong></th>
														</c:if>
														
														<c:if test="${userType==userTypeDisplayVO.checkingOfficer}">	
															<th class="text-center"><strong>Print/Preview Certificate</strong></th>
														</c:if>
														
														<c:if test="${userType==userTypeDisplayVO.oicUser
																	|| userType==userTypeDisplayVO.aspUser
																		|| userType==userTypeDisplayVO.dhaUser}">	
															<th class="text-center"><strong>OIC Approved</strong></th>
														</c:if>
														
														<c:if test="${userType==userTypeDisplayVO.aspUser || userType==userTypeDisplayVO.dhaUser}">	
															<th class="text-center"><strong>ASP Approved</strong></th>
														</c:if>
														
														<c:if test="${userType==userTypeDisplayVO.dhaUser || userType==userTypeDisplayVO.issuingOfficer}">	
															<th class="text-center"><strong>DHA Approved</strong></th>
														</c:if>
														
														
														<c:if test="${userType==userTypeDisplayVO.issuingOfficer}">
															<th class="text-center"><strong>Enter Certificate Serial #</strong></th>
														</c:if>	
														
														<c:if test="${userType==userTypeDisplayVO.postingOfficer}">
															<th class="text-center"><strong>Enter Reg. Post #</strong></th>
														</c:if>	
															
														<th class="text-center"><strong>Save</strong></th>	
														
														<c:if test="${userType==userTypeDisplayVO.postingOfficer}">
															<th class="text-center"><strong>Print Address</strong></th>
														</c:if>	
														
													</tr>
												</thead>
												<tbody>
													<c:set var="canAnyAddressBePrinted">0</c:set>
													<c:forEach items="${applicationList}" var="applicationVO">
																					
															<tr>
																<td class="text-center" style="vertical-align: middle;">
																	<input type="hidden" id="canEditClearence_${applicationVO.applicationId}" value="${applicationVO.canEditClearence}" />
																	<input type="hidden" id="canOicApprove_${applicationVO.applicationId}" value="${applicationVO.canOicApprove}" />
																	<input type="hidden" id="canAspApprove_${applicationVO.applicationId}" value="${applicationVO.canAspApprove}" />
																	<input type="hidden" id="canDHASign_${applicationVO.applicationId}" value="${applicationVO.canDHASign}" />
																	<input type="hidden" id="canEnterCertificateSerial_${applicationVO.applicationId}" value="${applicationVO.canEnterCertificateSerial}" />
																	<input type="hidden" id="canEditRegPost_${applicationVO.applicationId}" value="${applicationVO.canEditRegPost}" />
																	<input type="hidden" id="canPrintAddress_${applicationVO.applicationId}" value="${applicationVO.canPrintAddress}" />
																	<input type="hidden" id="canPrintCertificate_${applicationVO.applicationId}" value="${applicationVO.canPrintCertificate}" />
																	
																	<input type="hidden" id="oldClearenceStatus_${applicationVO.applicationId}" value="${applicationVO.applicationClearanceStatus}" />																	
																	<input type="hidden" id="oldOicApprovedStatus_${applicationVO.applicationId}" value="${applicationVO.oicApproved}" />
																	<input type="hidden" id="oldAspApprovedStatus_${applicationVO.applicationId}" value="${applicationVO.aspApproved}" />
																	<input type="hidden" id="oldDhaApprovedStatus_${applicationVO.applicationId}" value="${applicationVO.dhaApproved}" />
																	
																	<input type="hidden" id="oldCertificateSerialNo_${applicationVO.applicationId}" value="${applicationVO.certificateSerialNo}" />
																	<input type="hidden" id="oldRegPostNo_${applicationVO.applicationId}" value="${applicationVO.regPostNo}" />
																	
																	
																	<input type="hidden" id="constructedComment_${applicationVO.applicationId}" value="${applicationVO.constructedComment}" />
																	<input type="hidden" id="commentAvailable_${applicationVO.applicationId}" value='<c:out escapeXml="false"  value="${applicationVO.commentAvailable}"></c:out>' />
																	
																	<input type="hidden" id="hiddenVersionId_${applicationVO.applicationId}" value="${applicationVO.versionId}" />
																	
																	<c:choose>
																		<c:when test="${applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/lock.png"  alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="cancelEdit(${applicationVO.applicationId})" />
																		</c:when>
																		<c:otherwise>
																			<img src="images/unlock.png" alt="Select" title="Select" id="lockUnlockBtn_${applicationVO.applicationId}" class="basic_image_button" onclick="editRow(${applicationVO.applicationId})" />
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
																
																
																<c:if test="${userType==userTypeDisplayVO.checkingOfficer 
																		|| userType==userTypeDisplayVO.oicUser
																			|| userType==userTypeDisplayVO.aspUser
																				|| userType==userTypeDisplayVO.dhaUser}">	
																	<td class="text-center" style="vertical-align: middle;">
																	
																		<c:choose>
																			<c:when test="${applicationVO.canEditClearence==1 &&  applicationVO.hasCurrentUserLocked==1}">
																				<select id="clearenceStatusSelect_${applicationVO.applicationId}" name="clearenceStatusInput" class="form-control">
																					<c:forEach items="${applicationVO.clearenceStatusMap}" var="clearenceStatus">
																						<c:choose>
														   									<c:when test="${clearenceStatus.key == applicationVO.applicationClearanceStatus}">
														   										<option value="${clearenceStatus.key}" selected="selected">${clearenceStatus.value.displayName}</option>
														   									</c:when>
														   									<c:otherwise>
														   										<option value="${clearenceStatus.key}">${clearenceStatus.value.displayName}</option>
														   									</c:otherwise>
														   								</c:choose>
																					</c:forEach>
																				</select>																		
																			</c:when>
																			<c:otherwise>
																				<select id="clearenceStatusSelect_${applicationVO.applicationId}" name="clearenceStatusInput" disabled="disabled" readonly="readonly" class="form-control">
																					<c:forEach items="${applicationVO.clearenceStatusMap}" var="clearenceStatus">
																						<c:choose>
														   									<c:when test="${clearenceStatus.key == applicationVO.applicationClearanceStatus}">
														   										<option value="${clearenceStatus.key}" selected="selected">${clearenceStatus.value.displayName}</option>
														   									</c:when>
														   									<c:otherwise>
														   										<option value="${clearenceStatus.key}">${clearenceStatus.value.displayName}</option>
														   									</c:otherwise>
														   								</c:choose>
																					</c:forEach>
																				</select>																			
																			</c:otherwise>
																		</c:choose>
																	 </td>
																</c:if>
																
																<c:if test="${userType==userTypeDisplayVO.checkingOfficer}">
																	<td class="text-center" style="vertical-align: middle;">
																		<c:choose>
																			<c:when test="${applicationVO.canPrintCertificate==1 &&  applicationVO.hasCurrentUserLocked==1}">	
																				<img src="images/print.png" alt="Print" id="printCertificateImage_${applicationVO.applicationId}" class="basic_image_button_smaller" title="Print" onclick="printCertificate(${applicationVO.applicationId})" />
																			</c:when>
																			<c:otherwise>
																				<img src="images/print.png" alt="Print" id="printCertificateImage_${applicationVO.applicationId}" class="basic_image_button_smaller disabled_image" title="Print" onclick="javascript:void(0)"/>
																			</c:otherwise>
																		</c:choose>															
																	</td>
																</c:if>
																
																<c:if test="${userType==userTypeDisplayVO.oicUser
																			|| userType==userTypeDisplayVO.aspUser
																				|| userType==userTypeDisplayVO.dhaUser}">
																				
																			<c:set var="oicYesCheckedStatus" value="false"/>
																			<c:set var="oicNoCheckedStatus" value="false"/>																				
																			<c:choose>
																				<c:when test="${applicationVO.oicApproved =='AP' }">
																					<c:set var="oicYesCheckedStatus" value="checked"/>
																				</c:when>
																				<c:when test="${applicationVO.oicApproved =='RJ' }">
																					<c:set var="oicNoCheckedStatus" value="checked"/>
																				</c:when>
																			</c:choose>
																																					
																			<td class="text-center" style="vertical-align: middle;">																	
																				<c:choose>
																					<c:when test="${applicationVO.canOicApprove==1 && applicationVO.hasCurrentUserLocked == 1}">
																						<table>
																							<tr>
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${oicYesCheckedStatus}" type="radio" name="oicApproveStatus_${applicationVO.applicationId}" value="Y"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${oicNoCheckedStatus}" type="radio" name="oicApproveStatus_${applicationVO.applicationId}" value="N"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:when>
																					<c:otherwise>
																						<table>
																							<tr>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${oicYesCheckedStatus}" name="oicApproveStatus_${applicationVO.applicationId}" value="Y" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${oicNoCheckedStatus}" name="oicApproveStatus_${applicationVO.applicationId}" value="N" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:otherwise>
																				</c:choose>																	
																			</td>
																</c:if>
																
																
																<c:if test="${userType==userTypeDisplayVO.aspUser || userType==userTypeDisplayVO.dhaUser}">
																			<c:set var="aspYesCheckedStatus" value="false"/>
																			<c:set var="aspNoCheckedStatus" value="false"/>																				
																			<c:choose>
																				<c:when test="${applicationVO.aspApproved =='AP' }">
																					<c:set var="aspYesCheckedStatus" value="checked"/>
																				</c:when>
																				<c:when test="${applicationVO.aspApproved =='RJ' }">
																					<c:set var="aspNoCheckedStatus" value="checked"/>
																				</c:when>
																			</c:choose>
																			
																			<td class="text-center" style="vertical-align: middle;">																	
																				<c:choose>
																					<c:when test="${applicationVO.canAspApprove==1 && applicationVO.hasCurrentUserLocked == 1}">
																						<table>
																							<tr>
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${aspYesCheckedStatus}" type="radio" name="aspApproveStatus_${applicationVO.applicationId}" value="Y"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${aspNoCheckedStatus}" type="radio" name="aspApproveStatus_${applicationVO.applicationId}" value="N"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:when>
																					<c:otherwise>
																						<table>
																							<tr>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${aspYesCheckedStatus}" name="aspApproveStatus_${applicationVO.applicationId}" value="Y" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${aspNoCheckedStatus}" name="aspApproveStatus_${applicationVO.applicationId}" value="N" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:otherwise>
																				</c:choose>																	
																			</td>
																</c:if>
																
																<c:if test="${userType==userTypeDisplayVO.dhaUser || userType==userTypeDisplayVO.issuingOfficer}">
																			<c:set var="dhaYesCheckedStatus" value="false"/>
																			<c:set var="dhaNoCheckedStatus" value="false"/>																				
																			<c:choose>
																				<c:when test="${applicationVO.dhaApproved =='AP' }">
																					<c:set var="dhaYesCheckedStatus" value="checked"/>
																				</c:when>
																				<c:when test="${applicationVO.dhaApproved =='RJ' }">
																					<c:set var="dhaNoCheckedStatus" value="checked"/>
																				</c:when>
																			</c:choose>
																			<td class="text-center" style="vertical-align: middle;">																	
																				<c:choose>
																					<c:when test="${applicationVO.canDHASign==1 && applicationVO.hasCurrentUserLocked == 1}">
																						<table>
																							<tr> 
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${dhaYesCheckedStatus}" type="radio" name="dhaApproveStatus_${applicationVO.applicationId}" value="Y"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input style="cursor: pointer;" checked="${dhaNoCheckedStatus}" type="radio" name="dhaApproveStatus_${applicationVO.applicationId}" value="N"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:when>
																					<c:otherwise>
																						<table>
																							<tr>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${dhaYesCheckedStatus}" name="dhaApproveStatus_${applicationVO.applicationId}" value="Y" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">Y</td>
																								<td>&nbsp;&nbsp;</td>
																								<td style="vertical-align: middle;"><input type="radio" style="cursor: pointer;" checked="${dhaNoCheckedStatus}" name="dhaApproveStatus_${applicationVO.applicationId}" value="N" readonly="readonly" disabled="disabled"></td>
																								<td style="vertical-align: middle;">N</td>
																							</tr>
																						</table>
																					</c:otherwise>
																				</c:choose>																	
																			</td>
																</c:if>
																
																<c:if test="${userType==userTypeDisplayVO.issuingOfficer}">
																	<td class="text-center" style="vertical-align: middle;">
																		<c:choose>
																			<c:when test="${applicationVO.canEnterCertificateSerial==1 && applicationVO.hasCurrentUserLocked == 1}">
																				<input type="text" id="certificateSerialNo_${applicationVO.applicationId}" value="${applicationVO.certificateSerialNo}" class="form-control" />
																			</c:when>
																			<c:otherwise>
																				<input type="text" id="certificateSerialNo_${applicationVO.applicationId}" value="${applicationVO.certificateSerialNo}" class="form-control" readonly="readonly" disabled="disabled" />
																			</c:otherwise>
																		</c:choose>																		
																	</td>
																</c:if>	
																
																<c:if test="${userType==userTypeDisplayVO.postingOfficer}">
																	<td class="text-center" style="vertical-align: middle;">
																		<c:choose>
																			<c:when test="${applicationVO.canEditRegPost==1 && applicationVO.hasCurrentUserLocked == 1}">
																				<input type="text" id="regPostNo_${applicationVO.applicationId}" value="${applicationVO.certificateSerialNo}" class="form-control"/>
																			</c:when>
																			<c:otherwise>
																				<input type="text" id="regPostNo_${applicationVO.applicationId}" value="${applicationVO.certificateSerialNo}" class="form-control" readonly="readonly" disabled="disabled" />
																			</c:otherwise>
																		</c:choose>	
																	</td>
																</c:if>	
																
																
																<td class="text-center" style="vertical-align: middle;">															
																	<c:choose>
																		<c:when test="${applicationVO.hasCurrentUserLocked == 1}">
																			<img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button" onclick="saveRow(${applicationVO.applicationId})" />												
																		</c:when>
																		<c:otherwise>
																			<img src="images/save.png" style="width: 20px;height:20px;cursor: pointer;" id="save_image_${applicationVO.applicationId}" alt="Save" title="Save" class="basic_image_button disabled_image" />					
																		</c:otherwise>												
																	</c:choose> 								
																</td>
																
																<c:if test="${userType==userTypeDisplayVO.postingOfficer}">
																
																	<td class="text-center" style="vertical-align: middle;">
																		<c:choose>
																			<c:when test="${applicationVO.canPrintAddress==1}">
																				<c:set var="canAnyAddressBePrinted">1</c:set>
																				<input type="checkbox" id="printAddress_${applicationVO.applicationId}" value="${applicationVO.applicationId}" name="printAddressId" />
																			</c:when>
																			<c:otherwise>
																				<input type="checkbox" id="printAddress_${applicationVO.applicationId}" readonly="readonly" disabled="disabled" />
																			</c:otherwise>
																		</c:choose>																			
																	</td>
																</c:if>	
																
															</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>	
										
										<div style="margin: 10px">
											<div class="col-lg-7">&nbsp;</div>
											<div class="col-lg-5" style="text-align: right;">
												<c:choose>
													<c:when test="${canAnyAddressBePrinted ==1}">
														<form action="printAddresses.action" id="printAddressSubmitForm">
															<div id="hiddenInputAppendDiv"></div>
															<input type="button" onclick="return validateAddressPrintForm()" value="Print Addresses" class="btn btn-primary es-buttton">
															<input type="button" value="Refresh" onclick="refreshPage()" class="btn  btn-primary es-buttton">
														</form>
													</c:when>
													<c:otherwise>
														<input type="button" onclick="return false" disabled="disabled" value="Print Addresses" class="btn btn-primary es-buttton">
														<input type="button" value="Refresh" onclick="refreshPage()" class="btn btn-primary es-buttton">
													</c:otherwise>
												</c:choose>												
											</div>
										</div>
										<div style="clear: both;"></div>
										<br />
									</div>
									
									
							
							
								<c:if test="${! empty gridButtonMap}">	
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
													<c:forEach items="${gridButtonMap}" var="entry">
														<td style="padding-left: 5px;">
															<c:choose>
																<c:when test="${currentButton== entry.key}">
																	<input type="button" value="${entry.key}" disabled="disabled" class="btn btn-default">
																</c:when>
																<c:otherwise>
																	<input type="button" onclick="goToSelectedPage(${entry.value})" value="${entry.key}" class="btn btn-primary">
																</c:otherwise>
															</c:choose>											
														</td>
													</c:forEach>
												</tr>
											</table>
										</div>
									</c:if>
							
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
		
		<div style="display: none;">
			<a data-toggle="modal" id="commentInputModalLink" href="#commentInputModalPopUp">View</a>
			<a data-toggle="modal" id="commentModalLink" href="#commentModalPopUp">View</a>
			<a data-toggle="modal" id="pptViewModellLink" href="#pptViewModelPopUp">View</a>
			<a data-toggle="modal" id="nicViewModellLink" href="#nicViewModelPopUp">View</a>
			<a data-toggle="modal" id="bcViewModellLink" href="#bcViewModelPopUp">View</a>
			<a data-toggle="modal" id="slbfeViewModellLink" href="#slbfeViewModelPopUp">View</a>
			<a data-toggle="modal" id="nicRevisionModellLink" href="#nicRevisionModelPopUp">View</a>
		</div>
		
		<!--  #####################################################	NIC REVISION MODEL POPUP   ######################################################## -->
		<div class="modal fade" id="nicRevisionModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
			 <form action="updateNicrevisionClearence.action" method="post" id="nicrevisionUpdateForm">	
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">Insert Comment</h4>
				</div>
				<div class="modal-body">
					
							<s:hidden name="nicRevisionClearenceVO.applicationId" id="applicationId_nicRevision" />
							<s:hidden name="nicRevisionClearenceVO.nicRevisionClearenceStatus" id="clearenceStatus_nicRevision" />
													
							<input type="hidden" name="nicRevisionVersionId" id="versionId_nicRevision" />					
					
							<table class="table borderless" id="nicRevisionTable">
									<tr>
										<td><b>Changed Name:</b></td>
										<td>
											<input type="text" name="nicRevisionClearenceVO.nicRevisionChangedName" class="form-control" id="nicRevisionChangedName" />
										</td>
										<td>&nbsp;</td>										
									</tr>
									<tr>
										<td><b>Upload Copy:</b></td>
										<td>
											<input id="nicFileUpload" name="nicFileUpload" type="file" style=""/>
		   									<s:hidden name="nicRevisionClearenceVO.nicFileName" id="nicUploadPath" cssClass="form-control" />	
										</td>
										<td>
											<img src="images/upload.png" style="cursor: pointer;"  onclick="uploadFile();" height="35px"/>
						   					<img src="images/ajax-loader.gif" id="ajax_loader_nic" style="display:none;"/>
					        				<img src="images/right_green.jpg" id="upload_complete_nic" style="display:none;"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="2">
											 <div id="image_link_click_nic_revision" style="display: none;">
												Please click 
												<a id="nicRevisionFileName" target="_blank" href="javascript:void(0)">
													<span style="font-weight:bold;text-decoration:underline; ">here</span>
												</a>
												to Open/Download file	
											 </div>
											 <div>
											 	<img src="images/id_card_icon.png" id="nicRevisionImageView" style="height: 100px;width:135px;" />											 
											 </div>											 
										</td>																			
									</tr>									
									<tr><td colspan="3">&nbsp;</td></tr>
									<tr>
										<td colspan="3">
											<table>
												<tr>
													<td valign="middle" style="vertical-align: middle;padding: 2px;"><input type="checkbox" name="acceptCurrentName" id="acceptCurrentName" /></td>
													<td valign="middle" style="vertical-align: middle;padding:5px 2px 0px 2px;">Accept Current Name</td>
												</tr>
											</table>
										</td>
									</tr>
							</table>				
				 </div>
				
				 <div class="modal-footer">
				 	<button type="button" onclick="updateNICRevision()" class="btn btn-primary">OK</button>
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
				 </div>
				 
				</div>
				</form>
			</div>
		</div>
		
		<!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
		<div class="modal fade" id="commentInputModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
			 <form action="updateCertificateIssuance.action" method="post" id="certificateIssuanceUpdateForm" onsubmit="return validateUpdateForm()">	
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <h4 class="modal-title">Insert Comment</h4>
				</div>
				<div class="modal-body">
					
							<s:hidden name="applicationVO.applicationId"/>							
							<s:hidden name="clearenceVO.applicationId" id="applicationId_clearence" />
							<s:hidden name="clearenceVO.clearenceStatus" id="clearenceStatus_clearence" />
							<s:hidden name="clearenceVO.oicApprovedStatus" id="oicApprovedStatus_clearence" />
							<s:hidden name="clearenceVO.aspApprovedStatus" id="aspApprovedStatus_clearence" />
							<s:hidden name="clearenceVO.dhaApprovedStatus" id="dhaApprovedStatus_clearence" />
							<s:hidden name="clearenceVO.certificateSeriaNo" id="certificateSeriaNo_clearence" />
							<s:hidden name="clearenceVO.registeredPostNo" id="registeredPostNo_clearence" />
							
							<input type="hidden" name="clearenceVO.versionId" id="versionId_clearence" />					
					
							<table class="table borderless" id="recommendedOfficerNameTable" style="display: none;">
									<tr><td><b>Recommended Ofiicer:</b></td></tr>
									<tr><td><input type="text" name="clearenceVO.recomendedOfficerName" value="${currentUserFullName}" class="form-control" id="recommendedOfficerName" /></td></tr>
									<tr><td>&nbsp;</td></tr>
							</table>
							<table class="table borderless" id="commentInputTable">
									<tr><td><b>Please add a comment for your action:</b></td></tr>
									<tr><td><textarea name="clearenceVO.comment" id="commentInput" class="form-control"></textarea></td></tr>
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
							<tr><td colspan="2"><b>NIC:</b></td></tr>
							<tr>
								<td id="nicCommentAppendDiv">No comment is available!</td>
								<td id="nicCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>CID:</b></td></tr>
							<tr>
								<td id="cidCommentAppendDiv">No comment is available!</td>
								<td id="cidCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>TID:</b></td></tr>
							<tr>
								<td id="tidCommentAppendDiv">No comment is available!</td>
								<td id="tidCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>SIS:</b></td></tr>
							<tr>
								<td id="sisCommentAppendDiv">No comment is available!</td>
								<td id="sisCommentDateAppendDiv"></td>
							</tr>
							<tr><td colspan="2"><b>Immigration:</b></td></tr>
							<tr>
								<td id="immiCommentAppendDiv">No comment is available!</td>
								<td id="immiCommentDateAppendDiv"></td>
							</tr>
					</table>
					<table class="table borderless" id="emailSentStatusTable" style="margin-top: 5px;display: none;">
							<tr><td><b>NIC Revision Requirement:</b></td></tr>
							<tr><td>
								<input type="hidden" id="notificationemailsent_applicationId" />
								<textarea rows="7" name="emailText" id="emailText" class="form-control"></textarea>
							</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td>
								<table>
									<tr>
										<td valign="middle" style="vertical-align: middle;padding: 2px;"><input type="checkbox" name="doSendEmail" id="doSendEmail" /></td>
										<td valign="middle" style="vertical-align: middle;padding:5px 2px 0px 2px;">Email Applicant</td>
									</tr>
								</table>
							</td></tr>
							<tr><td>&nbsp;</td></tr>
					</table>				
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" onclick="checkAndUpdateMailSentStatus()" class="btn btn-primary">OK</button>
				 	<button type="button" style="display: none;" id="commentModalCloseButton" class="btn btn-primary" data-dismiss="modal">&nbsp;</button>
				 </div>
				</div>
			</div>
		</div>
		
		<!--  #####################################################	PPT VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="pptViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >Passport Verification</span>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<span style="font-size:18px;font-weight:bold;" >Passport No. <span id="passportNumberAppend"></span></span>
					</div>
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned Passport Copy from Applicant</span>
						</div>
						<div id="passportLinkDiv" >							
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
									<a id="passportFileName" target="_blank" href="javascript:void(0)">
										<span style="font-weight:bold;text-decoration:underline; ">here</span>
									</a>
								 to Open/Download file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="passportImge_link image-link" id="passportImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="passportImge" style="width:275px;height:280px;border: 1px solid #000;" /> 
								</a>	
								
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		<!--  #####################################################	NIC VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="nicViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >NIC Verification</span>
				</div>
				<div class="modal-body">
					<div style="text-align: center;  ">
						<span style="font-size:18px;font-weight:bold;" >NIC No. <span id="nicNumberAppend"></span></span>
					</div>
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned NIC Copy from Applicant</span>
						</div>
						<div id="nicLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="nicFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="nicImge_link image-link" id="nicImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="nicImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		
		<!--  #####################################################	BC VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="bcViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >Birth certificate</span>
				</div>
				<div class="modal-body">					
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned Birth certificate from Applicant</span>
						</div>
						<div id="bcLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="bcFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="bcImge_link image-link" id="bcImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="bcImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		
		<!--  #####################################################	SLBFE VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="slbfeViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >SLBFE Letter</span>
				</div>
				<div class="modal-body">					
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned SLBFE Letter from Applicant</span>
						</div>
						<div id="slbfeLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="slbfeFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="slbfeImge_link image-link" id="slbfeImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="slbfeImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		
		
		
		<div style="display: none;" id="hiddenForms">
			<form action="printcertificate.action" id="hiddenFormPrintCertificate">
				<input type="hidden" name="applicationId" readonly="readonly" id="printCertificateFormApplicationId" value="0" />
			</form>	
		</div>
		
		<!-- including footer -->
		<jsp:include page="../common/footer.jsp" />

	</div>
	<script type="text/javascript" src="js/jquery.numeric.js" ></script>
	<script language="javascript" type="text/javascript">
	
	var canEditClearence=0;
	var canOicApprove =0;
	var canAspApprove =0;
	var canDHASign =0;
	var canEnterCertificateSerial =0;
	var canEditRegPost =0;
	var canPrintAddress =0;
	var canPrintCertificate = 0;

	$(document).ready(function() {
		initializeDateTimePickers();		
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
	
	function refreshPage(){
		var conf=confirm('Are you sure you want to refersh the page?');
		if(conf){
			$('#myForm').submit();
		}
	}
	
	function validateAddressPrintForm(){
		var oneAvailable=0;
		$('#hiddenInputAppendDiv').html('');
		$('input[name="printAddressId"]:checked').each(function() {
		   var applicationId=parseInt(this.value);
		   if(applicationId>0){
			   oneAvailable=1;
			   $('#hiddenInputAppendDiv').append('<input type="text" value="' + applicationId + '" name="applicationIdList" />');
		   }
		});
		if(oneAvailable>0){
			var conf=confirm('Are you sure you want to print the selected addresses?');
			if(conf){
				$('#printAddressSubmitForm').submit();
			}else{
				return false;
			}
		}else{
			alert('Please select the addresses to be printed!');
			return false;
		}
		
	}
	
	function viewCommentsList(applicationId){
		 blockUI();
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
			  		
			  	 }else{
			  		alert('Sorry, Could not load the comments for this Application!');
			  		var defaultComment='No comment is available!';
			  		$('#nicCommentAppendDiv').html(defaultComment);
			  		$('#cidCommentAppendDiv').html(defaultComment);
			  		$('#tidCommentAppendDiv').html(defaultComment);
			  		$('#sisCommentAppendDiv').html(defaultComment);
			  		$('#immiCommentAppendDiv').html(defaultComment);
			  		$('#nicCommentDateAppendDiv').html('');
			  		$('#cidCommentDateAppendDiv').html('');
			  		$('#tidCommentDateAppendDiv').html('');
			  		$('#sisCommentDateAppendDiv').html('');
			  		$('#immiCommentDateAppendDiv').html('');
			  		$('#oicCommentDateAppendDiv').html('');
			  		$('#aspCommentDateAppendDiv').html('');
			  	 }		
			  	 unBlockUI();
			  	 
			  	 $('#emailSentStatusTable').hide();
			  	 var commentAvailable=parseInt($('#commentAvailable_' + applicationId).val());
			  	  if(!(isNaN(commentAvailable))){
			  		  if(commentAvailable==1){			  			
			  			$('#emailText').val($.trim($('#constructedComment_' + applicationId).val()));
			  			$('#notificationemailsent_applicationId').val(applicationId);
			  			$('#emailSentStatusTable').show();
			  		  }
			  	  }
			  	 
		  		 $('#commentModalLink').click();			  		 
		  });
	}
	
	function checkAndUpdateMailSentStatus(){
		blockUI();
		var isChekd=$("#doSendEmail").is(':checked');
		if(isChekd){
			var emailText=$.trim($('#emailText').val());
			if(!(emailText=='' || emailText==null || emailText=='undefined')){
				var applicationId=$('#notificationemailsent_applicationId').val();
				if(!(applicationId==0)){
					 $.post('updateEmailSentStatus.action',{applicationId:applicationId,emailText:emailText},function(data){
						 var status=data.emailSentStatus;
						 if(status=='SUCCESS'){
							 alert('Email was sent successfully!')
							 $('#emailText').val('');
			  				 $('#notificationemailsent_applicationId').val(0);
			  				 $("#doSendEmail").attr('checked',false);
							 $('#commentModalCloseButton').click();							 
						 }else if(status=='PLEASE ENTER EMAIL TEXT'){
							 alert('Please enter the email text!');
						 }else{
							 alert('Internal error occrred, email could not be sent!');
						 }
						 unBlockUI();
					 });					 
				}else{
					alert('Please select the application!');
					unBlockUI();
				}				 
			}else{
				alert('Please enter the email text!');
				unBlockUI();
			}		
		}else{
			$('#emailText').val('');
		    $('#notificationemailsent_applicationId').val(0);
		    $("#doSendEmail").attr('checked',false);
			$('#commentModalCloseButton').click();
			unBlockUI();
		}		
	}
	
	
	function updateNICRevision(){
		var isChekd=$("#acceptCurrentName").is(':checked');
		var isValid=true;
		if(!(isChekd)){
			var changedName=$.trim($('#nicRevisionChangedName').val());
			if(changedName==null || changedName=='' || changedName=='undefined'){
				alert('Please enter the changed name or accept the current name to proceed!');
				isValid=false;
			}
		}else{
			isValid=true;
		}
		
		if(isValid){
			$('#nicrevisionUpdateForm').submit();
		}
		
	}
	
	
	function printCertificate(applicationId){
		$('#printCertificateFormApplicationId').val(applicationId);
		$('#hiddenFormPrintCertificate').submit();
	}
	
	function saveRow(applicationId){
		$('#recommendedOfficerNameTable').hide();
		$('#applicationId_clearence').val(applicationId);
		
		var versionId=parseInt($('#hiddenVersionId_' + applicationId).val());
		
		var hasAnyChanged=false;
		intializeEditableStatus(applicationId);
		//try and enable clearence status 
		if(canEditClearence==1){
			var clearenceStatusSelect=$.trim($('#clearenceStatusSelect_' + applicationId).val());	
			$('#clearenceStatus_clearence').val(clearenceStatusSelect);
			
			var oldStaus=$.trim($('#oldClearenceStatus_' + applicationId).val());	
			
			if(!(clearenceStatusSelect==oldStaus)){
				if(clearenceStatusSelect=='GC'){
					$('#recommendedOfficerNameTable').show();
				}
				hasAnyChanged=true;
			}
		}
		
		 if(canOicApprove==1){
			 var inputName='oicApproveStatus_' + applicationId;
			 var status=$("input:radio[name=" + inputName + "]:checked").val();
			 $('#oicApprovedStatus_clearence').val(status);
			 
			 var oldStaus=$.trim($('#oldOicApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 hasAnyChanged=true;
			 }
		 }
		 
		 if(canAspApprove==1){
			 var inputName='aspApproveStatus_' + applicationId;
			 var status=$("input:radio[name=" + inputName + "]:checked").val();
			 $('#aspApprovedStatus_clearence').val(status);
			 
			 var oldStaus=$.trim($('#oldAspApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 hasAnyChanged=true;
			 }
		 }
		 
		 if(canDHASign==1){
			 var inputName='dhaApproveStatus_' + applicationId;
			 var status=$("input:radio[name=" + inputName + "]:checked").val();
			 $('#dhaApprovedStatus_clearence').val(status);
			 
			 var oldStaus=$.trim($('#oldDhaApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 hasAnyChanged=true;
			 }
		 }
		
		 if(canEnterCertificateSerial==1){
			 var cerialNo=$.trim($('#certificateSerialNo_' + applicationId).val());	
			 $('#certificateSeriaNo_clearence').val(cerialNo);
			 
			 var oldCerialNo=$.trim($('#oldCertificateSerialNo_' + applicationId).val());
			 if(!(cerialNo==oldCerialNo)){
				 hasAnyChanged=true;
			 }
		 }
		 
		 if(canEditRegPost==1){
			 var regPostNo=$.trim($('#regPostNo_' + applicationId).val());	
			 $('#registeredPostNo_clearence').val(regPostNo);
			 
			 var oldRegPostNo=$.trim($('#oldCertificateSerialNo_' + applicationId).val());
			 if(!(regPostNo==oldRegPostNo)){
				 hasAnyChanged=true;
			 }
		 }
		 
		 
		
		 if(hasAnyChanged){
			 var hasOtherModelLoaded=false;
			 if(canEditClearence==1){
					var clearenceStatusSelect=$.trim($('#clearenceStatusSelect_' + applicationId).val());	
					var oldStaus=$.trim($('#oldClearenceStatus_' + applicationId).val());	
					
					if(!(clearenceStatusSelect==oldStaus)){
						if(oldStaus=='NR' && clearenceStatusSelect=='CL'){
							hasOtherModelLoaded=true;
							 $('#versionId_nicRevision').val(versionId);
							 $('#applicationId_nicRevision').val(applicationId);
							 $('#clearenceStatus_nicRevision').val(clearenceStatusSelect);
							 $('#nicRevisionModellLink').click();
						}
					}
			 }
			 
			 if(!(hasOtherModelLoaded)){
				 $('#versionId_clearence').val(versionId);
				 $('#commentInputModalLink').click();
			 }
			 
			 
		 }else{
			 alert('There are no changes to be saved!');
		 }		  
	}
	
	function validateUpdateForm(){
		var returnValue=true;
		
		var isCommentEmpty=false;
		var comment=$.trim($('#commentInput').val());
		if((comment=='' || comment==null || comment=='undefined')){
			isCommentEmpty=true;
		}
		
		var applicationId=$('#applicationId_clearence').val();
		intializeEditableStatus(applicationId);
		
		//try and enable clearence status 
		if(canEditClearence==1){
			var clearenceStatus=$('#clearenceStatus_clearence').val();
			
			var oldStaus=$.trim($('#oldClearenceStatus_' + applicationId).val());	
			
			if(!(clearenceStatus==oldStaus)){
				if(clearenceStatus=='GC'){
					var recommendedOfficer=$.trim($('#recommendedOfficerName').val());
					if((recommendedOfficer==null || recommendedOfficer=='' || isCommentEmpty)){
						returnValue=false;
						alert('Please enter the comment and the recommended officer name!');
					}
				}else if(clearenceStatus=='BL' || clearenceStatus=='RJ'){
					if(isCommentEmpty){
						returnValue=false;
						alert('Please enter the comment!');
					}
				}				
			}
		}
		
		 if(canOicApprove==1){
			 var status=$("oicApprovedStatus_clearence").val();
			 var oldStaus=$.trim($('#oldOicApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 if(status=='N'){
					 if(isCommentEmpty){
						 returnValue=false;
						 alert('Please enter the comment!');
					 }
				 }
			 }
		 }
		 
		 if(canAspApprove==1){
			 var status=$('#aspApprovedStatus_clearence').val();			 
			 var oldStaus=$.trim($('#oldAspApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 if(status=='N'){
					 if(isCommentEmpty){
						 returnValue=false;
						 alert('Please enter the comment!');
					 }
				 }
			 }
		 }
		 
		 if(canDHASign==1){
			 var status=$('#dhaApprovedStatus_clearence').val();
			 var oldStaus=$.trim($('#oldDhaApprovedStatus_' + applicationId).val());
			 if(!(status==oldStaus)){
				 if(status=='N'){
					 if(isCommentEmpty){
						 returnValue=false;
						 alert('Please enter the comment!');
					 }
				 }
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
	
	function editRow(applicationId){
		 blockUI();
		 //var ans=confirm('Starting edit process will lock this record for other users. Are you sure you want start editing this record?');
		 //if(ans){
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
				  $('#maxId').val(0);
				  $('#myForm').submit();
				  //unLockCooRow(applicationId);
			  }			  
			  unBlockUI();
		 });
		 //}
	 }
	
	
	 
	 function cancelEdit(applicationId){
		 blockUI();
		// var ans=confirm('Cancelling edit process will release this record. Are you sure you want quit editing this record?');
		// if(ans){		 
		 $.get('checkAndRemoveLockClearence',{applicationId:applicationId},function(data){
			  var recordLockstatus=data.recordLockstatus;				 		
			  if(recordLockstatus=='SUCCESS'){
				  var defaultView=$('#defaultView').val();
				  if(defaultView==true || defaultView=='true'){
					  $('#defaultViewFromUi').val(1);
				  }	
				  $('#myForm').submit();
				  //lockCooRow(applicationId);
			  }			  
			  unBlockUI();
		 });
		 //}				
	 }
	 
	 function setLimit(value){
		  blockUI();
		  $('#limit').val(value);
		  $('#startFrom').val(0);
		  $('#myForm').submit();		  
	  }
	  
	  function goToSelectedPage(startFrom){
		  blockUI();
		  $('#startFrom').val(startFrom);
		  $('#myForm').submit();
	  }
	  
	  function validateForm(){
		  blockUI();
		  return true;
	  }
	  
	  function updateApplication(){
		  $('#certificateIssuanceUpdateForm').submit();
	  }
	  
	  function viewNicPopup(applicationId){
		  blockUI();			
		 var fileName=$('#hiddenNicFileName_' + applicationId).val();
		 var fileType=$('#hiddenNicFileType_' + applicationId).val();
		 var pptNo=$('#hiddenNicNo_' + applicationId).val();

		 if(fileType=='IMAGE'){
			 $('#nicImge').attr('src','policeFileFinder.htm?fileName=' + fileName);		
			 $('#nicImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);
		 }else{
			 $('#nicImge').attr('src','images/no_preview_available.png');
			 $('#nicImge_link').attr('href','images/no_preview_available.png');
		 }
		 
		 //$('#nicFileName').html(fileName);
		 $('#nicFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
		 
		 $('#nicNumberAppend').html(pptNo);
		 
		 initImageNic();
		 
		 $('#nicViewModellLink').click();	
		 unBlockUI();
	  }
	  
	  function viewPptPopup(applicationId){
			 blockUI();
			
			 var fileName=$('#hiddenPptFileName_' + applicationId).val();
			 var fileType=$('#hiddenPptFileType_' + applicationId).val();
			 var pptNo=$('#hiddenPptNo_' + applicationId).val();

			 if(fileType=='IMAGE'){
				 $('#passportImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#passportImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#passportImge').attr('src','images/no_preview_available.png');
				 $('#passportImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#passportFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 
			 $('#passportNumberAppend').html(pptNo);
			 
			 initImagePassport();
			 
			 $('#pptViewModellLink').click();	
			 unBlockUI();
		}
	  
	  function viewBcPopup(applicationId){
			 blockUI();
			
			 var fileName=$('#hiddenBcFileName_' + applicationId).val();
			 var fileType=$('#hiddenBcFileType_' + applicationId).val();

			 if(fileType=='IMAGE'){
				 $('#bcImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#bcImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#bcImge').attr('src','images/no_preview_available.png');
				 $('#bcImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#bcFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 
			 initImageBc();
			 
			 $('#bcViewModellLink').click();	
			 unBlockUI();
		}
	  
	  function viewSlbfePopup(applicationId){
			 blockUI();
			
			 var fileName=$('#hiddenSlbfeFileName_' + applicationId).val();
			 var fileType=$('#hiddenSlbfeFileType_' + applicationId).val();

			 if(fileType=='IMAGE'){
				 $('#slbfeImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#slbfeImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#slbfeImge').attr('src','images/no_preview_available.png');
				 $('#slbfeImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#slbfeFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 
			 initImageSlbfe();
			 
			 $('#slbfeViewModellLink').click();	
			 unBlockUI();
		}
	 
	  function initImagePassport(){
	    $('#passportImge_link').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});
	  }
	  
	  function initImageNic(){
	    $('#nicImge_link').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});
	  }
	  
	  function initImageBc(){
		    $('#bcImge_link').magnificPopup({
				type: 'image',
				closeOnContentClick: true,
				closeBtnInside: false,
				mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
				image: {
					verticalFit: true
				},
				zoom: {
					enabled: true,
					duration: 300 // don't foget to change the duration also in CSS
				}
			});
		}
	  
	  
	  function initImageSlbfe(){
		    $('#slbfeImge_link').magnificPopup({
				type: 'image',
				closeOnContentClick: true,
				closeBtnInside: false,
				mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
				image: {
					verticalFit: true
				},
				zoom: {
					enabled: true,
					duration: 300 // don't foget to change the duration also in CSS
				}
			});
		}
	  
	  
	  
	  
	  function intializeEditableStatus(applicationId){
			 canEditClearence = parseInt($('#canEditClearence_' + applicationId).val());
			 if(isNaN(canEditClearence)){
				 canEditClearence=0;
			 }
			 
			 canOicApprove = parseInt($('#canOicApprove_' + applicationId).val());
			 if(isNaN(canOicApprove)){
				 canOicApprove=0;
			 }
			 
			 canAspApprove = parseInt($('#canAspApprove_' + applicationId).val());
			 if(isNaN(canAspApprove)){
				 canAspApprove=0;
			 }
			 
			 canDHASign = parseInt($('#canDHASign_' + applicationId).val());
			 if(isNaN(canDHASign)){
				 canDHASign=0;
			 }
			 
			 canEnterCertificateSerial = parseInt($('#canEnterCertificateSerial_' + applicationId).val());
			 if(isNaN(canEnterCertificateSerial)){
				 canEnterCertificateSerial=0;
			 }
			 
			 canEditRegPost = parseInt($('#canEditRegPost_' + applicationId).val());
			 if(isNaN(canEditRegPost)){
				 canEditRegPost=0;
			 }
			 
			 canPrintAddress = parseInt($('#canPrintAddress_' + applicationId).val());
			 if(isNaN(canPrintAddress)){
				 canPrintAddress=0;
			 }
			 
			 canPrintCertificate = parseInt($('#canPrintCertificate_' + applicationId).val());
			 if(isNaN(canPrintCertificate)){
				 canPrintCertificate=0;
			 }
		}
	  
	  
	  
	  //to upload the external reports for blanks
	    function uploadFile(){
	  	  //check the browser
	  	  if(!supportAjaxUploadWithProgress){
	  		  alert("Please update your browser to upload");
	  		  return false;
	  	  }
	  	
	  	  blockUI();
	  	  
	  	  var xhr = new XMLHttpRequest();
	  	  
	  	  var nicInput = document.getElementById('nicFileUpload');
	  	  var nicFile = "";
		  var fileNicPath = "";
		  var nicExtension = "";
	  	  
	  	  nicFile = nicInput.files[0];
	  	  fileNicPath = document.getElementById('nicFileUpload').value;
	  	  nicExtension = fileNicPath.split(".").pop().trim();
	  	  validateFileUpload('Nic', fileNicPath, nicExtension, nicFile);
	  	  	  	  	  	  
	  	  var formData = new FormData();
	  	  $("#ajax_loader_nic").show();
  		  formData.append('nicFile', nicFile);
  		  formData.append('nicFileExtension', nicExtension);
	  	  
	  	  
	  	  xhr.open('POST', 'uploadFiles.action', true);
	  	  xhr.send(formData);
	  	  
	  	  xhr.onreadystatechange=function(){
	  	  	if (xhr.readyState==4 && xhr.status==200){
	  	  		var jsonData = JSON.parse(xhr.responseText);
	  	  		
  	  			var nicFileName = jsonData.nicFileName;
  	  			var fileType=jsonData.fileType;
	  	  		if(nicFileName != ''){
	  	  			$("#nicUploadPath").val(nicFileName);
	  	  		}
	  	  		
		  	  	 if(fileType=='IMAGE'){
					 $('#nicRevisionImageView').attr('src','policeFileFinder.htm?fileName=' + nicFileName);		
				 }else{
					 $('#nicRevisionImageView').attr('src','images/no_preview_available.png');
				 }
			 
			 	//$('#nicFileName').html(fileName);
				$('#nicRevisionFileName').attr('href','policeFileFinder.htm?fileName=' + nicFileName);
				$('#image_link_click_nic_revision').show();
	  	  	
	  	  		$("#ajax_loader_nic").hide();
	  	  		$("#upload_complete_nic").show(); 	 
	  	  	 	unBlockUI();
	  	    }
	  	  }
	  	  
	  }
	  
	  function validateFileUpload(uploadingFile, path, extension, file){
		  var maximumFileLimit=parseFloat($('#maximumFileLimit').val());
		  if(path == ''){
	  		  alert("please select a file before upload for "+uploadingFile);
	  		  return false;
	  	  }else if(extension != "pdf" && extension != "PDF" && extension != "doc" && extension != "DOC" && extension != "png" && extension != "PDF" && extension != "jpg" && extension != "jpg"){
	  		  alert("invalid "+uploadingFile+" file format");
	  		  return false;
	  	  }else if(file.size > maximumFileLimit){
	  		  alert(uploadingFile+" file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
	  		  return false;
	  	  }
		  return true;
	  }
	  
	//function for check the upload is supported for using browser
	    function supportAjaxUploadWithProgress() {
	  	  return supportFileAPI() && supportAjaxUploadProgressEvents() && supportFormData();
	  	     function supportFileAPI() {
	  	       var fi = document.createElement('INPUT');
	  	       fi.type = 'file';
	  	       return 'files' in fi;
	  	     };
	  	    function supportAjaxUploadProgressEvents() {
	  	      var xhr = new XMLHttpRequest();
	  	     return !! (xhr && ('upload' in xhr) && ('onprogress' in xhr.upload));
	  	    };
	  	    function supportFormData() {
	  	      return !! window.FormData;
	  	    }
	  	}

	</script>
</body>
</html>
</s:i18n>