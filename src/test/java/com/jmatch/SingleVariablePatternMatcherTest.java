package com.jmatch;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleVariablePatternMatcherTest {

    @Test
    public void testMatchesValid() {
        final SingleVariablePatternMatcher matcher = new SingleVariablePatternMatcher("abba");
        assertTrue(matcher.matches("dogcatcatdog"));
        assertTrue(matcher.matches("HelloGeorgeByeByeHelloGeorge"));
        assertTrue(matcher.matches("abba"));
        assertTrue(matcher.matches("abbbba"));
        assertTrue(matcher.matches("baab"));
    }

    @Test
    public void testMatchesInvalid() {
        final SingleVariablePatternMatcher matcher = new SingleVariablePatternMatcher("abba");
        assertFalse(matcher.matches("dogcat"));
        assertFalse(matcher.matches("abbba"));
        assertFalse(matcher.matches(""));
    }
}
