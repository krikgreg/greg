package com.nixsolutions.laboratoryeighteen.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.laboratoryeighteen.config.TestConfig;
import com.nixsolutions.laboratoryeighteen.dao.UserDao;
import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateUserDaoImplTest {

	private UserEntity user = null;
	private UserEntity userExpected = null;

	@Autowired
	private UserDao userDao;

	@Before
	public void importDataSet() throws Exception {
		RoleEntity role = new RoleEntity();
		role.setId(1);
		role.setName("Doe");
		user = new UserEntity();
		user.setFirstName("dfdfb");
		user.setLastName("dbrdrv");
		user.setEmail("denbars@mail.com");
		user.setLogin("Doe");
		user.setPassword("qW#4qw");
		user.setPasswordAgain("qW#4qw");
		user.setBirthday("1992-07-02");
		user.setRole(role);
		userExpected = new UserEntity();
		userExpected.setId(1);
		userExpected.setFirstName("Doe");
		userExpected.setLastName("DoeSecondName");
		userExpected.setEmail("DoeUser@gmail.com");
		userExpected.setLogin("DoeUser");
		userExpected.setPassword("qW#4qw");
		userExpected.setPasswordAgain("qW#4qw");
		userExpected.setBirthday("1992-07-02");
		userExpected.setRole(role);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void getAllUsersTest() throws Exception {
		List<UserEntity> users = userDao.findAll();
		assertEquals(2, users.size());
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void createUserNullTest() throws Exception {
		userDao.create(null);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	@ExpectedDatabase(value = "classpath:createuserexpected.xml", table="USERS")
	public void createUserTest() throws Exception {
		userDao.create(user);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void updateUserNullTest() throws Exception {
		userDao.update(null);
	}

	@Test(expected = HibernateOptimisticLockingFailureException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void updateUserNoSuchUserTest() throws Exception {
		user.setId(5);
		userDao.update(user);
	}

	@Test(expected = NullPointerException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void findRoleByIdNullTest() throws Exception {
		userDao.findUserById(null);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void findRoleByIdTest() throws Exception {
		UserEntity user = userDao.findUserById((long) 1);
		assertEquals(userExpected, user);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void isPasswordExistTest() throws Exception {
		assertTrue(userDao.isLoginPasswordExist(userExpected.getLogin(), userExpected.getPassword()));
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void isPasswordExistFalseTest() throws Exception {
		assertFalse(userDao.isLoginPasswordExist(user.getLogin(), user.getPassword()));
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void findRoleByIdNoSuchRoleTest() throws Exception {
		assertNull(userDao.findUserById((long) 3));
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	@ExpectedDatabase(value = "classpath:updateuserexpected.xml", table="USERS")
	public void updateUserTest() throws Exception {
		user.setId(2);
		userDao.update(user);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByLoginNullTest() throws Exception {
		userDao.findByLogin(null);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByLoginNoSuchLoginTest() throws Exception {
		assertEquals(null, userDao.findByLogin(user.getLogin()));
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByLoginTest() throws Exception {
		UserEntity userFromBd = userDao.findByLogin(userExpected.getLogin());
		assertEquals(userExpected, userFromBd);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByEmailNullTest() throws Exception {
		userDao.findByEmail(null);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByEmailNoSuchEmailTest() throws Exception {
		assertEquals(null, userDao.findByLogin(user.getLogin()));
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	public void getUserByEmailTest() throws Exception {
		UserEntity userFromBd = userDao.findByEmail(userExpected.getEmail());
		assertEquals(userExpected, userFromBd);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:userdataset.xml")
	public void deleteUserNullTest() throws Exception {
		userDao.remove(null);
	}

	@Test
	@DatabaseSetup("classpath:userdataset.xml")
	@ExpectedDatabase(value = "classpath:removeuserexpected.xml", table="USERS")
	public void deleteUserTest() throws Exception {
		userDao.remove(userExpected);
	}
}