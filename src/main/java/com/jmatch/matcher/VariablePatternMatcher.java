package com.jmatch.matcher;

/**
 * Used to match strings with a given pattern.
 */
public interface VariablePatternMatcher {

    /**
     * Returns true if the given input matches the pattern associated with the current VariablePatternMatcher.
     *
     * @param input the input string
     *
     * @return {@code true} if the input matches the pattern, {@code false} otherwise
     */
    public boolean matches(String input);
}
