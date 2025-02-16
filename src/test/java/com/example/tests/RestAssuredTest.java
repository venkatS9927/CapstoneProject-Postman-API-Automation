package com.example.tests;

import com.example.util.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestAssuredTest {
    private final List<String> results = new ArrayList<>();

    @Test
    public void getCountryTranslations() throws IOException {
        String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/excel.xlsx";
        ExcelUtil excel = new ExcelUtil(excelFilePath);

        int totalRows = excel.getTotalRowCount(0);
        Assert.assertTrue(totalRows > 1, "Excel file is empty or missing required data.");

        for (int i = 1; i < totalRows; i++) { // Skipping header row
            String translation = excel.getData(0, i, 0).trim().toLowerCase(); // Normalize input
            String url = "https://restcountries.com/v3.1/translation/" + translation;

            Response response = RestAssured.get(url);
            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString().toLowerCase(); // Normalize response

            // Validate response status
            boolean statusCheck = statusCode == 200;
            logResult("Translation '" + translation + "' - Status: " + statusCode, statusCheck);
            Assert.assertTrue(statusCheck, "Failed: Translation '" + translation + "' returned status " + statusCode);

            // Validate response contains expected country names
            boolean containsExpectedName = responseBody.contains("germany") ||
                    responseBody.contains("deutschland") ||
                    responseBody.contains("allemagne") ||
                    responseBody.contains("alemania") ||
                    responseBody.contains("saksamaa");

            logResult("Translation '" + translation + "' - Expected country name found", containsExpectedName);
            Assert.assertTrue(containsExpectedName, "Failed: Translation '" + translation + "' - Expected country name not found.");

            // Store test results
            results.add("PASS: '" + translation + "' - Status 200 & Country name verified.");
        }
    }

    @AfterClass
    public void verifyResults() {
        boolean resultsNotEmpty = !results.isEmpty();
        logResult("Test results list validation", resultsNotEmpty);
        Assert.assertTrue(resultsNotEmpty, "Test results list is empty.");
    }
    public static void logResult(String message, boolean isPassed) {
        String color = isPassed ? "green" : "red";
        String status = "<b><span style='color:" + color + ";'>" + (isPassed ? "✅ Passed" : "❌ Failed") + "</span></b>";
        Reporter.log(message + " || " + status, true);
    }
}
