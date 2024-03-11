package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class History extends Command {

    public History(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("history", "Выводит историю последних 13 комманд", consoleManager, collectionManager);
    }

    public void execute() {
        this.consoleManager.print("История команд:");
        this.consoleManager.getCommandManager().getHistory()
                .forEach(command -> this.consoleManager.print(command.getName()));
    }
}
