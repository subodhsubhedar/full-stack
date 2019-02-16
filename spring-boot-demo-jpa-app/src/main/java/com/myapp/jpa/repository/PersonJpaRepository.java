package com.myapp.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.myapp.jpa.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * @return
	 */
	public List<Person> findAllPersons() {

		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Person findPersonById(int id) {

		return entityManager.find(Person.class, id);

	}

	/**
	 * 
	 * @param id
	 */
	public void deletePerson(int id) {

		entityManager.remove(findPersonById(id));

	}

	/**
	 * 
	 * @param person
	 * @return
	 */
	public Person updatePerson(Person person) {

		return entityManager.merge(person);
	}

	/**
	 * 
	 * @param person
	 * @return
	 */
	public Person insertPerson(Person person) {

		return entityManager.merge(person);
	}

}
