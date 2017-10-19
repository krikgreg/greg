package com.nixsolutions.laboratorysixteen;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.entity.User;

public class Main {
	
	@Autowired
	private static UserDao userDao;

	public static void main(String[] args) {
		User user = new User();
		user.setFirstName("Olegsegseg");
		user.setLastName("Valeg");
		user.setEmail("denbarsd@mail.com");
		user.setLogin("Doem");
		user.setPassword("maxbarss");
		user.setBirthday(LocalDate.of(1992,05,02));
		user.setRole(new Role(1));
		userDao.create(user);
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
