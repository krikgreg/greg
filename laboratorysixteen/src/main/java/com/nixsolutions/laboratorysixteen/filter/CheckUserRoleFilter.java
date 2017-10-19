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
//@WebFilter({ "/admin/*", "/user/*" })
//public class CheckUserRoleFilter extends AbstractFilter {
//
//	@Override
//	public final void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		String userModelName = ((UserModel) request.getSession().getAttribute(Constants.CURRENT_USERMODEL)).getRole()
//				.getName();
//		String currentUrl = request.getRequestURI();
//		if (userModelName.equals("Admin") && !isAdminUrl(currentUrl)) {
//			response.sendRedirect("/laboratorysixteen/admin/listUsers");
//		} else if (userModelName.equals("Admin") && isAdminUrl(currentUrl)) {
//			chain.doFilter(request, response);
//		} else if (userModelName.equals("User") && !isUserUrl(currentUrl)) {
//			response.sendRedirect("/laboratorysixteen/user/home");
//		} else if (userModelName.equals("User") && isUserUrl(currentUrl)) {
//			chain.doFilter(request, response);
//		}
//	}
//}