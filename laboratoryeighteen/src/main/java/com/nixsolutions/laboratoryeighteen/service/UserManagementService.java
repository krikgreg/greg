package com.nixsolutions.laboratoryeighteen.service;

import java.util.List;
import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;


public interface UserManagementService {
	
	public List <UserEntity> findAllUsers();
	
	public void updateUser(UserEntity user);
	
	public void createUser(UserEntity user);
	
	public void removeUser(UserEntity user);
	
	public UserEntity getUserModelById(long id);
	
	public List<RoleEntity> findAllRoles();

	public UserEntity getUserModelByLogin(String login);

	public UserEntity getUserModelByEmail(String email);

	public boolean isUserLoginPasswordExist(String login, String password);

	public RoleEntity findRoleById(long id);
	
}
