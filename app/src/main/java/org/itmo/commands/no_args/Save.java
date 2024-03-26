package org.itmo.commands.no_args;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.itmo.commands.Command;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Flat;
import org.itmo.serializers.FlatSerializer;
import org.itmo.utils.Settings;

/**
 * The Save class represents a command that saves the collection to a file.
 * It extends the Command class and implements the execute method.
 */
public class Save extends Command {

    /**
     * Constructs a Save command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use
     * @param collectionManager the collection manager to use
     */
    public Save(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("save", "Сохраняет коллекцию в файл", consoleManager, collectionManager);
    }

    /**
     * Executes the save command.
     * Writes the collection to a file in JSON format.
     *
     * @param args the command arguments (not used)
     */
    public void execute(String... args) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Settings.saveFilePath))) {
            String comma = "";
            writer.write("[");
            for (Flat flat : this.collectionManager.getCollection()) {
                writer.write(comma + "\n" + FlatSerializer.jsonDumps(flat));
                comma = ",";
            }
            writer.newLine();
            writer.write("]");
            this.consoleManager.print("Коллекция сохранена в файл " + Settings.saveFilePath);
        } catch (IOException e) {
            this.consoleManager.printError(e.getMessage());
        }
    }
}
