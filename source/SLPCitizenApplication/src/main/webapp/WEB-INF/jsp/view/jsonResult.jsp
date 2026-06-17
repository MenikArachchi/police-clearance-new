<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='%{emailResult == null || "".equals(emailResult)}'>
{"result":"fail","loginAttemp":"<s:property value='%{loginAttemp}' />"}
</s:if>
<s:else>
{"result":"done","email":"<s:property value='%{emailResult}' />","name":"<s:property value='%{nameResult}' />"}
</s:else>