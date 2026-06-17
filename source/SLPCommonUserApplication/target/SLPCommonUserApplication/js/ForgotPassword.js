var errorFieldColor = "#FFFFCC";

function check_ForgotPwd()
{
	clearErrorFields();
	
	var userName = document.getElementById('userName').value;
	var emailId = document.getElementById('emailId').value;
	var verficationText = document.getElementById('uword').value;
	
	var isErrorOccured = false;
	var isValidEmail = false;
	var errorFileds = new Array();
	var count = 0;
	var errmsg = ""; 
	var isFirstErrorElement = '';
	
	if (userName == null || userName.length == 0 ) {
//		errmsg = getLocaleMessage('forgotPassword.userNameNotProvided') + "\n";
		errmsg = errmsg + 'You have not entered all required data. Please check again' + "\n";
		isFirstErrorElement = 'userName';
		errorFileds[count++] = 'userName';
		isErrorOccured = true;
	} 
	
	if (emailId == null || emailId.length == 0 ) {
		// Same error message not to be repeated
		if (!isErrorOccured) {
//			errmsg = getLocaleMessage('forgotPassword.emailIdNotProvided') + "\n"; 
			errmsg = errmsg + 'You have not entered all required data. Please check again' + "\n";
		}
	  
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'emailId';
		}
		errorFileds[count++] = 'emailId';
		isErrorOccured = true;
	} 
	
	if(!isEmailValid(emailId)){
//		errmsg = getLocaleMessage('forgotPassword.invalidEmail') + "\n"; 
		errmsg = errmsg + 'Invalid email format' + "\n"; 
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'emailId';
		}
		errorFileds[count++] = 'emailId';
		isValidEmail = true;
	}
	
	if (verficationText == null || verficationText.length == 0) {
		if (!isErrorOccured) {
			errmsg = errmsg + 'You have not entered all required data. Please check again' + "\n";
		}
	  
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'uword';
		}
		errorFileds[count++] = 'uword';
		isErrorOccured = true;
	}
	
	if (isErrorOccured || isValidEmail) {
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

function isEmailValid(str){
   if(str=="") return true;
   var indx1 = str.indexOf("@", 0);
   var indx2 = str.indexOf(".", indx1+1);
   var indx3 = str.indexOf(" ");
   if(indx1==-1||indx2==-1 || indx3>-1){
		return false;
   }
   else if(indx2+1==' '){
    	return true;
   }
   else if(indx2-indx1<=1) {
		return false;
   }
   else if(indx2 == (str.length-1)) {
		return false;
   }
   else {
	   return true;
   }
}

function resetText(){
	var verficationText = document.getElementById('uword').value;
	if(verficationText != null && verficationText.length > 0){
		document.getElementById('uword').value = '';
	}
}

function ResetForgtPwd(){
	$("#errorPart").empty();
	document.getElementById('userName').value = '';
	document.getElementById('emailId').value = '';
	resetText();
	clearErrorFields();
	document.getElementById('userName').focus();
}

function clearErrorFields() 
{
	document.getElementById('userName').style.backgroundColor='';
	document.getElementById('emailId').style.backgroundColor='';
	document.getElementById('uword').style.backgroundColor='';
}
