package com.digital.wordcounter.service.factory;

import com.digital.wordcounter.exceptions.ServiceCreationException;
import com.digital.wordcounter.service.BasicWordCountService;
import com.digital.wordcounter.service.WordCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to create instances of WordCountService based on the specified type.
 */
public class WordCountServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(WordCountServiceFactory.class);

    public enum WordCountServiceType {
        BASIC;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private static final Map<WordCountServiceType, WordCountService> serviceRegistry = new HashMap<>();

    static {
        // Register services
        serviceRegistry.put(WordCountServiceType.BASIC, new BasicWordCountService());
    }

    /**
     * Creates a WordCountService instance based on the specified type.
     *
     * @param type the type of WordCountService to create
     * @return the WordCountService instance
     * @throws ServiceCreationException if the service type is invalid
     */
    public static WordCountService createWordCountService(WordCountServiceType type) throws ServiceCreationException {
        WordCountService service = serviceRegistry.get(type);
        if (service == null) {
            LOG.error("Invalid service type: {}", type);
            throw new ServiceCreationException(type.toString());
        }
        return service;
    }
}
