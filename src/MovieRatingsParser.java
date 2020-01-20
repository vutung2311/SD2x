/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {

		if (allUsersRatings == null) {
			return new TreeMap<>();
		}
		if (allUsersRatings.isEmpty()) {
			return new TreeMap<>();
		}

		TreeMap<String, PriorityQueue<Integer>> titleToScoresMap = new TreeMap<>();
		for (UserMovieRating r : allUsersRatings) {
			if (r == null) {
				continue;
			}
			if (r.getMovie() == null || r.getMovie().isEmpty()) {
				continue;
			}
			if (r.getUserRating() < 0) {
				continue;
			}
			String moveTitleInLowerCase =  r.getMovie().toLowerCase();
			if (!titleToScoresMap.containsKey(moveTitleInLowerCase)) {
				titleToScoresMap.put(moveTitleInLowerCase, new PriorityQueue<>());
			}
			titleToScoresMap.get(moveTitleInLowerCase).add(r.getUserRating());
		}

		return titleToScoresMap;
	}

}
