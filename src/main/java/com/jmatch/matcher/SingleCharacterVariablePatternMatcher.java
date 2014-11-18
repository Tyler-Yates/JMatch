package com.jmatch.matcher;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link VariablePatternMatcher} implementation that uses single characters to represent variables in patterns.
 * <p>
 * The pattern matching for this class is non-strict such that different variables are allowed to share the same value.
 * For example, the pattern {@code "xyyx"} would match {@code "aaaa"} by assigning {@code "a"} to both variables.
 */
public class SingleCharacterVariablePatternMatcher extends CharacterVariablePatternMatcher implements
        VariablePatternMatcher {
    private final String pattern;

    /**
     * Constructs a new matcher with the given pattern. The pattern should consist of single characters to represent
     * variables in the pattern. E.g. {@code "abba"} is composed of the variables {@code 'a'} and {@code 'b'}.
     *
     * @param pattern the given pattern
     */
    public SingleCharacterVariablePatternMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String input) {
        return matchRemaining(pattern, input, new HashMap<>(), false) != null;
    }

    @Override
    public Map<String, String> getVariableAssignments(String input) {
        return matchRemaining(pattern, input, new HashMap<>(), false);
    }
}
