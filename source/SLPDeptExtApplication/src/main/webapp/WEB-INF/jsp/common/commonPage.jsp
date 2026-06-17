<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
<!--
.ui-icon-close{background-position:-80px -128px !important;}
.ui-icon-closethick{background-position:-96px -128px !important;}

.es-help {
    color: #2C3E50;
    font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 13px;
    line-height: 1.42857;
    font-weight: bold;
}
-->
</style>		
			         
			        
			        <div class="es-eservice-title clear-float">
						<div class="tittle-name">
									<c:out value="${param.title}"></c:out>
						</div>
						<div style="float:right">						
							<table>
								<tbody>
									<tr>
										<td></td>	
										<c:choose>
      										<c:when test="${empty param.homePage}">
      											<td>												
													<div class="es-help">													
														<strong>													
															<a title="Home" href="home.action">
																Home 
															</a>
															<c:if test="${! empty  sessionScope.userFullName}">	
																&nbsp;|&nbsp;
																<c:out value="${sessionScope.userFullName}"/>
																&nbsp;|&nbsp;
																<a title="Change Password" href="changePassword">
																	Change Password 
																</a>
																&nbsp;|&nbsp;
																<a title="Logout" href="logout">
																	Logout
																</a>
															</c:if>
														</strong>
													</div>
												</td>
      										</c:when>
											<c:when test="${param.homePage=='HOME'}">
												<td>												
													<div class="es-help">	
														<c:if test="${! empty  sessionScope.userFullName}">												
															<strong>
																<c:out value="${sessionScope.userFullName}"/>
																&nbsp;|&nbsp;
																<a title="Change Password" href="changePassword">
																	Change Password 
																</a>
																&nbsp;|&nbsp;
																<a title="Logout" href="logout">
																	Logout
																</a>
															</strong>
														</c:if>
													</div>
												</td>
											</c:when>
											<c:when test="${param.homePage=='RESET'}">
												<td>												
													<div class="es-help">													
														<strong>
															<a title="Back" href="login">
																Back to Login 
															</a>
														</strong>
													</div>
												</td>
											</c:when>
									      	<c:otherwise>
									      
									      	</c:otherwise>
										</c:choose>									
										<td>											
<!-- 											<div class="tittle-help-icon"> -->
<!-- 												<a title="Help" href="javascript:void(0);"> <img src="images/help.png" class="tittle-help-icon-img" alt=""></a> -->
<!-- 											</div> -->
										</td>
									</tr>
								</tbody>
							</table>						
						</div>
					</div>
			        
		   			
			
			<div style="clear:both;"></div>
			<div id="messagesDiv" style="margin:2px 0px;">
				<s:if test="actionErrors != null && actionErrors.size > 0">
					<div class="alert alert-error">
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="fieldErrors != null && fieldErrors.size > 0">
					<div class="alert alert-error">
						<s:fielderror />
					</div>
				</s:if>
				<s:if test="actionMessages != null && actionMessages.size > 0">
					<div  class="alert alert-success">
						<s:actionmessage />
					</div>
				</s:if>
				<s:if test="customMessage != null && customMessage != ''">
					<div  class="alert alert-success">
						<s:property value="customMessage" />
					</div>
				</s:if>
				<s:if test="customErrorMessage != null && customErrorMessage != ''">
					<div class="alert alert-error">
						<s:property value="customErrorMessage" />
					</div>
				</s:if>
				
			</div>		
			
				
			<div style="clear: both;"></div>
			


