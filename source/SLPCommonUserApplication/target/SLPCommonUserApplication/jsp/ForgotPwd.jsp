<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
        <title>Forgot Password</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"> 
		<script src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"
		type="text/javascript"></script>
        <script type="text/javascript">
         window.history.forward();
         window.onpageshow = function (evt) {
        	if (evt.persisted) noBack();
       	 };
         function noBack(){ window.history.forward(); }
     
   

		</script>
        <script type="text/javaScript" src="<%=request.getContextPath()%>/js/ForgotPassword.js" ></script>
        
        <script type="text/javascript">
        
        	function getLocaleMessage(messageKey, param) {
        	
        		var message = '';
        	
        		$.ajax({
        			url : '<%= request.getContextPath()%>/jsonlangloader.data?key='+ messageKey+'&param='+param,
        			cache : false,
        			async: false,
        			dataType : "json",
        			success : function(data) {
        				$.each(data, function(key, value) {
        					message = value;
        				});
        			}
        		});
        	
        		return message;
        	}
        </script>
        
    </head>
       <s:url id="RefreshCaptcha" action="RefreshCaptcha" />
     <script type="text/javascript">
   
	function refreashCaptcha(){
	document.getElementById('capcha_id').src = '<%=request.getContextPath()%>/Captcha.jpg?' + Math.random(); 
	resetText();
	
  }
    </script>
    
 
    <body onload="noBack();resetText()" onunload="" class="oneColElsCtrHdr" >     
     <div id="container">
     	<div id="header_div"><jsp:include page="loginHeader.jsp"></jsp:include></div>
     	
       <div id="mainContent">
        <s:form action="ForgotPwd">	        
	        	<table border="0" width="100%">
	        		<tr>
		            	<td colspan="2" align="center">
		            		&nbsp;
		            	</td>
		            </tr>
		        	<tr>
		             <td colspan="2" align="center" valign="bottom">
		              <s:if test="hasActionErrors()">
						   <div id="errorPart" class="errorDiv">
						      <s:actionerror/>
						   </div>
					  </s:if>&nbsp;
		             </td>              
		            </tr>
		            <tr>
		            	<td colspan="2" align="center" class="tdLabel">
		            		<strong><s:text name="commonuser.explanatoryText" /></strong>
		            	</td>
		            </tr>	
					 <tr>
		            	<td colspan="2" align="center">
		            		&nbsp;
		            	</td>
		            </tr>
		        	<tr>
		        		<td width="40%" align="right" class="tdLabel"><s:text name="commonuser.userName" /><font color='red'>*</font></td>
		        		<td width="60%" align="left" style="padding-left:25px"><s:textfield cssClass="text_box" name="userVo.userName" id="userName" size="40" maxLength="100" /></td>
		        	</tr>
		        	<tr>
		        		<td width="40%" align="right" class="tdLabel"><s:text name="commonuser.email" /><font color='red'>*</font></td>
		        		<td width="60%" align="left" style="padding-left:25px"><s:textfield cssClass="text_box" name="userVo.emailId" id="emailId" size="40" maxLength="100" /></td>
		        	</tr>
		        	
		        	<tr> 
						<td width="40%" align="right" class="tdLabel"><s:text name="commonuser.textVerify" /><font color='red'>*</font></td>
						<td width="60%" align="left" style="padding-left:23px"><table>
			        			<tr>
			        				<td><s:textfield cssClass="text_box" name="uword" id="uword" size="40" maxLength="100" /></td>
			        				<td><img src="<%=request.getContextPath()%>/Captcha.jpg" border="1" id="capcha_id" height="25" width="150"/></td>
			        				<td><img style="cursor: hand" src="<%=request.getContextPath()%>/images/refresh.jpg" border="0" height="25" width="25" title="Refresh" onclick="refreashCaptcha()" /></td>
			        			</tr>
			        			
			        		</table>	
		        	</td>
		        		
		        	</tr>	
		        	
		        	<!--  
		        	<tr>
		        		<td width="40%" align="right" >&nbsp;</td>
		        		<td width="60%" align="left"  valign="bottom" style="padding-left:25px; font-family: Geneva, Arial, Helvetica, sans-serif, Decker;font-size: 10px;color: #061a2e;"><i><s:text name="commonuser.textVerifyMessage" /></i></td>
		        		<td width="60%" align="left" style="padding-left:25px">&nbsp;</td>
		        	</tr>
		        	-->	   
		        	     	
		        	<tr>
		        		<td colspan="2" align="center">&nbsp;</td>
		        	</tr>
		        	<tr>
		        		<td colspan="2" align="center"><s:submit cssClass="submit_btn_base" action="ProcessForgotPwd" key="commonuser.resetAndSendNewPassword" onclick="return check_ForgotPwd()"/>&nbsp;&nbsp;<input type="button" class="submit_btn_base" value="<s:text name="commonuser.reset" />" onclick="ResetForgtPwd()" />
		        		&nbsp;&nbsp;<s:submit cssClass="submit_btn_base" action="NewLogin" key="commonuser.backToLogin" /></td>
		        	</tr>
		        	<tr>
		        		<td colspan="2" align="center">&nbsp;</td>
		        	</tr>
		        </table>        
        </s:form>
     </div>
     <jsp:include page="footer.jsp"></jsp:include>
      </div>  
    </body>
</html>
