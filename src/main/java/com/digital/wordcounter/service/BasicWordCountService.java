package com.digital.wordcounter.service;

import com.digital.wordcounter.constants.ApplicationConstants;
import com.digital.wordcounter.exceptions.InvalidInputException;
import com.digital.wordcounter.util.DataSourceUtil;
import com.digital.wordcounter.util.MessageUtil;
import com.digital.wordcounter.util.StopWordsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.digital.wordcounter.constants.ApplicationConstants.WORD_SPLIT_REGEX;
import static com.digital.wordcounter.util.DataSourceUtil.isValidFilePath;

/**
 * Basic implementation of the WordCountService interface that counts words in a given input.
 * It filters out stop words and counts only valid words based on a regex pattern.
 */
public class BasicWordCountService implements WordCountService {
    private static final Logger logger = LoggerFactory.getLogger(BasicWordCountService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public long countWords(String input, boolean isFileInput) throws InvalidInputException {

        try (Stream<String> wordsStream = getWordsStream(input, isFileInput)) {
            return wordsStream.filter(word -> word.matches(ApplicationConstants.WORK_MATCH_REGEX) && !StopWordsUtil.isStopWord(word)).count();
        } catch (Exception e) {
            logger.error(MessageUtil.getMessage("error.unexpected.error", e.getMessage()), e);
            throw e;
        }
    }

    /**
     * Creates a stream of words from the input text or input file.
     *
     * @param input       the input text or file path
     * @param isFileInput flag to indicate if the input is from a file or not
     * @return a stream of words
     */
    private Stream<String> getWordsStream(String input, boolean isFileInput) throws InvalidInputException {
        if (isFileInput) {
            if (!isValidFilePath(input)) {
                throw new InvalidInputException(MessageUtil.getMessage("error.invalid.data.source"));
            }
            return DataSourceUtil.getDataFromSource(input).stream();
        } else {
            validateInput(input);
        }
        return Arrays.stream(input.split(WORD_SPLIT_REGEX));
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
