package com.nixsolutions.laboratoryseventeen.validator.impl;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nixsolutions.laboratoryseventeen.Constants;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;

@Component
public class PasswordFormatValidator implements Validator {
	private final Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN);

	@Override
	public boolean supports(Class<?> clazz) {
		return UserEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		UserEntity user = (UserEntity) obj;
		if ( !pattern.matcher(user.getPassword()).matches()) {
			err.rejectValue("password", "Password.user.format");
		}
		
	}

}
