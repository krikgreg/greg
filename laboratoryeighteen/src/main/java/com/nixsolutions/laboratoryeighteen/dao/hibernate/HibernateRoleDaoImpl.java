package com.nixsolutions.laboratoryeighteen.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.laboratoryeighteen.dao.RoleDao;
import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;

@Repository
public class HibernateRoleDaoImpl implements RoleDao {
	private static final String FIND_ROLES_QUERY = "from RoleEntity";
	private static final String FIND_ROLE_BY_ID_QUERY = "from RoleEntity where ID_ROLE = :ID";
	private static final String FIND_ROLE_BY_NAME_QUERY = "from RoleEntity where NAME = :NAME";
	private final static Logger LOG = LoggerFactory.getLogger(HibernateRoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void create(RoleEntity role) {
		if (role == null) {
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().save(role);
	}

	@Transactional
	@Override
	public void update(RoleEntity role) {
		if (role == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().update(role);
	}

	@Transactional
	@Override
	public void remove(RoleEntity role) {
		sessionFactory.getCurrentSession().delete(role);
	}

	@Transactional(readOnly = true)
	@Override
	public List<RoleEntity> findAll() {
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles = sessionFactory.getCurrentSession().createQuery(FIND_ROLES_QUERY).list();
		return roles;
	}

	@Transactional(readOnly = true)
	@Override
	public RoleEntity findByName(String name) {
		if (name == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		RoleEntity role = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_ROLE_BY_NAME_QUERY);
		query.setString("NAME", name);
		role = (RoleEntity) query.uniqueResult();
		return role;
	}

	@Transactional(readOnly = true)
	@Override
	public RoleEntity findRoleById(Long id) {
		RoleEntity role = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_ROLE_BY_ID_QUERY);
		query.setLong("ID", id);
		role = (RoleEntity) query.uniqueResult();
		return role;
	}
}