package com.nixsolutions.laboratoryeighteen.dao;

import java.util.List;

import com.nixsolutions.laboratoryeighteen.entity.UserEntity;

public interface UserDao {

	public void create(UserEntity user);

	public void update(UserEntity user);

	public void remove(UserEntity user);

	public List<UserEntity> findAll();

	public UserEntity findByLogin(String login);

	public UserEntity findByEmail(String email);

	public UserEntity findUserById(Long id);
	
	public Boolean isLoginPasswordExist(String login, String password);
}
