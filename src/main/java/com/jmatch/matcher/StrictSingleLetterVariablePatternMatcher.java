package com.jmatch.matcher;

import com.google.common.collect.HashBiMap;
import com.jmatch.util.StringRemover;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * {@link VariablePatternMatcher} implementation that uses single letters to represent variables in patterns.
 * <p>
 * The pattern matching for this class is strict such that different variables are <b>not</b> allowed to share the same
 * value. For example, the pattern {@code "xyyx"} would not match {@code "aaaa"} because both {@code 'x'} and {@code
 * 'y'} would have to be assigned the value {@code "a"}.
 */
public class StrictSingleLetterVariablePatternMatcher implements VariablePatternMatcher {
    private final String pattern;

    /**
     * Constructs a new strict matcher with the given pattern. The pattern should consist of single letters to represent
     * variables in the pattern. E.g. {@code "abba"} is composed of the variables {@code 'a'} and {@code 'b'}.
     *
     * @param pattern the given pattern
     */
    public StrictSingleLetterVariablePatternMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String input) {
        return matchRemaining(pattern, input, HashBiMap.create()) != null;
    }

    @Override
    public Map<String, String> getVariableAssignments(String input) {
        return matchRemaining(pattern, input, HashBiMap.create());
    }

    private Map<String, String> matchRemaining(String pattern, String input, Map<String, String> assignments) {
        // If there is no more of the pattern left to match we need an empty input String to have matched the
        // original input.
        if (pattern.isEmpty()) {
            if (input.isEmpty()) {
                return assignments;
            } else {
                return null;
            }
        }

        // Pick the first letter in the pattern as our current variable
        final String variable = Character.toString(pattern.charAt(0));
        // Count the number of times the variable occurs in the pattern
        final int numberOfVariableOccurrencesInPattern = StringUtils.countMatches(pattern, variable);

        // Greedily attempt to assign a value to the current variable
        for (int endIndex = input.length(); endIndex > 0; endIndex--) {
            final String variableAssignment = input.substring(0, endIndex);

            // Ensure that different variables do not have the same assigned value
            if (assignments.containsValue(variableAssignment)) {
                continue;
            }

            assignments.put(variable, variableAssignment);
            // Check to make sure that the proper number of occurrences of the assigned value exist in the input
            final int numberOfAssignmentOccurrencesInInput = StringUtils.countMatches(input, variableAssignment);
            if (numberOfAssignmentOccurrencesInInput >= numberOfVariableOccurrencesInPattern) {
                // Get the permutations of removing the assignment the same number of times as the variable occurred
                // in the input
                final List<String> removalPermutations = StringRemover.getRemovalPermutations(variableAssignment,
                        numberOfVariableOccurrencesInPattern, input);

                // Check to see if the assignment leads to a valid match
                for (String removalPermutation : removalPermutations) {
                    final Map<String, String> result = matchRemaining(pattern.replaceAll(variable, ""),
                            removalPermutation, assignments);
                    if (result != null) {
                        return result;
                    }
                }
            }
            assignments.remove(variable);
        }

        return null;
    }
}
