package manager;
import java.util.TreeSet;

import models.Flat;

public class CollectionManager {
    private TreeSet<Flat> collection = new TreeSet<>();

    public boolean add(Flat element) {
        return collection.add(element);
    }

    public boolean remove(int id) {
        return collection.removeIf(flat -> flat.getId() == id);
    }

    public TreeSet<Flat> getCollection() {
        return collection;
    }

}

