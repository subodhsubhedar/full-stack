package com.myapp.library.menu.service;

import java.util.HashSet;
import java.util.Optional;
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
import com.myapp.library.menu.repository.LibraryRoleJpaRepository;
import com.myapp.library.menu.repository.LibraryUserJpaRepository;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

	@Autowired
	private LibraryUserJpaRepository userRepository;

	@Autowired
	private LibraryRoleJpaRepository roleRepository;

	@Override
	@Transactional
	public LibraryUser findByUsername(String username) throws LibraryServiceException {
		try {
			LibraryUser user = null;
			Optional<LibraryUser> optional = userRepository.findByUsername(username);

			if (optional.isPresent()) {
				user = optional.get();
			} else {
				throw new LibraryDaoException();
			}

			return user;
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findByUsername." + username, e);
		}
	}

	@Override
	@Transactional
	public LibraryUser createUser(LibraryUser user) throws LibraryServiceException {
		try {
			userRepository.save(user);
			
			return user;
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while createUser." + user, e);
		}
	}

	@Override
	@Transactional
	public LibraryUser findById(Long id) throws LibraryServiceException {
		try {
			LibraryUser user = null;
			Optional<LibraryUser> optional = userRepository.findById(id);

			if (optional.isPresent()) {
				user = optional.get();
			} else {
				throw new LibraryDaoException();
			}

			return user;
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findById : " + id, e);
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
			return new HashSet<LibraryRole>(roleRepository.findAll());
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while findAllRoles.", e);
		}
	}

}
