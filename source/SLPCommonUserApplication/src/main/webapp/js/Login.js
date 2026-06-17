var errorFieldColor = "#FFFFCC";

function check_Login()
{
	clearErrorFields();
	
	var userName = document.getElementById('userName').value;
	var password = document.getElementById('password').value;
	
	var isErrorOccured = false;
	var errorFileds = new Array();
	var count = 0;
	var errmsg = ""; 
	var isFirstErrorElement = '';
	
	if (userName == null || userName.length == 0 ) {
		errmsg = errmsg + 'You have not entered all required data. Please check again' + "\n"; 
		isFirstErrorElement = 'userName';
		errorFileds[count++] = 'userName';
		isErrorOccured = true;
	}
	
	if (password == null || password.length == 0 ) {
		// Same error message not to be repeated
		if (!isErrorOccured) {
			errmsg = errmsg + 'You have not entered all required data. Please check again' + "\n"; 
		}
	  
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'password';
		}
		errorFileds[count++] = 'password';
		isErrorOccured = true;
	}
	
	if (isErrorOccured) {
		var index = 0;
		var totalErrorElement = errorFileds.length;
		
		for (index = 0; index < totalErrorElement; index++) {
			document.getElementById(errorFileds[index]).style.backgroundColor = errorFieldColor;
		}
		
		if (isFirstErrorElement != '') {
			document.getElementById(isFirstErrorElement).focus();
		}
		
		// To avoid last message truncating in chrome
		errmsg = errmsg + " " + "\n";
		alert(errmsg);
		
		return false;
	}
	
	return true;
}

function ResetLogin(){
	$("#errorPart").empty();
	document.getElementById('userName').value = '';
	document.getElementById('password').value = '';
	clearErrorFields();
	document.getElementById('userName').focus();
}

function clearErrorFields() 
{
	document.getElementById('userName').style.backgroundColor='';
	document.getElementById('password').style.backgroundColor='';
}