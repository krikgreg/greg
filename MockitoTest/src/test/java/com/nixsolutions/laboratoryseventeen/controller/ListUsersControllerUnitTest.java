package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersControllerUnitTest {

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	ListUsersController listUserController;

	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		model = new ExtendedModelMap();
	}

	@Test
	public void showListUsers() {
		String actualResult = listUserController.showListUsers(model);
		assertEquals("listUsers", actualResult);
	}

	//
	// @RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
	// public String showListUsers(Model model) {
	// model.addAttribute("allUsers", userManagementService.findAllUsers());
	// return "listUsers";
	// }
}
