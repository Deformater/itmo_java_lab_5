package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The Exit class represents a command that stops the program.
 * It extends the Command class and provides an implementation for the execute method.
 */
public class Exit extends Command {

    /**
     * Constructs an Exit command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to be used
     * @param collectionManager the collection manager to be used
     */
    public Exit(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("exit", "Останавливает программу", consoleManager, collectionManager);
    }

    /**
     * Executes the Exit command.
     * This method stops the program by calling the System.exit method with the exit code 0.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        System.exit(0);
    }
}
