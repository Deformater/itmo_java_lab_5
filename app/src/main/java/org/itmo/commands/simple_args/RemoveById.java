package org.itmo.commands.simple_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class RemoveById extends Command {

    public RemoveById(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("remove_by_id", "Удаляет квартиру из колекции\n\tпринимает аргумент типа Integer id",
                consoleManager, collectionManager);
    }

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
