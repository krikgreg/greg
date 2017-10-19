package com.nixsolutions.laboratorysixteen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.UserManagementService;

public abstract class AbstractServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9135197929171020621L;
	
	@Autowired
	private UserManagementService userService;
	
	@Override
	public final void init() throws ServletException {
		this.userService = (UserManagementService) getServletContext().getAttribute(Constants.USER_MANAGEMENT_SERVICE);
	}
	
	public UserManagementService getUserService() {
		return userService;
	}
	
	protected void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("currentPage", page);
		request.getRequestDispatcher("/WEB-INF/view/page-template.jsp").forward(request, response);
	}
	
	protected UserModel setNewUserUiValues(HttpServletRequest req) {
		UserModel userModel = new UserModel();
		userModel.setLogin(req.getParameter("login"));
    	userModel.setPassword(req.getParameter("password"));
    	userModel.setPasswordAgain(req.getParameter("passwordAgain"));
    	userModel.setEmail(req.getParameter("email"));
    	userModel.setFirstName(req.getParameter("firstName"));
    	userModel.setLastName(req.getParameter("lastName"));
		userModel.setBirthday(req.getParameter("birthday"));
		userModel.setRole(new Role(Long.parseLong(req.getParameter("role"))));
		return userModel;
	}
	
	protected UserModel setUpdateUserUiValues(HttpServletRequest req) {
		UserModel user = setNewUserUiValues(req);
		user.setId(Long.parseLong(req.getParameter("id")));
		return user;
	}
}
