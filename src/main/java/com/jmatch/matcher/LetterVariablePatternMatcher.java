package com.jmatch.matcher;

import com.jmatch.util.StringRemover;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Represents matchers that use letters as variables in their patterns.
 */
abstract class LetterVariablePatternMatcher {

    Map<String, String> matchRemaining(String pattern, String input, Map<String, String> assignments, boolean strict) {
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

            // If we are strict, ensure that different variables do not have the same assigned value
            if (strict && assignments.containsValue(variableAssignment)) {
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
                            removalPermutation, assignments, strict);
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
