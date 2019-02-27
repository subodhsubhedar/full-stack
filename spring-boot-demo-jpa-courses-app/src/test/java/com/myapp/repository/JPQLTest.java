package com.myapp.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myapp.SpringBootDemoJpaCoursesAppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoJpaCoursesAppApplication.class)
public class JPQLTest {

	@Autowired
	private EntityManager entityMngr;

	@Test
	public void findAllCourses() {
		
		System.out.println(entityMngr.createQuery("select c from Course c").getResultList());
	}
	
	
	@Test
	public void findCourseById() {
		
		System.out.println(entityMngr.createQuery("select c from Course c where title like '%AWS%'").getResultList());
	}
}
