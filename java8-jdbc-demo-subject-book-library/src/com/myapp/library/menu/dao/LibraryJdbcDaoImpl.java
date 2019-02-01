package com.myapp.library.menu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;
import com.myapp.library.menu.dao.util.DBUtil;

/**
 * 
 * @author Admin
 *
 */
public class LibraryJdbcDaoImpl implements LibraryDao {

	private static final String DB_NAME = DBUtil.DB_MYSQL;

	@Override
	public Subject createSubject(Subject subject) throws LibraryDaoException {
		Subject newSubject = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.prepareStatement("INSERT INTO SUBJECT VALUES (?,?,?)");
			stmt.setLong(1, subject.getSubjectId());
			stmt.setString(2, subject.getSubtitle());
			stmt.setInt(3, subject.getDurationInHrs());

			stmt.executeUpdate();

			System.out.println("JDBC Update done successfully !");
			newSubject = subject;

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while creating subject :" + subject, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while creating subject :" + subject, e);
			}

		}

		return newSubject;
	}

	@Override
	public Book createBook(Book book) throws LibraryDaoException {
		Book newBook = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.prepareStatement("INSERT INTO BOOK VALUES (?,?,?,?,?,?)");
			stmt.setLong(1, book.getBookId());
			stmt.setString(2, book.getTitle());
			stmt.setDouble(3, book.getPrice());
			stmt.setInt(4, book.getVolume());
			stmt.setDate(5, Date.valueOf(book.getPublishDate()));
			stmt.setLong(6, book.getSubjectId());

			stmt.executeUpdate();

			System.out.println("JDBC Update done successfully !");
			newBook = book;

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while creating subject :" + book, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while creating subject :" + book, e);
			}

		}

		return newBook;
	}

	@Override
	public Subject getSubjectById(long subjId) throws LibraryDaoException {
		Subject subject = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from subject where subjectId =" + "'" + subjId + "'");

			System.out.println("JDBC Query executed successfully !");

			if (rs.next()) {
				subject = new Subject(rs.getLong(1), rs.getString(2), rs.getInt(3), null);
			}

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying subject :" + subjId, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying subject :" + subjId, e);
			}
		}

		return subject;
	}

	@Override
	public Set<Book> getBooksBySubjectId(long subjId) throws LibraryDaoException {

		Set<Book> bookSet = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from book where subjectId =" + "'" + subjId + "'");

			System.out.println("JDBC Query executed successfully !");

			bookSet = new LinkedHashSet<Book>();
			Book book;
			while (rs.next()) {
				book = new Book(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						(rs.getDate(5)).toLocalDate(), rs.getLong(6));

				bookSet.add(book);
			}

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying book with subject id :" + subjId, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying book with subject id :"
								+ subjId,
						e);
			}
		}

		return bookSet;
	}

	@Override
	public Subject deleteSubject(String subjTitle) throws LibraryDaoException {

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			stmt.executeUpdate("delete from subject where subjectTitle =" + "'" + subjTitle + "'");

			System.out.println("JDBC Delete operation executed successfully !");
		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while deleting subject :" + subjTitle, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while deleting subject :" + subjTitle, e);
			}
		}

		return null;
	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryDaoException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			stmt.executeUpdate("delete from book where title =" + "'" + bookTitle + "'");

			System.out.println("JDBC Delete operation executed successfully !");
		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while deleting book :" + bookTitle, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while deleting book :" + bookTitle, e);
			}
		}

		return null;

	}

	@Override
	public Book getBook(String bookTitle) throws LibraryDaoException {

		Book book = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from book where title =" + "'" + bookTitle + "'");

			System.out.println("JDBC Query executed successfully !");
			if (rs.first()) {
				book = new Book(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						(rs.getDate(5)).toLocalDate(), rs.getLong(6));
			}
		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying book :" + bookTitle, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying subject :" + bookTitle, e);
			}
		}

		return book;
	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryDaoException {

		Subject subject = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from subject where subjectTitle =" + "'" + subjTitle + "'");

			System.out.println("JDBC Query executed successfully !");

			if (rs.first()) {
				subject = new Subject(rs.getLong(1), rs.getString(2), rs.getInt(3), null);
			}

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying subject :" + subjTitle, ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying subject :" + subjTitle, e);
			}
		}

		return subject;
	}

	@Override
	public Set<Subject> findAllSubjects() throws LibraryDaoException {
		Set<Subject> subjectSet = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from subject");

			System.out.println("JDBC Query executed successfully !");

			subjectSet = new LinkedHashSet<Subject>();
			Subject subject;

			while (rs.next()) {
				subject = new Subject(rs.getLong(1), rs.getString(2), rs.getInt(3), null);
				subjectSet.add(subject);
			}

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying all subjects", ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying all subjects ", e);
			}
		}

		return subjectSet;
	}

	@Override
	public Set<Book> findAllBooks() throws LibraryDaoException {
		Set<Book> bookSet = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBUtil.getConnection(DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from book");

			System.out.println("JDBC Query executed successfully !");

			bookSet = new LinkedHashSet<Book>();
			Book book;
			while (rs.next()) {
				book = new Book(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						(rs.getDate(5)).toLocalDate(), rs.getLong(6));

				bookSet.add(book);
			}

		} catch (Exception ex) {
			throw new LibraryDaoException("Exception occured while querying all books.", ex);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new LibraryDaoException(
						"Exception occured while closing connection and stmt while querying all books",

						e);
			}
		}

		return bookSet;
	}

}
