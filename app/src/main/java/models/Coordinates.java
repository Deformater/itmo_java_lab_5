package models;

import exceptions.ValidationException;

public class Coordinates {
    private double x; // Максимальное значение поля: 591
    private Integer y; // Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(double x, Integer y) throws ValidationException{
        setX(x);
        setY(y);
    }

    public void setX(double x2) throws ValidationException {
        if (x2 > 591 || x2 < -591) {
            throw new ValidationException("X should be less than 591");
        }
        this.x = x2;
    }

    public void setX(String xString) throws ValidationException {
        try {
            double x = Double.parseDouble(xString);
            setX(x);
        } catch (NumberFormatException e) {
            throw new ValidationException("X should be a number");
        }
    }

    public void setY(Integer y) throws ValidationException {
        if (y == null) {
            throw new ValidationException("Y can't be null");
        }
        this.y = y;
    }

    public void setY(String yString) throws ValidationException {
        try {
            Integer y = Integer.parseInt(yString);
            setY(y);
        } catch (NumberFormatException e) {
            throw new ValidationException("Y should be a number");
        }
    }

}