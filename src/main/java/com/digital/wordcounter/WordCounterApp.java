package com.digital.wordcounter;

import com.digital.wordcounter.constants.ApplicationConstants;
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

public class WordCounterApp {

    private static final Logger LOG = LoggerFactory.getLogger(WordCounterApp.class);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(PROMPT_MESSAGE);
            String inputText = scanner.nextLine();

            // Specify the type of service to use
            WordCountService wordCountService = WordCountServiceFactory.createWordCountService(BASIC);
            long wordCount = wordCountService.countWords(inputText);
            System.out.println( PROMPT_RESPONSE + wordCount);
            LOG.info("Processed input successfully. Word count: {}", wordCount);
        } catch (InvalidInputException | ServiceCreationException e){
            System.err.println(e.getMessage());
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            System.err.println(MessageUtil.getMessage("error.unexpected.error", e.getMessage()));
            LOG.error(MessageUtil.getMessage("error.unexpected.error", e.getMessage()), e);
        }
    }
}
