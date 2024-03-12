package manager;

import java.util.Scanner;

public class ConsoleManager {
    CommandManager commandManager;
    Scanner reader = new Scanner(System.in);

    public ConsoleManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void run() {
        this.print("Введите команду: ");
        while (true) {
            String input = this.read();
            if (input.equals("exit")) {
                break;
            }
            String[] inputArray = input.split(" ");
            String commandName = inputArray[0];
            String[] args = new String[inputArray.length - 1];
            System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);
            try {
                this.commandManager.executeCommand(commandName, args);
            } catch (Exception e) {
                this.printError(e.getMessage());
            }
        }
    }

    public void print(Object obj) {
        System.out.println(obj);
    }

    public void printError(Object obj) {
        System.out.println("Ошибка: " + obj);
    }

    public String read() {
        return this.reader.nextLine();
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }
}
