package org.itmo.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itmo.commands.Command;
import org.itmo.commands.complex_args.Add;
import org.itmo.commands.complex_args.AddIfMin;
import org.itmo.commands.complex_args.FilterLessThenHouse;
import org.itmo.commands.complex_args.RemoveLower;
import org.itmo.commands.complex_args.Update;
import org.itmo.commands.no_args.Clear;
import org.itmo.commands.no_args.Exit;
import org.itmo.commands.no_args.Help;
import org.itmo.commands.no_args.History;
import org.itmo.commands.no_args.Info;
import org.itmo.commands.no_args.MaxByArea;
import org.itmo.commands.no_args.PrintFieldDescendingHouse;
import org.itmo.commands.no_args.Save;
import org.itmo.commands.no_args.Show;
import org.itmo.commands.simple_args.ExecuteScript;
import org.itmo.commands.simple_args.RemoveById;
import org.itmo.exceptions.CommandNotFoundError;

/**
 * The CommandManager class manages commands and their execution history.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<Command> history = new ArrayList<>();

    public CommandManager(ConsoleManager consoleManager, CollectionManager collectionManager) {
        Add addCommand = new Add(consoleManager, collectionManager);
        this.register(addCommand);
        History historyCommand = new History(consoleManager, collectionManager);
        this.register(historyCommand);
        Help helpCommand = new Help(consoleManager, collectionManager);
        this.register(helpCommand);
        Info infoCommand = new Info(consoleManager, collectionManager);
        this.register(infoCommand);
        Show showCommand = new Show(consoleManager, collectionManager);
        this.register(showCommand);
        Update updateCommand = new Update(consoleManager, collectionManager);
        this.register(updateCommand);
        RemoveById removeByIdCommand = new RemoveById(consoleManager, collectionManager);
        this.register(removeByIdCommand);
        Clear clearCommand = new Clear(consoleManager, collectionManager);
        this.register(clearCommand);
        Save saveCommand = new Save(consoleManager, collectionManager);
        this.register(saveCommand);
        ExecuteScript executeScriptCommand = new ExecuteScript(consoleManager, collectionManager);
        this.register(executeScriptCommand);
        AddIfMin addIfMinCommand = new AddIfMin(consoleManager, collectionManager);
        this.register(addIfMinCommand);
        RemoveLower removeLowerCommand = new RemoveLower(consoleManager, collectionManager);
        this.register(removeLowerCommand);
        FilterLessThenHouse filterLessThenHouseCommand = new FilterLessThenHouse(consoleManager, collectionManager);
        this.register(filterLessThenHouseCommand);
        MaxByArea maxByAreaCommand = new MaxByArea(consoleManager, collectionManager);
        this.register(maxByAreaCommand);
        PrintFieldDescendingHouse printFieldDescendingHouseCommand = new PrintFieldDescendingHouse(consoleManager,
                collectionManager);
        this.register(printFieldDescendingHouseCommand);
        Exit exitCommand = new Exit(consoleManager, collectionManager);
        this.register(exitCommand);
    }

    /**
     * Registers a command with the CommandManager.
     *
     * @param command The command to register.
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Returns the map of registered commands.
     *
     * @return The map of registered commands.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Returns the list of recently executed commands.
     *
     * @return The list of recently executed commands.
     */
    public List<Command> getHistory() {
        return history.subList(Integer.max(history.size() - 13, 0), history.size());
    }

    /**
     * Executes a command with the given name and arguments.
     *
     * @param commandName The name of the command to execute.
     * @param fromScript  Indicates whether the command is executed from a script.
     * @param args        The arguments for the command.
     * @throws CommandNotFoundError If the command with the given name is not found.
     */
    public void executeCommand(String commandName, boolean fromScript, String... args) throws CommandNotFoundError {
        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);
            command.setFromScript(fromScript);
            command.execute(args);
            this.addToHistory(command);
        } else {
            throw new CommandNotFoundError("Команда не найдена");
        }
    }

    /**
     * Adds a command to the execution history.
     *
     * @param command The command to add to the history.
     */
    public void addToHistory(Command command) {
        history.add(command);
    }
}
