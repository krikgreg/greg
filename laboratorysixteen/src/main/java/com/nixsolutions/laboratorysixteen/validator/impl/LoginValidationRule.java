package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class LoginValidationRule implements Validator {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if(userDao.findByLogin(req.getParameter("login")) == null) {
			errors.add("There is no such login in the system, please try again.");
		}
		return errors;
	}
}
