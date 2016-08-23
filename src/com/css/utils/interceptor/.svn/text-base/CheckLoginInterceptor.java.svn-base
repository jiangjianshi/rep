package com.css.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.css.utils.cache.web.LoginSessionCache;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	private String backUrl;
	private String obviateUrl;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			String url  = request.getRequestURI();
			String contextPath = request.getContextPath();
			HttpSession session = request.getSession();
			
			//-----判断当前访问请求地址是否需要监听session失效的访问页面----start
	        //默认需要作监听session处理
	        boolean isFilter = true;
	        if (StringUtils.isNotBlank(obviateUrl)) {
	            String[] obviateArray = obviateUrl.split(",");
	            for (int i = 0; i < obviateArray.length; i++) {
	                if (url.equals(contextPath + obviateArray[i])) {
	                    //不需要作监听session处理
	                    isFilter = false;
	                    break;
	                }
	            }
	        }
	        //-----判断当前访问请求地址是否需要监听session失效的访问页面----end
	        
	        
	        //如果需要作监听session处理
	        if (isFilter) {
	        	//从session中获取用户名
	        	LoginSessionCache loginSessionCache = (LoginSessionCache) session.getAttribute("UserSession");
	            //如果session中的用户名信息不存在，则表示session已过期
	            if (loginSessionCache==null) {
	            	//转到session失效页面
	                String targetUrl = contextPath + backUrl;
	                response.sendRedirect(response.encodeRedirectURL(targetUrl));
	                return false;
	            }
	        }
	        //正常访问当前页面
	        return true;
		}else{
			//正常访问当前页面
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void setObviateUrl(String obviateUrl) {
		this.obviateUrl = obviateUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

}
