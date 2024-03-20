package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

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
