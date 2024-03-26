package org.itmo.commands.no_args;

import java.util.TreeSet;

import org.itmo.commands.Command;
import org.itmo.exceptions.ScriptExecutionException;
import org.itmo.exceptions.ValidationException;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Flat;
import org.itmo.models.House;

/**
 * This class represents a command that prints the values of the 'house' field of all elements in descending order.
 */
public class PrintFieldDescendingHouse extends Command {

    /**
     * Constructs a new PrintFieldDescendingHouse command with the specified console manager and collection manager.
     *
     * @param consoleManager   the console manager to use for input/output operations
     * @param collectionManager the collection manager to use for accessing the collection
     */
    public PrintFieldDescendingHouse(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "print_field_descending_house",
                "Выводит значения поля house всех элементов в порядке убывания",
                consoleManager,
                collectionManager);
    }

    /**
     * Executes the PrintFieldDescendingHouse command.
     *
     * @param args the command arguments (not used in this command)
     */
    public void execute(String... args) {
        TreeSet<Flat> filteredSet = this.collectionManager.getCollection().stream()
                .sorted((f1, f2) -> f2.getHouse().compareTo(f1.getHouse()))
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);

        this.consoleManager.print("Значения поля house всех элементов в порядке убывания:");
        filteredSet.forEach(el -> this.consoleManager.print(el.getHouse()));
    }

    /**
     * Executes the PrintFieldDescendingHouse command from a file.
     *
     * @return the House object created from the input values
     * @throws ScriptExecutionException if an error occurs during script execution
     */
    public House execute_from_file() {
        House house = new House();
        try {
            house.setName(this.consoleManager.read());
            house.setYear(this.consoleManager.read());
            house.setNumberOfFlatsOnFloor(this.consoleManager.read());
        } catch (ValidationException e) {
            throw new ScriptExecutionException("Ошибка при удалении квартир ниже заданной в скрипте", e);
        }

        return house;
    }
}
