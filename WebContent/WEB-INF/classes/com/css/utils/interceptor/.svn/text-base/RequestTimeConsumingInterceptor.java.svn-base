package com.css.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestTimeConsumingInterceptor extends HandlerInterceptorAdapter {

	private Log logger = LogFactory.getLog(this.getClass());
	
    private NamedThreadLocal<Long>  startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if(handler instanceof HandlerMethod){
    		long beginTime = System.currentTimeMillis();//开始时间
            startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
    	}
        return true;//继续流程
    }
    
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	if(handler instanceof org.springframework.web.method.HandlerMethod){
    		long endTime = System.currentTimeMillis();//2、结束时间
            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long consumeTime = endTime - beginTime;//3、消耗的时间
            //if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
                //TODO 记录到日志文件
            //}
            if(logger.isDebugEnabled()) {
            	logger.debug(String.format("请求：%s 耗时： %d millis", request.getRequestURI(), consumeTime));
            }
    	}
    }
    
}
