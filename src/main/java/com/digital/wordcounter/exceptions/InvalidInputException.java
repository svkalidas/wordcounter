package com.digital.wordcounter.exceptions;

import com.digital.wordcounter.util.MessageUtil;

/**
 * Custom exception to handle invalid input scenarios in the Word Counter application.
 */
public class InvalidInputException extends Exception{

    /**
     * Default constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super(MessageUtil.getMessage("error.empty.input"));
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
