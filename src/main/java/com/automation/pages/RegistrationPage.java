package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * RegistrationPage - Page Object class for Account Registration Page
 */
public class RegistrationPage extends BasePage {

    // Locators
    private final String titleMr = "input[value='Mr']";
    private final String titleMrs = "input[value='Mrs']";
    private final String passwordInput = "input[data-qa='password']";
    private final String firstNameInput = "input[data-qa='first_name']";
    private final String lastNameInput = "input[data-qa='last_name']";
    private final String addressInput = "input[data-qa='address']";
    private final String countryDropdown = "select[data-qa='country']";
    private final String stateInput = "input[data-qa='state']";
    private final String cityInput = "input[data-qa='city']";
    private final String zipcodeInput = "input[data-qa='zipcode']";
    private final String mobileNumberInput = "input[data-qa='mobile_number']";
    private final String createAccountButton = "button[data-qa='create-account']";
    private final String accountCreatedMessage = "h2[data-qa='account-created']";
    private final String continueButton = "a[data-qa='continue-button']";

    public RegistrationPage(Page page) {
        super(page);
    }

    @Step("Fill registration form")
    public void fillRegistrationForm(String title, String password, String firstName,
                                     String lastName, String address, String country,
                                     String state, String city, String zipcode, String mobile) {
        logger.info("Filling registration form for: {} {}", firstName, lastName);

        // Select title
        if (title.equalsIgnoreCase("Mr")) {
            click(titleMr);
        } else {
            click(titleMrs);
        }

        fill(passwordInput, password);
        fill(firstNameInput, firstName);
        fill(lastNameInput, lastName);

        scrollToElement(addressInput);
        fill(addressInput, address);
        selectByVisibleText(countryDropdown, country);
        fill(stateInput, state);
        fill(cityInput, city);
        fill(zipcodeInput, zipcode);
        fill(mobileNumberInput, mobile);
    }

    @Step("Click create account button")
    public void clickCreateAccount() {
        logger.info("Clicking on Create Account button");
        scrollToElement(createAccountButton);
        click(createAccountButton);
    }

    @Step("Verify account created successfully")
    public boolean isAccountCreated() {
        logger.info("Verifying account created message");
        waitForElement(accountCreatedMessage);
        return isVisible(accountCreatedMessage);
    }

    @Step("Click continue button")
    public HomePage clickContinue() {
        logger.info("Clicking on Continue button");
        click(continueButton);
        return new HomePage(page);
    }
}
