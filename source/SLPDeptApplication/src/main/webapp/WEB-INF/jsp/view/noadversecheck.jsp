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
            <c:set var="pageTitle">Internal - Checking Officer (No Adverse)</c:set>
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

                    <c:set var="submitUrl">searchNoAdverseCheck.action</c:set>
                    <c:set var="includeChekBox">YES</c:set>
                    <c:set var="checkboxMessage">
                        Note :- Select the check box above and click 'Search' to filter the applications on your queue.
                        Already printed applications also are listed until the application is approved by OIC.
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

                        <c:if test="${! empty applicationList && userType==userTypeDisplayVO.checkingOfficerNoAdverse}">
                            <div class="col-lg-13" style="max-width: 100%;">
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
                                            <th class="text-center"><strong>Name Issue</strong></th>
                                            <th class="text-center"><strong>Attachments</strong></th>
                                            <th class="text-center"><strong>Clearance Status</strong></th>
                                            <th class="text-center"><strong>Printed Status</strong></th>
                                            <th class="text-center"><strong>Print/Preview Certificate</strong></th>
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

                                                <input type="hidden"
                                                       id="hiddenReferenceNo_${applicationVO.applicationId}"
                                                       value="${applicationVO.referenceNo}"/>

                                                <input type="hidden"
                                                       id="hiddenNicNameIssueEmail_${applicationVO.applicationId}"
                                                       value='<c:out value="${applicationVO.nicNameIssueEmail}" escapeXml="false" />'/>
                                                <input type="hidden"
                                                       id="hiddenImiNameIssueEmail_${applicationVO.applicationId}"
                                                       value='<c:out value="${applicationVO.imiNameIssueEmail}" escapeXml="false" />'/>

                                                <input type="hidden" id="hiddenNicStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.nicStatus}"/>
                                                <input type="hidden" id="hiddenImiStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.imiStatus}"/>

                                                <input type="hidden"
                                                       id="hiddenNicStatusText_${applicationVO.applicationId}"
                                                       value="${applicationVO.nicStatusText}"/>
                                                <input type="hidden"
                                                       id="hiddenImiStatusText_${applicationVO.applicationId}"
                                                       value="${applicationVO.imiStatusText}"/>

                                                <input type="hidden"
                                                       id="hiddenNotificationEmailSentStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.notificationEmailSentStatus}"/>

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
                                                <c:if test="${applicationVO.imiStatus=='NI' ||  applicationVO.nicStatus=='NI'}">
                                                    <c:choose>
                                                        <c:when test="${applicationVO.hasCurrentUserLocked==1}">
                                                            <img src="images/warning.png" alt="Name Issue"
                                                                 id="nameIssueImage_${applicationVO.applicationId}"
                                                                 class="basic_image_button_smaller" title="Name Issue"
                                                                 onclick="viewNicRevisionForm(${applicationVO.applicationId})"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="images/warning.png" alt="Name Issue"
                                                                 id="nameIssueImage_${applicationVO.applicationId}"
                                                                 class="basic_image_button_smaller disabled_image"
                                                                 title="Name Issue" onclick="javascript:void(0)"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
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
            <a data-toggle="modal" id="nicRevisionModellLink" href="#nicRevisionModelPopUp">View</a>
            <a data-toggle="modal" id="resendClearanceModalLink" href="#resendClearanceModalPopUp">View</a>
        </div>

        <!--  #####################################################	NIC REVISION MODEL POPUP   ######################################################## -->
        <div class="modal fade" id="nicRevisionModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Name Issue</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table borderless">
                            <tr>
                                <td><b>NIC : <span id="nic_email_cleared_status"></span></b></td>
                            </tr>
                            <tr>
                                <td id="nic_email_comment"></td>
                            </tr>
                            <tr>
                                <td><b>Immigration : <span id="imi_email_cleared_status"></span></b></td>
                            </tr>
                            <tr>
                                <td id="imi_email_comment"></td>
                            </tr>
                            <tr>
                                <td><b>NIC Revision Requirement email</b> &nbsp;&nbsp; <span
                                        style="font-weight:bold;color:#ff0000;" id="no_of_Times_sent_early"></span></td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" id="notificationemailsent_applicationId"/>
                                    <!-- 									<textarea rows="15" name="emailText" id="emailText" class="form-control"></textarea> -->
                                    <div id="hiddenEmailContent" contentEditable="true"
                                         style="padding: 2px;border: 1px solid black;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <td valign="middle" style="vertical-align: middle;padding: 2px;"><input
                                                    type="checkbox" name="doSendEmail" id="doSendEmail"/></td>
                                            <td valign="middle" style="vertical-align: middle;padding:5px 2px 0px 2px;">
                                                Email Applicant
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">
                                    <button type="button" onclick="checkAndUpdateMailSentStatus()"
                                            class="btn btn-primary">OK
                                    </button>
                                </td>
                            </tr>
                        </table>
                        <hr style="border:2px solid #c3c3c3"/>
                        <form action="updateNicrevisionClearenceNa.action" method="post" id="nicrevisionUpdateForm">
                            <s:hidden name="nicRevisionClearenceVO.applicationId" id="applicationId_nicRevision"/>

                            <input type="hidden" name="nicRevisionClearenceVO.nicRevisionVersionId"
                                   id="versionId_nicRevision"/>

                            <table class="table borderless" id="nicRevisionTable">
                                <tr>
                                    <td colspan="3"><h4>Name Update</h4></td>
                                </tr>
                                <tr>
                                    <td><b>Changed Name:</b></td>
                                    <td>
                                        <input type="text" name="nicRevisionClearenceVO.nicRevisionChangedName"
                                               class="form-control" id="nicRevisionChangedName"/>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><b>Upload front side Copy:</b></td>
                                    <td>
                                        <input id="nicFileUpload" name="nicFileUpload" type="file"/>
                                        <s:hidden name="nicRevisionClearenceVO.nicFileName" id="nicUploadPath"
                                                  cssClass="form-control"/>
                                    </td>
                                    <td>
                                        <img src="images/upload.png" style="cursor: pointer;"
                                             onclick="uploadFile('nic');" height="35px"/>
                                        <img src="images/ajax-loader.gif" id="ajax_loader_nic" style="display:none;"/>
                                        <img src="images/right_green.jpg" id="upload_complete_nic"
                                             style="display:none;"/>
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
                                            to Open/Download front side file
                                        </div>
                                        <div>
                                            <img src="images/id_card_icon.png" id="nicRevisionImageView"
                                                 style="height: 100px;width:135px;"/>
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td><b>Upload back side Copy:</b></td>
                                    <td>
                                        <input id="nicFileUploadBack" name="nicFileUploadBack" type="file"/>
                                        <s:hidden name="nicRevisionClearenceVO.nicFileNameBack" id="nicUploadPathBack"
                                                  cssClass="form-control"/>
                                    </td>
                                    <td>
                                        <img src="images/upload.png" style="cursor: pointer;"
                                             onclick="uploadFile('nic_back');" height="35px"/>
                                        <img src="images/ajax-loader.gif" id="ajax_loader_nic_back"
                                             style="display:none;"/>
                                        <img src="images/right_green.jpg" id="upload_complete_nic_back"
                                             style="display:none;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td colspan="2">
                                        <div id="image_link_click_nic_revision_back" style="display: none;">
                                            Please click
                                            <a id="nicRevisionFileNameBack" target="_blank" href="javascript:void(0)">
                                                <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                            </a>
                                            to Open/Download back side file
                                        </div>
                                        <div>
                                            <img src="images/id_card_icon.png" id="nicRevisionImageViewBack"
                                                 style="height: 100px;width:135px;"/>
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table>
                                            <tr>
                                                <td valign="middle" style="vertical-align: middle;padding: 2px;">
                                                    <input type="checkbox" name="updateClearence" id="updateClearence"/>
                                                    <input type="hidden" name="updateClearenceStatus"
                                                           id="updateClearenceStatus"/>
                                                </td>
                                                <td valign="middle"
                                                    style="vertical-align: middle;padding:5px 2px 0px 2px;">Update
                                                    Clearance Statsu to 'Name Issue Cleared'
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td style="text-align: right;">
                                        <button type="button" onclick="updateNICRevision()" class="btn btn-primary">
                                            Save
                                        </button>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>

                </div>

            </div>
        </div>

        <!--  #####################################################	VIEW COMMENT POPUP   ######################################################## -->
        <div class="modal fade" id="commentInputModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <form action="updateCertificateIssuanceNa.action" method="post" id="certificateIssuanceUpdateForm"
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

                            <table class="table borderless" id="commentInputTable">
                                <tr>
                                    <td><b>Please add a comment for your action:</b></td>
                                </tr>
                                <tr>
                                    <td><textarea name="clearenceVO.comment" id="commentInput"
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
                                <td colspan="2"><b>OIC Comment:</b></td>
                            </tr>
                            <tr>
                                <td id="oicCommentAppendDiv">No comment is available!</td>
                                <td id="oicCommentDateAppendDiv"></td>
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
                <form action="resendClearanceNoAdverse.action" method="post" id="resendClearanceUpdateForm"
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


        function refreshPage() {
            var conf = confirm('Are you sure you want to refersh the page?');
            if (conf) {
                $('#myForm').submit();
            }
        }

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
                if (reason == '' || reason == null) {
                    isValid = false;
                    alert('Please enter the reason to resend!');
                }
            }

            return isValid;
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

                    $('#oicCommentAppendDiv').html(commentTypeVO.oicComment);
                    $('#oicCommentDateAppendDiv').html(commentTypeVO.oicCommentDate);

                    $('#polStatusText').html(commentTypeVO.polStatusText);
                    $('#nicStatusText').html(commentTypeVO.nicStatusText);
                    $('#cidStatusText').html(commentTypeVO.cidStatusText);
                    $('#tidStatusText').html(commentTypeVO.tidStatusText);
                    $('#sisStatusText').html(commentTypeVO.sisStatusText);
                    $('#imiStatusText').html(commentTypeVO.imiStatusText);

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

            $('#nicCommentDateAppendDiv').html('');
            $('#cidCommentDateAppendDiv').html('');
            $('#tidCommentDateAppendDiv').html('');
            $('#sisCommentDateAppendDiv').html('');
            $('#immiCommentDateAppendDiv').html('');

            $('#nicStatusText').html('');
            $('#cidStatusText').html('');
            $('#tidStatusText').html('');
            $('#sisStatusText').html('');
            $('#imiStatusText').html('');
            $('#polStatusText').html('');

            $('#policeCommentAppendDiv').html('');
        }


        function viewNicRevisionForm(applicationId) {
            $('#notificationemailsent_applicationId').val(applicationId);
            blockUI();
            $.get('loadCommentList.action', {applicationId: applicationId}, function (data) {
                var commentTypeVO = data.typeDisplayVO;
                if (!(commentTypeVO == null)) {
                    $('#nic_email_comment').html(commentTypeVO.nicComment);
                    $('#imi_email_comment').html(commentTypeVO.imiComment);
                } else {
                    alert('Sorry, Could not load the comments for this Application!');
                    var defaultComment = 'No comment is available!';
                    $('#nic_email_comment').html(defaultComment);
                    $('#imi_email_comment').html(defaultComment);
                }

                var hiddenImiStatus = $('#hiddenImiStatus_' + applicationId).val();
                if (hiddenImiStatus == 'NI') {
// 			  		$('#emailText').val($('#hiddenImiNameIssueEmail_' + applicationId).val());
                    $('#hiddenEmailContent').html($('#hiddenImiNameIssueEmail_' + applicationId).val());
                }

                $('#imi_email_cleared_status').html($('#hiddenImiStatusText_' + applicationId).val());
                $('#nic_email_cleared_status').html($('#hiddenNicStatusText_' + applicationId).val());

                var notificationEmailSentStatus = parseInt($('#hiddenNotificationEmailSentStatus_' + applicationId).val());
                if (!(isNaN(notificationEmailSentStatus))) {
                    if (notificationEmailSentStatus > 0) {
                        if (notificationEmailSentStatus > 1) {
                            $('#no_of_Times_sent_early').html(' - Email has been already sent ' + notificationEmailSentStatus + ' times.');
                        } else {
                            $('#no_of_Times_sent_early').html(' - Email has been already sent ' + notificationEmailSentStatus + ' time.');
                        }
                    }
                }

                var hiddenNicStatus = $('#hiddenNicStatus_' + applicationId).val();
                if (hiddenNicStatus == 'NI') {
// 					$('#emailText').val($('#hiddenNicNameIssueEmail_' + applicationId).val());
                    $('#hiddenEmailContent').html($('#hiddenImiNameIssueEmail_' + applicationId).val());
                }
                unBlockUI();

                var versionId = $('#hiddenVersionId_' + applicationId).val();
                var clearenceStatusSelect = $('#clearenceStatusSelect_' + applicationId).val();

                $('#versionId_nicRevision').val(versionId);
                $('#applicationId_nicRevision').val(applicationId);
                $('#clearenceStatus_nicRevision').val(clearenceStatusSelect);

                $('#nicRevisionModellLink').click();
            });
        }


        function checkAndUpdateMailSentStatus() {
            blockUI();
            var isChekd = $("#doSendEmail").is(':checked');
            if (isChekd) {
                var emailText = $.trim($('#hiddenEmailContent').html());
                if (!(emailText == '' || emailText == null || emailText == 'undefined')) {
                    var applicationId = $('#notificationemailsent_applicationId').val();
                    if (!(applicationId == 0)) {
                        $.post('updateEmailSentStatus.action', {
                            applicationId: applicationId,
                            emailText: emailText
                        }, function (data) {
                            var status = data.emailSentStatus;
                            if (status == 'SUCCESS') {
                                alert('Email was sent successfully!')
                                $('#emailText').val('');
                                $('#notificationemailsent_applicationId').val(0);
                                $("#doSendEmail").attr('checked', false);

                                var times = parseInt($('#hiddenNotificationEmailSentStatus_' + applicationId).val());
                                times = times + 1;
                                $('#hiddenNotificationEmailSentStatus_' + applicationId).val(times);

                                $('#nicRevisionModellLink').click();
                            } else if (status == 'PLEASE ENTER EMAIL TEXT') {
                                alert('Please enter the email text!');
                            } else if (status == 'ERROR') {
                                alert('Error while sending email, Please try again later!');
                            } else {
                                alert(status);
                            }
                            unBlockUI();
                        });
                    } else {
                        alert('Please select the application!');
                        unBlockUI();
                    }
                } else {
                    alert('Please enter the email text!');
                    unBlockUI();
                }
            } else {
                $('#emailText').val('');
                $('#notificationemailsent_applicationId').val(0);
                $("#doSendEmail").attr('checked', false);
                $('#nicRevisionModellLink').click();
                unBlockUI();
            }
        }


        function updateNICRevision() {
            var isChekd = $("#updateClearence").is(':checked');
            var file = $('#nicUploadPath').val();
            var isValid = true;
            var changedName = $.trim($('#nicRevisionChangedName').val());
            if (changedName == null || changedName == '' || changedName == 'undefined') {
                alert('Please enter the changed name to proceed!');
                isValid = false;
            } else if (file == null || file == '' || file == 'undefined') {
                alert('Please upload the new file to proceed!');
                isValid = false;
            }
            if (isValid) {
                if (isChekd) {
                    $('#updateClearenceStatus').val(1);
                    var conf = confirm('Are you sure you want to clear the name issue? This action will resend the application to other departments for approval again!');
                    if (conf) {
                        blockUI();
                        $('#nicrevisionUpdateForm').submit();
                    }
                } else {
                    blockUI();
                    $('#nicrevisionUpdateForm').submit();
                }
            }

        }


        function printCertificate(applicationId) {
            $('#printCertificateFormApplicationId').val(applicationId);
            $('#hiddenFormPrintCertificate').submit();
        }

        function saveRow(applicationId) {
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
                    hasAnyChanged = true;
                }
            }

            if (hasAnyChanged) {
                var hasOtherModelLoaded = false;
                if (canEditClearence == 1) {
                    var clearenceStatusSelect = $.trim($('#clearenceStatusSelect_' + applicationId).val());
                    var oldStaus = $.trim($('#oldClearenceStatus_' + applicationId).val());

                    if (!(clearenceStatusSelect == oldStaus)) {
                        if (oldStaus == 'PN' && clearenceStatusSelect == 'NC') {
                            hasOtherModelLoaded = true;
                            var conf = confirm('Are you sure you want to clear the name issue? This action will resend the application to other departments for approval again!');
                            if (conf) {
                                $('#versionId_clearence').val(versionId);
                                $('#commentInputModalLink').click();
                            }
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
                                    $('#currentReason_resend_clearence').val('');
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
                        }
                    }
                }

                if (!(hasOtherModelLoaded)) {
                    $('#versionId_clearence').val(versionId);
                    $('#commentInputModalLink').click();
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
        }


        //to upload the external reports for blanks
        function uploadFile(fileType) {
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            blockUI();

            var xhr = new XMLHttpRequest();

            var nicInput = document.getElementById('nicFileUpload');
            var nicInputBack = document.getElementById('nicFileUploadBack');
            var nicFile = "";
            var nicFileBack = "";
            var fileNicPath = "";
            var fileNicPathBack = "";
            var nicExtension = "";
            var nicExtensionBack = "";

            if (fileType == 'nic') {
                nicFile = nicInput.files[0];
                fileNicPath = document.getElementById('nicFileUpload').value;
                nicExtension = fileNicPath.split(".").pop().trim();
                if (!validateFileUpload('Nic', fileNicPath, nicExtension, nicFile)) {
                    $("#ajax_loader_nic").hide();
                    return false;
                }
            } else if (fileType == 'nic_back') {
                nicFileBack = nicInputBack.files[0];
                fileNicPathBack = document.getElementById('nicFileUploadBack').value;
                nicExtensionBack = fileNicPathBack.split(".").pop().trim();
                if (!validateFileUpload('Nic', fileNicPathBack, nicExtensionBack, nicFileBack)) {
                    $("#ajax_loader_nic_back").hide();
                    return false;
                }
            }

            var formData = new FormData();
            if (fileType == 'nic') {
                $("#ajax_loader_nic").show();
                formData.append('file', nicFile);
                formData.append('fileExtension', nicExtension);
                formData.append('fileType', "NICF");
            } else if (fileType == 'nic_back') {
                $("#ajax_loader_nic_back").show();
                formData.append('file', nicFileBack);
                formData.append('fileExtension', nicExtensionBack);
                formData.append('fileType', "NICB");
            }
            formData.append('uploadType', "REUPCLR");

            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);

                    var fileType = jsonData.fileType;
                    var fileCategory = jsonData.fileCategory;

                    if (fileType == 'NICF') {
                        var nicFileName = jsonData.fileName;
                        if (nicFileName != '') {
                            $("#nicUploadPath").val(nicFileName);
                        }
                        $("#ajax_loader_nic").hide();
                        $("#upload_complete_nic").show();

                        if (fileCategory == 'IMAGE') {
                            $('#nicRevisionImageView').attr('src', 'policeFileFinder.htm?fileName=' + nicFileName);
                        } else {
                            $('#nicRevisionImageView').attr('src', 'images/no_preview_available.png');
                        }
                        $('#nicRevisionFileName').attr('href', 'policeFileFinder.htm?fileName=' + nicFileName);
                        $('#image_link_click_nic_revision').show();

                    } else if (fileType == 'NICB') {
                        var nicFileNameBack = jsonData.fileName;
                        if (nicFileNameBack != '') {
                            $("#nicUploadPathBack").val(nicFileNameBack);
                        }
                        $("#ajax_loader_nic_back").hide();
                        $("#upload_complete_nic_back").show();

                        if (fileCategory == 'IMAGE') {
                            $('#nicRevisionImageViewBack').attr('src', 'policeFileFinder.htm?fileName=' + nicFileNameBack);
                        } else {
                            $('#nicRevisionImageViewBack').attr('src', 'images/no_preview_available.png');
                        }
                        $('#nicRevisionFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + nicFileNameBack);
                        $('#image_link_click_nic_revision_back').show();
                    }
                    unBlockUI();
                }
            }
        }

        function validateFileUpload(uploadingFile, path, extension, file) {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            if (path == '') {
                alert("please select a file before upload for " + uploadingFile);
                return false;
            } else if (extension != "pdf" && extension != "PDF" && extension != "doc" && extension != "DOC" && extension != "png" && extension != "PDF" && extension != "jpg" && extension != "jpg") {
                alert("invalid " + uploadingFile + " file format");
                return false;
            } else if (file.size > maximumFileLimit) {
                alert(uploadingFile + " file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
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