package org.itmo.exceptions;

/**
 * This exception is thrown when there is an error with the command arguments.
 */
public class CommandArgumentsException extends Exception {

    /**
     * Constructs a new CommandArgumentsException with the specified detail message.
     *
     * @param message the detail message
     */
    public CommandArgumentsException(String message) {
        super(message);
    }
}