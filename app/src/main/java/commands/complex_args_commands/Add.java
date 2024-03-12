package commands.complex_args_commands;

import commands.Command;
import exceptions.ValidationException;
import handlers.FlatHandler;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.House;
import models.Transport;

import exceptions.ScriptExecutionException;

public class Add extends Command {
    private FlatHandler handler;

    public Add(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("add", "Добавляет квартиру в колекцию, запускает форму ввода Квартиры", consoleManager,
                collectionManager);
        this.handler = new FlatHandler(consoleManager);
    }

    public void execute(String... args) {
        if (super.fromScript) {
            this.execute_from_file();
            return;
        }
        Flat flat = (Flat) this.handler.create();
        flat.setGenId(collectionManager);
        this.collectionManager.add(flat);
        this.consoleManager.print("Квартира добавлена в коллекцию");
    }

    public void execute_from_file() {
        Flat flat = new Flat();
        try {
            flat.setName(this.consoleManager.read());
            Coordinates coordinates = new Coordinates();
            coordinates.setX(this.consoleManager.read());
            coordinates.setY(this.consoleManager.read());
            flat.setCoordinates(coordinates);
            flat.setArea(this.consoleManager.read());
            flat.setNumberOfRooms(this.consoleManager.read());
            flat.setHeight(this.consoleManager.read());
            flat.setFurnish(Furnish.valueOf(this.consoleManager.read()));
            flat.setTransport(Transport.valueOf(this.consoleManager.read()));
            House house = new House();
            house.setName(this.consoleManager.read());
            house.setYear(this.consoleManager.read());
            house.setNumberOfFlatsOnFloor(this.consoleManager.read());
            flat.setHouse(house);
        } catch (ValidationException e) {
            throw new ScriptExecutionException("Ошибка при создании квартиры в скрипте", e);
        }

        flat.setGenId(collectionManager);
        this.collectionManager.add(flat);
        this.consoleManager.print("Квартира добавлена в коллекцию");
    }
}
