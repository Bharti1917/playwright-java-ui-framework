package com.automation.pages;

import com.automation.base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * PaymentPage - Page Object class for Payment Page
 */
public class PaymentPage extends BasePage {

    // Locators
    private final String nameOnCardInput = "input[data-qa='name-on-card']";
    private final String cardNumberInput = "input[data-qa='card-number']";
    private final String cvcInput = "input[data-qa='cvc']";
    private final String expiryMonthInput = "input[data-qa='expiry-month']";
    private final String expiryYearInput = "input[data-qa='expiry-year']";
    private final String payConfirmButton = "button[data-qa='pay-button']";
    private final String successMessage = "//div[contains(text(),'Your order has been placed successfully!')]";
    private final String downloadInvoiceButton = "a[href='/download_invoice']";
    private final String continueButton = "a[data-qa='continue-button']";

    public PaymentPage(Page page) {
        super(page);
    }

    @Step("Enter payment details")
    public void enterPaymentDetails(String nameOnCard, String cardNumber, String cvc,
                                    String expiryMonth, String expiryYear) {
        logger.info("Entering payment details for card holder: {}", nameOnCard);
        fill(nameOnCardInput, nameOnCard);
        fill(cardNumberInput, cardNumber);
        fill(cvcInput, cvc);
        fill(expiryMonthInput, expiryMonth);
        fill(expiryYearInput, expiryYear);
    }

    @Step("Click pay and confirm order button")
    public void clickPayAndConfirm() {
        logger.info("Clicking Pay and Confirm Order button");
        click(payConfirmButton);
    }

    @Step("Verify order success message")
    public boolean isOrderSuccessful() {
        logger.info("Verifying order success message");
        waitForElement(successMessage);
        return isVisible(successMessage);
    }

    @Step("Download invoice")
    public void downloadInvoice() {
        logger.info("Downloading invoice");
        click(downloadInvoiceButton);
    }

    @Step("Click continue button")
    public HomePage clickContinue() {
        logger.info("Clicking Continue button");
        click(continueButton);
        return new HomePage(page);
    }
}
