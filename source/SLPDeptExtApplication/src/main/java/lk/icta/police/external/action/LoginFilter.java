package lk.icta.police.external.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lk.icta.police.framework.constant.PoliceConstant;

import org.apache.commons.lang3.StringUtils;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);
	        
	        String currentAction="login";
	        
	        if (request instanceof HttpServletRequest) {
	        	
	        	String urI = ((HttpServletRequest)request).getRequestURI().toString();
		    	currentAction = urI.substring((urI.lastIndexOf("/") + 1),urI.length());
	        	String queryString = ((HttpServletRequest)request).getQueryString();
	        	
	        	if(StringUtils.isNotEmpty(queryString)){
	        		currentAction=currentAction.replace(queryString, "");
	        	}
	        	currentAction=StringUtils.trim(currentAction.replaceAll("\\?", ""));
	        	currentAction=StringUtils.trim(currentAction.replace(".action", ""));
	        	currentAction=StringUtils.trim(currentAction.replace(".htm", ""));
	        }
	        	
	        if(StringUtils.isNotEmpty(currentAction)){
	        	if (!PoliceConstant.EXCLUDE_SESSION_CHECK.contains(currentAction)) {
			        if (session == null || session.getAttribute(PoliceConstant.SESSION_USER) == null) {
			            response.sendRedirect(request.getContextPath() + "/login.action"); // No logged-in user found, so redirect to login page.
			        } else {			        	
			        	if (!PoliceConstant.EXCLUDE_AUTHORIZATION_CHECK.contains(currentAction)) {
							List<Integer> assignedServiceIdList = (List<Integer>) session.getAttribute(PoliceConstant.ASSIGNED_SERVICE_ID_LIST);
							
							List<Integer> serviceIdList=LoginInterceptor.getServiceIdFromActionName(currentAction);							
							
							boolean isAuthorized=false;
							
							if(!(serviceIdList==null || serviceIdList.isEmpty())){
								for (Integer serviceId : serviceIdList) {
									if(!(serviceId==0)){
										if(assignedServiceIdList.contains(serviceId)){
											isAuthorized=true;
										}
									}							
								}
							}
							
							System.out.println("isAuthorized :  " + isAuthorized);
							
							if (isAuthorized) {
								chain.doFilter(req, res); // Logged-in user found, so just continue request.
							}else{
								response.sendRedirect(request.getContextPath() + "/home.action"); // user is not authorized, so redirect to home page.
							}
						}else{
							chain.doFilter(req, res);
						}		        
			        }
		        }else{
		        	 chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        }else{
	        	response.sendRedirect(request.getContextPath() + "/login.action"); // No logged-in user found, so redirect to login page.
	        }
	        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
