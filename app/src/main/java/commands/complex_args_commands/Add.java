package commands.complex_args_commands;

import commands.Command;
import handlers.FlatHandler;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;

public class Add extends Command {
    private FlatHandler handler;

    public Add(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("add", "Добавляет квартиру в колекцию, запускает форму ввода Квартиры", consoleManager, collectionManager);
        this.handler = new FlatHandler(consoleManager);
    }

    public void execute(String... args) {
        Flat flat = (Flat) this.handler.create();
        flat.setGenId(collectionManager);
        this.collectionManager.add(flat);
        this.consoleManager.print("Квартира добавлена в коллекцию");
    }
}
