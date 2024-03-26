package org.itmo.exceptions;

/**
 * This exception is thrown when a command is not found.
 */
public class CommandNotFoundError extends Exception {

    /**
     * Constructs a new CommandNotFoundError with the specified detail message.
     *
     * @param message the detail message
     */
    public CommandNotFoundError(String message) {
        super(message);
    }
}
