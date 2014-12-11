package com.jmatch.matcher;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class EmptyVariablePatternMatcherTest {

    private VariablePatternMatcher emptyVariablePatternMatcher;

    @Before
    public void setUp() throws Exception {
        emptyVariablePatternMatcher = SingleCharacterVariablePatternMatcher.createStrictMatcher("");
    }

    @Test
    public void testCreation() {
        final VariablePatternMatcher matcher1 = SingleCharacterVariablePatternMatcher.createStrictMatcher("");
        assertTrue(matcher1 instanceof EmptyVariablePatternMatcher);
        final VariablePatternMatcher matcher2 = SingleCharacterVariablePatternMatcher.createNonStrictMatcher("");
        assertTrue(matcher2 instanceof EmptyVariablePatternMatcher);
        assertSame(matcher1, matcher2);
    }

    @Test
    public void testMatches() {
        assertTrue(emptyVariablePatternMatcher.matches(""));
        assertTrue(emptyVariablePatternMatcher.matches(null));
        assertTrue(emptyVariablePatternMatcher.matches("abba"));
        assertTrue(emptyVariablePatternMatcher.matches("dogcatcatdog"));
        assertTrue(emptyVariablePatternMatcher.matches("a"));
    }

    @Test
    public void testGetVariableAssignments() {
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments(null).isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("abba").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("dogcatcatdog").isEmpty());
        assertTrue(emptyVariablePatternMatcher.getVariableAssignments("a").isEmpty());
    }
}