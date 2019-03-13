package com.myapp.library.menu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;

@Repository
@Transactional
public class LibraryJpaRepository implements LibraryDao {

	@Autowired
	private EntityManager entityMngr;

	private void init() {
		// entityMngr = JpaUtil.getEntityManager();
		entityMngr.getTransaction().begin();
	}

	private void commitTxn() {
		entityMngr.getTransaction().commit();
	}

	private void rollbackTxn() {
		entityMngr.getTransaction().rollback();
	}

	public Book createBook(Book book) throws LibraryDaoException {
		Book newbook;
		try {

			init();

			entityMngr.persist(book);

			commitTxn();
			newbook = book;

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while adding a new Book :" + book, e);
		}

		return newbook;
	}

	@Override
	public Subject createSubject(Subject subject) throws LibraryDaoException {

		Subject newSubject;
		try {

			init();

			entityMngr.persist(subject);

			commitTxn();
			newSubject = subject;

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while adding a new Subject :" + subject, e);
		}

		return newSubject;
	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryDaoException {
		List<Subject> subjectList;
		try {

			init();

			subjectList = entityMngr.createQuery("select s from Subject s", Subject.class).getResultList();

			commitTxn();

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while fetching all subjects", e);
		}

		return new LinkedHashSet<Subject>(subjectList);
	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryDaoException {

		Subject subject = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
			Root<Subject> sm = cq.from(Subject.class);
			cq.where(cb.equal(sm.get("subtitle"), subjTitle));

			subject = (Subject) entityMngr.createQuery(cq).getSingleResult();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching all subjects", e);
		}

		return subject;
	}

	@Override
	public Set<Book> findAllBooks() throws LibraryDaoException {
		List<Book> booksList;
		try {

			init();

			booksList = entityMngr.createQuery("select b from Book b", Book.class).getResultList();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while findAllBooks", e);
		}

		return new LinkedHashSet<Book>(booksList);
	}

	@Override
	public Book getBook(String bookTitle) throws LibraryDaoException {
		Book book = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Book> cq = cb.createQuery(Book.class);
			Root<Book> sm = cq.from(Book.class);
			cq.where(cb.equal(sm.get("title"), bookTitle));

			book = (Book) entityMngr.createQuery(cq).getSingleResult();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while getting book ", e);
		}

		return book;
	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryDaoException {
		Book book = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Book> cq = cb.createQuery(Book.class);
			Root<Book> sm = cq.from(Book.class);
			cq.where(cb.equal(sm.get("title"), bookTitle));

			book = (Book) entityMngr.createQuery(cq).getSingleResult();

			if (book != null) {
				entityMngr.remove(book);
			}

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while deleting book ", e);
		}

		return book;
	}

	@Override
	public Subject deleteSubject(String subjTitle) throws LibraryDaoException {
		Subject subject = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
			Root<Subject> sm = cq.from(Subject.class);
			cq.where(cb.equal(sm.get("subtitle"), subjTitle));

			subject = (Subject) entityMngr.createQuery(cq).getSingleResult();

			if (subject != null) {
				entityMngr.remove(subject);
			}

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while deleting subject", e);
		}

		return subject;
	}

	@Override
	public Set<Book> getBooksBySubjectId(long subjId) throws LibraryDaoException {
		Subject subject = null;
		Set<Book> bookSet;
		try {

			init();

			subject = entityMngr.find(Subject.class, subjId);

			bookSet = subject.getReferences();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching subjects", e);
		}

		return bookSet;
	}

	@Override
	public Subject getSubjectById(long subjId) throws LibraryDaoException {
		Subject subject = null;
		try {

			init();

			subject = entityMngr.find(Subject.class, subjId);

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching subject", e);
		}

		return subject;
	}

	@Override
	public Set<Book> findAllBooksSortByTitle() throws LibraryDaoException {

		List<Book> books = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Book> cq = cb.createQuery(Book.class);
			Root<Book> sm = cq.from(Book.class);

			cq.orderBy(cb.asc(sm.get("title")));

			books = (List<Book>) entityMngr.createQuery(cq).getResultList();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fidning all books sorted by title ", e);
		}

		return new LinkedHashSet<Book>(books);

	}

	@Override
	public Set<Subject> findAllSubjectsSortByTitle() throws LibraryDaoException {
		List<Subject> subjects = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
			Root<Subject> sm = cq.from(Subject.class);

			cq.orderBy(cb.asc(sm.get("subtitle")));

			subjects = (List<Subject>) entityMngr.createQuery(cq).getResultList();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while findAllSubjectsSortByTitle ", e);
		}

		return new LinkedHashSet<Subject>(subjects);
	}

	@Override
	public Set<Book> findAllBooksSortByPublishdDt() throws LibraryDaoException {
		List<Book> books = null;
		try {

			init();

			CriteriaBuilder cb = entityMngr.getCriteriaBuilder();
			CriteriaQuery<Book> cq = cb.createQuery(Book.class);
			Root<Book> sm = cq.from(Book.class);

			cq.orderBy(cb.asc(sm.get("publishDate")));

			books = (List<Book>) entityMngr.createQuery(cq).getResultList();

			commitTxn();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fidning all books sorted publish date ", e);
		}

		return new LinkedHashSet<Book>(books);
	}

}
