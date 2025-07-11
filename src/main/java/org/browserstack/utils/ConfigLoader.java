package org.browserstack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("Warning: config.properties not found. Using environment variables only.");
            }
        } catch (IOException e) {
            System.out.println("Warning: Failed to load config.properties: " + e.getMessage() + ". Using environment variables only.");
        }
    }

    public static String get(String key) {
        // First check environment variables
        String envValue = System.getenv(key.toUpperCase().replace(".", "_"));
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue;
        }
        
        // Fall back to properties file
        return properties.getProperty(key);
    }
    
    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }
}
