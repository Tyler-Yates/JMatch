package com.jmatch.matcher;

import com.google.common.collect.HashBiMap;

import java.util.Map;

/**
 * {@link VariablePatternMatcher} implementation that uses single characters to represent variables in patterns.
 * <p>
 * The pattern matching for this class is strict such that different variables are <b>not</b> allowed to share the same
 * value. For example, the pattern {@code "xyyx"} would not match {@code "aaaa"} because both {@code 'x'} and {@code
 * 'y'} would have to be assigned the value {@code "a"}.
 */
public class StrictSingleCharacterVariablePatternMatcher extends CharacterVariablePatternMatcher implements
        VariablePatternMatcher {
    private final String pattern;

    /**
     * Constructs a new strict matcher with the given pattern. The pattern should consist of single characters to
     * represent variables in the pattern. E.g. {@code "abba"} is composed of the variables {@code 'a'} and {@code
     * 'b'}.
     *
     * @param pattern the given pattern
     */
    public StrictSingleCharacterVariablePatternMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String input) {
        return matchRemaining(pattern, input, HashBiMap.create(), true) != null;
    }

    @Override
    public Map<String, String> getVariableAssignments(String input) {
        return matchRemaining(pattern, input, HashBiMap.create(), true);
    }
}
