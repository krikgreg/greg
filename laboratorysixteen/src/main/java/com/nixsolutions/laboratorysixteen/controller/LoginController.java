package com.nixsolutions.laboratorysixteen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratorysixteen.util.SessionUtils;
import com.nixsolutions.laboratorysixteen.validator.impl.Validation;

/**
 * Servlet implementation class LoginServlet
 */
@Controller
public class LoginController extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
@Autowired	
private UserManagementServiceImpl service;

@Autowired
private Validation validate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (SessionUtils.isCurrentUserLogin(request)) {
//			redirectDependsOnRole(request, response);
//		}
			UserModel user = new UserModel();
			model.addAttribute("user", user);
			return "login";
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLoginPassword(@ModelAttribute("user") UserModel user, BindingResult result, Model model,  HttpSession session)
			throws ServletException, IOException {
		validate.checkLoginPassword(user.getLogin(), user.getPassword(), result);
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("error", result.getFieldError("login"));
			return "login";
		}else{
			 return sendDependsOnRole(user.getLogin(), model, session);
		}
	}
	
//	private void redirectDependsOnRole(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		UserModel userModel = SessionUtils.getCurrentUserModel(req);
//		if (userModel.getRole().getName().equals("Admin")) {
//			resp.sendRedirect("/laboratorysixteen/user/home");
//		} else {
//			resp.sendRedirect("/laboratorysixteen/admin/listUsers");
//		}
//	}


	private String sendDependsOnRole(String login, Model model, HttpSession session)
			throws ServletException, IOException {
		UserModel userModel = service.getUserModelByLogin(login);
		if (userModel.getRole().getName().equals("Admin")) {
			session.setAttribute(Constants.CURRENT_USERMODEL, userModel);
			return "redirect:/admin/listUsers";
		} else {
			session.setAttribute("user", userModel);
			model.addAttribute(Constants.CURRENT_USERMODEL, userModel);
			return "redirect:/user/home";
		}
	}

}