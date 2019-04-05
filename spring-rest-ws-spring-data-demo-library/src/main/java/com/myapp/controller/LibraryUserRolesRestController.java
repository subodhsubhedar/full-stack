package com.myapp.controller;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.library.entity.LibraryRole;
import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LibraryUserRolesRestController {

	@Autowired
	private LibraryUserService libUserService;

	@GetMapping(value = "/roles")
	public Set<LibraryRole> listAllRoles() throws LibraryServiceException {
		return libUserService.findAllRoles();
	}

	@GetMapping(value = "/user/{username}")
	public LibraryUser getUserByName(@PathVariable @Valid @NotNull String username) throws LibraryServiceException {
		return libUserService.findByUsername(username);
	}

	@PostMapping(value = "/user/add")
	public LibraryUser addNewUser(@Valid @RequestBody LibraryUser user) throws LibraryServiceException {
		return libUserService.createUser(user);
	}

}
