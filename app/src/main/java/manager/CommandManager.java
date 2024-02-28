package manager;

import commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<Command> history = new ArrayList<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public List<Command> getHistory() {
        return history;
    }

    public void executeCommand(String commandName, String... args) {
        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);
            command.execute();
            this.addToHistory(command);
        } else {
            System.out.println("Команда не найдена");
        }
    }

    public void addToHistory(Command command){
        history.add(command);
    }
}