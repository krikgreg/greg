package com.nixsolutions.laboratorysixteen.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.laboratorysixteen.dao.UserDao;
import com.nixsolutions.laboratorysixteen.dao.hibernate.HibernateUserDaoImpl;
import com.nixsolutions.laboratorysixteen.entity.User;

@Repository
public class HibernateUserDaoImpl implements UserDao {

	private static final String FIND_USER_BY_LOGIN_PASSWORD_QUERY = "from User where LOGIN = :LOGIN AND PASSWORD = :PASSWORD";
	private static final String FIND_USER_BY_EMAIL_QUERY = "from User where EMAIL = :EMAIL";
	private static final String FIND_USER_BY_LOGIN_QUERY = "from User where LOGIN = :LOGIN";
	private static final String FIND_USERS_QUERY = "from User";
	private static final String FIND_USER_BY_ID_QUERY = "from User where ID = :ID";
	private final static Logger LOG = LoggerFactory.getLogger(HibernateUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void create(User user) {
		if (user == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().save(user);
	}

	@Transactional
	@Override
	public void update(User user) {
		if (user == null) {
			LOG.error("IllegalArgumentException. Role cann't be null");
			throw new IllegalArgumentException();
		}
		sessionFactory.getCurrentSession().update(user);
	}

	@Transactional
	@Override
	public void remove(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional(readOnly = true)
	@Override
	public User findUserById(Long id) {
		User user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_ID_QUERY);
		query.setLong("ID", id);
		user = (User) query.uniqueResult();
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		users = sessionFactory.getCurrentSession().createQuery(FIND_USERS_QUERY).list();
		return users;
	}

	@Transactional(readOnly = true)
	@Override
	public User findByLogin(String login) {
		if (login == null) {
			LOG.error("IllegalArgumentException. Login cann't be null");
			throw new IllegalArgumentException();
		}
		User user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_LOGIN_QUERY);
		query.setString("LOGIN", login);
		user = (User) query.uniqueResult();
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		if (email == null) {
			LOG.error("IllegalArgumentException. Email cann't be null");
			throw new IllegalArgumentException();
		}
		User user = null;
		Query query = sessionFactory.getCurrentSession().createQuery(FIND_USER_BY_EMAIL_QUERY);
		query.setString("EMAIL", email);
		user = (User) query.uniqueResult();
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