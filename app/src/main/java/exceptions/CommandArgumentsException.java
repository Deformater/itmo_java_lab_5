package exceptions;

public class CommandArgumentsException extends Exception{
    public CommandArgumentsException(String message) {
        super(message);
    }
}