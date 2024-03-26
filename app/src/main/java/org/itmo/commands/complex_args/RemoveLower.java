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
 * The RemoveLower class represents a command that removes all elements from the collection
 * that are lower than the specified element.
 */
public class RemoveLower extends Command {
    private FlatHandler handler;

    /**
     * Constructs a new RemoveLower command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for input/output
     * @param collectionManager the collection manager to use for managing the collection
     */
    public RemoveLower(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "remove_lower",
                "Удаляет из коллекции все элементы, меньшие, чем заданный",
                consoleManager,
                collectionManager);
        this.handler = new FlatHandler(consoleManager);
    }

    /**
     * Executes the RemoveLower command with the specified arguments.
     *
     * @param args the command arguments
     */
    public void execute(String... args) {
        Flat flat;
        if (super.fromScript) {
            flat = this.execute_from_file();
        } else {
            flat = (Flat) this.handler.create();
        }
        flat.setGenId(collectionManager);
        if (this.collectionManager.removeLower(flat)) {
            this.consoleManager.print("Квартиры удалены");
        } else {
            this.consoleManager.print("Квартир меньше заданной не найдено");
        }
    }

    /**
     * Executes the RemoveLower command from a file.
     *
     * @return the created Flat object
     * @throws ScriptExecutionException if an error occurs during script execution
     */
    public Flat execute_from_file() {
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
            throw new ScriptExecutionException("Ошибка при удалении квартир ниже заданной в скрипте", e);
        }

        return flat;
    }
}
