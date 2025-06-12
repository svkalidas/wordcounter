package com.digital.wordcounter;

import com.digital.wordcounter.service.WordCountService;
import com.digital.wordcounter.service.factory.WordCountServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.digital.wordcounter.service.factory.WordCountServiceFactory.WordCountServiceType.BASIC;

public class WordCounterApp {

    private static final Logger LOG = LoggerFactory.getLogger(WordCounterApp.class);
    private static final String PROMPT_MESSAGE = "Enter text:";
    private static final String ERROR_MESSAGE = "An unexpected error occurred: %s";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(PROMPT_MESSAGE);
            String inputText = scanner.nextLine();

            // Specify the type of service to use
            WordCountService wordCountService = WordCountServiceFactory.createWordCountService(BASIC);
            long wordCount = wordCountService.countWords(inputText);
            System.out.println("Number of words: " + wordCount);
            LOG.info("Processed input successfully. Word count: {}", wordCount);
        } catch (Exception e) {
            System.err.printf((ERROR_MESSAGE) + "%n", e.getMessage());
            LOG.error(ERROR_MESSAGE, e);
        }
    }
}
