package service;

import exception.OrderNotFoundException;
import model.Order;

public interface OrderService {

    public Order getOrder(final int idOrder) throws OrderNotFoundException;
}
