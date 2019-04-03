package com.myapp.library.menu.dao;

import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryDaoException;

public interface LibraryUserDao {

	LibraryUser findByUsername(String username) throws LibraryDaoException;

	void createUser(LibraryUser user) throws LibraryDaoException;

	LibraryUser findById(Long id) throws LibraryDaoException;
}
