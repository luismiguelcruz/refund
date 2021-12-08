package repository;

import model.Order;

import java.util.Collections;

public class OrdersRepository {

    public Order getOrder(final int idOrder) {
        return new Order(1, 1, Collections.emptyList());
    }

    public boolean exists(final int idOrder) {
        return true;
    }
}
