package org.browserstack.utils;

import java.util.*;

public class WordFrequencyAnalyzer {

    public static void analyze(List<String> translatedTitles) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String title : translatedTitles) {
            String[] words = title.split("\\s+");
            for (String word : words) {
                String clean = word.toLowerCase().replaceAll("[^a-z]", "");
                if (clean.isEmpty()) continue;

                frequencyMap.put(clean, frequencyMap.getOrDefault(clean, 0) + 1);
            }
        }

        System.out.println("Words repeated more than twice:");
        boolean found = false;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("None.");
        }
    }
}
