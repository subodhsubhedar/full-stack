package com.myapp.library.menu.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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
import com.myapp.library.menu.repository.LibraryUserJpaRepository;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	private LibraryUserJpaRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null || username.isEmpty()) {
			throw new BadCredentialsException("Please enter a valid user name.");
		}

		Optional<LibraryUser> libUser = userRepository.findByUsername(username);
		LibraryUser user = null;

		if (libUser.isPresent()) {
			user = libUser.get();
		} else {
			throw new BadCredentialsException("Username : " + username + " does not exists.");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		LibraryRole role = user.getRoles();

		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
