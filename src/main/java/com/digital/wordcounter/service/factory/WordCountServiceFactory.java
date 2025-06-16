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

    public static final WordCountServiceFactory INSTANCE = new WordCountServiceFactory();

    private WordCountServiceFactory() {
        // Private constructor to prevent instantiation
        initServiceRegistry();
    }

    /**
     * Enum representing the types of WordCountService available.
     * Currently, only a basic implementation is provided.
     */
    public enum WordCountServiceType {
        BASIC;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /** A registry to hold the available WordCountService implementations. */
    private final Map<WordCountServiceType, WordCountService> serviceRegistry = new HashMap<>();

    /**
     * Initializes the service registry with available WordCountService implementations.
     * This method is called once when the class is loaded.
     */
    protected void initServiceRegistry() {
        serviceRegistry.put(WordCountServiceType.BASIC, new BasicWordCountService());
        LOG.info("WordCountServiceFactory initialized with available services: {}", serviceRegistry.keySet());
    }


    /**
     * Creates a WordCountService instance based on the specified type.
     *
     * @param type the type of WordCountService to create
     * @return the WordCountService instance
     * @throws ServiceCreationException if the service type is invalid
     */
    public  WordCountService createWordCountService(WordCountServiceType type) throws ServiceCreationException {
        WordCountService service = serviceRegistry.get(type);
        if (service == null) {
            LOG.error("Invalid service type: {}", type);
            throw new ServiceCreationException(type.toString());
        }
        return service;
    }

    /**
     * Returns a read-only view of the registered WordCountService implementations.
     *
     * @return a map of WordCountServiceType to WordCountService
     */
    public Map<WordCountServiceType, WordCountService> getRegisteredServices() {
        return Map.copyOf(serviceRegistry);
    }
}
