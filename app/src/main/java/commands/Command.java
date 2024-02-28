package commands;

import forms.Form;
import manager.CollectionManager;
import manager.ConsoleManager;

public abstract class Command {
    protected String name;
    protected String helpText;
    protected Form form;
    protected ConsoleManager consoleManager;
    protected CollectionManager collectionManager;

    public Command(String name, String helpText, Form form, ConsoleManager consoleManager, CollectionManager collectionManager) {
        this.name = name;
        this.helpText = helpText;
        this.form = form;
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
    }

    public abstract void execute();

    public String getName() {
        return name;
    }

    public String getHelpText() {
        return helpText;
    }

    @Override
    public String toString() {
        return helpText;
    }
}
