package com.myapp.library.menu.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryDaoException;

@Repository
public class LibraryUserDaoImpl implements LibraryUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public LibraryUser findByUsername(String username) throws LibraryDaoException {
		LibraryUser user = null;
		try {

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryUser.class);

			user = (LibraryUser) criteria.add(Restrictions.eq("username", username)).uniqueResult();

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while getting user : " + username, e);
		}

		return user;
	}

	@Override
	public void createUser(LibraryUser user) throws LibraryDaoException {

		try {
			user.setPassword(getPasswordEncoder().encode(user.getPassword()));
			
			sessionFactory.getCurrentSession().save(user);

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while adding a new user :" + user, e);
		}
	}

	@Override
	public LibraryUser findById(Long id) throws LibraryDaoException {

		try {

			return sessionFactory.getCurrentSession().find(LibraryUser.class, id);

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while getting user : " + id, e);
		}

	}

}
