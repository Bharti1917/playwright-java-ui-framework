package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import com.automation.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * ProductTests - Test class for Product functionality
 */
@Epic("E-commerce")
@Feature("Products")
public class ProductTests extends BaseTest {

    @Test(priority = 1)
    @Description("Verify products page is accessible")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Listing")
    public void testProductsPageDisplay() {
        logger.info("Testing products page display");

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        Assert.assertTrue(productsPage.isProductsListDisplayed(),
                "Products list should be displayed");
    }

    @Test(priority = 2, dataProvider = "productData", dataProviderClass = TestDataProvider.class)
    @Description("Verify product search functionality")
    @Severity(SeverityLevel.NORMAL)
    @Story("Product Search")
    public void testProductSearch(Map<String, Object> data) {
        String productName = (String) data.get("productName");
        logger.info("Searching for product: {}", productName);

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        productsPage.searchProduct(productName);

        Assert.assertTrue(productsPage.isProductsListDisplayed(),
                "Search results should be displayed");
    }

    @Test(priority = 3)
    @Description("Verify add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to Cart")
    public void testAddProductToCart() {
        logger.info("Testing add product to cart");

        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = homePage.clickProductsLink();

        Assert.assertTrue(productsPage.isProductsListDisplayed(),
                "Products list should be displayed");

        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        logger.info("Product added to cart successfully");
    }
}
