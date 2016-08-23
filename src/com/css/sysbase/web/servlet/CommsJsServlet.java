package com.css.sysbase.web.servlet;


import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.sysbase.cache.CodeTBCache;
import com.css.sysbase.cache.model.CodeModel;
import com.css.sysbase.cache.model.CodeTypeModel;

public class CommsJsServlet extends HttpServlet {

	
	private String userId;
	
	private String userName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommsJsServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String, Object>)request.getSession().getAttribute(request.getSession().getId());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		StringBuffer wsbf= new StringBuffer();
		
		if(map==null||map.isEmpty()){
			wsbf.append("var auto_js_codes=function(){};var auto_js_codes_imp=new auto_js_codes();function get_js_codeText(a,b){}function has_js_codeText(a,b){}");
		}else{
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String day = String.valueOf(cal.get(Calendar.DATE));
			month = month.length()==1?"0".concat(month):month;
			day = day.length()==1?"0".concat(day):day;
			String curDate = year +"-" + month + "-" + day;
			wsbf.append("var curDateTime = '" + curDate+"';\n");
			
			wsbf.append("var auto_js_codes=function(){};\n");
			wsbf.append("auto_js_codes.prototype={ \n");
			@SuppressWarnings("unchecked")
			Map<String, String[]> reqMap= request.getParameterMap();
			String c[]=(String[]) reqMap.get("c");
			if(c!=null&&c.length>=1){
				for(String ic:c){
					CodeTypeModel codeTypeModel=CodeTBCache.getInsCodeCache().getCodes(ic);
					if(codeTypeModel!=null){
						wsbf.append("	//");
						wsbf.append(codeTypeModel.getCtypeName());
						wsbf.append("\n");
						wsbf.append("	");
						wsbf.append(codeTypeModel.getCtypeCode());
						wsbf.append("s_js:[");
						Iterator<CodeModel> its= codeTypeModel.getListIt();
						while(its.hasNext()){
							CodeModel cm=its.next();
							wsbf.append("{'code_name':'");
							wsbf.append(cm.getCodeName());
							wsbf.append("',");
							wsbf.append("'code_value':'");
							wsbf.append(cm.getCodeValue());
							wsbf.append("'},");

						}
						wsbf.deleteCharAt(wsbf.length() - 1);
//						wsbf.delete(wsbf.length()-1, wsbf.length());
						wsbf.append("],\n");
					}
				}
				wsbf.delete(wsbf.length()-2, wsbf.length());
			}
			wsbf.append("\n};\n");
			wsbf.append("\n");
			wsbf.append("var auto_js_codes_imp=new auto_js_codes();\n");
			wsbf.append("function get_js_codeText(a,c){\n");
			wsbf.append("	var codes=auto_js_codes_imp[a];\n");
			wsbf.append("	if(!codes){return '未知';};\n");
			wsbf.append("	for(var i=0;i<codes.length;i++){\n");
			wsbf.append("		if(codes[i].code_value==c){\n");
			wsbf.append("			return codes[i].code_name;\n");
			wsbf.append("		}\n");
			wsbf.append("	}\n");
			wsbf.append("	return '未知';\n");
			wsbf.append("}\n");
			
			wsbf.append("function has_js_codeText(a){\n");
			wsbf.append("	var codes=auto_js_codes_imp[a];\n");
			wsbf.append("	if(!codes){return false;};\n");
			wsbf.append("	return true;\n");
			wsbf.append("}\n");
			
		}
		response.getWriter().write(wsbf.toString());
	}
}
