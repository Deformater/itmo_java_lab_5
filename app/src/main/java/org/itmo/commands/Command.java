package org.itmo.commands;

import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;

/**
 * The abstract base class for all commands in the application.
 */
public abstract class Command {
    protected String name;
    protected String helpText;
    protected ConsoleManager consoleManager;
    protected CollectionManager collectionManager;
    protected boolean fromScript = false;

    /**
     * Constructs a new Command object with the specified name, help text, console manager, and collection manager.
     *
     * @param name              the name of the command
     * @param helpText          the help text for the command
     * @param consoleManager    the console manager for handling user input/output
     * @param collectionManager the collection manager for managing the collection
     */
    public Command(String name, String helpText, ConsoleManager consoleManager, CollectionManager collectionManager) {
        this.name = name;
        this.helpText = helpText;
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command with the specified arguments.
     *
     * @param args the arguments for the command
     */
    public abstract void execute(String... args);

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Sets whether the command is executed from a script.
     *
     * @param fromScript true if the command is executed from a script, false otherwise
     */
    public void setFromScript(boolean fromScript) {
        this.fromScript = fromScript;
    }

    /**
     * Returns the help text for the command.
     *
     * @return the help text for the command
     */
    public String getHelpText() {
        return helpText;
    }

    /**
     * Returns a string representation of the command.
     *
     * @return a string representation of the command
     */
    @Override
    public String toString() {
        return helpText;
    }
}
