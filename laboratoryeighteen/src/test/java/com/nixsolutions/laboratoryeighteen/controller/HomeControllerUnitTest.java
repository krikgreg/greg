package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

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
}

