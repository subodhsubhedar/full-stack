package com.myapp.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Admin
 *
 */

@Entity
@Table(name = "book_new")
public class Book implements Serializable {

	/**
	 * Generated id for serialization.
	 */
	private static final long serialVersionUID = -7588888595557161961L;

	@Id
	@GeneratedValue
	@Column(name = "bookId", nullable = false)
	private long bookId;

	@NotEmpty(message = "{book.title.invalid}")
	@Column(name = "title")
	private String title;

	@NotNull(message = "{book.price.invalid}")
	@Column(name = "price")
	@Min(message = "{book.price.negativeOrZero}", value = 1)
	private double price;

	@NotNull(message = "{book.volume.invalid}")
	@Min(message = "{book.volume.negativeOrZero}", value = 1)
	@Column(name = "volume")
	private int volume;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{book.publishDate.invalid}")
	@PastOrPresent(message = "{book.publishDate.past}")
	@Column(name = "publishDate")
	private LocalDate publishDate;

	@NotNull(message = "{book.subject.invalid}")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Subject subject;

	public Book(long bookId, String title, double price, int volume, LocalDate publishDate, Subject subjct) {
		super();
		// this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
		this.subject = subjct;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + "]";
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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
