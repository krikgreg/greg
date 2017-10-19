package com.nixsolutions.laboratorysixteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;

/**
 * Servlet implementation class RemoveUserServlet
 */
@Controller
public class RemoveUserController {
	
	@Autowired	
	private UserManagementServiceImpl service;
      
	@RequestMapping(value = "/admin/removeUser", method = RequestMethod.GET)
	public String removeUser(@RequestParam("id") long id) {
		UserModel userModel = service.getUserModelById(id);
		service.removeUser(userModel);
		return "redirect:/admin/listUsers";
	}
}
