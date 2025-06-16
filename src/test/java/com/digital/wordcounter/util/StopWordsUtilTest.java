package com.digital.wordcounter.util;

import com.digital.wordcounter.test.config.TestBaseConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StopWordsUtilTest {

    @BeforeAll
    static void initializeConfiguration() {
        TestBaseConfiguration.initializeConfigProperties();
    }

    @Test
    void shouldLoadStopWordsFromFile() {
        StopWordsUtil.loadStopWords();

        assertTrue(StopWordsUtil.isStopWord("the"), "Word 'the' should be a stopword");
        assertTrue(StopWordsUtil.isStopWord("ThE"), "Word 'ThE' in mixed case should be recognized as a stopword");
    }

    @Test
    void shouldReturnFalseForNullOrBlankWord() {
        assertFalse(StopWordsUtil.isStopWord(null), "Null input should return false");
        assertFalse(StopWordsUtil.isStopWord("   "), "Blank input should return false");
        assertTrue(StopWordsUtil.isStopWord("ThE"), "Word 'ThE' in mixed case should be recognized as a stopword");
    }

}
