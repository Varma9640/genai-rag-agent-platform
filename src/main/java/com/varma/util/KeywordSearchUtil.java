package com.varma.util;

public final class KeywordSearchUtil {
    private KeywordSearchUtil() {
    }

    public static double calculateScore(String question, String content) {

        String[] keywords = question.toLowerCase().split("\\s+");
        String text = content.toLowerCase();
        int matches = 0;
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                matches++;
            }
        }
        return (double) matches / keywords.length;
    }
}
