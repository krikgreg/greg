package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AddUserFormControllerUnitTest {


	private ModelMap model;
	
	@Mock
	private UserManagementServiceImpl userManagementService;
	
	@InjectMocks
	AddUserFormController userFormController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
	}
	
	@Test
	public void addUserForm(){
		String actualResult = userFormController.addUserForm(model);
		assertEquals("addUser", actualResult);
	}
	



//	@RequestMapping(method = RequestMethod.GET)
//	public String addUserForm(ModelMap model) {
//		List<Role> roles = userManagementService.findAllRoles();
//		model.addAttribute("roles", roles);
//		model.addAttribute("user", new UserModel());
//		return "addUser";
//	}
}


