package com.nixsolutions.laboratoryeighteen.controller;

import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.model.CurrentUser;
import com.nixsolutions.laboratoryeighteen.util.SecurityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerUnitTest {

	private CurrentUser user;

	private RoleEntity role;

	private Model model;

	@InjectMocks
	MockHttpSession session;

	@InjectMocks
	LoginController loginController;

	@Mock
	SecurityUtil securityUtil;

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

	@Test
	public void loginCurrentUserAdmin() throws Exception {
		Mockito.when(securityUtil.getCurrentUser()).thenReturn(user);
		String actualResult = loginController.login();
		assertEquals("redirect:/admin/listUsers", actualResult);
	}

	@Test
	public void loginCurrentUserUser() throws Exception {
		role.setName("User");
		Mockito.when(securityUtil.getCurrentUser()).thenReturn(user);
		String actualResult = loginController.login();
		assertEquals("redirect:/user/home", actualResult);
	}

	@Test
	public void loginCurrentUserIsNotExist() throws Exception {
		Mockito.when(securityUtil.getCurrentUser()).thenReturn(null);
		String actualResult = loginController.login();
		assertEquals("login", actualResult);
	}

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

	@Test(expected = NullPointerException.class)
	public void userHomeNullTest() {
		loginController.userHome(null, null);
	}

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

	@Test(expected = NullPointerException.class)
	public void loginFailedNullTest() {
		loginController.loginFailed(null);
	}
}