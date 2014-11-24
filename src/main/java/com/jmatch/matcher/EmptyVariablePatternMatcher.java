package com.jmatch.matcher;

import java.util.Collections;
import java.util.Map;

/**
 * Represents a matcher with an empty variable pattern. By definition, an empty variable pattern matches <b>any</b>
 * input including the {@code null} String.
 */
public class EmptyVariablePatternMatcher implements VariablePatternMatcher {

    @Override
    public boolean matches(String input) {
        return true;
    }

    @Override
    public Map<String, String> getVariableAssignments(String input) {
        return Collections.emptyMap();
    }
}
