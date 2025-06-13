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

    /** Regular expression to split words by whitespace and punctuation. */
    public  static final String WORD_SPLIT_REGEX ="[\\s\\p{Punct}]+";

    /** Regular expression to match valid words (alphabetic characters only). */
    public static final String WORK_MATCH_REGEX = "[a-zA-Z]+";

    /** Default config file name. */
    public static final String CONFIG_FILE = "config.properties";

    /** Default config property for stopwords file. */
    public static final String DEFAULT_STOPWORDS_FILE_PATH_CONFIG = "stopwords.file.path";

    /** Default name of the stopwords file. */
    public static final String DEFAULT_STOPWORDS_FILE_NAME = "stopwords.txt";

    /** Regular expression to validate file paths. */
    public static final String FILE_PATH_REGEX =  "^([a-zA-Z]:[\\\\/])?((?:[a-zA-Z0-9_\\-]+[\\\\/])*)([a-zA-Z0-9_\\-]+\\.[a-zA-Z0-9]+)$";

    // Add more constants as needed
}
