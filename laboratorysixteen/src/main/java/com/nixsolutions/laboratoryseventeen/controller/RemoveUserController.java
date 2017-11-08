package com.nixsolutions.laboratoryseventeen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@Controller
public class RemoveUserController {
	
	@Autowired	
	private UserManagementServiceImpl userManagementService;
      
	@RequestMapping(value = "/admin/removeUser", method = RequestMethod.GET)
	public String removeUser(@RequestParam("id") long id) {
		UserEntity userModel = userManagementService.getUserModelById(id);
		userManagementService.removeUser(userModel);
		return "redirect:/admin/listUsers";
	}
}
