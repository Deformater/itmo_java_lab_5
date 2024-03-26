package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * Represents a command that outputs any object from the collection whose 'area' field has the maximum value.
 */
public class MaxByArea extends Command {

    /**
     * Constructs a new MaxByArea command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to be used
     * @param collectionManager the collection manager to be used
     */
    public MaxByArea(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "max_by_area",
                "Выводит любой объект из коллекции, значение поля area которого является максимальным",
                consoleManager,
                collectionManager);
    }

    /**
     * Executes the MaxByArea command.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        this.consoleManager.print(collectionManager.getMaxByArea());
    }
}
