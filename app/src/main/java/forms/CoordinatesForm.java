package forms;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.checkerframework.checker.units.qual.s;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.Coordinates;

@SuppressWarnings("unused")
public class CoordinatesForm extends Form<Coordinates> {
    Coordinates coordinates;

    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("x", CoordinatesForm.class.getDeclaredMethod("xCoordHandler"));
                put("y", CoordinatesForm.class.getDeclaredMethod("yCoordHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    public CoordinatesForm(ConsoleManager consoleManager) {
        super(consoleManager, "x");
        super.handlers = this.handlers;
    }

    @Override
    public Coordinates create() {
        this.coordinates = new Coordinates();
        super.runHandler(this);
        return coordinates;
    }

    private void xCoordHandler() throws ValidationException {
        this.consoleManager.print("Введите координату x:");
        String x = this.consoleManager.read();
        this.coordinates.setX(x);
        super.setStep("y");
    }

    private void yCoordHandler() throws ValidationException {
        this.consoleManager.print("Введите координату y:");
        String y = this.consoleManager.read();
        this.coordinates.setY(y);
        super.clearStep();
    }

}
