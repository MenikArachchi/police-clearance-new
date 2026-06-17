<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/common/css.jspf"  %>
<%@ include file="/WEB-INF/jsp/common/javascript.jspf"  %>
<s:i18n name="lk.icta.resources.global">

    <html>
        <head>
            <title><s:text name="global.oauth.authentication"></s:text></title>

            <!-- Basic Page Needs  ================================================== -->
            <meta charset="utf-8">
            <meta name="description" content="document description">
            <meta name="keywords" content="keyword1,keyword2,keyword3">

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

        <link href="css/oauth/jquery-ui.css" rel="stylesheet" media="screen" />

        <!-- shortcut icon -->
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="#">
        
        
        
       <script type="text/javascript" src="js/oauth/jquery-2.0.3.min.js" ></script>
		<script type="text/javascript" src="js/oauth/jquery-ui.min.js" ></script>
		<script type="text/javascript" src="js/oauth/common.js" ></script>
        <script type="text/javascript">

            var error = false;

            $(document).ready(function(){
                $.ajaxSetup({cache: false, async: false});
            });

            function setSelectedProvider(provider) {
                $.getJSON("setOAuthProvider.action",
                {
                    selectedProvider: provider,
                },		
                checkStatus
            	);
                return false;
            }

            function checkStatus(json) {
                if (json.status == "success") {
                    error = true;
                } else {
                    error = false;
                }
            }

            function readyLink(title) {
                setSelectedProvider(title);
	
                if (error == false) {
                    return true;
                } else {
                    return false;
                }
            }

        </script>
    </head>
    <body class="theme1" >
        <!--  start -->
        <div id="es-container">
            <s:form method="post" windowState="maximized">
			<div id="es-content">
                    <div class="es-title clear-float">
                        <div class="tittle-name">
                            <h2>
                                <s:text name="oauth.viewlogin" />
                            </h2>
                        </div>
                        <div class="es-help">
                       			<jsp:include page="/WEB-INF/jsp/view/logOut.jsp" />
                        </div>
                    </div>

                    <div class="clear-float">
                        <div class="es-errormsg" ><s:fielderror cssErrorClass="errorMessage" /></div>
                        <div class="es-errormsg" ><s:actionerror cssErrorClass="errorMessage"/></div>
                        <div class="es-errormsg" ><s:actionmessage cssErrorClass="errorMessage"/></div>
                    </div>
                    <div class="content-wraper">
                    	<p><s:text name="oauth.info.message" /></p>
                        <fieldset class="login-fieldset" >
                            <legend>
                                <h3 ><s:text name="oauth.viewlogin.title" /></h3></legend>
                            <div class="text-center">
                                <s:iterator value="%{#session.PROVIDERS}">
                                    <a id="queryLink" href='<s:property value="value"/>' onclick="readyLink('<s:property value="key" />')" >
                                        <img  class="login-images" src='images/oauth/<s:property value="key" />.jpg' alt='<s:property value="key" />' />
                                    </a>
                                </s:iterator>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </s:form>
        </div>
    </body>

</html>
</s:i18n>
