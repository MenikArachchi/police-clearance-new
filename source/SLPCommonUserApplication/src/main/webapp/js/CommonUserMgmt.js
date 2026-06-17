var errorFieldColor = "#FFFFCC";
var disbaledButtonColor = '#C0C0C0';
var suType = "1";
var daType = "2";
var browserName = navigator.appName;


function setOpVal(val) {
    document.getElementById('operation').value = val;
    resetValidBtn();
}

function resetValidBtn() {
    var selectedOps = $('input[name=ops]:checked').val();
    if (selectedOps == 'edit') {
        $('#validateUserBtn').hide();
    } else {
        $('#validateUserBtn').show();
    }
}
function checkAndPopulateUsers() {
    resetValidBtn();
    var selectedOp = document.getElementById('operation').value;
    if (selectedOp == 'edit') {
        $('#validateUserBtn').hide();
        document.getElementById('createdUserId').disabled = false;
        document.getElementById('fetchButton').disabled = false;
        if (browserName != "Microsoft Internet Explorer") {
            document.getElementById('fetchButton').style.background = '';
        }
        document.getElementById('passwordsTrID').style.display = 'none';
        document.getElementById('userTypeUserNameID').style.borderBottom = '1px solid #d0d0d0';

        resetDetails();
        resetUserList();
        document.getElementById("fetchButton").focus();
    } else if (selectedOp == 'add') {

        document.getElementById('createdUserId').disabled = true;
        document.getElementById('fetchButton').disabled = true;
        if (browserName != "Microsoft Internet Explorer") {
            document.getElementById('fetchButton').style.background = disbaledButtonColor;
        }
        document.getElementById('passwordsTrID').style.display = '';
        document.getElementById('userTypeUserNameID').style.borderBottom = '';
        resetDetails();
        resetPasswords();
        resetUserList();
        document.getElementById("fullName").focus();
    }

}

function resetUserList() {
    document.getElementById('createdUserId').selectedIndex = '0';
}

function resetDetails() {
    $("#errorPart").empty();
    document.getElementById('fullName').value = '';
    document.getElementById('emailId').value = '';
    document.getElementById('userName').value = '';
    $('#currentNic').val('');
    if (document.getElementById('serviceListTrId') != null) {
        var chkBoxes = document.getElementsByName("servicesForUser");
        var i = 0;
        for (i = 0; i < chkBoxes.length; i++) {
            if (chkBoxes[i].disabled == false) {
                chkBoxes[i].checked = false;
            }
        }
    }
}

function onSelectDept() {
    checkAndPopulateUsers();
    resetDetails();
    resetUserList();
    resetPasswords();
    populateDeptServices();
}

function resetPasswords() {
    if (document.getElementById('addOpType').checked) {
        document.getElementById('password').value = '';
        document.getElementById('confirmedPwd').value = '';
    }
}

function ResetUserMgmt() {
    $("#errorPart").empty();

    resetDetails();
    var loggedOnUserType = document.getElementById('loggedOnUserType').value;
    if (loggedOnUserType == suType) {
        document.getElementById('departmentId').selectedIndex = '0';
    }
    document.getElementById('createdUserId').selectedIndex = '0';
    resetPasswords();
    document.getElementById("fullName").focus();
    clearErrorFields();
}

function resetServiceList() {
    $('#serviceListTableId').empty();
}

function setDefaultOp() {
    resetValidBtn();
    if (!(document.getElementById('addOpType').checked) && !(document.getElementById('editOpType').checked)) {
        document.getElementById('addOpType').checked = true;
        document.getElementById('operation').value = 'add';
        document.getElementById('passwordsTrID').style.display = '';
        document.getElementById('userTypeUserNameID').style.borderBottom = '';
        if (browserName != "Microsoft Internet Explorer") {
            document.getElementById('fetchButton').style.background = disbaledButtonColor;
        }
    }
}
function setOperationSelected() {
    var opSel = document.getElementById('operation').value;
    if (opSel == 'add') {
        document.getElementById('addOpType').checked = true;
        document.getElementById('createdUserId').disabled = true;
        document.getElementById('fetchButton').disabled = true;
        if (browserName != "Microsoft Internet Explorer") {
            document.getElementById('fetchButton').style.background = disbaledButtonColor;
        }
        document.getElementById('passwordsTrID').style.display = '';
        document.getElementById('userTypeUserNameID').style.borderBottom = '';
    } else if (opSel == 'edit') {
        document.getElementById('editOpType').checked = true;
        document.getElementById('createdUserId').disabled = false;
        document.getElementById('fetchButton').disabled = false;
        if (browserName != "Microsoft Internet Explorer") {
            document.getElementById('fetchButton').style.background = '';
        }
        document.getElementById('passwordsTrID').style.display = 'none';
        document.getElementById('userTypeUserNameID').style.borderBottom = '1px solid #d0d0d0';
    }
    document.getElementById("buttonSubmit").focus();
}
function check_Input() {

    clearErrorFields();

    var errmsg1 = 'Following are mandatory :' + "\n";
    var errmsg2 = 'Following are mandatory :' + "\n";

    var errorFileds = new Array();
    var count = 0;

    var deptCmbo = document.getElementById("departmentId");
    var selDept = deptCmbo.options[deptCmbo.selectedIndex].value;
    var userFullName = document.getElementById('fullName').value;
    var userEmail = document.getElementById('emailId').value;
    var userName = document.getElementById('userName').value;
    var location = document.getElementById("assignedLocationId");
    var locationValue = location.options[location.selectedIndex].value;
    var password;
    var confPassword;

    var isFirstErrorElement = '';

    if (selDept == "-1") {
        errmsg1 = errmsg1 + '- Valid Organisation' + "\n";
        errorFileds[count++] = 'departmentId';
        isFirstErrorElement = 'departmentId';
    }
    if (!(document.getElementById('addOpType').checked) && !(document.getElementById('editOpType').checked)) {
        errmsg1 = errmsg1 + '- Operation type selection (Add or Edit user)' + "\n";
    }
    if (document.getElementById('editOpType').checked) {

        var userCmbo = document.getElementById("createdUserId");
        var selUser = userCmbo.options[userCmbo.selectedIndex].value;

        if (selUser == 0) {
            errmsg1 = errmsg1 + '- Valid User' + "\n";
            errorFileds[count++] = 'createdUserId';

            if (isFirstErrorElement == '') {
                isFirstErrorElement = 'createdUserId';
            }
        }
    }

    var currentNic = $('#currentNic').val();

    if (currentNic == null || currentNic == '') {
        errorFileds[count++] = 'currentNic';
        errmsg1 = errmsg1 + '- Please enter valid NIC number' + "\n";
        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'currentNic';
        }
    }

    if (currentNic.length != 10 && currentNic.length != 12){
        errorFileds[count++] = 'currentNic';
        errmsg1 = errmsg1 + '- Invalid NIC Number' + "\n";
        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'currentNic';
        }
    }

    checkExitingUsers();


    if (userFullName == null || userFullName.length == 0) {
        errorFileds[count++] = 'fullName';
        errmsg1 = errmsg1 + '- Valid Name of the User' + "\n";
        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'fullName';
        }
    }
    if (userEmail == null || userEmail.length == 0) {
        errorFileds[count++] = 'emailId';

        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'emailId';
        }
        errmsg1 = errmsg1 + '- Valid Email' + "\n";
    }
    if (!isEmailValid(userEmail)) {
        errorFileds[count++] = 'emailId';

        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'emailId';
        }
        errmsg1 = errmsg1 + '- Valid email format' + "\n";
    }
    if (selDept == "2") {
        if (locationValue == "0") {
            errmsg1 = errmsg1 + '- Valid Location' + "\n";
            errorFileds[count++] = 'assignedLocationId';
            isFirstErrorElement = 'assignedLocationId';
        }
    }
    if (userName == null || userName.length == 0) {
        errorFileds[count++] = 'userName';

        if (isFirstErrorElement == '') {
            isFirstErrorElement = 'userName';
        }
        errmsg1 = errmsg1 + '- Valid User Name' + "\n";
    }
    if (document.getElementById('addOpType').checked) {
        password = document.getElementById('password').value;
        confPassword = document.getElementById('confirmedPwd').value;
        if (password == null || password.length == 0) {
            errorFileds[count++] = 'password';
            if (isFirstErrorElement == '') {
                isFirstErrorElement = 'password';
            }
            errmsg1 = errmsg1 + '- Valid Password' + "\n";
        }
        if (confPassword == null || confPassword.length == 0) {
            errorFileds[count++] = 'confirmedPwd';
            if (isFirstErrorElement == '') {
                isFirstErrorElement = 'confirmedPwd';
            }
            errmsg1 = errmsg1 + '- Valid Confirm Password' + "\n";
        }
    }

    if (document.getElementById('addOpType').checked) {
        if (password != confPassword) {
            errorFileds[count++] = 'password';
            if (isFirstErrorElement == '') {
                isFirstErrorElement = 'password';
            }
            errorFileds[count++] = 'confirmedPwd';
            errmsg1 = errmsg1 + '- Passwords should match' + "\n";
        }
        if (password != null && password.length > 0) {
            if (password.length < 6) {
                errorFileds[count++] = 'password';
                if (isFirstErrorElement == '') {
                    isFirstErrorElement = 'password';
                }
                errorFileds[count++] = 'confirmedPwd';
                errmsg1 = errmsg1 + '- Password length must be at least six characters' + "\n";
            }
            if (!checkPwdQuality(password)) {
                errorFileds[count++] = 'password';
                if (isFirstErrorElement == '') {
                    isFirstErrorElement = 'password';
                }
                errorFileds[count++] = 'confirmedPwd';
                errmsg1 = errmsg1 + '- Password must contain at least one alphabet and one numeric' + "\n";
            }
        }
    }

    if (document.getElementById('serviceListTrId') != null) {
        if (!checkServiceSelected()) {
            errmsg1 = errmsg1 + '- At least one service assignment needs to be selected' + "\n";
        }
    }

    if (errmsg1.length > errmsg2.length) {

        var index = 0;
        var totalErrorElement = errorFileds.length;

        for (index = 0; index < totalErrorElement; index++) {
            document.getElementById(errorFileds[index]).style.backgroundColor = errorFieldColor;
        }

        if (isFirstErrorElement != '') {
            document.getElementById(isFirstErrorElement).focus();
        }

        errmsg1 = errmsg1 + " " + "\n";
        alert(errmsg1);
        return false;
    }
}

function checkPwdQuality(pwd) {
    if (!hasAlphabet(pwd) || !hasNumeric(pwd)) {
        return false;
    } else {
        return true;
    }
}

function hasAlphabet(passwrd) {
    var noAlphaCnt = 0;
    for (var i = 0; i < passwrd.length; i++) {
        if (!((passwrd.charAt(i) >= 'A' && passwrd.charAt(i) <= 'Z') || (passwrd.charAt(i) >= 'a') && (passwrd.charAt(i) <= 'z'))) {
            noAlphaCnt++;
        }
    }
    if (noAlphaCnt == passwrd.length) {
        return false;
    } else {
        return true;
    }
}

function hasNumeric(passwrd) {
    var noNumCnt = 0;
    for (var i = 0; i < passwrd.length; i++) {
        if (!(passwrd.charAt(i) >= '0' && passwrd.charAt(i) <= '9')) {
            noNumCnt++;
        }
    }
    if (noNumCnt == passwrd.length) {
        return false;
    } else {
        return true;
    }
}

function isEmailValid(str) {
    if (str == "") return true;
    var indx1 = str.indexOf("@", 0);
    var indx2 = str.indexOf(".", indx1 + 1);
    var indx3 = str.indexOf(" ");
    if (indx1 == -1 || indx2 == -1 || indx3 > -1) {
        return false;
    }
    else if (indx2 + 1 == ' ') {
        return true;
    }
    else if (indx2 - indx1 <= 1) {
        return false;
    }
    else if (indx2 == (str.length - 1)) {
        return false;
    }
    else {
        return true;
    }
}

function checkServiceSelected() {
    var chkBoxes = document.getElementsByName("servicesForUser");
    var howmanyChecked = 0;
    var i = 0;
    for (i = 0; i < chkBoxes.length; i++) {
        if (chkBoxes[i].checked == true) {
            howmanyChecked++;
        }
    }
    if (howmanyChecked == 0) {
        return false;
    } else {
        return true;
    }
}


function onSelectUser() {
    document.getElementById("fetchButton").focus();
}

function handleEnterKey(e) {
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    if (unicode == 13) {
        document.getElementById("buttonSubmit").focus();
    }
}

function clearErrorFields() {
    document.getElementById("departmentId").style.backgroundColor = '';
    document.getElementById("createdUserId").style.backgroundColor = '';
    document.getElementById('fullName').style.backgroundColor = '';
    document.getElementById('emailId').style.backgroundColor = '';
    document.getElementById('userName').style.backgroundColor = '';
    document.getElementById('password').style.backgroundColor = '';
    document.getElementById('confirmedPwd').style.backgroundColor = '';
    document.getElementById('assignedLocationId').style.backgroundColor = '';
    document.getElementById('currentNic').style.backgroundColor = '';

}

function populateUserListForDA() {
    var loggedOnUserType = document.getElementById('loggedOnUserType').value;

    if (loggedOnUserType == daType) {

        populateUserBasedOnDept();

        var opSel = document.getElementById('operation').value;
        if (opSel == 'edit') {
            var selectUserId = document.getElementById('selectedUserId').value;
            var userCmbo = document.getElementById("createdUserId");
            userCmbo.value = selectUserId;
        }

    }
}

function checkExitingUsers() {
    var currentNicNo = $('#currentNic').val().trim();

    $.post('checkUserByCurrentNICNumber.action', {currentNic: currentNicNo},
        function (data) {
            if (data.exitingUser) {
                // $('#buttonSubmit').prop('disabled', true);
                // $('#buttonSubmit').attr('disabled', true);
                $('#buttonSubmit').attr('alt', 'Please change the NIC number before proceed ');

                alert("Entered NIC has an active user account. Please inactivate the current user account and create a new account.");
                return false;
            } else {
                // $('#buttonSubmit').attr('disabled', false);
                alert('Entered NIC does not has an active user account. You can proceed.');
            }
        }
    );

}



