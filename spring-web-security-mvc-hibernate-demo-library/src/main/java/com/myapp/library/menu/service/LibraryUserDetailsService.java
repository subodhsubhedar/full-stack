package com.myapp.library.menu.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.menu.dao.LibraryUserDao;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	private LibraryUserDao libraryUserDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			LibraryUser user = libraryUserDao.findByUsername(username);

			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

			LibraryRole role = user.getRoles();

			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

		} catch (LibraryDaoException e) {
			throw new UsernameNotFoundException("LibraryDaoException occured for :" + username, e);
		}
	}

}
