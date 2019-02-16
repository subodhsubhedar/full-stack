package com.myapp.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Admin
 *
 */
@Entity
@Table(name = "person")
@NamedQuery(name="find_all_persons",query="select p from Person p")
public class Person {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	public Person(String name, String location, LocalDateTime dateOfBirth) {
		super();

		this.name = name;
		this.location = location;
		this.dateOfBirth = dateOfBirth;
	}

	public Person(int id, String name, String location, LocalDateTime dateOfBirth) {
		super();

		this.id = id;
		this.name = name;
		this.location = location;
		this.dateOfBirth = dateOfBirth;
	}

	public Person() {

	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", dateOfBirth=" + dateOfBirth
				+ "]";
	}

	private String location;

	@Column(name="birthdate")
	private LocalDateTime dateOfBirth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
