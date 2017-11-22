package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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

import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

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
	}
	
	@Test
	public void updateUserIfBindingResultHasErrors(){
		when(result.hasErrors()).thenReturn(true);
		ObjectError objectError = new ObjectError("sgsceg", "segseg");
		when(result.getAllErrors()).thenReturn(Arrays.asList(new ObjectError [] {objectError}));
		String actualResult = updateUserController.updateUser(user, result, model);
		assertEquals("updateUser", actualResult);
	}
	
	@Test
	public void updateUserIfBindingResultHasNotErrors(){
		when(result.hasErrors()).thenReturn(false);
		String actualResult = updateUserController.updateUser(user, result, model);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}
}