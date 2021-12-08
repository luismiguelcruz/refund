package service;

import exception.ItemNotFoundException;
import exception.ItemQuantityErrorException;
import exception.OrderNotFoundException;
import exception.RefundNotFoundException;
import model.Refund;

import java.util.List;

public interface RefundService {

    public List<Refund> getRefundsByOrder(final int idOrder) throws OrderNotFoundException;

    public Refund createRefund(final Refund refund) throws RefundNotFoundException, OrderNotFoundException, ItemNotFoundException, ItemQuantityErrorException;
}
