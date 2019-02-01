package com.myapp.library.menu.dao;

import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;

/**
 * 
 * @author Admin
 *
 */
public interface LibraryDao {

	Set<Subject> findAllSubjects() throws LibraryDaoException;

	Set<Book> findAllBooks() throws LibraryDaoException;

	Book getBook(String bookTitle) throws LibraryDaoException;

	Subject getSubject(String subjTitle)  throws LibraryDaoException;

	Book createBook(Book book) throws LibraryDaoException;

	Subject createSubject(Subject subject) throws LibraryDaoException;

	Book deleteBook(String bookTitle) throws LibraryDaoException;

	Subject deleteSubject(String subjTitle)  throws LibraryDaoException;

	Set<Book> getBooksBySubjectId(long subjId) throws LibraryDaoException;

	Subject getSubjectById(long subjId) throws LibraryDaoException;

}
