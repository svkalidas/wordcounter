package com.digital.wordcounter.util;

import com.digital.wordcounter.configuration.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.digital.wordcounter.constants.ApplicationConstants.DEFAULT_STOPWORDS_FILE_NAME;
import static com.digital.wordcounter.constants.ApplicationConstants.DEFAULT_STOPWORDS_FILE_PATH_CONFIG;
import static com.digital.wordcounter.util.DataSourceUtil.getDataFromSource;
import static com.digital.wordcounter.util.DataSourceUtil.isValidFilePath;

/**
 * Utility class for managing stopwords.
 */
public class StopWordsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopWordsUtil.class);
    private static final Set<String> STOP_WORDS = new HashSet<>();

    // Static block to initialize stopwords
    static {
        loadStopWords();
    }


    private StopWordsUtil() {
        // Prevent instantiation
    }

    /**
     * Loads stopwords from the specified file.
     */
    public static void loadStopWords() {

        String filePath = Optional.ofNullable(ConfigProperties.getProperty(DEFAULT_STOPWORDS_FILE_PATH_CONFIG))
                .orElse(DEFAULT_STOPWORDS_FILE_NAME);

        if (isValidFilePath(filePath)) {
            try {
                STOP_WORDS.clear();
                STOP_WORDS.addAll(getDataFromSource(filePath));
            } catch (Exception e) {
                LOGGER.error(MessageUtil.getMessage("error.invalid.stopwords.file", e.getMessage()));
            }
        } else {
            LOGGER.info(MessageUtil.getMessage("info.missing.stopwords.file"));
        }
    }

    /**
     * Checks if a word is a stopword.
     *
     * @param word the word to check
     * @return true if the word is a stopword, false otherwise
     */
    public static boolean isStopWord(String word) {
        if (word == null || word.isBlank()) {
            return false;
        }
        return STOP_WORDS.contains(word.toLowerCase());
    }

}
