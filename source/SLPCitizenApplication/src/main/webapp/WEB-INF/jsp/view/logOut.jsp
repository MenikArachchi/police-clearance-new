<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='%{#session.EMAIL != null && !"".equals(#session.EMAIL)}'>
	<strong><s:text name="erl.oauth.loggedIn" /> </strong><s:property value="%{#session.NAME}" /> | <strong>
	<s:a href="logoutOauth.action" id="logoutUrlAnchor"></s:a>
	<a href="" id="logOutLink" title="Logout" onclick="logoutProvider();" title="Logout"><s:text name="erl.oauth.logout" /></a></strong>
</s:if>