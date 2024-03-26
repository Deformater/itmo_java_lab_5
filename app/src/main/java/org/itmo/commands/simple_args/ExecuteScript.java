package org.itmo.commands.simple_args;

import java.util.Stack;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The ExecuteScript class represents a command that reads and executes a script from a specified file.
 * It extends the Command class.
 */
public class ExecuteScript extends Command {
    Stack<String> history = new Stack<String>();

    /**
     * Constructs an ExecuteScript object with the specified console manager and collection manager.
     *
     * @param consoleManager   The console manager to be used for input/output operations.
     * @param collectionManager The collection manager to be used for managing the collection.
     */
    public ExecuteScript(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("execute_script",
                "Считывает и исполняет скрипт из указанного файла.\n\tПринимает аргумент типа String file_name",
                consoleManager, collectionManager);
    }

    /**
     * Executes the execute_script command with the specified arguments.
     *
     * @param args The arguments for the execute_script command.
     */
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
