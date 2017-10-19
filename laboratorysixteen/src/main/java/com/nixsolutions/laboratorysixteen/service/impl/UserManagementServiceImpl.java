package com.nixsolutions.laboratorysixteen.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.laboratorysixteen.dao.RoleDao;
import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.entity.User;
import com.nixsolutions.laboratorysixteen.model.UserModel;
import com.nixsolutions.laboratorysixteen.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public List<UserModel> findAllUsers() {
		List<UserModel> userModelList = new ArrayList<UserModel>();
		UserModel userModel = null;
		List<User> users = userDao.findAll();
		for (User user : users) {
			userModel = new UserModel();
			userModel.setId(user.getId());
			userModel.setLogin(user.getLogin());
			userModel.setFirstName(user.getFirstName());
			userModel.setLastName(user.getLastName());
			userModel.setAge(user.getAge());
			userModel.setRole(user.getRole());
			userModelList.add(userModel);
		}
		return userModelList;
	}

	public void updateUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		user.setPassword(userModel.getPassword());
		LocalDate localDate = LocalDate.parse(userModel.getBirthday(), formatter);
		user.setBirthday(localDate);
		user.setRole(userModel.getRole());
		userDao.update(user);
	}

	public void createUser(UserModel userModel) {
		User user = new User();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		user.setPassword(userModel.getPassword());
		LocalDate localDate = LocalDate.parse(userModel.getBirthday(), formatter);
		user.setBirthday(localDate);
		user.setRole(userModel.getRole());
		userDao.create(user);
	}

	public void removeUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		userDao.remove(userDao.findUserById(user.getId()));
	}

	public UserModel getUserModelById(long id) {
		User userFromBd = userDao.findUserById(id);
		UserModel userModel = new UserModel();
		userModel.setId(userFromBd.getId());
		userModel.setFirstName(userFromBd.getFirstName());
		userModel.setLastName(userFromBd.getLastName());
		userModel.setLogin(userFromBd.getLogin());
		userModel.setEmail(userFromBd.getEmail());
		userModel.setPassword(userFromBd.getPassword());
		String localDateAsString = userFromBd.getBirthday().format(formatter);
		userModel.setBirthday(localDateAsString);
		userModel.setRole(userFromBd.getRole());
		return userModel;
	}

	public List<Role> findAllRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles = roleDao.findAll();
		return roles;
	}
	
	@Override
	public Role findRoleById(long id) {
		return roleDao.findRoleById(id);
	}

	@Override
	public UserModel getUserModelByLogin(String login) {
		User userFromBd = userDao.findByLogin(login);
		if (userFromBd == null) {
			return null;
		}
		UserModel userModel = new UserModel();
		userModel.setId(userFromBd.getId());
		userModel.setFirstName(userFromBd.getFirstName());
		userModel.setLastName(userFromBd.getLastName());
		userModel.setLogin(userFromBd.getLogin());
		userModel.setEmail(userFromBd.getEmail());
		userModel.setPassword(userFromBd.getPassword());
		String localDateAsString = userFromBd.getBirthday().format(formatter);
		userModel.setBirthday(localDateAsString);
		userModel.setRole(userFromBd.getRole());
		return userModel;
	}
	
	
	@Override
	public UserModel getUserModelByEmail(String email) {
		User userFromBd = userDao.findByEmail(email);
		if (userFromBd == null) {
			return null;
		}
		UserModel userModel = new UserModel();
		userModel.setId(userFromBd.getId());
		userModel.setFirstName(userFromBd.getFirstName());
		userModel.setLastName(userFromBd.getLastName());
		userModel.setLogin(userFromBd.getLogin());
		userModel.setEmail(userFromBd.getEmail());
		userModel.setPassword(userFromBd.getPassword());
		String localDateAsString = userFromBd.getBirthday().format(formatter);
		userModel.setBirthday(localDateAsString);
		userModel.setRole(userFromBd.getRole());
		return userModel;
	}
	
	@Override
	public boolean isUserLoginPasswordExist(String login, String password){
		return userDao.isLoginPasswordExist(login, password);
	}
}