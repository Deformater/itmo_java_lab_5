package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class MaxByArea extends Command {
    public MaxByArea(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "max_by_area",
                "Выводит любой объект из коллекции, значение поля area которого является максимальным",
                consoleManager,
                collectionManager);
    }

    public void execute(String... args) {
        this.consoleManager.print(collectionManager.getMaxByArea());
    }
}
