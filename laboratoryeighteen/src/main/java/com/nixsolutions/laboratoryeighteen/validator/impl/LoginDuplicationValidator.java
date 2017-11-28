package com.nixsolutions.laboratoryeighteen.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.impl.UserManagementServiceImpl;

@Component
public class LoginDuplicationValidator implements Validator {
	
	@Autowired
	private UserManagementServiceImpl service;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		UserEntity user = (UserEntity) obj;
		UserEntity userFromDB = service.getUserModelByLogin(user.getLogin());
        if (userFromDB != null) {
        	err.rejectValue("login", "Login.user.login");
        }
	}
}
