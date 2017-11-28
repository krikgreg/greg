package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddUserConrollerUnitTest {

	private UserEntity user;

	@Mock
	private BindingResult result;

	private ModelMap model;

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	AddUserController userController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = getUserEntity();
	}

	@Test
	public void addUserIfBindingResultHasErrors() {
		when(result.hasErrors()).thenReturn(true);
		ObjectError objectError = new ObjectError("sgseg", "segseg");
		when(result.getAllErrors()).thenReturn(Arrays.asList(new ObjectError[] { objectError }));
		String actualResult = userController.addUser(user, result, model);
		verify(userManagementService, times(1)).findAllRoles();
		assertEquals("addUser", actualResult);
	}

	@Test
	public void addUserIfBindingResultHasNotErrors() {
		when(result.hasErrors()).thenReturn(false);
		String actualResult = userController.addUser(user, result, model);
		verify(userManagementService, times(1)).createUser(user);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void addUserNullTest() {
		userController.addUser(null, null, null);
	}

	private UserEntity getUserEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		return  userEntity;
	}
}