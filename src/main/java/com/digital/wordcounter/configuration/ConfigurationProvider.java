package com.digital.wordcounter.configuration;

/**
 * Interface for providing configuration properties.
 */
public interface ConfigurationProvider {

    /**
     * Retrieves the value of a configuration property by its key.
     *
     * @param key the key of the configuration property
     * @return the value of the configuration property, or null if not found
     */
    String getProperty(String key);
}
