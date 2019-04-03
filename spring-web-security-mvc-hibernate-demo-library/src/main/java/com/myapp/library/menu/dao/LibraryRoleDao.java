package com.myapp.library.menu.dao;

import java.util.Set;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.exception.LibraryDaoException;

public interface LibraryRoleDao {

	LibraryRole findByRoleName(String name) throws LibraryDaoException;

	LibraryRole findById(Long id) throws LibraryDaoException;

	Set<LibraryRole> findAllRoles() throws LibraryDaoException;
}
