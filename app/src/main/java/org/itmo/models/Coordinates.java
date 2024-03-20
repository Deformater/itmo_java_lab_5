package org.itmo.models;

import org.itmo.exceptions.ValidationException;

public class Coordinates {
    private Integer x; // Максимальное значение поля: 591, Поле не может быть null
    private long y;

    public Coordinates() {
    }

    public Coordinates(Integer xInteger, long yLong) throws ValidationException {
        this.setX(xInteger);
        this.setY(yLong);
    }

    public void setX(Integer xInteger) throws ValidationException {
        if (xInteger == null) {
            throw new ValidationException("X can't be null");
        }
        if (xInteger > 591 || xInteger < -591) {
            throw new ValidationException("X should be less than 591");
        }
        this.x = xInteger;
    }

    public void setX(String xString) throws ValidationException {
        try {
            this.setX(Integer.parseInt(xString));
        } catch (NumberFormatException e) {
            throw new ValidationException("X should be a number");
        }
    }

    public void setY(long yLong) throws ValidationException {
        this.y = yLong;
    }

    public void setY(String yString) throws ValidationException {
        try {
            Integer y = Integer.parseInt(yString);
            setY(y);
        } catch (NumberFormatException e) {
            throw new ValidationException("Y should be a number");
        }
    }

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("\tx: %d\n\ty: %d", this.getX(), this.getY());
    }

}
