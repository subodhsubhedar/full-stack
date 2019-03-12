package com.myapp.library.menu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.menu.dao.util.HibernateUtil;

public class LibraryHibernateDaoImpl implements LibraryDao {

	private Session session = null;

	private Transaction tx = null;

	private void init() {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();

	}

	private void commitTxn() {
		tx.commit();
	}

	private void rollbackTxn() {
		tx.rollback();
	}

	public void shutdown() {
		if (session != null) {
			session.close();
		}
		// 1HibernateUtil.shutdown();
	}

	@Override
	public Book createBook(Book book) throws LibraryDaoException {
		Book newbook;
		try {

			init();

			session.save(book);

			commitTxn();
			shutdown();
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

			session.save(subject);

			commitTxn();
			shutdown();
			newSubject = subject;

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while adding a new Book :" + subject, e);
		}

		return newSubject;
	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryDaoException {
		List<Subject> subjectList;
		try {

			init();

			subjectList = session.createCriteria(Subject.class).list();

			commitTxn();
			shutdown();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching all subjects", e);
		}

		return new LinkedHashSet<Subject>(subjectList);
	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryDaoException {

		Subject subject = null;
		try {

			init();

			Criteria criteria = session.createCriteria(Subject.class);

			criteria.add(Restrictions.eq("subtitle", subjTitle));

			subject = (Subject) criteria.uniqueResult();

			commitTxn();
			shutdown();

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

			booksList = session.createCriteria(Book.class).list();

			commitTxn();
			shutdown();

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

			Criteria criteria = session.createCriteria(Book.class);

			book = (Book) criteria.add(Restrictions.eq("title", bookTitle)).uniqueResult();

			commitTxn();
			shutdown();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while getting book ", e);
		}

		return book;
	}

	@Override
	public Subject getSubjectById(long subjId) throws LibraryDaoException {

		Subject subject = null;
		try {

			init();

			subject = session.find(Subject.class, subjId);

			commitTxn();
			shutdown();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching subject", e);
		}

		return subject;
	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryDaoException {
		Book book = null;
		try {

			init();

			Criteria criteria = session.createCriteria(Book.class);

			book = (Book) criteria.add(Restrictions.eq("title", bookTitle)).uniqueResult();

			if (book != null) {
				session.delete(book);
			}

			commitTxn();
			shutdown();

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

			Criteria criteria = session.createCriteria(Subject.class);

			criteria.add(Restrictions.eq("subtitle", subjTitle));

			subject = (Subject) criteria.uniqueResult();

			if (subject != null) {
				session.delete(subject);
			}

			commitTxn();
			shutdown();

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

			subject = session.find(Subject.class, subjId);

			bookSet = subject.getReferences();

			commitTxn();
			shutdown();

		} catch (Exception e) {
			rollbackTxn();
			throw new LibraryDaoException("Exception occured while fetching subjects", e);
		}

		return bookSet;
	}

}
