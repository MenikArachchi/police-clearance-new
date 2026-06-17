package lk.icta.commonuser.dept.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;

public class CommonAdminServlet extends HttpServlet {


	private static final long serialVersionUID = -7368278404360712843L;
	
	private static final Logger LOGGER = Logger.getLogger(CommonAdminServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null) {
			return;
		}
		if (method.equals("checkSession")) {
			checkSession(request, response);
		} else if (method.equals("populateUsers")) {
			populateUsers(request, response);
		}
	}

	private void checkSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session
				.getAttribute(SessionConstant.USER_DETAILS);

		boolean sessionExpired = false;

		if (user == null) {
			sessionExpired = true;
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml-response>");
			response.setContentType("text/xml");
			buffer.append("<session-check>");
			buffer.append("<is-expired>" + sessionExpired + "</is-expired>");
			buffer.append("</session-check>");

			buffer.append("</xml-response>");
			out.println(buffer);

		} catch (Exception exception) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			out.close();
		}
	}
	
	private void populateUsers(HttpServletRequest request,
			HttpServletResponse response) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entry populateUsers");
		}
		
		int loggedOnUserType = Integer.parseInt(request
				.getParameter("loggedOnUserType"));
		
		int associatedDeptId = Integer.parseInt(request
				.getParameter("departmentId"));
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("associatedDeptId: " + associatedDeptId + ", loggedOnUserType: " + loggedOnUserType);
		}
		
		PrintWriter out = null;

		try {
			
			out = response.getWriter();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml-response>");
			response.setContentType("text/xml");
			
			List<UserVO> createdUserList = CommonUserBusiness.getInstance().getCreatedUserList(
					loggedOnUserType, associatedDeptId);
			
			if (LOGGER.isDebugEnabled()) {
				//LOGGER.debug("createdUserList: " + createdUserList);
				LOGGER.debug("createdUserList: ");
			}
			
			for (UserVO userVO : createdUserList) {
				
				buffer.append("<user>");
				buffer.append("<user-id>" + userVO.getId() + "</user-id>");
				buffer.append("<user-full-name>" + userVO.getFullName() + "</user-full-name>");
				buffer.append("</user>");
			}
			
			buffer.append("</xml-response>");
			
			if (LOGGER.isDebugEnabled()) {
				//LOGGER.debug("buffer: " + buffer);
				LOGGER.debug("buffer: ");
			}
			
			out.println(buffer);
			
		} catch (Exception e) {
			LOGGER.error("Exception in populateUsers in CommonAdminServlet", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			out.close();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit populateUsers");
		}
	}
}
