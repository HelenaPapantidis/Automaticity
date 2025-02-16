#  AutomaticityAcademy Test Automation Suite

## 📌 Project Overview

This repository contains an automated test suite for [Automaticity Academy](https://automaticityacademy.ngrok.app/).  
The suite covers UI and API test scenarios to validate the core functionalities of the application, following industry best practices in test automation.

## 🚀 Technologies Used

This project is built using **Selenium WebDriver** with **Java** and **TestNG**, along with Maven for dependency management.  
Other possible frameworks include **Cypress, Playwright, WebdriverIO, Karate** if required.

- **Java** – Primary programming language
- **Selenium WebDriver** – Browser automation
- **TestNG** – Test framework
- **Maven** – Build and dependency management
- **RestAssured** – API automation (if applicable)
- **Git & GitHub** – Version control
- **Extent Reports** – Test reporting


## 📝 Test Scenarios Covered

### **✅ UI Automation**
1. **Login Tests**
    - ✅ Valid login with correct credentials
    - ❌ Invalid login with wrong password
    - ❌ Invalid login with unregistered email
    - ❌ Login with empty fields

2. **Registration Tests**
    - ✅ Successful user registration
    - ❌ Attempt to register with an existing email
    - ❌ Attempt to register leaving all field empty
    - ❌ Attempt to register with invalid password
    - ❌ Attempt to register with an existing username
    - ❌ Invalid input validations (e.g., weak password, incorrect email format)

3. **E2E Shopping Flow (Extra)**
    - ✅ Add items to cart
    - ✅ Remove items from cart
    - ✅ Complete checkout process

### **✅ API Automation (Extra)**
- 🔄 Validate API responses for authentication and product listing
- 🔄 Ensure data consistency between API and UI


📏 Coding Standards & Best Practices
✅ Page Object Model (POM) used for better maintainability
✅ Descriptive commit messages for each test scenario
✅ Followed DRY principle to avoid redundant code

📧 Contact
For any issues or questions, reach out at hpapantidis@gmail.com



