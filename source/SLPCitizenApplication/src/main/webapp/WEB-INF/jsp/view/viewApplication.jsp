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
        <title>View Application</title>
    </head>

    <body id="bd">

    <c:choose>
        <c:when test="${empty previousApplicationViewMessage}">
            <!--main container start -->
            <div id="es-container" class="container">

                <jsp:include page="../common/header.jsp"/>

                <div id="es-content">
                    <!-- Including the common page with title bar and help content -->
                    <c:set var="pageTitle">View Application</c:set>
                    <jsp:include page="../common/commonPage.jsp">
                        <jsp:param name="title" value="${pageTitle}"/>
                    </jsp:include>

                    <div class="middle_content">

                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="nationalitySelect" class="control-label"><b>Nationality:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom"
                                         id="referenceNoRequestUpdate">${applicationVO.nationality}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <label for="citizenOfSriLankaSelect" class="control-label"><b>Were you a citizen of Sri
                                        Lanka?</b></label>
                                </div>
                                <div class="col-sm-4">
                                    <c:choose>
                                        <c:when test="${applicationVO.citizenOfSriLanka != null}">
                                            <c:choose>
                                                <c:when test="${applicationVO.citizenOfSriLanka == 1}">
                                                    <div class="input_div input_div_custom">Yes</div>
                                                </c:when>
                                                <c:when test="${applicationVO.citizenOfSriLanka == 0}">
                                                    <div class="input_div input_div_custom">No</div>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <label for="dateOfcitizenshipObtained" class="control-label"><b>If Yes, the date citizenship
                                        obtained from another country</b></label>
                                </div>
                                <div class="col-sm-4">
                                    <c:set var="customCOD">
                                        <fmt:formatDate value="${applicationVO.citizenshipObtainedDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <div class="input_div input_div_custom">${customCOD}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="dateOfBirth" class="control-label"><b>Date Of Birth</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <c:set var="customDOB">
                                        <fmt:formatDate value="${applicationVO.dateOfBirth}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <div class="input_div input_div_custom">${customDOB}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <label for="applicantAge" class="control-label"><b>Age in years:</b></label>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input_div input_div_custom">${applicationVO.age}</div>
                                </div>
                            </div>
                        </div>

                        <div style="clear:both;"></div>
                        <hr/>
                        <div style="clear:both;"></div>
                    </div>

                    <s:hidden name="applicationVO.nationalityId" id="nationalityIdVerify"/>
                    <s:hidden name="applicationVO.nationality" id="nationalityVerify"/>
                    <s:hidden name="applicationVO.citizenOfSriLanka" id="citizenOfSriLankaVerify"/>
                    <s:hidden name="dateOfCitizenshipObtained" id="citizenshipObtainedDateVerify"/>
                    <s:hidden name="dateOfBirth" id="dateOfBirthVerify"/>
                    <s:hidden name="applicationVO.age" id="ageVerify"/>

                    <div class="middle_content">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-lg-8">
                                    &nbsp;
                                </div>
                                <div class="col-lg-4">
                                    <div class="col-sm-4" style="text-align: right;">
                                        <label for="referenceNo" class="control-label"><b>Reference No.</b></label>
                                    </div>
                                    <div class="col-sm-6" style="text-align: left;padding-left: 5px;">
                                        <div class="input_div input_div_custom"
                                             style="text-align: left;width:100%;">${applicationVO.referenceNo}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <p>Application with incorrect NIC Number and Passport Number will be rejected.</p>
                        <p>Please have your NIC and Passport scanned before you start filling in your application. Enter your
                            NIC number and Passport number to verify
                            if an application is already in process under your name.</p>

                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="nicNo" class="control-label"><b>NIC No:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom">${applicationVO.nic}</div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="newNicNo" class="control-label"><b>New NIC No:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom">${applicationVO.newNic}</div>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="passportNo" class="control-label"><b>Passport No:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom">${applicationVO.passport}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="countryVerifySelect" class="control-label"><b>Country:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom">${applicationVO.countryName}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="countryVerifySelect" class="control-label"><b>Reference high
                                        commission/embassy/consulate & Address:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div input_div_custom">${applicationVO.highCommisionReference}</div>
                                    <div class="input_div input_div_custom">${applicationVO.highCommisionReferenceAddress}</div>
                                </div>
                            </div>
                            <s:hidden name="applicationVO.highCommisionReference" id="highCommissionVerifySelectedValue"/>
                        </div>
                        <div style="clear:both;"></div>
                    </div>


                    <div id="verifyMessage">You can submit another application for the same country only when your previous
                        application is processed.
                    </div>

                    <div style="clear:both;"></div>
                    <hr/>
                    <div style="clear:both;"></div>

                    <s:hidden name="applicationVO.nationalityId" id="nationalityId"/>
                    <s:hidden name="applicationVO.nationality" id="nationality"/>
                    <s:hidden name="applicationVO.citizenOfSriLanka" id="citizenOfSriLanka"/>
                    <s:hidden name="dateOfCitizenshipObtained" id="citizenshipObtainedDate"/>
                    <s:hidden name="dateOfBirth" id="dateOfBirth"/>
                    <s:hidden name="applicationVO.age" id="age"/>

                    <s:hidden name="applicationVO.previousReferenceNo" id="previousReferenceNo"/>
                    <s:hidden name="applicationVO.referenceNo" id="referenceNo"/>
                    <s:hidden name="applicationVO.nic" id="nic"/>
                    <s:hidden name="applicationVO.passport" id="passport"/>
                    <s:hidden name="applicationVO.highCommisionReferenceId" id="highCommisionReferenceId"/>
                    <s:hidden name="applicationVO.highCommisionReference" id="highCommisionReference"/>

                    <input type="hidden" id="verificationStatus" name="verificationStatus" value="${verificationStatus}"/>
                    <input type="hidden" id="reapplyStatus" name="reApplyStatus" value="${reApplyStatus}"/>

                    <div class="middle_content">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="nameInFullAsNic" class="control-label"><b>Applicant's Name in full as in the
                                        NIC:</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <div class="input_div">${applicationVO.applicantNameAsNic}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="highCommisionReference" class="control-label"><b>Applicant's Name in full as in
                                        the Passport:</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <div class="input_div">${applicationVO.applicantNameAsPassport}</div>
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
                                        <c:when test="${applicationVO.selectedNameOption == 'nic'}">
                                            <div class="input_div">NIC</div>
                                        </c:when>
                                        <c:when test="${applicationVO.selectedNameOption == 'passport'}">
                                            <div class="input_div">Passport</div>
                                        </c:when>

                                    </c:choose>


                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="passportIssueDate" class="control-label"><b>Passport Issue Date:</b></label>
                                </div>
                                <div class="col-sm-5">
                                    <c:set var="customPPTDate">
                                        <fmt:formatDate value="${applicationVO.passportIssueDate}" pattern="dd/MM/yyyy"/>
                                    </c:set>
                                    <div class="input_div">${customPPTDate}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="presentAddressLanka" class="control-label"><b>Present Address in Sri Lanka:</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <div class="input_div">${applicationVO.presentAddressLocal}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="presentAddressOverseas" class="control-label"><b>Present Address (Overseas):</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <div class="input_div">${applicationVO.presentAddressOverseas}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="sexSelect" class="control-label"><b>Sex:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${applicationVO.sex != null}">
                                            <c:choose>
                                                <c:when test="${applicationVO.sex == 'M'}">
                                                    <div class="input_div">Male</div>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${applicationVO.sex == 'F'}">
                                                    <div class="input_div">Female</div>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="statusSelect" class="control-label"><b>Status</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${applicationVO.applicantStatus != null}">
                                            <c:forEach var="applicantStatus" items="${applicantStatus}">
                                                <c:choose>
                                                    <c:when test="${applicationVO.applicantStatus == applicantStatus.value}">
                                                        <div class="input_div">${applicantStatus.value.displayName}</div>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="occupation" class="control-label"><b>Occupation</b></label>
                                </div>
                                <div class="col-sm-9">
                                    <div class="input_div">${applicationVO.occupation}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="purposeSelect" class="control-label"><b>Purpose</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${applicationVO.purpose != null}">
                                            <c:forEach var="applicationPurpose" items="${applicationPurposeMap}">
                                                <c:choose>
                                                    <c:when test="${applicationVO.purpose == applicationPurpose.value}">
                                                        <div class="input_div">${applicationPurpose.value.displayName}</div>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
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
                                <div class="col-sm-3">
                                    <label for="previousCertificateSelect" class="control-label"><b>Have you applied for a
                                        certificate previously ?</b></label>
                                </div>
                                <div class="col-sm-2">

                                    <c:choose>
                                        <c:when test="${applicationVO.previousCertificateApply == 1}">
                                            <div class="input_div">Yes</div>
                                        </c:when>
                                        <c:when test="${applicationVO.previousCertificateApply == 0}">
                                            <div class="input_div">No</div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <c:choose>
                            <c:when test="${applicationVO.previousCertificateApply == 1}">
                                <div id="previousCertificateIssueForm">
                                    <div style="clear:both;"></div>
                                    <hr/>
                                    <div style="clear:both;"></div>

                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <label for="previousCertificateIssueSelect" class="control-label"><b>If so, was
                                                    a certificate issued to you:</b></label>
                                            </div>
                                            <div class="col-sm-4">
                                                <c:choose>
                                                    <c:when test="${applicationVO.previousCertificateIssueStatus != null}">
                                                        <c:choose>
                                                            <c:when test="${applicationVO.previousCertificateIssueStatus == 1}">
                                                                <div class="input_div">Yes</div>
                                                            </c:when>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${applicationVO.previousCertificateIssueStatus == 0}">
                                                                <div class="input_div">No</div>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <label for="previousCertificateCountrySelect" class="control-label"><b>Country
                                                    of the last certificate:</b></label>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="input_div">${applicationVO.previousCertificateCountryName}</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>
                                    <br/>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <label for="lastCertificateReferenceNo" class="control-label"><b>Reference No.
                                                    of the last certificate:</b></label>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="input_div">${applicationVO.previousCertificateReferenceNo}</div>
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
                                                <c:set var="customPreviousCertificateIssueDate">
                                                    <fmt:formatDate value="${applicationVO.previousCertificateIssueDate}"
                                                                    pattern="dd/MM/yyyy"/>
                                                </c:set>
                                                <div class="input_div">${customPreviousCertificateIssueDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>

                                </div>
                            </c:when>
                        </c:choose>
                        <div style="clear:both;"></div>
                        <hr/>
                        <div style="clear:both;"></div>

                        <br/>
                        <table border="1" style="width:100%" id="certificateAddressTable">
                            <tr>
                                <th width="30%">Address</th>
                                <th width="30%">Police Area</th>
                                <th width="20%">From</th>
                                <th width="20%">To</th>
                            </tr>
                            <c:forEach var="applicantAddress" items="${applicationVO.applicantCertificateAddresses}"
                                       varStatus="counter">
                                <tr>
                                    <td>
                                        <div class="input_div">${applicantAddress.address}</div>
                                    </td>
                                    <td>
                                        <div class="input_div">${applicantAddress.policeArea}</div>
                                    </td>
                                    <td>
                                        <c:set var="applicationCertificateFromDate"><fmt:formatDate
                                                value="${applicantAddress.fromDate}" pattern="dd/MM/yyyy"/> </c:set>
                                        <div class="input_div">${applicationCertificateFromDate}</div>
                                    </td>
                                    <td>
                                        <c:set var="applicationCertificateToDate"><fmt:formatDate
                                                value="${applicantAddress.toDate}" pattern="dd/MM/yyyy"/> </c:set>
                                        <div class="input_div">${applicationCertificateToDate}</div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        <div style="clear:both;"></div>
                        <hr/>
                        <div style="clear:both;"></div>

                        <%--<div class="col-lg-12">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-6">--%>
                                    <%--<label for="handoverPersonSelect" class="control-label"><b>Person authorised by the--%>
                                        <%--applicant who are in foreign to handover the application:</b></label>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${applicationVO.authorizedHandoverPerson != null}">--%>
                                            <%--<c:forEach var="handOverPerson" items="${handOverPersons}">--%>
                                                <%--<c:choose>--%>

                                                    <%--<c:when test="${handOverPerson.value == applicationVO.authorizedHandoverPerson}">--%>
                                                        <%--<div class="input_div">${handOverPerson.value.displayName}</div>--%>
                                                    <%--</c:when>--%>
                                                <%--</c:choose>--%>
                                            <%--</c:forEach>--%>
                                        <%--</c:when>--%>
                                    <%--</c:choose>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div style="clear:both;"></div>--%>
                        <%--<br/>--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-2">--%>
                                    <%--<label for="handoverPersonName" class="control-label"><b>Name:</b></label>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<div class="input_div">${applicationVO.authorizedHandoverPersonName}</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div style="clear:both;"></div>--%>
                        <%--<br/>--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-2">--%>
                                    <%--<label for="handoverPersonNIC" class="control-label"><b>NIC/Passport No:</b></label>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<div class="input_div">${applicationVO.authorizedHandoverPersonNicPassport}</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <div style="clear:both;"></div>

                        <%--<div style="clear:both;"></div>--%>
                        <hr/>
                        <%--<div style="clear:both;"></div>--%>

                        <%--<div class="col-lg-12">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-10">--%>
                                    <%--<label for="certificateIndicationAddress" class="control-label"><b>Indicate address of the--%>
                                        <%--High Commission/Embassy/Consultant/ to which--%>
                                        <%--the certificate should be addressed to:</b></label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div style="clear:both;"></div>--%>

                        <%--<div class="col-lg-12">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="input_div">${applicationVO.highCommisionReferenceAddress}</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div style="clear:both;"></div>--%>

                        <%--<div style="clear:both;"></div>--%>
                        <%--<hr/>--%>
                        <%--<div style="clear:both;"></div>--%>

                        <p>Indicate the address the police clearance certificate should be posted to:</p>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="addressLineOne" class="control-label"><b>Address Line 1:</b></label>
                                </div>
                                <div class="col-sm-10">
                                    <div class="input_div">${applicationVO.certificatePostAddressLineOne}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="addressLineTwo" class="control-label"><b>Address Line 2:</b></label>
                                </div>
                                <div class="col-sm-10">
                                    <div class="input_div">${applicationVO.certificatePostAddressLineTwo}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-6" style="margin-left:6px;">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="city" class="control-label"><b>City</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div">${applicationVO.certificatePostAddressCity}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="state" class="control-label"><b>State/Provice/Region:</b></label>
                                </div>
                                <div class="col-sm-8">
                                    <div class="input_div">${applicationVO.certificatePostAddressState}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-6" style="margin-left:6px;">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postal" class="control-label"><b>Postal/ZIP Code:</b></label>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input_div">${applicationVO.certificatePostAddressPostal}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postalCountrySelect" class="control-label"><b>Country:</b></label>
                                </div>
                                <div class="col-sm-8">
                                    <div class="input_div">${applicationVO.certificatePostAddressCountryName}</div>
                                </div>
                                <s:hidden id="postalCountrySelectName" name="applicationVO.certificatePostAddressCountryName"
                                          value=""/>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-6" style="margin-left:6px;">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postalMobile" class="control-label"><b>Mobile Number:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input_div">+94</div>
                                    <s:hidden name="applicationVO.mobileCountryCode" id="mobileCountryCodeSelected"/>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input_div">${applicationVO.mobileNo}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <label for="postalEmail" class="control-label"><b>Email:</b></label>
                                </div>
                                <div class="col-sm-8">
                                    <div class="input_div">${applicationVO.email}</div>
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
                                    <div class="input_div">${applicationVO.spouseFullName}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="spouseNationality" class="control-label"><b>Nationality:</b></label>
                                </div>
                                <div class="col-sm-5">
                                    <div class="input_div">${applicationVO.spouseNationality}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="spousePassport" class="control-label"><b>Passport Number:</b></label>
                                </div>
                                <div class="col-sm-5">
                                    <div class="input_div">${applicationVO.spousePassport}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                        <br/>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label for="spouseNic" class="control-label"><b>NIC Number:</b></label>
                                </div>
                                <div class="col-sm-5">
                                    <div class="input_div">${applicationVO.spouseNic}</div>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div style="clear:both;"></div>
                        <hr/>
                        <div style="clear:both;"></div>

                            <%--<div id="paymentModeDiv">
                                <p>Mode of Payment (Only for manual submission)</p>

                                <div class="col-lg-12">
                                       <div class="form-group">
                                        <div class="col-sm-2">
                                               <label for="modeOfPayment" class="control-label"><b>Mode of Payment:</b></label>
                                           </div>
                                           <div class="col-sm-5">
                                               <c:choose>
                                                   <c:when test="${transactionVO.paymentMode != null}">
                                                       <select name="transactionVO.paymentMode" id="paymentModeSelect" class="form-control">
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
                                                       <select name="transactionVO.paymentMode" id="paymentModeSelect" class="form-control">
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
                                               <label for="totalFee" class="control-label"><b>Amount:</b></label>
                                           </div>
                                           <div class="col-sm-5">
                                               <s:textfield name="transactionVO.totalFee" id="totalFee" cssClass="form-control" />
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
                                                   <s:textfield name="transactionVO.chequeNo" id="chequeNo" cssClass="form-control" />
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
                                                   <s:textfield name="transactionVO.accountNo" id="accountNo" cssClass="form-control" />
                                               </div>
                                           </div>
                                    </div>
                                    <div style="clear:both;"></div>
                                    <div class="col-lg-12">
                                           <div class="form-group">
                                            <div class="col-sm-2">
                                                   <label for="accountHolderName" class="control-label"><b>Account Holder's Name:</b></label>
                                               </div>
                                               <div class="col-sm-5">
                                                   <s:textfield name="transactionVO.accountHolderName" id="accountHolderName" cssClass="form-control" />
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
                                                   <s:textfield name="transactionVO.description" id="description" cssClass="form-control" />
                                               </div>
                                           </div>
                                    </div>
                                    <div style="clear:both;"></div>
                                </div>

                                <div class="col-lg-12">
                                       <div class="form-group">
                                        <div class="col-sm-2">
                                               <label for="bookReceiptNo" class="control-label"><b>Book Receipt No:</b></label>
                                           </div>
                                           <div class="col-sm-5">
                                               <s:textfield name="transactionVO.bookReceiptNo" id="bookReceiptNo" cssClass="form-control" />
                                           </div>
                                       </div>
                                </div>
                                <div style="clear:both;"></div>

                                <div style="clear:both;"></div><hr /><div style="clear:both;"></div>
                            </div>  --%>

                        <p>Upload Documents</p>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="passportUploadPath" class="control-label"><b>Passport:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" id="hiddenPptFileName_${applicationVO.applicationId}"
                                           value="${applicationVO.passportAttachPath}"/>
                                    <input type="hidden" id="hiddenPptFileType_${applicationVO.applicationId}"
                                           value="${applicationVO.pptFileType}"/>

                                    <input type="hidden" id="hiddenPptFileNameBack_${applicationVO.applicationId}"
                                           value="${applicationVO.passportBackAttachPath}"/>
                                    <input type="hidden" id="hiddenPptFileTypeBack_${applicationVO.applicationId}"
                                           value="${applicationVO.pptBackFileType}"/>

                                    <input type="hidden" id="hiddenPptNo_${applicationVO.applicationId}"
                                           value="${applicationVO.passport}"/>
                                    <img style="display: block;float: right;cursor: pointer;" src="images/open.png"
                                         class="basic_image_button" width="17px" height="17px"
                                         onclick="viewPptPopup(${applicationVO.applicationId});"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-3">
                                    <label class="control-label">Personal detail page and countries allowed page</label>
                                </div>
                            </div>

                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="nicUploadPath" class="control-label"><b>NIC:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" id="hiddenNicFileName_${applicationVO.applicationId}"
                                           value="${applicationVO.nicAttachPath}"/>
                                    <input type="hidden" id="hiddenNicFileType_${applicationVO.applicationId}"
                                           value="${applicationVO.nicFileType}"/>

                                    <input type="hidden" id="hiddenNicFileNameBack_${applicationVO.applicationId}"
                                           value="${applicationVO.nicBackAttachPath}"/>
                                    <input type="hidden" id="hiddenNicFileTypeBack_${applicationVO.applicationId}"
                                           value="${applicationVO.nicBackFileType}"/>

                                    <input type="hidden" id="hiddenNicNo_${applicationVO.applicationId}"
                                           value="${applicationVO.nic}"/>
                                    <%--<img style="display: block;float: right;cursor: pointer;" src="images/open.png"--%>
                                         <%--class="basic_image_button" width="17px" height="17px"--%>
                                         <%--onclick="viewNicPopup(${applicationVO.applicationId});"/>--%>
                                    <c:choose>
                                        <c:when test="${(!empty applicationVO.nicAttachPath)  || (!empty applicationVO.nicBackAttachPath)}">
                                            <img style="display: block;float: right;" src="images/open.png"
                                                 class="basic_image_button" width="17px" height="17px"
                                                 onclick="viewNicPopup(${applicationVO.applicationId});"/>
                                        </c:when>
                                        <c:otherwise>
                                            <b><span class="mandatory_field">No file is uploaded!</span></b>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </div>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-3">
                                    <label class="control-label">Both sides</label>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="newNicUploadPath" class="control-label"><b>New NIC:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" id="hiddenNewNicFileName_${applicationVO.applicationId}"
                                           value="${applicationVO.newNicAttachPath}"/>
                                    <input type="hidden" id="hiddenNewNicFileType_${applicationVO.applicationId}"
                                           value="${applicationVO.nicFileType}"/>

                                    <input type="hidden" id="hiddenNewNicFileNameBack_${applicationVO.applicationId}"
                                           value="${applicationVO.newNicBackAttachPath}"/>
                                    <input type="hidden" id="hiddenNewNicFileTypeBack_${applicationVO.applicationId}"
                                           value="${applicationVO.nicBackFileType}"/>

                                    <input type="hidden" id="hiddenNewNicNo_${applicationVO.applicationId}"
                                           value="${applicationVO.newNic}"/>
                                    <c:choose>
                                        <c:when test="${(!empty applicationVO.newNicAttachPath)  || (!empty applicationVO.newNicBackAttachPath)}">
                                            <img style="display: block;float: right;" src="images/open.png"
                                                 class="basic_image_button" width="17px" height="17px"
                                                 onclick="viewNewNicPopup(${applicationVO.applicationId});"/>
                                        </c:when>
                                        <c:otherwise>
                                            <b><span class="mandatory_field">No file is uploaded!</span></b>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-3">
                                    <label class="control-label">Both sides</label>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="affidavitUploadPath" class="control-label"><b>Affidavit:</b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" id="hiddenAffidavitFileName_${applicationVO.applicationId}"
                                           value="${applicationVO.affidavitAttachPath}"/>
                                    <input type="hidden" id="hiddenAffidavitFileType_${applicationVO.applicationId}"
                                           value="${applicationVO.nicFileType}"/>

                                    <c:choose>
                                        <c:when test="${!empty applicationVO.affidavitAttachPath}">
                                            <img style="display: block;float: right;" src="images/open.png"
                                                 class="basic_image_button" width="17px" height="17px"
                                                 onclick="viewAffidavitPopup(${applicationVO.applicationId});"/>
                                        </c:when>
                                        <c:otherwise>
                                            <b><span class="mandatory_field">No file is uploaded!</span></b>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-3">
                                    <label class="control-label"></label>
                                </div>
                            </div>
                        </div>

                        <div style="clear:both;"></div>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <label for="birthCertificateUpload" class="control-label"><b>Birth Certificate: </b></label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" id="hiddenBSFileName_${applicationVO.applicationId}"
                                           value="${applicationVO.birthCertificatePath}"/>
                                    <input type="hidden" id="hiddenBSFileType_${applicationVO.applicationId}"
                                           value="${applicationVO.birthCertificateFileType}"/>

                                    <input type="hidden" id="hiddenBSFileNameBack_${applicationVO.applicationId}"
                                           value="${applicationVO.birthCertificateBackPath}"/>
                                    <input type="hidden" id="hiddenBSFileTypeBack_${applicationVO.applicationId}"
                                           value="${applicationVO.birthCertificateFileBackType}"/>

                                    <input type="hidden" id="hiddenBSNo_${applicationVO.applicationId}" value=""/>
                                    <%--<img style="display: block;float: right;cursor: pointer;" src="images/open.png" width="17px"--%>
                                         <%--height="17px"--%>
                                        <%--&lt;%&ndash; <c:choose>--%>
                                            <%--<c:when test="${applicationVO.age < 15 }">--%>
                                                <%--class="basic_image_button"--%>
                                                      <%--onclick="viewBSPopup(${applicationVO.applicationId});"--%>
                                               <%--</c:when>--%>
                                               <%--<c:otherwise>--%>
                                                   <%--class="basic_image_button disabled_image"--%>
                                               <%--</c:otherwise>--%>
                                           <%--</c:choose>	 &ndash;%&gt;--%>
                                         <%--class="basic_image_button"--%>
                                         <%--onclick="viewBSPopup(${applicationVO.applicationId});"--%>
                                    <%--/>--%>
                                    <c:choose>
                                        <c:when test="${(!empty applicationVO.birthCertificatePath)  || (!empty applicationVO.birthCertificateBackPath)}">
                                            <img style="display: block;float: right;" src="images/open.png"
                                                 class="basic_image_button" width="17px" height="17px"
                                                 onclick="viewBSPopup(${applicationVO.applicationId});"/>
                                        </c:when>
                                        <c:otherwise>
                                            <b><span class="mandatory_field">No file is uploaded!</span></b>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-3">
                                    <label class="control-label">Sri Lankan citizen below 18. Both sides.</label>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="col-sm-8">
                                    <label for="referredThroughBereau" class="control-label"><b>Are you referred applicant
                                        through the Sri Lankan Foreign Employment Bureau(SLBFE)?</b></label>
                                </div>
                                <div class="col-sm-4">
                                    <c:choose>
                                        <c:when test="${applicationVO.referredThroughBereau != null}">
                                            <c:choose>
                                                <c:when test="${applicationVO.referredThroughBereau == 1}">
                                                    <div class="input_div">Yes</div>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${applicationVO.referredThroughBereau == 0}">
                                                    <div class="input_div">No</div>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div style="clear:both;"></div>

                        <div id="letterOfReferenceUploadDiv">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label for="uploadLetterOfReferenceUpload" class="control-label"><b>If Yes, letter of
                                            reference from SLBFE:</b></label>
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="hidden" id="hiddenLetterOfRefFileName_${applicationVO.applicationId}"
                                               value="${applicationVO.letterOfReferencePath}"/>
                                        <input type="hidden" id="hiddenLetterOfRefFileType_${applicationVO.applicationId}"
                                               value="${applicationVO.letterOfReferenceFileType}"/>
                                        <input type="hidden" id="hiddenLetterOfRefNo_${applicationVO.applicationId}" value=""/>
                                        <%--<img style="display: block;float: right;cursor: pointer;" src="images/open.png"--%>
                                             <%--width="17px" height="17px"--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${applicationVO.referredThroughBereau != null}">--%>
                                                        <%--<c:choose>--%>
                                                            <%--<c:when test="${applicationVO.referredThroughBereau == 1}">--%>
                                                                <%--class="basic_image_button"--%>
                                                                <%--onclick="viewLetterOfRefPopup(${applicationVO.applicationId});"--%>
                                                            <%--</c:when>--%>
                                                            <%--<c:otherwise>--%>
                                                                <%--class="basic_image_button disabled_image"--%>
                                                            <%--</c:otherwise>--%>
                                                        <%--</c:choose>--%>
                                                    <%--</c:when>--%>
                                                <%--</c:choose>--%>
                                        <%--/>--%>

                                        <c:choose>
                                            <c:when test="${(!empty applicationVO.letterOfReferencePath)}">
                                                <img style="display: block;float: right;" src="images/open.png"
                                                     class="basic_image_button" width="17px" height="17px"
                                                     onclick="viewLetterOfRefPopup(${applicationVO.applicationId});"/>
                                            </c:when>
                                            <c:otherwise>
                                                <b><span class="mandatory_field">No file is uploaded!</span></b>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Sri Lankan citizen below 18. Both sides.</label>
                                    </div>
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                        </div>

                        <div style="clear:both;"></div>
                        <hr/>
                        <div style="clear:both;"></div>

                    </div>
                </div>
            </div>

            <!-- including footer -->
            <jsp:include page="../common/footer.jsp"/>

            <div style="display: none;">
                <a data-toggle="modal" id="pptViewModellLink" href="#pptViewModelPopUp">View</a>
                <a data-toggle="modal" id="nicViewModellLink" href="#nicViewModelPopUp">View</a>
                <a data-toggle="modal" id="bsViewModellLink" href="#bsViewModelPopUp">View</a>
                <a data-toggle="modal" id="letterOfRefViewModellLink" href="#letterOfRefViewModelPopUp">View</a>
                <a data-toggle="modal" id="newNicViewModellLink" href="#nicNewViewModelPopUp">View</a>
                <a data-toggle="modal" id="affidaVitViewModellLink" href="#affidavitViewModelPopUp">View</a>
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
                                <span style="font-size:18px;font-weight:bold;">NIC No. <span id="nicNumberAppend"></span></span>
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
                                        to Open/Download front side file
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
                                        to Open/Download back side file
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
            <!-- ############################################################################ -->
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
            <!-- ######################################################################################## -->
            <!--  #####################################################	Birth Certificate VIEW POPUP   ######################################################## -->
            <div class="modal fade" id="bsViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <span class="modal-title"
                                  style="font-size:15px;font-weight:bold;">Birth Certificate Verification</span>
                        </div>
                        <div class="modal-body">
                            <div>
                                <div style="padding: 5px 0px;">
                                    <span style="font-size:16px;font-weight:bold;">Scanned Birth Certificate Copy from Applicant</span>
                                </div>
                                <div id="bsLinkDiv">
                                    <div style="padding: 5px 0px;font-size:14px;" id="showHideBsLink">
                                        Please click
                                        <a id="bsFileName" target="_blank" href="javascript:void(0)">
                                            <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                        </a>
                                        to Open/Download front side file
                                    </div>
                                    <div style="padding: 10px;text-align: center;">
                                        <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                           id="bsImge_link">
                                            <img class="image-link" src="images/preloader_large.gif" id="bsImge"
                                                 style="width:275px;height:280px;border: 1px solid #000;"/>
                                        </a>
                                    </div>
                                </div>

                                <div id="bsLinkDivBack">
                                    <div style="padding: 5px 0px;font-size:14px;" id="showHideBsLinkBack">
                                        Please click
                                        <a id="bsFileNameBack" target="_blank" href="javascript:void(0)">
                                            <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                        </a>
                                        to Open/Download back side file
                                    </div>
                                    <div style="padding: 10px;text-align: center;">
                                        <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                           id="bsImge_link_back">
                                            <img class="image-link" src="images/preloader_large.gif" id="bsImgeBack"
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
            <!-- ######################################################################################## -->
            <!--  #####################################################	letter of reference from SLBFE VIEW POPUP   ######################################################## -->
            <div class="modal fade" id="letterOfRefViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <span class="modal-title"
                                  style="font-size:15px;font-weight:bold;">Letter of reference from SLBFE</span>
                        </div>
                        <div class="modal-body">
                            <div>
                                <div style="padding: 5px 0px;">
                                    <span style="font-size:16px;font-weight:bold;">Scanned letter of reference from SLBFE from Applicant</span>
                                </div>
                                <div id="letterOfRefLinkDiv">
                                    <div style="padding: 5px 0px;font-size:14px;" id="showHideLetterOfRefLink">
                                        Please click
                                        <a id="letterOfRefFileName" target="_blank" href="javascript:void(0)">
                                            <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                        </a>
                                        to Open/Download file
                                    </div>
                                    <div style="padding: 10px;text-align: center;">
                                        <a href="images/no_preview_available.png" class="passportImge_link image-link"
                                           id="letterOfRefImge_link">
                                            <img class="image-link" src="images/preloader_large.gif" id="letterOfRefImge"
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
            <!-- ######################################################################################## -->

            <!--  ##################################################### NEW	NIC VIEW POPUP   ######################################################## -->
            <div class="modal fade" id="nicNewViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <span class="modal-title" style="font-size:15px;font-weight:bold;">New NIC Verification</span>
                        </div>
                        <div class="modal-body">
                            <div style="text-align: center;" id="showHideNewNICNo">
                                <span style="font-size:18px;font-weight:bold;">New NIC No. <span id="newNicNumberAppend"></span></span>
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
                                        to Open/Download front side file
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
                                        to Open/Download back side file
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
            <!-- ############################################################################ -->

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
                                    <span style="font-size:16px;font-weight:bold;">Scanned Affidavit Copy from Applicant</span>
                                </div>
                                <div id="affidavitLinkDiv">
                                    <div style="padding: 5px 0px;font-size:14px;" id="showHideAffidavitLink">
                                        Please click
                                        <a id="affidavitFileName" target="_blank" href="javascript:void(0)">
                                            <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                        </a>
                                        to Open/Download front side file
                                    </div>
                                    <div style="padding: 10px;text-align: center;">
                                        <a href="images/no_preview_available.png" class="affidavitImge_link image-link"
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
            <!-- ############################################################################ -->


            <script type="text/javascript">

                function viewAffidavitPopup(applicationId) {
                    //blockUI();
                    //alert(applicationId);
                    var fileName = $('#hiddenAffidavitFileName_' + applicationId).val();
                    var fileType = $('#hiddenAffidavitFileType_' + applicationId).val();

                    var pptNo = $('#hiddenNicNo_' + applicationId).val();

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


                    $('#affidavitAppend').html(pptNo);

                    initImageAppidavit();

                    $('#affidaVitViewModellLink').click();
                    //unBlockUI();
                }

                function initImageAppidavit() {
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
                function viewNicPopup(applicationId) {
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


                function viewNewNicPopup(applicationId) {
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

                    $('#newNicNumberAppend').html(pptNo);

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

                function viewBSPopup(applicationId) {
                    //blockUI();
                    //alert(applicationId);
                    var fileName = $('#hiddenBSFileName_' + applicationId).val();
                    var fileType = $('#hiddenBSFileType_' + applicationId).val();

                    var fileNameBack = $('#hiddenBSFileNameBack_' + applicationId).val();
                    var fileTypeBack = $('#hiddenBSFileTypeBack_' + applicationId).val();
                    //var bsNo=$('#hiddenBSNo_' + applicationId).val();

                    if (fileType == 'IMAGE') {
                        $('#bsImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                        $('#bsImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
                    } else {
                        $('#bsImge').attr('src', 'images/no_preview_available.png');
                        $('#bsImge_link').attr('href', 'images/no_preview_available.png');
                    }

                    if (fileTypeBack == 'IMAGE') {
                        $('#bsImgeBack').attr('src', 'policeFileFinder.htm?fileName=' + fileNameBack);
                        $('#bsImge_link_back').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);
                    } else {
                        $('#bsImgeBack').attr('src', 'images/no_preview_available.png');
                        $('#bsImge_link_back').attr('href', 'images/no_preview_available.png');
                    }

                    if (fileName != "") {
                        $('#showHideBsLink').show();
                    } else {
                        $('#showHideBsLink').hide();
                    }

                    if (fileNameBack != "") {
                        $('#showHideBsLinkBack').show();
                    } else {
                        $('#showHideBsLinkBack').hide();
                    }

                    //$('#nicFileName').html(fileName);
                    $('#bsFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
                    $('#bsFileNameBack').attr('href', 'policeFileFinder.htm?fileName=' + fileNameBack);

                    //$('#bsNumberAppend').html(pptNo);

                    initImageBS();

                    $('#bsViewModellLink').click();
                    //unBlockUI();
                }

                function initImageBS() {
                    $('#bsImge_link').magnificPopup({
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

                    $('#bsImge_link_back').magnificPopup({
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

                function viewLetterOfRefPopup(applicationId) {
                    //blockUI();
                    //alert(applicationId);
                    var fileName = $('#hiddenLetterOfRefFileName_' + applicationId).val();
                    var fileType = $('#hiddenLetterOfRefFileType_' + applicationId).val();
                    //var bsNo=$('#hiddenBSNo_' + applicationId).val();

                    if (fileType == 'IMAGE') {
                        $('#letterOfRefImge').attr('src', 'policeFileFinder.htm?fileName=' + fileName);
                        $('#letterOfRefImge_link').attr('href', 'policeFileFinder.htm?fileName=' + fileName);
                    } else {
                        $('#letterOfRefImge').attr('src', 'images/no_preview_available.png');
                        $('#letterOfRefImge_link').attr('href', 'images/no_preview_available.png');
                    }

                    if (fileName != "") {
                        $('#showHideLetterOfRefLink').show();
                    } else {
                        $('#showHideLetterOfRefLink').hide();
                    }

                    //$('#nicFileName').html(fileName);
                    $('#letterOfRefFileName').attr('href', 'policeFileFinder.htm?fileName=' + fileName);

                    //$('#bsNumberAppend').html(pptNo);

                    initImageLetterOfRef();

                    $('#letterOfRefViewModellLink').click();
                    //unBlockUI();
                }

                function initImageLetterOfRef() {
                    $('#letterOfRefImge_link').magnificPopup({
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

        </c:when>
        <c:otherwise>
            <h1 style="color: red"> This application was already viewed.</h1>
        </c:otherwise>
    </c:choose>


    </body>
    </html>
</s:i18n>