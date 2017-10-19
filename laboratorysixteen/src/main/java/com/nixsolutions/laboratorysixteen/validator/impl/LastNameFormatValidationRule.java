package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class LastNameFormatValidationRule implements Validator {

	private final Pattern pattern = Pattern.compile(Constants.LASTNAME_PATTERN);

	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if ( !pattern.matcher(req.getParameter("lastName")).matches()) {
			errors.add("Format of the last name is not valid, please try again.");
			 }
		return errors;
	}
}