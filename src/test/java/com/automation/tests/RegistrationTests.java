package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.RegistrationPage;
import com.automation.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * RegistrationTests - Test class for User Registration
 */
@Epic("Authentication")
@Feature("Registration")
public class RegistrationTests extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = TestDataProvider.class)
    @Description("Verify user can register with valid details")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Registration")
    public void testUserRegistration(Map<String, Object> data) {
        String testCase = (String) data.get("testCase");
        logger.info("Executing test case: {}", testCase);

        // Generate unique email to avoid duplicate registration
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = homePage.clickLoginLink();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        RegistrationPage registrationPage = loginPage.enterSignupDetails(
                (String) data.get("name"),
                uniqueEmail
        );

        registrationPage.fillRegistrationForm(
                (String) data.get("title"),
                (String) data.get("password"),
                (String) data.get("firstName"),
                (String) data.get("lastName"),
                (String) data.get("address"),
                (String) data.get("country"),
                (String) data.get("state"),
                (String) data.get("city"),
                (String) data.get("zipcode"),
                (String) data.get("mobile")
        );

        registrationPage.clickCreateAccount();

        Assert.assertTrue(registrationPage.isAccountCreated(),
                "Account created message should be displayed");

        homePage = registrationPage.clickContinue();

        Assert.assertTrue(homePage.isUserLoggedIn(),
                "User should be logged in after registration");

        logger.info("Registration successful for: {}", uniqueEmail);
    }
}
