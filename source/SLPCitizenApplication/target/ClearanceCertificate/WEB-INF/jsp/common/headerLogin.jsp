<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css"  rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.css"  rel="stylesheet">
<link href="css/bootstrap.min.css"  rel="stylesheet" media="screen">

<link href="css/bootstrap-responsive.min.css"  rel="stylesheet">
<link href="css/jquery-ui-1.10.4.custom.min.css"  rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.9.1.js" ></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js" ></script>
<script type="text/javascript" src="js/dialog.js" ></script>


<script type="text/javascript">
  	$(document).ready(function(){
  		timeoutMessageDivs();
  	});
  	
  	function timeoutMessageDivs(){
  		setTimeout(function() {
  			hideAllMessages(); 	      
  	    }, 9000);
  	}
  	
  	function hideAllMessages(){
  		$(".alert.alert-error").each(function(index,item){
			$(this).hide(500);
    	});
		$(".alert.alert-success").each(function(index,item){
				$(this).hide(500);
		}); 
  	}
</script>
<div id="es-header" class="es-header">
	<div style="width:auto;text-align: left" class="top_banner">
		<img src="images/banner.png"  alt="Department of Department of Motor Traffic" /> 	
	</div>
	<div style="clear: both;"></div>	
	<div class="mediumtray">			
		<div class="mediatext"><a target="_blank" href="http://www.motortraffic.gov.lk/web/index.php"><span><strong><s:text name="dmt.department.of.motor.traffic" /></strong></span></a></div>
	</div>	
</div>


