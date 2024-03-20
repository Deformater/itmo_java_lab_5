package org.itmo.manager;

import java.util.Scanner;

import org.itmo.exceptions.ScriptExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 * The ConsoleManager class handles the interaction with the console for executing commands.
 */
public class ConsoleManager {
    Scanner reader;
    boolean isScript = false;
    CommandManager commandManager;

    /**
     * Constructs a ConsoleManager object with the specified CommandManager.
     *
     */
    public ConsoleManager() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Runs the console manager, continuously prompting the user for commands and executing them.
     */
    public void run(CommandManager commandManager) {
        this.print("Введите команду: ");
        this.commandManager = commandManager;
        while (true) {
            String input = "";
            try{
                input = this.read();
            } catch (NoSuchElementException e) {
                this.print("Завершение работы");
                System.exit(0);
            }
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

    /**
     * Executes the commands from the specified script file.
     *
     * @param path The path to the script file.
     */
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

    /**
     * Prints the specified object to the console.
     *
     * @param obj The object to be printed.
     */
    public void print(Object obj) {
        System.out.println(obj);
    }

    /**
     * Prints the specified error message to the console.
     *
     * @param obj The error message to be printed.
     */
    public void printError(Object obj) {
        System.out.println("Ошибка: " + obj);
    }

    /**
     * Reads a line of input from the console.
     *
     * @return The input line as a string.
     */
    public String read() {
        return this.reader.nextLine();
    }

    /**
     * Gets the CommandManager associated with this ConsoleManager.
     *
     * @return The CommandManager object.
     */
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
}
