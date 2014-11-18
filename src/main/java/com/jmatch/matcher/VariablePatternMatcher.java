package com.jmatch.matcher;

import java.util.Map;

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

    /**
     * Returns a map representing assignments to the variables in the current matcher's pattern for the given input
     * string. For example, if the pattern were {@code "aba"} and the input were {@code "dogcatdog"} the variable
     * assignments would be:<br>
     * {@code "a" = "dog"}<br>
     * {@code "b" = "cat"}
     * <p>
     * If the input cannot be matched with the pattern {@code null} is returned.
     * <p>
     * If multiple valid assignments are possible, no guarantee is made as to which assignment will be picked.
     *
     * @param input the input string
     *
     * @return a map representing the variable assignments or {@code null} if no valid assignments are possible
     */
    public Map<String, String> getVariableAssignments(String input);
}
