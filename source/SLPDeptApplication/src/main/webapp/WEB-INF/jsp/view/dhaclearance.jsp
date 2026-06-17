<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

        <!-- including header -->
        <jsp:include page="../common/header.jsp"/>

        <!-- Starting the page Title -->
        <div id="es-content">

            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Internal - DHA</c:set>
            <jsp:include page="../common/commonPage.jsp">
                <jsp:param name="title" value="${pageTitle}"/>
            </jsp:include>

            <div id="messagesDiv" style="margin:2px 0px;">
                <s:if test="customErrorMessage != null">
                    <div class="alert alert-error">
                        <s:property value="customErrorMessage" escapeHtml="false"/>
                    </div>
                </s:if>
            </div>

            <div class="middle_content">
                <div>
                    <c:set var="submitUrl">searchDhaClearence.action</c:set>
                    <c:set var="includeChekBox">YES</c:set>
                    <c:set var="checkboxMessage">
                        Note :- Select the check box above and click 'Search' to filter the applications on your queue.
                    </c:set>
                    <c:set var="checkboxLabel">
                        Display only pending approval list
                    </c:set>
                    <jsp:include page="../common/clearencecommonsearch.jsp">
                        <jsp:param name="formSubmitUrl" value="${submitUrl}"/>
                        <jsp:param name="includeChekBox" value="${includeChekBox}"/>
                        <jsp:param name="checkboxMessage" value="${checkboxMessage}"/>
                        <jsp:param name="checkboxLabel" value="${checkboxLabel}"/>
                    </jsp:include>

                    <div style="clear: both;"></div>
                    <div class="form-group">

                        <c:if test="${! empty applicationList && userType==userTypeDisplayVO.dhaUser}">
                            <div class="col-lg-13" style="max-width: 100%; overflow-x: auto;">
                                <div class="table-responsive" style="max-width: 100%;">
                                    <table class="table table-bordered" style="max-width: 100%;">
                                        <thead>
                                        <tr>
                                            <th class="text-center"><strong>Select</strong></th>
                                            <th class="text-center"><strong>Application Date</strong></th>
                                            <th class="text-center"><strong>Reference</strong></th>
                                            <th class="text-center"><strong>Current NIC No</strong></th>
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
                                            <th class="text-center"><strong>Clearance Status</strong></th>
                                            <th class="text-center"><strong>Printed Status</strong></th>
                                            <th class="text-center"><strong>Print/Preview Certificate</strong></th>
                                            <th class="text-center"><strong>OIC Approved</strong></th>
                                            <th class="text-center"><strong>ASP Approved</strong></th>
                                            <th class="text-center"><strong>DIG Approved</strong></th>
                                            <th class="text-center"><strong>DHA Signed</strong></th>
                                            <th class="text-center"><strong>Save</strong></th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="canAnyAddressBePrinted">0</c:set>
                                        <c:forEach items="${applicationList}" var="applicationVO" varStatus="counter">

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
                                                <input type="hidden"
                                                       id="canEditClearence_${applicationVO.applicationId}"
                                                       value="${applicationVO.canEditClearence}"/>
                                                <input type="hidden"
                                                       id="canPrintCertificate_${applicationVO.applicationId}"
                                                       value="${applicationVO.canPrintCertificate}"/>
                                                <input type="hidden"
                                                       id="oldClearenceStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.applicationClearanceStatus}"/>
                                                <input type="hidden"
                                                       id="constructedComment_${applicationVO.applicationId}"
                                                       value="${applicationVO.constructedComment}"/>
                                                <input type="hidden"
                                                       id="commentAvailable_${applicationVO.applicationId}"
                                                       value='<c:out escapeXml="false"  value="${applicationVO.commentAvailable}"></c:out>'/>
                                                <input type="hidden" id="hiddenVersionId_${applicationVO.applicationId}"
                                                       value="${applicationVO.versionId}"/>
                                                <input type="hidden"
                                                       id="hiddenReferenceNo_${applicationVO.applicationId}"
                                                       value="${applicationVO.referenceNo}"/>
                                                <input type="hidden"
                                                       id="hiddenHasAnyAdverse_${applicationVO.applicationId}"
                                                       value="${applicationVO.hasAnyAdverseRecord}"/>

                                                <input type="hidden" id="canDhaApprove_${applicationVO.applicationId}"
                                                       value="${applicationVO.canDHASign}"/>
                                                <input type="hidden"
                                                       id="certificateLetterContent_${applicationVO.applicationId}"
                                                       value="${applicationVO.certificateLetterContent}"/>
                                                <input type="hidden"
                                                       id="certificateLetterContentCommentId_${applicationVO.applicationId}"
                                                       value="${applicationVO.certificateLetterContentCommentId}"/>


                                                <input type="hidden"
                                                       id="oldOicApprovedStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.oicApproved}"/>
                                                <input type="hidden"
                                                       id="oldAspApprovedStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.aspApproved}"/>
                                                <input type="hidden"
                                                       id="oldDhaApprovedStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.dhaApproved}"/>

                                                <c:choose>
                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                        <img src="images/lock.png" alt="Select" title="Select"
                                                             id="lockUnlockBtn_${applicationVO.applicationId}"
                                                             class="basic_image_button"
                                                             onclick="cancelEdit(${applicationVO.applicationId})"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/unlock.png" alt="Select" title="Select"
                                                             id="lockUnlockBtn_${applicationVO.applicationId}"
                                                             class="basic_image_button"
                                                             onclick="editRow(${applicationVO.applicationId},'${applicationVO.referenceNo}')"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="text-center"
                                                style="vertical-align: middle;">${applicationVO.updatedDateTime}</td>
                                            <td class="text-center" style="vertical-align: middle;">
                                                <a href="viewApplicationByReferenceNo.action?referenceNo=${applicationVO.referenceNo}">
                                                        ${applicationVO.referenceNo}
                                                </a>
                                            </td>
                                            <td class="text-center"
                                                style="vertical-align: middle;">${applicationVO.currentNicNo}</td>
                                            <td class="text-center"
                                                style="vertical-align: middle;">${applicationVO.passport}</td>
                                            <td class="text-center"
                                                style="vertical-align: middle;">${applicationVO.applicantName}</td>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.polStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.polStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.cidStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.cidStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.tidStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.tidStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.sisStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.sisStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.nicStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.nicStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.nicStatus=='OI'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.nicStatus=='NI'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.imiStatus=='AP'}">
                                                        <img src="images/approved.png" alt="Approved"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Approved"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.imiStatus=='RJ'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.imiStatus=='OI'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:when test="${applicationVO.imiStatus=='NI'}">
                                                        <img src="images/rejected.png" alt="Rejected"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Rejected"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <img src="images/comments_list.png" alt="Comments"
                                                     class="basic_image_button_smaller" title="Comments"
                                                     onclick="viewCommentsList(${applicationVO.applicationId})"/>
                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">

                                                <c:if test="${! empty  applicationVO.nicAttachPath}">
                                                    <a href="javascript:void(0)"
                                                       onclick="viewNicPopup(${applicationVO.applicationId})">
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    <input type="hidden"
                                                                           id="hiddenNicFileName_${applicationVO.applicationId}"
                                                                           value="${applicationVO.nicAttachPath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenNicFileType_${applicationVO.applicationId}"
                                                                           value="${applicationVO.nicFileType}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenNicFileNameBack_${applicationVO.applicationId}"
                                                                           value="${applicationVO.nicBackAttachPath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenNicFileTypeBack_${applicationVO.applicationId}"
                                                                           value="${applicationVO.nicBackFileType}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenNicNo_${applicationVO.applicationId}"
                                                                           value="${applicationVO.nic}"/>
                                                                    <img alt="NIC" src="images/zoom_in.png"
                                                                         style="width:15px;height:15px;"/>
                                                                </td>
                                                                <td>&nbsp;NIC</td>
                                                            </tr>
                                                        </table>
                                                    </a>
                                                </c:if>

                                                <c:if test="${!empty applicationVO.newNicAttachPath}">
                                                        <a href="javascript:void(0)"
                                                           onclick="viewNewNicPopup(${applicationVO.applicationId})">
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <input type="hidden"
                                                                               id="hiddenNewNicFileName_${applicationVO.applicationId}"
                                                                               value="${applicationVO.newNicAttachPath}"/>
                                                                        <input type="hidden"
                                                                               id="hiddenNewNicFileType_${applicationVO.applicationId}"
                                                                               value="${applicationVO.nicFileType}"/>

                                                                        <input type="hidden"
                                                                               id="hiddenNewNicFileNameBack_${applicationVO.applicationId}"
                                                                               value="${applicationVO.newNicBackAttachPath}"/>
                                                                        <input type="hidden"
                                                                               id="hiddenNewNicFileTypeBack_${applicationVO.applicationId}"
                                                                               value="${applicationVO.nicBackFileType}"/>

                                                                        <input type="hidden"
                                                                               id="hiddenNewNicNo_${applicationVO.applicationId}"
                                                                               value="${applicationVO.newNic}"/>
                                                                        <img alt="NIC" src="images/zoom_in.png"
                                                                             style="width:15px;height:15px;"/>

                                                                    </td>
                                                                    <td>&nbsp;New NIC</td>
                                                                </tr>
                                                            </table>
                                                        </a>
                                                </c:if>

                                                <c:if test="${! empty  applicationVO.passportAttachPath}">
                                                    <a href="javascript:void(0)"
                                                       onclick="viewPptPopup(${applicationVO.applicationId})">
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    <input type="hidden"
                                                                           id="hiddenPptFileName_${applicationVO.applicationId}"
                                                                           value="${applicationVO.passportAttachPath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenPptFileType_${applicationVO.applicationId}"
                                                                           value="${applicationVO.pptFileType}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenPptFileNameBack_${applicationVO.applicationId}"
                                                                           value="${applicationVO.passportBackAttachPath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenPptFileTypeBack_${applicationVO.applicationId}"
                                                                           value="${applicationVO.pptBackFileType}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenPptNo_${applicationVO.applicationId}"
                                                                           value="${applicationVO.passport}"/>
                                                                    <img alt="NIC" src="images/zoom_in.png"
                                                                         style="width:15px;height:15px;"/>
                                                                </td>
                                                                <td>&nbsp;PPT</td>
                                                            </tr>
                                                        </table>
                                                    </a>
                                                </c:if>

                                                <c:if test="${! empty  applicationVO.birthCertificatePath}">
                                                    <a href="javascript:void(0)"
                                                       onclick="viewBcPopup(${applicationVO.applicationId})">
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    <input type="hidden"
                                                                           id="hiddenBcFileName_${applicationVO.applicationId}"
                                                                           value="${applicationVO.birthCertificatePath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenBcFileType_${applicationVO.applicationId}"
                                                                           value="${applicationVO.birthCertificateFileType}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenBcFileName_back_${applicationVO.applicationId}"
                                                                           value="${applicationVO.birthCertificateBackPath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenBcFileType_back_${applicationVO.applicationId}"
                                                                           value="${applicationVO.birthCertificateFileBackType}"/>
                                                                    <img alt="Birth Certificate"
                                                                         src="images/zoom_in.png"
                                                                         style="width:15px;height:15px;"/>
                                                                </td>
                                                                <td>&nbsp;Birth Cert.</td>
                                                            </tr>
                                                        </table>
                                                    </a>
                                                </c:if>

                                                <c:if test="${!empty applicationVO.affidavitAttachPath}">
                                                        <a href="javascript:void(0)"
                                                           onclick="viewAffidavitPopup(${applicationVO.applicationId})">
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <input type="hidden"
                                                                               id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                                                               value="${applicationVO.affidavitAttachPath}"/>
                                                                        <input type="hidden"
                                                                               id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                                                               value="${applicationVO.nicFileType}"/>

                                                                        <img alt="AFFIDAVIT" src="images/zoom_in.png"
                                                                             style="width:15px;height:15px;"/>
                                                                    </td>
                                                                    <td>&nbsp;AFFIDAVIT</td>
                                                                </tr>
                                                            </table>
                                                        </a>
                                                </c:if>

                                                <c:if test="${! empty  applicationVO.letterOfReferencePath}">
                                                    <a href="javascript:void(0)"
                                                       onclick="viewSlbfePopup(${applicationVO.applicationId})">
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    <input type="hidden"
                                                                           id="hiddenSlbfeFileName_${applicationVO.applicationId}"
                                                                           value="${applicationVO.letterOfReferencePath}"/>
                                                                    <input type="hidden"
                                                                           id="hiddenSlbfeFileType_${applicationVO.applicationId}"
                                                                           value="${applicationVO.letterOfReferenceFileType}"/>
                                                                    <img alt="Birth Certificate"
                                                                         src="images/zoom_in.png"
                                                                         style="width:15px;height:15px;"/>
                                                                </td>
                                                                <td>&nbsp;SLBFE Lett.</td>
                                                            </tr>
                                                        </table>
                                                    </a>
                                                </c:if>



                                            </td>


                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.canEditClearence==1 &&  applicationVO.hasCurrentUserLocked==1}">
                                                        <select id="clearenceStatusSelect_${applicationVO.applicationId}"
                                                                name="clearenceStatusInput" class="form-control">
                                                            <c:forEach items="${applicationVO.clearenceStatusMap}"
                                                                       var="clearenceStatus">
                                                                <c:choose>
                                                                    <c:when test="${clearenceStatus.key == applicationVO.applicationClearanceStatus}">
                                                                        <option value="${clearenceStatus.key}"
                                                                                selected="selected">${clearenceStatus.value.displayName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${clearenceStatus.key}">${clearenceStatus.value.displayName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <select id="clearenceStatusSelect_${applicationVO.applicationId}"
                                                                name="clearenceStatusInput" disabled="disabled"
                                                                readonly="readonly" class="form-control">
                                                            <c:forEach items="${applicationVO.clearenceStatusMap}"
                                                                       var="clearenceStatus">
                                                                <c:choose>
                                                                    <c:when test="${clearenceStatus.key == applicationVO.applicationClearanceStatus}">
                                                                        <option value="${clearenceStatus.key}"
                                                                                selected="selected">${clearenceStatus.value.displayName}</option>
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

                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.certificatePrintedStatus>0}">
                                                        <img src="images/approved.png" alt="Printed"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Printed"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/pending.png" alt="Print Pending"
                                                             class="basic_image_button_smaller" style="cursor:default;"
                                                             title="Print Pending"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.canPrintCertificate==1 &&  applicationVO.hasCurrentUserLocked==1}">
                                                        <img src="images/print.png" alt="Print"
                                                             id="printCertificateImage_${applicationVO.applicationId}"
                                                             class="basic_image_button_smaller" title="Print"
                                                             onclick="printCertificate(${applicationVO.applicationId})"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/print.png" alt="Print"
                                                             id="printCertificateImage_${applicationVO.applicationId}"
                                                             class="basic_image_button_smaller disabled_image"
                                                             title="Print" onclick="javascript:void(0)"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <c:set var="oicYesCheckedStatus" value=""/>
                                            <c:set var="oicNoCheckedStatus" value=""/>
                                            <c:choose>
                                                <c:when test="${applicationVO.oicApproved =='AP' }">
                                                    <c:set var="oicYesCheckedStatus" value="checked"/>
                                                </c:when>
                                                <c:when test="${applicationVO.oicApproved =='RJ' }">
                                                    <c:set var="oicNoCheckedStatus" value="checked"/>
                                                </c:when>
                                            </c:choose>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <table>
                                                    <tr>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${oicYesCheckedStatus}
                                                                                                   name="oicApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="Y"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">Y</td>
                                                        <td>&nbsp;&nbsp;</td>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${oicNoCheckedStatus}
                                                                                                   name="oicApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="N"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">N</td>
                                                    </tr>
                                                </table>
                                            </td>


                                            <c:set var="aspYesCheckedStatus" value=""/>
                                            <c:set var="aspNoCheckedStatus" value=""/>
                                            <c:choose>
                                                <c:when test="${applicationVO.aspApproved =='AP' }">
                                                    <c:set var="aspYesCheckedStatus" value="checked"/>
                                                </c:when>
                                                <c:when test="${applicationVO.aspApproved =='RJ' }">
                                                    <c:set var="aspNoCheckedStatus" value="checked"/>
                                                </c:when>
                                            </c:choose>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <table>
                                                    <tr>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${aspYesCheckedStatus}
                                                                                                   name="aspApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="Y"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">Y</td>
                                                        <td>&nbsp;&nbsp;</td>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${aspNoCheckedStatus}
                                                                                                   name="aspApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="N"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">N</td>
                                                    </tr>
                                                </table>
                                            </td>

                                            <c:set var="digYesCheckedStatus" value=""/>
                                            <c:set var="digNoCheckedStatus" value=""/>
                                            <c:choose>
                                                <c:when test="${applicationVO.digApproved =='AP' }">
                                                    <c:set var="digYesCheckedStatus" value="checked"/>
                                                </c:when>
                                                <c:when test="${applicationVO.digApproved =='RJ' }">
                                                    <c:set var="digNoCheckedStatus" value="checked"/>
                                                </c:when>
                                            </c:choose>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <table>
                                                    <tr>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${digYesCheckedStatus}
                                                                                                   name="digApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="Y"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">Y</td>
                                                        <td>&nbsp;&nbsp;</td>
                                                        <td style="vertical-align: middle;"><input type="radio"
                                                                                                   style="cursor: pointer;" ${digNoCheckedStatus}
                                                                                                   name="digApproveStatus_${applicationVO.applicationId}"
                                                                                                   value="N"
                                                                                                   readonly="readonly"
                                                                                                   disabled="disabled">
                                                        </td>
                                                        <td style="vertical-align: middle;">N</td>
                                                    </tr>
                                                </table>
                                            </td>

                                            <c:set var="dhaYesCheckedStatus" value=""/>
                                            <c:set var="dhaNoCheckedStatus" value=""/>
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
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${dhaYesCheckedStatus}
                                                                        type="radio"
                                                                        name="dhaApproveStatus_${applicationVO.applicationId}"
                                                                        value="Y"></td>
                                                                <td style="vertical-align: middle;">Y</td>
                                                                <td>&nbsp;&nbsp;</td>
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${dhaNoCheckedStatus}
                                                                        type="radio"
                                                                        name="dhaApproveStatus_${applicationVO.applicationId}"
                                                                        value="N"></td>
                                                                <td style="vertical-align: middle;">N</td>
                                                            </tr>
                                                        </table>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <table>
                                                            <tr>
                                                                <td style="vertical-align: middle;"><input type="radio"
                                                                                                           style="cursor: pointer;" ${dhaYesCheckedStatus}
                                                                                                           name="dhaApproveStatus_${applicationVO.applicationId}"
                                                                                                           value="Y"
                                                                                                           readonly="readonly"
                                                                                                           disabled="disabled">
                                                                </td>
                                                                <td style="vertical-align: middle;">Y</td>
                                                                <td>&nbsp;&nbsp;</td>
                                                                <td style="vertical-align: middle;"><input type="radio"
                                                                                                           style="cursor: pointer;" ${dhaNoCheckedStatus}
                                                                                                           name="dhaApproveStatus_${applicationVO.applicationId}"
                                                                                                           value="N"
                                                                                                           readonly="readonly"
                                                                                                           disabled="disabled">
                                                                </td>
                                                                <td style="vertical-align: middle;">N</td>
                                                            </tr>
                                                        </table>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="text-center" style="vertical-align: middle;">
                                                <c:choose>
                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                        <img src="images/save.png"
                                                             style="width:20px;height:20px;cursor: pointer;"
                                                             id="save_image_${applicationVO.applicationId}" alt="Save"
                                                             title="Save" class="basic_image_button"
                                                             onclick="saveRow(${applicationVO.applicationId})"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="images/save.png"
                                                             style="width: 20px;height:20px;cursor: pointer;"
                                                             id="save_image_${applicationVO.applicationId}" alt="Save"
                                                             title="Save" class="basic_image_button disabled_image"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <div style="clear: both;"></div>
                                <br/>
                            </div>


                            <!-- including Grid Butons -->
                            <jsp:include page="../common/clearancegridbuttons.jsp"/>

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
            <a data-toggle="modal" id="resendClearanceModalLink" href="#resendClearanceModalPopUp">View</a>
        </div>


        <!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
        <div class="modal fade" id="commentInputModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <form action="updateCertificateIssuanceDha.action" method="post" id="certificateIssuanceUpdateForm"
                      onsubmit="return validateUpdateForm()">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Insert Comment</h4>
                        </div>
                        <div class="modal-body">

                            <s:hidden name="clearenceVO.applicationId" id="applicationId_clearence"/>
                            <s:hidden name="clearenceVO.clearenceStatus" id="clearenceStatus_clearence"/>
                            <s:hidden name="clearenceVO.dhaApprovedStatus" id="dhaApprovedStatus_clearence"/>
                            <s:hidden name="clearenceVO.certificateType" id="certificateType_clearence"/>
                            <s:hidden name="clearenceVO.hasAnyAdverse" id="hasAnyAdverse_clearence"/>
                            <s:hidden name="clearenceVO.letterContentCommentId" id="letterContentCommentId_clearence"/>

                            <input type="hidden" name="clearenceVO.versionId" id="versionId_clearence"/>

                            <table class="table borderless" id="certificateTypeTable" style="display: none;">
                                <tr>
                                    <td colspan="2"><b>Select the Certificate Type:</b></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="radio" id="certificateTypeCert" name="certificateType" value="CT"
                                               onclick="toggleLetterComment('CT')"/> Certificate &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" id="certificateTypeCertClause" name="certificateType"
                                               value="CL" onclick="toggleLetterComment('CL')"/> Certificate with clause
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--                                         <input type="radio" id="certificateTypeLetter" name="certificateType" value="LT" -->
<!--                                                onclick="toggleLetterComment('LT')"/> Letter -->
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                            </table>

                            <table class="table borderless" id="letterContentTable" style="display: none;">
                                <tr>
                                    <td><b>Please type the content below:</b></td>
                                </tr>
                                <tr>
                                    <td><textarea name="clearenceVO.letterContent" rows="12" id="letterContentInput"
                                                  class="form-control"></textarea></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>

                            <table class="table borderless" id="commentInputTable" style="display: none;">
                                <tr>
                                    <td><b>Please add a comment for your action:</b></td>
                                </tr>
                                <tr>
                                    <td><textarea name="clearenceVO.comment" rows="12" id="commentInput"
                                                  class="form-control"></textarea></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
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
        <div class="modal fade" id="commentModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">View Comments</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-striped table-bordered">
                            <tr>
                                <td colspan="2"><b>History/Related Adverse Records:</b></td>
                            </tr>
                            <tr>
                                <td colspan="2" id="historyAdverseRecords"></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>Current Adverse Records:</b></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>NIC: <span id="nicStatusText"></span></b></td>
                            </tr>
                            <tr>
                                <td id="nicCommentAppendDiv">No comment is available!</td>
                                <td id="nicCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>CID: <span id="cidStatusText"></span></b></td>
                            </tr>
                            <tr>
                                <td id="cidCommentAppendDiv">No comment is available!</td>
                                <td id="cidCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>TID: <span id="tidStatusText"></span></b></td>
                            </tr>
                            <tr>
                                <td id="tidCommentAppendDiv">No comment is available!</td>
                                <td id="tidCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>SIS: <span id="sisStatusText"></span></b></td>
                            </tr>
                            <tr>
                                <td id="sisCommentAppendDiv">No comment is available!</td>
                                <td id="sisCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>Immigration: <span id="imiStatusText"></span></b></td>
                            </tr>
                            <tr>
                                <td id="immiCommentAppendDiv">No comment is available!</td>
                                <td id="immiCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>Police: <span id="polStatusText"></span></b></td>
                            </tr>
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
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">Comments</td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>Checking Officer Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="checkOffCommentAppendDiv">No comment is available!</td>
                                <td id="checkOffCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>OIC Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="oicCommentAppendDiv">No comment is available!</td>
                                <td id="oicCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>ASP Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="aspCommentAppendDiv">No comment is available!</td>
                                <td id="aspCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>DHA Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="dhaCommentAppendDiv">No comment is available!</td>
                                <td id="dhaCommentDateAppendDiv"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>DIG Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="digCommentAppendDiv">No comment is available!</td>
                                <td id="digCommentDateAppendDiv"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" style="display: none;" id="commentModalCloseButton"
                                class="btn btn-primary" data-dismiss="modal">&nbsp;</button>
                    </div>
                </div>
            </div>
        </div>

        <!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
        <div class="modal fade" id="resendClearanceModalPopUp" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form action="resendClearanceDha.action" method="post" id="resendClearanceUpdateForm"
                      onsubmit="return validateResendClearance()">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Resend Clearance</h4>
                        </div>
                        <div class="modal-body">

                            <s:hidden name="applicationVO.applicationId"/>
                            <s:hidden name="clearenceVO.applicationId" id="applicationId_resend_clearence"/>
                            <s:hidden name="clearenceVO.clearenceStatus" id="clearenceStatus_resend_clearence"/>
                            <s:hidden name="clearenceVO.referenceNo" id="referenceNo_resend_clearence"/>
                            <input type="hidden" name="clearenceVO.versionId" id="versionId_resend_clearence"/>

                            <table class="table borderless" id="commentInputTable">
                                <tr>
                                    <td><b>Select:</b></td>
                                    <td>
                                        <select name="clearenceVO.departmentId" id="clearenceStatus_resend_department"
                                                class="form-control">
                                            <option value="">Select Department</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr id="policeAreaSelect" style="display: none;">
                                    <td><b>Police Area:</b></td>
                                    <td>
                                        <select name="clearenceVO.policeAreaId" id="clearenceStatus_resend_police_area"
                                                class="form-control">
                                            <option value="">Select Police Area</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr id="addressListDiv" style="display: none;">
                                    <td>&nbsp;</td>
                                    <td>
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Address</th>
                                                <th>Current Status</th>
                                                <th>Current Comment</th>
                                                <th>Current Clearance Date</th>
                                            </tr>
                                            </thead>
                                            <tbody id="listAppendDiv"></tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr id="currentStatusRow">
                                    <td><b>Current Status:</b></td>
                                    <td>
                                        <input type="text" id="currentStatus_resend_clearence" class="form-control"/>
                                    </td>
                                </tr>
                                <tr id="currentCommentRow">
                                    <td>&nbsp;</td>
                                    <td>
                                        <input type="text" id="currentComment_resend_clearence" class="form-control"/>
                                    </td>
                                </tr>
                                <tr id="currentDateRow">
                                    <td>&nbsp;</td>
                                    <td>
                                        <input type="text" id="currentDateCleared_resend_clearence"
                                               class="form-control"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Reason to Resend:</b></td>
                                    <td>
                                        <textarea rows="6" cols="35" id="currentReason_resend_clearence"
                                                  name="clearenceVO.reasonToResend" class="form-control"></textarea>
                                    </td>
                                </tr>
                            </table>

                        </div>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">OK</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>

        <div style="display: none;" id="hiddenForms">
            <form action="printcertificate.action" id="hiddenFormPrintCertificate" target="_blank">
                <input type="hidden" name="applicationId" readonly="readonly" id="printCertificateFormApplicationId"
                       value="0"/>
            </form>
        </div>

        <!-- including common popups -->
        <jsp:include page="../common/clearencecommonpopups.jsp"/>

        <!-- including footer -->
        <jsp:include page="../common/footer.jsp"/>

    </div>
    <script type="text/javascript" src="js/jquery.numeric.js"></script>
    <script language="javascript" type="text/javascript">

        var canEditClearence = 0;
        var canPrintCertificate = 0;
        var canDhaApprove = 0;

        function validateResendClearance() {
// 		alert('This functionality is still in the development stgae!');
            var isValid = true;
            var dept = $('#clearenceStatus_resend_department').val();

            if ((dept <= 0)) {
                alert('Please select the department!');
                isValid = false;
            }

            if (isValid) {
                if (dept == 2) {
                    var policeArea = $('#clearenceStatus_resend_police_area').val();
                    if (policeArea <= 0) {
                        isValid = false;
                        alert('Please select the police area!');
                    }
                }
            }

            if (isValid) {
                var reason = $.trim($('#currentReason_resend_clearence').val());
                //alert(reason);
                if (reason == '' || reason == null) {
                    isValid = false;
                    alert('Please enter the reason to resend!');
                }
            }

            return isValid;
        }

        function refreshPage() {
            var conf = confirm('Are you sure you want to refersh the page?');
            if (conf) {
                $('#myForm').submit();
            }
        }

        function viewCommentsList(applicationId) {
            blockUI();
            clearAppendDivs();
            $.get('loadCommentList.action', {applicationId: applicationId}, function (data) {
                var commentTypeVO = data.typeDisplayVO;
                if (!(commentTypeVO == null)) {
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

                    var str = '';
                    if (commentTypeVO.historyAdverseRecords.length >= 0) {
                        $.each(commentTypeVO.historyAdverseRecords, function (index, item) {
                            str = str + '<div>' + item + '</div>';
                        })
                    }
                    $('#historyAdverseRecords').html(str);

                    var polStr = '';
                    if (commentTypeVO.policeCommentTypeDisplayVOs.length > 0) {
                        $.each(commentTypeVO.policeCommentTypeDisplayVOs, function (index, item) {
                            polStr = polStr + '<tr>'
                                + '<td>' + item.policeArea + '</td>'
                                + '<td>' + item.address + '</td>'
                                + '<td>' + item.polStatusText + '</td>'
                                + '<td>' + item.policeComment + '</td>'
                                + '<td>' + item.policeCommentDate + '</td>'
                            '</tr>';
                        });
                    }
                    $('#policeCommentAppendDiv').html(polStr);


                } else {
                    alert('Sorry, Could not load the comments for this Application!');
                    clearAppendDivs();
                }
                unBlockUI();

                $('#commentModalLink').click();
            });
        }

        function clearAppendDivs() {
            var defaultComment = 'No comment is available!';
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


        function printCertificate(applicationId) {
            $('#printCertificateFormApplicationId').val(applicationId);
            $('#hiddenFormPrintCertificate').submit();
        }

        function saveRow(applicationId) {
            $('#applicationId_clearence').val(applicationId);

            var versionId = parseInt($('#hiddenVersionId_' + applicationId).val());
            var hiddenHasAnyAdverse = parseInt($('#hiddenHasAnyAdverse_' + applicationId).val());

            var commentId = parseInt($.trim($('#certificateLetterContentCommentId_' + applicationId).val()));
            $('#letterContentCommentId_clearence').val(commentId);
            if (commentId > 0) {
                $('#letterContentInput').val($('#certificateLetterContent_' + applicationId).val());
            }

            var hasAnyChanged = false;
            intializeEditableStatus(applicationId);
            var hasOtherModelLoaded = false;
            //try and enable clearence status
            if (canEditClearence == 1) {
                var clearenceStatusSelect = $.trim($('#clearenceStatusSelect_' + applicationId).val());
                $('#clearenceStatus_clearence').val(clearenceStatusSelect);
                var oldStaus = $.trim($('#oldClearenceStatus_' + applicationId).val());

                if (!(clearenceStatusSelect == oldStaus)) {
                    if (hiddenHasAnyAdverse == 1) {
                        if (clearenceStatusSelect == 'IS' || clearenceStatusSelect == 'CP') {
                            $('#certificateTypeTable').show();
                            $('#letterContentTable').hide();
                            $('#commentInputTable').hide();
                        } else if (oldStaus == 'PN' && clearenceStatusSelect == 'RC') {
                            blockUI();
                            hasOtherModelLoaded = true;
                            $.get('loadResendingDepartments.action', {applicationId: applicationId}, function (data) {
                                unBlockUI();
                                var departments = data.departmentList;
                                if (!(jQuery.isEmptyObject(departments))) {
                                    var appendText = '<option value="0">Select Department</option>';
                                    $.each(departments, function (key, value) {
                                        appendText = appendText + '<option value="' + key + '">' + value + '</option>';
                                    });
                                    $('#clearenceStatus_resend_department').html(appendText);

                                    $('#currentStatusRow').hide();
                                    $('#currentCommentRow').hide();
                                    $('#currentDateRow').hide();
                                    $('#addressListDiv').hide();
                                    $('#currentStatus_resend_clearence').val('');
                                    $('#currentComment_resend_clearence').val('');
                                    $('#currentDateCleared_resend_clearence').val('');
                                    $('#policeAreaSelect').hide();
                                    $('#clearenceStatus_resend_police_area').val('');

                                    $('#clearenceStatus_resend_department').unbind('change');

                                    $('#clearenceStatus_resend_department').change(function () {
                                        var selectedVal = $(this).val();
                                        //check if police Station
                                        if (selectedVal == 2) {
                                            loadPoliceAreaListForApplication(applicationId);
                                        } else {
                                            loadCurrentCommentDetails(applicationId, selectedVal);
                                        }
                                    });
                                    hasOtherModelLoaded = true;
                                    $('#applicationId_resend_clearence').val(applicationId);
                                    var referenceNo = $('#hiddenReferenceNo_' + applicationId).val();
                                    $('#referenceNo_resend_clearence').val(referenceNo);
                                    $('#resendClearanceModalLink').click();
                                } else {
                                    alert('No Department is available for resend!');
                                }
                            });
                        } else {
                            $('#certificateTypeTable').hide();
                            $('#letterContentTable').hide();
                            $('#commentInputTable').show();
                        }
                    } else {
                        if (clearenceStatusSelect == 'RJ') {
                            $('#certificateTypeTable').hide();
                            $('#letterContentTable').hide();
                            $('#commentInputTable').show();
                        }
                    }
                    hasAnyChanged = true;
                }
            }


            if (canDhaApprove == 1) {
                var oldApprovalStatus = $.trim($('#oldDhaApprovedStatus_' + applicationId).val());
                var inputName = 'dhaApproveStatus_' + applicationId;
                var newApprovalStatus = $("input:radio[name=" + inputName + "]:checked").val();

                if (!(newApprovalStatus == null || newApprovalStatus == 'undefined' || newApprovalStatus == '')) {
                    $('#dhaApprovedStatus_clearence').val(newApprovalStatus);
                    if (!(newApprovalStatus == oldApprovalStatus)) {
                        hasAnyChanged = true;
                        if (!(newApprovalStatus == 'AP')) {
                            $('#certificateTypeTable').hide();
                            $('#letterContentTable').hide();
                            $('#commentInputTable').show();
                        }

                    }
                }

            }


            if (hasAnyChanged) {

                if (canEditClearence == 1 || canDhaApprove == 1) {
                    if (!(hasOtherModelLoaded)) {
                        $('#versionId_clearence').val(versionId);
                        $('#commentInputModalLink').click();
                        hasOtherModelLoaded = true;
                    }
                }
            } else {
                alert('There are no changes to be saved!');
            }
        }


        function loadPoliceAreaListForApplication(applicationId) {
            $('#currentStatusRow').hide();
            $('#currentCommentRow').hide();
            $('#currentDateRow').hide();
            $('#addressListDiv').hide();
            blockUI();
            $.get('loadPoliceAreaListForApplication.action', {applicationId: applicationId}, function (data) {
                var policeAreas = data.policeAreaVOs;
                var appendText = '<option value="0">Select Police Area</option>';
                if (typeof policeAreas !== 'undefined' && policeAreas.length > 0) {
                    $.each(policeAreas, function (index, item) {
                        appendText = appendText + '<option value="' + item.id + '">' + item.policeArea + '</option>';
                    });
                    $('#clearenceStatus_resend_police_area').html(appendText);
                    $('#clearenceStatus_resend_police_area').unbind('change');
                    $('#clearenceStatus_resend_police_area').change(function () {
                        var selectedVal = $(this).val();
                        loadCurrentCommentDetailsForAddress(applicationId, selectedVal);
                    });
                    $('#policeAreaSelect').show();
                }
                unBlockUI();
            });
        }


        function loadCurrentCommentDetails(applicationId, department) {
            blockUI();
            $('#currentStatusRow').hide();
            $('#currentCommentRow').hide();
            $('#currentDateRow').hide();
            $('#addressListDiv').hide();
            $('#currentStatus_resend_clearence').val('');
            $('#currentComment_resend_clearence').val('');
            $('#currentDateCleared_resend_clearence').val('');
            if (department > 0) {
                $.get('loadCurrentCommentDetailsForDepartment.action', {
                    applicationId: applicationId,
                    department: department
                }, function (data) {
                    var clearanceViewVO = data.clearanceViewVO;
                    if (!(typeof clearanceViewVO == 'undefined' || clearanceViewVO == null)) {
                        $('#currentStatus_resend_clearence').val(clearanceViewVO.currentStatus);
                        $('#currentComment_resend_clearence').val(clearanceViewVO.currentComment);
                        $('#currentDateCleared_resend_clearence').val(clearanceViewVO.currentClearanceDate);
                        $('#addressListDiv').hide();

                        $('#currentStatusRow').show();
                        $('#currentCommentRow').show();
                        $('#currentDateRow').show();
                    }
                    unBlockUI();
                });
            } else {
                unBlockUI();
            }

        }


        function loadCurrentCommentDetailsForAddress(applicationId, policeAreaId) {
            blockUI();
            $('#listAppendDiv').html('');
            $('#currentStatusRow').hide();
            $('#currentCommentRow').hide();
            $('#currentDateRow').hide();
            $('#addressListDiv').hide();
            if (policeAreaId > 0) {
                $.get('loadCurrentCommentDetailsForPoliceArea.action', {
                    applicationId: applicationId,
                    policeAreaId: policeAreaId
                }, function (data) {
                    var clearanceViewVOList = data.clearanceViewVOList;
                    var appendText = '';
                    if (typeof clearanceViewVOList !== 'undefined' && clearanceViewVOList.length > 0) {
                        $.each(clearanceViewVOList, function (index, item) {
                            appendText = appendText + '<tr>'
                            appendText = appendText + '<td>' + item.address + '</td>';
                            appendText = appendText + '<td>' + item.currentStatus + '</td>';
                            appendText = appendText + '<td>' + item.currentComment + '</td>';
                            appendText = appendText + '<td>' + item.currentClearanceDate + '</td>';
                            appendText = appendText + '</tr>';
                        });
                        $('#listAppendDiv').html(appendText);
                        $('#addressListDiv').show();

                        $('#currentStatusRow').hide();
                        $('#currentCommentRow').hide();
                        $('#currentDateRow').hide();
                    }
                    unBlockUI();
                });
            } else {
                unBlockUI();
            }

        }

        function toggleLetterComment(letterType) {
            if (letterType == 'CL' || letterType == 'LT') {
                $('#letterContentTable').show();
                $('#commentInputTable').hide();
            } else {
                $('#letterContentTable').hide();
                $('#commentInputTable').show();
            }
        }

        function validateUpdateForm() {
            var returnValue = true;

            var isCommentEmpty = false;
            var comment = $.trim($('#commentInput').val());
            if ((comment == '' || comment == null || comment == 'undefined')) {
                isCommentEmpty = true;
            }

            var applicationId = $('#applicationId_clearence').val();
            intializeEditableStatus(applicationId);

            var hiddenHasAnyAdverse = parseInt($('#hiddenHasAnyAdverse_' + applicationId).val());
            $('#hasAnyAdverse_clearence').val(hiddenHasAnyAdverse);

            var letterType = $("input:radio[name=certificateType]:checked").val();
            var letterContent = $.trim($('#letterContentInput').val());

            $('#certificateType_clearence').val(letterType);

            //try and enable clearence status
            if (canEditClearence == 1) {
                var clearenceStatusSelect = $('#clearenceStatus_clearence').val();
                var oldStaus = $.trim($('#oldClearenceStatus_' + applicationId).val());

                if (!(clearenceStatusSelect == oldStaus)) {
                    if (hiddenHasAnyAdverse) {
                        if (clearenceStatusSelect == 'IS' || clearenceStatusSelect == 'CP') {
                            if (letterType == 'CL' || letterType == 'LT') {
                                if ((letterContent == '' || letterContent == null || letterContent == 'undefined')) {
                                    returnValue = false;
                                    alert('Please enter the letter content/clause!');
                                }
                            }
                        } else {
                            if (isCommentEmpty) {
                                returnValue = false;
                                alert('Please enter the comment!');
                            }
                        }
                    }
                }
            }


            if (canDhaApprove == 1) {
                var inputName = 'dhaApproveStatus_' + applicationId;
                var status = $("input:radio[name=" + inputName + "]:checked").val();
                $('#dhaApprovedStatus_clearence').val(status);
                if (!(status == null || status == 'undefined' || status == '')) {
                    var oldStaus = $.trim($('#oldDhaApprovedStatus_' + applicationId).val());
                    if (!(status == oldStaus)) {
                        if (status != 'AP') {
                            if (isCommentEmpty) {
                                returnValue = false;
                                alert('Please enter the comment!');
                            }
                        }
                        hasAnyChanged = true;
                    }
                }
            }


            if (returnValue) {
                var conf = confirm('Are you sure you want to save this record?');
                if (!(conf)) {
                    returnValue = false;
                }
            }

            return returnValue;
        }

        function editRow(applicationId, referenceNo) {
            blockUI();
            //var ans=confirm('Starting edit process will lock this record for other users. Are you sure you want start editing this record?');
            //if(ans){
            $.get('checkAndLockClearenceRecord', {applicationId: applicationId}, function (data) {
                var recordLockstatus = data.recordLockstatus;
                var userName = data.lockedUserName;
                if (recordLockstatus == 'NO_RECORDS_TO_LOCK') {
                    alert('Internal Error!');
                } else if (recordLockstatus == 'RECORD_IS_LOCKED_BY_ANOTHER_USER') {
                    alert('Sorry, this record is already locked by ' + userName + '!');
                } else if (recordLockstatus == 'ONE_RECORD_IS_ALREADY_LOCKED') {
                    alert('Sorry, you have already locked another record!');
                } else if (recordLockstatus == 'ERROR') {
                    alert('Internal Error!');
                } else if (recordLockstatus == 'SUCCESS') {
                    var defaultView = $('#defaultView').val();
                    if (defaultView == true || defaultView == 'true') {
                        $('#defaultViewFromUi').val(1);
                    }
                    $('#referenceNo').val(referenceNo);
                    $('#lockedRefresh').val(1);
                    $('#maxId').val(0);
                    $('#myForm').submit();
                    //unLockCooRow(applicationId);
                }
                unBlockUI();
            });
            //}
        }


        function cancelEdit(applicationId) {
            blockUI();
            // var ans=confirm('Cancelling edit process will release this record. Are you sure you want quit editing this record?');
            // if(ans){
            $.get('checkAndRemoveLockClearence', {applicationId: applicationId}, function (data) {
                var recordLockstatus = data.recordLockstatus;
                if (recordLockstatus == 'SUCCESS') {
                    var defaultView = $('#defaultView').val();
                    if (defaultView == true || defaultView == 'true') {
                        $('#defaultViewFromUi').val(1);
                    }
                    $('#myForm').submit();
                    //lockCooRow(applicationId);
                }
                unBlockUI();
            });
            //}
        }


        function updateApplication() {
            $('#certificateIssuanceUpdateForm').submit();
        }

        function intializeEditableStatus(applicationId) {
            canEditClearence = parseInt($('#canEditClearence_' + applicationId).val());
            if (isNaN(canEditClearence)) {
                canEditClearence = 0;
            }


            canPrintCertificate = parseInt($('#canPrintCertificate_' + applicationId).val());
            if (isNaN(canPrintCertificate)) {
                canPrintCertificate = 0;
            }

            canDhaApprove = parseInt($('#canDhaApprove_' + applicationId).val());
            if (isNaN(canDhaApprove)) {
                canDhaApprove = 0;
            }
        }

    </script>
    </body>
    </html>
</s:i18n>