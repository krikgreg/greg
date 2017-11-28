package com.nixsolutions.laboratoryeighteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;

@Controller
public class ListUsersController {

	@Autowired	
	private UserManagementServiceImpl userManagementService;

	@RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
	public String showListUsers(Model model) {
		model.addAttribute("allUsers", userManagementService.findAllUsers());
		return "listUsers";
	}
}
