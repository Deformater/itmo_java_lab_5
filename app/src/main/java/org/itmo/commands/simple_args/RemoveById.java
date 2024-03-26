package org.itmo.commands.simple_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * Represents a command that removes an apartment from the collection by its ID.
 */
public class RemoveById extends Command {

    /**
     * Constructs a new RemoveById command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for input/output
     * @param collectionManager the collection manager to use for managing the collection
     */
    public RemoveById(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("remove_by_id", "Удаляет квартиру из колекции\n\tпринимает аргумент типа Integer id",
                consoleManager, collectionManager);
    }

    /**
     * Executes the RemoveById command with the specified arguments.
     *
     * @param args the arguments for the command
     */
    public void execute(String... args) {
        if (args.length != 1) {
            this.consoleManager.printError("Команда принимает один аргумент");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            this.consoleManager.printError("Аргумент должен быть числом");
            return;
        }

        if (this.collectionManager.remove(id)) {
            this.consoleManager.print("Элемент с id " + id + " удален");
        } else {
            this.consoleManager.printError("Элемента с таким id нет в коллекции");
        }
    }
}
