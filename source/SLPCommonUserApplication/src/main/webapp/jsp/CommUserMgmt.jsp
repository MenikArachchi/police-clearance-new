<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:url id="populateServiceUrl" action="PopulateServices"/>
<html>
<head>
    <style>
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 53%;
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .custom-table {
            width: 100%;
            border-collapse: collapse;
        }

        .custom-table, .custom-table-head {
            border: 1px solid black;
            text-align: center;
            font-size: inherit;

        }

        .custom-table-td {
            border: 1px solid black;
            text-align: center;
            font-size: inherit;
        }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <title>User Management</title>

    <sj:head debug="true" compressed="false" jquerytheme="showcase"
             customBasepath="themes" defaultIndicator="myDefaultIndicator"
             defaultLoadingText="Please wait ..."/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">

    <!-- <script type="text/javaScript" src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"></script> -->
    <script type="text/javaScript" src="<%=request.getContextPath()%>/js/CommonUserMgmt.js"></script>

    <script type="text/javascript">
        window.history.forward();
        window.onpageshow = function (evt) {
            if (evt.persisted) noBack();
        };

        function noBack() {
            window.history.forward();
        }

        function populateDeptServices() {
            var selectedDeptId = $('#departmentId').val();
            var selectedUserId = $('#createdUserId').val();
            var operation = $('#operation').val();
            var isSessionExpired = validateSession();

            var divIdToLoad = '';

            if (isSessionExpired) {
                divIdToLoad = "#container";

            } else {
                divIdToLoad = "#div_serviceList";
            }

            $(divIdToLoad).load("<s:property value='populateServiceUrl'/>?selDept=" + selectedDeptId + "&selectedUserId=" + selectedUserId + "&operation=" + operation);
            resetValidBtn();
        }

        function populateUserBasedOnDept() {
            var loggedOnUserType = document.getElementById('loggedOnUserType').value;
            var deptCmbo = document.getElementById("departmentId");
            var selDept = deptCmbo.options[deptCmbo.selectedIndex].value;
            var selectedUserId = $('#createdUserId').val();
            $("#assignedLocationId").val("-1");
            try {
                var assignedLocation = parseInt($('#hiddenAssignedLocationFromDb').val());
                if (!(assignedLocation <= 0)) {
                    $("#assignedLocationId").val(assignedLocation);
                }
            } catch (e) {
                $("#assignedLocationId").val("-1");
            }


            if (selDept == '2') {
                document.getElementById("assignedLocationId").disabled = false;
            } else {
                document.getElementById("assignedLocationId").disabled = true;
            }


            $('#createdUserId').empty().append('<option value="0">--Select--</option>');

            $.ajax({
                async: false,
                cache: false,
                type: 'GET',
                url: '<%= request.getContextPath()%>/commonAdminServlet.data?method=populateUsers&departmentId='
                + selDept + '&loggedOnUserType=' + loggedOnUserType,
                success: function (xml) {
                    resetUserList();
                    $(xml).find('user').each(function () {
                        var key = $(this).find('user-id').text();
                        var value = $(this).find('user-full-name').text();
                        $("#createdUserId").append(
                            "<option value=" + key + ">"
                            + value + "</option>");
                    });
                },
                error: function (data, textStatus, errorThrown) {
                    //if there is some error handling mechanism that needs to be implemented
                }
            });

            if (parseInt(selectedUserId) != 0) {
                document.getElementById('createdUserId').value = selectedUserId;
            }
        }

        function validateSession() {

            var isSessionExpired = false;

            $.ajax({
                async: false,
                cache: false,
                type: 'GET',
                url: '<%= request.getContextPath()%>/commonAdminServlet.data?method=checkSession',
                success: function (xml) {
                    $(xml).find('session-check').each(function () {
                        var isExpired = $(this).find('is-expired').text();
                        // alert("isExists: " + isExists);
                        if (isExpired == "true") {
                            isSessionExpired = true;
                        }
                    });
                },
                error: function (data, textStatus, errorThrown) {
                    //if there is some error handling mechanism that needs to be implemented
                }
            });

            return isSessionExpired;

        }


    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            populateDeptServices();
        });

        function viewDocumentPopup(filename) {
            blockUI();
            $('#documentImge').attr('src', 'policeFileFinder.htm?fileName=' + filename);
            $('#documentImge_link').attr('href', 'policeFileFinder.htm?fileName=' + filename);

            $('#documentFileName').attr('href', 'policeFileFinder.htm?fileName=' + filename);

            initImageDocument();

            $('#documentViewModellLink').click();
            unBlockUI();
        }

        function initImageDocument() {
            $('#documentImge_link').magnificPopup({
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

        function getLocaleMessage(messageKey, param) {
            var message = '';

            $.ajax({
                url: '<%= request.getContextPath()%>/jsonlangloader.data?key=' + messageKey + '&param=' + param,
                cache: false,
                async: false,
                dataType: "json",
                success: function (data) {
                    $.each(data, function (key, value) {
                        message = value;
                    });
                }
            });

            return message;
        }
    </script>

</head>
<body onload="noBack();setOperationSelected();setDefaultOp();populateUserListForDA();populateUserBasedOnDept()"
      onunload="" class="oneColElsCtrHdr" style="overflow-y: scroll;">

<div id="container">
    <div id="header_div">
        <jsp:include page="header.jsp"></jsp:include>
    </div>

    <div id="mainContent">

        <c:if test="${canAccess}">
            <h3 align="center">Search Police Station User Details</h3>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">

                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 44px">Location
                    </td>

                    <td width="60%" align="left" style="padding-left: 20px">
                        <input type="hidden" id="hiddenAssignedLocationId"
                               value="${userVo.assignedLocation}"/>
                        <select class="text_box" name="userVo.assignedLocation" id="locationId"
                                onkeydown="handleEnterKey(event)">
                            <option value="0">--Select--</option>
                            <c:forEach items="${availableLocations}" var="location">
                                <option value="${location.id}">${location.locationName}</option>
                            </c:forEach>
                        </select>

                        <input type="submit" id="searchBtn" class="submit_btn_base" value="Search"
                               onclick="viewDocumentPopup('test');"/>
                    </td>
                    </td>
                </tr>


            </table>

            <br>
            <br>
            <hr>
        </c:if>

        <s:form action="CommUserMgmt">
            <h3 align="center">Add/Edit User Details</h3>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <s:hidden id="loggedOnUserType" name="loggedOnUserType"></s:hidden>
                <s:hidden id="operation" name="operation"></s:hidden>
                <s:hidden id="selectedUserId" name="selectedUserId"></s:hidden>

                <tr>
                    <td colspan="4" align="center" valign="bottom"><s:if
                            test="hasActionErrors()">
                        <div id="errorPart" class="errorDiv"><s:actionerror/></div>
                    </s:if>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 20px"><s:text
                            name="commonuser.department"/></td>
                    <td colspan="2" width="55%" align="left" style="padding-left: 25px">
                        <s:select cssClass="text_box" list="deptList" id="departmentId"
                                  listKey="id" listValue="name" name="userVo.dept.id" headerKey="-1"
                                  onchange="onSelectDept();populateUserBasedOnDept();clearErrorFields()"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" align="center" class="tdLabel">
                        <input
                                type="radio" name="ops" value="add" id="addOpType"
                                onclick="setOpVal('add');checkAndPopulateUsers();clearErrorFields()"/><s:text
                            name="commonuser.addUser"/>&nbsp;&nbsp;&nbsp;&nbsp;

                        <input
                                type="radio" name="ops" value="edit" id="editOpType"
                                onclick="setOpVal('edit');checkAndPopulateUsers();clearErrorFields()"/><s:text
                            name="commonuser.editUser"/> &nbsp;&nbsp; <s:select align="center"
                                                                                id="createdUserId" cssClass="text_box"
                                                                                cssStyle="width:150px"
                                                                                list="userList" name="userVo.id"
                                                                                headerKey="0"
                                                                                headerValue="--Select--"
                                                                                onchange="clearErrorFields();onSelectUser()"
                                                                                disabled="true"/>&nbsp;&nbsp;
                        <s:submit id="fetchButton" cssClass="submit_btn_base"
                                  action="FetchUser" key="commonuser.fetchUser"
                                  onclick="resetValidBtn();" disabled="true"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 20px"><s:text
                            name="commonuser.currentNic"/><font color='red'>*</font>
                    </td>

                    <td colspan="2" width="55%" align="left" style="padding-left: 24px">
                        <s:textfield cssClass="text_box" name="userVo.currentNic" id="currentNic"
                                     size="38" maxLength="100" onkeydown="handleEnterKey(event)"/>

                        <input type="button" class="submit_btn_base" id="validateUserBtn"
                               onclick="checkExitingUsers();" value="Check Exiting Users">
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 20px"><s:text
                            name="commonuser.nameOfUser"/><font color='red'>*</font></td>
                    <td colspan="2" width="55%" align="left" style="padding-left: 24px">
                        <s:textfield cssClass="text_box" name="userVo.fullName" id="fullName"
                                     size="38" maxLength="100" onkeydown="handleEnterKey(event)"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 20px"><s:text
                            name="commonuser.emailOfUser"/><font color='red'>*</font></td>
                    <td colspan="2" width="55%" align="left" style="padding-left: 24px">
                        <s:textfield cssClass="text_box" name="userVo.emailId" id="emailId"
                                     size="38" maxLength="100" onkeydown="handleEnterKey(event)"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right"
                        style="color: #0000FF; font-family: Geneva, Arial, Helvetica, sans-serif, Decker; font-size: 11px; font-weight: bold; padding-right: 80px;">
                        <s:text
                                name="commonuser.loginDetails"/></td>
                    <td colspan="2" width="55%" align="left" style="padding-left: 4px">&nbsp;</td>
                </tr>
                <tr id="userTypeUserNameTrID">
                    <td colspan="4" align="center">
                        <table id="userTypeUserNameID" border="0" width="44%"
                               style="border-left: solid 1px #d0d0d0; border-right: solid 1px #d0d0d0; border-top: solid 1px #d0d0d0;">

                            <tr>
                                <td width="40%" align="right" class="tdLabel"
                                    style="padding-right: 16px"><s:text
                                        name="Location"/></td>

                                <td width="60%" align="left" style="padding-left: 20px">
                                    <input type="hidden" id="hiddenAssignedLocationFromDb"
                                           value="${userVo.assignedLocation}"/>
                                    <select class="text_box" name="userVo.assignedLocation" id="assignedLocationId"
                                            onkeydown="handleEnterKey(event)" disabled="disabled">
                                        <option value="0">--Select--</option>
                                        <c:forEach items="${availableLocations}" var="location">
                                            <c:choose>
                                                <c:when test="${location.id==userVo.assignedLocation}">
                                                    <option value="${location.id}"
                                                            selected>${location.locationName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${location.id}">${location.locationName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>

                                </td>
                            </tr>
                            <tr>
                                <td width="40%" align="right" class="tdLabel"
                                    style="padding-right: 16px"><s:text
                                        name="commonuser.userType"/></td>
                                <td width="60%" align="left" style="padding-left: 20px"><s:select
                                        cssClass="text_box" id="userTypeId" name="assignedUserType"
                                        list="assignableUserTypes" onkeydown="handleEnterKey(event)"/></td>
                            </tr>
                            <tr>
                                <td width="40%" align="right" class="tdLabel"
                                    style="padding-right: 16px"><s:text
                                        name="commonuser.userNameOfUser"/><font color='red'>*</font></td>
                                <td width="60%" align="left" style="padding-left: 20px"><s:textfield
                                        cssClass="text_box" name="userVo.userName" id="userName" size="38"
                                        maxLength="100"
                                        onkeydown="handleEnterKey(event)"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr id="passwordsTrID" style="display: ;">
                    <td colspan="4" align="center">
                        <table border="0" width="44%"
                               style="border-left: solid 1px #d0d0d0; border-right: solid 1px #d0d0d0; border-bottom: solid 1px #d0d0d0;">
                            <tr>
                                <td width="40%" align="right" class="tdLabel"
                                    style="padding-right: 16px"><s:text
                                        name="commonuser.passwordOfUser"/><font color='red'>*</font></td>
                                <td width="60%" align="left" style="padding-left: 20px"><s:password
                                        cssClass="text_box" name="userVo.password" id="password" size="40"
                                        maxLength="100"
                                        onkeydown="handleEnterKey(event)"/></td>
                            </tr>
                            <tr>
                                <td width="40%" align="right" class="tdLabel"
                                    style="padding-right: 16px"><s:text
                                        name="commonuser.confirmPwd"/><font color='red'>*</font></td>
                                <td width="60%" align="left" style="padding-left: 20px"><s:password
                                        cssClass="text_box" name="confirmedPwd" id="confirmedPwd"
                                        size="40" maxLength="100" onkeydown="handleEnterKey(event)"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" width="45%" align="right" class="tdLabel"
                        style="padding-right: 18px"><s:text
                            name="commonuser.userStatus"/></td>
                    <td colspan="2" width="55%" align="left" class="tdLabel"
                        style="padding-left: 18px"><s:radio id="statusId"
                                                            name="presentStatus" list="userStatus"
                                                            onkeydown="handleEnterKey(event)"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>

                <tr>
                    <td colspan="4" align="center" width="100%"><sj:div id="div_serviceList">
                        <jsp:include page="ServiceDetails.jsp"></jsp:include>
                    </sj:div></td>
                </tr>

                <tr>
                    <td colspan="4" align="center"><s:submit id="buttonSubmit"
                                                             cssClass="submit_btn_base" action="ProcessCommUserMgmt"
                                                             key="commonuser.submit" onclick="return check_Input()"/>&nbsp;&nbsp;<input
                            type="button" class="submit_btn_base"
                            value="<s:text name="commonuser.reset" />" onclick="ResetUserMgmt()"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">&nbsp;</td>
                </tr>
            </table>
        </s:form>

    </div>

</div>


<div id="footer">
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</div>

<!-- The Modal -->
<div id="viewUserAccounts" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <h3 id="policeStationHeadId"></h3>

    </div>

</div>

<script type="text/javascript">
    // Get the modal
    var modal = document.getElementById('viewUserAccounts');

    // Get the button that opens the modal
    var btn = document.getElementById("searchBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {

        var selectedPloiceStation = $("#locationId option:selected").text();
        var locationId = $("#locationId option:selected").val();
        $('#accountsTableId').remove();

        if (locationId > 0) {
            var accountsDetails = '';
            $.post('findAllUsersByPoliceStation.action', {policeStationId: locationId}, function (data) {
                accountsDetails = accountsDetails + '<table class="custom-table" id="accountsTableId">';
                accountsDetails = accountsDetails + '<tr>';
                accountsDetails = accountsDetails + '<td class="custom-table-head" style="font-weight: bold"><br>Name<br></td>';
                accountsDetails = accountsDetails + '<td class="custom-table-head" style="font-weight: bold"><br>Username<br></td>';
                accountsDetails = accountsDetails + '<td class="custom-table-head" style="font-weight: bold"><br>Current NIC<br></td>';
                accountsDetails = accountsDetails + '<td class="custom-table-head" style="font-weight: bold"><br>Email<br></td>';
                accountsDetails = accountsDetails + '<td class="custom-table-head" style="font-weight: bold"><br>User Status<br></td>';
                accountsDetails = accountsDetails + '</tr>';

                var usersCount = data.assignedUsers.length;

                for (var i = 0; i < usersCount; i++) {
                    var userVO = data.assignedUsers[i];
                    accountsDetails = accountsDetails + '<tr>';
                    accountsDetails = accountsDetails + '<td class="custom-table-td">' + userVO.fullName + '</td>';
                    accountsDetails = accountsDetails + '<td class="custom-table-td">' + userVO.userName + '</td>';
                    accountsDetails = accountsDetails + '<td class="custom-table-td">' + userVO.currentNic + '</td>';
                    accountsDetails = accountsDetails + '<td class="custom-table-td">' + userVO.emailId + '</td>';
                    accountsDetails = accountsDetails + '<td class="custom-table-td">' + userVO.status + '</td>';
                    accountsDetails = accountsDetails + '</tr>';
                }

                accountsDetails = accountsDetails + '</table>';
                $('#policeStationHeadId').after(accountsDetails);

                $('#policeStationHeadId').text('Police Station: ' + selectedPloiceStation);

                $('#viewUserAccounts').show();

            });


        } else {
            alert('Please select a location');
        }

    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        $('#viewUserAccounts').hide();
    };

</script>

</body>
</html>
