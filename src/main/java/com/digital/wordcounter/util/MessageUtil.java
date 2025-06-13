package com.digital.wordcounter.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class for retrieving messages from the resource bundle.
 */
public class MessageUtil {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("message");

    /**
     * Retrieves a message for the given key from the resource bundle.
     *
     * @param key the key for the desired message
     * @return the message corresponding to the key
     * @throws IllegalArgumentException if the key is not found
     */
    public static String getMessage(String key, Object... args) {
        try {
            String pattern = MESSAGES.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException("Message key not found: " + key, e);
        }
    }
}