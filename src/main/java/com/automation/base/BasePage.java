package com.automation.base;

import com.automation.config.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

/**
 * BasePage - Parent class for all Page Object classes
 * Contains common methods for page interactions
 */
public class BasePage {
    protected Page page;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected ConfigReader config;

    public BasePage(Page page) {
        this.page = page;
        this.config = ConfigReader.getInstance();
    }

    /**
     * Navigate to a specific URL
     */
    protected void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        page.navigate(url);
    }

    /**
     * Click on an element
     */
    protected void click(String selector) {
        logger.debug("Clicking element: {}", selector);
        waitForElement(selector);
        page.locator(selector).click();
    }

    /**
     * Fill text in an input field
     */
    protected void fill(String selector, String text) {
        logger.debug("Filling element {} with text: {}", selector, text);
        waitForElement(selector);
        page.locator(selector).fill(text);
    }

    /**
     * Get text from an element
     */
    protected String getText(String selector) {
        logger.debug("Getting text from element: {}", selector);
        waitForElement(selector);
        return page.locator(selector).textContent();
    }

    /**
     * Check if element is visible
     */
    protected boolean isVisible(String selector) {
        logger.debug("Checking visibility of element: {}", selector);
        try {
            return page.locator(selector).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for element to be visible
     */
    protected void waitForElement(String selector) {
        logger.debug("Waiting for element: {}", selector);
        page.locator(selector).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(config.getTimeout()));
    }

    /**
     * Wait for element to be hidden
     */
    protected void waitForElementToDisappear(String selector) {
        logger.debug("Waiting for element to disappear: {}", selector);
        page.locator(selector).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(config.getTimeout()));
    }

    /**
     * Get page title
     */
    protected String getTitle() {
        String title = page.title();
        logger.info("Page title: {}", title);
        return title;
    }

    /**
     * Get current URL
     */
    protected String getCurrentUrl() {
        String url = page.url();
        logger.info("Current URL: {}", url);
        return url;
    }

    /**
     * Select dropdown option by visible text
     */
    protected void selectByVisibleText(String selector, String text) {
        logger.debug("Selecting option '{}' from dropdown: {}", text, selector);
        page.locator(selector).selectOption(text);
    }

    /**
     * Take screenshot and attach to Allure report
     */
    protected void takeScreenshot(String screenshotName) {
        try {
            byte[] screenshot = page.screenshot();
            Allure.addAttachment(screenshotName, "image/png",
                new java.io.ByteArrayInputStream(screenshot), "png");
            logger.info("Screenshot captured: {}", screenshotName);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", screenshotName, e);
        }
    }

    /**
     * Scroll to element
     */
    protected void scrollToElement(String selector) {
        logger.debug("Scrolling to element: {}", selector);
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Check if element is enabled
     */
    protected boolean isEnabled(String selector) {
        logger.debug("Checking if element is enabled: {}", selector);
        return page.locator(selector).isEnabled();
    }

    /**
     * Get attribute value
     */
    protected String getAttribute(String selector, String attribute) {
        logger.debug("Getting attribute '{}' from element: {}", attribute, selector);
        return page.locator(selector).getAttribute(attribute);
    }

    /**
     * Wait for page load
     */
    protected void waitForPageLoad() {
        logger.debug("Waiting for page to load");
        page.waitForLoadState();
    }
}
