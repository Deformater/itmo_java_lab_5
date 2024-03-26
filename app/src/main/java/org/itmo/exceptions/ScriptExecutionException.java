package org.itmo.exceptions;

/**
 * Exception thrown to indicate an error during script execution.
 */
public class ScriptExecutionException extends RuntimeException {

    /**
     * Constructs a new ScriptExecutionException with the specified detail message.
     *
     * @param message the detail message
     */
    public ScriptExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ScriptExecutionException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public ScriptExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
