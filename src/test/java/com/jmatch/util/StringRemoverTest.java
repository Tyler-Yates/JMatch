package com.jmatch.util;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class StringRemoverTest {

    @Test
    public void testGetRemovalPermutations() {
        assertThat(StringRemover.getRemovalPermutations("bob", 1, "bob*bob**bob"),
                IsIterableContainingInAnyOrder.containsInAnyOrder("*bob**bob", "bob***bob", "bob*bob**"));
        assertThat(StringRemover.getRemovalPermutations("bob", 2, "bob*bob**bob"),
                IsIterableContainingInAnyOrder.containsInAnyOrder("***bob", "*bob**", "bob***"));

        assertTrue(StringRemover.getRemovalPermutations("cat", 1, "bob*bob**bob").isEmpty());
    }
}
