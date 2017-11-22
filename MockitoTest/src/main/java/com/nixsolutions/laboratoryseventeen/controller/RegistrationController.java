package com.nixsolutions.laboratoryseventeen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.RecaptchaService;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;
import com.nixsolutions.laboratoryseventeen.util.SecurityUtil;
import com.nixsolutions.laboratoryseventeen.validator.impl.EmailDuplicationValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.LoginDuplicationValidator;
import com.nixsolutions.laboratoryseventeen.validator.impl.PasswordMatchValidator;

@Controller
public class RegistrationController {

	@Autowired
	private RecaptchaService recaptchaService;

	@Autowired
	private EmailDuplicationValidator emailValidator;

	@Autowired
	private PasswordMatchValidator passwordValidator;

	@Autowired
	private LoginDuplicationValidator loginValidator;

	@Autowired
	private UserManagementServiceImpl userManagementService;

	@RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
	public String registrationForm(ModelMap model) {
		UserEntity user = new UserEntity();
		List<RoleEntity> roles = userManagementService.findAllRoles();
		setRolesUserInModel(user, model, roles);
		return "registration";
	}

	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
		binder.addValidators(emailValidator);
		binder.addValidators(passwordValidator);
	}

	@ModelAttribute("recaptchaSiteKey")
	public String getRecaptchaSiteKey(@Value("${recaptcha.site-key}") String recaptchaSiteKey) {
		return recaptchaSiteKey;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrateUser(@ModelAttribute("user") @Valid UserEntity user, BindingResult result, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "g-recaptcha-response") String reCaptchaResponse) {
		List<RoleEntity> roles = userManagementService.findAllRoles();
		boolean isValid = recaptchaService.validateCaptcha(getRemoteIp(request), reCaptchaResponse);
		if (result.hasErrors()) {
			setRolesUserInModel(user, model, roles);
			model.addAttribute("errors", result.getAllErrors());
			return "registration";
		}
		if (!isValid) {
			model.addAttribute("errorMessage", "The Captcha is missing or wrong");
			setRolesUserInModel(user, model, roles);
			return "registration";
		}
		userManagementService.createUser(user);
		SecurityUtil.authentificate(user);
		return "redirect:/login";
	}

	private void setRolesUserInModel(UserEntity user, ModelMap model, List<RoleEntity> roles) {
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
	}

	private String getRemoteIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}