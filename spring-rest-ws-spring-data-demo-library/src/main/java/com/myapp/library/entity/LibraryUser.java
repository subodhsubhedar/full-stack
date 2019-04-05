package com.myapp.library.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "library_users")
public class LibraryUser implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1845003476258220485L;

	@Id
	@GeneratedValue
	@Column(name = "user_id", nullable = false)
	private Long id;

	private String username;

	private String password;

	@Transient
	private String confirmPassword;

	@OneToOne
	private LibraryRole roles;

	public LibraryUser() {

	}

	public LibraryUser(String userName, String password, LibraryRole roles) {
		super();
		this.username = userName;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "RegistrationForm [userName=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public LibraryRole getRoles() {
		return roles;
	}

	public void setRoles(LibraryRole roles) {
		this.roles = roles;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
