package com.digital.wordcounter.configuration;

import com.digital.wordcounter.exceptions.ApplicationConfigurationException;
import com.digital.wordcounter.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static com.digital.wordcounter.constants.ApplicationConstants.CONFIG_FILE;

/**
 * This class loads properties from a file named "config.properties"
 * config.properties is an optional file.
 */
public final class ConfigProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigProperties.class);
    private static final Properties PROPERTIES = new Properties();

    private ConfigProperties() {
        // Prevent instantiation
    }

    /*
      Static block to load configuration properties when the class is loaded.
     */
    static {
        loadConfigurationProperties();
    }

    private static void loadConfigurationProperties() {
        try (InputStream input = Optional.ofNullable(ConfigProperties.class.getClassLoader().getResourceAsStream(CONFIG_FILE)).orElseThrow(ApplicationConfigurationException::new) ) {
            PROPERTIES.load(input);
        } catch (IOException | ApplicationConfigurationException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }


    /**
     * Retrieves the value of a property by its key.
     *
     * @param key the property key
     * @return the property value, or null if the key is not found
     */
    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            LOGGER.warn(MessageUtil.getMessage("info.missing.config.properties", key));
        }
        return value;
    }
}
