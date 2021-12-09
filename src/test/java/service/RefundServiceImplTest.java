package service;

import com.google.common.collect.ImmutableList;
import exception.OrderNotFoundException;
import model.Refund;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.OrdersRepository;
import repository.RefundsRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class RefundServiceImplTest {

    @Mock
    private OrderServiceImpl orderServiceImpl;

    @Mock
    private RefundsRepository refundsRepository;

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private RefundServiceImpl refundService;

    @Test
    void whenOrderIsNotCreated_gettingRefundsByOrder_thenExceptionIsThrown() throws OrderNotFoundException {
        doReturn(false).when(ordersRepository).exists(anyInt());
        System.out.println();

        assertThrows(OrderNotFoundException.class, () -> refundService.getRefundsByOrder(1));
    }

    @Test
    void whenOrderIsCreated_gettingRefundsByOrder_thenOrderIsReturned() throws OrderNotFoundException {
        final List<Refund> expectedRefunds = ImmutableList.of(new Refund(1, 1, Collections.emptyList()));

        doReturn(true).when(ordersRepository).exists(anyInt());
        doReturn(expectedRefunds).when(refundsRepository).getRefunds(1);

        List<Refund> refundsByOrder = refundService.getRefundsByOrder(1);

        assertThat(refundsByOrder).containsAll(expectedRefunds);
    }

    @Test
    void whenOrderIsNotCreated_creatingRefund_thenExceptionIsThrown() throws OrderNotFoundException {
        final Refund refund = new Refund(1, 1, Collections.emptyList());

        doReturn(false).when(ordersRepository).exists(anyInt());

        assertThrows(OrderNotFoundException.class, () -> refundService.createRefund(refund));
    }

   //TODO: I would add more tests, to check all the exceptions thrown in RefundServiceImpl (ItemNotFoundException and ItemQuantityErrorException)
   //TODO: Finally it is needed a test to check the refund is created if the input values are correct
}