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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UpdateUserFormControllerUnitTest {

	private ModelMap model;

	private UserEntity user;

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	UpdateUserFormController updateUserFormController;


	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = getUserEntity();
	}

	@Test
	public void updateUserForm(){
		when(userManagementService.getUserModelById(1)).thenReturn(user);
		String actualResult = updateUserFormController.updateUserForm(1, model);
		verify(userManagementService, times(1)).getUserModelById(1);
		verify(userManagementService, times(1)).findAllRoles();
		assertEquals("updateUser", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void updateUserFormNullTest() {
		updateUserFormController.updateUserForm(1, null);
	}

	private UserEntity getUserEntity(){
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		return  userEntity;
	}

}
