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

        <title>Application Details Report</title>

    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

        <!-- including header -->
        <jsp:include page="../common/header.jsp"/>

        <!-- Starting the page Title -->
        <div id="es-content">

            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Application Details Report</c:set>
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
                            action="searchApplicationDetailsReport.action" method="post">
                        <div class="middle_content">
                            <div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label">From</s:label></strong>
                                        </div>
                                        <div class="col-sm-6">
                                            <c:set var="customStartDate">
                                                <fmt:formatDate value="${fromDate}" pattern="dd/MM/yyyy"/>
                                            </c:set>
                                            <input type="text" readonly="readonly" name="fromDate"
                                                   value="${customStartDate}" id="fromDate_id" class="form-control">
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
                                            <input type="text" readonly="readonly" name="toDate"
                                                   value="${customEndDate}" id="toDate_id" class="form-control">
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
                                                             for="reviewStatus_id">Verification Status </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:select cssClass="form-control" headerKey="" headerValue="All"
                                                      list="reviewStatusMap" listValue="value.displayName"
                                                      listKey="value"
                                                      name="searchReviewStatus" required="false"
                                                      id="searchReviewStatusId"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="clearanceStatusId">Clearance Status </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:select cssClass="form-control" headerKey="" headerValue="All"
                                                      list="clearanceStatusMap" listValue="value.displayName"
                                                      listKey="value"
                                                      name="clearanceStatus" required="false" id="clearanceStatusId"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="nicNo_id">NIC No </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:textfield name="nicNo" id="nicNo_id" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="new_nicNo_id">New NIC No </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:textfield name="newNicNo" id="new_nicNo_id" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="passportNo_id">Passport No </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:textfield name="passportNo" id="passportNo_id" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="name_id">Name </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                            <s:textfield name="name" id="name_id" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div style="clear: both;"></div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <strong><s:label cssClass="control-label bold-label"
                                                             for="country_id">Country </s:label></strong>
                                        </div>
                                        <div class="col-sm-7">
                                                <%-- 				   						<s:textfield name="country" id="country_id" cssClass="form-control"/> --%>
                                            <c:choose>
                                                <c:when test="${countryId != null}">
                                                    <select name="countryId" id="country_id" class="form-control">
                                                        <option value="0">Please select</option>
                                                        <c:forEach var="country" items="${countryList}">
                                                            <c:choose>
                                                                <c:when test="${countryId == country.id}">
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
                                                    <select name="countryId" id="country_id" class="form-control">
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

                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div style="text-align:right;">
                                                <s:submit value="Search"
                                                          cssClass="btn btn-primary es-buttton"></s:submit>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div style="text-align:right;">
                                                    <%-- 											<s:submit value="Reset" cssClass="btn btn-primary es-buttton"  onclick="reSetform()"></s:submit> --%>
                                                <input type="button" onclick="reSetform()" value="Reset"
                                                       class="btn btn-primary es-buttton">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>
                        <div class="form-group">
                            <div class="col-lg-12">

                                <div class="table-responsive">
                                    <div style="float: right;">
                                        <h5><b>Total No. of records : ${fn:length(applicationList)}</b></h5>
                                    </div>
                                    <div style="clear: both;"></div>
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th class="text-center"><strong>Reference No</strong></th>
                                            <th class="text-center"><strong>Current Nic No</strong></th>
                                            <th class="text-center"><strong>Passport No</strong></th>
                                            <th class="text-center"><strong>Name</strong></th>
                                            <th class="text-center"><strong>Address</strong></th>
                                            <th class="text-center"><strong>Date</strong></th>
                                            <th class="text-center"><strong>Verification Status</strong></th>
                                            <th class="text-center"><strong>Clearance Status</strong></th>
                                            <th class="text-center"><strong>Posted Date</strong></th>
                                            <th class="text-center"><strong>Mode</strong></th>
                                            <th class="text-center"><strong>Country</strong></th>
                                                <%-- 												<th class="text-center"><strong>Rivisions</strong></th> --%>
                                                <%-- 												<th class="text-center"><strong>Review Status</strong></th>		 --%>
                                                <%-- 												<th class="text-center" style="width: 5%;"><strong>Save</strong></th>	 --%>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${applicationList}" var="applicationVO">
                                            <tr style="background-color: #DFF0D8;">
                                                <td class="text-left">${applicationVO.referenceNo}</td>

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

                                                <td class="text-left">${applicationVO.passport}</td>
                                                <td class="text-left"
                                                    style="width:15%;">${applicationVO.applicantNameAsNic}</td>
                                                <td class="text-left wrapword"
                                                    style="width:20%;">${applicationVO.presentAddressLocal}</td>
                                                <td class="text-left"><fmt:formatDate
                                                        value="${applicationVO.submittedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                                <td class="text-center">${applicationVO.applicationReviewStatus}</td>
                                                <td class="text-center">${applicationVO.applicationClearanceStatus}</td>
                                                <td class="text-center"><fmt:formatDate
                                                        value="${applicationVO.certificatePostedDate}"
                                                        pattern="dd/MM/yyyy hh:mm a"/></td>
                                                <td class="text-left">${applicationVO.applicationType}</td>
                                                <td class="text-left">${applicationVO.countryName}</td>
                                                <!-- 									<td class="text-center">online</td> -->
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>


                        <div style="clear: both;"></div>

                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div style="text-align:right;">
                                        <input type="hidden" id="rptType" name="rptType"/>
                                            <%-- 										<s:submit value="Print" cssClass="btn btn-primary es-buttton" onclick="printApplicationDetailsReport()" ></s:submit> --%>
                                        <input type="button" onclick="printApplicationDetailsReport()" value="Print"
                                               class="btn btn-primary es-buttton">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div style="text-align:right;">
                                            <%-- 										<s:submit value="Export to Excel" cssClass="btn btn-primary es-buttton" onclick="printApplicationDetailsReport_Excel()" ></s:submit> --%>
                                        <input type="button" onclick="printApplicationDetailsReport_Excel()"
                                               value="Export to Excel" class="btn btn-primary es-buttton">
                                    </div>
                                </div>
                            </div>
                        </div>


                    </s:form>
                </div>
            </div>


        </div>

        <!-- including footer -->
        <jsp:include page="../common/footer.jsp"/>

    </div>
    <script type="text/javascript" src="js/jquery.numeric.js"></script>
    <script language="javascript" type="text/javascript">

        $(document).ready(function () {
            initializeDateTimePickers();
        });

        function initializeDateTimePickers() {
// 		  $('#fromDate_id').datepicker({
// 			  dateFormat:"dd/mm/yy"
// 		  });
// 		  $('#toDate_id').datepicker({
// 			  dateFormat:"dd/mm/yy"
// 		  });	 
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

        function reSetform() {
            window.location = "viewApplicationDetailsReport";
        }

        function printApplicationDetailsReport() {
            window.open("printApplicationDetailsReport");
        }

        function printApplicationDetailsReport_Excel() {
            window.open("printApplicationDetailsReportExcel");
        }

    </script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $("#messagesDiv").fadeIn(700).delay(7000).fadeOut(5000);
        });
    </script>
    </body>
    </html>
</s:i18n>