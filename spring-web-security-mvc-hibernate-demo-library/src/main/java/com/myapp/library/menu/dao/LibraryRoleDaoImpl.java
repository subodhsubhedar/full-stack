package com.myapp.library.menu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.exception.LibraryDaoException;

@Repository
public class LibraryRoleDaoImpl implements LibraryRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public LibraryRole findByRoleName(String name) throws LibraryDaoException {
		LibraryRole role = null;
		try {

			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryRole.class);

			role = (LibraryRole) criteria.add(Restrictions.eq("name", name)).uniqueResult();

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while getting user : " + name, e);
		}

		return role;
	}
	
	
	@Override
	public Set<LibraryRole> findAllRoles() throws LibraryDaoException {
		List<LibraryRole> rolesList;
		try {

			rolesList = sessionFactory.getCurrentSession().createCriteria(LibraryRole.class).list();

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while fetching all roles", e);
		}

		return new LinkedHashSet<LibraryRole>(rolesList);
	}


	@Override
	public LibraryRole findById(Long id) throws LibraryDaoException {

		try {

			return sessionFactory.getCurrentSession().find(LibraryRole.class, id);

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while getting user : " + id, e);
		}

	}

}
