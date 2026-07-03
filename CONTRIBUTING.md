# Contributing to Playwright Java UI Framework

Thank you for your interest in contributing to this project!

## Getting Started

1. Fork the repository
2. Clone your fork: `git clone https://github.com/YOUR_USERNAME/playwright-java-ui-framework.git`
3. Create a feature branch: `git checkout -b feature/your-feature-name`
4. Make your changes
5. Run tests to ensure everything works
6. Commit your changes: `git commit -m "Add your feature"`
7. Push to your fork: `git push origin feature/your-feature-name`
8. Create a Pull Request

## Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Use Allure @Step annotations for test steps
- Keep methods focused and single-purpose

## Adding New Tests

1. Create test class in `src/test/java/com/automation/tests/`
2. Extend `BaseTest` class
3. Add appropriate Allure annotations (@Epic, @Feature, @Story)
4. Use Page Object Model for page interactions
5. Add test data to JSON files if needed

## Adding New Pages

1. Create page class in `src/main/java/com/automation/pages/`
2. Extend `BasePage` class
3. Define locators as private final strings
4. Add @Step annotations to methods
5. Use descriptive method names

## Running Tests Locally

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=LoginTests

# Run with specific browser
mvn test -Dbrowser=firefox

# Generate Allure report
mvn allure:serve
```

## Pull Request Guidelines

- Ensure all tests pass
- Update README if adding new features
- Add test data for data-driven tests
- Include screenshots in PR description if UI changes

## Questions?

Feel free to open an issue for any questions or suggestions!
