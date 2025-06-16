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
        long wordCount = wordCountService.countWords(input, false);
        assertEquals(4, wordCount, "Word count should be 5");
    }

    @Test
    void testCountWordsWithNonAlphabeticCharacters() throws InvalidInputException {
        String input = "Hello, world! 123";
        long wordCount = wordCountService.countWords(input, false);
        assertEquals(2, wordCount, "Word count should be 2 (only alphabetic words)");
    }

    @Test
    void testCountWordsEmptyInput() {
        String input = "   ";
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords(input, false), "Should throw InvalidInputException for empty input");
    }

    @Test
    void testCountWordsNullInput() {
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords(null, false), "Should throw InvalidInputException for null input");
    }

    @Test
    void shouldReturnZeroForOnlyStopWords() throws InvalidInputException {
        String input = "On The the on THE ON";
        long wordCount = wordCountService.countWords(input, false);
        assertEquals(0, wordCount, "Word count should be 0 as all words are stopwords");
    }

    @Test
    void shouldCountWordsForMixedInput() throws InvalidInputException {
        String input = "The cat sat on the mat";
        long wordCount = wordCountService.countWords(input, false);
        // Excluding "The" as a stopword
        assertEquals(3, wordCount, "Word count should exclude stopwords");
    }

    @Test
    void shouldCountWordsFromValidFileInput() throws InvalidInputException {
        String validFilePath = "src/test/resources/inputTest.txt";
        long wordCount = wordCountService.countWords(validFilePath, true);
        assertEquals(4, wordCount, "Word count should match the number of valid words in the file (Excluding stopwords");
    }

    @Test
    void shouldHandleNullAndInvalidFilePathInput() {
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords(null, true),
                "Null file path should throw InvalidInputException");
        assertThrows(InvalidInputException.class, () -> wordCountService.countWords("invalid.txt", true),
                "Null file path should throw InvalidInputException");
    }



}
