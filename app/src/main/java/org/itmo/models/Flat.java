package org.itmo.models;

import java.time.ZonedDateTime;

import org.itmo.exceptions.ValidationException;
import org.itmo.manager.CollectionManager;

public class Flat implements Comparable<Flat> {
    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого
                        // поля должно быть уникальным, Значение этого поля должно генерироваться
                        // автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private ZonedDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                        // автоматически
    private Long area; // Максимальное значение поля: 570, Значение поля должно быть больше 0
    private int numberOfRooms; // Значение поля должно быть больше 0
    private Integer height; // Поле не может быть null, Значение поля должно быть больше 0

    private Furnish furnish; // Поле может быть null
    private Transport transport; // Поле не может быть null
    private House house; // Поле может быть null

    public Flat() {
        ZonedDateTime createdAt = ZonedDateTime.now();
        this.setCreationDate(createdAt);
    }

    public Flat(CollectionManager collectionManager, Integer id, String name, Coordinates coordinates, Long area,
            int numberOfRooms, Integer height, Furnish furnish, Transport transport, House house)
            throws ValidationException {
        this();
        this.setGenId(collectionManager);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setArea(area);
        this.setNumberOfRooms(numberOfRooms);
        this.setHeight(height);
        this.setFurnish(furnish);
        this.setTransport(transport);
        this.setHouse(house);
    }

    @Override
    public int compareTo(Flat other) {
        return this.area.compareTo(other.area);
    }

    public static Integer generateId(CollectionManager collectionManager) {
        Integer id = 1;
        for (Flat flat : collectionManager.getCollection()) {
            if (flat.getId() >= id) {
                id = flat.getId() + 1;
            }
        }
        return id;
    }

    public void setGenId(CollectionManager collectionManager) {
        while (true) {
            try {
                this.setId(Flat.generateId(collectionManager), collectionManager);
                break;
            } catch (ValidationException e) {
                continue;
            }
        }
    }

    public void setId(Integer id, CollectionManager collectionManager) throws ValidationException {
        if (id <= 0 || id == null) {
            throw new ValidationException("Id should be more than 0");
        }

        if (collectionManager.getCollection().stream().anyMatch(flat -> flat.getId() == id)) {
            throw new ValidationException("Id should be unique");
        }

        this.id = id;
    }

    public int getId() {
        return id;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null) {
            throw new ValidationException("Coordinates can't be null");
        }
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(ZonedDateTime creationDate) {
        // if (creationDate == null) {
        // throw new ValidationException("Creation date can't be null");
        // }
        this.creationDate = creationDate;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) throws ValidationException {
        if (area <= 0 || area > 570 || area == null) {
            throw new ValidationException("Area should be more than 0 and less than 570");
        }
        this.area = area;
    }

    public void setArea(String areaString) throws ValidationException {
        try {
            Long ariaLong = Long.parseLong(areaString);
            setArea(ariaLong);
        } catch (NumberFormatException e) {
            throw new ValidationException("Area should be a number");
        }
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) throws ValidationException {
        if (numberOfRooms <= 0) {
            throw new ValidationException("Number of rooms should be more than 0");
        }
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRoomsString) throws ValidationException {
        try {
            int numberOfRoomsInt = Integer.parseInt(numberOfRoomsString);
            this.setNumberOfRooms(numberOfRoomsInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Number of rooms should be a number");
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) throws ValidationException {
        if (height <= 0) {
            throw new ValidationException("Height should be more than 0");
        }
        this.height = height;
    }

    public void setHeight(String height) throws ValidationException, NullPointerException {
        try {
            Integer heightInt = Integer.parseInt(height);
            this.setHeight(heightInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Height should be a number");
        }
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) throws ValidationException {
        if (transport == null) {
            throw new ValidationException("Transport can't be null");
        }
        this.transport = transport;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s\nИмя квартиры: %s\nКоординаты:\n%s\nПлощадь: %d\nКоличество комнат: %d\nВысота: %d\nТип мебели: %s\nТип транспорта: %s\nДом:\n%s",
                this.getId(), this.getName(), this.getCoordinates(), this.getArea(), this.getNumberOfRooms(),
                this.getHeight(),
                this.getFurnish(),
                this.getTransport(), this.getHouse());
    }
}
