package com.jmatch.matcher;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class EmptyVariablePatternMatcherTest {

    private EmptyVariablePatternMatcher emptyVariablePatternMatcher;

    @Before
    public void setUp() throws Exception {
        emptyVariablePatternMatcher = new EmptyVariablePatternMatcher();
    }

    @Test
    public void testMatches() throws Exception {
        assertTrue(emptyVariablePatternMatcher.matches(""));
        assertTrue(emptyVariablePatternMatcher.matches(null));
        assertTrue(emptyVariablePatternMatcher.matches("abba"));
        assertTrue(emptyVariablePatternMatcher.matches("dogcatcatdog"));
        assertTrue(emptyVariablePatternMatcher.matches("a"));
    }

    @Test
    public void testGetVariableAssignments() throws Exception {
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments(null).isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("abba").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("dogcatcatdog").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("a").isEmpty());
    }
}