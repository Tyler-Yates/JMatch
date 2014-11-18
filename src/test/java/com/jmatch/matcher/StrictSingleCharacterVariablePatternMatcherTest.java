package com.jmatch.matcher;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class StrictSingleCharacterVariablePatternMatcherTest {

    private StrictSingleCharacterVariablePatternMatcher matcher;

    @Before
    public void init() {
        matcher = new StrictSingleCharacterVariablePatternMatcher("abba");
    }

    @Test
    public void testMatchesValid() {
        assertTrue(matcher.matches("dogcatcatdog"));
        assertTrue(matcher.matches("HelloGeorgeByeByeHelloGeorge"));
        assertTrue(matcher.matches("abba"));
        assertTrue(matcher.matches("abbbba"));
        assertTrue(matcher.matches("baab"));
        assertTrue(matcher.matches("aaaaaa"));
    }

    @Test
    public void testMatchesInvalid() {
        assertFalse(matcher.matches("dogcat"));
        assertFalse(matcher.matches("abbba"));
        assertFalse(matcher.matches(""));
        assertFalse(matcher.matches("aaaa"));
        assertFalse(matcher.matches("aaaaa"));
    }

    @Test
    public void testGetVariableAssignmentValid() {
        assertEquals(ImmutableMap.of("a", "dog", "b", "cat"), matcher.getVariableAssignments("dogcatcatdog"));
        assertEquals(ImmutableMap.of("a", "HelloGeorge", "b", "Bye"), matcher.getVariableAssignments
                ("HelloGeorgeByeByeHelloGeorge"));
        assertEquals(ImmutableMap.of("a", "a", "b", "b"), matcher.getVariableAssignments("abba"));
        assertEquals(ImmutableMap.of("a", "a", "b", "bb"), matcher.getVariableAssignments("abbbba"));
        assertEquals(ImmutableMap.of("a", "b", "b", "a"), matcher.getVariableAssignments("baab"));

        // Multiple valid assignments
        final Map<String, String> variableAssignments = matcher.getVariableAssignments("aaaaaa");
        assertTrue(variableAssignments.equals(ImmutableMap.of("a", "aa", "b", "a")) || variableAssignments.equals
                (ImmutableMap.of("a", "a", "b", "aa")));
    }

    @Test
    public void testGetVariableAssignmentInvalid() {
        assertNull(matcher.getVariableAssignments("dogcat"));
        assertNull(matcher.getVariableAssignments("abbba"));
        assertNull(matcher.getVariableAssignments(""));
        assertNull(matcher.getVariableAssignments("aaaa"));
        assertNull(matcher.getVariableAssignments("aaaaa"));
    }
}