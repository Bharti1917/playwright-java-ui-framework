package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * CheckoutPage - Page Object class for Checkout Page
 */
public class CheckoutPage extends BasePage {

    // Locators
    private final String deliveryAddress = "#address_delivery";
    private final String billingAddress = "#address_invoice";
    private final String commentTextArea = "textarea[name='message']";
    private final String placeOrderButton = "a[href='/payment']";
    private final String orderReview = "#cart_info";

    public CheckoutPage(Page page) {
        super(page);
    }

    @Step("Verify checkout page is displayed")
    public boolean isCheckoutPageDisplayed() {
        logger.info("Verifying checkout page is displayed");
        return isVisible(deliveryAddress) && isVisible(billingAddress);
    }

    @Step("Verify delivery address is displayed")
    public boolean isDeliveryAddressDisplayed() {
        logger.info("Verifying delivery address");
        return isVisible(deliveryAddress);
    }

    @Step("Verify billing address is displayed")
    public boolean isBillingAddressDisplayed() {
        logger.info("Verifying billing address");
        return isVisible(billingAddress);
    }

    @Step("Enter comment: {comment}")
    public void enterComment(String comment) {
        logger.info("Entering checkout comment");
        scrollToElement(commentTextArea);
        fill(commentTextArea, comment);
    }

    @Step("Click place order button")
    public PaymentPage clickPlaceOrder() {
        logger.info("Clicking Place Order button");
        click(placeOrderButton);
        return new PaymentPage(page);
    }

    @Step("Verify order review is displayed")
    public boolean isOrderReviewDisplayed() {
        logger.info("Verifying order review section");
        return isVisible(orderReview);
    }
}
