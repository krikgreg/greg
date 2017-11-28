package com.nixsolutions.laboratoryeighteen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;

@Controller
@RequestMapping("/admin/addUserForm")
public class AddUserFormController {
	
	@Autowired	
	private UserManagementServiceImpl userManagementService;
   
	@RequestMapping(method = RequestMethod.GET)
	public String addUserForm(ModelMap model) {
		List<RoleEntity> roles = userManagementService.findAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new UserEntity());
		return "addUser";
	}
}