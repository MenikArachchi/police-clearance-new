package lk.icta.commonuser.dept.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import lk.icta.commonuser.framework.constant.CommonUserConstant;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.utility.LanguageBasedPopupLoader;

public class JsonLanguageLoader extends HttpServlet {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JsonLanguageLoader.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new json language loader.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public JsonLanguageLoader() {
		super();
	}

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String key = request.getParameter("key");
		String value = getValueFromLocalePropertiesFile(request);

		Map<String, String> localeMessage = new HashMap<String, String>(1);
		localeMessage.put(key, value);
		
		String jsonText = null;
		try {
			Gson gson = new Gson();
			jsonText = gson.toJson(localeMessage);
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

	private String getValueFromLocalePropertiesFile(HttpServletRequest request) {

		String locale = (String) request.getSession().getAttribute(SessionConstant.LOCALE_VALUE);

		if (CommonUtil.checkBlank(locale)) {
			locale = CommonUserConstant.LOCALE_ENGLISH;
		}

		String key = request.getParameter("key");
		String param = request.getParameter("param");

		String value = "";

		// Key should not be blank to retrieve the value
		if (!CommonUtil.checkBlank(key)) {
			if (CommonUtil.checkBlank(param)) {
				value = LanguageBasedPopupLoader.getLocaleBasedValue(key, locale);
			} else {
				value = LanguageBasedPopupLoader.getLocaleBasedValue(key, locale,
						getParamList(param));
			}
		} else {
			// This is erroneous situation. Key should not be blank
			value = "key is blank";
		}

		// If no value present then, return the key itself
		if (CommonUtil.checkBlank(value)) {
			value = key;
		}

		return value;
	}

	private Object getParamList(String param) {

		Object returnObject = null;
		if (param.contains("#")) {
			List<Object> objList = new ArrayList<Object>();
			String[] strArray = param.split("#");
			for (String str : strArray) {
				objList.add(str);
			}
			
			returnObject = objList;

		} else {
			returnObject = param;
		}
		
		return returnObject;

	}

}