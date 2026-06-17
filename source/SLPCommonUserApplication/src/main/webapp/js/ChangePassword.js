var errorFieldColor = "#FFFFCC";

function check_ChangePwd()
{
	clearErrorFields();
	
//	var errmsg1 = getLocaleMessage('common.Mandatory') + "\n"; 
//	var errmsg2 = getLocaleMessage('common.Mandatory') + "\n";
	
	var errmsg1 = 'Following are mandatory :' + "\n"; 
	var errmsg2 = 'Following are mandatory :' + "\n";
	
	var currPassword = document.getElementById('currPassword').value;
	var newPassword = document.getElementById('newPassword').value;
	var confirmNewPassword = document.getElementById('confirmPassword').value;
	
	var errorFileds = new Array();
	var count = 0;
	
	var isFirstErrorElement = '';
	
	if (currPassword == null || currPassword.length == 0 ) {
		errorFileds[count++] = 'currPassword';
		isFirstErrorElement = 'currPassword';
//		errmsg1 = errmsg1 + getLocaleMessage('changePassword.validCurrentPassword') + "\n";
		errmsg1 = errmsg1 + '- Valid Current Password' + "\n";
	}
	if (newPassword == null || newPassword.length == 0 ) {
		errorFileds[count++] = 'newPassword';
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'newPassword';
		}
//		errmsg1 = errmsg1 + getLocaleMessage('changePassword.validNewPassword') + "\n";
		errmsg1 = errmsg1 + '- Valid New Password' + "\n";
	}
	if (confirmNewPassword == null || confirmNewPassword.length == 0 ) {
		errorFileds[count++] = 'confirmPassword';
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'confirmPassword';
		}
//		errmsg1 = errmsg1 + getLocaleMessage('changePassword.validConfirmNewPassword') + "\n";
		errmsg1 = errmsg1 + '- Valid Confirm New Password' + "\n";
	}			
	if(newPassword != confirmNewPassword){
		errorFileds[count++] = 'newPassword';
		errorFileds[count++] = 'confirmPassword';
		if (isFirstErrorElement == '') {
			isFirstErrorElement = 'newPassword';
		}
//		errmsg1 = errmsg1 + getLocaleMessage('changePassword.passwordNotMatching') + "\n"; 
		errmsg1 = errmsg1 + '- New Passwords should match' + "\n"; 
	}			
	if (newPassword != null && newPassword.length > 0 ) {
		if(currPassword == newPassword){
			errorFileds[count++] = 'currPassword';
			errorFileds[count++] = 'newPassword';
			if (isFirstErrorElement == '') {
				isFirstErrorElement = 'currPassword';
			}
//			errmsg1 = errmsg1 + getLocaleMessage('changePassword.currentNewAreSame') + "\n";  
			errmsg1 = errmsg1 + '- Current and New Password should not match' + "\n";  
		}
		if(newPassword.length <6){
			errorFileds[count++] = 'newPassword';
			errorFileds[count++] = 'confirmPassword';
			if (isFirstErrorElement == '') {
				isFirstErrorElement = 'newPassword';
			}
			
//			errmsg1 = errmsg1 + getLocaleMessage('changePassword.passwordLenghNotEnough') + "\n";   
			errmsg1 = errmsg1 + '- Password length must be at least six characters' + "\n";   
		}
		if(!checkPwdQuality(newPassword)){
			errorFileds[count++] = 'newPassword';
			errorFileds[count++] = 'confirmPassword';
			if (isFirstErrorElement == '') {
				isFirstErrorElement = 'newPassword';
			}
			
//			errmsg1 = errmsg1 + getLocaleMessage('changePassword.passwordNotStrongEnough') + "\n"; 
			errmsg1 = errmsg1 + '- Password must contain at least one alphabet and one numeric' + "\n"; 
		}
	}
	if(errmsg1.length>errmsg2.length){
		
		var index = 0;
		var totalErrorElement = errorFileds.length;
		
		for (index = 0; index < totalErrorElement; index++) {
			document.getElementById(errorFileds[index]).style.backgroundColor = errorFieldColor;
		}
		
		if (isFirstErrorElement != '') {
			document.getElementById(isFirstErrorElement).focus();
		}
		
	   // To avoid last message truncating in chrome
	   errmsg1 = errmsg1 + " " + "\n";
       alert(errmsg1);
       return false;
    } else {
    	return true;
    }
}
function checkPwdQuality(pwd){
	if(!hasAlphabet(pwd) || !hasNumeric(pwd)){
		return false;
	}else{
		return true;
	}
}

function hasAlphabet(passwrd){
	var noAlphaCnt = 0;
	for(var i=0;i<passwrd.length;i++)
    {
        if(!((passwrd.charAt(i)>='A' && passwrd.charAt(i)<='Z')||(passwrd.charAt(i)>='a')&&(passwrd.charAt(i)<='z')))
        {		
     	  noAlphaCnt++;
        }
    }
    if(noAlphaCnt==passwrd.length){
		return false;
	}else{
		return true;
	}
}

function hasNumeric(passwrd){
	var noNumCnt = 0;
	for(var i=0;i<passwrd.length;i++)
    {
        if(!(passwrd.charAt(i)>='0' && passwrd.charAt(i)<='9'))
        {		
        	noNumCnt++;
        }
    }
    if(noNumCnt==passwrd.length){
		return false;
	}else{
		return true;
	}
}
function ResetChgPwd(){
	$("#errorPart").empty();
	document.getElementById('currPassword').value = '';
	document.getElementById('newPassword').value = '';
	document.getElementById('confirmPassword').value = '';
	clearErrorFields();
	document.getElementById('currPassword').focus();
}

function clearErrorFields() 
{
	document.getElementById('currPassword').style.backgroundColor='';
	document.getElementById('newPassword').style.backgroundColor='';
	document.getElementById('confirmPassword').style.backgroundColor='';
}
