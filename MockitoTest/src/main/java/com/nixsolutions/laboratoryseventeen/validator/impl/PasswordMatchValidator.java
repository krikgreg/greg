package com.nixsolutions.laboratoryseventeen.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;

@Component
public class PasswordMatchValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		UserEntity user = (UserEntity) obj;
		if (!user.getPassword().equals(user.getPasswordAgain())) {
			err.rejectValue("passwordAgain", "PasswordsNotEquals.user.password");
        }
    }
}