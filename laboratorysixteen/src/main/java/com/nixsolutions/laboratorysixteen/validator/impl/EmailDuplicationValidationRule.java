package com.nixsolutions.laboratorysixteen.validator.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateUserDaoImpl;
import com.nixsolutions.laboratorysixteen.entity.User;
import com.nixsolutions.laboratorysixteen.validator.Validator;

public class EmailDuplicationValidationRule implements Validator {
	private UserDao userDao = new HibernateUserDaoImpl();

	@Override
	public List<String> validate(HttpServletRequest req, List<String> errors) {
		String id = req.getParameter("id");
		User userByEmail = userDao.findByEmail(req.getParameter("email"));
		if (id == null){
			if(userByEmail != null) {
				errors.add("There is user with such email in the System. Please fill out another email.");
			}
		}else{
			if(userByEmail != null) {
				if (Long.parseLong(id) != userByEmail.getId()) {
				errors.add("There is user with such email in the System. Please fill out another email.");
			}
		}
	}
		return errors;
	}
}
