package com.myapp.jpa.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.myapp.jpa.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();

			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setDateOfBirth((rs.getTimestamp("birthdate").toLocalDateTime()));

			return person;
		}

	}

	/**
	 * 
	 * @return
	 */
	public List<Person> findAllPersons() {

		return jdbcTemplate.query("select * from person", new PersonRowMapper());

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Person findPersonById(int id) {

		return jdbcTemplate.queryForObject("select * from person where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int deletePersonById(int id) {

		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	public int insertPerson(Person person) {

		return jdbcTemplate.update("insert into person (id, name, location, birthdate) values (?,?,?,?) ",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getDateOfBirth() });

	}

	public int updatePerson(Person person) {

		return jdbcTemplate.update("update person set name=?,  location=?,  birthdate=? where id=" + person.getId(),
				new Object[] { person.getName(), person.getLocation(), person.getDateOfBirth() });

	}

}
