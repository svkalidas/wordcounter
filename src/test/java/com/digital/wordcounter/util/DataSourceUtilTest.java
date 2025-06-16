package com.digital.wordcounter.util;

import com.digital.wordcounter.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceUtilTest {

    @Test
    void shouldReturnTrueForValidFilePath() {
        String validFilePath1 = "C:/valid/path/to/file.txt";
        String validFilePath2 = "/path/to/file.txt";
        assertTrue(DataSourceUtil.isValidFilePath(validFilePath1), "Valid file path should return true");
        assertTrue(DataSourceUtil.isValidFilePath(validFilePath2), "Valid file path should return true");
    }

    @Test
    void shouldReturnFalseForInvalidFilePath() {
        String invalidFilePath = "invalid\\path\\file?.txt";
        assertFalse(DataSourceUtil.isValidFilePath(invalidFilePath), "Invalid file path should return false");
    }

    @Test
    void shouldThrowExceptionForNonExistentFile() {
        String nonExistentFilePath = "nonexistentfile.txt";
        assertThrows(InvalidInputException.class, () -> DataSourceUtil.getDataFromSource(nonExistentFilePath), "Non-existent file should throw InvalidInputException");
    }

    @Test
    void shouldReadDataFromValidFilePath() throws Exception {
        String validFilePath = "src/test/resources/stopwords.txt";
        List<String> data = DataSourceUtil.getDataFromSource(validFilePath);
        assertFalse(data.isEmpty(), "Data from valid file path should not be empty");
    }

    @Test
    void shouldThrowExceptionForNullFilePath() {
        assertThrows(InvalidInputException.class, () -> DataSourceUtil.getDataFromSource(null), "Null file path should throw InvalidInputException");
    }

}
