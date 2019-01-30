package com.myapp.library.menu.service;

import java.io.IOException;
import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.menu.dao.LibraryDao;
import com.myapp.library.menu.dao.LibraryFileDaoImpl;

/**
 * 
 * @author Admin
 *
 */
public class LibraryServiceImpl implements LibraryService {
	private LibraryDao catalogueDao = null;

	public LibraryServiceImpl() {
		catalogueDao = new LibraryFileDaoImpl();
	}

	@Override
	public Set<Subject> findAllSubjects() throws IOException, ClassNotFoundException {
		return catalogueDao.findAllSubjects();
	}

	@Override
	public Set<Book> findAllBooks() throws IOException, ClassNotFoundException {
		return catalogueDao.findAllBooks();
	}

	@Override
	public Book getBook(String bookTitle) throws IOException, ClassNotFoundException {
		return catalogueDao.getBook(bookTitle);
	}

	@Override
	public Subject getSubject(String subjTitle) throws IOException, ClassNotFoundException{
		return catalogueDao.getSubject(subjTitle);
	}

	@Override
	public Book createBook(Book book) throws IOException, ClassNotFoundException {
		return catalogueDao.createBook(book);
	}

	@Override
	public Subject createSubject(Subject subject) throws IOException {
		return catalogueDao.createSubject(subject);
	}

	@Override
	public Book deleteBook(String bookTitle) throws IOException, ClassNotFoundException {
		return catalogueDao.deleteBook(bookTitle);
	}

	@Override
	public Subject deleteSubject(String subjTitle) throws IOException, ClassNotFoundException{
		return catalogueDao.deleteSubject(subjTitle);
	}
}
