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
            <c:set var="pageTitle">Internal - ASP</c:set>
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
                    <c:set var="submitUrl">searchAspClearence.action</c:set>
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

                        <c:if test="${! empty applicationList && userType==userTypeDisplayVO.aspUser}">
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
                                            <th class="text-center"><strong>Save</strong></th>
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

                                                <input type="hidden" id="canOicApprove_${applicationVO.applicationId}"
                                                       value="${applicationVO.canOicApprove}"/>
                                                <input type="hidden" id="canAspApprove_${applicationVO.applicationId}"
                                                       value="${applicationVO.canAspApprove}"/>

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

                                                <c:if test="${! empty  applicationVO.newNicAttachPath}">
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
                                                <c:if test="${! empty  applicationVO.affidavitAttachPath}">
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
                                                                    <img alt="Affidavit"
                                                                         src="images/zoom_in.png"
                                                                         style="width:15px;height:15px;"/>
                                                                </td>
                                                                <td>&nbsp;AFFIDAVIT</td>
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
                                                <c:choose>
                                                    <c:when test="${applicationVO.canOicApprove==1 && applicationVO.hasCurrentUserLocked == 1}">
                                                        <table>
                                                            <tr>
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${oicYesCheckedStatus}
                                                                        type="radio"
                                                                        name="oicApproveStatus_${applicationVO.applicationId}"
                                                                        value="Y"></td>
                                                                <td style="vertical-align: middle;">Y</td>
                                                                <td>&nbsp;&nbsp;</td>
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${oicNoCheckedStatus}
                                                                        type="radio"
                                                                        name="oicApproveStatus_${applicationVO.applicationId}"
                                                                        value="N"></td>
                                                                <td style="vertical-align: middle;">N</td>
                                                            </tr>
                                                        </table>
                                                    </c:when>
                                                    <c:otherwise>
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
                                                    </c:otherwise>
                                                </c:choose>
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
                                                <c:choose>
                                                    <c:when test="${applicationVO.canAspApprove==1 && applicationVO.hasCurrentUserLocked == 1}">
                                                        <table>
                                                            <tr>
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${aspYesCheckedStatus}
                                                                        type="radio"
                                                                        name="aspApproveStatus_${applicationVO.applicationId}"
                                                                        value="Y"></td>
                                                                <td style="vertical-align: middle;">Y</td>
                                                                <td>&nbsp;&nbsp;</td>
                                                                <td style="vertical-align: middle;"><input
                                                                        style="cursor: pointer;" ${aspNoCheckedStatus}
                                                                        type="radio"
                                                                        name="aspApproveStatus_${applicationVO.applicationId}"
                                                                        value="N"></td>
                                                                <td style="vertical-align: middle;">N</td>
                                                            </tr>
                                                        </table>
                                                    </c:when>
                                                    <c:otherwise>
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
        </div>


        <!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
        <div class="modal fade" id="commentInputModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <form action="updateCertificateIssuanceAsp.action" method="post" id="certificateIssuanceUpdateForm"
                      onsubmit="return validateUpdateForm()">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Insert Comment</h4>
                        </div>
                        <div class="modal-body">

                            <s:hidden name="applicationVO.applicationId"/>
                            <s:hidden name="clearenceVO.applicationId" id="applicationId_clearence"/>
                            <s:hidden name="clearenceVO.clearenceStatus" id="clearenceStatus_clearence"/>
                            <s:hidden name="clearenceVO.oicApprovedStatus" id="oicApprovedStatus_clearence"/>
                            <s:hidden name="clearenceVO.aspApprovedStatus" id="aspApprovedStatus_clearence"/>
                            <s:hidden name="clearenceVO.dhaApprovedStatus" id="dhaApprovedStatus_clearence"/>
                            <s:hidden name="clearenceVO.certificateSeriaNo" id="certificateSeriaNo_clearence"/>
                            <s:hidden name="clearenceVO.registeredPostNo" id="registeredPostNo_clearence"/>

                            <input type="hidden" name="clearenceVO.versionId" id="versionId_clearence"/>

                            <table class="table borderless" id="recommendedOfficerNameTable" style="display: none;">
                                <tr>
                                    <td colspan="3"><b>Recommended Ofiicer:</b></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><input type="text" name="clearenceVO.recomendedOfficerName"
                                                           value="${currentUserFullName}" class="form-control"
                                                           id="recommendedOfficerName"/></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><b>Upload Approval Letter from DIG:</b></td>
                                </tr>
                                <tr>
                                    <td><b>Upload Copy:</b></td>
                                    <td>
                                        <input id="approvalLetterUpload" name="approvalLetterUpload" type="file"/>
                                        <s:hidden name="clearenceVO.approvalLetterName" id="approvalLetterPath"
                                                  cssClass="form-control"/>
                                    </td>
                                    <td>
                                        <img src="images/upload.png" style="cursor: pointer;" onclick="uploadFile();"
                                             height="35px"/>
                                        <img src="images/ajax-loader.gif" id="ajax_loader_approval_letter"
                                             style="display:none;"/>
                                        <img src="images/right_green.jpg" id="upload_complete_approval_letter"
                                             style="display:none;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td colspan="2">
                                        <div id="image_link_click_approval_letter" style="display: none;">
                                            Please click
                                            <a id="approvalLetterFileName" target="_blank" href="javascript:void(0)">
                                                <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                            </a>
                                            to Open/Download file
                                        </div>
                                        <div>
                                            <img src="images/letter.png" id="approvalLetterImageView"
                                                 style="height: 100px;width:135px;"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                            </table>
                            <table class="table borderless" id="commentInputTable">
                                <tr>
                                    <td><b>Please add a comment for your action:</b></td>
                                </tr>
                                <tr>
                                    <td><textarea name="clearenceVO.comment" rows="8" id="commentInput"
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
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" style="display: none;" id="commentModalCloseButton"
                                class="btn btn-primary" data-dismiss="modal">&nbsp;</button>
                    </div>
                </div>
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
        var canOicApprove = 0;
        var canAspApprove = 0;

        function refreshPage() {
            var conf = confirm('Are you sure you want to refersh the page?');
            if (conf) {
                $('#myForm').submit();
            }
        }

        function viewCommentsList(applicationId) {
            clearAppendDivs();
            blockUI();
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

                    $('#polStatusText').html(commentTypeVO.polStatusText);
                    $('#nicStatusText').html(commentTypeVO.nicStatusText);
                    $('#cidStatusText').html(commentTypeVO.cidStatusText);
                    $('#tidStatusText').html(commentTypeVO.tidStatusText);
                    $('#sisStatusText').html(commentTypeVO.sisStatusText);
                    $('#imiStatusText').html(commentTypeVO.imiStatusText);

                    var str = '';
                    if (commentTypeVO.historyAdverseRecords.length > 0) {
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

            $('#nicCommentDateAppendDiv').html('');
            $('#cidCommentDateAppendDiv').html('');
            $('#tidCommentDateAppendDiv').html('');
            $('#sisCommentDateAppendDiv').html('');
            $('#immiCommentDateAppendDiv').html('');
            $('#oicCommentDateAppendDiv').html('');
            $('#aspCommentDateAppendDiv').html('');
            $('#checkOffCommentDateAppendDiv').html('');
            $('#dhaCommentDateAppendDiv').html('');

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
            $('#recommendedOfficerNameTable').hide();
            $('#applicationId_clearence').val(applicationId);

            var versionId = parseInt($('#hiddenVersionId_' + applicationId).val());

            var hasAnyChanged = false;
            intializeEditableStatus(applicationId);
            //try and enable clearence status
            if (canEditClearence == 1) {
                var clearenceStatusSelect = $.trim($('#clearenceStatusSelect_' + applicationId).val());
                $('#clearenceStatus_clearence').val(clearenceStatusSelect);

                var oldStaus = $.trim($('#oldClearenceStatus_' + applicationId).val());

                if (!(clearenceStatusSelect == oldStaus)) {
                    if (clearenceStatusSelect == 'GC') {
                        $('#recommendedOfficerNameTable').show();
                    }
                    hasAnyChanged = true;
                }
            }


            if (canAspApprove == 1) {
                var inputName = 'aspApproveStatus_' + applicationId;
                var status = $("input:radio[name=" + inputName + "]:checked").val();
                $('#aspApprovedStatus_clearence').val(status);

                var oldStaus = $.trim($('#oldAspApprovedStatus_' + applicationId).val());
                if (!(status == oldStaus)) {
                    hasAnyChanged = true;
                }
            }

            if (hasAnyChanged) {
                $('#versionId_clearence').val(versionId);
                $('#commentInputModalLink').click();
            } else {
                alert('There are no changes to be saved!');
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

            //try and enable clearence status
            if (canEditClearence == 1) {
                var clearenceStatus = $('#clearenceStatus_clearence').val();

                var oldStaus = $.trim($('#oldClearenceStatus_' + applicationId).val());

                if (!(clearenceStatus == oldStaus)) {
                    if (clearenceStatus == 'BL' || clearenceStatus == 'RJ') {
                        if (isCommentEmpty) {
                            returnValue = false;
                            alert('Please enter the comment!');
                        }
                    }
                }
            }


            if (canAspApprove == 1) {
                var inputName = 'aspApproveStatus_' + applicationId;
                var status = $("input:radio[name=" + inputName + "]:checked").val();
                $('#aspApprovedStatus_clearence').val(status);

                var oldStaus = $.trim($('#oldAspApprovedStatus_' + applicationId).val());
                if (!(status == oldStaus)) {
                    returnValue = true;
                }
            }


// 		 if(returnValue){
// 			 var conf=confirm('Are you sure you want to save this record?');
// 			 if(!(conf)){
// 				 returnValue=false;
// 			 }
// 		 }

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
            var conf = confirm('Are you sure you want to save the changes?');
            if (conf) {
                $('#certificateIssuanceUpdateForm').submit();
            }
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

            canAspApprove = parseInt($('#canAspApprove_' + applicationId).val());
            if (isNaN(canAspApprove)) {
                canAspApprove = 0;
            }
        }


        //to upload the approval letter from DIG
        function uploadFile() {
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            blockUI();

            var xhr = new XMLHttpRequest();

            var fileInput = document.getElementById('approvalLetterUpload');
            var file = "";
            var filePath = "";
            var fileExtension = "";

            file = fileInput.files[0];
            filePath = document.getElementById('approvalLetterUpload').value;
            fileExtension = filePath.split(".").pop().trim();
            validateFileUpload('Approval Letter', filePath, fileExtension, file);

            var formData = new FormData();
            $("#ajax_loader_approval_letter").show();
            formData.append('file', file);
            formData.append('fileExtension', fileExtension);
            formData.append('fileType', "LETA");
            formData.append('uploadType', "UPLOAD");


            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);

                    var nicFileName = jsonData.fileName;
                    var fileCategory = jsonData.fileCategory;
                    if (nicFileName != '') {
                        $("#approvalLetterPath").val(nicFileName);
                    }

                    if (fileCategory == 'IMAGE') {
                        $('#approvalLetterImageView').attr('src', 'policeFileFinder.htm?fileName=' + nicFileName);
                    } else {
                        $('#approvalLetterImageView').attr('src', 'images/no_preview_available.png');
                    }

                    //$('#nicFileName').html(fileName);
                    $('#approvalLetterFileName').attr('href', 'policeFileFinder.htm?fileName=' + nicFileName);
                    $('#image_link_click_approval_letter').show();

                    $("#ajax_loader_approval_letter").hide();
                    $("#upload_complete_approval_letter").show();
                    unBlockUI();
                }
            }

        }

        function validateFileUpload(uploadingFile, path, extension, file) {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            if (path == '') {
                alert("please select a file before upload for " + uploadingFile);
                unBlockUI();
                return false;
            } else if (extension != "pdf" && extension != "PDF" && extension != "doc" && extension != "DOC" && extension != "png" && extension != "PDF" && extension != "jpg" && extension != "jpg" && extension != "docx" && extension != "DOCX") {
                alert("invalid " + uploadingFile + " file format");
                unBlockUI();
                return false;
            } else if (file.size > maximumFileLimit) {
                alert(uploadingFile + " file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                unBlockUI();
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
                return !!(xhr && ('upload' in xhr) && ('onprogress' in xhr.upload));
            };
            function supportFormData() {
                return !!window.FormData;
            }
        }

    </script>
    </body>
    </html>
</s:i18n>