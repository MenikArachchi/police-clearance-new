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

        <title>Review Application - External</title>

    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

        <!-- including header -->
            <jsp:include page="../common/header.jsp"/>

        <!-- Starting the page Title -->
        <div id="es-content">

            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Review Application - External</c:set>
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
                    <s:form cssClass="form-horizontal" id="myForm" theme="simple"
                            action="searchReviewApplication.action" onsubmit="return validateForm()">
                        <input type="hidden" name="searchCriteriaVO.maxId" id="maxId" value="0"/>
                        <input type="hidden" name="searchCriteriaVO.limit" id="limit" value="20"/>
                        <input type="hidden" name="searchCriteriaVO.currentPage" id="currentPage"/>

                        <div class="col-lg-3">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <strong><s:label cssClass="control-label bold-label">From:</s:label></strong>
                                </div>
                                <div class="col-sm-9">
                                    <c:set var="customStartDate">
                                        <fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <input type="text" readonly="readonly" name="fromDate" value="${customStartDate}"
                                           id="fromDate_id" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <div class="col-sm-1">
                                    <strong><s:label cssClass="control-label bold-label">To:</s:label></strong>
                                </div>
                                <div class="col-sm-9">
                                    <c:set var="customEndDate">
                                        <fmt:formatDate value="${toDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <input type="text" readonly="readonly" name="toDate" value="${customEndDate}"
                                           id="toDate_id" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="referenceNo">Reference No :</s:label></strong>
                                </div>
                                <div class="col-sm-8">
                                    <s:textfield name="searchCriteriaVO.referenceNo" cssClass="form-control"
                                                 id="referenceNo"/>
                                </div>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${userRole != userRoleDisplayVO.imiUserRole}">
                                <div style="clear: both;"></div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="nicNo">NIC :</s:label></strong>
                                        </div>
                                        <div class="col-sm-8">
                                            <s:textfield name="searchCriteriaVO.nicNo" cssClass="form-control"
                                                         id="nicNo"/>
                                        </div>
                                    </div>
                                </div>
                                <div style="clear: both;"></div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="newNicNo">New NIC :</s:label></strong>
                                        </div>
                                        <div class="col-sm-8">
                                            <s:textfield name="searchCriteriaVO.newNicNo" cssClass="form-control"
                                                         id="newNicNo"/>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>


                        <div style="clear: both;"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="pptNo">PPT :</s:label></strong>
                                </div>
                                <div class="col-sm-8">
                                    <s:textfield name="searchCriteriaVO.pptNo" cssClass="form-control" id="pptNo"/>
                                </div>
                                <div class="col-sm-1">
                                    <div style="text-align:right;">
                                        &nbsp;
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <strong><s:label cssClass="control-label bold-label"
                                                     for="name">Name :</s:label></strong>
                                </div>
                                <div class="col-sm-8">
                                    <s:textfield name="searchCriteriaVO.name" cssClass="form-control" id="name"/>
                                </div>
                                <div class="col-sm-1">
                                    <div style="text-align:right;">
                                        &nbsp;
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>
                        <div class="col-lg-8">
                            <div class="form-group">
                                <div class="col-sm-8">
                                    <div style="text-align:right;">
                                        <input type="submit" value="Search" class="btn btn-primary es-buttton"
                                               id="searchReviewApplication"/>
                                        <input type="button" value="Clear" class="btn btn-primary es-buttton"
                                               id="clearReviewApplication"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </s:form>
                    <div style="clear: both;"></div>
                    <div class="form-group">

                        <c:if test="${! empty applicationList}">
                            <div class="col-lg-12" style="max-width: 100%;">
                                <div class="table-responsive" style="max-width: 100%;">
                                    <table class="table table-bordered" style="max-width: 100%;">
                                        <thead>
                                        <tr>
                                            <th class="text-center"><strong>Select</strong></th>
                                            <th class="text-center"><strong>Application Date</strong></th>
                                            <th class="text-center"><strong>Reference</strong></th>
                                            <c:choose>
                                                <c:when test="${userRole != userRoleDisplayVO.imiUserRole}">
                                                    <th class="text-center"><strong>Current NIC No</strong></th>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${userRole != userRoleDisplayVO.nicUserRole}">
                                                    <th class="text-center"><strong>Passport No</strong></th>
                                                </c:when>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${userRole==userRoleDisplayVO.imiUserRole}">
                                                    <th class="text-center" style="width: 10%;"><strong>Passport
                                                        Name</strong></th>
                                                </c:when>
                                                <c:when test="${userRole==userRoleDisplayVO.nicUserRole}">
                                                    <th class="text-center" style="width: 10%;"><strong>Current NIC
                                                        Name</strong></th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th class="text-center" style="width: 10%;"><strong>Current NIC
                                                        Name</strong></th>
                                                    <th class="text-center" style="width: 10%;"><strong>Passport
                                                        Name</strong></th>
                                                </c:otherwise>
                                            </c:choose>

                                            <th class="text-center"><strong>DOB</strong></th>
                                            <th class="text-center"><strong>Present Address</strong></th>
                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole}">
                                                <th class="text-center"><strong>Telephone No.</strong></th>
                                            </c:if>
                                                <%-- 														<c:if test="${userRole==userRoleDisplayVO.phqUserRole}">		 --%>
                                                <%-- 															<th class="text-center"><strong>Police Messages</strong></th> --%>
                                                <%-- 														</c:if> --%>
                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole}">
                                                <th class="text-center"><strong>Stay in SL</strong></th>
                                            </c:if>
                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">
                                                <th class="text-center"><strong>Attachments</strong></th>
                                            </c:if>
                                            <c:if test="${!(userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole)}">
                                                <c:if test="${userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">
                                                    <th class="text-center"><strong>Comment</strong></th>
                                                </c:if>
                                                <th class="text-center"><strong>Cleared</strong></th>
                                                <th class="text-center"><strong>Save</strong></th>
                                            </c:if>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${applicationList}" var="applicationVO">
                                            <input type="hidden" id="aspReviewStatusId_${applicationVO.applicationId}"
                                                   value="${applicationVO.aspReviewStatus}"/>
                                            <input type="hidden" id="hiddenAppId"
                                                   value="${applicationVO.applicationId}"/>
                                            <c:choose>
                                                <c:when test="${userRole==userRoleDisplayVO.phqUserRole}">
                                                    <c:choose>
                                                        <c:when test="${applicationVO.hasRequestClarification==1}">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <tr style="background-color: #ACD4F7;border: 2px solid #ff0000;">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <tr style="background-color: #ACD4F7;">
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <tr style="border: 2px solid #ff0000;">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <tr>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${applicationVO.hasResent==1}">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <tr style="background-color: #ACD4F7;border: 2px solid #FCDBD8;">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <tr style="background-color: #ACD4F7;">
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <tr style="border: 2px solid #ff0000;">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <tr>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>


                                            <td class="text-center-middle">
                                                <c:choose>
                                                    <c:when test="${userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">
                                                        <c:choose>
                                                            <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                <img src="images/lock.png"
                                                                     style="width: 30px;height:30px;cursor: pointer;"
                                                                     alt="Select" title="Select"
                                                                     id="lockUnlockBtn_${applicationVO.applicationId}"
                                                                     class="basic_image_button"
                                                                     onclick="cancelEditAdverse(${applicationVO.applicationId})"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="images/unlock.png"
                                                                     style="width: 30px;height:30px;cursor: pointer;"
                                                                     alt="Select" title="Select"
                                                                     id="lockUnlockBtn_${applicationVO.applicationId}"
                                                                     class="basic_image_button"
                                                                     onclick="editRowAdverse(${applicationVO.applicationId})"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                <img src="images/lock.png"
                                                                     style="width: 30px;height:30px;cursor: pointer;"
                                                                     alt="Select" title="Select"
                                                                     id="lockUnlockBtn_${applicationVO.applicationId}"
                                                                     class="basic_image_button"
                                                                     onclick="cancelEdit(${applicationVO.applicationId})"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="images/unlock.png"
                                                                     style="width: 30px;height:30px;cursor: pointer;"
                                                                     alt="Select" title="Select"
                                                                     id="lockUnlockBtn_${applicationVO.applicationId}"
                                                                     class="basic_image_button"
                                                                     onclick="editRow(${applicationVO.applicationId})"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="text-center-middle" style="vertical-align: middle;">
                                                <fmt:formatDate value="${applicationVO.submittedDate}"
                                                                pattern="dd/MM/yyyy hh:mm aa"/></td>
                                            <td class="text-center-middle" style="vertical-align: middle;">
                                                    ${applicationVO.referenceNo}
                                                <input type="hidden"
                                                       id="hiddenReferenceNumber_${applicationVO.applicationId}"
                                                       value="${applicationVO.referenceNo}"/>
                                            </td>

                                            <c:choose>
                                                <c:when test="${userRole != userRoleDisplayVO.imiUserRole}">
                                                    <td class="text-center-middle">${applicationVO.currentNicNo}</td>
                                                </c:when>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${userRole != userRoleDisplayVO.nicUserRole}">
                                                    <td class="text-center-middle"
                                                        style="vertical-align: middle;">${applicationVO.passport}</td>
                                                </c:when>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${userRole==userRoleDisplayVO.imiUserRole}">
                                                    <td class="text-center-middle">
                                                            ${applicationVO.applicantNameAsPassport}
                                                    </td>
                                                </c:when>
                                                <c:when test="${userRole==userRoleDisplayVO.nicUserRole}">
                                                    <td class="text-center-middle">
                                                            ${applicationVO.applicantNameAsNic}
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="text-center-middle">
                                                            ${applicationVO.applicantNameAsNic}
                                                    </td>
                                                    <td class="text-center-middle">
                                                            ${applicationVO.applicantNameAsPassport}
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>

                                            <td class="text-center-middle" style="vertical-align: middle;">
                                                <fmt:formatDate value="${applicationVO.dateOfBirth}"
                                                                pattern="dd/MM/yyyy"/></td>
                                            <td class="text-center-middle wrapword"
                                                style="vertical-align: middle;">${applicationVO.presentAddressLocal}</td>
                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole}">
                                                <td class="text-center">${applicationVO.mobileNo}</td>
                                            </c:if>

                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole}">
                                                <td class="text-center-middle" style="vertical-align: middle;">
                                                    <c:choose>
                                                        <c:when test="${userRole==userRoleDisplayVO.phqUserRole}">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <input onclick="viewAddressListPhq(${applicationVO.applicationId})"
                                                                           type="button"
                                                                           id="view_link_phq_${applicationVO.applicationId}"
                                                                           alt="Update" value="Update"
                                                                           class="btn btn-primary es-buttton"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input onclick="javascript:void(0)" type="button"
                                                                           id="view_link_phq_${applicationVO.applicationId}"
                                                                           disabled="disabled" alt="View" value="View"
                                                                           class="btn btn-primary es-buttton"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <input onclick="viewAddressListPolice(${applicationVO.applicationId})"
                                                                           type="button"
                                                                           id="view_link_pol_${applicationVO.applicationId}"
                                                                           alt="View" value="View"
                                                                           class="btn btn-primary es-buttton"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input onclick="javascript:void(0)" type="button"
                                                                           id="view_link_pol_${applicationVO.applicationId}"
                                                                           disabled="disabled" alt="View" value="View"
                                                                           class="btn btn-primary es-buttton"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:if>

<%--                                             <input type="hidden" id="isDHAUserId" value="${isDhaUser}"/> --%>
											<input type="hidden" id="isASPUserId" value="${isAspUser}"/>

                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">

                                                <c:choose>
                                                    <c:when test="${userRole==userRoleDisplayVO.nicUserRole}">
                                                        <td class="text-center-middle" style="vertical-align: middle;">
                                                            <c:choose>
                                                                <c:when test="${!empty applicationVO.newNic}">
                                                                    <c:if test="${!empty applicationVO.newNicAttachPath}">
                                                                        <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">
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
                                                                                        <td>&nbsp;&nbsp;New NIC</td>
                                                                                    </tr>
                                                                                </table>
                                                                            </a>
                                                                        </c:if>
                                                                    </c:if>
                                                                </c:when>
                                                                <c:otherwise >
                                                                    <c:if test="${!empty applicationVO.nicAttachPath}">
                                                                        <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">
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
                                                                                            <img alt="NIC"
                                                                                                 src="images/zoom_in.png"
                                                                                                 style="width:15px;height:15px;"/>

                                                                                        </td>
                                                                                        <td>&nbsp;&nbsp;NIC</td>
                                                                                    </tr>
                                                                                </table>
                                                                            </a>
                                                                        </c:if>
                                                                    </c:if>

                                                                </c:otherwise>
                                                            </c:choose>

                                                        </td>
                                                    </c:when>
                                                    <c:when test="${userRole==userRoleDisplayVO.imiUserRole}">
                                                        <td class="text-center-middle" style="vertical-align: middle;">
                                                        <c:if test="${!empty applicationVO.passportAttachPath}">
                                                            <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.imiUserRole}">
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
                                                                            <td>&nbsp;&nbsp;PPT</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>
                                                        </c:if>
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="text-center-middle" style="vertical-align: middle;">

                                                            <c:if test="${!empty applicationVO.nicAttachPath}">
                                                                <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">
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
                                                                                <td>&nbsp;&nbsp;NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                            </c:if>

                                                            <c:if test="${!empty applicationVO.newNicAttachPath}">
                                                                <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">
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
                                                                                <td>&nbsp;&nbsp;New NIC</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
                                                            </c:if>

                                                            <c:if test="${!empty applicationVO.passportAttachPath}">
                                                                <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.imiUserRole}">
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
                                                                                <td>&nbsp;&nbsp;PPT</td>
                                                                            </tr>
                                                                        </table>
                                                                    </a>
                                                                </c:if>
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
                                                                            <td>&nbsp;&nbsp;BC</td>
                                                                        </tr>
                                                                    </table>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${applicationVO.selectedNameOption =='passport'}">
                                                            <c:if test="${!empty applicationVO.affidavitAttachPath}">
                                                                <c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.imiUserRole}">
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
                                                            </c:if>

                                                        </td>

                                                    </c:otherwise>

                                                </c:choose>

                                                <%--<td class="text-center-middle" style="vertical-align: middle;">--%>

                                                    <%--<c:if test="${!empty applicationVO.nicAttachPath}">--%>
                                                        <%--<c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">--%>
                                                            <%--<a href="javascript:void(0)"--%>
                                                               <%--onclick="viewNicPopup(${applicationVO.applicationId})">--%>
                                                                <%--<table>--%>
                                                                    <%--<tr>--%>
                                                                        <%--<td>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNicFileName_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNicFileType_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNicFileNameBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicBackAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNicFileTypeBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicBackFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNicNo_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nic}"/>--%>
                                                                            <%--<img alt="NIC" src="images/zoom_in.png"--%>
                                                                                 <%--style="width:15px;height:15px;"/>--%>

                                                                        <%--</td>--%>
                                                                        <%--<td>&nbsp;&nbsp;NIC</td>--%>
                                                                    <%--</tr>--%>
                                                                <%--</table>--%>
                                                            <%--</a>--%>
                                                        <%--</c:if>--%>
                                                    <%--</c:if>--%>

                                                    <%--<c:if test="${!empty applicationVO.newNicAttachPath}">--%>
                                                        <%--<c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.nicUserRole}">--%>
                                                            <%--<a href="javascript:void(0)"--%>
                                                               <%--onclick="viewNewNicPopup(${applicationVO.applicationId})">--%>
                                                                <%--<table>--%>
                                                                    <%--<tr>--%>
                                                                        <%--<td>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNewNicFileName_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.newNicAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNewNicFileType_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNewNicFileNameBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.newNicBackAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNewNicFileTypeBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicBackFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenNewNicNo_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.newNic}"/>--%>
                                                                            <%--<img alt="NIC" src="images/zoom_in.png"--%>
                                                                                 <%--style="width:15px;height:15px;"/>--%>

                                                                        <%--</td>--%>
                                                                        <%--<td>&nbsp;&nbsp;New NIC</td>--%>
                                                                    <%--</tr>--%>
                                                                <%--</table>--%>
                                                            <%--</a>--%>
                                                        <%--</c:if>--%>
                                                    <%--</c:if>--%>

                                                    <%--<c:if test="${!empty applicationVO.passportAttachPath}">--%>
                                                        <%--<c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.imiUserRole}">--%>
                                                            <%--<a href="javascript:void(0)"--%>
                                                               <%--onclick="viewPptPopup(${applicationVO.applicationId})">--%>
                                                                <%--<table>--%>
                                                                    <%--<tr>--%>
                                                                        <%--<td>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenPptFileName_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.passportAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenPptFileType_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.pptFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenPptFileNameBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.passportBackAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenPptFileTypeBack_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.pptBackFileType}"/>--%>

                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenPptNo_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.passport}"/>--%>
                                                                            <%--<img alt="NIC" src="images/zoom_in.png"--%>
                                                                                 <%--style="width:15px;height:15px;"/>--%>
                                                                        <%--</td>--%>
                                                                        <%--<td>&nbsp;&nbsp;PPT</td>--%>
                                                                    <%--</tr>--%>
                                                                <%--</table>--%>
                                                            <%--</a>--%>
                                                        <%--</c:if>--%>
                                                    <%--</c:if>--%>

                                                    <%--<c:if test="${! empty  applicationVO.birthCertificatePath}">--%>
                                                        <%--<a href="javascript:void(0)"--%>
                                                           <%--onclick="viewBcPopup(${applicationVO.applicationId})">--%>
                                                            <%--<table>--%>
                                                                <%--<tr>--%>
                                                                    <%--<td>--%>
                                                                        <%--<input type="hidden"--%>
                                                                               <%--id="hiddenBcFileName_${applicationVO.applicationId}"--%>
                                                                               <%--value="${applicationVO.birthCertificatePath}"/>--%>
                                                                        <%--<input type="hidden"--%>
                                                                               <%--id="hiddenBcFileType_${applicationVO.applicationId}"--%>
                                                                               <%--value="${applicationVO.birthCertificateFileType}"/>--%>
                                                                        <%--<input type="hidden"--%>
                                                                               <%--id="hiddenBcFileName_back_${applicationVO.applicationId}"--%>
                                                                               <%--value="${applicationVO.birthCertificateBackPath}"/>--%>
                                                                        <%--<input type="hidden"--%>
                                                                               <%--id="hiddenBcFileType_back_${applicationVO.applicationId}"--%>
                                                                               <%--value="${applicationVO.birthCertificateFileBackType}"/>--%>
                                                                        <%--<img alt="Birth Certificate"--%>
                                                                             <%--src="images/zoom_in.png"--%>
                                                                             <%--style="width:15px;height:15px;"/>--%>
                                                                    <%--</td>--%>
                                                                    <%--<td>&nbsp;&nbsp;BC</td>--%>
                                                                <%--</tr>--%>
                                                            <%--</table>--%>
                                                        <%--</a>--%>
                                                    <%--</c:if>--%>

                                                    <%--<c:if test="${!empty applicationVO.affidavitAttachPath}">--%>
                                                        <%--<c:if test="${userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.imiUserRole}">--%>
                                                            <%--<a href="javascript:void(0)"--%>
                                                               <%--onclick="viewAffidavitPopup(${applicationVO.applicationId})">--%>
                                                                <%--<table>--%>
                                                                    <%--<tr>--%>
                                                                        <%--<td>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenAffidavitFileName_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.affidavitAttachPath}"/>--%>
                                                                            <%--<input type="hidden"--%>
                                                                                   <%--id="hiddenAffidavitFileType_${applicationVO.applicationId}"--%>
                                                                                   <%--value="${applicationVO.nicFileType}"/>--%>

                                                                            <%--<img alt="AFFIDAVIT"--%>
                                                                                 <%--src="images/zoom_in.png"--%>
                                                                                 <%--style="width:15px;height:15px;"/>--%>
                                                                        <%--</td>--%>
                                                                        <%--<td>&nbsp;&nbsp;AFFIDAVIT</td>--%>
                                                                    <%--</tr>--%>
                                                                <%--</table>--%>
                                                            <%--</a>--%>
                                                        <%--</c:if>--%>
                                                    <%--</c:if>--%>

                                                <%--</td>--%>


                                            </c:if>
                                            <c:if test="${!(userRole==userRoleDisplayVO.phqUserRole || userRole==userRoleDisplayVO.polUserRole)}">
                                                <c:if test="${userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">
                                                    <td class="text-center-middle" style="vertical-align: middle;">
                                                        <c:choose>
                                                            <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                <input type="text" class="form-control"
                                                                       id="comment_${applicationVO.applicationId}"
                                                                       name="comment">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="text" class="form-control"
                                                                       id="comment_${applicationVO.applicationId}"
                                                                       name="comment" readonly="readonly"
                                                                       disabled="disabled">
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </c:if>
                                                <td class="text-center-middle" style="vertical-align: middle;">
                                                    <c:choose>
                                                        <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                            <table>
                                                                <tr>
                                                                    <td style="vertical-align: middle;"><input
                                                                            style="cursor: pointer;" type="radio"
                                                                            name="clearenceStatus_${applicationVO.applicationId}"
                                                                            value="Y"></td>
                                                                    <td style="vertical-align: middle;">Y</td>
                                                                    <td>&nbsp;&nbsp;</td>
                                                                    <td style="vertical-align: middle;"><input
                                                                            style="cursor: pointer;" type="radio"
                                                                            name="clearenceStatus_${applicationVO.applicationId}"
                                                                            value="N"></td>
                                                                    <td style="vertical-align: middle;">N</td>
                                                                </tr>
                                                            </table>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <table>
                                                                <tr>
                                                                    <td style="vertical-align: middle;"><input
                                                                            type="radio" style="cursor: pointer;"
                                                                            name="clearenceStatus_${applicationVO.applicationId}"
                                                                            value="Y" readonly="readonly"
                                                                            disabled="disabled"></td>
                                                                    <td style="vertical-align: middle;">Y</td>
                                                                    <td>&nbsp;&nbsp;</td>
                                                                    <td style="vertical-align: middle;"><input
                                                                            type="radio" style="cursor: pointer;"
                                                                            name="clearenceStatus_${applicationVO.applicationId}"
                                                                            value="N" readonly="readonly"
                                                                            disabled="disabled"></td>
                                                                    <td style="vertical-align: middle;">N</td>
                                                                </tr>
                                                            </table>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="text-center-middle" style="vertical-align: middle;">
                                                    <c:choose>
                                                        <c:when test="${userRole==userRoleDisplayVO.nicUserRole || userRole==userRoleDisplayVO.imiUserRole}">
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/save.png"
                                                                         style="width:20px;height:20px;cursor: pointer;"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button"
                                                                         onclick="loadSaveAdverseIssueType(${applicationVO.applicationId})"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/save.png"
                                                                         style="width: 20px;height:20px;cursor: pointer;"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${applicationVO.hasCurrentUserLocked == 1}">
                                                                    <img src="images/save.png"
                                                                         style="width:20px;height:20px;cursor: pointer;"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button"
                                                                         onclick="loadLargeComment(${applicationVO.applicationId})"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="images/save.png"
                                                                         style="width: 20px;height:20px;cursor: pointer;"
                                                                         id="save_image_${applicationVO.applicationId}"
                                                                         alt="Save" title="Save"
                                                                         class="basic_image_button disabled_image"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:if>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <form action="updateClearenceStatus.action" method="post" id="clearenceUpdateForm">
                                <s:hidden name="applicationId" id="applicationId_clearence"/>
                                <s:hidden name="comment" id="comment_clearence"/>
                                <s:hidden name="clearenceStatus" id="clearenceStatus_clearence"/>
                            </form>

                            <form action="updateAdverseClearenceStatus.action" method="post"
                                  id="clearenceUpdateFormAdverse">
                                <s:hidden name="applicationId" id="applicationId_clearence_adverse"/>
                                <s:hidden name="comment" id="comment_clearence_adverse"/>
                                <s:hidden name="clearenceStatus" id="clearenceStatus_clearence_adverse"/>
                                <s:hidden name="adverseType" id="adverseType_clearence_adverse"/>
                            </form>


                            <c:if test="${! empty reviewGridButtons}">
                                <div id="gridButtons" style="padding: 5px;float: right;margin-right: 10px;">
                                    <table class="table table-striped">
                                        <tr>
                                            <td><label class="control-label"><b>No of Records Per Page: </b></label>
                                            </td>
                                            <td>
                                                <c:set var="recordCount" value="10,20,30,40,50" scope="page"/>
                                                <select id="noOfRecordsPerPage" onchange="setLimit(this.value)"
                                                        class="form-control">
                                                    <c:forEach items="${pageScope.recordCount}" var="currentPageCount">
                                                        <c:choose>
                                                            <c:when test="${currentPageCount == searchCriteriaVO.limit}">
                                                                <option selected="selected"
                                                                        value="${currentPageCount}">${currentPageCount}</option>
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
                                            <c:forEach items="${reviewGridButtons}" var="button">
                                                <td style="padding-left: 5px;">
                                                    <c:if test="${button.displayStatus==1}">
                                                        <c:choose>
                                                            <c:when test="${button.currentButtonStatus == 1}">
                                                                <input type="button" value="${button.label}"
                                                                       disabled="disabled" class="btn btn-default">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="button"
                                                                       onclick="goToSelectedPage(${button.maxId},${button.limit},${button.pageNo})"
                                                                       value="${button.label}" class="btn btn-primary">
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </div>
                            </c:if>

                        </c:if>

                        <div id="legends" style="padding: 5px;float: left;margin-left: 10px;">
                            <table>
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${userRole==userRoleDisplayVO.phqUserRole}">
                                                <div class="legend_div" style="background-color:#ACD4F7;">Edited
                                                    Applications By Police
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="legend_div" style="background-color:#ACD4F7;">Resent
                                                    Applications
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="legend_div" style="border:2px solid #ff0000;">Locked Application
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

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
            <a data-toggle="modal" id="addressListViewPoliceModelLink" href="#addressListViewPoliceModelPopUp">View</a>
            <a data-toggle="modal" id="addressListViewPhqModelLink" href="#addressListViewPhqModelPopUp">View</a>
            <a data-toggle="modal" id="adverseIssueTypeModellLink" href="#adverseIssueTypeModelModelPopUp">View</a>
            <a data-toggle="modal" id="pptViewModellLink" href="#pptViewModelPopUp">View</a>
            <a data-toggle="modal" id="nicViewModellLink" href="#nicViewModelPopUp">View</a>
            <a data-toggle="modal" id="newNicViewModellLink" href="#newNicViewModelPopUp">View</a>
            <a data-toggle="modal" id="commentViewModellLink" href="#commentViewModelPopUp">View</a>
            <a data-toggle="modal" id="affidavitViewModellLink" href="#affidavitViewModelPopUp">View</a>
            <a data-toggle="modal" id="bcViewModellLink" href="#bcViewModelPopUp">View</a>
        </div>


        <!--  #####################################################	PHQ CLEARENCE PERIOD POPUP   ######################################################## -->
        <div class="modal" id="addressListViewPhqModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">

            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">View Stay in SL and place for Clearance Period</h4>
                    </div>
                    <div class="modal-body">

                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Police Area</th>
                                <th>Address</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Comment</th>
                                <c:choose>
                                    <c:when test="${isAspUser == 'true'}">
                                        <th>Approve</th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>Cleared</th>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${isAspUser == 'true'}">
                                        <th>View</th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>Edit</th>
                                    </c:otherwise>
                                </c:choose>

                                <th>Save</th>
                            </tr>
                            </thead>
                            <tbody id="addressListViewPhqModelAppendDiv"></tbody>
                        </table>
                        <hr/>

                        <div class="col-lg-9" id="phq_address_edit_div" style="display: none;">
                            <div style="border:1px solid #ccc;padding:10px;">
                                <h4>Edit Address</h4>
                                <hr/>
                                <input type="hidden" id="addressEditPhqAddressIdHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPhqPoliceAreaHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPhqAddressHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPhqFromDateHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPhqToDateHidden" readonly="readonly"/>

                                <table width="100%" id="addressTable">
                                    <tr>
                                        <td><label class="control-label bold-label">Police Area: </label></td>
                                        <td><select id="addressEditPhqPoliceArea" class="form-control"></select></td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <div id="addressEditPhqPoliceAreaStatus" class="input_div"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><label class="control-label bold-label">Address: </label></td>
                                        <td><textarea rows="3" cols="10" id="addressEditPhqAddress"
                                                      class="form-control"></textarea></td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <div id="addressEditPhqAddressStatus" class="input_div"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr id="perioFromIdTr">
                                        <td><label class="control-label bold-label">Period From: </label></td>
                                        <td>
                                            <input type="text" id="addressEditPhqFromDate" class="form-control"/>
                                            <label class="control-label bold-label">&nbsp;&nbsp; To:&nbsp;</label>
                                            <input type="text" id="addressEditPhqToDate" class="form-control"/>
                                        </td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <div id="addressEditPhqPeriodStatus" class="input_div"></div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="4">&nbsp;</td>                                        
                                    </tr>
                                    <c:choose>
                                        <c:when test="${isAspUser == 'false'}">
                                            <tr id="commentTr">
                                                <td><label class="control-label bold-label">Comment: </label></td>
                                                <td><textarea rows="3" cols="10" id="addressEditPhqComment"
                                                              class="form-control"></textarea></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td><label class="control-label bold-label">Updated By: </label></td>
                                                <td>
                                                    <div id="addressEditPhqUpdatedByText" class="input_div"></div>
                                                </td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td>
                                                    <input type="checkbox" id="addressEditPhqSendEmail"
                                                           onclick="showEmailTypeButtonsPhq()"/>
                                                    <label class="control-label bold-label">Send Email to Applicant</label>
                                                </td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr id="emailTypePhqSection" style="display: none;">
                                                <td><label class="control-label bold-label">Select Email Type To Send</label>
                                                </td>
                                                <td>
                                                    <input type="radio" name="emailTypePhq" value="UP"
                                                           onclick="loadEmailContentPhq()"/>Update in police Area <br/>
                                                    <input type="radio" name="emailTypePhq" value="IA"
                                                           onclick="loadEmailContentPhq()"/>Incorrect Address or Period of stay
                                                </td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr id="emailContentPhqSection" style="display: none;">
                                                <td><label class="control-label bold-label">Email: </label></td>
                                                <td>
                                                    <div id="addressEditPhqEmail" contenteditable="true"
                                                         style="padding: 2px;border: 1px solid black;"></div>

                                                </td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td colspan="3" align="left">
                                                    <input type="button" class="btn btn-primary"
                                                           id="phqSendEmailAddressEditSave" onclick="javascript:void(0)"
                                                           value="Send Email"/>&nbsp;

                                                    <input type="button" class="btn btn-primary" id="phqAddressEditSave"
                                                           onclick="javascript:void(0)"
                                                           value=""/>&nbsp;

                                                    <input type="button" class="btn btn-primary" id="phqAddressEditCancel"
                                                           onclick="javascript:void(0)" value="Cancel"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                        </c:when>
                                    </c:choose>

                                </table>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--  #####################################################	POLICE CLEARENCE PERIOD POPUP   ######################################################## -->
        <div class="modal" id="addressListViewPoliceModelPopUp" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">View Stay in SL and place for Clearance Period</h4>
                    </div>
                    <div class="modal-body">

                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Police Area</th>
                                <th>Address</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Comment</th>
                                <th>Cleared</th>
                                <th>Edit</th>
                                <th>Save</th>
                            </tr>
                            </thead>
                            <tbody id="addressListViewPoliceModelAppendDiv"></tbody>
                        </table>
                        <hr/>

                        <div class="col-lg-9" id="police_address_edit_div" style="display: none;">
                            <div style="border:1px solid #ccc;padding:10px;">
                                <h4>Edit Address</h4>
                                <hr/>
                                <input type="hidden" id="addressEditPolicePoliceAreaHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPoliceAddressHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPoliceFromDateHidden" readonly="readonly"/>
                                <input type="hidden" id="addressEditPoliceToDateHidden" readonly="readonly"/>
                                <table width="100%">
                                    <tr>
                                        <td><label class="control-label bold-label">Police Area: </label></td>
                                        <td><select id="addressEditPolicePoliceArea" class="form-control"></select></td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <select id="addressEditPolicePoliceAreaStatus" class="form-control">
                                                <option value="0">--Select--</option>
                                                <option value="CF">Confirm</option>
                                                <option value="MF">Modified</option>
                                                <option value="RM">Request to Modify</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><label class="control-label bold-label">Address: </label></td>
                                        <td><textarea rows="3" cols="10" id="addressEditPoliceAddress"
                                                      readonly="readonly" class="form-control"></textarea></td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <select id="addressEditPoliceAddressStatus" class="form-control">
                                                <option value="0">--Select--</option>
                                                <option value="CF">Confirm</option>
                                                <option value="RM">Request to Modify</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><label class="control-label bold-label">Period From: </label></td>
                                        <td>
                                            <input type="text" id="addressEditPoliceFromDate" readonly="readonly"
                                                   class="form-control"/>
                                            <label class="control-label bold-label">&nbsp;&nbsp; To:&nbsp;</label>
                                            <input type="text" id="addressEditPoliceToDate" readonly="readonly"
                                                   class="form-control"/>
                                        </td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <select id="addressEditPolicePeriodStatus" class="form-control">
                                                <option value="0">--Select--</option>
                                                <option value="CF">Confirm</option>
                                                <option value="RM">Request to Modify</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><label class="control-label bold-label">Comment: </label></td>
                                        <td><textarea rows="3" cols="10" id="addressEditPoliceComment"
                                                      class="form-control"></textarea></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td colspan="2" align="left">
                                            <input type="button" class="btn btn-primary" id="policeAddressEditSave"
                                                   onclick="javascript:void(0)" value="Save"/>&nbsp;
                                            <input type="button" class="btn btn-primary" id="policeAddressEditCancel"
                                                   onclick="javascript:void(0)" value="Cancel"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--  #####################################################	ADVERSE ISSUE TYPE POPUP   ######################################################## -->
        <div class="modal fade" id="adverseIssueTypeModelModelPopUp" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Add Comment</span>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="adverseIssueTypeApplicationId" id="adverseIssueTypeApplicationId"/>
                        <table>
                            <tr>
                                <td colspan="2">
                                    <input type="radio" name="adverseIssueType" value="NI"/><label
                                        class="control-label bold-label">Name Issue</label> &nbsp;
                                    <input type="radio" name="adverseIssueType" value="OI"/><label
                                        class="control-label bold-label">Other Issue</label> &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <label class="control-label bold-label">Please add a comment for your action</label>
                                    <br/>
                                    <textarea rows="5" cols="90" name="adverseIssueTypeComment"
                                              id="adverseIssueTypeComment" class="form-control"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right" style="text-align: right;">
                                    <input type="button" class="btn btn-primary" id="adverseIssueTypeSave"
                                           onclick="javascript:void(0)" value="Save"/>&nbsp;
                                    <input type="button" class="btn btn-primary" id="adverseIssueTypeCancel"
                                           onclick="javascript:void(0)" value="Cancel"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

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
                        <div style="text-align: center;">
                            <span style="font-size:18px;font-weight:bold;">Passport No. <span
                                    id="passportNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned Passport Copy from Applicant</span>
                            </div>
                            <div id="passportLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
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
                        <div style="text-align: center;  ">
                            <span style="font-size:18px;font-weight:bold;">NIC No. <span
                                    id="nicNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned NIC Copy from Applicant</span>
                            </div>
                            <div id="nicLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
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
                        <div style="text-align: center;  ">
                            <span style="font-size:18px;font-weight:bold;">New NIC No. <span
                                    id="newNicNumberAppend"></span></span>
                        </div>
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned New NIC Copy from Applicant</span>
                            </div>
                            <div id="newNicLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
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

        <!--  #####################################################	COMMENT POPUP   ######################################################## -->
        <div class="modal fade" id="commentViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Add comment</span>
                    </div>
                    <div class="modal-body">
                        <div style="text-align: center;  ">
                            <textarea rows="30" cols="90" id="largeComment"></textarea>
                            <input type="hidden" id="largeCommentApplicationId" class="form-control"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="saveRow()">Save</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <!--  #####################################################	AFFIDAVIT VIEW POPUP   ######################################################## -->
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
                                <span style="font-size:16px;font-weight:bold;">Scanned affidavit Copy from Applicant</span>
                            </div>
                            <div id="affidavitLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
                                    Please click
                                    <a id="affidavitFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download Affidavit file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="affidavitImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="affidavitImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
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
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Birth certificate</span>
                    </div>
                    <div class="modal-body">
                        <div>
                            <div style="padding: 5px 0px;">
                                <span style="font-size:16px;font-weight:bold;">Scanned Birth certificate from Applicant</span>
                            </div>
                            <div id="bcLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
                                    Please click
                                    <a id="bcFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download front side file
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="bcImge_link image-link"
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

        <!-- including footer -->
            <jsp:include page="../common/footer.jsp"/>

        <script type="text/javascript" src="js/jquery.numeric.js"></script>
        <script language="javascript" type="text/javascript">

            $(document).ready(function () {
                initializeDateTimePickers();

                $('#clearReviewApplication').click(function () {
                    $.datepicker._clearDate('#fromDate_id');
                    $.datepicker._clearDate('#toDate_id');
                    $('#pptNo').val('');
                    $('#referenceNo').val('');
                    $('#nicNo').val('');
                    $('#newNicNo').val('');
                    $('#name').val('');
                });

            });

            function initializeDateTimePickers() {
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
            }

            function editAddressPolice() {
                blockUI();


            }

            function showEmailTypeButtonsPhq() {
                if ($("#addressEditPhqSendEmail").is(':checked')) {
                    $('#emailTypePhqSection').show();
                } else {
                    $('#emailTypePhqSection').hide();
                    $('#addressEditPhqEmail').html('');
                    $('#emailContentPhqSection').hide();
                }
            }

            function loadEmailContentPhq() {
                var emailType = $('input[name=emailTypePhq]:checked').val();
                blockUI();
                var addressId = $('#addressEditPhqAddressIdHidden').val();
                var address = $.trim($('#addressEditPhqAddress').val());
                var fromDate = $('#addressEditPhqFromDate').val();
                var toDate = $('#addressEditPhqToDate').val();
                var policeAreaId = $('#addressEditPhqPoliceArea').val();
                var policeArea = $("#addressEditPhqPoliceArea option:selected").text()

                $.post('loadEmailContentPhq.action', {
                    addressId: addressId,
                    address: address,
                    policeAreaId: policeAreaId,
                    policeArea: policeArea,
                    fromDate: fromDate,
                    toDate: toDate,
                    emailType: emailType
                }, function (data) {
                    unBlockUI();
                    $('#addressEditPhqEmail').html(data.emailContent);
                    $('#emailContentPhqSection').show();
                });
            }


            function enableEditAddressButtonPhq(addressId, applicationId) {
                $('#addressEditButtonPhq_' + addressId).removeClass('disabled_image');
                $("#addressEditButtonPhq_" + addressId).click(function () {
                    ediAddressByPhq(addressId, applicationId);
                });
            }

            function disableEditAddressButtonPhq(addressId, applicationId) {
                $("#addressEditButtonPhq_" + addressId).removeAttr('onclick');
                $("#addressEditButtonPhq_" + addressId).unbind('click');
                $('#addressEditButtonPhq_' + addressId).addClass('disabled_image');
                $('#phq_address_edit_div').hide();
            }


            function enableEditAddressButton(addressId, applicationId) {
                $('#addressEditButtonPolice_' + addressId).removeClass('disabled_image');
                $("#addressEditButtonPolice_" + addressId).click(function () {
                    ediAddressByPolice(addressId, applicationId);
                });
            }

            function disableEditAddressButton(addressId, applicationId) {
                $("#addressEditButtonPolice_" + addressId).removeAttr('onclick');
                $("#addressEditButtonPolice_" + addressId).unbind('click');
                $('#addressEditButtonPolice_' + addressId).addClass('disabled_image');
                $('#police_address_edit_div').hide();
            }


            function saveAddressByPolice(addressId, applicationId) {
                var inputName = 'address_clearenceStatus_' + addressId;
                var clearenceStatus = $.trim($('input[name=' + inputName + ']:checked').val());
                var comment = $.trim($('#address_comment_' + addressId).val());
                var isValid = true;
                if (!(clearenceStatus == 'Y' || clearenceStatus == 'N')) {
                    alert('Please select if the address is cleared or not!');
                    isValid = false;
                } else {
                    if (clearenceStatus == 'N') {
                        if (comment == null || comment == '') {
                            alert('Please enter a comment to indicate the reason why address is not cleared!');
                            isValid = false;
                        }
                    }
                }
                if (isValid) {
                    var con = confirm('Are you sure you want to update the clearence status?');
                    blockUI();
                    if (con) {
                        $.post('updateAddressPolice.action', {
                            addressId: addressId,
                            policeAddressComment: comment,
                            policeAddressClearenceStatus: clearenceStatus
                        }, function (data) {
                            unBlockUI();
                            alert(data.addressUpdatedMessage);
                            viewAddressListPolice(applicationId);
                        });
                    } else {
                        unBlockUI();
                    }
                }
            }


            function saveAddressByPhq(addressId, applicationId) {
                var inputName = 'address_clearenceStatus_phq_' + addressId;
                var clearenceStatus = $.trim($('input[name=' + inputName + ']:checked').val());
                var comment = $.trim($('#address_comment_phq' + addressId).val());
                var isValid = true;
                if (!(clearenceStatus == 'Y' || clearenceStatus == 'N')) {
                    alert('Please select if the address is cleared or not!');
                    isValid = false;
                } else {
                    if (clearenceStatus == 'N') {
                        if (comment == null || comment == '') {
                            alert('Please enter a comment to indicate the reason why address is not cleared!');
                            isValid = false;
                        }
                    }
                }
                if (isValid) {
                    var isAspUser = $('#isASPUserId').val();
                    var aspReviewStatus = '';
                    if (clearenceStatus == 'Y') {
                        if (isAspUser == 'false') {
                            aspReviewStatus = 'not_applicable';
                        } else if (isAspUser == 'true') {
                            aspReviewStatus = 'approved';
                        }
                    } else if (clearenceStatus == 'N') {
                        if (isAspUser == 'false') {
                            aspReviewStatus = 'not_applicable';
                        } else if (isAspUser == 'true') {
                            aspReviewStatus = 'not_approved';
                        }

                    }
                    var con ;
                    if(isAspUser == 'true'){
                        con = confirm('Are you sure you want to approve?');
                    }else{
                        con = confirm('Are you sure you want to update the clearence status?');
                    }

                    blockUI();
                    if (con) {
                        $.post('updateAddressPhq.action', {
                            addressId: addressId,
                            policeAddressComment: comment,
                            policeAddressClearenceStatus: clearenceStatus,
                            aspReviewStatus: aspReviewStatus,
                            applicationId: applicationId
                        }, function (data) {
                            unBlockUI();
                            alert(data.addressUpdatedMessage);
                            window.selectedAspStatus = data.aspReviewStatus;
                            viewAddressListPhq(applicationId);
                            $('#searchReviewApplication').click();
                        });
                    } else {
                        unBlockUI();
                    }
                }
            }


            function saveEditedAddressByPolice(addressId, applicationId) {
                var isValid = true;
                var addressEditPolicePoliceAreaStatus = $.trim($('#addressEditPolicePoliceAreaStatus').val());
                var addressEditPolicePoliceArea = $.trim($('#addressEditPolicePoliceArea').val());
                var addressEditPolicePoliceAreaHidden = $.trim($('#addressEditPolicePoliceAreaHidden').val());
                var addressEditPoliceAddressStatus = $.trim($('#addressEditPoliceAddressStatus').val());
                var addressEditPoliceAddress = $.trim($('#addressEditPoliceAddress').val());
                var policeArea = $("#addressEditPolicePoliceArea option:selected").text()
                var addressEditPolicePeriodStatus = $.trim($('#addressEditPolicePeriodStatus').val());
                var addressEditPoliceFromDate = $.trim($('#addressEditPoliceFromDate').val());
                var addressEditPoliceToDate = $.trim($('#addressEditPoliceToDate').val());
                var addressEditPoliceComment = $.trim($('#addressEditPoliceComment').val());

                if (addressEditPolicePoliceAreaStatus == "0" || addressEditPolicePoliceAreaStatus == 0) {
                    isValid = false;
                    alert('Please select the police area confirm status!');
                } else if (addressEditPolicePeriodStatus == 0 || addressEditPolicePeriodStatus == '0') {
                    isValid = false;
                    alert('Please select the stay period confirm status!');
                } else if (addressEditPoliceAddressStatus == 0 || addressEditPoliceAddressStatus == '0') {
                    isValid = false;
                    alert('Please select the address confirm status!');
                } else {
                    if (!(addressEditPolicePoliceArea == addressEditPolicePoliceAreaHidden)) {
                        if (!(addressEditPolicePoliceAreaStatus == 'MF')) {
                            isValid = false;
                            alert('The police area has been modified, the confirm status should be marked as modified!');
                        }
                    }
                    if (isValid) {
                        if (addressEditPoliceComment == '' || addressEditPoliceComment == null) {
                            isValid = false;
                            alert('Please enter the comment!');
                        }
                    }
                }

//                 var dhaReviewStatus = '';
				var aspReviewStatus = '';
                if (addressEditPolicePoliceAreaStatus == 'RM') {
//                     dhaReviewStatus = 'applicable';
                    aspReviewStatus = 'applicable';
                } else {
//                     dhaReviewStatus = 'not_applicable';
                    aspReviewStatus = 'not_applicable';
                }


                if (isValid) {
                    var con = confirm('Are you sure you want to update the address?');

                    if (con) {
                        blockUI();
                        $.post('updateAddressEditPolice.action', {
                            addressId: addressId,
                            address: addressEditPoliceAddress,
                            addressStatus: addressEditPoliceAddressStatus,
                            policeAreaId: addressEditPolicePoliceArea,
                            policeArea: policeArea,
                            policeAreaStatus: addressEditPolicePoliceAreaStatus,
                            fromDate: addressEditPoliceFromDate,
                            toDate: addressEditPoliceToDate,
                            stayPeriodStatus: addressEditPolicePeriodStatus,
                            comment: addressEditPoliceComment,
//                             dhaReviewStatus: dhaReviewStatus,
 							aspReviewStatus: aspReviewStatus,
                            applicationId: applicationId

                        }, function (data) {
                            unBlockUI();
                            alert(data.addressUpdatedMessage);
                            viewAddressListPolice(applicationId);
                        });
                    }
                }
            }


            function saveEditedAddressByPhq(addressId, applicationId) {
                var isValid = true;
                var address = $.trim($('#addressEditPhqAddress').val());
                var policeAreaId = $.trim($('#addressEditPhqPoliceArea').val());
                var policeArea = $.trim($("#addressEditPhqPoliceArea option:selected").text());
                var fromDate = $.trim($('#addressEditPhqFromDate').val());
                var toDate = $.trim($('#addressEditPhqToDate').val());
                var comment = $.trim($('#addressEditPhqComment').val());
                var sendEmailByPhq = 0;
                var emailContent = $.trim($('#addressEditPhqEmail').html());

                var emailType = $('input[name=emailTypePhq]:checked').val();

                if (policeAreaId <= 0) {
                    isValid = false;
                    alert('Please select the police area!');
                } else if (address == null || address == "" || address == 'undefined') {
                    isValid = false;
                    alert('Please enter the address!');
                } else if (fromDate == null || fromDate == "" || fromDate == 'undefined') {
                    isValid = false;
                    alert('Please enter the from date!');
                } else if (toDate == null || toDate == "" || toDate == 'undefined') {
                    isValid = false;
                    alert('Please enter the to date!');
                }

                if (isValid) {
                    if (comment == '' || comment == null) {
                        isValid = false;
                        alert('Please enter the comment!');
                    }
                }

                var submitBtlVal = $('#phqAddressEditSave').val();

                var clearedStatusNo = $('#approveNo_' + addressId).val();
                var clearedStatusYes = $('#approveYes_' + addressId).val();

//                 var isDhaUserVal = '';
//                 var dhaReviewStatus = '';
//                 var isDhaUser = $('#isDHAUserId').val();
                var isAspUserVal = '';
                var aspReviewStatus = '';
                var isAspUser = $('#isASPUserId').val();

                if (isAspUser == 'false') {
                    if (submitBtlVal == 'Forward to ASP') {
                    	aspReviewStatus = 'phq_request';
                    	isAspUserVal = 'false';
                    } else if (submitBtlVal == 'Confirm Changes and Save') {
                    	isAspUserVal = 'false';
                    	aspReviewStatus = 'not_applicable';
                    } else {
                    	isAspUserVal = 'false';
                    }

                } else {
                    if (clearedStatusNo == 'N') {
                        aspReviewStatus = 'not_approved';
                    } else if (clearedStatusYes == 'Y') {
                        aspReviewStatus = 'approved';
                    }
                }

                if (isValid) {
                    var con = confirm('Are you sure you want to update the address?');
                    if (con) {
                        blockUI();
                        $.post('updateAddressEditPhq.action', {
                            addressId: addressId,
                            address: address,
                            policeAreaId: policeAreaId,
                            policeArea: policeArea,
                            fromDate: fromDate,
                            toDate: toDate,
                            comment: comment,
                            sendEmailByPhq: sendEmailByPhq,
                            emailContent: emailContent,
                            emailType: emailType,
                            isAspUser: isAspUserVal,
                            aspReviewStatus: aspReviewStatus,
                            applicationId: applicationId
                        }, function (data) {
                            unBlockUI();
                            alert(data.addressUpdatedMessage);

                            window.selectedAspStatus = data.aspReviewStatus;

                            viewAddressListPhq(applicationId);
                            $('#searchReviewApplication').click();
                        });
                    }
                }
            }

            window.isEmailSend = false;
            function sendEmailByPhqAddressEdit(addressId, applicationId) {
                var isValid = true;

                var sendEmailByPhq = 0;
                var emailContent = $.trim($('#addressEditPhqEmail').html());
                var emailType = $('input[name=emailTypePhq]:checked').val();

                if ($("#addressEditPhqSendEmail").is(':checked')) {
                    sendEmailByPhq = 1;
                    if (emailContent == null || emailContent == "" || emailContent == 'undefined') {
                        isValid = false;
                        alert('Please enter the email content to be sent to the user!');
                    }
                } else {
                    alert('Please select whether to send the email!');
                    isValid = false;
                }

                if (isValid) {
                    var con = confirm('Are you sure you want to send the email?');
                    if (con) {
                        blockUI();
                        $.post('sendEmailAddressEditPhq.action', {
                            addressId: addressId,
                            sendEmailByPhq: sendEmailByPhq,
                            emailContent: emailContent,
                            emailType: emailType
                        }, function (data) {
                            unBlockUI();
                            alert(data.addressUpdatedMessage);
                            window.selectedAspStatus = $('#aspReviewStatusId_' + applicationId).val();
                            window.isEmailSend = true;

                            viewAddressListPhq(applicationId);
                        });
                    }
                }
            }

            function clearPhqEditAddress() {
                $('#addressEditPhqAddressIdHidden').val(0);
                $('#addressEditPhqPoliceAreaHidden').val(0);
                $('#addressEditPhqPoliceAreaStatus').val('');
                $('#addressEditPhqAddress').val('');
                $('#addressEditPhqAddressHidden').val('');
                $('#addressEditPhqAddressStatus').val('');
                $('#addressEditPhqPeriodStatus').val('');
                $('#addressEditPhqComment').val('');
                $('#addressEditPhqPoliceAreaStatus').val('');
                $('#addressEditPhqAddressStatus').val('');
                $('#addressEditPhqPeriodStatus').val('');
                $('#addressEditPhqUpdatedByText').val('');
                $('#addressEditPhqPoliceArea').val(0);
                $('#addressEditPhqFromDate').datepicker("setDate", new Date());
                $('#addressEditPhqToDate').datepicker("setDate", new Date());
                $('#addressEditPhqFromDateHidden').val($('#addressEditPhqFromDate').val());
                $('#addressEditPhqToDateHidden').val($('#addressEditPhqToDate').val());
            }

            function ediAddressByPhq(addressId, applicationId) {
                blockUI();
                clearPhqEditAddress();

                $(".asp-comments").hide();

                $.get('loadEditAddress.action', {addressId: addressId, applicationId: applicationId}, function (data) {
                    var addressTempVO = data.addressTempVO;
                    if (addressTempVO != null && data.policeAreaList.length > 0) {
                        $('#addressEditPhqPoliceArea').html('<option value="0" selected="selected">--select--</option>');

                        $.each(data.policeAreaList, function (index, item) {
                            if (addressTempVO.policeAreaId == item.id) {
                                $('#addressEditPhqPoliceArea').append('<option value="' + item.id + '" selected="selected">' + item.policeArea + '</option>');
                            } else {
                                $('#addressEditPhqPoliceArea').append('<option value="' + item.id + '">' + item.policeArea + '</option>');
                            }
                        });

                        $('#addressEditPhqAddressIdHidden').val(addressTempVO.addressId);
                        $('#addressEditPhqPoliceAreaHidden').val(addressTempVO.policeAreaId);
                        $('#addressEditPhqPoliceAreaStatus').val(addressTempVO.policeAreaStatus);
                        $('#addressEditPhqAddress').val(addressTempVO.address);
                        $('#addressEditPhqAddressHidden').val(addressTempVO.address);
                        $('#addressEditPhqAddressStatus').val(addressTempVO.addressStatus);
                        $('#addressEditPhqPeriodStatus').val(addressTempVO.stayPeriodStatus);
                        $('#addressEditPhqComment').val(addressTempVO.comment);
                        $('#addressEditPhqPoliceAreaStatus').html(addressTempVO.policeAreaStatusText);
                        $('#addressEditPhqAddressStatus').html(addressTempVO.addressStatusText);
                        $('#addressEditPhqPeriodStatus').html(addressTempVO.stayPeriodStatusText);
                        $('#addressEditPhqUpdatedByText').html(addressTempVO.updatedByText);

                        /*----------------------------------------DHA Comments----------------------------*/
                        var dhaCommets = data.dhaCommets;
                        var htmlComponet = '';
                        if (dhaCommets.length > 0) {
                            $.each(dhaCommets, function (index, item) {
                                htmlComponet = htmlComponet + '<tr class="dha-comments">';
                                htmlComponet = htmlComponet + '<td colspan="4">&nbsp;</td>';
                                htmlComponet = htmlComponet + '</tr>';
                                htmlComponet = htmlComponet + '<tr class="dha-comments">';
                                if (index == 0) {
                                    htmlComponet = htmlComponet + '<td><label class="control-label bold-label">DHA Comments: </label></td>';
                                } else {
                                    htmlComponet = htmlComponet + '<td><label class="control-label bold-label">&nbsp;</label></td>';
                                }

                                htmlComponet = htmlComponet + '<td><textarea rows="3" cols="10" class="form-control" readonly="readonly" disabled="disabled" id="dhaCommentId">' + item + '</textarea></td>';
                                htmlComponet = htmlComponet + '<td>&nbsp;</td>';
                                htmlComponet = htmlComponet + '<td>&nbsp;</td>';
                                htmlComponet = htmlComponet + '</tr>';


                            });

                            $('#perioFromIdTr').after(htmlComponet);
                        }

                        /*----------------------------------------DHA Comments----------------------------*/


                        $("#addressEditPhqFromDate").datepicker({
                            defaultDate: "+0d",
                            dateFormat: "dd/mm/yy",
                            maxDate: "+0d",
                            onClose: function (selectedDate) {
                                $("#addressEditPhqToDate").datepicker("option", "minDate", selectedDate);
                            }
                        });


                        $("#addressEditPhqToDate").datepicker({
                            defaultDate: "+0d",
                            dateFormat: "dd/mm/yy",
                            maxDate: "+0d",
                            onClose: function (selectedDate) {
                                $("#addressEditPhqFromDate").datepicker("option", "maxDate", selectedDate);
                            }
                        });

                        $('#addressEditPhqFromDate').datepicker("setDate", new Date(addressTempVO.fromDate));
                        $('#addressEditPhqToDate').datepicker("setDate", new Date(addressTempVO.toDate));
                        $('#addressEditPhqFromDateHidden').val($('#addressEditPhqFromDate').val());
                        $('#addressEditPhqToDateHidden').val($('#addressEditPhqToDate').val());

                        showPhqAddressEditDiv(addressId, applicationId);

                    } else {
                        alert('The selected address is not available!');
                    }
                    unBlockUI();
                });

            }

            function showPhqAddressEditDiv(addressId, applicationId) {
                $('#phq_address_edit_div').show();

                var isDhaUser = $('#isDHAUserId').val();

                if(isDhaUser == 'false'){
                    $('#address_comment_phq' + addressId).attr('disabled', 'disabled');
                    $('#address_comment_phq' + addressId).attr('readonly', 'readonly');
                }


                //$('#addressEditPhqComment').val($.trim($('#address_comment_phq' + addressId).val()));

                var inputName = 'address_clearenceStatus_phq_' + addressId;

                if(isDhaUser == 'false'){
                    $('input[name=' + inputName + ']').attr('disabled', 'disabled');
                    $('input[name=' + inputName + ']').attr('readonly', 'readonly');

                }


                if(isDhaUser == 'false'){
                    $("#addressSaveButtonPhq_" + addressId).removeAttr('onclick');
                    $("#addressSaveButtonPhq_" + addressId).unbind('click');
                    $('#addressSaveButtonPhq_' + addressId).addClass('disabled_image');
                }


                $('#phqAddressEditSave').removeAttr('onclick');
                $('#phqAddressEditSave').unbind('click');
                $('#phqAddressEditSave').click(function () {
                    saveEditedAddressByPhq(addressId, applicationId);
                });

                $('#phqSendEmailAddressEditSave').removeAttr('onclick');
                $('#phqSendEmailAddressEditSave').unbind('click');
                $('#phqSendEmailAddressEditSave').click(function () {
                    sendEmailByPhqAddressEdit(addressId, applicationId);
                });


                $('#phqAddressEditCancel').removeAttr('onclick');
                $('#phqAddressEditCancel').unbind('click');
                $('#phqAddressEditCancel').click(function () {
                    hidePhqAddressEditDiv(addressId, applicationId);
                });

            }

            function hidePhqAddressEditDiv(addressId, applicationId) {
                $('#phq_address_edit_div').hide();

                $('#address_comment_phq' + addressId).attr('disabled', false);
                $('#address_comment_phq' + addressId).attr('readonly', false);

                var inputName = 'address_clearenceStatus_phq_' + addressId;
                $('input[name=' + inputName + ']').attr('disabled', false);
                $('input[name=' + inputName + ']').attr('readonly', false);

                $('#addressSaveButtonPhq_' + addressId).removeClass('disabled_image');
                $('#addressSaveButtonPhq_' + addressId).click(function () {
                    saveAddressByPhq(addressId, applicationId);
                });
            }

            function clearPoliceEditAddress() {
                $('#addressEditPolicePoliceAreaHidden').val('');
                $('#addressEditPolicePoliceAreaStatus').val('');
                $('#addressEditPoliceAddress').val('');
                $('#addressEditPoliceAddressHidden').val('');
                $('#addressEditPoliceAddressStatus').val('');
                $('#addressEditPolicePeriodStatus').val('');
                $('#addressEditPoliceComment').val('');
                $('#addressEditPoliceFromDateHidden').datepicker("setDate", new Date());
                $('#addressEditPoliceToDateHidden').datepicker("setDate", new Date());
                $('#addressEditPoliceFromDate').val($('#addressEditPoliceFromDateHidden').val());
                $('#addressEditPoliceToDate').val($('#addressEditPoliceToDateHidden').val());
            }

            function ediAddressByPolice(addressId, applicationId) {
                blockUI();
                clearPoliceEditAddress();
                $.get('loadEditAddress.action', {addressId: addressId}, function (data) {
                    var addressTempVO = data.addressTempVO;
                    if (addressTempVO != null && data.policeAreaList.length > 0) {
                        $('#addressEditPolicePoliceArea').html('<option value="0" selected="selected">--select--</option>');

                        $.each(data.policeAreaList, function (index, item) {
                            if (addressTempVO.policeAreaId == item.id) {
                                $('#addressEditPolicePoliceArea').append('<option value="' + item.id + '" selected="selected">' + item.policeArea + '</option>');
                            } else {
                                $('#addressEditPolicePoliceArea').append('<option value="' + item.id + '">' + item.policeArea + '</option>');
                            }
                        });

                        $('#addressEditPolicePoliceAreaHidden').val(addressTempVO.policeAreaId);
                        $('#addressEditPolicePoliceAreaStatus').val(addressTempVO.policeAreaStatus);
                        $('#addressEditPoliceAddress').val(addressTempVO.address);
                        $('#addressEditPoliceAddressHidden').val(addressTempVO.address);
                        $('#addressEditPoliceAddressStatus').val(addressTempVO.addressStatus);
                        $('#addressEditPolicePeriodStatus').val(addressTempVO.stayPeriodStatus);
                        $('#addressEditPoliceComment').val(addressTempVO.comment);

                        $("#addressEditPoliceFromDateHidden").datepicker({
                            defaultDate: "+0d",
                            dateFormat: "dd/mm/yy",
                            maxDate: "+0d",
                            onClose: function (selectedDate) {
                                $("#addressEditPoliceToDate").datepicker("option", "minDate", selectedDate);
                            }
                        });

                        $("#addressEditPoliceToDateHidden").datepicker({
                            defaultDate: "+0d",
                            dateFormat: "dd/mm/yy",
                            maxDate: "+0d",
                            onClose: function (selectedDate) {
                                $("#addressEditPoliceFromDate").datepicker("option", "maxDate", selectedDate);
                            }
                        });

                        $('#addressEditPoliceFromDateHidden').datepicker("setDate", new Date(addressTempVO.fromDate));
                        $('#addressEditPoliceToDateHidden').datepicker("setDate", new Date(addressTempVO.toDate));

                        $('#addressEditPoliceFromDate').val($('#addressEditPoliceFromDateHidden').val());
                        $('#addressEditPoliceToDate').val($('#addressEditPoliceToDateHidden').val());

                        showPoliceAddressEditDiv(addressId, applicationId);

                    } else {
                        alert('The selected address is not available!');
                    }
                    unBlockUI();
                });
            }

            function showPoliceAddressEditDiv(addressId, applicationId) {
                $('#police_address_edit_div').show();

                $('#address_comment_' + addressId).attr('disabled', 'disabled');
                $('#address_comment_' + addressId).attr('readonly', 'readonly');

                $('#addressEditPoliceComment').val($.trim($('#address_comment_' + addressId).val()));

                var inputName = 'address_clearenceStatus_' + addressId;
                $('input[name=' + inputName + ']').attr('disabled', 'disabled');
                $('input[name=' + inputName + ']').attr('readonly', 'readonly');

                $("#addressSaveButtonPolice_" + addressId).removeAttr('onclick');
                $("#addressSaveButtonPolice_" + addressId).unbind('click');
                $('#addressSaveButtonPolice_' + addressId).addClass('disabled_image');

                $('#policeAddressEditSave').removeAttr('onclick');
                $('#policeAddressEditSave').unbind('click');
                $('#policeAddressEditSave').click(function () {
                    saveEditedAddressByPolice(addressId, applicationId);
                });

                $('#policeAddressEditCancel').removeAttr('onclick');
                $('#policeAddressEditCancel').unbind('click');
                $('#policeAddressEditCancel').click(function () {
                    hidePoliceAddressEditDiv(addressId, applicationId);
                });

            }

            function hidePoliceAddressEditDiv(addressId, applicationId) {
                $('#police_address_edit_div').hide();

                $('#address_comment_' + addressId).attr('disabled', false);
                $('#address_comment_' + addressId).attr('readonly', false);

                var inputName = 'address_clearenceStatus_' + addressId;
                $('input[name=' + inputName + ']').attr('disabled', false);
                $('input[name=' + inputName + ']').attr('readonly', false);

                $('#addressSaveButtonPolice_' + addressId).removeClass('disabled_image');
                $('#addressSaveButtonPolice_' + addressId).click(function () {
                    saveAddressByPolice(addressId, applicationId);
                });
            }


            function viewAddressListPolice(applicationId) {
                if ($('#addressListViewPoliceModelPopUp').hasClass('in')) {
                    $('#addressListViewPoliceModelPopUp').modal('toggle');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                }
                blockUI();
                $('#addressListViewPoliceModelAppendDiv').html('');
                $('#police_address_edit_div').hide();

                $.get('loadAddressListClearence.action', {applicationId: applicationId}, function (data) {
                    var addressVOList = data.addressVOList;
                    var adrsCount = 0;
                    if (addressVOList.length > 0) {
                        var appendText = "";
                        $.each(addressVOList, function (index, item) {
                            adrsCount = adrsCount + 1;
                            appendText = appendText + '<tr>';
                            appendText = appendText + '<td>' + item.policeArea + '</td>';
                            appendText = appendText + '<td>' + item.address + '</td>';
                            appendText = appendText + '<td>' + item.fromDateStr + '</td>';
                            appendText = appendText + '<td>' + item.toDateStr + '</td>';

                            appendText = appendText + '<td><input type="text" class="form-control police_address_list_comment"  id="address_comment_' + item.addressId + '" name="comment"></td>';

                            appendText = appendText + '<td>';
                            appendText = appendText + '<table><tr>';

                            appendText = appendText + '<td style="vertical-align: middle;"><input style="cursor: pointer;" type="radio" onclick="disableEditAddressButton(' + item.addressId + ',' + applicationId + ')" name="address_clearenceStatus_' + item.addressId + '" value="Y"></td>';
                            appendText = appendText + '<td style="vertical-align: middle;">Y</td>';
                            appendText = appendText + '<td>&nbsp;&nbsp;</td>';
                            appendText = appendText + '<td style="vertical-align: middle;"><input style="cursor: pointer;" type="radio" onclick="enableEditAddressButton(' + item.addressId + ',' + applicationId + ')" name="address_clearenceStatus_' + item.addressId + '" value="N"></td>';
                            appendText = appendText + '<td style="vertical-align: middle;">N</td>';

                            appendText = appendText + '</tr></table>';

                            appendText = appendText + '<td><img src="images/edit.png" style="width:20px;height:20px;cursor: pointer;" id="addressEditButtonPolice_' + item.addressId + '" alt="Edit" title="Edit" class="basic_image_button disabled_image" onclick="javascript:void(0)" /></td>';

                            appendText = appendText + '<td><img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="addressSaveButtonPolice_' + item.addressId + '" alt="Save" title="Save" class="basic_image_button" onclick="saveAddressByPolice(' + item.addressId + ',' + applicationId + ')" /></td>';

                            appendText = appendText + '</tr>';
                        });
                        $('#addressListViewPoliceModelAppendDiv').html(appendText);
                    } else {
                        $('#addressListViewPoliceModelAppendDiv').html('<tr><td colspan="4" align="center">No Address is available for this Application!</td></tr>');
                    }
                    unBlockUI();

                    $('#addressListViewPoliceModelLink').click();


                    if (adrsCount <= 0) {
                        $('#myForm').submit();
                    }

                });
            }

            window.selectedDhaStatus = $('#hiddenAppId').val();

            function viewAddressListPhq(applicationId) {
            	
                if ($('#addressListViewPhqModelPopUp').hasClass('in')) {
                    $('#addressListViewPhqModelPopUp').modal('toggle');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                }
                
                blockUI();
                var aspReviewStatus = '';
                if (window.selectedDhaStatus == applicationId) {
                    aspReviewStatus = $('#aspReviewStatusId_' + applicationId).val();

                } else {
                    aspReviewStatus = window.selectedAspStatus;
                }

                var isAspUser = $('#isASPUserId').val();

                if (isAspUser == 'false') {
                    if (aspReviewStatus == 'not_applicable' || aspReviewStatus == 'approved') {
                        $('#phqAddressEditSave').val('Confirm Changes and Save');

                    } else {
                        $('#phqAddressEditSave').val('Forward to ASP');
                    }
                } else {
                    $('#phqAddressEditSave').val('Confirm Changes and Save');
                }
                $('#addressListViewPhqModelAppendDiv').html('');

                $('#phq_address_edit_div').hide();
                $.get('loadAddressListClearence.action', {
                    applicationId: applicationId,
                    aspReviewStatus: aspReviewStatus
                }, function (data) {
                    var adrsCount = 0;
                    var addressVOList = data.addressVOList;
                    if (addressVOList.length > 0) {
                        var appendText = "";
                        $.each(addressVOList, function (index, item) {
                            adrsCount = adrsCount + 1;

                            if (!(item.addressTempVO == null || item.addressTempVO.length <= 0 || item.addressTempVO.addressTempId <= 0)) {
                                var addColour = '';
                                if (isAspUser == 'false') {
                                    addColour = 'style="background-color:#ACD4F7"';
                                }
                                appendText = appendText + '<tr ' + addColour + '>';
                            } else {
                                var addColour = '';
//                                 if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
//                                     addColour = 'style="background-color:#ACD4F7"';
//                                 }
                                appendText = appendText + '<tr ' + addColour + '>';
                            }


                            appendText = appendText + '<td valign="middle" style="vertical-align:middle">' + item.policeArea + '</td>';
                            appendText = appendText + '<td>' + item.address + '</td>';
                            appendText = appendText + '<td>' + item.fromDateStr + '</td>';
                            appendText = appendText + '<td>' + item.toDateStr + '</td>';

                            if (!(item.addressTempVO == null || item.addressTempVO.length <= 0 || item.addressTempVO.addressTempId <= 0)) {
                                var addComment = '';
                                if (isAspUser == 'false') {
                                    addComment = 'disabled="disabled" readonly="readonly"';
                                }
                                appendText = appendText + '<td>';
                                appendText = appendText + '<input type="text" class="form-control phq_address_list_comment" ' + addComment + '  id="address_comment_phq' + item.addressId + '" name="comment">';
                                appendText = appendText + '</td>';
                            } else {
                                var addComment = '';
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    addComment = 'disabled="disabled" readonly="readonly"';
                                }
                                appendText = appendText + '<td><input type="text" class="form-control phq_address_list_comment" ' + addComment + ' id="address_comment_phq' + item.addressId + '" name="comment" ></td>';
                            }

                            /** -------------------------------------------- Cleared section start-------------------------------------- **/
                            appendText = appendText + '<td>';
                            appendText = appendText + '<table>';
                            appendText = appendText + '<tr>';

                            if (!(item.addressTempVO == null || item.addressTempVO.length <= 0 || item.addressTempVO.addressTempId <= 0)) {

                                var disableStatus = '';
                                if (isAspUser == 'false') {
                                    disableStatus = 'disabled="disabled" readonly="readonly"';
                                }

                                appendText = appendText + '<td style="vertical-align: middle;">';
                                appendText = appendText + '<input style="cursor: pointer;" type="radio" ' + disableStatus + '  name="address_clearenceStatus_phq_' + item.addressId + '" value="Y" id="approveYes_' + item.addressId + '">';
                                appendText = appendText + '</td>';
                            } else {

                                var aspApproval = '';
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    aspApproval = 'disabled="disabled" readonly="readonly"';
                                }
                                appendText = appendText + '<td style="vertical-align: middle;">';
                                appendText = appendText + '<input style="cursor: pointer;" type="radio" ' + aspApproval + ' onclick="disableEditAddressButtonPhq(' + item.addressId + ',' + applicationId + ')" name="address_clearenceStatus_phq_' + item.addressId + '" value="Y" id="approveYes_' + item.addressId + '">';
                                appendText = appendText + '</td>';
                            }

                            appendText = appendText + '<td style="vertical-align: middle;">Y</td>';
                            appendText = appendText + '<td>&nbsp;&nbsp;</td>';

                            if (!(item.addressTempVO == null || item.addressTempVO.length <= 0 || item.addressTempVO.addressTempId <= 0)) {
                                var disableStatus = '';
                                if (isAspUser == 'false') {
                                    disableStatus = 'disabled="disabled" readonly="readonly"';
                                }
                                appendText = appendText + '<td style="vertical-align: middle;">';
                                appendText = appendText + '<input style="cursor: pointer;" type="radio" ' + disableStatus + '  name="address_clearenceStatus_phq_' + item.addressId + '" value="N" id="approveNo_' + item.addressId + '">';
                                appendText = appendText + '</td>';

                            } else {
                                var aspApproval = '';
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    aspApproval = 'disabled="disabled" readonly="readonly"';
                                }
                                appendText = appendText + '<td style="vertical-align: middle;">';
                                appendText = appendText + '<input style="cursor: pointer;" type="radio" ' + aspApproval + ' onclick="enableEditAddressButtonPhq(' + item.addressId + ',' + applicationId + ')" name="address_clearenceStatus_phq_' + item.addressId + '" value="N" id="approveNo_' + item.addressId + '">';
                                appendText = appendText + '</td>';
                            }

                            appendText = appendText + '<td style="vertical-align: middle;">N</td>';
                            appendText = appendText + '</tr>';
                            appendText = appendText + '</table>';
                            appendText = appendText + '</td>';

                            /** -------------------------------------------- Cleared section end-------------------------------------- **/

                            if (!(item.addressTempVO == null || item.addressTempVO.length <= 0 || item.addressTempVO.addressTempId <= 0)) {

                                appendText = appendText + '<td><img src="images/edit.png" style="width:20px;height:20px;cursor: pointer;" id="addressEditButtonPhq_' + item.addressId + '" alt="Edit" title="Edit" class="basic_image_button" onclick="ediAddressByPhq(' + item.addressId + ',' + applicationId + ')" /></td>';

                                var aspApproval = '';
                                var jsFunction = '';
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    aspApproval = 'disabled_image';
                                    jsFunction = 'onclick="javascript:void(0)"'
                                } else {
                                    jsFunction = 'onclick="saveAddressByPhq(' + item.addressId + ',' + applicationId + ')"';
                                }
                                appendText = appendText + '<td><img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="addressSaveButtonPhq_' + item.addressId + '" alt="Save" title="Save" class="basic_image_button ' + aspApproval + '" ' + jsFunction + ' /></td>';
                            } else {
                                var aspApproval = 'disabled_image';
                                var jsFunction = '';
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    aspApproval = '';
                                    jsFunction = 'onclick="ediAddressByPhq(' + item.addressId + ',' + applicationId + ')"'
                                } else {
                                    aspApproval = '';
                                    jsFunction = 'onclick="ediAddressByPhq(' + item.addressId + ',' + applicationId + ')"';
                                }
                                appendText = appendText + '<td><img src="images/edit.png" style="width:20px;height:20px;cursor: pointer;" id="addressEditButtonPhq_' + item.addressId + '" alt="Edit" title="Edit" class="basic_image_button ' + aspApproval + '" ' + jsFunction + ' /></td>';

                                var saveAspApproval = '';
                                var saveJsFunction = 'onclick="saveAddressByPhq(' + item.addressId + ',' + applicationId + ')"'
                                if (isAspUser == 'false' && (aspReviewStatus == 'not_approved' || aspReviewStatus == 'applicable')) {
                                    saveAspApproval = 'disabled_image';
                                    saveJsFunction = 'onclick="javascript:void(0)"';
                                }

                                appendText = appendText + '<td><img src="images/save.png" style="width:20px;height:20px;cursor: pointer;" id="addressSaveButtonPhq_' + item.addressId + '" alt="Save" title="Save" class="basic_image_button ' + saveAspApproval + '" ' + saveJsFunction + ' /></td>';
                            }

                            appendText = appendText + '</tr>';
                        });

                        $('#addressListViewPhqModelAppendDiv').html(appendText);
                    } else {
                        $('#addressListViewPhqModelAppendDiv').html('<tr><td colspan="4" align="center">No Address is available for this Application!</td></tr>');
                    }
                    unBlockUI();
                    $('#addressEditPhqSendEmail').attr('checked', false);
                    $('#addressListViewPhqModelLink').click();
                    if (adrsCount <= 0) {
                        $('#myForm').submit();
                    }

                });

                if (isAspUser == 'true' && aspReviewStatus == 'not_approved') {
                    $('#searchReviewApplication').click();
                }else if(window.isEmailSend){
                    $('#searchReviewApplication').click();
                }
            }

            function loadLargeComment(applicationId) {
                $('#largeCommentApplicationId').val(applicationId);
                $('#commentViewModellLink').click();
            }

            function saveRow() {
                var applicationId = $('#largeCommentApplicationId').val();
                var ans = confirm('Are you sure you want to save this record?');
                if (ans) {
                    $('#applicationId_clearence').val(applicationId);
                    var comment = $.trim($('#largeComment').val());
                    var inputName = 'clearenceStatus_' + applicationId;
                    var clrStatus = $("input:radio[name=" + inputName + "]:checked").val();
                    if (clrStatus == 'Y') {
                        $('#comment_clearence').val(comment);
                        $('#clearenceStatus_clearence').val(clrStatus);
                        $('#clearenceUpdateForm').submit();
                    } else if (clrStatus == 'N') {
                        if (!(comment == null || comment == '')) {
                            $('#comment_clearence').val(comment);
                            $('#clearenceStatus_clearence').val(clrStatus);
                            $('#clearenceUpdateForm').submit();
                        } else {
                            alert('Please enter a comment stating the reason for not to clear the application!');
                        }
                    } else {
                        alert('Please select if the application is cleared or not!');
                    }
                }
            }

            function loadSaveAdverseIssueType(applicationId) {
                $('#applicationId_clearence').val(applicationId);
                var comment = $.trim($('#comment_' + applicationId).val());
                var inputName = 'clearenceStatus_' + applicationId;
                var clrStatus = $("input:radio[name=" + inputName + "]:checked").val();
                if (clrStatus == 'Y') {
                    $('#comment_clearence').val(comment);
                    $('#clearenceStatus_clearence').val(clrStatus);
                    $('#clearenceUpdateForm').submit();

                } else if (clrStatus == 'N') {
                    $('#adverseIssueTypeApplicationId').val(applicationId);
                    if (!(comment == null || comment == '')) {
                        $('#adverseIssueTypeComment').val(comment);
                    }

                    $('#adverseIssueTypeSave').unbind('click');
                    $('#adverseIssueTypeSave').removeAttr('onclick');
                    $('#adverseIssueTypeSave').click(function () {
                        saveAdverseIssueType(applicationId);
                    })

                    $('#adverseIssueTypeCancel').unbind('click');
                    $('#adverseIssueTypeCancel').removeAttr('onclick');
                    $('#adverseIssueTypeCancel').click(function () {
                        $('#adverseIssueTypeModellLink').click();
                    })

                    $('#adverseIssueTypeModellLink').click();

                } else {
                    alert('Please select if the application is cleared or not!');
                }

            }

            function saveAdverseIssueType(applicationId) {
                var ans = confirm('Are you sure you want to save this record?');
                if (ans) {
                    $('#applicationId_clearence_adverse').val(applicationId);
                    var comment = $.trim($('#adverseIssueTypeComment').val());
                    var adverseType = $("input:radio[name=adverseIssueType]:checked").val();
                    if (!(comment == null || comment == '')) {
                        if (!(adverseType == null || adverseType == '')) {
                            $('#comment_clearence_adverse').val(comment);
                            $('#clearenceStatus_clearence_adverse').val('N');
                            $('#adverseType_clearence_adverse').val(adverseType);
                            $('#clearenceUpdateFormAdverse').submit();
                        } else {
                            alert('Please select the adverse type!');
                        }
                    } else {
                        alert('Please enter a comment stating the reason for not to clear the application!');
                    }
                }
            }

            function editRow(applicationId) {
                blockUI();
                //var ans=confirm('Starting edit process will lock this record for other users. Are you sure you want start editing this record?');
                //if(ans){
                $.get('checkAndLockCooRecord', {applicationId: applicationId}, function (data) {
                    var recordLockstatus = data.recordLockstatus;
                    var lockedUserName = data.lockedUserName;
                    var lockedMessage = data.lockedMessage;
                    if (recordLockstatus == 'NO_RECORDS_TO_LOCK') {
                        alert('Internal Error!');
                    } else if (recordLockstatus == 'RECORD_IS_LOCKED_BY_ANOTHER_USER') {
                        alert('Sorry, this record is already locked by ' + lockedUserName + '!');
                    } else if (recordLockstatus == 'ONE_RECORD_IS_ALREADY_LOCKED') {
                        alert(lockedMessage);
                    } else if (recordLockstatus == 'ERROR') {
                        alert('Internal Error!');
                    } else if (recordLockstatus == 'SUCCESS') {
                        unLockCooRow(applicationId);
                        var referenceNumber = $.trim($('#hiddenReferenceNumber_' + applicationId).val());
                        $('#referenceNo').val(referenceNumber);
                        $('#myForm').submit();
                    }
                    unBlockUI();
                });
                //}
            }

            function lockCooRow(applicationId) {
                var inputName = 'clearenceStatus_' + applicationId;
                $("input:radio[name=" + inputName + "]").each(function () {
                    $(this).attr('disabled', 'disabled');
                    $(this).attr('readonly', 'readonly');
                });

                $('#comment_' + applicationId).attr('disabled', 'disabled');
                $('#comment_' + applicationId).attr('readonly', 'readonly');

                $("#lockUnlockBtn_" + applicationId).attr('src', 'images/unlock.png');
                $("#lockUnlockBtn_" + applicationId).removeAttr('onclick');
                $("#lockUnlockBtn_" + applicationId).unbind('click');
                $("#lockUnlockBtn_" + applicationId).click(function () {
                    editRow(applicationId);
                })

                if ($('#view_link_phq_' + applicationId).length) {
                    $('#view_link_phq_' + applicationId).attr('disabled', 'disabled');
                    $("#view_link_phq_" + applicationId).unbind('click');
                    $("#view_link_phq_" + applicationId).removeAttr('onclick');
                }

                if ($('#update_pol_message_link_phq_' + applicationId).length) {
                    $('#update_pol_message_link_phq_' + applicationId).attr('disabled', 'disabled');
                    $("#update_pol_message_link_phq_" + applicationId).unbind('click');
                    $("#update_pol_message_link_phq_" + applicationId).removeAttr('onclick');
                }

                if ($('#view_link_pol_' + applicationId).length) {
                    $('#view_link_pol_' + applicationId).attr('disabled', 'disabled');
                    $("#view_link_pol_" + applicationId).unbind('click');
                    $("#view_link_pol_" + applicationId).removeAttr('onclick');
                }

                $('#save_image_' + applicationId).addClass('disabled_image');
                $('#save_image_' + applicationId).unbind('click');

            }

            function unLockCooRow(applicationId) {
                var inputName = 'clearenceStatus_' + applicationId;
                $("input:radio[name=" + inputName + "]").each(function () {
                    $(this).attr('disabled', false);
                    $(this).attr('readonly', false);
                });

                $('#comment_' + applicationId).attr('disabled', false);
                $('#comment_' + applicationId).attr('readonly', false);

                $("#lockUnlockBtn_" + applicationId).attr('src', 'images/lock.png');
                $("#lockUnlockBtn_" + applicationId).removeAttr('onclick');
                $("#lockUnlockBtn_" + applicationId).unbind('click');

                $("#lockUnlockBtn_" + applicationId).click(function () {
                    cancelEdit(applicationId);
                })

                if ($('#view_link_phq_' + applicationId).length) {
                    $('#view_link_phq_' + applicationId).attr('disabled', false);
                    $("#view_link_phq_" + applicationId).click(function () {
                        viewAddressListPhq(applicationId);
                    });
                }

                if ($('#update_pol_message_link_phq_' + applicationId).length) {
                    $('#update_pol_message_link_phq_' + applicationId).attr('disabled', false);
                    $("#update_pol_message_link_phq_" + applicationId).click(function () {
                        window.location.href = "updatePoliceMessage.action?applicationId=" + applicationId;
                    });
                }

                if ($('#view_link_pol_' + applicationId).length) {
                    $('#view_link_pol_' + applicationId).attr('disabled', false);
                    $("#view_link_pol_" + applicationId).click(function () {
                        viewAddressListPolice(applicationId);
                    });
                }

                $('#save_image_' + applicationId).removeClass('disabled_image');
                $('#save_image_' + applicationId).click(function () {
                    loadLargeComment(applicationId);
                });

            }

            function cancelEdit(applicationId) {
                blockUI();
                // var ans=confirm('Cancelling edit process will release this record. Are you sure you want quit editing this record?');
                // if(ans){
                $.get('checkAndRemoveLock', {applicationId: applicationId}, function (data) {
                    var recordLockstatus = data.recordLockstatus;
                    if (recordLockstatus == 'SUCCESS') {
                        lockCooRow(applicationId);
                        $('#referenceNo').val('');
                        $('#myForm').submit();
                    }
                    unBlockUI();
                });
                //}
            }


            function cancelEditAdverse(applicationId) {
                blockUI();
                // var ans=confirm('Cancelling edit process will release this record. Are you sure you want quit editing this record?');
                // if(ans){
                $.get('checkAndRemoveLock', {applicationId: applicationId}, function (data) {
                    var recordLockstatus = data.recordLockstatus;
                    if (recordLockstatus == 'SUCCESS') {
                        lockCooRowAdverse(applicationId);
                        $('#referenceNo').val('');
                        $('#myForm').submit();
                    }
                    unBlockUI();
                });
                //}
            }

            function lockCooRowAdverse(applicationId) {
                var inputName = 'clearenceStatus_' + applicationId;
                $("input:radio[name=" + inputName + "]").each(function () {
                    $(this).attr('disabled', 'disabled');
                    $(this).attr('readonly', 'readonly');
                });

                $('#comment_' + applicationId).attr('disabled', 'disabled');
                $('#comment_' + applicationId).attr('readonly', 'readonly');

                $("#lockUnlockBtn_" + applicationId).attr('src', 'images/unlock.png');
                $("#lockUnlockBtn_" + applicationId).removeAttr('onclick');
                $("#lockUnlockBtn_" + applicationId).unbind('click');
                $("#lockUnlockBtn_" + applicationId).click(function () {
                    editRowAdverse(applicationId);
                })

                $('#save_image_' + applicationId).addClass('disabled_image');
                $('#save_image_' + applicationId).unbind('click');
            }

            function editRowAdverse(applicationId) {
                blockUI();
                //var ans=confirm('Starting edit process will lock this record for other users. Are you sure you want start editing this record?');
                //if(ans){
                $.get('checkAndLockCooRecord', {applicationId: applicationId}, function (data) {
                    var recordLockstatus = data.recordLockstatus;
                    var lockedUserName = data.lockedUserName;
                    if (recordLockstatus == 'NO_RECORDS_TO_LOCK') {
                        alert('Internal Error!');
                    } else if (recordLockstatus == 'RECORD_IS_LOCKED_BY_ANOTHER_USER') {
                        alert('Sorry, this record is already locked by ' + lockedUserName + '!');
                    } else if (recordLockstatus == 'ONE_RECORD_IS_ALREADY_LOCKED') {
                        alert('Sorry, you have already locked another record!');
                    } else if (recordLockstatus == 'ERROR') {
                        alert('Internal Error!');
                    } else if (recordLockstatus == 'SUCCESS') {
                        unLockCooRowAdverse(applicationId);
                        var referenceNumber = $.trim($('#hiddenReferenceNumber_' + applicationId).val());
                        $('#referenceNo').val(referenceNumber);
                        $('#myForm').submit();
                    }
                    unBlockUI();


                });
                //}
            }

            function unLockCooRowAdverse(applicationId) {
                var inputName = 'clearenceStatus_' + applicationId;
                $("input:radio[name=" + inputName + "]").each(function () {
                    $(this).attr('disabled', false);
                    $(this).attr('readonly', false);
                });

                $('#comment_' + applicationId).attr('disabled', false);
                $('#comment_' + applicationId).attr('readonly', false);

                $("#lockUnlockBtn_" + applicationId).attr('src', 'images/lock.png');
                $("#lockUnlockBtn_" + applicationId).removeAttr('onclick');
                $("#lockUnlockBtn_" + applicationId).unbind('click');

                $("#lockUnlockBtn_" + applicationId).click(function () {
                    cancelEditAdverse(applicationId);
                })

                $('#save_image_' + applicationId).removeClass('disabled_image');
                $('#save_image_' + applicationId).click(function () {
                    loadSaveAdverseIssueType(applicationId);
                });

            }


            function setLimit(value) {
                blockUI();
                $('#limit').val(value);
                $('#maxId').val(0);
                $('#myForm').submit();
            }

            function goToSelectedPage(maxId, limit, pageNo) {
                blockUI();
                $('#maxId').val(maxId);
                $('#limit').val(limit);
                $('#currentPage').val(pageNo);
                $('#myForm').submit();
            }

            function validateForm() {
                blockUI();
                return true;
            }

            function viewNicPopup(applicationId) {
                $('#nicImge').attr('src', 'images/preloader_large.gif');
                $('#nicImgeBack').attr('src', 'images/preloader_large.gif');
                blockUI();
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
                unBlockUI();
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
                $('#nicImge').attr('src', 'images/preloader_large.gif');

                blockUI();
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
                unBlockUI();
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
                blockUI();
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

                $('#newNicNumberAppend').html(pptNo);

                initImageNewNic();

                $('#newNicViewModellLink').click();
                unBlockUI();
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
                blockUI();

                $('#passportImge').attr('src', 'images/preloader_large.gif');
                $('#passportImgeBack').attr('src', 'images/preloader_large.gif');

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
                unBlockUI();
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

            function viewBcPopup(applicationId) {
                blockUI();

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
                unBlockUI();
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

        </script>
    </body>
    </html>
</s:i18n>