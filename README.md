# Playwright Java UI Automation Framework

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Playwright](https://img.shields.io/badge/Playwright-1.40+-green.svg)](https://playwright.dev/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8+-red.svg)](https://testng.org/)

A robust, scalable UI automation framework built with Playwright Java, following industry best practices and design patterns.

## 🎯 Framework Features

- **Page Object Model (POM)** - Clean separation of test logic and page elements
- **Data-Driven Testing** - JSON-based test data management
- **Parallel Execution** - Run tests concurrently for faster feedback
- **Allure Reports** - Rich, interactive test reports with screenshots
- **Logging** - Log4j2 integration for comprehensive test execution logs
- **CI/CD Ready** - Jenkins pipeline configuration included
- **Cross-Browser Testing** - Support for Chromium, Firefox, and WebKit
- **Screenshot on Failure** - Automatic screenshot capture for failed tests

## 🏗️ Framework Architecture

```
playwright-java-ui-framework/
├── src/
│   ├── main/java/
│   │   ├── com.automation/
│   │   │   ├── pages/           # Page Object classes
│   │   │   ├── utils/           # Utility classes
│   │   │   ├── config/          # Configuration management
│   │   │   └── base/            # Base classes
│   └── test/java/
│       ├── com.automation.tests/ # Test classes
│       └── resources/
│           ├── test-data/        # Test data files
│           ├── config.properties # Configuration file
│           └── log4j2.xml        # Logging configuration
├── pom.xml
├── testng.xml
└── Jenkinsfile
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Bharti1917/playwright-java-ui-framework.git
cd playwright-java-ui-framework
```

2. Install dependencies:
```bash
mvn clean install
```

3. Install Playwright browsers:
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## 🧪 Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run tests in parallel:
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```

### Run tests on specific browser:
```bash
mvn clean test -Dbrowser=firefox
```

## 📊 Test Reports

### Generate Allure Report:
```bash
mvn allure:serve
```

Or generate and view:
```bash
mvn allure:report
```

Reports will be available at `target/allure-report/index.html`

## 🔧 Configuration

Edit `src/test/resources/config.properties`:

```properties
browser=chromium
headless=false
baseUrl=https://automationexercise.com
timeout=30000
```

## 📝 Test Data

Test data is managed using JSON files located in `src/test/resources/test-data/`

## 🔄 CI/CD Integration

The framework includes a Jenkins pipeline configuration. To use:

1. Create a new Pipeline job in Jenkins
2. Point to the `Jenkinsfile` in the repository
3. Configure credentials if needed
4. Run the pipeline

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Playwright | Browser Automation |
| Maven | Build & Dependency Management |
| TestNG | Test Framework |
| Allure | Test Reporting |
| Log4j2 | Logging |
| Jackson | JSON Processing |

## 📦 Project Structure Details

- **Base Package** (`com.automation.base`): Contains BasePage, BaseTest with common methods
- **Pages Package** (`com.automation.pages`): Page Object Model classes for each page
- **Utils Package** (`com.automation.utils`): Helper classes (ConfigReader, DataProvider, etc.)
- **Tests Package** (`com.automation.tests`): TestNG test classes

## 🤝 Contributing

This is a portfolio project. Suggestions and improvements are welcome!

## 👤 Author

**Bharti**
- GitHub: [@Bharti1917](https://github.com/Bharti1917)

## 📄 License

This project is licensed under the MIT License.

---

⭐ If you find this framework helpful, please consider giving it a star!
