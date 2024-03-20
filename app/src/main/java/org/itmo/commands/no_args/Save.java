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

public class Save extends Command {
    public Save(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("save", "Сохраняет коллекцию в файл", consoleManager,
                collectionManager);
    }

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
