package manager;

import java.util.Scanner;

import exceptions.ScriptExecutionException;

import java.io.File;
import java.io.FileNotFoundException;

public class ConsoleManager {
    CommandManager commandManager;
    Scanner reader;
    boolean isScript = false;

    public ConsoleManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.reader = new Scanner(System.in);
    }

    public void run() {
        this.print("Введите команду: ");
        while (true) {
            String input = this.read();
            String[] inputArray = input.split(" ");
            String commandName = inputArray[0];
            String[] args = new String[inputArray.length - 1];
            System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);
            try {
                this.commandManager.executeCommand(commandName, false, args);
            } catch (Exception e) {
                this.printError(e.getMessage());
            }
            if (this.isScript) {
                this.reader = new Scanner(System.in);
                this.isScript = false;
            }
        }
    }

    public void executeScript(String path) {
        File file = new File(path);
        if (file.getTotalSpace() == 0) {
            this.printError("Файл пуст");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (!this.isScript) {
                this.reader = scanner;
                this.isScript = true;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] inputArray = line.split(" ");
                String commandName = inputArray[0];
                String[] args = new String[inputArray.length - 1];
                System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);
                this.commandManager.executeCommand(commandName, true, args);
            }
        } catch (ScriptExecutionException e) {
            this.printError(e.getMessage());
        } catch (FileNotFoundException e) {
            this.printError("Файл " + path + " не найден");
        } catch (Exception e) {
            this.printError("Ошибка при чтении файла " + path + ": " + e.getMessage());
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
