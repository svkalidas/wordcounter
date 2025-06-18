package com.digital.wordcounter.configuration;

import com.digital.wordcounter.exceptions.ApplicationConfigurationException;
import com.digital.wordcounter.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * This class provides configuration properties loaded from a specified file.
 * It implements the ConfigurationProvider interface to retrieve property values.
 */
public class FileConfigurationProvider implements ConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileConfigurationProvider.class);
    private final Properties properties = new Properties();

    /**
     * Constructs a FileConfigurationProvider with the specified configuration file.
     * @param configFile the name of the configuration file to load properties from
     */
    public FileConfigurationProvider(String configFile) {
        loadConfigurationProperties(configFile);
    }

    /**
     * Loads configuration properties from the specified file.
     * If the file is not found, an ApplicationConfigurationException is thrown.
     *
     * @param configFile the name of the configuration file to load
     */
    private void loadConfigurationProperties(String configFile) {
        try (InputStream input = Optional.ofNullable(
                        getClass().getClassLoader().getResourceAsStream(configFile))
                .orElseThrow(ApplicationConfigurationException::new)) {
            properties.load(input);
        } catch (IOException | ApplicationConfigurationException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            LOGGER.warn(MessageUtil.getMessage("info.missing.config.properties", key));
        }
        return value;
    }
}
