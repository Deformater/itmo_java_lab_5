package forms;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.Transport;

public class FlatForm extends Form<Flat> {

    public FlatForm(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public Flat run() {
        Flat flat = new Flat();
        while (true) {
            try {
                this.consoleManager.print("Введите имя:");
                String name = this.consoleManager.read();
                flat.setName(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                CoordinatesForm coordinatesForm = new CoordinatesForm(consoleManager);
                Coordinates coordinates = coordinatesForm.run();
                flat.setCoordinates(coordinates);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите площадь:");
                String name = this.consoleManager.read();
                flat.setArea(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите количество комнат:");
                String name = this.consoleManager.read();
                flat.setNumberOfRooms(name);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите высоту:");
                String height = this.consoleManager.read();
                flat.setHeight(height);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите тип мебели:");
                String name = this.consoleManager.read();
                flat.setFurnish(Furnish.valueOf(name));
                break;
            } catch (IllegalArgumentException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите тип транспорта:");
                String transport = this.consoleManager.read();
                flat.setTransport(Transport.valueOf(transport));
                break;
            } catch (ValidationException | IllegalArgumentException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            HouseForm houseForm = new HouseForm(consoleManager);
            flat.setHouse(houseForm.run());
            break;
        }

        return flat;
    }

}
