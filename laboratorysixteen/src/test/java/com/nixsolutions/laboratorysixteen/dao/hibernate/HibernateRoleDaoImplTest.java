//package com.nixsolutions.laboratorysixteen.dao.hibernate;
//
//import static org.junit.Assert.*;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.util.List;
//import org.dbunit.*;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.ITable;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
//import org.dbunit.operation.DatabaseOperation;
//import org.hibernate.StaleStateException;
//import org.hibernate.cfg.Configuration;
//import org.junit.*;
//import com.nixsolutions.laboratorysixteen.dao.RoleDao;
//import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateRoleDaoImpl;
//import com.nixsolutions.laboratorysixteen.entity.Role;
//import com.nixsolutions.laboratorysixteen.util.HibernateSessionFactory;
//
//public class HibernateRoleDaoImplTest {
//
//	private static final String SELECT_ROLES = "SELECT ID_ROLE, NAME FROM ROLES ORDER BY ID_ROLE";
//	private static final Charset UTF8 = null;
//	private IDatabaseTester databaseTester;
//	private RoleDao roleDao = null;
//	private Role role = null;
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
//		roleDao = new HibernateRoleDaoImpl();
//		role = new Role();
//		role.setName("Registred");
//		cleanlyInsert(dataSet);
//	}
//
//	private IDataSet readDataSet() throws Exception {
//		InputStream input = HibernateRoleDaoImplTest.class.getClassLoader().getResourceAsStream("roledataset.xml");
//		return new FlatXmlDataSetBuilder().build(input);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void findRoleByNameNullTest() throws Exception {
//		
//		roleDao.findByName(null);
//	}
//
//	@Test
//	public void findRoleByNameNoSuchRoleTest() throws Exception {
//		assertEquals(null , roleDao.findByName(role.getName()));
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void createRoleNullTest() throws Exception {
//		roleDao.create(null);
//	}
//	
//	@Test
//	public void getAllRolesTest() throws Exception {
//		List<Role> users = roleDao.findAll();
//		ITable expeted = databaseTester.getConnection().createQueryTable("USERS", SELECT_ROLES);
//		assertEquals(expeted.getRowCount(), users.size());
//	}
//
//	@Test
//	public void createRoleTest() throws Exception {
//		InputStream input = HibernateRoleDaoImplTest.class.getClassLoader().getResourceAsStream("createroleexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("ROLES");
//		roleDao.create(role);
//		ITable actualData = databaseTester.getConnection().createQueryTable("ROLES", SELECT_ROLES);
//		String[] ignore = { "ID_ROLE" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//
//	@Test(expected = RuntimeException.class)
//	public void createRoleWithExistingNameTest() throws Exception {
//		role.setName("Doe");
//		roleDao.create(role);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void updateRoleNullTest() throws Exception {
//		roleDao.update(null);
//	}
//	
//	@Test
//	public void updateRoleTest() throws Exception {
//		role.setId(2);
//		InputStream input = HibernateRoleDaoImplTest.class.getClassLoader().getResourceAsStream("updateroleexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("ROLES");
//		roleDao.update(role);
//		ITable actualData = databaseTester.getConnection().createQueryTable("ROLES", SELECT_ROLES);
//		String[] ignore = { "ID_ROLE" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//	
//	@Test(expected = NullPointerException.class)
//	public void findRoleByIdNullTest() throws Exception {
//		roleDao.findRoleById(null);
//	}
//	
//	@Test
//	public void findRoleByIdTest() throws Exception {
//		Role roleExpected = new Role();
//		roleExpected.setId(1);
//		roleExpected.setName("Doe");
//		Role role = roleDao.findRoleById((long) 1);
//		assertEquals(roleExpected, role);
//	}
//	
//	@Test
//	public void findRoleByIdNoSuchRoleTest() throws Exception {
//		assertNull(roleDao.findRoleById((long) 3));
//	}
//	
//	@Test(expected = StaleStateException.class)
//	public void updateRoleNoSuchRoleTest() throws Exception {
//		role.setId(3);
//		roleDao.update(role);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void deleteNullTest() throws Exception {
//		roleDao.remove(null);
//	}
//
//	@Test
//	public void deleteRoleTest() throws Exception {
//		Role roleFromDb = new Role();
//		roleFromDb.setId(1);
//		roleFromDb.setName("Doe");
//		InputStream input = HibernateRoleDaoImplTest.class.getClassLoader().getResourceAsStream("removeroleexpected.xml");
//		ITable expectedData = new FlatXmlDataSetBuilder().build(input).getTable("ROLES");
//		roleDao.remove(roleFromDb);
//		ITable actualData = databaseTester.getConnection().createQueryTable("ROLES", SELECT_ROLES);
//		String[] ignore = { "ID_ROLE" };
//		Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignore);
//	}
//
//	private void cleanlyInsert(IDataSet dataSet) throws Exception {
//		databaseTester = new JdbcDatabaseTester(configuration.getProperty("connection.driver_class"), configuration.getProperty("connection.url"),
//				configuration.getProperty("connection.username"), configuration.getProperty("connection.password"));
//		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
//		databaseTester.setDataSet(dataSet);
//		databaseTester.onSetup();
//	}
//}