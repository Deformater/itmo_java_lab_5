package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The Help class represents a command that displays help information about available commands.
 * It extends the Command class.
 */
public class Help extends Command {

    /**
     * Constructs a Help command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for printing output
     * @param collectionManager the collection manager to retrieve the available commands
     */
    public Help(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("help", "Выводит это текст", consoleManager, collectionManager);
    }

    /**
     * Executes the Help command.
     * Prints the help information for all available commands.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        this.consoleManager.print("Справка по командам:");
        this.consoleManager.getCommandManager().getCommands().values()
                .forEach(command -> this.consoleManager
                        .print(String.format("%s - %s", command.getName(), command.getHelpText())));
    }
}
