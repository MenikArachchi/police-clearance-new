package lk.icta.commonuser.dept.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.ServiceVO;
import lk.icta.commonuser.framework.vo.UserVO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc

/**
 * Servlet implementation class JsonUserListLoader.
 */
public class JsonUserListLoader extends HttpServlet {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JsonUserListLoader.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do get.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * Do post.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * Do action.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void doAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int associatedDeptId = Integer.parseInt(request
				.getParameter("departmentId"));
		String methodName = request.getParameter("method");

		if (methodName != null
				&& methodName.equalsIgnoreCase("populateService")) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("doAction(HttpServletRequest, HttpServletResponse) -  associatedDeptId="
						+ associatedDeptId + " for populateServiceList");
			}
			populateServiceList(request, associatedDeptId, out, session);
		} else {
			int loggedOnUserType = Integer.parseInt(request
					.getParameter("loggedOnUserType"));
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("doAction(HttpServletRequest, HttpServletResponse) -  associatedDeptId="
						+ associatedDeptId
						+ " : loggedOnUserType="
						+ loggedOnUserType + " for populateUserList");
			}
			populateUserList(request, associatedDeptId, out, loggedOnUserType,
					session);
		}

	}

	/**
	 * Populate user list.
	 * 
	 * @param request
	 *            the request
	 * @param associatedDeptId
	 *            the associated dept id
	 * @param out
	 *            the out
	 * @param loggedOnUserType
	 *            the logged on user type
	 * @param session
	 *            the session
	 */
	public void populateUserList(HttpServletRequest request,
			int associatedDeptId, PrintWriter out, int loggedOnUserType,
			HttpSession session) {
		Map<Integer, UserVO> userList = null;

		List<UserVO> createdUserList = null;
		String jsonText = null;
		Map<Integer, String> users = new LinkedHashMap<Integer, String>();

		try {
			CommonUserBusiness commonUserBusiness = CommonUserBusiness
					.getInstance();
			createdUserList = commonUserBusiness.getCreatedUserList(
					loggedOnUserType, associatedDeptId);
			userList = new LinkedHashMap<Integer, UserVO>();
			for (UserVO user : createdUserList) {
				userList.put(user.getId(), user);

			}
			for (Entry<Integer, UserVO> entry : userList.entrySet()) {
				Integer key = entry.getKey();
				String value = entry.getValue().getFullName();
				users.put(key, value);
			}
		} catch (BusinessException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("JsonUserListLoader # populateUserList error : "
						+ e);
			}

		}

		Gson gson = new Gson();
		jsonText = gson.toJson(users);
		try {
			jsonText = new String(jsonText.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("doAction(HttpServletRequest, HttpServletResponse) - UnsupportedEncodingException e="
						+ e.getMessage());
			}
		}
		out.print(jsonText);
		out.flush();
	}

	/**
	 * Populate service list.
	 * 
	 * @param request
	 *            the request
	 * @param associatedDeptId
	 *            the associated dept id
	 * @param out
	 *            the out
	 * @param session
	 *            the session
	 */
	public void populateServiceList(HttpServletRequest request,
			int associatedDeptId, PrintWriter out, HttpSession session) {
		Map<Integer, ServiceVO> serviceMap = null;
		List<ServiceVO> serviceList = null;
		String jsonText = null;
		Map<Integer, String> services = new HashMap<Integer, String>();

		try {
			CommonUserBusiness commonUserBusiness = CommonUserBusiness
					.getInstance();
			serviceList = commonUserBusiness
					.getServicesByDeptId(associatedDeptId);
			serviceMap = new HashMap<Integer, ServiceVO>();
			for (ServiceVO service : serviceList) {
				serviceMap.put(service.getId(), service);
			}
			for (Entry<Integer, ServiceVO> entry : serviceMap.entrySet()) {
				Integer key = entry.getKey();
				String value = entry.getValue().getName();
				services.put(key, value);
			}
		} catch (BusinessException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("JsonUserListLoader # populateServiceList error : "
						+ e);
			}
		}

		Gson gson = new Gson();
		jsonText = gson.toJson(services);
		try {
			jsonText = new String(jsonText.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("doAction(HttpServletRequest, HttpServletResponse) - UnsupportedEncodingException e="
						+ e.getMessage());
			}
		}
		out.print(jsonText);
		out.flush();
	}

}
