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

public class Update extends Command {
    private FlatHandler handler;

    public Update(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("update",
                "Изменяет квартиру в колекции, запускает форму изменения данных Квартиры\n\tпринимает аргумент типа Integer id",
                consoleManager, collectionManager);
        this.handler = new FlatHandler(consoleManager);
    }

    public void execute(String... args) {
        if (args.length != 1) {
            this.consoleManager.printError("Команда принимает один аргумент");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            this.consoleManager.printError("Аргумент должен быть числом");
            return;
        }

        Flat flat = this.collectionManager.get(id);
        if (flat == null) {
            this.consoleManager.printError("Элемента с таким id нет в коллекции");
            return;
        }

        if (super.fromScript) {
            this.execute_from_file(flat);
            return;
        }

        this.handler.update(flat);
        this.consoleManager.print("Квартира с id " + id + " изменена");
    }

    public void execute_from_file(Flat flat) {
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
            throw new ScriptExecutionException("Ошибка при обновлении квартиры в скрипте", e);
        }

        this.consoleManager.print("Квартира с id " + flat.getId() + " изменена");
    }
}
