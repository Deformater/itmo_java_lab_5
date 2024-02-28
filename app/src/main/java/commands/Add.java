package commands;

import forms.FlatForm;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;

public class Add extends Command {

    public Add(String name, String helpText, ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("add", "Добавление элемента", new FlatForm(consoleManager), consoleManager, collectionManager);
    }

    public void execute() {
        Flat flat = (Flat) this.form.run();
        flat.generateId(collectionManager);
        this.collectionManager.add(flat);
    }
}
