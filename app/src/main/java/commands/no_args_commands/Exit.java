package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class Exit extends Command {

    public Exit(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("exit", "Останавливает программу", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        System.exit(0);
    }
}
