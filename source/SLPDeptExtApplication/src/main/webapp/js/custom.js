function makeReadOnlyTextBoxes(inputBox){
	  $(inputBox).keypress(function() {
		  if($(this).attr('readonly') == true)
		  return false;
	 });
  }


function addvalidationError(inputBox){
	$(inputBox).css('border','1px solid #ff0000');
}

function removevalidationError(inputBox){
	$(inputBox).css('border','1px solid #ccc');
}

function checkForEmptyValue(inputBox,type){
	  if(type=='text'){
		  var boxValue=$.trim($(inputBox).val());
		  if(!(boxValue.length > 0)){
			  addvalidationError(inputBox);
			  return false;
		  }				  
	  }
	  
	  if(type=='number'){
		  var boxValue=parseFloat($.trim($(inputBox).val()));
		  if(isNaN(boxValue) ||boxValue <=0){
			  addvalidationError(inputBox);
			  return false;
		  }				  
	  }
	  
	  if(type=='date'){
		  var boxValue=$.trim($(inputBox).val());			  
		  if(!(boxValue.length > 0)){
			  addvalidationError(inputBox);
			  return false;
		  }			 			  
	  }	 
	  return true;
  }
	  
	
	  
  function makeNumericOnlyTextBox(inputBox){
	  $(inputBox).keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
	  
	  $(inputBox).bind("cut copy paste",function(e) {
          e.preventDefault();
      });
  }