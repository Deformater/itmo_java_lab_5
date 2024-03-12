package commands.simple_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;
import java.util.ArrayList;
import java.util.List;

public class ExecuteScript extends Command {
    List<String> history = new ArrayList<>();

    public ExecuteScript(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("execute_script",
                "Считывает и исполняет скрипт из указанного файла.\n\tПринимает аргумент типа String file_name",
                consoleManager, collectionManager);

    }

    public void execute(String... args) {
        if (args.length != 1) {
            this.consoleManager.printError("Команда принимает один аргумент");
            return;
        }
        if (history.contains(args[0])) {
            this.consoleManager.printError("Предотвращено зацикливание скрипта");
            return;
        }
        history.add(args[0]);
        this.consoleManager.executeScript(args[0]);
        history.remove(args[0]);
    }
}
