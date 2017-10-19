package com.nixsolutions.laboratorysixteen.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.laboratorysixteen.dao.RoleDao;
import com.nixsolutions.laboratorysixteen.entity.Role;

@Repository
public class HibernateRoleDaoImpl implements RoleDao {
	private static final String FIND_ROLES_QUERY = "from Role";
	private static final String FIND_ROLE_BY_ID_QUERY = "from Role where ID_ROLE = :ID";
	private static final String FIND_ROLE_BY_NAME_QUERY = "from Role where NAME = :NAME";
	private final static Logger LOG = LoggerFactory.getLogger(HibernateRoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void create(Role role) {
		if (role == null) {
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().save(role);
	}

	@Transactional
	@Override
	public void update(Role role) {
		if (role == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().update(role);
	}

	@Transactional
	@Override
	public void remove(Role role) {
		sessionFactory.getCurrentSession().delete(role);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<Role>();
		roles = sessionFactory.getCurrentSession().createQuery(FIND_ROLES_QUERY).list();
		return roles;
	}

	@Transactional(readOnly = true)
	@Override
	public Role findByName(String name) {
		if (name == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		Role role = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_ROLE_BY_NAME_QUERY);
		query.setString("NAME", name);
		role = (Role) query.uniqueResult();
		return role;
	}

	@Transactional(readOnly = true)
	@Override
	public Role findRoleById(Long id) {
		Role role = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_ROLE_BY_ID_QUERY);
		query.setLong("ID", id);
		role = (Role) query.uniqueResult();
		return role;
	}
}