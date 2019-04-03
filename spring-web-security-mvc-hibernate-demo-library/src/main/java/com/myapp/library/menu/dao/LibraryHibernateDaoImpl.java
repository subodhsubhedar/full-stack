package com.myapp.library.menu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;

@Repository
public class LibraryHibernateDaoImpl implements LibraryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Book createBook(Book book) throws LibraryDaoException {
		Book newbook;
		try {
			sessionFactory.getCurrentSession().save(book);
			newbook = book;

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while adding a new Book :" + book, e);
		}

		return newbook;
	}

	@Override
	public Subject createSubject(Subject subject) throws LibraryDaoException {

		Subject newSubject;
		try {

			sessionFactory.getCurrentSession().save(subject);

			newSubject = subject;

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while adding a new Book :" + subject, e);
		}

		return newSubject;
	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryDaoException {
		List<Subject> subjectList;
		try {

			subjectList = sessionFactory.getCurrentSession().createCriteria(Subject.class).list();

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while fetching all subjects", e);
		}

		return new LinkedHashSet<Subject>(subjectList);
	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryDaoException {

		Subject subject = null;
		try {

			Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession()
					.createCriteria(Subject.class);

			criteria.add(Restrictions.eq("subtitle", subjTitle));

			subject = (Subject) criteria.uniqueResult();

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while fetching all subjects", e);
		}

		return subject;
	}

	@Override
	public Set<Book> findAllBooks() throws LibraryDaoException {
		List<Book> booksList;
		try {
			booksList = sessionFactory.getCurrentSession().createCriteria(Book.class).list();

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while findAllBooks", e);
		}

		return new LinkedHashSet<Book>(booksList);
	}

	@Override
	public Book getBook(String bookTitle) throws LibraryDaoException {
		Book book = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession()
					.createCriteria(Book.class);

			book = (Book) criteria.add(Restrictions.eq("title", bookTitle)).uniqueResult();
		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while getting book ", e);
		}

		return book;
	}

	@Override
	public Subject getSubjectById(long subjId) throws LibraryDaoException {

		Subject subject = null;
		try {

			subject = sessionFactory.getCurrentSession().get(Subject.class, subjId);

		} catch (Exception e) {

			throw new LibraryDaoException("Exception occured while fetching subject", e);
		}

		return subject;
	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryDaoException {
		Book book = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession()
					.createCriteria(Book.class);

			book = (Book) criteria.add(Restrictions.eq("title", bookTitle)).uniqueResult();

			if (book != null) {
				sessionFactory.getCurrentSession().delete(book);
			}

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while deleting book ", e);
		}

		return book;
	}

	@Override
	public Subject deleteSubject(String subjTitle) throws LibraryDaoException {
		Subject subject = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession()
					.createCriteria(Subject.class);

			criteria.add(Restrictions.eq("subtitle", subjTitle));

			subject = (Subject) criteria.uniqueResult();

			if (subject != null) {
				sessionFactory.getCurrentSession().delete(subject);
			}

		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while deleting subject", e);
		}

		return subject;
	}

	@Override
	public Set<Book> getBooksBySubjectId(long subjId) throws LibraryDaoException {
		Subject subject = null;
		Set<Book> bookSet;
		try {
			subject = sessionFactory.getCurrentSession().get(Subject.class, subjId);

			bookSet = subject.getReferences();
		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while fetching subjects", e);
		}

		return bookSet;
	}

	@Override
	public Set<Book> findAllBooksSortByTitle() throws LibraryDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Subject> findAllSubjectsSortByTitle() throws LibraryDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> findAllBooksSortByPublishdDt() throws LibraryDaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
