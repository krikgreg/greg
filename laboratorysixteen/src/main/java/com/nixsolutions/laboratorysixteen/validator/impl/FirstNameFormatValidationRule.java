package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class FirstNameFormatValidationRule implements Validator {

	private final Pattern pattern = Pattern.compile(Constants.FIRSTNAME_PATTERN);

	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if ( !pattern.matcher(req.getParameter("firstName")).matches()) {
			errors.add("Format of the first name is not valid, please try again.");
			 }
		return errors;
	}
}
