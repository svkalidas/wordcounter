package com.digital.wordcounter.exceptions;

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
        super(message);
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
