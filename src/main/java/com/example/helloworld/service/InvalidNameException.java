package com.example.helloworld.service;

/** Thrown when the supplied name does not satisfy the greeting rules. */
public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("Name must be present and start with a letter from A to M");
    }
}
