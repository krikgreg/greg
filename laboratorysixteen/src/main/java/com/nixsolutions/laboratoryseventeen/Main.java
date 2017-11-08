package com.nixsolutions.laboratoryseventeen;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import com.nixsolutions.laboratoryseventeen.dao.UserDao;
import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;

public class Main {
	
	@Autowired
	private static UserDao userDao;

	public static void main(String[] args) {
//		UserEntity user = new UserEntity();
//		user.setFirstName("Olegsegseg");
//		user.setLastName("Valeg");
//		user.setEmail("denbarsd@mail.com");
//		user.setLogin("Doem");
//		user.setPassword("maxbarss");
//		user.setBirthday(LocalDate.of(1992,05,02));
//		user.setRole(new RoleEntity(1));
//		userDao.create(user);
//		for(User user1 : userDao.findAll()){
//			System.out.println( user1.getLogin().toString());
//			
//		}
//		System.out.println(userDao.findByLogin("Greg").getFirstName());
//		userDao.create(user);
//		Role role = new Role();
//		role.setName("NewRole");
//		RoleDao roleDao = new JdbcRoleDaoImpl();
//		roleDao.create(role);
	}

}
