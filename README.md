# IMDb Movie Search Automation - Home Assignment

## Overview

This project automates the IMDb movie search flow using **Selenide**, **TestNG**, and **Allure Reports**.  

### Test Scenario

1. Open IMDb main page  
2. Search for `"QA"` using the search bar  
3. Save the name of the first movie from the search dropdown  
4. Click on the first movie title  
5. Verify that the movie page title matches the saved name  
6. Verify that there are more than 3 members in the **Top Cast** section  
7. Click on the 3rd cast member  
8. Verify that the correct profile page has opened  


## Tech Stack

| Technology        | Version / Tool        |
|------------------|---------------------|
| Java              | 17                  |
| Build Tool        | Gradle              |
| Test Framework    | TestNG              |
| Browser Automation| Selenide            |
| Reporting         | Allure              |
| Logging           | SLF4J + Logback     |

---


##  Prerequisites

- Java 17 installed  
- Chrome browser installed  
- **Allure CLI** installed for viewing reports:  
  - macOS: `brew install allure`  
  - Windows: `choco install allure`
 
  
##  Running Tests

### Run Tests with gradle and generate Allure report

```bash
./gradlew clean test
allure serve build/allure-results

