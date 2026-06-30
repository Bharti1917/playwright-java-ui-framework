package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * ProductsPage - Page Object class for Products Page
 */
public class ProductsPage extends BasePage {

    // Locators
    private final String searchInput = "input[id='search_product']";
    private final String searchButton = "button[id='submit_search']";
    private final String productsList = ".features_items";
    private final String addToCartButton = "//div[@class='productinfo text-center']//a[contains(@class,'add-to-cart')]";
    private final String continueShoppingButton = "button[data-dismiss='modal']";
    private final String viewCartLink = "//div[@class='modal-content']//a[text()='View Cart']";
    private final String productName = ".productinfo p";
    private final String productPrice = ".productinfo h2";

    public ProductsPage(Page page) {
        super(page);
    }

    @Step("Search for product: {productName}")
    public void searchProduct(String productName) {
        logger.info("Searching for product: {}", productName);
        fill(searchInput, productName);
        click(searchButton);
    }

    @Step("Verify products list is displayed")
    public boolean isProductsListDisplayed() {
        logger.info("Verifying products list is displayed");
        waitForElement(productsList);
        return isVisible(productsList);
    }

    @Step("Add first product to cart")
    public void addFirstProductToCart() {
        logger.info("Adding first product to cart");
        String firstProduct = addToCartButton + "[1]";
        scrollToElement(firstProduct);
        click(firstProduct);
        waitForElement(continueShoppingButton);
    }

    @Step("Click continue shopping")
    public void clickContinueShopping() {
        logger.info("Clicking Continue Shopping button");
        click(continueShoppingButton);
    }

    @Step("View cart after adding product")
    public CartPage viewCart() {
        logger.info("Clicking View Cart link");
        click(viewCartLink);
        return new CartPage(page);
    }

    @Step("Get first product name")
    public String getFirstProductName() {
        String name = getText(productName + ":first-child");
        logger.info("First product name: {}", name);
        return name;
    }

    @Step("Get first product price")
    public String getFirstProductPrice() {
        String price = getText(productPrice + ":first-child");
        logger.info("First product price: {}", price);
        return price;
    }
}
