<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<table border="0" width="45%">

	<s:if test="serviceList!=null &&!serviceList.isEmpty()">
		<tr>
			<td width="45%" align="left"
				style="color: #0000FF; font-family: Geneva, Arial, Helvetica, sans-serif, Decker; font-size: 11px; font-weight: bold; padding-right: 35px;"><s:text
				name="commonuser.assignService" /></td>
			<td width="55%" align="left" style="padding-left: 4px">&nbsp;</td>
		</tr>

		<tr id="serviceListTrId">
			<td colspan="2" align="center">
			<table id="serviceListTableId" border="0" width="100%"
				style="border-left: solid 1px #d0d0d0; border-right: solid 1px #d0d0d0; border-top: solid 1px #d0d0d0; border-bottom: solid 1px #d0d0d0;">
				<s:iterator value="subDeptServiceMap.entrySet()"
					status="subDeptStatus" var="subDeptIds">
					<tr>
						<td align="center" colspan="2"
							style="color: #0000FF; font-family: Geneva, Arial, Helvetica, sans-serif, Decker; font-size: 11px; font-weight: bold;"><s:property
							value="value.subDeptName" />
						</td>
					</tr>
					<s:if test="%{isDepAdmin}">
						<s:checkboxlist id="listOfServices" list="value.deptServices"
							name="servicesForUser" theme="vertical-checkbox" listKey="id"
							listValue="name" onkeydown="handleEnterKey(event)" />
					</s:if>

					<s:if test="%{isSupUser}">
						<s:checkboxlist id="listOfServices" list="value.deptServices"
							name="servicesForUser" theme="vertical-checkbox" listKey="id"
							listValue="name" disabled="true" />
					</s:if>
				</s:iterator>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center">&nbsp;</td>
		</tr>

	</s:if>

	<s:else>
		<tr>
			<td colspan="4" align="center">&nbsp;</td>
		</tr>
	</s:else>

</table>