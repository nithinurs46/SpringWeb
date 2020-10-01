package com.springweb.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		/*System.out.println("SecurityContextHolder.getContext().getAuthentication().getName():::: "
				+ SecurityContextHolder.getContext().getAuthentication().getName());*/
		String pageURI = request.getRequestURI();
		System.out.println("pageURI:- " + pageURI);

		chain.doFilter(req, res);

	}

}
