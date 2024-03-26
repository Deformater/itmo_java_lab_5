package org.itmo.manager;

import java.util.TreeSet;

import org.itmo.models.Flat;
import org.itmo.models.House;
import org.itmo.serializers.FlatSerializer;
import org.itmo.utils.Settings;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The CollectionManager class represents a manager for a collection of Flat
 * objects.
 */
public class CollectionManager {
    private TreeSet<Flat> collection = new TreeSet<>();
    private java.util.Date creationDate;

    /**
     * Constructs a CollectionManager object.
     * Initializes the creation date and loads the collection from a file.
     */
    public CollectionManager() {
        this.creationDate = new Date();
        this.loadCollection(Settings.saveFilePath);
    }

    /**
     * Loads the collection from a file.
     *
     * @param path the path of the file to load the collection from
     */
    private void loadCollection(String path) {
        File file = new File(path);
        if (file.getTotalSpace() == 0) {
            System.err.println("Файл пуст");
            System.exit(1);
        }

        try (Scanner scanner = new Scanner(file)) {
            String line = scanner.nextLine();
            line = scanner.nextLine();
            while (!line.equals("]")) {
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1);
                }
                Flat flat = FlatSerializer.jsonLoads(line);
                this.collection.add(flat);
                line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (Exception e) {
            System.err.println("Неправильный формат файла");
        }
    }

    /**
     * Retrieves the Flat object with the specified ID from the collection.
     *
     * @param id the ID of the Flat object to retrieve
     * @return the Flat object with the specified ID, or null if not found
     */
    public Flat get(int id) {
        for (Flat flat : this.collection) {
            if (flat.getId() == id) {
                return flat;
            }
        }
        return null;
    }

    /**
     * Retrieves the Flat object with the minimum value based on natural ordering
     * from the collection.
     *
     * @return the Flat object with the minimum value based on natural ordering
     */
    public Flat getMin() {
        return collection.first();
    }

    /**
     * Adds a Flat object to the collection.
     *
     * @param element the Flat object to add
     * @return true if the Flat object was added successfully, false otherwise
     */
    public boolean add(Flat element) {
        return this.collection.add(element);
    }

    /**
     * Removes the Flat object with the specified ID from the collection.
     *
     * @param id the ID of the Flat object to remove
     * @return true if the Flat object was removed successfully, false otherwise
     */
    public boolean remove(int id) {
        return this.collection.removeIf(flat -> flat.getId() == id);
    }

    /**
     * Clears the collection, removing all Flat objects.
     */
    public void clear() {
        this.collection.clear();
    }

    /**
     * Retrieves the collection of Flat objects.
     *
     * @return the collection of Flat objects
     */
    public TreeSet<Flat> getCollection() {
        return this.collection;
    }

    /**
     * Retrieves the size of the collection.
     *
     * @return the size of the collection
     */
    public Integer size() {
        return this.collection.size();
    }

    /**
     * Removes all Flat objects from the collection that are lower than the
     * specified Flat object.
     *
     * @param flat the Flat object to compare against
     * @return true if any Flat objects were removed, false otherwise
     */
    public boolean removeLower(Flat flat) {
        return this.collection.removeIf(flat1 -> flat1.compareTo(flat) < 0);
    }

    /**
     * Retrieves the Flat object with the maximum area from the collection.
     *
     * @return the Flat object with the maximum area
     */
    public Flat getMaxByArea() {
        return this.collection.stream().max(Flat::compareTo).get();
    }

    /**
     * Filters the collection and retrieves a new TreeSet containing all Flat
     * objects with a House object
     * that is less than the specified House object.
     *
     * @param house the House object to compare against
     * @return a new TreeSet containing all Flat objects with a House object that is
     *         less than the specified House object
     */
    public TreeSet<Flat> filterLessThenHouse(House house) {
        return this.collection.stream().filter(flat -> flat.getHouse().compareTo(house) < 0).collect(TreeSet::new,
                TreeSet::add, TreeSet::addAll);
    }

    /**
     * Returns a string representation of the CollectionManager object.
     *
     * @return a string representation of the CollectionManager object
     */
    @Override
    public String toString() {
        return String.format("Тип коллекции: %s, Дата создания: %s, Кол-во элементов: %s",
                this.collection.getClass().getSimpleName(), this.creationDate, this.collection.size());
    }
}
