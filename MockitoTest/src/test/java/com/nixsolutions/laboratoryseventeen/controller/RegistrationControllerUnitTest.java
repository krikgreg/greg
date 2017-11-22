package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.RecaptchaServiceImpl;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratoryseventeen.util.SecurityUtil;


@RunWith(PowerMockRunner.class)
public class RegistrationControllerUnitTest {

	@Mock
	private UserManagementServiceImpl userManagementService;
	
	@Mock
	private BindingResult result;

	@InjectMocks
	private RegistrationController registrationController;

	@Mock
	private RecaptchaServiceImpl recaptchaService;

	private ModelMap model;
	
	private UserEntity user;

	private MockHttpServletRequest request;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		model = new ModelMap();
		user = new UserEntity();
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

	@Test
	public void registerUserIfRecaptchaHasErrors() throws Exception {
		when(result.hasErrors()).thenReturn(false);
		when(recaptchaService.validateCaptcha(anyString(), anyString())).thenReturn(false);
		String actualResult = registrationController.registrateUser(user, result, model, request,"sefsef");
		assertEquals("registration", actualResult);
	}
	
	@PrepareForTest({ SecurityUtil.class })
	@Test
	public void registerUserIfBindingResultRecapthcaHasNotErrors() throws Exception {
		when(result.hasErrors()).thenReturn(false);
		when(recaptchaService.validateCaptcha(anyString(), anyString())).thenReturn(true);
		PowerMockito.mockStatic(SecurityUtil.class);
		PowerMockito.doNothing().when(SecurityUtil.class, "authentificate", Mockito.any(UserEntity.class));
		String actualResult = registrationController.registrateUser(user, result, model, request,"sefsef");
		assertEquals("redirect:/login", actualResult);
	}
	
	@Test
	public void registrationForm() {
			String actualResult = registrationController.registrationForm(model);
			assertEquals("registration", actualResult);
		}
	}
	
//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public String addUser(@ModelAttribute("user") @Valid UserModel user, BindingResult result, ModelMap model) {
//		if (result.hasErrors()) {
//			List<Role> roles = userManagementService.findAllRoles();
//			model.addAttribute("roles", roles);
//			model.addAttribute("user", user);
//			model.addAttribute("errors", result.getAllErrors());
//			return "registration";
//		} else {
//			userManagementService.createUser(user);
//			SecurityUtil.authentificate(user);
//			return "redirect:/login";
//		}
//	}
	
//	@RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
//	public String login(ModelMap model) {
//		UserModel user = new UserModel();
//		List<Role> roles = userManagementService.findAllRoles();
//		model.addAttribute("roles", roles);
//		model.addAttribute("user", user);
//		return "registration";
//	}
	
	
