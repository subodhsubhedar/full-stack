package com.myapp.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myapp.jpa.entity.Person;
import com.myapp.jpa.jdbc.dao.PersonJdbcDao;
import com.myapp.jpa.repository.PersonJpaRepository;

@SpringBootApplication
public class SpringBootDemoJpaAppApplication implements CommandLineRunner {

	private Log logger = LogFactory.getLog(SpringBootDemoJpaAppApplication.class);

	@Autowired
	private PersonJdbcDao personJdbcDao;

	@Autowired
	private PersonJpaRepository personJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoJpaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Result of find one person query : " + personJpaRepository.findPersonById(10003));

		personJpaRepository.deletePerson(10002);

		Person person = new Person("Vish", "Mumbai", LocalDateTime.now());
		personJpaRepository.insertPerson(person);
		
		
		Person person1 = new Person(1,"Vishwas Sharmaji", "Indore", LocalDateTime.now());
		personJpaRepository.updatePerson(person1);
		
		List<Person> personList = personJpaRepository.findAllPersons();
		
		
	}

	public void runJdbcDao() {
		logger.info("Result of find all persons query : " + personJdbcDao.findAllPersons());

		logger.info("Result of find one person query : " + personJdbcDao.findPersonById(10001));

		logger.info("Result of delete one person query : " + personJdbcDao.deletePersonById(10002));

		Person person = new Person(10004, "Vish", "Mumbai", LocalDateTime.now());
		logger.info("Result of insert one person update : " + personJdbcDao.insertPerson(person));

		Person person1 = new Person(10004, "Vishwas Sharmaji", "Pune", LocalDateTime.now());
		logger.info("Result of update one person : " + personJdbcDao.updatePerson(person1));

	}

}
