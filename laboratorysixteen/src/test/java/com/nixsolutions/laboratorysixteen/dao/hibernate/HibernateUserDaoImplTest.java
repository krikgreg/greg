//package com.nixsolutions.laboratorysixteen.dao.hibernate;
//
//import static org.junit.Assert.*;
//
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.dbunit.Assertion;
//import org.dbunit.DBTestCase;
//import org.dbunit.IDatabaseTester;
//import org.dbunit.JdbcDatabaseTester;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.ITable;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
//import org.dbunit.operation.DatabaseOperation;
//import org.hibernate.StaleStateException;
//import org.hibernate.cfg.Configuration;
//import org.junit.*;
//import com.nixsolutions.laboratorysixteen.dao.UserDao;
//import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateUserDaoImpl;
//import com.nixsolutions.laboratorysixteen.entity.Role;
//import com.nixsolutions.laboratorysixteen.entity.User;
//import com.nixsolutions.laboratorysixteen.util.HibernateSessionFactory;
//
//public class HibernateUserDaoImplTest extends DBTestCase {
//
//	private static final String SELECT_ALL_USERS = "SELECT ID, AGE, BIRTHDAY, EMAIL, FIRSTNAME, LASTNAME, ID_ROLE, LOGIN, PASSWORD FROM USERS ORDER BY ID";
//	private static final Charset UTF8 = null;
//	private IDatabaseTester databaseTester;
//	private UserDao userDao = null;
//	private User user = null;
//	private User userExpected = null;
//	private static Configuration configuration;
//
//	@BeforeClass
//	public static void createSchema() throws Exception {
//		configuration = new Configuration().configure("hibernate.cfg.xml");
//		HibernateSessionFactory.getSessionFactory();
//	}
//
//	@Before
//	public void importDataSet() throws Exception {
//		IDataSet dataSet = readDataSet();
////		cleanlyInsert(dataSet);
//		Role role = new Role();
//		role.setId(1);
//		role.setName("Doe");
//		userDao = new HibernateUserDaoImpl();
//		user = new User();
//		user.setFirstName("dfdfb");
//		user.setLastName("dbrdrv");
//		user.setEmail("denbars@mail.com");
//		user.setLogin("Doe");
//		user.setPassword("maxbars");
//		user.setBirthday(LocalDate.of(1992,07,02));
//		user.setRole(role);
//		userExpected = new User();
//		userExpected.setId(1);
//		userExpected.setFirstName("Doe");
//		userExpected.setLastName("DoeSecondName");
//		userExpected.setEmail("DoeUser@gmail.com");
//		userExpected.setLogin("DoeUser");
//		userExpected.setPassword("12345");
//		userExpected.setBirthday(LocalDate.of(1992,07,02));
//		userExpected.setRole(role);
//	}
//	
//	@Test
//	public void getAllUsersTest() throws Exception {
//		List<User> users = userDao.findAll();
//		ITable expeted = databaseTester.getConnection().createQueryTable("USERS", SELECT_ALL_USERS);
//		assertEquals(expeted.getRowCount(), users.size());
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void createUserNullTest() throws Exception {
//		userDao.create(null);
//	}
//
//	@Test
//	public void createUserTest() throws Exception {
//		InputStream input = HibernateUserDaoImplTest.class.getClassLoader().getResourceAsStream("createuserexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("USERS");
//		userDao.create(user);
//		ITable actualData = databaseTester.getConnection().createQueryTable("USERS", SELECT_ALL_USERS);
//		String[] ignore = { "ID" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void updateUserNullTest() throws Exception {
//		userDao.update(null);
//	}
//
//	@Test(expected = StaleStateException.class)
//	public void updateUserNoSuchUserTest() throws Exception {
//		user.setId(5);
//		userDao.update(user);
//	}
//	
//	@Test(expected = NullPointerException.class)
//	public void findRoleByIdNullTest() throws Exception {
//		userDao.findUserById(null);
//	}
//	
//	@Test
//	public void findRoleByIdTest() throws Exception {
//		User user = userDao.findUserById((long) 1);
//		assertEquals(userExpected, user);
//	}
//	
//	@Test
//	public void isPasswordExistTest() throws Exception {
//		assertTrue(userDao.isPasswordExist(userExpected.getLogin(), userExpected.getPassword()));
//	}
//	
//	@Test
//	public void isPasswordExistFalseTest() throws Exception {
//		assertFalse(userDao.isPasswordExist(user.getLogin(), user.getPassword()));
//	}
//	
//	@Test
//	public void findRoleByIdNoSuchRoleTest() throws Exception {
//		assertNull(userDao.findUserById((long) 3));
//	}
//
//	@Test
//	public void updateUserTest() throws Exception {
//		user.setId(2);
//		InputStream input = HibernateUserDaoImplTest.class.getClassLoader().getResourceAsStream("updateuserexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("USERS");
//		userDao.update(user);
//		ITable actualData = databaseTester.getConnection().createQueryTable("USERS", SELECT_ALL_USERS);
//		String[] ignore = { "id" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void getUserByLoginNullTest() throws Exception {
//		userDao.findByLogin(null);
//	}
//
//	@Test
//	public void getUserByLoginNoSuchLoginTest() throws Exception {
//		assertEquals(null, userDao.findByLogin(user.getLogin()));
//	}
//
//	@Test
//	public void getUserByLoginTest() throws Exception {
//		User userFromBd = userDao.findByLogin(userExpected.getLogin());
//		assertEquals(userExpected, userFromBd);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void getUserByEmailNullTest() throws Exception {
//		userDao.findByEmail(null);
//	}
//
//	@Test
//	public void getUserByEmailNoSuchEmailTest() throws Exception {
//		assertEquals(null, userDao.findByLogin(user.getLogin()));
//	}
//
//	@Test
//	public void getUserByEmailTest() throws Exception {
//		User userFromBd = userDao.findByEmail(userExpected.getEmail());
//		assertEquals(userExpected, userFromBd);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void deleteUserNullTest() throws Exception {
//		userDao.remove(null);
//	}
//
//	@Test
//	public void deleteUserTest() throws Exception {
//		InputStream input = HibernateUserDaoImplTest.class.getClassLoader().getResourceAsStream("removeuserexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("USERS");
//		userDao.remove(userExpected);
//		ITable actualData = databaseTester.getConnection().createQueryTable("USERS", SELECT_ALL_USERS);
//		String[] ignore = { "id" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//
////	private void cleanlyInsert(IDataSet dataSet) throws Exception {
////		databaseTester = new JdbcDatabaseTester(configuration.getProperty("connection.driver_class"), configuration.getProperty("connection.url"),
////				configuration.getProperty("connection.username"), configuration.getProperty("connection.password"));
////		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
////		databaseTester.setDataSet(dataSet);
////		databaseTester.onSetup();
////	}
//
//	private IDataSet readDataSet() throws Exception {
//		InputStream input = HibernateUserDaoImplTest.class.getClassLoader().getResourceAsStream("userdataset.xml");
//		return new FlatXmlDataSetBuilder().build(input);
//	}
//
//	@Override
//	protected IDataSet getDataSet() throws Exception {
//		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("userdataset.xml"));
//	}
//}