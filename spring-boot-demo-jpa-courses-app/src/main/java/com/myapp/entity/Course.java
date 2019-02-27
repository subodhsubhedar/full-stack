package com.myapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@CreationTimestamp
	private LocalDateTime creationDateTime;

	@UpdateTimestamp
	private LocalDateTime updationDateTime;

	public Course() {

	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

	public Course(String title) {
		super();
		this.title = title;
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

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getUpdationDateTime() {
		return updationDateTime;
	}

	public void setUpdationDateTime(LocalDateTime updationDateTime) {
		this.updationDateTime = updationDateTime;
	}
}
