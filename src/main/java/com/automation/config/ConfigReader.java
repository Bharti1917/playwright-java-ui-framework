package com.automation.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Utility class to read configuration properties
 * Implements Singleton pattern to ensure single instance
 */
public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    private ConfigReader() {
        loadProperties();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
            logger.info("Configuration properties loaded successfully from: {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration file: {}", CONFIG_FILE_PATH, e);
            throw new RuntimeException("Configuration file not found: " + CONFIG_FILE_PATH, e);
        }
    }

    public String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = properties.getProperty(key);
        }
        logger.debug("Retrieved property: {} = {}", key, value);
        return value;
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public int getNavigationTimeout() {
        return Integer.parseInt(getProperty("navigationTimeout"));
    }

    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshotOnFailure"));
    }

    public String getScreenshotPath() {
        return getProperty("screenshotPath");
    }

    public int getParallelTests() {
        return Integer.parseInt(getProperty("parallelTests"));
    }
}
