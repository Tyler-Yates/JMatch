package com.jmatch.matcher;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class SingleLetterVariablePatternMatcherTest {

    @Test
    public void testMatchesValid() {
        final SingleLetterVariablePatternMatcher matcher = new SingleLetterVariablePatternMatcher("abba");
        assertTrue(matcher.matches("dogcatcatdog"));
        assertTrue(matcher.matches("HelloGeorgeByeByeHelloGeorge"));
        assertTrue(matcher.matches("abba"));
        assertTrue(matcher.matches("abbbba"));
        assertTrue(matcher.matches("baab"));
        assertTrue(matcher.matches("aaaa"));
        assertTrue(matcher.matches("aaaaaa"));
    }

    @Test
    public void testMatchesInvalid() {
        final SingleLetterVariablePatternMatcher matcher = new SingleLetterVariablePatternMatcher("abba");
        assertFalse(matcher.matches("dogcat"));
        assertFalse(matcher.matches("abbba"));
        assertFalse(matcher.matches(""));
        assertFalse(matcher.matches("aaaaa"));
    }
}
