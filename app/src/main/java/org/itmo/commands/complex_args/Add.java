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

/**
 * Represents a command that adds a flat to the collection.
 * This command prompts the user to enter the details of the flat and adds it to the collection.
 */
public class Add extends Command {
    private FlatHandler handler;

    /**
     * Constructs an Add command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for input/output
     * @param collectionManager the collection manager to add the flat to
     */
    public Add(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("add", "Добавляет квартиру в колекцию, запускает форму ввода Квартиры", consoleManager,
                collectionManager);
        this.handler = new FlatHandler(consoleManager);
    }

    /**
     * Executes the Add command.
     * If the command is executed from a script, it calls the execute_from_file method.
     * Otherwise, it prompts the user to enter the details of the flat and adds it to the collection.
     *
     * @param args the command arguments (not used)
     */
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

    /**
     * Executes the Add command from a file.
     * Reads the details of the flat from the console and adds it to the collection.
     * If any validation errors occur, a ScriptExecutionException is thrown.
     */
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
