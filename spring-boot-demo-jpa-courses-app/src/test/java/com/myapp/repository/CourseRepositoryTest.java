package com.myapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.myapp.SpringBootDemoJpaCoursesAppApplication;
import com.myapp.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoJpaCoursesAppApplication.class)
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void findById_basic() {
		Course course = courseRepository.findCourseById(10001L);
		assertEquals("Core Java", course.getTitle());
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		courseRepository.deleteCourseById(10001L);

		assertNull(courseRepository.findCourseById(10001L));
	}

	@Test
	@DirtiesContext
	public void createCourse() {
		Course course = new Course("SQL Fundamentals");
		courseRepository.save(course);

	}

	@Test
	@DirtiesContext
	public void updateCourse() {
		Course course = courseRepository.findCourseById(10001L);
		course.setTitle("Core Java plus J2EE");

		courseRepository.save(course);

		assertEquals("Core Java plus J2EE", (courseRepository.findCourseById(10001L).getTitle()));
	}
}
