import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

    public static void insertSorted(LinkedList<Integer> list, int value) {

        if (list == null) {
            return;
        }

        if (list.size() == 0) {
            list.add(value);
            return;
        }

        if (list.getFirst() >= value) {
            list.addFirst(value);
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > value) {
                list.add(i, value);
                return;
            }
        }

        list.add(value);
    }


    public static void removeMaximumValues(LinkedList<String> list, int N) {

        if (list == null || N <= 0) {
            return;
        }

        int removedUniqueElementCount = 0;
        while (removedUniqueElementCount < N && removedUniqueElementCount < list.size()) {
            String maxElement = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).compareTo(maxElement) > 0) {
                    maxElement = list.get(i);
                }
            }
            String finalMaxElement = maxElement;
            list.removeIf(s -> s.equals(finalMaxElement));
            removedUniqueElementCount++;
        }
    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
        if (two == null || two.size() == 0) {
            return false;
        }

        if (one == null) {
            return false;
        }

        for (int i = 0; i < one.size(); i++) {
            if (one.get(i).equals(two.getFirst())) {
                for (int j = 1; j < two.size(); j++) {
                    if (i + j + 1 > one.size()) {
                        break;
                    }
                    if (!one.get(i + j).equals(two.get(j))) {
                        break;
                    }
                    if (j == two.size() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
