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
public class NativeQueriesTest {

	@Autowired
	private EntityManager entityMngr;

	@Test
	public void findAllCourses() {
		
	
	}
	
	
	@Test
	public void findCourseById() {
		
	
	}
}
