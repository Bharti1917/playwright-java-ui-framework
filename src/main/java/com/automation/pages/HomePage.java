package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * HomePage - Page Object class for Home Page
 */
public class HomePage extends BasePage {

    // Locators
    private final String loginLink = "a[href='/login']";
    private final String productsLink = "a[href='/products']";
    private final String cartLink = "a[href='/view_cart']";
    private final String logoutLink = "a[href='/logout']";
    private final String loggedInUsername = "//li/a[contains(text(),'Logged in as')]";
    private final String deleteAccountLink = "a[href='/delete_account']";

    public HomePage(Page page) {
        super(page);
    }

    @Step("Navigate to Login page")
    public LoginPage clickLoginLink() {
        logger.info("Clicking on Login/Signup link");
        click(loginLink);
        return new LoginPage(page);
    }

    @Step("Navigate to Products page")
    public ProductsPage clickProductsLink() {
        logger.info("Clicking on Products link");
        click(productsLink);
        return new ProductsPage(page);
    }

    @Step("Navigate to Cart page")
    public CartPage clickCartLink() {
        logger.info("Clicking on Cart link");
        click(cartLink);
        return new CartPage(page);
    }

    @Step("Verify user is logged in")
    public boolean isUserLoggedIn() {
        logger.info("Verifying if user is logged in");
        return isVisible(loggedInUsername);
    }

    @Step("Get logged in username")
    public String getLoggedInUsername() {
        logger.info("Getting logged in username");
        return getText(loggedInUsername);
    }

    @Step("Click logout link")
    public void clickLogout() {
        logger.info("Clicking on Logout link");
        click(logoutLink);
    }

    @Step("Verify home page is displayed")
    public boolean isHomePageDisplayed() {
        logger.info("Verifying Home page is displayed");
        return isVisible(loginLink);
    }
}
