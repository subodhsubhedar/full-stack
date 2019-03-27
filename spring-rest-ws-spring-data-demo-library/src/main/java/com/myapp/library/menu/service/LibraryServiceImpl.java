package com.myapp.library.menu.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.repository.LibraryBookJpaRepository;
import com.myapp.library.menu.repository.LibrarySubjectJpaRepository;

/**
 * ss
 * 
 * @author Admin
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibrarySubjectJpaRepository subjectRepository;

	@Autowired
	private LibraryBookJpaRepository bookRepository;

	@Override
	@Transactional
	public Set<Subject> findAllSubjects() throws LibraryServiceException {
		try {

			return new LinkedHashSet<Subject>(subjectRepository.findAll());

		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while retrieving all subjects.", e);
		}
	}

	@Override
	@Transactional
	public Set<Book> findAllBooks() throws LibraryServiceException {
		try {
			return new LinkedHashSet<Book>(bookRepository.findAll());

		} catch (DataAccessException dae) {
			throw new LibraryServiceException("DataAccessException Occured while retrieving all BOOKs", dae);
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while retrieving all books.", e);
		}

	}

	@Override
	@Transactional
	public Book getBook(String bookTitle) throws LibraryServiceException {
		Book book = null;
		try {

			Optional<Book> optional = bookRepository.findByTitle(bookTitle);

			if (optional.isPresent()) {
				book = optional.get();
			} else {
				throw new LibraryServiceException("Book Not found with title :" + bookTitle);
			}
			return book;
		} catch (Exception e) {
			throw new LibraryServiceException(("Book Title :" + bookTitle + " -- " + e.getMessage()), e);
		}
	}

	@Override
	@Transactional
	public Subject getSubject(String subjTitle) throws LibraryServiceException {
		Subject subject = null;
		try {

			Optional<Subject> optional = subjectRepository.findBySubtitle(subjTitle);

			if (optional.isPresent()) {
				subject = optional.get();
			} else {
				throw new LibraryServiceException("Subject Not found with title :" + subjTitle);
			}
			return subject;
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while retrieving subject :" + subjTitle, e);
		}
	}

	@Override
	@Transactional
	public Book createBook(Book book) throws LibraryServiceException {
		try {
			return bookRepository.save(book);

		} catch (DataAccessException dae) {
			throw new LibraryServiceException("DataAccessException Occured while creating BOOK :" + book, dae);
		} catch (Exception e) {
			throw new LibraryServiceException(("Book id :" + book.getBookId() + " -- " + e.getMessage()), e);
		}

	}

	@Override
	@Transactional
	public Subject createSubject(Subject subject) throws LibraryServiceException {
		try {
			return subjectRepository.save(subject);
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while creating subject :" + subject, e);
		}

	}

	@Override
	@Transactional
	public Book deleteBook(String bookTitle) throws LibraryServiceException {
		Book deletedBook;
		try {
			Book book = getBook(bookTitle);
			deletedBook = book;

			bookRepository.delete(deletedBook);

			return deletedBook;
		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while deleting book :" + bookTitle, e);
		}

	}

	@Override
	@Transactional
	public Subject deleteSubject(String subjTitle) throws LibraryServiceException {
		Subject deletedSubject;
		try {

			Subject subject = this.getSubject(subjTitle);
			deletedSubject = subject;

			if (subject.getReferences() == null || subject.getReferences().isEmpty()) {

				subjectRepository.delete(subject);
				return deletedSubject;
			} else {
				throw new LibraryServiceException(
						"Could not delete subject as there are one or more books associated with it: " + subjTitle);
			}

		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while deleting subject: " + subjTitle, e);
		}

	}

	@Override
	public Set<Subject> findSubjectByDuration(int startValue, int endValue) throws LibraryServiceException {
		List<Subject> subjectList;

		try {

			subjectList = subjectRepository.findSubjectBetweenDuration(startValue, endValue);

		} catch (Exception e) {
			throw new LibraryServiceException("Exception Occured while retrieving all subjects.", e);
		}

		return new LinkedHashSet<Subject>(subjectList);
	}

	@Override
	@Transactional
	public Book getBookById(Long bookId) throws LibraryServiceException {
		Book book = null;
		try {

			Optional<Book> optional = bookRepository.findById(bookId);

			if (optional.isPresent()) {
				book = optional.get();
			} else {
				throw new LibraryServiceException("Book Not found with id :" + bookId);
			}
			return book;
		} catch (DataAccessException dae) {
			throw new LibraryServiceException("DataAccessException Occured while retrieving BOOK :" + bookId, dae);
		} catch (Exception e) {
			throw new LibraryServiceException(("Book id :" + bookId + " -- " + e.getMessage()), e);
		}
	}

	@Override
	@Transactional
	public void deleteBookById(Long bookId) throws LibraryServiceException {
		try {
			Book book = null;

			Optional<Book> optional = bookRepository.findById(bookId);

			if (optional.isPresent()) {
				book = optional.get();
			} else {
				throw new LibraryServiceException("Book Not found with id :" + bookId);
			}

			bookRepository.delete(book);

		} catch (DataAccessException dae) {
			throw new LibraryServiceException("DataAccessException Occured while deleting BOOK :" + bookId, dae);
		} catch (Exception e) {
			throw new LibraryServiceException(("Book id :" + bookId + " -- " + e.getMessage()), e);
		}
	}

	@Override
	@Transactional
	public Book updateBook(Book book) throws LibraryServiceException {
		Book returnedObj = null;
		try {
			Book entity = getBookById(book.getBookId());

			entity.setPrice(book.getPrice());
			entity.setPublishDate(book.getPublishDate());
			entity.setSubject(book.getSubject());
			entity.setTitle(book.getTitle());
			entity.setVolume(book.getVolume());

			returnedObj = bookRepository.save(entity);
			return returnedObj;
		} catch (DataAccessException dae) {
			throw new LibraryServiceException("DataAccessException Occured while updating BOOK :" + book, dae);
		} catch (Exception e) {
			throw new LibraryServiceException(("Book id :" + book.getBookId() + " -- " + e.getMessage()), e);
		}
	}

}
