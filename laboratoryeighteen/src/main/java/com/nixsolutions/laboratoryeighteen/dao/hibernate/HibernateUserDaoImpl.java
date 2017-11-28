package com.nixsolutions.laboratoryeighteen.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.laboratoryeighteen.dao.UserDao;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;

@Repository
public class HibernateUserDaoImpl implements UserDao {

	private static final String FIND_USER_BY_LOGIN_PASSWORD_QUERY = "from UserEntity where LOGIN = :LOGIN AND PASSWORD = :PASSWORD";
	private static final String FIND_USER_BY_EMAIL_QUERY = "from UserEntity where EMAIL = :EMAIL";
	private static final String FIND_USER_BY_LOGIN_QUERY = "from UserEntity where LOGIN = :LOGIN";
	private static final String FIND_USERS_QUERY = "from UserEntity";
	private static final String FIND_USER_BY_ID_QUERY = "from UserEntity where ID = :ID";
	private final static Logger LOG = LoggerFactory.getLogger(HibernateUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void create(UserEntity user) {
		if (user == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().save(user);
	}

	@Transactional
	@Override
	public void update(UserEntity user) {
		if (user == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().update(user);
	}

	@Transactional
	@Override
	public void remove(UserEntity user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional(readOnly = true)
	@Override
	public UserEntity findUserById(Long id) {
		UserEntity user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_ID_QUERY);
		query.setLong("ID", id);
		user = (UserEntity) query.uniqueResult();
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		users = sessionFactory.getCurrentSession().createQuery(FIND_USERS_QUERY).list();
		return users;
	}

	@Transactional(readOnly = true)
	@Override
	public UserEntity findByLogin(String login) {
		if (login == null) {
			LOG.error("IllegalArgumentException. Login cann't be null");
			throw new IllegalArgumentException();
		}
		UserEntity user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_LOGIN_QUERY);
		query.setString("LOGIN", login);
		user = (UserEntity) query.uniqueResult();
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public UserEntity findByEmail(String email) {
		if (email == null) {
			LOG.error("IllegalArgumentException. Email cann't be null");
			throw new IllegalArgumentException();
		}
		UserEntity user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_EMAIL_QUERY);
		query.setString("EMAIL", email);
		user = (UserEntity) query.uniqueResult();
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean isLoginPasswordExist(String login, String password) {
		if (password == null || login == null) {
			LOG.error("IllegalArgumentException. User cann't be null");
			throw new IllegalArgumentException();
		}
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_LOGIN_PASSWORD_QUERY);
		query.setString("LOGIN", login);
		query.setString("PASSWORD", password);
		if (query.uniqueResult() == null) {
			return false;
		}
		return true;
	}
}