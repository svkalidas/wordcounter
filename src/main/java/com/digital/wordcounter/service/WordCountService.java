package com.digital.wordcounter.service;

public interface WordCountService {

    /**
     * Count the total number of words in a given sentence.
     * @param inputText
     * @return number of words in the give text.
     */
    int countWords(String inputText);
}
