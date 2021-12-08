package model;

public class Item {
    private final int id;
    private final ItemType type;
    private final String name;
    private final double price;
    private final int quantity;

    public Item(int id, ItemType type, String name, double price, int quantity) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}
