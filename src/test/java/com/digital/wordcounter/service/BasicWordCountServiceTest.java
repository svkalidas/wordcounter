package com.digital.wordcounter.service;

import com.digital.wordcounter.exceptions.InvalidInputException;
import com.digital.wordcounter.test.config.TestBaseConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicWordCountServiceTest {

    private BasicWordCountService wordCountService;

    @BeforeAll
    static void initializeConfiguration() {
        TestBaseConfiguration.initializeConfigProperties();
    }

    @BeforeEach
    void setUp() {
        wordCountService = new BasicWordCountService();
    }

    @Test
    void testCountWordsValidInput() throws InvalidInputException {
        String input = "Mary had a little lamb";
        long wordCount = wordCountService.countWords(input);
        assertEquals(5, wordCount, "Word count should be 5");
    }

    @Test
    void testCountWordsWithNonAlphabeticCharacters() throws InvalidInputException {
        String input = "Hello, world! 123";
        long wordCount = wordCountService.countWords(input);
        assertEquals(2, wordCount, "Word count should be 2 (only alphabetic words)");
    }

    @Test
    void testCountWordsEmptyInput() {
        String input = "   ";
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords(input), "Should throw InvalidInputException for empty input");
    }

    @Test
    void testCountWordsNullInput() {
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords(null), "Should throw InvalidInputException for null input");
    }

    @Test
    void shouldReturnZeroForOnlyStopWords() throws InvalidInputException {
        String input = "On The the on THE ON";
        long wordCount = wordCountService.countWords(input);
        assertEquals(0, wordCount, "Word count should be 0 as all words are stopwords");
    }

    @Test
    void shouldCountWordsForMixedInput() throws InvalidInputException {
        String input = "The cat sat on the mat";
        long wordCount = wordCountService.countWords(input);
        // Excluding "The" as a stopword
        assertEquals(3, wordCount, "Word count should exclude stopwords");
    }

}
