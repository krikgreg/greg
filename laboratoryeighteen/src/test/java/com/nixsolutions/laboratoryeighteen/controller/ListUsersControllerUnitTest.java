package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
		verify(userManagementService, times(1)).findAllUsers();
		assertEquals("listUsers", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void showListUsersNullTest() {
		listUserController.showListUsers(null);
	}
}