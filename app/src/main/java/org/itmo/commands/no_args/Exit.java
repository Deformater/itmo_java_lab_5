package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class Exit extends Command {

    public Exit(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("exit", "Останавливает программу", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        System.exit(0);
    }
}
