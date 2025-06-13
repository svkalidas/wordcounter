package com.digital.wordcounter.util;

import com.digital.wordcounter.configuration.ConfigProperties;
import com.digital.wordcounter.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static com.digital.wordcounter.constants.ApplicationConstants.DEFAULT_STOPWORDS_FILE_NAME;
import static com.digital.wordcounter.constants.ApplicationConstants.DEFAULT_STOPWORDS_FILE_PATH_CONFIG;

/**
 * Utility class for managing stopwords.
 */
public class StopWordsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopWordsUtil.class);
    private static final Pattern filePathPattern = Pattern.compile(ApplicationConstants.FILE_PATH_REGEX);
    private static final Set<String> STOPWORDS = new HashSet<>();

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

        if (isValidPath(filePath)) {
            try (BufferedReader reader = getFileReader(filePath)) {
                STOPWORDS.clear();
                reader.lines()
                        .map(String::trim)
                        .map(String::toLowerCase)
                        .forEach(STOPWORDS::add);
                LOGGER.info("Stopwords successfully loaded from file: {}", filePath);
            } catch (Exception e) {
                LOGGER.error(MessageUtil.getMessage("error.invalid.stopwords.file", e.getMessage()));
            }
        } else {
            LOGGER.info(MessageUtil.getMessage("info.missing.stopwords.file"));
        }
    }

    private static boolean isValidPath(String filePath) {
        return filePath != null && !filePath.isBlank() && filePathPattern.matcher(filePath).matches();
    }

    /**
     * Gets a BufferedReader for the specified file path.
     * First checks if the file exists in the provided directory, then falls back to the resource folder.
     *
     * @param filePath the path to the stopwords file
     * @return a BufferedReader for the stopwords file
     * @throws IOException if an I/O error occurs
     */
    private static BufferedReader getFileReader(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        // Check if the file exists in the provided directory
        if (Files.isReadable(path)) {
            return Files.newBufferedReader(path);
        }

        // Fallback to resource folder
        InputStreamReader resourceStream = Optional.ofNullable(
                        StopWordsUtil.class.getClassLoader().getResourceAsStream(filePath))
                .map(InputStreamReader::new)
                .orElseThrow(() -> new IOException("StopWords file not found in the location: " + filePath));
        return new BufferedReader(resourceStream);
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
        return STOPWORDS.contains(word.toLowerCase());

    }

}
