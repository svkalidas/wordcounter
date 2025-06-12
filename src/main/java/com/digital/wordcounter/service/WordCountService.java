package com.digital.wordcounter.service;

import com.digital.wordcounter.exceptions.InvalidInputException;

/**
 * Interface for counting words in a given text.
 * Implementations of this interface should handle edge cases like null or empty input.
 */
public interface WordCountService {

    /**
     * Count the total number of words in a given sentence.
     * @param inputText the text to count words in.
     * @return number of words in the give text.
     */
    long countWords(String inputText) throws InvalidInputException;
}
