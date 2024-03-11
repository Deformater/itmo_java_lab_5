package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class Info extends Command {

    public Info(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("info", "Выводит информацию о коллекции", consoleManager, collectionManager);
    }

    public void execute() {
        this.consoleManager.print("Информация о коллекции:");
        this.consoleManager.print(this.collectionManager);
    }
}
