package com.nixsolutions.laboratoryseventeen.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nixsolutions.laboratoryseventeen.model.CurrentUser;
import com.nixsolutions.laboratoryseventeen.util.SecurityUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String login() {
		CurrentUser currentUser = SecurityUtil.getCurrentUser();
		if (currentUser != null) {
			if (currentUser.getRole().getName().equals("Admin")) {
				return "redirect:/admin/listUsers";
			} else {
				return "redirect:/user/home";
			}
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/user-home")
	public String userHome(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
		if (currentUser.getRole().getName().equals("Admin")) {
			return "redirect:/admin/listUsers";
		} else {
			return "redirect:/user/home";
		}
	}

	@RequestMapping(value = "/login-failed")
	public String loginFailed(HttpSession session) {
		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
			return "redirect:/login";
		}
		return "login";
	}
}