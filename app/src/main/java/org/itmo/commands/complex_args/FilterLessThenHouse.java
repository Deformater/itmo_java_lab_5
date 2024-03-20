package org.itmo.commands.complex_args;

import java.util.TreeSet;

import org.itmo.commands.Command;
import org.itmo.exceptions.ScriptExecutionException;
import org.itmo.exceptions.ValidationException;
import org.itmo.handlers.HouseHandler;
import org.itmo.manager.CollectionManager;
import org.itmo.manager.ConsoleManager;
import org.itmo.models.Flat;
import org.itmo.models.House;

public class FilterLessThenHouse extends Command {
    private HouseHandler handler;

    public FilterLessThenHouse(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "filter_less_then_house",
                "Выводит элементы коллекции, значение поля house которых меньше заданного",
                consoleManager,
                collectionManager);
        this.handler = new HouseHandler(consoleManager);
    }

    public void execute(String... args) {
        House house;
        if (super.fromScript) {
            house = this.execute_from_file();
        } else {
            house = this.handler.create();
        }

        TreeSet<Flat> filteredSet = this.collectionManager.filterLessThenHouse(house);
        if (filteredSet.isEmpty()) {
            this.consoleManager.print("Квартир c домом меньше заданного не найдено");
            return;
        }
        this.consoleManager.print("Элементы коллекции меньше дома:");
        filteredSet.forEach(el -> this.consoleManager.print(el));
    }

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
