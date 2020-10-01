package com.springweb.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
		System.out.println("[preHandle]");
		//System.out.println("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		log.info("[postHandle][" + request + "]");
		System.out.println("[postHandle]");
		//System.out.println("[postHandle][" + request + "]");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		if (ex != null){
	        ex.printStackTrace();
	    }
		System.out.println("[afterCompletion]");
		log.info("[afterCompletion][" + request + "][exception: " + ex + "]");
		//System.out.println("[afterCompletion][" + request + "][exception: " + ex + "]");
	}
}
