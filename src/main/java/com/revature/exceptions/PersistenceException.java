package com.revature.exceptions;

/**
 * This exception is thrown when an error occurs in the database
 */
public class PersistenceException extends Exception {

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message) {
        super(message);
    }
}
