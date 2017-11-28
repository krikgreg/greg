package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.model.CurrentUser;
import com.nixsolutions.laboratoryeighteen.service.impl.RecaptchaServiceImpl;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratoryeighteen.util.SecurityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerUnitTest {

	@Mock
	private UserManagementServiceImpl userManagementService;

	@Mock
	private BindingResult result;

	@InjectMocks
	private RegistrationController registrationController;

	@Mock
	private RecaptchaServiceImpl recaptchaService;

	@Mock
	SecurityUtil securityUtil;

	private ModelMap model;

	private UserEntity user;

	private CurrentUser currentUser;

	private MockHttpServletRequest request;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = getUserEntity();
		request = new MockHttpServletRequest();
	}

	@Test
	public void registerUserIfBindingResultHasErrors() {
		when(result.hasErrors()).thenReturn(true);
		ObjectError objectError = new ObjectError("sgseg", "segseg");
		when(result.getAllErrors()).thenReturn(Arrays.asList(new ObjectError[] { objectError }));
		String actualResult = registrationController.registrateUser(user, result, model, request,"sefsef");
		assertEquals("registration", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void registrationFormUserNullTest() {
		registrationController.registrationForm(null);
	}

	@Test
	public void registerUserIfRecaptchaHasErrors() throws Exception {
		when(result.hasErrors()).thenReturn(false);
		when(recaptchaService.validateCaptcha("127.0.0.1", "Second String")).thenReturn(false);
		String actualResult = registrationController.registrateUser(user, result, model, request,"Second String");
		verify(userManagementService, times(1)).findAllRoles();
		verify(recaptchaService, times(1)).validateCaptcha("127.0.0.1", "Second String");
		assertEquals("registration", actualResult);
	}

	@Test
	public void registerUserIfBindingResultRecapthcaHasNotErrors() throws Exception {
		when(result.hasErrors()).thenReturn(false);
		when(recaptchaService.validateCaptcha("127.0.0.1", "success")).thenReturn(true);
		String actualResult = registrationController.registrateUser(user, result, model, request,"success");
		//adding changes
		verify(securityUtil, times(1)).authentificate(user);
		verify(userManagementService, times(1)).findAllRoles();
		verify(recaptchaService, times(1)).validateCaptcha("127.0.0.1", "success");
		verify(userManagementService, times(1)).createUser(user);
		assertEquals("redirect:/login", actualResult);
	}

	@Test
	public void registrationForm() {
		String actualResult = registrationController.registrationForm(model);
		verify(userManagementService, times(1)).findAllRoles();
		assertEquals("registration", actualResult);
	}

	@Test(expected = NullPointerException.class)
	public void registrationControllerUserNullTest() {
		registrationController.registrateUser(null, null, null, null, null);
	}

	private UserEntity getUserEntity(){
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		return  userEntity;
	}
}
	
