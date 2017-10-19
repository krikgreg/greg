package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.validator.Validator;

public class PasswordAgainValidationRule implements Validator {

	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if (!req.getParameter("password").equals(req.getParameter("passwordAgain"))) {
			errors.add("The password and password again is not match. Please try again.");
		}
		return errors;
	}
}
