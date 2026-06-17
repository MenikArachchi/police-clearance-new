<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:i18n name="lk.icta.resources.global">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><s:text name="global.oauth.authentication" /></title>

            <!-- Basic Page Needs  ================================================== -->
            <meta charset="utf-8">
            <meta name="description" content="document description">
            <meta name="keywords" content="keyword1,keyword2,keyword3">
            <!--[if IE 7 ]><meta http-equiv="X-UA-Compatible" value="IE=7"> <![endif]-->
            <!--[if IE 8 ]><meta http-equiv="X-UA-Compatible" value="IE=8"> <![endif]-->
            <!--[if IE 9 ]><meta http-equiv="X-UA-Compatible" value="IE=9"> <![endif]-->
            <!-- Mobile Specific Metas ============================================== -->
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
            <!-- CSS  ================================================== -->
            <!-- noscript style -->
        <noscript>
            <link rel="stylesheet" href="css/oauth/noscript.css">
        </noscript>
        <!-- end noscript style -->
        <link href="css/oauth/reset.css" rel="stylesheet" />
        <link href="css/oauth/erl_styles.css" rel="stylesheet" media="screen" />
        <link href="css/oauth/desktop.css" rel="stylesheet" />
        <link href="css/oauth/tablet.css" media="screen and (max-width: 960px)" rel="stylesheet" />
        <link href="css/oauth/mobile.css" media="screen and (max-width: 649px)" rel="stylesheet" />
        <link href="css/oauth/framework.css" rel="stylesheet" media="screen" />
        <link href="css/oauth/iframe.theme.css" rel="stylesheet" media="screen" />

        <!--[if IE 7 ]><link rel="stylesheet" href="assets/css/ie7.css"> <![endif]-->
        <!--[if IE 8 ]><link rel="stylesheet" href="assets/css/ie8.css"> <![endif]-->
        <!--[if IE 9 ]><link rel="stylesheet" href="assets/css/ie9.css"> <![endif]-->
        <!-- shortcut icon -->
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="#">
        
         <%-- <script src="${pageContext.request.contextPath}/js/oauth/jquery-2.0.3.min.js" type="text/javascript"></script> --%>
   <%--      <script src="${pageContext.request.contextPath}/js/oauth/jquery-ui.min.js" type="text/javascript"></script>  --%>
        <%-- <script src="${pageContext.request.contextPath}/jq-dialog/dialog.js" type="text/javascript"></script> --%>     
       	<%-- <script src="${pageContext.request.contextPath}/js/oauth/common.js" type="text/javascript"></script>  --%>
        
   <script type="text/javascript" src="js/oauth/jquery-2.0.3.min.js" ></script>
<script type="text/javascript" src="js/oauth/jquery-ui.min.js" ></script>
<script type="text/javascript" src="js/oauth/common.js" ></script>
        

        <script type="text/javascript">
			
             <s:if test="hasActionMessages()"> 
                function closeWindow() {
                	$.get("updateLoginFailure.action",{loginAttemp:'FAILED'},function(data) {
                		 window.close();
                	});                   
                }
            </s:if>
            <s:else>
                $(document).ready(function(){
                	window.close();               		                   
                });
            </s:else>

        </script>
    </head>
    <body  class="theme1">
        <div id="es-container">	

            <div id="es-content">
                <div class="clear-float">
                    <div class="es-errormsg" >
                        <s:if test="hasActionMessages()">
                            <div class="errorMessage">
                                <s:actionmessage cssErrorClass="errorMessage"/>
                            </div>
                        </s:if>
                    </div> 
                </div>
                <div class="content-wraper">
                    <div class="es-title clear-float">
                    	<div class="tittle-name">
                            <h2>
                                <s:text name="oauth.result.title" /> 
                            </h2>
                        </div> 
                    </div>  
                    <h3><s:text name="oauth.result" /></h3>
                    <p><s:text name="oauth.result.info" /></p>
                    <div class="button-warapper"> 
                        <form name="closeThis">
                            <input type="button" class="es-button" onclick="closeWindow();" value='Continue' />
                        </form>
                    </div>
                </div>
                <div class="clear-both"> </div>
            </div>
        </div>
    </body>
</html>
</s:i18n>
