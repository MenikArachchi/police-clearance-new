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
        <script type="text/javascript">
         window.history.forward();
         window.onpageshow = function (evt) {
        	if (evt.persisted) noBack();
       	 };
         function noBack(){ window.history.forward(); }
        </script>
    </head>
    <body onload="noBack();" onunload="" class="oneColElsCtrHdr">
      <div id="container">
      	<div id="header_div"><jsp:include page="loginHeader.jsp"></jsp:include></div>
      <%--div id="header">&nbsp;</div>
	  <div id="header1">
	   		<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
			  <tbody><tr>
			  <td class="btn_base_menu" width="100%">&nbsp;</td>
			  </tr>
			  </tbody>
			</table>
	   </div --%>	
       <div id="mainContent">
       <s:form action="ForgotPwd"> 
        <table border="0" width="100%">
        	<tr>
        		<td colspan="2" align="center">&nbsp;</td>
        	</tr>
        	<tr>
             <td colspan="2" align="center" valign="bottom">
              <s:if test="hasActionMessages()">
				   <div class="messageDiv">
				      <s:actionmessage/>
				   </div>
			  </s:if>&nbsp;
             </td>              
            </tr>		        	
        	<tr>
        		<td colspan="2" align="center"><s:submit cssClass="submit_btn_base" action="NewLogin" key="commonuser.backToLogin" /></td>
        	</tr>
        	<tr>
        		<td colspan="2" align="center">&nbsp;</td>
        	</tr>
        </table>
      </s:form>
     </div>
     <jsp:include page="footer.jsp"></jsp:include>
     <%--div id="footer">
			<table class="tableclass" border="0" width="100%">
			    <tbody><tr>
			      <td><span class="style2"><s:text name="commonuser.footer.copyright" /><label id="global_footermsg1"><s:text name="commonuser.footer.copyrightMsg" /></label></span></td>
			      <td><div align="right"><span class="style2"><label id="global_footermsg2"><s:text name="commonuser.footer.viewSuggestion" /></label></span></div></td>
			    </tr>
			  </tbody></table>
	 </div --%>
     </div> 
    </body>
</html>
