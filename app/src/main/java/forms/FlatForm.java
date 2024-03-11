package forms;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.Transport;

import java.util.HashMap;

import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class FlatForm extends Form<Flat> {

    HashMap<String, Method> handlers = new HashMap<>() {
        {
            try {
                put("name", FlatForm.class.getDeclaredMethod("nameHandler"));
                put("coords", FlatForm.class.getDeclaredMethod("coordsHandler"));
                put("area", FlatForm.class.getDeclaredMethod("areaHandler"));
                put("numberOfRooms", FlatForm.class.getDeclaredMethod("numberOfRoomsHandler"));
                put("height", FlatForm.class.getDeclaredMethod("heightHandler"));
                put("furnish", FlatForm.class.getDeclaredMethod("furnishHandler"));
                put("transport", FlatForm.class.getDeclaredMethod("transportHandler"));
                put("house", FlatForm.class.getDeclaredMethod("houseHandler"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    Flat flat;

    public FlatForm(ConsoleManager consoleManager) {
        super(consoleManager, "name");
        super.handlers = this.handlers;
    }

    @Override
    public Flat run() {
        this.flat = new Flat();
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
        CoordinatesForm coordinatesForm = new CoordinatesForm(consoleManager);
        Coordinates coordinates = coordinatesForm.run();
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
        HouseForm houseForm = new HouseForm(consoleManager);
        this.flat.setHouse(houseForm.run());
        super.clearStep();
    }

}
