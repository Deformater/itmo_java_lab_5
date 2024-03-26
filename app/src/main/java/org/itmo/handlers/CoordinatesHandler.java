package org.itmo.handlers;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.itmo.exceptions.ValidationException;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Coordinates;

@SuppressWarnings("unused")
public class CoordinatesHandler extends Handler<Coordinates> {
    Coordinates coordinates;

    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("x", CoordinatesHandler.class.getDeclaredMethod("xCoordHandler"));
                put("y", CoordinatesHandler.class.getDeclaredMethod("yCoordHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Constructs a new CoordinatesHandler object.
     *
     * @param consoleManager the console manager to use for input/output operations
     */
    public CoordinatesHandler(ConsoleManager consoleManager) {
        super(consoleManager, "x");
        super.handlers = this.handlers;
    }

    /**
     * Creates a new Coordinates object by collecting user input for x and y
     * coordinates.
     *
     * @return the created Coordinates object
     */
    @Override
    public Coordinates create() {
        this.coordinates = new Coordinates();
        super.runHandler(this);
        return coordinates;
    }

    /**
     * Handles the input for the x coordinate.
     *
     * @throws ValidationException if the input is invalid
     */
    private void xCoordHandler() throws ValidationException {
        this.consoleManager.print("Введите координату x:");
        String x = this.consoleManager.read();
        this.coordinates.setX(x);
        super.setStep("y");
    }

    /**
     * Handles the input for the y coordinate.
     *
     * @throws ValidationException if the input is invalid
     */
    private void yCoordHandler() throws ValidationException {
        this.consoleManager.print("Введите координату y:");
        String y = this.consoleManager.read();
        this.coordinates.setY(y);
        super.clearStep();
    }
}
