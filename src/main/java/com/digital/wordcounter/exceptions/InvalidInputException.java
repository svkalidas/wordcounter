package com.digital.wordcounter.exceptions;

/**
 * Custom exception to handle invalid input scenarios in the Word Counter application.
 */
public class InvalidInputException extends Exception{

    /**
     * Default constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super();
    }

    /**
     * Constructor with a custom error message.
     *
     * @param message the error message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
