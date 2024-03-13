package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

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
