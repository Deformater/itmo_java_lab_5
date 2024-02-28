package forms;

import manager.ConsoleManager;

public abstract class Form<T> {
    protected ConsoleManager consoleManager;

    public Form(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    public abstract T run();
}
