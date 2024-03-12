package commands.no_args_commands;

import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;
import serializers.FlatSerializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import commands.Command;
import io.github.cdimascio.dotenv.Dotenv;

public class Save extends Command {
    public Save(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("save", "Сохраняет коллекцию в файл", consoleManager,
                collectionManager);
    }

    public void execute(String... args) {
        Dotenv dotenv = Dotenv.load();
        String filePath = dotenv.get("SAVE_FILE_PATH");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String comma = "";
            writer.write("[");
            for (Flat flat : this.collectionManager.getCollection()) {
                writer.write(comma + FlatSerializer.jsonDumps(flat));
                writer.newLine();
                comma = ",";
            }
            writer.write("]");
            this.consoleManager.print("Коллекция сохранена в файл " + filePath);
        } catch (IOException e) {
            this.consoleManager.printError(e.getMessage());
        }
    }
}
