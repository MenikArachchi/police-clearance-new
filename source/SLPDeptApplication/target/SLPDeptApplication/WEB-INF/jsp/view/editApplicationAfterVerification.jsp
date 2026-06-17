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
        <title>Edit Application After Verification</title>
    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

            <jsp:include page="../common/header.jsp"/>

        <div id="es-content">
            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Edit Application After Verification</c:set>
            <jsp:include page="../common/commonPage.jsp">
                <jsp:param name="title" value="${pageTitle}"/>
            </jsp:include>

            <s:form theme="simple" class="form-horizontal" id="applicationRegistrationForm" role="form" method="post"
                    action="updateApplicationAfterVerification.action" onsubmit="return validateApplicationForm()">
            <input type="hidden" name="applicationVO.applicationId" value="${applicationVO.applicationId}"/>
            <input type="hidden" name="applicationVO.transactionId" value="${transactionVO.transactionId}"/>
            <input type="hidden" name="transactionVO.transactionId" value="${transactionVO.transactionId}"/>

            <input type="hidden" name="applicationVO.nationality" value="${applicationVO.nationality}"
                   id="nationalityTextInput"/>
            <input type="hidden" name="applicationVO.countryName" value="${applicationVO.countryName}"
                   id="countryNameTextInput"/>

            <div class="middle_content">

                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="applicationType" class="control-label"><b>Application Type</b></label>
                        </div>
                        <div class="col-sm-6">
                            <c:choose>
                                <c:when test="${empty applicationVO.previousReferenceNo}">
                                    <div class="input_div">New Application</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input_div">Renewal</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div style="clear: both;"></div>

                <c:if test="${! empty applicationVO.previousReferenceNo}">
                    <br/><br/>
                    <div class="col-lg-10">
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label for="previousReferenceNo" class="control-label"><b>Previous Application Reference
                                    No.</b></label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input_div"><c:out
                                        value="${applicationVO.previousReferenceNo}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <div style="clear: both;"></div>
                </c:if>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

            </div>

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
                            <div class="col-sm-6">
                                <label for="citizenOfSriLankaSelect" class="control-label"><b>Were you a citizen of Sri
                                    Lanka?</b></label>
                            </div>
                            <div class="col-sm-4">
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
                                <c:set var="dateOfcitizenshipObtained"><fmt:formatDate
                                        value="${applicationVO.citizenshipObtainedDate}" pattern="dd/MM/yyyy"/></c:set>
                                <input type="text" name="dateOfCitizenshipObtained" value="${dateOfcitizenshipObtained}"
                                       id="dateOfcitizenshipObtained" class="form-control"/>
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
                            <c:set var="dateOfBirth"><fmt:formatDate value="${applicationVO.dateOfBirth}"
                                                                     pattern="dd/MM/yyyy"/></c:set>
                            <input type="text" name="dateOfBirth" id="dateOfBirth" value="${dateOfBirth}"
                                   class="form-control" readonly="readonly"/>
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
                <hr/>
                <div style="clear:both;"></div>
            </div>


            <div class="middle_content">
                <div class="col-lg-6"></div>
                <div class="col-lg-6">
                    <div style="float: center;">
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label for="referenceNo" class="control-label"><b>Reference No.</b></label>
                            </div>
                            <div class="col-sm-6">
                                <s:textfield name="applicationVO.referenceNo" id="referenceNo" cssClass="form-control"
                                             readonly="true" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>


                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="nicNo" class="control-label"><b>NIC No:</b></label>
                        </div>
                        <div class="col-sm-6">
                            <s:textfield name="applicationVO.nic" id="nicNo" cssClass="form-control"
                                         autocomplete="off" maxlength="10"/>
                            <input type="hidden" value="${applicationVO.nic}" id="hiddenNicId"/>
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
                            <input type="hidden" value="${applicationVO.newNic}" id="hiddenNewNicId"/>
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
                            <input type="hidden" value="${applicationVO.passport}" id="hiddenPassportId"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>


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
                                        <option value="0">Other</option>
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
                                        class="mandatory_field">*</span>High Commission/Embassy/Consulate Name (Adressee
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
                                         id="highCommisionReferenceAddress" cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                </div>


            </div>


            <div style="clear:both;"></div>
            <hr/>
            <div style="clear:both;"></div>


            <div class="middle_content">

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="nameInFullAsNic" class="control-label"><b>Applicant's Name in full as in the
                                NIC:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="applicationVO.applicantNameAsNic" id="nameInFullAsNic"
                                         cssClass="form-control" autocomplete="off"/>
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


                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-sm-6" style="width:51.1%">
                            <label for="sexSelect" class="control-label"><b><span
                                    class="mandatory_field">*</span>Sex:</b></label>
                        </div>
                        <div class="col-sm-5" style="width:48.9%">
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
                        <div class="col-sm-6">
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
                <div style="clear:both;"></div>
                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="highCommisionReference" class="control-label"><b>Which name should be printed in
                                certificate? :</b></label>
                        </div>
                        <div class="col-sm-3">
                            <c:choose>
                                <c:when test="${applicationVO.nationalityId == 199 || applicationVO.citizenOfSriLanka == 1}">
                                    <select name="applicationVO.selectedNameOption" id="selectedName"
                                            class="form-control" onchange="isWantAffidavit();">
                                        <c:choose>
                                            <c:when test="${applicationVO.selectedNameOption == 'nic'}">
                                                <option value="nic" selected="selected">NIC</option>
                                                <option value="passport">Passport</option>
                                            </c:when>
                                            <c:when test="${applicationVO.selectedNameOption == 'passport'}">
                                                <option value="passport" selected="selected">Passport</option>
                                                <option value="nic">NIC</option>
                                            </c:when>
                                        </c:choose>

                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="applicationVO.selectedNameOption" id="selectedName"
                                            class="form-control" onchange="isWantAffidavit();">
                                        <c:choose>
                                            <c:when test="${applicationVO.selectedNameOption == 'nic'}">
                                                <option value="nic" selected="selected">NIC</option>
                                            </c:when>
                                            <c:when test="${applicationVO.selectedNameOption == 'passport'}">
                                                <option value="passport" selected="selected">Passport</option>
                                            </c:when>
                                        </c:choose>

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
                            <label for="passportIssueDate" class="control-label"><b><span
                                    class="mandatory_field">*</span>Passport Issue Date:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <c:set var="passportIssueDate"><fmt:formatDate value="${applicationVO.passportIssueDate}"
                                                                           pattern="dd/MM/yyyy"/></c:set>
                            <input type="text" name="passportIssueDate" id="passportIssueDate"
                                   value="${passportIssueDate}" class="form-control" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="presentAddressLanka" class="control-label"><b>Present Address in Sri Lanka:</b></label>
                        </div>
                        <div class="col-sm-9">
                            <s:textfield name="applicationVO.presentAddressLocal" id="presentAddressLanka"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="presentAddressOverseas" class="control-label"><b>Present Address (Overseas):</b></label>
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
                        <div class="col-sm-6" style="width: 51.4%">
                            <label for="purposeSelect" class="control-label"><b><span class="mandatory_field">*</span>Purpose</b></label>
                        </div>
                        <div class="col-sm-5" style="width:48.6%">
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
                                    class="mandatory_field">*</span>Have you applied for a certificate previously ?</b></label>
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
                                        <c:choose>
                                            <c:when test="${country.id == applicationVO.previousCertificateCountryId}">
                                                <option value="${country.id}"
                                                        selected="selected">${country.countryName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${country.id}">${country.countryName}</option>
                                            </c:otherwise>
                                        </c:choose>
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
                                <c:set var="previousCertificateIssueDateVar">
                                    <fmt:formatDate value="${applicationVO.previousCertificateIssueDate}"
                                                    pattern="dd/MM/yyyy"/>
                                </c:set>
                                <input type="text" name="previousCertificateIssueDate" id="lastCertificateIssueDate"
                                       value="${previousCertificateIssueDateVar}" class="form-control"
                                       readonly="readonly"/>
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

                <div id="addressFormDiv">

                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="certificationAddress" class="control-label"><b><span
                                        class="mandatory_field">*</span>Address:</b></label>
                            </div>
                            <div class="col-sm-9">
                                <s:hidden name="" id="certificationAddressId" cssClass="form-control"
                                          autocomplete="off"/>
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
                                <s:textfield name="" id="certificationPoliceAreaFromSelect" cssClass="form-control"
                                             readonly="true"/>
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
                                <s:textfield name="" id="certificationPoliceAreaToSelect" cssClass="form-control"
                                             readonly="true"/>
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
                    <c:forEach var="applicantAddress" items="${certificateAddressList}" varStatus="counter">
                        <tr id="cad${counter.index}" class="appendedRowAddress">
                            <td>
                                <input name="certificateAddressList[${counter.index}].address" style="width: 100%;"
                                       type="text" readonly="readonly" class="address form_control"
                                       value="${applicantAddress.address}"/>
                                <input name="certificateAddressList[${counter.index}].addressId" style="width: 100%;"
                                       type="hidden" readonly="readonly" class="addressId form_control"
                                       value="${applicantAddress.addressId}"/>
                            </td>
                            <td><input name="certificateAddressList[${counter.index}].policeAreaId" style="width: 100%;"
                                       type="hidden" readonly="readonly" class="policeAreaVal form_control"
                                       value="${applicantAddress.policeAreaId}"/>
                                <input type="text" name="certificateAddressList[${counter.index}].policeArea"
                                       style="width: 100%;" readonly="readonly" class="policeArea form_control"
                                       value="${applicantAddress.policeArea}"/></td>
                            <td>
                                <c:set var="applicationCertificateFromDate"><fmt:formatDate
                                        value="${applicantAddress.fromDate}" pattern="dd/MM/yyyy"/> </c:set>
                                <input name="certificateAddressList[${counter.index}].fromDateStr" style="width: 100%;"
                                       id="addedFromDate_${counter.index}" type="text" readonly="readonly"
                                       class="from form_control" value="${applicationCertificateFromDate}"/>
                            </td>
                            <td>
                                <c:set var="applicationCertificateToDate"><fmt:formatDate
                                        value="${applicantAddress.toDate}" pattern="dd/MM/yyyy"/> </c:set>
                                <input name="certificateAddressList[${counter.index}].toDateStr" style="width: 100%;"
                                       id="addedToDate_${counter.index}" type="text" readonly="readonly"
                                       class="to form_control" value="${applicationCertificateToDate}"/>
                            </td>
                            <td><img class="remRow" src="images/edit.png" style="cursor:pointer;"
                                     onclick="editCertificateAddressRow('cad${counter.index}')"></td>
                            <td><img class="remRow" src="images/cancel.png" style="cursor:pointer;"></td>
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

                <p class="mandatory_field">Note : Please enter your correct present address. Your certificate will be
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
                                                <%--<c:when test="${applicationVO.mobileCountryCodeId == country.id}">--%>
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
                                <p>+94</p>
                            <%--<s:hidden name="applicationVO.mobileCountryCode" id="mobileCountryCodeSelected"/>--%>
                            <s:hidden name="applicationVO.mobileCountryCode" id="mobileCountryCodeSelected" value="Sri Lanka(+94)"/>
                            <s:hidden name="applicationVO.mobileCountryCodeId" id="mobileCountryCodeSelectedVal" value="199"/>
                        </div>
                        <div class="col-sm-6">
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
                            <s:textfield name="applicationVO.spouseNic" id="spouseNic" maxLength="10"
                                         cssClass="form-control" autocomplete="off"/>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

                <div id="paymentModeDiv">
                    <p>Mode of Payment (Only for manual submission)</p>

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
                                                    <option value="ONLN">Online</option>
                                                </c:when>
                                                <c:when test="${transactionVO.paymentMode == 'CHEQ'}">
                                                    <option value="CASH">Cash</option>
                                                    <option value="CHEQ" selected="selected">Cheque</option>
                                                    <option value="WIRE">Wire Transfer</option>
                                                    <option value="ONLN">Online</option>
                                                </c:when>
                                                <c:when test="${transactionVO.paymentMode == 'WIRE'}">
                                                    <option value="CASH">Cash</option>
                                                    <option value="CHEQ">Cheque</option>
                                                    <option value="WIRE" selected="selected">Wire Transfer</option>
                                                    <option value="ONLN">Online</option>
                                                </c:when>
                                                <c:when test="${transactionVO.paymentMode == 'ONLN'}">
                                                    <option value="CASH">Cash</option>
                                                    <option value="CHEQ">Cheque</option>
                                                    <option value="WIRE">Wire Transfer</option>
                                                    <option value="ONLN" selected="selected">Online</option>
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
                                            <option value="ONLN">Online</option>
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
                            <div class="col-sm-3" id="passportAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.passportAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.passportAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('PASF')"><img src="images/cancel.png" height="20px"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
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
                            <div class="col-sm-3" id="passportBackAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.passportBackAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.passportBackAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('PASB')"><img src="images/cancel.png" height="20px"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <p class="mandatory_field" style="padding-left: 12px; padding-bottom: 20px;">(Note : Upload both
                            Personal detail page and countries allowed page.)</p>
                    </div>
                    <div style="clear:both;"></div>

                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPath" class="control-label"><b>NIC front side:<i>(Maximum file size
                                    is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="nicFileUpload" name="nicFileUpload" type="file" style=""/>
                                <s:hidden name="applicationVO.nicAttachPath" id="nicUploadPath"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('nic');" class="btn btn-primary es-buttton"
                                       value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_nic" style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_nic" style="display:none;"/>
                            </div>
                            <div class="col-sm-3" id="nicAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.nicAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.nicAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('NICF')"><img src="images/cancel.png" height="20px" id="nicRemove"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPathBack" class="control-label"><b>NIC back side:<i>(Maximum file
                                    size is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="nicFileUploadBack" name="nicFileUploadBack" type="file" style=""/>
                                <s:hidden name="applicationVO.nicBackAttachPath" id="nicUploadPathBack"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('nic_back');"
                                       class="btn btn-primary es-buttton" value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_nic_back" style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_nic_back" style="display:none;"/>
                            </div>
                            <div class="col-sm-3" id="nicBackAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.nicBackAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.nicBackAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('NICB')"><img src="images/cancel.png" height="20px" id="nicBackRemove"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPath" class="control-label"><b>New NIC front side:<i>(Maximum file
                                    size is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="newNicFileUpload" name="newNicFileUpload" type="file" style=""/>
                                <s:hidden name="applicationVO.newNicAttachPath" id="newNicUploadPath"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('new_nic');" class="btn btn-primary es-buttton"
                                       value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_new_nic" style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_new_nic" style="display:none;"/>
                            </div>
                            <div class="col-sm-3" id="newNicAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.newNicAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.newNicAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('NEWNICF')"><img src="images/cancel.png" height="20px" id="newNicRemove"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin:1px 0px">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="nicUploadPathBack" class="control-label"><b>New NIC back side:<i>(Maximum
                                    file size is 250 Kb)</i></b></label>
                            </div>
                            <div class="col-sm-3">
                                <input id="newNicFileUploadBack" name="newNicFileUploadBack" type="file" style=""/>
                                <s:hidden name="applicationVO.newNicBackAttachPath" id="newNicUploadPathBack"
                                          cssClass="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" onclick="uploadFile('new_nic_back');"
                                       class="btn btn-primary es-buttton" value="Upload"/>
                                <img src="images/ajax-loader.gif" id="ajax_loader_new_nic_back" style="display:none;"/>
                                <img src="images/right_green.jpg" id="upload_complete_new_nic_back"
                                     style="display:none;"/>
                            </div>
                            <div class="col-sm-3" id="newNicBackAttachPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.newNicBackAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.newNicBackAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('NEWNICB')"><img src="images/cancel.png" height="20px" id="newNicBackRemove"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <p class="mandatory_field" style="padding-left: 12px; padding-bottom: 20px;">(Note : Both
                            sides.)</p>
                    </div>
                    <div style="clear:both;"></div>

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
                            <div class="col-sm-3" id="birthCertificateUploadPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.birthCertificatePath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.birthCertificatePath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('BIRF')"><img src="images/cancel.png" height="20px"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
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
                            <div class="col-sm-3" id="birthCertificateUploadPathBackDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.birthCertificateBackPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.birthCertificateBackPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('BIRB')"><img src="images/cancel.png" height="20px"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <p class="mandatory_field" style="padding-left: 12px; padding-bottom: 20px;">(Note : Sri Lankan
                            citizen below 18. Both sides.)</p>
                    </div>
                    <div style="clear:both;"></div>

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
                            <div class="col-sm-3" id="affidavitUploadPathDisplay">
                                <c:choose>
                                    <c:when test="${!empty applicationVO.affidavitAttachPath}">
                                        <a href="policeFileFinder.htm?fileName=${applicationVO.affidavitAttachPath}"
                                           target="_blank"><img src="images/open.png" height="25px"/></a>
                                        &nbsp;&nbsp;<a href="javascript:removeUploadedFile('AFFIDAVIT')"><img src="images/cancel.png" height="20px"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b><span class="mandatory_field">No file is uploaded!</span></b>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" id="affidavitNoteId">
                        <p class="mandatory_field" style="padding-left: 12px; padding-bottom: 20px;">(Note : Sri
                            Lankan citizen only. The affidavit is mandatory only when the name as in the passport
                            need to be printed on the certificate.)</p>
                    </div>
                    <div style="clear:both;"></div>

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
                                <div class="col-sm-3" id="uploadLetterOfReferencePathDisplay">
                                    <c:choose>
                                        <c:when test="${!empty applicationVO.letterOfReferencePath}">
                                            <a href="policeFileFinder.htm?fileName=${applicationVO.letterOfReferencePath}"
                                               target="_blank"><img src="images/open.png" height="25px"/></a>
                                            &nbsp;&nbsp;<a href="javascript:removeUploadedFile('LETR')"><img src="images/cancel.png" height="20px"/></a>
                                        </c:when>
                                        <c:otherwise>
                                            <b><span class="mandatory_field">No file is uploaded!</span></b>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <p class="mandatory_field" style="padding-left: 12px; padding-bottom: 20px;"></p>
                        </div>
                        <div style="clear:both;"></div>
                    </div>

                    <p>Please ensure correct and clear copies of the documents are attached to avoid delays and
                        application rejection.</p>

                    <div style="clear:both;"></div>
                    <hr/>
                    <div style="clear:both;"></div>

                </div>

                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td valign="middle" style="vertical-align: middle;">
                                        <input type="checkbox" checked="checked" readonly="readonly" disabled="disabled"
                                               name="" id="acceptTermsCheck"/>
                                    </td>
                                    <td>&nbsp;&nbsp;</td>
                                    <td valign="middle" style="vertical-align: middle;">
                                        I declare that the information provided above are true to the best of my
                                        knowledge and I agree to the terms of Sri Lankan Police Headquarters.
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <div style="clear:both;"></div>
                <hr/>
                <div style="clear:both;"></div>

                <div class="col-lg-12">
                    <div style="float: right;">
                        <input id="saveApplicationSubmitButton" type="submit" class="btn btn-primary es-buttton"
                               value="Update Application"/>
                        <input type="button" class="btn btn-primary es-buttton" value="Cancel" onclick="resetForm();"/>
                    </div>
                </div>
                <div style="clear:both;"></div>

            </div>

            </s:form>

            <script type="text/javascript">
                window.previousNicNo;
                window.previousNewNicNo;
                window.previousPassportNo;
                window.previousSeletectOption;

                $(document).ready(function () {

                    window.previousNicNo = $('#hiddenNicId').val();
                    window.previousNewNicNo = $('#hiddenNewNicId').val();
                    window.previousPassportNo = $('#hiddenPassportId').val();
                    window.previousSeletectOption = $('#selectedName').find(":selected").text();


                    isWantAffidavit();

                    initializeDateTimePickers();

                    var selecetdHovPer = $('#handoverPersonSelect').val();
                    if (selecetdHovPer == 'NOA') {
                        $('#handoverPersonName').attr('readonly', 'readonly');
                        $('#handoverPersonNICPassport').attr('readonly', 'readonly');
                        $('#handoverPersonName').attr('disabled', 'disabled');
                        $('#handoverPersonNICPassport').attr('disabled', 'disabled');
                    }

                    $('#handoverPersonSelect').on('change', function () {
                        var selectedVal = $(this).val();
                        if (selectedVal == 'NOA') {
                            $('#handoverPersonName').val('');
                            $('#handoverPersonNICPassport').val('');
                            $('#handoverPersonName').attr('readonly', 'readonly');
                            $('#handoverPersonNICPassport').attr('readonly', 'readonly');
                            $('#handoverPersonName').attr('disabled', 'disabled');
                            $('#handoverPersonNICPassport').attr('disabled', 'disabled');
                        } else {
                            $('#handoverPersonName').attr('readonly', false);
                            $('#handoverPersonNICPassport').attr('readonly', false);
                            $('#handoverPersonName').attr('disabled', false);
                            $('#handoverPersonNICPassport').attr('disabled', false);
                        }
                    });

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

                    setVisibleLetterOfReferenceUpload();
                    changeStatuscCtizenOfSriLanka();

                    manualPaymentFormValidationOnLoad();
                    validateDeliveryType();

                    makeNumericOnlyTextBox('#applicantAge');
                    makeNumericOnlyTextBox('#postalMobile');
                    makeNumericOnlyTextBox('#postalConfirmMobile');
                    makeNumericOnlyTextBox('#totalFee');

                    previousCertificateAppliedForm();
                    previousCertificateIssue();

                    wereYouCitizenOfSriLankaShowHide();

                    $('#nicNo').attr('maxlength', '10');
                    $('#newNicNo').attr('maxlength', '12');
                });

                function isWantAffidavit() {
                    var selectedOption = $('#selectedName :selected').val();
                    var nationalityId = $("#nationalitySelect").val();

                    var age = $("#applicantAge").val();
                    var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();
                    if ((nationalityId == 199 || citizenOfSriLanka =='1') && age >= 18 && selectedOption == 'passport') {
                        $('#affidavitUploadId').show();
                        $('#affidavitNoteId').show();
                    } else {
                        $('#affidavitUploadId').hide();
                        $('#affidavitNoteId').hide();
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
                            var dob = $.datepicker.parseDate('dd/mm/yy', $("#dateOfBirth").val());
                            if (selectedDate < dob) {
                                selectedDate = dob;
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
                        minDate: $('#dateOfBirth').datepicker("getDate"),
                        dateFormat: "dd/mm/yy"
                    });
                    $('#passportIssueDate').datepicker({
                        changeYear: true,
                        yearRange: "-120:+0",
                        changeMonth: true,
                        maxDate: "+0d",
                        minDate: $('#dateOfBirth').datepicker("getDate"),
                        dateFormat: "dd/mm/yy"
                    });

                }


                function previousCertificateAppliedForm() {
                    var status = $("#previousCertificateSelect").val();
                    if (status == 0) {
                        $("#previousCertificateIssueForm").hide(500);
                    } else if (status == 1) {
                        $("#previousCertificateIssueForm").show(500);
                    }
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

                    var rowCountSaved = parseInt($('#hiddenPoliceAddressRowId').val());
                    if (rowCountSaved < 0) {
                        rowCountSaved = $('#certificateAddressTable tr').length - 1;
                    }

                    var rowCount = rowCountSaved;
                    //alert('rowCount :' + rowCount);
                    try {
                        var fromDateObj = $.datepicker.parseDate("dd/mm/yy", from);
                        var toDateObj = $.datepicker.parseDate("dd/mm/yy", to);
                        var isOverlapping = false;

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
                                    //clear fields
                                    $("#certificationAddressId").val('');
                                    $("#certificationAddress").val('');
                                    $("#certificationPoliceAreaSelect").val('');
                                    $("#certificationPoliceAreaFromSelect").val('');
                                    $("#certificationPoliceAreaToSelect").val('');
                                    $('#hiddenPoliceAddressRowId').val(-1);

                                    $("#certificateAddressTable").append('<tr id="cad' + rowCount + '" class="appendedRowAddress">' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].address" style="width: 100%;" type="text" readonly="readonly" class="address from-control" value="' + address + '" />' +
                                        '<input name="certificateAddressList[' + rowCount + '].addressId" type="hidden" readonly="readonly" class="addressId from-control" value="' + addressId + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].policeAreaId" style="width: 100%;" type="hidden" readonly="readonly" class="policeAreaVal from-control" value="' + policeArea + '" /><input type="text" name="certificateAddressList[' + rowCount + '].policeArea" readonly="readonly" class="policeArea" value="' + policeAreaName + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].fromDateStr" style="width: 100%;" id="addedFromDate_' + rowCount + '" type="text" readonly="readonly" class="from from-control" value="' + from + '" /></td>' +
                                        '<td><input name="certificateAddressList[' + rowCount + '].toDateStr" style="width: 100%;" id="addedToDate_' + rowCount + '" type="text" readonly="readonly" class="to from-control" value="' + to + '" /></td>' +
                                        '<td><img class="remRow" src="images/edit.png" style="cursor:pointer;" onclick="editCertificateAddressRow(\'cad' + rowCount + '\');"></td>' +
                                        '<td><img class="remRow" src="images/cancel.png" style="cursor:pointer;"></td>' +
                                        '</tr>');

                                    $("#certificationPoliceAreaFromSelect").datepicker("option", "maxDate", new Date());
                                }
                            });

                        } else {
                            //clear fields
                            $("#certificationAddressId").val('');
                            $("#certificationAddress").val('');
                            $("#certificationPoliceAreaSelect").val('');
                            $("#certificationPoliceAreaFromSelect").val('');
                            $("#certificationPoliceAreaToSelect").val('');
                            $('#hiddenPoliceAddressRowId').val(-1);

                            $("#certificateAddressTable").append('<tr id="cad' + rowCount + '" class="appendedRowAddress">' +
                                '<td><input name="certificateAddressList[' + rowCount + '].address" style="width: 100%;" type="text" readonly="readonly" class="address from-control" value="' + address + '" />' +
                                '<input name="certificateAddressList[' + rowCount + '].addressId" type="hidden" readonly="readonly" class="addressId from-control" value="' + addressId + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].policeAreaId" style="width: 100%;" type="hidden" readonly="readonly" class="policeAreaVal from-control" value="' + policeArea + '" /><input type="text" name="certificateAddressList[' + rowCount + '].policeArea" readonly="readonly" class="policeArea" value="' + policeAreaName + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].fromDateStr" style="width: 100%;" id="addedFromDate_' + rowCount + '" type="text" readonly="readonly" class="from from-control" value="' + from + '" /></td>' +
                                '<td><input name="certificateAddressList[' + rowCount + '].toDateStr" style="width: 100%;" id="addedToDate_' + rowCount + '" type="text" readonly="readonly" class="to from-control" value="' + to + '" /></td>' +
                                '<td><img class="remRow" src="images/edit.png" style="cursor:pointer;" onclick="editCertificateAddressRow(\'cad' + rowCount + '\');"></td>' +
                                '<td><img class="remRow" src="images/cancel.png" style="cursor:pointer;"></td>' +
                                '</tr>');

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

                function checkIfDataRangeValid(startD, endD, startdate, enddate) {
                    return (startD >= startdate && startD <= enddate) ||
                        (startdate >= startD && startdate <= endD);
                }

                function addLivedAddress() {
                    var usertype = $("#applicantSpouseSelect").val();
                    if (usertype == "applicant") {
                        addRowToApplicantLivedAddressTable();
                    } else if (usertype == "spouse") {
                        addRowToSpousLivedAddressTable();
                    }
                }


                function editCertificateAddressRow(rowId) {
                    $("#certificationAddress").val($("#certificateAddressTable").find('#' + rowId).find('.address').val());
                    $("#certificationAddressId").val($("#certificateAddressTable").find('#' + rowId).find('.addressId').val());
                    $("#certificationPoliceAreaSelect").val($("#certificateAddressTable").find('#' + rowId).find('.policeAreaVal').val());
                    $("#certificationPoliceAreaFromSelect").val($("#certificateAddressTable").find('#' + rowId).find('.from').val());
                    $("#certificationPoliceAreaToSelect").val($("#certificateAddressTable").find('#' + rowId).find('.to').val());

                    var roIntId = parseInt($.trim(rowId.replace("cad", "")));

                    $('#hiddenPoliceAddressRowId').val(roIntId);

                    $("#certificateAddressTable").find('#' + rowId).remove();
                }


                function validateApplicationForm() {
                    try {
                        setApplicationHiddenValues();

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
                        var passport = $("#passportNo").val();
                        var countryId = $("#countrySelect").val();

                        var consultantName = $("#highCommisionReferenceName").val();
                        var consultantAddress = $("#highCommisionReferenceAddress").val();

                        //var genuineAge = getAge($("#dateOfBirth").datepicker("getDate"));

                        var nicRegex = /\d{9}[V|v|x|X]/;

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

                        if (nationalityId == 199 && age >= 18 && nic == ''
                            && (nic == '' && newNic == '')) {
                            alert("Please enter  NIC or NEW NIC number");
                            $("#nicNo").focus();
                            $("#newNicNo").focus();
                            addRedBorder("nicNo");
                            addRedBorder("newNicNo");
                            return false;
                        }

                        if (!(nic == '' || nic == null)) {
                            var nicLength = nic.length;
                            if (nicLength == 10) {
                                if (!(nicRegex.test(nic))) {
                                    alert("Nic No is not valid");
                                    $("#nicNo").focus();
                                    return false;
                                }
                            } else if (nicLength != 12) {
                                alert("Nic No is not valid");
                                $("#nicNo").focus();
                                return false;
                            }
                        } else if (nationalityId == 199 && age >= 18 && newNic != null || newNic != '') {
                            var newNicLength = newNic.length;
                            if (newNicLength != 12) {
                                alert('Please enter a valid new NIC number');
                                $("newNicNo").focus();
                                return false;
                            }
                        }

//                        if (citizenOfSriLanka == 1 && ageWhenMigrate >= 18 && nic == '' && newNic == '') {
//                            alert("Please enter NIC number");
//                            $("#nicNo").focus();
//                            addRedBorder("nicNo");
//                            return false;
//                        }


                        if (passport == '') {
                            alert("Please enter Passport number");
                            $("#passportNo").focus();
                            addRedBorder("passportNo");
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

                        var applicantNameInFullAsNic = $("#nameInFullAsNic").val();
                        var applicantNameInFullAsPass = $("#nameInFullAsPassport").val();

                        var nationalityId = $("#nationalitySelect").val();
                        var dateOfBirth = $("#dateOfBirth").val();
                        var age = $("#applicantAge").val();
                        var citizenOfSriLanka = $("#citizenOfSriLankaSelect").val();
                        var dateOfObtained = $("#dateOfcitizenshipObtained").val();
                        var nicNo = $.trim($('#nicNo').val());

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
                        var email = $("#postalEmail").val();
                        var mobileRegx = /\d{10}/;
                        var emailRegx = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        var nicRegex = /\d{9}[V|v|x|X]/;

                        if (mobileNo == '') {
                            alert("mobile No. is empty");
                            $("#postalMobile").focus();
                            addRedBorder("postalMobile");
                            return false;
                        }

                        if (!email.match(emailRegx)) {
                            if (!(email == '' || email == null || email == 'undefined')) {
                                alert("email is invalid");
                                $("#postalEmail").focus();
                                addRedBorder("postalEmail");
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

                        var passportAttach = $("#passportUploadPath").val();
                        var passportAttachBack = $("#passportUploadPathBack").val();
                        var nicAttach = $("#nicUploadPath").val();
                        var newNicAttach = $("#newNicUploadPath").val();
                        var nicAttachBack = $("#nicUploadPathBack").val();
                        var newNicAttachBack = $("#newNicUploadPathBack").val();
                        var birthCertificateAttach = $("#birthCertificateUpload").val();
                        var birthCertificateAttachBack = $("#birthCertificateUploadBack").val();
                        var letterOfReferenceAttach = $("#uploadLetterOfReferencePath").val();
                        var referredThroughBereau = $("#referredThroughBereau").val();
                        if (referredThroughBereau == 1) {
                            if (letterOfReferenceAttach == '') {
                                alert("Please upload copy of letter of reference");
                                return false;
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
                                $("#passportUpload").focus();
                                addRedBorder("passportUpload");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadBack").focus();
                                addRedBorder("passportUploadBack");
                                return false;
                            }



                        } else if (genuineAge < 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUpload").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadBack").focus();
                                addRedBorder("passportUploadPathBack");
                                return false;
                            }


                        }

                        if (ageWhenMigrate >= 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportFileUpload").focus();
                                addRedBorder("passportFileUpload");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadBack").focus();
                                addRedBorder("passportUploadBack");
                                return false;
                            }
                        } else if (ageWhenMigrate < 18) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUpload").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadBack").focus();
                                addRedBorder("passportUploadBack");
                                return false;
                            }
                        }

                        var previousPassportno = window.previousPassportNo;
                        var currentPassportNo = $('#passportNo').val();

                        if(previousPassportno != currentPassportNo){
                            if(!$('#upload_complete_passport').is(':visible')) {
                                alert("Please upload the Countries allowed page copy of your current Passport");
                                $("#passportUpload").focus();
                                addRedBorder("passportUploadPath");
                                return false;
                            }

                            if(!$('#upload_complete_passport_back').is(':visible')) {
                                alert("Please upload the Countries allowed page copy of your current Passport");
                                $("#passportUpload").focus();
                                addRedBorder("passportUpload");
                                return false;
                            }
                        }

                        var previousNicNO = window.previousNicNo;
                        var currentNicNo = $('#nicNo').val();

                        var previousNewNicNO = window.previousNewNicNo;
                        var currentNewNicNo = $('#newNicNo').val();

                        var previousNameOption = window.previousSeletectOption;
                        var currentOption = $("#selectedName").val();

                        if (citizenSriLanka == 1) {
                            if (passportAttach == '') {
                                alert("Please upload the Personal detail page copy of your Passport");
                                $("#passportUpload").focus();
                                addRedBorder("passportUpload");
                                return false;
                            } else if (passportAttachBack == '') {
                                alert("Please upload the Countries allowed page copy of your Passport");
                                $("#passportUploadBack").focus();
                                addRedBorder("passportUploadBack");
                                return false;
                            }
                        }

                        if(!(currentNewNicNo == null || currentNewNicNo=='')){
                            if ( currentNewNicNo.length != 12) {
                                alert("Please enter valid new NIC");
                                $("#newNicNo").focus();
                                addRedBorder("newNicNo");
                                return false;
                            }
                        }


                        if(!(currentNicNo == null || currentNicNo =='')){
                            if (previousNicNO != currentNicNo) {
                                if (!$('#upload_complete_nic').is(':visible')) {
                                    alert("Please upload the front side of the current NIC");
                                    $("#nicUpload").focus();
                                    addRedBorder("nicUpload");
                                    return false;
                                }

                                if (!$('#upload_complete_nic_back').is(':visible')) {
                                    alert("Please upload the back side of the current NIC");
                                    $("#nicUploadBack").focus();
                                    addRedBorder("nicUploadBack");
                                    return false;
                                }
                            }
                        }else{
                            if($('#nicRemove').is(':visible')){
                                alert("Please remove the front side of the current NIC");
                                $("#nicAttachPathDisplay").focus();
                                addRedBorder("nicAttachPathDisplay");
//                                $('#nicRemove').remove();
                                return false;
                            }

                            if ($('#nicBackRemove').is(':visible')) {
                                alert("Please remove the back side of the current NIC");
                                $("#nicBackAttachPathDisplay").focus();
                                addRedBorder("nicBackAttachPathDisplay");
//                                $('#nicBackRemove').remove();
                                return false;
                            }
                        }

                        if(!(currentNewNicNo == null || currentNewNicNo == '')){
                            if (previousNewNicNO != currentNewNicNo) {
                                if (!$('#upload_complete_new_nic').is(':visible')) {
                                    alert("Please upload the front side of the current new NIC");
                                    $("#newNicUpload").focus();
                                    addRedBorder("newNicUpload");
                                    return false;
                                }

                                if (!$('#upload_complete_new_nic_back').is(':visible')) {
                                    alert("Please upload the back side of the current new NIC");
                                    $("#newNicUploadBack").focus();
                                    addRedBorder("newNicUploadBack");

                                    return false;
                                }
                            }
                        }else{
                            if ($('#newNicRemove').is(':visible')) {
                                alert("Please remove the front side of the current new NIC");
                                $("#newNicAttachPathDisplay").focus();
                                addRedBorder("newNicAttachPathDisplay");
//                                $('#newNicRemove').remove();
                                return false;
                            }

                            if ($('#newNicBackRemove').is(':visible')) {
                                alert("Please remove the back side of the current new NIC");
                                $("#newNicBackAttachPathDisplay").focus();
                                addRedBorder("newNicBackAttachPathDisplay");
//                                ('#newNicBackRemove').remove();
                                return false;
                            }

                        }


                        if ((citizenSriLanka == 1 || nationalityId == 199) && genuineAge >= 18) {
//                            if (nicAttach == '' && newNicAttach == '') {
//                                alert("Please upload the front side of the NIC");
//                                $("#nicUpload").focus();
//                                addRedBorder("nicUpload");
//                                return false;
//                            } else if (nicAttachBack == '' && newNicAttachBack == '') {
//                                alert("Please upload the back side of the NIC");
//                                $("#nicUploadBack").focus();
//                                addRedBorder("nicUploadBack");
//                                return false;
//                            }

                            if (previousNameOption.toLowerCase() != currentOption) {
                                if (currentOption == 'passport') {
                                    if (!$('#upload_complete_affidavit').is(':visible')) {
                                        alert("Please upload the Affidavit ");
                                        $("#affidavitUpload").focus();
                                        addRedBorder("affidavitUpload");
                                        return false;
                                    }
                                }

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
                    } catch (e) {
                        console.log('Error :-'+ e);
//                        alert('Error :- ' + e);
                        return false;
                    }
                    //$("#countrySelect").attr("disabled", "false");
                    var conf = confirm('Are you sure you want to submit the Application form?');
                    if (conf) {
                        return true;
                    }
                    return false;
                }

                function setApplicationHiddenValues() {
//                    $("#mobileCountryCodeSelected").val($("#mobileCountryCodeId option:selected").text());
                    $("#mobileCountryCodeSelected").val('Sri Lanka(+94)');
                    var postalCountry = $("#postalCountrySelect").val();
                    if (postalCountry != 0) {
                        var postalCountryName = $("#postalCountrySelect option:selected").text();
                        $("#postalCountrySelectName").val(postalCountryName);
                    }
                    var previousCertificateCountryName = $("#previousCertificateCountrySelect option:selected").text();
                    $("#previousCertificateCountryName").val(previousCertificateCountryName);
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
                    var letterOfReference = document.getElementById('uploadLetterOfReferenceUpload');
                    var affidavitInput = document.getElementById('affidavitUpload');

                    var nicFile = "";
                    var nicFileBack = "";
                    var newNicFile = "";
                    var newNicFileBack = "";
                    var passportFile = "";
                    var passportFileBack = "";
                    var birthCertificateFile = "";
                    var birthCertificateFileBack = "";
                    var letterOfReferenceFile = "";
                    var affidavitFile = "";

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
                    var letterOfReferenceExtension = "";
                    var affidavitExtension = "";

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
                    } else if (fileType == 'let') {
                        $("#ajax_loader_letter_of_reference").show();
                        formData.append('file', letterOfReferenceFile);
                        formData.append('fileExtension', letterOfReferenceExtension);
                        formData.append('fileType', "LETR");
                    } else if (fileType == 'affidavit') {
                        $("#ajax_loader_birth_affidavit").show();
                        formData.append('file', affidavitFile);
                        formData.append('fileExtension', affidavitExtension);
                        formData.append('fileType', "AFFIDAVIT");
                    }else if (fileType == 'new_nic') {
                        $("#ajax_loader_new_nic").show();
                        formData.append('file', newNicFile);
                        formData.append('fileExtension', newNicExtension);
                        formData.append('fileType', "NEWNICF");
                    } else if (fileType == 'new_nic_back') {
                        $("#ajax_loader_new_nic_back").show();
                        formData.append('file', newNicFileBack);
                        formData.append('fileExtension', newNicExtensionBack);
                        formData.append('fileType', "NEWNICB");
                    }else if (fileType == 'affidavit') {
                        $("#ajax_loader_affidavit").show();
                        formData.append('file', affidavitFile);
                        formData.append('fileExtension', affidavitExtension);
                        formData.append('fileType', "AFFIDAVIT");
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
                                    $('#nicAttachPathDisplay').html('<a href="policeFileFinder.htm?fileName=' + nicFileName + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#nicAttachPathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'NICF\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#nicAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                                }
                                $("#ajax_loader_nic").hide();
                                $("#upload_complete_nic").show();


                            } else if (fileType == 'NICB') {
                                var nicFileNameBack = jsonData.fileName;
                                if (nicFileNameBack != '') {
                                    $("#nicUploadPathBack").val(nicFileNameBack);
                                    $('#nicBackAttachPathDisplay').html('<a href="policeFileFinder.htm?fileName=' + nicFileNameBack + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#nicBackAttachPathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'NICB\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#nicBackAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
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
                                    $('#passportAttachPathDisplay').html('<a href="policeFileFinder.htm?fileName=' + passFileName + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#passportAttachPathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'PASF\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#passportAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                                }
                                $("#ajax_loader_passport").hide();
                                $("#upload_complete_passport").show();

                            } else if (fileType == 'PASB') {
                                var passFileNameBack = jsonData.fileName;
                                if (passFileNameBack != '') {
                                    $("#passportUploadPathBack").val(passFileNameBack);
                                    $('#passportBackAttachPathDisplay').html('<a href="policeFileFinder.htm?fileName=' + passFileNameBack + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#passportBackAttachPathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'PASB\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#passportBackAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                                }
                                $("#ajax_loader_passport_back").hide();
                                $("#upload_complete_passport_back").show();

                            } else if (fileType == 'BIRF') {
                                var birthCertificateFileName = jsonData.fileName;
                                if (birthCertificateFileName != '') {
                                    $("#birthCertificateUploadPath").val(birthCertificateFileName);
                                    $('#birthCertificateUploadPathDisplay').html('<a href="policeFileFinder.htm?fileName=' + birthCertificateFileName + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#birthCertificateUploadPathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'BIRF\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#birthCertificateUploadPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                                }
                                $("#ajax_loader_birth_certificate").hide();
                                $("#upload_complete_birth_certificate").show();

                            } else if (fileType == 'BIRB') {
                                var birthCertificateFileNameBack = jsonData.fileName;
                                if (birthCertificateFileNameBack != '') {
                                    $("#birthCertificateUploadPathBack").val(birthCertificateFileNameBack);
                                    $('#birthCertificateUploadPathBackDisplay').html('<a href="policeFileFinder.htm?fileName=' + birthCertificateFileNameBack + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#uploadLetterOfReferencePathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'BIRB\')"><img src="images/cancel.png" height="20px"/></a>')
                                } else {
                                    $('#birthCertificateUploadPathBackDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
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
                                    $('#uploadLetterOfReferencePathDisplay').html('<a href="policeFileFinder.htm?fileName=' + letterOfReferenceFileName + '" target="_blank"><img src="images/open.png" height="25px"/></a>');
                                    $('#uploadLetterOfReferencePathDisplay').append('&nbsp;&nbsp;<a href="javascript:removeUploadedFile(\'LETR\')"><img src="images/cancel.png" height="20px"/></a>');
                                } else {
                                    $('#uploadLetterOfReferencePathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
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
                                $("#ajax_loader_letter_of_reference").hide();
                                $("#ajax_loader_affidavit").hide();
                                alert('File upload failed, please check the file size and reupload!');
                            }
                        }
                    }

                }

                function removeUploadedFile(fileType) {
                    if (fileType == 'NICF') {
                        $("#nicUploadPath").val('');
                        $('#nicAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'NICB') {
                        $("#nicUploadPathBack").val('');
                        $('#nicBackAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    }

                    if (fileType == 'NEWNICF') {
                        $("#newNicUploadPath").val('');
                        $('#newNicAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'NEWNICB') {
                        $("#newNicUploadPathBack").val('');
                        $('#newNicBackAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'PASF') {
                        $("#passportUploadPath").val('');
                        $('#passportAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'PASB') {
                        $("#passportUploadPathBack").val('');
                        $('#passportBackAttachPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'BIRF') {
                        $("#birthCertificateUploadPath").val('');
                        $('#birthCertificateUploadPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'BIRB') {
                        $("#birthCertificateUploadPathBack").val('');
                        $('#birthCertificateUploadPathBackDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'LETR') {
                        $("#uploadLetterOfReferencePath").val('');
                        $("#uploadLetterOfReferenceUpload").val('');
                        $('#uploadLetterOfReferencePathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    } else if (fileType == 'AFFIDAVIT') {
                        $("#affidavitUpload").val('');
                        $('#affidavitUploadPathDisplay').html('<b><span class="mandatory_field">No file is uploaded!</span></b>');
                    }
                }

                function validateFileUpload(uploadingFile, path, extension, file) {
                    var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
                    if (path == '') {
                        alert("please select a file before upload for " + uploadingFile);
                        return false;
                    } else if (extension != "pdf" && extension != "PDF" && extension != "doc" && extension != "DOC" && extension != "docx" && extension != "DOCX" && extension != "png" && extension != "PDF" && extension != "jpg" && extension != "jpg") {
                        alert("invalid " + uploadingFile + " file format");
                        return false;
                    } else if (file.size > maximumFileLimit) {
                        alert(uploadingFile + " file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                        return false;
                    }
                    return true;
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
                    var countryNameText = $("#countrySelect option:selected").text();
                    $('#countryNameTextInput').val($.trim(countryNameText));

                    $.ajax({
                        url: "loadCommissionersByCountry",
                        data: {countryId: $("#countrySelect").val()},
                        dataType: "json",
                        success: function (data) {
                            var $el = $("#highCommissionVerifySelect");
                            $el.empty();

                            if (commisionerId == 0) {
                                $el.append("<option selected='selected' value=\"0\">Other</option>");
                            } else {
                                getSelectedCommissioner();
                                $el.append("<option value=\"0\">Other</option>");
                            }

                            for (var key in data.highCommissionList) {
                                if (commisionerId == data.highCommissionList[key].id) {
                                    $el.append("<option selected='selected' value=" + data.highCommissionList[key].id + ">" + data.highCommissionList[key].commissionEmbassyConsultantName + " - " + data.highCommissionList[key].commissionEmbassyConsultantAddress + "</option>");
                                } else {
                                    $el.append("<option value=" + data.highCommissionList[key].id + ">" + data.highCommissionList[key].commissionEmbassyConsultantName + " - " + data.highCommissionList[key].commissionEmbassyConsultantAddress + "</option>");
                                }
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


                function manualPaymentFormValidationOnLoad() {
                    var paymentMode = $("#paymentModeSelect").val();
                    if (paymentMode == 'CASH') {
                        changeReadOnlyStatus('bookReceiptNo', 0);
                        changeReadOnlyStatus('chequeNo', 1);
                        changeReadOnlyStatus('accountNo', 1);
                        changeReadOnlyStatus('accountHolderName', 1);
                    } else if (paymentMode == 'CHEQ') {
                        changeReadOnlyStatus('bookReceiptNo', 0);
                        changeReadOnlyStatus('chequeNo', 0);
                        changeReadOnlyStatus('accountNo', 1);
                        changeReadOnlyStatus('accountHolderName', 1);
                    } else if (paymentMode == 'WIRE') {
                        changeReadOnlyStatus('bookReceiptNo', 1);
                        changeReadOnlyStatus('chequeNo', 1);
                        changeReadOnlyStatus('accountNo', 0);
                        changeReadOnlyStatus('accountHolderName', 0);
                    }
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
                        changeReadOnlyStatus('bookReceiptNo', 1);
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
                    window.location = "home.action";
                }

                function wereYouCitizenOfSriLankaShowHide() {
                    var nationality = $("#nationalitySelect").val();
                    if (nationality == 199) {
                        $("#sriLankaCitizenDiv").hide(400);
                    } else {
                        $("#sriLankaCitizenDiv").show(400);
                    }

                    var nationalityText = $("#nationalitySelect option:selected").text();
                    $('#nationalityTextInput').val($.trim(nationalityText));

                }

            </script>


    </body>
    </html>
</s:i18n>