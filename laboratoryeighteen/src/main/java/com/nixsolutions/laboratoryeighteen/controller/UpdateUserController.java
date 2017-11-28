package com.nixsolutions.laboratoryeighteen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratoryeighteen.validator.impl.EmailDuplicationValidator;
import com.nixsolutions.laboratoryeighteen.validator.impl.PasswordMatchValidator;

@Controller
public class UpdateUserController {

	@Autowired
	private EmailDuplicationValidator emailValidator;

	@Autowired
	private PasswordMatchValidator passwordValidator;

	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(emailValidator);
		binder.addValidators(passwordValidator);
	}

	@Autowired
	private UserManagementServiceImpl userManagementService;

	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			List<RoleEntity> roles = userManagementService.findAllRoles();
			model.addAttribute("roles", roles);
			model.addAttribute("user", user);
			model.addAttribute("errors", result.getAllErrors());
			return "updateUser";
		} else {
			userManagementService.updateUser(user);
			return "redirect:/admin/listUsers";
		}
	}
}
