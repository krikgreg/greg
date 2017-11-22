package com.nixsolutions.laboratoryseventeen.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.model.CurrentUser;
import com.nixsolutions.laboratoryseventeen.util.SecurityUtil;

@RunWith(PowerMockRunner.class)
public class LoginControllerUnitTest {
	
	private CurrentUser user;
	
	private RoleEntity role;
	
	private Model model;
	
	 @InjectMocks
	    MockHttpSession session;
	
	@InjectMocks
	LoginController loginController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		role = new RoleEntity();
		role.setName("Admin");
		UserEntity userModel = new UserEntity();
		userModel.setLogin("Login");
		userModel.setPassword("22355");
		userModel.setRole(role);
		user = new CurrentUser(userModel);
		model = new ExtendedModelMap();
	}

	@PrepareForTest({ SecurityUtil.class })
	@Test
	public void loginCurrentUserAdmin() throws Exception {
		 PowerMockito.mockStatic(SecurityUtil.class);
		 PowerMockito.when(SecurityUtil.getCurrentUser()).thenReturn(user);
		 String actualResult = loginController.login();
		 assertEquals("redirect:/admin/listUsers", actualResult);
	}
	
	@PrepareForTest({ SecurityUtil.class })
	@Test
	public void loginCurrentUserUser() throws Exception {
		 PowerMockito.mockStatic(SecurityUtil.class);
		 role.setName("User");
		 PowerMockito.when(SecurityUtil.getCurrentUser()).thenReturn(user);
		 String actualResult = loginController.login();
		 assertEquals("redirect:/user/home", actualResult);
	}
	
	@PrepareForTest({ SecurityUtil.class })
	@Test
	public void loginCurrentUserIsNotExist() throws Exception {
		 PowerMockito.mockStatic(SecurityUtil.class);
		 PowerMockito.when(SecurityUtil.getCurrentUser()).thenReturn(null);
		 String actualResult = loginController.login();
		 assertEquals("login", actualResult);
	}
	
//	@RequestMapping(value = "/login")
//	public String login() {
//		CurrentUser currentUser = SecurityUtil.getCurrentUser();
//		if (currentUser != null) {
//			if (currentUser.getRole().getName().equals("Admin")) {
//				return "redirect:/admin/listUsers";
//			} else {
//				return "redirect:/user/home";
//			}
//		} else {
//			return "login";
//		}
//	}
	
	
	@Test
	public void userRoleAdmin() {
		 String actualResult = loginController.userHome(user, model);
		 assertEquals("redirect:/admin/listUsers", actualResult);
	}
	
	@Test
	public void userRoleUser() {
		 role.setName("User");
		 String actualResult = loginController.userHome(user, model);
		 assertEquals("redirect:/user/home", actualResult);
	}
	
	
	
//	@RequestMapping(value = "/user-home")
//	public String getUser(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
//		if (currentUser.getRole().getName().equals("Admin")) {
//			return "redirect:/admin/listUsers";
//		} else {
//			return "redirect:/user/home";
//		}
//	}
	
	@Test
	public void loginFailedWithErrors() {
		session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new Object());
		 String actualResult = loginController.loginFailed(session);
		 assertEquals("login", actualResult);
	}
	
	@Test
	public void loginFailedWithoutErrors() {
		 String actualResult = loginController.loginFailed(session);
		 assertEquals("redirect:/login", actualResult);
	}
	
//	@RequestMapping(value = "/login-failed")
//	public String loginFailed(HttpSession session) {
//		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
//			return "redirect:/login";
//		}
//		return "login";
//	}
}