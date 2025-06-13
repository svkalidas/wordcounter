package com.digital.wordcounter.service;

import com.digital.wordcounter.constants.ApplicationConstants;
import com.digital.wordcounter.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class BasicWordCountService implements WordCountService{
    private static final Logger logger = LoggerFactory.getLogger(BasicWordCountService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public long countWords(String text) throws InvalidInputException {
        validateInput(text);
        long wordCount = Arrays.stream(text.split(ApplicationConstants.WORD_SPLIT_REGEX))
                .filter(word -> word.matches(ApplicationConstants.WORK_MATCH_REGEX)).count();
        logger.debug("Counted {} words in the text.", wordCount);
        return wordCount;
    }

    /**
     * Validates the input text to ensure it is not null or empty.
     *
     * @param input the input text to validate
     * @throws InvalidInputException if the input is null or empty
     */
    private static void validateInput(String input) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException();
        }
    }
}
