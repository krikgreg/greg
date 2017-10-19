//package com.nixsolutions.laboratorysixteen.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.nixsolutions.laboratorysixteen.Constants;
//import com.nixsolutions.laboratorysixteen.model.UserModel;
//
//
//@WebFilter("/*")
//public class CheckLoginFilter extends AbstractFilter {
//	
//	@Override
//	public final void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
//		UserModel userModel = (UserModel) request.getSession().getAttribute(Constants.CURRENT_USERMODEL);
//		String currentUrl = request.getRequestURI();
//		if(userModel == null && isAdminUrl(currentUrl) || userModel == null && isUserUrl(currentUrl)){
//			response.sendRedirect("/laboratorysixteen/login");
//		}else{
//			chain.doFilter(request, response);
//		}
//	}
//}
