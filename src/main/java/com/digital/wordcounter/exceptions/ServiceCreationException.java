package com.digital.wordcounter.exceptions;

import com.digital.wordcounter.util.MessageUtil;

/**
 * Custom exception to handle service creation errors in the Word Counter application.
 */
public class ServiceCreationException extends Exception {
    /**
     * Default constructor for ServiceCreationException.
     */
    public ServiceCreationException() {
        super();
    }

    /**
     * Constructor with a custom error message.
     *
     * @param message the error message
     */
    public ServiceCreationException(String message) {
        super(MessageUtil.getMessage("error.invalid.service.type", message));
    }

    /**
     * Constructor with a custom error message and a cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public ServiceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
