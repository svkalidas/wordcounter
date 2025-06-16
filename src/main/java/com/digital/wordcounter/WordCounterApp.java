package com.digital.wordcounter;

import com.digital.wordcounter.exceptions.InvalidInputException;
import com.digital.wordcounter.exceptions.ServiceCreationException;
import com.digital.wordcounter.service.WordCountService;
import com.digital.wordcounter.service.factory.WordCountServiceFactory;
import com.digital.wordcounter.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.digital.wordcounter.constants.ApplicationConstants.PROMPT_MESSAGE;
import static com.digital.wordcounter.constants.ApplicationConstants.PROMPT_RESPONSE;
import static com.digital.wordcounter.service.factory.WordCountServiceFactory.WordCountServiceType.BASIC;

/**
 * Main application class for the Word Counter application.
 * It reads input from the user or a file, counts the words using the specified service,
 * and prints the word count to the console.
 */
public class WordCounterApp {

    private static final Logger LOG = LoggerFactory.getLogger(WordCounterApp.class);

    public static void main(String[] args) {

        try {
            String input = null;
            boolean isFileInput = args.length > 0;
            if(isFileInput){
                input = args[0];
            }else {
                try (Scanner scanner = new Scanner(System.in)) {
                    System.out.println(PROMPT_MESSAGE);
                    input = scanner.nextLine();
                }
            }
            // Specify the type of service to use
            WordCountService wordCountService = WordCountServiceFactory.INSTANCE.createWordCountService(BASIC);
            long wordCount = wordCountService.countWords(input, isFileInput);
            System.out.println(PROMPT_RESPONSE + wordCount);
            LOG.info("Processed input successfully. Word count: {}", wordCount);
        } catch (InvalidInputException | ServiceCreationException e) {
            LOG.error(e.getMessage(), e);
            System.err.println(e.getMessage());
        } catch (Exception e) {
            LOG.error(MessageUtil.getMessage("error.unexpected.error", e.getMessage()), e);
            System.err.println(MessageUtil.getMessage("error.unexpected.error", e.getMessage()));
        }
    }
}
