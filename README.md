# Hybrid Mobile Automation Framework

## Overview

**HybridMobileAutomationFramework** is a **Java-based test automation framework** designed for **mobile (Android/iOS) and web applications**. It follows a **hybrid approach** by combining **data-driven, keyword-driven, and modular design patterns** to enable scalable and maintainable test automation. ([ToolsQA][1])

The framework leverages tools like **Selenium WebDriver** and **Appium** for cross-platform automation and uses **TestNG** with **Maven** for test execution and dependency management.

---

## Key Features

* Supports **mobile and web automation**
* Built using **Page Object Model (POM)** for better maintainability
* Hybrid design (**data-driven + keyword-driven + modular**)
* Reusable utilities and centralized configuration
* Cross-platform support (Android, iOS, Web)
* Parallel test execution support via TestNG
* Easy integration with CI/CD pipelines
* Customizable reporting and logging

---

## Tech Stack

* **Language:** Java
* **Automation Tools:** Selenium WebDriver, Appium
* **Build Tool:** Maven
* **Test Framework:** TestNG
* **Design Pattern:** Page Object Model (POM)

---

## Project Structure

```
HybridMobileAutomationFramework/
│── src/main/java
│   ├── base          # Base classes (driver setup, config)
│   ├── pages         # Page Object classes
│   ├── utils         # Utilities and helpers
│
│── src/test/java
│   ├── tests         # Test cases
│   ├── testdata      # Test data files
│
│── resources         # Config files (properties, XML)
│── pom.xml           # Maven dependencies
│── testng.xml        # TestNG suite configuration
```

---

## Prerequisites

Ensure the following are installed:

* Java (JDK 8 or above)
* Maven
* Node.js (for Appium)
* Appium Server
* Android Studio / Xcode (for mobile testing)
* IDE (IntelliJ / Eclipse)

---

## Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/BugBound-Saradha/HybridMobileAutomationFramework.git
cd HybridMobileAutomationFramework
```

2. Install dependencies:

```bash
mvn clean install
```

3. Configure environment:

* Update device/emulator details in config files
* Set Appium server URL
* Provide application paths or URLs

---

## Running Tests

### Using Maven

```bash
mvn test
```

### Using TestNG

* Run `testng.xml` from your IDE

---

## Framework Design

The framework follows a **hybrid automation model**, combining:

* **Data-driven testing** (external test data sources)
* **Keyword-driven testing** (reusable action keywords)
* **Modular framework design** (separation of concerns)

This approach improves **reusability, scalability, and maintainability** of test scripts. ([ToolsQA][1])

---

## Reporting

* Test execution reports are generated after each run
* Logs help in debugging failures
* Can be extended with tools like Extent Reports or Allure

---

## Contributing

Contributions are welcome!
Feel free to fork the repository and submit pull requests.

---

## License

This project is open-source and available under the MIT License.
