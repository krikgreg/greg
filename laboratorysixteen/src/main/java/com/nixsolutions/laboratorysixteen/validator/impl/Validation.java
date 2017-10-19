package com.nixsolutions.laboratorysixteen.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;

@Service
public class Validation {

	@Autowired	
	private UserManagementServiceImpl service;
	
    public void checkLogginForAddUser(UserModel userBean,
            BindingResult result) {
    	UserModel user = service.getUserModelByLogin(userBean.getLogin());
        if (user != null) {
            result.rejectValue("login", "LoginIs.user.login");
        }
    }

    public void checkEmailForAddUser(UserModel userBean,
            BindingResult result) {
    	UserModel user = service.getUserModelByEmail(userBean.getEmail());
        if (user != null) {
            result.rejectValue("email", "EmailIs.user.email");
        }
    }

    public void checkEmailForEditUser(UserModel userBean, BindingResult result) {
    	UserModel user = service.getUserModelByEmail(userBean.getEmail());
        if (user != null && user.getId() != userBean.getId()) {
            result.rejectValue("email", "EmailIs.user.email");
        }
    }
    
    public void checkLoginPassword(String login, String password, BindingResult result){
    	if(!service.isUserLoginPasswordExist(login, password)){
    		result.rejectValue("login", "WrongCredential.user.login");
    	}
    }

    public void checkPasswords(UserModel userBean, BindingResult result) {
        if (!userBean.getPassword().equals(userBean.getPasswordAgain())) {
            result.rejectValue("password", "PasswordsNotEquals.user.password");
        }
    }

}
