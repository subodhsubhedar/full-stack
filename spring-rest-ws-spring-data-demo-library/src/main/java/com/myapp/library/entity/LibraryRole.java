package com.myapp.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "library_roles")
public class LibraryRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1633078051169637904L;

	@Id
	@GeneratedValue
	@Column(name = "role_id", nullable = false)
	private Long id;

	@Column(name = "role_name", nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Set<LibraryUser> users;

	public LibraryRole(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public LibraryRole() {

	}

	@Override
	public String toString() {
		return "LibraryRole [id=" + id + ", name=" + name + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<LibraryUser> getUsers() {
		return users;
	}

	public void setUsers(Set<LibraryUser> users) {
		this.users = users;
	}
}
