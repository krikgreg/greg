package com.nixsolutions.laboratorysixteen.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratorysixteen.validator.impl.Validation;

@Controller
public class AddUserController {

	@Autowired
	private Validation validate;
	
	@Autowired	
	private UserManagementServiceImpl service;

	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") UserModel user,
            BindingResult result, ModelMap model) {
		validate.checkLogginForAddUser(user, result);
		validate.checkEmailForEditUser(user, result);
		validate.checkPasswords(user, result);
		 if (result.hasErrors()) {
			 List<Role> roles = service.findAllRoles();
	         model.addAttribute("roles", roles);
	         model.addAttribute("user", user);
	         model.addAttribute("errors", result.getAllErrors());
	         return "addUser";
		} else {
			service.createUser(user);
			return "redirect:/admin/listUsers";
		}
	}
}
