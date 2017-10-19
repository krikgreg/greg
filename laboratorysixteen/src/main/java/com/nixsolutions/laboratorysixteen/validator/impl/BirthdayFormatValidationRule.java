package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.validator.Validator;


@Component
public class BirthdayFormatValidationRule implements Validator {
	private final Pattern pattern = Pattern.compile(Constants.DATE_PATTERN);


	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if ( !pattern.matcher(req.getParameter("birthday")).matches()) {
			errors.add("Format of the birthday date is not valid, please try again.");
			 }
		return errors;
	}
}
