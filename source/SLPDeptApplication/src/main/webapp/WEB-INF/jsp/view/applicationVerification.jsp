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
        <title>Application Verification</title>
        <style type="text/css">
            .label {
                color: #000 !important;
            }

            div.upload {
                width: 90px;
                height: 23px;
                overflow: hidden;
                margin-top: -15px;
                background: url(images/selectbutton.png);
            }

            div.upload input {
                display: block !important;
                width: 90px !important;
                height: 23px !important;
                opacity: 0 !important;
                overflow: hidden !important;
            }

            div.boxscroll {
                overflow: scroll;
            }

            .wall_override {
                background-color: #fff !important;
                border: 0px solid #e1e2e3 !important;
                border-radius: 4px !important;
                box-shadow: 0 0px 0px rgba(0, 0, 0, 0.05) inset !important;
                margin-bottom: 20px !important;
                min-height: 20px !important;
                padding: 20px !important;
            }

            .wall_override_name {
                background-color: #fff !important;
                border: 0px solid #e1e2e3 !important;
                border-radius: 4px !important;
                box-shadow: 0 0px 0px rgba(0, 0, 0, 0.05) inset !important;
                margin-bottom: 20px !important;
                min-height: 20px !important;
                padding: 35px !important;
            }

            @media (min-width: 992px) {
                .modal-lg {
                    width: 950px;
                    /* height: 900px;  control height here */
                }
            }

            .modal-lg {
                width: 80% !important;
            }

            @media (max-width: 768px) {
                .modal {
                    width: 90% !important;
                    margin-left: 2%;
                }
            }

        </style>
    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

        <!-- including header -->
        <jsp:include page="../common/header.jsp"/>

        <!-- Starting the page Title -->
        <div id="es-content">

            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Application Verification</c:set>
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
                    <form class="form-horizontal" action="searchApplicationVerification.action" method="post"
                          id="searchApplicationVerification">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label">From</s:label></strong>
                                </div>
                                <div class="col-sm-6">
                                    <c:set var="customStartDate">
                                        <fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <input type="text" readonly="readonly" name="fromDate" value="${customStartDate}"
                                           id="fromDate_id" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <strong><s:label cssClass="control-label bold-label">To</s:label></strong>
                                </div>
                                <div class="col-sm-6">
                                    <c:set var="customEndDate">
                                        <fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <input type="text" readonly="readonly" name="toDate" value="${customEndDate}"
                                           id="toDate_id" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="reviewStatus_id">Status </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:select cssClass="form-control" headerKey="" headerValue="All"
                                              list="reviewStatusMap" listValue="value.displayName" listKey="value"
                                              name="searchReviewStatus" required="false" id="searchReviewStatusId"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="referenceNo_id">Reference No </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:textfield name="searchReferenceNo" id="searchReferenceNoId"
                                                 cssClass="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="nicNoId">NIC No </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:textfield name="nic" id="nicNoId" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="newNicNoId">New NIC No </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:textfield name="newNic" id="newNicNoId" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="passportNoId">Passport No </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:textfield name="passport" id="passportNoId" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="nameId">Name </s:label></strong>
                                </div>
                                <div class="col-sm-7">
                                    <s:textfield name="name" id="nameId" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <div style="text-align:right;">
                                        <input id="searchBtn" type="submit" class="btn btn-primary es-buttton" value="Search"/>
                                        <input type="button" value="Clear" class="btn btn-primary es-buttton"
                                               id="clearReviewApplication"/>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </form>
                    <div style="clear: both;"></div>

                    <div class="form-group">
                        <div class="col-lg-12" id="applicationListAppendDiv" style="max-width: 100%;">
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
                                        <th class="text-center"><strong>Amendments</strong></th>
                                        <th class="text-center"><strong>Attachments</strong></th>
                                        <th class="text-center"><strong>Mode Of Submission</strong></th>
                                        <th class="text-center"><strong>Rivisions</strong></th>
                                        <th class="text-center"><strong>Review Status</strong></th>
                                        <th class="text-center" style="width: 7%;"><strong>Save</strong></th>
                                        <th class="text-center" style="width: 7%;"><strong>Edit</strong></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(applicationList) > 0}">
                                            <c:forEach items="${applicationList}" var="applicationVO">
                                                <input type="hidden"
                                                       id="hiddenApplicationReviewStatus_${applicationVO.applicationId}"
                                                       value="${applicationVO.applicationReviewStatus}"/>
                                                <c:choose>
                                                    <c:when test="${applicationVO.applicationReviewStatus=='RV'}">
                                                        <tr style="background-color: #BCE6FF;">
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/lock.png"
                                                                             style="cursor: pointer;"
                                                                             onclick="unlockRecord('${applicationVO.applicationId}');"
                                                                             width="32px" height="32px"
                                                                             id="${applicationVO.applicationId}">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/unlock.png"
                                                                             style="cursor: pointer;"
                                                                             onclick="lockRecord('${applicationVO.applicationId}');"
                                                                             width="32px" height="32px"
                                                                             id="${applicationVO.applicationId}">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center"><fmt:formatDate
                                                                    value="${applicationVO.submittedDate}"
                                                                    pattern="yyyy-MM-dd hh:mm aa"/></td>
                                                            <td class="text-center">
                                                                <a herf="javascript:void(0)"
                                                                   onclick="viewApplicationByReferenceNo('${applicationVO.referenceNo}');"
                                                                   style="cursor: pointer; cursor: hand;">${applicationVO.referenceNo}</a>
                                                                <input type="hidden"
                                                                       id="referenceNoSave${applicationVO.applicationId}"
                                                                       value="${applicationVO.referenceNo}"/><%-- <br />
																			<img src="images/viewhistory.png" width="20px" height="20px" id="viewHistory_${applicationVO.applicationId}"> --%>
                                                            </td>

                                                            <td class="text-center">${applicationVO.currentNicNo}</td>

                                                            <td class="text-center">${applicationVO.passport}</td>
                                                            <td class="text-center">${applicationVO.applicantNameAsNic}</td>
                                                            <td class="text-center"><img src="images/list_items.png"
                                                                                         style="cursor: pointer;"
                                                                                         <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAddressList(${applicationVO.applicationId});"</c:if>
                                                                                         width="25px" height="25px"
                                                                                         id="address${applicationVO.applicationId}"
                                                                                         <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
                                                            </td>
                                                                <%--<td class="text-center"><img src="images/passport.png"  onclick="showPassport('${applicationVO.passportAttachPath}');" width="32px" height="32px" id="passport${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
																		 | <img src="images/nic.png"  onclick="showNIC('${applicationVO.nicAttachPath}');" width="32px" height="32px" id="nic${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>></td> --%>
                                                            <td class="text-center">
                                                                <c:if test="${! empty (applicationVO.nicAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNicPopup(${applicationVO.applicationId});"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${! empty (applicationVO.newNicAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNewNicPopup(${applicationVO.applicationId});"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;New NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${! empty (applicationVO.passportAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewPptPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;PPT</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                                <!-- Birth Certificate -->
                                                                <c:if test="${! empty (applicationVO.birthCertificatePath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewBcPopup(${applicationVO.applicationId})"</c:if>>
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

                                                                                    <input type="hidden"
                                                                                           id="hiddenBcNo_${applicationVOView.applicationId}"
                                                                                           value=""/>
                                                                                    <img alt="BC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;BC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                                <!-- SLBFE Certificate -->
                                                                <c:if test="${! empty (applicationVO.letterOfReferencePath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewSLBFEPopup(${applicationVO.applicationId})"</c:if>>
                                                                        <table>
                                                                            <tr>
                                                                                <td>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFEFileName_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.letterOfReferencePath}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFEFileType_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.letterOfReferenceFileType}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFENo_${applicationVOView.applicationId}"
                                                                                           value=""/>
                                                                                    <img alt="BC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;SLBFE</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <!-- Affidavit -->
                                                                <c:if test="${applicationVO.selectedNameOption =='passport'}">
                                                                    <c:if test="${! empty (applicationVO.affidavitAttachPath)}">
                                                                        <a href="javascript:void(0)"
                                                                           <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAffidavitPopup(${applicationVO.applicationId})"</c:if>>
                                                                            <table>
                                                                                <tr>
                                                                                    <td>
                                                                                        <input type="hidden"
                                                                                               id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                                                                               value="${applicationVO.affidavitAttachPath}"/>
                                                                                        <input type="hidden"
                                                                                               id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                                                                               value="${applicationVO.nicFileType}"/>
                                                                                        <input type="hidden"
                                                                                               id="hiddenAffidavit_${applicationVOView.applicationId}"
                                                                                               value=""/>
                                                                                        <img alt="AFFIDAVIT"
                                                                                             src="images/zoom_in.png"
                                                                                             style="width:15px;height:15px;"/>
                                                                                    </td>
                                                                                    <td>&nbsp;&nbsp;AFFIDAVIT</td>
                                                                                </tr>
                                                                            </table>
                                                                        </a>
                                                                    </c:if>
                                                                </c:if>



                                                            </td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.applicationType == 'ON'}">
                                                                        <c:out value="Online"></c:out>
                                                                    </c:when>
                                                                    <c:when test="${applicationVO.applicationType == 'MA'}">
                                                                        <c:out value="Manual"></c:out>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">
                                                                <!-- commented bcz they requested to edit functionality enable before change the revised state. -->
                                                                <img src="images/revisions.png" style="cursor: pointer;"
                                                                     width="25px" height="25px"
                                                                     id="revisions${applicationVO.applicationId}"

                                                                <c:choose>
                                                                <c:when test="${applicationVO.hasRequestClarification == 2 && applicationVO.hasCurrentUserLocked == 1}">
                                                                     onclick="showRequestForUpdate('${applicationVO.referenceNo}');"
                                                                </c:when>
                                                                <c:when test="${applicationVO.hasRequestClarification == 3 && applicationVO.hasCurrentUserLocked == 1}">
                                                                     onclick="showRequestForUpdate('${applicationVO.referenceNo}');"
                                                                </c:when>
                                                                <c:when test="${applicationVO.hasRequestClarification == 3 || applicationVO.hasCurrentUserLocked != 1}">
                                                                     class="disabled_image"
                                                                </c:when>
                                                                </c:choose>
                                                                ></td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${not empty fn:trim(applicationVO.applicationReviewStatus)}">
                                                                        <select name="reviewStatus"
                                                                                id="reviewStatusId${applicationVO.applicationId}"
                                                                                <c:if test="${applicationVO.hasCurrentUserLocked != 1}">disabled="disabled"</c:if>
                                                                                onchange="showHideRequestClarificationBtn('${applicationVO.applicationId}');">
                                                                            <c:forEach items="${reviewStatusMap}"
                                                                                       var="entry">
                                                                                <c:choose>
                                                                                    <c:when test="${applicationVO.applicationReviewStatus == entry.value}">
                                                                                        <option value="${entry.value}"
                                                                                                selected="selected">${entry.key}</option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="${entry.value}">${entry.key}</option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">
                                                                <input type="hidden"
                                                                       value="${applicationVO.referenceNo}"
                                                                       name="referenceNo"/>
                                                                <input type="hidden"
                                                                       value="${applicationVO.applicationId}"
                                                                       name="applicationId"/>
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/save.png"
                                                                             id="save_image_${applicationVO.applicationId}"
                                                                             alt="Save" title="Save" class="basic_image_button
																						<c:choose>
																							<c:when test="${applicationVO.applicationReviewStatus == 'RC'}">disabled_image</c:when>
																						</c:choose>"
                                                                             onclick="saveRow(${applicationVO.applicationId})"/>&nbsp;
                                                                        <img style="
                                                                        <c:choose>
                                                                        <c:when test="${applicationVO.hasRequestClarification == 1}">display: block;</c:when>
                                                                        <c:when test="${applicationVO.hasRequestClarification != 1}">display: none;</c:when>
                                                                        </c:choose>
                                                                                float: right;"
                                                                             src="images/circle_orange.png"
                                                                             id="rc_image_${applicationVO.applicationId}"
                                                                             class="basic_image_button" width="17px"
                                                                             height="17px"
                                                                             onclick="showRequestClarification(${applicationVO.applicationId});"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/save.png"
                                                                             id="save_image_${applicationVO.applicationId}"
                                                                             alt="Save" title="Save"
                                                                             class="basic_image_button disabled_image"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/edit.png"
                                                                             id="edit_image_${applicationVO.applicationId}"
                                                                             alt="Edit" title="Edit"
                                                                             class="basic_image_button"
                                                                             onclick="editRow(${applicationVO.applicationId})"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/edit.png"
                                                                             id="edit_image_${applicationVO.applicationId}"
                                                                             alt="Edit" title="Edit"
                                                                             class="basic_image_button disabled_image"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:when>
                                                    <c:when test="${applicationVO.hasBlackListed==1}">
                                                        <tr style="background-color: #FFC6D1;">
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/lock.png"
                                                                             style="cursor: pointer;"
                                                                             onclick="unlockRecord('${applicationVO.applicationId}');"
                                                                             width="32px" height="32px"
                                                                             id="${applicationVO.applicationId}">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/unlock.png"
                                                                             style="cursor: pointer;"
                                                                             onclick="lockRecord('${applicationVO.applicationId}');"
                                                                             width="32px" height="32px"
                                                                             id="${applicationVO.applicationId}">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center"><fmt:formatDate
                                                                    value="${applicationVO.submittedDate}"
                                                                    pattern="yyyy-MM-dd hh:mm aa"/></td>
                                                            <td class="text-center">
                                                                <a herf="javascript:void(0)"
                                                                   onclick="viewApplicationByReferenceNo('${applicationVO.referenceNo}');"
                                                                   style="cursor: pointer; cursor: hand;">${applicationVO.referenceNo}</a>
                                                                <input type="hidden"
                                                                       id="referenceNoSave${applicationVO.applicationId}"
                                                                       value="${applicationVO.referenceNo}"/><%-- <br />
																			<img src="images/viewhistory.png" width="20px" height="20px" id="viewHistory_${applicationVO.applicationId}"> --%>
                                                            </td>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.newNic !=''}">
                                                                    <td class="text-center">${applicationVO.newNic}</td>
                                                                </c:when>
                                                                <c:when test="${applicationVO.newNic == '' && applicationVO.nic !=''}">
                                                                    <td class="text-center">${applicationVO.nic}</td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-center">N/A</td>
                                                                </c:otherwise>

                                                            </c:choose>
                                                            <td class="text-center">${applicationVO.passport}</td>
                                                            <td class="text-center">${applicationVO.applicantNameAsNic}</td>
                                                            <td class="text-center"><img src="images/list_items.png"
                                                                                         style="cursor: pointer;"
                                                                                         <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAddressList(${applicationVO.applicationId});"</c:if>
                                                                                         width="25px" height="25px"
                                                                                         id="address${applicationVO.applicationId}"
                                                                                         <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
                                                            </td>
                                                                <%--<td class="text-center"><img src="images/passport.png"  onclick="showPassport('${applicationVO.passportAttachPath}');" width="32px" height="32px" id="passport${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
																		 | <img src="images/nic.png"  onclick="showNIC('${applicationVO.nicAttachPath}');" width="32px" height="32px" id="nic${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>></td> --%>
                                                            <td class="text-center">
                                                                <c:if test="${! empty (applicationVO.nicAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNicPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${! empty (applicationVO.newNicAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNewNicPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;New NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${! empty (applicationVO.passportAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewPptPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                                    <img alt="NIC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;PPT</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                                <!-- Birth Certificate -->
                                                                <c:if test="${! empty (applicationVO.birthCertificatePath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewBcPopup(${applicationVO.applicationId})"</c:if>>
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

                                                                                    <input type="hidden"
                                                                                           id="hiddenBcNo_${applicationVOView.applicationId}"
                                                                                           value=""/>
                                                                                    <img alt="BC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;BC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                                <!-- SLBFE Certificate -->
                                                                <c:if test="${! empty (applicationVO.letterOfReferencePath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewSLBFEPopup(${applicationVO.applicationId})"</c:if>>
                                                                        <table>
                                                                            <tr>
                                                                                <td>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFEFileName_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.letterOfReferencePath}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFEFileType_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.letterOfReferenceFileType}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenSLBFENo_${applicationVOView.applicationId}"
                                                                                           value=""/>
                                                                                    <img alt="BC"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;SLBFE</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>

                                                                <!-- Affidavit -->
                                                                <c:if test="${applicationVO.selectedNameOption =='passport'}">
                                                                <c:if test="${! empty (applicationVO.affidavitAttachPath)}">
                                                                    <a href="javascript:void(0)"
                                                                       <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAffidavitPopup(${applicationVO.applicationId})"</c:if>>
                                                                        <table>
                                                                            <tr>
                                                                                <td>
                                                                                    <input type="hidden"
                                                                                           id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.affidavitAttachPath}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                                                                           value="${applicationVO.nicFileType}"/>
                                                                                    <input type="hidden"
                                                                                           id="hiddenAffidavit_${applicationVOView.applicationId}"
                                                                                           value=""/>
                                                                                    <img alt="AFFIDAVIT"
                                                                                         src="images/zoom_in.png"
                                                                                         style="width:15px;height:15px;"/>
                                                                                </td>
                                                                                <td>&nbsp;&nbsp;AFFIDAVIT</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                                </c:if>
                                                            </td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.applicationType == 'ON'}">
                                                                        <c:out value="Online"></c:out>
                                                                    </c:when>
                                                                    <c:when test="${applicationVO.applicationType == 'MA'}">
                                                                        <c:out value="Manual"></c:out>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center"><img src="images/revisions.png"
                                                                                         style="cursor: pointer;"
                                                                                         width="25px" height="25px"
                                                                                         id="revisions${applicationVO.applicationId}"
                                                                                         class="disabled_image"></td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${not empty fn:trim(applicationVO.applicationReviewStatus)}">
                                                                        <select name="reviewStatus"
                                                                                id="reviewStatusId${applicationVO.applicationId}"
                                                                                <c:if test="${applicationVO.hasCurrentUserLocked != 1}">disabled="disabled"</c:if>
                                                                                onchange="showHideRequestClarificationBtn('${applicationVO.applicationId}');">
                                                                            <c:forEach items="${reviewStatusMap}"
                                                                                       var="entry">
                                                                                <c:choose>
                                                                                    <c:when test="${applicationVO.applicationReviewStatus == entry.value}">
                                                                                        <option value="${entry.value}"
                                                                                                selected="selected">${entry.key}</option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="${entry.value}">${entry.key}</option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">
                                                                <input type="hidden"
                                                                       value="${applicationVO.referenceNo}"
                                                                       name="referenceNo"/>
                                                                <input type="hidden"
                                                                       value="${applicationVO.applicationId}"
                                                                       name="applicationId"/>
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/save.png"
                                                                             id="save_image_${applicationVO.applicationId}"
                                                                             alt="Save" title="Save" class="basic_image_button
																						<c:choose>
																							<c:when test="${applicationVO.applicationReviewStatus == 'RC'}">disabled_image</c:when>
																						</c:choose>"
                                                                             onclick="saveRow(${applicationVO.applicationId})"/>&nbsp;
                                                                        <img style="
                                                                        <c:choose>
                                                                        <c:when test="${applicationVO.hasRequestClarification == 1}">display: block;</c:when>
                                                                        <c:when test="${applicationVO.hasRequestClarification != 1}">display: none;</c:when>
                                                                        </c:choose> float: right;"
                                                                             src="images/circle_orange.png"
                                                                             id="rc_image_${applicationVO.applicationId}"
                                                                             class="basic_image_button" width="17px"
                                                                             height="17px"
                                                                             onclick="showRequestClarification(${applicationVO.applicationId});"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/save.png"
                                                                             id="save_image_${applicationVO.applicationId}"
                                                                             alt="Save" title="Save"
                                                                             class="basic_image_button disabled_image"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                        <img src="images/edit.png"
                                                                             id="edit_image_${applicationVO.applicationId}"
                                                                             alt="Edit" title="Edit"
                                                                             class="basic_image_button"
                                                                             onclick="editRow(${applicationVO.applicationId})"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="images/edit.png"
                                                                             id="edit_image_${applicationVO.applicationId}"
                                                                             alt="Edit" title="Edit"
                                                                             class="basic_image_button disabled_image"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:when>
                                                    <c:when test="${applicationVO.applicationType=='MA'}">
                                                        <c:choose>
                                                            <c:when test="${applicationVO.hasBlackListed==1}">
                                                                <tr style="background-color: #FFC6D1;">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr style="background-color: #fff;">
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/lock.png" style="cursor: pointer;"
                                                                         onclick="unlockRecord('${applicationVO.applicationId}');"
                                                                         width="32px" height="32px"
                                                                         id="${applicationVO.applicationId}">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/unlock.png"
                                                                         style="cursor: pointer;"
                                                                         onclick="lockRecord('${applicationVO.applicationId}');"
                                                                         width="32px" height="32px"
                                                                         id="${applicationVO.applicationId}">
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center"><fmt:formatDate
                                                                value="${applicationVO.submittedDate}"
                                                                pattern="yyyy-MM-dd hh:mm aa"/></td>
                                                        <td class="text-center">
                                                            <a herf="javascript:void(0)"
                                                               onclick="viewApplicationByReferenceNo('${applicationVO.referenceNo}');"
                                                               style="cursor: pointer; cursor: hand;">${applicationVO.referenceNo}</a>
                                                            <input type="hidden"
                                                                   id="referenceNoSave${applicationVO.applicationId}"
                                                                   value="${applicationVO.referenceNo}"/><%-- <br />
																			<img src="images/viewhistory.png" width="20px" height="20px" id="viewHistory_${applicationVO.applicationId}"> --%>
                                                        </td>
                                                        <td class="text-center">${applicationVO.currentNicNo}</td>
                                                        <td class="text-center">${applicationVO.passport}</td>
                                                        <td class="text-center">${applicationVO.applicantNameAsNic}</td>
                                                        <td class="text-center"><img src="images/list_items.png"
                                                                                     style="cursor: pointer;"
                                                                                     <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAddressList(${applicationVO.applicationId});"</c:if>
                                                                                     width="25px" height="25px"
                                                                                     id="address${applicationVO.applicationId}"
                                                                                     <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
                                                        </td>
                                                        <%--<td class="text-center"><img src="images/passport.png"  onclick="showPassport('${applicationVO.passportAttachPath}');" width="32px" height="32px" id="passport${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
																		 | <img src="images/nic.png"  onclick="showNIC('${applicationVO.nicAttachPath}');" width="32px" height="32px" id="nic${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>></td> --%>
                                                        <td class="text-center">
                                                            <c:if test="${! empty (applicationVO.nicAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNicPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                            <td>&nbsp;&nbsp;NIC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${! empty (applicationVO.newNicAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNewNicPopup(${applicationVO.applicationId});"</c:if>>
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
                                                                                <img alt="NIC"
                                                                                     src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;New NIC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${! empty (applicationVO.passportAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewPptPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                            <td>&nbsp;&nbsp;PPT</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <!-- Birth Certificate -->
                                                            <c:if test="${! empty (applicationVO.birthCertificatePath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewBcPopup(${applicationVO.applicationId})"</c:if>>
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

                                                                                <input type="hidden"
                                                                                       id="hiddenBcNo_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="BC" src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;BC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                            <!-- SLBFE Certificate -->
                                                            <c:if test="${! empty (applicationVO.letterOfReferencePath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewSLBFEPopup(${applicationVO.applicationId})"</c:if>>
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFEFileName_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.letterOfReferencePath}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFEFileType_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.letterOfReferenceFileType}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFENo_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="BC" src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;SLBFE</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <!-- Affidavit -->
                                                            <c:if test="${applicationVO.selectedNameOption =='passport'}">
                                                            <c:if test="${! empty (applicationVO.affidavitAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAffidavitPopup(${applicationVO.applicationId})"</c:if>>
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.affidavitAttachPath}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.nicFileType}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavit_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="AFFIDAVIT"
                                                                                     src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;AFFIDAVIT</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                        </c:if>
                                                        </td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.applicationType == 'ON'}">
                                                                    <c:out value="Online"></c:out>
                                                                </c:when>
                                                                <c:when test="${applicationVO.applicationType == 'MA'}">
                                                                    <c:out value="Manual"></c:out>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center"><img src="images/revisions.png"
                                                                                     style="cursor: pointer;"
                                                                                     width="25px" height="25px"
                                                                                     id="revisions${applicationVO.applicationId}"
                                                                                     class="disabled_image"></td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${not empty fn:trim(applicationVO.applicationReviewStatus)}">
                                                                    <select name="reviewStatus"
                                                                            id="reviewStatusId${applicationVO.applicationId}"
                                                                            <c:if test="${applicationVO.hasCurrentUserLocked != 1}">disabled="disabled"</c:if>
                                                                            onchange="showHideRequestClarificationBtn('${applicationVO.applicationId}');">
                                                                        <c:forEach items="${reviewStatusMap}"
                                                                                   var="entry">
                                                                            <c:choose>
                                                                                <c:when test="${applicationVO.applicationReviewStatus == entry.value}">
                                                                                    <option value="${entry.value}"
                                                                                            selected="selected">${entry.key}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${entry.value}">${entry.key}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center">
                                                            <input type="hidden" value="${applicationVO.referenceNo}"
                                                                   name="referenceNo"/>

                                                            <input id="hiddenReferenceNumber_${applicationVO.applicationId}" type="hidden" value="${applicationVO.referenceNo}"/>

                                                            <input type="hidden" value="${applicationVO.applicationId}"
                                                                   name="applicationId"/>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/save.png"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save" class="basic_image_button
																						<c:choose>
																							<c:when test="${applicationVO.applicationReviewStatus == 'RC'}">disabled_image</c:when>
																						</c:choose>"
                                                                         onclick="saveRow(${applicationVO.applicationId})"/>&nbsp;
                                                                    <img style="
                                                                    <c:choose>
                                                                    <c:when test="${applicationVO.hasRequestClarification == 1}">display: block;</c:when>
                                                                    <c:when test="${applicationVO.hasRequestClarification != 1}">display: none;</c:when>
                                                                    </c:choose> float: right;"
                                                                         src="images/circle_orange.png"
                                                                         id="rc_image_${applicationVO.applicationId}"
                                                                         class="basic_image_button" width="17px"
                                                                         height="17px"
                                                                         onclick="showRequestClarification(${applicationVO.applicationId});"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/save.png"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/edit.png"
                                                                         id="edit_image_${applicationVO.applicationId}"
                                                                         alt="Edit" title="Edit"
                                                                         class="basic_image_button"
                                                                         onclick="editRow(${applicationVO.applicationId})"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/edit.png"
                                                                         id="edit_image_${applicationVO.applicationId}"
                                                                         alt="Edit" title="Edit"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${applicationVO.hasBlackListed==1}">
                                                                <tr style="background-color: #FFC6D1;">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr style="background-color: #C8F4C9;">
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/lock.png" style="cursor: pointer;"
                                                                         onclick="unlockRecord('${applicationVO.applicationId}');"
                                                                         width="32px" height="32px"
                                                                         id="${applicationVO.applicationId}">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/unlock.png"
                                                                         style="cursor: pointer;"
                                                                         onclick="lockRecord('${applicationVO.applicationId}');"
                                                                         width="32px" height="32px"
                                                                         id="${applicationVO.applicationId}">
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center"><fmt:formatDate
                                                                value="${applicationVO.submittedDate}"
                                                                pattern="yyyy-MM-dd hh:mm aa"/></td>
                                                        <td class="text-center">
                                                            <a herf="javascript:void(0)"
                                                               onclick="viewApplicationByReferenceNo('${applicationVO.referenceNo}');"
                                                               style="cursor: pointer; cursor: hand;">${applicationVO.referenceNo}</a>
                                                            <input type="hidden"
                                                                   id="referenceNoSave${applicationVO.applicationId}"
                                                                   value="${applicationVO.referenceNo}"/><%-- <br />
																			<a herf="javascript:void(0)" <c:if test="${applicationVO.hasCurrentUserLocked == 1}" > onclick="viewApplicationByReferenceNo('${applicationVO.referenceNo}');"</c:if> style="cursor: pointer; cursor: hand;">
																				<img src="images/viewhistory.png" width="20px" height="20px" id="viewHistory_${applicationVO.applicationId}">
																			</a> --%>
                                                        </td>
                                                        <td class="text-center">${applicationVO.currentNicNo}</td>

                                                        <td class="text-center">${applicationVO.passport}</td>
                                                        <td class="text-center">${applicationVO.applicantNameAsNic}</td>
                                                        <td class="text-center"><img src="images/list_items.png"
                                                                                     style="cursor: pointer;"
                                                                                     <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAddressList(${applicationVO.applicationId});"</c:if>
                                                                                     width="25px" height="25px"
                                                                                     id="address${applicationVO.applicationId}"
                                                                                     <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
                                                        </td>
                                                        <%--<td class="text-center"><img src="images/passport.png"  onclick="showPassport('${applicationVO.passportAttachPath}');" width="32px" height="32px" id="passport${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>>
																		| <img src="images/nic.png"  onclick="showNIC('${applicationVO.nicAttachPath}');" width="32px" height="32px" id="nic${applicationVO.applicationId}" <c:if test="${applicationVO.hasCurrentUserLocked != 1}">class="disabled_image"</c:if>></td>--%>
                                                        <td class="text-center">
                                                            <c:if test="${! empty (applicationVO.nicAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNicPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                            <td>&nbsp;&nbsp;NIC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${! empty (applicationVO.newNicAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewNewNicPopup(${applicationVO.applicationId});"</c:if>>
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
                                                                                <img alt="NIC"
                                                                                     src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;New NIC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${! empty (applicationVO.passportAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewPptPopup(${applicationVO.applicationId})"</c:if>>
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
                                                                            <td>&nbsp;&nbsp;PPT</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                            <!-- Birth Certificate -->
                                                            <c:if test="${! empty (applicationVO.birthCertificatePath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewBcPopup(${applicationVO.applicationId})"</c:if>>
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

                                                                                <input type="hidden"
                                                                                       id="hiddenBcNo_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="BC" src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;BC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                            <!-- SLBFE Certificate -->
                                                            <c:if test="${! empty (applicationVO.letterOfReferencePath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewSLBBFEPopup(${applicationVO.applicationId})"</c:if>>
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFEFileName_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.letterOfReferencePath}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFEFileType_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.letterOfReferenceFileType}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenSLBFENo_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="BC" src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;SLBFE</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <!-- Affidavit -->
                                                            <c:if test="${applicationVO.selectedNameOption =='passport'}">
                                                            <c:if test="${! empty (applicationVO.affidavitAttachPath)}">
                                                                <a href="javascript:void(0)"
                                                                   <c:if test="${applicationVO.hasCurrentUserLocked == 1}">onclick="viewAffidavitPopup(${applicationVO.applicationId})"</c:if>>
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.affidavitAttachPath}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                                                                       value="${applicationVO.nicFileType}"/>
                                                                                <input type="hidden"
                                                                                       id="hiddenAffidavit_${applicationVOView.applicationId}"
                                                                                       value=""/>
                                                                                <img alt="AFFIDAVIT"
                                                                                     src="images/zoom_in.png"
                                                                                     style="width:15px;height:15px;"/>
                                                                            </td>
                                                                            <td>&nbsp;&nbsp;AFFIDAVIT</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                            </c:if>

                                                        </td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.applicationType == 'ON'}">
                                                                    <c:out value="Online"></c:out>
                                                                </c:when>
                                                                <c:when test="${applicationVO.applicationType == 'MA'}">
                                                                    <c:out value="Manual"></c:out>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center"><img src="images/revisions.png"
                                                                                     style="cursor: pointer;"
                                                                                     width="25px" height="25px"
                                                                                     id="revisions${applicationVO.applicationId}"
                                                                                     class="disabled_image"></td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${not empty fn:trim(applicationVO.applicationReviewStatus)}">
                                                                    <select name="reviewStatus"
                                                                            id="reviewStatusId${applicationVO.applicationId}"
                                                                            <c:if test="${applicationVO.hasCurrentUserLocked != 1}">disabled="disabled"</c:if>
                                                                            onchange="showHideRequestClarificationBtn('${applicationVO.applicationId}');">
                                                                        <c:forEach items="${reviewStatusMap}"
                                                                                   var="entry">
                                                                            <c:choose>
                                                                                <c:when test="${applicationVO.applicationReviewStatus == entry.value}">
                                                                                    <option value="${entry.value}"
                                                                                            selected="selected">${entry.key}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${entry.value}">${entry.key}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center">

                                                            <input type="hidden" value="${applicationVO.referenceNo}"
                                                                   name="referenceNo"/>
                                                            <input type="hidden" value="${applicationVO.applicationId}"
                                                                   name="applicationId"/>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/save.png"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save" class="basic_image_button
																					<c:choose>
																						<c:when test="${applicationVO.applicationReviewStatus == 'RC'}">disabled_image</c:when>
																					</c:choose>"
                                                                         onclick="saveRow(${applicationVO.applicationId})"/>&nbsp;
                                                                    <img style="
                                                                    <c:choose>
                                                                    <c:when test="${applicationVO.hasRequestClarification == 1}">display: block;</c:when>
                                                                    <c:when test="${applicationVO.hasRequestClarification != 1}">display: none;</c:when>
                                                                    </c:choose> float: right;"
                                                                         src="images/circle_orange.png"
                                                                         id="rc_image_${applicationVO.applicationId}"
                                                                         class="basic_image_button" width="17px"
                                                                         height="17px"
                                                                         onclick="showRequestClarification(${applicationVO.applicationId});"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/save.png"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/edit.png"
                                                                         id="edit_image_${applicationVO.applicationId}"
                                                                         alt="Edit" title="Edit"
                                                                         class="basic_image_button"
                                                                         onclick="editRow(${applicationVO.applicationId})"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/edit.png"
                                                                         id="edit_image_${applicationVO.applicationId}"
                                                                         alt="Edit" title="Edit"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center" colspan="11">No Results Found !</td>
                                        </c:otherwise>
                                    </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div id="legends" style="padding: 5px;float: left;margin-left: 10px;">
                            <table>
                                <tr>
                                    <td>
                                        <div class="legend_div" style="background-color:#fff;">Manual Applications</div>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="legend_div" style="background-color:#C8F4C9;">Online Applications
                                        </div>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="legend_div" style="background-color:#BCE6FF;">Revised Applications
                                        </div>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="legend_div" style="background-color:#FFC6D1;">Blacklisted
                                            Applications
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div style="clear: both;"></div>
                    </div>
                </div>
            </div>

            <form action="updateReviewStatus.action" method="post" id="reviewUpdateForm">
                <s:hidden name="applicationId" id="applicationId_review"/>
                <s:hidden name="reviewStatus" id="reviewStatus_review"/>
                <s:hidden name="referenceNo" id="referenceNo_review"/>
            </form>

            <form action="loadEditApplication.action" method="post" target="_blank"
                  id="applicationCompleteModificationForm">
                <s:hidden name="applicationId" id="modifyApplicationCompleteFormAppId"/>
            </form>

            <div style="clear: both;"></div>
        </div>

        <!-- including footer -->
        <jsp:include page="../common/footer.jsp"/>

        <div style="display: none;">
                <%--<a data-toggle="modal" id="imageLink" href="#imageModalPopUp" >NIC</a>--%>
            <a data-toggle="modal" id="pptViewModellLink" href="#pptViewModelPopUp">View</a>
            <a data-toggle="modal" id="bcViewModellLink" href="#bcViewModelPopUp">View</a>
            <a data-toggle="modal" id="slbfeViewModellLink" href="#slbfeViewModelPopUp">View</a>
            <a data-toggle="modal" id="affidavitViewModellLink" href="#affidavitViewModelPopUp">View</a>
            <a data-toggle="modal" id="nicViewModellLink" href="#nicViewModelPopUp">View</a>
            <a data-toggle="modal" id="newNicViewModellLink" href="#newNicViewModelPopUp">View</a>
            <a data-toggle="modal" id="stayInSL" href="#stayInSLModalPopUp">Amendments</a>
            <a data-toggle="modal" id="requestClarification" href="#requestClarificationModalPopUp">Request
                Clarification</a>
            <a data-toggle="modal" id="requestforUpdate" href="#reqForUpdateModalPopUp">Request for Update</a>
        </div>
        <!--  #####################################################	 IMAGE MODEl POPUP   ######################################################## -->
            <%--<div class="modal fade" id="imageModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			 <div class="modal-dialog modal-lg">
	   			 <div class="modal-content">
					<div class="modal-header">
					     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					     <h4 class="modal-title" id="imageHeader"></h4>
					</div>

					<div class="modal-body">
						<img id="image"/>
					 </div>
					 <div class="modal-footer">
					 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					 </div>
					</div>
				</div>
			</div>--%>
        <!--  #####################################################	IMAGE MODEl POPUP   ######################################################## -->
        <!--  #####################################################	 Request for update MODEl POPUP   ######################################################## -->
        <div class="modal fade boxscroll" id="reqForUpdateModalPopUp" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Request for Updates - Department</h4>
                    </div>

                    <div class="modal-body">
                        <div style="clear: both;"></div>
                        <!-- START -->
                        <div>
                            <div class="col-lg-7"></div>
                        </div>
                        <div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <strong><s:label cssClass="control-label bold-label"
                                                         for="referenceNo_id">Reference No </s:label></strong>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="input_div" id="referenceNoRequestUpdate"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->
                        <br/>
                        <br/>
                        <div>
                            <div class="col-lg-12">
                                Please submit the following requested information.
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div>
                            <div class="col-lg-7">
                                <p style="text-align: center;"><strong>Replace With</strong></p>
                            </div>
                        </div>

                        <div>
                            <div class="col-lg-1">
                                <p style="text-align: center;"><strong>Accept</strong></p>
                            </div>
                        </div>
                        <div>
                            <div class="col-lg-4">
                                <p style="text-align: center;"><strong>Message from PHQ</strong></p>
                            </div>
                        </div>
                        <!-- START -->
                        <div id="showNicForUpdate">
                            <div>
                                <div class="col-lg-7">
                                    <div class="well new_bullet">
                                        <ul>
                                            <li>
                                                <div class="col-sm-4">
                                                    <label for="nicUploadPath" class="control-label"><b>NIC Front
                                                        Side:<span style="color:red;" id="nicspan">*</span></b></label>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="NIC" src="images/nic.png"
                                                         style="width: 54px; height: 52px;margin-top: -14px;"
                                                         onclick="viewReuploadNicPopup();">
                                                    <input type="hidden" id="hiddenReuploadNICFileName"/>
                                                    <input type="hidden" id="hiddenReuploadNIFileType"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input id="nicFileUpload" name="nicFileUpload"
                                                                           type="file"/>
                                                                    <s:hidden name="nicAttachPath" id="nicUploadPath"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload NIC" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadNICFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif" id="ajax_loader_nic"
                                                                     style="display:none;"/>
                                                                <img src="images/tick.png" id="upload_complete_nic"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </li>
                                            <li>
                                                <div class="col-sm-4">
                                                    <label for="nicUploadPath" class="control-label"><b>NIC Back
                                                        Side:<span style="color:red;"
                                                                   id="nicspanback">*</span></b></label>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="NIC" src="images/nic.png"
                                                         style="width: 54px; height: 52px;margin-top: -14px;"
                                                         onclick="viewReuploadNicPopup();">
                                                    <input type="hidden" id="hiddenReuploadNICFileNameBack"/>
                                                    <input type="hidden" id="hiddenReuploadNIFileTypeBack"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input id="nicFileUploadBack"
                                                                           name="nicFileUploadBack" type="file"/>
                                                                    <s:hidden name="nicAttachPathBack"
                                                                              id="nicUploadPathBack"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload NIC" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadNICBackFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif"
                                                                     id="ajax_loader_nic_back" style="display:none;"/>
                                                                <img src="images/tick.png" id="upload_complete_nic_back"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-1">
                                    <div class="well new_bullet">
                                        <s:checkbox name="checkNic" id="checkNic" label=""/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-4">
                                    <div class="well new_bullet" id="deptRequestUpdateNICMsg">
                                        <ul>
                                            <li id="nicMessage"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->


                        <!-- START -->
                        <div id="showNicForUpdate">
                            <div>
                                <div class="col-lg-7">
                                    <div class="well new_bullet">
                                        <ul>
                                            <li>
                                                <div class="col-sm-4">
                                                    <label for="nicUploadPath" class="control-label"><b>NIC Front
                                                        Side:<span style="color:red;" id="nicspan">*</span></b></label>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="NIC" src="images/nic.png"
                                                         style="width: 54px; height: 52px;margin-top: -14px;"
                                                         onclick="viewReuploadNicPopup();">
                                                    <input type="hidden" id="hiddenReuploadNICFileName"/>
                                                    <input type="hidden" id="hiddenReuploadNIFileType"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input id="nicFileUpload" name="nicFileUpload"
                                                                           type="file"/>
                                                                    <s:hidden name="nicAttachPath" id="nicUploadPath"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload NIC" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadNICFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif" id="ajax_loader_nic"
                                                                     style="display:none;"/>
                                                                <img src="images/tick.png" id="upload_complete_nic"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </li>
                                            <li>
                                                <div class="col-sm-4">
                                                    <label for="nicUploadPath" class="control-label"><b>NIC Back
                                                        Side:<span style="color:red;"
                                                                   id="nicspanback">*</span></b></label>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="NIC" src="images/nic.png"
                                                         style="width: 54px; height: 52px;margin-top: -14px;"
                                                         onclick="viewReuploadNicPopup();">
                                                    <input type="hidden" id="hiddenReuploadNICFileNameBack"/>
                                                    <input type="hidden" id="hiddenReuploadNIFileTypeBack"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input id="nicFileUploadBack"
                                                                           name="nicFileUploadBack" type="file"/>
                                                                    <s:hidden name="nicAttachPathBack"
                                                                              id="nicUploadPathBack"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload NIC" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadNICBackFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif"
                                                                     id="ajax_loader_nic_back" style="display:none;"/>
                                                                <img src="images/tick.png" id="upload_complete_nic_back"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-1">
                                    <div class="well new_bullet">
                                        <s:checkbox name="checkNic" id="checkNic" label=""/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-4">
                                    <div class="well new_bullet" id="deptRequestUpdateNICMsg">
                                        <ul>
                                            <li id="nicMessage"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->

                        <!-- START -->
                        <div id="showPassportForUpdate">
                            <div>
                                <div class="col-lg-7">
                                    <div class="well new_bullet">
                                        <ul>
                                            <li>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="passportUploadPath" class="control-label"><b>Personal
                                                            detail page:<span style="color:red;"
                                                                              id="pptspan">*</span></b></label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="Passport" src="images/passport.png"
                                                         style="width: 57px; height: 61px;margin-top: -20px;"
                                                         onclick="viewReuploadPassportPopup();">
                                                    <input type="hidden" id="hiddenReuploadPassportFileName"/>
                                                    <input type="hidden" id="hiddenReuploadPassportFileType"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input type="file" id="passportFileUpload"
                                                                           name="passportFileUpload"/>
                                                                    <s:hidden name="passportAttachPath"
                                                                              id="passportUploadPath"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload Passport" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadPassportFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif"
                                                                     id="ajax_loader_passport" style="display:none;"/>
                                                                <img src="images/tick.png" id="upload_complete_passport"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </li>
                                            <div style="clear: both;"></div>
                                            <li>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="passportUploadPath" class="control-label"><b>Countries
                                                            allowed page:<span style="color:red;"
                                                                               id="pptspanBack">*</span></b></label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <img alt="NIC" src="images/passport.png"
                                                         style="width: 57px; height: 61px;margin-top: -20px;"
                                                         onclick="viewReuploadPassportPopup();">
                                                    <input type="hidden" id="hiddenReuploadPassportFileNameBack"/>
                                                    <input type="hidden" id="hiddenReuploadPassportFileTypeBack"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <table class="table-responsive">
                                                        <tr>
                                                            <td style="width: 50%;">
                                                                <div class="upload">
                                                                    <input type="file" id="passportFileUploadBack"
                                                                           name="passportFileUploadBack"/>
                                                                    <s:hidden name="passportAttachPathBack"
                                                                              id="passportUploadPathBack"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                            </td>
                                                            <td style="width: 40%;">
                                                                <img alt="Upload Passport" src="images/upload.png"
                                                                     style="width: 50px; height: 45px;margin-top: -10px;"
                                                                     onclick="uploadPassportBackFile();">
                                                            </td>
                                                            <td style="width: 10%;">
                                                                <img src="images/ajax-loader.gif"
                                                                     id="ajax_loader_passport_back"
                                                                     style="display:none;"/>
                                                                <img src="images/tick.png"
                                                                     id="upload_complete_passport_back"
                                                                     style="display:none;width: 30px; height: 30px;margin-top: -8px;"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-1">
                                    <div class="well new_bullet">
                                        <s:checkbox name="checkPassport" id="checkPassport" label=""/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-4">
                                    <div class="well new_bullet" id="deptRequestUpdatePPTMsg">
                                        <ul>
                                            <li id="passportMessage"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->

                        <!-- START -->
                        <div id="showNameForUpdate">
                            <div>
                                <div class="col-lg-7">
                                    <div class="well new_bullet" style="min-height: 100px;">
                                        <ul>
                                            <li>
                                                <div class="col-sm-3">
                                                    <label for="name" class="control-label"><b>Name:<span
                                                            style="color:red;" id="namespan">*</span></b></label>
                                                </div>
                                                <div class="col-sm-9">
                                                    <s:textarea cssClass="form-control" name="name"
                                                                id="updatedNameByApplicant" cols="35" rows="3"/>

                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-1">
                                    <div class="well new_bullet">
                                        <s:checkbox name="checkName" id="checkName" label=""/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-4">
                                    <div class="well new_bullet" style="min-height: 100px;"
                                         id="deptRequestUpdateNameMsg">
                                        <ul>
                                            <li id="nameMessage"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->

                        <!-- START -->
                        <div id="showDOBForUpdate">
                            <div>
                                <div class="col-lg-7">
                                    <div class="well new_bullet">
                                        <ul>
                                            <li>
                                                <div class="col-sm-3">
                                                    <label for="dateOfBirth" class="control-label"><b>Date of
                                                        Birth:<span style="color:red;" id="dobspan">*</span></b></label>
                                                </div>
                                                <div class="col-sm-9">
                                                    <c:set var="customDateOfBirth">
                                                        <fmt:formatDate value="${dateOfBirth}" pattern="dd/MM/yyyy"/>
                                                    </c:set>
                                                    <input type="text" readonly="readonly" name="dateOfBirth"
                                                           id="dateOfBirth_id" class="form-control"
                                                           value="${customDateOfBirth}">
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-1">
                                    <div class="well new_bullet">
                                        <s:checkbox name="checkDateOfBirth" id="checkDateOfBirth" label=""/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-lg-4">
                                    <div class="well new_bullet" id="deptRequestUpdateDOBMsg">
                                        <ul>
                                            <li id="dateOfBirthMessage"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END -->
                        <div style="clear:both;"></div>
                        <!-- START -->
                        <hr style="width:90%;border: 2px solid #ccc;" size="2px;"/>
                        <br/>
                        <div style="clear:both;"></div>

                        <div>
                            <div class="col-lg-7">

                                <div class="col-sm-3">
                                    <label for="comment" class="control-label"><b>Comment:</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <s:textarea name="comment" cssClass="form-control" id="commentForRequestUpdate"
                                                cols="70" rows="5" readonly="true"/>
                                </div>
                            </div>
                        </div>

                        <!-- END -->
                        <div style="clear: both;"></div>
                        <!-- END -->
                    </div>
                    <div class="modal-footer">
                        <div>
                            <div class="col-lg-4">

                            </div>
                        </div>

                        <div>
                            <div class="col-lg-12" style="float: right;">
                                <div style="float: right;">
                                    <input type="hidden" id="updateRequestForUpdate_nicApplicant">
                                    <input type="hidden" id="updateRequestForUpdate_passportApplicant">
                                    <input type="hidden" id="updateRequestForUpdate_nameApplicant">
                                    <input type="hidden" id="updateRequestForUpdate_dobApplicant">

                                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                                            id="updateRequestForUpdateForm" onclick="saveRequestForUpdate();">Submit
                                    </button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>

                                    <form action="saveRequestForUpdate.action" method="post"
                                          id="saveRequestForUpdateForm">
                                        <input type="hidden" id="updateRequestForUpdate_applicationID"
                                               name="reqClarificationApplicationNo">
                                        <input type="hidden" id="updateRequestForUpdate_referenceNo"
                                               name="reqClarificationReferenceNo">
                                        <input type="hidden" id="updateRequestForUpdate_nic"
                                               name="updateRequestForUpdate_nic">
                                        <input type="hidden" id="updateRequestForUpdate_passport"
                                               name="updateRequestForUpdate_passport">
                                        <input type="hidden" id="updateRequestForUpdate_name"
                                               name="updateRequestForUpdate_name">
                                        <input type="hidden" id="updateRequestForUpdate_dob"
                                               name="updateRequestForUpdate_dob">
                                        <input type="hidden" id="updateRequestForUpdate_newNic"
                                               name="updateRequestForUpdate_nicAttachPath">
                                        <input type="hidden" id="updateRequestForUpdate_newNic_back"
                                               name="updateRequestForUpdate_nicAttachPathBack">
                                        <input type="hidden" id="updateRequestForUpdate_newPassport"
                                               name="updateRequestForUpdate_passportAttachPath">
                                        <input type="hidden" id="updateRequestForUpdate_newPassport_back"
                                               name="updateRequestForUpdate_passportAttachPathBack">
                                        <input type="hidden" id="updateRequestForUpdate_newName"
                                               name="updateRequestForUpdate_changeName">
                                        <input type="hidden" id="updateRequestForUpdate_newDob"
                                               name="updateRequestForUpdateChangeDob">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--  #####################################################	Request for update MODEl POPUP   ######################################################## -->
        <!--  #####################################################	PPT VIEW POPUP   ######################################################## -->
        <div class="modal fade" id="pptViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Passport Verification</span>
                    </div>
                    <div class="modal-body">
                        <div style="text-align: center;" id="showHidePassportNo">
                            <span style="font-size:18px;font-weight:bold;">Passport No. <span
                                    id="passportNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned Passport Copy from Applicant</span>
                            </div>
                            <div id="passportLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHidePptLink">
                                    Please click
                                    <a id="passportFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download Personal detail page file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="passportImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="passportImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>

                                </div>
                            </div>

                            <div id="passportLinkDivBack">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHidePptLinkBack">
                                    Please click
                                    <a id="passportFileNameBack" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download Countries allowed page file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="passportImge_link_back">
                                        <img class="image-link" src="images/preloader_large.gif" id="passportImgeBack"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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
        <div class="modal fade" id="nicViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">NIC Verification</span>
                    </div>
                    <div class="modal-body">
                        <div style="text-align: center;" id="showHideNICNo">
                            <span style="font-size:18px;font-weight:bold;">NIC No. <span
                                    id="nicNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned NIC Copy from Applicant</span>
                            </div>
                            <div id="nicLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideNicLink">
                                    Please click
                                    <a id="nicFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download Nic Front side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="nicImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="nicImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>
                                </div>
                            </div>
                            <br/>
                            <div id="nicLinkDivBack">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideNicLinkBack">
                                    Please click
                                    <a id="nicFileNameBack" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download Nic Back side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="nicImge_link_back">
                                        <img class="image-link" src="images/preloader_large.gif" id="nicImgeBack"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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


        <!--  #####################################################	New NIC VIEW POPUP   ######################################################## -->
        <div class="modal fade" id="newNicViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">New NIC Verification</span>
                    </div>
                    <div class="modal-body">
                        <div style="text-align: center;" id="showHideNewNICNo">
                            <span style="font-size:18px;font-weight:bold;">New NIC No. <span
                                    id="newNicNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned New NIC Copy from Applicant</span>
                            </div>
                            <div id="newNicLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideNewNicLink">
                                    Please click
                                    <a id="newNicFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download New Nic Front side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="newNicImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="newNicImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>
                                </div>
                            </div>
                            <br/>
                            <div id="newNicLinkDivBack">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideNewNicLinkBack">
                                    Please click
                                    <a id="newNicFileNameBack" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download New Nic Back side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="newNicImge_link_back">
                                        <img class="image-link" src="images/preloader_large.gif" id="newNicImgeBack"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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
        <div class="modal fade" id="bcViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Birth Certificate Verification</span>
                    </div>
                    <div class="modal-body">

                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned Birth certificate copy from Applicant</span>
                            </div>
                            <div id="bcLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideBcLink">
                                    Please click
                                    <a id="bcFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download front side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="bcImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="bcImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>

                                </div>
                            </div>

                            <div id="bcLinkDivBack">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideBcLinkBack">
                                    Please click
                                    <a id="bcFileNameBack" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download back side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="bcImge_link_back">
                                        <img class="image-link" src="images/preloader_large.gif" id="bcImgeBack"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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
        <div class="modal fade" id="slbfeViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Letter of reference Verification</span>
                    </div>
                    <div class="modal-body">

                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned letter of reference from applicant</span>
                            </div>
                            <div id="bcLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideSLBFELink">
                                    Please click
                                    <a id="slbfeFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="slbfeImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="slbfeImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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


        <!--  #####################################################	Affidavit VIEW POPUP   ######################################################## -->
        <div class="modal fade" id="affidavitViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Affidavit Verification</span>
                    </div>
                    <div class="modal-body">

                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned affidavit from applicant</span>
                            </div>
                            <div id="affidavitLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;" id="showHideAffidavitLink">
                                    Please click
                                    <a id="affidavitFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                       id="affidavitImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="affidavitImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
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

        <!--  #####################################################	 Amendments MODEl POPUP   ######################################################## -->
        <div class="modal fade" id="stayInSLModalPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">

            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Amendments</h4>
                    </div>
                    <div class="modal-body">

                        <div style="float:right;padding: 10px;">
                            <input type="button" class="btn btn-primary" id="modifyApplicationButton"
                                   value="Modify Application" onclick="return startModifyApplication()"/>
                        </div>
                        <div style="clear:both;"></div>

                        <form action="editApplicationFromVerification.action" method="post"
                              id="applicationModificationForm" style="display:none;">


                            <input type="hidden" id="applicationModificationApplicationId" name="applicationId"/>
                            <s:hidden name="fromDate" id="applicationModificationFromDate_id"/>
                            <s:hidden name="toDate" id="applicationModificationToDate_id"/>
                            <s:hidden name="searchReviewStatus" id="applicationModificationSearchReviewStatusId"/>
                            <s:hidden name="searchReferenceNo" id="applicationModificationSearchReferenceNoId"/>
                            <s:hidden name="nic" id="applicationModificationNicNoId"/>
                            <s:hidden name="passport" id="applicationModificationPassportNoId"/>
                            <s:hidden name="name" id="applicationModificationNameId"/>


                            <input type="hidden" id="highCommisionReferenceId" name="highCommisionReferenceId"/>
                            <input type="hidden" id="certificatePostAddressCountry"
                                   name="certificatePostAddressCountry"/>


                            <table class="table table-striped">
                                <tr>
                                    <td style="width: 30%;">
                                        <label for="applicantNameAsNic" class="control-label"><b>Applicant's Name in
                                            full as in the NIC:</b></label>
                                    </td>
                                    <td>:</td>
                                    <td><input type="text" name="applicantNameAsNic" id="applicantNameAsNic"
                                               class="form-control" autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="applicantNameAsPassport" class="control-label"><b>Applicant's Name
                                        in full as in the Passport:</b><span class="mandatory_field">*</span></label>
                                    </td>
                                    <td>:</td>
                                    <td><input type="text" name="applicantNameAsPassport" id="applicantNameAsPassport"
                                               class="form-control" autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="presentAddressLocal" class="control-label"><b>Present Address in Sri
                                        Lanka:</b></label></td>
                                    <td>:</td>
                                    <td>
                                        <textarea rows="2" name="presentAddressLocal" id="presentAddressLocal"
                                                  class="form-control"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="presentAddressOverseas" class="control-label"><b>Present Address
                                        (Overseas):</b></label></td>
                                    <td>:</td>
                                    <td>
                                        <textarea rows="2" name="presentAddressOverseas" id="presentAddressOverseas"
                                                  class="form-control"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="highCommisionReference" class="control-label"><b>Reference high
                                        commission/embassy/consulate:</b><span class="mandatory_field">*</span></label>
                                    </td>
                                    <td>:</td>
                                    <td>
                                        <textarea rows="2" name="highCommisionReference" id="highCommisionReference"
                                                  class="form-control"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="highCommisionReferenceAddress" class="control-label"><b>Address of
                                        the High Commission/Embassy/Consulate:</b><span class="mandatory_field">*</span></label>
                                    </td>
                                    <td>:</td>
                                    <td>
                                        <textarea rows="2" name="highCommisionReferenceAddress"
                                                  id="highCommisionReferenceAddress" class="form-control"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <label class="control-label" style="font-size: 14px;font-weight: bold;">
                                            The address the police clearance certificate should be posted to:
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressLineOne" class="control-label"><b>Address Line
                                        1:</b><span class="mandatory_field">*</span></label></td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressLineOne"
                                               id="certificatePostAddressLineOne" class="form-control"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressLineTwo" class="control-label"><b>Address Line
                                        2:</b></label></td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressLineTwo"
                                               id="certificatePostAddressLineTwo" class="form-control"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressCity" class="control-label"><b>City:</b><span
                                            class="mandatory_field">*</span></label></td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressCity"
                                               id="certificatePostAddressCity" class="form-control" autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressState" class="control-label"><b>State/Provice/Region:</b></label>
                                    </td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressState"
                                               id="certificatePostAddressState" class="form-control"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressPostal" class="control-label"><b>Postal/ZIP
                                        Code:</b></label></td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressPostal"
                                               id="certificatePostAddressPostal" class="form-control"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td><label for="certificatePostAddressCountryName"
                                               class="control-label"><b>Country:</b><span
                                            class="mandatory_field">*</span></label></td>
                                    <td>:</td>
                                    <td><input type="text" name="certificatePostAddressCountryName"
                                               id="certificatePostAddressCountryName" class="form-control"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td colspan="2" style="text-align: right;" align="right">
                                        <input type="button" class="btn btn-primary"
                                               id="saveApplicationModificationButton"
                                               onclick="return saveModifiedApplication()" value="Update Application"/>
                                        <input type="button" class="btn btn-primary" id="modifyApplicationCancelButton"
                                               onclick="return cancelModifyApplication()"
                                               value="Cancel Update Application"/>
                                    </td>
                                </tr>
                            </table>
                        </form>


                        <hr size="2px;"/>

                        <form action="modifyAddressListOfApplication.action" id="stayInSriLankaModificationForm">
                            <input type="hidden" id="stayInSlApplicationId" name="applicationId"/>

                            <div style="float:right;padding: 10px;">
                                <input type="button" class="btn btn-primary" id="stayInSLModifyButton"
                                       value="Modify Clearance Address list" onclick="return startModifyAddress()"/>
                                <input type="button" class="btn btn-primary" style="display:none;"
                                       id="stayInSLModifyCancelButton" value="Cancel Modification"
                                       onclick="return cancelModifyAddress()"/>
                            </div>
                            <div style="clear:both;"></div>

                            <table class="table table-bordered" id="newAddressAddingTable" style="display:none;">
                                <tr>
                                    <th>Police Area</th>
                                    <th>Address</th>
                                    <th>From</th>
                                    <th>To</th>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" id="certificationAddressId" value="0"/>
                                        <select id="certificationPoliceAreaSelect" class="form-control">
                                            <c:forEach var="policearea" items="${policeAreaList}">
                                                <option value="${policearea.id}">${policearea.policeArea}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" id="certificationAddress" class="form-control"/>
                                        <input type="hidden" id="hiddenRowId" class="form-control" value="-1"/>
                                    </td>
                                    <td><input type="text" id="certificationPoliceAreaFromSelect" class="form-control"
                                               readonly="true"/></td>
                                    <td><input type="text" id="certificationPoliceAreaToSelect" class="form-control"
                                               readonly="true"/></td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right" style="align:right;">
                                        <input type="button" id="certificateAddressTableAddButton"
                                               class="btn btn-primary es-buttton" value="Add"
                                               onclick="addRowToCertificateAddressTable();"/>
                                    </td>
                                </tr>
                            </table>
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>Police Area</th>
                                    <th>Address</th>
                                    <th>From</th>
                                    <th>To</th>
                                    <th class="hiddenMasterEdit" style="display:none;">Edit</th>
                                    <th class="hiddenMasterEdit" style="display:none;">Remove</th>
                                </tr>
                                </thead>
                                <tbody id="certificateAddressTable">

                                </tbody>
                            </table>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <input type="button" style="display:none;" class="btn btn-primary" id="stayInSlSaveButton"
                               onclick="saveModifiedAddressList()" value="Save Modified Addresses"/>
                        <input type="button" class="btn btn-primary" id="stayInSlCloseButton" data-dismiss="modal"
                               value="Close">
                    </div>
                </div>
            </div>

        </div>
        <!--  #####################################################	Amendments MODEl POPUP ######################################################## -->

        <!--  #####################################################	 Request Clarification MODEl POPUP   ######################################################## -->
        <div class="modal fade" id="requestClarificationModalPopUp" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Request Clarification</h4>
                    </div>

                    <div class="modal-body">
                        Please select one or more of the following.
                        <div style="clear: both;"></div>
                        <div class="col-lg-6">
                            <div class="well new_bullet">
                                <ul>
                                    <li>
                                        <s:checkbox name="resubmitNICCheck" id="resubmitNICCheck" label="  "
                                                    onclick="showHideNICTextBox();"/> Re submit NIC copy <img
                                            src="images/view_more.png" id="viewMoreNic" alt="View More"
                                            title="View More" class="basic_image_button" width="20px" height="20px"
                                            style="float: right;" onclick="showHideNICTextBoxBtn();"/>
                                    </li>
                                    <li>
                                        <s:checkbox name="resubmitPassportCheck" id="resubmitPassportCheck" label="  "
                                                    onclick="showHidePassportTextBox();"/> Re submit passport copy <img
                                            src="images/view_more.png" id="viewMorePassport" alt="View More"
                                            title="View More" class="basic_image_button" width="20px" height="20px"
                                            style="float: right;" onclick="showHidePassportTextBoxBtn();"/>
                                    </li>
                                    <li>
                                        <s:checkbox name="resubmitNameCheck" id="resubmitNameCheck" label="  "
                                                    onclick="showHideNameTextBox();"/> Verify Name <img
                                            src="images/view_more.png" id="viewMoreName" alt="View More"
                                            title="View More" class="basic_image_button" width="20px" height="20px"
                                            style="float: right;" onclick="showHideNameTextBoxBtn();"/>
                                    </li>
                                    <li>
                                        <s:checkbox name="resubmitDOBCheck" id="resubmitDOBCheck" label="  "
                                                    onclick="showHideDOBTextBox();"/> Verify Date of Birth <img
                                            src="images/view_more.png" id="viewMoreDOB" alt="View More"
                                            title="View More" class="basic_image_button" width="20px" height="20px"
                                            style="float: right;" onclick="showHideDOBTextBoxBtn();"/>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="well new_bullet">
                                <ul id="requestClarificationTextAreas">
                                    <li id="li_resubmitNIC"><s:textarea label="Re submit NIC copy" name="resubmitNIC"
                                                                        id="resubmitNIC" cols="35" rows="5"
                                                                        value="Due to ambiguity in the information you provided we kindly request you to re-send the following data:<< Re submit NIC copy >>"/></li>
                                    <li id="li_resubmitPassport"><s:textarea label="Re submit passport copy"
                                                                             name="resubmitPassport"
                                                                             id="resubmitPassport" cols="35" rows="5"
                                                                             value="Due to ambiguity in the information you provided we kindly request you to re-send the following data:<< Re submit passport copy >>"/></li>
                                    <li id="li_resubmitName"><s:textarea label="Verify Name" name="resubmitName"
                                                                         id="resubmitName" cols="35" rows="5"
                                                                         value="Due to ambiguity in the information you provided we kindly request you to re-send the following data:<< Verify Name >>"/></li>
                                    <li id="li_resubmitDOB"><s:textarea label="Verify Date of Birth" name="resubmitDOB"
                                                                        id="resubmitDOB" cols="35" rows="5"
                                                                        value="Due to ambiguity in the information you provided we kindly request you to re-send the following data:<< Verify Date of Birth >>"/></li>
                                </ul>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="resubmitApplicationId" id="resubmitApplicationId"/>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"
                                onclick="saveResubmitApplication();">Submit
                        </button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>

                        <form action="saveRequestClarification.action" method="post" id="saveRequestClarificationForm"
                              onsubmit="return validateRequestClarification();">
                            <s:hidden name="applicationId" id="applicationId_clarification"/>
                            <s:hidden name="reviewStatus" id="reviewStatus_clarification"/>
                            <s:hidden name="referenceNo" id="referenceNo_clarification"/>
                            <s:hidden name="resubmitNICCheck" id="resubmitNICCheck_clarification"/>
                            <s:hidden name="resubmitNewNICCheck" id="resubmitNewNICCheck_clarification"/>
                            <s:hidden name="resubmitPassportCheck" id="resubmitPassportCheck_clarification"/>
                            <s:hidden name="resubmitNameCheck" id="resubmitNameCheck_clarification"/>
                            <s:hidden name="resubmitDOBCheck" id="resubmitDOBCheck_clarification"/>
                            <s:hidden name="resubmitNIC" id="resubmitNIC_clarification"/>
                            <s:hidden name="resubmitPassport" id="resubmitPassport_clarification"/>
                            <s:hidden name="resubmitName" id="resubmitName_clarification"/>
                            <s:hidden name="resubmitDOB" id="resubmitDOB_clarification"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--  #####################################################	 Request Clarification MODEl POPUP ######################################################## -->

    </div>
    <script type="text/javascript" src="js/jquery.zoom.js"></script>
    <script type="text/javascript" src="js/jquery.numeric.js"></script>
    <script type="text/javascript" src="js/jquery.confirm.js"></script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            initializeDateTimePickers();

            $("#certificateAddressTable").on('click', '.remRow', function () {
                $(this).parent().parent().remove();
            });


        });

        function startModifyAddress() {
            $('#newAddressAddingTable').show();
            $('.hiddenMasterEdit').each(function (index) {
                $(this).show();
            });
            $('#stayInSLModifyButton').hide();
            $('#stayInSLModifyCancelButton').show();
            $('#stayInSlSaveButton').show();
            return false;
        }

        function startModifyApplication() {
            clearModifyApplicationFields();
            var applicationId = $('#stayInSlApplicationId').val();
            if (!(applicationId <= 0)) {
                blockUI();
                $.get('loadEditApplicationVerification.action', {applicationId: applicationId}, function (data) {
                    unBlockUI();

                    $('#applicationModificationApplicationId').val(applicationId);
                    $('#applicationModificationFromDate_id').val($('#fromDate_id').val());
                    $('#applicationModificationToDate_id').val($('#toDate_id').val());
                    $('#applicationModificationSearchReviewStatusId').val($('#searchReviewStatusId').val());
                    $('#applicationModificationSearchReferenceNoId').val($('#searchReferenceNoId').val());
                    $('#applicationModificationNicNoId').val($('#nicNoId').val());
                    $('#applicationModificationPassportNoId').val($('#passportNoId').val());
                    $('#applicationModificationNameId').val($('#nameId').val());


                    $('#applicantNameAsNic').val(data.applicantNameAsNic);
                    $('#applicantNameAsPassport').val(data.applicantNameAsPassport);

                    $('#presentAddressLocal').val(data.presentAddressLocal);
                    $('#presentAddressOverseas').val(data.presentAddressOverseas);

                    $('#highCommisionReferenceId').val(data.highCommisionReferenceId);
                    $('#highCommisionReference').val(data.highCommisionReference);
                    $('#highCommisionReferenceAddress').val(data.highCommisionReferenceAddress);

                    $('#certificatePostAddressLineOne').val(data.certificatePostAddressLineOne);
                    $('#certificatePostAddressLineTwo').val(data.certificatePostAddressLineTwo);
                    $('#certificatePostAddressCity').val(data.certificatePostAddressCity);
                    $('#certificatePostAddressState').val(data.certificatePostAddressState);
                    $('#certificatePostAddressPostal').val(data.certificatePostAddressPostal);
                    $('#certificatePostAddressCountry').val(data.certificatePostAddressCountry);
                    $('#certificatePostAddressCountryName').val(data.certificatePostAddressCountryName);

                    $('#modifyApplicationButton').hide();

                    $('#applicationModificationForm').show();
                });
            } else {
                alert('Invalid application id');
            }
        }

        function cancelModifyApplication() {
            clearModifyApplicationFields();
            $('#modifyApplicationButton').show();
            $('#applicationModificationForm').hide();
        }

        function clearModifyApplicationFields() {
            $('#applicationModificationApplicationId').val(0);
            $('#applicationModificationFromDate_id').val('');
            $('#applicationModificationToDate_id').val('');
            $('#applicationModificationSearchReviewStatusId').val('');
            $('#applicationModificationSearchReferenceNoId').val('');
            $('#applicationModificationNicNoId').val('');
            $('#applicationModificationPassportNoId').val('');
            $('#applicationModificationNameId').val('');

            $('#applicantNameAsNic').val('');
            $('#applicantNameAsPassport').val('');

            $('#presentAddressLocal').val('');
            $('#presentAddressOverseas').val('');

            $('#highCommisionReferenceId').val('');
            $('#highCommisionReference').val('');
            $('#highCommisionReferenceAddress').val('');

            $('#certificatePostAddressLineOne').val('');
            $('#certificatePostAddressLineTwo').val('');
            $('#certificatePostAddressCity').val('');
            $('#certificatePostAddressState').val('');
            $('#certificatePostAddressPostal').val('');
            $('#certificatePostAddressCountry').val('');
            $('#certificatePostAddressCountryName').val('');
        }

        function saveModifiedApplication() {
            var applicantNameAsNic = $.trim($('#applicantNameAsNic').val());
            var applicantNameAsPassport = $.trim($('#applicantNameAsPassport').val());

            var presentAddressLocal = $.trim($('#presentAddressLocal').val());
            var presentAddressOverseas = $.trim($('#presentAddressOverseas').val());

            var highCommisionReferenceId = $('#highCommisionReferenceId').val();
            var highCommisionReference = $.trim($('#highCommisionReference').val());
            var highCommisionReferenceAddress = $.trim($('#highCommisionReferenceAddress').val());

            var certificatePostAddressLineOne = $.trim($('#certificatePostAddressLineOne').val());
            var certificatePostAddressLineTwo = $.trim($('#certificatePostAddressLineTwo').val());
            var certificatePostAddressCity = $.trim($('#certificatePostAddressCity').val());
            var certificatePostAddressState = $.trim($('#certificatePostAddressState').val());
            var certificatePostAddressPostal = $.trim($('#certificatePostAddressPostal').val());
            var certificatePostAddressCountry = $('#certificatePostAddressCountry').val();
            var certificatePostAddressCountryName = $.trim($('#certificatePostAddressCountryName').val());

            var isValid = true;
            if (applicantNameAsPassport == '' || applicantNameAsPassport == null || applicantNameAsPassport == 'undefined') {
                alert('Applicant\'s Name in full as in the Passport cannot be empty!');
                isValid = false;
            } else if (highCommisionReference == '' || highCommisionReference == null || highCommisionReference == 'undefined') {
                alert('Reference high commission/embassy/consulate cannot be empty!');
                isValid = false;
            } else if (highCommisionReferenceAddress == '' || highCommisionReferenceAddress == null || highCommisionReferenceAddress == 'undefined') {
                alert('Address of the High Commission/Embassy/Consulate cannot be empty!');
                isValid = false;
            } else if (certificatePostAddressLineOne == '' || certificatePostAddressLineOne == null || certificatePostAddressLineOne == 'undefined') {
                alert('Address Line 1 cannot be empty!');
                isValid = false;
            } else if (certificatePostAddressCity == '' || certificatePostAddressCity == null || certificatePostAddressCity == 'undefined') {
                alert('City cannot be empty!');
                isValid = false;
            } else if (certificatePostAddressCountryName == '' || certificatePostAddressCountryName == null || certificatePostAddressCountryName == 'undefined') {
                alert('Country cannot be empty!');
                isValid = false;
            }

            if (isValid) {
                var con = confirm('Are you sure you want to update the application?');
                if (con) {
                    $('#applicationModificationForm').submit();
                }
            }

        }

        function cancelModifyAddress() {
            $('#newAddressAddingTable').hide();
            $('.hiddenMasterEdit').each(function (index) {
                $(this).hide();
            });
            $('#stayInSLModifyButton').show();
            $('#stayInSLModifyCancelButton').hide();
            $('#stayInSlSaveButton').hide();

            var applicationId = $('#stayInSlApplicationId').val();
            blockUI();
            $('#certificateAddressTable').html('');
            $('#stayInSlApplicationId').val(applicationId);
            $('#newAddressAddingTable').hide();
            $('.hiddenMasterEdit').each(function (index) {
                $(this).hide();
            });
            $.get('loadAddressList.action', {applicationId: applicationId}, function (data) {
                unBlockUI();
                var addressVOList = data.addressVOList;
                if (addressVOList.length > 0) {
                    var rowCount = 0;
                    $.each(addressVOList, function (index, item) {
                        appendAddressRow(rowCount, item.policeAreaId, item.policeArea, item.address, item.fromDateStr, item.toDateStr, item.addressId);
                        rowCount = rowCount + 1;
                    });
                    $('.hiddenMasterEdit').each(function (index) {
                        $(this).hide();
                    });
                } else {
                    $('#certificateAddressTable').html('<tr><td colspan="6" align="center">No Address is available for this Application!</td></tr>');
                }
            });
            return false;
        }

        function saveModifiedAddressList() {

            var applicationId = $('#stayInSlApplicationId').val();
            //first check if any address is avaliable
            var rowCount = $('#certificateAddressTable tr').length;
            if (rowCount <= 0) {
                alert('The address list cannot be empty!');
                return false;
            }

            //check and list edited addresses and new addresses
            var existingAddresses = new Array();
            var newAddresses = new Array();

            for (i = 0; i < rowCount; i++) {
                var addressId = parseFloat($("#certificateAddressTable").find('#cad' + i).find('.adressIdVal').val());
                var address = $("#certificateAddressTable").find('#cad' + i).find('.address').val();
                var policeArea = $("#certificateAddressTable").find('#cad' + i).find('.policeAreaVal').val();
                var from = $("#certificateAddressTable").find('#cad' + i).find('.from').val();
                var to = $("#certificateAddressTable").find('#cad' + i).find('.to').val();
                var addressObject = new addressRow(addressId, address, policeArea, from, to);
                if (isNaN(addressId)) {
                    addressId = 0;
                }

                if (addressId <= 0) {
                    newAddresses.push(addressObject);
                } else {
                    existingAddresses.push(addressObject);
                }
            }

            //check if there are any new addresses, if so there are changes to be saved
            if (newAddresses.length <= 0) {
                //this array is empty-there are no changes to be saved

                //check if there are any existing addresses
                if (existingAddresses.length > 0) {
                    //check if the existing addresses have modified
                    //get the exisitng address list from backend
                    blockUI();
                    $.get('loadAddressList.action', {applicationId: applicationId}, function (data) {
                        unBlockUI();
                        var addressVOList = data.addressVOList;

                        var hasChanged = false;
                        //check if the length of two arrays are different, if so, items have been removed

                        if (!(addressVOList.length == existingAddresses.length)) {
                            hasChanged = true;
                        }

                        if (!(hasChanged)) {
                            if (addressVOList.length > 0) {
                                $.each(addressVOList, function (index, item) {
                                    $.each(existingAddresses, function (indexInner, itemInner) {
                                        if (itemInner.addressId == item.addressId) {
                                            if (item.address != itemInner.address) {
                                                hasChanged = true;
                                            }
                                            if (item.policeAreaId != itemInner.policeArea) {
                                                hasChanged = true;
                                            }
                                            if (item.fromDateStr != itemInner.from) {
                                                hasChanged = true;
                                            }
                                            if (item.toDateStr != itemInner.to) {
                                                hasChanged = true;
                                            }
                                        }
                                    });
                                });
                            }
                        }

                        if (!(hasChanged)) {
                            alert('There are no changes to be saved!');
                            return false;
                        } else {
                            //alert('There are changes to be saved - existing addresses have removed/modified');
                            var conf = confirm('Are you sure you want to save changes to addresses?');
                            if (conf) {
                                blockUI();
                                $('#stayInSriLankaModificationForm').submit();
                            }
                        }
                        unBlockUI();
                    });
                } else {
                    //alert('There are changes to be saved no existing addresses');
                    var conf = confirm('Are you sure you want to save changes to addresses?');
                    if (conf) {
                        blockUI();
                        $('#stayInSriLankaModificationForm').submit();
                    }
                }
            } else {
                //alert('There are changes to be saved - new addressses');
                var conf = confirm('Are you sure you want to save changes to addresses?');
                if (conf) {
                    blockUI();
                    $('#stayInSriLankaModificationForm').submit();
                }
            }
        }

        function addressRow(addressId, address, policeArea, from, to) {
            this.addressId = addressId;
            this.address = address;
            this.policeArea = policeArea;
            this.from = from;
            this.to = to;
        }

        function addRowToCertificateAddressTable() {
            var address = $("#certificationAddress").val();
            var policeArea = $("#certificationPoliceAreaSelect").val();
            var policeAreaName = $("#certificationPoliceAreaSelect option:selected").text();
            var from = $("#certificationPoliceAreaFromSelect").val();
            var to = $("#certificationPoliceAreaToSelect").val();
            var addressId = $('#certificationAddressId').val();

            if (address == '') {
                alert('Please insert a address');
                return false;
            } else if (policeArea == '') {
                alert('Please insert a police area');
                return false;
            } else if (from == '') {
                alert('Please insert a from date');
                return false;
            } else if (to == '') {
                alert('Please insert a to date');
                return false;
            }

            var rowCount = parseInt($('#hiddenRowId').val());
            if (rowCount < 0) {
                rowCount = $('#certificateAddressTable tr').length;
            }

            try {
                var fromDateObj = $.datepicker.parseDate("dd/mm/yy", from);
                var toDateObj = $.datepicker.parseDate("dd/mm/yy", to);

                var myArray = [];
                if ($(".appendedRowAddress").length) {
                    blockUI();
                    $(".appendedRowAddress").each(function (index) {
                        var rowId = $(this).attr('id');
                        var rowCountNumber = $.trim((rowId.replace("cad", "")));

                        var fromDateId = 'addedFromDate_' + rowCountNumber;
                        var toDateId = 'addedToDate_' + rowCountNumber;
                        var policeAreaIdId = 'addedPoliceArea_' + rowCountNumber;
                        var selectedAddressId = 'addedAddress_' + rowCountNumber;

                        var fromDt = $('#' + fromDateId).val();
                        var toDt = $('#' + toDateId).val();
                        var policeAr = $.trim($('#' + policeAreaIdId).val());
                        var selectedAdrs = $.trim($('#' + selectedAddressId).val());

                        myArray.push(new DateValidationVO(selectedAdrs, policeAr, fromDt, toDt));

                    });

                    ///add newly added date
                    myArray.push(new DateValidationVO(address, policeAreaName, from, to));

                    var data = JSON.stringify(myArray);

                    $.post("validateDateRange.action", {dateValidateJsonString: data}, function (response) {
                        unBlockUI();
                        if (response.addressPeriodValidStatus != 1) {
                            alert(response.addressPeriodValidMessage);
                            return false;
                        } else {
                            appendAddressRow(rowCount, policeArea, policeAreaName, address, from, to, addressId);
                            clearAddressRowFields();
                            $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());
                        }
                    });
                } else {
                    appendAddressRow(rowCount, policeArea, policeAreaName, address, from, to, addressId);
                    clearAddressRowFields();
                    $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());
                }
            } catch (e) {
                console.log(e);
            }
        }

        function DateValidationVO(addressValue, policeArea, fromDate, toDate) {
            this.addressValue = addressValue;
            this.policeArea = policeArea;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        function clearAddressRowFields() {
            //clear fields
            $("#certificationAddress").val('');
            $("#certificationPoliceAreaSelect").val('');
            $("#certificationPoliceAreaFromSelect").val('');
            $("#certificationPoliceAreaToSelect").val('');
            $('#certificationAddressId').val(0);
            $('#hiddenRowId').val(-1);
        }

        function viewAddressList(applicationId) {
            blockUI();

            var rStat = $('#hiddenApplicationReviewStatus_' + applicationId).val();
            if (!(rStat == 'TS' || rStat == 'VF' || rStat == 'RJ' || rStat == 'TM')) {
                $('#stayInSLModifyButton').show();
            } else {
                $('#stayInSLModifyButton').hide();
            }

            $('#stayInSLModifyCancelButton').hide();
            $('#certificateAddressTable').html('');
            $('#stayInSlApplicationId').val(applicationId);
            $('#newAddressAddingTable').hide();
            $('#stayInSlSaveButton').hide();
            $('.hiddenMasterEdit').each(function (index) {
                $(this).hide();
            });
            $.get('loadAddressList.action', {applicationId: applicationId}, function (data) {
                unBlockUI();
                var addressVOList = data.addressVOList;
                if (addressVOList.length > 0) {
                    var rowCount = 0;
                    $.each(addressVOList, function (index, item) {
                        appendAddressRow(rowCount, item.policeAreaId, item.policeArea, item.address, item.fromDateStr, item.toDateStr, item.addressId);
                        rowCount = rowCount + 1;
                    });
                    $('.hiddenMasterEdit').each(function (index) {
                        $(this).hide();
                    });
                } else {
                    $('#certificateAddressTable').html('<tr><td colspan="6" align="center">No Address is available for this Application!</td></tr>');
                }
                $('#stayInSL').click();
            });
        }

        function initializeRowRemoval() {
            $('.remRow').unbind('click');
            $("#certificateAddressTable").on('click', '.remRow', function () {
                $(this).parent().parent().remove();
            });
        }

        function appendAddressRow(rowCount, policeArea, policeAreaName, address, from, to, addressId) {

            var appendText = '';
            appendText = appendText + '<tr id="cad' + rowCount + '" class="appendedRowAddress">';
            appendText = appendText + '<td><input name="certificateAddressList[' + rowCount + '].addressId" type="hidden" readonly="true" class="form-control adressIdVal" value="' + addressId + '" />';
            appendText = appendText + '<input name="certificateAddressList[' + rowCount + '].policeAreaId" type="hidden" readonly="true" class="form-control policeAreaVal" value="' + policeArea + '" />';
            appendText = appendText + '<input name="certificateAddressList[' + rowCount + '].policeArea" type="text" readonly="true" class="form-control" value="' + policeAreaName + '" /></td>';
            appendText = appendText + '<td><input name="certificateAddressList[' + rowCount + '].address" type="text" readonly="true" class="form-control address" value="' + address + '" /></td>';
            appendText = appendText + '<td><input name="certificateAddressList[' + rowCount + '].fromDateStr" id="addedFromDate_' + rowCount + '" type="text" readonly="true" class="form-control from" value="' + from + '" /></td>';
            appendText = appendText + '<td><input name="certificateAddressList[' + rowCount + '].toDateStr" id="addedToDate_' + rowCount + '" type="text" readonly="true" class="form-control to" value="' + to + '" /></td>';

            appendText = appendText + '<td class="hiddenMasterEdit"><img style="cursor:pointer;" class="remRow" src="images/edit.png" onclick="editCertificateAddressRow(\'cad' + rowCount + '\');"></td>';
            appendText = appendText + '<td class="hiddenMasterEdit"><img style="cursor:pointer;" class="remRow" src="images/cancel.png"></td>';
            appendText = appendText + '</tr>';

            $("#certificateAddressTable").append(appendText);
            initializeRowRemoval();
        }

        function checkIfDataRangeValid(startD, endD, startdate, enddate) {
            return (startD >= startdate && startD <= enddate) ||
                (startdate >= startD && startdate <= endD);
        }

        function editCertificateAddressRow(rowId) {
            $("#certificationAddressId").val($("#certificateAddressTable").find('#' + rowId).find('.adressIdVal').val());
            $("#certificationAddress").val($("#certificateAddressTable").find('#' + rowId).find('.address').val());
            $("#certificationPoliceAreaSelect").val($("#certificateAddressTable").find('#' + rowId).find('.policeAreaVal').val());
            $("#certificationPoliceAreaFromSelect").val($("#certificateAddressTable").find('#' + rowId).find('.from').val());
            $("#certificationPoliceAreaToSelect").val($("#certificateAddressTable").find('#' + rowId).find('.to').val());

            var rowCount = parseInt($.trim((rowId.replace("cad", ""))));
            $("#hiddenRowId").val(rowCount);

            $("#certificateAddressTable").find('#' + rowId).remove();
        }

        function initializeDateTimePickers() {

            $('#certificationPoliceAreaFromSelect').datepicker({
                changeYear: true,
                yearRange: "-120:+0",
                changeMonth: true,
                maxDate: "+0d",
                dateFormat: "dd/mm/yy",
                onClose: function (selectedDate) {
                    $("#certificationPoliceAreaToSelect").datepicker("option", "minDate", selectedDate);
                }
            });
            $('#certificationPoliceAreaToSelect').datepicker({
                changeYear: true,
                yearRange: "-120:+0",
                changeMonth: true,
                maxDate: "+0d",
                dateFormat: "dd/mm/yy",
                onClose: function (selectedDate) {
                    if (!(selectedDate == "")) {
                        $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", selectedDate);
                    } else {
                        $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());
                    }
                }
            });

            $('#clearReviewApplication').click(function () {
                $.datepicker._clearDate('#fromDate_id');
                $.datepicker._clearDate('#toDate_id');
                $('#searchReviewStatusId').val('');
                $('#searchReferenceNoId').val('');
                $('#nicNoId').val('');
                $('#newNicNoId').val('');
                $('#passportNoId').val('');
                $('#nameId').val('');
                $('#applicationListAppendDiv').html('');
                $('#gridButtons').hide();
                $('#legends').hide();
            });


            $("#fromDate_id").datepicker({
                defaultDate: "+0d",
                dateFormat: "dd/mm/yy",
                onClose: function (selectedDate) {
                    $("#toDate_id").datepicker("option", "minDate", selectedDate);
                }
            });

            $("#toDate_id").datepicker({
                defaultDate: "+0d",
                dateFormat: "dd/mm/yy",
                onClose: function (selectedDate) {
                    $("#fromDate_id").datepicker("option", "maxDate", selectedDate);
                }
            });

            /* $("#").datepicker({
             dateFormat: 'dd/mm/yy'
             }); */
            /*$('#fromDate_id').datepicker({
             dateFormat:"dd/mm/yy"
             });
             $('#toDate_id').datepicker({
             dateFormat:"dd/mm/yy"
             });*/
            $('#dateOfBirth_id').datepicker({
                changeYear: true,
                yearRange: "-120:+0",
                changeMonth: true,
                dateFormat: "dd/mm/yy",
            });

            //To solve the problem - Jquery Ui Datepicker month/year dropdown is not working in popup in latest firefox
            // Since confModal is essentially a nested modal it's enforceFocus method
            // must be no-op'd or the following error results
            // "Uncaught RangeError: Maximum call stack size exceeded"
            // But then when the nested modal is hidden we reset modal.enforceFocus
            var enforceModalFocusFn = $.fn.modal.Constructor.prototype.enforceFocus;

            $.fn.modal.Constructor.prototype.enforceFocus = function () {
            };

            $confModal.on('hidden', function () {
                $.fn.modal.Constructor.prototype.enforceFocus = enforceModalFocusFn;
            });

            $confModal.modal({backdrop: false});
        }

        /*function showNIC(imagePath) {
         $('#imageHeader').text("NIC");
         $("#image").attr("src", imagePath);
         $('#imageLink').click();
         }

         function showPassport(imagePath) {
         $('#imageHeader').text("Passport");
         $("#image").attr("src", imagePath);
         $('#imageLink').click();
         }*/
        function showRequestForUpdate(referenceNo) {
            $.get('fetchRequestClarificationFromRefNo', {
                referenceNo: referenceNo,
            }, function (data) {
                var requestClarificationVO = data.requestClarificationVO;
                //VALIDATION COMMENTED ACCORDING TO THE REQUIREMENT. USER CAN SUBMIT NIC, PPT, NAME AND DOB
                //EVEN PHQ NOT REQUESTED. IF PHQ WILLING TO ACCEPT THE CHANGE THE CAN ACCEPT IF THEY NOT REQUESTED
                if (requestClarificationVO.nic == 0) {
                    $('#showNicForUpdate').show();
                    $('#deptRequestUpdateNICMsg').removeClass("wall new_bullet").addClass("wall_override");
                    $('#nicspan').css("color", "#E0E1FC");
                    $('#nicspanBack').css("color", "#E0E1FC");
                    $('#nicMessage').text("");
                } else if (requestClarificationVO.nic == 1) {
                    $('#showNicForUpdate').show();
                    $('#deptRequestUpdateNICMsg').removeClass("wall_override").addClass("wall new_bullet");
                    $('#nicspan').css("color", "RED");
                    $('#nicspanBack').css("color", "RED");
                    $('#nicMessage').text(requestClarificationVO.nicMessage);
                    $('#hiddenReuploadNICFileName').val(requestClarificationVO.nicPath);
                    $('#hiddenReuploadNIFileType').val(requestClarificationVO.reuploadedNicFileType);
                    $('#hiddenReuploadNICFileNameBack').val(requestClarificationVO.nicPathBack);
                    $('#hiddenReuploadNIFileTypeBack').val(requestClarificationVO.reuploadedNicFileTypeBack);
                }

                if (requestClarificationVO.passport == 0) {
                    $('#showPassportForUpdate').show();
                    $('#deptRequestUpdatePPTMsg').removeClass("wall new_bullet").addClass("wall_override");
                    $('#pptspan').css("color", "#E0E1FC");
                    $('#pptspanBack').css("color", "#E0E1FC");
                    $('#passportMessage').text("");
                } else if (requestClarificationVO.passport == 1) {
                    $('#showPassportForUpdate').show();
                    $('#deptRequestUpdatePPTMsg').removeClass("wall_override").addClass("wall new_bullet");
                    $('#pptspan').css("color", "RED");
                    $('#pptspanBack').css("color", "RED");
                    $('#passportMessage').text(requestClarificationVO.passportMessage);
                    $('#hiddenReuploadPassportFileName').val(requestClarificationVO.passportPath);
                    $('#hiddenReuploadPassportFileType').val(requestClarificationVO.reuploadedPptFileType);
                    $('#hiddenReuploadPassportFileNameBack').val(requestClarificationVO.passportPathBack);
                    $('#hiddenReuploadPassportFileTypeBack').val(requestClarificationVO.reuploadedPptFileTypeBack);
                }

                if (requestClarificationVO.verifyName == 0) {
                    $('#showNameForUpdate').show();
                    $('#deptRequestUpdateNameMsg').removeClass("wall new_bullet").addClass("wall_override_name");//color:#E0E1FC;
                    $('#namespan').css("color", "#E0E1FC");
                    $('#nameMessage').text("");
                } else if (requestClarificationVO.verifyName == 1) {
                    $('#showNameForUpdate').show();
                    $('#deptRequestUpdateNameMsg').removeClass("wall_override_name").addClass("wall new_bullet");
                    $('#namespan').css("color", "RED");
                    $('#nameMessage').text(requestClarificationVO.nameMessage);
                    $('#updatedNameByApplicant').text(requestClarificationVO.name);
                }

                if (requestClarificationVO.verifyDateOfBirth == 0) {
                    $('#showDOBForUpdate').show();
                    $('#deptRequestUpdateDOBMsg').removeClass("wall new_bullet").addClass("wall_override");
                    $('#dobspan').css("color", "#E0E1FC");
                    $('#dateOfBirthMessage').text("");
                } else if (requestClarificationVO.verifyDateOfBirth == 1) {
                    $('#showDOBForUpdate').show();
                    $('#deptRequestUpdateDOBMsg').removeClass("wall_override").addClass("wall new_bullet");
                    $('#dobspan').css("color", "RED");
                    $('#dateOfBirthMessage').text(requestClarificationVO.dateOfBirthMessage);
                    if (requestClarificationVO.dateOfBirth != null) {
                        var fullDate = requestClarificationVO.dateOfBirth.substr(0, 10);
                        var year = fullDate.substr(0, 4);
                        var month = fullDate.substr(5, 2);
                        var date = fullDate.substr(8, 9);

                        $('#dateOfBirth_id').val(date + "/" + month + "/" + year);
                    }
                    //$('#dateOfBirth_id').datepicker("setDate", new Date(year,month,date) );
                }
                $('#commentForRequestUpdate').text(requestClarificationVO.comment);
                $('#referenceNoRequestUpdate').text(requestClarificationVO.referenceNo);

                $('#updateRequestForUpdate_applicationID').val(requestClarificationVO.applicationId);
                $('#updateRequestForUpdate_referenceNo').val(requestClarificationVO.referenceNo);
                $('#updateRequestForUpdate_nicApplicant').val(requestClarificationVO.nic);
                $('#updateRequestForUpdate_passportApplicant').val(requestClarificationVO.passport);
                $('#updateRequestForUpdate_nameApplicant').val(requestClarificationVO.verifyName);
                $('#updateRequestForUpdate_dobApplicant').val(requestClarificationVO.verifyDateOfBirth);
                /*
                 reuploadedNicFileType reuploadedPptFileType
                 requestClarificationVO.requestClarificationId
                 requestClarificationVO.nic
                 requestClarificationVO.nicMessage
                 requestClarificationVO.passport
                 requestClarificationVO.passportMessage
                 requestClarificationVO.verifyName
                 requestClarificationVO.nameMessage
                 requestClarificationVO.verifyDateOfBirth
                 requestClarificationVO.dateOfBirthMessage
                 requestClarificationVO.referenceNo
                 requestClarificationVO.nicPath
                 requestClarificationVO.nicAcceptStatus
                 requestClarificationVO.passportPath
                 requestClarificationVO.passportAcceptStatus
                 requestClarificationVO.name
                 requestClarificationVO.nameAcceptStatus
                 requestClarificationVO.dateOfBirth
                 requestClarificationVO.dateOfBirthAcceptStatus
                 requestClarificationVO.comment
                 requestClarificationVO.applicationId*/
            });
            $('#requestforUpdate').click();
        }
        function showRequestClarification(applicationId) {
            $('#li_resubmitNIC').hide();
            $('#li_resubmitPassport').hide();
            $('#li_resubmitName').hide();
            $('#li_resubmitDOB').hide();
            $('#resubmitApplicationId').val(applicationId);
            $('#requestClarification').click();
        }

        function lockRecord(applicationId) {
            blockUI();
            $.get('lockRecoed', {
                applicationId: applicationId,
            }, function (data) {
                var lockStatus = data.lockRecordStatus;
                var lockedUserName = data.lockRecordUserName;
                //alert(lockStatus);
                if (lockStatus == 'SUCCESS') {
                    $('#' + applicationId).attr("src", "images/lock.png");
                    $('#' + applicationId).unbind('click');
                    $('#' + applicationId).removeAttr('onclick');
                    $('#reviewStatusId' + applicationId).attr('disabled', false);
                    $('#nic' + applicationId).removeClass('disabled_image');
                    $('#passport' + applicationId).removeClass('disabled_image');
                    $('#save_image_' + applicationId).removeClass('disabled_image');
                    $('#address' + applicationId).removeClass('disabled_image');
                    $('#rc_image_' + applicationId).removeClass('disabled_image');
                    $('#' + applicationId).click(function () {
                        unlockRecord(applicationId);
                    });
                    $('#save_image_' + applicationId).removeClass('disabled_image');
                    $('#save_image_' + applicationId).click(function () {
                        saveRow(applicationId);
                    });
//                    $('#searchApplicationVerification').submit();

                    $.datepicker._clearDate('#fromDate_id');
                    $.datepicker._clearDate('#toDate_id');
                    $('#searchReviewStatusId').val('');

                    var referenceNumber = $.trim($('#referenceNoSave' + applicationId).val());
                    $('#searchReferenceNoId').val(referenceNumber);

                    $('#nicNoId').val('');
                    $('#newNicNoId').val('');
                    $('#passportNoId').val('');
                    $('#nameId').val('');
                    $('#applicationListAppendDiv').html('');
                    $('#gridButtons').hide();
                    $('#legends').hide();

                    showHideRequestClarificationBtn(applicationId);
                    $('#searchApplicationVerification').submit();

                } else if (lockStatus == 'ONE_RECORD_IS_ALREADY_LOCKED') {
                    $('#' + applicationId).attr("src", "images/unlock.png");
                    alert("You can only lock one record at a time. Please unlock the locked record and retry.");
                } else if (lockStatus == 'RECORD_IS_LOCKED_BY_ANOTHER_USER') {
                    $('#' + applicationId).attr("src", "images/unlock.png");
                    alert("The record is already locked by " + lockedUserName);
                }
                unBlockUI();
            });
        }

        function unlockRecord(applicationId) {
            blockUI();
            $.get('unlockRecoed', {
                applicationId: applicationId,
            }, function (data) {
                var lockStatus = data.lockRecordStatus;
                //alert(lockStatus);rc_image_
                if (lockStatus == 'SUCCESS') {
                    $('#' + applicationId).attr("src", "images/unlock.png");
                    $('#' + applicationId).unbind('click');
                    $('#' + applicationId).removeAttr('onclick');
                    $('#reviewStatusId' + applicationId).attr('disabled', true);
                    $('#nic' + applicationId).addClass('disabled_image');
                    $('#passport' + applicationId).addClass('disabled_image');
                    $('#save_image_' + applicationId).addClass('disabled_image');
                    $('#address' + applicationId).addClass('disabled_image');
                    $('#rc_image_' + applicationId).addClass('disabled_image');
                    $('#' + applicationId).click(function () {
                        lockRecord(applicationId);
                    });
                    $('#searchApplicationVerification').submit();
                    //$('#rc_image_'+applicationId).css('display', 'none');

                }
                unBlockUI();
            });
        }

        function saveRow(applicationId) {

            var reviewStatus = $.trim($('#reviewStatusId' + applicationId).val());
            if (reviewStatus == 'NW') {
                alert("You cant save the record as 'New'");
            }

            if (reviewStatus == 'RV') {
                alert("You cant save the record as 'Revised'");
            }

            if (reviewStatus != 'NW' && reviewStatus != 'RV') {
                $('#save_image_' + applicationId).confirm({
                    title: "Update Application",
                    text: "Are you sure you want to update this record?",
                    confirm: function (button) {
                        button.fadeOut(2000).fadeIn(2000);
                        var referenceNo = $.trim($('#referenceNoSave' + applicationId).val());
                        $('#applicationId_review').val(applicationId);
                        $('#reviewStatus_review').val(reviewStatus);
                        $('#referenceNo_review').val(referenceNo);
                        $('#reviewUpdateForm').submit();
                    },
                    cancel: function (button) {
                        button.fadeOut(2000).fadeIn(2000);
                    },
                    confirmButton: "Yes",
                    cancelButton: "No"
                });
                /*var ans=confirm('Are you sure you want to update this record?');

                 if(ans){
                 var referenceNo=$.trim($('#referenceNoSave' + applicationId).val());
                 $('#applicationId_review').val(applicationId);
                 $('#reviewStatus_review').val(reviewStatus);
                 $('#referenceNo_review').val(referenceNo);
                 $('#reviewUpdateForm').submit();
                 } */
            }

        }

        function editRow(applicationId) {
            applicationId = parseInt(applicationId);
            if (applicationId > 0) {
                var con = confirm('Are you sure you want to modify the application?');
                if (con) {
                    $('#modifyApplicationCompleteFormAppId').val(applicationId);
                    $('#applicationCompleteModificationForm').submit();
                }
            }
        }

        function showHideNICTextBox() {
            var nicCheck = $('#resubmitNICCheck').is(":checked");
            if (nicCheck) {
                $('#li_resubmitPassport').hide();
                $('#li_resubmitName').hide();
                $('#li_resubmitDOB').hide();
                $('#resubmitNIC').attr('disabled', false);
                $('#li_resubmitNIC').show();
            } else {
                $('#resubmitNIC').attr('disabled', true);
                $('#li_resubmitNIC').hide();
                $('#li_resubmitPassport').hide();
                $('#li_resubmitName').hide();
                $('#li_resubmitDOB').hide();
            }
        }

        function showHidePassportTextBox() {
            var passportCheck = $('#resubmitPassportCheck').is(":checked");

            if (passportCheck) {
                $('#li_resubmitNIC').hide();
                $('#li_resubmitName').hide();
                $('#li_resubmitDOB').hide();
                $('#li_resubmitPassport').show();
                $('#resubmitPassport').attr('disabled', false);
            } else {
                $('#resubmitPassport').attr('disabled', true);
                $('#li_resubmitPassport').hide();
                $('#li_resubmitNIC').hide();
                $('#li_resubmitName').hide();
                $('#li_resubmitDOB').hide();
            }
        }

        function showHideNameTextBox() {
            var nameCheck = $('#resubmitNameCheck').is(":checked");

            if (nameCheck) {
                $('#li_resubmitNIC').hide();
                $('#li_resubmitPassport').hide();
                $('#li_resubmitDOB').hide();
                $('#li_resubmitName').show();
                $('#resubmitName').attr('disabled', false);
            } else {
                $('#resubmitName').attr('disabled', true);
                $('#li_resubmitName').hide();
                $('#li_resubmitNIC').hide();
                $('#li_resubmitPassport').hide();
                $('#li_resubmitDOB').hide();
            }
        }

        function showHideDOBTextBox() {
            var dobCheck = $('#resubmitDOBCheck').is(":checked");

            if (dobCheck) {
                $('#li_resubmitNIC').hide();
                $('#li_resubmitPassport').hide();
                $('#li_resubmitName').hide();
                $('#li_resubmitDOB').show();
                $('#resubmitDOB').attr('disabled', false);
            } else {
                $('#resubmitDOB').attr('disabled', true);
                $('#li_resubmitDOB').hide();
                $('#li_resubmitNIC').hide();
                $('#li_resubmitPassport').hide();
                $('#li_resubmitName').hide();
            }
        }

        function showHideNICTextBoxBtn() {
            var nicCheck = $('#resubmitNICCheck').is(":checked");

            if (nicCheck) {
                $('#resubmitNIC').attr('disabled', false);
            } else {
                $('#resubmitNIC').attr('disabled', true);
            }
            $('#li_resubmitPassport').hide();
            $('#li_resubmitName').hide();
            $('#li_resubmitDOB').hide();
            $('#li_resubmitNIC').show();

        }

        function showHidePassportTextBoxBtn() {
            var passportCheck = $('#resubmitPassportCheck').is(":checked");

            if (passportCheck) {
                $('#resubmitPassport').attr('disabled', false);
            } else {
                $('#resubmitPassport').attr('disabled', true);
            }
            $('#li_resubmitNIC').hide();
            $('#li_resubmitName').hide();
            $('#li_resubmitDOB').hide();
            $('#li_resubmitPassport').show();

        }

        function showHideNameTextBoxBtn() {
            var nameCheck = $('#resubmitNameCheck').is(":checked");

            if (nameCheck) {
                $('#resubmitName').attr('disabled', false);
            } else {
                $('#resubmitName').attr('disabled', true);
            }
            $('#li_resubmitNIC').hide();
            $('#li_resubmitPassport').hide();
            $('#li_resubmitDOB').hide();
            $('#li_resubmitName').show();

        }

        function showHideDOBTextBoxBtn() {
            var dobCheck = $('#resubmitDOBCheck').is(":checked");

            if (dobCheck) {
                $('#resubmitDOB').attr('disabled', false);
            } else {
                $('#resubmitDOB').attr('disabled', true);
            }
            $('#li_resubmitNIC').hide();
            $('#li_resubmitPassport').hide();
            $('#li_resubmitName').hide();
            $('#li_resubmitDOB').show();

        }

        function showHideRequestClarificationBtn(applicationId) {
            var reviewStatus = $.trim($('#reviewStatusId' + applicationId).val());
            if (reviewStatus == 'RC') {
                var requestApplicationCountForAppId = 0;
                $.get('getRequestClarificationCountForAppId', {
                    applicationId: applicationId,
                }, function (data) {
                    requestApplicationCountForAppId = data.requestApplicationCountForAppId;

                    if (requestApplicationCountForAppId == 0) {
                        //alert("applicationIdd -> "+ applicationId + " count -> " + requestApplicationCountForAppId + " reviewStatus -> " + reviewStatus);
                        $('#save_image_' + applicationId).addClass('disabled_image');
                        $('#save_image_' + applicationId).unbind('click');
                        $('#save_image_' + applicationId).removeAttr('onclick');
                        $('#rc_image_' + applicationId).css('display', 'block');
                        $('#rc_image_' + applicationId).removeClass('disabled_image');
                    } else {
                        $('#save_image_' + applicationId).addClass('disabled_image');
                        $('#save_image_' + applicationId).unbind('click');
                        $('#save_image_' + applicationId).removeAttr('onclick');
                        $('#rc_image_' + applicationId).addClass('disabled_image');
                    }
                });

            } else {
                if (reviewStatus != 'NW' && reviewStatus != 'RV') {
                    $('#save_image_' + applicationId).removeClass('disabled_image');
                    $('#save_image_' + applicationId).click(function () {
                        saveRow(applicationId);
                    });
                    $('#rc_image_' + applicationId).css('display', 'none');
                    $('#rc_image_' + applicationId).removeClass('disabled_image');
                } else {
                    $('#save_image_' + applicationId).removeClass('disabled_image');
                    $('#rc_image_' + applicationId).css('display', 'none');
                    $('#rc_image_' + applicationId).removeClass('disabled_image');
                }
            }
        }

        function saveResubmitApplication() {
            var applicationId = $('#resubmitApplicationId').val();
            var reviewStatus = $.trim($('#reviewStatusId' + applicationId).val());
            var referenceNo = $.trim($('#referenceNoSave' + applicationId).val());
            var resubmitNICCheck = $('#resubmitNICCheck').is(":checked");
            var resubmitNewNICCheck = $('#resubmitNewNICCheck').is(":checked");
            var resubmitPassportCheck = $('#resubmitPassportCheck').is(":checked");
            var resubmitNameCheck = $('#resubmitNameCheck').is(":checked");
            var resubmitDOBCheck = $('#resubmitDOBCheck').is(":checked");
            var resubmitNIC = $('#resubmitNIC').val();
            var resubmitPassport = $('#resubmitPassport').val();
            var resubmitName = $('#resubmitName').val();
            var resubmitDOB = $('#resubmitDOB').val();
            //alert(referenceNo);
            if (reviewStatus == 'RC') {
                var ans = confirm('Are you sure you want to update this record?');
                if (ans) {
                    $('#applicationId_clarification').val(applicationId);
                    $('#reviewStatus_clarification').val(reviewStatus);
                    $('#referenceNo_clarification').val(referenceNo);

                    $('#resubmitNICCheck_clarification').val(resubmitNICCheck);
                    $('#resubmitNewNICCheck_clarification').val(resubmitNewNICCheck);
                    $('#resubmitPassportCheck_clarification').val(resubmitPassportCheck);
                    $('#resubmitNameCheck_clarification').val(resubmitNameCheck);
                    $('#resubmitDOBCheck_clarification').val(resubmitDOBCheck);
                    $('#resubmitNIC_clarification').val(resubmitNIC);
                    $('#resubmitPassport_clarification').val(resubmitPassport);
                    $('#resubmitName_clarification').val(resubmitName);
                    $('#resubmitDOB_clarification').val(resubmitDOB);

                    $('#saveRequestClarificationForm').submit();
                } else {
                    return false;
                }
            }

        }

        function validateRequestClarification() {
            var nicCheck = $('#resubmitNICCheck').is(":checked");
            var newNicCheck = $('#resubmitNewNICCheck').is(":checked");
            var passportCheck = $('#resubmitPassportCheck').is(":checked");
            var nameCheck = $('#resubmitNameCheck').is(":checked");
            var dobCheck = $('#resubmitDOBCheck').is(":checked");

            if (!nicCheck && !passportCheck && !nameCheck && !dobCheck) {

                alert("Please select field/s for request clarification.");
                return false;
            }

            if ($('#resubmitNIC').val().trim().length == 0 && nicCheck) {
                alert("Please type a message to the applicant. Missing NIC message.");
                return false;
            }

            if ($('#resubmitNewNIC').val().trim().length == 0 && newNicCheck) {
                alert("Please type a message to the applicant. Missing New NIC message.");
                return false;
            }

            if ($('#resubmitPassport').val().trim().length == 0 && passportCheck) {
                alert("Please type a message to the applicant. Missing Passport message.");
                return false;
            }

            if ($('#resubmitName').val().trim().length == 0 && nameCheck) {
                alert("Please type a message to the applicant. Missing Name message.");
                return false;
            }

            if ($('#resubmitDOB').val().trim().length == 0 && dobCheck) {
                alert("Please type a message to the applicant. Missing Date of Birth message.");
                return false;
            }

        }

        function viewNicPopup(applicationId) {
            $('#nicImge').attr('src', 'images/preloader_large.gif');
            $('#nicImgeBack').attr('src', 'images/preloader_large.gif');
            //blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenNicFileName_' + applicationId).val();
            var fileType = $('#hiddenNicFileType_' + applicationId).val();

            var fileNameBack = $('#hiddenNicFileNameBack_' + applicationId).val();
            var fileTypeBack = $('#hiddenNicFileTypeBack_' + applicationId).val();

            var pptNo = $('#hiddenNicNo_' + applicationId).val();

            if (fileType == 'IMAGE') {
                $('#nicImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#nicImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#nicImge').attr('src', 'images/no_preview_available.png');
                $('#nicImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#nicImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#nicImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#nicImgeBack').attr('src', 'images/no_preview_available.png');
                $('#nicImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideNicLink').show();
            } else {
                $('#showHideNicLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHideNicLinkBack').show();
            } else {
                $('#showHideNicLinkBack').hide();
            }

            //$('#nicFileName').html(fileName);
            $('#nicFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#nicFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#nicNumberAppend').html(pptNo);

            initImageNic();

            $('#nicViewModellLink').click();
            //unBlockUI();
        }

        function initImageNic() {
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

            $('#nicImge_link_back').magnificPopup({
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

        function viewAffidavitPopup(applicationId) {
            $('#affidavitImge').attr('src', 'images/preloader_large.gif');
            //blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenAffidavitFileName_' + applicationId).val();
            var fileType = $('#hiddenAffidavitFileType_' + applicationId).val();

            if (fileType == 'IMAGE') {
                $('#affidavitImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#affidavitImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#affidavitImge').attr('src', 'images/no_preview_available.png');
                $('#affidavitImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideAffidavitLink').show();
            } else {
                $('#showHideAffidavitLink').hide();
            }

            //$('#nicFileName').html(fileName);
            $('#affidavitFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);

            initImageAffidavit();

            $('#affidavitViewModellLink').click();
            //unBlockUI();
        }

        function initImageAffidavit() {
            $('#affidavitImge_link').magnificPopup({
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

        function viewNewNicPopup(applicationId) {
            $('#newNicImge').attr('src', 'images/preloader_large.gif');
            $('#newNicImgeBack').attr('src', 'images/preloader_large.gif');
            //blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenNewNicFileName_' + applicationId).val();
            var fileType = $('#hiddenNewNicFileType_' + applicationId).val();

            var fileNameBack = $('#hiddenNewNicFileNameBack_' + applicationId).val();
            var fileTypeBack = $('#hiddenNewNicFileTypeBack_' + applicationId).val();

            var pptNo = $('#hiddenNewNicNo_' + applicationId).val();

            if (fileType == 'IMAGE') {
                $('#newNicImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#newNicImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#newNicImge').attr('src', 'images/no_preview_available.png');
                $('#newNicImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#newNicImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#newNicImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#newNicImgeBack').attr('src', 'images/no_preview_available.png');
                $('#newNicImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideNewNicLink').show();
            } else {
                $('#showHideNewNicLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHideNewNicLinkBack').show();
            } else {
                $('#showHideNewNicLinkBack').hide();
            }

            //$('#nicFileName').html(fileName);
            $('#newNicFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#newNicFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#nweNicNumberAppend').html(pptNo);

            initImageNewNic();

            $('#newNicViewModellLink').click();
            //unBlockUI();
        }

        function initImageNewNic() {
            $('#newNicImge_link').magnificPopup({
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

            $('#newNicImge_link_back').magnificPopup({
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

        function viewPptPopup(applicationId) {
            $('#passportImge').attr('src', 'images/preloader_large.gif');
            $('#passportImgeBack').attr('src', 'images/preloader_large.gif');
            // blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenPptFileName_' + applicationId).val();
            var fileType = $('#hiddenPptFileType_' + applicationId).val();

            var fileNameBack = $('#hiddenPptFileNameBack_' + applicationId).val();
            var fileTypeBack = $('#hiddenPptFileTypeBack_' + applicationId).val();

            var pptNo = $('#hiddenPptNo_' + applicationId).val();

            if (fileType == 'IMAGE') {
                $('#passportImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#passportImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#passportImge').attr('src', 'images/no_preview_available.png');
                $('#passportImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#passportImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#passportImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#passportImgeBack').attr('src', 'images/no_preview_available.png');
                $('#passportImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHidePptLink').show();
            } else {
                $('#showHidePptLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHidePptLinkBack').show();
            } else {
                $('#showHidePptLinkBack').hide();
            }

            //$('#passportFileName').html(fileName);
            $('#passportFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#passportFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#passportNumberAppend').html(pptNo);

            initImagePassport();

            $('#pptViewModellLink').click();
            //unBlockUI();
        }


        function viewBcPopup(applicationId) {
            $('#bcImge').attr('src', 'images/preloader_large.gif');
            $('#bcImgeBack').attr('src', 'images/preloader_large.gif');
            // blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenBcFileName_' + applicationId).val();
            var fileType = $('#hiddenBcFileType_' + applicationId).val();

            var fileNameBack = $('#hiddenBcFileName_back_' + applicationId).val();
            var fileTypeBack = $('#hiddenBcFileType_back_' + applicationId).val();

            var pptNo = $('#hiddenBcNo_' + applicationId).val();

            if (fileType == 'IMAGE') {
                $('#bcImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#bcImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#bcImge').attr('src', 'images/no_preview_available.png');
                $('#bcImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#bcImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#bcImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#bcImgeBack').attr('src', 'images/no_preview_available.png');
                $('#bcImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideBcLink').show();
            } else {
                $('#showHideBcLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHideBcLinkBack').show();
            } else {
                $('#showHideBcLinkBack').hide();
            }

            //$('#passportFileName').html(fileName);
            $('#bcFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#bcFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#bcNumberAppend').html(pptNo);

            initImageBc();

            $('#bcViewModellLink').click();
            //unBlockUI();
        }

        function viewSLBFEPopup(applicationId) {
            // blockUI();
            //alert(applicationId);
            var fileName = $('#hiddenSLBFEFileName_' + applicationId).val();
            var fileType = $('#hiddenSLBFEFileType_' + applicationId).val();
            var pptNo = $('#hiddenSLBFENo_' + applicationId).val();
            if (fileType == 'IMAGE') {
                $('#slbfeImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#slbfeImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#slbfeImge').attr('src', 'images/no_preview_available.png');
                $('#slbfeImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideSLBFELink').show();
            } else {
                $('#showHideSLBFELink').hide();
            }
            //$('#passportFileName').html(fileName);
            $('#slbfeFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);

            $('#slbfeNumberAppend').html(pptNo);

            initImageSLBFE();

            $('#slbfeViewModellLink').click();
            //unBlockUI();
        }

        function initImagePassport() {
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

            $('#passportImge_link_back').magnificPopup({
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

        function initImageBc() {
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

            $('#bcImge_link_back').magnificPopup({
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

        function initImageSLBFE() {
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

        function initImageNic() {
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

            $('#nicImge_link_back').magnificPopup({
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

        function viewReuploadPassportPopup() {
            $('#passportImge').attr('src', 'images/preloader_large.gif');
            $('#passportImgeBack').attr('src', 'images/preloader_large.gif');

            var fileName = $('#hiddenReuploadPassportFileName').val();
            var fileType = $('#hiddenReuploadPassportFileType').val();
            var fileNameBack = $('#hiddenReuploadPassportFileNameBack').val();
            var fileTypeBack = $('#hiddenReuploadPassportFileTypeBack').val();

            var pptNo = "";

            if (fileType == 'IMAGE') {
                $('#passportImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#passportImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#passportImge').attr('src', 'images/no_preview_available.png');
                $('#passportImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#passportImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#passportImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#passportImgeBack').attr('src', 'images/no_preview_available.png');
                $('#passportImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHidePptLink').show();
            } else {
                $('#showHidePptLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHidePptLinkBack').show();
            } else {
                $('#showHidePptLinkBack').hide();
            }

            //$('#passportFileName').html(fileName);
            $('#passportFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#passportFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#passportNumberAppend').html(pptNo);
            $('#showHidePassportNo').hide();
            initImagePassport();

            $('#pptViewModellLink').click();
        }

        function viewReuploadNicPopup() {
            $('#nicImge').attr('src', 'images/preloader_large.gif');
            $('#nicImgeBack').attr('src', 'images/preloader_large.gif');

            var fileName = $('#hiddenReuploadNICFileName').val();
            var fileType = $('#hiddenReuploadNIFileType').val();

            var fileNameBack = $('#hiddenReuploadNICFileNameBack').val();
            var fileTypeBack = $('#hiddenReuploadNIFileTypeBack').val();

            var pptNo = "";

            if (fileType == 'IMAGE') {
                $('#nicImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                $('#nicImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            } else {
                $('#nicImge').attr('src', 'images/no_preview_available.png');
                $('#nicImge_link').attr('href', 'images/no_preview_available.png');
            }

            if (fileTypeBack == 'IMAGE') {
                $('#nicImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                $('#nicImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
            } else {
                $('#nicImgeBack').attr('src', 'images/no_preview_available.png');
                $('#nicImge_link_back').attr('href', 'images/no_preview_available.png');
            }

            if (fileName != "") {
                $('#showHideNicLink').show();
            } else {
                $('#showHideNicLink').hide();
            }

            if (fileNameBack != "") {
                $('#showHideNicLinkBack').show();
            } else {
                $('#showHideNicLinkBack').hide();
            }

            $('#nicFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
            $('#nicFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

            $('#nicNumberAppend').html(pptNo);
            $('#showHideNICNo').hide();
            initImageNic();

            $('#nicViewModellLink').click();
        }


        function saveRequestForUpdate() {
            //var updateRequestForUpdate_applicationID = $('#updateRequestForUpdate_applicationID').val();
            //var updateRequestForUpdate_referenceNo = $('#updateRequestForUpdate_referenceNo').val();

            var checkDateOfBirth = $('#checkDateOfBirth').is(":checked");
            var checkName = $('#checkName').is(":checked");
            var checkPassport = $('#checkPassport').is(":checked");
            var checkNic = $('#checkNic').is(":checked");
            var newNic = $('#nicUploadPath').val();
            var newNicBack = $('#nicUploadPathBack').val();
            var newPassport = $('#passportUploadPath').val();
            var newPassportBack = $('#passportUploadPathBack').val();
            var newName = $('#updatedNameByApplicant').val();
            var newDob = $('#dateOfBirth_id').val();

// 			$('#updateRequestForUpdate_nic').val(checkNic);
// 		  	$('#updateRequestForUpdate_passport').val(checkPassport);
// 		  	$('#updateRequestForUpdate_name').val(checkName);
// 		  	$('#updateRequestForUpdate_dob').val(checkDateOfBirth);
// 		  	$('#updateRequestForUpdate_newNic').val(newNic);
// 		  	$('#updateRequestForUpdate_newPassport').val(newPassport);
// 		  	$('#updateRequestForUpdate_newName').val(newName);
            //alert(newDob);
// 		  	$('#updateRequestForUpdate_newDob').val(newDob);

            var updateRequestForUpdate_nic = $('#updateRequestForUpdate_nicApplicant').val();
            var updateRequestForUpdate_passport = $('#updateRequestForUpdate_passportApplicant').val();
            var updateRequestForUpdate_name = $('#updateRequestForUpdate_nameApplicant').val();
            var updateRequestForUpdate_dob = $('#updateRequestForUpdate_dobApplicant').val();


            /*if(updateRequestForUpdate_nic == 1){
             if(!checkNic) {
             var ans=confirm('Are you rejecting resubmitted NIC copy ?');
             if(!ans) {
             return false;
             }
             }
             }

             if(updateRequestForUpdate_passport == 1){
             if(!checkPassport) {
             var ans=confirm('Are you rejecting resubmitted Passport copy ?');
             if(!ans) {
             return false;
             }
             }
             }

             if(updateRequestForUpdate_name == 1){
             if(!checkName) {
             var ans=confirm('Are you rejecting resubmitted Name ?');
             if(!ans) {
             return false;
             }
             }
             }

             if(updateRequestForUpdate_dob == 1){
             if(!checkDateOfBirth) {
             var ans=confirm('Are you rejecting resubmitted Date of Birth ?');
             if(!ans) {
             return false;
             }
             }
             }*/

            var ans = confirm('Are you sure you want to update this record?');
            if (ans) {

                $('#updateRequestForUpdate_nic').val(checkNic);
                $('#updateRequestForUpdate_passport').val(checkPassport);
                $('#updateRequestForUpdate_name').val(checkName);
                $('#updateRequestForUpdate_dob').val(checkDateOfBirth);
                $('#updateRequestForUpdate_newNic').val(newNic);
                $('#updateRequestForUpdate_newNic_back').val(newNicBack);
                $('#updateRequestForUpdate_newPassport').val(newPassport);
                $('#updateRequestForUpdate_newPassport_back').val(newPassportBack);
                $('#updateRequestForUpdate_newName').val(newName);
                $('#updateRequestForUpdate_newDob').val(newDob);

                $('#saveRequestForUpdateForm').submit();
            } else {
                return false;
            }
        }

        function viewApplicationByReferenceNo(referenceNo) {
            window.open("viewApplicationByReferenceNo.action?referenceNo=" + referenceNo);
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

        function uploadNICFile() {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            var xhr = new XMLHttpRequest();
            var nicInput = document.getElementById('nicFileUpload');

            var nicFile = nicInput.files[0];

            //validate upload
            var fileNicPath = document.getElementById('nicFileUpload').value;
            var nicExtension = fileNicPath.split(".").pop().trim();

            if (fileNicPath == '') {
                alert("please select a file before upload for nic");
                return false;
            } else if (nicExtension != "pdf" && nicExtension != "PDF" && nicExtension != "docx" && nicExtension != "DOCX" && nicExtension != "doc" && nicExtension != "DOC" && nicExtension != "png" && nicExtension != "PNG" && nicExtension != "jpg" && nicExtension != "JPG") {
                alert("invalid nic file format");
                return false;
            } else if (nicFile.size > maximumFileLimit) {
                alert("nic file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                return false;
            }

            //show ajax upload gif
            $("#ajax_loader_nic").show();

            var formData = new FormData();
            formData.append('nicFile', nicFile);
            formData.append('nicFileExtension', nicExtension);
            xhr.open('POST', 'reuploadNICFiles.action', true);

            var formData = new FormData();
            formData.append('file', nicFile);
            formData.append('fileExtension', nicExtension);
            formData.append('fileType', "NICF");
            formData.append('uploadType', "REUPVRF");

            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);
                    var nicFileName = jsonData.fileName;

                    if (nicFileName != '') {
                        $("#nicUploadPath").val(nicFileName);
                    }

                    $("#ajax_loader_nic").hide();
                    $("#upload_complete_nic").show();
                    //clear the ppt upload field for prevent the multiple upload of same file
                    document.getElementById('nicFileUpload').value = "";
                }
            }

        }

        function uploadNICBackFile() {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            var xhr = new XMLHttpRequest();
            var nicInput = document.getElementById('nicFileUploadBack');

            var nicFile = nicInput.files[0];

            //validate upload
            var fileNicPath = document.getElementById('nicFileUploadBack').value;
            var nicExtension = fileNicPath.split(".").pop().trim();

            if (fileNicPath == '') {
                alert("please select a file before upload for nic");
                return false;
            } else if (nicExtension != "pdf" && nicExtension != "PDF" && nicExtension != "docx" && nicExtension != "DOCX" && nicExtension != "doc" && nicExtension != "DOC" && nicExtension != "png" && nicExtension != "PNG" && nicExtension != "jpg" && nicExtension != "JPG") {
                alert("invalid nic file format");
                return false;
            } else if (nicFile.size > maximumFileLimit) {
                alert("nic file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                return false;
            }

            //show ajax upload gif
            $("#ajax_loader_nic_back").show();

            var formData = new FormData();
            formData.append('file', nicFile);
            formData.append('fileExtension', nicExtension);
            formData.append('fileType', "NICF");
            formData.append('uploadType', "REUPVRF");

            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);
                    var nicFileName = jsonData.fileName;

                    if (nicFileName != '') {
                        $("#nicUploadPathBack").val(nicFileName);
                    }

                    $("#ajax_loader_nic_back").hide();
                    $("#upload_complete_nic_back").show();
                    //clear the ppt upload field for prevent the multiple upload of same file
                    document.getElementById('nicFileUploadBack').value = "";
                }
            }

        }

        //passport front file
        function uploadPassportFile() {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            var xhr = new XMLHttpRequest();
            var passportInput = document.getElementById('passportFileUpload');

            var passportFile = passportInput.files[0];

            //validate upload
            var filePassportPath = document.getElementById('passportFileUpload').value;
            var passportExtension = filePassportPath.split(".").pop().trim();

            if (filePassportPath == '') {
                alert("please select a file before upload for passport");
                return false;
            } else if (passportExtension != "pdf" && passportExtension != "PDF" && passportExtension != "docx" && passportExtension != "DOCX" && passportExtension != "doc" && passportExtension != "DOC" && passportExtension != "png" && passportExtension != "PNG" && passportExtension != "jpg" && passportExtension != "JPG") {
                alert("invalid passport file format");
                return false;
            } else if (passportFile.size > maximumFileLimit) {
                alert("passport file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                return false;
            }

            //show ajax upload gif
            $("#ajax_loader_passport").show();

            var formData = new FormData();
            formData.append('file', passportFile);
            formData.append('fileExtension', passportExtension);
            formData.append('fileType', "PASF");
            formData.append('uploadType', "REUPVRF");

            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);
                    var passFileName = jsonData.fileName;

                    if (passFileName != '') {
                        $("#passportUploadPath").val(passFileName);
                    }

                    $("#ajax_loader_passport").hide();
                    $("#upload_complete_passport").show();
                    //clear the ppt upload field for prevent the multiple upload of same file
                    document.getElementById('passportFileUpload').value = "";
                }
            }

        }


        function uploadPassportBackFile() {
            var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
            //check the browser
            if (!supportAjaxUploadWithProgress) {
                alert("Please update your browser to upload");
                return false;
            }

            var xhr = new XMLHttpRequest();
            var passportInput = document.getElementById('passportFileUploadBack');

            var passportFile = passportInput.files[0];

            //validate upload
            var filePassportPath = document.getElementById('passportFileUploadBack').value;
            var passportExtension = filePassportPath.split(".").pop().trim();

            if (filePassportPath == '') {
                alert("please select a file before upload for passport");
                return false;
            } else if (passportExtension != "pdf" && passportExtension != "PDF" && passportExtension != "docx" && passportExtension != "DOCX" && passportExtension != "doc" && passportExtension != "DOC" && passportExtension != "png" && passportExtension != "PNG" && passportExtension != "jpg" && passportExtension != "JPG") {
                alert("invalid passport file format");
                return false;
            } else if (passportFile.size > maximumFileLimit) {
                alert("passport file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                return false;
            }

            //show ajax upload gif
            $("#ajax_loader_passport_back").show();

            var formData = new FormData();
            formData.append('file', passportFile);
            formData.append('fileExtension', passportExtension);
            formData.append('fileType', "PASB");
            formData.append('uploadType', "REUPVRF");

            xhr.open('POST', 'uploadFile.action', true);
            xhr.send(formData);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var jsonData = JSON.parse(xhr.responseText);
                    var passFileName = jsonData.fileName;

                    if (passFileName != '') {
                        $("#passportUploadPathBack").val(passFileName);
                    }

                    $("#ajax_loader_passport_back").hide();
                    $("#upload_complete_passport_back").show();
                    //clear the ppt upload field for prevent the multiple upload of same file
                    document.getElementById('passportFileUploadBack').value = "";
                }
            }

        }
    </script>
    </body>
    </html>
</s:i18n>