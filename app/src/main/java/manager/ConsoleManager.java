package manager;

public class ConsoleManager {
    CommandManager commandManager;

    public ConsoleManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void run() {
        print("Введите команду: ");
        while (true) {
            String command = this.read();
            if (command.equals("exit")) {
                break;
            }
            commandManager.executeCommand(command);
        }
    }

    public void print(Object obj) {
        System.out.println(obj);
    }

    public void printError(Object obj) {
        System.out.println("Ошибка: " + obj);
    }

    public String read() {
        return System.console().readLine();
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }
}
