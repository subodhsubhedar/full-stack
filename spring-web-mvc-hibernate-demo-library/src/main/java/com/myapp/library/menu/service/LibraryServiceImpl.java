package com.myapp.library.menu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.dao.LibraryDao;

/**
 * 
 * @author Admin
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao catalogueDao;

	@Override
	@Transactional
	public Set<Subject> findAllSubjects() throws LibraryServiceException {
		try {

			return catalogueDao.findAllSubjects();

		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving all subjects.", e);
		}
	}

	@Override
	@Transactional
	public Set<Book> findAllBooks() throws LibraryServiceException {
		try {
			return catalogueDao.findAllBooks();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving all books.", e);
		}

	}

	@Override
	@Transactional
	public Book getBook(String bookTitle) throws LibraryServiceException {
		try {
			return catalogueDao.getBook(bookTitle);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving book :" + bookTitle, e);
		}

	}

	@Override
	@Transactional
	public Subject getSubject(String subjTitle) throws LibraryServiceException {
		try {

			return catalogueDao.getSubject(subjTitle);

		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while retrieving subject :" + subjTitle, e);
		}

	}

	@Override
	@Transactional
	public Book createBook(Book book) throws LibraryServiceException {
		try {
			return catalogueDao.createBook(book);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while creating book :" + book, e);
		}

	}

	@Override
	@Transactional
	public Subject createSubject(Subject subject) throws LibraryServiceException {
		try {
			return catalogueDao.createSubject(subject);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while creating subject :" + subject, e);
		}

	}

	@Override
	@Transactional
	public Book deleteBook(String bookTitle) throws LibraryServiceException {
		try {
			return catalogueDao.deleteBook(bookTitle);
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while deleting book :" + bookTitle, e);
		}

	}

	@Override
	@Transactional
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
	@Transactional
	public Set<Subject> findAllSubjectsSortByTitle() throws LibraryServiceException {
		try {
			return catalogueDao.findAllSubjectsSortByTitle();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllSubjectsSortByTitle ");
		}
	}

	@Override
	@Transactional
	public Set<Book> findAllBooksSortByTitle() throws LibraryServiceException {
		try {
			return catalogueDao.findAllBooksSortByTitle();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllBooksSortByTitle ");
		}
	}

	@Override
	@Transactional
	public Set<Book> findAllBooksSortByPublishdDt() throws LibraryServiceException {
		try {
			return catalogueDao.findAllBooksSortByPublishdDt();
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllBooksSortByPublishdDt ");
		}
	}

}
