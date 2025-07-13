# BrowserStack Opinion Scraper

A comprehensive web scraping project that demonstrates Selenium automation, API integration, and cross-browser testing using BrowserStack. This project scrapes opinion articles from El País (Spanish newspaper), translates them to English, and performs word frequency analysis.

## 🎯 Assignment Requirements Fulfilled

This project successfully implements all requirements from the technical assignment:

### ✅ Core Requirements
- **El País Website Access**: Navigates to https://elpais.com
- **Spanish Language Validation**: Ensures website content is in Spanish
- **Opinion Section Scraping**: Extracts first 5 articles from the Opinion section
- **Content Extraction**: Captures titles and content in Spanish
- **Image Download**: Downloads and saves article cover images locally
- **Translation Service**: Translates Spanish titles to English using RapidAPI
- **Word Frequency Analysis**: Identifies words repeated more than twice across translated headers
- **Cross-Browser Testing**: Runs on BrowserStack with 5 parallel threads
- **Desktop & Mobile Coverage**: Tests across Chrome, Firefox, Edge (desktop) and Safari iOS, Chrome Android (mobile)

## 🚀 Quick Start

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- BrowserStack account (free trial available)
- RapidAPI account for translation service

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/a5hit/browserstack-opinion-scraper.git
   cd browserstack-opinion-scraper
   ```

2. **Configure BrowserStack credentials** using environment variables:
   ```bash
   export BROWSERSTACK_USERNAME=your_browserstack_username
   export BROWSERSTACK_ACCESS_KEY=your_browserstack_access_key
   export TRANSLATION_API_KEY=your_rapidapi_key
   ```

3. **Run the tests**
   ```bash
   # Run tests with BrowserStack Java SDK (5 parallel threads)
   mvn clean test
   ```

## 🏗️ Project Architecture

### Page Object Model
- **HomePage**: Handles navigation to El País and cookie acceptance
- **OpinionPage**: Manages article scraping, content extraction, and image downloads

### Utility Classes
- **TranslationService**: Integrates with RapidAPI for Spanish to English translation
- **WordFrequencyAnalyzer**: Analyzes word frequency in translated titles
- **FileDownloader**: Handles image downloads with unique naming
- **ConfigLoader**: Manages configuration properties

### Test Framework
- **TestNG**: Test execution framework with parallel support
- **BrowserStack Java SDK**: Automatic driver management and cross-browser testing

## 📁 Project Structure

```
src/
├── main/java/org/browserstack/
│   ├── pages/           # Page Object classes
│   │   ├── HomePage.java
│   │   └── OpinionPage.java
│   └── utils/           # Utility classes
│       ├── ConfigLoader.java
│       ├── FileDownloader.java
│       ├── TranslationService.java
│       └── WordFrequencyAnalyzer.java
├── resources/
│   └── config.properties
└── test/
    ├── java/org/browserstack/tests/
    │   └── OpinionScraperTest.java
    └── resources/
        ├── browserstack.yml          # BrowserStack configuration
        └── BrowserStackParallelSuite.xml
```

## 🔧 Configuration

### 🔒 Security Note
This project uses environment variables to protect sensitive credentials. The `config.properties` file is excluded from Git to prevent exposing API keys and credentials.

### BrowserStack Setup
1. Create a free account at [BrowserStack](https://www.browserstack.com/)
2. Get your username and access key from the dashboard
3. Update `config.properties` with your credentials

### Translation API Setup
1. Sign up for [RapidAPI](https://rapidapi.com/)
2. Subscribe to "Rapid Translate Multi Traduction" API
3. Get your API key and update `config.properties`

### Test Execution Modes
- **Local Mode**: Runs tests locally using Chrome in headless mode
- **Remote Mode**: Runs tests on BrowserStack with 5 parallel threads

## 🧪 Test Execution

### Test Execution
```bash
mvn clean test
```

### Parallel Test Configuration
The project runs 5 parallel tests across:
- **Desktop Browsers**: Chrome, Firefox, Edge on Windows 11
- **Mobile Browsers**: Safari on iOS 15, Chrome on Android 12

The BrowserStack Java SDK automatically handles:
- Driver creation and management
- Parallel test execution
- Session management
- Test reporting and observability

## 📊 Output

### Console Output
- Article titles and content in Spanish
- Translated titles in English
- Word frequency analysis results
- Download status for images

### File Output
- **Images**: Downloaded to `output/images/` with unique naming
- **Screenshots**: Error screenshots saved on test failures

## 🛠️ Technologies Used

- **Java 11**: Core programming language
- **Selenium WebDriver 4.18.1**: Web automation framework
- **TestNG 7.10.1**: Test execution framework
- **Maven**: Build and dependency management
- **BrowserStack**: Cross-browser testing platform
- **RapidAPI**: Translation service integration
- **OkHttp**: HTTP client for API calls
- **Gson**: JSON parsing library

## 🎯 Key Features

- **Cross-Browser Compatibility**: Tests run on multiple browsers and devices
- **Image Download**: Automatic download with duplicate prevention
- **Translation Integration**: Real-time Spanish to English translation
- **Word Analysis**: Frequency analysis of translated content
- **Error Handling**: Comprehensive error handling with screenshots
- **Parallel Execution**: 5 parallel threads for efficient testing
- **Configuration Management**: Environment-based configuration

## 🔍 Challenges Solved

1. **Dynamic Content Loading**: Implemented explicit waits for dynamic content
2. **Cookie Consent**: Automated handling of cookie acceptance popups
3. **Image Download**: Robust image download with error handling
4. **Cross-Browser Compatibility**: Ensured consistent behavior across browsers
5. **Translation API Integration**: Seamless integration with RapidAPI
6. **Parallel Test Execution**: Configured TestNG for parallel BrowserStack testing

## 📈 Future Enhancements

- [ ] Add more robust error recovery mechanisms
- [ ] Implement retry logic for failed API calls
- [ ] Add support for multiple news sources
- [ ] Implement database storage for scraped data
- [ ] Add comprehensive unit test coverage
- [ ] Implement CI/CD pipeline integration

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is created for educational and demonstration purposes.

## 🔗 Links

- **GitHub Repository**: [browserstack-opinion-scraper](https://github.com/a5hit/browserstack-opinion-scraper)
- **BrowserStack**: [Cross-browser testing platform](https://www.browserstack.com/)
- **RapidAPI**: [API marketplace](https://rapidapi.com/)
- **El País**: [Spanish news website](https://elpais.com/)

---

**Note**: This project was created to fulfill the technical assignment requirements for demonstrating web scraping, API integration, and cross-browser testing capabilities. 