package com.nixsolutions.laboratorysixteen.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
	
	public List<String> validate(HttpServletRequest req, List<String> errors);

}
