package com.myapp.library.menu.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryDaoException;

/**
 * 
 * @author Admin
 *
 */
@Repository
public class LibraryFileDaoImpl implements LibraryDao {

	// File resource injection here
	@Value("classpath:data/subjects.ser")
	Resource subjectsDataFile;

	// File resource injection here
	@Value("classpath:data/books.ser")
	Resource booksDataFile;

	@Override
	public Set<Subject> findAllSubjects() throws LibraryDaoException {

		Set<Subject> objResultSet = readObject(subjectsDataFile);
		return objResultSet;
	}

	@Override
	public Set<Book> findAllBooks() throws LibraryDaoException {

		Set<Book> objResultSet = readObject(booksDataFile);
		return objResultSet;
	}

	@Override
	public Book getBook(String bookTitle) throws LibraryDaoException {

		Set<Book> allBookSet = findAllBooks();

		if (allBookSet != null && !allBookSet.isEmpty()) {
			Set<Book> resultBookSet = (allBookSet.stream()
					.filter(book -> (book.getTitle().toLowerCase()).contains(bookTitle.toLowerCase())))
							.collect(Collectors.toCollection(() -> new HashSet<Book>()));

			Optional<Book> optional = resultBookSet.stream().findFirst();

			if (optional.isPresent()) {
				return optional.get();
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public Subject getSubject(String subjTitle) throws LibraryDaoException {
		Set<Subject> allSubjectsSet = findAllSubjects();

		Set<Subject> resultSubjectSet = (allSubjectsSet.stream()
				.filter(subj -> (subj.getSubtitle().toLowerCase()).contains(subjTitle.toLowerCase())))
						.collect(Collectors.toCollection(() -> new HashSet<Subject>()));

		Optional<Subject> optional = resultSubjectSet.stream().findFirst();

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Book createBook(Book book) throws LibraryDaoException {
		Set<Book> bookSet = new HashSet<Book>();
		bookSet.add(book);

		saveObject(bookSet, true, booksDataFile);
		System.out.println("Input Book object saved :" + bookSet);

		// Now update the subject object state
		updateBooksInSubject(bookSet);

		return book;
	}

	/**
	 * 
	 * @param bookSet
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void updateBooksInSubject(Set<Book> bookSet) throws LibraryDaoException {

		Set<Subject> allSubjects = findAllSubjects();

		bookSet.forEach(book -> {
			long subjId = book.getSubjectId();

			allSubjects.stream().forEach((subjct) -> {
				if ((subjct.getSubjectId()) == subjId) {
					if (subjct.getReferences() != null) {
						subjct.getReferences().addAll(bookSet);
					} else {
						subjct.setReferences(bookSet);
					}
				}
			});
		});
		// Now update the saved object details
		saveObject(allSubjects, false, subjectsDataFile);
	}

	@Override
	public Subject createSubject(Subject subject) throws LibraryDaoException {

		Set<Subject> subjectSet = new HashSet<Subject>();
		subjectSet.add(subject);

		saveObject(subjectSet, true, subjectsDataFile);

		return subject;
	}

	@Override
	public Book deleteBook(String bookTitle) throws LibraryDaoException {

		Set<Book> allBooks = findAllBooks();

		Book deletedBook = getBook(bookTitle);

		allBooks.removeIf(book -> (book.getTitle().toLowerCase()).equals(bookTitle.toLowerCase()));

		// Now update the saved object details
		saveObject(allBooks, false, booksDataFile);

		// Now delete references in subject
		Set<Subject> allSubjects = findAllSubjects();

		allSubjects.forEach(subj -> {
			subj.getReferences().removeIf(book -> (book.getBookId() == (deletedBook.getBookId())));
		});

		allSubjects.stream().filter(subj -> subj.getSubjectId() == deletedBook.getSubjectId()).findFirst()
				.get().getReferences().removeIf(book -> (book.getBookId() == (deletedBook.getBookId())));

		// Now update the saved object details
		saveObject(allSubjects, false, subjectsDataFile);

		return deletedBook;
	}

	@Override
	public Subject deleteSubject(String subjTitle) throws LibraryDaoException {

		// Now delete references in subject
		Set<Subject> allSubjects = findAllSubjects();

		Subject deletedSubject = getSubject(subjTitle);

		allSubjects.removeIf(subj -> subj.getSubtitle().equalsIgnoreCase(subjTitle));

		saveObject(allSubjects, false, subjectsDataFile);
		return deletedSubject;
	}

	/**
	 * 
	 * @param obj
	 * @param append
	 * @param fileName
	 * @throws IOException
	 */
	private void saveObject(Set obj, boolean append, Resource fileResource) throws LibraryDaoException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileResource.getFile(), append);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(obj);

			fileOutputStream.close();
			objectOutputStream.close();
		} catch (Exception e) {
			throw new LibraryDaoException(
					"Exception occured while saving object :" + obj + " in file :" + fileResource.getFilename(), e);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private Set readObject(Resource fileResource) throws LibraryDaoException {
		try {

			FileInputStream fileInputStream = new FileInputStream(fileResource.getFile());

			ObjectInputStream objectInputStream = null;

			Set results = new HashSet();

			boolean isExist = true;

			while (isExist) {
				if (fileInputStream.available() != 0) {
					objectInputStream = new ObjectInputStream(fileInputStream);
					results.addAll((Set) objectInputStream.readObject());
				} else {
					isExist = false;
				}
			}

			fileInputStream.close();
			if (objectInputStream != null) {
				objectInputStream.close();
			}

			return results;
		} catch (Exception e) {
			throw new LibraryDaoException("Exception occured while reading from file :" + fileResource.getFilename(),
					e);
		}
	}

	@Override
	public Set<Book> getBooksBySubjectId(long subjId) throws LibraryDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject getSubjectById(long subjId) throws LibraryDaoException {
		// TODO Auto-generated method stub
		return null;
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
