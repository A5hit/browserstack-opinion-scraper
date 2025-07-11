# BrowserStack Opinion Scraper

A web scraping project that extracts opinion articles from El País (Spanish newspaper) and performs analysis on the content.

## Project Overview

This project was built to demonstrate web scraping capabilities using Selenium WebDriver with BrowserStack for cross-browser testing. The main goal is to scrape opinion articles from El País, translate them to English, and perform word frequency analysis.

## Architecture Decisions

### Why Selenium + BrowserStack?
- **Cross-browser compatibility**: BrowserStack allows testing on multiple browsers and devices
- **Real browser environment**: More reliable than headless browsers for complex websites
- **Parallel execution**: Can run tests simultaneously across different environments

### Page Object Model
I chose to implement the Page Object Model pattern to:
- Separate test logic from page interactions
- Make the code more maintainable
- Allow for easy reuse of page elements

### Translation Service
Used RapidAPI's translation service to convert Spanish titles to English for better analysis.

## Key Features

1. **Multi-browser Support**: Tests run on Chrome, Firefox, Edge, and mobile browsers
2. **Image Download**: Automatically downloads article images with unique naming
3. **Translation**: Translates Spanish article titles to English
4. **Word Frequency Analysis**: Analyzes common words in translated titles
5. **Error Handling**: Comprehensive error handling with screenshots
6. **Parallel Execution**: Runs tests in parallel using TestNG

## Setup Instructions

1. **Clone the repository**
2. **Configure credentials** in `src/main/resources/config.properties`:
   - Add your BrowserStack username and access key
   - Add your RapidAPI translation key
3. **Run the tests**:
   ```bash
   mvn clean test
   ```

## Project Structure

```
src/
├── main/java/org/browserstack/
│   ├── drivers/          # WebDriver management
│   ├── pages/           # Page Object classes
│   └── utils/           # Utility classes
└── test/
    ├── java/            # Test classes
    └── resources/       # Test configuration
```

## Challenges Faced

1. **Dynamic Content**: El País uses dynamic loading, requiring explicit waits
2. **Cookie Consent**: Had to handle cookie acceptance popups
3. **Image Download**: Implementing robust image download with error handling
4. **Cross-browser Compatibility**: Ensuring consistent behavior across browsers

## Future Improvements

- [ ] Add more robust error recovery
- [ ] Implement retry mechanisms for failed requests
- [ ] Add support for more news sources
- [ ] Implement database storage for scraped data
- [ ] Add unit tests for utility classes

## Technologies Used

- **Java 11**
- **Selenium WebDriver 4.18.1**
- **TestNG** for test framework
- **Maven** for build management
- **BrowserStack** for cross-browser testing
- **RapidAPI** for translation services
- **OkHttp** for HTTP requests
- **Gson** for JSON parsing 