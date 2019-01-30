package com.myapp.library.menu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class AddBookMenu {

	private LibraryService catalogueService;

	public AddBookMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	public void menu(Scanner scanInput) {
		System.out.println("You have selected to add a new Book ... ");
		scanInput.nextLine();

		System.out.println("Please provide a title:");
		String title = scanInput.nextLine();

		System.out.println("Please provide a price:");
		double price = scanInput.nextDouble();

		System.out.println("Please provide a volume:");
		int vol = scanInput.nextInt();

		System.out.println("Please provide a publish date in dd/mm/yyyy format:");
		scanInput.nextLine();
		String publishDateStr = scanInput.next();

		LocalDate publishDate = LocalDate.parse(publishDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		Subject matchedSubject = chooseSubjectAssociation(scanInput);

		Random random = new Random();

		Book book = new Book(random.nextLong(), title, price, vol, publishDate, matchedSubject.getSubjectId());

		addBook(book);
	}

	/**
	 * 
	 * @param book
	 */
	public void addBook(Book book) {

		Book addedBook = null;
		try {
			addedBook = catalogueService.createBook(book);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("New Book added Sucessfully ..");
		System.out.println(addedBook);
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public Set<Subject> findAllSubjects() {

		Set<Subject> subjectSet = null;
		try {
			subjectSet = catalogueService.findAllSubjects();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (subjectSet != null && !subjectSet.isEmpty()) {
			System.out.println("Total Subjects available : " + subjectSet.size());
		} else {
			System.out.println("No Subjects are available currently in the Library Catalogue.");
		}

		return subjectSet;
	}

	/**
	 * 
	 * @param scanInput
	 * @return
	 */
	public Subject chooseSubjectAssociation(Scanner scanInput) {
		Subject matchedSubject = null;

		while (matchedSubject == null) {

			System.out.println("Please choose a subject title to associate this book to:");
			scanInput.nextLine();

			Set<Subject> subjSet = findAllSubjects();

			subjSet.forEach(subj -> {
				System.out.println(subj.getSubtitle());

			});
			String subjTitle = scanInput.nextLine();

			Optional<Subject> match = subjSet.stream()
					.filter(subject -> subject.getSubtitle().equalsIgnoreCase(subjTitle)).findFirst();

			if (match.isPresent()) {
				matchedSubject = match.get();
				System.out.println("Subject retrieved :" + matchedSubject);
			} else {
				System.out.println("No Subject found please enter a valid name.");
			}
		}
		return matchedSubject;
	}
}
