package com.nixsolutions.laboratorysixteen.dao;

import java.util.List;

import com.nixsolutions.laboratorysixteen.entity.User;

public interface UserDao {

	public void create(User user);

	public void update(User user);

	public void remove(User user);

	public List<User> findAll();

	public User findByLogin(String login);

	public User findByEmail(String email);

	public User findUserById(Long id);
	
	public Boolean isLoginPasswordExist(String login, String password);
}
