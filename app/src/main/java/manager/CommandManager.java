package manager;

import commands.Command;
import exceptions.CommandNotFoundError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<Command> history = new ArrayList<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public List<Command> getHistory() {
        return history.subList(Integer.max(history.size() - 13, 0), history.size());
    }

    public void executeCommand(String commandName, String... args) throws CommandNotFoundError {
        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);
            command.execute(args);
            this.addToHistory(command);
        } else {
            throw new CommandNotFoundError("Команда не найдена");
        }
    }

    public void addToHistory(Command command) {
        history.add(command);
    }
}
