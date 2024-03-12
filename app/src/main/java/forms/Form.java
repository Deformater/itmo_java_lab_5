package forms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import manager.ConsoleManager;

public abstract class Form<T> {
    protected ConsoleManager consoleManager;
    protected String step;
    protected Map<String, Method> handlers;

    public Form(ConsoleManager consoleManager, String step) {
        this.consoleManager = consoleManager;
        this.step = step;
    }

    public abstract T create();

    protected void runHandler(@SuppressWarnings("rawtypes") Form form) {
        while (true) {
            Method handler = this.currentStepHandler();
            if (handler == null) {
                break;
            }
            try {
                handler.setAccessible(true);
                handler.invoke(form);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                this.consoleManager.printError(e.getCause().getMessage());
            }
        }
    }

    protected Method currentStepHandler() {
        if (this.step == null) {
            return null;
        }
        return this.handlers.get(this.step);
    }

    protected void setStep(String step) {
        this.step = step;
    }

    protected void clearStep() {
        this.step = null;
    }
}
