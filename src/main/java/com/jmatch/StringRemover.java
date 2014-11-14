package com.jmatch;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to remove substrings from strings.
 */
public class StringRemover {

    /**
     * Returns a list of strings that represent all of the permutations of removing the given
     * substring from the given input {@code timesToRemove} times.
     * <p>
     * If the given substring occurs less than {@code timesToRemove} times then an empty list is
     * returned.
     *
     * @param substring the substring to remove
     * @param timesToRemove the number of times to remove the substring
     * @param input the input
     *
     * @return a list of removal permutations
     *
     * @throws IllegalArgumentException if {@code timesToRemove} is negative or {@code substring} is
     * the empty string
     */
    public static List<String> getRemovalPermutations(String substring, int timesToRemove,
                                                      String input) {
        Preconditions.checkArgument(timesToRemove >= 0);
        Preconditions.checkArgument(!substring.isEmpty());

        final List<String> permutations = new ArrayList<>();
        removePermute(permutations, substring, timesToRemove, "", input);
        return permutations;
    }

    private static void removePermute(List<String> permutations, String substring,
                                      int timesLeftToRemove, String processed, String unprocessed) {
        if (timesLeftToRemove == 0) {
            permutations.add(processed + unprocessed);
        } else {
            final int substringStartIndex = unprocessed.indexOf(substring);

            if (substringStartIndex != -1) {
                final int substringEndIndex = substringStartIndex + substring.length();

                // Recursive call without removing the substring
                removePermute(permutations, substring, timesLeftToRemove,
                        processed + unprocessed.substring(0, substringEndIndex),
                        unprocessed.substring(substringEndIndex));

                // Recursive call removing the substring
                removePermute(permutations, substring, timesLeftToRemove - 1,
                        processed + unprocessed.substring(0, substringStartIndex),
                        unprocessed.substring(substringEndIndex));
            }
        }
    }
}
