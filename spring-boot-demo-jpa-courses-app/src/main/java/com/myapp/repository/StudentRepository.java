package com.myapp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Passport;
import com.myapp.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	private EntityManager entMngr;

	public void saveStudentWithPassport(Student student, Passport passport) {

		if (student.getId() == null) {

			entMngr.persist(passport);
			entMngr.persist(student);
		} else {
			entMngr.merge(student);
		}
	}

	public Student findStudentById(Long id) {

		Student student1 = entMngr.find(Student.class, id);
		System.out.println("From Student repo : "+student1.getPassport());

		return student1;
	}

}
