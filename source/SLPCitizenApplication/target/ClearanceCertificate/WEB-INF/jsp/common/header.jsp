<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/jquery-ui.min.css"  rel="stylesheet">
<link href="css/magnific-popup.css"  rel="stylesheet">

<style id="antiClickjack">body{display:none !important;}</style>
<style type="text/css">
  .ui-autocomplete { height: 200px; overflow-y: scroll; overflow-x: hidden;}
</style>

<script type="text/javascript" src="js/jquery-1.9.1.js" ></script>
<script type="text/javascript" src="js/jquery.tmpl.min.js" ></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js" ></script>
<script type="text/javascript" src="js/custom.js" ></script>
<script type="text/javascript" src="js/bootstrap.js" ></script>

<script type="text/javascript" src="js/jquery.magnific-popup.min.js" ></script>

<script type="text/javascript">
  	$(document).ready(function(){
  		//timeoutMessageDivs();
  		 if (self === top) {
          var antiClickjack = document.getElementById("antiClickjack");
          antiClickjack.parentNode.removeChild(antiClickjack);
      	 } else {
          top.location = self.location;
      	 }
  	});
  	
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
  	
  	function timeoutMessageDivs(){
  		setTimeout(function() {
  			hideAllMessages(); 	      
  	    }, 15000);
  	}
  	
  	function hideAllMessages(){
  		$(".alert.alert-error").each(function(index,item){
			$(this).hide(500);
    	});
		$(".alert.alert-success").each(function(index,item){
				$(this).hide(500);
		}); 
  	}
  	
  	function blockUI(){
		 var docHeight = $(document).height();

		   $("#overlay")
		      .height(docHeight)
		      .css({
		         'opacity' : 0.4,
		         'position': 'absolute',
		         'top': 0,
		         'left': 0,
		         'background-color': 'black',
		         'width': '100%',
		         'z-index': 5000,
		         'text-align':'center',
		         'vertical-align':'middle',
		         'display': 'table'
		      });

	}
	
	function unBlockUI(){
		 $("#overlay").hide();		     
	}
</script>

	
<div id="es-header" class="es-header">
	<div class="top_banner">
		<div style="position: absolute;height: 100px;width:100%;">			
			<table style="position: absolute;display: table-cell;margin: 0px 0px 0px 5px;">
				<tr>
					<td><img src="images/police_logo_small.png"  alt="Sri Lanka Police"  /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><span class="logo">Sri Lanka Police</span></td>
				</tr>
			</table>
		</div>	
		
	</div>
	<div style="clear: both;"></div>
</div>
<div style="clear: both;"></div>	
<input type="hidden" id="maximumFileLimit" value='<s:text name="police.maximum.upload.file.size.limit"/>'>

