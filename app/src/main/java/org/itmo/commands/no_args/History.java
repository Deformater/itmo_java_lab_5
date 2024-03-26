package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The History class represents a command that displays the history of the last 13 commands.
 * It extends the Command class.
 */
public class History extends Command {

    /**
     * Constructs a History command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to be used
     * @param collectionManager the collection manager to be used
     */
    public History(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("history", "Выводит историю последних 13 комманд", consoleManager, collectionManager);
    }

    /**
     * Executes the History command.
     *
     * @param args the command arguments
     */
    public void execute(String... args) {
        this.consoleManager.print("История команд:");
        this.consoleManager.getCommandManager().getHistory()
                .forEach(command -> this.consoleManager.print(command.getName()));
    }
}
