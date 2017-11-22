package com.nixsolutions.laboratoryseventeen.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.laboratoryseventeen.config.TestConfig;
import com.nixsolutions.laboratoryseventeen.dao.RoleDao;
import com.nixsolutions.laboratoryseventeen.entity.RoleEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateRoleDaoImplTest {

	private RoleEntity role = null;

	@Autowired
	private RoleDao roleDao;

	@Before
//	@DatabaseSetup("classpath:roledataset.xml")
	public void importDataSet() throws Exception {
		role = new RoleEntity();
		role.setName("Registred");
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void findRoleByNameNullTest() throws Exception {
		roleDao.findByName(null);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	public void findRoleByNameNoSuchRoleTest() throws Exception {
		assertEquals(null, roleDao.findByName(role.getName()));
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void createRoleNullTest() throws Exception {
		roleDao.create(null);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	public void getAllRolesTest() throws Exception {
		List<RoleEntity> roles = roleDao.findAll();
		assertEquals(3, roles.size());
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	@ExpectedDatabase(value = "classpath:createroleexpected.xml", table="ROLES")
	public void createRoleTest() throws Exception {
		roleDao.create(role);
	}

	@Test(expected = RuntimeException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void createRoleWithExistingNameTest() throws Exception {
		role.setName("Doe");
		roleDao.create(role);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void updateRoleNullTest() throws Exception {
		roleDao.update(null);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	@ExpectedDatabase(value = "classpath:updateroleexpected.xml", table="ROLES")
	public void updateRoleTest() throws Exception {
		RoleEntity roleUpdated = new RoleEntity();
		roleUpdated.setId(3);
		roleUpdated.setName("Updated");
		roleDao.update(roleUpdated);
	}

	@Test(expected = NullPointerException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void findRoleByIdNullTest() throws Exception {
		roleDao.findRoleById(null);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	public void findRoleByIdTest() throws Exception {
		RoleEntity roleExpected = new RoleEntity();
		roleExpected.setId(1);
		roleExpected.setName("Doe");
		RoleEntity role = roleDao.findRoleById((long) 1);
		assertEquals(roleExpected, role);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	public void findRoleByIdNoSuchRoleTest() throws Exception {
		assertNull(roleDao.findRoleById((long) 5));
	}

	@Test(expected = HibernateOptimisticLockingFailureException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void updateRoleNoSuchRoleTest() throws Exception {
		role.setId(6);
		roleDao.update(role);
	}

	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup("classpath:roledataset.xml")
	public void deleteNullTest() throws Exception {
		roleDao.remove(null);
	}

	@Test
	@DatabaseSetup("classpath:roledataset.xml")
	@ExpectedDatabase(value = "classpath:removeroleexpected.xml", table="ROLES")
	public void deleteRoleTest() throws Exception {
		RoleEntity roleFromDb = new RoleEntity();
		roleFromDb.setId(3);
		roleFromDb.setName("Mavi");
		roleDao.remove(roleFromDb);
	}
}