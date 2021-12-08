package model;

import java.util.List;

public class Order {
    private final int id;
    private final int idTransaction;
    private final List<Item> items;

    public Order(int id, int idTransaction, List<Item> items) {
        this.id = id;
        this.idTransaction = idTransaction;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public List<Item> getItems() {
        return items;
    }
}
