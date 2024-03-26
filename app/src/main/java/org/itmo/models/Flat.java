package org.itmo.models;

import java.time.ZonedDateTime;

import org.itmo.exceptions.ValidationException;
import org.itmo.manager.CollectionManager;

/**
 * Represents a flat.
 */
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

    /**
     * Default constructor for Flat.
     * Sets the creation date to the current date and time.
     */
    public Flat() {
        ZonedDateTime createdAt = ZonedDateTime.now();
        this.setCreationDate(createdAt);
    }

    /**
     * Constructor for Flat with parameters.
     * @param collectionManager The collection manager.
     * @param id The ID of the flat.
     * @param name The name of the flat.
     * @param coordinates The coordinates of the flat.
     * @param area The area of the flat.
     * @param numberOfRooms The number of rooms in the flat.
     * @param height The height of the flat.
     * @param furnish The furnish of the flat.
     * @param transport The transport of the flat.
     * @param house The house of the flat.
     * @throws ValidationException If any of the parameters fail validation.
     */
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

    /**
     * Compares this flat to another flat based on their areas.
     * @param other The other flat to compare to.
     * @return A negative integer, zero, or a positive integer as this flat is less than, equal to, or greater than the other flat.
     */
    @Override
    public int compareTo(Flat other) {
        return this.area.compareTo(other.area);
    }

    /**
     * Generates a unique ID for the flat.
     * @param collectionManager The collection manager.
     * @return The generated ID.
     */
    public static Integer generateId(CollectionManager collectionManager) {
        Integer id = 1;
        for (Flat flat : collectionManager.getCollection()) {
            if (flat.getId() >= id) {
                id = flat.getId() + 1;
            }
        }
        return id;
    }

    /**
     * Sets a generated ID for the flat.
     * @param collectionManager The collection manager.
     */
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

    /**
     * Sets the ID of the flat.
     * @param id The ID to set.
     * @param collectionManager The collection manager.
     * @throws ValidationException If the ID fails validation.
     */
    public void setId(Integer id, CollectionManager collectionManager) throws ValidationException {
        if (id <= 0 || id == null) {
            throw new ValidationException("Id should be more than 0");
        }

        if (collectionManager.getCollection().stream().anyMatch(flat -> flat.getId() == id)) {
            throw new ValidationException("Id should be unique");
        }

        this.id = id;
    }

    /**
     * Gets the ID of the flat.
     * @return The ID of the flat.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the flat.
     * @return The name of the flat.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the flat.
     * @param name The name to set.
     * @throws ValidationException If the name fails validation.
     */
    public void setName(String name) throws ValidationException {
        if (name == null || name.equals("")) {
            throw new ValidationException("Name can't be null or empty");
        }
        this.name = name;
    }

    /**
     * Gets the coordinates of the flat.
     * @return The coordinates of the flat.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the coordinates of the flat.
     * @param coordinates The coordinates to set.
     * @throws ValidationException If the coordinates fail validation.
     */
    public void setCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null) {
            throw new ValidationException("Coordinates can't be null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Gets the creation date of the flat.
     * @return The creation date of the flat.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(ZonedDateTime creationDate) {
        // if (creationDate == null) {
        // throw new ValidationException("Creation date can't be null");
        // }
        this.creationDate = creationDate;
    }

    /**
     * Gets the area of the flat.
     * @return The area of the flat.
     */
    public Long getArea() {
        return area;
    }

    /**
     * Sets the area of the flat.
     * @param area The area to set.
     * @throws ValidationException If the area fails validation.
     */
    public void setArea(Long area) throws ValidationException {
        if (area <= 0 || area > 570 || area == null) {
            throw new ValidationException("Area should be more than 0 and less than 570");
        }
        this.area = area;
    }

    /**
     * Sets the area of the flat from a string.
     * @param areaString The area as a string.
     * @throws ValidationException If the area fails validation or is not a number.
     */
    public void setArea(String areaString) throws ValidationException {
        try {
            Long ariaLong = Long.parseLong(areaString);
            setArea(ariaLong);
        } catch (NumberFormatException e) {
            throw new ValidationException("Area should be a number");
        }
    }

    /**
     * Gets the number of rooms in the flat.
     * @return The number of rooms in the flat.
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the number of rooms in the flat.
     * @param numberOfRooms The number of rooms to set.
     * @throws ValidationException If the number of rooms fails validation.
     */
    public void setNumberOfRooms(int numberOfRooms) throws ValidationException {
        if (numberOfRooms <= 0) {
            throw new ValidationException("Number of rooms should be more than 0");
        }
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Sets the number of rooms in the flat from a string.
     * @param numberOfRoomsString The number of rooms as a string.
     * @throws ValidationException If the number of rooms fails validation or is not a number.
     */
    public void setNumberOfRooms(String numberOfRoomsString) throws ValidationException {
        try {
            int numberOfRoomsInt = Integer.parseInt(numberOfRoomsString);
            this.setNumberOfRooms(numberOfRoomsInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Number of rooms should be a number");
        }
    }

    /**
     * Gets the height of the flat.
     * @return The height of the flat.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the height of the flat.
     * @param height The height to set.
     * @throws ValidationException If the height fails validation.
     */
    public void setHeight(Integer height) throws ValidationException {
        if (height <= 0) {
            throw new ValidationException("Height should be more than 0");
        }
        this.height = height;
    }

    /**
     * Sets the height of the flat from a string.
     * @param height The height as a string.
     * @throws ValidationException If the height fails validation or is not a number.
     */
    public void setHeight(String height) throws ValidationException, NullPointerException {
        try {
            Integer heightInt = Integer.parseInt(height);
            this.setHeight(heightInt);
        } catch (NumberFormatException e) {
            throw new ValidationException("Height should be a number");
        }
    }

    /**
     * Gets the furnish of the flat.
     * @return The furnish of the flat.
     */
    public Furnish getFurnish() {
        return furnish;
    }

    /**
     * Sets the furnish of the flat.
     * @param furnish The furnish to set.
     */
    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    /**
     * Gets the transport of the flat.
     * @return The transport of the flat.
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Sets the transport of the flat.
     * @param transport The transport to set.
     * @throws ValidationException If the transport fails validation.
     */
    public void setTransport(Transport transport) throws ValidationException {
        if (transport == null) {
            throw new ValidationException("Transport can't be null");
        }
        this.transport = transport;
    }

    /**
     * Gets the house of the flat.
     * @return The house of the flat.
     */
    public House getHouse() {
        return house;
    }

    /**
     * Sets the house of the flat.
     * @param house The house to set.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Returns a string representation of the flat.
     * @return A string representation of the flat.
     */
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
