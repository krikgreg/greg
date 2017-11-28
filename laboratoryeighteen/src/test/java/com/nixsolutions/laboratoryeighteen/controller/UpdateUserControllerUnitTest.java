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
public class UpdateUserControllerUnitTest {

	private UserEntity user;

	@Mock
	private BindingResult result;

	private ModelMap model;

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	UpdateUserController updateUserController;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = getUserEntity();
	}

	@Test
	public void updateUserIfBindingResultHasErrors(){
		when(result.hasErrors()).thenReturn(true);
		ObjectError objectError = new ObjectError("sgsceg", "segseg");
		when(result.getAllErrors()).thenReturn(Arrays.asList(new ObjectError [] {objectError}));
		String actualResult = updateUserController.updateUser(user, result, model);
		verify(userManagementService, times(1)).findAllRoles();
		assertEquals("updateUser", actualResult);
	}

	@Test
	public void updateUserIfBindingResultHasNotErrors(){
		when(result.hasErrors()).thenReturn(false);
		String actualResult = updateUserController.updateUser(user, result, model);
		// adding new verification
		verify(userManagementService, times(1)).updateUser(user);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void updateUserControllerUserNullTest() {
		updateUserController.updateUser(null, null, null);
	}


	private UserEntity getUserEntity(){
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		return  userEntity;
	}
}