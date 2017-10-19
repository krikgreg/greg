package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateUserDaoImpl;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class PasswordValidationRule implements Validator {
	private UserDao userDao = new HibernateUserDaoImpl();
	
	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if(!userDao.isLoginPasswordExist(req.getParameter("login"), req.getParameter("password"))) {
			errors.add("The password is invalid, please try again.");
		}
		return errors;
	}
}
