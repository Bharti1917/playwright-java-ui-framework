package com.automation.base;

import com.automation.config.ConfigReader;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BaseTest - Parent class for all test classes
 * Handles browser initialization and cleanup
 */
public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ConfigReader config;

    @BeforeClass
    @Parameters({"browser"})
    public void setupClass(@Optional String browserType) {
        config = ConfigReader.getInstance();
        logger.info("===== Starting Test Execution =====");
        logger.info("Browser: {}", browserType != null ? browserType : config.getBrowser());
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional String browserType) {
        logger.info("Setting up test environment");

        // Initialize Playwright
        playwright = Playwright.create();

        // Determine browser type
        String browser = browserType != null ? browserType : config.getBrowser();

        // Launch browser
        this.browser = launchBrowser(browser);

        // Create browser context
        context = this.browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));

        // Enable tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));

        // Create new page
        page = context.newPage();

        // Navigate to base URL
        page.navigate(config.getBaseUrl());
        logger.info("Navigated to base URL: {}", config.getBaseUrl());
    }

    /**
     * Launch browser based on type
     */
    private Browser launchBrowser(String browserType) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(config.isHeadless());

        logger.info("Launching browser: {} (Headless: {})", browserType, config.isHeadless());

        return switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit", "safari" -> playwright.webkit().launch(launchOptions);
            default -> playwright.chromium().launch(launchOptions);
        };
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Tearing down test environment");

        // Capture screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE && config.isScreenshotOnFailure()) {
            captureScreenshot(result.getName());
        }

        // Stop tracing
        if (context != null) {
            String tracePath = "target/traces/" + result.getName() + ".zip";
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(tracePath)));
            logger.info("Trace saved to: {}", tracePath);
        }

        // Close browser
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

        logger.info("Test completed: {} - Status: {}",
                result.getName(),
                result.getStatus() == ITestResult.SUCCESS ? "PASSED" : "FAILED");
    }

    @AfterClass
    public void tearDownClass() {
        logger.info("===== Test Execution Completed =====");
    }

    /**
     * Capture screenshot on test failure
     */
    private void captureScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timestamp;

            byte[] screenshot = page.screenshot();

            // Attach to Allure report
            Allure.addAttachment(screenshotName, "image/png",
                    new java.io.ByteArrayInputStream(screenshot), "png");

            logger.info("Screenshot captured for failed test: {}", screenshotName);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
        }
    }
}
