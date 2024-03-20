package org.itmo.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.itmo.manager.ConsoleManager;

public abstract class Handler<T> {
    protected ConsoleManager consoleManager;
    private String step;
    private String startStep;
    protected Map<String, Method> handlers;

    public Handler(ConsoleManager consoleManager, String startStep) {
        this.consoleManager = consoleManager;
        this.startStep = startStep;
        this.step = startStep;
    }

    public abstract T create();

    protected void runHandler(@SuppressWarnings("rawtypes") Handler form) {
        this.step = this.startStep;
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
