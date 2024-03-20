package org.itmo.commands.no_args;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class Help extends Command {

    public Help(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("help", "Выводит это текст", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        this.consoleManager.print("Справка по командам:");
        this.consoleManager.getCommandManager().getCommands().values()
                .forEach(command -> this.consoleManager
                        .print(String.format("%s - %s", command.getName(), command.getHelpText())));

    }
}
