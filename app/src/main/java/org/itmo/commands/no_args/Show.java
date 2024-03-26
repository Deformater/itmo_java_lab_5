package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The Show class represents a command that displays the list of elements in a collection.
 * It extends the Command class.
 */
public class Show extends Command {

    /**
     * Constructs a Show object with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for input/output operations
     * @param collectionManager the collection manager to use for accessing the collection
     */
    public Show(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("show", "Выводит список элементов коллекции", consoleManager, collectionManager);
    }

    /**
     * Executes the show command.
     * If the collection is not empty, it prints the elements of the collection.
     * Otherwise, it prints a message indicating that the collection is empty.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        if (this.collectionManager.size() > 0) {
            this.consoleManager.print("Элементы коллекции:");
            this.collectionManager.getCollection().forEach(el -> this.consoleManager.print(el));
        } else {
            this.consoleManager.print("В коллекции пока нет элементов(");
        }
    }
}
