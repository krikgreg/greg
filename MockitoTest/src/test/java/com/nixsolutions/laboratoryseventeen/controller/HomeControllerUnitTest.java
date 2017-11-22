package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerUnitTest {
	
	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	HomeController homeController;
	
	@Test
	public void addUserForm(){
		String actualResult = homeController.userHome();
		assertEquals("home", actualResult);
	}
	
//	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
//	public String userHome(){
//		return "home";
//	}

}
