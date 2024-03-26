package org.itmo.models;

import org.itmo.exceptions.ValidationException;

/**
 * Represents the coordinates of a point in a 2D space.
 */
public class Coordinates {
    private Integer x; // Максимальное значение поля: 591, Поле не может быть null
    private long y;

    /**
     * Constructs a new instance of the Coordinates class with default values.
     */
    public Coordinates() {
    }

    /**
     * Constructs a new instance of the Coordinates class with the specified x and y values.
     *
     * @param xInteger the x-coordinate value
     * @param yLong    the y-coordinate value
     * @throws ValidationException if the x or y value is invalid
     */
    public Coordinates(Integer xInteger, long yLong) throws ValidationException {
        this.setX(xInteger);
        this.setY(yLong);
    }

    /**
     * Sets the x-coordinate value.
     *
     * @param xInteger the x-coordinate value to set
     * @throws ValidationException if the x value is null or exceeds the maximum allowed value
     */
    public void setX(Integer xInteger) throws ValidationException {
        if (xInteger == null) {
            throw new ValidationException("X can't be null");
        }
        if (xInteger > 591 || xInteger < -591) {
            throw new ValidationException("X should be less than 591");
        }
        this.x = xInteger;
    }

    /**
     * Sets the x-coordinate value from a string representation.
     *
     * @param xString the string representation of the x-coordinate value
     * @throws ValidationException if the x value is not a valid number
     */
    public void setX(String xString) throws ValidationException {
        try {
            this.setX(Integer.parseInt(xString));
        } catch (NumberFormatException e) {
            throw new ValidationException("X should be a number");
        }
    }

    /**
     * Sets the y-coordinate value.
     *
     * @param yLong the y-coordinate value to set
     */
    public void setY(long yLong) {
        this.y = yLong;
    }

    /**
     * Sets the y-coordinate value from a string representation.
     *
     * @param yString the string representation of the y-coordinate value
     * @throws ValidationException if the y value is not a valid number
     */
    public void setY(String yString) throws ValidationException {
        try {
            Integer y = Integer.parseInt(yString);
            setY(y);
        } catch (NumberFormatException e) {
            throw new ValidationException("Y should be a number");
        }
    }

    /**
     * Gets the x-coordinate value.
     *
     * @return the x-coordinate value
     */
    public Integer getX() {
        return x;
    }

    /**
     * Gets the y-coordinate value.
     *
     * @return the y-coordinate value
     */
    public long getY() {
        return y;
    }

    /**
     * Returns a string representation of the Coordinates object.
     *
     * @return a string representation of the Coordinates object
     */
    @Override
    public String toString() {
        return String.format("\tx: %d\n\ty: %d", this.getX(), this.getY());
    }

}
