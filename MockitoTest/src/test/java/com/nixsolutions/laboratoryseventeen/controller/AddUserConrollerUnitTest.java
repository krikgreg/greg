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
	}

	@Test
	public void addUserIfBindingResultHasErrors() {
		when(result.hasErrors()).thenReturn(true);
		ObjectError objectError = new ObjectError("sgseg", "segseg");
		when(result.getAllErrors()).thenReturn(Arrays.asList(new ObjectError[] { objectError }));
		String actualResult = userController.addUser(user, result, model);
		assertEquals("addUser", actualResult);
	}

	// @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	// public String addUser(@ModelAttribute("user") @Valid UserModel user,
	// BindingResult result, ModelMap model) {
	// if (result.hasErrors()) {
	// List<Role> roles = userManagementService.findAllRoles();
	// model.addAttribute("roles", roles);
	// model.addAttribute("user", user);
	// model.addAttribute("errors", result.getAllErrors());
	// return "addUser";
	// } else {
	// userManagementService.createUser(user);
	// return "redirect:/admin/listUsers";
	// }
	// }

	@Test
	public void addUserIfBindingResultHasNotErrors() {
		when(result.hasErrors()).thenReturn(false);
		String actualResult = userController.addUser(user, result, model);
		assertEquals("redirect:/admin/listUsers", actualResult);
	}
}