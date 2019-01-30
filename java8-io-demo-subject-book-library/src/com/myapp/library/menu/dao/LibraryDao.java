package com.myapp.library.menu.dao;

import java.io.IOException;
import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;

/**
 * 
 * @author Admin
 *
 */
public interface LibraryDao {

	Set<Subject> findAllSubjects() throws IOException, ClassNotFoundException;

	Set<Book> findAllBooks() throws IOException, ClassNotFoundException;

	Book getBook(String bookTitle) throws IOException, ClassNotFoundException;

	Subject getSubject(String subjTitle)  throws IOException, ClassNotFoundException;

	Book createBook(Book book) throws IOException, ClassNotFoundException;

	Subject createSubject(Subject subject) throws IOException;

	Book deleteBook(String bookTitle) throws IOException, ClassNotFoundException;

	Subject deleteSubject(String subjTitle)  throws IOException, ClassNotFoundException;

}
