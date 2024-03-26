package org.itmo.exceptions;

/**
 * The ValidationException class represents an exception that is thrown when a validation error occurs.
 */
public class ValidationException extends Exception {

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ValidationException(String message) {
        super(message);
    }
}
