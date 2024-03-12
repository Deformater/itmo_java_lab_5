package commands.complex_args_commands;

import commands.Command;
import forms.FlatForm;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;

public class Update extends Command {
    private FlatForm form;

    public Update(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("update", "Изменяет квартиру в колекции, запускает форму изменения данных Квартиры\n\tпринимает аргумент типа Integer id",
                consoleManager, collectionManager);
        this.form = new FlatForm(consoleManager);
    }

    public void execute(String... args) {
        if (args.length != 1) {
            this.consoleManager.printError("Команда принимает один аргумент");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            this.consoleManager.printError("Аргумент должен быть числом");
            return;
        }

        Flat flat = this.collectionManager.get(id);
        if (flat == null) {
            this.consoleManager.printError("Элемента с таким id нет в коллекции");
            return;
        }

        this.form.update(flat);
        this.consoleManager.print("Квартира с id " + id + " изменена");
    }
}
