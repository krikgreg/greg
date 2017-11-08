package com.nixsolutions.laboratoryseventeen.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.service.impl.UserManagementServiceImpl;

@Component
public class EmailDuplicationValidator implements Validator {

	@Autowired
	private UserManagementServiceImpl service;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		UserEntity user = (UserEntity) obj;
		UserEntity userFromBd = service.getUserModelByEmail(user.getEmail());
		long id = user.getId();
		if (id == 0) {
			if (userFromBd != null) {
				err.rejectValue("email", "Email.user.duplication");
			}
		} else {
			if (userFromBd != null) {
				if (id != userFromBd.getId()) {
					err.rejectValue("email", "Email.user.duplication");
				}
			}
		}
	}

}
