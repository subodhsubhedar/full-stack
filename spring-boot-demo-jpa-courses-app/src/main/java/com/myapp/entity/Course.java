package com.myapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	//this is by default Lazy fetching
	
	@OneToMany(mappedBy = "course",fetch=FetchType.EAGER)
	private List<Review> reviews = new ArrayList<Review>();

	public Course() {

	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

	public Course(String title, List<Review> reviews) {
		super();
		this.title = title;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void deleteReview(Review review) {
		this.reviews.remove(review);
	}

}
