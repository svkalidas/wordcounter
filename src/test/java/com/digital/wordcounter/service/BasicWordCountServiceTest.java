package com.digital.wordcounter.service;

import com.digital.wordcounter.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicWordCountServiceTest {

    private BasicWordCountService wordCountService;

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

}
