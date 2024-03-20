package org.itmo.commands;

import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

public abstract class Command {
    protected String name;
    protected String helpText;
    protected ConsoleManager consoleManager;
    protected CollectionManager collectionManager;
    protected boolean fromScript = false;

    public Command(String name, String helpText, ConsoleManager consoleManager, CollectionManager collectionManager) {
        this.name = name;
        this.helpText = helpText;
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
    }

    public abstract void execute(String... args);

    public String getName() {
        return name;
    }

    public void setFromScript(boolean fromScript) {
        this.fromScript = fromScript;
    }

    public String getHelpText() {
        return helpText;
    }

    @Override
    public String toString() {
        return helpText;
    }
}