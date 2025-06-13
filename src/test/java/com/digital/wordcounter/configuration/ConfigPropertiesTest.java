package com.digital.wordcounter.configuration;

import com.digital.wordcounter.test.config.TestBaseConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigPropertiesTest {

    @BeforeAll
    static void initializeConfiguration() {
        TestBaseConfiguration.initializeConfigProperties();
    }

    @Test
    void shouldLoadPropertySuccessfully() {
        String expectedValue = "src/test/resources/stopwords.txt"; // Replace with an actual value from your test config.properties
        String actualValue = ConfigProperties.getProperty("stopwords.file.path"); // Replace with an actual key
        assertEquals(expectedValue, actualValue, "Property value should match the expected value");
    }

    @Test
    void shouldReturnNullForMissingKey() {
        String value = ConfigProperties.getProperty("nonExistentKey");
        assertNull(value, "Should return null for a missing key");
    }
}
