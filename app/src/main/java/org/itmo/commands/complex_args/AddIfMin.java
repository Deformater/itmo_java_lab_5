package org.itmo.commands.complex_args;

import org.itmo.commands.Command;
import org.itmo.exceptions.ScriptExecutionException;
import org.itmo.exceptions.ValidationException;
import org.itmo.handlers.FlatHandler;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Coordinates;
import org.itmo.models.Flat;
import org.itmo.models.Furnish;
import org.itmo.models.House;
import org.itmo.models.Transport;

public class AddIfMin extends Command {
    private FlatHandler handler;

    public AddIfMin(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "add_if_min",
                "Добавляет квартиру в колекцию, если она минимальна",
                consoleManager,
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
        if (flat.compareTo(collectionManager.getMin()) >= 0) {
            this.consoleManager.print("Квартира не минимальна");
            return;
        }
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

        if (flat.compareTo(collectionManager.getMin()) >= 0) {
            this.consoleManager.print("Квартира не минимальная");
            return;
        }
        flat.setGenId(collectionManager);
        this.collectionManager.add(flat);
        this.consoleManager.print("Квартира добавлена в коллекцию");
    }
}
