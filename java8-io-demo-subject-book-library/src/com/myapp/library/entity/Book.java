package com.myapp.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author Admin
 *
 */
public class Book implements Serializable {

	/**
	 * Generated id for serialization.
	 */
	private static final long serialVersionUID = -7588888595557161961L;

	private long bookId;

	private String title;

	private double price;

	private int volume;

	private LocalDate publishDate;

	private long subjectId;

	public Book(long bookId, String title, double price, int volume, LocalDate publishDate, long subjectId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + ", subjectId=" + subjectId + "]";
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

}
