package com.myapp.library.menu.service;

import java.util.Set;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryServiceException;

public interface LibraryUserService {

	LibraryUser findByUsername(String username) throws LibraryServiceException;

	void createUser(LibraryUser user) throws LibraryServiceException;

	LibraryUser findById(Long id) throws LibraryServiceException;

	String findLoggedInUsername() throws LibraryServiceException;
	
	Set<LibraryRole> findAllRoles() throws LibraryServiceException;
}
