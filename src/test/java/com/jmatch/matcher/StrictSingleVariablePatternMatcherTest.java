package com.jmatch.matcher;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class StrictSingleVariablePatternMatcherTest {

    @Test
    public void testMatchesValid() {
        final StrictSingleVariablePatternMatcher matcher = new StrictSingleVariablePatternMatcher("abba");
        assertTrue(matcher.matches("dogcatcatdog"));
        assertTrue(matcher.matches("HelloGeorgeByeByeHelloGeorge"));
        assertTrue(matcher.matches("abba"));
        assertTrue(matcher.matches("abbbba"));
        assertTrue(matcher.matches("baab"));
        assertTrue(matcher.matches("aaaaaa"));
    }

    @Test
    public void testMatchesInvalid() {
        final StrictSingleVariablePatternMatcher matcher = new StrictSingleVariablePatternMatcher("abba");
        assertFalse(matcher.matches("dogcat"));
        assertFalse(matcher.matches("abbba"));
        assertFalse(matcher.matches(""));
        assertFalse(matcher.matches("aaaa"));
        assertFalse(matcher.matches("aaaaa"));
    }
}