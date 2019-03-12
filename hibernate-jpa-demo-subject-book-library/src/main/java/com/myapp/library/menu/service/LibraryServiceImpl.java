package com.myapp.library.menu.service;

import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.dao.LibraryDao;
import com.myapp.library.menu.dao.LibraryHibernateDaoImpl;
import com.myapp.library.menu.dao.LibraryJpaRepository;

/**
 * 
 * @author Admin
 *
 */
public class LibraryServiceImpl implements LibraryService {
	private LibraryDao catalogueDao = null;

	public LibraryServiceImpl() {
		// catalogueDao = new LibraryHibernateDaoImpl();

		catalogueDao = new LibraryJpaRepository();
	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryServiceException {
		try {

			return catalogueDao.findAllSubjects();

		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving all subjects.", e);
		}
	}

	@Override
	public Set<Book> findAllBooks() throws LibraryServiceException {
		try {
			return catalogueDao.findAllBooks();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving all books.", e);
		}

	}

	@Override
	public Book getBook(String bookTitle) throws LibraryServiceException {
		try {
			return catalogueDao.getBook(bookTitle);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving book :" + bookTitle, e);
		}

	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryServiceException {
		try {

			return catalogueDao.getSubject(subjTitle);

		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving subject :" + subjTitle, e);
		}

	}

	@Override
	public Book createBook(Book book) throws LibraryServiceException {
		try {
			return catalogueDao.createBook(book);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while creating book :" + book, e);
		}

	}

	@Override
	public Subject createSubject(Subject subject) throws LibraryServiceException {
		try {
			return catalogueDao.createSubject(subject);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while creating subject :" + subject, e);
		}

	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryServiceException {
		try {
			return catalogueDao.deleteBook(bookTitle);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while deleting book :" + bookTitle, e);
		}

	}

	@Override
	public Subject deleteSubject(String subjTitle) throws LibraryServiceException {
		try {

			Subject subject = this.getSubject(subjTitle);

			if (subject.getReferences() == null || subject.getReferences().isEmpty()) {
				return catalogueDao.deleteSubject(subjTitle);
			} else {
				throw new LibraryServiceException(
						"Could not delete subject as there are one or more books associated with it: " + subjTitle);
			}

		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while deleting subject: " + subjTitle, e);
		}

	}

	@Override
	public Set<Subject> findAllSubjectsSortByTitle() throws LibraryServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> findAllBooksSortByTitle() throws LibraryServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> findAllBooksSortByPublishdDt() throws LibraryServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
