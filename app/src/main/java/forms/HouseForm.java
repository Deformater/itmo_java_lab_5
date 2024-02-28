package forms;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.House;

public class HouseForm extends Form<House> {

    public HouseForm(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public House run() {
        House house = new House();
        while (true) {
            try {
                this.consoleManager.print("Введите имя:");
                String name = this.consoleManager.read();
                house.setName(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите год постройки:");
                String name = this.consoleManager.read();
                house.setYear(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите количество этажей:");
                String name = this.consoleManager.read();
                house.setNumberOfFlatsOnFloor(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }
        return house;
    }

}
