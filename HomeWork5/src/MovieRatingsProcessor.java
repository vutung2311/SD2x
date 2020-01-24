/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

    public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
        if (movieRatings == null) {
            return Collections.emptyList();
        }
        if (movieRatings.isEmpty()) {
            return Collections.emptyList();
        }

        return new ArrayList<>(movieRatings.keySet());
    }

    public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
        if (movieRatings == null) {
            return Collections.emptyList();
        }
        if (movieRatings.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> alphabeticalMoviesAboveRating = new ArrayList<>(movieRatings.size());
        movieRatings.forEach((key, value) -> {
            if (key == null) {
                return;
            }
            if (value == null || value.isEmpty()) {
                return;
            }
            if (value.peek() > rating) {
                alphabeticalMoviesAboveRating.add(key);
            }
        });
        return alphabeticalMoviesAboveRating;
    }

    public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
        if (movieRatings == null) {
            return new TreeMap<>();
        }
        if (movieRatings.isEmpty()) {
            return new TreeMap<>();
        }

        TreeMap<String, Integer> ratingRemovedMovies = new TreeMap<>();
        List<String> removedMovies = new LinkedList<>();
        movieRatings.forEach((title, ratings) -> {
            if (title == null || ratings == null || ratings.isEmpty()) {
                return;
            }

            while (!ratings.isEmpty() && ratings.peek() < rating) {
                ratings.remove();
                if (!ratingRemovedMovies.containsKey(title)) {
                    ratingRemovedMovies.put(title, 1);
                    continue;
                }
                ratingRemovedMovies.replace(title, ratingRemovedMovies.get(title) + 1);
            }
            if (ratings.isEmpty()) {
                removedMovies.add(title);
            }
        });
        for (String removedMovie : removedMovies) {
            movieRatings.remove(removedMovie);
        }
        return ratingRemovedMovies;
    }
}
