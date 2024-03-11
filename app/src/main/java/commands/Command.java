package commands;

import forms.Form;
import manager.CollectionManager;
import manager.ConsoleManager;

@SuppressWarnings("rawtypes")
public abstract class Command {
    protected String name;
    protected String helpText;
    protected Form form = null;
    protected String arg = null;
    protected ConsoleManager consoleManager;
    protected CollectionManager collectionManager;

    public Command(String name, String helpText, Form form, ConsoleManager consoleManager,
            CollectionManager collectionManager) {
        this(name, helpText, consoleManager, collectionManager);
        this.form = form;
    }

    public Command(String name, String helpText, String arg, Form form, ConsoleManager consoleManager,
            CollectionManager collectionManager) {
        this(name, helpText, consoleManager, collectionManager);
        this.form = form;
        this.arg = arg;
    }

    public Command(String name, String helpText, String arg, ConsoleManager consoleManager,
            CollectionManager collectionManager) {
        this(name, helpText, consoleManager, collectionManager);
        this.arg = arg;
    }

    public Command(String name, String helpText, ConsoleManager consoleManager, CollectionManager collectionManager) {
        this.name = name;
        this.helpText = helpText;
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
