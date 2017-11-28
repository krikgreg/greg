package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUserConrollerUnitTest {

	@Mock
	private UserManagementServiceImpl userManagementService;

	@InjectMocks
	RemoveUserController removeUserController;

	@Captor
	private ArgumentCaptor<UserManagementServiceImpl> argumentCaptor;


	@Test
	public void removeUser() {
		UserEntity user = getUserEntity();
		when(userManagementService.getUserModelById(1)).thenReturn(user);
		String actualResult = removeUserController.removeUser(1);
		verify(userManagementService, times(1)).removeUser(user);
		verify(userManagementService, times(1)).getUserModelById(1);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}

	private UserEntity getUserEntity(){
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		return  userEntity;
	}

}