/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataTier {

    private String fileName; // the name of the file to read

    public DataTier(String inputSource) {
        fileName = inputSource;
    }

    public Set<Book> getAllBooks() {
        Path file = Paths.get(fileName);
        Set<Book> allBooks = null;
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            allBooks = new LinkedHashSet<Book>();
            while ((line = reader.readLine()) != null) {
                String[] bookInfos = line.split("\t");
                allBooks.add(new Book(bookInfos[0], bookInfos[1], Integer.parseInt(bookInfos[2])));
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return allBooks;
    }
}
