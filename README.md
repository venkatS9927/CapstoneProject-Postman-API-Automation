# CapstoneProject-Postman-API-Automation
Capstone project using Postman for API automation
**Mini Project : API Automation using POSTMAN and Rest-Assured**

**Overview**

This project involves automating API testing using POSTMAN and Rest-Assured. The primary objective is to implement a generic function to read test data from an Excel sheet and trigger API requests using the GET method.

**Project Structure**

- **Excel Utility Class (ExcelUtil.java)**
  - Path: D:\\restassured-test\\src\\main\\java\\com\\example\\util\\ExcelUtil.java
  - This utility reads data from an Excel sheet and provides it to the test scripts dynamically.
- **Test Class (RestAssuredTest.java)**
  - Path: D:\\restassured-test\\src\\test\\java\\com\\example\\tests\\RestAssuredTest.java
  - Contains test cases that use the Excel data to send API requests and validate responses.
- **Test Data and Configuration Files**
  - Excel File (excel.xlsx): D:\\restassured-test\\src\\test\\resources\\excel.xlsx
    - Stores different translation values that will be used in API requests.
  - TestNG Configuration (testng.xml): D:\\restassured-test\\src\\test\\resources\\testng.xml
    - Defines the test execution flow and configurations for running tests.
- **POM XML Files: Dependency**

**API Details**

The API used in this project is the RestCountries API, which provides country details based on translation values. The endpoint follows this format: <https://restcountries.com/v3.1/translation/{translation}>

**Example API Calls**

1. <https://restcountries.com/v3.1/translation/germany>
2. <https://restcountries.com/v3.1/translation/alemania>
3. <https://restcountries.com/v3.1/translation/Saksamaa>

These API calls return country details for Germany in different translations.

**Project Implementation**

**Step 1: Read Data from Excel**

- The ExcelUtil.java class reads test data (translation values) from excel.xlsx.
- The Excel sheet contains a list of translation values that will be used in API requests.

**Step 2: Trigger API Request**

- RestAssuredTest.java retrieves translation values from Excel and constructs API requests.
- The GET method is used to fetch country details dynamically.

**Step 3: Validate API Response**

- The response is validated for:
  - HTTP Status Code (200 OK)
  - Expected country details in the response JSON
  - Response time and headers

**Step 4: Execution via TestNG**

- testng.xml is configured to run API tests in a structured manner.
- The tests can be executed using Maven commands or directly through TestNG.

**Expected Outcome**

- The tests should fetch country details based on different translation values.
- The results should be validated against expected responses.
- A detailed test report should be generated after execution.

**Conclusion**

This project demonstrates a scalable and data-driven approach to API automation using POSTMAN, Rest-Assured, and TestNG. The integration of Excel as a data source ensures flexibility, making it easy to add new test cases without modifying the test scripts.

