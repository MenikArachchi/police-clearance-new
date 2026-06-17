package lk.icta.police.web.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class XssInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181755734186500443L;

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		final ActionContext context = invocation.getInvocationContext();
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = (Map<String, String[]>) context
				.get(ActionContext.PARAMETERS);

		Map<String, String[]> cleanedMap = new HashMap<String, String[]>();

//		for (Iterator<Entry<String, String[]>> it = parameters.entrySet().iterator(); it.hasNext();) {
//			Map.Entry<String, String[]> es =  it.next();
//			if (!es.getKey().contains(":")) {// drop action:
//				String[] cleansedValue = cleanseValue(es.getValue());
//				cleanedMap.put(es.getKey(), cleansedValue);
//				//System.out.println("key=>" + es.getKey() + " cleansed value=>"+ cleansedValue[0]);
//			} else {
//				cleanedMap.put(es.getKey(), es.getValue());
//			}
//		}
		
		for (Entry<String, String[]> item : parameters.entrySet()) {
			String[] newArray=new String[item.getValue().length];
			for (int i = 0; i < item.getValue().length; i++) {
//				System.out.println("CLEANED VAL BEFORE :" + item.getValue()[i]);
				newArray[i]=StringEscapeUtils.escapeHtml(item.getValue()[i]);
//				System.out.println("CLEANED VAL :" + newArray[i]);
			}
			cleanedMap.put(item.getKey(), newArray);
		}

		context.put(ActionContext.PARAMETERS, cleanedMap);
		
		return invocation.invoke();
	}

	public static String[] cleanseValue(String[] value) {
		String[] cleanseValue = new String[value.length];
		for (int i = 0; i < value.length; i++) {
			cleanseValue[i] = forHTML(value[i]);
		}

		return cleanseValue;
	}

	public static String forHTML(String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '&') {
				result.append("&amp;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else if (character == '\t') {
				addCharEntity(9, result);
			} else if (character == '!') {
				addCharEntity(33, result);
			} else if (character == '#') {
				addCharEntity(35, result);
			} else if (character == '$') {
				addCharEntity(36, result);
			} else if (character == '%') {
				addCharEntity(37, result);
			} else if (character == '\'') {
				addCharEntity(39, result);
			} else if (character == '(') {
				addCharEntity(40, result);
			} else if (character == ')') {
				addCharEntity(41, result);
			} else if (character == '*') {
				addCharEntity(42, result);
			} else if (character == '+') {
				addCharEntity(43, result);
			} else if (character == ',') {
				addCharEntity(44, result);
			} else if (character == '-') {
				addCharEntity(45, result);
			} else if (character == '.') {
				addCharEntity(46, result);
			} else if (character == '/') {
				addCharEntity(47, result);
			} else if (character == ':') {
				addCharEntity(58, result);
			} else if (character == ';') {
				addCharEntity(59, result);
			} else if (character == '=') {
				addCharEntity(61, result);
			} else if (character == '?') {
				addCharEntity(63, result);
			} else if (character == '@') {
				addCharEntity(64, result);
			} else if (character == '[') {
				addCharEntity(91, result);
			} else if (character == '\\') {
				addCharEntity(92, result);
			} else if (character == ']') {
				addCharEntity(93, result);
			} else if (character == '^') {
				addCharEntity(94, result);
			} else if (character == '_') {
				addCharEntity(95, result);
			} else if (character == '`') {
				addCharEntity(96, result);
			} else if (character == '{') {
				addCharEntity(123, result);
			} else if (character == '|') {
				addCharEntity(124, result);
			} else if (character == '}') {
				addCharEntity(125, result);
			} else if (character == '~') {
				addCharEntity(126, result);
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	private static void addCharEntity(Integer aIdx, StringBuilder aBuilder) {
		String padding = "";
		if (aIdx <= 9) {
			padding = "00";
		} else if (aIdx <= 99) {
			padding = "0";
		} else {
			// no prefix
		}
		String number = padding + aIdx.toString();
		aBuilder.append("&#" + number + ";");
	}

	public void destroy() {
	}
}