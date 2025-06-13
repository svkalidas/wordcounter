package com.digital.wordcounter.util;

import com.digital.wordcounter.exceptions.ApplicationConfigurationException;
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
        assertTrue(StopWordsUtil.isStopWord("on"), "Word 'on' should be a stopword");
    }

    @Test
    void shouldHandleMissingStopWordsFileGracefully() {
        System.setProperty("config.properties", "non_existent_file.txt");
        StopWordsUtil.loadStopWords();

        assertFalse(StopWordsUtil.isStopWord("a"), "No stopwords should be loaded for a missing file");
    }

    @Test
    void shouldReturnFalseForNullOrBlankWord() {
        assertFalse(StopWordsUtil.isStopWord(null), "Null input should return false");
        assertFalse(StopWordsUtil.isStopWord("   "), "Blank input should return false");
    }

}
