package com.myapp.controller.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.library.entity.Book;

@Component
public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.book");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.book");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volume", "NotEmpty.book");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publishDate", "NotEmpty.book");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectId", "NotEmpty.book");

		if (book.getPrice() == 0) {
			errors.rejectValue("price", "NotEmpty.book.price");
		}

		if (book.getVolume() == 0) {
			errors.rejectValue("volume", "NotEmpty.book.volume");
		}
		if (book.getPublishDate() != null && book.getPublishDate().isAfter(LocalDate.now())) {
			errors.rejectValue("publishDate", "invalid.book.publishDate");
		}
		if (book.getSubject().getSubjectId() == 99999) {
			errors.rejectValue("subjectId", "NotEmpty.book.subjectId");
		}
	}

}
