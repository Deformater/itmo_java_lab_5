package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class Info extends Command {

    public Info(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("info", "Выводит информацию о коллекции", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        this.consoleManager.print("Информация о коллекции:");
        this.consoleManager.print(this.collectionManager);
    }
}
