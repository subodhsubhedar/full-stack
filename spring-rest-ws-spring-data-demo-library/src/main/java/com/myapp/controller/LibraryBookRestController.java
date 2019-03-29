package com.myapp.controller;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LibraryBookRestController {

	@Autowired
	private LibraryService catalogueService;

	@GetMapping(value = "/books")
	public Set<Book> listAllBooks() throws LibraryServiceException {
		return catalogueService.findAllBooks();
	}

	@GetMapping(value = "/subjects")
	public Set<Subject> listAllSubjects() throws LibraryServiceException {
		return catalogueService.findAllSubjects();
	}
	
	@GetMapping(value = "/book/{bookId}")
	public Book getBook(@PathVariable @Valid @NotNull Long bookId) throws LibraryServiceException {

		return catalogueService.getBookById(bookId);
	}

	@PostMapping(value = "/book/add")
	public Book addNewBook(@Valid @RequestBody Book book) throws LibraryServiceException {
		return catalogueService.createBook(book);
	}

	@PutMapping(value = "/book/update")
	public Book updateBook(@Valid @RequestBody Book book) throws LibraryServiceException {
		return catalogueService.updateBook(book);
	}

	@DeleteMapping(value = "/book/delete/{bookId}")
	public void deleteBook(@PathVariable @Valid @NotNull Long bookId) throws LibraryServiceException {
		catalogueService.deleteBookById(bookId);
	}

}
