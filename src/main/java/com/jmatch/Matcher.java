package com.jmatch;

/**
 * Used to match strings with a given pattern.
 */
public interface Matcher {

    /**
     * Returns true if the given input matches the pattern associated with the current Matcher.
     *
     * @param input the input string
     *
     * @return {@code true} if the input matches the pattern, {@code false} otherwise
     */
    public boolean matches(String input);
}
