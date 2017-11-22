package com.nixsolutions.laboratoryseventeen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@Controller
@RequestMapping("/admin/updateUserForm")
public class UpdateUserFormController {
	
	@Autowired	
	private UserManagementServiceImpl userManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public String updateUserForm(@RequestParam("id") long id, ModelMap model) {
		UserEntity user = userManagementService.getUserModelById(id);
		user.setPasswordAgain(user.getPassword());
		List<RoleEntity> roles = userManagementService.findAllRoles();
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "updateUser";
	}	
}
