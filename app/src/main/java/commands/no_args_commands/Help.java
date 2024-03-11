package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class Help extends Command {

    public Help(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("help", "Выводит это текст", consoleManager, collectionManager);
    }

    public void execute() {
        this.consoleManager.print("Справка по командам:");
        this.consoleManager.getCommandManager().getCommands().values()
                .forEach(command -> this.consoleManager
                        .print(String.format("%s - %s", command.getName(), command.getHelpText())));

    }
}
