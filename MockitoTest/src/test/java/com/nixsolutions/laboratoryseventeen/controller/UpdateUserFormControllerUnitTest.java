package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class UpdateUserFormControllerUnitTest {
	
	private ModelMap model;
	
	private UserEntity user;
	
	@Mock
	private UserManagementServiceImpl userManagementService;
	
	@InjectMocks
	UpdateUserFormController updateUserFormController;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = new UserEntity();
	}
	
	@Test
	public void updateUserForm(){
		when(userManagementService.getUserModelById(1)).thenReturn(user);
		String actualResult = updateUserFormController.updateUserForm(1, model);
		assertEquals("updateUser", actualResult);
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String updateUserForm(@RequestParam("id") long id, ModelMap model) {
//		UserModel user = userManagementService.getUserModelById(id);
//		user.setPasswordAgain(user.getPassword());
//		List<Role> roles = userManagementService.findAllRoles();
//		model.addAttribute("user", user);
//		model.addAttribute("roles", roles);
//		return "updateUser";
//	}	

}
