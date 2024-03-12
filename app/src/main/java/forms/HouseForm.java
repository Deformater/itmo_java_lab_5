package forms;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.House;

import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings("unused")
public class HouseForm extends Form<House> {
    House house;
    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("name", HouseForm.class.getDeclaredMethod("nameHandler"));
                put("year", HouseForm.class.getDeclaredMethod("yearHandler"));
                put("numberOfFlatsOnFloor", HouseForm.class.getDeclaredMethod("numberOfFlatsOnFloorHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    public HouseForm(ConsoleManager consoleManager) {
        super(consoleManager, "name");
        super.handlers = this.handlers;
    }

    @Override
    public House create() {
        this.house = new House();
        super.runHandler(this);
        return house;
    }

    private void nameHandler() throws ValidationException {
        this.consoleManager.print("Введите имя дома:");
        String name = this.consoleManager.read();
        this.house.setName(name);
        super.setStep("year");
    }

    private void yearHandler() throws ValidationException {
        this.consoleManager.print("Введите год постройки:");
        String year = this.consoleManager.read();
        this.house.setYear(year);
        super.setStep("numberOfFlatsOnFloor");
    }

    private void numberOfFlatsOnFloorHandler() throws ValidationException {
        this.consoleManager.print("Введите количество квартир на этаже:");
        String number = this.consoleManager.read();
        this.house.setNumberOfFlatsOnFloor(number);
        super.clearStep();
    }

}
