/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

import java.util.LinkedHashSet;
import java.util.Set;

public class LogicTier {

	private DataTier dataTier; // link to the Data Tier

	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}

	public Set<String> findBookTitlesByAuthor(String author) {
		Set<String> titlesOfAuthor = new LinkedHashSet<>();
		for (Book book : dataTier.getAllBooks()) {
			if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				titlesOfAuthor.add(book.getTitle());
			}
		}
		return titlesOfAuthor;
	}

	public Integer findNumberOfBooksInYear(Integer year) {
		Integer numberOfBooksInYear = 0;
		for (Book book : dataTier.getAllBooks()) {
			if (book.getPublicationYear() == year) {
				numberOfBooksInYear++;
			}
		}
		return numberOfBooksInYear;
	}
}
