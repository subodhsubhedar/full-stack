package com.myapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Course;
import com.myapp.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	private EntityManager entMngr;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Course findCourseById(Long id) {

		return entMngr.find(Course.class, id);
	}

	public void deleteCourseById(Long id) {
		entMngr.remove(entMngr.find(Course.class, id));
	}

	public void save(Course course) {

		if (course.getId() == null) {
			entMngr.persist(course);
		} else {
			entMngr.merge(course);
		}
	}

	public List<Review> findReviewsForCourse(Long courseId) {

		Course course = entMngr.find(Course.class, courseId);
		List<Review> reviews = course.getReviews();

		return reviews;
	}

	public Course findCourseForReview(Long reviewId) {

		Review review = entMngr.find(Review.class, reviewId);

		return review.getCourse();
	}

	public void addReviewstoCourse(Long courseId, List<Review> reviews) {

	}
}
