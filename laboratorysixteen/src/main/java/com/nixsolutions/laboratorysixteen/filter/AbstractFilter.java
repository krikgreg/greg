package com.nixsolutions.laboratorysixteen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractFilter implements Filter {

	public void init(FilterConfig filterConfig) {

	}

	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		doFilter(req, resp, chain);
	}

	public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException;

	public void destroy() {

	}
	
	protected boolean isAdminUrl(String curentUrl) {
		if (curentUrl.startsWith("/laboratorysixteen/admin/")) {
			return true;
		}
		return false;
	}
	
	protected boolean isUserUrl(String curentUrl) {
		if (curentUrl.startsWith("/laboratorysixteen/user/")) {
			return true;
		}
		return false;
	}

}
