package com.example.helloworld.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    /**
     * Returns a greeting for names starting with A–M (case-insensitive).
     *
     * @throws InvalidNameException if the name is missing, blank, or does not start with A–M
     */
    public String greet(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidNameException();
        }

        String trimmed = name.strip();
        char first = trimmed.charAt(0);
        if (!isInFirstHalfOfAlphabet(first)) {
            throw new InvalidNameException();
        }

        return "Hello " + Character.toUpperCase(first) + trimmed.substring(1);
    }

    private static boolean isInFirstHalfOfAlphabet(char c) {
        return (c >= 'A' && c <= 'M') || (c >= 'a' && c <= 'm');
    }
}
