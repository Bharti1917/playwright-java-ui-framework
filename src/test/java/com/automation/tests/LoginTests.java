package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * LoginTests - Test class for Login functionality
 */
@Epic("Authentication")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Test(priority = 1, dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    @Description("Verify user can login with valid/invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Login")
    public void testLogin(Map<String, Object> data) {
        String testCase = (String) data.get("testCase");
        String email = (String) data.get("email");
        String password = (String) data.get("password");
        String expectedResult = (String) data.get("expectedResult");

        logger.info("Executing test case: {}", testCase);

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = homePage.clickLoginLink();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        loginPage.login(email, password);

        if (expectedResult.equals("success")) {
            Assert.assertTrue(homePage.isUserLoggedIn(),
                    "User should be logged in successfully");
            logger.info("Login successful for: {}", email);
        } else {
            Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
                    "Login error message should be displayed");
            logger.info("Login failed as expected for: {}", email);
        }
    }

    @Test(priority = 2)
    @Description("Verify user can navigate to login page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigation")
    public void testNavigateToLoginPage() {
        logger.info("Testing navigation to login page");

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = homePage.clickLoginLink();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed after clicking login link");
    }
}
