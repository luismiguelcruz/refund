package service;

import exception.ItemNotFoundException;
import exception.ItemQuantityErrorException;
import exception.OrderNotFoundException;
import exception.RefundNotFoundException;
import model.Item;
import model.Refund;
import org.springframework.util.CollectionUtils;
import repository.OrdersRepository;
import repository.RefundsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RefundServiceImpl implements RefundService {

    private OrderServiceImpl orderServiceImpl;
    private RefundsRepository refundsRepository;
    private OrdersRepository ordersRepository;

    public RefundServiceImpl(OrderServiceImpl orderServiceImpl, RefundsRepository refundsRepository, OrdersRepository ordersRepository) {
        this.orderServiceImpl = orderServiceImpl;
        this.refundsRepository = refundsRepository;
        this.ordersRepository = ordersRepository;
        System.out.println();
    }

    @Override
    public List<Refund> getRefundsByOrder(final int idOrder) throws OrderNotFoundException {
        // Returns all the refunds by order
        if (!ordersRepository.exists(idOrder)) {
            throw new OrderNotFoundException();
        }

        return refundsRepository.getRefunds(idOrder);
    }

    @Override
    public Refund createRefund(final Refund refund)
            throws RefundNotFoundException, OrderNotFoundException, ItemNotFoundException, ItemQuantityErrorException {
        // Creates a refund if the order is correct and we didn't refund all the money for an order yet
        checkValidRefund(refund);

        return refundsRepository.createRefund(refund);
    }

    @Override
    public void deleteRefund(final int idRefund) throws RefundNotFoundException, OrderNotFoundException {
        if (!refundsRepository.exists(idRefund)) {
            throw new OrderNotFoundException();
        }

        final Optional<Refund> refund = refundsRepository.getRefund(idRefund);
        if (!refund.isPresent()) {
            throw new RefundNotFoundException();
        }

        refundsRepository.removeRefund(idRefund);
    }

    private void checkValidRefund(final Refund refund)
            throws RefundNotFoundException, OrderNotFoundException, ItemNotFoundException, ItemQuantityErrorException {
        if (refund.getIdOrder() <= 0 || getRefundsByOrder(refund.getIdOrder()).isEmpty()) {
            throw new RefundNotFoundException();
        }

        // Getting all the Items for the existing order;
        final Map<Integer, Item> orderedItems = orderServiceImpl.getOrder(refund.getIdOrder()).getItems().stream()
                .collect(Collectors.toMap(x -> x.getId(), x -> x));

        // Get all the refunds already paid (it means all the items created)
        final Map<Integer, Item> refundedItems = buildPaidRefunds(refund).stream()
                .collect(Collectors.toMap(x -> x.getId(), x -> x));;

        // checking the refund is correct and we didn't pay all the refund for an specific item already
        for (Item currentItem: refund.getItems()) {
            checkValidRefundItem(currentItem, orderedItems, refundedItems);
        }
    }

    private void checkValidRefundItem(final Item currentItem, final Map<Integer, Item> orderedItems,
                  final Map<Integer, Item> refundedItems) throws ItemNotFoundException, ItemQuantityErrorException {
        if (!orderedItems.containsKey(currentItem.getId())) {
            // If the item id is incorrect return item not found
            throw new ItemNotFoundException();
        }

        // If the item hasn't been refunded yet, and the client requires more items refunded than the quantity present in the order
        if (!refundedItems.containsKey(currentItem.getId()) &&
                orderedItems.get(currentItem.getId()).getQuantity() < currentItem.getQuantity()) {
            throw new ItemQuantityErrorException();
        }

        // If the item has been refunded, we have to ensure if the item quantity bouught + the refunded item quantity is not
        if (refundedItems.get(currentItem.getId()).getQuantity() + currentItem.getQuantity() > orderedItems.get(currentItem.getId()).getQuantity()) {
            throw new ItemQuantityErrorException();
        }
    }

    // Group all the items already refunded in a list
    private List<Item> buildPaidRefunds(Refund refund) throws OrderNotFoundException {
        final List<List<Item>> unorderedPaidItems = getRefundsByOrder(refund.getIdOrder()).stream()
                .map(refundPaid -> refundPaid.getItems())
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(unorderedPaidItems)) {
            return Collections.emptyList();
        }

        final Map<Integer, Item> itemsPaid = unorderedPaidItems.get(0).stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x));

        for(int i=1; i<unorderedPaidItems.size(); i++) {
            for(Item currentItem: unorderedPaidItems.get(i)) {
                if (!itemsPaid.containsKey(currentItem.getId())) {
                    itemsPaid.put(currentItem.getId(), currentItem);
                } else {
                    final Item existingItem = itemsPaid.get(currentItem.getId());

                    final Item updatedItem = new Item(existingItem.getId(),existingItem.getType(),
                            existingItem.getName(), existingItem.getPrice(),
                            existingItem.getQuantity()+currentItem.getQuantity());

                    itemsPaid.put(currentItem.getId(), updatedItem);
                }
            }
        }

        return itemsPaid.values().stream()
                .collect(Collectors.toList());
    }
}
