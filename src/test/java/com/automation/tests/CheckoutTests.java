package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utils.JsonDataReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * CheckoutTests - Test class for Checkout and Payment flow
 */
@Epic("E-commerce")
@Feature("Checkout")
public class CheckoutTests extends BaseTest {

    @Test
    @Description("Verify complete checkout flow with payment")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Complete Purchase")
    public void testCompleteCheckoutFlow() {
        logger.info("Testing complete checkout flow");

        // First register a new user
        String uniqueEmail = "checkout_" + System.currentTimeMillis() + "@test.com";
        Map<String, Object> regData = JsonDataReader.readJsonAsList("registration-data.json").get(0);

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = homePage.clickLoginLink();

        RegistrationPage registrationPage = loginPage.enterSignupDetails(
                (String) regData.get("name"), uniqueEmail);

        registrationPage.fillRegistrationForm(
                (String) regData.get("title"),
                (String) regData.get("password"),
                (String) regData.get("firstName"),
                (String) regData.get("lastName"),
                (String) regData.get("address"),
                (String) regData.get("country"),
                (String) regData.get("state"),
                (String) regData.get("city"),
                (String) regData.get("zipcode"),
                (String) regData.get("mobile")
        );

        registrationPage.clickCreateAccount();
        homePage = registrationPage.clickContinue();

        // Add product to cart
        ProductsPage productsPage = homePage.clickProductsLink();
        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.viewCart();

        Assert.assertTrue(cartPage.isProductInCart(),
                "Product should be in cart");

        // Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page should be displayed");
        Assert.assertTrue(checkoutPage.isDeliveryAddressDisplayed(),
                "Delivery address should be displayed");
        Assert.assertTrue(checkoutPage.isBillingAddressDisplayed(),
                "Billing address should be displayed");

        checkoutPage.enterComment("Please deliver between 9 AM to 5 PM");

        // Payment
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        Map<String, Object> paymentData = JsonDataReader.readJsonAsMap("payment-data.json");

        paymentPage.enterPaymentDetails(
                (String) paymentData.get("nameOnCard"),
                (String) paymentData.get("cardNumber"),
                (String) paymentData.get("cvc"),
                (String) paymentData.get("expiryMonth"),
                (String) paymentData.get("expiryYear")
        );

        paymentPage.clickPayAndConfirm();

        Assert.assertTrue(paymentPage.isOrderSuccessful(),
                "Order success message should be displayed");

        logger.info("Checkout completed successfully");
    }
}
