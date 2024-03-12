package commands.no_args_commands;

import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;
import serializers.FlatSerializer;
import utils.Settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import commands.Command;

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
