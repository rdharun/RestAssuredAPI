# RestAssured

The RestAssured project is designed to streamline API testing using the RestAssured library in Java. It offers a framework for writing and running API tests with ease, leveraging the power of RestAssured along with other technologies like TestNG and Gradle.

## Tech Stack

- Java
- TestNG
- Gradle
- RestAssured
- Allure

## Features

- Parallel Testing
- Environment-Specific Configurations
- Allure Integration for Reporting


# Project Structure

The project follows a structured organization to enhance maintainability and readability. Here's a brief overview of the key folders and files:

## Folders

1. **`src` Directory:**
   - Contains the main source code for the project.
   - Subdirectories:
      - `main`: Java source code for production.
      - `test`: Java source code for tests.

2. **`testData` Directory:**
   - Holds test data used in the API tests.
   - Subdirectories:
      - `json`: JSON files containing data for different scenarios.

3. **`utilities` Directory:**
   - Utility classes used across the project.
   - Notable files:
      - `PropertyUtils.java`: Manages environment-specific configurations.
      - `DataProvider.java`: Retrieves data from JSON files.

4. **`spec` Directory:**
   - Test specification classes.
   - Notable files:
      - `BaseTest.java`: Configuration setup and teardown for all tests.
      - `UserLoginTest.java`: Test cases for user login functionality.
      - `ConfigurationValidationTest.java`: Tests validating environment configurations.

5. **`build` Directory:**
   - Contains build artifacts.
   - Subdirectories:
      - `allure-results`: Raw result data for Allure reports.

## Files

1. **`build.gradle` File:**
   - Gradle build configuration.
   - Dependencies, plugins, and build settings.

2. **`settings.gradle` File:**
   - Gradle settings, includes project modules.

3. **`README.md` File:**
   - The documentation you are currently reading.

4. **`gradlew` and `gradlew.bat` Files:**
   - Gradle wrapper scripts for cross-platform project execution.

5. **`gitignore` File:**
   - Specifies files and directories to be ignored by version control.


## Setup

1. **Prerequisites:**
    - JDK installed
    - Gradle installed

2. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   cd project-directory
