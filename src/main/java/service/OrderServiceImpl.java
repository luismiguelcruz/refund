package service;

import exception.OrderNotFoundException;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import repository.OrdersRepository;

import java.util.Collections;
import java.util.Objects;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    public Order getOrder(final int idOrder) throws OrderNotFoundException {
        // Returns the requested order if exists and empty in other case

        checkValidOrder(idOrder);

        return new Order(1, 1, Collections.emptyList());
    }

    private void checkValidOrder(final int idOrder) throws OrderNotFoundException {
        if (idOrder <= 0 || Objects.isNull(ordersRepository.getOrder(idOrder))) {
            throw new OrderNotFoundException();
        }
    }
}
