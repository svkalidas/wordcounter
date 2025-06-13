package com.digital.wordcounter.test.config;

import static com.digital.wordcounter.constants.ApplicationConstants.CONFIG_FILE;

/**
 * Utility class for test setup, specifically for initializing configuration properties.
 * This class is used to set up the environment for tests by specifying the path to the configuration properties file.
 */
public final class TestBaseConfiguration {

    private static final String CONFIG_PROPERTIES_PATH = "src/test/resources/config.properties";

    private TestBaseConfiguration() {
        // Prevent instantiation
    }

    public static void initializeConfigProperties() {
        System.setProperty(CONFIG_FILE, CONFIG_PROPERTIES_PATH);
    }
}