package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
		verify(userManagementService, times(1)).findAllRoles();
		assertEquals("addUser", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void addUserNullTest() {
		userFormController.addUserForm(null);
	}
}

