package org.browserstack.utils;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class WordFrequencyAnalyzerTest {

    @Test
    public void testAnalyzeWithRepeatedWords() {
        // Test with titles that have repeated words
        List<String> titles = Arrays.asList(
            "The Future of Technology",
            "Technology Trends in 2024",
            "Future of AI Technology"
        );
        
        // This should not throw any exceptions
        WordFrequencyAnalyzer.analyze(titles);
    }

    @Test
    public void testAnalyzeWithNoRepeatedWords() {
        // Test with titles that have no repeated words
        List<String> titles = Arrays.asList(
            "Breaking News Today",
            "Sports Update",
            "Weather Forecast"
        );
        
        // This should not throw any exceptions
        WordFrequencyAnalyzer.analyze(titles);
    }

    @Test
    public void testAnalyzeWithEmptyList() {
        // Test with empty list
        List<String> titles = Arrays.asList();
        
        // This should not throw any exceptions
        WordFrequencyAnalyzer.analyze(titles);
    }

    @Test
    public void testAnalyzeWithSpecialCharacters() {
        // Test with titles containing special characters
        List<String> titles = Arrays.asList(
            "COVID-19 Update",
            "AI & Machine Learning",
            "Tech@2024"
        );
        
        // This should not throw any exceptions
        WordFrequencyAnalyzer.analyze(titles);
    }
} 