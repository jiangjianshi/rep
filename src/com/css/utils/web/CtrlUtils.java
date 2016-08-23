package com.css.utils.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.utils.cache.web.LoginSessionCache;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class CtrlUtils {

	private CtrlUtils() {
	}

	// 是否为 Ajax访问
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

	public static void putJSON(Object object,Map<String, String> dateFormatInfor, HttpServletResponse res) {
		JsonConfig jsonConfig = getJsonConfig(dateFormatInfor);
		putJSON_(JSONArray.fromObject(object,jsonConfig), res);
	}

	public static void putJSON_(JSONArray jsonArray, HttpServletResponse res) {
		writeStr2Res(jsonArray.toString(), res);
	}

	public static void putMapJSONToClient(final Map map, HttpServletResponse res) {
		// 为某些时间类型的列指定输出格式
		final Map<String, String> dateFormatInfor = (Map<String, String>) map.get("dateFormatInfor");
		JsonConfig jsonConfig = getJsonConfig(dateFormatInfor);
		String json = JSONObject.fromObject(map, jsonConfig).toString();
		writeStr2Res(json, res);
	}

	public static JsonConfig getJsonConfig(final Map<String, String> dateFormatInfor) {
		// json转换器
		JsonConfig jsonConfig = new JsonConfig();
		// 注册 java.util.Date.class
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessor() {
					@Override
					public Object processObjectValue(String arg0, Object value,
							JsonConfig arg2) {
						if (value == null) {
							return "";
						}
						if (value instanceof java.util.Date) {
							String formatResult = "";
							if (dateFormatInfor != null
									&& dateFormatInfor.containsKey(arg0)) {
								formatResult = new SimpleDateFormat(
										dateFormatInfor.get(arg0))
										.format(value);
							} else {
								formatResult = new SimpleDateFormat(
										"yyyy-MM-dd").format(value);
							}
							return formatResult;
						}
						return value;
					}

					@Override
					public Object processArrayValue(Object arg0, JsonConfig arg1) {
						return null;
					}
				});
		// 注册 java.sql.Date.class
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessor() {
					@Override
					public Object processObjectValue(String arg0, Object value,
							JsonConfig arg2) {
						if (value == null) {
							return "";
						}
						if (value instanceof java.sql.Date) {
							String formatResult = "";
							if (dateFormatInfor != null
									&& dateFormatInfor.containsKey(arg0)) {
								formatResult = new SimpleDateFormat(
										dateFormatInfor.get(arg0))
										.format(value);
							} else {
								formatResult = new SimpleDateFormat(
										"yyyy-MM-dd").format(value);
							}
							return formatResult;
						}
						return value;
					}

					@Override
					public Object processArrayValue(Object arg0, JsonConfig arg1) {
						return null;
					}
				});
		// 注册 java.sql.Timestamp.class
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
				new JsonValueProcessor() {
					@Override
					public Object processObjectValue(String arg0, Object value,
							JsonConfig arg2) {
						if (value == null) {
							return "";
						}
						if (value instanceof java.sql.Timestamp) {
							String result = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss").format(value);
							return result;
						}
						return value;
					}

					@Override
					public Object processArrayValue(Object arg0, JsonConfig arg1) {
						return null;
					}
				});
		return jsonConfig;
	}

	public static void writeStr2Res(String jsonStr, HttpServletResponse res) {
		res.setContentType("text/html");
		res.setContentType("text/html; charset=UTF-8");
		try {
			res.getWriter().write(jsonStr);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	public static void writeImageBytes(String imagesPath,
			HttpServletResponse response) throws Exception {
		response.reset();
		response.setContentType("image/*");
		File imageFile = new File(imagesPath);
		InputStream is = new FileInputStream(imageFile);
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		is.close();
	}

	/**
	 * 将request中的参数转换为Map键值对形式(并处理UTF-8编码)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map getReqParametersMap_Utf8(HttpServletRequest request)
			throws Exception {
		Map paramMap = new HashMap<String, String>();
		Map reqMap = request.getParameterMap();
		Set keys = reqMap.keySet();
		for (Object key : keys.toArray()) {
			String value = new String(((String[]) reqMap.get(key))[0]);
			value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
			paramMap.put(key.toString(), value);
		}
		paramMap.put("basePath", request.getContextPath());
		return paramMap;
	}

	/**
	 * 将request中的参数转换为Map键值对形式
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map getReqParametersMap(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map paramMap = new HashMap<String, String>();
		Map reqMap = request.getParameterMap();
		Set keys = reqMap.keySet();
		for (Object key : keys.toArray()) {
			String value = new String(((String[]) reqMap.get(key))[0]);
			paramMap.put(key.toString(), value);
		}
		return paramMap;
	}

	/**
	 * getWebrootPath
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getWebrootPath(String path) {
		String fileSeparator = System.getProperty("file.separator");
		String classesPath = new CtrlUtils().getClass().getResource("/")
				.getPath();
		File classesDir = new File(classesPath);
		File webRootDir = classesDir.getParentFile().getParentFile();
		path = path.replace("/", fileSeparator);
		return webRootDir.getAbsolutePath() + path;
	}

	public static String getSessionUserId(HttpServletRequest request) {
		Map userMap = (Map) request.getSession().getAttribute(
				request.getSession().getId());
		Map searchParam = new HashMap();
		String userId = (String) userMap.get("user_id");
		return userId;
	}
	
	public static Map getSessionUserInforMap(HttpServletRequest request) {
		Map userMap = (Map) request.getSession().getAttribute(
				request.getSession().getId());
		return userMap;
	}
}
