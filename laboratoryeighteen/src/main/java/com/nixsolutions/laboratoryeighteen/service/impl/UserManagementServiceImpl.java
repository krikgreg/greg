package com.nixsolutions.laboratoryeighteen.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nixsolutions.laboratoryeighteen.dao.RoleDao;
import com.nixsolutions.laboratoryeighteen.dao.UserDao;
import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	public List<UserEntity> findAllUsers() {
		List<UserEntity> users = userDao.findAll();
		return users;
	}

	public void updateUser(UserEntity userEntity) {
		userDao.update(userEntity);
	}

	public void createUser(UserEntity userEntity) {
		userDao.create(userEntity);
	}

	public void removeUser(UserEntity userEntity) {
		userDao.remove(userDao.findUserById(userEntity.getId()));
	}

	public UserEntity getUserModelById(long id) {
		UserEntity userFromBd = userDao.findUserById(id);
		return userFromBd;
	}

	public List<RoleEntity> findAllRoles() {
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles = roleDao.findAll();
		return roles;
	}

	@Override
	public RoleEntity findRoleById(long id) {
		return roleDao.findRoleById(id);
	}

	@Override
	public UserEntity getUserModelByLogin(String login) {
		UserEntity userFromBd = userDao.findByLogin(login);
		if (userFromBd == null) {
			return null;
		}
		return userFromBd;
	}

	@Override
	public UserEntity getUserModelByEmail(String email) {
		UserEntity userFromBd = userDao.findByEmail(email);
		if (userFromBd == null) {
			return null;
		}
		return userFromBd;
	}

	@Override
	public boolean isUserLoginPasswordExist(String login, String password) {
		return userDao.isLoginPasswordExist(login, password);
	}
}