<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:i18n name="lk.icta.resources.global">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

            <jsp:include page="../common/header.jsp"/>

        <div id="es-content">
            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Application</c:set>
            <jsp:include page="../common/commonPage.jsp">
                <jsp:param name="title" value="${pageTitle}"/>
            </jsp:include>


            <p>If you need to get an extended clearance certificate for a previously approved one, then please select
                renewal. The clearance period has to be same
                as the previous certificate. A new certificate for the same period but for a different country also can
                be obtained through the renewal. Application charges are
                applicable.<br>
                An application can be renewed within one year from the date the clearance certificate was issued.
            </p>

            <s:form theme="simple" class="form-horizontal" id="previousApplicationReferenceForm" role="form"
                    method="post" action="loadPreviousApplication.action" onsubmit="return validateReApplyForm()">
            <div class="middle_content">

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="applicationType" class="control-label"><b>Application Type</b></label>
                        </div>
                        <div class="col-sm-5" style="width:49.7%">
                            <c:choose>
                                <c:when test="${reApplyStatus == 1}">
                                    <select id="applicationTypeSelect" class="form-control"
                                            onchange="previousAppRefNoForm();">
                                        <option value="new">New Application</option>
                                        <option value="re" selected="selected">Renewal</option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select id="applicationTypeSelect" class="form-control"
                                            onchange="previousAppRefNoForm();">
                                        <option value="new">New Application</option>
                                        <option value="re">Renewal</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear: both;"></div>

                <div id="previousReferenceForm" style="display: none;">
                    <br/><br/>
                    <div class="col-lg-10">
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label for="previousReferenceNo" class="control-label"><b>Previous Application
                                    Reference
                                    No.</b></label>
                            </div>
                            <div class="col-sm-3">
                                <s:textfield name="applicationVO.previousReferenceNo" id="previousReferenceNo"
                                             cssClass="form-control" autocomplete="off"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="submit" class="btn btn-primary es-buttton" value="Load"/>
                            </div>
                        </div>
                    </div>
                    <div style="clear: both;"></div>
                </div>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

            </div>
            </s:form>

            <div class="middle_content">

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="nationalitySelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Nationality:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${applicationVO.nationalityId != null}">
                                    <select name="applicationVO.nationalityId" id="nationalitySelect"
                                            class="form-control" onchange="wereYouCitizenOfSriLankaShowHide();">
                                        <option value="0">Please select</option>
                                        <c:forEach var="country" items="${countryList}">
                                            <c:choose>
                                                <c:when test="${applicationVO.nationalityId == country.id}">
                                                    <option value="${country.id}"
                                                            selected="selected">${country.nationality}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${country.id}">${country.nationality}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.nationalityId" id="nationalitySelect"
                                            class="form-control" onchange="wereYouCitizenOfSriLankaShowHide();">
                                        <option value="0">Please select</option>
                                        <c:forEach var="country" items="${countryList}">
                                            <option value="${country.id}">${country.nationality}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <div id="sriLankaCitizenDiv">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label for="citizenOfSriLankaSelect" class="control-label"><b>Were you a citizen of Sri
                                    Lanka?</b></label>
                            </div>
                            <div class="col-sm-5" style="width: 49.7%">
                                <c:choose>
                                    <c:when test="${applicationVO.citizenOfSriLanka != null}">
                                        <select name="applicationVO.citizenOfSriLanka" id="citizenOfSriLankaSelect"
                                                class="form-control" onchange="changeStatuscCtizenOfSriLanka();">
                                            <c:choose>
                                                <c:when test="${applicationVO.citizenOfSriLanka == 1}">
                                                    <option value="1" selected="selected">Yes</option>
                                                    <option value="0">No</option>
                                                </c:when>
                                                <c:when test="${applicationVO.citizenOfSriLanka == 0}">
                                                    <option value="1">Yes</option>
                                                    <option value="0" selected="selected">No</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="applicationVO.citizenOfSriLanka" id="citizenOfSriLankaSelect"
                                                class="form-control" onchange="changeStatuscCtizenOfSriLanka();">
                                            <option value="1">Yes</option>
                                            <option value="0">No</option>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="dateOfcitizenshipObtained" class="control-label"><b>If Yes, the date
                                    citizenship obtained from another country</b></label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield name="dateOfCitizenshipObtained" id="dateOfcitizenshipObtained"
                                             cssClass="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="dateOfBirth" class="control-label"><b><span class="mandatory_field">*</span>Date
                                Of Birth</b></label>
                        </div>
                        <div class="col-sm-6">
                            <s:textfield name="dateOfBirth" id="dateOfBirth" cssClass="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="applicantAge" class="control-label"><b><span class="mandatory_field">*</span>Age
                                in years:</b></label>
                        </div>
                        <div class="col-sm-4">
                            <s:textfield name="applicationVO.age" id="applicantAge" cssClass="form-control"/>
                        </div>
                    </div>
                </div>

                <div style="clear:both;"></div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="nicValidate" class="control-label"><b><span
                                    class="mandatory_field">*</span>Did you leave Sri Lanka before the age 16 ?</b>
                            </label>
                        </div>
                        <div class="col-sm-6">
                            <select name="isLeftSriLanka" id="nicValidate"
                                    class="form-control" onchange="validateCitizenOfUser();">
                                <c:choose>
                                    <c:when test="${isLeftSriLanka  == '1'}">
                                        <option value="1" selected="selected">Yes</option>
                                        <option value="0">No</option>
                                    </c:when>
                                    <c:when test="${isLeftSriLanka == '0'}">
                                        <option value="1">Yes</option>
                                        <option value="0" selected="selected">No</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">No</option>
                                        <option value="1">Yes</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>

                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>
            </div>

            <s:form theme="simple" class="form-horizontal" id="applicationVerificationForm" role="form" method="post"
                    action="verifyApplication.action" onsubmit="return validateVerificationForm();">

                <s:hidden name="applicationVO.nationalityId" id="nationalityIdVerify"/>
                <s:hidden name="applicationVO.nationality" id="nationalityVerify"/>
            <input type="hidden" name="countryId" id="countryIdVerify"/>

                <s:hidden name="applicationVO.countryName" id="countryVerify"/>
                <s:hidden name="applicationVO.citizenOfSriLanka" id="citizenOfSriLankaVerify"/>
                <s:hidden name="dateOfCitizenshipObtained" id="citizenshipObtainedDateVerify"/>
                <s:hidden name="dateOfBirth" id="dateOfBirthVerify"/>
                <s:hidden name="isLeftSriLanka" id="isLeftSriLankaVerify"/>

                <s:hidden name="applicationVO.age" id="ageVerify"/>
            <input type="hidden" name="applicationReferenceNoValue" id="applicationReferenceNoValue"
                   value="${applicationVO.referenceNo}"/>

            <div class="middle_content">
                <div class="col-lg-6"></div>
                <div class="col-lg-6">
                    <div style="float:center;">
                        <div class="form-group">
                            <div class="col-sm-5" style="width:33.1%">
                                <label for="referenceNo" class="control-label"><b>Reference No.</b></label>
                            </div>
                            <div class="col-sm-6">
                                <s:textfield name="applicationVO.referenceNo" id="referenceNo"
                                             cssClass="form-control"
                                             readonly="true" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <p>Application with incorrect NIC Number and Passport Number will be rejected.</p>
                <p>Please have your NIC and Passport scanned before you start filling in your application. Enter
                    your
                    NIC number and Passport number to verify
                    if an application is already in process under your name.</p>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="nicNo" class="control-label"><b>NIC No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <s:textfield name="applicationVO.nic" id="nicNo" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="passportNo" class="control-label"><b><span class="mandatory_field">*</span>Passport
                                No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <s:textfield name="applicationVO.passport" id="passportNo" cssClass="form-control"
                                         autocomplete="off"/>

                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="confirmNicNo" class="control-label"><b>Confirm NIC No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <input type="text" name="confirmNic" id="confirmNicNo" class="form-control"
                                   value="${applicationVO.nic}" autocomplete="off"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="confirmPassportNo" class="control-label"><b><span
                                    class="mandatory_field">*</span>Confirm Passport No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <input type="text" name="confirmPassport" id="confirmPassportNo" class="form-control"
                                   value="${applicationVO.passport}" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="newNicNo" class="control-label"><b>New NIC No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <s:textfield name="applicationVO.newNic" id="newNicNo" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="countryVerifySelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Country:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${applicationVO.countryId != null}">
                                    <select name="applicationVO.countryId" id="countrySelect" class="form-control"
                                            onchange="getCommissioners();">
                                        <option value="0">Please select</option>
                                        <c:forEach var="country" items="${countryList}">
                                            <c:choose>
                                                <c:when test="${applicationVO.countryId == country.id}">
                                                    <option value="${country.id}"
                                                            selected="selected">${country.countryName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${country.id}">${country.countryName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.countryId" id="countrySelect" class="form-control"
                                            onchange="getCommissioners();">
                                        <option value="0">Please select</option>
                                        <c:forEach var="country" items="${countryList}">
                                            <option value="${country.id}">${country.countryName}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="confirmNewNicNo" class="control-label"><b> Confirm New NIC No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <input type="text" name="confirmNewNic" id="confirmNewNicNo" class="form-control"
                                   value="${applicationVO.newNic}" autocomplete="off"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="countryVerifySelect" class="control-label"><b>Reference high
                                commission/embassy/consulate:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${highCommissionList != null}">
                                    <select name="applicationVO.highCommisionReferenceId"
                                            id="highCommissionVerifySelect" class="form-control"
                                            onchange="getSelectedCommissioner();">

                                        <c:forEach var="commissioner" items="${highCommissionList}">
                                            <c:choose>
                                                <c:when test="${applicationVO.highCommisionReferenceId == commissioner.id}">
                                                    <option value="${commissioner.id}"
                                                            selected="selected">${commissioner.commissionEmbassyConsultantName}
                                                        - ${commissioner.commissionEmbassyConsultantAddress}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${commissioner.id}">${commissioner.commissionEmbassyConsultantName}
                                                        - ${commissioner.commissionEmbassyConsultantAddress}</option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${applicationVO.highCommisionReferenceId == 0}">
                                                <option value="0" selected="selected">Other</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0">Other</option>
                                            </c:otherwise>
                                        </c:choose>

                                            <%--<c:forEach var="commissioner" items="${highCommissionList}">--%>
                                            <%--<c:choose>--%>
                                            <%--<c:when test="${applicationVO.highCommisionReferenceId == commissioner.id}">--%>
                                            <%--<option value="${commissioner.id}"--%>
                                            <%--selected="selected">${commissioner.commissionEmbassyConsultantName}--%>
                                            <%--- ${commissioner.commissionEmbassyConsultantAddress}</option>--%>
                                            <%--<option value="0">Other</option>--%>
                                            <%--</c:when>--%>
                                            <%--<c:otherwise>--%>
                                            <%--<option value="${commissioner.id}">${commissioner.commissionEmbassyConsultantName}--%>
                                            <%--- ${commissioner.commissionEmbassyConsultantAddress}</option>--%>
                                            <%--<option value="0">Other</option>--%>
                                            <%--</c:otherwise>--%>
                                            <%--</c:choose>--%>
                                            <%--</c:forEach>--%>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.highCommisionReferenceId"
                                            id="highCommissionVerifySelect" class="form-control"
                                            onchange="getSelectedCommissioner();">
                                        <!-- need to update dynamically -->
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <!-- 	   					<s:hidden name="applicationVO.highCommisionReference" id="highCommissionVerifySelectedValue" /> -->
                </div>

                <div id="highCommissionerOtherDetailDiv">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <label for="certificateIndicationAddress" class="control-label"><b><span
                                        class="mandatory_field">*</span>High Commission/Embassy/Consulate Name
                                    (Adressee
                                    & the Name of the Authority):</b></label>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>

                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <p><i>"e.g. H. E. THE HIGH COMMISSIONER, SRI LANKAN HIGH COMMISSION"</i></p>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>

                    <div class="col-lg-12">
                        <div class="form-group">
                            <s:textfield name="applicationVO.highCommisionReference" id="highCommisionReferenceName"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                    <div style="clear:both;"></div>

                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <label for="certificateIndicationAddress" class="control-label"><b><span
                                        class="mandatory_field">*</span>Indicate address of the High
                                    Commission/Embassy/Consulate to which
                                    the certificate should be addressed to:</b></label>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>

                    <div class="col-lg-12">
                        <div class="form-group">
                            <s:textfield name="applicationVO.highCommisionReferenceAddress"
                                         id="highCommisionReferenceAddress" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                </div>

                <div style="clear:both;"></div>
                <div class="col-lg-12" id="verifyButtonDiv">
                    <div style="float: right;">
                        <input type="submit" id="verifySubmitButton" class="btn btn-primary es-buttton"
                               value="Verify"/>
                    </div>
                </div>
                <div style="clear:both;"></div>

            </div>

            </s:form>

            <div id="verifyMessage">You can submit another application for the same country only when your previous
                application is processed.
            </div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <s:form theme="simple" class="form-horizontal" id="applicationRegistrationForm" role="form" method="post"
                    action="saveApplication.action" onsubmit="return validateApplicationForm()">

                <s:hidden name="applicationVO.nationalityId" id="nationalityId"/>
                <s:hidden name="applicationVO.nationality" id="nationality"/>
                <s:hidden name="applicationVO.citizenOfSriLanka" id="citizenOfSriLanka"/>
                <s:hidden name="dateOfCitizenshipObtained" id="citizenshipObtainedDate"/>
                <s:hidden name="dateOfBirth" id="dateOfBirth"/>

                <s:hidden name="applicationVO.age" id="age"/>

                <s:hidden name="applicationVO.previousReferenceNo" id="previousReferenceNoValue"/>
                <s:hidden name="applicationVO.referenceNo" id="referenceNo"/>
                <s:hidden name="applicationVO.nic" id="nic"/>
                <s:hidden name="applicationVO.newNic" id="newNic"/>
                <s:hidden name="applicationVO.passport" id="passport"/>
                <s:hidden name="applicationVO.countryId" id="countryId"/>
                <s:hidden name="applicationVO.countryName" id="countryName"/>
                <s:hidden name="applicationVO.highCommisionReferenceId" id="highCommisionReferenceId"/>
                <s:hidden name="applicationVO.highCommisionReference" id="highCommisionReference"/>
                <s:hidden name="applicationVO.highCommisionReferenceAddress" id="highCommisionReferenceAddressHidden"/>

            <input type="hidden" id="verificationStatus" name="verificationStatus" value="${verificationStatus}"/>
            <input type="hidden" id="reapplyStatus" name="reApplyStatus" value="${reApplyStatus}"/>

            <div class="middle_content">

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="nameInFullAsNic" class="control-label"><b>Applicant's Name in full as in the
                                current NIC:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="applicationVO.applicantNameAsNic" id="nameInFullAsNic"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6" style="width: 51.3%">
                            <label for="sexSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Sex:</b></label>
                        </div>
                        <div class="col-sm-5" style="width:48.7%">
                            <c:choose>
                                <c:when test="${applicationVO.sex != null}">
                                    <select name="applicationVO.sex" id="sexSelect" class="form-control">
                                        <c:choose>
                                            <c:when test="${applicationVO.sex == 'M'}">
                                                <option value="M" selected="selected">Male</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="M">Male</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${applicationVO.sex == 'F'}">
                                                <option value="F" selected="selected">Female</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="F">Female</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.sex" id="sexSelect" class="form-control">
                                        <option value="M">Male</option>
                                        <option value="F">Female</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="statusSelect" class="control-label"><b><span class="mandatory_field">*</span>Status</b></label>
                        </div>
                        <div class="col-sm-4">
                            <c:choose>
                                <c:when test="${applicationVO.applicantStatus != null}">
                                    <select name="applicationVO.applicantStatus" id="statusSelect" class="form-control">
                                        <c:forEach var="applicantStatus" items="${applicantStatus}">
                                            <c:choose>
                                                <c:when test="${applicationVO.applicantStatus == applicantStatus.value}">
                                                    <option value="${applicantStatus.value}"
                                                            selected="selected">${applicantStatus.value.displayName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${applicantStatus.value}">${applicantStatus.value.displayName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.applicantStatus" id="statusSelect" class="form-control">
                                        <c:forEach var="applicantStatus" items="${applicantStatus}">
                                            <option value="${applicantStatus.value}">${applicantStatus.value.displayName}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>


                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="highCommisionReference" class="control-label"><b><span
                                    class="mandatory_field">*</span>Applicant's Name in full as in the
                                Passport:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="applicationVO.applicantNameAsPassport" id="nameInFullAsPassport"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="highCommisionReference" class="control-label"><b><span
                                    class="mandatory_field">*</span>Which name should be printed in certificate?
                                :</b></label>
                        </div>
                        <c:choose>
                            <c:when test="${empty applicationVO.selectedNameOption || applicationVO.selectedNameOption  == '' }">
                                <div class="col-sm-3" id="slSelection">
                                    <select name="applicationVO.selectedNameOption" id="selectedName"
                                            class="form-control"
                                            onchange="isWantAffidavit();">
                                        <option value="none">Select One</option>
                                        <option value="nic">NIC</option>
                                        <option value="passport">Passport</option>
                                    </select>

                                </div>
                                <div class="col-sm-3" id="forieginSelection">
                                    <select name="applicationVO.selectedNameOption" class="form-control">
                                        <option value="passport">Passport</option>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${applicationVO.selectedNameOption == 'nic'}">
                                            <select name="applicationVO.selectedNameOption" class="form-control">
                                                <option value="nic">NIC</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${applicationVO.selectedNameOption == 'passport'}">
                                            <select name="applicationVO.selectedNameOption" class="form-control">
                                                <option value="passport">Passport</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-3">
                        <label for="passportIssueDate" class="control-label"><b><span
                                class="mandatory_field">*</span>Passport Issue Date:</b></label>
                    </div>
                    <div class="col-sm-3">
                        <s:textfield name="passportIssueDate" id="passportIssueDate" cssClass="form-control"/>
                    </div>
                </div>
                <c:set var="passportIssueDateHiddenFieldVal"><fmt:formatDate
                        value="${applicationVO.passportIssueDate}" pattern="yyyy,MM,dd"/></c:set>
                <input type="hidden" id="passportIssueDateHiddenField" value="${passportIssueDateHiddenFieldVal}"/>
            </div>
            <div style="clear:both;"></div>
            <br/>
            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-3">
                        <label for="presentAddressLanka" class="control-label"><b>Present Address in Sri
                            Lanka:</b></label>
                    </div>
                    <div class="col-sm-9">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="addressLineOneCopy" class="control-label"><b><span
                                            class="mandatory_field">*</span>Address Line 1:</b></label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" onkeyup="constructPresentAddressInSriLanka()"
                                           id="addressLineOneCopy" class="form-control" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="addressLineTwoCopy" class="control-label"><b>Address Line
                                        2:</b></label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" onkeyup="constructPresentAddressInSriLanka()"
                                           id="addressLineTwoCopy" class="form-control" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="cityCopy" class="control-label"><b><span
                                            class="mandatory_field">*</span>City</b></label>
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" onkeyup="constructPresentAddressInSriLanka()" id="cityCopy"
                                           class="form-control" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="stateCopy"
                                           class="control-label"><b>State/Provice/Region:</b></label>
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" onkeyup="constructPresentAddressInSriLanka()" id="stateCopy"
                                           class="form-control" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postalCopy" class="control-label"><b>Postal/ZIP Code:</b></label>
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" onkeyup="constructPresentAddressInSriLanka()" id="postalCopy"
                                           class="form-control" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postalCountrySelectCopy" class="control-label"><b><span
                                            class="mandatory_field">*</span>Country:</b></label>
                                </div>
                                <div class="col-sm-7">
                                    <select id="postalCountrySelectCopy" class="form-control"
                                            onchange="constructPresentAddressInSriLanka()">
                                        <option value="0">Please select</option>
                                        <c:forEach var="country" items="${countryList}">
                                            <option value="${country.id}">${country.countryName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" id="postalCountrySelectNameCopy" value=""/>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <s:textfield name="applicationVO.presentAddressLocal" readonly="true"
                                     id="presentAddressLanka" cssClass="form-control" autocomplete="off"/>


                    </div>
                </div>
            </div>

            <div style="clear:both;"></div>
            <br/>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-3">
                        <label for="presentAddressOverseas" class="control-label"><b>Present Address
                            (Overseas):</b></label>
                    </div>
                    <div class="col-sm-9">
                        <s:textfield name="applicationVO.presentAddressOverseas" id="presentAddressOverseas"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>

            <div style="clear:both;"></div>
            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-3">
                        <label for="occupation" class="control-label"><b>Occupation</b></label>
                    </div>
                    <div class="col-sm-9">
                        <s:textfield name="applicationVO.occupation" id="occupation" cssClass="form-control"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-6" style="width:51.2%">
                        <label for="purposeSelect" class="control-label"><b><span class="mandatory_field">*</span>Purpose</b></label>
                    </div>
                    <div class="col-sm-5">
                        <c:choose>
                            <c:when test="${applicationVO.purpose != null}">
                                <select name="applicationVO.purpose" id="purposeSelect" class="form-control">
                                    <c:forEach var="applicationPurpose" items="${applicationPurposeMap}">
                                        <c:choose>
                                            <c:when test="${applicationVO.purpose == applicationPurpose.value}">
                                                <option value="${applicationPurpose.value}"
                                                        selected="selected">${applicationPurpose.value.displayName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${applicationPurpose.value}">${applicationPurpose.value.displayName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="applicationVO.purpose" id="purposeSelect" class="form-control">
                                    <c:forEach var="applicationPurpose" items="${applicationPurposeMap}">
                                        <option value="${applicationPurpose.value}">${applicationPurpose.value.displayName}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div style="clear:both;"></div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="previousCertificateSelect" class="control-label"><b><span
                                class="mandatory_field">*</span>Have you applied for a certificate previously
                            ?</b></label>
                    </div>
                    <div class="col-sm-2">
                        <c:choose>
                            <c:when test="${applicationVO.previousCertificateApply != null}">
                                <select name="applicationVO.previousCertificateApply" id="previousCertificateSelect"
                                        class="form-control" onchange="previousCertificateAppliedForm();">
                                    <c:choose>
                                        <c:when test="${applicationVO.previousCertificateApply == 1}">
                                            <option value="1" selected="selected">Yes</option>
                                            <option value="0">No</option>
                                        </c:when>
                                        <c:when test="${applicationVO.previousCertificateApply == 0}">
                                            <option value="1">Yes</option>
                                            <option value="0" selected="selected">No</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="applicationVO.previousCertificateApply" id="previousCertificateSelect"
                                        class="form-control" onchange="previousCertificateAppliedForm();">
                                    <option value="1">Yes</option>
                                    <option value="0" selected="selected">No</option>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div id="previousCertificateIssueForm" style="display: none;">
                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="previousCertificateIssueSelect" class="control-label"><b>If so, was a
                                certificate issued to you:</b></label>
                        </div>
                        <div class="col-sm-4">
                            <c:choose>
                                <c:when test="${applicationVO.previousCertificateIssueStatus != null}">
                                    <select name="applicationVO.previousCertificateIssueStatus"
                                            id="previousCertificateIssueSelect" class="form-control"
                                            onchange="previousCertificateIssue();">
                                        <c:choose>
                                            <c:when test="${applicationVO.previousCertificateIssueStatus == 1}">
                                                <option value="1" selected="selected">Yes</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">Yes</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${applicationVO.previousCertificateIssueStatus == 0}">
                                                <option value="0" selected="selected">No</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0">No</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.previousCertificateIssueStatus"
                                            id="previousCertificateIssueSelect" class="form-control"
                                            onchange="previousCertificateIssue();">
                                        <option value="1">Yes</option>
                                        <option value="0">No</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="previousCertificateCountrySelect" class="control-label"><b>Country of the
                                last certificate:</b></label>
                        </div>
                        <div class="col-sm-4">
                            <select name="applicationVO.previousCertificateCountryId"
                                    id="previousCertificateCountrySelect" class="form-control">
                                <c:forEach var="country" items="${countryList}">
                                    <option value="${country.id}">${country.countryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <s:hidden id="previousCertificateCountryName"
                                  name="applicationVO.previousCertificateCountryName" value=""/>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="lastCertificateReferenceNo" class="control-label"><b>Reference No. of the
                                last certificate:</b></label>
                        </div>
                        <div class="col-sm-4">
                            <s:textfield name="applicationVO.previousCertificateReferenceNo"
                                         id="lastCertificateReferenceNo" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="lastCertificateIssueDate" class="control-label"><b>Date of
                                Issue:</b></label>
                        </div>
                        <div class="col-sm-4">
                            <s:textfield name="previousCertificateIssueDate" id="lastCertificateIssueDate"
                                         cssClass="form-control"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>
            </div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <p>Places of residence for the period certificate is required:</p>
            <p class="mandatory_field">Note:
                When mentioning residential addresss and police areas, mention only the
                residential address and
                police areas in Sri Lanka where you had resided. Also enter correct dates of stay.
            </p>

            <div id="addressFormDiv">

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="certificationAddress" class="control-label"><b><span
                                    class="mandatory_field">*</span>Address:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="" id="certificationAddress" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="certificationPoliceAreaSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Police Area:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <select id="certificationPoliceAreaSelect" class="form-control">
                                <c:forEach var="policearea" items="${policeAreaList}">
                                    <option value="${policearea.id}">${policearea.policeArea}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-5">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="certificationPoliceAreaFromSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>From</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="" id="certificationPoliceAreaFromSelect" cssClass="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="certificationPoliceAreaToSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>To</b></label>
                        </div>
                        <div class="col-sm-6">
                            <input type="hidden" id="hiddenPoliceAddressRowId" value="-1"/>
                            <s:textfield name="" id="certificationPoliceAreaToSelect" cssClass="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="form-group">
                        <div style="float: right;">
                            <input type="button" id="certificateAddressTableAddButton"
                                   class="btn btn-primary es-buttton" value="Add"
                                   onclick="addRowToCertificateAddressTable();"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

            </div>

            <br/>
            <table border="1" style="width:100%" id="certificateAddressTable">
                <tr>
                    <th width="30%">Address</th>
                    <th width="30%">Police Area</th>
                    <th width="15%">From</th>
                    <th width="15%">To</th>
                    <th width="5%">Edit</th>
                    <th width="5%">Delete</th>
                </tr>
                <c:forEach var="applicantAddress" items="${applicationVO.applicantCertificateAddresses}"
                           varStatus="counter">
                    <tr>
                        <td><input name="certificateAddressList[${counter.index}].address" type="text"
                                   readonly="readonly" class="address" value="${applicantAddress.address}"/></td>
                        <td><input name="certificateAddressList[${counter.index}].policeAreaId" type="hidden"
                                   readonly="readonly" class="policeAreaVal"
                                   value="${applicantAddress.policeAreaId}"/><input type="text"
                                                                                    name="certificateAddressList[${counter.index}].policeArea"
                                                                                    readonly="readonly"
                                                                                    class="policeArea"
                                                                                    value="${applicantAddress.policeArea}"/>
                        </td>
                        <td>
                            <c:set var="applicationCertificateFromDate"><fmt:formatDate
                                    value="${applicantAddress.fromDate}" pattern="dd/MM/yyyy"/> </c:set>
                            <input name="certificateAddressList[${counter.index}].fromDateStr" type="text"
                                   readonly="readonly" class="from" value="${applicationCertificateFromDate}"/>
                        </td>
                        <td>
                            <c:set var="applicationCertificateToDate"><fmt:formatDate
                                    value="${applicantAddress.toDate}" pattern="dd/MM/yyyy"/> </c:set>
                            <input name="certificateAddressList[${counter.index}].toDateStr" type="text"
                                   readonly="readonly" class="to" value="${applicationCertificateToDate}"/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-6">
                        <label for="handoverPersonSelect" class="control-label"><b>Person authorised by the
                            applicant who are in foreign to handover the application:</b></label>
                    </div>
                    <div class="col-sm-3">
                        <c:choose>
                            <c:when test="${applicationVO.authorizedHandoverPerson != null}">
                                <select name="applicationVO.authorizedHandoverPerson" id="handoverPersonSelect"
                                        class="form-control">
                                    <c:forEach var="handOverPerson" items="${handOverPersons}">
                                        <c:choose>
                                            <c:when test="${handOverPerson.value == applicationVO.authorizedHandoverPerson}">
                                                <option value="${handOverPerson.value}"
                                                        selected="selected">${handOverPerson.value.displayName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${handOverPerson.value}">${handOverPerson.value.displayName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="applicationVO.authorizedHandoverPerson" id="handoverPersonSelect"
                                        class="form-control">
                                    <c:forEach var="handOverPerson" items="${handOverPersons}">
                                        <option value="${handOverPerson.value}">${handOverPerson.value.displayName}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="handoverPersonName" class="control-label"><b>Name:</b></label>
                    </div>
                    <div class="col-sm-9">
                        <s:textfield name="applicationVO.authorizedHandoverPersonName" id="handoverPersonName"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="handoverPersonNIC" class="control-label"><b>NIC/Passport No:</b></label>
                    </div>
                    <div class="col-sm-3">
                        <s:textfield name="applicationVO.authorizedHandoverPersonNicPassport"
                                     id="handoverPersonNICPassport" cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <p>Indicate the address the police clearance certificate should be posted to:</p>

            <input type="checkbox" onclick="copyCurrentAddressToPostalAddress()" id="localAddressCopyCheck"/>
            <label for="localAddressCopyCheck" class="control-label"><b>Copy current address to postal
                address</b></label>
            <p class="mandatory_field">Note :- Checking this checkbox will copy values from "Present Address in Sri
                Lanka" entered above to below fields,
                unchecking it will clear all the eneterd values in below fields</p>


            <p class="mandatory_field">Note :- Please enter your correct present address. Your certificate will be
                posted to this address. This address cannot be changed.</p>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="addressLineOne" class="control-label"><b><span class="mandatory_field">*</span>Address
                            Line 1:</b></label>
                    </div>
                    <div class="col-sm-10">
                        <s:textfield name="applicationVO.certificatePostAddressLineOne" id="addressLineOne"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="addressLineTwo" class="control-label"><b>Address Line 2:</b></label>
                    </div>
                    <div class="col-sm-10">
                        <s:textfield name="applicationVO.certificatePostAddressLineTwo" id="addressLineTwo"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="city" class="control-label"><b><span
                                class="mandatory_field">*</span>City</b></label>
                    </div>
                    <div class="col-sm-7">
                        <s:textfield name="applicationVO.certificatePostAddressCity" id="city"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="state" class="control-label"><b>State/Provice/Region:</b></label>
                    </div>
                    <div class="col-sm-7">
                        <s:textfield name="applicationVO.certificatePostAddressState" id="state"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postal" class="control-label"><b>Postal/ZIP Code:</b></label>
                    </div>
                    <div class="col-sm-7">
                        <s:textfield name="applicationVO.certificatePostAddressPostal" id="postal"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postalCountrySelect" class="control-label"><b><span
                                class="mandatory_field">*</span>Country:</b></label>
                    </div>
                    <div class="col-sm-7">

                        <select name="applicationVO.certificatePostAddressCountry" id="postalCountrySelect"
                                class="form-control">
                            <c:choose>
                                <c:when test="${applicationVO.certificatePostAddressCountry != null}">
                                    <option value="0">Please select</option>
                                    <c:forEach var="country" items="${countryList}">
                                        <c:choose>
                                            <c:when test="${applicationVO.certificatePostAddressCountry == country.id}">
                                                <option value="${country.id}"
                                                        selected="selected">${country.countryName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${country.id}">${country.countryName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <option value="0">Please select</option>
                                    <c:forEach var="country" items="${countryList}">
                                        <option value="${country.id}">${country.countryName}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <s:hidden id="postalCountrySelectName" name="applicationVO.certificatePostAddressCountryName"
                              value=""/>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postalMobile" class="control-label"><b><span class="mandatory_field">*</span>Mobile
                            Number:</b></label>
                    </div>
                    <div class="col-sm-1">
                            <%--<c:choose>--%>
                            <%--<c:when test="${applicationVO.mobileCountryCodeId != null}">--%>
                            <%--<select name="applicationVO.mobileCountryCodeId" id="mobileCountryCodeId"--%>
                            <%--class="form-control">--%>
                            <%--<c:forEach var="country" items="${countryList}">--%>
                            <%--<c:choose>--%>
                            <%--<c:when test="${applicationVO.nationalityId == country.id}">--%>
                            <%--<option value="${country.id}"--%>
                            <%--selected="selected">${country.countryName}(${country.mobileCountryCode})--%>
                            <%--</option>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<option value="${country.id}">${country.countryName}(${country.mobileCountryCode})</option>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--</c:forEach>--%>
                            <%--</select>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<select name="applicationVO.mobileCountryCodeId" id="mobileCountryCodeId"--%>
                            <%--class="form-control">--%>
                            <%--<c:forEach var="country" items="${countryList}">--%>
                            <%--<option value="${country.id}">${country.countryName}(${country.mobileCountryCode})</option>--%>
                            <%--</c:forEach>--%>
                            <%--</select>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <label class="control-label">+94</label>
                        <s:hidden name="applicationVO.mobileCountryCode" id="mobileCountryCodeSelected" value="Sri Lanka(+94)"/>
                        <s:hidden name="applicationVO.mobileCountryCodeId" id="mobileCountryCodeSelectedVal" value="199"/>
                    </div>
                    <div class="col-sm-5" style="width:49.5%">
                        <s:textfield name="applicationVO.mobileNo" id="postalMobile" cssClass="form-control"
                                     autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postalEmail" class="control-label"><b>Email:</b></label>
                    </div>
                    <div class="col-sm-7">
                        <s:textfield name="applicationVO.email" id="postalEmail" cssClass="form-control"
                                     autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postalConfirmMobile" class="control-label"><b><span
                                class="mandatory_field">*</span>Confirm Mobile Number:</b></label>
                    </div>
                    <div class="col-sm-1">
                            <%--<c:choose>--%>
                            <%--<c:when test="${applicationVO.mobileCountryCodeId != null}">--%>
                            <%--<select name="mobileCountryCodeConId" id="mobileCountryCodeConId"--%>
                            <%--class="form-control">--%>
                            <%--<c:forEach var="country" items="${countryList}">--%>
                            <%--<c:choose>--%>
                            <%--<c:when test="${applicationVO.nationalityId == country.id}">--%>
                            <%--<option value="${country.id}"--%>
                            <%--selected="selected">${country.countryName}(${country.mobileCountryCode})--%>
                            <%--</option>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<option value="${country.id}">${country.countryName}(${country.mobileCountryCode})</option>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--</c:forEach>--%>
                            <%--</select>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<select name="mobileCountryCodeConId" id="mobileCountryCodeConId"--%>
                            <%--class="form-control">--%>
                            <%--<c:forEach var="country" items="${countryList}">--%>
                            <%--<option value="${country.id}">${country.countryName}(${country.mobileCountryCode})</option>--%>
                            <%--</c:forEach>--%>
                            <%--</select>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <label class="control-label">+94</label>
                    </div>
                    <div class="col-sm-5" style="width:49.5%;">
                        <input type="text" name="postalConfirmMobile" id="postalConfirmMobile" class="form-control"
                               value="${applicationVO.mobileNo}" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="postalConfirmEmail" class="control-label"><b>Confirm Email:</b></label>
                    </div>
                    <div class="col-sm-7">
                        <input type="text" name="postalConfirmEmail" id="postalConfirmEmail" class="form-control"
                               value="${applicationVO.email}" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <p>Details of Spouse (If applicable only)</p>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="spouseFullName" class="control-label"><b>Spouse's Full Name:</b></label>
                    </div>
                    <div class="col-sm-10">
                        <s:textfield name="applicationVO.spouseFullName" id="spouseFullName" cssClass="form-control"
                                     autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="spouseNationality" class="control-label"><b>Nationality:</b></label>
                    </div>
                    <div class="col-sm-5">
                        <s:textfield name="applicationVO.spouseNationality" id="spouseNationality"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="spousePassport" class="control-label"><b>Passport Number:</b></label>
                    </div>
                    <div class="col-sm-5">
                        <s:textfield name="applicationVO.spousePassport" id="spousePassport" maxLength="30"
                                     cssClass="form-control" autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="spouseNic" class="control-label"><b>NIC Number:</b></label>
                    </div>
                    <div class="col-sm-5">
                        <s:textfield name="applicationVO.spouseNic" id="spouseNic"
                                     cssClass="form-control" autocomplete="off"  maxLength="12"/>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>

            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>

            <div id="paymentModeDiv">
                <p>Mode of Payment (Only for manual submission)</p>
                <p class="mandatory_field">* Note: Enter reference number where possible</p>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="modeOfPayment" class="control-label"><b>Mode of Payment:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <c:choose>
                                <c:when test="${transactionVO.paymentMode != null}">
                                    <select name="transactionVO.paymentMode" id="paymentModeSelect"
                                            class="form-control" onchange="manualPaymentFormValidation();">
                                        <c:choose>
                                            <c:when test="${transactionVO.paymentMode == 'CASH'}">
                                                <option value="CASH" selected="selected">Cash</option>
                                                <option value="CHEQ">Cheque</option>
                                                <option value="WIRE">Wire Transfer</option>
                                            </c:when>
                                            <c:when test="${transactionVO.paymentMode == 'CHEQ'}">
                                                <option value="CASH">Cash</option>
                                                <option value="CHEQ" selected="selected">Cheque</option>
                                                <option value="WIRE">Wire Transfer</option>
                                            </c:when>
                                            <c:when test="${transactionVO.paymentMode == 'WIRE'}">
                                                <option value="CASH">Cash</option>
                                                <option value="CHEQ">Cheque</option>
                                                <option value="WIRE" selected="selected">Wire Transfer</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="transactionVO.paymentMode" id="paymentModeSelect"
                                            class="form-control" onchange="manualPaymentFormValidation();">
                                        <option value="CASH">Cash</option>
                                        <option value="CHEQ">Cheque</option>
                                        <option value="WIRE">Wire Transfer</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="totalFee" class="control-label"><b><span class="mandatory_field">*</span>Amount:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <s:textfield name="transactionVO.totalFee" id="totalFee" cssClass="form-control"
                                         autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div id="paymentModeChequeDiv">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="chequeNo" class="control-label"><b>Cheque No:</b></label>
                            </div>
                            <div class="col-sm-5">
                                <s:textfield name="transactionVO.chequeNo" id="chequeNo" cssClass="form-control"
                                             autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="accountNo" class="control-label"><b>Account No:</b></label>
                            </div>
                            <div class="col-sm-5">
                                <s:textfield name="transactionVO.accountNo" id="accountNo" cssClass="form-control"
                                             autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="accountHolderName" class="control-label"><b>Account Holder's
                                    Name:</b></label>
                            </div>
                            <div class="col-sm-5">
                                <s:textfield name="transactionVO.accountHolderName" id="accountHolderName"
                                             cssClass="form-control" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                </div>

                <div id="paymentModeWireDiv">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="description" class="control-label"><b>Detail:</b></label>
                            </div>
                            <div class="col-sm-5">
                                <s:textfield name="transactionVO.description" id="description"
                                             cssClass="form-control" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                </div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="bookReceiptNo" class="control-label"><b><span
                                    class="mandatory_field">*</span>Book Receipt No:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <s:textfield name="transactionVO.bookReceiptNo" id="bookReceiptNo"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="deliveryTypeSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Delivery Type:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <select name="applicationVO.deliveryType" id="deliveryTypeSelect" class="form-control"
                                    onchange="validateDeliveryType();">
                                <c:forEach var="deliveryType" items="${deliveryTypes}">
                                    <c:choose>
                                        <c:when test="${applicationVO.deliveryType == deliveryType.value}">
                                            <option value="${deliveryType.value}"
                                                    selected="selected">${deliveryType.value.displayName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${deliveryType.value}">${deliveryType.value.displayName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="foriegnMinistryInvertNo" class="control-label"><b>Foreign Ministry Invert
                                Number:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <s:textfield name="applicationVO.foriegnMinistryInvertNo" id="foriegnMinistryInvertNo"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>
            </div>

            <div id="uploadFormDiv">

                <p>Upload Documents</p>

                <div class="col-lg-12" style="margin:1px 0px">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="passportUploadPath" class="control-label"><b><span
                                    class="mandatory_field">*</span>Passport - Personal detail page: <i>(Maximum
                                file size is 250 Kb)</i></b></label>
                        </div>
                        <div class="col-sm-3">
                            <input id="passportFileUpload" name="passportFileUpload" type="file" style=""/>
                            <s:hidden name="applicationVO.passportAttachPath" id="passportUploadPath"
                                      cssClass="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <input type="button" onclick="uploadFile('pas');" class="btn btn-primary es-buttton"
                                   value="Upload"/>
                            <img src="images/ajax-loader.gif" id="ajax_loader_passport" style="display:none;"/>
                            <img src="images/right_green.jpg" id="upload_complete_passport" style="display:none;"/>
                        </div>
                        <c:if test="${reApplyStatus == 1}">
                            <div class="col-sm-1">
                                <c:if test="${applicationVO.passportAttachPath != ''}">
                                    <a href="policeFileFinder.htm?fileName=${applicationVO.passportAttachPath}"
                                       target="_blank"><img src="images/open.png" height="25px"/></a>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12" style="margin:1px 0px">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="passportUploadPath" class="control-label"><b><span
                                    class="mandatory_field">*</span>Passport - Countries allowed page: <i>(Maximum
                                file size is 250 Kb)</i></b></label>
                        </div>
                        <div class="col-sm-3">
                            <input id="passportFileUploadBack" name="passportFileUploadBack" type="file" style=""/>
                            <s:hidden name="applicationVO.passportBackAttachPath" id="passportUploadPathBack"
                                      cssClass="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <input type="button" onclick="uploadFile('pas_back');"
                                   class="btn btn-primary es-buttton" value="Upload"/>
                            <img src="images/ajax-loader.gif" id="ajax_loader_passport_back" style="display:none;"/>
                            <img src="images/right_green.jpg" id="upload_complete_passport_back"
                                 style="display:none;"/>
                        </div>
                        <c:if test="${reApplyStatus == 1}">
                            <div class="col-sm-1">
                                <c:if test="${applicationVO.passportBackAttachPath != ''}">
                                    <a href="policeFileFinder.htm?fileName=${applicationVO.passportBackAttachPath}"
                                       target="_blank"><img src="images/open.png" height="25px"/></a>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12">
                    <label class="col-sm-12 mandatory_field" >(Note : Upload both
                        Personal detail page and countries allowed page.)</label>
                </div>
                <div style="clear:both;"></div>

                    <%--<div id="nicFileUploadDiv">--%>
                <div>
                    <div class="col-lg-12" style="margin: 1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPath" class="control-label"><b>NIC
                                    front side:<i>(Maximum file size is 250 Kb)</i>
                                </b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="nicFileUpload" name="nicFileUpload" type="file"
                                       style=""/>
                                <s:hidden name="applicationVO.nicAttachPath" id="nicUploadPath"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('nic');"
                                       class="btn btn-primary es-buttton" value="Upload"/> <img
                                    src="images/ajax-loader.gif" id="ajax_loader_nic"
                                    style="display: none;"/> <img src="images/right_green.jpg"
                                                                  id="upload_complete_nic" style="display: none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.nicAttachPath != ''}">
                                        <a
                                                href="policeFileFinder.htm?fileName=${applicationVO.nicAttachPath}"
                                                target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin: 1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPathBack" class="control-label"><b>NIC
                                    back side:<i>(Maximum file size is 250 Kb)</i>
                                </b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="nicFileUploadBack" name="nicFileUploadBack"
                                       type="file" style=""/>
                                <s:hidden name="applicationVO.nicBackAttachPath"
                                          id="nicUploadPathBack" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('nic_back');"
                                       class="btn btn-primary es-buttton" value="Upload"/> <img
                                    src="images/ajax-loader.gif" id="ajax_loader_nic_back"
                                    style="display: none;"/> <img src="images/right_green.jpg"
                                                                  id="upload_complete_nic_back"
                                                                  style="display: none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.nicBackAttachPath != ''}">
                                        <a
                                                href="policeFileFinder.htm?fileName=${applicationVO.nicBackAttachPath}"
                                                target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin: 1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="newNicUploadPath" class="control-label"><b>New NIC
                                    front side:<i>(Maximum file size is 250 Kb)</i>
                                </b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="newNicFileUpload" name="newNicFileUpload" type="file"
                                       style=""/>
                                <s:hidden name="applicationVO.newNicAttachPath" id="newNicUploadPath"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('new_nic');"
                                       class="btn btn-primary es-buttton" value="Upload"/> <img
                                    src="images/ajax-loader.gif" id="ajax_loader_new_nic"
                                    style="display: none;"/> <img src="images/right_green.jpg"
                                                                  id="upload_complete_new_nic"
                                                                  style="display: none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.newNicAttachPath != ''}">
                                        <a
                                                href="policeFileFinder.htm?fileName=${applicationVO.newNicAttachPath}"
                                                target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>


                    <div class="col-lg-12" style="margin: 1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="newNicUploadPathBack" class="control-label"><b>New NIC
                                    back side:<i>(Maximum file size is 250 Kb)</i>
                                </b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="newNicFileUploadBack" name="newNicFileUploadBack"
                                       type="file" style=""/>
                                <s:hidden name="applicationVO.newNicBackAttachPath"
                                          id="newNicUploadPathBack" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('new_nic_back');"
                                       class="btn btn-primary es-buttton" value="Upload"/> <img
                                    src="images/ajax-loader.gif" id="ajax_loader_new_nic_back"
                                    style="display: none;"/> <img src="images/right_green.jpg"
                                                                  id="upload_complete_new_nic_back"
                                                                  style="display: none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.newNicBackAttachPath != ''}">
                                        <a
                                                href="policeFileFinder.htm?fileName=${applicationVO.newNicBackAttachPath}"
                                                target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <label class="col-sm-12 mandatory_field">(Note :
                            Both sides.) Non-Sri Lankan provide NIC if availble to avoid processing delays.</label>
                    </div>


                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="birthCertificateUpload" class="control-label"><b>Birth Certificate - front
                                    side:<i>(Maximum file size is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="birthCertificateUpload" name="birthCertificateUpload" type="file" style=""/>
                                <s:hidden name="applicationVO.birthCertificatePath" id="birthCertificateUploadPath"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('bir');" class="btn btn-primary es-buttton"
                                       value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_birth_certificate"
                                     style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_birth_certificate"
                                     style="display:none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.birthCertificatePath != ''}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.birthCertificatePath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="birthCertificateUploadBack" class="control-label"><b>Birth Certificate -
                                    back side:<i>(Maximum file size is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="birthCertificateUploadBack" name="birthCertificateUploadBack" type="file"
                                       style=""/>
                                <s:hidden name="applicationVO.birthCertificateBackPath"
                                          id="birthCertificateUploadPathBack" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('bir_back');"
                                       class="btn btn-primary es-buttton" value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_birth_certificate_back"
                                     style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_birth_certificate_back"
                                     style="display:none;"/>
                            </div>
                            <c:if test="${reApplyStatus == 1}">
                                <div class="col-sm-1">
                                    <c:if test="${applicationVO.birthCertificateBackPath != ''}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.birthCertificateBackPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <label class="col-sm-12 mandatory_field" style="padding-left: 12px; padding-bottom: 20px;">(Note : Sri Lankan
                            citizen below 18. Both sides.)</label>
                    </div>
                    <div style="clear:both;"></div>
                    <div id="affidavitDivId">
                        <div class="col-lg-12" style="margin:1px 0px" id="affidavitUploadId">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="affidavitUpload" class="control-label"><b>Affidavit :
                                        <i>(Maximum file size is 250 Kb)</i></b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input id="affidavitUpload" name="affidavitUpload" type="file"
                                           style=""/>
                                    <s:hidden name="applicationVO.affidavitAttachPath"
                                              id="affidavitUploadPath" cssClass="form-control"/>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" onclick="uploadFile('affidavit');"
                                           class="btn btn-primary es-buttton" value="Upload"/>
                                    <img src="images/ajax-loader.gif" id="ajax_loader_affidavit"
                                         style="display:none;"/>
                                    <img src="images/right_green.jpg" id="upload_complete_affidavit"
                                         style="display:none;"/>
                                </div>
                                <c:if test="${reApplyStatus == 1}">
                                    <div class="col-sm-1">
                                        <c:if test="${applicationVO.affidavitAttachPath != ''}">
                                            <a href="policeFileFinder.htm?fileName=${applicationVO.affidavitAttachPath}"
                                               target="_blank"><img src="images/open.png" height="25px"/></a>
                                        </c:if>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="col-lg-12" id="affidavitNoteId">
                            <label class="col-sm-12 mandatory_field" >(Note : Sri
                                Lankan citizen only. The affidavit is mandatory only when the name as in the passport
                                need to be printed on the certificate.)</label>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-8">
                                <label for="referredThroughBereau" class="control-label"><b><span
                                        class="mandatory_field">*</span>Are you referred applicant through the Sri
                                    Lankan Foreign Employment Bureau(SLBFE)?</b></label>
                            </div>
                            <div class="col-sm-4">
                                <c:choose>
                                    <c:when test="${applicationVO.referredThroughBereau != null}">
                                        <select name="applicationVO.referredThroughBereau" id="referredThroughBereau"
                                                class="form-control" onchange="setVisibleLetterOfReferenceUpload();">
                                            <c:choose>
                                                <c:when test="${applicationVO.referredThroughBereau == 1}">
                                                    <option value="1" selected="selected">Yes</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="1">Yes</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${applicationVO.referredThroughBereau == 0}">
                                                    <option value="0" selected="selected">No</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="0">No</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="applicationVO.referredThroughBereau" id="referredThroughBereau"
                                                class="form-control" onchange="setVisibleLetterOfReferenceUpload();">
                                            <option value="1">Yes</option>
                                            <option value="0">No</option>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>

                    <div id="letterOfReferenceUploadDiv">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="uploadLetterOfReferenceUpload" class="control-label"><b>If Yes, letter
                                        of reference from SLBFE:<i>(Maximum file size is 250 Kb)</i></b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input id="uploadLetterOfReferenceUpload" name="uploadLetterOfReference" type="file"
                                           style=""/>
                                    <s:hidden name="applicationVO.letterOfReferencePath"
                                              id="uploadLetterOfReferencePath" cssClass="form-control"/>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" onclick="uploadFile('let');" class="btn btn-primary es-buttton"
                                           value="Upload"/>
                                    <img src="images/ajax-loader.gif" id="ajax_loader_letter_of_reference"
                                         style="display:none;"/>
                                    <img src="images/right_green.jpg" id="upload_complete_letter_of_reference"
                                         style="display:none;"/>
                                </div>
                                <c:if test="${reApplyStatus == 1}">
                                    <div class="col-sm-1">
                                        <c:if test="${applicationVO.letterOfReferencePath != ''}">
                                            <a href="policeFileFinder.htm?fileName=${applicationVO.letterOfReferencePath}"
                                               target="_blank"><img src="images/open.png" height="25px"/></a>
                                        </c:if>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div style="clear:both;"></div>
                    </div>

                    <p>Please ensure correct and clear copies of the documents are attached to avoid delays and
                        application rejection.</p>

                    <div style="clear:both;"></div>
                    <hr/>
                    <div style="clear:both;"></div>

                </div>


                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

            </div>

            <table>
                <tr>
                    <td style="vertical-align: middle; width: 50px;"><input type="checkbox" name=""
                                                                            id="acceptTermsCheck"
                                                                            class="form-control"
                                                                            onclick="submitButtonStatus();"/></td>
                    <td style="vertical-align: middle;"><p><span class="mandatory_field">*</span>I declare that the
                        information provided above are true to the best of my knowledge and I agree to the terms of
                        Sri Lankan Police Headquarters.</p></td>
                </tr>
            </table>

            <div style="clear:both;"></div>

            <div class="col-lg-12">
                <div style="float: right;">
                    <input id="saveApplicationSubmitButton" type="submit" class="btn btn-primary es-buttton"
                           value="Submit"/>
                    <input type="button" class="btn btn-primary es-buttton" value="Reset" onclick="resetForm();"/>
                </div>
            </div>
            <div style="clear:both;"></div>

            </s:form>

            <!-- including footer -->
                <jsp:include page="../common/footer.jsp"/>

            <script src="js/jquery.maskedinput.min.js" type="text/javascript"></script>

            <script type="text/javascript">
                function copyCurrentAddressToPostalAddress() {
                    var isChecked = $('#localAddressCopyCheck').is(":checked")
                    if (isChecked) {
                        var addressLineOneCopy = $.trim($('#addressLineOneCopy').val());
                        var addressLineTwoCopy = $.trim($('#addressLineTwoCopy').val());
                        var cityCopy = $.trim($('#cityCopy').val());
                        var stateCopy = $.trim($('#stateCopy').val());
                        var postalCopy = $.trim($('#postalCopy').val());
                        var postalCountry = $("#postalCountrySelectCopy").val();
                        if (postalCountry != 0) {
                            var postalCountryName = $("#postalCountrySelectCopy option:selected").text();
                            $("#postalCountrySelectNameCopy").val(postalCountryName);
                        }
                        var postalCountrySelectNameCopy = $.trim($('#postalCountrySelectNameCopy').val());
                        $('#addressLineOne').val(addressLineOneCopy);
                        $('#addressLineTwo').val(addressLineTwoCopy);
                        $('#city').val(cityCopy);
                        $('#state').val(stateCopy);
                        $('#postal').val(postalCopy);
                        $("#postalCountrySelect").val(postalCountry);
                        $('#postalCountrySelectName').val(postalCountrySelectNameCopy);
                    } else {
                        $('#addressLineOne').val('');
                        $('#addressLineTwo').val('');
                        $('#city').val('');
                        $('#state').val('');
                        $('#postal').val('');
                        $("#postalCountrySelect").val(0);
                        $('#postalCountrySelectName').val('');
                    }
                }

                function constructPresentAddressInSriLanka() {
                    var addressLineOneCopy = $.trim($('#addressLineOneCopy').val());
                    var addressLineTwoCopy = $.trim($('#addressLineTwoCopy').val());
                    var cityCopy = $.trim($('#cityCopy').val());
                    var stateCopy = $.trim($('#stateCopy').val());
                    var postalCopy = $.trim($('#postalCopy').val());

                    var postalCountry = $("#postalCountrySelectCopy").val();
                    if (postalCountry != 0) {
                        var postalCountryName = $("#postalCountrySelectCopy option:selected").text();
                        $("#postalCountrySelectNameCopy").val(postalCountryName);
                    }

                    var postalCountrySelectNameCopy = $.trim($('#postalCountrySelectNameCopy').val());

                    var constructedAddress = '';
                    if (!(isBlank(addressLineOneCopy))) {
                        constructedAddress = constructedAddress + addressLineOneCopy + ', ';
                    }
                    if (!(isBlank(addressLineTwoCopy))) {
                        constructedAddress = constructedAddress + addressLineTwoCopy + ', ';
                    }
                    if (!(isBlank(cityCopy))) {
                        constructedAddress = constructedAddress + cityCopy + ', ';
                    }
                    if (!(isBlank(stateCopy))) {
                        constructedAddress = constructedAddress + stateCopy + ', ';
                    }
                    if (!(isBlank(postalCopy))) {
                        constructedAddress = constructedAddress + postalCopy + ', ';
                    }
                    if (!(isBlank(postalCountrySelectNameCopy))) {
                        constructedAddress = constructedAddress + postalCountrySelectNameCopy + ', ';
                    }
                    constructedAddress = $.trim(constructedAddress);
                    constructedAddress = constructedAddress.substring(0, constructedAddress.length - 1);
                    $('#presentAddressLanka').val(constructedAddress);
                }

                function isBlank(str) {
                    return (!str || /^\s*$/.test(str));
                }

                function isDate(value) {
                    var isDate = false;
                    try {
                        var dateObject = $.datepicker.parseDate('dd/mm/yy', value);
                        //console.log(dateObject);
                        isDate = true;
                    } catch (e) {
                    }
                    return isDate;
                }

                function initializeInputMskList() {
                    $("#dateOfBirth").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#dateOfBirth").blur(function () {
                        var isValid = isDate($.trim($("#dateOfBirth").val()));
                        if (!(isValid)) {
                            alert('The value enetered for \'date of birth\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("dateOfBirth");
                        }
                    });
                    $("#dateOfBirth").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });

                    $("#dateOfcitizenshipObtained").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#dateOfcitizenshipObtained").blur(function () {
                        var isValid = isDate($.trim($("#dateOfcitizenshipObtained").val()));
                        if (!(isValid)) {
                            alert('The value enetered for date \'citizenship obtained from another country\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("dateOfcitizenshipObtained");
                        }
                    });
                    $("#dateOfcitizenshipObtained").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });


                    $("#passportIssueDate").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#passportIssueDate").blur(function () {
                        var isValid = isDate($.trim($("#passportIssueDate").val()));
                        if (!(isValid)) {
                            alert('The value enetered for date \'Passport Issue Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("passportIssueDate");
                        }
                    });
                    $("#passportIssueDate").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });


                    $("#certificationPoliceAreaFromSelect").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#certificationPoliceAreaFromSelect").blur(function () {
                        var isValid = isDate($.trim($("#certificationPoliceAreaFromSelect").val()));
                        if (!(isValid)) {
                            alert('The value enetered for date \'From Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("certificationPoliceAreaFromSelect");
                        }
                    });
                    $("#certificationPoliceAreaFromSelect").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });


                    $("#certificationPoliceAreaToSelect").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#certificationPoliceAreaToSelect").blur(function () {
                        var isValid = isDate($.trim($("#certificationPoliceAreaToSelect").val()));
                        if (!(isValid)) {
                            alert('The value enetered for date \'To Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("certificationPoliceAreaToSelect");
                        }
                    });
                    $("#certificationPoliceAreaToSelect").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });


                    $("#lastCertificateIssueDate").mask("cd/nm/vwww", {placeholder: "dd/mm/yyyy"});
                    $("#lastCertificateIssueDate").blur(function () {
                        var isValid = isDate($.trim($("#lastCertificateIssueDate").val()));
                        if (!(isValid)) {
                            alert('The value enetered for date \'Previous certificate date of issue\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            addRedBorder("lastCertificateIssueDate");
                        }
                    });
                    $("#lastCertificateIssueDate").click(function () {
                        $(this).css("border", "1px solid #e0e1fc");
                    });


                }

                $(document).ready(function () {

                    $.mask.definitions['c'] = "[0-3]";
                    $.mask.definitions['d'] = "[0-9]";
                    $.mask.definitions['n'] = "[0-1]";
                    $.mask.definitions['m'] = "[0-9]";

                    $.mask.definitions['v'] = "[1-2]";
                    $.mask.definitions['w'] = "[0-9]";

                    previousAppRefNoForm();
                    initializeDateTimePickers();

                    var status = $("#reapplyStatus").val();
                    if (status == 1) {
                        //its a reapply
                        var passportIssuedDate = $('#passportIssueDateHiddenField').val();
                        //console.log(passportIssuedDate);
                        if (!(passportIssuedDate == null || passportIssuedDate == 'undefined')) {
                            $('#passportIssueDate').datepicker("setDate", new Date(passportIssuedDate));
                        }

                        $('#deliveryTypeSelect option:not(:selected)').attr('disabled', true);
                    }

                    initializeInputMskList();

                    $("#certificateAddressTable").on('click', '.remRow', function () {
                        $(this).parent().parent().remove();
                    });
                    $("#applicantLivedAddressTable").on('click', '.remRow', function () {
                        $(this).parent().parent().remove();
                    });
                    $("#spouseLivedAddressTable").on('click', '.remRow', function () {
                        $(this).parent().parent().remove();
                    });

                    var postalCountry = $("#postalCountrySelect").val();
                    if (postalCountry != 0) {
                        var postalCountryName = $("#postalCountrySelect option:selected").text();
                        $("#postalCountrySelectName").val(postalCountryName);
                    }

                    var countryName = $("#countryVerifySelect option:selected").text();
                    $("#countryVerifySelectName").val(countryName);

                    var previousCertificateCountryName = $("#previousCertificateCountrySelect option:selected").text();
                    $("#previousCertificateCountryName").val(previousCertificateCountryName);

// 		$("#mobileCountryCodeSelected").val($("#mobileCountryCodeId option:selected").text());

                    verificationValidator();
                    reApplyStatus();
                    setVisibleLetterOfReferenceUpload();
                    changeStatuscCtizenOfSriLanka();

                    $("#saveApplicationSubmitButton").attr('disabled', 'disabled');

                    getCommissioners();
                    manualPaymentFormValidation();
                    validateDeliveryType();

                    makeNumericOnlyTextBox('#applicantAge');
                    makeNumericOnlyTextBox('#postalMobile');
                    makeNumericOnlyTextBox('#postalConfirmMobile');
                    makeNumericOnlyTextBox('#totalFee');

                    previousCertificateAppliedForm();
                    previousCertificateIssue();

                    wereYouCitizenOfSriLankaShowHide();

                    isWantAffidavit();

                    validateCitizenOfUser();

                    $('#nicNo').attr('maxlength', '10');
                    $('#newNicNo').attr('maxlength', '12');
                    $('#confirmNicNo').attr('maxlength', '10');
                    $('#confirmNewNicNo').attr('maxlength', '12');

                    var nationalityId = $("#nationalitySelect").val();
                    var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();
                    var age = $('#applicantAge').val();

                });

                function isWantAffidavit() {
                    var selectedOption = $('#selectedName :selected').val();

                    var nationalityId = $("#nationalitySelect").val();

                    var age = $("#applicantAge").val();
                    var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();

                    if ((nationalityId == 199 || citizenOfSriLanka == '1') && age >= 18 && selectedOption == 'passport') {
                        $('#affidavitUploadId').show();
                        $('#affidavitNoteId').show();
                    } else {
                        $('#affidavitUploadId').hide();
                        $('#affidavitNoteId').hide();
                    }
                }


                function validateCitizenOfUser() {
                    var selectedOption = $('#nicValidate :selected').val();
                    if (selectedOption == '1') {
                        var msg = 'You have indicate that you left Sri Lanka before the age of 16. \n \nPlease hand over your application ' +
                            'through a High Commission/ Embassy/ Consulate or any application centre.';
//                    alert(msg);
                        $('#verifySubmitButton').prop('disabled', false);
                        $('#acceptTermsCheck').prop('disabled', false);

                        var checkBoxStatus = $('#acceptTermsCheck').prop('checked');

                        if (checkBoxStatus) {
                            $('#saveApplicationSubmitButton').prop('disabled', false);
                        }

                    }
                }

                // Prevent the backspace key from navigating back.
                $(document).unbind('keydown').bind('keydown', function (event) {
                    var doPrevent = false;
                    if (event.keyCode === 8) {
                        var d = event.srcElement || event.target;
                        if ((d.tagName.toUpperCase() === 'INPUT' &&
                                (
                                d.type.toUpperCase() === 'TEXT' ||
                                d.type.toUpperCase() === 'PASSWORD' ||
                                d.type.toUpperCase() === 'FILE' ||
                                d.type.toUpperCase() === 'EMAIL' ||
                                d.type.toUpperCase() === 'SEARCH' ||
                                d.type.toUpperCase() === 'DATE' )
                            ) ||
                            d.tagName.toUpperCase() === 'TEXTAREA') {
                            doPrevent = d.readOnly || d.disabled;
                        }
                        else {
                            doPrevent = true;
                        }
                    }

                    if (doPrevent) {
                        event.preventDefault();
                    }
                });

                function verificationValidator() {
                    if ($("#verificationStatus").val() == 1) {
                        $("#applicationRegistrationForm").hide();
                    } else if ($("#verificationStatus").val() == 2) {
                        $("#applicationRegistrationForm").hide();
                        var conf = confirm('Application is processing for the given criteria, do you want to add another one with same passport number and country?');
                        if (conf) {
                            $("#verifyMessage").hide();
                            $("#verificationStatus").val(1);
                            $("#nicNo").attr("readonly", "true");
                            $("#newNicNo").attr("readonly", "true");
                            $("#passportNo").attr("readonly", "true");
                            $("#confirmNicNo").attr("readonly", "true");
                            $("#confirmNewNicNo").attr("readonly", "true");
                            $("#confirmPassportNo").attr("readonly", "true");
                            $("#highCommisionReferenceName").attr("readonly", "true");
                            $("#highCommisionReferenceAddress").attr("readonly", "true");
                            //$("#countrySelect").attr("disabled", "true");
                            $("#applicationRegistrationForm").show();
                        }
                    } else {
                        $("#verifyMessage").hide();

                        $("#nicNo").attr("readonly", "true");
                        $("#newNicNo").attr("readonly", "true");
                        $("#passportNo").attr("readonly", "true");
                        $("#confirmNicNo").attr("readonly", "true");
                        $("#confirmNewNicNo").attr("readonly", "true");
                        $("#confirmPassportNo").attr("readonly", "true");
                        $("#highCommisionReferenceName").attr("readonly", "true");
                        $("#highCommisionReferenceAddress").attr("readonly", "true");

                        //$("#dateOfBirth").attr("readonly", "true");

                        //$("#countrySelect").attr("disabled", "true");

                        $("#applicationRegistrationForm").show();
                    }
                }

                function hideModeOfPayment() {
                    $("#paymentModeDiv").hide();
                }

                function initializeDateTimePickers() {
                    $('#certificationPoliceAreaFromSelect').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        minDate: ($.datepicker.parseDate('dd/mm/yy', $("#dateOfBirth").val())),
                        dateFormat: "dd/mm/yy",
                        onClose: function (selectedDate) {
                            if (!(($("#dateOfBirth").val()) == '' || ($("#dateOfBirth").val()) == null)) {
                                var dob = $.datepicker.parseDate('dd/mm/yy', $("#dateOfBirth").val());
                                if (selectedDate < dob) {
                                    selectedDate = dob;
                                }
                            }
                            $("#certificationPoliceAreaToSelect").datepicker("option", "minDate", selectedDate);
                        }
                    });
                    $('#certificationPoliceAreaToSelect').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        minDate: ($.datepicker.parseDate('dd/mm/yy', $("#dateOfBirth").val())),
                        dateFormat: "dd/mm/yy",
                        onClose: function (selectedDate) {
                            if (!(selectedDate == "")) {
                                $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", selectedDate);
                            } else {
                                $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());
                            }
                        }
                    });
                    $('#livedPoliceAreaFromSelect').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        dateFormat: "dd/mm/yy"
                    });
                    $('#livedPoliceAreaToSelect').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        dateFormat: "dd/mm/yy"
                    });
                    $('#lastCertificateIssueDate').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        minDate: $('#dateOfBirth').datepicker("getDate"),
                        dateFormat: "dd/mm/yy"
                    });
                    $('#dateOfBirth').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        dateFormat: "dd/mm/yy",
                        onClose: function (selectedDate) {
                            if (!(selectedDate == "")) {
                                $("#passportIssueDate").datepicker("option", "minDate", selectedDate);
                                $("#dateOfcitizenshipObtained").datepicker("option", "minDate", selectedDate);
                                $("#lastCertificateIssueDate").datepicker("option", "minDate", selectedDate);
                            }
                        }
                    });
                    $('#dateOfcitizenshipObtained').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        dateFormat: "dd/mm/yy"
                    });

                    var selectedDob = $('#dateOfBirth').datepicker("getDate");
                    if (!(selectedDob == '' || selectedDob == null)) {
                        $("#dateOfcitizenshipObtained").datepicker("option", "minDate", selectedDob);
                    }

                    $('#passportIssueDate').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        minDate: $('#dateOfBirth').datepicker("getDate"),
                        dateFormat: "dd/mm/yy"
                    });

                }

                function previousAppRefNoForm() {
                    var status = $("#applicationTypeSelect").val();
                    if (status == 'new') {
                        $("#previousReferenceForm").hide(500);
                        var reapStatus = $("#reapplyStatus").val();
                        if (reapStatus == 1) {
                            window.location = 'application.action';
                        }
                    } else if (status == 're') {
                        $("#previousReferenceNo").val($("#applicationReferenceNoValue").val());
                        $("#previousReferenceForm").show(500);
                    }
                }

                function previousCertificateAppliedForm() {
                    var status = $("#previousCertificateSelect").val();
                    if (status == 0) {
                        $("#previousCertificateIssueForm").hide(500);
                    } else if (status == 1) {
                        $("#previousCertificateIssueForm").show(500);
                    }
                }

                function DateValidationVO(addressValue, policeArea, fromDate, toDate) {
                    this.addressValue = addressValue;
                    this.policeArea = policeArea;
                    this.fromDate = fromDate;
                    this.toDate = toDate;
                }

                function addRowToCertificateAddressTable() {
                    var address = $("#certificationAddress").val();
                    var policeArea = $("#certificationPoliceAreaSelect").val();
                    var policeAreaName = $("#certificationPoliceAreaSelect option:selected").text();
                    var from = $("#certificationPoliceAreaFromSelect").val();
                    var to = $("#certificationPoliceAreaToSelect").val();

                    var isValid = isDate($.trim($("#certificationPoliceAreaFromSelect").val()));
                    if (!(isValid)) {
                        alert('The value enetered for date \'From Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                        addRedBorder("certificationPoliceAreaFromSelect");
                        $("#certificationPoliceAreaFromSelect").focus();
                        return false;
                    }

                    isValid = isDate($.trim($("#certificationPoliceAreaToSelect").val()));
                    if (!(isValid)) {
                        alert('The value enetered for date \'To Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                        addRedBorder("certificationPoliceAreaToSelect");
                        $("#certificationPoliceAreaToSelect").focus();
                        return false;
                    }

                    if (address.replace(/^\s+/, '').replace(/\s+$/, '') == '') {
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

                    var sysDate = new Date();
                    
                    var month = sysDate.getMonth() + 1;
                    var day = sysDate.getDate();
                    var year = sysDate.getFullYear();                    

//                     var formatedDate = new Date(day + '/' + month + '/' + year);
  					var formatedDate = new Date(month + '/' + day + '/' + year);
                    
                    var toDateArr = to.split('/');                    
                    var formatedToDate = new Date(toDateArr[1]+'/'+toDateArr[0]+'/'+toDateArr[2]);                  

                    var fromDateArr = from.split('/');
                    var formatedFromDate = new Date(fromDateArr[1]+'/'+fromDateArr[0]+'/'+fromDateArr[2]);

                    var toDate = new Date(to).getTime();
                    var fromDate = new Date(from).getTime();
                    var sysDateInMilis = formatedDate.getTime();
                    if (formatedFromDate.getTime() > sysDateInMilis) {
                        alert('Sorry, From date cannot be future date.');
                        $("#certificationPoliceAreaFromSelect").val('');
                        return false;
                    }                     
                    if (formatedToDate.getTime() > sysDateInMilis) {
                        alert('Sorry, To date cannot be future date');
                        $("#certificationPoliceAreaToSelect").val('');
                        return false;
                    }

                    var rowCountSaved = parseInt($('#hiddenPoliceAddressRowId').val());
                    if (rowCountSaved < 0) {
                        rowCountSaved = $('#certificateAddressTable tr').length - 1;
                    }

                    var rowCount = rowCountSaved;
                    //alert('rowCount :' + rowCount);
                    try {
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

// 					var fromDt=$.datepicker.parseDate( "dd/mm/yy", $('#' + fromDateId ).val() );
// 					var toDt=$.datepicker.parseDate( "dd/mm/yy", $('#' + toDateId ).val() );

                                var fromDt = $('#' + fromDateId).val();
                                var toDt = $('#' + toDateId).val();
                                var policeAr = $.trim($('#' + policeAreaIdId).val());
                                var selectedAdrs = $.trim($('#' + selectedAddressId).val());

                                myArray.push(new DateValidationVO(selectedAdrs, policeAr, fromDt, toDt));

                            });

                            ///add newly added date
                            myArray.push(new DateValidationVO(address, policeAreaName, from, to));

                            var data = JSON.stringify(myArray);
                            //alert(data);

                            $.post("validateDateRange.action", {dateValidateJsonString: data}, function (response) {
                                unBlockUI();
                                if (response.addressPeriodValidStatus != 1) {
                                    alert(response.addressPeriodValidMessage);
                                    return false;
                                } else {

                                    //clear fields
                                    $("#certificationAddress").val('');
                                    $("#certificationPoliceAreaSelect").val('');
                                    $("#certificationPoliceAreaFromSelect").val('');
                                    $("#certificationPoliceAreaToSelect").val('');
                                    $('#hiddenPoliceAddressRowId').val(-1);

                                    $("#certificateAddressTable").append('<tr id="cad' + rowCount + '" class="appendedRowAddress">' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].address" type="text" id="addedAddress_' + rowCount + '" readonly="readonly" class="address" value="' + address + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].policeAreaId" type="hidden" readonly="readonly" class="policeAreaVal" value="' + policeArea + '" />' +
                                        '<input type="text" name="certificateAddressList[' + rowCount + '].policeArea" id="addedPoliceArea_' + rowCount + '" readonly="readonly" class="policeArea" value="' + policeAreaName + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].fromDateStr" id="addedFromDate_' + rowCount + '" type="text" readonly="readonly" class="from" value="' + from + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].toDateStr" id="addedToDate_' + rowCount + '" type="text" readonly="readonly" class="to" value="' + to + '" /></td>' +
                                        '<td><img class="remRow" src="images/edit.png" onclick="editCertificateAddressRow(\'cad' + rowCount + '\');"></td>' +
                                        '<td><img class="remRow" src="images/cancel.png"></td>' +
                                        '</tr>');

                                    $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());

                                }
                            });

                        } else {

                            //clear fields
                            $("#certificationAddress").val('');
                            $("#certificationPoliceAreaSelect").val('');
                            $("#certificationPoliceAreaFromSelect").val('');
                            $("#certificationPoliceAreaToSelect").val('');
                            $('#hiddenPoliceAddressRowId').val(-1);

                            $("#certificateAddressTable").append('<tr id="cad' + rowCount + '" class="appendedRowAddress">' +
                                '<td><input name="certificateAddressList[' + rowCount + '].address" type="text" id="addedAddress_' + rowCount + '" readonly="readonly" class="address" value="' + address + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].policeAreaId" type="hidden" readonly="readonly" class="policeAreaVal" value="' + policeArea + '" />' +
                                '<input type="text" name="certificateAddressList[' + rowCount + '].policeArea" id="addedPoliceArea_' + rowCount + '" readonly="readonly" class="policeArea" value="' + policeAreaName + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].fromDateStr" id="addedFromDate_' + rowCount + '" type="text" readonly="readonly" class="from" value="' + from + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].toDateStr" id="addedToDate_' + rowCount + '" type="text" readonly="readonly" class="to" value="' + to + '" /></td>' +
                                '<td><img class="remRow" src="images/edit.png" onclick="editCertificateAddressRow(\'cad' + rowCount + '\');"></td>' +
                                '<td><img class="remRow" src="images/cancel.png"></td>' +
                                '</tr>');

                            $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());

                        }

                    } catch (e) {
                        console.log(e);
                    }

                }


                function addLivedAddress() {
                    var usertype = $("#applicantSpouseSelect").val();
                    if (usertype == "applicant") {
                        addRowToApplicantLivedAddressTable();
                    } else if (usertype == "spouse") {
                        addRowToSpousLivedAddressTable();
                    }
                }

                // 	function addRowToApplicantLivedAddressTable(){
                // 		var address = $("#livedAddress").val();
                // 		var policeArea = $("#livedPoliceAreaSelect").val();
                // 		var policeAreaName = $("#livedPoliceAreaSelect option:selected").text();
                // 		var from = $("#livedPoliceAreaFromSelect").val();
                // 		var to = $("#livedPoliceAreaToSelect").val();

                // 		if(address == ''){
                // 			alert('Please insert a address');
                // 			return false;
                // 		}else if(policeArea == ''){
                // 			alert('Please insert a police area');
                // 			return false;
                // 		}else if(from == ''){
                // 			alert('Please insert a from date');
                // 			return false;
                // 		}else if(to == ''){
                // 			alert('Please insert a to date');
                // 			return false;
                // 		}

                // 		var rowCount = $('#applicantLivedAddressTable tr').length-1;

                // 		//clear fields
                // 		$("#livedAddress").val('');
                // 		$("#livedPoliceAreaSelect").val('');
                // 		$("#livedPoliceAreaFromSelect").val('');
                // 		$("#livedPoliceAreaToSelect").val('');

                // 		$("#applicantLivedAddressTable").append('<tr id="alpa'+rowCount+'">'+
                // 				'<td><input name="applicantLivedAddressList['+rowCount+'].address" type="text" readonly="readonly" class="address" value="'+address+'" /></td>'+
                // 				'<td><input name="applicantLivedAddressList['+rowCount+'].policeAreaId" type="hidden" readonly="readonly" class="policeAreaVal" value="'+policeArea+'" /><input type="text" name="applicantLivedAddressList['+rowCount+'].policeArea" readonly="readonly" class="policeArea" value="'+policeAreaName+'" /></td>'+
                // 				'<td><input style="width: 70px;" name="applicantLivedAddressList['+rowCount+'].fromDateStr" type="text" readonly="readonly" class="from" value="'+from+'" /></td>'+
                // 				'<td><input style="width: 70px;" name="applicantLivedAddressList['+rowCount+'].toDateStr" type="text" readonly="readonly" class="to" value="'+to+'" /></td>'+
                // 				'<td><img class="remRow" src="images/edit.png" onclick="editApplicantLivedAddressRow(\'alpa'+rowCount+'\');"></td>'+
                // 				'<td><img class="remRow" src="images/cancel.png"></td>'+
                // 				'</tr>');
                // 	}

                // 	function addRowToSpousLivedAddressTable(){
                // 		var address = $("#livedAddress").val();
                // 		var policeArea = $("#livedPoliceAreaSelect").val();
                // 		var policeAreaName = $("#livedPoliceAreaSelect option:selected").text();
                // 		var from = $("#livedPoliceAreaFromSelect").val();
                // 		var to = $("#livedPoliceAreaToSelect").val();

                // 		if(address == ''){
                // 			alert('Please insert a address');
                // 			return false;
                // 		}else if(policeArea == ''){
                // 			alert('Please insert a police area');
                // 			return false;
                // 		}else if(from == ''){
                // 			alert('Please insert a from date');
                // 			return false;
                // 		}else if(to == ''){
                // 			alert('Please insert a to date');
                // 			return false;
                // 		}

                // 		var rowCount = $('#spouseLivedAddressTable tr').length-1;

                // 		//clear fields
                // 		$("#livedAddress").val('');
                // 		$("#livedPoliceAreaSelect").val('');
                // 		$("#livedPoliceAreaFromSelect").val('');
                // 		$("#livedPoliceAreaToSelect").val('');

                // 		$("#spouseLivedAddressTable").append('<tr id="slpa'+rowCount+'">'+
                // 				'<td><input name="spouseLivedAddressList['+rowCount+'].address" type="text" readonly="readonly" class="address" value="'+address+'" /></td>'+
                // 				'<td><input name="spouseLivedAddressList['+rowCount+'].policeAreaId" type="hidden" readonly="readonly" class="policeAreaVal" value="'+policeArea+'" /><input type="text" name="spouseLivedAddressList['+rowCount+'].policeArea" readonly="readonly" class="policeArea" value="'+policeAreaName+'" /></td>'+
                // 				'<td><input style="width: 70px;" name="spouseLivedAddressList['+rowCount+'].fromDateStr" type="text" readonly="readonly" class="from" value="'+from+'" /></td>'+
                // 				'<td><input style="width: 70px;" name="spouseLivedAddressList['+rowCount+'].toDateStr" type="text" readonly="readonly" class="to" value="'+to+'" /></td>'+
                // 				'<td><img class="remRow" src="images/edit.png" onclick="editSpouseLivedAddressRow(\'slpa'+rowCount+'\');"></td>'+
                // 				'<td><img class="remRow" src="images/cancel.png"></td>'+
                // 				'</tr>');
                // 	}

                function editCertificateAddressRow(rowId) {
                    $("#certificationAddress").val($("#certificateAddressTable").find('#' + rowId).find('.address').val());
                    $("#certificationPoliceAreaSelect").val($("#certificateAddressTable").find('#' + rowId).find('.policeAreaVal').val());
                    $("#certificationPoliceAreaFromSelect").val($("#certificateAddressTable").find('#' + rowId).find('.from').val());
                    $("#certificationPoliceAreaToSelect").val($("#certificateAddressTable").find('#' + rowId).find('.to').val());

                    var roIntId = parseInt($.trim(rowId.replace("cad", "")));

                    $('#hiddenPoliceAddressRowId').val(roIntId);

                    $("#certificateAddressTable").find('#' + rowId).remove();
                }

                // 	function editApplicantLivedAddressRow(rowId){
                // 		$("#livedAddress").val($("#applicantLivedAddressTable").find('#'+rowId).find('.address').val());
                // 		$("#livedPoliceAreaSelect").val($("#applicantLivedAddressTable").find('#'+rowId).find('.policeAreaVal').val());
                // 		$("#livedPoliceAreaFromSelect").val($("#applicantLivedAddressTable").find('#'+rowId).find('.from').val());
                // 		$("#livedPoliceAreaToSelect").val($("#applicantLivedAddressTable").find('#'+rowId).find('.to').val());

                // 		$("#applicantLivedAddressTable").find('#'+rowId).remove();
                // 	}

                // 	function editSpouseLivedAddressRow(rowId){
                // 		$("#livedAddress").val($("#spouseLivedAddressTable").find('#'+rowId).find('.address').val());
                // 		$("#livedPoliceAreaSelect").val($("#spouseLivedAddressTable").find('#'+rowId).find('.policeAreaVal').val());
                // 		$("#livedPoliceAreaFromSelect").val($("#spouseLivedAddressTable").find('#'+rowId).find('.from').val());
                // 		$("#livedPoliceAreaToSelect").val($("#spouseLivedAddressTable").find('#'+rowId).find('.to').val());

                // 		$("#spouseLivedAddressTable").find('#'+rowId).remove();
                // 	}

                function validateReApplyForm() {
                    var preReferenceNo = $("#previousReferenceNo").val();

                    if (preReferenceNo == '') {
                        alert("Please enter previous reference No.");
                        $("#previousReferenceNo").focus();
                        addRedBorder("previousReferenceNo");
                        return false;
                    } else {
                        return true;
                    }
                }

                function validateVerificationForm() {
                    try {
                        setHiddenFieldValues();

                        var nationalityId = $("#nationalitySelect").val();
                        var dateOfBirth = $("#dateOfBirth").val();
                        var age = $("#applicantAge").val();
                        var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();
                        var dateOfObtained = $("#dateOfcitizenshipObtained").val();

                        var ageWhenMigrate = 0;
                        if (dateOfBirth != '' && dateOfObtained != '') {
                            ageWhenMigrate = getAgeOnTwoDate($("#dateOfBirth").datepicker("getDate"), $("#dateOfcitizenshipObtained").datepicker("getDate"));
                        }

                        var nic = $("#nicNo").val();
                        var newNic = $("#newNicNo").val();
                        var confirmNic = $("#confirmNicNo").val();
                        var confirmNewNic = $("#confirmNewNicNo").val();
                        var passport = $("#passportNo").val().trim();
                        var confirmPassport = $("#confirmPassportNo").val().trim();
                        var countryId = $("#countrySelect").val();

                        var consultantName = $("#highCommisionReferenceName").val();
                        var consultantAddress = $("#highCommisionReferenceAddress").val();

                        //var genuineAge = getAge($("#dateOfBirth").datepicker("getDate"));

                        var nicRegex = /\d{9}[V|v|x|X]/;
                        var newNicRegex = /\d{12}/;

                        if (nationalityId == 0) {
                            alert("Please select Nationality.");
                            $("#nationalitySelect").focus();
                            addRedBorder("nationalitySelect");
                            return false;
                        }

                        if (dateOfBirth == '') {
                            alert("Date of birth is empty");
                            $("#dateOfBirth").focus();
                            addRedBorder("dateOfBirth");
                            return false;
                        }

                        var isValidDob = isDate($.trim($("#dateOfBirth").val()));
                        if (!(isValidDob)) {
                            alert('The value enetered for \'date of birth\' is not valid! The date format should be \'dd/mm/yyyy\'');
                            $("#dateOfBirth").focus();
                            addRedBorder("dateOfBirth");
                            return false;
                        }

                        if (age == '') {
                            alert("age is empty");
                            $("#applicantAge").focus();
                            addRedBorder("applicantAge");
                            return false;
                        }

                        if (getAge($("#dateOfBirth").datepicker("getDate")) != age) {
                            alert("age is not matching with date of birth");
                            $("#applicantAge").focus();
                            addRedBorder("applicantAge");
                            return false;
                        }

                        if (nationalityId != 199 && citizenOfSriLanka == 1 && dateOfObtained == '') {
                            alert("citizenship obtained date is empty");
                            $("#citizenOfSriLankaSelect").focus();
                            addRedBorder("citizenOfSriLankaSelect");
                            return false;
                        }

                        if (nationalityId != 199 && citizenOfSriLanka == 1) {
                            var isValidDob = isDate($.trim($("#dateOfcitizenshipObtained").val()));
                            if (!(isValidDob)) {
                                alert('The value enetered for \'citizenship obtained date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                                $("#dateOfcitizenshipObtained").focus();
                                addRedBorder("dateOfcitizenshipObtained");
                                return false;
                            }
                        }

                        var checkBoxVal = $('#nicValidate :selected').val();
                        var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();

                        if (!(nic == null || nic == '')) {
//                                var nicLength = nic.length;
                            if (!(nicRegex.test(nic))) {
                                alert("Nic No is not valid");
                                $("#nicNo").focus();
                                return false;
                            }
                            if (nic != confirmNic) {
                                alert("NIC numbers do not match");
                                $("#passportNo").focus();
                                addRedBorder("confirmNicNo");
                                return false;
                            }
                        }

                        if (!(newNic == null || newNic == '')) {
//                                var newNicLength = newNic.length;
                            if (!(newNicRegex.test(newNic))) {
                                alert('New NIC is not valid');
                                $("#newNicNo").focus();
                                addRedBorder('newNicNo');
                                return false;
                            }
                            if (newNic != confirmNewNic) {
                                alert("New NIC numbers do not match");
                                $("#confirmNewNicNo").focus();
                                addRedBorder("confirmNewNicNo");
                                return false;
                            }
                        }

                        if (checkBoxVal == '0') {
                            if (citizenOfSriLanka == 1) {

                                if (nationalityId == 199 && age >= 18 && nic == '' && (nic == '' && newNic == '')) {
                                    alert("Please enter  NIC or NEW NIC number");
                                    $("#nicNo").focus();
                                    $("#newNicNo").focus();
                                    addRedBorder("nicNo");
                                    addRedBorder("newNicNo");
                                    return false;
                                }
                                if (nationalityId == 199 && age >= 18
                                    && nic != '' && nic != confirmNic) {
                                    alert("NIC numbers do not match");
                                    $("#confirmNicNo").focus();
                                    return false;
                                } else if (nationalityId == 199 && age >= 18
                                    && newNic != ''
                                    && newNic != confirmNewNic) {
                                    alert("New NIC numbers do not match");
                                    $("#confirmNewNicNo").focus();
                                    return false;
                                }

                                if (!(nic == '' || nic == null)) {
                                    var nicLength = nic.length;
                                        if (!(nicRegex.test(nic))) {
                                            alert("Nic No is not valid");
                                            $("#nicNo").focus();
                                            return false;
                                    }
                                } else if (nationalityId == 199 && age >= 18 && newNic != null || newNic != '') {
                                    var newNicLength = newNic.length;
                                    if (!(newNicRegex.test(newNic))) {
                                        alert('New NIC is not valid');
                                        $("#newNicNo").focus();
                                        addRedBorder('newNicNo');
                                        return false;
                                    }
                                }
                            }
                        }

                        if (passport == '') {
                            alert("Please enter Passport number");
                            $("#passportNo").focus();
                            addRedBorder("passportNo");
                            return false;
                        }

                        if (passport != confirmPassport) {
                            alert("Passport numbers do not match");
                            return false;
                        }

                        if (countryId == 0) {
                            alert("Please select Country.");
                            $("#countrySelect").focus();
                            addRedBorder("countrySelect");
                            return false;
                        }

                        if (consultantName == '') {
                            alert("Please Enter Commission/Embassy/Consultant Name.");
                            $("#highCommisionReferenceName").focus();
                            addRedBorder("highCommisionReferenceName");
                            return false;
                        }

                        if (consultantAddress == '') {
                            alert("Please Enter High Commission/Embassy/Consultant/ to which the certificate should be addressed to.");
                            $("#highCommisionReferenceAddress").focus();
                            addRedBorder("highCommisionReferenceAddress");
                            return false;
                        }


                    } catch (e) {
                        //console.log(e);
                        alert('UI validation Error :' + e)
                        return false;
                    }
                    var conf = confirm('Do you want to proceed with the application?');
                    if (conf) {

                        return true;
                    }
                    return false;
                }

                function setHiddenFieldValues() {

                    $("#nationalityIdVerify").val($("#nationalitySelect").val());
                    $("#nationalityVerify").val($("#nationalitySelect option:selected").text());
                    $("#nationalityId").val($("#nationalitySelect").val());
                    $("#nationality").val($("#nationalitySelect option:selected").text());

                    $("#countryIdVerify").val($("#countrySelect").val());
                    $("#countryVerify").val($("#countrySelect option:selected").text());
                    $("#countryId").val($("#countrySelect").val());
                    $("#countryName").val($("#countrySelect option:selected").text());

                    $("#citizenOfSriLankaVerify").val($("#citizenOfSriLankaSelect").val());
                    $("#citizenOfSriLanka").val($("#citizenOfSriLankaSelect").val());

                    $("#citizenshipObtainedDateVerify").val($("#dateOfcitizenshipObtained").val());
                    $("#citizenshipObtainedDate").val($("#dateOfcitizenshipObtained").val());

                    $("#dateOfBirthVerify").val($("#dateOfBirth").val());
                    $("#dateOfBirth").val($("#dateOfBirth").val());

                    $("#ageVerify").val($("#applicantAge").val());
                    $("#age").val($("#applicantAge").val());

                    $("#highCommissionVerifySelectedValue").val($("#highCommissionVerifySelect option:selected").text());
                    $("#highCommisionReferenceId").val($("#highCommissionVerifySelect").val());
                    $("#highCommisionReference").val($("#highCommisionReferenceName").val());
                    $("#highCommisionReferenceAddressHidden").val($("#highCommisionReferenceAddress").val());

                    var checkBoxVal = $('#nicValidate :selected').val();
                    $('#isLeftSriLankaVerify').val(checkBoxVal);
                }

                function submitButtonStatus() {
                    if ($("#acceptTermsCheck").is(":checked")) {
                        $("#saveApplicationSubmitButton").removeAttr('disabled');
                    } else {
                        $("#saveApplicationSubmitButton").attr('disabled', 'disabled');
                    }
                }

                function validateApplicationForm() {
                    try {
                        setHiddenFieldValues();
                        setApplicationHiddenValues();

                        var applicantNameInFullAsNic = $("#nameInFullAsNic").val();
                        var applicantNameInFullAsPass = $("#nameInFullAsPassport").val();

                        var nationalityId = $("#nationalitySelect").val();
                        var dateOfBirth = $("#dateOfBirth").val();
                        var age = $("#applicantAge").val();
                        var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();
                        var dateOfObtained = $("#dateOfcitizenshipObtained").val();
                        var nicNo = $.trim($('#nicNo').val());
                        var newNicNo = $.trim($('#newNicNo').val());

                        var ageWhenMigrate = 0;
                        if (dateOfBirth != '' && dateOfObtained != '') {
                            ageWhenMigrate = getAgeOnTwoDate($("#dateOfBirth").datepicker("getDate"), $("#dateOfcitizenshipObtained").datepicker("getDate"));
                        }

                        if (nationalityId == 199 && age >= 18 && applicantNameInFullAsNic == '') {
                            alert("Applicant's Name is empty");
                            $("#nameInFullAsPassport").focus();
                            addRedBorder("nameInFullAsPassport");
                            return false;
                        }

                        if (citizenOfSriLanka == 1 && ageWhenMigrate >= 18 && applicantNameInFullAsNic == '') {
                            alert("Applicant's Name is empty");
                            $("#nameInFullAsPassport").focus();
                            addRedBorder("nameInFullAsPassport");
                            return false;
                        }

                        if (applicantNameInFullAsPass == '') {
                            alert("Applicant's Name is empty");
                            $("#nameInFullAsPassport").focus();
                            addRedBorder("nameInFullAsPassport");
                            return false;
                        }

                        var previousCertificate = $("#previousCertificateSelect").val();
                        var certificateIssueStatus = $("#previousCertificateIssueSelect").val();
                        var lastCertificateReferenceNo = $("#lastCertificateReferenceNo").val();
                        var dateOfIssue = $("#lastCertificateIssueDate").val();
                        if (previousCertificate == 1) {
                            if (certificateIssueStatus == 1) {
                                if (lastCertificateReferenceNo == '') {
                                    alert("Please enter last certificate reference No.");
                                    $("#lastCertificateReferenceNo").focus();
                                    addRedBorder("lastCertificateReferenceNo");
                                    return false;
                                } else if (dateOfIssue == '') {
                                    alert("Please enter last certificate issue date");
                                    $("#lastCertificateIssueDate").focus();
                                    addRedBorder("lastCertificateIssueDate");
                                    return false;
                                } else {
                                    var isValid = isDate($.trim($("#lastCertificateIssueDate").val()));
                                    if (!(isValid)) {
                                        alert('The value enetered for date of date \'Previous certificate Date of Issue\' is not valid! The date format should be \'dd/mm/yyyy\'');
                                        addRedBorder("lastCertificateIssueDate");
                                        $("#lastCertificateIssueDate").focus();
                                        return false;
                                    }
                                }

                                var previousCertificateIssueDate = $.trim($("#lastCertificateIssueDate").val());
                                if ((!(previousCertificateIssueDate == '' || previousCertificateIssueDate == null))) {
                                    var isValid = isDate(previousCertificateIssueDate);
                                    if (!(isValid)) {
                                        alert('The value enetered for date \'Previous certificate date of issue\' is not valid! The date format should be \'dd/mm/yyyy\'');
                                        addRedBorder("lastCertificateIssueDate");
                                        $("#lastCertificateIssueDate").focus();
                                        return false;
                                    }
                                }
                            }
                        }

                        var presentAddressSriLanka = $("#presentAddressLanka").val();
                        var presentAddressOverseas = $("#presentAddressOverseas").val();
                        if (presentAddressSriLanka == '' && presentAddressOverseas == '') {
                            alert("Please enter present address in Sri Lanka or Overseas address");
                            $("#presentAddressLanka").focus();
                            //addRedBorder("presentAddressLanka");
                            return false;
                        }

                        //certificate list
                        var certificateAddressTableRows = $("#certificateAddressTable tr").length - 1;
                        if (certificateAddressTableRows == 0) {
                            alert("Please enter at least one address to apply certification.");
                            $("#certificationAddress").focus();
                            addRedBorder("certificationAddress");
                            return false;
                        }

                        var highCommissionIndicateAddress = $("#certificateIndicationAddress").val();
                        if (highCommissionIndicateAddress == '') {
                            alert("Please enter address to indicate in the certificate");
                            $("#certificateIndicationAddress").focus();
                            addRedBorder("certificateIndicationAddress");
                            return false;
                        }

                        var addressLineOne = $("#addressLineOne").val();
                        var city = $("#city").val();
                        var postal = $("#postal").val();
                        var country = $("#postalCountrySelect").val();
                        if (addressLineOne == '') {
                            alert("Address line one is empty");
                            $("#addressLineOne").focus();
                            addRedBorder("addressLineOne");
                            return false;
                        }

                        if (city == '') {
                            alert("city is empty");
                            $("#city").focus();
                            addRedBorder("city");
                            return false;
                            //}else if(postal == ''){
                            //	alert("postal code is empty");
                            //	$("#postal").focus();
                            //	addRedBorder("postal");
                            //	return false;
                        }

                        if (country == 0) {
                            alert("Please select a country.");
                            $("#postalCountrySelect").focus();
                            addRedBorder("postalCountrySelect");
                            return false;
                        }

                        var mobileNo = $("#postalMobile").val();
                        var cMobileNo = $("#postalConfirmMobile").val();
                        var email = $("#postalEmail").val();
                        var cEmail = $("#postalConfirmEmail").val();
                        var mobileRegx = /\d{10}/;
                        var emailRegx = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        var nicRegex = /\d{9}[V|v|x|X]/;

                        if (mobileNo == '' && cMobileNo == '') {
                            alert("mobile No. is empty");
                            $("#postalMobile").focus();
                            addRedBorder("postalMobile");
                            return false;
                            //}else if(email == '' && cEmail == ''){
                            //	alert("email is empty");
                            //	$("#postalEmail").focus();
                            //	addRedBorder("postalEmail");
                            //	return false;
                        }

                        if (!email.match(emailRegx)) {
                            if (!(email == '' || email == null || email == 'undefined')) {
                                alert("email is invalid");
                                $("#postalEmail").focus();
                                addRedBorder("postalEmail");
                                return false;
                            }
                        }

                        if (mobileNo != cMobileNo) {
                            alert("mobile No is mismatching");
                            $("#postalConfirmMobile").focus();
                            addRedBorder("postalConfirmMobile");
                            return false;
                        }

                        if (email != cEmail) {
                            if (!(email == '' || email == null || email == 'undefined' || cEmail == '' || cEmail == null || cEmail == 'undefined')) {
                                alert("email is mismatching");
                                $("#postalConfirmEmail").focus();
                                addRedBorder("postalConfirmEmail");
                                return false;
                            }
                        }

                        if (!(nicNo == '' || nicNo == null)) {
                            var nicLength = nicNo.length;
                            if (nicLength == 10) {
                                if (!(nicRegex.test(nicNo))) {
                                    alert("Nic No is not valid");
                                    $("#nicNo").focus();
                                    return false;
                                }
                            } else if (nicLength != 12) {
                                alert("Nic No is not valid");
                                $("#nicNo").focus();
                                return false;
                            }

                        }

                        var passIssuedDate = $.trim($("#passportIssueDate").val());
                        if (!(passIssuedDate == '' || passIssuedDate == null)) {
                            var isValid = isDate(passIssuedDate);
                            if (!(isValid)) {
                                alert('The value enetered for date of date \'Passport Issue Date\' is not valid! The date format should be \'dd/mm/yyyy\'');
                                addRedBorder("passportIssueDate");
                                $("#passportIssueDate").focus();
                                return false;
                            }
                        } else {
                            var passport = $.trim($("#passportNo").val());
                            if (!(passport == '' || passport == null)) {
                                alert("Please enter the Passport Issue Date");
                                addRedBorder("passportIssueDate");
                                $("#passportIssueDate").focus();
                                return false;
                            }
                        }

                        var selectedOption = $('#selectedName :selected').val();

                        if (selectedOption == 'none') {
                            alert("Please select one of name.");
                            addRedBorder('selectedName');
                            return false;
                        }

                        var passportAttach = $("#passportUploadPath").val();
                        var passportAttachBack = $("#passportUploadPathBack").val();
                        var nicAttach = $("#nicUploadPath").val();
                        var newNicAttach = $("#newNicUploadPath").val();
                        var nicAttachBack = $("#nicUploadPathBack").val();
                        var newNicAttachBack = $("#newNicUploadPathBack").val();
                        var birthCertificateAttach = $("#birthCertificateUpload").val();
                        var birthCertificateAttachBack = $("#birthCertificateUploadBack").val();
                        var affidavitAttach = $("#affidavitUploadPath").val();
                        var letterOfReferenceAttach = $("#uploadLetterOfReferenceUpload").val();
                        var referredThroughBereau = $("#referredThroughBereau").val();
                        if (referredThroughBereau == 1) {
                            if (letterOfReferenceAttach == '') {
                                var status = $("#reapplyStatus").val();
                                if (status == 0) {
                                    alert("Please upload copy of letter of reference");
                                    return false;
                                }
                            }
                        }

                        //condition validation
                        var citizenSriLanka = $("#citizenOfSriLankaSelect").val();
                        var genuineAge = getAge($("#dateOfBirth").datepicker("getDate"));

                        var ageWhenMigrate = 0;
                        ageWhenMigrate = getAgeOnTwoDate($("#dateOfBirth").datepicker("getDate"), $("#dateOfcitizenshipObtained").datepicker("getDate"));

                        if (genuineAge >= 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUploadPath").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadPathBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }
                        } else if (genuineAge < 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUploadPath").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadPathBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }
                        }

                        if (ageWhenMigrate >= 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUploadPath").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadPathBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }
                        } else if (ageWhenMigrate < 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUploadPath").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadPathBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }
                        }

                        if (citizenSriLanka == '1') {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUploadPath").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadPathBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }
                            if (selectedOption == 'passport' && genuineAge >= 18) {
                                if (affidavitAttach == '') {
                                    alert('Please upload the copy of affidavit');
                                    return false;
                                }
                            }

                        }

                        if (citizenSriLanka == '1' && genuineAge >= 18 && nicNo != '') {
                            if (nicAttach == '') {
                                alert("Please upload the front side of the NIC");
                                $("#nicUploadPath").focus();
                                $("#newNicUploadPath").focus();
                                addRedBorder("nicUploadPath");
                                addRedBorder("newNicUploadPath");
                                return false;
                            } else if (nicAttach != '' && nicAttachBack == '') {
                                alert("Please upload the back side of the NIC");
                                $("#nicUploadPathBack").focus();
                                addRedBorder("nicUploadPathBack");
                                return false;
                            }

                        }
                        if (citizenSriLanka == '1' && genuineAge >= 18 && newNicNo != '') {
                            if (newNicAttach == '') {
                                alert("Please upload the front side of the New NIC");
                                $("#newNicUploadPath").focus();
                                addRedBorder("newNicUploadPathBack");
                                return false;
                            }

                            if (newNicAttachBack == '') {
                                alert("Please upload the back side of the New NIC");
                                $("#newNicUploadPathBack").focus();
                                addRedBorder("newNicUploadPathBack");
                                return false;
                            }
                        }

                        //validate payment information
                        var modeOfPayment = $("#paymentModeSelect").val();
                        var amount = $("#totalFee").val();
                        var chequeNo = $("#chequeNo").val();
                        var accountNo = $("#accountNo").val();
                        var accountHolderName = $("#accountHolderName").val();
                        var bookReceiptNo = $("#bookReceiptNo").val();

                        if (amount == '') {
                            alert("Please enter amount.");
                            $("#totalFee").focus();
                            addRedBorder("totalFee");
                            return false;
                        }

                        if (bookReceiptNo == '') {
                            alert("Please enter book receipt No.");
                            $("#bookReceiptNo").focus();
                            addRedBorder("bookReceiptNo");
                            return false;
                        }

                        if (modeOfPayment == 'CASH') {
                            if (bookReceiptNo == '') {
                                alert("Please enter book receipt No.");
                                $("#bookReceiptNo").focus();
                                addRedBorder("bookReceiptNo");
                                return false;
                            }
                        } else if (modeOfPayment == 'CHEQ') {
                            if (chequeNo == '') {
                                alert("Please enter check No.");
                                $("#chequeNo").focus();
                                addRedBorder("chequeNo");
                                return false;
                            } else if (bookReceiptNo == '') {
                                alert("Please enter book receipt No.");
                                $("#bookReceiptNo").focus();
                                addRedBorder("bookReceiptNo");
                                return false;
                            }
                        } else if (modeOfPayment == 'WIRE') {
                            if (accountNo == '') {
                                alert("Please enter account No.");
                                $("#accountNo").focus();
                                addRedBorder("accountNo");
                                return false;
                            } else if (accountHolderName == '') {
                                alert("Please enter account holder name.");
                                $("#accountHolderName").focus();
                                addRedBorder("accountHolderName");
                                return false;
                            }
                        }

                        var value = $("#deliveryTypeSelect").val();
                        var invertNo = $("#foriegnMinistryInvertNo").val();
                        if (value == 'FM') {
                            if (invertNo == '') {
                                alert("Please enter foriegn ministry invert no.");
                                $("#foriegnMinistryInvertNo").focus();
                                addRedBorder("foriegnMinistryInvertNo");
                                return false;
                            }
                        }


                    }
                    catch
                        (e) {
                        console.log(e);
//                        alert('Error :- ' + e)
                        return false;
                    }
                    //$("#countrySelect").attr("disabled", "false");

                    var conf = confirm('Are you sure you want to submit the Application form?');
                    if (conf) {
                        blockUI();
                        $('#saveApplicationSubmitButton').attr('disabled', 'disabled');
                        return true;
                    }
                    return false;
                }

                function setApplicationHiddenValues() {
                    $("#mobileCountryCodeSelected").val('Sri Lanka(+94)');
                    var postalCountry = $("#postalCountrySelect").val();
                    if (postalCountry != 0) {
                        var postalCountryName = $("#postalCountrySelect option:selected").text();
                        $("#postalCountrySelectName").val(postalCountryName);
                    }
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

                //to upload the external reports for blanks
                function uploadFile(fileType) {
                    //check the browser
                    if (!supportAjaxUploadWithProgress) {
                        alert("Please update your browser to upload");
                        return false;
                    }

                    var xhr = new XMLHttpRequest();

                    var nicInput = document.getElementById('nicFileUpload');
                    var nicInputBack = document.getElementById('nicFileUploadBack');
                    var newNicInput = document.getElementById('newNicFileUpload');
                    var newNicInputBack = document.getElementById('newNicFileUploadBack');
                    var passportInput = document.getElementById('passportFileUpload');
                    var passportInputBack = document.getElementById('passportFileUploadBack');
                    var birthCertificateInput = document.getElementById('birthCertificateUpload');
                    var birthCertificateInputBack = document.getElementById('birthCertificateUploadBack');
                    var affidavitInput = document.getElementById('affidavitUpload');
                    var letterOfReference = document.getElementById('uploadLetterOfReferenceUpload');

                    var nicFile = "";
                    var nicFileBack = "";
                    var newNicFile = "";
                    var newNicFileBack = "";
                    var passportFile = "";
                    var passportFileBack = "";
                    var birthCertificateFile = "";
                    var birthCertificateFileBack = "";
                    var affidavitFile = "";
                    var letterOfReferenceFile = "";

                    var fileNicPath = "";
                    var fileNicPathBack = "";
                    var newFileNicPath = "";
                    var newFileNicPathBack = "";
                    var filePassportPath = "";
                    var filePassportPathBack = "";
                    var fileBirthCertificatePath = "";
                    var fileBirthCertificatePathBack = "";
                    var affidavitPath = "";
                    var fileLetterOfReferencePath = "";

                    var nicExtension = "";
                    var nicExtensionBack = "";
                    var newNicExtension = "";
                    var newNicExtensionBack = "";

                    var passportExtension = "";
                    var passportExtensionBack = "";
                    var birthCertificateExtension = "";
                    var birthCertificateExtensionBack = "";
                    var affidavitExtension = "";
                    var letterOfReferenceExtension = "";

                    if (fileType == 'nic') {
                        nicFile = nicInput.files[0];
                        fileNicPath = document.getElementById('nicFileUpload').value;
                        nicExtension = fileNicPath.split(".").pop().trim();
                        if (!validateFileUpload('Nic', fileNicPath,
                                nicExtension, nicFile)) {
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
                    } else if (fileType == 'new_nic') {
                        newNicFile = newNicInput.files[0];
                        newFileNicPath = document.getElementById('newNicFileUpload').value;
                        newNicExtension = newFileNicPath.split(".").pop().trim();
                        if (!validateFileUpload('NewNic', newFileNicPath,
                                newNicExtension, newNicFile)) {
                            $("#ajax_loader_new_nic").hide();
                            return false;
                        }

                    } else if (fileType == 'new_nic_back') {
                        newNicFileBack = newNicInputBack.files[0];
                        newFileNicPathBack = document.getElementById('newNicFileUploadBack').value;
                        newNicExtensionBack = newFileNicPathBack.split(".").pop().trim();
                        if (!validateFileUpload('NewNic', newFileNicPathBack,
                                newNicExtensionBack, newNicFileBack)) {
                            $("#ajax_loader_new_nic_back").hide();
                            return false;
                        }
                    } else if (fileType == 'pas') {
                        passportFile = passportInput.files[0];
                        filePassportPath = document.getElementById('passportFileUpload').value;
                        passportExtension = filePassportPath.split(".").pop().trim();
                        if (!validateFileUpload('Passport', filePassportPath, passportExtension, passportFile)) {
                            $("#ajax_loader_passport").hide();
                            return false;
                        }

                    } else if (fileType == 'pas_back') {
                        passportFileBack = passportInputBack.files[0];
                        filePassportPathBack = document.getElementById('passportFileUploadBack').value;
                        passportExtensionBack = filePassportPathBack.split(".").pop().trim();
                        if (!validateFileUpload('Passport', filePassportPathBack, passportExtensionBack, passportFileBack)) {
                            $("#ajax_loader_passport_back").hide();
                            return false;
                        }

                    } else if (fileType == 'bir') {
                        birthCertificateFile = birthCertificateInput.files[0];
                        fileBirthCertificatePath = document.getElementById('birthCertificateUpload').value;
                        birthCertificateExtension = fileBirthCertificatePath.split(".").pop().trim();
                        if (!validateFileUpload('Birth Certificate', fileBirthCertificatePath, birthCertificateExtension, birthCertificateFile)) {
                            $("#ajax_loader_birth_certificate").hide();
                            return false;
                        }

                    } else if (fileType == 'bir_back') {
                        birthCertificateFileBack = birthCertificateInputBack.files[0];
                        fileBirthCertificatePathBack = document.getElementById('birthCertificateUploadBack').value;
                        birthCertificateExtensionBack = fileBirthCertificatePathBack.split(".").pop().trim();
                        if (!validateFileUpload('Birth Certificate', fileBirthCertificatePathBack, birthCertificateExtensionBack, birthCertificateFileBack)) {
                            $("#ajax_loader_birth_certificate_back").hide();
                            return false;
                        }
                    } else if (fileType == 'affidavit') {
                        affidavitFile = affidavitInput.files[0];
                        affidavitPath = document.getElementById('affidavitUpload').value;
                        affidavitExtension = affidavitPath.split(".").pop().trim();
                        if (!validateFileUpload('Affidavit', affidavitPath, affidavitExtension, affidavitFile)) {
                            $("#ajax_loader_affidavit").hide();
                            return false;
                        }

                    } else if (fileType == 'let') {
                        letterOfReferenceFile = letterOfReference.files[0];
                        fileLetterOfReferencePath = document.getElementById('uploadLetterOfReferenceUpload').value;
                        letterOfReferenceExtension = fileLetterOfReferencePath.split(".").pop().trim();
                        if (!validateFileUpload('Letter of Reference', fileLetterOfReferencePath, letterOfReferenceExtension, letterOfReferenceFile)) {
                            $("#ajax_loader_letter_of_reference").hide();
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
                    } else if (fileType == 'new_nic') {
                        $("#ajax_loader_new_nic").show();
                        formData.append('file', newNicFile);
                        formData.append('fileExtension', newNicExtension);
                        formData.append('fileType', "NEWNICF");
                    } else if (fileType == 'new_nic_back') {
                        $("#ajax_loader_new_nic_back").show();
                        formData.append('file', newNicFileBack);
                        formData.append('fileExtension', newNicExtensionBack);
                        formData.append('fileType', "NEWNICB");
                    } else if (fileType == 'pas') {
                        $("#ajax_loader_passport").show();
                        formData.append('file', passportFile);
                        formData.append('fileExtension', passportExtension);
                        formData.append('fileType', "PASF");
                    } else if (fileType == 'pas_back') {
                        $("#ajax_loader_passport_back").show();
                        formData.append('file', passportFileBack);
                        formData.append('fileExtension', passportExtensionBack);
                        formData.append('fileType', "PASB");
                    } else if (fileType == 'bir') {
                        $("#ajax_loader_birth_certificate").show();
                        formData.append('file', birthCertificateFile);
                        formData.append('fileExtension', birthCertificateExtension);
                        formData.append('fileType', "BIRF");
                    } else if (fileType == 'bir_back') {
                        $("#ajax_loader_birth_certificate_back").show();
                        formData.append('file', birthCertificateFileBack);
                        formData.append('fileExtension', birthCertificateExtensionBack);
                        formData.append('fileType', "BIRB");
                    } else if (fileType == 'affidavit') {
                        $("#ajax_loader_birth_affidavit").show();
                        formData.append('file', affidavitFile);
                        formData.append('fileExtension', affidavitExtension);
                        formData.append('fileType', "AFFIDAVIT");
                    } else if (fileType == 'let') {
                        $("#ajax_loader_letter_of_reference").show();
                        formData.append('file', letterOfReferenceFile);
                        formData.append('fileExtension', letterOfReferenceExtension);
                        formData.append('fileType', "LETR");
                    }

                    formData.append('uploadType', "UPLOAD");

                    xhr.open('POST', 'uploadFile.action', true);
                    xhr.send(formData);

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState == 4 && xhr.status == 200) {
                            var jsonData = JSON.parse(xhr.responseText);

                            fileType = jsonData.fileType;

                            if (fileType == 'NICF') {
                                var nicFileName = jsonData.fileName;
                                if (nicFileName != '') {
                                    $("#nicUploadPath").val(nicFileName);
                                }
                                $("#ajax_loader_nic").hide();
                                $("#upload_complete_nic").show();

                            } else if (fileType == 'NICB') {
                                var nicFileNameBack = jsonData.fileName;
                                if (nicFileNameBack != '') {
                                    $("#nicUploadPathBack").val(nicFileNameBack);
                                }
                                $("#ajax_loader_nic_back").hide();
                                $("#upload_complete_nic_back").show();

                            } else if (fileType == 'NEWNICF') {
                                var newNicFileName = jsonData.fileName;
                                if (newNicFileName != '') {
                                    $("#newNicUploadPath").val(newNicFileName);
                                }
                                $("#ajax_loader_new_nic").hide();
                                $("#upload_complete_new_nic").show();

                            } else if (fileType == 'NEWNICB') {
                                var newNicFileNameBack = jsonData.fileName;
                                if (newNicFileNameBack != '') {
                                    $("#newNicUploadPathBack").val(newNicFileNameBack);
                                }
                                $("#ajax_loader_new_nic_back").hide();
                                $("#upload_complete_new_nic_back").show();

                            } else if (fileType == 'PASF') {
                                var passFileName = jsonData.fileName;
                                if (passFileName != '') {
                                    $("#passportUploadPath").val(passFileName);
                                }
                                $("#ajax_loader_passport").hide();
                                $("#upload_complete_passport").show();

                            } else if (fileType == 'PASB') {
                                var passFileNameBack = jsonData.fileName;
                                if (passFileNameBack != '') {
                                    $("#passportUploadPathBack").val(passFileNameBack);
                                }
                                $("#ajax_loader_passport_back").hide();
                                $("#upload_complete_passport_back").show();

                            } else if (fileType == 'BIRF') {
                                var birthCertificateFileName = jsonData.fileName;
                                if (birthCertificateFileName != '') {
                                    $("#birthCertificateUploadPath").val(birthCertificateFileName);
                                }
                                $("#ajax_loader_birth_certificate").hide();
                                $("#upload_complete_birth_certificate").show();

                            } else if (fileType == 'BIRB') {
                                var birthCertificateFileNameBack = jsonData.fileName;
                                if (birthCertificateFileNameBack != '') {
                                    $("#birthCertificateUploadPathBack").val(birthCertificateFileNameBack);
                                }
                                $("#ajax_loader_birth_certificate_back").hide();
                                $("#upload_complete_birth_certificate_back").show();

                            } else if (fileType == 'AFFIDAVIT') {
                                var affidavitFileName = jsonData.fileName;
                                if (affidavitFileName != '') {
                                    $("#affidavitUploadPath").val(affidavitFileName);
                                }
                                $("#ajax_loader_affidavit").hide();
                                $("#upload_complete_affidavit").show();

                            } else if (fileType == 'LETR') {
                                var letterOfReferenceFileName = jsonData.fileName;
                                if (letterOfReferenceFileName != '') {
                                    $("#uploadLetterOfReferencePath").val(letterOfReferenceFileName);
                                }
                                $("#ajax_loader_letter_of_reference").hide();
                                $("#upload_complete_letter_of_reference").show();

                            } else {
                                $("#ajax_loader_nic").hide();
                                $("#ajax_loader_nic_back").hide();
                                $("#ajax_loader_new_nic").hide();
                                $("#ajax_loader_new_nic_back").hide();
                                $("#ajax_loader_passport").hide();
                                $("#ajax_loader_passport_back").hide();
                                $("#ajax_loader_birth_certificate").hide();
                                $("#ajax_loader_birth_certificate_back").hide();
                                $("#ajax_loader_affidavit").hide();
                                $("#ajax_loader_letter_of_reference").hide();
                                alert('File upload failed, please check the file size and reupload!');
                            }
                        }

                    }
                }

                function validateFileUpload(uploadingFile, path, extension, file) {
                    var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
                    if (path == '') {
                        alert("please select a file before upload for " + uploadingFile);
                        return false;
                    } else if (extension != "pdf" && extension != "PDF"
                        && extension != "doc" && extension != "DOC"
                        && extension != "docx" && extension != "DOCX"
                        && extension != "png" && extension != "PDF"
                        && extension != "jpg" && extension != "jpg") {
                        alert("invalid " + uploadingFile + " file format");
                        return false;
                    } else if (file.size > maximumFileLimit) {
                        alert(uploadingFile
                            + " file size is too large to upload, the maximum file size limit is "
                            + maximumFileLimit + " bytes!");
                        return false;
                    }
                    return true;
                }
                function reApplyStatus() {
                    var status = $("#reapplyStatus").val();
                    if (status == 0) {

                    } else if (status == 1) {
                        reApplyOperation();
                    }
                }

                function reApplyOperation() {
                    getSelectedCommissioner();
                    $("#nicNo").attr("readonly", "true");
                    $("#newNicNo").attr("readonly", "true");
                    $("#passportNo").attr("readonly", "true");
                    $("#confirmNicNo").attr("readonly", "true");
                    $("#confirmNewNicNo").attr("readonly", "true");
                    $("#confirmPassportNo").attr("readonly", "true");

                    $("#nameInFull").attr("readonly", "true");
                    $("#nationality").attr("readonly", "true");
                    $("#dateOfcitizenshipObtained").attr("readonly", "true");
                    $("#dateOfBirth").attr("readonly", "true");

                    $("#nameInFullAsNic").attr("readonly", "true");
                    $("#nameInFullAsPassport").attr("readonly", "true");
                    $("#passportIssueDate").attr("readonly", "true");
                    $("#sexSelect").attr("readonly", "true");

                    $("#previousCertificateSelect").attr("readonly", "true");
                    $("#certificateAddressTableAddButton").attr("readonly", "true");

                    $("#passportFileUpload").attr("readonly", "true");
                    $("#passportFileUploadBack").attr("readonly", "true");
                    $("#nicFileUpload").attr("readonly", "true");
                    $("#nicFileUploadBack").attr("readonly", "true");
                    $("#birthCertificateUpload").attr("readonly", "true");
                    $("#birthCertificateUploadBack").attr("readonly", "true");
                    $("#affidavitUpload").attr("readonly", "true");
                    $("#uploadLetterOfReferenceUpload").attr("readonly", "true");

                    $("#referredThroughBereau").attr("readonly", "true");

                    $("#spouseFullName").attr("readonly", "true");
                    $("#spouseNationality").attr("readonly", "true");
                    $("#spousePassport").attr("readonly", "true");
                    $("#spouseNic").attr("readonly", "true");

                    $("#certificateAddressTableAddButton").attr("readonly", "true");
                    $("#verifySubmitButton").attr("readonly", "true");

                    //$("#bookReceiptNo").attr("readonly", "true");

                    $("#verifyButtonDiv").hide();

                    $("#addressFormDiv").hide();

                    $("#uploadFormDiv").hide();

                    var passportAttach = $("#passportUploadPath").val();
                    var passportAttachBack = $("#passportUploadPathBack").val();
                    var nicAttach = $("#nicUploadPath").val();
                    var nicAttachBack = $("#nicUploadPathBack").val();
                    var birthCertificateAttach = $("#birthCertificateUpload").val();
                    var letterOfReferenceAttach = $("#uploadLetterOfReferenceUpload").val();

                    if (letterOfReferenceAttach == '') {
                        if ($("#uploadLetterOfReferenceUpload").is(":visible")) {
                            $("#uploadLetterOfReferenceUpload").val("no_upload");
                        }

                    }

                    if (passportAttach == '') {
                        if ($("#passportUploadPath").is(":visible")) {
                            $("#passportUploadPath").val("no_upload");
                        }
                    }

                    if (passportAttachBack == '') {
                        if ($("#passportUploadPathBack").is(":visible")) {
                            $("#passportUploadPathBack").val("no_upload");
                        }
                    }
                }

                function addRedBorder(id) {
                    $("#" + id).css("border", "solid 1px red");
                }

                function getAge(birth) {
                    if (!(birth == null)) {
                        var ageDifMs = Date.now() - birth.getTime();
                        var temp = Math.floor(ageDifMs / 1000);
                        var years = Math.floor(temp / 31536000);
                        return years;
                    }
                    return 0;
                }

                function getAgeOnTwoDate(birth, migrate) {
                    if (!(birth == null || migrate == null)) {
                        var ageDifMs = migrate.getTime() - birth.getTime();
                        var temp = Math.floor(ageDifMs / 1000);
                        var years = Math.floor(temp / 31536000);
                        return years;
                    }
                }

                function setVisibleLetterOfReferenceUpload() {
                    var employment = $("#referredThroughBereau").val();
                    if (employment == 1) {
                        $("#letterOfReferenceUploadDiv").show(500);
                    } else {
                        $("#letterOfReferenceUploadDiv").hide(500);
                    }
                }

                function getCommissioners() {
                    var commisionerId = $("#highCommisionReferenceId").val();
                    $.ajax({
                        url: "loadCommissionersByCountry",
                        data: {countryId: $("#countrySelect").val()},
                        dataType: "json",
                        success: function (data) {
                            var $el = $("#highCommissionVerifySelect");
                            $el.empty();
                            for (var key in data.highCommissionList) {
                                if (commisionerId == data.highCommissionList[key].id) {
                                    $el.append("<option selected='selected' value=" + data.highCommissionList[key].id + ">" + data.highCommissionList[key].commissionEmbassyConsultantName + " - " + data.highCommissionList[key].commissionEmbassyConsultantAddress + "</option>");
                                } else {
                                    $el.append("<option value=" + data.highCommissionList[key].id + ">" + data.highCommissionList[key].commissionEmbassyConsultantName + " - " + data.highCommissionList[key].commissionEmbassyConsultantAddress + "</option>");
                                }
                            }

                            if (commisionerId == 0) {
                                $el.append("<option selected='selected' value=\"0\">Other</option>");
                                $("#highCommisionReferenceName").attr('readonly', false);
                                $("#highCommisionReferenceAddress").attr('readonly', false);
//                                $("#highCommisionReferenceName").val('');
//                                $("#highCommisionReferenceAddress").val('');

                            } else {
                                getSelectedCommissioner();
                                $el.append("<option value=\"0\">Other</option>");
                            }

                        }
                    });
                }

                function getSelectedCommissioner() {
                    var selectedVal = $("#highCommissionVerifySelect").val();
                    if (selectedVal == "0") {
                        if (!($("#verificationStatus").val() == 0)) {
                            $("#highCommisionReferenceName").val('');
                            $("#highCommisionReferenceAddress").val('');
                        }
                        $("#highCommisionReferenceName").attr('readonly', false);
                        $("#highCommisionReferenceAddress").attr('readonly', false);
                    } else {
                        $("#highCommisionReferenceName").attr('readonly', true);
                        $("#highCommisionReferenceAddress").attr('readonly', true);

                        $.ajax({
                            url: "loadCommissionerById",
                            data: {commissionerId: selectedVal},
                            dataType: "json",
                            success: function (data) {
                                $("#highCommisionReferenceName").val(data.commissionerVO.addressee + "," + data.commissionerVO.commissionEmbassyConsultantName);
                                $("#highCommisionReferenceAddress").val(data.commissionerVO.commissionEmbassyConsultantAddress);
                            }
                        });
                    }
                }

                $('#confirmNicNo').bind("cut copy paste", function (e) {
                    e.preventDefault();
                });

                $('#confirmNewNicNo').bind("cut copy paste", function (e) {
                    e.preventDefault();
                });

                $('#confirmPassportNo').bind("cut copy paste", function (e) {
                    e.preventDefault();
                });

                $('#postalConfirmMobile').bind("cut copy paste", function (e) {
                    e.preventDefault();
                });

                $('#postalConfirmEmail').bind("cut copy paste", function (e) {
                    e.preventDefault();
                });

                function changeStatuscCtizenOfSriLanka() {
                    var val = $("#citizenOfSriLankaSelect").val();
                    if (val == 0) {
                        $("#dateOfcitizenshipObtained").attr('readonly', true);
                        $("#dateOfcitizenshipObtained").datepicker("destroy");
                    } else {
                        $("#dateOfcitizenshipObtained").attr('readonly', false);
                        $('#dateOfcitizenshipObtained').datepicker({
                            changeYear: true,
                            yearRange: "-120:+0",
                            changeMonth: true,
                            maxDate: "+0d",
                            dateFormat: "dd/mm/yy"
                        });
                    }
                }

                function changeReadOnlyStatus(id, status) {
                    if (status == 0) {
                        $("#" + id).attr('readonly', false);
                    } else if (status == 1) {
                        $("#" + id).attr('readonly', true);
                    }
                }

                function resetToBlank(id) {
                    $("#" + id).val('');
                }

                function manualPaymentFormValidation() {
                    var paymentMode = $("#paymentModeSelect").val();

                    if (paymentMode == 'CASH') {
                        changeReadOnlyStatus('bookReceiptNo', 0);

                        changeReadOnlyStatus('chequeNo', 1);
                        changeReadOnlyStatus('accountNo', 1);
                        changeReadOnlyStatus('accountHolderName', 1);

                        resetToBlank('chequeNo');
                        resetToBlank('accountNo');
                        resetToBlank('accountHolderName');

                    } else if (paymentMode == 'CHEQ') {
                        changeReadOnlyStatus('bookReceiptNo', 0);
                        changeReadOnlyStatus('chequeNo', 0);
                        changeReadOnlyStatus('accountNo', 1);
                        changeReadOnlyStatus('accountHolderName', 1);

                        resetToBlank('bookReceiptNo');
                        resetToBlank('accountNo');
                        resetToBlank('accountHolderName');

                    } else if (paymentMode == 'WIRE') {
                        changeReadOnlyStatus('bookReceiptNo', 0);
                        changeReadOnlyStatus('chequeNo', 1);
                        changeReadOnlyStatus('accountNo', 0);
                        changeReadOnlyStatus('accountHolderName', 0);

                        resetToBlank('bookReceiptNo');
                        resetToBlank('chequeNo');
                    }
                }

                function validateDeliveryType() {
                    var value = $("#deliveryTypeSelect").val();
                    if (value == 'FM') {
                        $("#foriegnMinistryInvertNo").attr('readonly', false);
                    } else {
                        $("#foriegnMinistryInvertNo").val('');
                        $("#foriegnMinistryInvertNo").attr('readonly', true);
                    }
                }

                function previousCertificateIssue() {
                    var issueStatus = $("#previousCertificateIssueSelect").val();
                    if (issueStatus == 1) {
                        changeReadOnlyStatus('lastCertificateReferenceNo', 0);
                        changeReadOnlyStatus('lastCertificateIssueDate', 0);
                        $('#lastCertificateIssueDate').datepicker({
                            changeYear: true,
                            yearRange: "-120:+0",
                            changeMonth: true,
                            maxDate: "+0d",
                            minDate: $('#dateOfBirth').datepicker("getDate"),
                            dateFormat: "dd/mm/yy"
                        });
                    } else {
                        changeReadOnlyStatus('lastCertificateReferenceNo', 1);
                        changeReadOnlyStatus('lastCertificateIssueDate', 1);
                        $("#lastCertificateIssueDate").datepicker("destroy");
                    }
                }

                function resetForm() {
                    window.location = "application.action";
                }

                function wereYouCitizenOfSriLankaShowHide() {
                    var nationality = $("#nationalitySelect").val();
                    var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();

                    var age = $('#applicantAge').val();

                    if (nationality == 199 || citizenOfSriLanka == 1) {
                        if (age >= 18) {
                            $('#slSelection').show();
                            $('#forieginSelection').remove();
                        } else {
                            $('#slSelection').remove();
                            $('#forieginSelection').show();
                        }

                        $('#nicFileUploadDiv').show()

                        var selectedOption = $('#selectedName :selected').val();
                        if (selectedOption == 'passport') {
                            $('#affidavitDivId').show();
                        }
                    } else {
                        $('#slSelection').remove();
                        $('#forieginSelection').show();
                        $('#nicFileUploadDiv').hide();
                        $('#affidavitDivId').hide();
                    }


                    if (nationality == 199) {
                        if (!($("#verificationStatus").val() == 0)) {
                            $("#citizenOfSriLankaSelect").val(1);
                        }
                        $("#sriLankaCitizenDiv").hide(400);


                    } else {
                        if (!($("#verificationStatus").val() == 0)) {
                            $("#citizenOfSriLankaSelect").val(1);
                        }
                        $("#sriLankaCitizenDiv").show(400);

                    }

                }


            </script>

    </body>
    </html>
</s:i18n>