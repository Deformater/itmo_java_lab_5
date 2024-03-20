package org.itmo.models;

import org.itmo.exceptions.ValidationException;

public class House implements Comparable<House> {
    private String name; // Поле не может быть null
    private Integer year; // Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; // Значение поля должно быть больше 0

    public House() {
    }

    public House(String name, Integer year, long numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null || name.equals("")) {
            throw new ValidationException("Name can't be null or empty");
        }
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) throws ValidationException {
        if (year <= 0) {
            throw new ValidationException("Year should be more than 0");
        }
        this.year = year;
    }

    public void setYear(String year) throws ValidationException {
        try {
            Integer yearInt = Integer.parseInt(year);
            this.setYear(yearInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Year should be a number");
        }
    }

    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) throws ValidationException {
        if (numberOfFlatsOnFloor <= 0) {
            throw new ValidationException("Number of flats on floor should be more than 0");
        }
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(String numberOfFlatsOnFloor) throws ValidationException {
        try {
            long numberOfFlatsOnFloorLong = Long.parseLong(numberOfFlatsOnFloor);
            this.setNumberOfFlatsOnFloor(numberOfFlatsOnFloorLong);
        } catch (NumberFormatException e) {
            throw new ValidationException("Number of flats on floor should be a number");
        }
    }

    @Override
    public int compareTo(House house) {
        return this.getYear() - house.getYear();
    }

    @Override
    public String toString() {
        return String.format("\tИмя: %s\n\tГод постройки: %d\n\tКол-во квартир на этаже: %d", this.getName(),
                this.getYear(),
                this.getNumberOfFlatsOnFloor());
    }
}
