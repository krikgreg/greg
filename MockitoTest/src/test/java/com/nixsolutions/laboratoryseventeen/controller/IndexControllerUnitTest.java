package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

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

	// @RequestMapping(value = "/" , method = RequestMethod.GET)
	// public String login() {
	// return "redirect:login";
	// }

}
