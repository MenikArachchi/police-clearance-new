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

        <title>View Application Status</title>

    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

        <!-- including header -->
        <jsp:include page="../common/header.jsp"/>

        <!-- Starting the page Title -->
        <div id="es-content">

            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">View Application Status</c:set>
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
            <br/>
            <div class="middle_content">

                <div class="col-lg-12">
                    <div style="float: right;">
                        <form action="printApplicationStatus.action" target="_blank">
                            <input type="hidden" name="aid" value="${applicationStatusViewGridVO.applicationId}"/>
                            <input type="submit" class="btn btn-primary es-buttton" value="Print"/>
                        </form>
                    </div>
                </div>
                <div style="clear: both;"></div>
                <br/>
                <br/>
                <table class="col-lg-12">
                    <tr>
                        <td width="10%">
                            <label for="referenecNo" class="control-label"><b>Reference No:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left"
                                 id="referenecNo">${applicationStatusViewGridVO.referenceNo}</div>
                        </td>
                        <td width="10%">&nbsp;</td>
                        <td width="15%">
                            <label class="control-label"><b>Date of Appli. Handed over:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left"><fmt:formatDate
                                    value="${applicationStatusViewGridVO.submittedDate}"
                                    pattern="dd/MM/yyyy hh:mm aa"/></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><b>Full Name:</b></label>
                        </td>
                        <td colspan="4">
                            <c:choose>
                                <c:when test="${not empty applicationStatusViewGridVO.applicantNameAsNic || applicationStatusViewGridVO.applicantNameAsNic != ''}">
                                    <div class="input_div input_div_custom_left" >${applicationStatusViewGridVO.applicantNameAsNic}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input_div input_div_custom_left" >${applicationStatusViewGridVO.applicantNameAsPassport}</div>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                    <tr>
                    <td colspan="5">&nbsp;</td>
                </tr>
                    <tr>
                        <td width="10%">
                            <label for="referenecNo" class="control-label"><b>NIC Number:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left"
                                 id="referenecNo">${applicationStatusViewGridVO.nic}</div>
                        </td>
                        <td width="10%">&nbsp;</td>
                        <td width="15%">
                            <label class="control-label"><b>Passport No:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.passport}</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="10%">
                            <label class="control-label"><b>New NIC:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left" >${applicationStatusViewGridVO.newNic}</div>
                        </td>
                        <td width="10%">&nbsp;</td>
                        <td width="15%"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="10%">
                            <label class="control-label"><b>Application Verification Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.applicationReviewStatus}</div>
                        </td>
                        <td width="10%">&nbsp;</td>
                        <td width="15%">
                            <label class="control-label"><b>Application Clearance Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.applicationClearanceStatus}</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="10%">
                            <label class="control-label"><b>Certificate Issued Date:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left"><fmt:formatDate
                                    value="${applicationStatusViewGridVO.certificateIssuedDate}"
                                    pattern="dd/MM/yyyy hh:mm a"/></div>
                        </td>
                        <td width="10%">&nbsp;</td>
                        <td width="15%">
                            <label class="control-label"><b>Certificate Posted Date:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left"><fmt:formatDate
                                    value="${applicationStatusViewGridVO.certificatePostedDate}"
                                    pattern="dd/MM/yyyy hh:mm a"/></div>
                        </td>
                    </tr>
                </table>

                <div style="clear:both;"></div>
                <br/>
                <hr width="100%" style="height:1px;border:none;color:#333;background-color:#ccc;"/>


                <table class="col-lg-12">
                    <tr>
                        <td width="10%">
                            <label class="control-label"><b>CO Approval Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.coApproved}</div>
                        </td>
                        <td width="2%">&nbsp;</td>
                        <td width="10%">
                            <label class="control-label"><b>OIC Approval Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.oicApproved}</div>
                        </td>
                        <td width="2%">&nbsp;</td>
                        <td width="10%">
                            <label class="control-label"><b>ASP Approval Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.aspApproved}</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="10%">
                            <label class="control-label"><b>DHA Approval Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.dhaApproved}</div>
                        </td>
                        <td width="2%">&nbsp;</td>
                        <td width="10%">
                            <label class="control-label"><b>DIG Approval Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.digApproved}</div>
                        </td>
                        <td width="2%">&nbsp;</td>
                        <td width="10%">
                            <label class="control-label"><b>Posted Status:</b></label>
                        </td>
                        <td>
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.postedStatus}</div>
                        </td>
                    </tr>
                </table>

                <div style="clear:both;"></div>
                <br/>
                <hr width="100%" style="height:1px;border:none;color:#333;background-color:#ccc;"/>

                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-7">
                            <label class="control-label"><b>Certificate Reference Number:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.certificateSerialNo}</div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label class="control-label"><b>Date of Birth:</b></label>
                        </div>
                        <div class="col-sm-8">
                            <div class="input_div input_div_custom_left"><fmt:formatDate
                                    value="${applicationStatusViewGridVO.dateOfBirth}" pattern="dd/MM/yyyy"/></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label class="control-label"><b>Tel No:</b></label>
                        </div>
                        <div class="col-sm-8">
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.mobileNo}</div>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <br/>


                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-7">
                            <label class="control-label"><b>Status:</b></label>
                        </div>
                        <div class="col-sm-5">
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.applicantStatus}</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label class="control-label"><b>Sex:</b></label>
                        </div>
                        <div class="col-sm-8">
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.sex}</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label class="control-label"><b>Purpose:</b></label>
                        </div>
                        <div class="col-sm-8">
                            <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.purpose}</div>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>

                <br/>

                    <%-- 	<c:if test="${! empty applicationStatusViewGridVO.recommendedOfficerName }">	 --%>
                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">Personal Recommendation</legend>
                    <div class="col-lg-11">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label"><b>Name of the officer:</b></label>
                            </div>
                            <div class="col-sm-10">
                                <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.recommendedOfficerName}</div>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <br/>


                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label"><b>Rank:</b></label>
                            </div>
                            <div class="col-sm-10">
                                <div class="input_div input_div_custom_left"><c:out
                                        value="${applicationStatusViewGridVO.recommendedOfficerRank}"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label"><b>Date:</b></label>
                            </div>
                            <div class="col-sm-10">
                                <div class="input_div input_div_custom_left"><fmt:formatDate
                                        value="${applicationStatusViewGridVO.recommendedDate}"
                                        pattern="dd/MM/yyyy hh:mm a"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label"><b>Comment:</b></label>
                            </div>
                            <div class="col-sm-8">
                                <div class="input_div input_div_custom_left">${applicationStatusViewGridVO.recommendedComment}</div>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <br/>
                </fieldset>
                    <%-- 	</c:if>	 --%>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">CID Return</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.cidStatusViewGridVOs}" var="gridVO">
                                <tr>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">TID Return</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.tidStatusViewGridVOs}" var="gridVO">
                                <tr>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">SIS Return</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.sisStatusViewGridVOs}" var="gridVO">
                                <tr>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">NIC Return</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.nicStatusViewGridVOs}" var="gridVO">
                                <tr>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">IMI Return</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.imiStatusViewGridVOs}" var="gridVO">
                                <tr>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>


                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">Return from Police Stations</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Station</th>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.addressStatusViewGridVOsPolice}"
                                       var="gridVO">
                                <tr>
                                    <td>${gridVO.policeArea}</td>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>

                <fieldset>
                    <legend style="font-size: 14px; font-weight: bold;">Updates By Police Headquarters</legend>
                    <div style="margin-left: 30px;">
                        <table class="col-lg-10 table table-bordered table-striped">
                            <tr>
                                <th>Station</th>
                                <th>Date Sent</th>
                                <th>Sent By</th>
                                <th>Date Responded</th>
                                <th>Responded By</th>
                                <th>Approval Status</th>
                                <th width="50%">Comment</th>
                            </tr>
                            <c:forEach items="${applicationStatusViewGridVO.addressStatusViewGridVOsPhq}" var="gridVO">
                                <tr>
                                    <td>${gridVO.policeArea}</td>
                                    <td><fmt:formatDate value="${gridVO.sentDate}" pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.sentUserName}</td>
                                    <td><fmt:formatDate value="${gridVO.receivedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                    <td>${gridVO.respondedUserName}</td>
                                    <td>${gridVO.adverse}</td>
                                    <td>${gridVO.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>

                <br/>
                <br/>
            </div>

            <!-- including footer -->
            <jsp:include page="../common/footer.jsp"/>

        </div>
    </div>
    </body>
    </html>
</s:i18n>