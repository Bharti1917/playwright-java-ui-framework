package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CartTests - Test class for Shopping Cart functionality
 */
@Epic("E-commerce")
@Feature("Shopping Cart")
public class CartTests extends BaseTest {

    @Test(priority = 1)
    @Description("Verify cart page displays added products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View Cart")
    public void testViewCart() {
        logger.info("Testing view cart functionality");

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.viewCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page should be displayed");
        Assert.assertTrue(cartPage.isProductInCart(),
                "Product should be in the cart");
    }

    @Test(priority = 2)
    @Description("Verify product removal from cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Remove from Cart")
    public void testRemoveProductFromCart() {
        logger.info("Testing remove product from cart");

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.viewCart();

        Assert.assertTrue(cartPage.isProductInCart(),
                "Product should be in cart before removal");

        cartPage.removeProduct();
        page.reload();

        int itemCount = cartPage.getCartItemsCount();
        Assert.assertEquals(itemCount, 0,
                "Cart should be empty after removing product");
    }

    @Test(priority = 3)
    @Description("Verify cart items count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Cart Count")
    public void testCartItemsCount() {
        logger.info("Testing cart items count");

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.viewCart();

        int count = cartPage.getCartItemsCount();
        Assert.assertTrue(count > 0, "Cart should have at least one item");
        logger.info("Cart items count: {}", count);
    }
}
