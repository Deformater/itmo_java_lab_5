package org.itmo.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.itmo.manager.ConsoleManager;

/**
 * The abstract base class for all handlers.
 *
 * @param <T> the type of object that the handler creates
 */
public abstract class Handler<T> {
    protected ConsoleManager consoleManager;
    private String step;
    private String startStep;
    protected Map<String, Method> handlers;

    /**
     * Constructs a new Handler object.
     *
     * @param consoleManager the console manager to use for input/output
     * @param startStep the initial step of the handler
     */
    public Handler(ConsoleManager consoleManager, String startStep) {
        this.consoleManager = consoleManager;
        this.startStep = startStep;
        this.step = startStep;
    }

    /**
     * Creates a new object of type T.
     *
     * @return the created object
     */
    public abstract T create();

    /**
     * Runs the handler by executing the appropriate methods based on the current step.
     *
     * @param form the form to handle
     */
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

    /**
     * Returns the method associated with the current step.
     *
     * @return the method associated with the current step, or null if the step is null
     */
    protected Method currentStepHandler() {
        if (this.step == null) {
            return null;
        }
        return this.handlers.get(this.step);
    }

    /**
     * Sets the current step of the handler.
     *
     * @param step the step to set
     */
    protected void setStep(String step) {
        this.step = step;
    }

    /**
     * Clears the current step of the handler.
     */
    protected void clearStep() {
        this.step = null;
    }
}
