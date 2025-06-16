package com.digital.wordcounter.util;

import com.digital.wordcounter.constants.ApplicationConstants;
import com.digital.wordcounter.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.digital.wordcounter.constants.ApplicationConstants.WORD_SPLIT_REGEX;

/**
 * The utility class for reading data from external files or resources.
 */
public class DataSourceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceUtil.class);

    private static final Pattern FILE_PATH_PATTERN = Pattern.compile(ApplicationConstants.FILE_PATH_REGEX);

    /**
     * Validates the file path against a regular expression.
     *
     * @param filePath the path to the file
     * @return true if the file path is valid, false otherwise
     */
    public static boolean isValidFilePath(String filePath) {
        return filePath != null && !filePath.isBlank() && FILE_PATH_PATTERN.matcher(filePath).matches();
    }

    /**
     * Reads data from a specified file path or resource.
     * @param filePath the path to the file or resource
     */
    public static List<String> getDataFromSource(String filePath) throws InvalidInputException {

        try (BufferedReader reader = getDataReader(filePath)) {
            return reader.lines()
                    .flatMap(line -> Pattern.compile(WORD_SPLIT_REGEX).splitAsStream(line))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .toList();
        } catch (Exception e) {
            LOGGER.error(MessageUtil.getMessage("error.reading.data.file", e.getMessage()));
            throw new InvalidInputException(MessageUtil.getMessage("error.reading.data.file", filePath));
        }
    }

    /**
     * Gets a BufferedReader for the specified file path or resource.
     *
     * @param filePath the path to the file or resource
     * @return a BufferedReader for reading the data
     * @throws IOException if an I/O error occurs
     */
    private static BufferedReader getDataReader(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        // Check if the file exists in the provided directory, else Fallback to resource folder
        return Files.isReadable(path) ? Files.newBufferedReader(path) :
                Optional.ofNullable(
                                DataSourceUtil.class.getClassLoader().getResourceAsStream(filePath))
                        .map(InputStreamReader::new)
                        .map(BufferedReader::new)
                        .orElseThrow(() -> new InvalidInputException(MessageUtil.getMessage("error.invalid.file.path", filePath)));
    }
}
