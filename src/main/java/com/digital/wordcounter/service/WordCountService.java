package com.digital.wordcounter.service;

import com.digital.wordcounter.exceptions.InvalidInputException;

/**
 * Interface for counting words in a given text.
 * Implementations of this interface should handle edge cases like null or empty input.
 */
public interface WordCountService {

    /**
     * Count the total number of words in a given sentence.
     * @param input the text to count words in.
     * @param isFileInput flag to indicate if the input is from a file or not.
     * @return number of words in the give text.
     */
    long countWords(String input, boolean isFileInput) throws InvalidInputException;
}
