package com.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myapp.entity.Passport;
import com.myapp.entity.Student;
import com.myapp.repository.CourseRepository;
import com.myapp.repository.StudentRepository;

@SpringBootApplication
public class SpringBootDemoJpaCoursesAppApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoJpaCoursesAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Passport passport = new Passport("W2348723");
		 * 
		 * Student student = new Student("Mahinder"); student.setPassport(passport);
		 * 
		 * studentRepository.saveStudentWithPassport(student, passport);
		 * 
		 * Student student1 = studentRepository.findStudentById(2004L);
		 * System.out.println(student1);
		 */
		//

		System.out.println(courseRepository.findReviewsForCourse(10001L));
		
		System.out.println(courseRepository.findCourseForReview(7001L));

	}

}
