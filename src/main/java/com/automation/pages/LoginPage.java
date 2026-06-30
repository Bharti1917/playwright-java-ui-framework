package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * LoginPage - Page Object class for Login/Signup Page
 */
public class LoginPage extends BasePage {

    // Login section locators
    private final String loginEmailInput = "input[data-qa='login-email']";
    private final String loginPasswordInput = "input[data-qa='login-password']";
    private final String loginButton = "button[data-qa='login-button']";
    private final String loginErrorMessage = "//p[contains(text(),'Your email or password is incorrect!')]";

    // Signup section locators
    private final String signupNameInput = "input[data-qa='signup-name']";
    private final String signupEmailInput = "input[data-qa='signup-email']";
    private final String signupButton = "button[data-qa='signup-button']";
    private final String signupErrorMessage = "//p[contains(text(),'Email Address already exist!')]";

    public LoginPage(Page page) {
        super(page);
    }

    @Step("Login with email: {email} and password: {password}")
    public HomePage login(String email, String password) {
        logger.info("Attempting to login with email: {}", email);
        fill(loginEmailInput, email);
        fill(loginPasswordInput, password);
        click(loginButton);
        return new HomePage(page);
    }

    @Step("Verify login error message is displayed")
    public boolean isLoginErrorDisplayed() {
        logger.info("Verifying login error message");
        return isVisible(loginErrorMessage);
    }

    @Step("Enter signup details - Name: {name}, Email: {email}")
    public RegistrationPage enterSignupDetails(String name, String email) {
        logger.info("Entering signup details for: {}", name);
        fill(signupNameInput, name);
        fill(signupEmailInput, email);
        click(signupButton);
        return new RegistrationPage(page);
    }

    @Step("Verify signup error message is displayed")
    public boolean isSignupErrorDisplayed() {
        logger.info("Verifying signup error message");
        return isVisible(signupErrorMessage);
    }

    @Step("Verify login page is displayed")
    public boolean isLoginPageDisplayed() {
        logger.info("Verifying Login page is displayed");
        return isVisible(loginButton) && isVisible(signupButton);
    }
}
