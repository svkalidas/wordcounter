package com.digital.wordcounter.constants;

/**
 * Contains application-wide constants.
 */
public final class ApplicationConstants {

    private ApplicationConstants() {}

    /** Prompt message for user input. */
    public static final String PROMPT_MESSAGE = "Enter text:";

    /** Response message for word count. */
    public static final String PROMPT_RESPONSE = "Number of words: ";

    public  static final String WORD_SPLIT_REGEX ="[\\s\\p{Punct}]+";

    public static final String WORK_MATCH_REGEX = "[a-zA-Z]+";
    // Add more constants as needed
}
