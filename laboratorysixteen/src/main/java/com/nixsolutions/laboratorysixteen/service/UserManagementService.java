package com.nixsolutions.laboratorysixteen.service;

import java.util.List;

import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.model.UserModel;


public interface UserManagementService {
	
	public List <UserModel> findAllUsers();
	
	public void updateUser(UserModel userModel);
	
	public void createUser(UserModel userModel);
	
	public void removeUser(UserModel userModel);
	
	public UserModel getUserModelById(long id);
	
	public List<Role> findAllRoles();

	public UserModel getUserModelByLogin(String login);

	public UserModel getUserModelByEmail(String email);

	public boolean isUserLoginPasswordExist(String login, String password);

	public Role findRoleById(long id);
	
}
