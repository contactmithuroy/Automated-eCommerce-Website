# Automated eCommerce Project with Selenium & TestNG

## 🔥 Brief About the Project
This project automates the testing of a web application using **Selenium WebDriver**,**Maven** and **TestNG**. It covers the automation of various web pages, including the Home, Product, Contact, Login, Logout, Registration, and Cart pages. The tests are data-driven, extracting inputs from an Excel sheet. Detailed HTML reports are generated using **ExtentReports**, which include test logs, screenshots on failure, and browser information for better visibility.

## Language and Tools
- **Programming Language**: Java
- **Testing Framework**: TestNG
- **Automation Tools**:
  - **Selenium WebDriver**: For browser automation (Chrome, Firefox, Edge).
  - **ExtentReports**: For generating HTML reports with logs and screenshots.
  - **Apache POI**: For reading data from Excel files.
- **Version Control**: Git & GitHub
- **Build Tool**: Maven
- **Reporting**: HTML reports (ExtentReports)

## Testing Architecture
- **Page Object Model (POM)**: Separates page-specific functionality and test scripts for reusability and maintenance.
- **TestNG Annotations**: Utilizes `@BeforeClass`, `@AfterClass`, `@BeforeMethod`, and `@AfterMethod` for setup, teardown, and test management.
- **Cross-Browser Testing**: Automated tests across different browsers (Chrome, Firefox, Edge) via parameters.
- **Data-Driven Testing**: Inputs are fetched from an Excel sheet (`Book.xlsx`).
- **Screenshot Capture**: Automatically takes screenshots on test failures, attached to the ExtentReports.
- **Test Reports**: Generated in HTML format, including detailed logs and screenshots.

## Testing Scenarios
- **Home Page Tests**: Validates page elements and functionality.
- **Product Page Tests**: Verifies product listing, filtering, and details display.
- **Contact Page Tests**: Ensures contact form submission works as expected.
- **Login/Logout Tests**: Validates login/logout with valid/invalid credentials.
- **Registration Tests**: Automates the user registration process.
- **Cart Page Tests**: Checks cart functionality (add/remove items, checkout).
- **Error Handling**: Includes failure scenarios to verify system resilience.

## Test Report
The **ExtentReports** library provides an interactive, HTML-based report that includes:
- **Test Case Summary**: Pass/Fail status for each test case.
- **Logs**: Detailed execution logs.
- **Screenshots**: Screenshots attached to failed tests.
- **System Info**: Browser and OS details for context.
- Viewable in any browser with enhanced visualization of test results.

## 🔥 How to Run in System

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/Automated-eCommerce-Website.git
2. **Install Prerequisites:**
Java: Ensure Java is installed (Download JDK).
Maven: Install Maven for dependency management (Download Maven).
3. **Install WebDriver:**
Download WebDriver for your preferred browser (e.g., ChromeDriver, GeckoDriver, EdgeDriver) and add it to your system’s PATH or specify its path in your test code.
4. **Install Dependencies:**
Run this Maven command to install required dependencies
   ```bash
   mvn clean install
   mvn test
## 🔥 Additional Notes
Test Data: Provided in Book.xlsx under the src/test/resources/ folder.
Browser Selection: Choose the browser via the @Parameters annotation in your TestNG XML configuration (e.g., browserName="chrome").
