package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The Info class represents a command that displays information about the collection.
 * It extends the Command class.
 */
public class Info extends Command {

    /**
     * Constructs an Info object with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to be used
     * @param collectionManager the collection manager to be used
     */
    public Info(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("info", "Выводит информацию о коллекции", consoleManager, collectionManager);
    }

    /**
     * Executes the info command.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        this.consoleManager.print("Информация о коллекции:");
        this.consoleManager.print(this.collectionManager);
    }
}
