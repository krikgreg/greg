package com.nixsolutions.laboratoryseventeen.controller;

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
import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratoryseventeen.validator.impl.BirthdayFormatValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.FirstNameFormatValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.LastNameFormatValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.LoginDuplicationValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.LoginFormatValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.PasswordFormatValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.PasswordMatchValidator;

@Controller
public class AddUserController {

	@Autowired
	private PasswordMatchValidator passwordValidator;

	@Autowired
	private LoginFormatValidator loginFormatValidator;

	@Autowired
	private PasswordFormatValidator passwordFormatValidator;

	@Autowired
	private BirthdayFormatValidator birthdayFormatValidator;

	@Autowired
	private FirstNameFormatValidator firstNameFormatValidator;

	@Autowired
	private LastNameFormatValidator lastNameFormatValidator;

	@Autowired
	private LoginDuplicationValidator loginValidator;
	
	@Autowired	
	private UserManagementServiceImpl userManagementService;
	
	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(firstNameFormatValidator);
		binder.addValidators(lastNameFormatValidator);
		binder.addValidators(birthdayFormatValidator);
		binder.addValidators(passwordFormatValidator);
		binder.addValidators(loginFormatValidator);
		binder.addValidators(loginValidator);
		binder.addValidators(passwordValidator);
	}

	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Valid UserEntity user,
            BindingResult result, ModelMap model) {
		 if (result.hasErrors()) {
			 List<RoleEntity> roles = userManagementService.findAllRoles();
	         model.addAttribute("roles", roles);
	         model.addAttribute("user", user);
	         model.addAttribute("errors", result.getAllErrors());
	         return "addUser";
		} else {
			userManagementService.createUser(user);
			return "redirect:/admin/listUsers";
		}
	}
}
