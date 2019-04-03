package com.myapp.library.menu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.dao.LibraryRoleDao;
import com.myapp.library.menu.dao.LibraryUserDao;
@Service
public class LibraryUserServiceImpl implements LibraryUserService {

	@Autowired
	private LibraryUserDao libraryUserDao;

	@Autowired
	private LibraryRoleDao libraryRoleDao;

	@Override
	@Transactional
	public LibraryUser findByUsername(String username) throws LibraryServiceException {
		try {
			return libraryUserDao.findByUsername(username);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findByUsername." + username, e);
		}
	}

	@Override
	@Transactional
	public void createUser(LibraryUser user) throws LibraryServiceException {
		try {
			libraryUserDao.createUser(user);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while createUser." + user, e);
		}
	}

	@Override
	@Transactional
	public LibraryUser findById(Long id) throws LibraryServiceException {
		try {
			return libraryUserDao.findById(id);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findById." + id, e);
		}
	}

	@Override
	@Transactional
	public String findLoggedInUsername() throws LibraryServiceException {

		Object userDtls = SecurityContextHolder.getContext().getAuthentication().getDetails();

		if (userDtls instanceof UserDetails) {
			return ((UserDetails) userDtls).getUsername();
		}

		return null;
	}

	@Transactional
	public Set<LibraryRole> findAllRoles() throws LibraryServiceException {
		try {
			return libraryRoleDao.findAllRoles();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllRoles.", e);
		}
	}

}
