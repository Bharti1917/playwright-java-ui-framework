package com.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JsonDataReader - Utility class to read test data from JSON files
 */
public class JsonDataReader {
    private static final Logger logger = LogManager.getLogger(JsonDataReader.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TEST_DATA_PATH = "src/test/resources/test-data/";

    /**
     * Read JSON file and return as Map
     */
    public static Map<String, Object> readJsonAsMap(String fileName) {
        try {
            File file = new File(TEST_DATA_PATH + fileName);
            logger.info("Reading JSON file: {}", file.getAbsolutePath());
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            logger.error("Failed to read JSON file: {}", fileName, e);
            throw new RuntimeException("Failed to read test data from: " + fileName, e);
        }
    }

    /**
     * Read JSON file and return as List
     */
    public static List<Map<String, Object>> readJsonAsList(String fileName) {
        try {
            File file = new File(TEST_DATA_PATH + fileName);
            logger.info("Reading JSON file: {}", file.getAbsolutePath());
            return objectMapper.readValue(file, List.class);
        } catch (IOException e) {
            logger.error("Failed to read JSON file: {}", fileName, e);
            throw new RuntimeException("Failed to read test data from: " + fileName, e);
        }
    }

    /**
     * Read JSON file and return as specific type
     */
    public static <T> T readJson(String fileName, Class<T> valueType) {
        try {
            File file = new File(TEST_DATA_PATH + fileName);
            logger.info("Reading JSON file: {}", file.getAbsolutePath());
            return objectMapper.readValue(file, valueType);
        } catch (IOException e) {
            logger.error("Failed to read JSON file: {}", fileName, e);
            throw new RuntimeException("Failed to read test data from: " + fileName, e);
        }
    }

    /**
     * Get specific test data by key
     */
    public static Object getTestData(String fileName, String key) {
        Map<String, Object> data = readJsonAsMap(fileName);
        logger.debug("Retrieved test data for key: {}", key);
        return data.get(key);
    }
}
