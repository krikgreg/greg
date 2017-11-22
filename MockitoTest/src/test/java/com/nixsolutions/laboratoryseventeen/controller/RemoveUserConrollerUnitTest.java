package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUserConrollerUnitTest {
	
	@Mock
	private UserManagementServiceImpl userManagementService;
	
	@InjectMocks
	RemoveUserController removeUserController;
	
	@Test
	public void removeUser(){
		String actualResult = removeUserController.removeUser(1);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}
	
//	@RequestMapping(value = "/admin/removeUser", method = RequestMethod.GET)
//	public String removeUser(@RequestParam("id") long id) {
//		UserModel userModel = userManagementService.getUserModelById(id);
//		userManagementService.removeUser(userModel);
//		return "redirect:/admin/listUsers";
//	}

}
