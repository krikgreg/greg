package com.nixsolutions.laboratorysixteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;

@Controller
public class ListUsersController {

	@Autowired	
	private UserManagementServiceImpl service;

	@RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
	public String showListUsers(Model model) {
		model.addAttribute("allUsers", service.findAllUsers());
		return "listUsers";
	}
}
