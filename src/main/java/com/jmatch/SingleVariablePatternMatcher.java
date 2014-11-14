package com.jmatch;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link com.jmatch.Matcher} implementation that uses single letters to represent variables in patterns.
 * <p/>
 * The pattern matching for this class is non-strict such that different variables are allowed to share the same value.
 * For example, the pattern {@code "xyyx"} would match {@code "aaaa"} by assigning {@code "a"} to both variables.
 */
public class SingleVariablePatternMatcher implements Matcher {
    private final String pattern;

    /**
     * Constructs a new matcher with the given pattern. The pattern should consist of single letters to represent
     * variables in the pattern. E.g. {@code "abba"} is composed of the variables {@code 'a'} and {@code 'b'}.
     *
     * @param pattern the given pattern
     */
    public SingleVariablePatternMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String input) {
        return matchRemaining(pattern, input);
    }

    private boolean matchRemaining(String pattern, String input) {
        // If there is no more of the pattern left to match we need an empty input String to have matched the
        // original input.
        if (pattern.isEmpty()) {
            return input.isEmpty();
        }

        // Pick the first letter in the pattern as our current variable
        final String variable = Character.toString(pattern.charAt(0));
        // Count the number of times the variable occurs in the pattern
        final int numberOfVariableOccurrencesInPattern = StringUtils.countMatches(pattern, variable);

        // Greedily attempt to assign a value to the current variable
        for (int endIndex = input.length(); endIndex > 0; endIndex--) {
            final String variableAssignment = input.substring(0, endIndex);

            // Check to make sure that the proper number of occurrences of the assigned value exist in the input
            final int numberOfAssignmentOccurrencesInInput = StringUtils.countMatches(input, variableAssignment);
            if (numberOfAssignmentOccurrencesInInput == numberOfVariableOccurrencesInPattern) {
                // Check to see if the assignment leads to a valid match
                final boolean matchSuccessful = matchRemaining(pattern.replaceAll(variable, ""), input.replaceAll
                        (variableAssignment, ""));

                if (matchSuccessful) {
                    return true;
                }
            }
        }

        return false;
    }
}
