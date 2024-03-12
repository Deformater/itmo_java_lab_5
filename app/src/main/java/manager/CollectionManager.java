package manager;

import java.util.TreeSet;

import com.google.gson.Gson;

import java.util.Date;
import models.Flat;
import serializers.FlatSerializer;
import utils.Settings;

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
        File file = new File(path); // Replace with your file's path

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
            System.err.println("Неправильный формат файла" + e.getMessage());
        }

    }

    public Flat get(int id) {
        for (Flat flat : collection) {
            if (flat.getId() == id) {
                return flat;
            }
        }
        return null;
    }

    public boolean add(Flat element) {
        return collection.add(element);
    }

    public boolean remove(int id) {
        return collection.removeIf(flat -> flat.getId() == id);
    }

    public void clear() {
        collection.clear();
    }

    public TreeSet<Flat> getCollection() {
        return collection;
    }

    public Integer size() {
        return this.collection.size();
    }

    @Override
    public String toString() {
        return String.format("Тип коллекции: %s, Дата создания: %s, Кол-во элементов: %s",
                this.collection.getClass().getSimpleName(), this.creationDate, this.collection.size());
    }

}
