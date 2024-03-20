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

public class CollectionManager {
    private TreeSet<Flat> collection = new TreeSet<>();
    private java.util.Date creationDate;

    public CollectionManager() {
        this.creationDate = new Date();
        this.loadCollection(Settings.saveFilePath);
    }

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

    public Flat get(int id) {
        for (Flat flat : this.collection) {
            if (flat.getId() == id) {
                return flat;
            }
        }
        return null;
    }

    public Flat getMin() {
        return collection.first();
    }

    public boolean add(Flat element) {
        return this.collection.add(element);
    }

    public boolean remove(int id) {
        return this.collection.removeIf(flat -> flat.getId() == id);
    }

    public void clear() {
        this.collection.clear();
    }

    public TreeSet<Flat> getCollection() {
        return this.collection;
    }

    public Integer size() {
        return this.collection.size();
    }

    public boolean removeLower(Flat flat) {
        return this.collection.removeIf(flat1 -> flat1.compareTo(flat) < 0);
    }

    public Flat getMaxByArea() {
        return this.collection.stream().max(Flat::compareTo).get();
    }

    public TreeSet<Flat> filterLessThenHouse(House house) {
        return this.collection.stream().filter(flat -> flat.getHouse().compareTo(house) < 0).collect(TreeSet::new,
                TreeSet::add, TreeSet::addAll);
    }

    @Override
    public String toString() {
        return String.format("Тип коллекции: %s, Дата создания: %s, Кол-во элементов: %s",
                this.collection.getClass().getSimpleName(), this.creationDate, this.collection.size());
    }

}
