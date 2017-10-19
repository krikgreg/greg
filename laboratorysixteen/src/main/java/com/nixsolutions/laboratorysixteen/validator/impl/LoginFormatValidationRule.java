package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class LoginFormatValidationRule implements Validator {
	private final Pattern pattern = Pattern.compile(Constants.LOGIN_PATTERN);

	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if ( !pattern.matcher(req.getParameter("login")).matches()) {
			errors.add("Format of the login is not valid, please try again.");
			 }
		return errors;
	}
}
