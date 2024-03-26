package org.itmo.models;

import org.itmo.exceptions.ValidationException;

/**
 * Represents a house with its name, year of construction, and number of flats on each floor.
 */
public class House implements Comparable<House> {
    private String name; // Поле не может быть null
    private Integer year; // Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; // Значение поля должно быть больше 0

    /**
     * Constructs an empty House object.
     */
    public House() {
    }

    /**
     * Constructs a House object with the specified name, year, and number of flats on each floor.
     *
     * @param name                 the name of the house
     * @param year                 the year of construction
     * @param numberOfFlatsOnFloor the number of flats on each floor
     */
    public House(String name, Integer year, long numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    /**
     * Gets the name of the house.
     *
     * @return the name of the house
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the house.
     *
     * @param name the name of the house
     * @throws ValidationException if the name is null or empty
     */
    public void setName(String name) throws ValidationException {
        if (name == null || name.equals("")) {
            throw new ValidationException("Name can't be null or empty");
        }
        this.name = name;
    }

    /**
     * Gets the year of construction.
     *
     * @return the year of construction
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets the year of construction.
     *
     * @param year the year of construction
     * @throws ValidationException if the year is less than or equal to 0
     */
    public void setYear(Integer year) throws ValidationException {
        if (year <= 0) {
            throw new ValidationException("Year should be more than 0");
        }
        this.year = year;
    }

    /**
     * Sets the year of construction from a string representation.
     *
     * @param year the year of construction as a string
     * @throws ValidationException if the year is not a valid number
     */
    public void setYear(String year) throws ValidationException {
        try {
            Integer yearInt = Integer.parseInt(year);
            this.setYear(yearInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Year should be a number");
        }
    }

    /**
     * Gets the number of flats on each floor.
     *
     * @return the number of flats on each floor
     */
    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    /**
     * Sets the number of flats on each floor.
     *
     * @param numberOfFlatsOnFloor the number of flats on each floor
     * @throws ValidationException if the number of flats on each floor is less than or equal to 0
     */
    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) throws ValidationException {
        if (numberOfFlatsOnFloor <= 0) {
            throw new ValidationException("Number of flats on floor should be more than 0");
        }
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    /**
     * Sets the number of flats on each floor from a string representation.
     *
     * @param numberOfFlatsOnFloor the number of flats on each floor as a string
     * @throws ValidationException if the number of flats on each floor is not a valid number
     */
    public void setNumberOfFlatsOnFloor(String numberOfFlatsOnFloor) throws ValidationException {
        try {
            long numberOfFlatsOnFloorLong = Long.parseLong(numberOfFlatsOnFloor);
            this.setNumberOfFlatsOnFloor(numberOfFlatsOnFloorLong);
        } catch (NumberFormatException e) {
            throw new ValidationException("Number of flats on floor should be a number");
        }
    }

    /**
     * Compares this House object with another House object based on their years of construction.
     *
     * @param house the House object to compare with
     * @return a negative integer if this House is older, zero if they are of the same year,
     *         or a positive integer if this House is newer
     */
    @Override
    public int compareTo(House house) {
        return this.getYear() - house.getYear();
    }

    /**
     * Returns a string representation of the House object.
     *
     * @return a string representation of the House object
     */
    @Override
    public String toString() {
        return String.format("\tИмя: %s\n\tГод постройки: %d\n\tКол-во квартир на этаже: %d", this.getName(),
                this.getYear(),
                this.getNumberOfFlatsOnFloor());
    }
}
