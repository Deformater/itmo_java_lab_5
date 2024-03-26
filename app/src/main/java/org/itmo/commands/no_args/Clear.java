package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The Clear class represents a command that clears the collection.
 */
public class Clear extends Command {

    /**
     * Constructs a Clear command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to be used
     * @param collectionManager the collection manager to be used
     */
    public Clear(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("clear", "Отчищает колекцию", consoleManager, collectionManager);
    }

    /**
     * Executes the Clear command.
     *
     * @param args the command arguments (not used)
     */
    public void execute(String... args) {
        this.collectionManager.clear();
        this.consoleManager.print("Коллекция очищена");
    }
}
