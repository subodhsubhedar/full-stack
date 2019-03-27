package com.myapp.library.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class LibraryErrorResponse {

	private LocalDateTime dateTime;

	private HttpStatus httpStatus;

	private List<String> errors;

	public LibraryErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
