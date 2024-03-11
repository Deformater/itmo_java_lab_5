package commands.complex_args_commands;

import commands.Command;
import forms.FlatForm;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;

public class Update extends Command {

    public Update(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("add", "Добавляет квартиру в колекцию, запускает форму ввода Квартиры", new FlatForm(consoleManager),
                consoleManager, collectionManager);
    }

    public void execute() {
        Flat flat = (Flat) this.form.run();
        flat.setGenId(collectionManager);
        this.collectionManager.add(flat);
    }
}
