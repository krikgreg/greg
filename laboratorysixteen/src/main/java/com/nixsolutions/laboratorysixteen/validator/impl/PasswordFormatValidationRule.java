package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class PasswordFormatValidationRule implements Validator {

private final Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN);

@Override
public List<String> validate(HttpServletRequest req, List<String> errors) {
	if ( !pattern.matcher(req.getParameter("password")).matches()) {
		errors.add("The password must contains one digit, one lowercase characters, one uppercase characters, contains one special symbols '@#$%' - length at least 6 characters and maximum of 20");
		 }
	return errors;
}
}
