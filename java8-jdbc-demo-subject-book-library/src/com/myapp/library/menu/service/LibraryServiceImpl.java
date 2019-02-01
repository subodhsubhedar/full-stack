package com.myapp.library.menu.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.dao.LibraryDao;
import com.myapp.library.menu.dao.LibraryJdbcDaoImpl;

/**
 * 
 * @author Admin
 *
 */
public class LibraryServiceImpl implements LibraryService {
	private LibraryDao catalogueDao = null;

	public LibraryServiceImpl() {
		// catalogueDao = new LibraryFileDaoImpl();
		catalogueDao = new LibraryJdbcDaoImpl();

	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryServiceException {
		try {

			Set<Subject> subjectSet = catalogueDao.findAllSubjects();

			Set<Book> bookSet = findAllBooks();

			subjectSet.forEach(subject -> {
				Set<Book> subjBookSet = bookSet.stream().filter(book -> (book.getSubjectId() == subject.getSubjectId()))
						.collect(Collectors.toCollection(() -> new LinkedHashSet<Book>()));
				subject.setReferences(subjBookSet);
			});

			return subjectSet;

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

			Subject subject = catalogueDao.getSubject(subjTitle);
			if (subject != null) {
				Set<Book> bookSet = catalogueDao.getBooksBySubjectId(subject.getSubjectId());
				subject.setReferences(bookSet);
			}

			return subject;
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
		try {
			Set<Subject> subjectSet = this.findAllSubjects();

			if (subjectSet != null) {
				return subjectSet.stream()
						.sorted((Subject s1, Subject s2) -> (s1.getSubtitle()).compareTo(s2.getSubtitle()))
						.collect(Collectors.toCollection(() -> new LinkedHashSet<Subject>()));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while findAllSubjectsSortByTitle.", e);
		}

	}

	@Override
	public Set<Book> findAllBooksSortByTitle() throws LibraryServiceException {
		try {
			Set<Book> bookSet = catalogueDao.findAllBooks();
			if (bookSet != null) {
				return bookSet.stream().sorted((Book b1, Book b2) -> (b1.getTitle().compareTo(b2.getTitle())))
						.collect(Collectors.toCollection(() -> new LinkedHashSet<Book>()));
			} else {
				return null;
			}
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllBooksSortByTitle.", e);
		}
	}

	@Override
	public Set<Book> findAllBooksSortByPublishdDt() throws LibraryServiceException {
		try {
			Set<Book> bookSet = catalogueDao.findAllBooks();
			if (bookSet != null) {
				return bookSet.stream()
						.sorted((Book b1, Book b2) -> (b1.getPublishDate().compareTo(b2.getPublishDate())))
						.collect(Collectors.toCollection(() -> new LinkedHashSet<Book>()));
			} else {
				return null;
			}
		} catch (LibraryDaoException e) {
			throw new LibraryServiceException("Exception Occured while findAllBooksSortByPublishdDt.", e);
		}
	}
}
