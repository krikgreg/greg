package com.nixsolutions.laboratorysixteen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;

@Controller
@RequestMapping("/admin/addUserForm")
public class AddUserFormController extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	@Autowired	
	private UserManagementServiceImpl service;
   
	@RequestMapping(method = RequestMethod.GET)
	public String addUserForm(ModelMap model) {
		List<Role> roles = service.findAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new UserModel());
		return "addUser";
	}
}