# 🧪 Lentera CPNS Automation Test

[![Automation Test](https://github.com/ekipy/lenteraCPNS-Automation/actions/workflows/automation.yml/badge.svg)](https://github.com/ekipy/lenteraCPNS-Automation/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-orange)](https://ekipy.github.io/lenteraCPNS-Automation/)
![Java](https://img.shields.io/badge/Java-21-red)
![JUnit5](https://img.shields.io/badge/JUnit-5-brightgreen)
![Cucumber](https://img.shields.io/badge/Cucumber-7.x-green)
![Selenium](https://img.shields.io/badge/Selenium-4.x-blue)
![Browser](https://img.shields.io/badge/Browser-Chrome-blue)

---

## 📌 Project Overview

This repository contains **end-to-end automation testing** for the **Lentera CPNS** web application, built using **BDD (Behavior Driven Development)** principles with **Cucumber**.

The project is designed as a **QA Automation portfolio**, demonstrating:

* UI Automation best practices
* Page Object Model (POM)
* Form validation testing (including multiple error messages)
* Allure reporting
* CI/CD integration with GitHub Actions
* Automated publishing of reports to GitHub Pages

🔗 **Live Allure Report:**
👉 [https://ekipy.github.io/lenteraCPNS-Automation/](https://ekipy.github.io/lenteraCPNS-Automation/)

---

## 📸 Allure Report Preview

> Below is a preview of the generated Allure Report.


![Allure Report Preview](https://raw.githubusercontent.com/ekipy/lenteraCPNS-Automation/main/docs/allure-preview.png)

---

## 📊 Test Coverage

### UI Automation

* ✅ Register Page

  * Email validation (multiple error messages)
  * Required field validation
* ✅ Login Page
* ✅ Checkout Process
* ✅ Examination Process

---

## 🧰 Tech Stack

| Tool           | Version     |
| -------------- | ----------- |
| Java           | 21          |
| JUnit          | 5 (Jupiter) |
| Cucumber       | 7.x         |
| Selenium       | 4.x         |
| Gradle         | Wrapper     |
| Allure Report  | 2.x         |
| GitHub Actions | CI/CD       |

---

## 📂 Project Structure

```
lenteracpns-automation/
│
├── app/
│   ├── src/test/java/
│   │   ├── pages/          # Page Object Model (UI)
│   │   ├── steps/          # Step Definitions
│   │   ├── api/            # API tests (Rest Assured)
│   │   ├── runners/        # Cucumber runners
│   │   └── hooks/          # Hooks (Before / After)
│   │
│   ├── src/test/resources/
│   │   ├── features/       # Feature files (UI & API)
│   │   └── allure.properties
│   │
│   └── build/
│       ├── allure-results/
│       └── allure-report/
│
├── docs/
│   └── allure-preview.png  # Allure screenshot
│
├── .github/workflows/
│   └── automation.yml      # CI pipeline
│
├── gradlew
├── build.gradle
└── README.md
```

---

## ▶️ How to Run Tests Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/ekipy/lenteraCPNS-Automation.git
cd lenteraCPNS-Automation
```

### 2️⃣ Execute Tests

```bash
./gradlew clean test
```

### 3️⃣ Generate Allure Report

```bash
allure generate app/build/allure-results -o app/build/allure-report --clean
allure open app/build/allure-report
```

---

## 🤖 CI/CD Pipeline (GitHub Actions)

Every push or pull request to the `main` branch triggers:

```
Code Push → GitHub Actions → UI/API Tests → Allure Report → GitHub Pages
```

This ensures:

* Automated regression testing
* Centralized reporting
* Easy access for stakeholders

---

## 🎯 Purpose of This Project

This project was built to:

* Serve as a **QA Automation Engineer portfolio**
* Demonstrate real-world automation practices
* Showcase CI/CD and reporting capabilities
* Prepare for professional QA Automation roles

---

## 👤 Author

**Eki Panca Nugraha**
QA Manual & Automation Engineer

🔗 LinkedIn: [https://www.linkedin.com/in/ekipnugraha/](https://www.linkedin.com/in/ekipnugraha/)

---

🚀 *Built with passion for quality and continuous improvement.*
