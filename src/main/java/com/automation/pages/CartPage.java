package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * CartPage - Page Object class for Shopping Cart Page
 */
public class CartPage extends BasePage {

    // Locators
    private final String cartInfo = "#cart_info_table";
    private final String productRow = "tr[id*='product-']";
    private final String proceedToCheckoutButton = ".btn-default.check_out";
    private final String registerLoginLink = "//u[text()='Register / Login']";
    private final String deleteProductButton = ".cart_quantity_delete";
    private final String emptyCartMessage = "//b[text()='Cart is empty!']";

    public CartPage(Page page) {
        super(page);
    }

    @Step("Verify cart page is displayed")
    public boolean isCartPageDisplayed() {
        logger.info("Verifying cart page is displayed");
        return isVisible(cartInfo);
    }

    @Step("Verify product is in cart")
    public boolean isProductInCart() {
        logger.info("Verifying product is in cart");
        return isVisible(productRow);
    }

    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        logger.info("Clicking Proceed to Checkout button");
        click(proceedToCheckoutButton);
        return new CheckoutPage(page);
    }

    @Step("Click Register/Login link from cart modal")
    public LoginPage clickRegisterLogin() {
        logger.info("Clicking Register/Login link");
        click(registerLoginLink);
        return new LoginPage(page);
    }

    @Step("Remove product from cart")
    public void removeProduct() {
        logger.info("Removing product from cart");
        click(deleteProductButton);
    }

    @Step("Verify cart is empty")
    public boolean isCartEmpty() {
        logger.info("Verifying cart is empty");
        return isVisible(emptyCartMessage) || !isVisible(productRow);
    }

    @Step("Get cart items count")
    public int getCartItemsCount() {
        int count = page.locator(productRow).count();
        logger.info("Cart items count: {}", count);
        return count;
    }
}
