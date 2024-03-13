package commands.no_args_commands;

import java.util.TreeSet;

import commands.Command;
import exceptions.ScriptExecutionException;
import exceptions.ValidationException;
import manager.CollectionManager;
import manager.ConsoleManager;
import models.Flat;
import models.House;

public class PrintFieldDescendingHouse extends Command {

    public PrintFieldDescendingHouse(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super(
                "print_field_descending_house",
                "Выводит значения поля house всех элементов в порядке убывания",
                consoleManager,
                collectionManager);
    }

    public void execute(String... args) {
        TreeSet<Flat> filteredSet = this.collectionManager.getCollection().stream()
                .sorted((f1, f2) -> f2.getHouse().compareTo(f1.getHouse()))
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);

        this.consoleManager.print("Значения поля house всех элементов в порядке убывания:");
        filteredSet.forEach(el -> this.consoleManager.print(el.getHouse()));
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