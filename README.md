# 🛒 NinjaShop Automation Framework

A Selenium-based automation framework built using Java, TestNG, Maven, and Page Object Model (POM) to automate an e-commerce application (TutorialsNinja demo site).

---

## 🚀 Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Page Object Model (POM)
* Extent Reports
* Apache POI

---

## 📁 Project Structure

```plaintext
NinjaShop
│
├── src/main/java
│   ├── base
│   │   └── BasePage.java              → Common reusable methods (click, type, waits, scroll)
│   │
│   ├── pages
│   │   ├── HomePage.java              → Homepage actions (search, navigation, cart access)
│   │   ├── LoginPage.java             → Login functionality
│   │   ├── RegisterPage.java          → User registration flow
│   │   ├── SearchPage.java            → Handles search results and product selection
│   │   ├── ProductPage.java           → Product details and add-to-cart actions
│   │   ├── CartPage.java              → Cart operations (update, delete, checkout)
│   │   └── CheckoutPage.java          → Complete checkout workflow
│   │
│   ├── utils
│   │   ├── ConfigReader.java          → Reads config.properties (URL, credentials)
│   │   ├── ExcelUtil.java             → Handles Excel test data
│   │   ├── ExtentManager.java         → Manages Extent Reports setup
│   │   └── ScreenshotUtil.java        → Captures screenshots on failures
│
├── src/test/java
│   ├── base
│   │   └── BaseTest.java              → WebDriver setup, teardown
│   │
│   ├── listeners
│   │   ├── TestListener.java          → Captures test events & reporting
│   │   └── RetryListener.java         → Attaches retry logic to tests
│   │
│   ├── tests
│   │   ├── AuthTest.java              → Authentication test scenarios
│   │   ├── ProductSearchTest.java     → Product search & navigation tests
│   │   ├── CartTest.java              → Cart functionality tests
│   │   ├── CheckoutTest.java          → End-to-end checkout tests
│   │   └── FormValidationsTest.java   → Form validation test cases
│   │
│   └── utils
│       └── RetryAnalyzer.java         → Retry logic implementation
│
├── src/test/resources
│   ├── testdata
│   │   └── LoginData.xlsx             → External test data for login scenarios
│   └── config.properties              → Configuration file (URL, credentials, browser)
│
├── reports/                           → Extent report output
├── screenshots/                       → Failure screenshots
├── pom.xml                            → Maven dependencies & build config
└── testng.xml                         → Test suite configuration
```

---

## 🧪 Test Modules Covered

### 🔐 User Authentication

* Login with valid/invalid credentials
* Logout functionality
* Registration flow

### 🔍 Product Search & Browse

* Search products
* Navigate categories
* Validate product details
* Handle no-result scenarios

### 🛒 Shopping Cart

* Add product
* Update quantity
* Remove product
* Verify cart count

### 💳 Checkout Flow

* Complete checkout as logged-in user
* Validate order confirmation
* Verify checkout without being logged-in

### 🧾 Form Validations

* Empty field validation
* Invalid input validation
* Field-level error checks

---

## ▶️ How to Run

### Maven

```bash
mvn test
```

### TestNG

Right click → `testng.xml` → Run

---

## ⚙️ Configuration

```properties
url=https://tutorialsninja.com/demo
email=your_email
password=your_password
browser=chrome
```

---

## 📊 Reports

* Extent → `/reports/`
* Screenshots → `/screenshots/`

---

## 👤 Author

Vaishnavi

---

## 📌 Conclusion

This framework follows best practices like POM, reusable utilities, reporting, and modular design, making it scalable.

---
