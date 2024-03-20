package org.itmo.commands.simple_args;

import java.util.Stack;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public class ExecuteScript extends Command {
    Stack<String> history = new Stack<String>();

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
        history.push(args[0]);
        this.consoleManager.executeScript(args[0]);
        history.pop();
    }
}
