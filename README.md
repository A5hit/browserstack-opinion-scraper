# 🌐 BrowserStack Opinion Scraper

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green.svg)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.1-red.svg)](https://testng.org/)
[![BrowserStack](https://img.shields.io/badge/BrowserStack-Supported-brightgreen.svg)](https://www.browserstack.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A comprehensive web scraping automation project that demonstrates advanced Selenium testing, API integration, and cross-browser testing using BrowserStack. This project scrapes opinion articles from El País (Spanish newspaper), translates them to English, and performs word frequency analysis.

## 🎯 Features

- **🌍 Multi-language Support**: Scrapes Spanish content and translates to English
- **📱 Cross-Platform Testing**: Runs on desktop and mobile browsers via BrowserStack
- **🖼️ Image Processing**: Downloads and manages article cover images
- **📊 Data Analysis**: Performs word frequency analysis on translated content
- **⚡ Parallel Execution**: Supports both local and remote parallel testing
- **🔧 Flexible Configuration**: Environment-based configuration management

## 🚀 Quick Start

### Prerequisites

- **Java 11** or higher
- **Maven 3.6** or higher
- **BrowserStack Account** ([Free Trial Available](https://www.browserstack.com/))
- **RapidAPI Account** for translation services

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/A5hit/browserstack-opinion-scraper.git
   cd browserstack-opinion-scraper
   ```

2. **Set up environment variables**
   ```bash
   export BROWSERSTACK_USERNAME=your_browserstack_username
   export BROWSERSTACK_ACCESS_KEY=your_browserstack_access_key
   export TRANSLATION_API_KEY=your_rapidapi_key
   ```

3. **Run tests locally**
   ```bash
   mvn clean test -Plocal
   ```

4. **Run tests on BrowserStack**
   ```bash
   mvn clean test -Pbrowserstack
   ```

## 🏗️ Project Architecture

```
src/
├── main/java/org/browserstack/
│   ├── drivers/              # WebDriver management
│   │   ├── DriverManager.java
│   │   └── LocalDriverFactory.java
│   ├── pages/               # Page Object Model
│   │   ├── HomePage.java
│   │   └── OpinionPage.java
│   └── utils/               # Utility services
│       ├── ConfigLoader.java
│       ├── FileDownloader.java
│       ├── TranslationService.java
│       └── WordFrequencyAnalyzer.java
├── resources/
│   └── config.properties.template
└── test/
    ├── java/org/browserstack/tests/
    │   ├── OpinionScraperTest.java
    │   └── OpinionScraperLocalTest.java
    └── resources/
        ├── BrowserStackSuite.xml
        └── LocalSuite.xml
```

## 🔧 Configuration

### Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `BROWSERSTACK_USERNAME` | Your BrowserStack username | Yes (for remote) |
| `BROWSERSTACK_ACCESS_KEY` | Your BrowserStack access key | Yes (for remote) |
| `TRANSLATION_API_KEY` | RapidAPI translation service key | Yes |

### BrowserStack Setup

1. Create a free account at [BrowserStack](https://www.browserstack.com/)
2. Get your credentials from the [Automate Dashboard](https://automate.browserstack.com/)
3. Set environment variables or update `browserstack.yml`

### Translation API Setup

1. Sign up for [RapidAPI](https://rapidapi.com/)
2. Subscribe to "Rapid Translate Multi Traduction" API
3. Get your API key and set the environment variable

## 🧪 Test Execution

### Local Testing
```bash
# Run local tests with Chrome headless
mvn clean test -Plocal
```

### BrowserStack Testing
```bash
# Run tests on BrowserStack with 5 parallel threads
mvn clean test -Pbrowserstack
```

### Test Coverage

The project tests across multiple platforms:

| Platform | Browser | Version |
|----------|---------|---------|
| Windows 11 | Chrome | Latest |
| Windows 11 | Firefox | Latest |
| Windows 11 | Edge | Latest |
| macOS Ventura | Safari | Latest |
| iOS 16 | Safari | Latest |
| Android 12 | Chrome | Latest |

## 📊 Output & Results

### Console Output
- Article titles and content in Spanish
- Translated titles in English
- Word frequency analysis results
- Image download status

### File Output
- **Images**: Downloaded to `output/images/` with unique naming
- **Logs**: Detailed execution logs in `log/` directory
- **Screenshots**: Error screenshots on test failures

### Sample Output
```
📰 Scraping Article 1: "El futuro de la inteligencia artificial"
🔄 Translated: "The future of artificial intelligence"
📥 Image downloaded: output/images/article_1_20241201_143022.jpg
📊 Word frequency analysis: "the" appears 3 times
```

## 🛠️ Technologies

- **Java 11** - Core programming language
- **Selenium WebDriver 4.18.1** - Web automation framework
- **TestNG 7.10.1** - Test execution framework
- **Maven** - Build and dependency management
- **BrowserStack Java SDK** - Cross-browser testing platform
- **RapidAPI** - Translation service integration
- **OkHttp 4.12.0** - HTTP client for API calls
- **Gson 2.13.1** - JSON parsing library

## 🎯 Key Features

### ✅ Core Functionality
- **El País Website Access**: Navigates to https://elpais.com
- **Spanish Language Validation**: Ensures website content is in Spanish
- **Opinion Section Scraping**: Extracts first 5 articles from the Opinion section
- **Content Extraction**: Captures titles and content in Spanish
- **Image Download**: Downloads and saves article cover images locally
- **Translation Service**: Translates Spanish titles to English using RapidAPI
- **Word Frequency Analysis**: Identifies words repeated more than twice across translated headers
- **Cross-Browser Testing**: Runs on BrowserStack with parallel execution
- **Desktop & Mobile Coverage**: Tests across multiple browsers and devices

### 🔧 Advanced Features
- **Page Object Model**: Clean separation of test logic and page interactions
- **Parallel Execution**: Configurable parallel test execution
- **Error Handling**: Comprehensive error handling with screenshots
- **Configuration Management**: Environment-based configuration
- **Logging**: Detailed logging for debugging and monitoring

## 🚀 Getting Started with Development

### Prerequisites
```bash
# Check Java version
java -version

# Check Maven version
mvn -version
```

### Development Setup
```bash
# Clone and setup
git clone https://github.com/A5hit/browserstack-opinion-scraper.git
cd browserstack-opinion-scraper

# Install dependencies
mvn clean install

# Run tests locally
mvn test -Plocal
```

### IDE Setup
This project works with any Java IDE. Recommended setup:
- **IntelliJ IDEA**: Import as Maven project
- **Eclipse**: Import existing Maven project
- **VS Code**: Install Java extension pack

## 📈 Performance & Scalability

- **Parallel Execution**: Up to 5 parallel threads on BrowserStack
- **Local Testing**: Single-threaded execution for development
- **Resource Management**: Automatic cleanup of WebDriver instances
- **Error Recovery**: Retry mechanisms for transient failures

## 🔍 Troubleshooting

### Common Issues

1. **BrowserStack Connection Issues**
   ```bash
   # Verify credentials
   echo $BROWSERSTACK_USERNAME
   echo $BROWSERSTACK_ACCESS_KEY
   ```

2. **Translation API Errors**
   ```bash
   # Check API key
   echo $TRANSLATION_API_KEY
   ```

3. **Local Test Failures**
   ```bash
   # Check Chrome installation
   google-chrome --version
   ```

### Debug Mode
```bash
# Enable debug logging
mvn test -Plocal -Ddebug=true
```

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **BrowserStack** for providing cross-browser testing platform
- **RapidAPI** for translation services
- **El País** for the content source
- **Selenium** community for the automation framework

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/A5hit/browserstack-opinion-scraper/issues)
- **Documentation**: [Wiki](https://github.com/A5hit/browserstack-opinion-scraper/wiki)
- **Email**: [Contact Support](mailto:support@example.com)

## 🔗 Links

- **Repository**: [browserstack-opinion-scraper](https://github.com/A5hit/browserstack-opinion-scraper)
- **BrowserStack**: [Cross-browser testing platform](https://www.browserstack.com/)
- **RapidAPI**: [API marketplace](https://rapidapi.com/)
- **El País**: [Spanish news website](https://elpais.com/)

---

<div align="center">
  <p>Made with ❤️ for web automation and testing</p>
  <p>⭐ Star this repository if you find it helpful!</p>
</div> 