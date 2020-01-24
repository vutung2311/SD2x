import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {

    /*
     * Implement this method in Part 1
     */
    public static List<Sentence> readFile(String filename) {
        List<Sentence> sentenceList = new LinkedList<>();

        if (filename == null) {
            return sentenceList;
        }
        Path filePath = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ", 2);
                if (splitLine.length < 2) {
                    continue;
                }
                try {
                    int score = Integer.parseInt(splitLine[0]);
                    if (score > 2 || score < -2) {
                        continue;
                    }
                    if (splitLine[1].length() == 0) {
                        continue;
                    }
                    sentenceList.add(new Sentence(score, splitLine[1]));
                } catch (NumberFormatException exception) {
                    System.err.format("NumberFormatException: %s%n", exception);
                }
            }
        } catch (IOException exception) {
            System.err.format("IOException: %s%n", exception);
            return sentenceList;
        }

        return sentenceList;
    }

    /*
     * Implement this method in Part 2
     */
    public static Set<Word> allWords(List<Sentence> sentences) {
        if (sentences == null) {
            return Collections.emptySet();
        }
        if (sentences.isEmpty()) {
            return Collections.emptySet();
        }

        HashSet<Word> words = new HashSet<>();
        while (!sentences.isEmpty()) {
            Sentence sentence = sentences.remove(0);
            if (sentence == null) {
                continue;
            }

            HashSet<String> sentenceWordSet = Arrays.stream(sentence.getText().split(" ")).collect(Collectors.toCollection(HashSet::new));
            for (String x : sentenceWordSet) {
                if (x.length() == 1) {
                    continue;
                }
                String nt = x.toLowerCase();
                if (isNonCharacter(nt.charAt(0))) {
                    continue;
                }
                Word tmpWord = new Word(nt);
                tmpWord.increaseTotal(sentence.getScore());
                boolean wordNotExisted = words.add(tmpWord);
                if (!wordNotExisted) {
                    for (Word word : words) {
                        if (word.equals(tmpWord)) {
                            word.increaseTotal(sentence.getScore());
                        }
                    }
                }
            }
        }

        return words;
    }

    public static boolean isNonCharacter(char input) {
        return input < 'a' || input > 'z';
    }

    /*
     * Implement this method in Part 3
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {
        if (words == null) {
            return Collections.emptyMap();
        }
        if (words.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Double> wordScores = new HashMap<>();
        for (Word word : words) {
            if (word == null) {
                continue;
            }
            wordScores.put(word.getText(), word.calculateScore());
        }
        return wordScores;
    }

    /*
     * Implement this method in Part 4
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
        if (sentence == null || wordScores == null || sentence.isEmpty()) {
            return 0.0;
        }
        Double totalScore = 0.0;
        String[] words = sentence.split(" ");
        int wordCount = words.length;
        for (String word : words) {
            String token = word.toLowerCase();
            if (isNonCharacter(token.charAt(0))) {
                wordCount--;
                continue;
            }
            totalScore += wordScores.getOrDefault(token, 0.0);
        }

        return wordCount == 0 ? 0.0 : totalScore / wordCount;
    }

    /*
     * This method is here to help you run your program. Y
     * You may modify it as needed.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the name of the input file");
            System.exit(0);
        }
        String filename = args[0];
        System.out.print("Please enter a sentence: ");
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();
        in.close();
        List<Sentence> sentences = Analyzer.readFile(filename);
        Set<Word> words = Analyzer.allWords(sentences);
        Map<String, Double> wordScores = Analyzer.calculateScores(words);
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);
        System.out.println("The sentiment score is " + score);
    }
}
