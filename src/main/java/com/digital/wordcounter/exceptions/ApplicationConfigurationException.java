package com.digital.wordcounter.exceptions;

import com.digital.wordcounter.util.MessageUtil;


/**
 * Exception thrown when there is an issue with the application configuration.
 */
public class ApplicationConfigurationException extends Exception {

    /**
     * Default constructor for ApplicationConfigurationException.
     * Retrieves a default error message from the resource bundle.
     */
    public ApplicationConfigurationException() {
        super(MessageUtil.getMessage("error.missing.config.properties"));
    }

    /**
     * Constructor with a custom error message.
     *
     * @param message the custom error message
     */
    public ApplicationConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructor with a custom error message and a cause.
     * @param cause
     */
    public ApplicationConfigurationException(Throwable cause) {

        super(MessageUtil.getMessage("error.missing.config.properties"), cause);
    }
}
