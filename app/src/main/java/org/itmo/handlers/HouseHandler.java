package org.itmo.handlers;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.itmo.exceptions.ValidationException;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.House;

@SuppressWarnings("unused")
public class HouseHandler extends Handler<House> {
    House house;
    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("name", HouseHandler.class.getDeclaredMethod("nameHandler"));
                put("year", HouseHandler.class.getDeclaredMethod("yearHandler"));
                put("numberOfFlatsOnFloor", HouseHandler.class.getDeclaredMethod("numberOfFlatsOnFloorHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Constructs a new HouseHandler object.
     *
     * @param consoleManager the console manager to use for input/output
     */
    public HouseHandler(ConsoleManager consoleManager) {
        super(consoleManager, "name");
        super.handlers = this.handlers;
    }

    /**
     * Creates a new House object by running the handler methods.
     *
     * @return the created House object
     */
    @Override
    public House create() {
        this.house = new House();
        super.runHandler(this);
        return house;
    }

    /**
     * Handles the input for the name of the house.
     *
     * @throws ValidationException if the input is invalid
     */
    private void nameHandler() throws ValidationException {
        this.consoleManager.print("Введите имя дома:");
        String name = this.consoleManager.read();
        this.house.setName(name);
        super.setStep("year");
    }

    /**
     * Handles the input for the year of construction of the house.
     *
     * @throws ValidationException if the input is invalid
     */
    private void yearHandler() throws ValidationException {
        this.consoleManager.print("Введите год постройки:");
        String year = this.consoleManager.read();
        this.house.setYear(year);
        super.setStep("numberOfFlatsOnFloor");
    }

    /**
     * Handles the input for the number of flats on each floor of the house.
     *
     * @throws ValidationException if the input is invalid
     */
    private void numberOfFlatsOnFloorHandler() throws ValidationException {
        this.consoleManager.print("Введите количество квартир на этаже:");
        String number = this.consoleManager.read();
        this.house.setNumberOfFlatsOnFloor(number);
        super.clearStep();
    }
}
