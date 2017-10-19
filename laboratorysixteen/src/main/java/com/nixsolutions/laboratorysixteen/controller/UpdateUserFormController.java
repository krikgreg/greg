package com.nixsolutions.laboratorysixteen.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;

/**
 * Servlet implementation class EditAccountServlet
 */
@Controller
@RequestMapping("/admin/updateUserForm")
public class UpdateUserFormController extends AbstractServlet {
	private static final long serialVersionUID = -7613855051406520241L;
	
	@Autowired	
	private UserManagementServiceImpl service;

	@RequestMapping(method = RequestMethod.GET)
	public String updateUserForm(@RequestParam("id") long id, ModelMap model) {
		UserModel user = getUserService().getUserModelById(id);
		user.setPasswordAgain(user.getPassword());
		List<Role> roles = service.findAllRoles();
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "updateUser";
	}	
}
