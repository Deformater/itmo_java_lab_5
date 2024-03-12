package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class Clear extends Command {
    public Clear(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("clear", "Отчищает колекцию", consoleManager,
                collectionManager);
    }

    public void execute(String... args) {
        this.collectionManager.clear();
        this.consoleManager.print("Коллекция очищена");
    }
}
