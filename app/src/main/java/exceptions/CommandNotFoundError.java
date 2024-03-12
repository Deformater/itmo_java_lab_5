package exceptions;

public class CommandNotFoundError extends Exception{
    public CommandNotFoundError(String message) {
        super(message);
    }
}
