package com.automation.utils;

import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

/**
 * TestDataProvider - Provides test data for data-driven testing
 */
public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        List<Map<String, Object>> dataList = JsonDataReader.readJsonAsList("login-data.json");
        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {
        List<Map<String, Object>> dataList = JsonDataReader.readJsonAsList("registration-data.json");
        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }

    @DataProvider(name = "productData")
    public static Object[][] getProductData() {
        List<Map<String, Object>> dataList = JsonDataReader.readJsonAsList("product-data.json");
        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}
