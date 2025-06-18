package com.digital.wordcounter.configuration;

import static com.digital.wordcounter.constants.ApplicationConstants.CONFIG_FILE;

/**
 * This class loads properties from a file named "config.properties"
 * config.properties is an optional file.
 */
public final class ConfigProperties {

    /** The default configuration provider that reads from the config file.*/
    private static ConfigurationProvider provider = new FileConfigurationProvider(CONFIG_FILE);

    private ConfigProperties() {
        // Prevent instantiation
    }

    /**
     * Sets a custom configuration provider
     * @param newProvider the provider to use
     */
    public static void setProvider(ConfigurationProvider newProvider) {
        if (newProvider != null) {
            provider = newProvider;
        }
    }

    public static String getProperty(String key) {
        return provider.getProperty(key);
    }
}
