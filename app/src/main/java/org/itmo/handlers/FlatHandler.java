package org.itmo.handlers;

import java.util.HashMap;

import org.itmo.exceptions.ValidationException;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Coordinates;
import org.itmo.models.Flat;
import org.itmo.models.Furnish;
import org.itmo.models.Transport;

import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class FlatHandler extends Handler<Flat> {

    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("name", FlatHandler.class.getDeclaredMethod("nameHandler"));
                put("coords", FlatHandler.class.getDeclaredMethod("coordsHandler"));
                put("area", FlatHandler.class.getDeclaredMethod("areaHandler"));
                put("numberOfRooms", FlatHandler.class.getDeclaredMethod("numberOfRoomsHandler"));
                put("height", FlatHandler.class.getDeclaredMethod("heightHandler"));
                put("furnish", FlatHandler.class.getDeclaredMethod("furnishHandler"));
                put("transport", FlatHandler.class.getDeclaredMethod("transportHandler"));
                put("house", FlatHandler.class.getDeclaredMethod("houseHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    public Flat flat;

    public FlatHandler(ConsoleManager consoleManager) {
        super(consoleManager, "name");
        super.handlers = this.handlers;
    }

    @Override
    public Flat create() {
        this.flat = new Flat();
        super.runHandler(this);
        return this.flat;
    }

    public Flat update(Flat flat) {
        this.flat = flat;
        super.runHandler(this);
        return this.flat;
    }

    private void nameHandler() throws ValidationException {
        this.consoleManager.print("Введите имя:");
        String name = this.consoleManager.read();
        this.flat.setName(name);
        super.setStep("coords");
    }

    private void coordsHandler() throws ValidationException {
        this.consoleManager.print("Координаты (x, y):");
        CoordinatesHandler coordinatesForm = new CoordinatesHandler(consoleManager);
        Coordinates coordinates = coordinatesForm.create();
        this.flat.setCoordinates(coordinates);
        super.setStep("area");
    }

    private void areaHandler() throws ValidationException {
        this.consoleManager.print("Введите площадь:");
        String area = this.consoleManager.read();
        this.flat.setArea(area);
        super.setStep("numberOfRooms");
    }

    private void numberOfRoomsHandler() throws ValidationException {
        this.consoleManager.print("Введите количество комнат:");
        String number = this.consoleManager.read();
        this.flat.setNumberOfRooms(number);
        super.setStep("height");
    }

    private void heightHandler() throws ValidationException {
        this.consoleManager.print("Введите высоту:");
        String height = this.consoleManager.read();
        this.flat.setHeight(height);
        super.setStep("furnish");
    }

    private void furnishHandler() throws ValidationException {
        this.consoleManager.print("Введите тип мебели:");
        String furnishType = this.consoleManager.read();
        this.flat.setFurnish(Furnish.valueOf(furnishType));
        super.setStep("transport");
    }

    private void transportHandler() throws ValidationException {
        this.consoleManager.print("Введите тип транспорта:");
        String transport = this.consoleManager.read();
        this.flat.setTransport(Transport.valueOf(transport));
        super.setStep("house");
    }

    private void houseHandler() throws ValidationException {
        HouseHandler houseForm = new HouseHandler(consoleManager);
        this.flat.setHouse(houseForm.create());
        super.clearStep();
    }

}
