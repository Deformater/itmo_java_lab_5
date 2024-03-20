package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class History extends Command {

    public History(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("history", "Выводит историю последних 13 комманд", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        this.consoleManager.print("История команд:");
        this.consoleManager.getCommandManager().getHistory()
                .forEach(command -> this.consoleManager.print(command.getName()));
    }
}
