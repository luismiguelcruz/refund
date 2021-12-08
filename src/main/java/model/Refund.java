package model;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class Refund {
    private int id;
    private int idOrder;
    private List<Item> items;

    public Refund(int id, int idOrder, List<Item> items) {
        this.id = id;
        this.idOrder = idOrder;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double total() {
        if (CollectionUtils.isEmpty(items)) {
            return 0D;
        }

        return items.stream()
                .map(item -> item.getTotalPrice())
                .reduce(0D, Double::sum);
    }
}
