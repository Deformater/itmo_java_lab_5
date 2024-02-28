package forms;

import exceptions.ValidationException;
import manager.ConsoleManager;
import models.Coordinates;

public class CoordinatesForm extends Form<Coordinates> {

    public CoordinatesForm(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public Coordinates run() {
        Coordinates coordinates = new Coordinates();
        while (true) {
            try {
                this.consoleManager.print("Введите координату x:");
                String x = this.consoleManager.read();
                coordinates.setX(x);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }

        while (true) {
            try {
                this.consoleManager.print("Введите координату y:");
                String y = this.consoleManager.read();
                coordinates.setY(y);
                break;
            } catch (ValidationException e) {
                this.consoleManager.printError(e.getMessage());
                continue;
            }
        }
        return coordinates;
    }

}
