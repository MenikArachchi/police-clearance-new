<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url id="changeLocale" action="changeLocale" />

<html>
<head>
<!-- <script type="text/javaScript" src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js" ></script>  -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">

<title>Insert title here</title>

<script type="text/javascript">
function loadEnglish() {
	//alert("header English");
	$("#header_div").empty();
	
	var urlStr = "<s:property value='changeLocale'/>?request_locale=en";

	$.ajax({
		url: urlStr,
		async:false,
		success : function(responseText) {
			$("#header_div").html(responseText);
			}
	});

	window.location.reload();
}

function loadSinhala() {
	//alert("header Sinhala");
	$("#header_div").empty();
	
	var urlStr = "<s:property value='changeLocale'/>?request_locale=si_LK";

	$.ajax({
		url: urlStr,
		async:false,
		success : function(responseText) {
			$("#header_div").html(responseText);
			}
	});
	
	window.location.reload();
}

function loadTamil() {
	//alert("header Tamil");
	$("#header_div").empty();
	
	var urlStr = "<s:property value='changeLocale'/>?request_locale=ta_IN";

	$.ajax({
		url: urlStr,
		async:false,
		success : function(responseText) {
			$("#header_div").html(responseText);
			}
	});
	
	window.location.reload();
}
</script>


</head>
<body>
	<div id="header"></div>
	<div id="header1">
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>				
					<td class="btn_base_menu" width="8%" align="center">
						<!--  
						<a onclick="return loadEnglish();" class="nav_link_yellow">
						<s:text name="commonuser.lang.english" />
						</a>
						-->
					</td>
					<td class="btn_base_menu" width="8%" align="center">
						<!-- 
						<a onclick="return loadSinhala();" class="nav_link_yellow">
						<s:text name="commonuser.lang.sinhala" />
						</a>
						-->
					</td>
					<td class="btn_base_menu" width="8%" align="center">
						<!-- 
						<a onclick="return loadTamil();" class="nav_link_yellow">
						<s:text name="commonuser.lang.tamil" />
						</a>
						-->
					</td>						
					<td class="btn_base_menu" width="16%">&nbsp;</td>					
					<td class="btn_base_menu" width="26%"><span style="color: #ffe851;padding-left: 10px;"><s:text name="commonuser.welcome" />&nbsp;&nbsp;<s:property value="dispFullName" /></span></td>					
					<td class="btn_base_menu" width="15%" align="center"><a href="<s:url action='ChangePwd'/>" class="nav_link_yellow"><s:text name="commonuser.changePassword" /></a></td>
		      		<td class="btn_base_menu" width="9%" align="center"><a href="<s:url action='Logout'/>" class="nav_link_yellow"><s:text name="commonuser.logout" /></a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>