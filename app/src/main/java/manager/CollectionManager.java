package manager;

import java.util.TreeSet;
import java.util.Date;
import models.Flat;

public class CollectionManager {
    private TreeSet<Flat> collection = new TreeSet<>();
    private java.util.Date creationDate;

    public CollectionManager() {
        this.creationDate = new Date();
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
