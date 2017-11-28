package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class IndexControllerUnitTest {

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	IndexController indexController;

	@Test
	public void login() {
		String actualResult = indexController.login();
		assertEquals("redirect:login", actualResult);
	}
}