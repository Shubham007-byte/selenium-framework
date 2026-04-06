# Selenium Testing Framework

This is a complete Selenium test automation framework built with:
- **Selenium WebDriver 4.15.0** - Browser automation
- **TestNG 7.7.1** - Test framework
- **Maven** - Build and dependency management
- **Page Object Model (POM)** - Best practices design pattern
- **Allure Reporting** - Advanced test reporting
- **WebDriverManager** - Automatic driver management

## Project Structure

```
src/
 ├── main/java/
 │   ├── base/
 │   │   └── BaseTest.java        # Base test class with setup/teardown
 │   ├── pages/
 │   │   └── LoginPage.java       # Page Object for Login page
 │   └── utils/
 │       ├── DriverFactory.java   # WebDriver initialization
 │       ├── ConfigReader.java    # Properties file reader
 │       ├── ScreenshotUtil.java  # Screenshot capture utility
 │       └── TestListener.java    # Test failure listener
 │
 └── test/java/
     └── tests/
         └── LoginTest.java       # Test cases

src/test/resources/
 └── config.properties            # Configuration file

testng.xml                         # TestNG suite configuration
```

## Features

✅ **Base Test Class** - Setup and teardown lifecycle management
✅ **Page Object Model** - Reusable page components
✅ **Configuration Management** - External properties file
✅ **Screenshot on Failure** - Automatic failure documentation
✅ **Allure Reporting** - Rich HTML reports with attachments
✅ **Test Listeners** - Custom test execution listeners
✅ **WebDriver Management** - Automatic driver download and management

## Prerequisites

- Java 21 or higher
- Maven 3.8.9 or higher
- Chrome Browser (tests configured for Chrome)

## Setup & Installation

1. Clone the repository:
```bash
git clone <your-repo-url>
cd selenium-framework
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run tests with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### View Allure Report:
```bash
mvn allure:serve
```

### Generate Allure Report:
```bash
mvn allure:report
```

## Configuration

Edit `src/test/resources/config.properties` to customize:
```properties
url=https://www.saucedemo.com/
browser=chrome
```

## Adding New Tests

1. Create a new page class in `src/main/java/pages/`
2. Create a test class in `src/test/java/tests/`
3. Extend `BaseTest` in your test class
4. Add test methods with `@Test` annotation

## Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager
- Allure TestNG
- Apache Commons IO
- SLF4J

## Author

Your Name

## License

MIT License
