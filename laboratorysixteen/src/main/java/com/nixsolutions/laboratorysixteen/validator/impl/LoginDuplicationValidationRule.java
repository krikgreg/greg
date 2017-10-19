package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateUserDaoImpl;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class LoginDuplicationValidationRule implements Validator {
	private UserDao userDao = new HibernateUserDaoImpl();
	
	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		if(userDao.findByLogin(req.getParameter("login")) != null) {
			errors.add("There is user with such login in the System. Please fill out another login.");
		}
		return errors;
	}

}
